package org.archid.civ4.info.building;

import java.util.ArrayList;
import java.util.List;
import org.archid.civ4.info.IInfo;
import org.archid.utils.IPair;

public interface IBuildingInfo extends IInfo {
	String getBuildingClass();
	void setBuildingClass(String buildingClass);

	String getSpecialBuildingType();
	void setSpecialBuildingType(String specialBuildingType);

	String getDescription();
	void setDescription(String description);

	String getCivilopedia();
	void setCivilopedia(String civilopedia);

	String getStrategy();
	void setStrategy(String strategy);

	String getHelp();
	void setHelp(String help);

	String getAdvisor();
	void setAdvisor(String advisor);

	String getArtDefineTag();
	void setArtDefineTag(String artDefineTag);

	String getMovieDefineTag();
	void setMovieDefineTag(String movieDefineTag);

	Boolean isAutoBuild();
	void setAutoBuild(Boolean autoBuild);

	String getHolyCity();
	void setHolyCity(String holyCity);

	String getReligionType();
	void setReligionType(String religionType);

	String getStateReligion();
	void setStateReligion(String stateReligion);

	Boolean isStateReligionBool();
	void setStateReligionBool(Boolean stateReligionBool);

	String getPrereqReligion();
	void setPrereqReligion(String prereqReligion);

	String getPrereqCorporation();
	void setPrereqCorporation(String prereqCorporation);

	String getFoundsCorporation();
	void setFoundsCorporation(String foundsCorporation);

	String getGlobalReligionCommerce();
	void setGlobalReligionCommerce(String globalReligionCommerce);

	String getGlobalCorporationCommerce();
	void setGlobalCorporationCommerce(String globalCorporationCommerce);

	String getVictoryPrereq();
	void setVictoryPrereq(String victoryPrereq);

	String getFreeStartEra();
	void setFreeStartEra(String freeStartEra);

	String getMaxStartEra();
	void setMaxStartEra(String maxStartEra);

	String getObsoleteTech();
	void setObsoleteTech(String obsoleteTech);

	List<String> getPrereqAndTechs();
	void addPrereqAndTech(String prereqAndTech);

	String getPrereqBonus();
	void setPrereqBonus(String prereqBonus);

	List<String> getPrereqOrBonuses();
	void addPrereqOrBonus(String prereqOrBonus);

	List<String> getPrereqAndCivics();
	void addPrereqAndCivic(String prereqAndCivic);

	List<String> getPrereqOrCivics();
	void addPrereqOrCivic(String prereqOrCivic);

	List<String> getPrereqAndTerrains();
	void addPrereqAndTerrain(String prereqAndTerrain);

	List<String> getPrereqOrTerrains();
	void addPrereqOrTerrain(String prereqOrTerrain);

	List<String> getPrereqVicinityAndBonus();
	void addPrereqVicinityAndBonu(String prereqVicinityAndBonu);

	List<String> getPrereqVicinityOrBonus();
	void addPrereqVicinityOrBonu(String prereqVicinityOrBonu);

	Boolean isPrereqVicinityBonusUnconnectedAllowed();
	void setPrereqVicinityBonusUnconnectedAllowed(Boolean prereqVicinityBonusUnconnectedAllowed);

	List<String> getPrereqVicinityImprovements();
	void addPrereqVicinityImprovement(String prereqVicinityImprovement);

	List<String> getPrereqVicinityFeatures();
	void addPrereqVicinityFeature(String prereqVicinityFeature);

	List<String> getPrereqWorldViews();
	void addPrereqWorldView(String prereqWorldView);

	Integer getMinPopulation();
	void setMinPopulation(Integer minPopulation);

	String getMinCultureLevel();
	void setMinCultureLevel(String minCultureLevel);

	Boolean isPrereqPower();
	void setPrereqPower(Boolean prereqPower);

	List<IPair<String, Integer>> getProductionTraits();
	void addProductionTrait(IPair<String, Integer> productionTrait);

	List<IPair<String, Integer>> getHappinessTraits();
	void addHappinessTrait(IPair<String, Integer> happinessTrait);

	String getNoBonus();
	void setNoBonus(String noBonus);

	String getPowerBonus();
	void setPowerBonus(String powerBonus);

	String getFreeBonus();
	void setFreeBonus(String freeBonus);

	Integer getNumFreeBonuses();
	void setNumFreeBonuses(Integer numFreeBonuses);

