package org.archid.civ4.info.civic;

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

public class CivicInfoXmlFormatter extends AbstractXmlFormatter {

	/** Logging facility */
	static Logger log = Logger.getLogger(CivicInfoXmlFormatter.class.getName());
	
	private static final String TAG_OPTION_TYPE = "CivicOptionType";
	private static final String TAG_INDEX = "iIndex";
	private final List<String> groups = new  ArrayList<String>(Arrays.asList(TAG_OPTION_TYPE));
	private final List<String> stores = new  ArrayList<String>(Arrays.asList(TAG_INDEX));

	private StringBuilder out = null;
	private int totalInfoCount = 0;
	
	@Override
	public void format(String path) {
		
		XmlInfoList infoList = new XmlInfoList(path);
		infoList.parse(groups, stores);

		out = new StringBuilder(infoList.getHeader());
		// The civics are processed by CivicOptionType first
		Map<String, Set<IXmlInfo>> optionMap = infoList.getIndexMap(TAG_OPTION_TYPE);
		
		// Loop through the civic option types
		for (String civicOptionType: optionMap.keySet()) {
			out.append(groupHeader.replaceAll("xxxGROUPxxx",	getCommentText(civicOptionType)));
			// Now sort the civics in this civic option group
			Map<Integer, IXmlInfo> sortedInfos = new TreeMap<Integer, IXmlInfo>();
			for (IXmlInfo info: optionMap.get(civicOptionType)) {
				info.setStartTag(info.getStartTag() + ' ' + typeHeader.replaceAll("xxxTYPExxx", getCommentText(info.getType())));
				Integer index = Integer.parseInt(info.getTagValue(TAG_INDEX));
				sortedInfos.put(index, info);
			}
			int groupCount = 0;
			for (IXmlInfo info: sortedInfos.values()) {
				out.append(info.getXml());
				groupCount++;
			}
			totalInfoCount += groupCount;
			log.info("Processed " + groupCount + " " + civicOptionType + " civics");		
		}
		log.info("Wrote " + totalInfoCount + " total civics");
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
