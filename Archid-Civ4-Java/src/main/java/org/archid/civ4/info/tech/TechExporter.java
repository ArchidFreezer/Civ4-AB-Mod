package org.archid.civ4.info.tech;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.archid.civ4.info.AbstractExporter;
import org.archid.civ4.info.EInfo;
import org.archid.civ4.info.IInfos;
import org.archid.civ4.info.tech.ITechWorkbook.SheetHeaders;

public class TechExporter extends AbstractExporter<IInfos<ITechInfo>, ITechInfo> {

	/** Logging facility */
	static Logger log = Logger.getLogger(TechExporter.class.getName());

	private Map<Integer, CellStyle> backgrounds;

	public TechExporter(EInfo infoEnum) {
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
		return ITechWorkbook.SheetHeaders.values().length;
	}

	@Override
	protected String getInfoListSheetName() {
		return ITechWorkbook.SHEETNAME_LIST;
	}

	@Override
	protected void createSheets() {
		createTechTreeSheet();
		super.createSheets();
	}

	@Override
	protected void populateRow(Row row, ITechInfo info) {
		int maxHeight = 1;
		int colNum = 0;
		addSingleCell(row.createCell(colNum++), info.getType());
		addSingleCell(row.createCell(colNum++), info.getDescription());
		addSingleCell(row.createCell(colNum++), info.getCivilopedia());
		addSingleCell(row.createCell(colNum++), info.getHelp());
		addSingleCell(row.createCell(colNum++), info.getStrategy());
		addSingleCell(row.createCell(colNum++), info.getAdvisor());
		addSingleCell(row.createCell(colNum++), info.getAIWeight());
		addSingleCell(row.createCell(colNum++), info.getAITradeModifier());
		addSingleCell(row.createCell(colNum++), info.getCost());
		addSingleCell(row.createCell(colNum++), info.getAdvancedStartCost());
		addSingleCell(row.createCell(colNum++), info.getAdvancedStartCostIncrease());
		addSingleCell(row.createCell(colNum++), info.getEra());
		addSingleCell(row.createCell(colNum++), info.isCivSettled());
		addSingleCell(row.createCell(colNum++), info.getFreeCapitalBuildingClass());
		addSingleCell(row.createCell(colNum++), info.getFirstFreeUnitClass());
		addSingleCell(row.createCell(colNum++), info.getFreeUnitClass());
		addSingleCell(row.createCell(colNum++), info.getFeatureProductionModifier());
		addSingleCell(row.createCell(colNum++), info.getWorkerSpeedModifier());
		addSingleCell(row.createCell(colNum++), info.getTradeRoutes());
		addSingleCell(row.createCell(colNum++), info.getHealth());
		addSingleCell(row.createCell(colNum++), info.getHappiness());
		addSingleCell(row.createCell(colNum++), info.getFirstFreeTechs());
		addSingleCell(row.createCell(colNum++), info.getAsset());
		addSingleCell(row.createCell(colNum++), info.getPower());
		addSingleCell(row.createCell(colNum++), info.isRepeat());
		addSingleCell(row.createCell(colNum++), info.isTrade());
		addSingleCell(row.createCell(colNum++), info.isEmbassyTrading());
		addSingleCell(row.createCell(colNum++), info.isFreeTradeAgreementTrading());
		addSingleCell(row.createCell(colNum++), info.isNonAggressionTrading());
		addSingleCell(row.createCell(colNum++), info.isDisable());
		addSingleCell(row.createCell(colNum++), info.isGoodyTech());
		addSingleCell(row.createCell(colNum++), info.isExtraWaterSeeFrom());
		addSingleCell(row.createCell(colNum++), info.isMapCentering());
		addSingleCell(row.createCell(colNum++), info.isMapVisible());
		addSingleCell(row.createCell(colNum++), info.isMapTrading());
		addSingleCell(row.createCell(colNum++), info.isTechTrading());
		addSingleCell(row.createCell(colNum++), info.isGoldTrading());
		addSingleCell(row.createCell(colNum++), info.isOpenBordersTrading());
		addSingleCell(row.createCell(colNum++), info.isLimitedBordersTrading());
		addSingleCell(row.createCell(colNum++), info.isDefensivePactTrading());
		addSingleCell(row.createCell(colNum++), info.isPermanentAllianceTrading());
		addSingleCell(row.createCell(colNum++), info.isVassalTrading());
		addSingleCell(row.createCell(colNum++), info.isBridgeBuilding());
		addSingleCell(row.createCell(colNum++), info.isIrrigation());
		addSingleCell(row.createCell(colNum++), info.isIgnoreIrrigation());
		addSingleCell(row.createCell(colNum++), info.isWaterWork());
		addSingleCell(row.createCell(colNum++), info.isCanPassPeaks());
		addSingleCell(row.createCell(colNum++), info.isMoveFastPeaks());
		addSingleCell(row.createCell(colNum++), info.isCanFoundOnPeaks());
		addSingleCell(row.createCell(colNum++), info.getGridX());
		addSingleCell(row.createCell(colNum++), info.getGridY());
		maxHeight = addRepeatingPairCell(row.createCell(colNum++), info.getDomainExtraMoves(), maxHeight);
		maxHeight = addRepeatingCell(row.createCell(colNum++), info.getCommerceModifiers(), maxHeight);
		maxHeight = addRepeatingCell(row.createCell(colNum++), info.getSpecialistExtraCommerces(), maxHeight);
		maxHeight = addRepeatingCell(row.createCell(colNum++), info.getCommerceFlexible(), maxHeight);
		maxHeight = addRepeatingCell(row.createCell(colNum++), info.getTerrainTrades(), maxHeight);
		addSingleCell(row.createCell(colNum++), info.isRiverTrade());
		addSingleCell(row.createCell(colNum++), info.isCaptureCities());
		addSingleCell(row.createCell(colNum++), info.isUnitRangeUnbound());
		addSingleCell(row.createCell(colNum++), info.isUnitTerritoryUnbound());
		addSingleCell(row.createCell(colNum++), info.getUnitRangeChange());
		addSingleCell(row.createCell(colNum++), info.getUnitRangeModifier());
		addSingleCell(row.createCell(colNum++), info.getCultureDefenceModifier());
		maxHeight = addRepeatingCell(row.createCell(colNum++), info.getForestPlotYieldChanges(), maxHeight);
		maxHeight = addRepeatingCell(row.createCell(colNum++), info.getRiverPlotYieldChanges(), maxHeight);
		maxHeight = addRepeatingCell(row.createCell(colNum++), info.getSeaPlotYieldChanges(), maxHeight);
		maxHeight = addRepeatingPairCell(row.createCell(colNum++), info.getWorldViewRevoltTurnChanges(), maxHeight);
		maxHeight = addRepeatingPairCell(row.createCell(colNum++), info.getFlavors(), maxHeight);
		maxHeight = addRepeatingCell(row.createCell(colNum++), info.getOrPreReqs(), maxHeight);
		maxHeight = addRepeatingCell(row.createCell(colNum++), info.getAndPreReqs(), maxHeight);
		maxHeight = addRepeatingCell(row.createCell(colNum++), info.getEnabledWorldViews(), maxHeight);
		addSingleCell(row.createCell(colNum++), info.getQuote());
		addSingleCell(row.createCell(colNum++), info.getSound());
		addSingleCell(row.createCell(colNum++), info.getSoundMP());
		addSingleCell(row.createCell(colNum++), info.getButton());

		row.setHeightInPoints(maxHeight * row.getSheet().getDefaultRowHeightInPoints());
	}

