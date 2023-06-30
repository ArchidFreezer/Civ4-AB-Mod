/**
 * 
 */
package org.archid.civ4.schema;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


import org.apache.log4j.Logger;
import org.archid.civ4.schema.XmlTagDefinition.DataType;

/**
 * @author jim
 * 
 */
public class SchemaParser {

	/** Logging facility */
	static Logger log = Logger.getLogger(SchemaParser.class.getName());
	private String schemaName = null;
	private String schemaFolder = null;

	/** Map of tags in schema */
	static protected Map<String, XmlTagDefinition> allUserTags = new HashMap<String, XmlTagDefinition>();
	static protected Map<String, XmlTagDefinition> userTags = new HashMap<String, XmlTagDefinition>();
	static protected Map<String, XmlTagDefinition> allBtsTags = new HashMap<String, XmlTagDefinition>();
	static protected Map<String, XmlTagDefinition> btsTags = new HashMap<String, XmlTagDefinition>();
	
	/** RE for schema lines */
	private Pattern parentPattern = Pattern.compile("\\s*<\\s*ElementType\\s*name\\s*=\\s*\"([a-zA-Z0-9]*)\"\\s*(?:content=\"([^\"]+)\")\\s*(?:dt:type\\s*=\"([^\"]+)\")?\\s*(/)?>.*");
	private Pattern childPattern1 = Pattern.compile("\\s*<\\s*element\\s*type\\s*=\\s*\"([a-zA-Z0-9]*)\"\\s*(?:minOccurs=\"([^\"]+)\")?\\s*(?:maxOccurs=\"([^\"]+)\")?\\s*/>(?:\\s*<!--\\s*Default:\\s*(-?[\\w]+))?.*");
	private Pattern childPattern2 = Pattern.compile("\\s*<\\s*element\\s*type\\s*=\\s*\"([a-zA-Z0-9]*)\"\\s*(?:maxOccurs=\"([^\"]+)\")?\\s*(?:minOccurs=\"([^\"]+)\")?\\s*/>(?:\\s*<!--\\s*Default:\\s*(-?[\\w]+))?.*");
	private Pattern endPattern = Pattern.compile("\\s*</\\s*ElementType\\s*>.*");

	public XmlTagDefinition getTagDefinition(String tagName) {
		return allUserTags.get(schemaFolder + "." + tagName);
	}
	
	public XmlTagDefinition getUserTagDefinition(String tagName) {
		return userTags.get(schemaFolder + "." + tagName);
	}
	
	public XmlTagDefinition getBtsTagDefinition(String tagName) {
		return allBtsTags.get(schemaFolder + "." + tagName);
	}
	
	public XmlTagDefinition getCurrentBtsTagDefinition(String tagName) {
		return btsTags.get(schemaFolder + "." + tagName);
	}
	
	public void setSchemaFolder(String folder) {
		schemaFolder = folder;
	}
	
	/**
	 * Reads the schema file and populates the hash of tags
	 * 
	 * @throws FileNotFoundException
	 */
	public void parse(String modSchema) throws FileNotFoundException {
		BufferedReader userIn = null;
		XmlTagDefinition tag = null;
		
		userTags.clear();

		schemaName = modSchema;

		String line = null;
		try {
			tag = null;
			userIn = new BufferedReader(new FileReader(modSchema));
			line = userIn.readLine();
			while (line != null) {
				tag = parseTag(line, tag, userTags);
				if (null != tag && tag.isCompleted()) {
					log.debug("Adding tag " + tag.getTagName().toLowerCase() + " from " + modSchema);
					userTags.put(schemaFolder + "." + tag.getTagName(), tag);
					allUserTags.put(schemaFolder + "." + tag.getTagName(), tag);
					tag = null;
				}
				line = userIn.readLine();
			}
			
			for (XmlTagDefinition testTag: userTags.values()) {
				if (!testTag.isCompleted())
					log.warn("Tag: " + testTag.getTagName() + " in " + modSchema + " looks to either have a broken definition or referenced without being defined");
			}
		} catch (FileNotFoundException e) {
			log.error("Could not find file: ", e);
			throw e;
		} catch (IOException e) {
			log.error("IO error: ", e);
		} finally {
			try {
				if (null != userIn)
					userIn.close();
			} catch (IOException e) {
				log.error("Error closing the input file");
			}
		}

	}

