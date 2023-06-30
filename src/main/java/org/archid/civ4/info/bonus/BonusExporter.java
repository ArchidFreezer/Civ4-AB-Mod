package org.archid.civ4.info.bonus;

import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.archid.civ4.info.AbstractExporter;
import org.archid.civ4.info.EInfo;
import org.archid.civ4.info.IInfoWorkbook;
import org.archid.civ4.info.IInfos;
import org.archid.civ4.info.bonus.IBonusWorkbook.SheetHeaders;

public class BonusExporter extends AbstractExporter<IInfos<IBonusInfo>, IBonusInfo> {

	/** Logging facility */
	static Logger log = Logger.getLogger(BonusExporter.class.getName());

	public BonusExporter(EInfo infoEnum) {
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
		return IBonusWorkbook.SheetHeaders.values().length;
	}

	@Override
	protected String getInfoListSheetName() {
		return IBonusWorkbook.SHEETNAME_LIST;
	}

	@Override
	protected void populateRow(Row row, IBonusInfo info) {
		int maxHeight = 1;
		int colNum = 0;
		addSingleCell(row.createCell(colNum++), info.getType());
		addSingleCell(row.createCell(colNum++), info.getDescription());
		addSingleCell(row.createCell(colNum++), info.getCivilopedia());
		addSingleCell(row.createCell(colNum++), info.getHelp());
		addSingleCell(row.createCell(colNum++), info.getBonusClassType());
		addSingleCell(row.createCell(colNum++), info.getArtDefineTag());
		addSingleCell(row.createCell(colNum++), info.getTechReveal());
		addSingleCell(row.createCell(colNum++), info.getTechCityTrade());
		addSingleCell(row.createCell(colNum++), info.getTechObsolete());
		maxHeight = addRepeatingCell(row.createCell(colNum++), info.getYieldChanges(), maxHeight);
		addSingleCell(row.createCell(colNum++), info.getAITradeModifier());
		addSingleCell(row.createCell(colNum++), info.getAIObjective());
		addSingleCell(row.createCell(colNum++), info.getHealth());
		addSingleCell(row.createCell(colNum++), info.getHappiness());
		addSingleCell(row.createCell(colNum++), info.getPlacementOrder());
		addSingleCell(row.createCell(colNum++), info.getConstAppearance());
		addSingleCell(row.createCell(colNum++), info.getMinAreaSize());
		addSingleCell(row.createCell(colNum++), info.getMinLatitude());
		addSingleCell(row.createCell(colNum++), info.getMaxLatitude());
		maxHeight = addRandsCell(row.createCell(colNum++), info.getRands(), maxHeight);
		addSingleCell(row.createCell(colNum++), info.getPlayer());
		addSingleCell(row.createCell(colNum++), info.getTilesPer());
		addSingleCell(row.createCell(colNum++), info.getMinLandPercent());
		addSingleCell(row.createCell(colNum++), info.getUnique());
		addSingleCell(row.createCell(colNum++), info.getGroupRange());
		addSingleCell(row.createCell(colNum++), info.getGroupRand());
		addSingleCell(row.createCell(colNum++), info.isArea());
		addSingleCell(row.createCell(colNum++), info.isHills());
		addSingleCell(row.createCell(colNum++), info.isPeaks());
		addSingleCell(row.createCell(colNum++), info.isFlatlands());
		addSingleCell(row.createCell(colNum++), info.isNoRiverSide());
		addSingleCell(row.createCell(colNum++), info.isNormalize());
		maxHeight = addRepeatingCell(row.createCell(colNum++), info.getTerrainBooleans(), maxHeight);
		maxHeight = addRepeatingCell(row.createCell(colNum++), info.getFeatureBooleans(), maxHeight);
		maxHeight = addRepeatingCell(row.createCell(colNum++), info.getFeatureTerrainBooleans(), maxHeight);
		addSingleCell(row.createCell(colNum++), info.isUseLSystem());

		row.setHeightInPoints(maxHeight * row.getSheet().getDefaultRowHeightInPoints());
	}

	private int addRandsCell(Cell cell, Rands rands, int maxHeight) {
		if (rands == null) return maxHeight;
		cell.setCellStyle(csWrap);
		StringBuilder sb = new StringBuilder();
		sb.append(rands.getRandApp1());
		sb.append(IInfoWorkbook.CELL_NEWLINE + rands.getRandApp2());
		sb.append(IInfoWorkbook.CELL_NEWLINE + rands.getRandApp3());
		sb.append(IInfoWorkbook.CELL_NEWLINE + rands.getRandApp4());
		cell.setCellValue(sb.toString());
		return (maxHeight > 4) ? maxHeight : 4;
	}
}