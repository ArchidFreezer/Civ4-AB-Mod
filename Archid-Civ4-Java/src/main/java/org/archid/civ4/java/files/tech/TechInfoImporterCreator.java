package org.archid.civ4.java.files.tech;

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

public class TechInfoImporterCreator implements IJavaFileCreator {

	IInfoProcessor infoProcessor = JavaCodeGeneratorData.getInstance().getInfoProcessor();
	
	@Override
	public String getFileContent() {
		String infoNameRoot = JavaCodeGeneratorData.getInstance().getInfoNameRoot();
		String infoName = JavaCodeGeneratorData.getInstance().getInfoName();
		// Imports
		Set<String> imports = new HashSet<String>();
		imports.add("import java.util.Iterator;");
		imports.add("import org.apache.log4j.Logger;");
		imports.add("import org.apache.poi.ss.usermodel.Cell;");
		imports.add("import org.apache.poi.ss.usermodel.Row;");
		imports.add("import org.apache.poi.ss.usermodel.Sheet;");
		imports.add("import org.apache.poi.ss.usermodel.Workbook;");
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
		mainClass.append(getOverrides());
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

	private String getOverrides() {
		StringBuilder sb = new StringBuilder(getOverride());
		sb.append(infoProcessor.getOverride(InfoOverrides.IMPORTER));
		return sb.toString();
	}

	private String getOverride() {
		StringBuilder customCellReaders = new StringBuilder();
		StringBuilder sb = new StringBuilder();
		sb.append(NEWLINE);
		sb.append(NEWLINET + "/**");
		sb.append(NEWLINET + " * Overrides the the method from {@link AbstractImporter} to allow the {@code <TechInfo>} objects to be defined");
		sb.append(NEWLINET + " * by the tech tree sheet rather than the list sheet. A tech will be created for each one defined in the tech tree");
		sb.append(NEWLINET + " * sheet which will provide the {@code <Type>}, {@code <iGridX>} and {@code <iGridY>} tag values. All other values");
		sb.append(NEWLINET + " * are then populated from the list sheet.");
		sb.append(NEWLINET + " * ");
		sb.append(NEWLINET + " * @param wb {@link Workbook} containing the tech tree and list sheets");
		sb.append(NEWLINET + " */");
		sb.append(NEWLINET + "@Override");
		sb.append(NEWLINET + "protected boolean parseXlsx(Workbook wb) {");
		sb.append(NEWLINE);
		sb.append(NEWLINETT + "// Get the iGridX and iGridY values from the tech tree");
		sb.append(NEWLINETT + "Sheet sheet = wb.getSheet(ITechWorkbook.SHEETNAME_TREE);");
		sb.append(NEWLINETT + "if (sheet == null) {");
		sb.append(NEWLINETTT + "log.warn(\"Sheet \" + ITechWorkbook.SHEETNAME_TREE + \" does not exist in the workbook\");");
		sb.append(NEWLINETTT + "return false;");
		sb.append(NEWLINETT + "}");
		sb.append(NEWLINE);
		sb.append(NEWLINETT + "Iterator<Row> itRow = sheet.rowIterator();");
		sb.append(NEWLINETT + "while (itRow.hasNext()) {");
		sb.append(NEWLINETTT + "Row row = itRow.next();");
		sb.append(NEWLINETTT + "Iterator<Cell> itCell = row.cellIterator();");
		sb.append(NEWLINETTT + "while (itCell.hasNext()) {");
		sb.append(NEWLINETTTT + "Cell cell = itCell.next();");
		sb.append(NEWLINETTTT + "String type = cell.getStringCellValue();");
		sb.append(NEWLINETTTT + "// Handle cells that have been moved");
		sb.append(NEWLINETTTT + "if (type.isEmpty())");
		sb.append(NEWLINETTTTT + "continue;");
		sb.append(NEWLINETTTT + "ITechInfo info = TechInfos.createInfo(type);");
		sb.append(NEWLINETTTT + "Integer gridX = getGridXFromCell(cell);");
		sb.append(NEWLINETTTT + "info.setGridX(gridX);");
		sb.append(NEWLINETTTT + "Integer gridY = cell.getRowIndex() + 1;");
		sb.append(NEWLINETTTT + "info.setGridY(gridY);");
		sb.append(NEWLINETTTT + "infos.addInfo(info);");
		sb.append(NEWLINETTT + "}");
		sb.append(NEWLINETT + "}");
		sb.append(NEWLINE);
		sb.append(NEWLINETT + "// Get the rest of the values from the tech tree");
		sb.append(NEWLINETT + "sheet = wb.getSheet(getListSheetName());");
		sb.append(NEWLINETT + "if (sheet == null) {");
		sb.append(NEWLINETTT + "log.warn(\"Sheet \" + getListSheetName() + \" does not exist in the workbook\");");
		sb.append(NEWLINETTT + "return false;");
		sb.append(NEWLINETT + "}");
		sb.append(NEWLINE);
		sb.append(NEWLINETT + "itRow = sheet.rowIterator();");
		sb.append(NEWLINETT + "while (itRow.hasNext()) {");
		sb.append(NEWLINETTT + "Row row = itRow.next();");
		sb.append(NEWLINETTT + "int colNum = 0;");
		sb.append(NEWLINE);
		sb.append(NEWLINETTT + "String type = row.getCell(0).getStringCellValue();");
		sb.append(NEWLINETTT + "log.debug(\"Processing: \" + type);");
		sb.append(NEWLINETTT + "// Handle cells that have been moved");
		sb.append(NEWLINETTT + "if (type.isEmpty())");
		sb.append(NEWLINETTTT + "continue;");
		sb.append(NEWLINETTT + "ITechInfo info = infos.getInfo(type);");
		sb.append(NEWLINETTT + "// Handle deleted techs");
		sb.append(NEWLINETTT + "if (info == null)");
		sb.append(NEWLINETTTT + "continue;");
				for (XmlTagInstance mainChild : JavaCodeGeneratorData.getInstance().getInfoChildTags()) {
					TagInstance tag = JavaCodeGeneratorData.getInstance().getTagInstance(mainChild.getTagName());
					ITagProcessor tagProcessor = infoProcessor.getTagProcessor(mainChild.getTagName());
					if (tagProcessor != null) {
						sb.append(tagProcessor.getImporterRow());
						customCellReaders.append(tagProcessor.getImporterCellReader());
					} else if (tag.requiresArray()) {
						if (tag.getNumLeaves() == 1) {
							if (tag.requiresArray()) {
								sb.append(NEWLINETTT + "parseListCell(row.getCell(colNum++), " + tag.getDataType() + ".class, info::" + tag.getSetterName() + ");");
							} else {
								sb.append(NEWLINETTT + "parseCell(row.getCell(colNum++), " + tag.getDataType() + ".class, info::" + tag.getSetterName() + ");");
							}
						} else if (tag.getNumLeaves() == 2) { 
							sb.append(NEWLINETTT + "parsePairsCell(row.getCell(colNum++), " + tag.getLeaf(0).getType() + ".class, " + tag.getLeaf(1).getType() + ".class, info::" + tag.getSetterName() + ");");
						} else if (tag.getNumLeaves() == 3) {
							sb.append(NEWLINETTT + "parseTriplesCell(row.getCell(colNum++), " + tag.getLeaf(0).getType() + ".class, " + tag.getLeaf(1).getType() + ".class, " + tag.getLeaf(2).getType() + ".class, info::" + tag.getSetterName() + ");");
						}
					} else {
						sb.append(NEWLINETTT + "parseCell(row.getCell(colNum++), " + tag.getDataType() + ".class, info::" + tag.getSetterName() + ");");					
					}
				}
		sb.append(NEWLINETT + "}");
		sb.append(NEWLINE);
		sb.append(NEWLINETT + "return true;");
		sb.append(NEWLINET + "}");

		sb.append(NEWLINE);
		sb.append(NEWLINET + "private Integer getGridXFromCell(Cell cell) {");
		sb.append(NEWLINETT + "Integer gridX = 0;");
		sb.append(NEWLINETT + "Integer colIndex = cell.getColumnIndex();");
		sb.append(NEWLINE);
		sb.append(NEWLINETT + "if (colIndex > 0) {");
		sb.append(NEWLINETTT + "gridX = colIndex / 2;");
		sb.append(NEWLINETT + "}");
		sb.append(NEWLINE);
		sb.append(NEWLINETT + "return ++gridX;");
		sb.append(NEWLINET + "}");
		
		sb.append(NEWLINE);
		sb.append(NEWLINET + "/**");
		sb.append(NEWLINET + " * This method is not used in this class as the {@link AbstractImporter#parseXlsx(Workbook)} method is overriden  ");
		sb.append(NEWLINET + " */");
		sb.append(NEWLINET + "@Override");
		sb.append(NEWLINET + "protected ITechInfo parseRow(Row row) {");
		sb.append(NEWLINETT + "return null;");
		sb.append(NEWLINET + "}");
		
		sb.append(customCellReaders);
		
		return sb.toString();
	}

}
