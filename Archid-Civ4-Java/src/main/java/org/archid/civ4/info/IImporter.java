package org.archid.civ4.info;

public interface IImporter {

	/**
	 * Read the contents of the Xlsx file and create an info file from the contents.
	 * 
	 * The XML file will have leading spaces replaced by tabs and by default will have the value of the {@code <Type>} tag added
	 * to the start info tag as a comment. To prevent this behaviour, override the {@link AbstractImporter#commentXml()} function
	 * to return {@code false}
	 */
	void importXLSX();
	
	/**
	 * Gets the name of the sheet within the workbook used as the source of the import.
	 * This sheet is expected to contain a single row for each info object.
	 * 
	 * @return {@code String} containing the sheet name to process
	 */
	String getListSheetName();
	
}