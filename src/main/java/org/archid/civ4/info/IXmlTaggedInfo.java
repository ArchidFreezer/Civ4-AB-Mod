package org.archid.civ4.info;

public interface IXmlTaggedInfo {
	
	/**
	 * Gets the complete xml text representation of the info including the start and end tags
	 * 
	 * @return text string containing the xml
	 */
	String getXml();
	
	/**
	 * Each info has a type defined as the primary key which should be unique. When the XML file is read by the civ dll
	 * the info maps use this as their key.
	 *  
	 * @return value of the {@code <Type>} tag
	 */
	String getType();

	/**
	 * Gets the XML content of a tag within the info, including both the start and end tags for multi-line tags
	 *  
	 * @param tag name of the xml tag
	 * @return xml content of the tag if it exists; otherwise {@code null}
	 * @see #setTagXml(String, String)
	 */
	String getTagXml(String tag);

	/**
	 * Sets the XML content of a tag within the info, including both the start and end tags for multi-line tags.
	 * If the tag does not already exist then it will be added as a new tag
	 * 
	 * @param tag name of the xml tag
	 * @param xml content of the tag to set
	 * @return {@code true} if the tag was added as a new tag; {@code false} if the tag previously existed and was updated 
	 * @see #getTagXml(String)
	 */
	boolean setTagXml(String tag, String xml);

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

	/**
	 * Gets the full end tag line, this can be used in conjunction with {@link #setEndTag(String)}
	 * 
	 * @return text of the info end tag
	 * @see #setEndTag(String)
	 */
	String getEndTag();
	
	/**
	 * Sets the value that will be used as the end tag when retrieving the complete xml entry for the info
	 * 
	 * @param val line used as the end tag
	 * @see #getXml()
	 */
	void setEndTag(String val);
	
	/**
	 * Gets the prefix for the top level tags in the info. This should be either a number of tabs or spaces
	 * 
	 * @return prefix to use for tags
	 */
	String getPrefix();

}
