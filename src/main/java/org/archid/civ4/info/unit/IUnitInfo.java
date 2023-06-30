package org.archid.civ4.info.unit;

import java.util.ArrayList;
import java.util.List;
import org.archid.civ4.info.IInfo;
import org.archid.utils.IPair;

public interface IUnitInfo extends IInfo {
	String getClazz();
	void setClazz(String clazz);

	List<String> getUniqueNames();
	void addUniqueName(String uniqueName);

	String getSpecial();
	void setSpecial(String special);

	String getCapture();
	void setCapture(String capture);

	String getCombat();
	void setCombat(String combat);

	List<String> getSubCombatTypes();
	void addSubCombatType(String subCombatType);

	String getDomain();
	void setDomain(String domain);

	String getDefaultUnitAI();
	void setDefaultUnitAI(String defaultUnitAI);

	Boolean isFixedAI();
	void setFixedAI(Boolean fixedAI);

	Integer getMaxWeaponTypeTier();
	void setMaxWeaponTypeTier(Integer maxWeaponTypeTier);

	Integer getMaxAmmoTypeTier();
	void setMaxAmmoTypeTier(Integer maxAmmoTypeTier);

	String getInvisible();
	void setInvisible(String invisible);

	List<String> getSeeInvisibles();
	void addSeeInvisible(String seeInvisible);

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

	Boolean isAnimal();
	void setAnimal(Boolean animal);

	Boolean isFood();
	void setFood(Boolean food);

	Boolean isNoBadGoodies();
	void setNoBadGoodies(Boolean noBadGoodies);

	Boolean isOnlyDefensive();
	void setOnlyDefensive(Boolean onlyDefensive);

	Boolean isNoCapture();
	void setNoCapture(Boolean noCapture);

	Boolean isQuickCombat();
	void setQuickCombat(Boolean quickCombat);

	Boolean isRivalTerritory();
	void setRivalTerritory(Boolean rivalTerritory);

	Boolean isMilitaryHappiness();
	void setMilitaryHappiness(Boolean militaryHappiness);

	Boolean isMilitarySupport();
	void setMilitarySupport(Boolean militarySupport);

	Boolean isMilitaryProduction();
	void setMilitaryProduction(Boolean militaryProduction);

	Boolean isPillage();
	void setPillage(Boolean pillage);

	Boolean isSpy();
	void setSpy(Boolean spy);

	Boolean isSabotage();
	void setSabotage(Boolean sabotage);

	Boolean isDestroy();
	void setDestroy(Boolean destroy);

	Boolean isStealPlans();
	void setStealPlans(Boolean stealPlans);

	Boolean isInvestigate();
	void setInvestigate(Boolean investigate);

	Boolean isCounterSpy();
	void setCounterSpy(Boolean counterSpy);

	Boolean isFound();
	void setFound(Boolean found);

	Boolean isGoldenAge();
	void setGoldenAge(Boolean goldenAge);

	Boolean isInvisibleBool();
	void setInvisibleBool(Boolean invisibleBool);

	Boolean isFirstStrikeImmune();
	void setFirstStrikeImmune(Boolean firstStrikeImmune);

	Boolean isNoDefensiveBonus();
	void setNoDefensiveBonus(Boolean noDefensiveBonus);

	Boolean isIgnoreBuildingDefense();
	void setIgnoreBuildingDefense(Boolean ignoreBuildingDefense);

	Boolean isCanMoveImpassable();
	void setCanMoveImpassable(Boolean canMoveImpassable);

	Boolean isCanMoveAllTerrain();
	void setCanMoveAllTerrain(Boolean canMoveAllTerrain);

	Boolean isFlatMovementCost();
	void setFlatMovementCost(Boolean flatMovementCost);

	Boolean isIgnoreTerrainCost();
	void setIgnoreTerrainCost(Boolean ignoreTerrainCost);

	Boolean isNukeImmune();
	void setNukeImmune(Boolean nukeImmune);

	Boolean isPrereqBonuses();
	void setPrereqBonuses(Boolean prereqBonuses);

	Boolean isPrereqReligionBool();
	void setPrereqReligionBool(Boolean prereqReligionBool);

	Boolean isMechanized();
	void setMechanized(Boolean mechanized);

	Boolean isRenderBelowWater();
	void setRenderBelowWater(Boolean renderBelowWater);

	Boolean isRenderAlways();
	void setRenderAlways(Boolean renderAlways);

	Boolean isSuicide();
	void setSuicide(Boolean suicide);

	Boolean isSingleBuild();
	void setSingleBuild(Boolean singleBuild);

	Boolean isSlave();
	void setSlave(Boolean slave);

	Boolean isLineOfSight();
	void setLineOfSight(Boolean lineOfSight);

	Boolean isHiddenNationality();
	void setHiddenNationality(Boolean hiddenNationality);

	Boolean isAlwaysHostile();
	void setAlwaysHostile(Boolean alwaysHostile);

	Boolean isWorkerTrade();
	void setWorkerTrade(Boolean workerTrade);

