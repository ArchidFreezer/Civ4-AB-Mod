package org.archid.civ4.info.buildingclass;

import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.Row;
import org.archid.civ4.info.AbstractImporter;
import org.archid.civ4.info.EInfo;
import org.archid.civ4.info.IInfos;

public class BuildingClassImporter extends AbstractImporter<IInfos<IBuildingClassInfo>, IBuildingClassInfo> {

	/** Logging facility */
	static Logger log = Logger.getLogger(BuildingClassImporter.class.getName());

	public BuildingClassImporter(EInfo infoEnum) {
		super(infoEnum, new BuildingClassInfoXmlFormatter());
	}

	@Override
	public String getListSheetName() {
		return IBuildingClassWorkbook.SHEETNAME_LIST;
	}

	@Override
	protected IBuildingClassInfo parseRow(Row row) {
		int colNum = 0;
		String type = row.getCell(0).getStringCellValue();
		// Handle cells that have been moved
		if (type.isEmpty())
			return null;

		IBuildingClassInfo info = BuildingClassInfos.createInfo(type);
		parseCell(row.getCell(colNum++), String.class, info::setType);
		parseCell(row.getCell(colNum++), String.class, info::setDescription);
		parseCell(row.getCell(colNum++), Integer.class, info::setCategory);
		parseCell(row.getCell(colNum++), Integer.class, info::setMaxGlobalInstances);
		parseCell(row.getCell(colNum++), Integer.class, info::setMaxTeamInstances);
		parseCell(row.getCell(colNum++), Integer.class, info::setMaxPlayerInstances);
		parseCell(row.getCell(colNum++), Integer.class, info::setExtraPlayerInstances);
		parseCell(row.getCell(colNum++), Boolean.class, info::setNoLimit);
		parseCell(row.getCell(colNum++), Boolean.class, info::setMonument);
		parseCell(row.getCell(colNum++), String.class, info::setDefaultBuilding);
		parsePairsCell(row.getCell(colNum++), String.class, Integer.class, info::addVictoryThreshold);

		return info;
	}
}