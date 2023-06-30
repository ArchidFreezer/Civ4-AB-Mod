package org.archid.civ4.info.unit;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.adapters.XmlAdapter;
import org.archid.utils.CollectionUtils;
import org.archid.utils.IPair;
import org.archid.utils.JaxbUtils;
import org.archid.utils.Pair;
import org.archid.utils.StringUtils;

public class UnitMapAdapter extends XmlAdapter<UnitMapAdapter.UnitMap, Map<String, IUnitInfo>> {

	public static class UnitMap {
		@XmlElement(name = "UnitInfo")
		List<AdaptedUnit> entries = new ArrayList<AdaptedUnit>();
	}

	private static class AdaptedUnit {
		@XmlElement(name="Class")
		private String clazz;
		@XmlElement(name="Type")
		private String type;
		@XmlElementWrapper(name="UniqueNames")
		@XmlElement(name="UniqueName")
		private List<String> uniqueNames;
		@XmlElement(name="Special")
		private String special;
		@XmlElement(name="Capture")
		private String capture;
		@XmlElement(name="Combat")
		private String combat;
		@XmlElementWrapper(name="SubCombatTypes")
		@XmlElement(name="UnitCombatType")
		private List<String> subCombatTypes;
		@XmlElement(name="Domain")
		private String domain;
		@XmlElement(name="DefaultUnitAI")
		private String defaultUnitAI;
		@XmlElement(name="bFixedAI")
		private Integer fixedAI;
		@XmlElement(name="iMaxWeaponTypeTier")
		private Integer maxWeaponTypeTier;
		@XmlElement(name="iMaxAmmoTypeTier")
		private Integer maxAmmoTypeTier;
		@XmlElement(name="Invisible")
		private String invisible;
		@XmlElementWrapper(name="SeeInvisibles")
		@XmlElement(name="InvisibleType")
		private List<String> seeInvisibles;
		@XmlElement(name="Description")
		private String description;
		@XmlElement(name="Civilopedia")
		private String civilopedia;
		@XmlElement(name="Strategy")
		private String strategy;
		@XmlElement(name="Help")
		private String help;
		@XmlElement(name="Advisor")
		private String advisor;
		@XmlElement(name="bAnimal")
		private Integer animal;
		@XmlElement(name="bFood")
		private Integer food;
		@XmlElement(name="bNoBadGoodies")
		private Integer noBadGoodies;
		@XmlElement(name="bOnlyDefensive")
		private Integer onlyDefensive;
		@XmlElement(name="bNoCapture")
		private Integer noCapture;
		@XmlElement(name="bQuickCombat")
		private Integer quickCombat;
		@XmlElement(name="bRivalTerritory")
		private Integer rivalTerritory;
		@XmlElement(name="bMilitaryHappiness")
		private Integer militaryHappiness;
		@XmlElement(name="bMilitarySupport")
		private Integer militarySupport;
		@XmlElement(name="bMilitaryProduction")
		private Integer militaryProduction;
		@XmlElement(name="bPillage")
		private Integer pillage;
		@XmlElement(name="bSpy")
		private Integer spy;
		@XmlElement(name="bSabotage")
		private Integer sabotage;
		@XmlElement(name="bDestroy")
		private Integer destroy;
		@XmlElement(name="bStealPlans")
		private Integer stealPlans;
		@XmlElement(name="bInvestigate")
		private Integer investigate;
		@XmlElement(name="bCounterSpy")
		private Integer counterSpy;
		@XmlElement(name="bFound")
		private Integer found;
		@XmlElement(name="bGoldenAge")
		private Integer goldenAge;
		@XmlElement(name="bInvisible")
		private Integer invisibleBool;
		@XmlElement(name="bFirstStrikeImmune")
		private Integer firstStrikeImmune;
		@XmlElement(name="bNoDefensiveBonus")
		private Integer noDefensiveBonus;
		@XmlElement(name="bIgnoreBuildingDefense")
		private Integer ignoreBuildingDefense;
		@XmlElement(name="bCanMoveImpassable")
		private Integer canMoveImpassable;
		@XmlElement(name="bCanMoveAllTerrain")
		private Integer canMoveAllTerrain;
		@XmlElement(name="bFlatMovementCost")
		private Integer flatMovementCost;
		@XmlElement(name="bIgnoreTerrainCost")
		private Integer ignoreTerrainCost;
		@XmlElement(name="bNukeImmune")
		private Integer nukeImmune;
		@XmlElement(name="bPrereqBonuses")
		private Integer prereqBonuses;
		@XmlElement(name="bPrereqReligion")
		private Integer prereqReligionBool;
		@XmlElement(name="bMechanized")
		private Integer mechanized;
		@XmlElement(name="bRenderBelowWater")
		private Integer renderBelowWater;
		@XmlElement(name="bRenderAlways")
		private Integer renderAlways;
		@XmlElement(name="bSuicide")
		private Integer suicide;
		@XmlElement(name="bSingleBuild")
		private Integer singleBuild;
		@XmlElement(name="bSlave")
		private Integer slave;
		@XmlElement(name="bLineOfSight")
		private Integer lineOfSight;
		@XmlElement(name="bHiddenNationality")
		private Integer hiddenNationality;
		@XmlElement(name="bAlwaysHostile")
		private Integer alwaysHostile;
		@XmlElement(name="bWorkerTrade")
		private Integer workerTrade;
		@XmlElement(name="bMilitaryTrade")
		private Integer militaryTrade;
		@XmlElement(name="bBarbarianLeader")
		private Integer barbarianLeader;
		@XmlElement(name="bCityPacifier")
		private Integer cityPacifier;
		@XmlElementWrapper(name="UnitClassUpgrades")
		@XmlElement(name="UnitClassType")
		private List<String> unitClassUpgrades;
		@XmlElementWrapper(name="UnitClassTargets")
		@XmlElement(name="UnitClassType")
		private List<String> unitClassTargets;
		@XmlElementWrapper(name="UnitCombatTargets")
		@XmlElement(name="UnitCombatType")
		private List<String> unitCombatTargets;
		@XmlElementWrapper(name="UnitClassDefenders")
		@XmlElement(name="UnitClassType")
		private List<String> unitClassDefenders;
		@XmlElementWrapper(name="UnitCombatDefenders")
		@XmlElement(name="UnitCombatType")
		private List<String> unitCombatDefenders;
		@XmlElementWrapper(name="FlankingStrikes")
		@XmlElement(name="FlankingStrike")
		private List<AdaptedFlankingStrikes> flankingStrikes;
		@XmlElementWrapper(name="UnitAIs")
		@XmlElement(name="UnitAIType")
		private List<String> unitAIs;
		@XmlElementWrapper(name="NotUnitAIs")
		@XmlElement(name="UnitAIType")
		private List<String> notUnitAIs;
		@XmlElementWrapper(name="Builds")
		@XmlElement(name="BuildType")
		private List<String> builds;
		@XmlElementWrapper(name="ReligionSpreads")
		@XmlElement(name="ReligionSpread")
		private List<AdaptedReligionSpreads> religionSpreads;
		@XmlElementWrapper(name="CorporationSpreads")
		@XmlElement(name="CorporationSpread")
		private List<AdaptedCorporationSpreads> corporationSpreads;
		@XmlElementWrapper(name="GreatPeoples")
		@XmlElement(name="GreatPeopleType")
		private List<String> greatPeoples;
		@XmlElement(name="SlaveSpecialistType")
		private String slaveSpecialistType;
		@XmlElementWrapper(name="Buildings")
		@XmlElement(name="BuildingType")
		private List<String> buildings;
		@XmlElementWrapper(name="ForceBuildings")
		@XmlElement(name="BuildingType")
		private List<String> forceBuildings;
		@XmlElement(name="HolyCity")
		private String holyCity;
		@XmlElement(name="ReligionType")
		private String religionType;
		@XmlElement(name="StateReligion")
		private String stateReligion;
		@XmlElement(name="PrereqReligion")
		private String prereqReligion;
		@XmlElement(name="PrereqCorporation")
		private String prereqCorporation;
		@XmlElement(name="PrereqBuilding")
		private String prereqBuilding;
		@XmlElementWrapper(name="PrereqTechs")
		@XmlElement(name="TechType")
		private List<String> prereqTechs;
		@XmlElement(name="ObsoleteTech")
		private String obsoleteTech;
		@XmlElement(name="BonusType")
		private String bonusType;
		@XmlElementWrapper(name="PrereqOrBonuses")
		@XmlElement(name="BonusType")
		private List<String> prereqOrBonuses;
		@XmlElementWrapper(name="PrereqAndCivics")
		@XmlElement(name="CivicType")
		private List<String> prereqAndCivics;
		@XmlElementWrapper(name="PrereqOrCivics")
		@XmlElement(name="CivicType")
		private List<String> prereqOrCivics;
		@XmlElementWrapper(name="PrereqAndTerrains")
		@XmlElement(name="TerrainType")
		private List<String> prereqAndTerrains;
		@XmlElementWrapper(name="PrereqOrTerrains")
		@XmlElement(name="TerrainType")
		private List<String> prereqOrTerrains;
		@XmlElementWrapper(name="PrereqOrBuildingClasses")
		@XmlElement(name="BuildingClassType")
		private List<String> prereqOrBuildingClasses;
		@XmlElementWrapper(name="PrereqNotBuildingClasses")
		@XmlElement(name="BuildingClassType")
		private List<String> prereqNotBuildingClasses;
		@XmlElementWrapper(name="PrereqVicinityAndBonus")
		@XmlElement(name="BonusType")
		private List<String> prereqVicinityAndBonus;
		@XmlElementWrapper(name="PrereqVicinityOrBonus")
		@XmlElement(name="BonusType")
		private List<String> prereqVicinityOrBonus;
		@XmlElementWrapper(name="PrereqVicinityImprovements")
		@XmlElement(name="ImprovementType")
		private List<String> prereqVicinityImprovements;
		@XmlElementWrapper(name="PrereqVicinityFeatures")
		@XmlElement(name="FeatureType")
		private List<String> prereqVicinityFeatures;
		@XmlElementWrapper(name="PrereqWorldViews")
		@XmlElement(name="WorldViewType")
		private List<String> prereqWorldViews;
		@XmlElement(name="iMinPopulation")
		private Integer minPopulation;
		@XmlElement(name="MinCultureLevel")
		private String minCultureLevel;
		@XmlElement(name="bPrereqPower")
		private Integer prereqPower;
		@XmlElementWrapper(name="ProductionTraits")
		@XmlElement(name="ProductionTrait")
		private List<AdaptedProductionTraits> productionTraits;
		@XmlElementWrapper(name="Flavors")
		@XmlElement(name="Flavor")
		private List<AdaptedFlavors> flavors;
		@XmlElement(name="iAIWeight")
		private Integer aIWeight;
		@XmlElement(name="iCost")
		private Integer cost;
		@XmlElement(name="iHurryCostModifier")
		private Integer hurryCostModifier;
		@XmlElement(name="iAdvancedStartCost")
		private Integer advancedStartCost;
		@XmlElement(name="iAdvancedStartCostIncrease")
		private Integer advancedStartCostIncrease;
		@XmlElement(name="iMinAreaSize")
		private Integer minAreaSize;
		@XmlElement(name="iMorale")
		private Integer morale;
		@XmlElement(name="iMoves")
		private Integer moves;
		@XmlElement(name="UnitRangeType")
		private String unitRangeType;
		@XmlElement(name="bNoRevealMap")
		private Integer noRevealMap;
		@XmlElement(name="iAirRange")
		private Integer airRange;
		@XmlElement(name="iAirUnitCap")
		private Integer airUnitCap;
		@XmlElement(name="iDropRange")
		private Integer dropRange;
		@XmlElement(name="iNukeRange")
		private Integer nukeRange;
		@XmlElement(name="iWorkRate")
		private Integer workRate;
		@XmlElement(name="iBaseDiscover")
		private Integer baseDiscover;
		@XmlElement(name="iDiscoverMultiplier")
		private Integer discoverMultiplier;
		@XmlElement(name="iBaseHurry")
		private Integer baseHurry;
		@XmlElement(name="iHurryMultiplier")
		private Integer hurryMultiplier;
		@XmlElement(name="iBaseTrade")
		private Integer baseTrade;
		@XmlElement(name="iTradeMultiplier")
		private Integer tradeMultiplier;
		@XmlElement(name="iGreatWorkCulture")
		private Integer greatWorkCulture;
		@XmlElement(name="iEspionagePoints")
		private Integer espionagePoints;
		@XmlElement(name="iGreatJestHappiness")
		private Integer greatJestHappiness;
		@XmlElement(name="iGreatJestDuration")
		private Integer greatJestDuration;
		@XmlElementWrapper(name="TerrainImpassables")
		@XmlElement(name="TerrainType")
		private List<String> terrainImpassables;
		@XmlElementWrapper(name="FeatureImpassables")
		@XmlElement(name="FeatureType")
		private List<String> featureImpassables;
		@XmlElementWrapper(name="TerrainPassableTechs")
		@XmlElement(name="TerrainPassableTech")
		private List<AdaptedTerrainPassableTechs> terrainPassableTechs;
		@XmlElementWrapper(name="FeaturePassableTechs")
		@XmlElement(name="FeaturePassableTech")
		private List<AdaptedFeaturePassableTechs> featurePassableTechs;
		@XmlElement(name="iCombat")
		private Integer combatInt;
		@XmlElement(name="iCombatLimit")
		private Integer combatLimit;
		@XmlElement(name="iAirCombat")
		private Integer airCombat;
		@XmlElement(name="iAirCombatLimit")
		private Integer airCombatLimit;
		@XmlElement(name="iXPValueAttack")
		private Integer xPValueAttack;
		@XmlElement(name="iXPValueDefense")
		private Integer xPValueDefense;
		@XmlElement(name="iFirstStrikes")
		private Integer firstStrikes;
		@XmlElement(name="iChanceFirstStrikes")
		private Integer chanceFirstStrikes;
		@XmlElement(name="iInterceptionProbability")
		private Integer interceptionProbability;
		@XmlElement(name="iEvasionProbability")
		private Integer evasionProbability;
		@XmlElement(name="iWithdrawalProb")
		private Integer withdrawalProb;
		@XmlElement(name="iCollateralDamage")
		private Integer collateralDamage;
		@XmlElement(name="iCollateralDamageLimit")
		private Integer collateralDamageLimit;
		@XmlElement(name="iCollateralDamageMaxUnits")
		private Integer collateralDamageMaxUnits;
		@XmlElement(name="iCityAttack")
		private Integer cityAttack;
		@XmlElement(name="iCityDefense")
		private Integer cityDefense;
		@XmlElement(name="iAnimalCombat")
		private Integer animalCombat;
		@XmlElement(name="iHillsAttack")
		private Integer hillsAttack;
		@XmlElement(name="iHillsDefense")
		private Integer hillsDefense;
		@XmlElementWrapper(name="TerrainNatives")
		@XmlElement(name="TerrainType")
		private List<String> terrainNatives;
		@XmlElementWrapper(name="FeatureNatives")
		@XmlElement(name="FeatureType")
		private List<String> featureNatives;
		@XmlElementWrapper(name="TerrainAttacks")
		@XmlElement(name="TerrainAttack")
		private List<AdaptedTerrainAttacks> terrainAttacks;
		@XmlElementWrapper(name="TerrainDefenses")
		@XmlElement(name="TerrainDefense")
		private List<AdaptedTerrainDefenses> terrainDefenses;
		@XmlElementWrapper(name="FeatureAttacks")
		@XmlElement(name="FeatureAttack")
		private List<AdaptedFeatureAttacks> featureAttacks;
		@XmlElementWrapper(name="FeatureDefenses")
		@XmlElement(name="FeatureDefense")
		private List<AdaptedFeatureDefenses> featureDefenses;
		@XmlElementWrapper(name="UnitClassAttackMods")
		@XmlElement(name="UnitClassAttackMod")
		private List<AdaptedUnitClassAttackMods> unitClassAttackMods;
		@XmlElementWrapper(name="UnitClassDefenseMods")
		@XmlElement(name="UnitClassDefenseMod")
		private List<AdaptedUnitClassDefenseMods> unitClassDefenseMods;
		@XmlElementWrapper(name="UnitCombatMods")
		@XmlElement(name="UnitCombatMod")
		private List<AdaptedUnitCombatMods> unitCombatMods;
		@XmlElementWrapper(name="UnitCombatCollateralImmunes")
		@XmlElement(name="UnitCombatCollateralImmune")
		private List<AdaptedUnitCombatCollateralImmunes> unitCombatCollateralImmunes;
		@XmlElementWrapper(name="DomainMods")
		@XmlElement(name="DomainMod")
		private List<AdaptedDomainMods> domainMods;
		@XmlElementWrapper(name="BonusProductionModifiers")
		@XmlElement(name="BonusProductionModifier")
		private List<AdaptedBonusProductionModifiers> bonusProductionModifiers;
		@XmlElementWrapper(name="YieldsFromKill")
		@XmlElement(name="iYield")
		private List<Integer> yieldsFromKill;
		@XmlElementWrapper(name="CommercesFromKill")
		@XmlElement(name="iCommerce")
		private List<Integer> commercesFromKill;
		@XmlElement(name="iBombRate")
		private Integer bombRate;
		@XmlElement(name="iBombardRate")
		private Integer bombardRate;
		@XmlElement(name="SpecialCargo")
		private String specialCargo;
		@XmlElement(name="DomainCargo")
		private String domainCargo;
		@XmlElement(name="iCargo")
		private Integer cargo;
		@XmlElement(name="iConscription")
		private Integer conscription;
		@XmlElement(name="iCultureGarrison")
		private Integer cultureGarrison;
		@XmlElement(name="iExtraCost")
		private Integer extraCost;
		@XmlElement(name="iAsset")
		private Integer asset;
		@XmlElement(name="iPower")
		private Integer power;
		@XmlElement(name="UnitMeshGroups")
		private UnitMeshGroups unitMeshGroups;
		@XmlElement(name="FormationType")
		private String formationType;
		@XmlElement(name="HotKey")
		private String hotKey;
		@XmlElement(name="bAltDown")
		private Integer altDown;
		@XmlElement(name="bShiftDown")
		private Integer shiftDown;
		@XmlElement(name="bCtrlDown")
		private Integer ctrlDown;
		@XmlElement(name="iHotKeyPriority")
		private Integer hotKeyPriority;
		@XmlElementWrapper(name="FreePromotions")
		@XmlElement(name="PromotionType")
		private List<String> freePromotions;
		@XmlElement(name="LeaderPromotion")
		private String leaderPromotion;
		@XmlElement(name="iLeaderExperience")
		private Integer leaderExperience;
		@XmlElement(name="iOrderPriority")
		private Integer orderPriority;
	}

