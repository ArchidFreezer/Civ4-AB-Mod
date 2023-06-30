package org.archid.civ4.java;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.archid.civ4.java.infoprocessor.IInfoProcessor;
import org.archid.civ4.schema.SchemaParser;
import org.archid.civ4.schema.XmlTagDefinition;
import org.archid.civ4.schema.XmlTagInstance;
import org.archid.civ4.schema.XmlTagDefinition.DataType;

public class JavaCodeGeneratorData {
	
	// Singleton instance
	private static JavaCodeGeneratorData singleton = new JavaCodeGeneratorData();
	
	private String namespaceFolder = null; // somevalue
	private String infoName = null;        // SomeValueInfo
	private String infoNameRoot = null;    // SomeValue
	private String infoNamePlural = null;  // SomeValueInfos
	private String infoTopLevelTag = null; // Civ4SomeValueInfos
	private String packageDef = null;
	private SchemaParser parser = null;

	private Set<String> dynamicImports = new HashSet<String>();
	
	private IInfoProcessor infoProcessor = null;
	private XmlTagDefinition infoTagDefinition;
	private Map<String, TagInstance> tagInstanceMap = new HashMap<String, TagInstance>();
	private TagNameData tagNameData = new TagNameData();
	
	private JavaCodeGeneratorData() {	}
	
	public static JavaCodeGeneratorData getInstance() {
		return singleton;
	}
	
	public void init(SchemaParser parser, String infoTopLevelTag) {
		this.parser = parser;
		this.infoTopLevelTag = infoTopLevelTag;                             // Civ4SomeValueInfos
		infoNamePlural = infoTopLevelTag.substring(4);                      // SomeValueInfos
		infoName = infoNamePlural.substring(0,infoNamePlural.length() - 1); // SomeValueInfo
		infoNameRoot = infoName.substring(0,infoName.length() - 4);         // SomeValue
		namespaceFolder = infoNameRoot.toLowerCase();                       // somevalue
		packageDef = "package org.archid.civ4.info." + namespaceFolder + ";";
		infoTagDefinition = parser.getTagDefinition(infoName);
		parseInfo();
		infoProcessor = InfoProcessorFactory.getProcessor(infoName);
		if (infoProcessor != null) {
			infoProcessor.init(namespaceFolder);
		}
	}

	private void parseInfo() {
		Map<String, DataType> tagDatatype = new HashMap<String, XmlTagDefinition.DataType>();
		for (XmlTagInstance tag: getInfoChildTags()) {
			XmlTagDefinition tagDef = parser.getTagDefinition(tag.getTagName());
			TagInstance tagInstance = new TagInstance(tagDef, tag);
			if (tagInstance.requiresArray()) {
				dynamicImports.add("import java.util.List;");
				dynamicImports.add("import java.util.ArrayList;");
			}
			if (tagInstance.getNumLeaves() == 2) dynamicImports.add("import org.archid.utils.IPair;");
			if (tagInstance.getNumLeaves() == 3) dynamicImports.add("import org.archid.utils.ITriple;");
			tagInstanceMap.put(tag.getTagName(), tagInstance);
			tagDatatype.put(tag.getTagName(), tagDef.getDataType());
		}
		// Get the variable names to be used
		tagNameData.buildUniqueNames(tagDatatype);
		// Set the variable names to be used
		for (String tagName: tagInstanceMap.keySet()) {
			tagInstanceMap.get(tagName).setRootName(tagNameData.getRootName(tagName));
			tagInstanceMap.get(tagName).setVarName(tagNameData.getVarName(tagName));
			tagInstanceMap.get(tagName).init();
		}
	}

	public XmlTagDefinition getTagDefinition(String tag) {
		return parser.getTagDefinition(tag);
	}

	public List<XmlTagInstance> getInfoChildTags() {
		return infoTagDefinition.getChildren();
	}

	public TagInstance getTagInstance(String tag) {
		return tagInstanceMap.get(tag);
	}

	public TagNameData getTagNameData() {
		return tagNameData;
	}

	public Set<String> getDynamicImports() {
		return dynamicImports;
	}

	public String getPackageDef() {
		return packageDef;
	}

	public String getInfoTopLevelTag() {
		return infoTopLevelTag;
	}

	public String getNamespaceFolder() {
		return namespaceFolder;
	}

	public String getInfoNameRoot() {
		return infoNameRoot;
	}

	public String getInfoName() {
		return infoName;
	}

	public String getInfoNamePlural() {
		return infoNamePlural;
	}

	public IInfoProcessor getInfoProcessor() {
		return infoProcessor;
	}

}
