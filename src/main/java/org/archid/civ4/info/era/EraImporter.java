package org.archid.civ4.info.era;

import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.Row;
import org.archid.civ4.info.AbstractImporter;
import org.archid.civ4.info.EInfo;
import org.archid.civ4.info.IInfos;

public class EraImporter extends AbstractImporter<IInfos<IEraInfo>, IEraInfo> {

	/** Logging facility */
	static Logger log = Logger.getLogger(EraImporter.class.getName());

	public EraImporter(EInfo infoEnum) {
		super(infoEnum, new EraInfoXmlFormatter());
	}

	@Override
	public String getListSheetName() {
		return IEraWorkbook.SHEETNAME_LIST;
	}

	@Override
	protected IEraInfo parseRow(Row row) {
		int colNum = 0;
		String type = row.getCell(0).getStringCellValue();
		// Handle cells that have been moved
		if (type.isEmpty())
			return null;

		IEraInfo info = EraInfos.createInfo(type);
		parseCell(row.getCell(colNum++), String.class, info::setType);
		parseCell(row.getCell(colNum++), String.class, info::setDescription);
		parseCell(row.getCell(colNum++), String.class, info::setStrategy);
		parseCell(row.getCell(colNum++), Boolean.class, info::setNoGoodies);
		parseCell(row.getCell(colNum++), Boolean.class, info::setNoAnimals);
		parseCell(row.getCell(colNum++), Boolean.class, info::setNoBarbUnits);
		parseCell(row.getCell(colNum++), Boolean.class, info::setNoBarbCities);
		parseCell(row.getCell(colNum++), Integer.class, info::setAdvancedStartPoints);
		parseCell(row.getCell(colNum++), Integer.class, info::setStartingUnitMultiplier);
		parseCell(row.getCell(colNum++), Integer.class, info::setStartingDefenseUnits);
		parseCell(row.getCell(colNum++), Integer.class, info::setStartingWorkerUnits);
		parseCell(row.getCell(colNum++), Integer.class, info::setStartingExploreUnits);
		parseCell(row.getCell(colNum++), Integer.class, info::setStartingGold);
		parseCell(row.getCell(colNum++), Integer.class, info::setMaxCities);
		parseCell(row.getCell(colNum++), Integer.class, info::setFreePopulation);
		parseCell(row.getCell(colNum++), Integer.class, info::setStartPercent);
		parseCell(row.getCell(colNum++), Integer.class, info::setGrowthPercent);
		parseCell(row.getCell(colNum++), Integer.class, info::setTrainPercent);
		parseCell(row.getCell(colNum++), Integer.class, info::setConstructPercent);
		parseCell(row.getCell(colNum++), Integer.class, info::setCreatePercent);
		parseCell(row.getCell(colNum++), Integer.class, info::setResearchPercent);
		parseCell(row.getCell(colNum++), Integer.class, info::setTechCostModifier);
		parseCell(row.getCell(colNum++), Integer.class, info::setBuildPercent);
		parseCell(row.getCell(colNum++), Integer.class, info::setImprovementPercent);
		parseCell(row.getCell(colNum++), Integer.class, info::setGreatPeoplePercent);
		parseCell(row.getCell(colNum++), Integer.class, info::setCulturePercent);
		parseCell(row.getCell(colNum++), Integer.class, info::setAnarchyPercent);
		parseCell(row.getCell(colNum++), Integer.class, info::setEventChancePerTurn);
		parseCell(row.getCell(colNum++), Boolean.class, info::setUnitRangeUnbound);
		parseCell(row.getCell(colNum++), Boolean.class, info::setUnitTerritoryUnbound);
		parseCell(row.getCell(colNum++), Integer.class, info::setUnitRangeChange);
		parseCell(row.getCell(colNum++), Integer.class, info::setUnitRangeModifier);
		parseCell(row.getCell(colNum++), Integer.class, info::setSoundtrackSpace);
		parseCell(row.getCell(colNum++), Boolean.class, info::setFirstSoundtrackFirst);
		parseListCell(row.getCell(colNum++), Integer.class, info::addNaturalYieldLimit);
		parseListCell(row.getCell(colNum++), String.class, info::addEraInfoSoundtrack);
		parsePairsCell(row.getCell(colNum++), String.class, String.class, info::addCitySoundscape);
		parseCell(row.getCell(colNum++), String.class, info::setAudioUnitVictoryScript);
		parseCell(row.getCell(colNum++), String.class, info::setAudioUnitDefeatScript);

		return info;
	}
}