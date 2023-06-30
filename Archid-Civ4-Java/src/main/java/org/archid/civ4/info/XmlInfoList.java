package org.archid.civ4.info;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;
import org.archid.utils.StringUtils;

public class XmlInfoList {

	/** Logging facility */
	static Logger log = Logger.getLogger(XmlInfoList.class);

	protected static final String newline = System.getProperty("line.separator");
	
	private String missingTagVal;

	private String xmlPath;
	
	private boolean sorted = true;

	private StringBuilder header = new StringBuilder();
	private StringBuilder footer = new StringBuilder();
	private String comment = null;

	/** {@link Map} keyed on {@code <Type>} tag values */ 
	private Map<String, IXmlInfo> typeMap;
	/** {@link Map} keyed on the tags being indexed with values containing the map with the index values */ 
	private Map<String, Map<String, Set<IXmlInfo>>> indexMaps;
	/** {@link Map} keyed on the indexed tags with values containing  the RE {@link Pattern} to identify the tags to index */
	private Map<String, Pattern> indexPatterns = new TreeMap<String, Pattern>();
	/** {@link Map} keyed on the stored tags with values containing  the RE {@link Pattern} to identify the tags values to store in the info objects */
	private Map<String, Pattern> storePatterns = new TreeMap<String, Pattern>();
	
	private final String tagTemplate = ".*<xxxTAGxxx>([a-zA-Z0-9_]+).*";
	private final String typeTag = "Type";
	
	private final Pattern patternInfoStart = Pattern.compile(".*<[a-zA-Z]+Info>.*");
	private final Pattern patternInfoEnd = Pattern.compile(".*</[a-zA-Z]+Info>\\s*");
	private final Pattern patternInfosEnd = Pattern.compile(".*</[a-zA-Z]+Infos>\\s*");
	
	public XmlInfoList(String xmlPath) {
		this.xmlPath = xmlPath;
	}
	
	public void parse() {
		this.parse(new ArrayList<String>());
	}
	
	public void parse(List<String> indexTags) {
		this.parse(indexTags, new ArrayList<String>());
	}
	
	public void parse(List<String> indexTags, List<String> storeTags) {
		this.parse(indexTags, storeTags, 4);
	}
	
	public void parse(List<String> indexTags, List<String> storeTags, int padLen) {
		
		typeMap = sorted ? new TreeMap<String, IXmlInfo>() : new LinkedHashMap<String, IXmlInfo>();
		indexMaps = sorted ? new TreeMap<String, Map<String, Set<IXmlInfo>>>() : new LinkedHashMap<String, Map<String, Set<IXmlInfo>>>();
		
		// We want to ensure that we can iterate over this list without having to check for null each time
		// so create an empty list 
		if (indexTags == null) {
			indexTags = new ArrayList<String>();
		}
		
		if (storeTags == null) {
			storeTags = new ArrayList<String>();
		}
		
		StringBuilder replace = new StringBuilder();
		for (int i = 0; i < padLen; i++) {
			replace.append(" ");
		}
		
		File xmlFile = new File(xmlPath);
		
		// First find the header lines of the xml file
		// We read up to and including the first Info start tag
		try {
			Pattern patternType = Pattern.compile(tagTemplate.replaceFirst("xxxTAGxxx", typeTag));
			for (String tag: indexTags) {
				indexPatterns.put(tag, Pattern.compile(tagTemplate.replaceFirst("xxxTAGxxx", tag)));
			}
			for (String tag: storeTags) {
				// No need to duplicate pattern matching
				if (!indexTags.contains(tag)) {
					storePatterns.put(tag, Pattern.compile(tagTemplate.replaceFirst("xxxTAGxxx", tag)));
				}
			}
			
			Matcher matcher;
			BufferedReader reader = new BufferedReader(new FileReader(xmlFile));
			String line = "";
			Boolean first = true;
			while ((line = reader.readLine()) != null) {
				if (padLen > 0) line = line.replaceAll(replace.toString(), "\t");
				if (first) {
					first = false;
					if (StringUtils.hasCharacters(comment)) {
						line = line + newline + comment;
					}
				}

				matcher = patternInfoStart.matcher(line);
				if (matcher.matches()) {
					break;
				}
				header.append(line + newline);
			}			

			// We now have the start tag of an info so create the info and store off the start tag for later use so we can add a comment later
			XmlInfo info = new XmlInfo();
			info.setStartTag(line);
			while ((line = reader.readLine()) != null) {
				if (padLen > 0) line = line.replaceAll(replace.toString(), "\t");

				// If we find the end of all the infos move onto the footer
				matcher = patternInfosEnd.matcher(line);
				if (matcher.matches()) {
					footer.append(line);
					break;
				}
				
				// If we find the info end tag then save off the info into the appropriate maps
				matcher = patternInfoEnd.matcher(line);
				if (matcher.matches()) {
					info.appendXmlLine(line);
					if (StringUtils.hasCharacters(info.getType())) {
						typeMap.put(info.getType(), info);
					}
					for (String tag: indexTags) {
						// Get the map containing the indexed tag data
						Map<String, Set<IXmlInfo>> tagMap = indexMaps.get(tag);
						if (tagMap == null) {
							tagMap = new TreeMap<String, Set<IXmlInfo>>();
							indexMaps.put(tag, tagMap);
						}
						// Get the infos associated with the index value
						String val =  StringUtils.hasCharacters(info.getTagValue(tag)) ? info.getTagValue(tag) : (StringUtils.hasCharacters(missingTagVal) ? missingTagVal : "MissingKeyVal");
						Set<IXmlInfo> infoList = tagMap.get(val);
						if (infoList == null) {
							infoList = sorted ? new TreeSet<IXmlInfo>() : new LinkedHashSet<IXmlInfo>();
							tagMap.put(val, infoList);
						}
						// Add the info keyed on its type for sorting purposes only
						infoList.add(info);
					}
					continue;
				}
				
				// If we find a new start tag then create a new info object
				matcher = patternInfoStart.matcher(line);
				if (matcher.matches()) {
					info = new XmlInfo();
					info.setStartTag(line);
					continue;
				}
				
				info.appendXmlLine(line);

				matcher = patternType.matcher(line);
				if (matcher.matches()) {
					info.setType(matcher.group(1));
					continue;
				}
				
				for (String key: indexPatterns.keySet()) {
					matcher = indexPatterns.get(key).matcher(line);
					if (matcher.matches()) {
						info.setTagValue(key, matcher.group(1));
					}
				}
				
				for (String key: storePatterns.keySet()) {
					matcher = storePatterns.get(key).matcher(line);
					if (matcher.matches()) {
						info.setTagValue(key, matcher.group(1));
					}
				}
				
			}

			while ((line = reader.readLine()) != null) {
				if (padLen > 0) line = line.replaceAll(replace.toString(), "\t");
				footer.append(newline + line);
			}
			
			reader.close();
			
		} catch (IOException e) {
			log.error("Could not access the file", e);
		}
		
	}
	
