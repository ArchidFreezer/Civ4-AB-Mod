package org.archid.civ4.info;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;
import org.archid.utils.FileUtils;
import org.archid.utils.IPropertyHandler;
import org.archid.utils.PropertyHandler;
import org.archid.utils.PropertyKeys;
import org.archid.utils.StringUtils;

public abstract class AbstractConverter implements IConverter {
	
	/** Logging facility */
	static Logger log = Logger.getLogger(AbstractConverter.class.getName());

	protected static final String newline = System.getProperty("line.separator");
	
	protected IPropertyHandler props = PropertyHandler.getInstance();

	private String tagEndRegex = "\\s*?</xxxTAGxxx>.*";
	private Pattern tagSinglePattern = Pattern.compile("(\\s*?)<([^>]+)>([^<]*?)</\\2>.*");
	private Pattern tagStartPattern = Pattern.compile("\\s*?<([^>]+)>.*");
	private Pattern tagEmptyPattern = Pattern.compile("\\s*?<([a-zA-Z0-9]+)/>.*");

	private String typeHeader = " <!-- xxxTYPExxx -->";
	
	protected IXmlFormatter formatter;

	/* (non-Javadoc)
	 * @see org.archid.civ4.info.IConverter#convert(java.lang.String)
	 */
	@Override
	public void convert() {
		String path = props.getAppProperty(PropertyKeys.PROPERTY_KEY_FILE_INFOS);
		XmlInfoList infoList = new XmlInfoList(path);
		infoList.parse();
		
		StringBuilder out = new StringBuilder(infoList.getHeader());
		List<IXmlInfo> infoXmls = infoList.getTypeIndex();
		for (IXmlInfo infoXml: infoXmls) {
			IXmlTaggedInfo info = tagInfo(infoXml);
			info.setStartTag(buildStartTag(info));
			info = updateCustomTags(info);
			out.append(info.getXml() + newline);
		}
		out.append(infoList.getFooter());

		try {
			FileUtils.backupFile(path);
			BufferedWriter writer = new BufferedWriter(new FileWriter(new File(path)));
			writer.write(out.toString());
			writer.close();
			if (formatter != null) formatter.format(path);
			
		} catch (IOException e) {
			log.error("Could not access the file", e);
		}
}

	private IXmlTaggedInfo tagInfo(IXmlInfo infoXml) {
		IXmlTaggedInfo info = new XmlTaggedInfo();
		String[] arrLines = infoXml.getXml().split(newline);
		info.setStartTag(arrLines[0]);
		info.setEndTag(arrLines[arrLines.length - 1]);
		List<String> lines = Arrays.asList(arrLines).subList(1, arrLines.length - 1);
		XmlTag tag = new XmlTag();
		for (String line: lines) {
			parseLine(line, tag);
			if (tag.isComplete()) {
				info.setTagXml(tag.getTagName(), tag.toString());
				tag = new XmlTag();
			}
		}
		return info;
	}

	private void parseLine(String line, XmlTag tag) {
	
		if (tag.getTagName() == null) {
			Matcher mEmpty = tagEmptyPattern.matcher(line);
			Matcher mSingle = tagSinglePattern.matcher(line);
			Matcher mStart = tagStartPattern.matcher(line);
			if (mEmpty.matches()) {
				// This is an empty tag so we can close it out now
				tag.setTagName(mEmpty.group(1));
				tag.setTagStart(line);
				tag.setComplete(true);
			} else if (mSingle.matches()) {
				tag.setTagName(mSingle.group(2));
				tag.setTagStart(line);
				tag.setComplete(true);
			} else if (mStart.matches()) {
				tag.setTagName(mStart.group(1));
				tag.setTagStart(line);
			} else {
				log.warn("Unexpected first line of tag, ignoring: " + line);
			}
		} else {
			Pattern tagEndPattern = Pattern.compile(tagEndRegex.replaceAll("xxxTAGxxx", tag.getTagName()));
			Matcher mEnd = tagEndPattern.matcher(line);
			if (mEnd.matches()) {
				tag.setTagEnd(line);
				tag.setComplete(true);
			} else {
				tag.appendContent(line);
			}
		}
	
	}

