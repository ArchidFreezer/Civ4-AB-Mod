package org.archid.civ4.gui;

import java.awt.BorderLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;


import org.apache.log4j.Logger;
import org.archid.civ4.schema.HtmlGenerator;
import org.archid.civ4.schema.ISchemaPropertyhandler;
import org.archid.civ4.schema.SchemaParser;
import org.archid.civ4.schema.SchemaPropertyHandler;
import org.archid.civ4.schema.SchemaPropertyKeys;
import org.archid.civ4.schema.XmlTagDefinition;

@SuppressWarnings("serial")
public class SchemaHelpGenerator extends JPanel implements ActionListener {

	/** Logging facility */
	static Logger log = Logger.getLogger(SchemaHelpGenerator.class.getName());

	static private final String newline = "\n";
	private JButton openButton, civButton, parseButton, outputButton;
	private JTextArea textArea;
	private JFileChooser fc;
	private File userSchema, btsSchema, outputDir;
	private ISchemaPropertyhandler props = SchemaPropertyHandler.getInstance();

	public SchemaHelpGenerator() {

		super(new BorderLayout());

		// Create the log first, because the action listeners
		// need to refer to it.
		textArea = new JTextArea(5, 20);
		textArea.setMargin(new Insets(5, 5, 5, 5));
		textArea.setEditable(false);
		JScrollPane logScrollPane = new JScrollPane(textArea);

		// Create a file chooser
		fc = new JFileChooser();

		// Uncomment one of the following lines to try a different
		// file selection mode. The first allows just directories
		// to be selected (and, at least in the Java look and feel,
		// shown). The second allows both files and directories
		// to be selected. If you leave these lines commented out,
		// then the default mode (FILES_ONLY) will be used.
		//
		// fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		// fc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);

		// Create the open button. We use the image from the JLF
		// Graphics Repository (but we extracted it from the jar).
		openButton = new JButton("Open Schema File...");
		openButton.addActionListener(this);

		civButton = new JButton("BTS Schema File...");
		civButton.addActionListener(this);

		outputButton = new JButton("Output Dir...");
		outputButton.addActionListener(this);

		// Create the save button. We use the image from the JLF
		// Graphics Repository (but we extracted it from the jar).
		parseButton = new JButton("Parse");
		parseButton.addActionListener(this);

		// For layout purposes, put the buttons in a separate panel
		JPanel buttonPanel = new JPanel(); // use FlowLayout
		buttonPanel.add(openButton);
		buttonPanel.add(civButton);
		buttonPanel.add(outputButton);
		buttonPanel.add(parseButton);

		// Add the buttons and the log to this panel.
		add(buttonPanel, BorderLayout.PAGE_START);
		add(logScrollPane, BorderLayout.CENTER);
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		// Handle open button action.
		if (e.getSource() == openButton) {
			String dir = props.getAppProperty(SchemaPropertyKeys.PROPERTY_KEY_MOD_SCHEMA_DIR, ".");
			try {
				fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
				fc.setCurrentDirectory(new File(new File(dir).getCanonicalPath()));
			} catch (IOException e1) {
				log.error("Error loading file chooser", e1);
			}
			int returnVal = fc.showOpenDialog(SchemaHelpGenerator.this);
			if (returnVal == JFileChooser.APPROVE_OPTION) {
				userSchema = fc.getSelectedFile();
				if (!dir.equals(fc.getSelectedFile().getParent()))
					props.setAppProperty(SchemaPropertyKeys.PROPERTY_KEY_MOD_SCHEMA_DIR, fc.getSelectedFile().getParent());
				textArea.append("User schema: " + userSchema.getName() + "." + newline);
			} else {
				textArea.append("Open command cancelled by user." + newline);
			}
			textArea.setCaretPosition(textArea.getDocument().getLength());
		} else if (e.getSource() == civButton) {
			String dir = props.getAppProperty(SchemaPropertyKeys.PROPERTY_KEY_REF_SCHEMA_DIR, ".");
			try {
				fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
				fc.setCurrentDirectory(new File(new File(dir).getCanonicalPath()));
			} catch (IOException e1) {
				log.error("Error loading file chooser", e1);
			}
			int returnVal = fc.showOpenDialog(SchemaHelpGenerator.this);
			if (returnVal == JFileChooser.APPROVE_OPTION) {
				btsSchema = fc.getSelectedFile();
				if (!dir.equals(fc.getSelectedFile().getParent()))
					props.setAppProperty(SchemaPropertyKeys.PROPERTY_KEY_REF_SCHEMA_DIR, fc.getSelectedFile().getParent());
				textArea.append("BTS schema: " + btsSchema.getName() + "." + newline);
			} else {
				textArea.append("Open command cancelled by user." + newline);
			}
			textArea.setCaretPosition(textArea.getDocument().getLength());
		} else if (e.getSource() == outputButton) {
			String dir = props.getAppProperty(SchemaPropertyKeys.PROPERTY_KEY_HTML_OUTPUT_DIR, ".");
			try {
				fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				fc.setCurrentDirectory(new File(new File(dir).getCanonicalPath()));
			} catch (IOException e1) {
				log.error("Error loading file chooser", e1);
			}
			int returnVal = fc.showOpenDialog(SchemaHelpGenerator.this);
			if (returnVal == JFileChooser.APPROVE_OPTION) {
				outputDir = fc.getSelectedFile();
				if (!dir.equals(fc.getSelectedFile().getPath()))
					props.setAppProperty(SchemaPropertyKeys.PROPERTY_KEY_HTML_OUTPUT_DIR, fc.getSelectedFile().getPath());
				textArea.append("Output dir: " + outputDir.getName() + "." + newline);
			} else {
				textArea.append("Open command cancelled by user." + newline);
			}
			textArea.setCaretPosition(textArea.getDocument().getLength());
		} else if (e.getSource() == parseButton) {
			try {
				if (userSchema == null) {
					textArea.append("You must select the schema to parse" + newline);
				} else if (btsSchema == null) {
					textArea.append("You must select the bts schema to compare to" + newline);
				} else {
					textArea.append("Parsing schema ... " + newline);
					SchemaParser parser = new SchemaParser();
					parser.parse(userSchema.getCanonicalPath(), btsSchema.getCanonicalPath());
					Map<String, Set<XmlTagDefinition>> printableTags = new HashMap<String, Set<XmlTagDefinition>>();
					printableTags.put(props.getAppProperty(SchemaPropertyKeys.PROPERTY_KEY_MOD_SCHEMA_DIR, "."), parser.getPrintableTags());
					HtmlGenerator htmlGen = new HtmlGenerator(parser);
					htmlGen.printAllTags(printableTags, false);
					
					// If the output folder doesn't contain the named stylesheet add it
					Path stylesheetPath = Paths.get(props.getAppProperty(SchemaPropertyKeys.PROPERTY_KEY_HTML_OUTPUT_DIR, "."), props.getAppProperty(SchemaPropertyKeys.PROPERTY_KEY_HTML_STYLESHEET)); 
					if (!Files.exists(stylesheetPath))
						htmlGen.createStylesheet();
					
					textArea.append("Done." + newline);
				}
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}

}
