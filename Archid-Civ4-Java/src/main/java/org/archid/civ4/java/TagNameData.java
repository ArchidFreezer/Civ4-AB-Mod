package org.archid.civ4.java;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.map.HashedMap;
import org.archid.civ4.schema.XmlTagDefinition.DataType;
import org.archid.utils.StringUtils;

public class TagNameData {
	
	private static Map<String, String> javaKeywords = buildJavaKeywordsMap();
	private static Map<String, String> buildJavaKeywordsMap() {
		Map<String, String> map = new HashedMap<String, String>();
		map.put("Class", "Clazz");
		map.put("Package", "PackageVal");
		return map;
	}
	
	private static Map<String, String> singularMap = buildSingularMap();
	private static Map<String, String> buildSingularMap() {
		Map<String, String> map = new HashedMap<String, String>();
		map.put("Bonuses", "Bonus");
		map.put("Buildings", "Building");
		map.put("Classes", "Class");
		map.put("Corporations", "Corporation");
		map.put("Events", "Event");
		map.put("Features", "Feature");
		map.put("Improvements", "Improvement");
		map.put("Prereqs", "Prereq");
		map.put("Religions", "Religion");
		map.put("Routes", "Route");
		map.put("Techs", "Tech");
		map.put("Terrains", "Terrain");
		map.put("Types", "Type");
		map.put("Units", "Unit");
		return map;
	}
	
	private Map<String, String> tagUniqueNames = new HashMap<String, String>();

	/**
	 * Generate unique names for the tags in an info file
	 * The default name has any leading i or b char removed for Integer and Boolean datatypes. In some
	 * cases this may lead to duplicate names such as in UnitInfos there are Combat and iCombat tags which
	 * would produce the same output. In cases of name collisions the data type decides which name is
	 * chosen with precedence given in the order:
	 * <ul>
	 * <li>String
	 * <li>Integer
	 * <li>Boolean
	 * </ul>
	 * For clashes Integer will have "Int" appended and Boolean will have "Bool" appended
	 * <br>In all cases if the final name would clash with a java keyword then an alternative will be used.
	 *
	 * <p>The output will be stored in the {@code tagVarNames} member variable
	 * 
	 * @param tagData {@code Map<String, DataType>} tag names to process
	 */
	public void buildUniqueNames(Map<String, DataType> tagData) {
		for (String tag: tagData.keySet()) {
			DataType type = tagData.get(tag);
			String unique = getSafeTagRootName(tag, type);
			if (tagUniqueNames.containsValue(unique)) {
				// We have a clash
				// If this is a boolean then we know it has lowest precedence so change it now
				if (type == DataType.BOOLEAN) {
					unique = getAltTagName(tag, type);
					tagUniqueNames.put(tag, unique);
				} else {
					String clashTag = null;
					for (String loopTag: tagUniqueNames.keySet()) {
						if (tagUniqueNames.get(loopTag).equals(unique)) {
							clashTag = loopTag;
							break;
						}
					}
					DataType clashType = tagData.get(clashTag);
					if (type == DataType.STRING) {
						// If this is a string then we have precedence
						tagUniqueNames.put(tag, unique);
						tagUniqueNames.put(clashTag, getAltTagName(clashTag, clashType));
					} else if (clashType == DataType.STRING) {
						// The existing entry has precedence, change our name
						tagUniqueNames.put(tag, getAltTagName(unique, type));
					} else {
						tagUniqueNames.put(tag, unique);
						tagUniqueNames.put(clashTag, getAltTagName(clashTag, clashType));
					}
				}
			} else {
				tagUniqueNames.put(tag, getSafeName(unique));
			}
		}
	}
	
	/**
	 * Get a unique name for a tag that can be used in method calls
	 * @param tag name of the tag
	 * @return unique name to use
	 */
	public String getRootName(String tag) {
		if (tagUniqueNames.containsKey(tag))
			return tagUniqueNames.get(tag);
		else
			return getSafeName(tag);
	}
	
	/**
	 * Get the java variable name to be used for a tag
	 * @param tag name of the tag
	 * @return variable name to use
	 */
	public String getVarName(String tag) {
		return getJavaVarName(getRootName(tag));
	}
	
	/**
	 * Get an alternative name for use in java code for a tag is the normal value is already in use
	 * @param var tag name to get alternative for
	 * @param type type of tag
	 * @return unique name to be used in java code
	 */
	private String getAltTagName(String var, DataType type) {
		StringBuilder sb = new StringBuilder();
		switch (type) {
		case BOOLEAN:
			sb.append(var.substring(1) + "Bool");
			break;
		case INTEGER:
			sb.append(var.substring(1) + "Int");
			break;
		default:
			sb.append(var);
			break;
		}
		return getSafeName(sb.toString());
	}

	
	/**
	 * Attempt to derive a singular form of plural tags
	 * e.g. UnitsRequired -> UnitRequired
	 *      PrereqTechs   -> PrereqTech
	 *      
	 * @param plural TitleCase tag name to derive the plural from
	 * @return String containing derived singular value
	 */
	public String singularForm(String plural) {
		StringBuilder singular = new StringBuilder();
		// First try and split the value assuming TitleCase
		List<String> words = StringUtils.findWordsInMixedCase(plural);
		
		Integer numWordsLeft = words.size();
		for (String word: words) {
			numWordsLeft--;
			// Need to check if we are processing a word starting with a cap
			boolean lCase = StringUtils.startsWithLowerCase(word);
			String test = lCase ? StringUtils.uCaseFirstChar(word) : word;
			if (singularMap.containsKey(test)) {
				singular.append(lCase ? StringUtils.lCaseFirstChar(singularMap.get(test)) : singularMap.get(test));
			} else if (numWordsLeft == 0 && word.endsWith("s")) {
				singular.append(word.substring(0,word.length() -1));
			} else {
				singular.append(word);
			}
		}
		return singular.toString(); 
	}
	
	/**
	 * Strip off any leading char for Integer and Boolean types, then ensure resulting string is
	 * safe for use as a variable or in a method signature
	 * @param var tag name to process
	 * @param type data type of the tag
	 * @return String that is safe to use in java code
	 */
	private String getSafeTagRootName(String var, DataType type) {
		StringBuilder sb = new StringBuilder();
		switch (type) {
		case BOOLEAN:
		case INTEGER:
			sb.append(var.substring(1));
			break;
		default:
			sb.append(var);
			break;
		}
		return getSafeName(sb.toString());
	}

	private String getJavaVarName(String var) {
		return StringUtils.lCaseFirstChar(getSafeName(var));
	}

	/**
	 * If the passed in value is one of a list of java keywords commonly used an alternative value will
	 * be returned. There is no guarantee that the replaced work is appropriate
	 */
	private String getSafeName(String var) {
		if (javaKeywords.containsKey(var)) {
			return javaKeywords.get(var);
		}
		return var;
	}

}