	String getFreeBuilding();
	void setFreeBuilding(String freeBuilding);

	String getFreePromotion();
	void setFreePromotion(String freePromotion);

	String getFreeUnitClass();
	void setFreeUnitClass(String freeUnitClass);

	String getCreateFeatureType();
	void setCreateFeatureType(String createFeatureType);

	String getCivicOption();
	void setCivicOption(String civicOption);

	String getGreatPeopleUnitClass();
	void setGreatPeopleUnitClass(String greatPeopleUnitClass);

	Integer getGreatPeopleRateChange();
	void setGreatPeopleRateChange(Integer greatPeopleRateChange);

	Integer getHurryAngerModifier();
	void setHurryAngerModifier(Integer hurryAngerModifier);

	Boolean isBorderObstacle();
	void setBorderObstacle(Boolean borderObstacle);

	Boolean isTeamShare();
	void setTeamShare(Boolean teamShare);

	Boolean isWater();
	void setWater(Boolean water);

	Boolean isRiver();
	void setRiver(Boolean river);

	Boolean isPowerBool();
	void setPowerBool(Boolean powerBool);

	Boolean isDirtyPower();
	void setDirtyPower(Boolean dirtyPower);

	Boolean isAreaCleanPower();
	void setAreaCleanPower(Boolean areaCleanPower);

	String getDiploVoteType();
	void setDiploVoteType(String diploVoteType);

	Boolean isForceTeamVoteEligible();
	void setForceTeamVoteEligible(Boolean forceTeamVoteEligible);

	Boolean isCapital();
	void setCapital(Boolean capital);

	Boolean isGovernmentCenter();
	void setGovernmentCenter(Boolean governmentCenter);

	Boolean isGoldenAge();
	void setGoldenAge(Boolean goldenAge);

	Boolean isAllowsNukes();
	void setAllowsNukes(Boolean allowsNukes);

	Boolean isMapCentering();
	void setMapCentering(Boolean mapCentering);

	Boolean isNoUnhappiness();
	void setNoUnhappiness(Boolean noUnhappiness);

	Integer getUnhealthyPopulationModifier();
	void setUnhealthyPopulationModifier(Integer unhealthyPopulationModifier);

	Boolean isBuildingOnlyHealthy();
	void setBuildingOnlyHealthy(Boolean buildingOnlyHealthy);

	Boolean isNeverCapture();
	void setNeverCapture(Boolean neverCapture);

	Boolean isNukeImmune();
	void setNukeImmune(Boolean nukeImmune);

	Boolean isPrereqReligionBool();
	void setPrereqReligionBool(Boolean prereqReligionBool);

	Boolean isCenterInCity();
	void setCenterInCity(Boolean centerInCity);

	Boolean isSlaveMarket();
	void setSlaveMarket(Boolean slaveMarket);

	Boolean isApplyAllFreePromotionsOnMove();
	void setApplyAllFreePromotionsOnMove(Boolean applyAllFreePromotionsOnMove);

	Boolean isApplyFreePromotionOnMove();
	void setApplyFreePromotionOnMove(Boolean applyFreePromotionOnMove);

	Boolean isUnitCityDeathCulture();
	void setUnitCityDeathCulture(Boolean unitCityDeathCulture);

	Boolean isUnitAllCityDeathCulture();
	void setUnitAllCityDeathCulture(Boolean unitAllCityDeathCulture);

	Boolean isForceDisableStarSigns();
	void setForceDisableStarSigns(Boolean forceDisableStarSigns);

	Boolean isStarSignGoodOnly();
	void setStarSignGoodOnly(Boolean starSignGoodOnly);

	Integer getStarSignMitigateChangePercent();
	void setStarSignMitigateChangePercent(Integer starSignMitigateChangePercent);

	Integer getGlobalStarSignMitigateChangePercent();
	void setGlobalStarSignMitigateChangePercent(Integer globalStarSignMitigateChangePercent);

	Integer getStarSignScaleChangePercent();
	void setStarSignScaleChangePercent(Integer starSignScaleChangePercent);

	Integer getGlobalStarSignScaleChangePercent();
	void setGlobalStarSignScaleChangePercent(Integer globalStarSignScaleChangePercent);

	Integer getAIWeight();
	void setAIWeight(Integer aIWeight);

	Integer getCost();
	void setCost(Integer cost);

	Integer getHurryCostModifier();
	void setHurryCostModifier(Integer hurryCostModifier);

