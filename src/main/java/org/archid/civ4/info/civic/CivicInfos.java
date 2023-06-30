package org.archid.civ4.info.civic;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import org.archid.civ4.info.AbstractInfos;
import org.archid.utils.IPair;

@XmlRootElement(name="Civ4CivicInfos", namespace="x-schema:CIV4GameInfoSchema.xml")
@XmlAccessorType(XmlAccessType.NONE)
public class CivicInfos extends AbstractInfos<ICivicInfo> {

	public CivicInfos() {
		super(new LinkedHashMap<String, ICivicInfo>());
	}

	@XmlJavaTypeAdapter(CivicMapAdapter.class)
	@XmlElement(name="CivicInfos")
	public Map<String, ICivicInfo> getInfoMap() {
		return infos;
	}

	public static ICivicInfo createInfo(String type) {
		return new CivicInfo(type);
	}

	@Override
	public void setInfoMap(Map<String, ICivicInfo> infos) {
		this.infos = infos;
	}

	private static class CivicInfo implements ICivicInfo{

		private String civicOptionType;
		private String type;
		private String description;
		private String civilopedia;
		private String strategy;
		private String help;
		private String button;
		private Integer index;
		private String techPrereq;
		private Boolean createBarbarians;
		private Boolean enableStarSigns;
		private Integer starSignMitigateChangePercent;
		private Integer starSignScaleChangePercent;
		private Integer anarchyLength;
		private String upkeep;
		private Integer aIWeight;
		private Integer greatPeopleRateModifier;
		private Integer greatGeneralRateModifier;
		private Integer domesticGreatGeneralRateModifier;
		private Integer stateReligionGreatPeopleRateModifier;
		private Integer distanceMaintenanceModifier;
		private Integer numCitiesMaintenanceModifier;
		private Integer corporationMaintenanceModifier;
		private Integer foreignTradeRouteModifier;
		private Integer extraHealth;
		private Integer extraHappiness;
		private Integer freeExperience;
		private Integer workerSpeedModifier;
		private Integer improvementUpgradeRateModifier;
		private Integer militaryProductionModifier;
		private Integer baseFreeUnits;
		private Integer baseFreeMilitaryUnits;
		private Integer freeUnitsPopulationPercent;
		private Integer freeMilitaryUnitsPopulationPercent;
		private Integer goldPerUnit;
		private Integer goldPerMilitaryUnit;
		private Integer happyPerMilitaryUnit;
		private Boolean militaryFoodProduction;
		private Boolean tribalConscription;
		private Integer maxConscript;
		private Integer unhealthyPopulationModifier;
		private Integer expInBorderModifier;
		private Boolean buildingOnlyHealthy;
		private Integer largestCityHappiness;
		private Boolean noCapitalUnhappiness;
		private Integer taxRateAngerModifier;
		private Integer distantUnitSupplyCostModifier;
		private Integer warWearinessModifier;
		private Integer freeSpecialist;
		private Integer tradeRoutes;
		private Boolean noForeignTrade;
		private Boolean noCorporations;
		private Boolean noForeignCorporations;
		private Integer civicPercentAnger;
		private Boolean stateReligion;
		private Boolean noNonStateReligionSpread;
		private Integer stateReligionHappiness;
		private Integer nonStateReligionHappiness;
		private Integer stateReligionUnitProductionModifier;
		private Integer stateReligionBuildingProductionModifier;
		private Integer stateReligionFreeExperience;
		private Boolean unitRangeUnbound;
		private Boolean unitTerritoryUnbound;
		private Integer unitRangeChange;
		private Integer unitRangeModifier;
		private Integer cultureDefenceChange;
		private Integer populationGrowthRateModifier;
		private List<Integer> yieldModifiers = new ArrayList<Integer>();
		private List<Integer> capitalYieldModifiers = new ArrayList<Integer>();
		private List<Integer> tradeYieldModifiers = new ArrayList<Integer>();
		private List<Integer> commerceModifiers = new ArrayList<Integer>();
		private List<Integer> capitalCommerceModifiers = new ArrayList<Integer>();
		private List<Integer> specialistCommerceChanges = new ArrayList<Integer>();
		private List<String> hurrys = new ArrayList<String>();
		private List<String> specialBuildingNotRequireds = new ArrayList<String>();
		private List<String> specialistValids = new ArrayList<String>();
		private List<IPair<String, Integer>> freeSpecialistCounts = new ArrayList<IPair<String, Integer>>();
		private List<IPair<String, Integer>> buildingClassProductionModifiers = new ArrayList<IPair<String, Integer>>();
		private List<IPair<String, Integer>> buildingHappinessChanges = new ArrayList<IPair<String, Integer>>();
		private List<IPair<String, Integer>> buildingHealthChanges = new ArrayList<IPair<String, Integer>>();
		private List<IPair<String, Integer>> featureHappinessChanges = new ArrayList<IPair<String, Integer>>();
		private ImprovementYieldChanges improvementYieldChanges = new ImprovementYieldChanges();
		private Boolean upgradeAnywhere;
		private String weLoveTheKing;
		private Integer cityDefenceModifier;

