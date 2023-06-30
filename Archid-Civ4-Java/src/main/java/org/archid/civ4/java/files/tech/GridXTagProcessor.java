package org.archid.civ4.java.files.tech;

import org.archid.civ4.java.DefaultSimpleTagProcessor;

public class GridXTagProcessor extends DefaultSimpleTagProcessor {
	
	public GridXTagProcessor() {
		super("iGridX");
	}

	@Override
	public String getImporterRow() {
		return NEWLINETTT + "colNum++; // skip the iGridX col";
	}
}
