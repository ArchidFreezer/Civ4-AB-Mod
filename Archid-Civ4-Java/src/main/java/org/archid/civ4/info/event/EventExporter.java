package org.archid.civ4.info.event;

import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.Row;
import org.archid.civ4.info.AbstractExporter;
import org.archid.civ4.info.EInfo;
import org.archid.civ4.info.IInfos;
import org.archid.civ4.info.event.IEventWorkbook.SheetHeaders;

public class EventExporter extends AbstractExporter<IInfos<IEventInfo>, IEventInfo> {

	/** Logging facility */
	static Logger log = Logger.getLogger(EventExporter.class.getName());

	public EventExporter(EInfo infoEnum) {
		super(infoEnum);
	}

	@Override
	public List<String> getHeaders() {
		List<String> headers = new ArrayList<String>();
		for (SheetHeaders header: SheetHeaders.values()) {
			headers.add(header.toString());
		}
		return headers;
	}

	@Override
	protected int getNumCols() {
		return IEventWorkbook.SheetHeaders.values().length;
	}

	@Override
	protected String getInfoListSheetName() {
		return IEventWorkbook.SHEETNAME_LIST;
	}

	@Override
	protected void populateRow(Row row, IEventInfo info) {
		int maxHeight = 1;
		int colNum = 0;
		addSingleCell(row.createCell(colNum++), info.getType());
		addSingleCell(row.createCell(colNum++), info.getDescription());
		addSingleCell(row.createCell(colNum++), info.getLocalInfoText());
		maxHeight = addRepeatingCell(row.createCell(colNum++), info.getWorldNewsTexts(), maxHeight);
		addSingleCell(row.createCell(colNum++), info.getOtherPlayerPopup());
		addSingleCell(row.createCell(colNum++), info.getQuestFailText());
		addSingleCell(row.createCell(colNum++), info.isQuest());
		addSingleCell(row.createCell(colNum++), info.isGlobal());
		addSingleCell(row.createCell(colNum++), info.isTeam());
		addSingleCell(row.createCell(colNum++), info.isPickCity());
		addSingleCell(row.createCell(colNum++), info.isPickOtherPlayerCity());
		addSingleCell(row.createCell(colNum++), info.isDeclareWar());
		addSingleCell(row.createCell(colNum++), info.getGold());
		addSingleCell(row.createCell(colNum++), info.isGoldToPlayer());
		addSingleCell(row.createCell(colNum++), info.getRandomGold());
		addSingleCell(row.createCell(colNum++), info.getCulture());
		addSingleCell(row.createCell(colNum++), info.getEspionagePoints());
		addSingleCell(row.createCell(colNum++), info.isGoldenAge());
		addSingleCell(row.createCell(colNum++), info.getFreeUnitSupport());
		addSingleCell(row.createCell(colNum++), info.getInflationMod());
		addSingleCell(row.createCell(colNum++), info.getSpaceProductionMod());
		addSingleCell(row.createCell(colNum++), info.getTech());
		maxHeight = addRepeatingPairCell(row.createCell(colNum++), info.getTechFlavors(), maxHeight);
		addSingleCell(row.createCell(colNum++), info.getTechPercent());
		addSingleCell(row.createCell(colNum++), info.getTechCostPercent());
		addSingleCell(row.createCell(colNum++), info.getTechMinTurnsLeft());
		addSingleCell(row.createCell(colNum++), info.getPrereqTech());
		addSingleCell(row.createCell(colNum++), info.getUnitClass());
		addSingleCell(row.createCell(colNum++), info.getNumFreeUnits());
		addSingleCell(row.createCell(colNum++), info.isDisbandUnit());
		addSingleCell(row.createCell(colNum++), info.getUnitExperience());
		addSingleCell(row.createCell(colNum++), info.getUnitImmobileTurns());
		addSingleCell(row.createCell(colNum++), info.getUnitPromotion());
		addSingleCell(row.createCell(colNum++), info.getUnitName());
		maxHeight = addRepeatingPairCell(row.createCell(colNum++), info.getUnitCombatPromotions(), maxHeight);
		maxHeight = addRepeatingPairCell(row.createCell(colNum++), info.getUnitClassPromotions(), maxHeight);
		addSingleCell(row.createCell(colNum++), info.getBuildingClass());
		addSingleCell(row.createCell(colNum++), info.getBuildingChange());
		maxHeight = addRepeatingTripleCell(row.createCell(colNum++), info.getBuildingExtraYields(), maxHeight);
		maxHeight = addRepeatingTripleCell(row.createCell(colNum++), info.getBuildingExtraCommerces(), maxHeight);
		maxHeight = addRepeatingPairCell(row.createCell(colNum++), info.getBuildingExtraHappies(), maxHeight);
		maxHeight = addRepeatingPairCell(row.createCell(colNum++), info.getBuildingExtraHealths(), maxHeight);
		addSingleCell(row.createCell(colNum++), info.getHappy());
		addSingleCell(row.createCell(colNum++), info.getHealth());
		addSingleCell(row.createCell(colNum++), info.getHurryAnger());
		addSingleCell(row.createCell(colNum++), info.getHappyTurns());
		addSingleCell(row.createCell(colNum++), info.getRevoltTurns());
		addSingleCell(row.createCell(colNum++), info.getMinPillage());
		addSingleCell(row.createCell(colNum++), info.getMaxPillage());
		addSingleCell(row.createCell(colNum++), info.getFood());
		addSingleCell(row.createCell(colNum++), info.getFoodPercent());
		maxHeight = addRepeatingPairCell(row.createCell(colNum++), info.getFreeSpecialistCounts(), maxHeight);
		addSingleCell(row.createCell(colNum++), info.getFeatureType());
		addSingleCell(row.createCell(colNum++), info.getFeatureChange());
		addSingleCell(row.createCell(colNum++), info.getImprovementType());
		addSingleCell(row.createCell(colNum++), info.getImprovementChange());
		addSingleCell(row.createCell(colNum++), info.getBonusType());
		addSingleCell(row.createCell(colNum++), info.getBonusChange());
		addSingleCell(row.createCell(colNum++), info.getRouteType());
		addSingleCell(row.createCell(colNum++), info.getRouteChange());
		addSingleCell(row.createCell(colNum++), info.getBonusRevealed());
		addSingleCell(row.createCell(colNum++), info.getBonusGift());
		maxHeight = addRepeatingPairCell(row.createCell(colNum++), info.getPlotExtraYields(), maxHeight);
		addSingleCell(row.createCell(colNum++), info.getConvertOwnCities());
		addSingleCell(row.createCell(colNum++), info.getConvertOtherCities());
		addSingleCell(row.createCell(colNum++), info.getMaxNumReligions());
		addSingleCell(row.createCell(colNum++), info.getOurAttitudeModifier());
		addSingleCell(row.createCell(colNum++), info.getAttitudeModifier());
		addSingleCell(row.createCell(colNum++), info.getTheirEnemyAttitudeModifier());
		addSingleCell(row.createCell(colNum++), info.getPopulationChange());
		maxHeight = addRepeatingPairCell(row.createCell(colNum++), info.getAdditionalEvents(), maxHeight);
		maxHeight = addRepeatingPairCell(row.createCell(colNum++), info.getEventTimes(), maxHeight);
		maxHeight = addRepeatingPairCell(row.createCell(colNum++), info.getClearEvents(), maxHeight);
		addSingleCell(row.createCell(colNum++), info.getPythonCallback());
		addSingleCell(row.createCell(colNum++), info.getPythonExpireCheck());
		addSingleCell(row.createCell(colNum++), info.getPythonCanDo());
		addSingleCell(row.createCell(colNum++), info.getPythonHelp());
		addSingleCell(row.createCell(colNum++), info.getButton());
		addSingleCell(row.createCell(colNum++), info.getAIValue());

		row.setHeightInPoints(maxHeight * row.getSheet().getDefaultRowHeightInPoints());
	}
}