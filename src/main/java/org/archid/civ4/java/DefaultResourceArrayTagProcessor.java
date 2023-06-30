package org.archid.civ4.java;

import org.archid.utils.StringUtils;

public class DefaultResourceArrayTagProcessor extends AbstractTagProcessor {
	
	private final DefaultResourceArrayTagProcessorData tagVals;
	private TagNameData tagNameUtils;
	
	public DefaultResourceArrayTagProcessor(DefaultResourceArrayTagProcessorData tagVals, TagNameData tagNameUtils) {
		super(tagVals.outer);
		this.tagVals = tagVals;
		this.tagNameUtils = tagNameUtils;
		exporterImports.add("import org.archid.civ4.info." + tagVals.folder + "." + tagVals.outer + "." + tagVals.wrapper + ";");
		importerImports.add("import org.archid.civ4.info." + tagVals.folder + "." + tagVals.outer + "." + tagVals.wrapper + ";");
		filesToWrite.put(tagVals.outer + ".java", getTagFileContent());
		filesToWrite.put(tagVals.outer + "Adapter.java", getAdapterFileContent());
		JavaCodeGeneratorData.getInstance().getTagInstance(tagName).setDataType(getTagName());
		JavaCodeGeneratorData.getInstance().getTagInstance(tagName).resetLevels();
	}

	@Override
	public String getUnmarshallString() {
		 return NEWLINETTT + "info.set" + tagNameUtils.getRootName(tagVals.outer) + "(aInfo." + tagNameUtils.getVarName(tagVals.outer) + ");";
	}

	@Override
	public String getMarshallString() {
		 return NEWLINETTT + "aInfo." + tagNameUtils.getVarName(tagVals.outer) + " = info.get" + tagNameUtils.getRootName(tagVals.outer) + "();";
	}

	@Override
	public String getImporterRow() {
		return NEWLINETT + "parse" + tagVals.wrapper + "Cell(row.getCell(colNum++), info);";
	}

	@Override
	public String getImporterCellReader() {
		StringBuilder sb = new StringBuilder();
		sb.append(NEWLINE + "");
		sb.append(NEWLINET + "private void parse" + tagVals.wrapper + "Cell(Cell cell, " + tagVals.infoInterface + " info) {");
		sb.append(NEWLINETT + "String[] arr = cell.getStringCellValue().split(IInfoWorkbook.CELL_NEWLINE);");
		sb.append(NEWLINETT + "if (arr.length > 1) {");
		sb.append(NEWLINETTT + "boolean first = true;");
		sb.append(NEWLINETTT + "String resource = null;");
		sb.append(NEWLINETTT + "List<Integer> list = null;");
		sb.append(NEWLINETTT + "for (String str: arr) {");
		sb.append(NEWLINETTTT + "if (StringUtils.hasCharacters(str)) {");
		sb.append(NEWLINETTTTT + "if (first) {");
		sb.append(NEWLINETTTTTT + "list = new ArrayList<Integer>();");
		sb.append(NEWLINETTTTTT + "resource = getVal(str, String.class);");
		sb.append(NEWLINETTTTTT + "first = false;");
		sb.append(NEWLINETTTTT + "} else if (str.equals(\"-\")) {");
		sb.append(NEWLINETTTTTT + tagVals.wrapper + " wrapper = new " + tagVals.wrapper + "();");
		sb.append(NEWLINETTTTTT + "wrapper.setResource(resource);");
		sb.append(NEWLINETTTTTT + "for (Integer element: list) {");
		sb.append(NEWLINETTTTTTT + "wrapper.addElement(element);");
		sb.append(NEWLINETTTTTT + "}");
		sb.append(NEWLINETTTTTT + "info.get" + tagVals.outer + "().get" + tagVals.wrapper + "List().add(wrapper);");
		sb.append(NEWLINETTTTTT + "first = !first;");
		sb.append(NEWLINETTTTT + "} else {");
		sb.append(NEWLINETTTTTT + "list.add(Integer.valueOf(str));");
		sb.append(NEWLINETTTTT + "}");
		sb.append(NEWLINETTTT + "}");
		sb.append(NEWLINETTT + "}");
		sb.append(NEWLINETT + "}");
		sb.append(NEWLINET + "}");
		return sb.toString();
	}

	@Override
	public String getExporterRow() {
		return NEWLINETT + "maxHeight = add" + tagVals.wrapper + "Cell(row.createCell(colNum++), info.get" + tagNameUtils.getRootName(tagVals.outer) + "(), maxHeight);";	}