	/**
	 * Reads the schema file and populates the hash of tags
	 * 
	 * @throws FileNotFoundException
	 */
	public void parse(String modSchema, String refSchema) throws FileNotFoundException {
		BufferedReader userIn = null;
		BufferedReader btsIn = null;
		XmlTagDefinition tag = null;
		
		userTags.clear();
		btsTags.clear();

		schemaName = modSchema;

		String line = null;
		try {
			btsIn = new BufferedReader(new FileReader(refSchema));
			line = btsIn.readLine();
			while (line != null) {
				tag = parseTag(line, tag, btsTags);
				if (null != tag && tag.isCompleted()) {
					log.debug("Adding tag " + tag.getTagName().toLowerCase() + " from " + refSchema);
					btsTags.put(schemaFolder + "." + tag.getTagName(), tag);
					allBtsTags.put(schemaFolder + "." + tag.getTagName(), tag);
					tag = null;
				}
				line = btsIn.readLine();
			}

			tag = null;
			userIn = new BufferedReader(new FileReader(modSchema));
			line = userIn.readLine();
			while (line != null) {
				tag = parseTag(line, tag, userTags);
				if (null != tag && tag.isCompleted()) {
					log.debug("Adding tag " + tag.getTagName().toLowerCase() + " from " + modSchema);
					userTags.put(schemaFolder + "." + tag.getTagName(), tag);
					allUserTags.put(schemaFolder + "." + tag.getTagName(), tag);
					tag = null;
				}
				line = userIn.readLine();
			}
			
			for (XmlTagDefinition testTag: userTags.values()) {
				if (!testTag.isCompleted())
					log.warn("Tag: " + testTag.getTagName() + " in " + modSchema + " looks to either have a broken definition or referenced without being defined");
			}
		} catch (FileNotFoundException e) {
			log.error("Could not find file: ", e);
			throw e;
		} catch (IOException e) {
			log.error("IO error: ", e);
		} finally {
			try {
				if (null != btsIn)
					btsIn.close();
			} catch (IOException e) {
				log.error("Error closing the input file");
			}
			try {
				if (null != userIn)
					userIn.close();
			} catch (IOException e) {
				log.error("Error closing the input file");
			}
		}

	}

