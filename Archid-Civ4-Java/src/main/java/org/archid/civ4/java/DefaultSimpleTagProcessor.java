package org.archid.civ4.java;

public class DefaultSimpleTagProcessor extends AbstractTagProcessor {
	
	private TagNameData tagNameData = JavaCodeGeneratorData.getInstance().getTagNameData();
	private TagInstance tagInstance;
	
	public DefaultSimpleTagProcessor(String tagName) {
		super(tagName);
		tagInstance = JavaCodeGeneratorData.getInstance().getTagInstance(tagName);
	}

	@Override
	public String getUnmarshallString() {
		return NEWLINETTT + "info.set" + tagNameData.getRootName(tagName) + "(" + getUnmarshall("aInfo." + tagNameData.getVarName(tagName) + ")") + ";";
	}

	@Override
	public String getMarshallString() {
		return NEWLINETTT + "aInfo." + tagNameData.getVarName(tagName) + " = " + getMarshall("info.get" + tagNameData.getRootName(tagName) + "()") + ";";
	}

	@Override
	public String getImporterRow() {
		return NEWLINETT + "parseCell(row.getCell(colNum++), " + tagInstance.getDataType() + ".class, info::" + tagInstance.getSetterName() + ");";					
	}

	@Override
	public String getImporterCellReader() {
		return "";
	}

	@Override
	public String getExporterRow() {
		return NEWLINETT + "addSingleCell(row.createCell(colNum++), info." + JavaCodeGeneratorData.getInstance().getTagInstance(tagName).getGetterName() + "());";
	}

	@Override
	public String getExporterCellWriter() {
		return "";
	}

	private String getUnmarshall(String val) {
		StringBuilder sb = new StringBuilder();
		sb.append("JaxbUtils.unmarshall");
		sb.append(tagInstance.getDataType() + "(" + val + ")");
		return sb.toString();
	}
	
	private String getMarshall(String val) {
		StringBuilder sb = new StringBuilder();
		sb.append("JaxbUtils.marshall");
		if (tagInstance.isMandatory()) sb.append("Mandatory");
		sb.append(tagInstance.getDataType() + "(" + val + ")");
		return sb.toString();
	}
	
}
