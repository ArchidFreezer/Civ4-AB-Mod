package org.archid.civ4.info;

public interface IXmlFormatter {
	
	/**
	 * Allows an XML file to be processed after Jaxb has generated it.<br>
	 *  
	 * @param path file path of the Xml file to process
	 */
	public void format(String path);

}