		private CivicInfo(String type) {
			this.type = type;
		}

		@Override
		public String getCivicOptionType() {
			return civicOptionType;
		}

		@Override
		public void setCivicOptionType(String civicOptionType) {
			this.civicOptionType = civicOptionType;
		}

		@Override
		public String getType() {
			return type;
		}

		@Override
		public void setType(String type) {
			this.type = type;
		}

		@Override
		public String getDescription() {
			return description;
		}

		@Override
		public void setDescription(String description) {
			this.description = description;
		}

		@Override
		public String getCivilopedia() {
			return civilopedia;
		}

		@Override
		public void setCivilopedia(String civilopedia) {
			this.civilopedia = civilopedia;
		}

		@Override
		public String getStrategy() {
			return strategy;
		}

		@Override
		public void setStrategy(String strategy) {
			this.strategy = strategy;
		}

		@Override
		public String getHelp() {
			return help;
		}

		@Override
		public void setHelp(String help) {
			this.help = help;
		}

		@Override
		public String getButton() {
			return button;
		}

		@Override
		public void setButton(String button) {
			this.button = button;
		}

		@Override
		public Integer getIndex() {
			return index;
		}

		@Override
		public void setIndex(Integer index) {
			this.index = index;
		}

		@Override
		public String getTechPrereq() {
			return techPrereq;
		}

		@Override
		public void setTechPrereq(String techPrereq) {
			this.techPrereq = techPrereq;
		}

		@Override
		public Boolean isCreateBarbarians() {
			return createBarbarians;
		}

		@Override
		public void setCreateBarbarians(Boolean createBarbarians) {
			this.createBarbarians = createBarbarians;
		}

		@Override
		public Boolean isEnableStarSigns() {
			return enableStarSigns;
		}

		@Override
		public void setEnableStarSigns(Boolean enableStarSigns) {
			this.enableStarSigns = enableStarSigns;
		}

		@Override
		public Integer getStarSignMitigateChangePercent() {
			return starSignMitigateChangePercent;
		}

		@Override
		public void setStarSignMitigateChangePercent(Integer starSignMitigateChangePercent) {
			this.starSignMitigateChangePercent = starSignMitigateChangePercent;
		}

		@Override
		public Integer getStarSignScaleChangePercent() {
			return starSignScaleChangePercent;
		}

		@Override
		public void setStarSignScaleChangePercent(Integer starSignScaleChangePercent) {
			this.starSignScaleChangePercent = starSignScaleChangePercent;
		}

		@Override
		public Integer getAnarchyLength() {
			return anarchyLength;
		}

		@Override
		public void setAnarchyLength(Integer anarchyLength) {
			this.anarchyLength = anarchyLength;
		}

		@Override
		public String getUpkeep() {
			return upkeep;
		}

		@Override
		public void setUpkeep(String upkeep) {
			this.upkeep = upkeep;
		}

		@Override
		public Integer getAIWeight() {
			return aIWeight;
		}

		@Override
		public void setAIWeight(Integer aIWeight) {
			this.aIWeight = aIWeight;
		}

		@Override
		public Integer getGreatPeopleRateModifier() {
			return greatPeopleRateModifier;
		}

		@Override
		public void setGreatPeopleRateModifier(Integer greatPeopleRateModifier) {
			this.greatPeopleRateModifier = greatPeopleRateModifier;
		}

		@Override
		public Integer getGreatGeneralRateModifier() {
			return greatGeneralRateModifier;
		}

		@Override
		public void setGreatGeneralRateModifier(Integer greatGeneralRateModifier) {
			this.greatGeneralRateModifier = greatGeneralRateModifier;
		}

		@Override
		public Integer getDomesticGreatGeneralRateModifier() {
			return domesticGreatGeneralRateModifier;
		}