	Integer getAdvancedStartCost();
	void setAdvancedStartCost(Integer advancedStartCost);

	Integer getAdvancedStartCostIncrease();
	void setAdvancedStartCostIncrease(Integer advancedStartCostIncrease);

	Integer getExtraBarbarianCostChange();
	void setExtraBarbarianCostChange(Integer extraBarbarianCostChange);

	Integer getBarbarianConversionCostModifier();
	void setBarbarianConversionCostModifier(Integer barbarianConversionCostModifier);

	Integer getMinAreaSize();
	void setMinAreaSize(Integer minAreaSize);

	Integer getConquestProb();
	void setConquestProb(Integer conquestProb);

	Integer getCitiesPrereq();
	void setCitiesPrereq(Integer citiesPrereq);

	Integer getTeamsPrereq();
	void setTeamsPrereq(Integer teamsPrereq);

	Integer getLevelPrereq();
	void setLevelPrereq(Integer levelPrereq);

	Integer getMinLatitude();
	void setMinLatitude(Integer minLatitude);

	Integer getMaxLatitude();
	void setMaxLatitude(Integer maxLatitude);

	Integer getWorkableRadius();
	void setWorkableRadius(Integer workableRadius);

	Integer getGreatPeopleRateModifier();
	void setGreatPeopleRateModifier(Integer greatPeopleRateModifier);

	Integer getGreatGeneralRateModifier();
	void setGreatGeneralRateModifier(Integer greatGeneralRateModifier);

	Integer getDomesticGreatGeneralRateModifier();
	void setDomesticGreatGeneralRateModifier(Integer domesticGreatGeneralRateModifier);

	Integer getGlobalGreatPeopleRateModifier();
	void setGlobalGreatPeopleRateModifier(Integer globalGreatPeopleRateModifier);

	Integer getAnarchyModifier();
	void setAnarchyModifier(Integer anarchyModifier);

	Integer getGoldenAgeModifier();
	void setGoldenAgeModifier(Integer goldenAgeModifier);

	Integer getGlobalHurryModifier();
	void setGlobalHurryModifier(Integer globalHurryModifier);

	Integer getExperience();
	void setExperience(Integer experience);

	Integer getGlobalExperience();
	void setGlobalExperience(Integer globalExperience);

	Integer getFoodKept();
	void setFoodKept(Integer foodKept);

	Integer getAirlift();
	void setAirlift(Integer airlift);

	Integer getAirModifier();
	void setAirModifier(Integer airModifier);

	Integer getAirUnitCapacity();
	void setAirUnitCapacity(Integer airUnitCapacity);

	Integer getNukeModifier();
	void setNukeModifier(Integer nukeModifier);

	Integer getNukeExplosionRand();
	void setNukeExplosionRand(Integer nukeExplosionRand);

	Integer getFreeSpecialist();
	void setFreeSpecialist(Integer freeSpecialist);

	Integer getAreaFreeSpecialist();
	void setAreaFreeSpecialist(Integer areaFreeSpecialist);

	Integer getGlobalFreeSpecialist();
	void setGlobalFreeSpecialist(Integer globalFreeSpecialist);

	Integer getMaintenanceModifier();
	void setMaintenanceModifier(Integer maintenanceModifier);

	Integer getWarWearinessModifier();
	void setWarWearinessModifier(Integer warWearinessModifier);

	Integer getGlobalWarWearinessModifier();
	void setGlobalWarWearinessModifier(Integer globalWarWearinessModifier);

	Integer getEnemyWarWearinessModifier();
	void setEnemyWarWearinessModifier(Integer enemyWarWearinessModifier);

	Integer getHealRateChange();
	void setHealRateChange(Integer healRateChange);

	Integer getHealth();
	void setHealth(Integer health);

	Integer getAreaHealth();
	void setAreaHealth(Integer areaHealth);

	Integer getGlobalHealth();
	void setGlobalHealth(Integer globalHealth);

	Integer getHappiness();
	void setHappiness(Integer happiness);

	Integer getAreaHappiness();
	void setAreaHappiness(Integer areaHappiness);

	Integer getGlobalHappiness();
	void setGlobalHappiness(Integer globalHappiness);

	Integer getStateReligionHappiness();
	void setStateReligionHappiness(Integer stateReligionHappiness);

	Integer getWorkerSpeedModifier();
	void setWorkerSpeedModifier(Integer workerSpeedModifier);

