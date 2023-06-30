package org.archid.civ4.java.infoprocessor;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.archid.civ4.java.DefaultResourceArrayTagProcessorData;
import org.archid.civ4.java.IJavaFileCreator;
import org.archid.civ4.java.ITagProcessor;
import org.archid.civ4.java.JavaCodeGeneratorData;
import org.archid.civ4.java.TagNameData;
import org.archid.civ4.java.files.DefaultInfoCodeCreator;
import org.archid.civ4.java.files.DefaultInfoExporterCreator;
import org.archid.civ4.java.files.DefaultInfoImporterCreator;
import org.archid.civ4.java.files.DefaultInfoInterfaceCreator;
import org.archid.civ4.java.files.DefaultInfoMapAdapterCreator;
import org.archid.civ4.java.files.DefaultInfoWorkbookCreator;
import org.archid.civ4.java.files.DefaultPackageInfoCreator;

public class DefaultInfoProcessor implements IInfoProcessor {
	
	private Map<String, ITagProcessor> tags = new HashMap<String, ITagProcessor>();
	protected String packageName;
	protected Set<String> exportImports = new HashSet<String>();
	protected Set<String> importImports = new HashSet<String>();
	protected Map<String, String> filesToWrite = new HashMap<String, String>();
	protected Map<InfoOverrides, String> overrides = new HashMap<InfoOverrides, String>();
	protected TagNameData tagNameData;
	
	public DefaultInfoProcessor() {
		this.tagNameData = JavaCodeGeneratorData.getInstance().getTagNameData();
	}
	

	@Override
	public boolean hasTagProcessor(String tag) {
		return tags.containsKey(tag);
	}

	@Override
	public ITagProcessor getTagProcessor(String tag) {
		return tags.get(tag);
	}
	
	public void addTagProcessor(ITagProcessor tag) {
		tags.put(tag.getTagName(), tag);
		exportImports.addAll(tag.getExporterImports());
		importImports.addAll(tag.getImporterImports());
		filesToWrite.putAll(tag.getFilesToWrite());
	}

	@Override
	public void init(String packageName) {
		this.packageName = packageName;
	}

	@Override
	public String getXmlFormatter() {
		importImports.add("import org.archid.civ4.info.DefaultXmlFormatter;");
		return "DefaultXmlFormatter(\"" + packageName + "\")";
	}

	@Override
	public Integer getTypeTagIndex() {
		return 0;
	}
	
	@Override
	public Set<String> getExportImports() {
		return exportImports;
	}
	
	@Override
	public Set<String> getImportImports() {
		return importImports;
	}


	@Override
	public Map<String, String> getFilesToWrite() {
		return filesToWrite;
	}
	
	protected DefaultResourceArrayTagProcessorData getTagProcessorData(String outer, String wrapper, String resource, String list, String element) {
		DefaultResourceArrayTagProcessorData tagData = new DefaultResourceArrayTagProcessorData();
		tagData.outer = outer;
		tagData.wrapper = wrapper;
		tagData.resource = resource;
		tagData.list = list;
		tagData.element = element;
		return tagData;
	}


	@Override
	public boolean hasOverride(InfoOverrides override) {
		return overrides.containsKey(override);
	}


	@Override
	public String getOverride(InfoOverrides override) {
		return hasOverride(override) ? overrides.get(override) : "";
	}


	@Override
	public IJavaFileCreator getFileCreator(FileCreators creator) {
		switch(creator) {
		case INFO_CODE:
			return new DefaultInfoCodeCreator();
		case INFO_EXPORTER:
			return new DefaultInfoExporterCreator();
		case INFO_IMPORTER:
			return new DefaultInfoImporterCreator();
		case INFO_INTERFACE:
			return new DefaultInfoInterfaceCreator();
		case INFO_MAP_ADAPTER:
			return new DefaultInfoMapAdapterCreator();
		case INFO_WORKBOOK:
			return new DefaultInfoWorkbookCreator();
		case PACKAGE:
			return new DefaultPackageInfoCreator();
		default:
			return null;
		}
	}


}
