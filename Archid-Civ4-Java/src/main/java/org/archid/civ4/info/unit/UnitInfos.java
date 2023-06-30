package org.archid.civ4.info.unit;

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

@XmlRootElement(name="Civ4UnitInfos", namespace="x-schema:CIV4UnitSchema.xml")
@XmlAccessorType(XmlAccessType.NONE)
public class UnitInfos extends AbstractInfos<IUnitInfo> {

	public UnitInfos() {
		super(new LinkedHashMap<String, IUnitInfo>());
	}

	@XmlJavaTypeAdapter(UnitMapAdapter.class)
	@XmlElement(name="UnitInfos")
	public Map<String, IUnitInfo> getInfoMap() {
		return infos;
	}

	public static IUnitInfo createInfo(String type) {
		return new UnitInfo(type);
	}

	@Override
	public void setInfoMap(Map<String, IUnitInfo> infos) {
		this.infos = infos;
	}

	private static class UnitInfo implements IUnitInfo{

		private String clazz;
		private String type;
		private List<String> uniqueNames = new ArrayList<String>();
		private String special;
		private String capture;
		private String combat;
		private List<String> subCombatTypes = new ArrayList<String>();
		private String domain;
		private String defaultUnitAI;
		private Boolean fixedAI;
		private Integer maxWeaponTypeTier;
		private Integer maxAmmoTypeTier;
		private String invisible;
		private List<String> seeInvisibles = new ArrayList<String>();
		private String description;
		private String civilopedia;
		private String strategy;
		private String help;
		private String advisor;
		private Boolean animal;
		private Boolean food;
		private Boolean noBadGoodies;
		private Boolean onlyDefensive;
		private Boolean noCapture;
		private Boolean quickCombat;
		private Boolean rivalTerritory;
		private Boolean militaryHappiness;
		private Boolean militarySupport;
		private Boolean militaryProduction;
		private Boolean pillage;
		private Boolean spy;
		private Boolean sabotage;
		private Boolean destroy;
		private Boolean stealPlans;
		private Boolean investigate;
		private Boolean counterSpy;
		private Boolean found;
		private Boolean goldenAge;
		private Boolean invisibleBool;
		private Boolean firstStrikeImmune;
		private Boolean noDefensiveBonus;
		private Boolean ignoreBuildingDefense;
		private Boolean canMoveImpassable;
		private Boolean canMoveAllTerrain;
		private Boolean flatMovementCost;
		private Boolean ignoreTerrainCost;
		private Boolean nukeImmune;
		private Boolean prereqBonuses;
		private Boolean prereqReligionBool;
		private Boolean mechanized;
		private Boolean renderBelowWater;
		private Boolean renderAlways;
		private Boolean suicide;
		private Boolean singleBuild;
		private Boolean slave;
		private Boolean lineOfSight;
		private Boolean hiddenNationality;
		private Boolean alwaysHostile;
		private Boolean workerTrade;
		private Boolean militaryTrade;
		private Boolean barbarianLeader;
		private Boolean cityPacifier;
		private List<String> unitClassUpgrades = new ArrayList<String>();
		private List<String> unitClassTargets = new ArrayList<String>();
		private List<String> unitCombatTargets = new ArrayList<String>();
		private List<String> unitClassDefenders = new ArrayList<String>();
		private List<String> unitCombatDefenders = new ArrayList<String>();
		private List<IPair<String, Integer>> flankingStrikes = new ArrayList<IPair<String, Integer>>();
		private List<String> unitAIs = new ArrayList<String>();
		private List<String> notUnitAIs = new ArrayList<String>();
		private List<String> builds = new ArrayList<String>();
		private List<IPair<String, Integer>> religionSpreads = new ArrayList<IPair<String, Integer>>();
		private List<IPair<String, Integer>> corporationSpreads = new ArrayList<IPair<String, Integer>>();
		private List<String> greatPeoples = new ArrayList<String>();
		private String slaveSpecialistType;
		private List<String> buildings = new ArrayList<String>();
		private List<String> forceBuildings = new ArrayList<String>();
		private String holyCity;
		private String religionType;
		private String stateReligion;
		private String prereqReligion;
		private String prereqCorporation;
		private String prereqBuilding;
		private List<String> prereqTechs = new ArrayList<String>();
		private String obsoleteTech;
		private String bonusType;
		private List<String> prereqOrBonuses = new ArrayList<String>();
		private List<String> prereqAndCivics = new ArrayList<String>();
		private List<String> prereqOrCivics = new ArrayList<String>();
		private List<String> prereqAndTerrains = new ArrayList<String>();
		private List<String> prereqOrTerrains = new ArrayList<String>();
		private List<String> prereqOrBuildingClasses = new ArrayList<String>();
		private List<String> prereqNotBuildingClasses = new ArrayList<String>();
		private List<String> prereqVicinityAndBonus = new ArrayList<String>();
		private List<String> prereqVicinityOrBonus = new ArrayList<String>();
		private List<String> prereqVicinityImprovements = new ArrayList<String>();
		private List<String> prereqVicinityFeatures = new ArrayList<String>();
		private List<String> prereqWorldViews = new ArrayList<String>();
		private Integer minPopulation;
		private String minCultureLevel;
		private Boolean prereqPower;
		private List<IPair<String, Integer>> productionTraits = new ArrayList<IPair<String, Integer>>();
		private List<IPair<String, Integer>> flavors = new ArrayList<IPair<String, Integer>>();
		private Integer aIWeight;
		private Integer cost;
		private Integer hurryCostModifier;
		private Integer advancedStartCost;
		private Integer advancedStartCostIncrease;
		private Integer minAreaSize;
		private Integer morale;
		private Integer moves;
		private String unitRangeType;
		private Boolean noRevealMap;
		private Integer airRange;
		private Integer airUnitCap;
		private Integer dropRange;
		private Integer nukeRange;
		private Integer workRate;
		private Integer baseDiscover;
		private Integer discoverMultiplier;
		private Integer baseHurry;
		private Integer hurryMultiplier;
		private Integer baseTrade;
		private Integer tradeMultiplier;
		private Integer greatWorkCulture;
		private Integer espionagePoints;
		private Integer greatJestHappiness;
		private Integer greatJestDuration;
		private List<String> terrainImpassables = new ArrayList<String>();
		private List<String> featureImpassables = new ArrayList<String>();
		private List<IPair<String, String>> terrainPassableTechs = new ArrayList<IPair<String, String>>();
		private List<IPair<String, String>> featurePassableTechs = new ArrayList<IPair<String, String>>();
		private Integer combatInt;
		private Integer combatLimit;
		private Integer airCombat;
		private Integer airCombatLimit;
		private Integer xPValueAttack;
		private Integer xPValueDefense;
		private Integer firstStrikes;
		private Integer chanceFirstStrikes;
		private Integer interceptionProbability;
		private Integer evasionProbability;
		private Integer withdrawalProb;
		private Integer collateralDamage;
		private Integer collateralDamageLimit;
		private Integer collateralDamageMaxUnits;
		private Integer cityAttack;
		private Integer cityDefense;
		private Integer animalCombat;
		private Integer hillsAttack;
		private Integer hillsDefense;
		private List<String> terrainNatives = new ArrayList<String>();
		private List<String> featureNatives = new ArrayList<String>();
		private List<IPair<String, Integer>> terrainAttacks = new ArrayList<IPair<String, Integer>>();
		private List<IPair<String, Integer>> terrainDefenses = new ArrayList<IPair<String, Integer>>();
		private List<IPair<String, Integer>> featureAttacks = new ArrayList<IPair<String, Integer>>();
		private List<IPair<String, Integer>> featureDefenses = new ArrayList<IPair<String, Integer>>();
		private List<IPair<String, Integer>> unitClassAttackMods = new ArrayList<IPair<String, Integer>>();
		private List<IPair<String, Integer>> unitClassDefenseMods = new ArrayList<IPair<String, Integer>>();
		private List<IPair<String, Integer>> unitCombatMods = new ArrayList<IPair<String, Integer>>();
		private List<IPair<String, Integer>> unitCombatCollateralImmunes = new ArrayList<IPair<String, Integer>>();
		private List<IPair<String, Integer>> domainMods = new ArrayList<IPair<String, Integer>>();
		private List<IPair<String, Integer>> bonusProductionModifiers = new ArrayList<IPair<String, Integer>>();
		private List<Integer> yieldsFromKill = new ArrayList<Integer>();
		private List<Integer> commercesFromKill = new ArrayList<Integer>();
		private Integer bombRate;
		private Integer bombardRate;
		private String specialCargo;
		private String domainCargo;
		private Integer cargo;
		private Integer conscription;
		private Integer cultureGarrison;
		private Integer extraCost;
		private Integer asset;
		private Integer power;
		private UnitMeshGroups unitMeshGroups = new UnitMeshGroups();
		private String formationType;
		private String hotKey;
		private Boolean altDown;
		private Boolean shiftDown;
		private Boolean ctrlDown;
		private Integer hotKeyPriority;
		private List<String> freePromotions = new ArrayList<String>();
		private String leaderPromotion;
		private Integer leaderExperience;
		private Integer orderPriority;

