package org.archid.civ4.java.files;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.archid.civ4.java.IJavaFileCreator;
import org.archid.civ4.java.ITagProcessor;
import org.archid.civ4.java.JavaCodeGeneratorData;
import org.archid.civ4.java.TagInstance;
import org.archid.civ4.java.infoprocessor.IInfoProcessor;
import org.archid.civ4.java.infoprocessor.IInfoProcessor.InfoOverrides;
import org.archid.civ4.schema.XmlTagInstance;

public class DefaultInfoImporterCreator implements IJavaFileCreator {

	@Override
	public String getFileContent() {
		String infoNameRoot = JavaCodeGeneratorData.getInstance().getInfoNameRoot();
		String infoName = JavaCodeGeneratorData.getInstance().getInfoName();
		IInfoProcessor infoProcessor = JavaCodeGeneratorData.getInstance().getInfoProcessor();
		// Imports
		Set<String> imports = new HashSet<String>();
		imports.add("import org.apache.log4j.Logger;");
		imports.add("import org.apache.poi.ss.usermodel.Row;");
		imports.add("import org.archid.civ4.info.AbstractImporter;");
		imports.add("import org.archid.civ4.info.EInfo;");
		imports.add("import org.archid.civ4.info.IInfos;");

		// Main content
		StringBuilder mainClass = new StringBuilder();
		mainClass.append(NEWLINE);
		mainClass.append(NEWLINE + "public class " + infoNameRoot + "Importer extends AbstractImporter<IInfos<I" + infoName + ">, I" + infoName + "> {");
		mainClass.append(NEWLINE);
		mainClass.append(NEWLINET + "/** Logging facility */");
		mainClass.append(NEWLINET + "static Logger log = Logger.getLogger(" + infoNameRoot + "Importer.class.getName());");
		mainClass.append(NEWLINE);
		mainClass.append(NEWLINET + "public " + infoNameRoot + "Importer(EInfo infoEnum) {");
		mainClass.append(NEWLINETT + "super(infoEnum, new " + infoProcessor.getXmlFormatter() + ");");
		mainClass.append(NEWLINET + "}");
		mainClass.append(NEWLINE);
		mainClass.append(NEWLINET + "@Override");
		mainClass.append(NEWLINET + "public String getListSheetName() {");
		mainClass.append(NEWLINETT + "return I" + infoNameRoot + "Workbook.SHEETNAME_LIST;");
		mainClass.append(NEWLINET + "}");
		mainClass.append(getInfoImporterOverrides());
		mainClass.append(NEWLINE + "}");

		// Sort the imports
		imports.addAll(infoProcessor.getImportImports());
		List<String> sortedImports = new ArrayList<String>(imports);
		Collections.sort(sortedImports);
		
		// Build the file
		StringBuilder file = new StringBuilder();
		file.append(JavaCodeGeneratorData.getInstance().getPackageDef());
		file.append(NEWLINE);
		for (String imp: sortedImports) {
			file.append(NEWLINE + imp);
		}
		file.append(mainClass);

		return file.toString();
	}

	private String getInfoImporterOverrides() {
		IInfoProcessor infoProcessor = JavaCodeGeneratorData.getInstance().getInfoProcessor();
		if (infoProcessor.hasOverride(InfoOverrides.IMPORTER)) {
			return infoProcessor.getOverride(InfoOverrides.IMPORTER);
		} else {
			return getDefaultInfoImporterOverrides();
		}
	}

	private String getDefaultInfoImporterOverrides() {
		String infoName = JavaCodeGeneratorData.getInstance().getInfoName();
		IInfoProcessor infoProcessor = JavaCodeGeneratorData.getInstance().getInfoProcessor();
		
		StringBuilder customCellReaders = new StringBuilder();
		StringBuilder sb = new StringBuilder();
		sb.append(NEWLINE);
		sb.append(NEWLINET + "@Override");
		sb.append(NEWLINET + "protected I" + infoName + " parseRow(Row row) {");
		sb.append(NEWLINETT + "int colNum = 0;");
		sb.append(NEWLINETT + "String type = row.getCell(" + infoProcessor.getTypeTagIndex().toString() + ").getStringCellValue();");
		sb.append(NEWLINETT + "// Handle cells that have been moved");
		sb.append(NEWLINETT + "if (type.isEmpty())");
		sb.append(NEWLINETTT + "return null;");
		sb.append(NEWLINE + "");
		sb.append(NEWLINETT + "I" + infoName + " info = " + infoName + "s.createInfo(type);");

		for (XmlTagInstance mainChild : JavaCodeGeneratorData.getInstance().getInfoChildTags()) {
			TagInstance tag = JavaCodeGeneratorData.getInstance().getTagInstance(mainChild.getTagName());
			ITagProcessor tagProcessor = infoProcessor.getTagProcessor(mainChild.getTagName());
			if (tagProcessor != null) {
				sb.append(tagProcessor.getImporterRow());
				customCellReaders.append(tagProcessor.getImporterCellReader());
			} else if (tag.requiresArray()) {
				if (tag.getNumLeaves() == 1) {
					if (tag.requiresArray()) {
						sb.append(NEWLINETT + "parseListCell(row.getCell(colNum++), " + tag.getDataType() + ".class, info::" + tag.getSetterName() + ");");
					} else {
						sb.append(NEWLINETT + "parseCell(row.getCell(colNum++), " + tag.getDataType() + ".class, info::" + tag.getSetterName() + ");");
					}
				} else if (tag.getNumLeaves() == 2) { 
					sb.append(NEWLINETT + "parsePairsCell(row.getCell(colNum++), " + tag.getLeaf(0).getType() + ".class, " + tag.getLeaf(1).getType() + ".class, info::" + tag.getSetterName() + ");");
				} else if (tag.getNumLeaves() == 3) {
					sb.append(NEWLINETT + "parseTriplesCell(row.getCell(colNum++), " + tag.getLeaf(0).getType() + ".class, " + tag.getLeaf(1).getType() + ".class, " + tag.getLeaf(2).getType() + ".class, info::" + tag.getSetterName() + ");");
				}
			} else {
				sb.append(NEWLINETT + "parseCell(row.getCell(colNum++), " + tag.getDataType() + ".class, info::" + tag.getSetterName() + ");");					
			}
		}
		
		sb.append(NEWLINE);
		sb.append(NEWLINETT + "return info;");
		sb.append(NEWLINET + "}");
		sb.append(customCellReaders);
		
		return sb.toString();
	}

}
