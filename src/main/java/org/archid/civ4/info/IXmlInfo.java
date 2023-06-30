package org.archid.civ4.info;

public interface IXmlInfo extends Comparable<IXmlInfo>{

	/**
	 * Gets the complete xml text representation of the info including the start and end tags
	 * 
	 * @return text string containing the xml
	 */
	String getXml();
	
	/**
	 * Each info has a type defined as the primary key which should be unique. When the XML file is read by the civ dll
	 * the info maps use this as their key. This is used when sorting infos.
	 *  
	 * @return value of the {@code <Type>} tag
	 */
	String getType();

	/**
	 * Gets the value of any single line tag that was stored when the xml was parsed. If the tag was not stored then {@code null} will be returned.
	 *  
	 * @param tag name of the xml tag
	 * @return value of the xml tag if it was stored; otherwise {@code null}
	 */
	String getTagValue(String tag);

	/**
	 * Gets the full start tag line, this can be used in conjunction with {@link #setStartTag(String)} to append a comment to the start tag if required
	 * 
	 * @return text of the info start tag
	 * @see #setStartTag(String)
	 */
	String getStartTag();
	
	/**
	 * Sets the value that will be used as the start tag when retrieving the complete xml entry for the info
	 * 
	 * @param val line used as the start tag
	 * @see #getXml()
	 */
	void setStartTag(String val);

}