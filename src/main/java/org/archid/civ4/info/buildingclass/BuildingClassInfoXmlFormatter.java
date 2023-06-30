package org.archid.civ4.info.buildingclass;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import org.apache.log4j.Logger;
import org.archid.civ4.info.AbstractXmlFormatter;
import org.archid.civ4.info.IXmlInfo;
import org.archid.civ4.info.XmlInfoList;

public class BuildingClassInfoXmlFormatter extends AbstractXmlFormatter {

	/** Logging facility */
	static Logger log = Logger.getLogger(BuildingClassInfoXmlFormatter.class.getName());
	
	private static final String MISSING_TAG_VAL = "xxxMissingxxx";
	private static final String MISSING_CATEGORY_LABEL = "xxxLabelxxx";
	

	private static final String GROUP_CATEGORY_TAG = "iCategory";
	private static final String GROUP_STANDARD_LABEL = "General";
	
	private final Integer subCategoryPadLength = 65;

	private final List<String> groups = new ArrayList<String>(Arrays.asList(GROUP_CATEGORY_TAG));
	
	private Map<Integer, String> labels = new HashMap<Integer, String>();
	
	private StringBuilder out;
	private StringBuilder comment = new StringBuilder();
	
	public BuildingClassInfoXmlFormatter() {
		labels.put(2, "World Wonders");
		labels.put(3, "National Wonders");
		labels.put(4, "Natural Wonders");
		labels.put(5, "Religious");
		labels.put(6, "Special Construction");
		labels.put(10, "Capital");
		labels.put(11, "City Managemnt");
		labels.put(12, "City Walls");
		labels.put(13, "Castle");
		labels.put(14, "City Defence");
		labels.put(15, "Security");
		labels.put(16, "Food");
		labels.put(17, "Military Training");
		labels.put(18, "Military Production");
		labels.put(19, "Seafood");
		labels.put(20, "Water Trade");
		labels.put(21, "Taxes");
		labels.put(22, "City Boost");
		labels.put(23, "City Maintenance");
		labels.put(24, "Production");
		labels.put(25, "Production - Resource");
		labels.put(26, "Production - Factory/Power");
		labels.put(27, "Production - Efficiency");
		labels.put(28, "Commerce");
		labels.put(29, "Commerce - Retail");
		labels.put(30, "Commerce - Banking");
		labels.put(31, "Culture");
		labels.put(32, "Entertainment");
		labels.put(33, "Research");
		labels.put(34, "Research - Technology");
		labels.put(35, "Education");
		labels.put(36, "Resources");
		labels.put(37, "Health");
		labels.put(38, "Water");
		labels.put(39, "Corpse Disposal");
		labels.put(40, "Trade Infrastructure");
		labels.put(41, "Communications");
		labels.put(42, "Siege Weapons");
		labels.put(43, "Sports");
	}
	
