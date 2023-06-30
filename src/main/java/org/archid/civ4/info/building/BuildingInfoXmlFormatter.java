package org.archid.civ4.info.building;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import org.apache.log4j.Logger;
import org.archid.civ4.info.AbstractXmlFormatter;
import org.archid.civ4.info.IXmlInfo;
import org.archid.civ4.info.XmlInfoList;

public class BuildingInfoXmlFormatter extends AbstractXmlFormatter {

	/** Logging facility */
	static Logger log = Logger.getLogger(BuildingInfoXmlFormatter.class.getName());
	
	private static final String MISSING_TAG_VAL = "xxxMissingxxx";
	
	private static final String GROUP_AUTO_BUILD_LABEL = "Features";
	private static final String GROUP_AUTO_BUILD_TAG = "bAutoBuild";
	private static final String GROUP_NO_CONSTRUCT_LABEL = "No Construct";
	private static final String GROUP_NO_CONSTRUCT_TAG = "iCost";
	private static final String GROUP_STANDARD_LABEL = "Standard";

	private static final String TAG_BUILDING_CLASS = "BuildingClass";
	private final List<String> groups = new ArrayList<String>(Arrays.asList(GROUP_AUTO_BUILD_TAG, GROUP_NO_CONSTRUCT_TAG));
	private final List<String> stores = new ArrayList<String>(Arrays.asList(TAG_BUILDING_CLASS));

	private StringBuilder out;
	
	@Override
	public void format(String path) {
		XmlInfoList infoList = new XmlInfoList(path);
		infoList.setMissingTagVal(MISSING_TAG_VAL);
		infoList.parse(groups, stores);

		// First output the file header
		out = new StringBuilder(infoList.getHeader());
		
		// Record the total number of buildings processed
		int totalInfoCount = 0;
		
		/* We are going to have 3 groups:
		 *  Features - bAutoBuild = true
		 *  No Construct - iCost = -1 and not in Features
		 *  Standard - anything not in Features or No Construct
		 *  
		 * We are going to sort the infos into their respective group at the start as
		 * the standard buildings are displayed first and they require the Feature
		 * and Auto Build buildings definitions as a prereq 
		 */ 
		Map<String, IXmlInfo> features = new TreeMap<String, IXmlInfo>();
		Map<String, Set<IXmlInfo>> autoBuildGroup = infoList.getIndexMap(GROUP_AUTO_BUILD_TAG);
		if (autoBuildGroup.containsKey("1")) {
			for (IXmlInfo xml: autoBuildGroup.get("1")) {
				features.put(xml.getType(), xml);
			}
		}
		
		Map<String, IXmlInfo> noConstruct = new TreeMap<String, IXmlInfo>();
		Map<String, Set<IXmlInfo>> costGroup = infoList.getIndexMap(GROUP_NO_CONSTRUCT_TAG);
		// Because we are looking for the default value we need to check the cases where the tag value matches and where there is no tag  
		if (costGroup.get(MISSING_TAG_VAL) != null) {
			for (IXmlInfo xml: costGroup.get(MISSING_TAG_VAL)) {
				if (!features.containsKey(xml.getType()))
					noConstruct.put(xml.getType(), xml);
			}
		}
		if (costGroup.get("-1") != null) {
			for (IXmlInfo xml: costGroup.get("-1")) {
				if (!features.containsKey(xml.getType()))
					noConstruct.put(xml.getType(), xml);
			}
		}
		
		Map<String, IXmlInfo> standard = new TreeMap<String, IXmlInfo>();
		List<IXmlInfo> types = infoList.getTypeIndex();
		for (IXmlInfo xml: types) {
			if (!features.containsKey(xml.getType()) && !noConstruct.containsKey(xml.getType()))
				standard.put(xml.getType(), xml);
		}
		
		/*
		 * Standard
		 */
		// Output the domain header comment
		out.append(groupHeader.replaceAll("xxxGROUPxxx",	GROUP_STANDARD_LABEL));
		
		// Now loop through the buildings, in most cases there will only be a single instance, but there
		// will be multiples where there are UBs for the class that need to be sorted
		// The map is maintained in the following keys: Building Comment / Building
		Map<String, IXmlInfo> sortedGroup =  new TreeMap<String, IXmlInfo>();
		for (IXmlInfo info: standard.values()) {
			// If the type matches the class then we will use the type only
			String unitType = getCommentText(info.getType());
			String unitClass = getCommentText(info.getTagValue(TAG_BUILDING_CLASS));
			if (!unitType.equals(unitClass)) {
				unitType = unitClass + ": " + unitType;
			}
			info.setStartTag(info.getStartTag() + ' ' + typeHeader.replaceAll("xxxTYPExxx", unitType));
			sortedGroup.put(info.getStartTag(), info);
		}
		int groupCount = 0;
		for (IXmlInfo info: sortedGroup.values()) {
			out.append(info.getXml());
			groupCount++;
		}
		totalInfoCount += groupCount;
		log.info("Processed " + groupCount + " " + GROUP_STANDARD_LABEL + " buildings");
		
		/*
		 * Features
		 * 
		 * In order for unique features to have an impact on cities a building is automatically
		 * created in the city when it has the feature in a workable plot
		 */
		// Output the domain header comment
		out.append(newline + newline + groupHeader.replaceAll("xxxGROUPxxx",	GROUP_AUTO_BUILD_LABEL));
		
		// There shouldn't be any equivalent of UBs, but we may as well process the infos as if there were
		sortedGroup =  new TreeMap<String, IXmlInfo>();
		for (IXmlInfo info: features.values()) {
			// If the type matches the class then we will use the type only
			String unitType = getCommentText(info.getType());
			String unitClass = getCommentText(info.getTagValue(TAG_BUILDING_CLASS));
			if (!unitType.equals(unitClass)) {
				unitType = unitClass + ": " + unitType;
			}
			info.setStartTag(info.getStartTag() + ' ' + typeHeader.replaceAll("xxxTYPExxx", unitType));
			sortedGroup.put(info.getStartTag(), info);
		}
		groupCount = 0;
		for (IXmlInfo info: sortedGroup.values()) {
			out.append(info.getXml());
			groupCount++;
		}
		totalInfoCount += groupCount;
		log.info("Processed " + groupCount + " " + GROUP_AUTO_BUILD_LABEL + " buildings");

		/*
		 * No construct
		 * 
		 * These are buildings that cannot be built by the player and are not features
		 */
		// Output the domain header comment
		out.append(newline + newline + groupHeader.replaceAll("xxxGROUPxxx",	GROUP_NO_CONSTRUCT_LABEL));
		
		// There shouldn't be any equivalent of UBs, but we may as well process the infos as if there were
		sortedGroup =  new TreeMap<String, IXmlInfo>();
		for (IXmlInfo info: noConstruct.values()) {
			// If the type matches the class then we will use the type only
			String unitType = getCommentText(info.getType());
			String unitClass = getCommentText(info.getTagValue(TAG_BUILDING_CLASS));
			if (!unitType.equals(unitClass)) {
				unitType = unitClass + ": " + unitType;
			}
			info.setStartTag(info.getStartTag() + ' ' + typeHeader.replaceAll("xxxTYPExxx", unitType));
			sortedGroup.put(info.getStartTag(), info);
		}
		groupCount = 0;
		for (IXmlInfo info: sortedGroup.values()) {
			out.append(info.getXml());
			groupCount++;
		}
		totalInfoCount += groupCount;
		log.info("Processed " + groupCount + " " + GROUP_NO_CONSTRUCT_LABEL + " buildings");
		log.info("Wrote " + totalInfoCount + " total buildings");
		
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

}
