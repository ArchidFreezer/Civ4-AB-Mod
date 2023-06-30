package org.archid.civ4.main;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.OptionGroup;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.archid.civ4.info.ConverterFactory;
import org.archid.civ4.info.EInfo;
import org.archid.civ4.info.ExporterFactory;
import org.archid.civ4.info.IExporter;
import org.archid.civ4.info.ImporterFactory;
import org.archid.utils.IPropertyHandler;
import org.archid.utils.PropertyHandler;
import org.archid.utils.PropertyKeys;

/**
 * Hello world!
 *
 */
public class InfoUtils {

	/** Logging facility */
	static Logger log = Logger.getLogger(InfoUtils.class.getName());

	/** Command line parser */
	private String[] args = null;
	private Options options = new Options();
	private CommandLine cmd = null;
	private IPropertyHandler props = PropertyHandler.getInstance();
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {

		PropertyConfigurator.configure("log4j.properties");
		
		InfoUtils app = new InfoUtils(args);
		app.parse();
	}
	
	private InfoUtils(String[] args) {
		this.args = args;
		
		OptionGroup actions = new OptionGroup();
		actions.setRequired(true);
		actions.addOption(Option.builder("c").longOpt("convert").hasArg(false).desc("Convert from OOB to mod XML").build());
		actions.addOption(Option.builder("i").longOpt("import").hasArg(false).desc("Import from XLSX file").build());
		actions.addOption(Option.builder("e").longOpt("export").hasArg(false).desc("Export to XLSX file").build());
		actions.addOption(Option.builder("h").longOpt("help").hasArg(false).desc("Display this usage message").build());
		options.addOptionGroup(actions);

		options.addOption(Option.builder("f").longOpt("file").required().hasArg(true).argName("XML").desc("Path to Civ4xxxInfos.xml file to process or folder containing files for All option. Filename must be provided if it is non standard").build());
		options.addOption(Option.builder("o").longOpt("output").hasArg(true).argName("Output Dir").desc("Directory to create output files").build());
		options.addOption(Option.builder("p").longOpt("prefix").hasArg(true).argName("Prefix").desc("Prefix to new output file").build());
		options.addOption(Option.builder("t").longOpt("type").required().hasArg(true).argName("Info Type").desc("Type of info to process, such as Tech, Era; can be All to process all known files").build());
		options.addOption(Option.builder("x").longOpt("xlsx").hasArg(true).argName("XLSX").desc("XLSX file to use, required for import action").build());
	}