	Integer getMilitaryProductionModifier();
	void setMilitaryProductionModifier(Integer militaryProductionModifier);

	Integer getSpaceProductionModifier();
	void setSpaceProductionModifier(Integer spaceProductionModifier);

	Integer getGlobalSpaceProductionModifier();
	void setGlobalSpaceProductionModifier(Integer globalSpaceProductionModifier);

	Integer getTradeRoutes();
	void setTradeRoutes(Integer tradeRoutes);

	Integer getCoastalTradeRoutes();
	void setCoastalTradeRoutes(Integer coastalTradeRoutes);

	Integer getGlobalTradeRoutes();
	void setGlobalTradeRoutes(Integer globalTradeRoutes);

	Integer getTradeRouteModifier();
	void setTradeRouteModifier(Integer tradeRouteModifier);

	Integer getForeignTradeRouteModifier();
	void setForeignTradeRouteModifier(Integer foreignTradeRouteModifier);

	Integer getGlobalPopulationChange();
	void setGlobalPopulationChange(Integer globalPopulationChange);

	Integer getGlobalFoundPopulationChange();
	void setGlobalFoundPopulationChange(Integer globalFoundPopulationChange);

	Integer getFreeTechs();
	void setFreeTechs(Integer freeTechs);

	Integer getDefense();
	void setDefense(Integer defense);

	Integer getObsoleteDefence();
	void setObsoleteDefence(Integer obsoleteDefence);

	Integer getBombardDefense();
	void setBombardDefense(Integer bombardDefense);

	Integer getAllCityDefense();
	void setAllCityDefense(Integer allCityDefense);

	Integer getEspionageDefense();
	void setEspionageDefense(Integer espionageDefense);

	Integer getAsset();
	void setAsset(Integer asset);

	Integer getPower();
	void setPower(Integer power);

	Integer getGlobalWonderProductionModifier();
	void setGlobalWonderProductionModifier(Integer globalWonderProductionModifier);

	String getfVisibilityPriority();
	void setfVisibilityPriority(String fVisibilityPriority);

	List<Integer> getSeaPlotYieldChanges();
	void addSeaPlotYieldChange(Integer seaPlotYieldChange);

	List<Integer> getRiverPlotYieldChanges();
	void addRiverPlotYieldChange(Integer riverPlotYieldChange);

	List<Integer> getGlobalSeaPlotYieldChanges();
	void addGlobalSeaPlotYieldChange(Integer globalSeaPlotYieldChange);

	List<Integer> getYieldChanges();
	void addYieldChange(Integer yieldChange);

	List<Integer> getGlobalYieldChanges();
	void addGlobalYieldChange(Integer globalYieldChange);

	List<Integer> getYieldModifiers();
	void addYieldModifier(Integer yieldModifier);

	List<Integer> getPowerYieldModifiers();
	void addPowerYieldModifier(Integer powerYieldModifier);

	List<Integer> getAreaYieldModifiers();
	void addAreaYieldModifier(Integer areaYieldModifier);

	List<Integer> getGlobalYieldModifiers();
	void addGlobalYieldModifier(Integer globalYieldModifier);

	List<Integer> getCommerceChanges();
	void addCommerceChange(Integer commerceChange);

	List<Integer> getObsoleteSafeCommerceChanges();
	void addObsoleteSafeCommerceChange(Integer obsoleteSafeCommerceChange);

	List<Integer> getCommerceChangeDoubleTimes();
	void addCommerceChangeDoubleTime(Integer commerceChangeDoubleTime);

	List<Integer> getCommerceModifiers();
	void addCommerceModifier(Integer commerceModifier);

	List<Integer> getGlobalCommerceModifiers();
	void addGlobalCommerceModifier(Integer globalCommerceModifier);

	List<Integer> getSpecialistExtraCommerces();
	void addSpecialistExtraCommerce(Integer specialistExtraCommerce);

	List<Integer> getStateReligionCommerces();
	void addStateReligionCommerce(Integer stateReligionCommerce);

	List<Integer> getCommerceHappinesses();
	void addCommerceHappinesse(Integer commerceHappinesse);

	List<IPair<String, Integer>> getReligionChanges();
	void addReligionChange(IPair<String, Integer> religionChange);

	List<IPair<String, Integer>> getSpecialistCounts();
	void addSpecialistCount(IPair<String, Integer> specialistCount);

