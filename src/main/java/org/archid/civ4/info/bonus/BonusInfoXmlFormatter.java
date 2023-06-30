package org.archid.civ4.info.bonus;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;
import org.archid.civ4.info.AbstractXmlFormatter;
import org.archid.civ4.info.IXmlInfo;
import org.archid.civ4.info.XmlInfoList;
import org.archid.civ4.info.building.BuildingInfoXmlFormatter;

public class BonusInfoXmlFormatter extends AbstractXmlFormatter {

	/** Logging facility */
	static Logger log = Logger.getLogger(BuildingInfoXmlFormatter.class.getName());
	
	private static final String MISSING_TAG_VAL = "No Class";
	
	private static final String GROUP_BONUS_CLASS_TYPE_TAG = "BonusClassType";
	
	private final List<String> groups = new ArrayList<String>(Arrays.asList(GROUP_BONUS_CLASS_TYPE_TAG));

	private StringBuilder out;
	
	@Override
	public void format(String path) {
		XmlInfoList infoList = new XmlInfoList(path);
		infoList.setMissingTagVal(MISSING_TAG_VAL);
		infoList.parse(groups);

		// First output the file header
		out = new StringBuilder(infoList.getHeader());
		
		// Record the total number of bonuses processed
		int totalInfoCount = 0;
		
		Map<String, Set<IXmlInfo>> classBonuses = infoList.getIndexMap(GROUP_BONUS_CLASS_TYPE_TAG);
		for (String group: classBonuses.keySet()) {
			// Output the group comment
			out.append(newline + newline + groupHeader.replaceAll("xxxGROUPxxx",	getCommentText(group)));
			for (IXmlInfo info: classBonuses.get(group)) {
				info.setStartTag(info.getStartTag() + ' ' + typeHeader.replaceAll("xxxTYPExxx", getCommentText(info.getType())));
				out.append(info.getXml());
				totalInfoCount++;
			}
		}
		log.info("Wrote " + totalInfoCount + " total bonus");
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