		private UnitInfo(String type) {
			this.type = type;
		}

		@Override
		public String getClazz() {
			return clazz;
		}

		@Override
		public void setClazz(String clazz) {
			this.clazz = clazz;
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
		public List<String> getUniqueNames() {
			return uniqueNames;
		}

		@Override
		public void addUniqueName(String uniqueName) {
			this.uniqueNames.add(uniqueName);
		}

		@Override
		public String getSpecial() {
			return special;
		}

		@Override
		public void setSpecial(String special) {
			this.special = special;
		}

		@Override
		public String getCapture() {
			return capture;
		}

		@Override
		public void setCapture(String capture) {
			this.capture = capture;
		}

		@Override
		public String getCombat() {
			return combat;
		}

		@Override
		public void setCombat(String combat) {
			this.combat = combat;
		}

		@Override
		public List<String> getSubCombatTypes() {
			return subCombatTypes;
		}

		@Override
		public void addSubCombatType(String subCombatType) {
			this.subCombatTypes.add(subCombatType);
		}

		@Override
		public String getDomain() {
			return domain;
		}

		@Override
		public void setDomain(String domain) {
			this.domain = domain;
		}

		@Override
		public String getDefaultUnitAI() {
			return defaultUnitAI;
		}

		@Override
		public void setDefaultUnitAI(String defaultUnitAI) {
			this.defaultUnitAI = defaultUnitAI;
		}

		@Override
		public Boolean isFixedAI() {
			return fixedAI;
		}

		@Override
		public void setFixedAI(Boolean fixedAI) {
			this.fixedAI = fixedAI;
		}

		@Override
		public Integer getMaxWeaponTypeTier() {
			return maxWeaponTypeTier;
		}

		@Override
		public void setMaxWeaponTypeTier(Integer maxWeaponTypeTier) {
			this.maxWeaponTypeTier = maxWeaponTypeTier;
		}

		@Override
		public Integer getMaxAmmoTypeTier() {
			return maxAmmoTypeTier;
		}

		@Override
		public void setMaxAmmoTypeTier(Integer maxAmmoTypeTier) {
			this.maxAmmoTypeTier = maxAmmoTypeTier;
		}

		@Override
		public String getInvisible() {
			return invisible;
		}

		@Override
		public void setInvisible(String invisible) {
			this.invisible = invisible;
		}

		@Override
		public List<String> getSeeInvisibles() {
			return seeInvisibles;
		}

		@Override
		public void addSeeInvisible(String seeInvisible) {
			this.seeInvisibles.add(seeInvisible);
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
		public String getAdvisor() {
			return advisor;
		}

		@Override
		public void setAdvisor(String advisor) {
			this.advisor = advisor;
		}

		@Override
		public Boolean isAnimal() {
			return animal;
		}

		@Override
		public void setAnimal(Boolean animal) {
			this.animal = animal;
		}

		@Override
		public Boolean isFood() {
			return food;
		}

		@Override
		public void setFood(Boolean food) {
			this.food = food;
		}

		@Override
		public Boolean isNoBadGoodies() {
			return noBadGoodies;
		}

		@Override
		public void setNoBadGoodies(Boolean noBadGoodies) {
			this.noBadGoodies = noBadGoodies;
		}

		@Override
		public Boolean isOnlyDefensive() {
			return onlyDefensive;
		}

		@Override
		public void setOnlyDefensive(Boolean onlyDefensive) {
			this.onlyDefensive = onlyDefensive;
		}

		@Override
		public Boolean isNoCapture() {
			return noCapture;
		}

		@Override
		public void setNoCapture(Boolean noCapture) {
			this.noCapture = noCapture;
		}

		@Override
		public Boolean isQuickCombat() {
			return quickCombat;
		}

		@Override
		public void setQuickCombat(Boolean quickCombat) {
			this.quickCombat = quickCombat;
		}

		@Override
		public Boolean isRivalTerritory() {
			return rivalTerritory;
		}

		@Override
		public void setRivalTerritory(Boolean rivalTerritory) {
			this.rivalTerritory = rivalTerritory;
		}

		@Override
		public Boolean isMilitaryHappiness() {
			return militaryHappiness;
		}

		@Override
		public void setMilitaryHappiness(Boolean militaryHappiness) {
			this.militaryHappiness = militaryHappiness;
		}

		@Override
		public Boolean isMilitarySupport() {
			return militarySupport;
		}

		@Override
		public void setMilitarySupport(Boolean militarySupport) {
			this.militarySupport = militarySupport;
		}

		@Override
		public Boolean isMilitaryProduction() {
			return militaryProduction;
		}

		@Override
		public void setMilitaryProduction(Boolean militaryProduction) {
			this.militaryProduction = militaryProduction;
		}

		@Override
		public Boolean isPillage() {
			return pillage;
		}

		@Override
		public void setPillage(Boolean pillage) {
			this.pillage = pillage;
		}

		@Override
		public Boolean isSpy() {
			return spy;
		}

		@Override
		public void setSpy(Boolean spy) {
			this.spy = spy;
		}

		@Override
		public Boolean isSabotage() {
			return sabotage;
		}

		@Override
		public void setSabotage(Boolean sabotage) {
			this.sabotage = sabotage;
		}

		@Override
		public Boolean isDestroy() {
			return destroy;
		}

		@Override
		public void setDestroy(Boolean destroy) {
			this.destroy = destroy;
		}

		@Override
		public Boolean isStealPlans() {
			return stealPlans;
		}

		@Override
		public void setStealPlans(Boolean stealPlans) {
			this.stealPlans = stealPlans;
		}

		@Override
		public Boolean isInvestigate() {
			return investigate;
		}

		@Override
		public void setInvestigate(Boolean investigate) {
			this.investigate = investigate;
		}

		@Override
		public Boolean isCounterSpy() {
			return counterSpy;
		}

		@Override
		public void setCounterSpy(Boolean counterSpy) {
			this.counterSpy = counterSpy;
		}

		@Override
		public Boolean isFound() {
			return found;
		}

		@Override
		public void setFound(Boolean found) {
			this.found = found;
		}

		@Override
		public Boolean isGoldenAge() {
			return goldenAge;
		}

		@Override
		public void setGoldenAge(Boolean goldenAge) {
			this.goldenAge = goldenAge;
		}

		@Override
		public Boolean isInvisibleBool() {
			return invisibleBool;
		}

		@Override
		public void setInvisibleBool(Boolean invisibleBool) {
			this.invisibleBool = invisibleBool;
		}

		@Override
		public Boolean isFirstStrikeImmune() {
			return firstStrikeImmune;
		}

		@Override
		public void setFirstStrikeImmune(Boolean firstStrikeImmune) {
			this.firstStrikeImmune = firstStrikeImmune;
		}

		@Override
		public Boolean isNoDefensiveBonus() {
			return noDefensiveBonus;
		}

		@Override
		public void setNoDefensiveBonus(Boolean noDefensiveBonus) {
			this.noDefensiveBonus = noDefensiveBonus;
		}

		@Override
		public Boolean isIgnoreBuildingDefense() {
			return ignoreBuildingDefense;
		}

		@Override
		public void setIgnoreBuildingDefense(Boolean ignoreBuildingDefense) {
			this.ignoreBuildingDefense = ignoreBuildingDefense;
		}

		@Override
		public Boolean isCanMoveImpassable() {
			return canMoveImpassable;
		}

		@Override
		public void setCanMoveImpassable(Boolean canMoveImpassable) {
			this.canMoveImpassable = canMoveImpassable;
		}

		@Override
		public Boolean isCanMoveAllTerrain() {
			return canMoveAllTerrain;
		}

		@Override
		public void setCanMoveAllTerrain(Boolean canMoveAllTerrain) {
			this.canMoveAllTerrain = canMoveAllTerrain;
		}

		@Override
		public Boolean isFlatMovementCost() {
			return flatMovementCost;
		}

		@Override
		public void setFlatMovementCost(Boolean flatMovementCost) {
			this.flatMovementCost = flatMovementCost;
		}

		@Override
		public Boolean isIgnoreTerrainCost() {
			return ignoreTerrainCost;
		}

		@Override
		public void setIgnoreTerrainCost(Boolean ignoreTerrainCost) {
			this.ignoreTerrainCost = ignoreTerrainCost;
		}

		@Override
		public Boolean isNukeImmune() {
			return nukeImmune;
		}

		@Override
		public void setNukeImmune(Boolean nukeImmune) {
			this.nukeImmune = nukeImmune;
		}

		@Override
		public Boolean isPrereqBonuses() {
			return prereqBonuses;
		}

		@Override
		public void setPrereqBonuses(Boolean prereqBonuses) {
			this.prereqBonuses = prereqBonuses;
		}

		@Override
		public Boolean isPrereqReligionBool() {
			return prereqReligionBool;
		}

		@Override
		public void setPrereqReligionBool(Boolean prereqReligionBool) {
			this.prereqReligionBool = prereqReligionBool;
		}

		@Override
		public Boolean isMechanized() {
			return mechanized;
		}

		@Override
		public void setMechanized(Boolean mechanized) {
			this.mechanized = mechanized;
		}

		@Override
		public Boolean isRenderBelowWater() {
			return renderBelowWater;
		}

		@Override
		public void setRenderBelowWater(Boolean renderBelowWater) {
			this.renderBelowWater = renderBelowWater;
		}

		@Override
		public Boolean isRenderAlways() {
			return renderAlways;
		}

		@Override
		public void setRenderAlways(Boolean renderAlways) {
			this.renderAlways = renderAlways;
		}

		@Override
		public Boolean isSuicide() {
			return suicide;
		}

		@Override
		public void setSuicide(Boolean suicide) {
			this.suicide = suicide;
		}

		@Override
		public Boolean isSingleBuild() {
			return singleBuild;
		}

		@Override
		public void setSingleBuild(Boolean singleBuild) {
			this.singleBuild = singleBuild;
		}

		@Override
		public Boolean isSlave() {
			return slave;
		}

		@Override
		public void setSlave(Boolean slave) {
			this.slave = slave;
		}

		@Override
		public Boolean isLineOfSight() {
			return lineOfSight;
		}

		@Override
		public void setLineOfSight(Boolean lineOfSight) {
			this.lineOfSight = lineOfSight;
		}

		@Override
		public Boolean isHiddenNationality() {
			return hiddenNationality;
		}

		@Override
		public void setHiddenNationality(Boolean hiddenNationality) {
			this.hiddenNationality = hiddenNationality;
		}

		@Override
		public Boolean isAlwaysHostile() {
			return alwaysHostile;
		}

		@Override
		public void setAlwaysHostile(Boolean alwaysHostile) {
			this.alwaysHostile = alwaysHostile;
		}

		@Override
		public Boolean isWorkerTrade() {
			return workerTrade;
		}

		@Override
		public void setWorkerTrade(Boolean workerTrade) {
			this.workerTrade = workerTrade;
		}

		@Override
		public Boolean isMilitaryTrade() {
			return militaryTrade;
		}

		@Override
		public void setMilitaryTrade(Boolean militaryTrade) {
			this.militaryTrade = militaryTrade;
		}

		@Override
		public Boolean isBarbarianLeader() {
			return barbarianLeader;
		}

		@Override
		public void setBarbarianLeader(Boolean barbarianLeader) {
			this.barbarianLeader = barbarianLeader;
		}

		@Override
		public Boolean isCityPacifier() {
			return cityPacifier;
		}

		@Override
		public void setCityPacifier(Boolean cityPacifier) {
			this.cityPacifier = cityPacifier;
		}

		@Override
		public List<String> getUnitClassUpgrades() {
			return unitClassUpgrades;
		}

		@Override
		public void addUnitClassUpgrade(String unitClassUpgrade) {
			this.unitClassUpgrades.add(unitClassUpgrade);
		}

		@Override
		public List<String> getUnitClassTargets() {
			return unitClassTargets;
		}

		@Override
		public void addUnitClassTarget(String unitClassTarget) {
			this.unitClassTargets.add(unitClassTarget);
		}

		@Override
		public List<String> getUnitCombatTargets() {
			return unitCombatTargets;
		}

		@Override
		public void addUnitCombatTarget(String unitCombatTarget) {
			this.unitCombatTargets.add(unitCombatTarget);
		}

		@Override
		public List<String> getUnitClassDefenders() {
			return unitClassDefenders;
		}

		@Override
		public void addUnitClassDefender(String unitClassDefender) {
			this.unitClassDefenders.add(unitClassDefender);
		}

		@Override
		public List<String> getUnitCombatDefenders() {
			return unitCombatDefenders;
		}

		@Override
		public void addUnitCombatDefender(String unitCombatDefender) {
			this.unitCombatDefenders.add(unitCombatDefender);
		}

		@Override
		public List<IPair<String, Integer>> getFlankingStrikes() {
			return flankingStrikes;
		}

		@Override
		public void addFlankingStrike(IPair<String, Integer> flankingStrike) {
			this.flankingStrikes.add(flankingStrike);
		}

		@Override
		public List<String> getUnitAIs() {
			return unitAIs;
		}

		@Override
		public void addUnitAI(String unitAI) {
			this.unitAIs.add(unitAI);
		}

		@Override
		public List<String> getNotUnitAIs() {
			return notUnitAIs;
		}

		@Override
		public void addNotUnitAI(String notUnitAI) {
			this.notUnitAIs.add(notUnitAI);
		}

		@Override
		public List<String> getBuilds() {
			return builds;
		}

		@Override
		public void addBuild(String build) {
			this.builds.add(build);
		}

		@Override
		public List<IPair<String, Integer>> getReligionSpreads() {
			return religionSpreads;
		}

		@Override
		public void addReligionSpread(IPair<String, Integer> religionSpread) {
			this.religionSpreads.add(religionSpread);
		}

		@Override
		public List<IPair<String, Integer>> getCorporationSpreads() {
			return corporationSpreads;
		}

		@Override
		public void addCorporationSpread(IPair<String, Integer> corporationSpread) {
			this.corporationSpreads.add(corporationSpread);
		}

		@Override
		public List<String> getGreatPeoples() {
			return greatPeoples;
		}

		@Override
		public void addGreatPeople(String greatPeople) {
			this.greatPeoples.add(greatPeople);
		}

		@Override
		public String getSlaveSpecialistType() {
			return slaveSpecialistType;
		}

		@Override
		public void setSlaveSpecialistType(String slaveSpecialistType) {
			this.slaveSpecialistType = slaveSpecialistType;
		}

		@Override
		public List<String> getBuildings() {
			return buildings;
		}

		@Override
		public void addBuilding(String building) {
			this.buildings.add(building);
		}

		@Override
		public List<String> getForceBuildings() {
			return forceBuildings;
		}

		@Override
		public void addForceBuilding(String forceBuilding) {
			this.forceBuildings.add(forceBuilding);
		}

		@Override
		public String getHolyCity() {
			return holyCity;
		}

		@Override
		public void setHolyCity(String holyCity) {
			this.holyCity = holyCity;
		}

		@Override
		public String getReligionType() {
			return religionType;
		}

		@Override
		public void setReligionType(String religionType) {
			this.religionType = religionType;
		}

		@Override
		public String getStateReligion() {
			return stateReligion;
		}

		@Override
		public void setStateReligion(String stateReligion) {
			this.stateReligion = stateReligion;
		}

		@Override
		public String getPrereqReligion() {
			return prereqReligion;
		}

		@Override
		public void setPrereqReligion(String prereqReligion) {
			this.prereqReligion = prereqReligion;
		}

		@Override
		public String getPrereqCorporation() {
			return prereqCorporation;
		}

		@Override
		public void setPrereqCorporation(String prereqCorporation) {
			this.prereqCorporation = prereqCorporation;
		}

		@Override
		public String getPrereqBuilding() {
			return prereqBuilding;
		}

		@Override
		public void setPrereqBuilding(String prereqBuilding) {
			this.prereqBuilding = prereqBuilding;
		}

		@Override
		public List<String> getPrereqTechs() {
			return prereqTechs;
		}

		@Override
		public void addPrereqTech(String prereqTech) {
			this.prereqTechs.add(prereqTech);
		}

		@Override
		public String getObsoleteTech() {
			return obsoleteTech;
		}

		@Override
		public void setObsoleteTech(String obsoleteTech) {
			this.obsoleteTech = obsoleteTech;
		}

		@Override
		public String getBonusType() {
			return bonusType;
		}

		@Override
		public void setBonusType(String bonusType) {
			this.bonusType = bonusType;
		}

		@Override
		public List<String> getPrereqOrBonuses() {
			return prereqOrBonuses;
		}

		@Override
		public void addPrereqOrBonus(String prereqOrBonus) {
			this.prereqOrBonuses.add(prereqOrBonus);
		}

		@Override
		public List<String> getPrereqAndCivics() {
			return prereqAndCivics;
		}

		@Override
		public void addPrereqAndCivic(String prereqAndCivic) {
			this.prereqAndCivics.add(prereqAndCivic);
		}

		@Override
		public List<String> getPrereqOrCivics() {
			return prereqOrCivics;
		}

		@Override
		public void addPrereqOrCivic(String prereqOrCivic) {
			this.prereqOrCivics.add(prereqOrCivic);
		}

		@Override
		public List<String> getPrereqAndTerrains() {
			return prereqAndTerrains;
		}

		@Override
		public void addPrereqAndTerrain(String prereqAndTerrain) {
			this.prereqAndTerrains.add(prereqAndTerrain);
		}

		@Override
		public List<String> getPrereqOrTerrains() {
			return prereqOrTerrains;
		}

		@Override
		public void addPrereqOrTerrain(String prereqOrTerrain) {
			this.prereqOrTerrains.add(prereqOrTerrain);
		}

		@Override
		public List<String> getPrereqOrBuildingClasses() {
			return prereqOrBuildingClasses;
		}

		@Override
		public void addPrereqOrBuildingClass(String prereqOrBuildingClass) {
			this.prereqOrBuildingClasses.add(prereqOrBuildingClass);
		}

		@Override
		public List<String> getPrereqNotBuildingClasses() {
			return prereqNotBuildingClasses;
		}

		@Override
		public void addPrereqNotBuildingClass(String prereqNotBuildingClass) {
			this.prereqNotBuildingClasses.add(prereqNotBuildingClass);
		}

		@Override
		public List<String> getPrereqVicinityAndBonus() {
			return prereqVicinityAndBonus;
		}

		@Override
		public void addPrereqVicinityAndBonu(String prereqVicinityAndBonu) {
			this.prereqVicinityAndBonus.add(prereqVicinityAndBonu);
		}

		@Override
		public List<String> getPrereqVicinityOrBonus() {
			return prereqVicinityOrBonus;
		}

		@Override
		public void addPrereqVicinityOrBonu(String prereqVicinityOrBonu) {
			this.prereqVicinityOrBonus.add(prereqVicinityOrBonu);
		}

		@Override
		public List<String> getPrereqVicinityImprovements() {
			return prereqVicinityImprovements;
		}

		@Override
		public void addPrereqVicinityImprovement(String prereqVicinityImprovement) {
			this.prereqVicinityImprovements.add(prereqVicinityImprovement);
		}

		@Override
		public List<String> getPrereqVicinityFeatures() {
			return prereqVicinityFeatures;
		}

		@Override
		public void addPrereqVicinityFeature(String prereqVicinityFeature) {
			this.prereqVicinityFeatures.add(prereqVicinityFeature);
		}

		@Override
		public List<String> getPrereqWorldViews() {
			return prereqWorldViews;
		}

		@Override
		public void addPrereqWorldView(String prereqWorldView) {
			this.prereqWorldViews.add(prereqWorldView);
		}

		@Override
		public Integer getMinPopulation() {
			return minPopulation;
		}

		@Override
		public void setMinPopulation(Integer minPopulation) {
			this.minPopulation = minPopulation;
		}

		@Override
		public String getMinCultureLevel() {
			return minCultureLevel;
		}

		@Override
		public void setMinCultureLevel(String minCultureLevel) {
			this.minCultureLevel = minCultureLevel;
		}

		@Override
		public Boolean isPrereqPower() {
			return prereqPower;
		}

		@Override
		public void setPrereqPower(Boolean prereqPower) {
			this.prereqPower = prereqPower;
		}

		@Override
		public List<IPair<String, Integer>> getProductionTraits() {
			return productionTraits;
		}

		@Override
		public void addProductionTrait(IPair<String, Integer> productionTrait) {
			this.productionTraits.add(productionTrait);
		}

		@Override
		public List<IPair<String, Integer>> getFlavors() {
			return flavors;
		}

		@Override
		public void addFlavor(IPair<String, Integer> flavor) {
			this.flavors.add(flavor);
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
		public Integer getCost() {
			return cost;
		}

		@Override
		public void setCost(Integer cost) {
			this.cost = cost;
		}

		@Override
		public Integer getHurryCostModifier() {
			return hurryCostModifier;
		}

		@Override
		public void setHurryCostModifier(Integer hurryCostModifier) {
			this.hurryCostModifier = hurryCostModifier;
		}

		@Override
		public Integer getAdvancedStartCost() {
			return advancedStartCost;
		}

		@Override
		public void setAdvancedStartCost(Integer advancedStartCost) {
			this.advancedStartCost = advancedStartCost;
		}

		@Override
		public Integer getAdvancedStartCostIncrease() {
			return advancedStartCostIncrease;
		}

		@Override
		public void setAdvancedStartCostIncrease(Integer advancedStartCostIncrease) {
			this.advancedStartCostIncrease = advancedStartCostIncrease;
		}

		@Override
		public Integer getMinAreaSize() {
			return minAreaSize;
		}

		@Override
		public void setMinAreaSize(Integer minAreaSize) {
			this.minAreaSize = minAreaSize;
		}

		@Override
		public Integer getMorale() {
			return morale;
		}
		@Override
		public void setMorale(Integer morale) {
			this.morale = morale;
		}
		@Override
		public Integer getMoves() {
			return moves;
		}

		@Override
		public void setMoves(Integer moves) {
			this.moves = moves;
		}

		@Override
		public String getUnitRangeType() {
			return unitRangeType;
		}

		@Override
		public void setUnitRangeType(String unitRangeType) {
			this.unitRangeType = unitRangeType;
		}

		@Override
		public Boolean isNoRevealMap() {
			return noRevealMap;
		}

		@Override
		public void setNoRevealMap(Boolean noRevealMap) {
			this.noRevealMap = noRevealMap;
		}

		@Override
		public Integer getAirRange() {
			return airRange;
		}

		@Override
		public void setAirRange(Integer airRange) {
			this.airRange = airRange;
		}

		@Override
		public Integer getAirUnitCap() {
			return airUnitCap;
		}

		@Override
		public void setAirUnitCap(Integer airUnitCap) {
			this.airUnitCap = airUnitCap;
		}

		@Override
		public Integer getDropRange() {
			return dropRange;
		}

		@Override
		public void setDropRange(Integer dropRange) {
			this.dropRange = dropRange;
		}

		@Override
		public Integer getNukeRange() {
			return nukeRange;
		}

		@Override
		public void setNukeRange(Integer nukeRange) {
			this.nukeRange = nukeRange;
		}

		@Override
		public Integer getWorkRate() {
			return workRate;
		}

		@Override
		public void setWorkRate(Integer workRate) {
			this.workRate = workRate;
		}

		@Override
		public Integer getBaseDiscover() {
			return baseDiscover;
		}

		@Override
		public void setBaseDiscover(Integer baseDiscover) {
			this.baseDiscover = baseDiscover;
		}

		@Override
		public Integer getDiscoverMultiplier() {
			return discoverMultiplier;
		}

		@Override
		public void setDiscoverMultiplier(Integer discoverMultiplier) {
			this.discoverMultiplier = discoverMultiplier;
		}

		@Override
		public Integer getBaseHurry() {
			return baseHurry;
		}

		@Override
		public void setBaseHurry(Integer baseHurry) {
			this.baseHurry = baseHurry;
		}

		@Override
		public Integer getHurryMultiplier() {
			return hurryMultiplier;
		}

		@Override
		public void setHurryMultiplier(Integer hurryMultiplier) {
			this.hurryMultiplier = hurryMultiplier;
		}

		@Override
		public Integer getBaseTrade() {
			return baseTrade;
		}

		@Override
		public void setBaseTrade(Integer baseTrade) {
			this.baseTrade = baseTrade;
		}

		@Override
		public Integer getTradeMultiplier() {
			return tradeMultiplier;
		}

		@Override
		public void setTradeMultiplier(Integer tradeMultiplier) {
			this.tradeMultiplier = tradeMultiplier;
		}

		@Override
		public Integer getGreatWorkCulture() {
			return greatWorkCulture;
		}

		@Override
		public void setGreatWorkCulture(Integer greatWorkCulture) {
			this.greatWorkCulture = greatWorkCulture;
		}

		@Override
		public Integer getEspionagePoints() {
			return espionagePoints;
		}

		@Override
		public void setEspionagePoints(Integer espionagePoints) {
			this.espionagePoints = espionagePoints;
		}

		@Override
		public Integer getGreatJestHappiness() {
			return greatJestHappiness;
		}

		@Override
		public void setGreatJestHappiness(Integer greatJestHappiness) {
			this.greatJestHappiness = greatJestHappiness;
		}

		@Override
		public Integer getGreatJestDuration() {
			return greatJestDuration;
		}

		@Override
		public void setGreatJestDuration(Integer greatJestDuration) {
			this.greatJestDuration = greatJestDuration;
		}

		@Override
		public List<String> getTerrainImpassables() {
			return terrainImpassables;
		}

		@Override
		public void addTerrainImpassable(String terrainImpassable) {
			this.terrainImpassables.add(terrainImpassable);
		}

		@Override
		public List<String> getFeatureImpassables() {
			return featureImpassables;
		}

		@Override
		public void addFeatureImpassable(String featureImpassable) {
			this.featureImpassables.add(featureImpassable);
		}

		@Override
		public List<IPair<String, String>> getTerrainPassableTechs() {
			return terrainPassableTechs;
		}

		@Override
		public void addTerrainPassableTech(IPair<String, String> terrainPassableTech) {
			this.terrainPassableTechs.add(terrainPassableTech);
		}

		@Override
		public List<IPair<String, String>> getFeaturePassableTechs() {
			return featurePassableTechs;
		}

		@Override
		public void addFeaturePassableTech(IPair<String, String> featurePassableTech) {
			this.featurePassableTechs.add(featurePassableTech);
		}

		@Override
		public Integer getCombatInt() {
			return combatInt;
		}

		@Override
		public void setCombatInt(Integer combatInt) {
			this.combatInt = combatInt;
		}

		@Override
		public Integer getCombatLimit() {
			return combatLimit;
		}

		@Override
		public void setCombatLimit(Integer combatLimit) {
			this.combatLimit = combatLimit;
		}

		@Override
		public Integer getAirCombat() {
			return airCombat;
		}

		@Override
		public void setAirCombat(Integer airCombat) {
			this.airCombat = airCombat;
		}

		@Override
		public Integer getAirCombatLimit() {
			return airCombatLimit;
		}

		@Override
		public void setAirCombatLimit(Integer airCombatLimit) {
			this.airCombatLimit = airCombatLimit;
		}

		@Override
		public Integer getXPValueAttack() {
			return xPValueAttack;
		}

		@Override
		public void setXPValueAttack(Integer xPValueAttack) {
			this.xPValueAttack = xPValueAttack;
		}

		@Override
		public Integer getXPValueDefense() {
			return xPValueDefense;
		}

		@Override
		public void setXPValueDefense(Integer xPValueDefense) {
			this.xPValueDefense = xPValueDefense;
		}

		@Override
		public Integer getFirstStrikes() {
			return firstStrikes;
		}

		@Override
		public void setFirstStrikes(Integer firstStrikes) {
			this.firstStrikes = firstStrikes;
		}

		@Override
		public Integer getChanceFirstStrikes() {
			return chanceFirstStrikes;
		}

		@Override
		public void setChanceFirstStrikes(Integer chanceFirstStrikes) {
			this.chanceFirstStrikes = chanceFirstStrikes;
		}

		@Override
		public Integer getInterceptionProbability() {
			return interceptionProbability;
		}

		@Override
		public void setInterceptionProbability(Integer interceptionProbability) {
			this.interceptionProbability = interceptionProbability;
		}

		@Override
		public Integer getEvasionProbability() {
			return evasionProbability;
		}

		@Override
		public void setEvasionProbability(Integer evasionProbability) {
			this.evasionProbability = evasionProbability;
		}

		@Override
		public Integer getWithdrawalProb() {
			return withdrawalProb;
		}

		@Override
		public void setWithdrawalProb(Integer withdrawalProb) {
			this.withdrawalProb = withdrawalProb;
		}

		@Override
		public Integer getCollateralDamage() {
			return collateralDamage;
		}

		@Override
		public void setCollateralDamage(Integer collateralDamage) {
			this.collateralDamage = collateralDamage;
		}

		@Override
		public Integer getCollateralDamageLimit() {
			return collateralDamageLimit;
		}

		@Override
		public void setCollateralDamageLimit(Integer collateralDamageLimit) {
			this.collateralDamageLimit = collateralDamageLimit;
		}

		@Override
		public Integer getCollateralDamageMaxUnits() {
			return collateralDamageMaxUnits;
		}

		@Override
		public void setCollateralDamageMaxUnits(Integer collateralDamageMaxUnits) {
			this.collateralDamageMaxUnits = collateralDamageMaxUnits;
		}

		@Override
		public Integer getCityAttack() {
			return cityAttack;
		}

		@Override
		public void setCityAttack(Integer cityAttack) {
			this.cityAttack = cityAttack;
		}

		@Override
		public Integer getCityDefense() {
			return cityDefense;
		}

		@Override
		public void setCityDefense(Integer cityDefense) {
			this.cityDefense = cityDefense;
		}

		@Override
		public Integer getAnimalCombat() {
			return animalCombat;
		}

		@Override
		public void setAnimalCombat(Integer animalCombat) {
			this.animalCombat = animalCombat;
		}

		@Override
		public Integer getHillsAttack() {
			return hillsAttack;
		}

		@Override
		public void setHillsAttack(Integer hillsAttack) {
			this.hillsAttack = hillsAttack;
		}

		@Override
		public Integer getHillsDefense() {
			return hillsDefense;
		}

		@Override
		public void setHillsDefense(Integer hillsDefense) {
			this.hillsDefense = hillsDefense;
		}

		@Override
		public List<String> getTerrainNatives() {
			return terrainNatives;
		}

		@Override
		public void addTerrainNative(String terrainNative) {
			this.terrainNatives.add(terrainNative);
		}

		@Override
		public List<String> getFeatureNatives() {
			return featureNatives;
		}

		@Override
		public void addFeatureNative(String featureNative) {
			this.featureNatives.add(featureNative);
		}

		@Override
		public List<IPair<String, Integer>> getTerrainAttacks() {
			return terrainAttacks;
		}

		@Override
		public void addTerrainAttack(IPair<String, Integer> terrainAttack) {
			this.terrainAttacks.add(terrainAttack);
		}

		@Override
		public List<IPair<String, Integer>> getTerrainDefenses() {
			return terrainDefenses;
		}

		@Override
		public void addTerrainDefense(IPair<String, Integer> terrainDefense) {
			this.terrainDefenses.add(terrainDefense);
		}

		@Override
		public List<IPair<String, Integer>> getFeatureAttacks() {
			return featureAttacks;
		}

		@Override
		public void addFeatureAttack(IPair<String, Integer> featureAttack) {
			this.featureAttacks.add(featureAttack);
		}

		@Override
		public List<IPair<String, Integer>> getFeatureDefenses() {
			return featureDefenses;
		}

		@Override
		public void addFeatureDefense(IPair<String, Integer> featureDefense) {
			this.featureDefenses.add(featureDefense);
		}

		@Override
		public List<IPair<String, Integer>> getUnitClassAttackMods() {
			return unitClassAttackMods;
		}

		@Override
		public void addUnitClassAttackMod(IPair<String, Integer> unitClassAttackMod) {
			this.unitClassAttackMods.add(unitClassAttackMod);
		}

		@Override
		public List<IPair<String, Integer>> getUnitClassDefenseMods() {
			return unitClassDefenseMods;
		}

		@Override
		public void addUnitClassDefenseMod(IPair<String, Integer> unitClassDefenseMod) {
			this.unitClassDefenseMods.add(unitClassDefenseMod);
		}

		@Override
		public List<IPair<String, Integer>> getUnitCombatMods() {
			return unitCombatMods;
		}

		@Override
		public void addUnitCombatMod(IPair<String, Integer> unitCombatMod) {
			this.unitCombatMods.add(unitCombatMod);
		}

		@Override
		public List<IPair<String, Integer>> getUnitCombatCollateralImmunes() {
			return unitCombatCollateralImmunes;
		}

		@Override
		public void addUnitCombatCollateralImmune(IPair<String, Integer> unitCombatCollateralImmune) {
			this.unitCombatCollateralImmunes.add(unitCombatCollateralImmune);
		}

		@Override
		public List<IPair<String, Integer>> getDomainMods() {
			return domainMods;
		}

		@Override
		public void addDomainMod(IPair<String, Integer> domainMod) {
			this.domainMods.add(domainMod);
		}

		@Override
		public List<IPair<String, Integer>> getBonusProductionModifiers() {
			return bonusProductionModifiers;
		}

		@Override
		public void addBonusProductionModifier(IPair<String, Integer> bonusProductionModifier) {
			this.bonusProductionModifiers.add(bonusProductionModifier);
		}

		@Override
		public List<Integer> getYieldsFromKill() {
			return yieldsFromKill;
		}

		@Override
		public void addYieldsFromKill(Integer yieldsFromKill) {
			this.yieldsFromKill.add(yieldsFromKill);
		}

		@Override
		public List<Integer> getCommercesFromKill() {
			return commercesFromKill;
		}

		@Override
		public void addCommercesFromKill(Integer commercesFromKill) {
			this.commercesFromKill.add(commercesFromKill);
		}

		@Override
		public Integer getBombRate() {
			return bombRate;
		}

		@Override
		public void setBombRate(Integer bombRate) {
			this.bombRate = bombRate;
		}

		@Override
		public Integer getBombardRate() {
			return bombardRate;
		}

		@Override
		public void setBombardRate(Integer bombardRate) {
			this.bombardRate = bombardRate;
		}

		@Override
		public String getSpecialCargo() {
			return specialCargo;
		}

		@Override
		public void setSpecialCargo(String specialCargo) {
			this.specialCargo = specialCargo;
		}

		@Override
		public String getDomainCargo() {
			return domainCargo;
		}

		@Override
		public void setDomainCargo(String domainCargo) {
			this.domainCargo = domainCargo;
		}

		@Override
		public Integer getCargo() {
			return cargo;
		}

		@Override
		public void setCargo(Integer cargo) {
			this.cargo = cargo;
		}

		@Override
		public Integer getConscription() {
			return conscription;
		}

		@Override
		public void setConscription(Integer conscription) {
			this.conscription = conscription;
		}

		@Override
		public Integer getCultureGarrison() {
			return cultureGarrison;
		}

		@Override
		public void setCultureGarrison(Integer cultureGarrison) {
			this.cultureGarrison = cultureGarrison;
		}

		@Override
		public Integer getExtraCost() {
			return extraCost;
		}

		@Override
		public void setExtraCost(Integer extraCost) {
			this.extraCost = extraCost;
		}

		@Override
		public Integer getAsset() {
			return asset;
		}

		@Override
		public void setAsset(Integer asset) {
			this.asset = asset;
		}

		@Override
		public Integer getPower() {
			return power;
		}

		@Override
		public void setPower(Integer power) {
			this.power = power;
		}

		@Override
		public UnitMeshGroups getUnitMeshGroups() {
			return unitMeshGroups;
		}

		@Override
		public void setUnitMeshGroups(UnitMeshGroups unitMeshGroups) {
			this.unitMeshGroups = unitMeshGroups;
		}

		@Override
		public String getFormationType() {
			return formationType;
		}

		@Override
		public void setFormationType(String formationType) {
			this.formationType = formationType;
		}

		@Override
		public String getHotKey() {
			return hotKey;
		}

		@Override
		public void setHotKey(String hotKey) {
			this.hotKey = hotKey;
		}

		@Override
		public Boolean isAltDown() {
			return altDown;
		}

		@Override
		public void setAltDown(Boolean altDown) {
			this.altDown = altDown;
		}

		@Override
		public Boolean isShiftDown() {
			return shiftDown;
		}

		@Override
		public void setShiftDown(Boolean shiftDown) {
			this.shiftDown = shiftDown;
		}

		@Override
		public Boolean isCtrlDown() {
			return ctrlDown;
		}

		@Override
		public void setCtrlDown(Boolean ctrlDown) {
			this.ctrlDown = ctrlDown;
		}

		@Override
		public Integer getHotKeyPriority() {
			return hotKeyPriority;
		}

		@Override
		public void setHotKeyPriority(Integer hotKeyPriority) {
			this.hotKeyPriority = hotKeyPriority;
		}

		@Override
		public List<String> getFreePromotions() {
			return freePromotions;
		}

		@Override
		public void addFreePromotion(String freePromotion) {
			this.freePromotions.add(freePromotion);
		}

		@Override
		public String getLeaderPromotion() {
			return leaderPromotion;
		}

		@Override
		public void setLeaderPromotion(String leaderPromotion) {
			this.leaderPromotion = leaderPromotion;
		}

		@Override
		public Integer getLeaderExperience() {
			return leaderExperience;
		}

		@Override
		public void setLeaderExperience(Integer leaderExperience) {
			this.leaderExperience = leaderExperience;
		}

		@Override
		public Integer getOrderPriority() {
			return orderPriority;
		}

		@Override
		public void setOrderPriority(Integer orderPriority) {
			this.orderPriority = orderPriority;
		}
	}
}