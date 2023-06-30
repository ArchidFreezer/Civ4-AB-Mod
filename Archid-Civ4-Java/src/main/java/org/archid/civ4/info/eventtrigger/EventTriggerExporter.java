package org.archid.civ4.info.eventtrigger;

import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.Row;
import org.archid.civ4.info.AbstractExporter;
import org.archid.civ4.info.EInfo;
import org.archid.civ4.info.IInfos;
import org.archid.civ4.info.eventtrigger.IEventTriggerWorkbook.SheetHeaders;

public class EventTriggerExporter extends AbstractExporter<IInfos<IEventTriggerInfo>, IEventTriggerInfo> {

	/** Logging facility */
	static Logger log = Logger.getLogger(EventTriggerExporter.class.getName());

	public EventTriggerExporter(EInfo infoEnum) {
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
		return IEventTriggerWorkbook.SheetHeaders.values().length;
	}

	@Override
	protected String getInfoListSheetName() {
		return IEventTriggerWorkbook.SHEETNAME_LIST;
	}

	@Override
	protected void populateRow(Row row, IEventTriggerInfo info) {
		int maxHeight = 1;
		int colNum = 0;
		addSingleCell(row.createCell(colNum++), info.getType());
		maxHeight = addRepeatingCell(row.createCell(colNum++), info.getWorldNewsTexts(), maxHeight);
		maxHeight = addRepeatingPairCell(row.createCell(colNum++), info.getTriggerTexts(), maxHeight);
		addSingleCell(row.createCell(colNum++), info.isSinglePlayer());
		addSingleCell(row.createCell(colNum++), info.getPercentGamesActive());
		addSingleCell(row.createCell(colNum++), info.getWeight());
		addSingleCell(row.createCell(colNum++), info.isProbabilityUnitMultiply());
		addSingleCell(row.createCell(colNum++), info.isProbabilityBuildingMultiply());
		addSingleCell(row.createCell(colNum++), info.getCivic());
		addSingleCell(row.createCell(colNum++), info.getMinTreasury());
		addSingleCell(row.createCell(colNum++), info.getMinPopulation());
		addSingleCell(row.createCell(colNum++), info.getMaxPopulation());
		addSingleCell(row.createCell(colNum++), info.getMinMapLandmass());
		addSingleCell(row.createCell(colNum++), info.getMinOurLandmass());
		addSingleCell(row.createCell(colNum++), info.getMaxOurLandmass());
		addSingleCell(row.createCell(colNum++), info.getMinDifficulty());
		addSingleCell(row.createCell(colNum++), info.getAngry());
		addSingleCell(row.createCell(colNum++), info.getUnhealthy());
		maxHeight = addRepeatingCell(row.createCell(colNum++), info.getUnitsRequired(), maxHeight);
		addSingleCell(row.createCell(colNum++), info.getNumUnits());
		addSingleCell(row.createCell(colNum++), info.getNumUnitsGlobal());
		addSingleCell(row.createCell(colNum++), info.getUnitDamagedWeight());
		addSingleCell(row.createCell(colNum++), info.getUnitDistanceWeight());
		addSingleCell(row.createCell(colNum++), info.getUnitExperienceWeight());
		addSingleCell(row.createCell(colNum++), info.isUnitsOnPlot());
		maxHeight = addRepeatingCell(row.createCell(colNum++), info.getBuildingsRequired(), maxHeight);
		addSingleCell(row.createCell(colNum++), info.getNumBuildings());
		addSingleCell(row.createCell(colNum++), info.getNumBuildingsGlobal());
		addSingleCell(row.createCell(colNum++), info.getNumPlotsRequired());
		addSingleCell(row.createCell(colNum++), info.isOwnPlot());
		addSingleCell(row.createCell(colNum++), info.getPlotType());
		maxHeight = addRepeatingCell(row.createCell(colNum++), info.getFeaturesRequired(), maxHeight);
		maxHeight = addRepeatingCell(row.createCell(colNum++), info.getTerrainsRequired(), maxHeight);
		maxHeight = addRepeatingCell(row.createCell(colNum++), info.getImprovementsRequired(), maxHeight);
		maxHeight = addRepeatingCell(row.createCell(colNum++), info.getBonusesRequired(), maxHeight);
		maxHeight = addRepeatingCell(row.createCell(colNum++), info.getRoutesRequired(), maxHeight);
		maxHeight = addRepeatingCell(row.createCell(colNum++), info.getReligionsRequired(), maxHeight);
		addSingleCell(row.createCell(colNum++), info.getNumReligions());
		maxHeight = addRepeatingCell(row.createCell(colNum++), info.getCorporationsRequired(), maxHeight);
		addSingleCell(row.createCell(colNum++), info.getNumCorporations());
		addSingleCell(row.createCell(colNum++), info.isPickReligion());
		addSingleCell(row.createCell(colNum++), info.isStateReligion());
		addSingleCell(row.createCell(colNum++), info.isHolyCity());
		addSingleCell(row.createCell(colNum++), info.isPickCorporation());
		addSingleCell(row.createCell(colNum++), info.isHeadquarters());
		maxHeight = addRepeatingCell(row.createCell(colNum++), info.getEvents(), maxHeight);
		maxHeight = addRepeatingCell(row.createCell(colNum++), info.getPrereqEvents(), maxHeight);
		addSingleCell(row.createCell(colNum++), info.isPrereqEventPlot());
		maxHeight = addRepeatingCell(row.createCell(colNum++), info.getOrPreReqs(), maxHeight);
		maxHeight = addRepeatingCell(row.createCell(colNum++), info.getAndPreReqs(), maxHeight);
		maxHeight = addRepeatingCell(row.createCell(colNum++), info.getObsoleteTechs(), maxHeight);
		addSingleCell(row.createCell(colNum++), info.isRecurring());
		addSingleCell(row.createCell(colNum++), info.isTeam());
		addSingleCell(row.createCell(colNum++), info.isGlobal());
		addSingleCell(row.createCell(colNum++), info.isPickPlayer());
		addSingleCell(row.createCell(colNum++), info.isOtherPlayerWar());
		addSingleCell(row.createCell(colNum++), info.isOtherPlayerHasReligion());
		addSingleCell(row.createCell(colNum++), info.isOtherPlayerHasOtherReligion());
		addSingleCell(row.createCell(colNum++), info.isOtherPlayerAI());
		addSingleCell(row.createCell(colNum++), info.getOtherPlayerShareBorders());
		addSingleCell(row.createCell(colNum++), info.getOtherPlayerHasTech());
		addSingleCell(row.createCell(colNum++), info.isPickCity());
		addSingleCell(row.createCell(colNum++), info.isPickOtherPlayerCity());
		addSingleCell(row.createCell(colNum++), info.isShowPlot());
		addSingleCell(row.createCell(colNum++), info.getCityFoodWeight());
		addSingleCell(row.createCell(colNum++), info.getPythonCanDo());
		addSingleCell(row.createCell(colNum++), info.getPythonCanDoCity());
		addSingleCell(row.createCell(colNum++), info.getPythonCanDoUnit());
		addSingleCell(row.createCell(colNum++), info.getPythonCallback());

		row.setHeightInPoints(maxHeight * row.getSheet().getDefaultRowHeightInPoints());
	}
}