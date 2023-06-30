package org.archid.civ4.java.files.tech;

import org.archid.civ4.java.DefaultSimpleTagProcessor;

public class GridYTagProcessor extends DefaultSimpleTagProcessor {
	
	public GridYTagProcessor() {
		super("iGridY");
	}

	@Override
	public String getImporterRow() {
		return NEWLINETTT + "colNum++; // skip the iGridY col";
	}
}
