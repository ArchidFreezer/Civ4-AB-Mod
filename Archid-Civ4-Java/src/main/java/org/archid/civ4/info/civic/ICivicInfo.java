package org.archid.civ4.info.civic;

import java.util.ArrayList;
import java.util.List;
import org.archid.civ4.info.IInfo;
import org.archid.utils.IPair;

public interface ICivicInfo extends IInfo {
	String getCivicOptionType();
	void setCivicOptionType(String civicOptionType);

	String getDescription();
	void setDescription(String description);

	String getCivilopedia();
	void setCivilopedia(String civilopedia);

	String getStrategy();
	void setStrategy(String strategy);

	String getHelp();
	void setHelp(String help);

	String getButton();
	void setButton(String button);

	Integer getIndex();
	void setIndex(Integer index);

	String getTechPrereq();
	void setTechPrereq(String techPrereq);

	Boolean isCreateBarbarians();
	void setCreateBarbarians(Boolean createBarbarians);

	Boolean isEnableStarSigns();
	void setEnableStarSigns(Boolean enableStarSigns);

	Integer getStarSignMitigateChangePercent();
	void setStarSignMitigateChangePercent(Integer starSignMitigateChangePercent);

	Integer getStarSignScaleChangePercent();
	void setStarSignScaleChangePercent(Integer starSignScaleChangePercent);

	Integer getAnarchyLength();
	void setAnarchyLength(Integer anarchyLength);

	String getUpkeep();
	void setUpkeep(String upkeep);

	Integer getAIWeight();
	void setAIWeight(Integer aIWeight);

	Integer getGreatPeopleRateModifier();
	void setGreatPeopleRateModifier(Integer greatPeopleRateModifier);

	Integer getGreatGeneralRateModifier();
	void setGreatGeneralRateModifier(Integer greatGeneralRateModifier);

	Integer getDomesticGreatGeneralRateModifier();
	void setDomesticGreatGeneralRateModifier(Integer domesticGreatGeneralRateModifier);

	Integer getStateReligionGreatPeopleRateModifier();
	void setStateReligionGreatPeopleRateModifier(Integer stateReligionGreatPeopleRateModifier);

	Integer getDistanceMaintenanceModifier();
	void setDistanceMaintenanceModifier(Integer distanceMaintenanceModifier);

	Integer getNumCitiesMaintenanceModifier();
	void setNumCitiesMaintenanceModifier(Integer numCitiesMaintenanceModifier);

	Integer getCorporationMaintenanceModifier();
	void setCorporationMaintenanceModifier(Integer corporationMaintenanceModifier);

	Integer getForeignTradeRouteModifier();
	void setForeignTradeRouteModifier(Integer foreignTradeRouteModifier);

	Integer getExtraHealth();
	void setExtraHealth(Integer extraHealth);

	Integer getExtraHappiness();
	void setExtraHappiness(Integer extraHappiness);

	Integer getFreeExperience();
	void setFreeExperience(Integer freeExperience);

	Integer getWorkerSpeedModifier();
	void setWorkerSpeedModifier(Integer workerSpeedModifier);

	Integer getImprovementUpgradeRateModifier();
	void setImprovementUpgradeRateModifier(Integer improvementUpgradeRateModifier);

	Integer getMilitaryProductionModifier();
	void setMilitaryProductionModifier(Integer militaryProductionModifier);

	Integer getBaseFreeUnits();
	void setBaseFreeUnits(Integer baseFreeUnits);

	Integer getBaseFreeMilitaryUnits();
	void setBaseFreeMilitaryUnits(Integer baseFreeMilitaryUnits);

	Integer getFreeUnitsPopulationPercent();
	void setFreeUnitsPopulationPercent(Integer freeUnitsPopulationPercent);

	Integer getFreeMilitaryUnitsPopulationPercent();
	void setFreeMilitaryUnitsPopulationPercent(Integer freeMilitaryUnitsPopulationPercent);

	Integer getGoldPerUnit();
	void setGoldPerUnit(Integer goldPerUnit);

	Integer getGoldPerMilitaryUnit();
	void setGoldPerMilitaryUnit(Integer goldPerMilitaryUnit);

	Integer getHappyPerMilitaryUnit();
	void setHappyPerMilitaryUnit(Integer happyPerMilitaryUnit);

	Boolean isMilitaryFoodProduction();
	void setMilitaryFoodProduction(Boolean militaryFoodProduction);

	Boolean isTribalConscription();
	void setTribalConscription(Boolean tribalConscription);

	Integer getMaxConscript();
	void setMaxConscript(Integer maxConscript);

	Integer getUnhealthyPopulationModifier();
	void setUnhealthyPopulationModifier(Integer unhealthyPopulationModifier);

	Integer getExpInBorderModifier();
	void setExpInBorderModifier(Integer expInBorderModifier);

	Boolean isBuildingOnlyHealthy();
	void setBuildingOnlyHealthy(Boolean buildingOnlyHealthy);

