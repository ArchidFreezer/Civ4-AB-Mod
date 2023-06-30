package org.archid.civ4.info.event;

import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.Row;
import org.archid.civ4.info.AbstractImporter;
import org.archid.civ4.info.DefaultXmlFormatter;
import org.archid.civ4.info.EInfo;
import org.archid.civ4.info.IInfos;

public class EventImporter extends AbstractImporter<IInfos<IEventInfo>, IEventInfo> {

	/** Logging facility */
	static Logger log = Logger.getLogger(EventImporter.class.getName());

	public EventImporter(EInfo infoEnum) {
		super(infoEnum, new DefaultXmlFormatter("Event"));
	}

	@Override
	public String getListSheetName() {
		return IEventWorkbook.SHEETNAME_LIST;
	}

	@Override
	protected IEventInfo parseRow(Row row) {
		int colNum = 0;
		String type = row.getCell(0).getStringCellValue();
		// Handle cells that have been moved
		if (type.isEmpty())
			return null;

		IEventInfo info = EventInfos.createInfo(type);
		parseCell(row.getCell(colNum++), String.class, info::setType);
		parseCell(row.getCell(colNum++), String.class, info::setDescription);
		parseCell(row.getCell(colNum++), String.class, info::setLocalInfoText);
		parseListCell(row.getCell(colNum++), String.class, info::addWorldNewsText);
		parseCell(row.getCell(colNum++), String.class, info::setOtherPlayerPopup);
		parseCell(row.getCell(colNum++), String.class, info::setQuestFailText);
		parseCell(row.getCell(colNum++), Boolean.class, info::setQuest);
		parseCell(row.getCell(colNum++), Boolean.class, info::setGlobal);
		parseCell(row.getCell(colNum++), Boolean.class, info::setTeam);
		parseCell(row.getCell(colNum++), Boolean.class, info::setPickCity);
		parseCell(row.getCell(colNum++), Boolean.class, info::setPickOtherPlayerCity);
		parseCell(row.getCell(colNum++), Boolean.class, info::setDeclareWar);
		parseCell(row.getCell(colNum++), Integer.class, info::setGold);
		parseCell(row.getCell(colNum++), Boolean.class, info::setGoldToPlayer);
		parseCell(row.getCell(colNum++), Integer.class, info::setRandomGold);
		parseCell(row.getCell(colNum++), Integer.class, info::setCulture);
		parseCell(row.getCell(colNum++), Integer.class, info::setEspionagePoints);
		parseCell(row.getCell(colNum++), Boolean.class, info::setGoldenAge);
		parseCell(row.getCell(colNum++), Integer.class, info::setFreeUnitSupport);
		parseCell(row.getCell(colNum++), Integer.class, info::setInflationMod);
		parseCell(row.getCell(colNum++), Integer.class, info::setSpaceProductionMod);
		parseCell(row.getCell(colNum++), String.class, info::setTech);
		parsePairsCell(row.getCell(colNum++), String.class, Integer.class, info::addTechFlavor);
		parseCell(row.getCell(colNum++), Integer.class, info::setTechPercent);
		parseCell(row.getCell(colNum++), Integer.class, info::setTechCostPercent);
		parseCell(row.getCell(colNum++), Integer.class, info::setTechMinTurnsLeft);
		parseCell(row.getCell(colNum++), String.class, info::setPrereqTech);
		parseCell(row.getCell(colNum++), String.class, info::setUnitClass);
		parseCell(row.getCell(colNum++), Integer.class, info::setNumFreeUnits);
		parseCell(row.getCell(colNum++), Boolean.class, info::setDisbandUnit);
		parseCell(row.getCell(colNum++), Integer.class, info::setUnitExperience);
		parseCell(row.getCell(colNum++), Integer.class, info::setUnitImmobileTurns);
		parseCell(row.getCell(colNum++), String.class, info::setUnitPromotion);
		parseCell(row.getCell(colNum++), String.class, info::setUnitName);
		parsePairsCell(row.getCell(colNum++), String.class, String.class, info::addUnitCombatPromotion);
		parsePairsCell(row.getCell(colNum++), String.class, String.class, info::addUnitClassPromotion);
		parseCell(row.getCell(colNum++), String.class, info::setBuildingClass);
		parseCell(row.getCell(colNum++), Integer.class, info::setBuildingChange);
		parseTriplesCell(row.getCell(colNum++), String.class, String.class, Integer.class, info::addBuildingExtraYield);
		parseTriplesCell(row.getCell(colNum++), String.class, String.class, Integer.class, info::addBuildingExtraCommerce);
		parsePairsCell(row.getCell(colNum++), String.class, Integer.class, info::addBuildingExtraHappie);
		parsePairsCell(row.getCell(colNum++), String.class, Integer.class, info::addBuildingExtraHealth);
		parseCell(row.getCell(colNum++), Integer.class, info::setHappy);
		parseCell(row.getCell(colNum++), Integer.class, info::setHealth);
		parseCell(row.getCell(colNum++), Integer.class, info::setHurryAnger);
		parseCell(row.getCell(colNum++), Integer.class, info::setHappyTurns);
		parseCell(row.getCell(colNum++), Integer.class, info::setRevoltTurns);
		parseCell(row.getCell(colNum++), Integer.class, info::setMinPillage);
		parseCell(row.getCell(colNum++), Integer.class, info::setMaxPillage);
		parseCell(row.getCell(colNum++), Integer.class, info::setFood);
		parseCell(row.getCell(colNum++), Integer.class, info::setFoodPercent);
		parsePairsCell(row.getCell(colNum++), String.class, Integer.class, info::addFreeSpecialistCount);
		parseCell(row.getCell(colNum++), String.class, info::setFeatureType);
		parseCell(row.getCell(colNum++), Integer.class, info::setFeatureChange);
		parseCell(row.getCell(colNum++), String.class, info::setImprovementType);
		parseCell(row.getCell(colNum++), Integer.class, info::setImprovementChange);
		parseCell(row.getCell(colNum++), String.class, info::setBonusType);
		parseCell(row.getCell(colNum++), Integer.class, info::setBonusChange);
		parseCell(row.getCell(colNum++), String.class, info::setRouteType);
		parseCell(row.getCell(colNum++), Integer.class, info::setRouteChange);
		parseCell(row.getCell(colNum++), String.class, info::setBonusRevealed);
		parseCell(row.getCell(colNum++), String.class, info::setBonusGift);
		parsePairsCell(row.getCell(colNum++), String.class, Integer.class, info::addPlotExtraYield);
		parseCell(row.getCell(colNum++), Integer.class, info::setConvertOwnCities);
		parseCell(row.getCell(colNum++), Integer.class, info::setConvertOtherCities);
		parseCell(row.getCell(colNum++), Integer.class, info::setMaxNumReligions);
		parseCell(row.getCell(colNum++), Integer.class, info::setOurAttitudeModifier);
		parseCell(row.getCell(colNum++), Integer.class, info::setAttitudeModifier);
		parseCell(row.getCell(colNum++), Integer.class, info::setTheirEnemyAttitudeModifier);
		parseCell(row.getCell(colNum++), Integer.class, info::setPopulationChange);
		parsePairsCell(row.getCell(colNum++), String.class, Integer.class, info::addAdditionalEvent);
		parsePairsCell(row.getCell(colNum++), String.class, Integer.class, info::addEventTime);
		parsePairsCell(row.getCell(colNum++), String.class, Integer.class, info::addClearEvent);
		parseCell(row.getCell(colNum++), String.class, info::setPythonCallback);
		parseCell(row.getCell(colNum++), String.class, info::setPythonExpireCheck);
		parseCell(row.getCell(colNum++), String.class, info::setPythonCanDo);
		parseCell(row.getCell(colNum++), String.class, info::setPythonHelp);
		parseCell(row.getCell(colNum++), String.class, info::setButton);
		parseCell(row.getCell(colNum++), Integer.class, info::setAIValue);

		return info;
	}
}