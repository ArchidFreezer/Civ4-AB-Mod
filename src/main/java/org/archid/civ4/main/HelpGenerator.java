package org.archid.civ4.main;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.swing.JFrame;
import javax.swing.UIManager;


import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.archid.civ4.gui.SchemaHelpGenerator;
import org.archid.civ4.schema.HtmlGenerator;
import org.archid.civ4.schema.ISchemaPropertyhandler;
import org.archid.civ4.schema.SchemaParser;
import org.archid.civ4.schema.SchemaPropertyHandler;
import org.archid.civ4.schema.XmlTagDefinition;
import org.archid.utils.PropertyKeys;

public class HelpGenerator {

	/** Logging facility */
	static Logger log = Logger.getLogger(HelpGenerator.class.getName());
	
	/** Command line parser */
	private String[] args = null;
	private Options options = new Options();
	private CommandLine cmd = null;
	private ISchemaPropertyhandler props = SchemaPropertyHandler.getInstance();
	
	/** Schema parser */
	SchemaParser parser = new SchemaParser();
	
	public HelpGenerator(String[] args) {
		this.args = args;
		
		options.addOption("a", "parseAll", false, "Recurse the directories, parsing all schema files found");
		options.addOption("f", "fullOutput", false, "Output includes framed page, only valid when parsing all schema files");
		options.addOption("g", "gui", false, "Run the application via a GUI");
		options.addOption("i", "inputSchemaDir", true, "Dir containing schema file to parse");
		options.addOption("I", "inputSchema", true, "Schema file to parse");
		options.addOption("h", "help", false, "Display help");
		options.addOption("o", "outputDir", true, "Directory to create the output in");
		options.addOption("r", "referenceSchemaDir", true, "Dir containing reference schema to compare against");
		options.addOption("R", "referenceSchema", true, "Reference schema to compare against");
		options.addOption("s", "saveProperties", false, "Save properties on exit");
		options.addOption("S", "createStyleSheet", false, "Create a CSS file that formats the output");
	}
	
	public void parse() {
		CommandLineParser cmdParser = new DefaultParser();
		
		try {
			cmd = cmdParser.parse(options, args);
			
			if (cmd.hasOption("h"))
				help();

			if (cmd.hasOption("g")) {
				// Turn off metal's use of bold fonts
				UIManager.put("swing.boldMetal", Boolean.FALSE);
				createAndShowGUI();
			} else {
				// Set the input info first
				if (cmd.hasOption("i"))
					props.setAppProperty(PropertyKeys.PROPERTY_KEY_MOD_SCHEMA_DIR, cmd.getOptionValue("i"));
				if (cmd.hasOption("I"))
					props.setAppProperty(PropertyKeys.PROPERTY_KEY_MOD_SCHEMA, cmd.getOptionValue("I"));
				if (cmd.hasOption("r"))
					props.setAppProperty(PropertyKeys.PROPERTY_KEY_REF_SCHEMA_DIR, cmd.getOptionValue("r"));
				if (cmd.hasOption("R"))
					props.setAppProperty(PropertyKeys.PROPERTY_KEY_REF_SCHEMA, cmd.getOptionValue("R"));
				if (cmd.hasOption("o"))
					props.setAppProperty(PropertyKeys.PROPERTY_KEY_HTML_OUTPUT_DIR, cmd.getOptionValue("o"));
				
				// Now perform the actions
				if (cmd.hasOption("a"))
					processAllSchema();
				else {
					String modSchema = props.getAppProperty(PropertyKeys.PROPERTY_KEY_MOD_SCHEMA_DIR, ".") + "\\" + props.getAppProperty(PropertyKeys.PROPERTY_KEY_MOD_SCHEMA);
					String refSchema = props.getAppProperty(PropertyKeys.PROPERTY_KEY_REF_SCHEMA_DIR, ".") + "\\" + props.getAppProperty(PropertyKeys.PROPERTY_KEY_REF_SCHEMA);
					processSchema(modSchema, refSchema);
				}
				
				if (cmd.hasOption("S"))
					new HtmlGenerator(parser).createStylesheet();
				
				exit(0);
			}
			
			
		} catch (ParseException e) {
			log.error("Failed to parse comand line properties", e);
			help();
		}
	}

