/**
 * 
 */
package org.archid.civ4.info;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import org.apache.log4j.Logger;

/**
 * @author Jim
 *
 */
public class DefaultXmlFormatter extends AbstractXmlFormatter {

	/** Logging facility */
	static Logger log = Logger.getLogger(DefaultXmlFormatter.class.getName());
	
	private String label;
	private boolean sorted;
	
	public DefaultXmlFormatter(String label) {
		this(label, true);
	}

	public DefaultXmlFormatter(String label, boolean sorted) {
		this.sorted = sorted;
		this.label = label;
	}

	/**
	 * The default implementation will replace the standard 4 leading spaces with tabs and will also
	 * take the value of the {@code <Type>} tag and append it as a comment to the info start tag.
	 *  
	 * @param path file path of the Xml file to process
	 */
	@Override
	public void format(String path) {
		XmlInfoList infoList = new XmlInfoList(path);
		infoList.setSorted(sorted);
		infoList.parse();
		List<IXmlInfo> infos = infoList.getTypeIndex();
		int infoCount = 0;
		for (IXmlInfo info: infos) {
			info.setStartTag(buildStarttag(info));
			infoCount++;
		}

		File xmlFile = new File(path);
		try {
			
			BufferedWriter writer = new BufferedWriter(new FileWriter(xmlFile));
			writer.write(infoList.toString());
			log.info("Wrote " + infoCount + " " + label + " infos");
			writer.close();
			
		} catch (IOException e) {
			log.error("Could not access the file", e);
		}
	}

	private String buildStarttag(IXmlInfo info) {
		return info.getStartTag() +  " " + typeHeader.replaceAll("xxxTYPExxx", getCommentText(info.getType()));
	}

}
