package org.archid.civ4.java.files;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.archid.civ4.java.IJavaFileCreator;
import org.archid.civ4.java.JavaCodeGeneratorData;
import org.archid.civ4.java.TagInstance;
import org.archid.civ4.schema.XmlTagInstance;
import org.archid.utils.PropertyHandler;
import org.archid.utils.PropertyKeys;

public class DefaultInfoCodeCreator implements IJavaFileCreator {

	@Override
	public String getFileContent() {
		String infoNameRoot = JavaCodeGeneratorData.getInstance().getInfoNameRoot();
		String infoName = JavaCodeGeneratorData.getInstance().getInfoName();
		String infoNamePlural = JavaCodeGeneratorData.getInstance().getInfoNamePlural();
		
		// Sort the imports, this is cosmetic, but easy enough
		Set<String> imports = new HashSet<String>(JavaCodeGeneratorData.getInstance().getDynamicImports());
		imports.add("import java.util.LinkedHashMap;");
		imports.add("import java.util.Map;");
		imports.add("import org.archid.civ4.info.AbstractInfos;");
		imports.add("import javax.xml.bind.annotation.XmlAccessType;");
		imports.add("import javax.xml.bind.annotation.XmlAccessorType;");
		imports.add("import javax.xml.bind.annotation.XmlElement;");
		imports.add("import javax.xml.bind.annotation.XmlRootElement;");
		imports.add("import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;");

		// Create the wrapper class
		StringBuilder mainClass = new StringBuilder();
		mainClass.append(NEWLINE + "@XmlRootElement(name=\"" + JavaCodeGeneratorData.getInstance().getInfoTopLevelTag() + "\", namespace=\"x-schema:" + PropertyHandler.getInstance().getAppProperty(PropertyKeys.PROPERTY_KEY_MOD_SCHEMA) + "\")");
		mainClass.append(NEWLINE + "@XmlAccessorType(XmlAccessType.NONE)");
		mainClass.append(NEWLINE + "public class " + infoNamePlural + " extends AbstractInfos<I" + infoName + "> {");
		mainClass.append(NEWLINE);
		mainClass.append(NEWLINET + "public " + infoNamePlural + "() {");
		mainClass.append(NEWLINETT + "super(new LinkedHashMap<String, I" + infoName + ">());");
		mainClass.append(NEWLINET + "}");
		mainClass.append(NEWLINE);
		mainClass.append(NEWLINET + "@XmlJavaTypeAdapter(" + infoNameRoot + "MapAdapter.class)");
		mainClass.append(NEWLINET + "@XmlElement(name=\"" + infoNamePlural + "\")");
		mainClass.append(NEWLINET + "public Map<String, I" + infoName + "> getInfoMap() {");
		mainClass.append(NEWLINETT + "return infos;");
		mainClass.append(NEWLINET + "}");
		mainClass.append(NEWLINE);
		mainClass.append(NEWLINET + "public static I" + infoName + " createInfo(String type) {");
		mainClass.append(NEWLINETT + "return new " + infoName + "(type);");
		mainClass.append(NEWLINET + "}");
		mainClass.append(NEWLINE);
		mainClass.append(NEWLINET + "@Override");
		mainClass.append(NEWLINET + "public void setInfoMap(Map<String, I" + infoName + "> infos) {");
		mainClass.append(NEWLINETT + "this.infos = infos;");
		mainClass.append(NEWLINET + "}");
		mainClass.append(NEWLINE);
		
		// Create the inner class
		mainClass.append(NEWLINET + "private static class " + infoName + " implements I" + infoName + "{");
		mainClass.append(NEWLINE);
		StringBuilder vars = new StringBuilder();
		StringBuilder methods = new StringBuilder();
		// We always have the constructor first
		methods.append(NEWLINE);
		methods.append(NEWLINETT + "private " + infoName + "(String type) {");
		methods.append(NEWLINETTT + "this.type = type;");
		methods.append(NEWLINETT + "}");
		for (XmlTagInstance mainChild : JavaCodeGeneratorData.getInstance().getInfoChildTags()) {
			TagInstance tag = JavaCodeGeneratorData.getInstance().getTagInstance(mainChild.getTagName());
			if (tag.isCustomDataType()) {
				vars.append(NEWLINETT + "private " + tag.getDataType() + " " + tag.getVarName() + " = new " + tag.getDataType() + "();");
			} else if (tag.requiresArray()) {
				vars.append(NEWLINETT + "private List<" + tag.getDataType() + "> " + tag.getVarName() + " = new ArrayList<" + tag.getDataType() + ">();");
			} else { 
				vars.append(NEWLINETT + "private " + tag.getDataType() + " " + tag.getVarName() + ";");
			}
			methods.append(NEWLINE);
			methods.append(NEWLINETT + "@Override");
			methods.append(NEWLINETT + "public " + tag.getterSignature() + " {");
			methods.append(NEWLINETTT + "return " + tag.getVarName() + ";");
			methods.append(NEWLINETT + "}");
			methods.append(NEWLINE);
			methods.append(NEWLINETT + "@Override");
			methods.append(NEWLINETT + "public " + tag.setterSignature() + " {");
			if (tag.requiresArray() && !tag.isCustomDataType())
				methods.append(NEWLINETTT + "this." + tag.getVarName() + ".add(" + tag.setterVarName() + ");");
			else
				methods.append(NEWLINETTT + "this." + tag.getVarName() + " = " + tag.setterVarName() + ";");
			methods.append(NEWLINETT + "}");
		}
		mainClass.append(vars);
		mainClass.append(methods);
		mainClass.append(NEWLINET + "}");
		mainClass.append(NEWLINE + "}");

		// Construct the file
		StringBuilder file = new StringBuilder();
		file.append(JavaCodeGeneratorData.getInstance().getPackageDef());
		file.append(NEWLINE);
		List<String> sortedImports = new ArrayList<String>(imports);
		Collections.sort(sortedImports);
		for (String imp: sortedImports) {
			file.append(NEWLINE + imp);
		}
		file.append(NEWLINE);
		file.append(mainClass);
		
		return file.toString();
	}

}