	private static class AdaptedFlankingStrikes {
		@XmlElement(name="FlankingStrikeUnitClass")
		private String flankingStrikeUnitClass;
		@XmlElement(name="iFlankingStrength")
		private Integer iFlankingStrength;
	}

	private static class AdaptedReligionSpreads {
		@XmlElement(name="ReligionType")
		private String religionType;
		@XmlElement(name="iReligionSpread")
		private Integer iReligionSpread;
	}

	private static class AdaptedCorporationSpreads {
		@XmlElement(name="CorporationType")
		private String corporationType;
		@XmlElement(name="iCorporationSpread")
		private Integer iCorporationSpread;
	}

	private static class AdaptedProductionTraits {
		@XmlElement(name="ProductionTraitType")
		private String productionTraitType;
		@XmlElement(name="iProductionTrait")
		private Integer iProductionTrait;
	}

	private static class AdaptedFlavors {
		@XmlElement(name="FlavorType")
		private String flavorType;
		@XmlElement(name="iFlavor")
		private Integer iFlavor;
	}

	private static class AdaptedTerrainPassableTechs {
		@XmlElement(name="TerrainType")
		private String terrainType;
		@XmlElement(name="PassableTech")
		private String passableTech;
	}

	private static class AdaptedFeaturePassableTechs {
		@XmlElement(name="FeatureType")
		private String featureType;
		@XmlElement(name="PassableTech")
		private String passableTech;
	}

	private static class AdaptedTerrainAttacks {
		@XmlElement(name="TerrainType")
		private String terrainType;
		@XmlElement(name="iTerrainAttack")
		private Integer iTerrainAttack;
	}

	private static class AdaptedTerrainDefenses {
		@XmlElement(name="TerrainType")
		private String terrainType;
		@XmlElement(name="iTerrainDefense")
		private Integer iTerrainDefense;
	}

	private static class AdaptedFeatureAttacks {
		@XmlElement(name="FeatureType")
		private String featureType;
		@XmlElement(name="iFeatureAttack")
		private Integer iFeatureAttack;
	}

	private static class AdaptedFeatureDefenses {
		@XmlElement(name="FeatureType")
		private String featureType;
		@XmlElement(name="iFeatureDefense")
		private Integer iFeatureDefense;
	}

