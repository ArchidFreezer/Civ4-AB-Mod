package org.archid.civ4.java.files;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.archid.civ4.java.IJavaFileCreator;
import org.archid.civ4.java.ITagProcessor;
import org.archid.civ4.java.JavaCodeGeneratorData;
import org.archid.civ4.java.TagInstanceLeafData;
import org.archid.civ4.java.TagInstance;
import org.archid.civ4.java.infoprocessor.IInfoProcessor;
import org.archid.civ4.schema.XmlTagDefinition;
import org.archid.civ4.schema.XmlTagInstance;

public class DefaultInfoMapAdapterCreator implements IJavaFileCreator {

	/** Logging facility */
	static Logger log = Logger.getLogger(DefaultInfoMapAdapterCreator.class.getName());
	
	@Override
	public String getFileContent() {
		String infoNameRoot = JavaCodeGeneratorData.getInstance().getInfoNameRoot();
		String infoName = JavaCodeGeneratorData.getInstance().getInfoName();
		IInfoProcessor infoProcessor = JavaCodeGeneratorData.getInstance().getInfoProcessor();

		Set<String> imports = new HashSet<String>(JavaCodeGeneratorData.getInstance().getDynamicImports());
		imports.add("import java.util.ArrayList;");
		imports.add("import java.util.List;");
		imports.add("import java.util.Map;");
		imports.add("import java.util.TreeMap;");
		imports.add("import javax.xml.bind.annotation.XmlElement;");
		imports.add("import javax.xml.bind.annotation.XmlElementWrapper;");
		imports.add("import javax.xml.bind.annotation.adapters.XmlAdapter;");
		imports.add("import org.archid.utils.CollectionUtils;");
		imports.add("import org.archid.utils.JaxbUtils;");
		imports.add("import org.archid.utils.StringUtils;");
		
		
		// There are 3 core classes and the custom adapters inside the main class with each tag having entries
		// in at least the 3 core classes so to prevent multiple iterations we will use 4 StringBuilders
		StringBuilder adaptedClass = new StringBuilder();
		adaptedClass.append(NEWLINET + "private static class Adapted" + infoNameRoot + " {");

		StringBuilder customAdapters = new StringBuilder();

		StringBuilder unmarshalClass = new StringBuilder();
		unmarshalClass.append(NEWLINE);
		unmarshalClass.append(NEWLINET + "@Override");
		unmarshalClass.append(NEWLINET + "public Map<String, I" + infoName + "> unmarshal(" + infoNameRoot + "Map v) throws Exception {");
		unmarshalClass.append(NEWLINETT + "Map<String, I" + infoName + "> map = new TreeMap<String, I" + infoName + ">();");
		unmarshalClass.append(NEWLINETT + "for (Adapted" + infoNameRoot + " aInfo: v.entries) {");
		unmarshalClass.append(NEWLINETTT + "I" + infoName + " info = " + JavaCodeGeneratorData.getInstance().getInfoNamePlural() + ".createInfo(aInfo.type);");

		StringBuilder marshalClass = new StringBuilder();
		marshalClass.append(NEWLINE);
		marshalClass.append(NEWLINET + "@Override");
		marshalClass.append(NEWLINET + "public " + infoNameRoot + "Map marshal(Map<String, I" + infoName + "> v) throws Exception {");
		marshalClass.append(NEWLINETT + infoNameRoot + "Map map = new " + infoNameRoot + "Map();");
		marshalClass.append(NEWLINETT + "for (I" + infoName + " info: v.values()) {");
		marshalClass.append(NEWLINETTT + "Adapted" + infoNameRoot + " aInfo = new Adapted" + infoNameRoot + "();");
		marshalClass.append(NEWLINETTT + "aInfo.type = JaxbUtils.marshallString(info.getType());");
		
		for (XmlTagInstance mainChild : JavaCodeGeneratorData.getInstance().getInfoChildTags()) {
			ITagProcessor tagProcessor = null;
			TagInstance tag = JavaCodeGeneratorData.getInstance().getTagInstance(mainChild.getTagName());
			String tagVarName = tag.getVarName();
			if (infoProcessor.hasTagProcessor(mainChild.getTagName())) {
				tagProcessor = infoProcessor.getTagProcessor(mainChild.getTagName());
				imports.addAll(tagProcessor.getAdapterImports());
			}
			
			// Process the adapted class
			if (tagProcessor != null) {
				adaptedClass.append(tagProcessor.getAdapterElement());
			} else if (tag.requiresAdapter()) {
				XmlTagDefinition innerTagXmlDef = JavaCodeGeneratorData.getInstance().getTagDefinition(tag.getChildren().get(0).getTagName());
				adaptedClass.append(NEWLINETT + "@XmlElementWrapper(name=\"" + mainChild.getTagName() + "\")");
				adaptedClass.append(NEWLINETT + "@XmlElement(name=\"" + innerTagXmlDef.getTagName() + "\")");
				adaptedClass.append(NEWLINETT + "private List<Adapted" + mainChild.getTagName() + "> " + tagVarName + ";");				
			} else if (tag.requiresArray()) {
				XmlTagDefinition innerTagXmlDef = JavaCodeGeneratorData.getInstance().getTagDefinition(tag.getChildren().get(0).getTagName());
				adaptedClass.append(NEWLINETT + "@XmlElementWrapper(name=\"" + mainChild.getTagName() + "\")");
				adaptedClass.append(NEWLINETT + "@XmlElement(name=\"" + innerTagXmlDef.getTagName() + "\")");
				adaptedClass.append(NEWLINETT + "private List<" + getXmlDataType(tag.getLeaf(0).getType()) + "> " + tagVarName + ";");				
			} else {
				adaptedClass.append(NEWLINETT + "@XmlElement(name=\"" + mainChild.getTagName() + "\")");
				adaptedClass.append(NEWLINETT + "private " + getXmlDataType(tag.getLeaf(0).getType()) + " " + tagVarName + ";");				
			}
			
			// Process any custom adapters
			if (tag.requiresAdapter() && tagProcessor == null) {
				if (tag.isCustomDataType()) {
					log.warn("Unable to create adapter for " + tag.getRootName() + ": " + tag.getDataType());
					customAdapters.append(NEWLINE);
					customAdapters.append(NEWLINET + "private static class Adapted" + mainChild.getTagName() + " {");
					customAdapters.append(NEWLINETT + "@XmlElement(name=\"" + tag.getDataType() + "\")");
					customAdapters.append(NEWLINETT + "private " + tag.getDataType() + " VAR_NAME;");
					customAdapters.append(NEWLINET + "}");
				} else {
					customAdapters.append(NEWLINE);
					customAdapters.append(NEWLINET + "private static class Adapted" + mainChild.getTagName() + " {");
					for (TagInstanceLeafData leaf: tag.getLeaves()) {
						customAdapters.append(NEWLINETT + "@XmlElement(name=\"" + leaf.getName() + "\")");
						customAdapters.append(NEWLINETT + "private " + getXmlDataType(leaf.getType()) + " " + leaf.getVarName() + ";");
					}
					customAdapters.append(NEWLINET + "}");
				}
			}
			
			// Process the unmarshall class
			// The type is set when we instantiated the info class above and has no mutator
			if (!tag.getRootName().equals("Type")) {
				if (tagProcessor != null) {
					unmarshalClass.append(tagProcessor.getUnmarshallString());
				} else if (tag.isCustomDataType()) {
					unmarshalClass.append(NEWLINE);
					unmarshalClass.append(NEWLINETTT + "if (CollectionUtils.hasElements(aInfo." + tagVarName + ")) {");
					unmarshalClass.append(NEWLINETTTT + "for (Adapted" + mainChild.getTagName() + " adaptor: aInfo." + tagVarName + ") {");
					unmarshalClass.append(NEWLINETTTTT + tag.getDataType());
					unmarshalClass.append(NEWLINETTTT + "}");
					unmarshalClass.append(NEWLINETTT + "}");
				} else if (tag.requiresAdapter()) {
					unmarshalClass.append(NEWLINE);
					unmarshalClass.append(NEWLINETTT + "if (CollectionUtils.hasElements(aInfo." + tagVarName + ")) {");
					unmarshalClass.append(NEWLINETTTT + "for (Adapted" + mainChild.getTagName() + " adaptor: aInfo." + tagVarName + ") {");
					unmarshalClass.append(NEWLINETTTTT + "if (StringUtils.hasCharacters(adaptor." + tag.getLeaf(0).getVarName() + ")) {");
					if (tag.getNumLeaves() == 2) {
						imports.add("import org.archid.utils.Pair;");
						unmarshalClass.append(NEWLINETTTTTT + "info." + tag.getSetterName() + "(new Pair<" + tag.getLeaf(0).getType() + ", " + tag.getLeaf(1).getType() + ">(adaptor." + tag.getLeaf(0).getVarName() + ", adaptor." + tag.getLeaf(1).getVarName() + "));");
					} else if (tag.getNumLeaves() == 3) {
						imports.add("import org.archid.utils.Triple;");
						unmarshalClass.append(NEWLINETTTTTT + "info." + tag.getSetterName() + "(new Triple<" + tag.getLeaf(0).getType() + ", " + tag.getLeaf(1).getType() + ", " + tag.getLeaf(2).getType() + ">(adaptor." + tag.getLeaf(0).getVarName() + ", adaptor." + tag.getLeaf(1).getVarName() + ", adaptor." + tag.getLeaf(2).getVarName() + "));");						
					}
					unmarshalClass.append(NEWLINETTTTT + "}");
					unmarshalClass.append(NEWLINETTTT + "}");
					unmarshalClass.append(NEWLINETTT + "}");
				} else if (tag.requiresArray()) {
					unmarshalClass.append(NEWLINE);
					unmarshalClass.append(NEWLINETTT + "if (CollectionUtils.hasElements(aInfo." + tagVarName + ")) {");
					unmarshalClass.append(NEWLINETTTT + "for (" + getXmlDataType(tag.getDataType()) + " val: aInfo." + tagVarName + ") {");
					if (tag.getDataType().equals("String")) {
						unmarshalClass.append(NEWLINETTTTT + "if (StringUtils.hasCharacters(val)) {");
						unmarshalClass.append(NEWLINETTTTTT + "info." + tag.getSetterName() + "(" + getUnmarshallString(tag, "val") + ");");
						unmarshalClass.append(NEWLINETTTTT + "}");
					} else {
						unmarshalClass.append(NEWLINETTTTT + "info." + tag.getSetterName() + "(" + getUnmarshallString(tag, "val") + ");");
					}
					unmarshalClass.append(NEWLINETTTT + "}");
					unmarshalClass.append(NEWLINETTT + "}");
				} else {
					unmarshalClass.append(NEWLINETTT + "info." + tag.getSetterName() + "(" + getUnmarshallString(tag, "aInfo." + tagVarName) + ");");
				}
			}
			
			// Process the marshall class
			if (!tag.getRootName().equals("Type")) {
				if (tagProcessor != null) {
					marshalClass.append(tagProcessor.getMarshallString());
				} else if (tag.isCustomDataType()) {
					marshalClass.append(NEWLINE);
					marshalClass.append(NEWLINETTT + "if (CollectionUtils.hasElements(info." + tag.getGetterName() + "())) {");
					marshalClass.append(NEWLINETTTT + "aInfo." + tagVarName + " = new ArrayList<Adapted" + mainChild.getTagName() + ">();");
					marshalClass.append(NEWLINETTTTT + tag.getDataType());
					marshalClass.append(NEWLINETTTT + "}");
					marshalClass.append(NEWLINETTT + "}");
				} else if (tag.requiresAdapter()) {
					marshalClass.append(NEWLINE);
					marshalClass.append(NEWLINETTT + "if (CollectionUtils.hasElements(info." + tag.getGetterName() + "())) {");
					marshalClass.append(NEWLINETTTT + "aInfo." + tagVarName + " = new ArrayList<Adapted" + mainChild.getTagName() + ">();");
					if (tag.getNumLeaves() == 2) {
						marshalClass.append(NEWLINETTTT + "for (IPair<" + tag.getLeaf(0).getType() + ", " + tag.getLeaf(1).getType() + "> pair: info." + tag.getGetterName() + "()) {");
						marshalClass.append(NEWLINETTTTT + "Adapted" + mainChild.getTagName() + " adaptor = new Adapted" + mainChild.getTagName() + "();");
						marshalClass.append(NEWLINETTTTT + "adaptor." + tag.getLeaf(0).getVarName() + " = pair.getKey();");
						marshalClass.append(NEWLINETTTTT + "adaptor." + tag.getLeaf(1).getVarName() + " = pair.getValue();");
					} else if (tag.getNumLeaves() == 3) {
						marshalClass.append(NEWLINETTTT + "for (ITriple<" + tag.getLeaf(0).getType() + ", " + tag.getLeaf(1).getType() + ", " + tag.getLeaf(2).getType() + "> triple: info." + tag.getGetterName() + "()) {");
						marshalClass.append(NEWLINETTTTT + "Adapted" + mainChild.getTagName() + " adaptor = new Adapted" + mainChild.getTagName() + "();");
						marshalClass.append(NEWLINETTTTT + "adaptor." + tag.getLeaf(0).getVarName() + " = triple.getKey();");
						marshalClass.append(NEWLINETTTTT + "adaptor." + tag.getLeaf(1).getVarName() + " = triple.getValue();");
						marshalClass.append(NEWLINETTTTT + "adaptor." + tag.getLeaf(2).getVarName() + " = triple.getData();");
					}
					marshalClass.append(NEWLINETTTTT + "aInfo." + tagVarName + ".add(adaptor);");
					marshalClass.append(NEWLINETTTT + "}");
					marshalClass.append(NEWLINETTT + "}");
				} else if (tag.requiresArray()) {
					marshalClass.append(NEWLINE);
					marshalClass.append(NEWLINETTT + "if (CollectionUtils.hasElements(info." + tag.getGetterName() + "())) {");
					marshalClass.append(NEWLINETTTT + "aInfo." + tagVarName + " = new ArrayList<" + getXmlDataType(tag.getDataType()) + ">();");
					marshalClass.append(NEWLINETTTT + "for(" + tag.getDataType() + " val: info." + tag.getGetterName() + "()) {");
					marshalClass.append(NEWLINETTTTT + "aInfo." + tagVarName + ".add(" + getMarshallString(tag, "val", true) + ");");
					marshalClass.append(NEWLINETTTT + "}");
					marshalClass.append(NEWLINETTT + "}");
				} else {
					marshalClass.append(NEWLINETTT + "aInfo." + tagVarName + " = " + getMarshallString(tag, "info." + tag.getGetterName() + "()") + ";");
				}
			}
			
			
		}
		
		adaptedClass.append(NEWLINET + "}");
		
		unmarshalClass.append(NEWLINE);
		unmarshalClass.append(NEWLINETTT + "map.put(aInfo.type, info);");
		unmarshalClass.append(NEWLINETT + "}");
		unmarshalClass.append(NEWLINETT + "return map;");
		unmarshalClass.append(NEWLINET + "}");
		
		marshalClass.append(NEWLINE);
		marshalClass.append(NEWLINETTT + "map.entries.add(aInfo);");
		marshalClass.append(NEWLINETT + "}");
		marshalClass.append(NEWLINETT + "return map;");
		marshalClass.append(NEWLINET + "}");
		
		StringBuilder file = new StringBuilder();
		file.append(JavaCodeGeneratorData.getInstance().getPackageDef());
		file.append(NEWLINE);
		// Sort the imports
		List<String> sortedImports = new ArrayList<String>(imports);
		Collections.sort(sortedImports);
		for (String imp: sortedImports) {
			file.append(NEWLINE + imp);
		}
		file.append(NEWLINE);
		file.append(NEWLINE + "public class " + infoNameRoot + "MapAdapter extends XmlAdapter<" + infoNameRoot + "MapAdapter." + infoNameRoot + "Map, Map<String, I" + infoName + ">> {");
		file.append(NEWLINE);
		file.append(NEWLINET + "public static class " + infoNameRoot + "Map {");
		file.append(NEWLINETT + "@XmlElement(name = \"" + infoName + "\")");
		file.append(NEWLINETT + "List<Adapted" + infoNameRoot + "> entries = new ArrayList<Adapted" + infoNameRoot + ">();");
		file.append(NEWLINET + "}");
		file.append(NEWLINE);
		file.append(adaptedClass);
		file.append(customAdapters);
		file.append(unmarshalClass);
		file.append(marshalClass);
		file.append(NEWLINE + "}");
		
		return file.toString();
	}

	private String getMarshallString(TagInstance tag, String val) {
		return getMarshallString(tag, val, tag.isMandatory());
	}
	
	private String getMarshallString(TagInstance tag, String val, boolean mandatory) {
		StringBuilder sb = new StringBuilder();
		sb.append("JaxbUtils.marshall");
		if (mandatory) sb.append("Mandatory");
		sb.append(tag.getDataType() + "(" + val + ")");
		return sb.toString();
	}
	
	private String getUnmarshallString(TagInstance tag, String val) {
		StringBuilder sb = new StringBuilder();
		sb.append("JaxbUtils.unmarshall");
		sb.append(tag.getDataType() + "(" + val + ")");
		return sb.toString();
	}
	
	private String getXmlDataType(String javaType) {
		if (javaType.equals("Boolean"))
			return "Integer";
		else if (javaType.equals("Float"))
			return "String";
		else
			return javaType;
	}

}
