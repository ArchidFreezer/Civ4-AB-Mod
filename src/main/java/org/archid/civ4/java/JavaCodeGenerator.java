package org.archid.civ4.java;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import org.apache.log4j.Logger;
import org.archid.civ4.java.infoprocessor.IInfoProcessor;
import org.archid.civ4.java.infoprocessor.IInfoProcessor.FileCreators;
import org.archid.civ4.schema.SchemaParser;
import org.archid.utils.FileUtils;
import org.archid.utils.IPropertyHandler;
import org.archid.utils.PropertyHandler;
import org.archid.utils.PropertyKeys;

public class JavaCodeGenerator implements IJavaFileContent{

	/** Logging facility */
	static Logger log = Logger.getLogger(JavaCodeGenerator.class.getName());
	
	private IPropertyHandler props = PropertyHandler.getInstance();
	
	public JavaCodeGenerator(SchemaParser parser, String infoTopLevelTag) {
		createPackageFolder(infoTopLevelTag.substring(4, infoTopLevelTag.length() - 5).toLowerCase());
		JavaCodeGeneratorData.getInstance().init(parser, infoTopLevelTag);
	}

	public void createJavaCode() {
		createInfoProcessorCustomFiles();
		createPackageInfo();
		createInfoInterface();
		createInfoClass();
		createInfoWorkbookInterface();
		createInfoMapAdapter();
		createInfoExporter();
		createInfoImporter();
	}
	
	private void createInfoProcessorCustomFiles() {
		IInfoProcessor infoProcessor = JavaCodeGeneratorData.getInstance().getInfoProcessor();
		if (infoProcessor != null) {
			for (String filename: infoProcessor.getFilesToWrite().keySet()) {
				writeFile(filename, infoProcessor.getFilesToWrite().get(filename));
			}
		}
	}
	
	private void createInfoImporter() {
		writeFile(JavaCodeGeneratorData.getInstance().getInfoNameRoot() + "Importer.java", JavaCodeGeneratorData.getInstance().getInfoProcessor().getFileCreator(FileCreators.INFO_IMPORTER).getFileContent());
	}
	
	private void createInfoExporter() {
		writeFile(JavaCodeGeneratorData.getInstance().getInfoNameRoot() + "Exporter.java", JavaCodeGeneratorData.getInstance().getInfoProcessor().getFileCreator(FileCreators.INFO_EXPORTER).getFileContent());
	}
	
	private void createInfoMapAdapter() {
		writeFile(JavaCodeGeneratorData.getInstance().getInfoNameRoot() + "MapAdapter.java", JavaCodeGeneratorData.getInstance().getInfoProcessor().getFileCreator(FileCreators.INFO_MAP_ADAPTER).getFileContent());
	}

	private void createInfoWorkbookInterface() {
		writeFile("I" + JavaCodeGeneratorData.getInstance().getInfoNameRoot() + "Workbook.java", JavaCodeGeneratorData.getInstance().getInfoProcessor().getFileCreator(FileCreators.INFO_WORKBOOK).getFileContent());
	}

	private void createInfoClass() {
		writeFile(JavaCodeGeneratorData.getInstance().getInfoNamePlural() + ".java", JavaCodeGeneratorData.getInstance().getInfoProcessor().getFileCreator(FileCreators.INFO_CODE).getFileContent());
	}

	private void createInfoInterface() {
		writeFile("I" + JavaCodeGeneratorData.getInstance().getInfoName() + ".java", JavaCodeGeneratorData.getInstance().getInfoProcessor().getFileCreator(FileCreators.INFO_INTERFACE).getFileContent());
	}

	private void createPackageInfo() {
		writeFile("package-info.java", JavaCodeGeneratorData.getInstance().getInfoProcessor().getFileCreator(FileCreators.PACKAGE).getFileContent());
	}

	private void createPackageFolder(String namespaceFolder) {
		String folderPath = props.getAppProperty(PropertyKeys.PROPERTY_KEY_JAVA_OUTPUT_DIR, ".") + "\\" + namespaceFolder;
		FileUtils.ensureDirExists(folderPath);
	}
	
	private void writeFile(String fileName, String content) {
		BufferedWriter out = null;
		String filePath = props.getAppProperty(PropertyKeys.PROPERTY_KEY_JAVA_OUTPUT_DIR, ".") + "\\" + JavaCodeGeneratorData.getInstance().getNamespaceFolder() + "\\" + fileName;
		try {
			out = new BufferedWriter(new FileWriter(filePath));
			out.write(content);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (null != out)
					out.close();
			} catch (IOException e) {
				log.error("Error closing output file!");
			}
		}
	}
	
}
