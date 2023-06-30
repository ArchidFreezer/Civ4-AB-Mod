package org.archid.civ4.java;

import java.util.Map;
import java.util.Set;

public interface ITagProcessor extends IJavaFileContent {

	String getTagName();

	Set<String> getAdapterImports();
	Set<String> getExporterImports();
	Set<String> getImporterImports();
	Map<String, String> getFilesToWrite();

	String getAdapterElement();

	String getUnmarshallString();

	String getMarshallString();
	
	String getImporterRow();
	String getImporterCellReader();

	String getExporterRow();
	String getExporterCellWriter();
}