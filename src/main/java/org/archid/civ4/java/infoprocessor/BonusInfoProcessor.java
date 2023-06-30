package org.archid.civ4.java.infoprocessor;

import org.archid.civ4.java.AbstractTagProcessor;

public class BonusInfoProcessor extends DefaultInfoProcessor {
	
	public void init(String packageName) {
		this.packageName = packageName;
		exportImports.add("import org.apache.poi.ss.usermodel.Cell;");
		exportImports.add("import org.archid.civ4.info.IInfoWorkbook;");
		importImports.add("import org.apache.poi.ss.usermodel.Cell;");
		importImports.add("import org.archid.civ4.info.IInfoWorkbook;");
		addTagProcessor(new RandsProcessor("Rands"));
	}

	@Override
	public String getXmlFormatter() {
		return "BonusInfoXmlFormatter()";
	}
	
	private class RandsProcessor extends AbstractTagProcessor {

		public RandsProcessor(String tagName) {
			super(tagName);
		}

		@Override
		public String getUnmarshallString() {
			 return NEWLINETTT + "info.setRands(aInfo.rands);";
		}

		@Override
		public String getMarshallString() {
			 return NEWLINETTT + "aInfo.rands = info.getRands();";
		}

		@Override
		public String getImporterRow() {
			return NEWLINETT + "parseRandsCell(row.getCell(colNum++), info);";
		}

		@Override
		public String getImporterCellReader() {
			StringBuilder sb = new StringBuilder();
			sb.append(NEWLINE);
			sb.append(NEWLINET + "private void parseRandsCell(Cell cell, IBonusInfo info) {");
			sb.append(NEWLINETT + "String[] vals = cell.getStringCellValue().split(IInfoWorkbook.CELL_NEWLINE);");
			sb.append(NEWLINETT + "if (vals.length > 1) {");
			sb.append(NEWLINETTT + "Rands rands = new Rands();");
			sb.append(NEWLINETTT + "rands.setRandApp1(Integer.valueOf(vals[0]));");
			sb.append(NEWLINETTT + "rands.setRandApp2(Integer.valueOf(vals[1]));");
			sb.append(NEWLINETTT + "rands.setRandApp3(Integer.valueOf(vals[2]));");
			sb.append(NEWLINETTT + "rands.setRandApp4(Integer.valueOf(vals[3]));");
			sb.append(NEWLINETTT + "info.setRands(rands);");
			sb.append(NEWLINETT + "}");
			sb.append(NEWLINET + "}");
			return sb.toString();
		}

		@Override
		public String getExporterRow() {
			return NEWLINETT + "maxHeight = addRandsCell(row.createCell(colNum++), info.getRands(), maxHeight);";
		}

		@Override
		public String getExporterCellWriter() {
			StringBuilder sb = new StringBuilder();
			sb.append(NEWLINE);
			sb.append(NEWLINET + "private int addRandsCell(Cell cell, Rands rands, int maxHeight) {");
			sb.append(NEWLINETT + "if (rands == null) return maxHeight;");
			sb.append(NEWLINETT + "cell.setCellStyle(csWrap);");
			sb.append(NEWLINETT + "StringBuilder sb = new StringBuilder();");
			sb.append(NEWLINETT + "sb.append(rands.getRandApp1());");
			sb.append(NEWLINETT + "sb.append(IInfoWorkbook.CELL_NEWLINE + rands.getRandApp2());");
			sb.append(NEWLINETT + "sb.append(IInfoWorkbook.CELL_NEWLINE + rands.getRandApp3());");
			sb.append(NEWLINETT + "sb.append(IInfoWorkbook.CELL_NEWLINE + rands.getRandApp4());");
			sb.append(NEWLINETT + "cell.setCellValue(sb.toString());");
			sb.append(NEWLINETT + "return (maxHeight > 4) ? maxHeight : 4;");
			sb.append(NEWLINET + "}");
			return sb.toString();
		}
		
	}

}