		@Override
		public void setDomesticGreatGeneralRateModifier(Integer domesticGreatGeneralRateModifier) {
			this.domesticGreatGeneralRateModifier = domesticGreatGeneralRateModifier;
		}

		@Override
		public Integer getStateReligionGreatPeopleRateModifier() {
			return stateReligionGreatPeopleRateModifier;
		}

		@Override
		public void setStateReligionGreatPeopleRateModifier(Integer stateReligionGreatPeopleRateModifier) {
			this.stateReligionGreatPeopleRateModifier = stateReligionGreatPeopleRateModifier;
		}

		@Override
		public Integer getDistanceMaintenanceModifier() {
			return distanceMaintenanceModifier;
		}

		@Override
		public void setDistanceMaintenanceModifier(Integer distanceMaintenanceModifier) {
			this.distanceMaintenanceModifier = distanceMaintenanceModifier;
		}

		@Override
		public Integer getNumCitiesMaintenanceModifier() {
			return numCitiesMaintenanceModifier;
		}

		@Override
		public void setNumCitiesMaintenanceModifier(Integer numCitiesMaintenanceModifier) {
			this.numCitiesMaintenanceModifier = numCitiesMaintenanceModifier;
		}

		@Override
		public Integer getCorporationMaintenanceModifier() {
			return corporationMaintenanceModifier;
		}

		@Override
		public void setCorporationMaintenanceModifier(Integer corporationMaintenanceModifier) {
			this.corporationMaintenanceModifier = corporationMaintenanceModifier;
		}

		@Override
		public Integer getForeignTradeRouteModifier() {
			return foreignTradeRouteModifier;
		}

		@Override
		public void setForeignTradeRouteModifier(Integer foreignTradeRouteModifier) {
			this.foreignTradeRouteModifier = foreignTradeRouteModifier;
		}

		@Override
		public Integer getExtraHealth() {
			return extraHealth;
		}

		@Override
		public void setExtraHealth(Integer extraHealth) {
			this.extraHealth = extraHealth;
		}

		@Override
		public Integer getExtraHappiness() {
			return extraHappiness;
		}

		@Override
		public void setExtraHappiness(Integer extraHappiness) {
			this.extraHappiness = extraHappiness;
		}

		@Override
		public Integer getFreeExperience() {
			return freeExperience;
		}

		@Override
		public void setFreeExperience(Integer freeExperience) {
			this.freeExperience = freeExperience;
		}

		@Override
		public Integer getWorkerSpeedModifier() {
			return workerSpeedModifier;
		}

		@Override
		public void setWorkerSpeedModifier(Integer workerSpeedModifier) {
			this.workerSpeedModifier = workerSpeedModifier;
		}

		@Override
		public Integer getImprovementUpgradeRateModifier() {
			return improvementUpgradeRateModifier;
		}

		@Override
		public void setImprovementUpgradeRateModifier(Integer improvementUpgradeRateModifier) {
			this.improvementUpgradeRateModifier = improvementUpgradeRateModifier;
		}

		@Override
		public Integer getMilitaryProductionModifier() {
			return militaryProductionModifier;
		}

		@Override
		public void setMilitaryProductionModifier(Integer militaryProductionModifier) {
			this.militaryProductionModifier = militaryProductionModifier;
		}

		@Override
		public Integer getBaseFreeUnits() {
			return baseFreeUnits;
		}

		@Override
		public void setBaseFreeUnits(Integer baseFreeUnits) {
			this.baseFreeUnits = baseFreeUnits;
		}

		@Override
		public Integer getBaseFreeMilitaryUnits() {
			return baseFreeMilitaryUnits;
		}

		@Override
		public void setBaseFreeMilitaryUnits(Integer baseFreeMilitaryUnits) {
			this.baseFreeMilitaryUnits = baseFreeMilitaryUnits;
		}

		@Override
		public Integer getFreeUnitsPopulationPercent() {
			return freeUnitsPopulationPercent;
		}

		@Override
		public void setFreeUnitsPopulationPercent(Integer freeUnitsPopulationPercent) {
			this.freeUnitsPopulationPercent = freeUnitsPopulationPercent;
		}

		@Override
		public Integer getFreeMilitaryUnitsPopulationPercent() {
			return freeMilitaryUnitsPopulationPercent;
		}

		@Override
		public void setFreeMilitaryUnitsPopulationPercent(Integer freeMilitaryUnitsPopulationPercent) {
			this.freeMilitaryUnitsPopulationPercent = freeMilitaryUnitsPopulationPercent;
		}

