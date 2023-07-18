package org.archid.civ4.info;

import java.util.Comparator;

import org.archid.utils.StringUtils;

public abstract class AbstractXmlFormatter implements IXmlFormatter {
	
	protected static final String newline = System.getProperty("line.separator");
	protected String groupHeader = "<!-- ************ -->\n<!-- xxxGROUPxxx -->\n<!-- ************ -->\n";
	protected String typeHeader = "<!-- xxxTYPExxx -->";

	protected String getCommentText(String value) {
		return StringUtils.titleCaseSpace(value.substring(value.indexOf('_') + 1), '_');
	}	

	/**
	 * We are using standard alpha sorting, except colon is considered below space
	 *  to allow UBs to be sorted with their parent in the case where the parent
	 *  is the same e.g. for the following buildings,
	 * Walls
	 * Walls: Celtic Dun
	 * Walls Of Troy
	 * In standard alpha sort Walls of Troy would be sorted above the Dun as space comes before
	 *  colon. This comparator considers colon to come before space so UBs will be grouped with
	 *  the parent.
	 */
	protected Comparator<String> infoCommentComparator = new Comparator<String>() {
		@Override public int compare(String s1, String s2) {
			final Integer SPACE = 32;
			final Integer COLON = 58;
			// We are looking to work with the string between the XML comment tags so extract these first
			String s1name = s1.substring(s1.indexOf("<!--") + 5, s1.length() - 4);
			String s2name = s2.substring(s2.indexOf("<!--") + 5, s2.length() - 4);
			
			// We need to loop through each char until we get to the first difference and then check
			//   if one is the colon
			for (int i = 0; i < s1name.length() && i < s2name.length(); i++) {
				if (s1name.charAt(i) == SPACE && s2name.charAt(i) == COLON) {
					return 1;
				} else if (s2name.charAt(i) == SPACE && s1name.charAt(i) == COLON) {
					return -1;
				} else if (s1name.charAt(i) != s2name.charAt(i)) {
					return s1name.charAt(i) - s2name.charAt(i);
				}
			}
			return s1name.length() - s2name.length();
		}
	};

}
