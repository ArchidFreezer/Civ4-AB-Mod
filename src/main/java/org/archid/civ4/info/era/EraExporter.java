package org.archid.civ4.info.era;

import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.Row;
import org.archid.civ4.info.AbstractExporter;
import org.archid.civ4.info.EInfo;
import org.archid.civ4.info.IInfos;
import org.archid.civ4.info.era.IEraWorkbook.SheetHeaders;

public class EraExporter extends AbstractExporter<IInfos<IEraInfo>, IEraInfo> {

	/** Logging facility */
	static Logger log = Logger.getLogger(EraExporter.class.getName());

	public EraExporter(EInfo infoEnum) {
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
		return IEraWorkbook.SheetHeaders.values().length;
	}

	@Override
	protected String getInfoListSheetName() {
		return IEraWorkbook.SHEETNAME_LIST;
	}

	@Override
	protected void populateRow(Row row, IEraInfo info) {
		int maxHeight = 1;
		int colNum = 0;
		addSingleCell(row.createCell(colNum++), info.getType());
		addSingleCell(row.createCell(colNum++), info.getDescription());
		addSingleCell(row.createCell(colNum++), info.getStrategy());
		addSingleCell(row.createCell(colNum++), info.isNoGoodies());
		addSingleCell(row.createCell(colNum++), info.isNoAnimals());
		addSingleCell(row.createCell(colNum++), info.isNoBarbUnits());
		addSingleCell(row.createCell(colNum++), info.isNoBarbCities());
		addSingleCell(row.createCell(colNum++), info.getAdvancedStartPoints());
		addSingleCell(row.createCell(colNum++), info.getStartingUnitMultiplier());
		addSingleCell(row.createCell(colNum++), info.getStartingDefenseUnits());
		addSingleCell(row.createCell(colNum++), info.getStartingWorkerUnits());
		addSingleCell(row.createCell(colNum++), info.getStartingExploreUnits());
		addSingleCell(row.createCell(colNum++), info.getStartingGold());
		addSingleCell(row.createCell(colNum++), info.getMaxCities());
		addSingleCell(row.createCell(colNum++), info.getFreePopulation());
		addSingleCell(row.createCell(colNum++), info.getStartPercent());
		addSingleCell(row.createCell(colNum++), info.getGrowthPercent());
		addSingleCell(row.createCell(colNum++), info.getTrainPercent());
		addSingleCell(row.createCell(colNum++), info.getConstructPercent());
		addSingleCell(row.createCell(colNum++), info.getCreatePercent());
		addSingleCell(row.createCell(colNum++), info.getResearchPercent());
		addSingleCell(row.createCell(colNum++), info.getTechCostModifier());
		addSingleCell(row.createCell(colNum++), info.getBuildPercent());
		addSingleCell(row.createCell(colNum++), info.getImprovementPercent());
		addSingleCell(row.createCell(colNum++), info.getGreatPeoplePercent());
		addSingleCell(row.createCell(colNum++), info.getCulturePercent());
		addSingleCell(row.createCell(colNum++), info.getAnarchyPercent());
		addSingleCell(row.createCell(colNum++), info.getEventChancePerTurn());
		addSingleCell(row.createCell(colNum++), info.isUnitRangeUnbound());
		addSingleCell(row.createCell(colNum++), info.isUnitTerritoryUnbound());
		addSingleCell(row.createCell(colNum++), info.getUnitRangeChange());
		addSingleCell(row.createCell(colNum++), info.getUnitRangeModifier());
		addSingleCell(row.createCell(colNum++), info.getSoundtrackSpace());
		addSingleCell(row.createCell(colNum++), info.isFirstSoundtrackFirst());
		maxHeight = addRepeatingCell(row.createCell(colNum++), info.getNaturalYieldLimits(), maxHeight);
		maxHeight = addRepeatingCell(row.createCell(colNum++), info.getEraInfoSoundtracks(), maxHeight);
		maxHeight = addRepeatingPairCell(row.createCell(colNum++), info.getCitySoundscapes(), maxHeight);
		addSingleCell(row.createCell(colNum++), info.getAudioUnitVictoryScript());
		addSingleCell(row.createCell(colNum++), info.getAudioUnitDefeatScript());

		row.setHeightInPoints(maxHeight * row.getSheet().getDefaultRowHeightInPoints());
	}
}