	private void parse() {		
		CommandLineParser parser = new DefaultParser();
		try {
			EInfo infoType = null;
			cmd = parser.parse(options, args);
			
			if (cmd.hasOption("o"))
				props.setAppProperty(PropertyKeys.PROPERTY_KEY_OUTPUT_DIR, cmd.getOptionValue("o"));
			else
				props.removeAppProperty(PropertyKeys.PROPERTY_KEY_OUTPUT_DIR);
			if (cmd.hasOption("p"))
				props.setAppProperty(PropertyKeys.PROPERTY_KEY_FILE_PREFIX, cmd.getOptionValue("p"));
			if (cmd.hasOption("t")) {
				if (cmd.getOptionValue("t").equalsIgnoreCase("all"))
					infoType = null;
				else if (cmd.getOptionValue("t").equalsIgnoreCase("bonus"))
					infoType = EInfo.BONUS;
				else if (cmd.getOptionValue("t").equalsIgnoreCase("bonusclass"))
					infoType = EInfo.BONUS_CLASS;
				else if (cmd.getOptionValue("t").equalsIgnoreCase("building"))
					infoType = EInfo.BUILDING;
				else if (cmd.getOptionValue("t").equalsIgnoreCase("buildingclass"))
					infoType = EInfo.BUILDING_CLASS;
				else if (cmd.getOptionValue("t").equalsIgnoreCase("civic"))
					infoType = EInfo.CIVIC;
				else if (cmd.getOptionValue("t").equalsIgnoreCase("era"))
					infoType = EInfo.ERA;
				else if (cmd.getOptionValue("t").equalsIgnoreCase("event"))
					infoType = EInfo.EVENT;
				else if (cmd.getOptionValue("t").equalsIgnoreCase("eventtrigger"))
					infoType = EInfo.EVENT_TRIGGER;
				else if (cmd.getOptionValue("t").equalsIgnoreCase("tech"))
					infoType = EInfo.TECH;
				else if (cmd.getOptionValue("t").equalsIgnoreCase("unit"))
					infoType = EInfo.UNIT;
				else
					throw new ParseException("Processing of type " + cmd.getOptionValue("t") + " is not implemented yet");
			}
			Path path = null;
			if (cmd.hasOption("f")) {
				String pathVal = cmd.getOptionValue("f");
				path = Paths.get(pathVal);
				if (!Files.exists(path))
					throw new ParseException("Path contained in 'f' argument does not exist");
				else if (!Files.isDirectory(path) && infoType == null)
					throw new ParseException("Path contained in 'f' argument must be a folder when processing all types");
				else if (Files.isDirectory(path) && infoType != null)
					// If this is a folder then append the OOB filename for the info type
					pathVal = Paths.get(pathVal, infoType.getFolder(), infoType.toString()).toString();
				props.setAppProperty(PropertyKeys.PROPERTY_KEY_FILE_INFOS, pathVal);
			}
			if (cmd.hasOption("x")) {
				props.setAppProperty(PropertyKeys.PROPERTY_KEY_FILE_XSLX, cmd.getOptionValue("x"));
			}

			if (cmd.hasOption("c")) {
				if (infoType == null)
					throw new ParseException("Batch converting all types is not implemented yet");
				ConverterFactory.getConverter(infoType).convert();
				log.info("Finished");
			} else if (cmd.hasOption("e")) {
				if (infoType != null) {
					ExporterFactory.getExporter(infoType).createXLSX();
				} else {
					// We are trying to process all known files so we need to walk through the files structure looking for 
					// xml files that we know how to process
					KnownInfosVisitor visitor = new KnownInfosVisitor();
					Files.walkFileTree(path, visitor);
					Map<EInfo, Path> infos = visitor.getInfos();
					for (EInfo info: infos.keySet()) {
						props.setAppProperty(PropertyKeys.PROPERTY_KEY_FILE_INFOS, infos.get(info).toString());
						IExporter exporter = ExporterFactory.getExporter(info);
						exporter.setBackup(false);
						exporter.createXLSX();
					}
				}
				log.info("Finished");
			}	else if (cmd.hasOption("i")) {
				if (cmd.hasOption("x")) {
					if (infoType != null) {
						ImporterFactory.getImporter(infoType).importXLSX();
					} else {
						for (EInfo info: EInfo.values()) {
							if (info != EInfo.UNKNOWN) {
								props.setAppProperty(PropertyKeys.PROPERTY_KEY_FILE_INFOS, Paths.get(path.toString(), info.getFolder(), info.toString()).toString());
								ImporterFactory.getImporter(info).importXLSX();
							}
						}
					}
				} else {
					throw new ParseException("xlsx file to process not provided");
				}
				log.info("Finished");
			}
			
			if (cmd.hasOption("h")) {
				printHelp();
			}
			
		} catch (ParseException | IOException e) {
			log.error(e.getMessage() + System.lineSeparator());
			printHelp();
		}
	}
	
	private void printHelp() {
		HelpFormatter formatter = new HelpFormatter();
		formatter.printHelp(120, "UpdateTechs", "", options, "", true);		
	}
	
	private class KnownInfosVisitor extends SimpleFileVisitor<Path> {
		private Map<EInfo, Path> infos = new HashMap<EInfo, Path>();
		
		@Override
		public FileVisitResult visitFile(Path file, BasicFileAttributes attr) {
			EInfo info = EInfo.fromFile(file.getFileName().toString());
			if (info != EInfo.UNKNOWN) {
				infos.put(info, file);
			}
			return FileVisitResult.CONTINUE;
		}
		
		public Map<EInfo, Path> getInfos() {
			return infos;
		}
	}

}