	Integer getLargestCityHappiness();
	void setLargestCityHappiness(Integer largestCityHappiness);

	Boolean isNoCapitalUnhappiness();
	void setNoCapitalUnhappiness(Boolean noCapitalUnhappiness);

	Integer getTaxRateAngerModifier();
	void setTaxRateAngerModifier(Integer taxRateAngerModifier);

	Integer getDistantUnitSupplyCostModifier();
	void setDistantUnitSupplyCostModifier(Integer distantUnitSupplyCostModifier);

	Integer getWarWearinessModifier();
	void setWarWearinessModifier(Integer warWearinessModifier);

	Integer getFreeSpecialist();
	void setFreeSpecialist(Integer freeSpecialist);

	Integer getTradeRoutes();
	void setTradeRoutes(Integer tradeRoutes);

	Boolean isNoForeignTrade();
	void setNoForeignTrade(Boolean noForeignTrade);

	Boolean isNoCorporations();
	void setNoCorporations(Boolean noCorporations);

	Boolean isNoForeignCorporations();
	void setNoForeignCorporations(Boolean noForeignCorporations);

	Integer getCivicPercentAnger();
	void setCivicPercentAnger(Integer civicPercentAnger);

	Boolean isStateReligion();
	void setStateReligion(Boolean stateReligion);

	Boolean isNoNonStateReligionSpread();
	void setNoNonStateReligionSpread(Boolean noNonStateReligionSpread);

	Integer getStateReligionHappiness();
	void setStateReligionHappiness(Integer stateReligionHappiness);

	Integer getNonStateReligionHappiness();
	void setNonStateReligionHappiness(Integer nonStateReligionHappiness);

	Integer getStateReligionUnitProductionModifier();
	void setStateReligionUnitProductionModifier(Integer stateReligionUnitProductionModifier);

	Integer getStateReligionBuildingProductionModifier();
	void setStateReligionBuildingProductionModifier(Integer stateReligionBuildingProductionModifier);

	Integer getStateReligionFreeExperience();
	void setStateReligionFreeExperience(Integer stateReligionFreeExperience);

	Boolean isUnitRangeUnbound();
	void setUnitRangeUnbound(Boolean unitRangeUnbound);

	Boolean isUnitTerritoryUnbound();
	void setUnitTerritoryUnbound(Boolean unitTerritoryUnbound);

	Integer getUnitRangeChange();
	void setUnitRangeChange(Integer unitRangeChange);

	Integer getUnitRangeModifier();
	void setUnitRangeModifier(Integer unitRangeModifier);

	Integer getCultureDefenceChange();
	void setCultureDefenceChange(Integer cultureDefenceChange);

	Integer getPopulationGrowthRateModifier();
	void setPopulationGrowthRateModifier(Integer populationGrowthRateModifier);

	List<Integer> getYieldModifiers();
	void addYieldModifier(Integer yieldModifier);

	List<Integer> getCapitalYieldModifiers();
	void addCapitalYieldModifier(Integer capitalYieldModifier);

	List<Integer> getTradeYieldModifiers();
	void addTradeYieldModifier(Integer tradeYieldModifier);

	List<Integer> getCommerceModifiers();
	void addCommerceModifier(Integer commerceModifier);

	List<Integer> getCapitalCommerceModifiers();
	void addCapitalCommerceModifier(Integer capitalCommerceModifier);

	List<Integer> getSpecialistCommerceChanges();
	void addSpecialistCommerceChange(Integer specialistCommerceChange);

	List<String> getHurrys();
	void addHurry(String hurry);

	List<String> getSpecialBuildingNotRequireds();
	void addSpecialBuildingNotRequired(String specialBuildingNotRequired);

	List<String> getSpecialistValids();
	void addSpecialistValid(String specialistValid);

	List<IPair<String, Integer>> getFreeSpecialistCounts();
	void addFreeSpecialistCount(IPair<String, Integer> freeSpecialistCount);

	List<IPair<String, Integer>> getBuildingClassProductionModifiers();
	void addBuildingClassProductionModifier(IPair<String, Integer> buildingClassProductionModifier);

	List<IPair<String, Integer>> getBuildingHappinessChanges();
	void addBuildingHappinessChange(IPair<String, Integer> buildingHappinessChange);

	List<IPair<String, Integer>> getBuildingHealthChanges();
	void addBuildingHealthChange(IPair<String, Integer> buildingHealthChange);

	List<IPair<String, Integer>> getFeatureHappinessChanges();
	void addFeatureHappinessChange(IPair<String, Integer> featureHappinessChange);

	ImprovementYieldChanges getImprovementYieldChanges();
	void setImprovementYieldChanges(ImprovementYieldChanges improvementYieldChanges);

	Boolean isUpgradeAnywhere();
	void setUpgradeAnywhere(Boolean upgradeAnywhere);

	String getWeLoveTheKing();
	void setWeLoveTheKing(String weLoveTheKing);

	Integer getCityDefenceModifier();
	void setCityDefenceModifier(Integer cityDefenceModifier);

}