	@Override
	public String getExporterCellWriter() {
		StringBuilder sb = new StringBuilder();
		sb.append(NEWLINE);
		sb.append(NEWLINET + "private int add" + tagVals.wrapper + "Cell(Cell cell, " + tagNameUtils.getRootName(tagVals.outer) + " list, int maxHeight) {");
		sb.append(NEWLINETT + "int currHeight = 0;");
		sb.append(NEWLINETT + "cell.setCellStyle(csWrap);");
		sb.append(NEWLINETT + "StringBuilder cellvalue = new StringBuilder();");
		sb.append(NEWLINETT + "if (list != null) {");
		sb.append(NEWLINETTT + "for (" + tagVals.wrapper + " wrapper: list.get" + tagVals.wrapper + "List()) {");
		sb.append(NEWLINETTTT + "if (currHeight > 0) cellvalue.append(IInfoWorkbook.CELL_NEWLINE);");
		sb.append(NEWLINETTTT + "cellvalue.append(wrapper.getResource() + IInfoWorkbook.CELL_NEWLINE);");
		sb.append(NEWLINETTTT + "for (Integer element: wrapper.getElements()) {");
		sb.append(NEWLINETTTTT + "cellvalue.append(element + IInfoWorkbook.CELL_NEWLINE);");
		sb.append(NEWLINETTTTT + "currHeight ++;");
		sb.append(NEWLINETTTT + "}");
		sb.append(NEWLINETTTT + "cellvalue.append(\"-\");");
		sb.append(NEWLINETTTT + "currHeight += 2;");
		sb.append(NEWLINETTT + "}");
		sb.append(NEWLINETT + "}");
		sb.append(NEWLINETT + "cell.setCellValue(cellvalue.toString());");
		sb.append(NEWLINETT + "if (currHeight > maxHeight) maxHeight = currHeight;");
		sb.append(NEWLINETT + "return maxHeight;");
		sb.append(NEWLINET + "}");
		sb.append(NEWLINE);
		return sb.toString();
	}
	
	private String getTagFileContent() {
		StringBuilder sb = new StringBuilder();
		sb.append("package org.archid.civ4.info." + tagVals.folder + ";");
		sb.append(NEWLINE + "import java.util.ArrayList;");
		sb.append(NEWLINE + "import java.util.List;");
		sb.append(NEWLINE);
		sb.append(NEWLINE + "import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;");
		sb.append(NEWLINE);
		sb.append(NEWLINE + "@XmlJavaTypeAdapter(" + tagVals.outer + "Adapter.class)");
		sb.append(NEWLINE + "class " + tagVals.outer + "{");
		sb.append(NEWLINET + "private List<" + tagVals.wrapper + "> wrapper = new ArrayList<" + tagVals.wrapper + ">();");
		sb.append(NEWLINE);
		sb.append(NEWLINET + "public List<" + tagVals.wrapper + "> get" + tagVals.wrapper + "List() {");
		sb.append(NEWLINETT + "return wrapper;");
		sb.append(NEWLINET + "}");
		sb.append(NEWLINE);
		sb.append(NEWLINET + "public void set" + tagVals.wrapper + "List(List<" + tagVals.wrapper + "> wrapper){");
		sb.append(NEWLINETT + "this.wrapper = wrapper;");
		sb.append(NEWLINET + "}");
		sb.append(NEWLINE);
		sb.append(NEWLINET + "static class " + tagVals.wrapper + " {");
		sb.append(NEWLINETT + "private String resource;");
		sb.append(NEWLINETT + "private List<Integer> list = new ArrayList<Integer>();");
		sb.append(NEWLINE);
		sb.append(NEWLINETT + "public void setResource (String resource) {");
		sb.append(NEWLINETTT + "this.resource = resource;");
		sb.append(NEWLINETT + "}");
		sb.append(NEWLINE);
		sb.append(NEWLINETT + "public String getResource() {");
		sb.append(NEWLINETTT + "return resource;");
		sb.append(NEWLINETT + "}");
		sb.append(NEWLINE);
		sb.append(NEWLINETT + "public void addElement(Integer element) {");
		sb.append(NEWLINETTT + "list.add(element);");
		sb.append(NEWLINETT + "}");
		sb.append(NEWLINE);
		sb.append(NEWLINETT + "public List<Integer> getElements() {");
		sb.append(NEWLINETTT + "return list;");
		sb.append(NEWLINETT + "}");
		sb.append(NEWLINET + "}");
		sb.append(NEWLINE + "}");		
		return sb.toString();
	}
	