	@Override
	public void format(String path) {
		XmlInfoList infoList = new XmlInfoList(path);
		infoList.setMissingTagVal(MISSING_TAG_VAL);
		infoList.setComment(comment.toString());
		infoList.parse(groups);

		// First output the file header
		out = new StringBuilder(infoList.getHeader());
		
		// Record the total number of buildings processed
		int totalInfoCount = 0;
		
		/* 
		 * The infos will be sorted into 6 groups by their category value
		 * - (10+) General, will be further split into subcategories
		 * - (2) World Wonders
		 * - (3) National Wonders
		 * - (5) Religious
		 * - (6) Special Construction
		 * - (4) Natural Wonders
		 */ 
		Map<Integer, Set<IXmlInfo>> general = new TreeMap<Integer, Set<IXmlInfo>>();
		Map<String, IXmlInfo> worldWonders = new TreeMap<String, IXmlInfo>();
		Map<String, IXmlInfo> nationalWonders = new TreeMap<String, IXmlInfo>();
		Map<String, IXmlInfo> naturalWonders = new TreeMap<String, IXmlInfo>();
		Map<String, IXmlInfo> religious = new TreeMap<String, IXmlInfo>();
		Map<String, IXmlInfo> special = new TreeMap<String, IXmlInfo>();
		Map<String, Set<IXmlInfo>> groups = infoList.getIndexMap(GROUP_CATEGORY_TAG);
		
		for (String category: groups.keySet()) {
			Integer catVal = Integer.valueOf(category);
			// Deal with any missing category labels first
			if (!labels.containsKey(catVal))
				labels.put(catVal, MISSING_CATEGORY_LABEL);
			
			if (catVal == 2) {
				for (IXmlInfo xml: groups.get(category)) {
					worldWonders.put(xml.getType(), xml);
				}
			} else if (catVal == 3) {
				for (IXmlInfo xml: groups.get(category)) {
					nationalWonders.put(xml.getType(), xml);
				}
			} else if (catVal == 4) {
				for (IXmlInfo xml: groups.get(category)) {
					naturalWonders.put(xml.getType(), xml);
				}
			} else if (catVal == 5) {
				for (IXmlInfo xml: groups.get(category)) {
					religious.put(xml.getType(), xml);
				}
			} else if (catVal == 6) {
				for (IXmlInfo xml: groups.get(category)) {
					special.put(xml.getType(), xml);
				}
			} else {
				if (!general.containsKey(catVal)) {
					general.put(catVal, new HashSet<IXmlInfo>());
				}
				for (IXmlInfo xml: groups.get(category)) {
					general.get(catVal).add(xml);
				}
			}
		}
		
		// Used for sorting the infos by their building class name
		Map<String, IXmlInfo> sortedGroup =  null;
		
		/*
		 * General
		 */
		// Add the group header
		out.append(groupHeader.replaceAll("xxxGROUPxxx",	GROUP_STANDARD_LABEL));
		
		// Loop through the sub categories
		int groupCount = 0;
		for (Integer subCategory: general.keySet()) {
			// First sort the category infos
			sortedGroup =  new TreeMap<String, IXmlInfo>();
			for (IXmlInfo info: general.get(subCategory)) {
				sortedGroup.put(info.getType(), info);
			}
			// Now we have the sorted list we can add them to the output
			groupCount = 0;
			boolean first = true;
			for (IXmlInfo info: sortedGroup.values()) {
				String type = getCommentText(info.getType());
				StringBuilder startTag = new StringBuilder();
				if (first) {
					startTag.append(newline + info.getStartTag() + ' ' + typeHeader.replaceAll("xxxTYPExxx", type));
					startTag.append(getSubCategoryPad(startTag.length()));
					startTag.append(typeHeader.replaceAll("xxxTYPExxx", labels.get(subCategory)));
					first = false;
				} else {
					startTag.append(info.getStartTag() + ' ' + typeHeader.replaceAll("xxxTYPExxx", type));
					
				}
				info.setStartTag(startTag.toString());
				out.append(info.getXml());
				groupCount++;
			}
			totalInfoCount += groupCount;
			log.info("Processed " + groupCount + " " + labels.get(subCategory) + " building classes");
		}
		
		/*
		 * World Wonders
		 */
		// Output the group header
		out.append(newline + newline + groupHeader.replaceAll("xxxGROUPxxx", labels.get(2)));
		// Loop through the infos
		groupCount = 0;
		for (IXmlInfo info: worldWonders.values()) {
			info.setStartTag(info.getStartTag() + ' ' + typeHeader.replaceAll("xxxTYPExxx", getCommentText(info.getType())));
			out.append(info.getXml());
			groupCount++;
		}
		totalInfoCount += groupCount;
		log.info("Processed " + groupCount + " " + labels.get(2) + " building classes");

		/*
		 * National Wonders
		 */
		// Output the group header
		out.append(newline + newline + groupHeader.replaceAll("xxxGROUPxxx", labels.get(3)));
		// Loop through the infos
		groupCount = 0;
		for (IXmlInfo info: nationalWonders.values()) {
			info.setStartTag(info.getStartTag() + ' ' + typeHeader.replaceAll("xxxTYPExxx", getCommentText(info.getType())));
			out.append(info.getXml());
			groupCount++;
		}
		totalInfoCount += groupCount;
		log.info("Processed " + groupCount + " " + labels.get(3) + " building classes");

		/*
		 * Religious
		 */
		// Output the group header
		out.append(newline + newline + groupHeader.replaceAll("xxxGROUPxxx", labels.get(5)));
		// Loop through the infos
		groupCount = 0;
		for (IXmlInfo info: religious.values()) {
			info.setStartTag(info.getStartTag() + ' ' + typeHeader.replaceAll("xxxTYPExxx", getCommentText(info.getType())));
			out.append(info.getXml());
			groupCount++;
		}
		totalInfoCount += groupCount;
		log.info("Processed " + groupCount + " " + labels.get(5) + " building classes");

		/*
		 * Special Construction
		 */
		// Output the group header
		out.append(newline + newline + groupHeader.replaceAll("xxxGROUPxxx", labels.get(6)));
		// Loop through the infos
		groupCount = 0;
		for (IXmlInfo info: special.values()) {
			info.setStartTag(info.getStartTag() + ' ' + typeHeader.replaceAll("xxxTYPExxx", getCommentText(info.getType())));
			out.append(info.getXml());
			groupCount++;
		}
		totalInfoCount += groupCount;
		log.info("Processed " + groupCount + " " + labels.get(6) + " building classes");

		/*
		 * Natural Wonders
		 */
		// Output the group header
		out.append(newline + newline + groupHeader.replaceAll("xxxGROUPxxx", labels.get(4)));
		// Loop through the infos
		groupCount = 0;
		for (IXmlInfo info: naturalWonders.values()) {
			info.setStartTag(info.getStartTag() + ' ' + typeHeader.replaceAll("xxxTYPExxx", getCommentText(info.getType())));
			out.append(info.getXml());
			groupCount++;
		}
		totalInfoCount += groupCount;
		log.info("Processed " + groupCount + " " + labels.get(4) + " building classes");
		log.info("Wrote " + totalInfoCount + " total building classes");

		// Output the file footer
		out.append(infoList.getFooter());

		File xmlFile = new File(path);
		try {
			
			BufferedWriter writer = new BufferedWriter(new FileWriter(xmlFile));
			writer.write(out.toString());
			writer.close();
			
		} catch (IOException e) {
			log.error("Could not access the file", e);
		}
	}

	private String getSubCategoryPad(Integer commentLength) {
		StringBuilder pad = new StringBuilder();
		Integer padRequired = subCategoryPadLength - commentLength;
		while (padRequired > 0) {
			pad.append(' ');
			padRequired -= 1;
		}
		return pad.toString();
	}
}