	private static class AdaptedUnitClassAttackMods {
		@XmlElement(name="UnitClassType")
		private String unitClassType;
		@XmlElement(name="iUnitClassMod")
		private Integer iUnitClassMod;
	}

	private static class AdaptedUnitClassDefenseMods {
		@XmlElement(name="UnitClassType")
		private String unitClassType;
		@XmlElement(name="iUnitClassMod")
		private Integer iUnitClassMod;
	}

	private static class AdaptedUnitCombatMods {
		@XmlElement(name="UnitCombatType")
		private String unitCombatType;
		@XmlElement(name="iUnitCombatMod")
		private Integer iUnitCombatMod;
	}

	private static class AdaptedUnitCombatCollateralImmunes {
		@XmlElement(name="UnitCombatType")
		private String unitCombatType;
		@XmlElement(name="iUnitCombatCollateralImmune")
		private Integer iUnitCombatCollateralImmune;
	}

	private static class AdaptedDomainMods {
		@XmlElement(name="DomainType")
		private String domainType;
		@XmlElement(name="iDomainMod")
		private Integer iDomainMod;
	}

	private static class AdaptedBonusProductionModifiers {
		@XmlElement(name="BonusType")
		private String bonusType;
		@XmlElement(name="iProductonModifier")
		private Integer iProductonModifier;
	}

