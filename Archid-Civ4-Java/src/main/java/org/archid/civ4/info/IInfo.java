package org.archid.civ4.info;

/**
 * @author Jim
 *
 * Base of all info types
 */
public interface IInfo {

	/**
	 * @return value of the {@code <Type>} tag in the info file
	 */
	String getType();

	/**
	 * Sets the value of the {@code <Type>} tag in the XML file
	 *  
	 * @param type {@code String} value of the tag
	 */
	void setType(String type);

}