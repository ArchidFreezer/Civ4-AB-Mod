package org.archid.civ4.info.civic;

import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.archid.civ4.info.AbstractExporter;
import org.archid.civ4.info.EInfo;
import org.archid.civ4.info.IInfoWorkbook;
import org.archid.civ4.info.IInfos;
import org.archid.civ4.info.civic.ICivicWorkbook.SheetHeaders;
import org.archid.civ4.info.civic.ImprovementYieldChanges.ImprovementYieldChange;

public class CivicExporter extends AbstractExporter<IInfos<ICivicInfo>, ICivicInfo> {

	/** Logging facility */
	static Logger log = Logger.getLogger(CivicExporter.class.getName());

	public CivicExporter(EInfo infoEnum) {
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
		return ICivicWorkbook.SheetHeaders.values().length;
	}

	@Override
	protected String getInfoListSheetName() {
		return ICivicWorkbook.SHEETNAME_LIST;
	}

	@Override
	protected void populateRow(Row row, ICivicInfo info) {
		int maxHeight = 1;
		int colNum = 0;
		addSingleCell(row.createCell(colNum++), info.getCivicOptionType());
		addSingleCell(row.createCell(colNum++), info.getType());
		addSingleCell(row.createCell(colNum++), info.getDescription());
		addSingleCell(row.createCell(colNum++), info.getCivilopedia());
		addSingleCell(row.createCell(colNum++), info.getStrategy());
		addSingleCell(row.createCell(colNum++), info.getHelp());
		addSingleCell(row.createCell(colNum++), info.getButton());
		addSingleCell(row.createCell(colNum++), info.getIndex());
		addSingleCell(row.createCell(colNum++), info.getTechPrereq());
		addSingleCell(row.createCell(colNum++), info.isCreateBarbarians());
		addSingleCell(row.createCell(colNum++), info.isEnableStarSigns());
		addSingleCell(row.createCell(colNum++), info.getStarSignMitigateChangePercent());
		addSingleCell(row.createCell(colNum++), info.getStarSignScaleChangePercent());
		addSingleCell(row.createCell(colNum++), info.getAnarchyLength());
		addSingleCell(row.createCell(colNum++), info.getUpkeep());
		addSingleCell(row.createCell(colNum++), info.getAIWeight());
		addSingleCell(row.createCell(colNum++), info.getGreatPeopleRateModifier());
		addSingleCell(row.createCell(colNum++), info.getGreatGeneralRateModifier());
		addSingleCell(row.createCell(colNum++), info.getDomesticGreatGeneralRateModifier());
		addSingleCell(row.createCell(colNum++), info.getStateReligionGreatPeopleRateModifier());
		addSingleCell(row.createCell(colNum++), info.getDistanceMaintenanceModifier());
		addSingleCell(row.createCell(colNum++), info.getNumCitiesMaintenanceModifier());
		addSingleCell(row.createCell(colNum++), info.getCorporationMaintenanceModifier());
		addSingleCell(row.createCell(colNum++), info.getForeignTradeRouteModifier());
		addSingleCell(row.createCell(colNum++), info.getExtraHealth());
		addSingleCell(row.createCell(colNum++), info.getExtraHappiness());
		addSingleCell(row.createCell(colNum++), info.getFreeExperience());
		addSingleCell(row.createCell(colNum++), info.getWorkerSpeedModifier());
		addSingleCell(row.createCell(colNum++), info.getImprovementUpgradeRateModifier());
		addSingleCell(row.createCell(colNum++), info.getMilitaryProductionModifier());
		addSingleCell(row.createCell(colNum++), info.getBaseFreeUnits());
		addSingleCell(row.createCell(colNum++), info.getBaseFreeMilitaryUnits());
		addSingleCell(row.createCell(colNum++), info.getFreeUnitsPopulationPercent());
		addSingleCell(row.createCell(colNum++), info.getFreeMilitaryUnitsPopulationPercent());
		addSingleCell(row.createCell(colNum++), info.getGoldPerUnit());
		addSingleCell(row.createCell(colNum++), info.getGoldPerMilitaryUnit());
		addSingleCell(row.createCell(colNum++), info.getHappyPerMilitaryUnit());
		addSingleCell(row.createCell(colNum++), info.isMilitaryFoodProduction());
		addSingleCell(row.createCell(colNum++), info.isTribalConscription());
		addSingleCell(row.createCell(colNum++), info.getMaxConscript());
		addSingleCell(row.createCell(colNum++), info.getUnhealthyPopulationModifier());
		addSingleCell(row.createCell(colNum++), info.getExpInBorderModifier());
		addSingleCell(row.createCell(colNum++), info.isBuildingOnlyHealthy());
		addSingleCell(row.createCell(colNum++), info.getLargestCityHappiness());
		addSingleCell(row.createCell(colNum++), info.isNoCapitalUnhappiness());
		addSingleCell(row.createCell(colNum++), info.getTaxRateAngerModifier());
		addSingleCell(row.createCell(colNum++), info.getDistantUnitSupplyCostModifier());
		addSingleCell(row.createCell(colNum++), info.getWarWearinessModifier());
		addSingleCell(row.createCell(colNum++), info.getFreeSpecialist());
		addSingleCell(row.createCell(colNum++), info.getTradeRoutes());
		addSingleCell(row.createCell(colNum++), info.isNoForeignTrade());
		addSingleCell(row.createCell(colNum++), info.isNoCorporations());
		addSingleCell(row.createCell(colNum++), info.isNoForeignCorporations());
		addSingleCell(row.createCell(colNum++), info.getCivicPercentAnger());
		addSingleCell(row.createCell(colNum++), info.isStateReligion());
		addSingleCell(row.createCell(colNum++), info.isNoNonStateReligionSpread());
		addSingleCell(row.createCell(colNum++), info.getStateReligionHappiness());
		addSingleCell(row.createCell(colNum++), info.getNonStateReligionHappiness());
		addSingleCell(row.createCell(colNum++), info.getStateReligionUnitProductionModifier());
		addSingleCell(row.createCell(colNum++), info.getStateReligionBuildingProductionModifier());
		addSingleCell(row.createCell(colNum++), info.getStateReligionFreeExperience());
		addSingleCell(row.createCell(colNum++), info.isUnitRangeUnbound());
		addSingleCell(row.createCell(colNum++), info.isUnitTerritoryUnbound());
		addSingleCell(row.createCell(colNum++), info.getUnitRangeChange());
		addSingleCell(row.createCell(colNum++), info.getUnitRangeModifier());
		addSingleCell(row.createCell(colNum++), info.getCultureDefenceChange());
		addSingleCell(row.createCell(colNum++), info.getPopulationGrowthRateModifier());
		maxHeight = addRepeatingCell(row.createCell(colNum++), info.getYieldModifiers(), maxHeight);
		maxHeight = addRepeatingCell(row.createCell(colNum++), info.getCapitalYieldModifiers(), maxHeight);
		maxHeight = addRepeatingCell(row.createCell(colNum++), info.getTradeYieldModifiers(), maxHeight);
		maxHeight = addRepeatingCell(row.createCell(colNum++), info.getCommerceModifiers(), maxHeight);
		maxHeight = addRepeatingCell(row.createCell(colNum++), info.getCapitalCommerceModifiers(), maxHeight);
		maxHeight = addRepeatingCell(row.createCell(colNum++), info.getSpecialistCommerceChanges(), maxHeight);
		maxHeight = addRepeatingCell(row.createCell(colNum++), info.getHurrys(), maxHeight);
		maxHeight = addRepeatingCell(row.createCell(colNum++), info.getSpecialBuildingNotRequireds(), maxHeight);
		maxHeight = addRepeatingCell(row.createCell(colNum++), info.getSpecialistValids(), maxHeight);
		maxHeight = addRepeatingPairCell(row.createCell(colNum++), info.getFreeSpecialistCounts(), maxHeight);
		maxHeight = addRepeatingPairCell(row.createCell(colNum++), info.getBuildingClassProductionModifiers(), maxHeight);
		maxHeight = addRepeatingPairCell(row.createCell(colNum++), info.getBuildingHappinessChanges(), maxHeight);
		maxHeight = addRepeatingPairCell(row.createCell(colNum++), info.getBuildingHealthChanges(), maxHeight);
		maxHeight = addRepeatingPairCell(row.createCell(colNum++), info.getFeatureHappinessChanges(), maxHeight);
		maxHeight = addImprovementYieldChangeCell(row.createCell(colNum++), info.getImprovementYieldChanges(), maxHeight);
		addSingleCell(row.createCell(colNum++), info.isUpgradeAnywhere());
		addSingleCell(row.createCell(colNum++), info.getWeLoveTheKing());
		addSingleCell(row.createCell(colNum++), info.getCityDefenceModifier());

		row.setHeightInPoints(maxHeight * row.getSheet().getDefaultRowHeightInPoints());
	}

	private int addImprovementYieldChangeCell(Cell cell, ImprovementYieldChanges list, int maxHeight) {
		int currHeight = 0;
		cell.setCellStyle(csWrap);
		StringBuilder cellvalue = new StringBuilder();
		if (list != null) {
			for (ImprovementYieldChange wrapper: list.getImprovementYieldChangeList()) {
				if (currHeight > 0) cellvalue.append(IInfoWorkbook.CELL_NEWLINE);
				cellvalue.append(wrapper.getResource() + IInfoWorkbook.CELL_NEWLINE);
				for (Integer element: wrapper.getElements()) {
					cellvalue.append(element + IInfoWorkbook.CELL_NEWLINE);
					currHeight ++;
				}
				cellvalue.append("-");
				currHeight += 2;
			}
		}
		cell.setCellValue(cellvalue.toString());
		if (currHeight > maxHeight) maxHeight = currHeight;
		return maxHeight;
	}

}