	Boolean isMilitaryTrade();
	void setMilitaryTrade(Boolean militaryTrade);

	Boolean isBarbarianLeader();
	void setBarbarianLeader(Boolean barbarianLeader);

	Boolean isCityPacifier();
	void setCityPacifier(Boolean cityPacifier);

	List<String> getUnitClassUpgrades();
	void addUnitClassUpgrade(String unitClassUpgrade);

	List<String> getUnitClassTargets();
	void addUnitClassTarget(String unitClassTarget);

	List<String> getUnitCombatTargets();
	void addUnitCombatTarget(String unitCombatTarget);

	List<String> getUnitClassDefenders();
	void addUnitClassDefender(String unitClassDefender);

	List<String> getUnitCombatDefenders();
	void addUnitCombatDefender(String unitCombatDefender);

	List<IPair<String, Integer>> getFlankingStrikes();
	void addFlankingStrike(IPair<String, Integer> flankingStrike);

	List<String> getUnitAIs();
	void addUnitAI(String unitAI);

	List<String> getNotUnitAIs();
	void addNotUnitAI(String notUnitAI);

	List<String> getBuilds();
	void addBuild(String build);

	List<IPair<String, Integer>> getReligionSpreads();
	void addReligionSpread(IPair<String, Integer> religionSpread);

	List<IPair<String, Integer>> getCorporationSpreads();
	void addCorporationSpread(IPair<String, Integer> corporationSpread);

	List<String> getGreatPeoples();
	void addGreatPeople(String greatPeople);

	String getSlaveSpecialistType();
	void setSlaveSpecialistType(String slaveSpecialistType);

	List<String> getBuildings();
	void addBuilding(String building);

	List<String> getForceBuildings();
	void addForceBuilding(String forceBuilding);

	String getHolyCity();
	void setHolyCity(String holyCity);

	String getReligionType();
	void setReligionType(String religionType);

	String getStateReligion();
	void setStateReligion(String stateReligion);

	String getPrereqReligion();
	void setPrereqReligion(String prereqReligion);

	String getPrereqCorporation();
	void setPrereqCorporation(String prereqCorporation);

	String getPrereqBuilding();
	void setPrereqBuilding(String prereqBuilding);

	List<String> getPrereqTechs();
	void addPrereqTech(String prereqTech);

	String getObsoleteTech();
	void setObsoleteTech(String obsoleteTech);

	String getBonusType();
	void setBonusType(String bonusType);

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

	List<String> getPrereqOrBuildingClasses();
	void addPrereqOrBuildingClass(String prereqOrBuildingClass);

	List<String> getPrereqNotBuildingClasses();
	void addPrereqNotBuildingClass(String prereqNotBuildingClass);

	List<String> getPrereqVicinityAndBonus();
	void addPrereqVicinityAndBonu(String prereqVicinityAndBonu);

	List<String> getPrereqVicinityOrBonus();
	void addPrereqVicinityOrBonu(String prereqVicinityOrBonu);

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

	List<IPair<String, Integer>> getFlavors();
	void addFlavor(IPair<String, Integer> flavor);

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

	Integer getMinAreaSize();
	void setMinAreaSize(Integer minAreaSize);

	Integer getMorale();
	void setMorale(Integer morale);
	Integer getMoves();
	void setMoves(Integer moves);

	String getUnitRangeType();
	void setUnitRangeType(String unitRangeType);

	Boolean isNoRevealMap();
	void setNoRevealMap(Boolean noRevealMap);

	Integer getAirRange();
	void setAirRange(Integer airRange);

	Integer getAirUnitCap();
	void setAirUnitCap(Integer airUnitCap);

	Integer getDropRange();
	void setDropRange(Integer dropRange);

	Integer getNukeRange();
	void setNukeRange(Integer nukeRange);

	Integer getWorkRate();
	void setWorkRate(Integer workRate);

	Integer getBaseDiscover();
	void setBaseDiscover(Integer baseDiscover);

	Integer getDiscoverMultiplier();
	void setDiscoverMultiplier(Integer discoverMultiplier);

	Integer getBaseHurry();
	void setBaseHurry(Integer baseHurry);

	Integer getHurryMultiplier();
	void setHurryMultiplier(Integer hurryMultiplier);

	Integer getBaseTrade();
	void setBaseTrade(Integer baseTrade);

	Integer getTradeMultiplier();
	void setTradeMultiplier(Integer tradeMultiplier);

	Integer getGreatWorkCulture();
	void setGreatWorkCulture(Integer greatWorkCulture);

	Integer getEspionagePoints();
	void setEspionagePoints(Integer espionagePoints);

	Integer getGreatJestHappiness();
	void setGreatJestHappiness(Integer greatJestHappiness);

	Integer getGreatJestDuration();
	void setGreatJestDuration(Integer greatJestDuration);

	List<String> getTerrainImpassables();
	void addTerrainImpassable(String terrainImpassable);

	List<String> getFeatureImpassables();
	void addFeatureImpassable(String featureImpassable);

	List<IPair<String, String>> getTerrainPassableTechs();
	void addTerrainPassableTech(IPair<String, String> terrainPassableTech);