	private String buildStartTag(IXmlTaggedInfo info) {
		return info.getStartTag() + typeHeader.replaceAll("xxxTYPExxx", getCommentText(info.getType()));
	}

	private String getCommentText(String value) {
		return StringUtils.titleCaseSpace(value.substring(value.indexOf('_') + 1), '_');
	}	
	
	protected abstract IXmlTaggedInfo updateCustomTags(IXmlTaggedInfo info);
	
	protected IXmlTaggedInfo getXmlTaggedInfo() {
		return new XmlTaggedInfo();
	}
	
	private class XmlTaggedInfo implements IXmlTaggedInfo {
		
		private String prefix = "\\t\\t\\t";
		private String endTag = null;
		private String startTag = null;
		private String type = "";
		private Map<String, String> tags = new LinkedHashMap<String, String>();		

		@Override
		public String getXml() {
			StringBuilder sb = new StringBuilder(startTag);
			for (String key: tags.keySet()) {
				sb.append(newline + tags.get(key));
			}
			sb.append(newline + endTag);
			return sb.toString();
		}

		@Override
		public String getType() {
			return type;
		}

		@Override
		public String getTagXml(String tag) {
			return tags.get(tag);
		}

		@Override
		public boolean setTagXml(String tag, String xml) {
			if (tag.equals("Type")) {
				Matcher m = tagSinglePattern.matcher(xml);
				if (m.find()) {
					prefix = m.group(1);
					type = m.group(3);
				}
			}
			return (tags.put(tag, xml) == null);
		}

		@Override
		public String getStartTag() {
			return startTag;
		}

		@Override
		public void setStartTag(String val) {
			startTag = val;
		}
		
		@Override
		public void setEndTag(String val) {
			endTag = val;
		}

		@Override
		public String getPrefix() {
			return prefix;
		}

		@Override
		public String getEndTag() {
			return endTag;
		}
		
	}
	
	private class XmlTag {
		private String tagEnd = "";
		private String tagName = null;
		private String tagStart = "";
		private StringBuilder contents = new StringBuilder();
		private boolean complete = false;
		
		public void setTagName(String name) {
			tagName = name;
		}
		
		public String getTagName() {
			return tagName;
		}
		
		public void setComplete(boolean complete) {
			this.complete = complete;
		}
		
		public boolean isComplete() {
			return complete;
		}
		
		public void appendContent(String content) {
			contents.append(newline + content);
		}
		
		public String getContent() {
			return tagStart + contents.toString() + tagEnd;
		}
		
		public String toString() {
			return getContent();
		}

		/**
		 * @param tagEnd the tagEnd to set
		 */
		public void setTagEnd(String tagEnd) {
			this.tagEnd = newline + tagEnd;
		}

		/**
		 * @param tagStart the tagStart to set
		 */
		public void setTagStart(String tagStart) {
			this.tagStart = tagStart;
		}
		
	}

	/**
	 * Takes an xml tag definition and returns the it with one of its tags removed. The tag is expected to
	 * be a multi-line tag, thought this is not required, and any line that contains {@code tagToRemove} will
	 * not be included in the output.
	 * <p>
	 * This is used in the common OOB pattern where a boolean tag is supposed to indicate whether a value should
	 * be used or not, e.g.
	 * <pre> {@code
	 * <Wrappers>
	 *   <Wrapper>
	 *     <SomeType></SomeType>
	 *     <bEnabled></bEnabled>
	 *   </Wrapper>
	 * </Wrappers>
	 *  } </pre>
	 * The use of this function with {@code bEnabled} as the value of {@code tagToRemove} would leave {@code SoType}
	 * as the only child tag.
	 *  
	 * @param tagXml tag definition
	 * @param tagToRemove tag to remove form the definition
	 * @return tagXml without the removed tag
	 */
	protected String removeTag(String tagXml, String tagToRemove) {
		StringBuilder sb = new StringBuilder();
		String[] arr = tagXml.split(newline);
		boolean first = true;
		for (String line: arr) {
			if (line.contains(tagToRemove)) continue;
			if (first) {
				sb.append(line);
				first = false;
			} else {
				sb.append(newline + line);
			}
		}
		return sb.toString();
	}