	private void createTechTreeSheet() {
		Sheet sheet = getSheet(ITechWorkbook.SHEETNAME_TREE);

		// Add the tech data
		Row row;
		Cell cell;
		int maxGridX = 0; // Used to autosize all the cols
		int maxGridY = 0; // Used to create all the rows at one go
		List<String> eras = new ArrayList<String>(); // Used to add background colour to cells based on era
		for (ITechInfo techInfo: infos.getInfos()) {
			// Get the row (iGridY value from tech)
			int gridY = techInfo.getGridY();
			if (gridY <= 0) {
				log.info("Ignoring tech " + techInfo.getType() + " invalid gridY value: " + gridY);
				continue;
			}
			if (gridY > maxGridY) maxGridY = gridY;
			row = sheet.getRow(gridY - 1);
			if (row == null)
				row = sheet.createRow(gridY - 1);

			// Get the col (iGridX from tech)
			int gridX = techInfo.getGridX();
			if (gridX <= 0) {
				log.info("Ignoring tech " + techInfo.getType() + " invalid gridX value: " + gridX);
				continue;
			}
			if (gridX > maxGridX) maxGridX = gridX;
			cell = row.createCell(getCellCol(gridX), CellType.STRING);
			cell.setCellValue(techInfo.getType());

			String era = techInfo.getEra();
			if (!eras.contains(era))
				eras.add(era);
			cell.setCellStyle(getStyle(eras.lastIndexOf(era)));

			setCellComment(cell, getCellMessage(techInfo));
		}

		// Autosize the columns
		int iNumCols = getCellCol(maxGridX) + 1;
		for (int i = 0; i < iNumCols; i++) {
			sheet.autoSizeColumn(i);
		}
		log.info("Wrote " +  infos.getInfos().size() + " tech tree infos to " + ITechWorkbook.SHEETNAME_TREE + " sheet.");
	}

