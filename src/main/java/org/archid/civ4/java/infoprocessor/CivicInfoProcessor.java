package org.archid.civ4.java.infoprocessor;

import org.archid.civ4.java.DefaultResourceArrayTagProcessor;
import org.archid.civ4.java.DefaultResourceArrayTagProcessorData;

public class CivicInfoProcessor extends DefaultInfoProcessor {

	public void init(String packageName) {
		this.packageName = packageName;
		exportImports.add("import org.apache.poi.ss.usermodel.Cell;");
		exportImports.add("import org.archid.civ4.info.IInfoWorkbook;");
		importImports.add("import java.util.ArrayList;");
		importImports.add("import java.util.List;");
		importImports.add("import org.apache.poi.ss.usermodel.Cell;");
		importImports.add("import org.archid.civ4.info.IInfoWorkbook;");
		importImports.add("import org.archid.utils.StringUtils;");
		addTagProcessor(new DefaultResourceArrayTagProcessor(getTagProcessorData("ImprovementYieldChanges", "ImprovementYieldChange", "ImprovementType", "ImprovementYields", "iYield"), tagNameData));
	}
	
	@Override
	public String getXmlFormatter() {
		return "CivicInfoXmlFormatter()";
	}

	@Override
	public Integer getTypeTagIndex() {
		return 1;
	}
	
	@Override
	protected DefaultResourceArrayTagProcessorData getTagProcessorData(String outer, String wrapper, String resource, String list, String element) {
		DefaultResourceArrayTagProcessorData tagData = super.getTagProcessorData(outer, wrapper, resource, list, element);
		tagData.infoInterface = "ICivicInfo";
		tagData.folder = "civic";
		return tagData;
	}
	
}