		@Override
		public Integer getGoldPerUnit() {
			return goldPerUnit;
		}

		@Override
		public void setGoldPerUnit(Integer goldPerUnit) {
			this.goldPerUnit = goldPerUnit;
		}

		@Override
		public Integer getGoldPerMilitaryUnit() {
			return goldPerMilitaryUnit;
		}

		@Override
		public void setGoldPerMilitaryUnit(Integer goldPerMilitaryUnit) {
			this.goldPerMilitaryUnit = goldPerMilitaryUnit;
		}

		@Override
		public Integer getHappyPerMilitaryUnit() {
			return happyPerMilitaryUnit;
		}

		@Override
		public void setHappyPerMilitaryUnit(Integer happyPerMilitaryUnit) {
			this.happyPerMilitaryUnit = happyPerMilitaryUnit;
		}

		@Override
		public Boolean isMilitaryFoodProduction() {
			return militaryFoodProduction;
		}

		@Override
		public void setMilitaryFoodProduction(Boolean militaryFoodProduction) {
			this.militaryFoodProduction = militaryFoodProduction;
		}

		@Override
		public Boolean isTribalConscription() {
			return tribalConscription;
		}

		@Override
		public void setTribalConscription(Boolean tribalConscription) {
			this.tribalConscription = tribalConscription;
		}

		@Override
		public Integer getMaxConscript() {
			return maxConscript;
		}

		@Override
		public void setMaxConscript(Integer maxConscript) {
			this.maxConscript = maxConscript;
		}

		@Override
		public Integer getUnhealthyPopulationModifier() {
			return unhealthyPopulationModifier;
		}

		@Override
		public void setUnhealthyPopulationModifier(Integer unhealthyPopulationModifier) {
			this.unhealthyPopulationModifier = unhealthyPopulationModifier;
		}

		@Override
		public Integer getExpInBorderModifier() {
			return expInBorderModifier;
		}

		@Override
		public void setExpInBorderModifier(Integer expInBorderModifier) {
			this.expInBorderModifier = expInBorderModifier;
		}

		@Override
		public Boolean isBuildingOnlyHealthy() {
			return buildingOnlyHealthy;
		}

		@Override
		public void setBuildingOnlyHealthy(Boolean buildingOnlyHealthy) {
			this.buildingOnlyHealthy = buildingOnlyHealthy;
		}

		@Override
		public Integer getLargestCityHappiness() {
			return largestCityHappiness;
		}

		@Override
		public void setLargestCityHappiness(Integer largestCityHappiness) {
			this.largestCityHappiness = largestCityHappiness;
		}

		@Override
		public Boolean isNoCapitalUnhappiness() {
			return noCapitalUnhappiness;
		}

		@Override
		public void setNoCapitalUnhappiness(Boolean noCapitalUnhappiness) {
			this.noCapitalUnhappiness = noCapitalUnhappiness;
		}

		@Override
		public Integer getTaxRateAngerModifier() {
			return taxRateAngerModifier;
		}

		@Override
		public void setTaxRateAngerModifier(Integer taxRateAngerModifier) {
			this.taxRateAngerModifier = taxRateAngerModifier;
		}

		@Override
		public Integer getDistantUnitSupplyCostModifier() {
			return distantUnitSupplyCostModifier;
		}

		@Override
		public void setDistantUnitSupplyCostModifier(Integer distantUnitSupplyCostModifier) {
			this.distantUnitSupplyCostModifier = distantUnitSupplyCostModifier;
		}

		@Override
		public Integer getWarWearinessModifier() {
			return warWearinessModifier;
		}

		@Override
		public void setWarWearinessModifier(Integer warWearinessModifier) {
			this.warWearinessModifier = warWearinessModifier;
		}

		@Override
		public Integer getFreeSpecialist() {
			return freeSpecialist;
		}

		@Override
		public void setFreeSpecialist(Integer freeSpecialist) {
			this.freeSpecialist = freeSpecialist;
		}

		@Override
		public Integer getTradeRoutes() {
			return tradeRoutes;
		}

		@Override
		public void setTradeRoutes(Integer tradeRoutes) {
			this.tradeRoutes = tradeRoutes;
		}

		@Override
		public Boolean isNoForeignTrade() {
			return noForeignTrade;
		}

		@Override
		public void setNoForeignTrade(Boolean noForeignTrade) {
			this.noForeignTrade = noForeignTrade;
		}

		@Override
		public Boolean isNoCorporations() {
			return noCorporations;
		}