	private int getCellCol(int gridX) {
		return (--gridX * 2);
	}

	@Override
	protected void preCreateCellStyles() {
		super.preCreateCellStyles();

		// Create the coloured backgrounds for the tech tree
		backgrounds = new HashMap<Integer, CellStyle>();
		CellStyle style = wb.createCellStyle();
		style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		style.setFillForegroundColor(IndexedColors.LIGHT_GREEN.getIndex());
		backgrounds.put(0, style);
		style = wb.createCellStyle();
		style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		style.setFillForegroundColor(IndexedColors.LIGHT_BLUE.getIndex());
		backgrounds.put(1, style);
		style = wb.createCellStyle();
		style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		style.setFillForegroundColor(IndexedColors.LIGHT_YELLOW.getIndex());
		backgrounds.put(2, style);
		style = wb.createCellStyle();
		style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		style.setFillForegroundColor(IndexedColors.LIGHT_ORANGE.getIndex());
		backgrounds.put(3, style);
		style = wb.createCellStyle();
		style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		style.setFillForegroundColor(IndexedColors.LIGHT_CORNFLOWER_BLUE.getIndex());
		backgrounds.put(4, style);
		style = wb.createCellStyle();
		style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		style.setFillForegroundColor(IndexedColors.LIGHT_TURQUOISE.getIndex());
		backgrounds.put(5, style);
		style = wb.createCellStyle();
		style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		style.setFillForegroundColor(IndexedColors.CORAL.getIndex());
		backgrounds.put(6, style);
		style = wb.createCellStyle();
		style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		style.setFillForegroundColor(IndexedColors.ROSE.getIndex());
		backgrounds.put(7, style);
		style = wb.createCellStyle();
		style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		style.setFillForegroundColor(IndexedColors.AQUA.getIndex());
		backgrounds.put(8, style);
		style = wb.createCellStyle();
		style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		style.setFillForegroundColor(IndexedColors.LAVENDER.getIndex());
		backgrounds.put(9, style);
		style = wb.createCellStyle();
		style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		style.setFillForegroundColor(IndexedColors.TEAL.getIndex());
		backgrounds.put(10, style);
	}

	private CellStyle getStyle(int index) {
		if (index == -1)
			index = 0;

		if (!backgrounds.containsKey(index))
			index = index % backgrounds.size();

		return backgrounds.get(index);
	}

	private String getCellMessage(ITechInfo info) {
		StringBuilder message = new StringBuilder("iGridX: " + info.getGridX());
		message.append("\niGridY: " + info.getGridY());
		message.append("\niCost: " + info.getCost());
		message.append("\nEra: " + info.getEra());
		if (!info.getOrPreReqs().isEmpty()) {
			message.append("\nOrTechPreReqs:");
			for (String prereq: info.getOrPreReqs()) {
				message.append("\n  " + prereq);
			}
		}
		if (!info.getAndPreReqs().isEmpty()) {
			message.append("\nAndTechPreReqs:");
			for (String prereq: info.getAndPreReqs()) {
				message.append("\n  " + prereq);
			}
		}
		return message.toString();
	}
}