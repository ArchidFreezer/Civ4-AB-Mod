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

public class TechInfoExporterCreator implements IJavaFileCreator {

	IInfoProcessor infoProcessor = JavaCodeGeneratorData.getInstance().getInfoProcessor();

	@Override
	public String getFileContent() {
		String infoNameRoot = JavaCodeGeneratorData.getInstance().getInfoNameRoot();
		String infoName = JavaCodeGeneratorData.getInstance().getInfoName();

		// Imports
		Set<String> imports = new HashSet<String>();
		imports.add("import java.util.ArrayList;");
		imports.add("import java.util.HashMap;");
		imports.add("import java.util.List;");
		imports.add("import java.util.Map;");
		imports.add("import org.apache.log4j.Logger;");
		imports.add("import org.apache.poi.ss.usermodel.Cell;");
		imports.add("import org.apache.poi.ss.usermodel.CellStyle;");
		imports.add("import org.apache.poi.ss.usermodel.CellType;");
		imports.add("import org.apache.poi.ss.usermodel.FillPatternType;");
		imports.add("import org.apache.poi.ss.usermodel.IndexedColors;");
		imports.add("import org.apache.poi.ss.usermodel.Row;");
		imports.add("import org.apache.poi.ss.usermodel.Sheet;");
		imports.add("import org.archid.civ4.info.AbstractExporter;");
		imports.add("import org.archid.civ4.info.EInfo;");
		imports.add("import org.archid.civ4.info.IInfos;");
		imports.add("import org.archid.civ4.info." + JavaCodeGeneratorData.getInstance().getNamespaceFolder() + ".I" + infoNameRoot + "Workbook.SheetHeaders;");

		// Main content
		StringBuilder mainClass = new StringBuilder();
		mainClass.append(NEWLINE);
		mainClass.append(NEWLINE + "public class " + infoNameRoot + "Exporter extends AbstractExporter<IInfos<I" + infoName + ">, I" + infoName + "> {");
		mainClass.append(NEWLINE);
		mainClass.append(NEWLINET + "/** Logging facility */");
		mainClass.append(NEWLINET + "static Logger log = Logger.getLogger(" + infoNameRoot + "Exporter.class.getName());");
		mainClass.append(NEWLINE);
		mainClass.append(NEWLINET + "private Map<Integer, CellStyle> backgrounds;");
		mainClass.append(NEWLINE);
		mainClass.append(NEWLINET + "public " + infoNameRoot + "Exporter(EInfo infoEnum) {");
		mainClass.append(NEWLINETT + "super(infoEnum);");
		mainClass.append(NEWLINET + "}");
		mainClass.append(NEWLINE);
		mainClass.append(NEWLINET + "@Override");
		mainClass.append(NEWLINET + "public List<String> getHeaders() {");
		mainClass.append(NEWLINETT + "List<String> headers = new ArrayList<String>();");
		mainClass.append(NEWLINETT + "for (SheetHeaders header: SheetHeaders.values()) {");
		mainClass.append(NEWLINETTT + "headers.add(header.toString());");
		mainClass.append(NEWLINETT + "}");
		mainClass.append(NEWLINETT + "return headers;");
		mainClass.append(NEWLINET + "}");
		mainClass.append(NEWLINE);
		mainClass.append(NEWLINET + "@Override");
		mainClass.append(NEWLINET + "protected int getNumCols() {");
		mainClass.append(NEWLINETT + "return I" + infoNameRoot + "Workbook.SheetHeaders.values().length;");
		mainClass.append(NEWLINET + "}");
		mainClass.append(NEWLINE);
		mainClass.append(NEWLINET + "@Override");
		mainClass.append(NEWLINET + "protected String getInfoListSheetName() {");
		mainClass.append(NEWLINETT + "return I" + infoNameRoot + "Workbook.SHEETNAME_LIST;");
		mainClass.append(NEWLINET + "}");
		mainClass.append(getOverrides());
		mainClass.append(NEWLINE + "}");

		// Sort the imports
		imports.addAll(infoProcessor.getExportImports());
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
		IInfoProcessor infoProcessor = JavaCodeGeneratorData.getInstance().getInfoProcessor();
		StringBuilder customCellWriters = new StringBuilder();
		StringBuilder sb = new StringBuilder();
		sb.append(NEWLINE);
		sb.append(NEWLINET + "@Override");
		sb.append(NEWLINET + "protected void createSheets() {");
		sb.append(NEWLINETT + "createTechTreeSheet();");
		sb.append(NEWLINETT + "super.createSheets();");
		sb.append(NEWLINET + "}");

		sb.append(NEWLINE);
		sb.append(NEWLINET + "@Override");
		sb.append(NEWLINET + "protected void populateRow(Row row, I" + JavaCodeGeneratorData.getInstance().getInfoName() + " info) {");
		sb.append(NEWLINETT + "int maxHeight = 1;");
		sb.append(NEWLINETT + "int colNum = 0;");
		for (XmlTagInstance mainChild : JavaCodeGeneratorData.getInstance().getInfoChildTags()) {
			TagInstance tag = JavaCodeGeneratorData.getInstance().getTagInstance(mainChild.getTagName());
			ITagProcessor tagProcessor = infoProcessor.getTagProcessor(mainChild.getTagName());
			if (tagProcessor != null) {
				sb.append(tagProcessor.getExporterRow());
				customCellWriters.append(tagProcessor.getExporterCellWriter());
			} else if (tag.requiresArray()) {
				if (tag.getNumLeaves() == 1) {
					sb.append(NEWLINETT + "maxHeight = addRepeatingCell(row.createCell(colNum++), info." + tag.getGetterName() + "(), maxHeight);");					
				} else if (tag.getNumLeaves() == 2) { 
					sb.append(NEWLINETT + "maxHeight = addRepeatingPairCell(row.createCell(colNum++), info." + tag.getGetterName() + "(), maxHeight);");					
				} else if (tag.getNumLeaves() == 3) {
					sb.append(NEWLINETT + "maxHeight = addRepeatingTripleCell(row.createCell(colNum++), info." + tag.getGetterName() + "(), maxHeight);");					
				}
			} else {
				sb.append(NEWLINETT + "addSingleCell(row.createCell(colNum++), info." + tag.getGetterName() + "());");					
			}
		}
		sb.append(NEWLINE);
		sb.append(NEWLINETT + "row.setHeightInPoints(maxHeight * row.getSheet().getDefaultRowHeightInPoints());");
		sb.append(NEWLINET + "}");

		sb.append(NEWLINE);
		sb.append(NEWLINET + "private void createTechTreeSheet() {");
		sb.append(NEWLINETT + "Sheet sheet = getSheet(ITechWorkbook.SHEETNAME_TREE);");
		sb.append(NEWLINE);
		sb.append(NEWLINETT + "// Add the tech data");
		sb.append(NEWLINETT + "Row row;");
		sb.append(NEWLINETT + "Cell cell;");
		sb.append(NEWLINETT + "int maxGridX = 0; // Used to autosize all the cols");
		sb.append(NEWLINETT + "int maxGridY = 0; // Used to create all the rows at one go");
		sb.append(NEWLINETT + "List<String> eras = new ArrayList<String>(); // Used to add background colour to cells based on era");
		sb.append(NEWLINETT + "for (ITechInfo techInfo: infos.getInfos()) {");
		sb.append(NEWLINETTT + "// Get the row (iGridY value from tech)");
		sb.append(NEWLINETTT + "int gridY = techInfo.getGridY();");
		sb.append(NEWLINETTT + "if (gridY <= 0) {");
		sb.append(NEWLINETTTT + "log.info(\"Ignoring tech \" + techInfo.getType() + \" invalid gridY value: \" + gridY);");
		sb.append(NEWLINETTTT + "continue;");
		sb.append(NEWLINETTT + "}");
		sb.append(NEWLINETTT + "if (gridY > maxGridY) maxGridY = gridY;");
		sb.append(NEWLINETTT + "row = sheet.getRow(gridY - 1);");
		sb.append(NEWLINETTT + "if (row == null)");
		sb.append(NEWLINETTTT + "row = sheet.createRow(gridY - 1);");
		sb.append(NEWLINE);
		sb.append(NEWLINETTT + "// Get the col (iGridX from tech)");
		sb.append(NEWLINETTT + "int gridX = techInfo.getGridX();");
		sb.append(NEWLINETTT + "if (gridX <= 0) {");
		sb.append(NEWLINETTTT + "log.info(\"Ignoring tech \" + techInfo.getType() + \" invalid gridX value: \" + gridX);");
		sb.append(NEWLINETTTT + "continue;");
		sb.append(NEWLINETTT + "}");
		sb.append(NEWLINETTT + "if (gridX > maxGridX) maxGridX = gridX;");
		sb.append(NEWLINETTT + "cell = row.createCell(getCellCol(gridX), CellType.STRING);");
		sb.append(NEWLINETTT + "cell.setCellValue(techInfo.getType());");
		sb.append(NEWLINE);
		sb.append(NEWLINETTT + "String era = techInfo.getEra();");
		sb.append(NEWLINETTT + "if (!eras.contains(era))");
		sb.append(NEWLINETTTT + "eras.add(era);");
		sb.append(NEWLINETTT + "cell.setCellStyle(getStyle(eras.lastIndexOf(era)));");
		sb.append(NEWLINE);
		sb.append(NEWLINETTT + "setCellComment(cell, getCellMessage(techInfo));");
		sb.append(NEWLINETT + "}");
		sb.append(NEWLINE);
		sb.append(NEWLINETT + "// Autosize the columns");
		sb.append(NEWLINETT + "int iNumCols = getCellCol(maxGridX) + 1;");
		sb.append(NEWLINETT + "for (int i = 0; i < iNumCols; i++) {");
		sb.append(NEWLINETTT + "sheet.autoSizeColumn(i);");
		sb.append(NEWLINETT + "}");
		sb.append(NEWLINETT + "log.info(\"Wrote \" +  infos.getInfos().size() + \" tech tree infos to \" + ITechWorkbook.SHEETNAME_TREE + \" sheet.\");");
		sb.append(NEWLINET + "}");

		sb.append(NEWLINE);
		sb.append(NEWLINET + "private int getCellCol(int gridX) {");
		sb.append(NEWLINETT + "return (--gridX * 2);");
		sb.append(NEWLINET + "}");

		sb.append(NEWLINE);
		sb.append(NEWLINET + "@Override");
		sb.append(NEWLINET + "protected void preCreateCellStyles() {");
		sb.append(NEWLINETT + "super.preCreateCellStyles();");
		sb.append(NEWLINE);
		sb.append(NEWLINETT + "// Create the coloured backgrounds for the tech tree");
		sb.append(NEWLINETT + "backgrounds = new HashMap<Integer, CellStyle>();");
		sb.append(NEWLINETT + "CellStyle style = wb.createCellStyle();");
		sb.append(NEWLINETT + "style.setFillPattern(FillPatternType.SOLID_FOREGROUND);");
		sb.append(NEWLINETT + "style.setFillForegroundColor(IndexedColors.LIGHT_GREEN.getIndex());");
		sb.append(NEWLINETT + "backgrounds.put(0, style);");
		sb.append(NEWLINETT + "style = wb.createCellStyle();");
		sb.append(NEWLINETT + "style.setFillPattern(FillPatternType.SOLID_FOREGROUND);");
		sb.append(NEWLINETT + "style.setFillForegroundColor(IndexedColors.LIGHT_BLUE.getIndex());");
		sb.append(NEWLINETT + "backgrounds.put(1, style);");
		sb.append(NEWLINETT + "style = wb.createCellStyle();");
		sb.append(NEWLINETT + "style.setFillPattern(FillPatternType.SOLID_FOREGROUND);");
		sb.append(NEWLINETT + "style.setFillForegroundColor(IndexedColors.LIGHT_YELLOW.getIndex());");
		sb.append(NEWLINETT + "backgrounds.put(2, style);");
		sb.append(NEWLINETT + "style = wb.createCellStyle();");
		sb.append(NEWLINETT + "style.setFillPattern(FillPatternType.SOLID_FOREGROUND);");
		sb.append(NEWLINETT + "style.setFillForegroundColor(IndexedColors.LIGHT_ORANGE.getIndex());");
		sb.append(NEWLINETT + "backgrounds.put(3, style);");
		sb.append(NEWLINETT + "style = wb.createCellStyle();");
		sb.append(NEWLINETT + "style.setFillPattern(FillPatternType.SOLID_FOREGROUND);");
		sb.append(NEWLINETT + "style.setFillForegroundColor(IndexedColors.LIGHT_CORNFLOWER_BLUE.getIndex());");
		sb.append(NEWLINETT + "backgrounds.put(4, style);");
		sb.append(NEWLINETT + "style = wb.createCellStyle();");
		sb.append(NEWLINETT + "style.setFillPattern(FillPatternType.SOLID_FOREGROUND);");
		sb.append(NEWLINETT + "style.setFillForegroundColor(IndexedColors.LIGHT_TURQUOISE.getIndex());");
		sb.append(NEWLINETT + "backgrounds.put(5, style);");
		sb.append(NEWLINETT + "style = wb.createCellStyle();");
		sb.append(NEWLINETT + "style.setFillPattern(FillPatternType.SOLID_FOREGROUND);");
		sb.append(NEWLINETT + "style.setFillForegroundColor(IndexedColors.CORAL.getIndex());");
		sb.append(NEWLINETT + "backgrounds.put(6, style);");
		sb.append(NEWLINETT + "style = wb.createCellStyle();");
		sb.append(NEWLINETT + "style.setFillPattern(FillPatternType.SOLID_FOREGROUND);");
		sb.append(NEWLINETT + "style.setFillForegroundColor(IndexedColors.ROSE.getIndex());");
		sb.append(NEWLINETT + "backgrounds.put(7, style);");
		sb.append(NEWLINETT + "style = wb.createCellStyle();");
		sb.append(NEWLINETT + "style.setFillPattern(FillPatternType.SOLID_FOREGROUND);");
		sb.append(NEWLINETT + "style.setFillForegroundColor(IndexedColors.AQUA.getIndex());");
		sb.append(NEWLINETT + "backgrounds.put(8, style);");
		sb.append(NEWLINETT + "style = wb.createCellStyle();");
		sb.append(NEWLINETT + "style.setFillPattern(FillPatternType.SOLID_FOREGROUND);");
		sb.append(NEWLINETT + "style.setFillForegroundColor(IndexedColors.LAVENDER.getIndex());");
		sb.append(NEWLINETT + "backgrounds.put(9, style);");
		sb.append(NEWLINETT + "style = wb.createCellStyle();");
		sb.append(NEWLINETT + "style.setFillPattern(FillPatternType.SOLID_FOREGROUND);");
		sb.append(NEWLINETT + "style.setFillForegroundColor(IndexedColors.TEAL.getIndex());");
		sb.append(NEWLINETT + "backgrounds.put(10, style);");
		sb.append(NEWLINET + "}");

		sb.append(NEWLINE);
		sb.append(NEWLINET + "private CellStyle getStyle(int index) {");
		sb.append(NEWLINETT + "if (index == -1)");
		sb.append(NEWLINETTT + "index = 0;");
		sb.append(NEWLINE);
		sb.append(NEWLINETT + "if (!backgrounds.containsKey(index))");
		sb.append(NEWLINETTT + "index = index % backgrounds.size();");
		sb.append(NEWLINE);
		sb.append(NEWLINETT + "return backgrounds.get(index);");
		sb.append(NEWLINET + "}");

		sb.append(NEWLINE);
		sb.append(NEWLINET + "private String getCellMessage(ITechInfo info) {");
		sb.append(NEWLINETT + "StringBuilder message = new StringBuilder(\"iGridX: \" + info.getGridX());");
		sb.append(NEWLINETT + "message.append(\"\\niGridY: \" + info.getGridY());");
		sb.append(NEWLINETT + "message.append(\"\\niCost: \" + info.getCost());");
		sb.append(NEWLINETT + "message.append(\"\\nEra: \" + info.getEra());");
		sb.append(NEWLINETT + "if (!info.getOrPreReqs().isEmpty()) {");
		sb.append(NEWLINETTT + "message.append(\"\\nOrTechPreReqs:\");");
		sb.append(NEWLINETTT + "for (String prereq: info.getOrPreReqs()) {");
		sb.append(NEWLINETTTT + "message.append(\"\\n  \" + prereq);");
		sb.append(NEWLINETTT + "}");
		sb.append(NEWLINETT + "}");
		sb.append(NEWLINETT + "if (!info.getAndPreReqs().isEmpty()) {");
		sb.append(NEWLINETTT + "message.append(\"\\nAndTechPreReqs:\");");
		sb.append(NEWLINETTT + "for (String prereq: info.getAndPreReqs()) {");
		sb.append(NEWLINETTTT + "message.append(\"\\n  \" + prereq);");
		sb.append(NEWLINETTT + "}");
		sb.append(NEWLINETT + "}");
		sb.append(NEWLINETT + "return message.toString();");
		sb.append(NEWLINET + "}");

		sb.append(customCellWriters);
		
		return sb.toString();
	}

}