	List<IPair<String, String>> getFeaturePassableTechs();
	void addFeaturePassableTech(IPair<String, String> featurePassableTech);

	Integer getCombatInt();
	void setCombatInt(Integer combatInt);

	Integer getCombatLimit();
	void setCombatLimit(Integer combatLimit);

	Integer getAirCombat();
	void setAirCombat(Integer airCombat);

	Integer getAirCombatLimit();
	void setAirCombatLimit(Integer airCombatLimit);

	Integer getXPValueAttack();
	void setXPValueAttack(Integer xPValueAttack);

	Integer getXPValueDefense();
	void setXPValueDefense(Integer xPValueDefense);

	Integer getFirstStrikes();
	void setFirstStrikes(Integer firstStrikes);

	Integer getChanceFirstStrikes();
	void setChanceFirstStrikes(Integer chanceFirstStrikes);

	Integer getInterceptionProbability();
	void setInterceptionProbability(Integer interceptionProbability);

	Integer getEvasionProbability();
	void setEvasionProbability(Integer evasionProbability);

	Integer getWithdrawalProb();
	void setWithdrawalProb(Integer withdrawalProb);

	Integer getCollateralDamage();
	void setCollateralDamage(Integer collateralDamage);

	Integer getCollateralDamageLimit();
	void setCollateralDamageLimit(Integer collateralDamageLimit);

	Integer getCollateralDamageMaxUnits();
	void setCollateralDamageMaxUnits(Integer collateralDamageMaxUnits);

	Integer getCityAttack();
	void setCityAttack(Integer cityAttack);

	Integer getCityDefense();
	void setCityDefense(Integer cityDefense);

	Integer getAnimalCombat();
	void setAnimalCombat(Integer animalCombat);

	Integer getHillsAttack();
	void setHillsAttack(Integer hillsAttack);

	Integer getHillsDefense();
	void setHillsDefense(Integer hillsDefense);

	List<String> getTerrainNatives();
	void addTerrainNative(String terrainNative);

	List<String> getFeatureNatives();
	void addFeatureNative(String featureNative);

	List<IPair<String, Integer>> getTerrainAttacks();
	void addTerrainAttack(IPair<String, Integer> terrainAttack);

	List<IPair<String, Integer>> getTerrainDefenses();
	void addTerrainDefense(IPair<String, Integer> terrainDefense);

	List<IPair<String, Integer>> getFeatureAttacks();
	void addFeatureAttack(IPair<String, Integer> featureAttack);

	List<IPair<String, Integer>> getFeatureDefenses();
	void addFeatureDefense(IPair<String, Integer> featureDefense);

	List<IPair<String, Integer>> getUnitClassAttackMods();
	void addUnitClassAttackMod(IPair<String, Integer> unitClassAttackMod);

	List<IPair<String, Integer>> getUnitClassDefenseMods();
	void addUnitClassDefenseMod(IPair<String, Integer> unitClassDefenseMod);

	List<IPair<String, Integer>> getUnitCombatMods();
	void addUnitCombatMod(IPair<String, Integer> unitCombatMod);

	List<IPair<String, Integer>> getUnitCombatCollateralImmunes();
	void addUnitCombatCollateralImmune(IPair<String, Integer> unitCombatCollateralImmune);

	List<IPair<String, Integer>> getDomainMods();
	void addDomainMod(IPair<String, Integer> domainMod);

	List<IPair<String, Integer>> getBonusProductionModifiers();
	void addBonusProductionModifier(IPair<String, Integer> bonusProductionModifier);

	List<Integer> getYieldsFromKill();
	void addYieldsFromKill(Integer yieldsFromKill);

	List<Integer> getCommercesFromKill();
	void addCommercesFromKill(Integer commercesFromKill);

	Integer getBombRate();
	void setBombRate(Integer bombRate);

	Integer getBombardRate();
	void setBombardRate(Integer bombardRate);

	String getSpecialCargo();
	void setSpecialCargo(String specialCargo);

	String getDomainCargo();
	void setDomainCargo(String domainCargo);

	Integer getCargo();
	void setCargo(Integer cargo);

	Integer getConscription();
	void setConscription(Integer conscription);

	Integer getCultureGarrison();
	void setCultureGarrison(Integer cultureGarrison);

	Integer getExtraCost();
	void setExtraCost(Integer extraCost);

	Integer getAsset();
	void setAsset(Integer asset);

	Integer getPower();
	void setPower(Integer power);

	UnitMeshGroups getUnitMeshGroups();
	void setUnitMeshGroups(UnitMeshGroups unitMeshGroups);

	String getFormationType();
	void setFormationType(String formationType);

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

	List<String> getFreePromotions();
	void addFreePromotion(String freePromotion);

	String getLeaderPromotion();
	void setLeaderPromotion(String leaderPromotion);

	Integer getLeaderExperience();
	void setLeaderExperience(Integer leaderExperience);

	Integer getOrderPriority();
	void setOrderPriority(Integer orderPriority);

}