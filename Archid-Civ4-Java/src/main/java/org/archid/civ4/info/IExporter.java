package org.archid.civ4.info;

import java.util.List;

public interface IExporter {
	
	void createXLSX();
	
	List<String> getHeaders();

	void setBackup(boolean backup);
	
}