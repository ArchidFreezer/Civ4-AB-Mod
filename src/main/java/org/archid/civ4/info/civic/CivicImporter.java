package org.archid.civ4.info.civic;

import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.archid.civ4.info.AbstractImporter;
import org.archid.civ4.info.EInfo;
import org.archid.civ4.info.IInfoWorkbook;
import org.archid.civ4.info.IInfos;
import org.archid.civ4.info.civic.ImprovementYieldChanges.ImprovementYieldChange;
import org.archid.utils.StringUtils;

public class CivicImporter extends AbstractImporter<IInfos<ICivicInfo>, ICivicInfo> {

	/** Logging facility */
	static Logger log = Logger.getLogger(CivicImporter.class.getName());

	public CivicImporter(EInfo infoEnum) {
		super(infoEnum, new CivicInfoXmlFormatter());
	}

	@Override
	public String getListSheetName() {
		return ICivicWorkbook.SHEETNAME_LIST;
	}

	@Override
	protected ICivicInfo parseRow(Row row) {
		int colNum = 0;
		String type = row.getCell(1).getStringCellValue();
		// Handle cells that have been moved
		if (type.isEmpty())
			return null;

		ICivicInfo info = CivicInfos.createInfo(type);
		parseCell(row.getCell(colNum++), String.class, info::setCivicOptionType);
		parseCell(row.getCell(colNum++), String.class, info::setType);
		parseCell(row.getCell(colNum++), String.class, info::setDescription);
		parseCell(row.getCell(colNum++), String.class, info::setCivilopedia);
		parseCell(row.getCell(colNum++), String.class, info::setStrategy);
		parseCell(row.getCell(colNum++), String.class, info::setHelp);
		parseCell(row.getCell(colNum++), String.class, info::setButton);
		parseCell(row.getCell(colNum++), Integer.class, info::setIndex);
		parseCell(row.getCell(colNum++), String.class, info::setTechPrereq);
		parseCell(row.getCell(colNum++), Boolean.class, info::setCreateBarbarians);
		parseCell(row.getCell(colNum++), Boolean.class, info::setEnableStarSigns);
		parseCell(row.getCell(colNum++), Integer.class, info::setStarSignMitigateChangePercent);
		parseCell(row.getCell(colNum++), Integer.class, info::setStarSignScaleChangePercent);
		parseCell(row.getCell(colNum++), Integer.class, info::setAnarchyLength);
		parseCell(row.getCell(colNum++), String.class, info::setUpkeep);
		parseCell(row.getCell(colNum++), Integer.class, info::setAIWeight);
		parseCell(row.getCell(colNum++), Integer.class, info::setGreatPeopleRateModifier);
		parseCell(row.getCell(colNum++), Integer.class, info::setGreatGeneralRateModifier);
		parseCell(row.getCell(colNum++), Integer.class, info::setDomesticGreatGeneralRateModifier);
		parseCell(row.getCell(colNum++), Integer.class, info::setStateReligionGreatPeopleRateModifier);
		parseCell(row.getCell(colNum++), Integer.class, info::setDistanceMaintenanceModifier);
		parseCell(row.getCell(colNum++), Integer.class, info::setNumCitiesMaintenanceModifier);
		parseCell(row.getCell(colNum++), Integer.class, info::setCorporationMaintenanceModifier);
		parseCell(row.getCell(colNum++), Integer.class, info::setForeignTradeRouteModifier);
		parseCell(row.getCell(colNum++), Integer.class, info::setExtraHealth);
		parseCell(row.getCell(colNum++), Integer.class, info::setExtraHappiness);
		parseCell(row.getCell(colNum++), Integer.class, info::setFreeExperience);
		parseCell(row.getCell(colNum++), Integer.class, info::setWorkerSpeedModifier);
		parseCell(row.getCell(colNum++), Integer.class, info::setImprovementUpgradeRateModifier);
		parseCell(row.getCell(colNum++), Integer.class, info::setMilitaryProductionModifier);
		parseCell(row.getCell(colNum++), Integer.class, info::setBaseFreeUnits);
		parseCell(row.getCell(colNum++), Integer.class, info::setBaseFreeMilitaryUnits);
		parseCell(row.getCell(colNum++), Integer.class, info::setFreeUnitsPopulationPercent);
		parseCell(row.getCell(colNum++), Integer.class, info::setFreeMilitaryUnitsPopulationPercent);
		parseCell(row.getCell(colNum++), Integer.class, info::setGoldPerUnit);
		parseCell(row.getCell(colNum++), Integer.class, info::setGoldPerMilitaryUnit);
		parseCell(row.getCell(colNum++), Integer.class, info::setHappyPerMilitaryUnit);
		parseCell(row.getCell(colNum++), Boolean.class, info::setMilitaryFoodProduction);
		parseCell(row.getCell(colNum++), Boolean.class, info::setTribalConscription);
		parseCell(row.getCell(colNum++), Integer.class, info::setMaxConscript);
		parseCell(row.getCell(colNum++), Integer.class, info::setUnhealthyPopulationModifier);
		parseCell(row.getCell(colNum++), Integer.class, info::setExpInBorderModifier);
		parseCell(row.getCell(colNum++), Boolean.class, info::setBuildingOnlyHealthy);
		parseCell(row.getCell(colNum++), Integer.class, info::setLargestCityHappiness);
		parseCell(row.getCell(colNum++), Boolean.class, info::setNoCapitalUnhappiness);
		parseCell(row.getCell(colNum++), Integer.class, info::setTaxRateAngerModifier);
		parseCell(row.getCell(colNum++), Integer.class, info::setDistantUnitSupplyCostModifier);
		parseCell(row.getCell(colNum++), Integer.class, info::setWarWearinessModifier);
		parseCell(row.getCell(colNum++), Integer.class, info::setFreeSpecialist);
		parseCell(row.getCell(colNum++), Integer.class, info::setTradeRoutes);
		parseCell(row.getCell(colNum++), Boolean.class, info::setNoForeignTrade);
		parseCell(row.getCell(colNum++), Boolean.class, info::setNoCorporations);
		parseCell(row.getCell(colNum++), Boolean.class, info::setNoForeignCorporations);
		parseCell(row.getCell(colNum++), Integer.class, info::setCivicPercentAnger);
		parseCell(row.getCell(colNum++), Boolean.class, info::setStateReligion);
		parseCell(row.getCell(colNum++), Boolean.class, info::setNoNonStateReligionSpread);
		parseCell(row.getCell(colNum++), Integer.class, info::setStateReligionHappiness);
		parseCell(row.getCell(colNum++), Integer.class, info::setNonStateReligionHappiness);
		parseCell(row.getCell(colNum++), Integer.class, info::setStateReligionUnitProductionModifier);
		parseCell(row.getCell(colNum++), Integer.class, info::setStateReligionBuildingProductionModifier);
		parseCell(row.getCell(colNum++), Integer.class, info::setStateReligionFreeExperience);
		parseCell(row.getCell(colNum++), Boolean.class, info::setUnitRangeUnbound);
		parseCell(row.getCell(colNum++), Boolean.class, info::setUnitTerritoryUnbound);
		parseCell(row.getCell(colNum++), Integer.class, info::setUnitRangeChange);
		parseCell(row.getCell(colNum++), Integer.class, info::setUnitRangeModifier);
		parseCell(row.getCell(colNum++), Integer.class, info::setCultureDefenceChange);
		parseCell(row.getCell(colNum++), Integer.class, info::setPopulationGrowthRateModifier);
		parseListCell(row.getCell(colNum++), Integer.class, info::addYieldModifier);
		parseListCell(row.getCell(colNum++), Integer.class, info::addCapitalYieldModifier);
		parseListCell(row.getCell(colNum++), Integer.class, info::addTradeYieldModifier);
		parseListCell(row.getCell(colNum++), Integer.class, info::addCommerceModifier);
		parseListCell(row.getCell(colNum++), Integer.class, info::addCapitalCommerceModifier);
		parseListCell(row.getCell(colNum++), Integer.class, info::addSpecialistCommerceChange);
		parseListCell(row.getCell(colNum++), String.class, info::addHurry);
		parseListCell(row.getCell(colNum++), String.class, info::addSpecialBuildingNotRequired);
		parseListCell(row.getCell(colNum++), String.class, info::addSpecialistValid);
		parsePairsCell(row.getCell(colNum++), String.class, Integer.class, info::addFreeSpecialistCount);
		parsePairsCell(row.getCell(colNum++), String.class, Integer.class, info::addBuildingClassProductionModifier);
		parsePairsCell(row.getCell(colNum++), String.class, Integer.class, info::addBuildingHappinessChange);
		parsePairsCell(row.getCell(colNum++), String.class, Integer.class, info::addBuildingHealthChange);
		parsePairsCell(row.getCell(colNum++), String.class, Integer.class, info::addFeatureHappinessChange);
		parseImprovementYieldChangeCell(row.getCell(colNum++), info);
		parseCell(row.getCell(colNum++), Boolean.class, info::setUpgradeAnywhere);
		parseCell(row.getCell(colNum++), String.class, info::setWeLoveTheKing);
		parseCell(row.getCell(colNum++), Integer.class, info::setCityDefenceModifier);

		return info;
	}

	private void parseImprovementYieldChangeCell(Cell cell, ICivicInfo info) {
		String[] arr = cell.getStringCellValue().split(IInfoWorkbook.CELL_NEWLINE);
		if (arr.length > 1) {
			boolean first = true;
			String resource = null;
			List<Integer> list = null;
			for (String str: arr) {
				if (StringUtils.hasCharacters(str)) {
					if (first) {
						list = new ArrayList<Integer>();
						resource = getVal(str, String.class);
						first = false;
					} else if (str.equals("-")) {
						ImprovementYieldChange wrapper = new ImprovementYieldChange();
						wrapper.setResource(resource);
						for (Integer element: list) {
							wrapper.addElement(element);
						}
						info.getImprovementYieldChanges().getImprovementYieldChangeList().add(wrapper);
						first = !first;
					} else {
						list.add(Integer.valueOf(str));
					}
				}
			}
		}
	}
}