		@Override
		public void setNoCorporations(Boolean noCorporations) {
			this.noCorporations = noCorporations;
		}

		@Override
		public Boolean isNoForeignCorporations() {
			return noForeignCorporations;
		}

		@Override
		public void setNoForeignCorporations(Boolean noForeignCorporations) {
			this.noForeignCorporations = noForeignCorporations;
		}

		@Override
		public Integer getCivicPercentAnger() {
			return civicPercentAnger;
		}

		@Override
		public void setCivicPercentAnger(Integer civicPercentAnger) {
			this.civicPercentAnger = civicPercentAnger;
		}

		@Override
		public Boolean isStateReligion() {
			return stateReligion;
		}

		@Override
		public void setStateReligion(Boolean stateReligion) {
			this.stateReligion = stateReligion;
		}

		@Override
		public Boolean isNoNonStateReligionSpread() {
			return noNonStateReligionSpread;
		}

		@Override
		public void setNoNonStateReligionSpread(Boolean noNonStateReligionSpread) {
			this.noNonStateReligionSpread = noNonStateReligionSpread;
		}

		@Override
		public Integer getStateReligionHappiness() {
			return stateReligionHappiness;
		}

		@Override
		public void setStateReligionHappiness(Integer stateReligionHappiness) {
			this.stateReligionHappiness = stateReligionHappiness;
		}

		@Override
		public Integer getNonStateReligionHappiness() {
			return nonStateReligionHappiness;
		}

		@Override
		public void setNonStateReligionHappiness(Integer nonStateReligionHappiness) {
			this.nonStateReligionHappiness = nonStateReligionHappiness;
		}

		@Override
		public Integer getStateReligionUnitProductionModifier() {
			return stateReligionUnitProductionModifier;
		}

		@Override
		public void setStateReligionUnitProductionModifier(Integer stateReligionUnitProductionModifier) {
			this.stateReligionUnitProductionModifier = stateReligionUnitProductionModifier;
		}

		@Override
		public Integer getStateReligionBuildingProductionModifier() {
			return stateReligionBuildingProductionModifier;
		}

		@Override
		public void setStateReligionBuildingProductionModifier(Integer stateReligionBuildingProductionModifier) {
			this.stateReligionBuildingProductionModifier = stateReligionBuildingProductionModifier;
		}

		@Override
		public Integer getStateReligionFreeExperience() {
			return stateReligionFreeExperience;
		}

		@Override
		public void setStateReligionFreeExperience(Integer stateReligionFreeExperience) {
			this.stateReligionFreeExperience = stateReligionFreeExperience;
		}

		@Override
		public Boolean isUnitRangeUnbound() {
			return unitRangeUnbound;
		}

		@Override
		public void setUnitRangeUnbound(Boolean unitRangeUnbound) {
			this.unitRangeUnbound = unitRangeUnbound;
		}

		@Override
		public Boolean isUnitTerritoryUnbound() {
			return unitTerritoryUnbound;
		}

		@Override
		public void setUnitTerritoryUnbound(Boolean unitTerritoryUnbound) {
			this.unitTerritoryUnbound = unitTerritoryUnbound;
		}

		@Override
		public Integer getUnitRangeChange() {
			return unitRangeChange;
		}

		@Override
		public void setUnitRangeChange(Integer unitRangeChange) {
			this.unitRangeChange = unitRangeChange;
		}

		@Override
		public Integer getUnitRangeModifier() {
			return unitRangeModifier;
		}

		@Override
		public void setUnitRangeModifier(Integer unitRangeModifier) {
			this.unitRangeModifier = unitRangeModifier;
		}

		@Override
		public Integer getCultureDefenceChange() {
			return cultureDefenceChange;
		}

		@Override
		public void setCultureDefenceChange(Integer cultureDefenceChange) {
			this.cultureDefenceChange = cultureDefenceChange;
		}

		@Override
		public Integer getPopulationGrowthRateModifier() {
			return populationGrowthRateModifier;
		}

		@Override
		public void setPopulationGrowthRateModifier(Integer populationGrowthRateModifier) {
			this.populationGrowthRateModifier = populationGrowthRateModifier;
		}

		@Override
		public List<Integer> getYieldModifiers() {
			return yieldModifiers;
		}

		@Override
		public void addYieldModifier(Integer yieldModifier) {
			this.yieldModifiers.add(yieldModifier);
		}

		@Override
		public List<Integer> getCapitalYieldModifiers() {
			return capitalYieldModifiers;
		}