	private String getAdapterFileContent() {
		StringBuilder sb = new StringBuilder();
		sb.append("package org.archid.civ4.info." + tagVals.folder + ";");
		sb.append(NEWLINE + "import java.util.ArrayList;");
		sb.append(NEWLINE + "import java.util.List;");
		sb.append(NEWLINE);
		sb.append(NEWLINE + "import javax.xml.bind.annotation.XmlElement;");
		sb.append(NEWLINE + "import javax.xml.bind.annotation.XmlElementWrapper;");
		sb.append(NEWLINE + "import javax.xml.bind.annotation.adapters.XmlAdapter;");
		sb.append(NEWLINE);
		sb.append(NEWLINE + "import org.archid.civ4.info." + tagVals.folder + "." + tagVals.outer + "." + tagVals.wrapper + ";");
		sb.append(NEWLINE + "import org.archid.utils.CollectionUtils;");
		sb.append(NEWLINE + "import org.archid.utils.JaxbUtils;");
		sb.append(NEWLINE);
		sb.append(NEWLINE + "public class " + tagVals.outer + "Adapter extends XmlAdapter<" + tagVals.outer + "Adapter.Adapted" + tagVals.outer + ", " + tagVals.outer + "> {");
		sb.append(NEWLINE + "");
		sb.append(NEWLINET + "public static class Adapted" + tagVals.outer + " {");
		sb.append(NEWLINETT + "@XmlElement(name = \"" + tagVals.wrapper + "\")");
		sb.append(NEWLINETT + "private List<Adapted" + tagVals.wrapper + "> entries = new ArrayList<Adapted" + tagVals.wrapper + ">();");
		sb.append(NEWLINET + "}");
		sb.append(NEWLINE + "");
		sb.append(NEWLINET + "static class Adapted" + tagVals.wrapper + " {");
		sb.append(NEWLINETT + "@XmlElement(name=\"" + tagVals.resource + "\")");
		sb.append(NEWLINETT + "String resource;");
		sb.append(NEWLINETT + "@XmlElementWrapper(name=\"" + tagVals.list + "\")");
		sb.append(NEWLINETT + "@XmlElement(name=\"" + tagVals.element + "\")");
		sb.append(NEWLINETT + "List<Integer> list;");
		sb.append(NEWLINET + "}");
		sb.append(NEWLINE);
		sb.append(NEWLINET + "@Override");
		sb.append(NEWLINET + "public " + tagVals.outer + " unmarshal(Adapted" + tagVals.outer + " v) throws Exception {");
		sb.append(NEWLINETT  + tagVals.outer + " changes = new " + tagVals.outer + "();");
		sb.append(NEWLINETT + "for (Adapted" + tagVals.wrapper + " adapter: v.entries) {");
		sb.append(NEWLINETTT  + tagVals.wrapper + " wrapper = new " + tagVals.wrapper + "();");
		sb.append(NEWLINETTT + "wrapper.setResource(JaxbUtils.unmarshallString(adapter.resource));");
		sb.append(NEWLINETTT + "if (CollectionUtils.hasElements(adapter.list)) {");
		sb.append(NEWLINETTTT + "for (Integer val: adapter.list) {");
		sb.append(NEWLINETTTTT + "wrapper.addElement(val);");
		sb.append(NEWLINETTTT + "}");
		sb.append(NEWLINETTT + "}");
		sb.append(NEWLINETTT + "changes.get" + tagVals.wrapper + "List().add(wrapper);");
		sb.append(NEWLINETT + "}");
		sb.append(NEWLINETT + "return changes;");
		sb.append(NEWLINET + "}");
		sb.append(NEWLINE);
		sb.append(NEWLINET + "@Override");
		sb.append(NEWLINET + "public Adapted" + tagVals.outer + " marshal(" + tagVals.outer + " v) throws Exception {");
		sb.append(NEWLINETT + "if (!CollectionUtils.hasElements(v.get" + tagVals.wrapper + "List())) return null;");
		sb.append(NEWLINE);
		sb.append(NEWLINETT + "Adapted" + tagVals.outer + " changes = new Adapted" + tagVals.outer + "();");
		sb.append(NEWLINETT + "for(" + tagVals.wrapper + " wrapper: v.get" + tagVals.wrapper + "List()) {");
		sb.append(NEWLINETTT + "Adapted" + tagVals.wrapper + " adapter = new Adapted" + tagVals.wrapper + "();");
		sb.append(NEWLINETTT + "adapter.resource = wrapper.getResource();");
		sb.append(NEWLINETTT + "adapter.list = new ArrayList<Integer>();");
		sb.append(NEWLINETTT + "if (CollectionUtils.hasElements(wrapper.getElements())) {");
		sb.append(NEWLINETTTT + "adapter.list = wrapper.getElements();");
		sb.append(NEWLINETTT + "}");
		sb.append(NEWLINETTT + "changes.entries.add(adapter);");
		sb.append(NEWLINETT + "}");
		sb.append(NEWLINETT + "return changes;");
		sb.append(NEWLINET + "}");
		sb.append(NEWLINE + "}");
		return sb.toString();
	}
	
	@Override
	public String getAdapterElement() {
		StringBuilder sb = new StringBuilder();
		sb.append(NEWLINETT + "@XmlElement(name=\"" + tagName + "\")");
		sb.append(NEWLINETT + "private " + getTagName() + " " + StringUtils.lCaseFirstChar(tagName) + ";");
		return sb.toString();
	}
	
}