	public String toString() {
		StringBuilder sb= new StringBuilder(header);
		for (IXmlInfo info: typeMap.values()) {
			sb.append(info.getXml());
		}
		sb.append(footer);
		return sb.toString();
	}
	
	public String getHeader() {
		return header.toString();
	}
	
	public String getFooter() {
		return footer.toString();
	}
	
	public List<IXmlInfo> getTypeIndex() {
		List<IXmlInfo> infos = new ArrayList<IXmlInfo>();
		for (IXmlInfo info: typeMap.values())
		{
			infos.add(info);
		}
		return infos;
	}

	public Map<String, Set<IXmlInfo>> getIndexMap(String key) {
		return indexMaps.get(key);
	}

	/**
	 * @return the missingTagVal
	 */
	public String getMissingTagVal() {
		return missingTagVal;
	}

	/**
	 * @param missingTagVal the missingTagVal to set
	 */
	public void setMissingTagVal(String missingTagVal) {
		this.missingTagVal = missingTagVal;
	}

	/**
	 * @return the sortedList
	 */
	public boolean isSorted() {
		return sorted;
	}

	/**
	 * @param sortedList the sortedList to set
	 */
	public void setSorted(boolean sorted) {
		this.sorted = sorted;
	}
	
	public void setComment(String comment) {
		this.comment = comment;
	}
	
	private class XmlInfo implements IXmlInfo {
		
		private StringBuilder xml;
		private String startTag;
		private String type;
		private Map<String, String> tags = new TreeMap<String, String>();
		
		private XmlInfo() {
			xml = new StringBuilder();
			// We need to initialise this with a value as it is used in the comparator
			type = "";
		}
		
		private void appendXmlLine(String line) {
			xml.append(line + newline);
		}
		
		@Override
		public String getXml() {
			return getStartTag() + newline + xml.toString();
		}

		@Override
		public String getType() {
			return type;
		}

		private void setType(String type) {
			this.type = type;
		}

		@Override
		public String getStartTag() {
			return startTag;
		}

		@Override
		public void setStartTag(String startTag) {
			this.startTag = startTag;
		}

		@Override
		public String getTagValue(String tag) {
			return tags.get(tag);
		}
		
		private void setTagValue(String tag, String value) {
			tags.put(tag, value);
		}

		@Override
		public int compareTo(IXmlInfo o) {
			return type.compareTo(o.getType());
		}
	}

}