	/**
	 * Takes an xml tag definition of a single line tag and returns the value of the tag without the tag. The start and end tags
	 * will not be included in the output. Empty tags using the single tag syntax is handled. If the tag does not appear to be 
	 * in the correct format {@code null} will be returned. The empty string will be returned if the xml is well formed, but the tag
	 * contains no value.
	 * 
	 * @param tagXml line containing a single line xml tag
	 * @return value of the tag; otherwise {@code null} if the tag is not well formed
	 */
	protected String getSingleTagValue(String tagXml) {
		String val = null;
		
		if (StringUtils.hasCharacters(tagXml)) {
			Matcher mEmpty = tagEmptyPattern.matcher(tagXml);
			Matcher mSingle = tagSinglePattern.matcher(tagXml);
			if (mEmpty.matches()) {
				val = "";
			} else if (mSingle.matches()) {
				val = mSingle.group(3);
			} else {
				log.warn("Unexpected single tag, ignoring: " + tagXml);
			}
		}
		
		return val;
	}
	
	/**
	 * Takes an xml tag definition consisting of a set of single values and returns a {@link Set} ordered in the
	 * same way as the values in the definition. If there are no values then an empty set is returned.
	 * 
	 * @param tagXml {@code String} containing the xml definition
	 * @return {@link Set} containing the tag values  
	 */
	protected Set<String> getMultiSingleValues(String tagXml) {
		Set<String> vals = new LinkedHashSet<String>();
		if (!StringUtils.hasCharacters(tagXml)) return vals;

		String[] arr = tagXml.split(newline);
		
		// We know we can ignore the first and last lines
		String val = null;
		int x = 1;
		while(x < arr.length - 1) {
			val = getSingleTagValue(arr[x++]);
			if (StringUtils.hasCharacters(val)) vals.add(val);
		}
		
		return vals;
	}

	/**
	 * Takes an array of tag names and copies the tag values from one {@link IXmlTaggedInfo} object to another
	 * if the tag exists. If the tag does not exist then it is ignored
	 * 
	 * @param source object containing the set of tags to copy from
	 * @param target object where the tags will be copied to if they exist
	 * @param arr {@code String[]} containing the names of the tags to copy
	 */
	protected void copyTags(IXmlTaggedInfo source, IXmlTaggedInfo target, String[] arr) {
		Set<String> tags = new LinkedHashSet<String>(Arrays.asList(arr));
		String line;
		for(String tag: tags) {
			line = source.getTagXml(tag);
			if (StringUtils.hasCharacters(line)) target.setTagXml(tag, line);
		}
	}

	protected String buildTag(String tag, String val) {
		return getPrefix() + "<" + tag + ">" + val + "</" + tag + ">";
	}
	
	protected String buildInnerTag(int nestLevel, String tag, String val) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < nestLevel; i++) {
			sb.append("\t");
		}
		return sb.toString() + buildTag(tag, val);
	}
	
	protected String buildMultiSingleTag(String outerTag, String innerTag, Set<String> vals) {
		if (vals.isEmpty() || (vals.size() == 1 && vals.contains("NONE")))
			return getPrefix() + "<" + outerTag + "/>";
		
		StringBuilder sb = new StringBuilder(getPrefix() + "<" + outerTag + ">");
		for (String val: vals) {
			if (!val.equalsIgnoreCase("NONE")) sb.append(newline + buildInnerTag(1, innerTag, val));
		}
		sb.append(newline + getPrefix() + "</" + outerTag + ">");
		return sb.toString();
	}
	
	protected String getPrefix() {
		return "\\t\\t\\t";
	}
	
}

