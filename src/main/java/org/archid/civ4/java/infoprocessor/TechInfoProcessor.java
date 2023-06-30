package org.archid.civ4.java.infoprocessor;

import org.archid.civ4.java.DefaultResourceArrayTagProcessorData;
import org.archid.civ4.java.IJavaFileCreator;
import org.archid.civ4.java.files.tech.GridXTagProcessor;
import org.archid.civ4.java.files.tech.GridYTagProcessor;
import org.archid.civ4.java.files.tech.TechInfoExporterCreator;
import org.archid.civ4.java.files.tech.TechInfoImporterCreator;
import org.archid.civ4.java.files.tech.TechInfoWorkbookCreator;

public class TechInfoProcessor extends DefaultInfoProcessor {
	
	@Override
	public void init(String packageName) {
		this.packageName = packageName;
		addTagProcessor(new GridXTagProcessor());
		addTagProcessor(new GridYTagProcessor());
	}
	
	@Override
	public IJavaFileCreator getFileCreator(FileCreators creator) {
		switch(creator) {
		case INFO_WORKBOOK:
			return new TechInfoWorkbookCreator();
		case INFO_EXPORTER:
			return new TechInfoExporterCreator();
		case INFO_IMPORTER:
			return new TechInfoImporterCreator();
		default:
			return super.getFileCreator(creator);
		}
	}

	@Override
	protected DefaultResourceArrayTagProcessorData getTagProcessorData(String outer, String wrapper, String resource, String list, String element) {
		DefaultResourceArrayTagProcessorData tagData = super.getTagProcessorData(outer, wrapper, resource, list, element);
		tagData.infoInterface = "ITechInfo";
		tagData.folder = "tech";
		return tagData;
	}
	
}
