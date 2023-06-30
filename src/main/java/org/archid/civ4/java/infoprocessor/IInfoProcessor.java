package org.archid.civ4.java.infoprocessor;

import java.util.Map;
import java.util.Set;

import org.archid.civ4.java.IJavaFileCreator;
import org.archid.civ4.java.ITagProcessor;

public interface IInfoProcessor {
	
	public enum FileCreators {
		INFO_CODE, INFO_EXPORTER, INFO_IMPORTER, INFO_INTERFACE, INFO_MAP_ADAPTER, INFO_WORKBOOK, PACKAGE;
	}
	
	public enum InfoOverrides {
		IMPORTER, EXPORTER;
	}

	public void init(String packageName);
	public boolean hasTagProcessor(String tag);
	public ITagProcessor getTagProcessor(String tag);
	public String getXmlFormatter();
	public Integer getTypeTagIndex();
	public IJavaFileCreator getFileCreator(FileCreators creator);
	
	
	public Set<String> getExportImports();
	public Set<String> getImportImports();
	public Map<String, String> getFilesToWrite();
	public boolean hasOverride(InfoOverrides override);
	public String getOverride(InfoOverrides override);
}