	@Override
	public Map<String, IUnitInfo> unmarshal(UnitMap v) throws Exception {
		Map<String, IUnitInfo> map = new TreeMap<String, IUnitInfo>();
		for (AdaptedUnit aInfo: v.entries) {
			IUnitInfo info = UnitInfos.createInfo(aInfo.type);
			info.setClazz(JaxbUtils.unmarshallString(aInfo.clazz));

			if (CollectionUtils.hasElements(aInfo.uniqueNames)) {
				for (String val: aInfo.uniqueNames) {
					if (StringUtils.hasCharacters(val)) {
						info.addUniqueName(JaxbUtils.unmarshallString(val));
					}
				}
			}
			info.setSpecial(JaxbUtils.unmarshallString(aInfo.special));
			info.setCapture(JaxbUtils.unmarshallString(aInfo.capture));
			info.setCombat(JaxbUtils.unmarshallString(aInfo.combat));

			if (CollectionUtils.hasElements(aInfo.subCombatTypes)) {
				for (String val: aInfo.subCombatTypes) {
					if (StringUtils.hasCharacters(val)) {
						info.addSubCombatType(JaxbUtils.unmarshallString(val));
					}
				}
			}
			info.setDomain(JaxbUtils.unmarshallString(aInfo.domain));
			info.setDefaultUnitAI(JaxbUtils.unmarshallString(aInfo.defaultUnitAI));
			info.setFixedAI(JaxbUtils.unmarshallBoolean(aInfo.fixedAI));
			info.setMaxWeaponTypeTier(JaxbUtils.unmarshallInteger(aInfo.maxWeaponTypeTier));
			info.setMaxAmmoTypeTier(JaxbUtils.unmarshallInteger(aInfo.maxAmmoTypeTier));
			info.setInvisible(JaxbUtils.unmarshallString(aInfo.invisible));

			if (CollectionUtils.hasElements(aInfo.seeInvisibles)) {
				for (String val: aInfo.seeInvisibles) {
					if (StringUtils.hasCharacters(val)) {
						info.addSeeInvisible(JaxbUtils.unmarshallString(val));
					}
				}
			}
			info.setDescription(JaxbUtils.unmarshallString(aInfo.description));
			info.setCivilopedia(JaxbUtils.unmarshallString(aInfo.civilopedia));
			info.setStrategy(JaxbUtils.unmarshallString(aInfo.strategy));
			info.setHelp(JaxbUtils.unmarshallString(aInfo.help));
			info.setAdvisor(JaxbUtils.unmarshallString(aInfo.advisor));
			info.setAnimal(JaxbUtils.unmarshallBoolean(aInfo.animal));
			info.setFood(JaxbUtils.unmarshallBoolean(aInfo.food));
			info.setNoBadGoodies(JaxbUtils.unmarshallBoolean(aInfo.noBadGoodies));
			info.setOnlyDefensive(JaxbUtils.unmarshallBoolean(aInfo.onlyDefensive));
			info.setNoCapture(JaxbUtils.unmarshallBoolean(aInfo.noCapture));
			info.setQuickCombat(JaxbUtils.unmarshallBoolean(aInfo.quickCombat));
			info.setRivalTerritory(JaxbUtils.unmarshallBoolean(aInfo.rivalTerritory));
			info.setMilitaryHappiness(JaxbUtils.unmarshallBoolean(aInfo.militaryHappiness));
			info.setMilitarySupport(JaxbUtils.unmarshallBoolean(aInfo.militarySupport));
			info.setMilitaryProduction(JaxbUtils.unmarshallBoolean(aInfo.militaryProduction));
			info.setPillage(JaxbUtils.unmarshallBoolean(aInfo.pillage));
			info.setSpy(JaxbUtils.unmarshallBoolean(aInfo.spy));
			info.setSabotage(JaxbUtils.unmarshallBoolean(aInfo.sabotage));
			info.setDestroy(JaxbUtils.unmarshallBoolean(aInfo.destroy));
			info.setStealPlans(JaxbUtils.unmarshallBoolean(aInfo.stealPlans));
			info.setInvestigate(JaxbUtils.unmarshallBoolean(aInfo.investigate));
			info.setCounterSpy(JaxbUtils.unmarshallBoolean(aInfo.counterSpy));
			info.setFound(JaxbUtils.unmarshallBoolean(aInfo.found));
			info.setGoldenAge(JaxbUtils.unmarshallBoolean(aInfo.goldenAge));
			info.setInvisibleBool(JaxbUtils.unmarshallBoolean(aInfo.invisibleBool));
			info.setFirstStrikeImmune(JaxbUtils.unmarshallBoolean(aInfo.firstStrikeImmune));
			info.setNoDefensiveBonus(JaxbUtils.unmarshallBoolean(aInfo.noDefensiveBonus));
			info.setIgnoreBuildingDefense(JaxbUtils.unmarshallBoolean(aInfo.ignoreBuildingDefense));
			info.setCanMoveImpassable(JaxbUtils.unmarshallBoolean(aInfo.canMoveImpassable));
			info.setCanMoveAllTerrain(JaxbUtils.unmarshallBoolean(aInfo.canMoveAllTerrain));
			info.setFlatMovementCost(JaxbUtils.unmarshallBoolean(aInfo.flatMovementCost));
			info.setIgnoreTerrainCost(JaxbUtils.unmarshallBoolean(aInfo.ignoreTerrainCost));
			info.setNukeImmune(JaxbUtils.unmarshallBoolean(aInfo.nukeImmune));
			info.setPrereqBonuses(JaxbUtils.unmarshallBoolean(aInfo.prereqBonuses));
			info.setPrereqReligionBool(JaxbUtils.unmarshallBoolean(aInfo.prereqReligionBool));
			info.setMechanized(JaxbUtils.unmarshallBoolean(aInfo.mechanized));
			info.setRenderBelowWater(JaxbUtils.unmarshallBoolean(aInfo.renderBelowWater));
			info.setRenderAlways(JaxbUtils.unmarshallBoolean(aInfo.renderAlways));
			info.setSuicide(JaxbUtils.unmarshallBoolean(aInfo.suicide));
			info.setSingleBuild(JaxbUtils.unmarshallBoolean(aInfo.singleBuild));
			info.setSlave(JaxbUtils.unmarshallBoolean(aInfo.slave));
			info.setLineOfSight(JaxbUtils.unmarshallBoolean(aInfo.lineOfSight));
			info.setHiddenNationality(JaxbUtils.unmarshallBoolean(aInfo.hiddenNationality));
			info.setAlwaysHostile(JaxbUtils.unmarshallBoolean(aInfo.alwaysHostile));
			info.setWorkerTrade(JaxbUtils.unmarshallBoolean(aInfo.workerTrade));
			info.setMilitaryTrade(JaxbUtils.unmarshallBoolean(aInfo.militaryTrade));
			info.setBarbarianLeader(JaxbUtils.unmarshallBoolean(aInfo.barbarianLeader));
			info.setCityPacifier(JaxbUtils.unmarshallBoolean(aInfo.cityPacifier));

			if (CollectionUtils.hasElements(aInfo.unitClassUpgrades)) {
				for (String val: aInfo.unitClassUpgrades) {
					if (StringUtils.hasCharacters(val)) {
						info.addUnitClassUpgrade(JaxbUtils.unmarshallString(val));
					}
				}
			}

			if (CollectionUtils.hasElements(aInfo.unitClassTargets)) {
				for (String val: aInfo.unitClassTargets) {
					if (StringUtils.hasCharacters(val)) {
						info.addUnitClassTarget(JaxbUtils.unmarshallString(val));
					}
				}
			}

			if (CollectionUtils.hasElements(aInfo.unitCombatTargets)) {
				for (String val: aInfo.unitCombatTargets) {
					if (StringUtils.hasCharacters(val)) {
						info.addUnitCombatTarget(JaxbUtils.unmarshallString(val));
					}
				}
			}

			if (CollectionUtils.hasElements(aInfo.unitClassDefenders)) {
				for (String val: aInfo.unitClassDefenders) {
					if (StringUtils.hasCharacters(val)) {
						info.addUnitClassDefender(JaxbUtils.unmarshallString(val));
					}
				}
			}

			if (CollectionUtils.hasElements(aInfo.unitCombatDefenders)) {
				for (String val: aInfo.unitCombatDefenders) {
					if (StringUtils.hasCharacters(val)) {
						info.addUnitCombatDefender(JaxbUtils.unmarshallString(val));
					}
				}
			}

			if (CollectionUtils.hasElements(aInfo.flankingStrikes)) {
				for (AdaptedFlankingStrikes adaptor: aInfo.flankingStrikes) {
					if (StringUtils.hasCharacters(adaptor.flankingStrikeUnitClass)) {
						info.addFlankingStrike(new Pair<String, Integer>(adaptor.flankingStrikeUnitClass, adaptor.iFlankingStrength));
					}
				}
			}

			if (CollectionUtils.hasElements(aInfo.unitAIs)) {
				for (String val: aInfo.unitAIs) {
					if (StringUtils.hasCharacters(val)) {
						info.addUnitAI(JaxbUtils.unmarshallString(val));
					}
				}
			}

			if (CollectionUtils.hasElements(aInfo.notUnitAIs)) {
				for (String val: aInfo.notUnitAIs) {
					if (StringUtils.hasCharacters(val)) {
						info.addNotUnitAI(JaxbUtils.unmarshallString(val));
					}
				}
			}

			if (CollectionUtils.hasElements(aInfo.builds)) {
				for (String val: aInfo.builds) {
					if (StringUtils.hasCharacters(val)) {
						info.addBuild(JaxbUtils.unmarshallString(val));
					}
				}
			}

			if (CollectionUtils.hasElements(aInfo.religionSpreads)) {
				for (AdaptedReligionSpreads adaptor: aInfo.religionSpreads) {
					if (StringUtils.hasCharacters(adaptor.religionType)) {
						info.addReligionSpread(new Pair<String, Integer>(adaptor.religionType, adaptor.iReligionSpread));
					}
				}
			}

			if (CollectionUtils.hasElements(aInfo.corporationSpreads)) {
				for (AdaptedCorporationSpreads adaptor: aInfo.corporationSpreads) {
					if (StringUtils.hasCharacters(adaptor.corporationType)) {
						info.addCorporationSpread(new Pair<String, Integer>(adaptor.corporationType, adaptor.iCorporationSpread));
					}
				}
			}

			if (CollectionUtils.hasElements(aInfo.greatPeoples)) {
				for (String val: aInfo.greatPeoples) {
					if (StringUtils.hasCharacters(val)) {
						info.addGreatPeople(JaxbUtils.unmarshallString(val));
					}
				}
			}
			info.setSlaveSpecialistType(JaxbUtils.unmarshallString(aInfo.slaveSpecialistType));

			if (CollectionUtils.hasElements(aInfo.buildings)) {
				for (String val: aInfo.buildings) {
					if (StringUtils.hasCharacters(val)) {
						info.addBuilding(JaxbUtils.unmarshallString(val));
					}
				}
			}

			if (CollectionUtils.hasElements(aInfo.forceBuildings)) {
				for (String val: aInfo.forceBuildings) {
					if (StringUtils.hasCharacters(val)) {
						info.addForceBuilding(JaxbUtils.unmarshallString(val));
					}
				}
			}
			info.setHolyCity(JaxbUtils.unmarshallString(aInfo.holyCity));
			info.setReligionType(JaxbUtils.unmarshallString(aInfo.religionType));
			info.setStateReligion(JaxbUtils.unmarshallString(aInfo.stateReligion));
			info.setPrereqReligion(JaxbUtils.unmarshallString(aInfo.prereqReligion));
			info.setPrereqCorporation(JaxbUtils.unmarshallString(aInfo.prereqCorporation));
			info.setPrereqBuilding(JaxbUtils.unmarshallString(aInfo.prereqBuilding));

			if (CollectionUtils.hasElements(aInfo.prereqTechs)) {
				for (String val: aInfo.prereqTechs) {
					if (StringUtils.hasCharacters(val)) {
						info.addPrereqTech(JaxbUtils.unmarshallString(val));
					}
				}
			}
			info.setObsoleteTech(JaxbUtils.unmarshallString(aInfo.obsoleteTech));
			info.setBonusType(JaxbUtils.unmarshallString(aInfo.bonusType));

			if (CollectionUtils.hasElements(aInfo.prereqOrBonuses)) {
				for (String val: aInfo.prereqOrBonuses) {
					if (StringUtils.hasCharacters(val)) {
						info.addPrereqOrBonus(JaxbUtils.unmarshallString(val));
					}
				}
			}

			if (CollectionUtils.hasElements(aInfo.prereqAndCivics)) {
				for (String val: aInfo.prereqAndCivics) {
					if (StringUtils.hasCharacters(val)) {
						info.addPrereqAndCivic(JaxbUtils.unmarshallString(val));
					}
				}
			}

			if (CollectionUtils.hasElements(aInfo.prereqOrCivics)) {
				for (String val: aInfo.prereqOrCivics) {
					if (StringUtils.hasCharacters(val)) {
						info.addPrereqOrCivic(JaxbUtils.unmarshallString(val));
					}
				}
			}

			if (CollectionUtils.hasElements(aInfo.prereqAndTerrains)) {
				for (String val: aInfo.prereqAndTerrains) {
					if (StringUtils.hasCharacters(val)) {
						info.addPrereqAndTerrain(JaxbUtils.unmarshallString(val));
					}
				}
			}

			if (CollectionUtils.hasElements(aInfo.prereqOrTerrains)) {
				for (String val: aInfo.prereqOrTerrains) {
					if (StringUtils.hasCharacters(val)) {
						info.addPrereqOrTerrain(JaxbUtils.unmarshallString(val));
					}
				}
			}

			if (CollectionUtils.hasElements(aInfo.prereqOrBuildingClasses)) {
				for (String val: aInfo.prereqOrBuildingClasses) {
					if (StringUtils.hasCharacters(val)) {
						info.addPrereqOrBuildingClass(JaxbUtils.unmarshallString(val));
					}
				}
			}

			if (CollectionUtils.hasElements(aInfo.prereqNotBuildingClasses)) {
				for (String val: aInfo.prereqNotBuildingClasses) {
					if (StringUtils.hasCharacters(val)) {
						info.addPrereqNotBuildingClass(JaxbUtils.unmarshallString(val));
					}
				}
			}

			if (CollectionUtils.hasElements(aInfo.prereqVicinityAndBonus)) {
				for (String val: aInfo.prereqVicinityAndBonus) {
					if (StringUtils.hasCharacters(val)) {
						info.addPrereqVicinityAndBonu(JaxbUtils.unmarshallString(val));
					}
				}
			}

			if (CollectionUtils.hasElements(aInfo.prereqVicinityOrBonus)) {
				for (String val: aInfo.prereqVicinityOrBonus) {
					if (StringUtils.hasCharacters(val)) {
						info.addPrereqVicinityOrBonu(JaxbUtils.unmarshallString(val));
					}
				}
			}

			if (CollectionUtils.hasElements(aInfo.prereqVicinityImprovements)) {
				for (String val: aInfo.prereqVicinityImprovements) {
					if (StringUtils.hasCharacters(val)) {
						info.addPrereqVicinityImprovement(JaxbUtils.unmarshallString(val));
					}
				}
			}

			if (CollectionUtils.hasElements(aInfo.prereqVicinityFeatures)) {
				for (String val: aInfo.prereqVicinityFeatures) {
					if (StringUtils.hasCharacters(val)) {
						info.addPrereqVicinityFeature(JaxbUtils.unmarshallString(val));
					}
				}
			}

			if (CollectionUtils.hasElements(aInfo.prereqWorldViews)) {
				for (String val: aInfo.prereqWorldViews) {
					if (StringUtils.hasCharacters(val)) {
						info.addPrereqWorldView(JaxbUtils.unmarshallString(val));
					}
				}
			}
			info.setMinPopulation(JaxbUtils.unmarshallInteger(aInfo.minPopulation));
			info.setMinCultureLevel(JaxbUtils.unmarshallString(aInfo.minCultureLevel));
			info.setPrereqPower(JaxbUtils.unmarshallBoolean(aInfo.prereqPower));

			if (CollectionUtils.hasElements(aInfo.productionTraits)) {
				for (AdaptedProductionTraits adaptor: aInfo.productionTraits) {
					if (StringUtils.hasCharacters(adaptor.productionTraitType)) {
						info.addProductionTrait(new Pair<String, Integer>(adaptor.productionTraitType, adaptor.iProductionTrait));
					}
				}
			}

			if (CollectionUtils.hasElements(aInfo.flavors)) {
				for (AdaptedFlavors adaptor: aInfo.flavors) {
					if (StringUtils.hasCharacters(adaptor.flavorType)) {
						info.addFlavor(new Pair<String, Integer>(adaptor.flavorType, adaptor.iFlavor));
					}
				}
			}
			info.setAIWeight(JaxbUtils.unmarshallInteger(aInfo.aIWeight));
			info.setCost(JaxbUtils.unmarshallInteger(aInfo.cost));
			info.setHurryCostModifier(JaxbUtils.unmarshallInteger(aInfo.hurryCostModifier));
			info.setAdvancedStartCost(JaxbUtils.unmarshallInteger(aInfo.advancedStartCost));
			info.setAdvancedStartCostIncrease(JaxbUtils.unmarshallInteger(aInfo.advancedStartCostIncrease));
			info.setMinAreaSize(JaxbUtils.unmarshallInteger(aInfo.minAreaSize));
			info.setMorale(JaxbUtils.unmarshallInteger(aInfo.morale));
			info.setMoves(JaxbUtils.unmarshallInteger(aInfo.moves));
			info.setUnitRangeType(JaxbUtils.unmarshallString(aInfo.unitRangeType));
			info.setNoRevealMap(JaxbUtils.unmarshallBoolean(aInfo.noRevealMap));
			info.setAirRange(JaxbUtils.unmarshallInteger(aInfo.airRange));
			info.setAirUnitCap(JaxbUtils.unmarshallInteger(aInfo.airUnitCap));
			info.setDropRange(JaxbUtils.unmarshallInteger(aInfo.dropRange));
			info.setNukeRange(JaxbUtils.unmarshallInteger(aInfo.nukeRange));
			info.setWorkRate(JaxbUtils.unmarshallInteger(aInfo.workRate));
			info.setBaseDiscover(JaxbUtils.unmarshallInteger(aInfo.baseDiscover));
			info.setDiscoverMultiplier(JaxbUtils.unmarshallInteger(aInfo.discoverMultiplier));
			info.setBaseHurry(JaxbUtils.unmarshallInteger(aInfo.baseHurry));
			info.setHurryMultiplier(JaxbUtils.unmarshallInteger(aInfo.hurryMultiplier));
			info.setBaseTrade(JaxbUtils.unmarshallInteger(aInfo.baseTrade));
			info.setTradeMultiplier(JaxbUtils.unmarshallInteger(aInfo.tradeMultiplier));
			info.setGreatWorkCulture(JaxbUtils.unmarshallInteger(aInfo.greatWorkCulture));
			info.setEspionagePoints(JaxbUtils.unmarshallInteger(aInfo.espionagePoints));
			info.setGreatJestHappiness(JaxbUtils.unmarshallInteger(aInfo.greatJestHappiness));
			info.setGreatJestDuration(JaxbUtils.unmarshallInteger(aInfo.greatJestDuration));

			if (CollectionUtils.hasElements(aInfo.terrainImpassables)) {
				for (String val: aInfo.terrainImpassables) {
					if (StringUtils.hasCharacters(val)) {
						info.addTerrainImpassable(JaxbUtils.unmarshallString(val));
					}
				}
			}

			if (CollectionUtils.hasElements(aInfo.featureImpassables)) {
				for (String val: aInfo.featureImpassables) {
					if (StringUtils.hasCharacters(val)) {
						info.addFeatureImpassable(JaxbUtils.unmarshallString(val));
					}
				}
			}

			if (CollectionUtils.hasElements(aInfo.terrainPassableTechs)) {
				for (AdaptedTerrainPassableTechs adaptor: aInfo.terrainPassableTechs) {
					if (StringUtils.hasCharacters(adaptor.terrainType)) {
						info.addTerrainPassableTech(new Pair<String, String>(adaptor.terrainType, adaptor.passableTech));
					}
				}
			}

			if (CollectionUtils.hasElements(aInfo.featurePassableTechs)) {
				for (AdaptedFeaturePassableTechs adaptor: aInfo.featurePassableTechs) {
					if (StringUtils.hasCharacters(adaptor.featureType)) {
						info.addFeaturePassableTech(new Pair<String, String>(adaptor.featureType, adaptor.passableTech));
					}
				}
			}
			info.setCombatInt(JaxbUtils.unmarshallInteger(aInfo.combatInt));
			info.setCombatLimit(JaxbUtils.unmarshallInteger(aInfo.combatLimit));
			info.setAirCombat(JaxbUtils.unmarshallInteger(aInfo.airCombat));
			info.setAirCombatLimit(JaxbUtils.unmarshallInteger(aInfo.airCombatLimit));
			info.setXPValueAttack(JaxbUtils.unmarshallInteger(aInfo.xPValueAttack));
			info.setXPValueDefense(JaxbUtils.unmarshallInteger(aInfo.xPValueDefense));
			info.setFirstStrikes(JaxbUtils.unmarshallInteger(aInfo.firstStrikes));
			info.setChanceFirstStrikes(JaxbUtils.unmarshallInteger(aInfo.chanceFirstStrikes));
			info.setInterceptionProbability(JaxbUtils.unmarshallInteger(aInfo.interceptionProbability));
			info.setEvasionProbability(JaxbUtils.unmarshallInteger(aInfo.evasionProbability));
			info.setWithdrawalProb(JaxbUtils.unmarshallInteger(aInfo.withdrawalProb));
			info.setCollateralDamage(JaxbUtils.unmarshallInteger(aInfo.collateralDamage));
			info.setCollateralDamageLimit(JaxbUtils.unmarshallInteger(aInfo.collateralDamageLimit));
			info.setCollateralDamageMaxUnits(JaxbUtils.unmarshallInteger(aInfo.collateralDamageMaxUnits));
			info.setCityAttack(JaxbUtils.unmarshallInteger(aInfo.cityAttack));
			info.setCityDefense(JaxbUtils.unmarshallInteger(aInfo.cityDefense));
			info.setAnimalCombat(JaxbUtils.unmarshallInteger(aInfo.animalCombat));
			info.setHillsAttack(JaxbUtils.unmarshallInteger(aInfo.hillsAttack));
			info.setHillsDefense(JaxbUtils.unmarshallInteger(aInfo.hillsDefense));

			if (CollectionUtils.hasElements(aInfo.terrainNatives)) {
				for (String val: aInfo.terrainNatives) {
					if (StringUtils.hasCharacters(val)) {
						info.addTerrainNative(JaxbUtils.unmarshallString(val));
					}
				}
			}

			if (CollectionUtils.hasElements(aInfo.featureNatives)) {
				for (String val: aInfo.featureNatives) {
					if (StringUtils.hasCharacters(val)) {
						info.addFeatureNative(JaxbUtils.unmarshallString(val));
					}
				}
			}

			if (CollectionUtils.hasElements(aInfo.terrainAttacks)) {
				for (AdaptedTerrainAttacks adaptor: aInfo.terrainAttacks) {
					if (StringUtils.hasCharacters(adaptor.terrainType)) {
						info.addTerrainAttack(new Pair<String, Integer>(adaptor.terrainType, adaptor.iTerrainAttack));
					}
				}
			}

			if (CollectionUtils.hasElements(aInfo.terrainDefenses)) {
				for (AdaptedTerrainDefenses adaptor: aInfo.terrainDefenses) {
					if (StringUtils.hasCharacters(adaptor.terrainType)) {
						info.addTerrainDefense(new Pair<String, Integer>(adaptor.terrainType, adaptor.iTerrainDefense));
					}
				}
			}

			if (CollectionUtils.hasElements(aInfo.featureAttacks)) {
				for (AdaptedFeatureAttacks adaptor: aInfo.featureAttacks) {
					if (StringUtils.hasCharacters(adaptor.featureType)) {
						info.addFeatureAttack(new Pair<String, Integer>(adaptor.featureType, adaptor.iFeatureAttack));
					}
				}
			}

			if (CollectionUtils.hasElements(aInfo.featureDefenses)) {
				for (AdaptedFeatureDefenses adaptor: aInfo.featureDefenses) {
					if (StringUtils.hasCharacters(adaptor.featureType)) {
						info.addFeatureDefense(new Pair<String, Integer>(adaptor.featureType, adaptor.iFeatureDefense));
					}
				}
			}

			if (CollectionUtils.hasElements(aInfo.unitClassAttackMods)) {
				for (AdaptedUnitClassAttackMods adaptor: aInfo.unitClassAttackMods) {
					if (StringUtils.hasCharacters(adaptor.unitClassType)) {
						info.addUnitClassAttackMod(new Pair<String, Integer>(adaptor.unitClassType, adaptor.iUnitClassMod));
					}
				}
			}

			if (CollectionUtils.hasElements(aInfo.unitClassDefenseMods)) {
				for (AdaptedUnitClassDefenseMods adaptor: aInfo.unitClassDefenseMods) {
					if (StringUtils.hasCharacters(adaptor.unitClassType)) {
						info.addUnitClassDefenseMod(new Pair<String, Integer>(adaptor.unitClassType, adaptor.iUnitClassMod));
					}
				}
			}

			if (CollectionUtils.hasElements(aInfo.unitCombatMods)) {
				for (AdaptedUnitCombatMods adaptor: aInfo.unitCombatMods) {
					if (StringUtils.hasCharacters(adaptor.unitCombatType)) {
						info.addUnitCombatMod(new Pair<String, Integer>(adaptor.unitCombatType, adaptor.iUnitCombatMod));
					}
				}
			}

			if (CollectionUtils.hasElements(aInfo.unitCombatCollateralImmunes)) {
				for (AdaptedUnitCombatCollateralImmunes adaptor: aInfo.unitCombatCollateralImmunes) {
					if (StringUtils.hasCharacters(adaptor.unitCombatType)) {
						info.addUnitCombatCollateralImmune(new Pair<String, Integer>(adaptor.unitCombatType, adaptor.iUnitCombatCollateralImmune));
					}
				}
			}

			if (CollectionUtils.hasElements(aInfo.domainMods)) {
				for (AdaptedDomainMods adaptor: aInfo.domainMods) {
					if (StringUtils.hasCharacters(adaptor.domainType)) {
						info.addDomainMod(new Pair<String, Integer>(adaptor.domainType, adaptor.iDomainMod));
					}
				}
			}

			if (CollectionUtils.hasElements(aInfo.bonusProductionModifiers)) {
				for (AdaptedBonusProductionModifiers adaptor: aInfo.bonusProductionModifiers) {
					if (StringUtils.hasCharacters(adaptor.bonusType)) {
						info.addBonusProductionModifier(new Pair<String, Integer>(adaptor.bonusType, adaptor.iProductonModifier));
					}
				}
			}

			if (CollectionUtils.hasElements(aInfo.yieldsFromKill)) {
				for (Integer val: aInfo.yieldsFromKill) {
					info.addYieldsFromKill(JaxbUtils.unmarshallInteger(val));
				}
			}

			if (CollectionUtils.hasElements(aInfo.commercesFromKill)) {
				for (Integer val: aInfo.commercesFromKill) {
					info.addCommercesFromKill(JaxbUtils.unmarshallInteger(val));
				}
			}
			info.setBombRate(JaxbUtils.unmarshallInteger(aInfo.bombRate));
			info.setBombardRate(JaxbUtils.unmarshallInteger(aInfo.bombardRate));
			info.setSpecialCargo(JaxbUtils.unmarshallString(aInfo.specialCargo));
			info.setDomainCargo(JaxbUtils.unmarshallString(aInfo.domainCargo));
			info.setCargo(JaxbUtils.unmarshallInteger(aInfo.cargo));
			info.setConscription(JaxbUtils.unmarshallInteger(aInfo.conscription));
			info.setCultureGarrison(JaxbUtils.unmarshallInteger(aInfo.cultureGarrison));
			info.setExtraCost(JaxbUtils.unmarshallInteger(aInfo.extraCost));
			info.setAsset(JaxbUtils.unmarshallInteger(aInfo.asset));
			info.setPower(JaxbUtils.unmarshallInteger(aInfo.power));
			info.setUnitMeshGroups(aInfo.unitMeshGroups);
			info.setFormationType(JaxbUtils.unmarshallString(aInfo.formationType));
			info.setHotKey(JaxbUtils.unmarshallString(aInfo.hotKey));
			info.setAltDown(JaxbUtils.unmarshallBoolean(aInfo.altDown));
			info.setShiftDown(JaxbUtils.unmarshallBoolean(aInfo.shiftDown));
			info.setCtrlDown(JaxbUtils.unmarshallBoolean(aInfo.ctrlDown));
			info.setHotKeyPriority(JaxbUtils.unmarshallInteger(aInfo.hotKeyPriority));

			if (CollectionUtils.hasElements(aInfo.freePromotions)) {
				for (String val: aInfo.freePromotions) {
					if (StringUtils.hasCharacters(val)) {
						info.addFreePromotion(JaxbUtils.unmarshallString(val));
					}
				}
			}
			info.setLeaderPromotion(JaxbUtils.unmarshallString(aInfo.leaderPromotion));
			info.setLeaderExperience(JaxbUtils.unmarshallInteger(aInfo.leaderExperience));
			info.setOrderPriority(JaxbUtils.unmarshallInteger(aInfo.orderPriority));

			map.put(aInfo.type, info);
		}
		return map;
	}