		@Override
		public void addCapitalYieldModifier(Integer capitalYieldModifier) {
			this.capitalYieldModifiers.add(capitalYieldModifier);
		}

		@Override
		public List<Integer> getTradeYieldModifiers() {
			return tradeYieldModifiers;
		}

		@Override
		public void addTradeYieldModifier(Integer tradeYieldModifier) {
			this.tradeYieldModifiers.add(tradeYieldModifier);
		}

		@Override
		public List<Integer> getCommerceModifiers() {
			return commerceModifiers;
		}

		@Override
		public void addCommerceModifier(Integer commerceModifier) {
			this.commerceModifiers.add(commerceModifier);
		}

		@Override
		public List<Integer> getCapitalCommerceModifiers() {
			return capitalCommerceModifiers;
		}

		@Override
		public void addCapitalCommerceModifier(Integer capitalCommerceModifier) {
			this.capitalCommerceModifiers.add(capitalCommerceModifier);
		}

		@Override
		public List<Integer> getSpecialistCommerceChanges() {
			return specialistCommerceChanges;
		}

		@Override
		public void addSpecialistCommerceChange(Integer specialistCommerceChange) {
			this.specialistCommerceChanges.add(specialistCommerceChange);
		}

		@Override
		public List<String> getHurrys() {
			return hurrys;
		}

		@Override
		public void addHurry(String hurry) {
			this.hurrys.add(hurry);
		}

		@Override
		public List<String> getSpecialBuildingNotRequireds() {
			return specialBuildingNotRequireds;
		}

		@Override
		public void addSpecialBuildingNotRequired(String specialBuildingNotRequired) {
			this.specialBuildingNotRequireds.add(specialBuildingNotRequired);
		}

		@Override
		public List<String> getSpecialistValids() {
			return specialistValids;
		}

		@Override
		public void addSpecialistValid(String specialistValid) {
			this.specialistValids.add(specialistValid);
		}

		@Override
		public List<IPair<String, Integer>> getFreeSpecialistCounts() {
			return freeSpecialistCounts;
		}

		@Override
		public void addFreeSpecialistCount(IPair<String, Integer> freeSpecialistCount) {
			this.freeSpecialistCounts.add(freeSpecialistCount);
		}

		@Override
		public List<IPair<String, Integer>> getBuildingClassProductionModifiers() {
			return buildingClassProductionModifiers;
		}

		@Override
		public void addBuildingClassProductionModifier(IPair<String, Integer> buildingClassProductionModifier) {
			this.buildingClassProductionModifiers.add(buildingClassProductionModifier);
		}

		@Override
		public List<IPair<String, Integer>> getBuildingHappinessChanges() {
			return buildingHappinessChanges;
		}

		@Override
		public void addBuildingHappinessChange(IPair<String, Integer> buildingHappinessChange) {
			this.buildingHappinessChanges.add(buildingHappinessChange);
		}

		@Override
		public List<IPair<String, Integer>> getBuildingHealthChanges() {
			return buildingHealthChanges;
		}

		@Override
		public void addBuildingHealthChange(IPair<String, Integer> buildingHealthChange) {
			this.buildingHealthChanges.add(buildingHealthChange);
		}

		@Override
		public List<IPair<String, Integer>> getFeatureHappinessChanges() {
			return featureHappinessChanges;
		}

		@Override
		public void addFeatureHappinessChange(IPair<String, Integer> featureHappinessChange) {
			this.featureHappinessChanges.add(featureHappinessChange);
		}

		@Override
		public ImprovementYieldChanges getImprovementYieldChanges() {
			return improvementYieldChanges;
		}

		@Override
		public void setImprovementYieldChanges(ImprovementYieldChanges improvementYieldChanges) {
			this.improvementYieldChanges = improvementYieldChanges;
		}

		@Override
		public Boolean isUpgradeAnywhere() {
			return upgradeAnywhere;
		}

		@Override
		public void setUpgradeAnywhere(Boolean upgradeAnywhere) {
			this.upgradeAnywhere = upgradeAnywhere;
		}

		@Override
		public String getWeLoveTheKing() {
			return weLoveTheKing;
		}

		@Override
		public void setWeLoveTheKing(String weLoveTheKing) {
			this.weLoveTheKing = weLoveTheKing;
		}

		@Override
		public Integer getCityDefenceModifier() {
			return cityDefenceModifier;
		}

		@Override
		public void setCityDefenceModifier(Integer cityDefenceModifier) {
			this.cityDefenceModifier = cityDefenceModifier;
		}
	}
}