	/**
	 * Parses a line from the schema file into a String array.
	 * 
	 */
	protected XmlTagDefinition parseTag(String line, XmlTagDefinition tag, Map<String, XmlTagDefinition> tags) {
		if (tag == null) {
			// We need to loop until we find the starting line of a new tag,
			// ignore any other lines
			// RE matches are:
			// 1 - tag name
			// 2 - content type
			// 3 - data type
			// 4 - end marker
			Matcher parentMatcher = parentPattern.matcher(line);
			if (parentMatcher.matches()) {

				// Check to see if this is a new definition
				// It is a valid scenario for the tag to exist an be incomplete
				// if it is referenced before being defined
				String tagName = parentMatcher.group(1);
				if (tags.containsKey(schemaFolder + "." + tagName)) {
					tag = tags.get(schemaFolder + "." + tagName);
					if (tag.isCompleted())
						log.warn("Duplicate definition of " + tagName + " tag.");
				} else {
					tag = new XmlTagDefinition(tagName);
				}

				if (parentMatcher.group(4) != null)
				{
					tag.setCompleted(true);
				}
				// The default data type is STRING which corresponds with the default in the schema
				// We also have an ugly hack for floats
				if (parentMatcher.group(3) != null)
					tag.setDataType(DataType.fromLabel(parentMatcher.group(3)));
				else if (tagName.startsWith("f"))
					tag.setDataType(DataType.FLOAT);

				tag.setCompleted(parentMatcher.group(4) != null);
			}
		} else {
			// This is a multi-line tag so look for any child element
			// definitions
			// RE matches are:
			// 1 - tag name
			// 2 - min occurs value
			// 3 - max occurs value
			// 4 - default value
			Matcher childMatcher1 = childPattern1.matcher(line);
			Matcher childMatcher2 = childPattern2.matcher(line);
			Matcher endMatcher = endPattern.matcher(line);
			if (endMatcher.matches()) {
				tag.setCompleted(true);
			} else if (childMatcher1.matches()) {

				// Find the childs tag definition and set it to be non top level
				String childName = childMatcher1.group(1);
				XmlTagDefinition childTag = tags.get(schemaFolder + "." + childName);
				if (childTag == null) {
					childTag = new XmlTagDefinition(childName);
				}
				childTag.setTopLevel(false);
				tags.put(schemaFolder + "." + childName, childTag);

				XmlTagInstance child = new XmlTagInstance(childMatcher1.group(1));
				if (childMatcher1.group(2) != null)
					try {
						int minCount = Integer.parseInt(childMatcher1.group(2));
						child.setMandatory(minCount != 0);
					} catch (NumberFormatException e) {
						log.error("Error processing " + childMatcher1.group(1) + ": could not convert minOccurs value of " + childMatcher1.group(2) + " to an int\nFull line: " + childMatcher1.group(0));
					}
				if (childMatcher1.group(3) != null) {
					if (childMatcher1.group(3).equals("*")) {
						child.setCount(-1);
					} else {
						try {
							int maxCount = Integer.parseInt(childMatcher1.group(3));
							child.setCount(maxCount);
						} catch (NumberFormatException e) {
							log.error("Error processing " + childMatcher1.group(1) + ": could not convert maxOccurs value of " + childMatcher1.group(3) + " to an int\nFull line: " + childMatcher1.group(0));
						}
					}
				}
				// Get any default value
				child.setDefaultVal(childMatcher1.group(4));
				tag.addChild(child);
				tag.setDataType(DataType.MULTI);
			} else if (childMatcher2.matches()) {

				// Find the childs tag definition and set if to be non top level
				String childName = childMatcher2.group(1);
				XmlTagDefinition childTag = tags.get(schemaFolder + "." + childName);
				if (childTag == null) {
					childTag = new XmlTagDefinition(childName);
				}
				childTag.setTopLevel(false);
				tags.put(schemaFolder + "." + childName, childTag);

				XmlTagInstance child = new XmlTagInstance(childMatcher2.group(1));
				if (childMatcher2.group(2) != null)
					if (childMatcher2.group(2).equals("*"))
					{
						child.setCount(-1);
					} else {
						try {
							int maxCount = Integer.parseInt(childMatcher2.group(2));
							child.setCount(maxCount);
						} catch (NumberFormatException e) {
							log.error("Error processing " + childMatcher2.group(1) + ": could not convert maxOccurs value of " + childMatcher2.group(2) + " to an int\nFull line: " + childMatcher2.group(0));
						}
					}
				if (childMatcher2.group(3) != null) {
					if (childMatcher2.group(3).equals("*")) {
						child.setCount(-1);
					} else {
						try {
							int minCount = Integer.parseInt(childMatcher2.group(3));
							child.setMandatory(minCount != 0);
						} catch (NumberFormatException e) {
							log.error("Error processing " + childMatcher2.group(1) + ": could not convert minOccurs value of " + childMatcher2.group(3) + " to an int\nFull line: " + childMatcher2.group(0));
						}
					}
				}
				// Get any default value
				child.setDefaultVal(childMatcher2.group(4));
				tag.addChild(child);
				tag.setDataType(DataType.MULTI);
			} else {
				log.info(schemaName + ": Could not parse line " + line);
			}
		}

		return tag;
	}

	public Set<XmlTagDefinition> getPrintableTags() {
		Set<XmlTagDefinition> tags = new HashSet<XmlTagDefinition>();
		Set<String> ignores = SchemaPropertyHandler.getInstance().getIgnores();

		for (XmlTagDefinition tag : userTags.values()) {
			if (tag.isTopLevel() && !ignores.contains(tag.getTagName().toLowerCase())) {
				log.debug("Adding tag " + tag.getTagName().toLowerCase() + " to be processed");
				tags.add(tag);
			}
		}

		return tags;
	}
	
}