	@Override
	public UnitMap marshal(Map<String, IUnitInfo> v) throws Exception {
		UnitMap map = new UnitMap();
		for (IUnitInfo info: v.values()) {
			AdaptedUnit aInfo = new AdaptedUnit();
			aInfo.type = JaxbUtils.marshallString(info.getType());
			aInfo.clazz = JaxbUtils.marshallMandatoryString(info.getClazz());

			if (CollectionUtils.hasElements(info.getUniqueNames())) {
				aInfo.uniqueNames = new ArrayList<String>();
				for(String val: info.getUniqueNames()) {
					aInfo.uniqueNames.add(JaxbUtils.marshallMandatoryString(val));
				}
			}
			aInfo.special = JaxbUtils.marshallString(info.getSpecial());
			aInfo.capture = JaxbUtils.marshallString(info.getCapture());
			aInfo.combat = JaxbUtils.marshallString(info.getCombat());

			if (CollectionUtils.hasElements(info.getSubCombatTypes())) {
				aInfo.subCombatTypes = new ArrayList<String>();
				for(String val: info.getSubCombatTypes()) {
					aInfo.subCombatTypes.add(JaxbUtils.marshallMandatoryString(val));
				}
			}
			aInfo.domain = JaxbUtils.marshallMandatoryString(info.getDomain());
			aInfo.defaultUnitAI = JaxbUtils.marshallMandatoryString(info.getDefaultUnitAI());
			aInfo.fixedAI = JaxbUtils.marshallBoolean(info.isFixedAI());
			aInfo.maxWeaponTypeTier = JaxbUtils.marshallInteger(info.getMaxWeaponTypeTier());
			aInfo.maxAmmoTypeTier = JaxbUtils.marshallInteger(info.getMaxAmmoTypeTier());
			aInfo.invisible = JaxbUtils.marshallString(info.getInvisible());

			if (CollectionUtils.hasElements(info.getSeeInvisibles())) {
				aInfo.seeInvisibles = new ArrayList<String>();
				for(String val: info.getSeeInvisibles()) {
					aInfo.seeInvisibles.add(JaxbUtils.marshallMandatoryString(val));
				}
			}
			aInfo.description = JaxbUtils.marshallString(info.getDescription());
			aInfo.civilopedia = JaxbUtils.marshallString(info.getCivilopedia());
			aInfo.strategy = JaxbUtils.marshallString(info.getStrategy());
			aInfo.help = JaxbUtils.marshallString(info.getHelp());
			aInfo.advisor = JaxbUtils.marshallString(info.getAdvisor());
			aInfo.animal = JaxbUtils.marshallBoolean(info.isAnimal());
			aInfo.food = JaxbUtils.marshallBoolean(info.isFood());
			aInfo.noBadGoodies = JaxbUtils.marshallBoolean(info.isNoBadGoodies());
			aInfo.onlyDefensive = JaxbUtils.marshallBoolean(info.isOnlyDefensive());
			aInfo.noCapture = JaxbUtils.marshallBoolean(info.isNoCapture());
			aInfo.quickCombat = JaxbUtils.marshallBoolean(info.isQuickCombat());
			aInfo.rivalTerritory = JaxbUtils.marshallBoolean(info.isRivalTerritory());
			aInfo.militaryHappiness = JaxbUtils.marshallBoolean(info.isMilitaryHappiness());
			aInfo.militarySupport = JaxbUtils.marshallBoolean(info.isMilitarySupport());
			aInfo.militaryProduction = JaxbUtils.marshallBoolean(info.isMilitaryProduction());
			aInfo.pillage = JaxbUtils.marshallBoolean(info.isPillage());
			aInfo.spy = JaxbUtils.marshallBoolean(info.isSpy());
			aInfo.sabotage = JaxbUtils.marshallBoolean(info.isSabotage());
			aInfo.destroy = JaxbUtils.marshallBoolean(info.isDestroy());
			aInfo.stealPlans = JaxbUtils.marshallBoolean(info.isStealPlans());
			aInfo.investigate = JaxbUtils.marshallBoolean(info.isInvestigate());
			aInfo.counterSpy = JaxbUtils.marshallBoolean(info.isCounterSpy());
			aInfo.found = JaxbUtils.marshallBoolean(info.isFound());
			aInfo.goldenAge = JaxbUtils.marshallBoolean(info.isGoldenAge());
			aInfo.invisibleBool = JaxbUtils.marshallBoolean(info.isInvisibleBool());
			aInfo.firstStrikeImmune = JaxbUtils.marshallBoolean(info.isFirstStrikeImmune());
			aInfo.noDefensiveBonus = JaxbUtils.marshallBoolean(info.isNoDefensiveBonus());
			aInfo.ignoreBuildingDefense = JaxbUtils.marshallBoolean(info.isIgnoreBuildingDefense());
			aInfo.canMoveImpassable = JaxbUtils.marshallBoolean(info.isCanMoveImpassable());
			aInfo.canMoveAllTerrain = JaxbUtils.marshallBoolean(info.isCanMoveAllTerrain());
			aInfo.flatMovementCost = JaxbUtils.marshallBoolean(info.isFlatMovementCost());
			aInfo.ignoreTerrainCost = JaxbUtils.marshallBoolean(info.isIgnoreTerrainCost());
			aInfo.nukeImmune = JaxbUtils.marshallBoolean(info.isNukeImmune());
			aInfo.prereqBonuses = JaxbUtils.marshallBoolean(info.isPrereqBonuses());
			aInfo.prereqReligionBool = JaxbUtils.marshallBoolean(info.isPrereqReligionBool());
			aInfo.mechanized = JaxbUtils.marshallBoolean(info.isMechanized());
			aInfo.renderBelowWater = JaxbUtils.marshallBoolean(info.isRenderBelowWater());
			aInfo.renderAlways = JaxbUtils.marshallBoolean(info.isRenderAlways());
			aInfo.suicide = JaxbUtils.marshallBoolean(info.isSuicide());
			aInfo.singleBuild = JaxbUtils.marshallBoolean(info.isSingleBuild());
			aInfo.slave = JaxbUtils.marshallBoolean(info.isSlave());
			aInfo.lineOfSight = JaxbUtils.marshallBoolean(info.isLineOfSight());
			aInfo.hiddenNationality = JaxbUtils.marshallBoolean(info.isHiddenNationality());
			aInfo.alwaysHostile = JaxbUtils.marshallBoolean(info.isAlwaysHostile());
			aInfo.workerTrade = JaxbUtils.marshallBoolean(info.isWorkerTrade());
			aInfo.militaryTrade = JaxbUtils.marshallBoolean(info.isMilitaryTrade());
			aInfo.barbarianLeader = JaxbUtils.marshallBoolean(info.isBarbarianLeader());
			aInfo.cityPacifier = JaxbUtils.marshallBoolean(info.isCityPacifier());

			if (CollectionUtils.hasElements(info.getUnitClassUpgrades())) {
				aInfo.unitClassUpgrades = new ArrayList<String>();
				for(String val: info.getUnitClassUpgrades()) {
					aInfo.unitClassUpgrades.add(JaxbUtils.marshallMandatoryString(val));
				}
			}

			if (CollectionUtils.hasElements(info.getUnitClassTargets())) {
				aInfo.unitClassTargets = new ArrayList<String>();
				for(String val: info.getUnitClassTargets()) {
					aInfo.unitClassTargets.add(JaxbUtils.marshallMandatoryString(val));
				}
			}

			if (CollectionUtils.hasElements(info.getUnitCombatTargets())) {
				aInfo.unitCombatTargets = new ArrayList<String>();
				for(String val: info.getUnitCombatTargets()) {
					aInfo.unitCombatTargets.add(JaxbUtils.marshallMandatoryString(val));
				}
			}

			if (CollectionUtils.hasElements(info.getUnitClassDefenders())) {
				aInfo.unitClassDefenders = new ArrayList<String>();
				for(String val: info.getUnitClassDefenders()) {
					aInfo.unitClassDefenders.add(JaxbUtils.marshallMandatoryString(val));
				}
			}

			if (CollectionUtils.hasElements(info.getUnitCombatDefenders())) {
				aInfo.unitCombatDefenders = new ArrayList<String>();
				for(String val: info.getUnitCombatDefenders()) {
					aInfo.unitCombatDefenders.add(JaxbUtils.marshallMandatoryString(val));
				}
			}

			if (CollectionUtils.hasElements(info.getFlankingStrikes())) {
				aInfo.flankingStrikes = new ArrayList<AdaptedFlankingStrikes>();
				for (IPair<String, Integer> pair: info.getFlankingStrikes()) {
					AdaptedFlankingStrikes adaptor = new AdaptedFlankingStrikes();
					adaptor.flankingStrikeUnitClass = pair.getKey();
					adaptor.iFlankingStrength = pair.getValue();
					aInfo.flankingStrikes.add(adaptor);
				}
			}

			if (CollectionUtils.hasElements(info.getUnitAIs())) {
				aInfo.unitAIs = new ArrayList<String>();
				for(String val: info.getUnitAIs()) {
					aInfo.unitAIs.add(JaxbUtils.marshallMandatoryString(val));
				}
			}

			if (CollectionUtils.hasElements(info.getNotUnitAIs())) {
				aInfo.notUnitAIs = new ArrayList<String>();
				for(String val: info.getNotUnitAIs()) {
					aInfo.notUnitAIs.add(JaxbUtils.marshallMandatoryString(val));
				}
			}

			if (CollectionUtils.hasElements(info.getBuilds())) {
				aInfo.builds = new ArrayList<String>();
				for(String val: info.getBuilds()) {
					aInfo.builds.add(JaxbUtils.marshallMandatoryString(val));
				}
			}

			if (CollectionUtils.hasElements(info.getReligionSpreads())) {
				aInfo.religionSpreads = new ArrayList<AdaptedReligionSpreads>();
				for (IPair<String, Integer> pair: info.getReligionSpreads()) {
					AdaptedReligionSpreads adaptor = new AdaptedReligionSpreads();
					adaptor.religionType = pair.getKey();
					adaptor.iReligionSpread = pair.getValue();
					aInfo.religionSpreads.add(adaptor);
				}
			}

			if (CollectionUtils.hasElements(info.getCorporationSpreads())) {
				aInfo.corporationSpreads = new ArrayList<AdaptedCorporationSpreads>();
				for (IPair<String, Integer> pair: info.getCorporationSpreads()) {
					AdaptedCorporationSpreads adaptor = new AdaptedCorporationSpreads();
					adaptor.corporationType = pair.getKey();
					adaptor.iCorporationSpread = pair.getValue();
					aInfo.corporationSpreads.add(adaptor);
				}
			}

			if (CollectionUtils.hasElements(info.getGreatPeoples())) {
				aInfo.greatPeoples = new ArrayList<String>();
				for(String val: info.getGreatPeoples()) {
					aInfo.greatPeoples.add(JaxbUtils.marshallMandatoryString(val));
				}
			}
			aInfo.slaveSpecialistType = JaxbUtils.marshallString(info.getSlaveSpecialistType());

			if (CollectionUtils.hasElements(info.getBuildings())) {
				aInfo.buildings = new ArrayList<String>();
				for(String val: info.getBuildings()) {
					aInfo.buildings.add(JaxbUtils.marshallMandatoryString(val));
				}
			}

			if (CollectionUtils.hasElements(info.getForceBuildings())) {
				aInfo.forceBuildings = new ArrayList<String>();
				for(String val: info.getForceBuildings()) {
					aInfo.forceBuildings.add(JaxbUtils.marshallMandatoryString(val));
				}
			}
			aInfo.holyCity = JaxbUtils.marshallString(info.getHolyCity());
			aInfo.religionType = JaxbUtils.marshallString(info.getReligionType());
			aInfo.stateReligion = JaxbUtils.marshallString(info.getStateReligion());
			aInfo.prereqReligion = JaxbUtils.marshallString(info.getPrereqReligion());
			aInfo.prereqCorporation = JaxbUtils.marshallString(info.getPrereqCorporation());
			aInfo.prereqBuilding = JaxbUtils.marshallString(info.getPrereqBuilding());

			if (CollectionUtils.hasElements(info.getPrereqTechs())) {
				aInfo.prereqTechs = new ArrayList<String>();
				for(String val: info.getPrereqTechs()) {
					aInfo.prereqTechs.add(JaxbUtils.marshallMandatoryString(val));
				}
			}
			aInfo.obsoleteTech = JaxbUtils.marshallString(info.getObsoleteTech());
			aInfo.bonusType = JaxbUtils.marshallString(info.getBonusType());

			if (CollectionUtils.hasElements(info.getPrereqOrBonuses())) {
				aInfo.prereqOrBonuses = new ArrayList<String>();
				for(String val: info.getPrereqOrBonuses()) {
					aInfo.prereqOrBonuses.add(JaxbUtils.marshallMandatoryString(val));
				}
			}

			if (CollectionUtils.hasElements(info.getPrereqAndCivics())) {
				aInfo.prereqAndCivics = new ArrayList<String>();
				for(String val: info.getPrereqAndCivics()) {
					aInfo.prereqAndCivics.add(JaxbUtils.marshallMandatoryString(val));
				}
			}

			if (CollectionUtils.hasElements(info.getPrereqOrCivics())) {
				aInfo.prereqOrCivics = new ArrayList<String>();
				for(String val: info.getPrereqOrCivics()) {
					aInfo.prereqOrCivics.add(JaxbUtils.marshallMandatoryString(val));
				}
			}

			if (CollectionUtils.hasElements(info.getPrereqAndTerrains())) {
				aInfo.prereqAndTerrains = new ArrayList<String>();
				for(String val: info.getPrereqAndTerrains()) {
					aInfo.prereqAndTerrains.add(JaxbUtils.marshallMandatoryString(val));
				}
			}

			if (CollectionUtils.hasElements(info.getPrereqOrTerrains())) {
				aInfo.prereqOrTerrains = new ArrayList<String>();
				for(String val: info.getPrereqOrTerrains()) {
					aInfo.prereqOrTerrains.add(JaxbUtils.marshallMandatoryString(val));
				}
			}

			if (CollectionUtils.hasElements(info.getPrereqOrBuildingClasses())) {
				aInfo.prereqOrBuildingClasses = new ArrayList<String>();
				for(String val: info.getPrereqOrBuildingClasses()) {
					aInfo.prereqOrBuildingClasses.add(JaxbUtils.marshallMandatoryString(val));
				}
			}

			if (CollectionUtils.hasElements(info.getPrereqNotBuildingClasses())) {
				aInfo.prereqNotBuildingClasses = new ArrayList<String>();
				for(String val: info.getPrereqNotBuildingClasses()) {
					aInfo.prereqNotBuildingClasses.add(JaxbUtils.marshallMandatoryString(val));
				}
			}

			if (CollectionUtils.hasElements(info.getPrereqVicinityAndBonus())) {
				aInfo.prereqVicinityAndBonus = new ArrayList<String>();
				for(String val: info.getPrereqVicinityAndBonus()) {
					aInfo.prereqVicinityAndBonus.add(JaxbUtils.marshallMandatoryString(val));
				}
			}

			if (CollectionUtils.hasElements(info.getPrereqVicinityOrBonus())) {
				aInfo.prereqVicinityOrBonus = new ArrayList<String>();
				for(String val: info.getPrereqVicinityOrBonus()) {
					aInfo.prereqVicinityOrBonus.add(JaxbUtils.marshallMandatoryString(val));
				}
			}

			if (CollectionUtils.hasElements(info.getPrereqVicinityImprovements())) {
				aInfo.prereqVicinityImprovements = new ArrayList<String>();
				for(String val: info.getPrereqVicinityImprovements()) {
					aInfo.prereqVicinityImprovements.add(JaxbUtils.marshallMandatoryString(val));
				}
			}

			if (CollectionUtils.hasElements(info.getPrereqVicinityFeatures())) {
				aInfo.prereqVicinityFeatures = new ArrayList<String>();
				for(String val: info.getPrereqVicinityFeatures()) {
					aInfo.prereqVicinityFeatures.add(JaxbUtils.marshallMandatoryString(val));
				}
			}

			if (CollectionUtils.hasElements(info.getPrereqWorldViews())) {
				aInfo.prereqWorldViews = new ArrayList<String>();
				for(String val: info.getPrereqWorldViews()) {
					aInfo.prereqWorldViews.add(JaxbUtils.marshallMandatoryString(val));
				}
			}
			aInfo.minPopulation = JaxbUtils.marshallInteger(info.getMinPopulation());
			aInfo.minCultureLevel = JaxbUtils.marshallString(info.getMinCultureLevel());
			aInfo.prereqPower = JaxbUtils.marshallBoolean(info.isPrereqPower());

			if (CollectionUtils.hasElements(info.getProductionTraits())) {
				aInfo.productionTraits = new ArrayList<AdaptedProductionTraits>();
				for (IPair<String, Integer> pair: info.getProductionTraits()) {
					AdaptedProductionTraits adaptor = new AdaptedProductionTraits();
					adaptor.productionTraitType = pair.getKey();
					adaptor.iProductionTrait = pair.getValue();
					aInfo.productionTraits.add(adaptor);
				}
			}

			if (CollectionUtils.hasElements(info.getFlavors())) {
				aInfo.flavors = new ArrayList<AdaptedFlavors>();
				for (IPair<String, Integer> pair: info.getFlavors()) {
					AdaptedFlavors adaptor = new AdaptedFlavors();
					adaptor.flavorType = pair.getKey();
					adaptor.iFlavor = pair.getValue();
					aInfo.flavors.add(adaptor);
				}
			}
			aInfo.aIWeight = JaxbUtils.marshallInteger(info.getAIWeight());
			aInfo.cost = JaxbUtils.marshallInteger(info.getCost());
			aInfo.hurryCostModifier = JaxbUtils.marshallInteger(info.getHurryCostModifier());
			aInfo.advancedStartCost = JaxbUtils.marshallInteger(info.getAdvancedStartCost());
			aInfo.advancedStartCostIncrease = JaxbUtils.marshallInteger(info.getAdvancedStartCostIncrease());
			aInfo.minAreaSize = JaxbUtils.marshallInteger(info.getMinAreaSize());
			aInfo.morale = JaxbUtils.marshallInteger(info.getMorale());
			aInfo.moves = JaxbUtils.marshallInteger(info.getMoves());
			aInfo.unitRangeType = JaxbUtils.marshallString(info.getUnitRangeType());
			aInfo.noRevealMap = JaxbUtils.marshallBoolean(info.isNoRevealMap());
			aInfo.airRange = JaxbUtils.marshallInteger(info.getAirRange());
			aInfo.airUnitCap = JaxbUtils.marshallInteger(info.getAirUnitCap());
			aInfo.dropRange = JaxbUtils.marshallInteger(info.getDropRange());
			aInfo.nukeRange = JaxbUtils.marshallInteger(info.getNukeRange());
			aInfo.workRate = JaxbUtils.marshallInteger(info.getWorkRate());
			aInfo.baseDiscover = JaxbUtils.marshallInteger(info.getBaseDiscover());
			aInfo.discoverMultiplier = JaxbUtils.marshallInteger(info.getDiscoverMultiplier());
			aInfo.baseHurry = JaxbUtils.marshallInteger(info.getBaseHurry());
			aInfo.hurryMultiplier = JaxbUtils.marshallInteger(info.getHurryMultiplier());
			aInfo.baseTrade = JaxbUtils.marshallInteger(info.getBaseTrade());
			aInfo.tradeMultiplier = JaxbUtils.marshallInteger(info.getTradeMultiplier());
			aInfo.greatWorkCulture = JaxbUtils.marshallInteger(info.getGreatWorkCulture());
			aInfo.espionagePoints = JaxbUtils.marshallInteger(info.getEspionagePoints());
			aInfo.greatJestHappiness = JaxbUtils.marshallInteger(info.getGreatJestHappiness());
			aInfo.greatJestDuration = JaxbUtils.marshallInteger(info.getGreatJestDuration());

			if (CollectionUtils.hasElements(info.getTerrainImpassables())) {
				aInfo.terrainImpassables = new ArrayList<String>();
				for(String val: info.getTerrainImpassables()) {
					aInfo.terrainImpassables.add(JaxbUtils.marshallMandatoryString(val));
				}
			}

			if (CollectionUtils.hasElements(info.getFeatureImpassables())) {
				aInfo.featureImpassables = new ArrayList<String>();
				for(String val: info.getFeatureImpassables()) {
					aInfo.featureImpassables.add(JaxbUtils.marshallMandatoryString(val));
				}
			}

			if (CollectionUtils.hasElements(info.getTerrainPassableTechs())) {
				aInfo.terrainPassableTechs = new ArrayList<AdaptedTerrainPassableTechs>();
				for (IPair<String, String> pair: info.getTerrainPassableTechs()) {
					AdaptedTerrainPassableTechs adaptor = new AdaptedTerrainPassableTechs();
					adaptor.terrainType = pair.getKey();
					adaptor.passableTech = pair.getValue();
					aInfo.terrainPassableTechs.add(adaptor);
				}
			}

			if (CollectionUtils.hasElements(info.getFeaturePassableTechs())) {
				aInfo.featurePassableTechs = new ArrayList<AdaptedFeaturePassableTechs>();
				for (IPair<String, String> pair: info.getFeaturePassableTechs()) {
					AdaptedFeaturePassableTechs adaptor = new AdaptedFeaturePassableTechs();
					adaptor.featureType = pair.getKey();
					adaptor.passableTech = pair.getValue();
					aInfo.featurePassableTechs.add(adaptor);
				}
			}
			aInfo.combatInt = JaxbUtils.marshallInteger(info.getCombatInt());
			aInfo.combatLimit = JaxbUtils.marshallInteger(info.getCombatLimit());
			aInfo.airCombat = JaxbUtils.marshallInteger(info.getAirCombat());
			aInfo.airCombatLimit = JaxbUtils.marshallInteger(info.getAirCombatLimit());
			aInfo.xPValueAttack = JaxbUtils.marshallInteger(info.getXPValueAttack());
			aInfo.xPValueDefense = JaxbUtils.marshallInteger(info.getXPValueDefense());
			aInfo.firstStrikes = JaxbUtils.marshallInteger(info.getFirstStrikes());
			aInfo.chanceFirstStrikes = JaxbUtils.marshallInteger(info.getChanceFirstStrikes());
			aInfo.interceptionProbability = JaxbUtils.marshallInteger(info.getInterceptionProbability());
			aInfo.evasionProbability = JaxbUtils.marshallInteger(info.getEvasionProbability());
			aInfo.withdrawalProb = JaxbUtils.marshallInteger(info.getWithdrawalProb());
			aInfo.collateralDamage = JaxbUtils.marshallInteger(info.getCollateralDamage());
			aInfo.collateralDamageLimit = JaxbUtils.marshallInteger(info.getCollateralDamageLimit());
			aInfo.collateralDamageMaxUnits = JaxbUtils.marshallInteger(info.getCollateralDamageMaxUnits());
			aInfo.cityAttack = JaxbUtils.marshallInteger(info.getCityAttack());
			aInfo.cityDefense = JaxbUtils.marshallInteger(info.getCityDefense());
			aInfo.animalCombat = JaxbUtils.marshallInteger(info.getAnimalCombat());
			aInfo.hillsAttack = JaxbUtils.marshallInteger(info.getHillsAttack());
			aInfo.hillsDefense = JaxbUtils.marshallInteger(info.getHillsDefense());

			if (CollectionUtils.hasElements(info.getTerrainNatives())) {
				aInfo.terrainNatives = new ArrayList<String>();
				for(String val: info.getTerrainNatives()) {
					aInfo.terrainNatives.add(JaxbUtils.marshallMandatoryString(val));
				}
			}

			if (CollectionUtils.hasElements(info.getFeatureNatives())) {
				aInfo.featureNatives = new ArrayList<String>();
				for(String val: info.getFeatureNatives()) {
					aInfo.featureNatives.add(JaxbUtils.marshallMandatoryString(val));
				}
			}

			if (CollectionUtils.hasElements(info.getTerrainAttacks())) {
				aInfo.terrainAttacks = new ArrayList<AdaptedTerrainAttacks>();
				for (IPair<String, Integer> pair: info.getTerrainAttacks()) {
					AdaptedTerrainAttacks adaptor = new AdaptedTerrainAttacks();
					adaptor.terrainType = pair.getKey();
					adaptor.iTerrainAttack = pair.getValue();
					aInfo.terrainAttacks.add(adaptor);
				}
			}

			if (CollectionUtils.hasElements(info.getTerrainDefenses())) {
				aInfo.terrainDefenses = new ArrayList<AdaptedTerrainDefenses>();
				for (IPair<String, Integer> pair: info.getTerrainDefenses()) {
					AdaptedTerrainDefenses adaptor = new AdaptedTerrainDefenses();
					adaptor.terrainType = pair.getKey();
					adaptor.iTerrainDefense = pair.getValue();
					aInfo.terrainDefenses.add(adaptor);
				}
			}

			if (CollectionUtils.hasElements(info.getFeatureAttacks())) {
				aInfo.featureAttacks = new ArrayList<AdaptedFeatureAttacks>();
				for (IPair<String, Integer> pair: info.getFeatureAttacks()) {
					AdaptedFeatureAttacks adaptor = new AdaptedFeatureAttacks();
					adaptor.featureType = pair.getKey();
					adaptor.iFeatureAttack = pair.getValue();
					aInfo.featureAttacks.add(adaptor);
				}
			}

			if (CollectionUtils.hasElements(info.getFeatureDefenses())) {
				aInfo.featureDefenses = new ArrayList<AdaptedFeatureDefenses>();
				for (IPair<String, Integer> pair: info.getFeatureDefenses()) {
					AdaptedFeatureDefenses adaptor = new AdaptedFeatureDefenses();
					adaptor.featureType = pair.getKey();
					adaptor.iFeatureDefense = pair.getValue();
					aInfo.featureDefenses.add(adaptor);
				}
			}

			if (CollectionUtils.hasElements(info.getUnitClassAttackMods())) {
				aInfo.unitClassAttackMods = new ArrayList<AdaptedUnitClassAttackMods>();
				for (IPair<String, Integer> pair: info.getUnitClassAttackMods()) {
					AdaptedUnitClassAttackMods adaptor = new AdaptedUnitClassAttackMods();
					adaptor.unitClassType = pair.getKey();
					adaptor.iUnitClassMod = pair.getValue();
					aInfo.unitClassAttackMods.add(adaptor);
				}
			}

			if (CollectionUtils.hasElements(info.getUnitClassDefenseMods())) {
				aInfo.unitClassDefenseMods = new ArrayList<AdaptedUnitClassDefenseMods>();
				for (IPair<String, Integer> pair: info.getUnitClassDefenseMods()) {
					AdaptedUnitClassDefenseMods adaptor = new AdaptedUnitClassDefenseMods();
					adaptor.unitClassType = pair.getKey();
					adaptor.iUnitClassMod = pair.getValue();
					aInfo.unitClassDefenseMods.add(adaptor);
				}
			}

			if (CollectionUtils.hasElements(info.getUnitCombatMods())) {
				aInfo.unitCombatMods = new ArrayList<AdaptedUnitCombatMods>();
				for (IPair<String, Integer> pair: info.getUnitCombatMods()) {
					AdaptedUnitCombatMods adaptor = new AdaptedUnitCombatMods();
					adaptor.unitCombatType = pair.getKey();
					adaptor.iUnitCombatMod = pair.getValue();
					aInfo.unitCombatMods.add(adaptor);
				}
			}

			if (CollectionUtils.hasElements(info.getUnitCombatCollateralImmunes())) {
				aInfo.unitCombatCollateralImmunes = new ArrayList<AdaptedUnitCombatCollateralImmunes>();
				for (IPair<String, Integer> pair: info.getUnitCombatCollateralImmunes()) {
					AdaptedUnitCombatCollateralImmunes adaptor = new AdaptedUnitCombatCollateralImmunes();
					adaptor.unitCombatType = pair.getKey();
					adaptor.iUnitCombatCollateralImmune = pair.getValue();
					aInfo.unitCombatCollateralImmunes.add(adaptor);
				}
			}

			if (CollectionUtils.hasElements(info.getDomainMods())) {
				aInfo.domainMods = new ArrayList<AdaptedDomainMods>();
				for (IPair<String, Integer> pair: info.getDomainMods()) {
					AdaptedDomainMods adaptor = new AdaptedDomainMods();
					adaptor.domainType = pair.getKey();
					adaptor.iDomainMod = pair.getValue();
					aInfo.domainMods.add(adaptor);
				}
			}

			if (CollectionUtils.hasElements(info.getBonusProductionModifiers())) {
				aInfo.bonusProductionModifiers = new ArrayList<AdaptedBonusProductionModifiers>();
				for (IPair<String, Integer> pair: info.getBonusProductionModifiers()) {
					AdaptedBonusProductionModifiers adaptor = new AdaptedBonusProductionModifiers();
					adaptor.bonusType = pair.getKey();
					adaptor.iProductonModifier = pair.getValue();
					aInfo.bonusProductionModifiers.add(adaptor);
				}
			}

			if (CollectionUtils.hasElements(info.getYieldsFromKill())) {
				aInfo.yieldsFromKill = new ArrayList<Integer>();
				for(Integer val: info.getYieldsFromKill()) {
					aInfo.yieldsFromKill.add(JaxbUtils.marshallMandatoryInteger(val));
				}
			}

			if (CollectionUtils.hasElements(info.getCommercesFromKill())) {
				aInfo.commercesFromKill = new ArrayList<Integer>();
				for(Integer val: info.getCommercesFromKill()) {
					aInfo.commercesFromKill.add(JaxbUtils.marshallMandatoryInteger(val));
				}
			}
			aInfo.bombRate = JaxbUtils.marshallInteger(info.getBombRate());
			aInfo.bombardRate = JaxbUtils.marshallInteger(info.getBombardRate());
			aInfo.specialCargo = JaxbUtils.marshallString(info.getSpecialCargo());
			aInfo.domainCargo = JaxbUtils.marshallString(info.getDomainCargo());
			aInfo.cargo = JaxbUtils.marshallInteger(info.getCargo());
			aInfo.conscription = JaxbUtils.marshallInteger(info.getConscription());
			aInfo.cultureGarrison = JaxbUtils.marshallInteger(info.getCultureGarrison());
			aInfo.extraCost = JaxbUtils.marshallInteger(info.getExtraCost());
			aInfo.asset = JaxbUtils.marshallInteger(info.getAsset());
			aInfo.power = JaxbUtils.marshallInteger(info.getPower());
			aInfo.unitMeshGroups = info.getUnitMeshGroups();
			aInfo.formationType = JaxbUtils.marshallMandatoryString(info.getFormationType());
			aInfo.hotKey = JaxbUtils.marshallString(info.getHotKey());
			aInfo.altDown = JaxbUtils.marshallBoolean(info.isAltDown());
			aInfo.shiftDown = JaxbUtils.marshallBoolean(info.isShiftDown());
			aInfo.ctrlDown = JaxbUtils.marshallBoolean(info.isCtrlDown());
			aInfo.hotKeyPriority = JaxbUtils.marshallInteger(info.getHotKeyPriority());

			if (CollectionUtils.hasElements(info.getFreePromotions())) {
				aInfo.freePromotions = new ArrayList<String>();
				for(String val: info.getFreePromotions()) {
					aInfo.freePromotions.add(JaxbUtils.marshallMandatoryString(val));
				}
			}
			aInfo.leaderPromotion = JaxbUtils.marshallString(info.getLeaderPromotion());
			aInfo.leaderExperience = JaxbUtils.marshallInteger(info.getLeaderExperience());
			aInfo.orderPriority = JaxbUtils.marshallInteger(info.getOrderPriority());

			map.entries.add(aInfo);
		}
		return map;
	}
}