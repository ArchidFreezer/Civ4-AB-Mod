package org.archid.civ4.info.eventtrigger;

import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.Row;
import org.archid.civ4.info.AbstractImporter;
import org.archid.civ4.info.DefaultXmlFormatter;
import org.archid.civ4.info.EInfo;
import org.archid.civ4.info.IInfos;

public class EventTriggerImporter extends AbstractImporter<IInfos<IEventTriggerInfo>, IEventTriggerInfo> {

	/** Logging facility */
	static Logger log = Logger.getLogger(EventTriggerImporter.class.getName());

	public EventTriggerImporter(EInfo infoEnum) {
		super(infoEnum, new DefaultXmlFormatter("EventTrigger"));
	}

	@Override
	public String getListSheetName() {
		return IEventTriggerWorkbook.SHEETNAME_LIST;
	}

	@Override
	protected IEventTriggerInfo parseRow(Row row) {
		int colNum = 0;
		String type = row.getCell(0).getStringCellValue();
		// Handle cells that have been moved
		if (type.isEmpty())
			return null;

		IEventTriggerInfo info = EventTriggerInfos.createInfo(type);
		parseCell(row.getCell(colNum++), String.class, info::setType);
		parseListCell(row.getCell(colNum++), String.class, info::addWorldNewsText);
		parsePairsCell(row.getCell(colNum++), String.class, String.class, info::addTriggerText);
		parseCell(row.getCell(colNum++), Boolean.class, info::setSinglePlayer);
		parseCell(row.getCell(colNum++), Integer.class, info::setPercentGamesActive);
		parseCell(row.getCell(colNum++), Integer.class, info::setWeight);
		parseCell(row.getCell(colNum++), Boolean.class, info::setProbabilityUnitMultiply);
		parseCell(row.getCell(colNum++), Boolean.class, info::setProbabilityBuildingMultiply);
		parseCell(row.getCell(colNum++), String.class, info::setCivic);
		parseCell(row.getCell(colNum++), Integer.class, info::setMinTreasury);
		parseCell(row.getCell(colNum++), Integer.class, info::setMinPopulation);
		parseCell(row.getCell(colNum++), Integer.class, info::setMaxPopulation);
		parseCell(row.getCell(colNum++), Integer.class, info::setMinMapLandmass);
		parseCell(row.getCell(colNum++), Integer.class, info::setMinOurLandmass);
		parseCell(row.getCell(colNum++), Integer.class, info::setMaxOurLandmass);
		parseCell(row.getCell(colNum++), String.class, info::setMinDifficulty);
		parseCell(row.getCell(colNum++), Integer.class, info::setAngry);
		parseCell(row.getCell(colNum++), Integer.class, info::setUnhealthy);
		parseListCell(row.getCell(colNum++), String.class, info::addUnitRequired);
		parseCell(row.getCell(colNum++), Integer.class, info::setNumUnits);
		parseCell(row.getCell(colNum++), Integer.class, info::setNumUnitsGlobal);
		parseCell(row.getCell(colNum++), Integer.class, info::setUnitDamagedWeight);
		parseCell(row.getCell(colNum++), Integer.class, info::setUnitDistanceWeight);
		parseCell(row.getCell(colNum++), Integer.class, info::setUnitExperienceWeight);
		parseCell(row.getCell(colNum++), Boolean.class, info::setUnitsOnPlot);
		parseListCell(row.getCell(colNum++), String.class, info::addBuildingRequired);
		parseCell(row.getCell(colNum++), Integer.class, info::setNumBuildings);
		parseCell(row.getCell(colNum++), Integer.class, info::setNumBuildingsGlobal);
		parseCell(row.getCell(colNum++), Integer.class, info::setNumPlotsRequired);
		parseCell(row.getCell(colNum++), Boolean.class, info::setOwnPlot);
		parseCell(row.getCell(colNum++), Integer.class, info::setPlotType);
		parseListCell(row.getCell(colNum++), String.class, info::addFeatureRequired);
		parseListCell(row.getCell(colNum++), String.class, info::addTerrainRequired);
		parseListCell(row.getCell(colNum++), String.class, info::addImprovementRequired);
		parseListCell(row.getCell(colNum++), String.class, info::addBonusRequired);
		parseListCell(row.getCell(colNum++), String.class, info::addRouteRequired);
		parseListCell(row.getCell(colNum++), String.class, info::addReligionRequired);
		parseCell(row.getCell(colNum++), Integer.class, info::setNumReligions);
		parseListCell(row.getCell(colNum++), String.class, info::addCorporationRequired);
		parseCell(row.getCell(colNum++), Integer.class, info::setNumCorporations);
		parseCell(row.getCell(colNum++), Boolean.class, info::setPickReligion);
		parseCell(row.getCell(colNum++), Boolean.class, info::setStateReligion);
		parseCell(row.getCell(colNum++), Boolean.class, info::setHolyCity);
		parseCell(row.getCell(colNum++), Boolean.class, info::setPickCorporation);
		parseCell(row.getCell(colNum++), Boolean.class, info::setHeadquarters);
		parseListCell(row.getCell(colNum++), String.class, info::addEvent);
		parseListCell(row.getCell(colNum++), String.class, info::addPrereqEvent);
		parseCell(row.getCell(colNum++), Boolean.class, info::setPrereqEventPlot);
		parseListCell(row.getCell(colNum++), String.class, info::addOrPreReq);
		parseListCell(row.getCell(colNum++), String.class, info::addAndPreReq);
		parseListCell(row.getCell(colNum++), String.class, info::addObsoleteTech);
		parseCell(row.getCell(colNum++), Boolean.class, info::setRecurring);
		parseCell(row.getCell(colNum++), Boolean.class, info::setTeam);
		parseCell(row.getCell(colNum++), Boolean.class, info::setGlobal);
		parseCell(row.getCell(colNum++), Boolean.class, info::setPickPlayer);
		parseCell(row.getCell(colNum++), Boolean.class, info::setOtherPlayerWar);
		parseCell(row.getCell(colNum++), Boolean.class, info::setOtherPlayerHasReligion);
		parseCell(row.getCell(colNum++), Boolean.class, info::setOtherPlayerHasOtherReligion);
		parseCell(row.getCell(colNum++), Boolean.class, info::setOtherPlayerAI);
		parseCell(row.getCell(colNum++), Integer.class, info::setOtherPlayerShareBorders);
		parseCell(row.getCell(colNum++), String.class, info::setOtherPlayerHasTech);
		parseCell(row.getCell(colNum++), Boolean.class, info::setPickCity);
		parseCell(row.getCell(colNum++), Boolean.class, info::setPickOtherPlayerCity);
		parseCell(row.getCell(colNum++), Boolean.class, info::setShowPlot);
		parseCell(row.getCell(colNum++), Integer.class, info::setCityFoodWeight);
		parseCell(row.getCell(colNum++), String.class, info::setPythonCanDo);
		parseCell(row.getCell(colNum++), String.class, info::setPythonCanDoCity);
		parseCell(row.getCell(colNum++), String.class, info::setPythonCanDoUnit);
		parseCell(row.getCell(colNum++), String.class, info::setPythonCallback);

		return info;
	}
}