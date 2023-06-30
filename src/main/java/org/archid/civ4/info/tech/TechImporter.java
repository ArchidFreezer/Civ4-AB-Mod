package org.archid.civ4.info.tech;

import java.util.Iterator;
import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.archid.civ4.info.AbstractImporter;
import org.archid.civ4.info.DefaultXmlFormatter;
import org.archid.civ4.info.EInfo;
import org.archid.civ4.info.IInfos;

public class TechImporter extends AbstractImporter<IInfos<ITechInfo>, ITechInfo> {

	/** Logging facility */
	static Logger log = Logger.getLogger(TechImporter.class.getName());
	
	private static String DUMMY_TECH_TYPE = "TECH_SPECIAL_PROMOTION";

	public TechImporter(EInfo infoEnum) {
		super(infoEnum, new DefaultXmlFormatter("tech"));
	}

	@Override
	public String getListSheetName() {
		return ITechWorkbook.SHEETNAME_LIST;
	}

	/**
	 * Overrides the the method from {@link AbstractImporter} to allow the {@code <TechInfo>} objects to be defined
	 * by the tech tree sheet rather than the list sheet. A tech will be created for each one defined in the tech tree
	 * sheet which will provide the {@code <Type>}, {@code <iGridX>} and {@code <iGridY>} tag values. All other values
	 * are then populated from the list sheet.
	 * 
	 * @param wb {@link Workbook} containing the tech tree and list sheets
	 */
	@Override
	protected boolean parseXlsx(Workbook wb) {

		// Get the iGridX and iGridY values from the tech tree
		Sheet sheet = wb.getSheet(ITechWorkbook.SHEETNAME_TREE);
		if (sheet == null) {
			log.warn("Sheet " + ITechWorkbook.SHEETNAME_TREE + " does not exist in the workbook");
			return false;
		}

		Iterator<Row> itRow = sheet.rowIterator();
		while (itRow.hasNext()) {
			Row row = itRow.next();
			Iterator<Cell> itCell = row.cellIterator();
			while (itCell.hasNext()) {
				Cell cell = itCell.next();
				String type = cell.getStringCellValue();
				// Handle cells that have been moved
				if (type.isEmpty())
					continue;
				ITechInfo info = TechInfos.createInfo(type);
				Integer gridX = getGridXFromCell(cell);
				info.setGridX(gridX);
				Integer gridY = cell.getRowIndex() + 1;
				info.setGridY(gridY);
				infos.addInfo(info);
			}
		}
		// We also need to handle the dummy tech used for manually added promotions as it doesn't appear on the tree
		ITechInfo dummyInfo = TechInfos.createInfo(DUMMY_TECH_TYPE);
		dummyInfo.setGridX(-1);
		dummyInfo.setGridY(-1);
		infos.addInfo(dummyInfo);

		// Get the rest of the values from the tech tree
		sheet = wb.getSheet(getListSheetName());
		if (sheet == null) {
			log.warn("Sheet " + getListSheetName() + " does not exist in the workbook");
			return false;
		}

		itRow = sheet.rowIterator();
		while (itRow.hasNext()) {
			Row row = itRow.next();
			int colNum = 0;

			String type = row.getCell(0).getStringCellValue();
			log.debug("Processing: " + type);
			// Handle cells that have been moved
			if (type.isEmpty())
				continue;
			ITechInfo info = infos.getInfo(type);
			// Handle deleted techs
			if (info == null)
				continue;
			parseCell(row.getCell(colNum++), String.class, info::setType);
			parseCell(row.getCell(colNum++), String.class, info::setDescription);
			parseCell(row.getCell(colNum++), String.class, info::setCivilopedia);
			parseCell(row.getCell(colNum++), String.class, info::setHelp);
			parseCell(row.getCell(colNum++), String.class, info::setStrategy);
			parseCell(row.getCell(colNum++), String.class, info::setAdvisor);
			parseCell(row.getCell(colNum++), Integer.class, info::setAIWeight);
			parseCell(row.getCell(colNum++), Integer.class, info::setAITradeModifier);
			parseCell(row.getCell(colNum++), Integer.class, info::setCost);
			parseCell(row.getCell(colNum++), Integer.class, info::setAdvancedStartCost);
			parseCell(row.getCell(colNum++), Integer.class, info::setAdvancedStartCostIncrease);
			parseCell(row.getCell(colNum++), String.class, info::setEra);
			parseCell(row.getCell(colNum++), Boolean.class, info::setCivSettled);
			parseCell(row.getCell(colNum++), String.class, info::setFreeCapitalBuildingClass);
			parseCell(row.getCell(colNum++), String.class, info::setFirstFreeUnitClass);
			parseCell(row.getCell(colNum++), String.class, info::setFreeUnitClass);
			parseCell(row.getCell(colNum++), Integer.class, info::setFeatureProductionModifier);
			parseCell(row.getCell(colNum++), Integer.class, info::setWorkerSpeedModifier);
			parseCell(row.getCell(colNum++), Integer.class, info::setTradeRoutes);
			parseCell(row.getCell(colNum++), Integer.class, info::setHealth);
			parseCell(row.getCell(colNum++), Integer.class, info::setHappiness);
			parseCell(row.getCell(colNum++), Integer.class, info::setFirstFreeTechs);
			parseCell(row.getCell(colNum++), Integer.class, info::setAsset);
			parseCell(row.getCell(colNum++), Integer.class, info::setPower);
			parseCell(row.getCell(colNum++), Boolean.class, info::setRepeat);
			parseCell(row.getCell(colNum++), Boolean.class, info::setTrade);
			parseCell(row.getCell(colNum++), Boolean.class, info::setEmbassyTrading);
			parseCell(row.getCell(colNum++), Boolean.class, info::setFreeTradeAgreementTrading);
			parseCell(row.getCell(colNum++), Boolean.class, info::setNonAggressionTrading);
			parseCell(row.getCell(colNum++), Boolean.class, info::setDisable);
			parseCell(row.getCell(colNum++), Boolean.class, info::setGoodyTech);
			parseCell(row.getCell(colNum++), Boolean.class, info::setExtraWaterSeeFrom);
			parseCell(row.getCell(colNum++), Boolean.class, info::setMapCentering);
			parseCell(row.getCell(colNum++), Boolean.class, info::setMapVisible);
			parseCell(row.getCell(colNum++), Boolean.class, info::setMapTrading);
			parseCell(row.getCell(colNum++), Boolean.class, info::setTechTrading);
			parseCell(row.getCell(colNum++), Boolean.class, info::setGoldTrading);
			parseCell(row.getCell(colNum++), Boolean.class, info::setOpenBordersTrading);
			parseCell(row.getCell(colNum++), Boolean.class, info::setLimitedBordersTrading);
			parseCell(row.getCell(colNum++), Boolean.class, info::setDefensivePactTrading);
			parseCell(row.getCell(colNum++), Boolean.class, info::setPermanentAllianceTrading);
			parseCell(row.getCell(colNum++), Boolean.class, info::setVassalTrading);
			parseCell(row.getCell(colNum++), Boolean.class, info::setBridgeBuilding);
			parseCell(row.getCell(colNum++), Boolean.class, info::setIrrigation);
			parseCell(row.getCell(colNum++), Boolean.class, info::setIgnoreIrrigation);
			parseCell(row.getCell(colNum++), Boolean.class, info::setWaterWork);
			parseCell(row.getCell(colNum++), Boolean.class, info::setCanPassPeaks);
			parseCell(row.getCell(colNum++), Boolean.class, info::setMoveFastPeaks);
			parseCell(row.getCell(colNum++), Boolean.class, info::setCanFoundOnPeaks);
			colNum++; // skip the iGridX col
			colNum++; // skip the iGridY col
			parsePairsCell(row.getCell(colNum++), String.class, Integer.class, info::addDomainExtraMove);
			parseListCell(row.getCell(colNum++), Integer.class, info::addCommerceModifier);
			parseListCell(row.getCell(colNum++), Integer.class, info::addSpecialistExtraCommerce);
			parseListCell(row.getCell(colNum++), Boolean.class, info::addCommerceFlexible);
			parseListCell(row.getCell(colNum++), String.class, info::addTerrainTrade);
			parseCell(row.getCell(colNum++), Boolean.class, info::setRiverTrade);
			parseCell(row.getCell(colNum++), Boolean.class, info::setCaptureCities);
			parseCell(row.getCell(colNum++), Boolean.class, info::setUnitRangeUnbound);
			parseCell(row.getCell(colNum++), Boolean.class, info::setUnitTerritoryUnbound);
			parseCell(row.getCell(colNum++), Integer.class, info::setUnitRangeChange);
			parseCell(row.getCell(colNum++), Integer.class, info::setUnitRangeModifier);
			parseCell(row.getCell(colNum++), Integer.class, info::setCultureDefenceModifier);
			parseListCell(row.getCell(colNum++), Integer.class, info::addForestPlotYieldChange);
			parseListCell(row.getCell(colNum++), Integer.class, info::addRiverPlotYieldChange);
			parseListCell(row.getCell(colNum++), Integer.class, info::addSeaPlotYieldChange);
			parsePairsCell(row.getCell(colNum++), String.class, Integer.class, info::addWorldViewRevoltTurnChange);
			parsePairsCell(row.getCell(colNum++), String.class, Integer.class, info::addFlavor);
			parseListCell(row.getCell(colNum++), String.class, info::addOrPreReq);
			parseListCell(row.getCell(colNum++), String.class, info::addAndPreReq);
			parseListCell(row.getCell(colNum++), String.class, info::addEnabledWorldView);
			parseCell(row.getCell(colNum++), String.class, info::setQuote);
			parseCell(row.getCell(colNum++), String.class, info::setSound);
			parseCell(row.getCell(colNum++), String.class, info::setSoundMP);
			parseCell(row.getCell(colNum++), String.class, info::setButton);
		}

		return true;
	}

	private Integer getGridXFromCell(Cell cell) {
		Integer gridX = 0;
		Integer colIndex = cell.getColumnIndex();

		if (colIndex > 0) {
			gridX = colIndex / 2;
		}

		return ++gridX;
	}

	/**
	 * This method is not used in this class as the {@link AbstractImporter#parseXlsx(Workbook)} method is overriden  
	 */
	@Override
	protected ITechInfo parseRow(Row row) {
		return null;
	}
}