	private void processSchema(String modSchema, String refSchema) {
		try {
			parser.parse(modSchema, refSchema);

			Map<String, Set<XmlTagDefinition>> printableTags = new HashMap<String, Set<XmlTagDefinition>>();
			printableTags.put(props.getAppProperty(PropertyKeys.PROPERTY_KEY_MOD_SCHEMA_DIR, "."), parser.getPrintableTags());
			new HtmlGenerator(parser).printAllTags(printableTags, false);
		} catch (FileNotFoundException e) {
			log.error("Could not find schema file", e);
		}
	}

	private void processAllSchema() {
		Map<String, Set<XmlTagDefinition>> printableTags = new HashMap<String, Set<XmlTagDefinition>>();
		Path modSchemaPath = Paths.get(props.getAppProperty(PropertyKeys.PROPERTY_KEY_MOD_SCHEMA_DIR, "."));
		Path refSchemaPath = Paths.get(props.getAppProperty(PropertyKeys.PROPERTY_KEY_REF_SCHEMA_DIR, "."));
		try {
			MyFileVisitor modVisitor = new MyFileVisitor();
			Files.walkFileTree(modSchemaPath, modVisitor);
			Map<String, Path> modSchemaFiles = modVisitor.getSchemaFiles();
			MyFileVisitor refVisitor = new MyFileVisitor();
			Files.walkFileTree(refSchemaPath, refVisitor);
			Map<String, Path> refSchemaFiles = refVisitor.getSchemaFiles();
			for (String schemaName: modSchemaFiles.keySet()) {
				parser.setSchemaFolder(modSchemaFiles.get(schemaName).getParent().getFileName().toString());
				parser.parse(modSchemaFiles.get(schemaName).toString(), refSchemaFiles.get(schemaName).toString());
				if (printableTags.containsKey(modSchemaFiles.get(schemaName).getParent().getFileName().toString()))
					printableTags.get(modSchemaFiles.get(schemaName).getParent().getFileName().toString()).addAll(parser.getPrintableTags());
				else
					printableTags.put(modSchemaFiles.get(schemaName).getParent().getFileName().toString(), parser.getPrintableTags());
			}
			new HtmlGenerator(parser).printAllTags(printableTags, cmd.hasOption("f"));
		} catch (IOException e) {
			log.error("Error walking file tree from: " + props.getAppProperty(PropertyKeys.PROPERTY_KEY_MOD_SCHEMA_DIR, "."), e);
		}
	}

	private void help() {
		HelpFormatter formatter = new HelpFormatter();
		formatter.printHelp("SchemaProcessor", options);
		exit(0);
	}
	
	private void exit(int exitCode) {
		exit(exitCode, cmd.hasOption("s"));
	}

	private void exit(int exitCode, boolean saveProperties) {
		if (saveProperties)	props.save();
		System.exit(exitCode);
	}

	/**
	 * Creates the GUI main window
	 */
	private void createAndShowGUI() {

		// Create and setup the window
		JFrame frame = new JFrame("SchemaHelpGenerator");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.addWindowListener(new java.awt.event.WindowAdapter() {
			public void windowClosing(java.awt.event.WindowEvent evt) {
				exit(0, true);
			}
		});

		SchemaHelpGenerator app = new SchemaHelpGenerator();

		// Add the content
		frame.add(app);

		// Display the window
		frame.pack();
		frame.setVisible(true);
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		PropertyConfigurator.configure("log4j.properties");
		
		HelpGenerator app = new HelpGenerator(args);
		app.parse();

	}
	
	private class MyFileVisitor extends SimpleFileVisitor<Path> {
		
		private Map<String, Path> schemaFiles = new HashMap<String, Path>();
		
		@Override
		public FileVisitResult visitFile(Path file, BasicFileAttributes attr) {
			if (attr.isRegularFile() && file.toString().endsWith("Schema.xml") && !file.getFileName().toString().equals("CIV4GlobalTypesSchema.xml")) {
				schemaFiles.put(file.getFileName().toString(), file);
			}
			return FileVisitResult.CONTINUE;
		}
		
		public Map<String, Path> getSchemaFiles() {
			return schemaFiles;
		}
	}

}
