package org.archid.civ4.info.era;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.apache.log4j.Logger;
import org.archid.civ4.info.AbstractXmlFormatter;
import org.archid.civ4.info.IXmlInfo;
import org.archid.civ4.info.XmlInfoList;

public class EraInfoXmlFormatter extends AbstractXmlFormatter {

	/** Logging facility */
	static Logger log = Logger.getLogger(EraInfoXmlFormatter.class.getName());
	
	private static final String TAG_COST = "iAdvancedStartPoints";

	private final List<String> stores = new  ArrayList<String>(Arrays.asList(TAG_COST));

	private StringBuilder out = null;
	private int totalInfoCount = 0;
	
	@Override
	public void format(String path) {
		XmlInfoList infoList = new XmlInfoList(path);
		infoList.parse(null, stores);

		out = new StringBuilder(infoList.getHeader());
		// We want to sort by the Advanced Start Cost
		Map<Integer, IXmlInfo> sortedInfos = new TreeMap<Integer, IXmlInfo>();
		for (IXmlInfo info: infoList.getTypeIndex()) {
			info.setStartTag(info.getStartTag() + ' ' + typeHeader.replaceAll("xxxTYPExxx", getCommentText(info.getType())));
			Integer cost = Integer.parseInt(info.getTagValue(TAG_COST));
			sortedInfos.put(cost, info);
		}
		for (IXmlInfo info: sortedInfos.values()) {
			out.append(info.getXml());
			totalInfoCount++;
		}
		
		log.info("Wrote " + totalInfoCount + " total eras");
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