	List<IPair<String, Integer>> getFreeSpecialistCounts();
	void addFreeSpecialistCount(IPair<String, Integer> freeSpecialistCount);

	List<Boolean> getCommerceFlexibles();
	void addCommerceFlexible(Boolean commerceFlexible);

	List<Boolean> getCommerceChangeOriginalOwners();
	void addCommerceChangeOriginalOwner(Boolean commerceChangeOriginalOwner);

	String getConstructSound();
	void setConstructSound(String constructSound);

	List<IPair<String, Integer>> getBonusHealthChanges();
	void addBonusHealthChange(IPair<String, Integer> bonusHealthChange);

	List<IPair<String, Integer>> getBonusHappinessChanges();
	void addBonusHappinessChange(IPair<String, Integer> bonusHappinessChange);

	List<IPair<String, Integer>> getBonusProductionModifiers();
	void addBonusProductionModifier(IPair<String, Integer> bonusProductionModifier);

	List<IPair<String, Integer>> getUnitCombatFreeExperiences();
	void addUnitCombatFreeExperience(IPair<String, Integer> unitCombatFreeExperience);

	List<IPair<String, Integer>> getDomainFreeExperiences();
	void addDomainFreeExperience(IPair<String, Integer> domainFreeExperience);

	List<IPair<String, Integer>> getDomainProductionModifiers();
	void addDomainProductionModifier(IPair<String, Integer> domainProductionModifier);

	List<IPair<String, Integer>> getBuildingClassProductionModifiers();
	void addBuildingClassProductionModifier(IPair<String, Integer> buildingClassProductionModifier);

	List<IPair<String, Integer>> getUnitCombatProductionModifiers();
	void addUnitCombatProductionModifier(IPair<String, Integer> unitCombatProductionModifier);

	List<IPair<String, Integer>> getBuildingHappinessChanges();
	void addBuildingHappinessChange(IPair<String, Integer> buildingHappinessChange);

	List<IPair<String, Integer>> getPrereqNumOfBuildingClasses();
	void addPrereqNumOfBuildingClass(IPair<String, Integer> prereqNumOfBuildingClass);

	List<String> getPrereqAndBuildingClasses();
	void addPrereqAndBuildingClass(String prereqAndBuildingClass);

	List<String> getPrereqOrBuildingClasses();
	void addPrereqOrBuildingClass(String prereqOrBuildingClass);

	List<String> getPrereqNotBuildingClasses();
	void addPrereqNotBuildingClass(String prereqNotBuildingClass);

	List<String> getReplacedByBuildingClasses();
	void addReplacedByBuildingClass(String replacedByBuildingClass);

	SpecialistYieldChanges getSpecialistYieldChanges();
	void setSpecialistYieldChanges(SpecialistYieldChanges specialistYieldChanges);

	BonusYieldModifiers getBonusYieldModifiers();
	void setBonusYieldModifiers(BonusYieldModifiers bonusYieldModifiers);

	BonusYieldChanges getBonusYieldChanges();
	void setBonusYieldChanges(BonusYieldChanges bonusYieldChanges);

	VicinityBonusYieldChanges getVicinityBonusYieldChanges();
	void setVicinityBonusYieldChanges(VicinityBonusYieldChanges vicinityBonusYieldChanges);

	TechYieldChanges getTechYieldChanges();
	void setTechYieldChanges(TechYieldChanges techYieldChanges);

	TechCommerceChanges getTechCommerceChanges();
	void setTechCommerceChanges(TechCommerceChanges techCommerceChanges);

	List<IPair<String, Integer>> getImprovementFreeSpecialists();
	void addImprovementFreeSpecialist(IPair<String, Integer> improvementFreeSpecialist);

	List<IPair<String, Integer>> getFlavors();
	void addFlavor(IPair<String, Integer> flavor);

	String getHotKey();
	void setHotKey(String hotKey);

	Boolean isAltDown();
	void setAltDown(Boolean altDown);

	Boolean isShiftDown();
	void setShiftDown(Boolean shiftDown);

	Boolean isCtrlDown();
	void setCtrlDown(Boolean ctrlDown);

	Integer getHotKeyPriority();
	void setHotKeyPriority(Integer hotKeyPriority);

	Integer getOrderPriority();
	void setOrderPriority(Integer orderPriority);

	Boolean isGraphicalOnly();
	void setGraphicalOnly(Boolean graphicalOnly);

}