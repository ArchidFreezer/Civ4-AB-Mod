package org.archid.civ4.java.infoprocessor;

import org.archid.civ4.java.AbstractTagProcessor;
import org.archid.civ4.java.JavaCodeGeneratorData;

public class UnitInfoProcessor extends DefaultInfoProcessor {
	
	@Override
	public void init(String packageName) {
		this.packageName = packageName;
		addTagProcessor(new UnitMeshGroupsProcessor("UnitMeshGroups"));
	}
	
	@Override
	public String getXmlFormatter() {
		return "UnitInfoXmlFormatter()";
	}

	@Override
	public Integer getTypeTagIndex() {
		return 1;
	}

	private class UnitMeshGroupsProcessor extends AbstractTagProcessor {

		public UnitMeshGroupsProcessor(String tagName) {
			super(tagName);
			exportImports.add("import org.archid.civ4.info.unit.UnitMeshGroups.UnitMeshGroup;");
			exportImports.add("import org.archid.civ4.info.IInfoWorkbook;");
			importImports.add("import java.util.ArrayList;");
			importImports.add("import java.util.List;");
			importImports.add("import org.archid.civ4.info.IInfoWorkbook;");
			importImports.add("import org.archid.utils.StringUtils;");
			importImports.add("import org.archid.civ4.info.unit.UnitMeshGroups.UnitMeshGroup;");
			JavaCodeGeneratorData.getInstance().getTagInstance(tagName).setDataType(getTagName());
			JavaCodeGeneratorData.getInstance().getTagInstance(tagName).resetLevels();
		}

		@Override
		public String getUnmarshallString() {
			return NEWLINETTT + "info.setUnitMeshGroups(aInfo.unitMeshGroups);";
		}

		@Override
		public String getMarshallString() {
			return NEWLINETTT + "aInfo.unitMeshGroups = info.getUnitMeshGroups();";
		}

		@Override
		public String getImporterRow() {
			return NEWLINETT + "info.setUnitMeshGroups(getUnitMeshGroups(row.getCell(colNum++).getStringCellValue()));";
		}

		@Override
		public String getImporterCellReader() {
			StringBuilder sb = new StringBuilder();
			sb.append(NEWLINET + "private UnitMeshGroups getUnitMeshGroups(String stringCellValue) {");
			sb.append(NEWLINETT + "String[] rows = stringCellValue.split(IInfoWorkbook.CELL_NEWLINE);");
			sb.append(NEWLINETT + "int index = 0;");
			sb.append(NEWLINETT + "UnitMeshGroups mesh = new UnitMeshGroups();");
			sb.append(NEWLINETT + "mesh.setGroupSize(Integer.parseInt(rows[index++]));");
			sb.append(NEWLINETT + "mesh.setMaxSpeed(Float.parseFloat(rows[index++]));");
			sb.append(NEWLINETT + "mesh.setPadTime(Float.parseFloat(rows[index++]));");
			sb.append(NEWLINETT + "mesh.setMeleeWaveSize(Integer.parseInt(rows[index++]));");
			sb.append(NEWLINETT + "mesh.setRangedWaveSize(Integer.parseInt(rows[index++]));");
			sb.append(NEWLINETT + "List<UnitMeshGroup> groups = new ArrayList<UnitMeshGroup>();");
			sb.append(NEWLINETT + "while (index < rows.length) {");
			sb.append(NEWLINETTT + "String row = rows[index++];");
			sb.append(NEWLINETTT + "if (row.equals(IUnitWorkbook.CELL_GROUP_DELIM)) {");
			sb.append(NEWLINETTTT + "UnitMeshGroup group = new UnitMeshGroup();");
			sb.append(NEWLINETTTT + "group.setRequired(Integer.parseInt(rows[index++].trim()));");
			sb.append(NEWLINETTTT + "group.setEarlyArtDefineTag(rows[index++].trim());");
			sb.append(NEWLINETTTT + "group.setLateArtDefineTag(StringUtils.getNullIfEmpty(rows[index++].trim()));");
			sb.append(NEWLINETTTT + "group.setMiddleArtDefineTag(StringUtils.getNullIfEmpty(rows[index++].trim()));");
			sb.append(NEWLINETTTT + "groups.add(group);");
			sb.append(NEWLINETTT + "}");
			sb.append(NEWLINETTT + "mesh.setUnitMeshGroupList(groups);");
			sb.append(NEWLINETT + "}");
			sb.append(NEWLINETT + "return mesh;");
			sb.append(NEWLINET + "}");
			return sb.toString();
		}

		@Override
		public String getExporterRow() {
			return NEWLINETT + "addSingleCell(row.createCell(colNum++), getUnitMeshText(info));";
		}

		@Override
		public String getExporterCellWriter() {
			StringBuilder sb = new StringBuilder();
			sb.append(NEWLINE);
			sb.append(NEWLINET + "private String getUnitMeshText(IUnitInfo info) {");
			sb.append(NEWLINETT + "StringBuilder sb = new StringBuilder();");
			sb.append(NEWLINETT + "UnitMeshGroups mesh = info.getUnitMeshGroups();");
			sb.append(NEWLINETT + "sb.append(mesh.getGroupSize() + IInfoWorkbook.CELL_NEWLINE);");
			sb.append(NEWLINETT + "sb.append(mesh.getMaxSpeed() + IInfoWorkbook.CELL_NEWLINE);");
			sb.append(NEWLINETT + "sb.append(mesh.getPadTime() + IInfoWorkbook.CELL_NEWLINE);");
			sb.append(NEWLINETT + "sb.append(mesh.getMeleeWaveSize() + IInfoWorkbook.CELL_NEWLINE);");
			sb.append(NEWLINETT + "sb.append(mesh.getRangedWaveSize());");
			sb.append(NEWLINETT + "for (UnitMeshGroup group: mesh.getUnitMeshGroupList()) {");
			sb.append(NEWLINETTT + "sb.append(IInfoWorkbook.CELL_NEWLINE + IUnitWorkbook.CELL_GROUP_DELIM + IInfoWorkbook.CELL_NEWLINE);");
			sb.append(NEWLINETTT + "sb.append(IUnitWorkbook.CELL_GROUP_PAD + group.getRequired() + IInfoWorkbook.CELL_NEWLINE);");
			sb.append(NEWLINETTT + "sb.append(IUnitWorkbook.CELL_GROUP_PAD + group.getEarlyArtDefineTag() + IInfoWorkbook.CELL_NEWLINE);");
			sb.append(NEWLINETTT + "sb.append(IUnitWorkbook.CELL_GROUP_PAD + group.getLateArtDefineTag() + IInfoWorkbook.CELL_NEWLINE);");
			sb.append(NEWLINETTT + "sb.append(IUnitWorkbook.CELL_GROUP_PAD + group.getMiddleArtDefineTag());");
			sb.append(NEWLINETT + "}");
			sb.append(NEWLINE);
			sb.append(NEWLINETT + "return sb.toString();");
			sb.append(NEWLINET + "}");
			return sb.toString();
		}
		
	}
}
