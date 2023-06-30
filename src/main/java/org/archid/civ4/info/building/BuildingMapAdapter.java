package org.archid.civ4.info.building;

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

public class BuildingMapAdapter extends XmlAdapter<BuildingMapAdapter.BuildingMap, Map<String, IBuildingInfo>> {

	public static class BuildingMap {
		@XmlElement(name = "BuildingInfo")
		List<AdaptedBuilding> entries = new ArrayList<AdaptedBuilding>();
	}

	private static class AdaptedBuilding {
		@XmlElement(name="BuildingClass")
		private String buildingClass;
		@XmlElement(name="Type")
		private String type;
		@XmlElement(name="SpecialBuildingType")
		private String specialBuildingType;
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
		@XmlElement(name="ArtDefineTag")
		private String artDefineTag;
		@XmlElement(name="MovieDefineTag")
		private String movieDefineTag;
		@XmlElement(name="bAutoBuild")
		private Integer autoBuild;
		@XmlElement(name="HolyCity")
		private String holyCity;
		@XmlElement(name="ReligionType")
		private String religionType;
		@XmlElement(name="StateReligion")
		private String stateReligion;
		@XmlElement(name="bStateReligion")
		private Integer stateReligionBool;
		@XmlElement(name="PrereqReligion")
		private String prereqReligion;
		@XmlElement(name="PrereqCorporation")
		private String prereqCorporation;
		@XmlElement(name="FoundsCorporation")
		private String foundsCorporation;
		@XmlElement(name="GlobalReligionCommerce")
		private String globalReligionCommerce;
		@XmlElement(name="GlobalCorporationCommerce")
		private String globalCorporationCommerce;
		@XmlElement(name="VictoryPrereq")
		private String victoryPrereq;
		@XmlElement(name="FreeStartEra")
		private String freeStartEra;
		@XmlElement(name="MaxStartEra")
		private String maxStartEra;
		@XmlElement(name="ObsoleteTech")
		private String obsoleteTech;
		@XmlElementWrapper(name="PrereqAndTechs")
		@XmlElement(name="TechType")
		private List<String> prereqAndTechs;
		@XmlElement(name="PrereqBonus")
		private String prereqBonus;
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
		@XmlElementWrapper(name="PrereqVicinityAndBonus")
		@XmlElement(name="BonusType")
		private List<String> prereqVicinityAndBonus;
		@XmlElementWrapper(name="PrereqVicinityOrBonus")
		@XmlElement(name="BonusType")
		private List<String> prereqVicinityOrBonus;
		@XmlElement(name="bPrereqVicinityBonusUnconnectedAllowed")
		private Integer prereqVicinityBonusUnconnectedAllowed;
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
		@XmlElementWrapper(name="HappinessTraits")
		@XmlElement(name="HappinessTrait")
		private List<AdaptedHappinessTraits> happinessTraits;
		@XmlElement(name="NoBonus")
		private String noBonus;
		@XmlElement(name="PowerBonus")
		private String powerBonus;
		@XmlElement(name="FreeBonus")
		private String freeBonus;
		@XmlElement(name="iNumFreeBonuses")
		private Integer numFreeBonuses;
		@XmlElement(name="FreeBuilding")
		private String freeBuilding;
		@XmlElement(name="FreePromotion")
		private String freePromotion;
		@XmlElement(name="FreeUnitClass")
		private String freeUnitClass;
		@XmlElement(name="CreateFeatureType")
		private String createFeatureType;
		@XmlElement(name="CivicOption")
		private String civicOption;
		@XmlElement(name="GreatPeopleUnitClass")
		private String greatPeopleUnitClass;
		@XmlElement(name="iGreatPeopleRateChange")
		private Integer greatPeopleRateChange;
		@XmlElement(name="iHurryAngerModifier")
		private Integer hurryAngerModifier;
		@XmlElement(name="bBorderObstacle")
		private Integer borderObstacle;
		@XmlElement(name="bTeamShare")
		private Integer teamShare;
		@XmlElement(name="bWater")
		private Integer water;
		@XmlElement(name="bRiver")
		private Integer river;
		@XmlElement(name="bPower")
		private Integer powerBool;
		@XmlElement(name="bDirtyPower")
		private Integer dirtyPower;
		@XmlElement(name="bAreaCleanPower")
		private Integer areaCleanPower;
		@XmlElement(name="DiploVoteType")
		private String diploVoteType;
		@XmlElement(name="bForceTeamVoteEligible")
		private Integer forceTeamVoteEligible;
		@XmlElement(name="bCapital")
		private Integer capital;
		@XmlElement(name="bGovernmentCenter")
		private Integer governmentCenter;
		@XmlElement(name="bGoldenAge")
		private Integer goldenAge;
		@XmlElement(name="bAllowsNukes")
		private Integer allowsNukes;
		@XmlElement(name="bMapCentering")
		private Integer mapCentering;
		@XmlElement(name="bNoUnhappiness")
		private Integer noUnhappiness;
		@XmlElement(name="iUnhealthyPopulationModifier")
		private Integer unhealthyPopulationModifier;
		@XmlElement(name="bBuildingOnlyHealthy")
		private Integer buildingOnlyHealthy;
		@XmlElement(name="bNeverCapture")
		private Integer neverCapture;
		@XmlElement(name="bNukeImmune")
		private Integer nukeImmune;
		@XmlElement(name="bPrereqReligion")
		private Integer prereqReligionBool;
		@XmlElement(name="bCenterInCity")
		private Integer centerInCity;
		@XmlElement(name="bSlaveMarket")
		private Integer slaveMarket;
		@XmlElement(name="bApplyAllFreePromotionsOnMove")
		private Integer applyAllFreePromotionsOnMove;
		@XmlElement(name="bApplyFreePromotionOnMove")
		private Integer applyFreePromotionOnMove;
		@XmlElement(name="bUnitCityDeathCulture")
		private Integer unitCityDeathCulture;
		@XmlElement(name="bUnitAllCityDeathCulture")
		private Integer unitAllCityDeathCulture;
		@XmlElement(name="bForceDisableStarSigns")
		private Integer forceDisableStarSigns;
		@XmlElement(name="bStarSignGoodOnly")
		private Integer starSignGoodOnly;
		@XmlElement(name="iStarSignMitigateChangePercent")
		private Integer starSignMitigateChangePercent;
		@XmlElement(name="iGlobalStarSignMitigateChangePercent")
		private Integer globalStarSignMitigateChangePercent;
		@XmlElement(name="iStarSignScaleChangePercent")
		private Integer starSignScaleChangePercent;
		@XmlElement(name="iGlobalStarSignScaleChangePercent")
		private Integer globalStarSignScaleChangePercent;
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
		@XmlElement(name="iExtraBarbarianCostChange")
		private Integer extraBarbarianCostChange;
		@XmlElement(name="iBarbarianConversionCostModifier")
		private Integer barbarianConversionCostModifier;
		@XmlElement(name="iMinAreaSize")
		private Integer minAreaSize;
		@XmlElement(name="iConquestProb")
		private Integer conquestProb;
		@XmlElement(name="iCitiesPrereq")
		private Integer citiesPrereq;
		@XmlElement(name="iTeamsPrereq")
		private Integer teamsPrereq;
		@XmlElement(name="iLevelPrereq")
		private Integer levelPrereq;
		@XmlElement(name="iMinLatitude")
		private Integer minLatitude;
		@XmlElement(name="iMaxLatitude")
		private Integer maxLatitude;
		@XmlElement(name="iWorkableRadius")
		private Integer workableRadius;
		@XmlElement(name="iGreatPeopleRateModifier")
		private Integer greatPeopleRateModifier;
		@XmlElement(name="iGreatGeneralRateModifier")
		private Integer greatGeneralRateModifier;
		@XmlElement(name="iDomesticGreatGeneralRateModifier")
		private Integer domesticGreatGeneralRateModifier;
		@XmlElement(name="iGlobalGreatPeopleRateModifier")
		private Integer globalGreatPeopleRateModifier;
		@XmlElement(name="iAnarchyModifier")
		private Integer anarchyModifier;
		@XmlElement(name="iGoldenAgeModifier")
		private Integer goldenAgeModifier;
		@XmlElement(name="iGlobalHurryModifier")
		private Integer globalHurryModifier;
		@XmlElement(name="iExperience")
		private Integer experience;
		@XmlElement(name="iGlobalExperience")
		private Integer globalExperience;
		@XmlElement(name="iFoodKept")
		private Integer foodKept;
		@XmlElement(name="iAirlift")
		private Integer airlift;
		@XmlElement(name="iAirModifier")
		private Integer airModifier;
		@XmlElement(name="iAirUnitCapacity")
		private Integer airUnitCapacity;
		@XmlElement(name="iNukeModifier")
		private Integer nukeModifier;
		@XmlElement(name="iNukeExplosionRand")
		private Integer nukeExplosionRand;
		@XmlElement(name="iFreeSpecialist")
		private Integer freeSpecialist;
		@XmlElement(name="iAreaFreeSpecialist")
		private Integer areaFreeSpecialist;
		@XmlElement(name="iGlobalFreeSpecialist")
		private Integer globalFreeSpecialist;
		@XmlElement(name="iMaintenanceModifier")
		private Integer maintenanceModifier;
		@XmlElement(name="iWarWearinessModifier")
		private Integer warWearinessModifier;
		@XmlElement(name="iGlobalWarWearinessModifier")
		private Integer globalWarWearinessModifier;
		@XmlElement(name="iEnemyWarWearinessModifier")
		private Integer enemyWarWearinessModifier;
		@XmlElement(name="iHealRateChange")
		private Integer healRateChange;
		@XmlElement(name="iHealth")
		private Integer health;
		@XmlElement(name="iAreaHealth")
		private Integer areaHealth;
		@XmlElement(name="iGlobalHealth")
		private Integer globalHealth;
		@XmlElement(name="iHappiness")
		private Integer happiness;
		@XmlElement(name="iAreaHappiness")
		private Integer areaHappiness;
		@XmlElement(name="iGlobalHappiness")
		private Integer globalHappiness;
		@XmlElement(name="iStateReligionHappiness")
		private Integer stateReligionHappiness;
		@XmlElement(name="iWorkerSpeedModifier")
		private Integer workerSpeedModifier;
		@XmlElement(name="iMilitaryProductionModifier")
		private Integer militaryProductionModifier;
		@XmlElement(name="iSpaceProductionModifier")
		private Integer spaceProductionModifier;
		@XmlElement(name="iGlobalSpaceProductionModifier")
		private Integer globalSpaceProductionModifier;
		@XmlElement(name="iTradeRoutes")
		private Integer tradeRoutes;
		@XmlElement(name="iCoastalTradeRoutes")
		private Integer coastalTradeRoutes;
		@XmlElement(name="iGlobalTradeRoutes")
		private Integer globalTradeRoutes;
		@XmlElement(name="iTradeRouteModifier")
		private Integer tradeRouteModifier;
		@XmlElement(name="iForeignTradeRouteModifier")
		private Integer foreignTradeRouteModifier;
		@XmlElement(name="iGlobalPopulationChange")
		private Integer globalPopulationChange;
		@XmlElement(name="iGlobalFoundPopulationChange")
		private Integer globalFoundPopulationChange;
		@XmlElement(name="iFreeTechs")
		private Integer freeTechs;
		@XmlElement(name="iDefense")
		private Integer defense;
		@XmlElement(name="iObsoleteDefence")
		private Integer obsoleteDefence;
		@XmlElement(name="iBombardDefense")
		private Integer bombardDefense;
		@XmlElement(name="iAllCityDefense")
		private Integer allCityDefense;
		@XmlElement(name="iEspionageDefense")
		private Integer espionageDefense;
		@XmlElement(name="iAsset")
		private Integer asset;
		@XmlElement(name="iPower")
		private Integer power;
		@XmlElement(name="iGlobalWonderProductionModifier")
		private Integer globalWonderProductionModifier;
		@XmlElement(name="fVisibilityPriority")
		private String fVisibilityPriority;
		@XmlElementWrapper(name="SeaPlotYieldChanges")
		@XmlElement(name="iYield")
		private List<Integer> seaPlotYieldChanges;
		@XmlElementWrapper(name="RiverPlotYieldChanges")
		@XmlElement(name="iYield")
		private List<Integer> riverPlotYieldChanges;
		@XmlElementWrapper(name="GlobalSeaPlotYieldChanges")
		@XmlElement(name="iYield")
		private List<Integer> globalSeaPlotYieldChanges;
		@XmlElementWrapper(name="YieldChanges")
		@XmlElement(name="iYield")
		private List<Integer> yieldChanges;
		@XmlElementWrapper(name="GlobalYieldChanges")
		@XmlElement(name="iYield")
		private List<Integer> globalYieldChanges;
		@XmlElementWrapper(name="YieldModifiers")
		@XmlElement(name="iYield")
		private List<Integer> yieldModifiers;
		@XmlElementWrapper(name="PowerYieldModifiers")
		@XmlElement(name="iYield")
		private List<Integer> powerYieldModifiers;
		@XmlElementWrapper(name="AreaYieldModifiers")
		@XmlElement(name="iYield")
		private List<Integer> areaYieldModifiers;
		@XmlElementWrapper(name="GlobalYieldModifiers")
		@XmlElement(name="iYield")
		private List<Integer> globalYieldModifiers;
		@XmlElementWrapper(name="CommerceChanges")
		@XmlElement(name="iCommerce")
		private List<Integer> commerceChanges;
		@XmlElementWrapper(name="ObsoleteSafeCommerceChanges")
		@XmlElement(name="iCommerce")
		private List<Integer> obsoleteSafeCommerceChanges;
		@XmlElementWrapper(name="CommerceChangeDoubleTimes")
		@XmlElement(name="iCommerce")
		private List<Integer> commerceChangeDoubleTimes;
		@XmlElementWrapper(name="CommerceModifiers")
		@XmlElement(name="iCommerce")
		private List<Integer> commerceModifiers;
		@XmlElementWrapper(name="GlobalCommerceModifiers")
		@XmlElement(name="iCommerce")
		private List<Integer> globalCommerceModifiers;
		@XmlElementWrapper(name="SpecialistExtraCommerces")
		@XmlElement(name="iCommerce")
		private List<Integer> specialistExtraCommerces;
		@XmlElementWrapper(name="StateReligionCommerces")
		@XmlElement(name="iCommerce")
		private List<Integer> stateReligionCommerces;
		@XmlElementWrapper(name="CommerceHappinesses")
		@XmlElement(name="iCommerce")
		private List<Integer> commerceHappinesses;
		@XmlElementWrapper(name="ReligionChanges")
		@XmlElement(name="ReligionChange")
		private List<AdaptedReligionChanges> religionChanges;
		@XmlElementWrapper(name="SpecialistCounts")
		@XmlElement(name="SpecialistCount")
		private List<AdaptedSpecialistCounts> specialistCounts;
		@XmlElementWrapper(name="FreeSpecialistCounts")
		@XmlElement(name="FreeSpecialistCount")
		private List<AdaptedFreeSpecialistCounts> freeSpecialistCounts;
		@XmlElementWrapper(name="CommerceFlexibles")
		@XmlElement(name="bCommerce")
		private List<Integer> commerceFlexibles;
		@XmlElementWrapper(name="CommerceChangeOriginalOwners")
		@XmlElement(name="bCommerceChangeOriginalOwner")
		private List<Integer> commerceChangeOriginalOwners;
		@XmlElement(name="ConstructSound")
		private String constructSound;
		@XmlElementWrapper(name="BonusHealthChanges")
		@XmlElement(name="BonusHealthChange")
		private List<AdaptedBonusHealthChanges> bonusHealthChanges;
		@XmlElementWrapper(name="BonusHappinessChanges")
		@XmlElement(name="BonusHappinessChange")
		private List<AdaptedBonusHappinessChanges> bonusHappinessChanges;
		@XmlElementWrapper(name="BonusProductionModifiers")
		@XmlElement(name="BonusProductionModifier")
		private List<AdaptedBonusProductionModifiers> bonusProductionModifiers;
		@XmlElementWrapper(name="UnitCombatFreeExperiences")
		@XmlElement(name="UnitCombatFreeExperience")
		private List<AdaptedUnitCombatFreeExperiences> unitCombatFreeExperiences;
		@XmlElementWrapper(name="DomainFreeExperiences")
		@XmlElement(name="DomainFreeExperience")
		private List<AdaptedDomainFreeExperiences> domainFreeExperiences;
		@XmlElementWrapper(name="DomainProductionModifiers")
		@XmlElement(name="DomainProductionModifier")
		private List<AdaptedDomainProductionModifiers> domainProductionModifiers;
		@XmlElementWrapper(name="BuildingClassProductionModifiers")
		@XmlElement(name="BuildingClassProductionModifier")
		private List<AdaptedBuildingClassProductionModifiers> buildingClassProductionModifiers;
		@XmlElementWrapper(name="UnitCombatProductionModifiers")
		@XmlElement(name="UnitCombatProductionModifier")
		private List<AdaptedUnitCombatProductionModifiers> unitCombatProductionModifiers;
		@XmlElementWrapper(name="BuildingHappinessChanges")
		@XmlElement(name="BuildingHappinessChange")
		private List<AdaptedBuildingHappinessChanges> buildingHappinessChanges;
		@XmlElementWrapper(name="PrereqNumOfBuildingClasses")
		@XmlElement(name="PrereqNumOfBuildingClass")
		private List<AdaptedPrereqNumOfBuildingClasses> prereqNumOfBuildingClasses;
		@XmlElementWrapper(name="PrereqAndBuildingClasses")
		@XmlElement(name="BuildingClassType")
		private List<String> prereqAndBuildingClasses;
		@XmlElementWrapper(name="PrereqOrBuildingClasses")
		@XmlElement(name="BuildingClassType")
		private List<String> prereqOrBuildingClasses;
		@XmlElementWrapper(name="PrereqNotBuildingClasses")
		@XmlElement(name="BuildingClassType")
		private List<String> prereqNotBuildingClasses;
		@XmlElementWrapper(name="ReplacedByBuildingClasses")
		@XmlElement(name="BuildingClassType")
		private List<String> replacedByBuildingClasses;
		@XmlElement(name="SpecialistYieldChanges")
		private SpecialistYieldChanges specialistYieldChanges;
		@XmlElement(name="BonusYieldModifiers")
		private BonusYieldModifiers bonusYieldModifiers;
		@XmlElement(name="BonusYieldChanges")
		private BonusYieldChanges bonusYieldChanges;
		@XmlElement(name="VicinityBonusYieldChanges")
		private VicinityBonusYieldChanges vicinityBonusYieldChanges;
		@XmlElement(name="TechYieldChanges")
		private TechYieldChanges techYieldChanges;
		@XmlElement(name="TechCommerceChanges")
		private TechCommerceChanges techCommerceChanges;
		@XmlElementWrapper(name="ImprovementFreeSpecialists")
		@XmlElement(name="ImprovementFreeSpecialist")
		private List<AdaptedImprovementFreeSpecialists> improvementFreeSpecialists;
		@XmlElementWrapper(name="Flavors")
		@XmlElement(name="Flavor")
		private List<AdaptedFlavors> flavors;
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
		@XmlElement(name="iOrderPriority")
		private Integer orderPriority;
		@XmlElement(name="bGraphicalOnly")
		private Integer graphicalOnly;
	}

	private static class AdaptedProductionTraits {
		@XmlElement(name="TraitType")
		private String traitType;
		@XmlElement(name="iChange")
		private Integer iChange;
	}

	private static class AdaptedHappinessTraits {
		@XmlElement(name="TraitType")
		private String traitType;
		@XmlElement(name="iChange")
		private Integer iChange;
	}

	private static class AdaptedReligionChanges {
		@XmlElement(name="ReligionType")
		private String religionType;
		@XmlElement(name="iChange")
		private Integer iChange;
	}

	private static class AdaptedSpecialistCounts {
		@XmlElement(name="SpecialistType")
		private String specialistType;
		@XmlElement(name="iCount")
		private Integer iCount;
	}

	private static class AdaptedFreeSpecialistCounts {
		@XmlElement(name="SpecialistType")
		private String specialistType;
		@XmlElement(name="iCount")
		private Integer iCount;
	}

	private static class AdaptedBonusHealthChanges {
		@XmlElement(name="BonusType")
		private String bonusType;
		@XmlElement(name="iChange")
		private Integer iChange;
	}

	private static class AdaptedBonusHappinessChanges {
		@XmlElement(name="BonusType")
		private String bonusType;
		@XmlElement(name="iChange")
		private Integer iChange;
	}

	private static class AdaptedBonusProductionModifiers {
		@XmlElement(name="BonusType")
		private String bonusType;
		@XmlElement(name="iModifier")
		private Integer iModifier;
	}

	private static class AdaptedUnitCombatFreeExperiences {
		@XmlElement(name="UnitCombatType")
		private String unitCombatType;
		@XmlElement(name="iExperience")
		private Integer iExperience;
	}

	private static class AdaptedDomainFreeExperiences {
		@XmlElement(name="DomainType")
		private String domainType;
		@XmlElement(name="iExperience")
		private Integer iExperience;
	}

	private static class AdaptedDomainProductionModifiers {
		@XmlElement(name="DomainType")
		private String domainType;
		@XmlElement(name="iModifier")
		private Integer iModifier;
	}

	private static class AdaptedBuildingClassProductionModifiers {
		@XmlElement(name="BuildingClassType")
		private String buildingClassType;
		@XmlElement(name="iModifier")
		private Integer iModifier;
	}

	private static class AdaptedUnitCombatProductionModifiers {
		@XmlElement(name="UnitCombatType")
		private String unitCombatType;
		@XmlElement(name="iModifier")
		private Integer iModifier;
	}

	private static class AdaptedBuildingHappinessChanges {
		@XmlElement(name="BuildingType")
		private String buildingType;
		@XmlElement(name="iChange")
		private Integer iChange;
	}

	private static class AdaptedPrereqNumOfBuildingClasses {
		@XmlElement(name="BuildingClassType")
		private String buildingClassType;
		@XmlElement(name="iNeeded")
		private Integer iNeeded;
	}

	private static class AdaptedImprovementFreeSpecialists {
		@XmlElement(name="ImprovementType")
		private String improvementType;
		@XmlElement(name="iCount")
		private Integer iCount;
	}

	private static class AdaptedFlavors {
		@XmlElement(name="FlavorType")
		private String flavorType;
		@XmlElement(name="iFlavor")
		private Integer iFlavor;
	}

	@Override
	public Map<String, IBuildingInfo> unmarshal(BuildingMap v) throws Exception {
		Map<String, IBuildingInfo> map = new TreeMap<String, IBuildingInfo>();
		for (AdaptedBuilding aInfo: v.entries) {
			IBuildingInfo info = BuildingInfos.createInfo(aInfo.type);
			info.setBuildingClass(JaxbUtils.unmarshallString(aInfo.buildingClass));
			info.setSpecialBuildingType(JaxbUtils.unmarshallString(aInfo.specialBuildingType));
			info.setDescription(JaxbUtils.unmarshallString(aInfo.description));
			info.setCivilopedia(JaxbUtils.unmarshallString(aInfo.civilopedia));
			info.setStrategy(JaxbUtils.unmarshallString(aInfo.strategy));
			info.setHelp(JaxbUtils.unmarshallString(aInfo.help));
			info.setAdvisor(JaxbUtils.unmarshallString(aInfo.advisor));
			info.setArtDefineTag(JaxbUtils.unmarshallString(aInfo.artDefineTag));
			info.setMovieDefineTag(JaxbUtils.unmarshallString(aInfo.movieDefineTag));
			info.setAutoBuild(JaxbUtils.unmarshallBoolean(aInfo.autoBuild));
			info.setHolyCity(JaxbUtils.unmarshallString(aInfo.holyCity));
			info.setReligionType(JaxbUtils.unmarshallString(aInfo.religionType));
			info.setStateReligion(JaxbUtils.unmarshallString(aInfo.stateReligion));
			info.setStateReligionBool(JaxbUtils.unmarshallBoolean(aInfo.stateReligionBool));
			info.setPrereqReligion(JaxbUtils.unmarshallString(aInfo.prereqReligion));
			info.setPrereqCorporation(JaxbUtils.unmarshallString(aInfo.prereqCorporation));
			info.setFoundsCorporation(JaxbUtils.unmarshallString(aInfo.foundsCorporation));
			info.setGlobalReligionCommerce(JaxbUtils.unmarshallString(aInfo.globalReligionCommerce));
			info.setGlobalCorporationCommerce(JaxbUtils.unmarshallString(aInfo.globalCorporationCommerce));
			info.setVictoryPrereq(JaxbUtils.unmarshallString(aInfo.victoryPrereq));
			info.setFreeStartEra(JaxbUtils.unmarshallString(aInfo.freeStartEra));
			info.setMaxStartEra(JaxbUtils.unmarshallString(aInfo.maxStartEra));
			info.setObsoleteTech(JaxbUtils.unmarshallString(aInfo.obsoleteTech));

			if (CollectionUtils.hasElements(aInfo.prereqAndTechs)) {
				for (String val: aInfo.prereqAndTechs) {
					if (StringUtils.hasCharacters(val)) {
						info.addPrereqAndTech(JaxbUtils.unmarshallString(val));
					}
				}
			}
			info.setPrereqBonus(JaxbUtils.unmarshallString(aInfo.prereqBonus));

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
			info.setPrereqVicinityBonusUnconnectedAllowed(JaxbUtils.unmarshallBoolean(aInfo.prereqVicinityBonusUnconnectedAllowed));

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
					if (StringUtils.hasCharacters(adaptor.traitType)) {
						info.addProductionTrait(new Pair<String, Integer>(adaptor.traitType, adaptor.iChange));
					}
				}
			}

			if (CollectionUtils.hasElements(aInfo.happinessTraits)) {
				for (AdaptedHappinessTraits adaptor: aInfo.happinessTraits) {
					if (StringUtils.hasCharacters(adaptor.traitType)) {
						info.addHappinessTrait(new Pair<String, Integer>(adaptor.traitType, adaptor.iChange));
					}
				}
			}
			info.setNoBonus(JaxbUtils.unmarshallString(aInfo.noBonus));
			info.setPowerBonus(JaxbUtils.unmarshallString(aInfo.powerBonus));
			info.setFreeBonus(JaxbUtils.unmarshallString(aInfo.freeBonus));
			info.setNumFreeBonuses(JaxbUtils.unmarshallInteger(aInfo.numFreeBonuses));
			info.setFreeBuilding(JaxbUtils.unmarshallString(aInfo.freeBuilding));
			info.setFreePromotion(JaxbUtils.unmarshallString(aInfo.freePromotion));
			info.setFreeUnitClass(JaxbUtils.unmarshallString(aInfo.freeUnitClass));
			info.setCreateFeatureType(JaxbUtils.unmarshallString(aInfo.createFeatureType));
			info.setCivicOption(JaxbUtils.unmarshallString(aInfo.civicOption));
			info.setGreatPeopleUnitClass(JaxbUtils.unmarshallString(aInfo.greatPeopleUnitClass));
			info.setGreatPeopleRateChange(JaxbUtils.unmarshallInteger(aInfo.greatPeopleRateChange));
			info.setHurryAngerModifier(JaxbUtils.unmarshallInteger(aInfo.hurryAngerModifier));
			info.setBorderObstacle(JaxbUtils.unmarshallBoolean(aInfo.borderObstacle));
			info.setTeamShare(JaxbUtils.unmarshallBoolean(aInfo.teamShare));
			info.setWater(JaxbUtils.unmarshallBoolean(aInfo.water));
			info.setRiver(JaxbUtils.unmarshallBoolean(aInfo.river));
			info.setPowerBool(JaxbUtils.unmarshallBoolean(aInfo.powerBool));
			info.setDirtyPower(JaxbUtils.unmarshallBoolean(aInfo.dirtyPower));
			info.setAreaCleanPower(JaxbUtils.unmarshallBoolean(aInfo.areaCleanPower));
			info.setDiploVoteType(JaxbUtils.unmarshallString(aInfo.diploVoteType));
			info.setForceTeamVoteEligible(JaxbUtils.unmarshallBoolean(aInfo.forceTeamVoteEligible));
			info.setCapital(JaxbUtils.unmarshallBoolean(aInfo.capital));
			info.setGovernmentCenter(JaxbUtils.unmarshallBoolean(aInfo.governmentCenter));
			info.setGoldenAge(JaxbUtils.unmarshallBoolean(aInfo.goldenAge));
			info.setAllowsNukes(JaxbUtils.unmarshallBoolean(aInfo.allowsNukes));
			info.setMapCentering(JaxbUtils.unmarshallBoolean(aInfo.mapCentering));
			info.setNoUnhappiness(JaxbUtils.unmarshallBoolean(aInfo.noUnhappiness));
			info.setUnhealthyPopulationModifier(JaxbUtils.unmarshallInteger(aInfo.unhealthyPopulationModifier));
			info.setBuildingOnlyHealthy(JaxbUtils.unmarshallBoolean(aInfo.buildingOnlyHealthy));
			info.setNeverCapture(JaxbUtils.unmarshallBoolean(aInfo.neverCapture));
			info.setNukeImmune(JaxbUtils.unmarshallBoolean(aInfo.nukeImmune));
			info.setPrereqReligionBool(JaxbUtils.unmarshallBoolean(aInfo.prereqReligionBool));
			info.setCenterInCity(JaxbUtils.unmarshallBoolean(aInfo.centerInCity));
			info.setSlaveMarket(JaxbUtils.unmarshallBoolean(aInfo.slaveMarket));
			info.setApplyAllFreePromotionsOnMove(JaxbUtils.unmarshallBoolean(aInfo.applyAllFreePromotionsOnMove));
			info.setApplyFreePromotionOnMove(JaxbUtils.unmarshallBoolean(aInfo.applyFreePromotionOnMove));
			info.setUnitCityDeathCulture(JaxbUtils.unmarshallBoolean(aInfo.unitCityDeathCulture));
			info.setUnitAllCityDeathCulture(JaxbUtils.unmarshallBoolean(aInfo.unitAllCityDeathCulture));
			info.setForceDisableStarSigns(JaxbUtils.unmarshallBoolean(aInfo.forceDisableStarSigns));
			info.setStarSignGoodOnly(JaxbUtils.unmarshallBoolean(aInfo.starSignGoodOnly));
			info.setStarSignMitigateChangePercent(JaxbUtils.unmarshallInteger(aInfo.starSignMitigateChangePercent));
			info.setGlobalStarSignMitigateChangePercent(JaxbUtils.unmarshallInteger(aInfo.globalStarSignMitigateChangePercent));
			info.setStarSignScaleChangePercent(JaxbUtils.unmarshallInteger(aInfo.starSignScaleChangePercent));
			info.setGlobalStarSignScaleChangePercent(JaxbUtils.unmarshallInteger(aInfo.globalStarSignScaleChangePercent));
			info.setAIWeight(JaxbUtils.unmarshallInteger(aInfo.aIWeight));
			info.setCost(JaxbUtils.unmarshallInteger(aInfo.cost));
			info.setHurryCostModifier(JaxbUtils.unmarshallInteger(aInfo.hurryCostModifier));
			info.setAdvancedStartCost(JaxbUtils.unmarshallInteger(aInfo.advancedStartCost));
			info.setAdvancedStartCostIncrease(JaxbUtils.unmarshallInteger(aInfo.advancedStartCostIncrease));
			info.setExtraBarbarianCostChange(JaxbUtils.unmarshallInteger(aInfo.extraBarbarianCostChange));
			info.setBarbarianConversionCostModifier(JaxbUtils.unmarshallInteger(aInfo.barbarianConversionCostModifier));
			info.setMinAreaSize(JaxbUtils.unmarshallInteger(aInfo.minAreaSize));
			info.setConquestProb(JaxbUtils.unmarshallInteger(aInfo.conquestProb));
			info.setCitiesPrereq(JaxbUtils.unmarshallInteger(aInfo.citiesPrereq));
			info.setTeamsPrereq(JaxbUtils.unmarshallInteger(aInfo.teamsPrereq));
			info.setLevelPrereq(JaxbUtils.unmarshallInteger(aInfo.levelPrereq));
			info.setMinLatitude(JaxbUtils.unmarshallInteger(aInfo.minLatitude));
			info.setMaxLatitude(JaxbUtils.unmarshallInteger(aInfo.maxLatitude));
			info.setWorkableRadius(JaxbUtils.unmarshallInteger(aInfo.workableRadius));
			info.setGreatPeopleRateModifier(JaxbUtils.unmarshallInteger(aInfo.greatPeopleRateModifier));
			info.setGreatGeneralRateModifier(JaxbUtils.unmarshallInteger(aInfo.greatGeneralRateModifier));
			info.setDomesticGreatGeneralRateModifier(JaxbUtils.unmarshallInteger(aInfo.domesticGreatGeneralRateModifier));
			info.setGlobalGreatPeopleRateModifier(JaxbUtils.unmarshallInteger(aInfo.globalGreatPeopleRateModifier));
			info.setAnarchyModifier(JaxbUtils.unmarshallInteger(aInfo.anarchyModifier));
			info.setGoldenAgeModifier(JaxbUtils.unmarshallInteger(aInfo.goldenAgeModifier));
			info.setGlobalHurryModifier(JaxbUtils.unmarshallInteger(aInfo.globalHurryModifier));
			info.setExperience(JaxbUtils.unmarshallInteger(aInfo.experience));
			info.setGlobalExperience(JaxbUtils.unmarshallInteger(aInfo.globalExperience));
			info.setFoodKept(JaxbUtils.unmarshallInteger(aInfo.foodKept));
			info.setAirlift(JaxbUtils.unmarshallInteger(aInfo.airlift));
			info.setAirModifier(JaxbUtils.unmarshallInteger(aInfo.airModifier));
			info.setAirUnitCapacity(JaxbUtils.unmarshallInteger(aInfo.airUnitCapacity));
			info.setNukeModifier(JaxbUtils.unmarshallInteger(aInfo.nukeModifier));
			info.setNukeExplosionRand(JaxbUtils.unmarshallInteger(aInfo.nukeExplosionRand));
			info.setFreeSpecialist(JaxbUtils.unmarshallInteger(aInfo.freeSpecialist));
			info.setAreaFreeSpecialist(JaxbUtils.unmarshallInteger(aInfo.areaFreeSpecialist));
			info.setGlobalFreeSpecialist(JaxbUtils.unmarshallInteger(aInfo.globalFreeSpecialist));
			info.setMaintenanceModifier(JaxbUtils.unmarshallInteger(aInfo.maintenanceModifier));
			info.setWarWearinessModifier(JaxbUtils.unmarshallInteger(aInfo.warWearinessModifier));
			info.setGlobalWarWearinessModifier(JaxbUtils.unmarshallInteger(aInfo.globalWarWearinessModifier));
			info.setEnemyWarWearinessModifier(JaxbUtils.unmarshallInteger(aInfo.enemyWarWearinessModifier));
			info.setHealRateChange(JaxbUtils.unmarshallInteger(aInfo.healRateChange));
			info.setHealth(JaxbUtils.unmarshallInteger(aInfo.health));
			info.setAreaHealth(JaxbUtils.unmarshallInteger(aInfo.areaHealth));
			info.setGlobalHealth(JaxbUtils.unmarshallInteger(aInfo.globalHealth));
			info.setHappiness(JaxbUtils.unmarshallInteger(aInfo.happiness));
			info.setAreaHappiness(JaxbUtils.unmarshallInteger(aInfo.areaHappiness));
			info.setGlobalHappiness(JaxbUtils.unmarshallInteger(aInfo.globalHappiness));
			info.setStateReligionHappiness(JaxbUtils.unmarshallInteger(aInfo.stateReligionHappiness));
			info.setWorkerSpeedModifier(JaxbUtils.unmarshallInteger(aInfo.workerSpeedModifier));
			info.setMilitaryProductionModifier(JaxbUtils.unmarshallInteger(aInfo.militaryProductionModifier));
			info.setSpaceProductionModifier(JaxbUtils.unmarshallInteger(aInfo.spaceProductionModifier));
			info.setGlobalSpaceProductionModifier(JaxbUtils.unmarshallInteger(aInfo.globalSpaceProductionModifier));
			info.setTradeRoutes(JaxbUtils.unmarshallInteger(aInfo.tradeRoutes));
			info.setCoastalTradeRoutes(JaxbUtils.unmarshallInteger(aInfo.coastalTradeRoutes));
			info.setGlobalTradeRoutes(JaxbUtils.unmarshallInteger(aInfo.globalTradeRoutes));
			info.setTradeRouteModifier(JaxbUtils.unmarshallInteger(aInfo.tradeRouteModifier));
			info.setForeignTradeRouteModifier(JaxbUtils.unmarshallInteger(aInfo.foreignTradeRouteModifier));
			info.setGlobalPopulationChange(JaxbUtils.unmarshallInteger(aInfo.globalPopulationChange));
			info.setGlobalFoundPopulationChange(JaxbUtils.unmarshallInteger(aInfo.globalFoundPopulationChange));
			info.setFreeTechs(JaxbUtils.unmarshallInteger(aInfo.freeTechs));
			info.setDefense(JaxbUtils.unmarshallInteger(aInfo.defense));
			info.setObsoleteDefence(JaxbUtils.unmarshallInteger(aInfo.obsoleteDefence));
			info.setBombardDefense(JaxbUtils.unmarshallInteger(aInfo.bombardDefense));
			info.setAllCityDefense(JaxbUtils.unmarshallInteger(aInfo.allCityDefense));
			info.setEspionageDefense(JaxbUtils.unmarshallInteger(aInfo.espionageDefense));
			info.setAsset(JaxbUtils.unmarshallInteger(aInfo.asset));
			info.setPower(JaxbUtils.unmarshallInteger(aInfo.power));
			info.setGlobalWonderProductionModifier(JaxbUtils.unmarshallInteger(aInfo.globalWonderProductionModifier));
			info.setfVisibilityPriority(JaxbUtils.unmarshallString(aInfo.fVisibilityPriority));

			if (CollectionUtils.hasElements(aInfo.seaPlotYieldChanges)) {
				for (Integer val: aInfo.seaPlotYieldChanges) {
					info.addSeaPlotYieldChange(JaxbUtils.unmarshallInteger(val));
				}
			}

			if (CollectionUtils.hasElements(aInfo.riverPlotYieldChanges)) {
				for (Integer val: aInfo.riverPlotYieldChanges) {
					info.addRiverPlotYieldChange(JaxbUtils.unmarshallInteger(val));
				}
			}

			if (CollectionUtils.hasElements(aInfo.globalSeaPlotYieldChanges)) {
				for (Integer val: aInfo.globalSeaPlotYieldChanges) {
					info.addGlobalSeaPlotYieldChange(JaxbUtils.unmarshallInteger(val));
				}
			}

			if (CollectionUtils.hasElements(aInfo.yieldChanges)) {
				for (Integer val: aInfo.yieldChanges) {
					info.addYieldChange(JaxbUtils.unmarshallInteger(val));
				}
			}

			if (CollectionUtils.hasElements(aInfo.globalYieldChanges)) {
				for (Integer val: aInfo.globalYieldChanges) {
					info.addGlobalYieldChange(JaxbUtils.unmarshallInteger(val));
				}
			}

			if (CollectionUtils.hasElements(aInfo.yieldModifiers)) {
				for (Integer val: aInfo.yieldModifiers) {
					info.addYieldModifier(JaxbUtils.unmarshallInteger(val));
				}
			}

			if (CollectionUtils.hasElements(aInfo.powerYieldModifiers)) {
				for (Integer val: aInfo.powerYieldModifiers) {
					info.addPowerYieldModifier(JaxbUtils.unmarshallInteger(val));
				}
			}

			if (CollectionUtils.hasElements(aInfo.areaYieldModifiers)) {
				for (Integer val: aInfo.areaYieldModifiers) {
					info.addAreaYieldModifier(JaxbUtils.unmarshallInteger(val));
				}
			}

			if (CollectionUtils.hasElements(aInfo.globalYieldModifiers)) {
				for (Integer val: aInfo.globalYieldModifiers) {
					info.addGlobalYieldModifier(JaxbUtils.unmarshallInteger(val));
				}
			}

			if (CollectionUtils.hasElements(aInfo.commerceChanges)) {
				for (Integer val: aInfo.commerceChanges) {
					info.addCommerceChange(JaxbUtils.unmarshallInteger(val));
				}
			}

			if (CollectionUtils.hasElements(aInfo.obsoleteSafeCommerceChanges)) {
				for (Integer val: aInfo.obsoleteSafeCommerceChanges) {
					info.addObsoleteSafeCommerceChange(JaxbUtils.unmarshallInteger(val));
				}
			}

			if (CollectionUtils.hasElements(aInfo.commerceChangeDoubleTimes)) {
				for (Integer val: aInfo.commerceChangeDoubleTimes) {
					info.addCommerceChangeDoubleTime(JaxbUtils.unmarshallInteger(val));
				}
			}

			if (CollectionUtils.hasElements(aInfo.commerceModifiers)) {
				for (Integer val: aInfo.commerceModifiers) {
					info.addCommerceModifier(JaxbUtils.unmarshallInteger(val));
				}
			}

			if (CollectionUtils.hasElements(aInfo.globalCommerceModifiers)) {
				for (Integer val: aInfo.globalCommerceModifiers) {
					info.addGlobalCommerceModifier(JaxbUtils.unmarshallInteger(val));
				}
			}

			if (CollectionUtils.hasElements(aInfo.specialistExtraCommerces)) {
				for (Integer val: aInfo.specialistExtraCommerces) {
					info.addSpecialistExtraCommerce(JaxbUtils.unmarshallInteger(val));
				}
			}

			if (CollectionUtils.hasElements(aInfo.stateReligionCommerces)) {
				for (Integer val: aInfo.stateReligionCommerces) {
					info.addStateReligionCommerce(JaxbUtils.unmarshallInteger(val));
				}
			}

			if (CollectionUtils.hasElements(aInfo.commerceHappinesses)) {
				for (Integer val: aInfo.commerceHappinesses) {
					info.addCommerceHappinesse(JaxbUtils.unmarshallInteger(val));
				}
			}

			if (CollectionUtils.hasElements(aInfo.religionChanges)) {
				for (AdaptedReligionChanges adaptor: aInfo.religionChanges) {
					if (StringUtils.hasCharacters(adaptor.religionType)) {
						info.addReligionChange(new Pair<String, Integer>(adaptor.religionType, adaptor.iChange));
					}
				}
			}

			if (CollectionUtils.hasElements(aInfo.specialistCounts)) {
				for (AdaptedSpecialistCounts adaptor: aInfo.specialistCounts) {
					if (StringUtils.hasCharacters(adaptor.specialistType)) {
						info.addSpecialistCount(new Pair<String, Integer>(adaptor.specialistType, adaptor.iCount));
					}
				}
			}

			if (CollectionUtils.hasElements(aInfo.freeSpecialistCounts)) {
				for (AdaptedFreeSpecialistCounts adaptor: aInfo.freeSpecialistCounts) {
					if (StringUtils.hasCharacters(adaptor.specialistType)) {
						info.addFreeSpecialistCount(new Pair<String, Integer>(adaptor.specialistType, adaptor.iCount));
					}
				}
			}

			if (CollectionUtils.hasElements(aInfo.commerceFlexibles)) {
				for (Integer val: aInfo.commerceFlexibles) {
					info.addCommerceFlexible(JaxbUtils.unmarshallBoolean(val));
				}
			}

			if (CollectionUtils.hasElements(aInfo.commerceChangeOriginalOwners)) {
				for (Integer val: aInfo.commerceChangeOriginalOwners) {
					info.addCommerceChangeOriginalOwner(JaxbUtils.unmarshallBoolean(val));
				}
			}
			info.setConstructSound(JaxbUtils.unmarshallString(aInfo.constructSound));

			if (CollectionUtils.hasElements(aInfo.bonusHealthChanges)) {
				for (AdaptedBonusHealthChanges adaptor: aInfo.bonusHealthChanges) {
					if (StringUtils.hasCharacters(adaptor.bonusType)) {
						info.addBonusHealthChange(new Pair<String, Integer>(adaptor.bonusType, adaptor.iChange));
					}
				}
			}

			if (CollectionUtils.hasElements(aInfo.bonusHappinessChanges)) {
				for (AdaptedBonusHappinessChanges adaptor: aInfo.bonusHappinessChanges) {
					if (StringUtils.hasCharacters(adaptor.bonusType)) {
						info.addBonusHappinessChange(new Pair<String, Integer>(adaptor.bonusType, adaptor.iChange));
					}
				}
			}

			if (CollectionUtils.hasElements(aInfo.bonusProductionModifiers)) {
				for (AdaptedBonusProductionModifiers adaptor: aInfo.bonusProductionModifiers) {
					if (StringUtils.hasCharacters(adaptor.bonusType)) {
						info.addBonusProductionModifier(new Pair<String, Integer>(adaptor.bonusType, adaptor.iModifier));
					}
				}
			}

			if (CollectionUtils.hasElements(aInfo.unitCombatFreeExperiences)) {
				for (AdaptedUnitCombatFreeExperiences adaptor: aInfo.unitCombatFreeExperiences) {
					if (StringUtils.hasCharacters(adaptor.unitCombatType)) {
						info.addUnitCombatFreeExperience(new Pair<String, Integer>(adaptor.unitCombatType, adaptor.iExperience));
					}
				}
			}

			if (CollectionUtils.hasElements(aInfo.domainFreeExperiences)) {
				for (AdaptedDomainFreeExperiences adaptor: aInfo.domainFreeExperiences) {
					if (StringUtils.hasCharacters(adaptor.domainType)) {
						info.addDomainFreeExperience(new Pair<String, Integer>(adaptor.domainType, adaptor.iExperience));
					}
				}
			}

			if (CollectionUtils.hasElements(aInfo.domainProductionModifiers)) {
				for (AdaptedDomainProductionModifiers adaptor: aInfo.domainProductionModifiers) {
					if (StringUtils.hasCharacters(adaptor.domainType)) {
						info.addDomainProductionModifier(new Pair<String, Integer>(adaptor.domainType, adaptor.iModifier));
					}
				}
			}

			if (CollectionUtils.hasElements(aInfo.buildingClassProductionModifiers)) {
				for (AdaptedBuildingClassProductionModifiers adaptor: aInfo.buildingClassProductionModifiers) {
					if (StringUtils.hasCharacters(adaptor.buildingClassType)) {
						info.addBuildingClassProductionModifier(new Pair<String, Integer>(adaptor.buildingClassType, adaptor.iModifier));
					}
				}
			}

			if (CollectionUtils.hasElements(aInfo.unitCombatProductionModifiers)) {
				for (AdaptedUnitCombatProductionModifiers adaptor: aInfo.unitCombatProductionModifiers) {
					if (StringUtils.hasCharacters(adaptor.unitCombatType)) {
						info.addUnitCombatProductionModifier(new Pair<String, Integer>(adaptor.unitCombatType, adaptor.iModifier));
					}
				}
			}

			if (CollectionUtils.hasElements(aInfo.buildingHappinessChanges)) {
				for (AdaptedBuildingHappinessChanges adaptor: aInfo.buildingHappinessChanges) {
					if (StringUtils.hasCharacters(adaptor.buildingType)) {
						info.addBuildingHappinessChange(new Pair<String, Integer>(adaptor.buildingType, adaptor.iChange));
					}
				}
			}

			if (CollectionUtils.hasElements(aInfo.prereqNumOfBuildingClasses)) {
				for (AdaptedPrereqNumOfBuildingClasses adaptor: aInfo.prereqNumOfBuildingClasses) {
					if (StringUtils.hasCharacters(adaptor.buildingClassType)) {
						info.addPrereqNumOfBuildingClass(new Pair<String, Integer>(adaptor.buildingClassType, adaptor.iNeeded));
					}
				}
			}

			if (CollectionUtils.hasElements(aInfo.prereqAndBuildingClasses)) {
				for (String val: aInfo.prereqAndBuildingClasses) {
					if (StringUtils.hasCharacters(val)) {
						info.addPrereqAndBuildingClass(JaxbUtils.unmarshallString(val));
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

			if (CollectionUtils.hasElements(aInfo.replacedByBuildingClasses)) {
				for (String val: aInfo.replacedByBuildingClasses) {
					if (StringUtils.hasCharacters(val)) {
						info.addReplacedByBuildingClass(JaxbUtils.unmarshallString(val));
					}
				}
			}
			info.setSpecialistYieldChanges(aInfo.specialistYieldChanges);
			info.setBonusYieldModifiers(aInfo.bonusYieldModifiers);
			info.setBonusYieldChanges(aInfo.bonusYieldChanges);
			info.setVicinityBonusYieldChanges(aInfo.vicinityBonusYieldChanges);
			info.setTechYieldChanges(aInfo.techYieldChanges);
			info.setTechCommerceChanges(aInfo.techCommerceChanges);

			if (CollectionUtils.hasElements(aInfo.improvementFreeSpecialists)) {
				for (AdaptedImprovementFreeSpecialists adaptor: aInfo.improvementFreeSpecialists) {
					if (StringUtils.hasCharacters(adaptor.improvementType)) {
						info.addImprovementFreeSpecialist(new Pair<String, Integer>(adaptor.improvementType, adaptor.iCount));
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
			info.setHotKey(JaxbUtils.unmarshallString(aInfo.hotKey));
			info.setAltDown(JaxbUtils.unmarshallBoolean(aInfo.altDown));
			info.setShiftDown(JaxbUtils.unmarshallBoolean(aInfo.shiftDown));
			info.setCtrlDown(JaxbUtils.unmarshallBoolean(aInfo.ctrlDown));
			info.setHotKeyPriority(JaxbUtils.unmarshallInteger(aInfo.hotKeyPriority));
			info.setOrderPriority(JaxbUtils.unmarshallInteger(aInfo.orderPriority));
			info.setGraphicalOnly(JaxbUtils.unmarshallBoolean(aInfo.graphicalOnly));

			map.put(aInfo.type, info);
		}
		return map;
	}

	@Override
	public BuildingMap marshal(Map<String, IBuildingInfo> v) throws Exception {
		BuildingMap map = new BuildingMap();
		for (IBuildingInfo info: v.values()) {
			AdaptedBuilding aInfo = new AdaptedBuilding();
			aInfo.type = JaxbUtils.marshallString(info.getType());
			aInfo.buildingClass = JaxbUtils.marshallMandatoryString(info.getBuildingClass());
			aInfo.specialBuildingType = JaxbUtils.marshallString(info.getSpecialBuildingType());
			aInfo.description = JaxbUtils.marshallMandatoryString(info.getDescription());
			aInfo.civilopedia = JaxbUtils.marshallMandatoryString(info.getCivilopedia());
			aInfo.strategy = JaxbUtils.marshallMandatoryString(info.getStrategy());
			aInfo.help = JaxbUtils.marshallString(info.getHelp());
			aInfo.advisor = JaxbUtils.marshallMandatoryString(info.getAdvisor());
			aInfo.artDefineTag = JaxbUtils.marshallMandatoryString(info.getArtDefineTag());
			aInfo.movieDefineTag = JaxbUtils.marshallString(info.getMovieDefineTag());
			aInfo.autoBuild = JaxbUtils.marshallBoolean(info.isAutoBuild());
			aInfo.holyCity = JaxbUtils.marshallString(info.getHolyCity());
			aInfo.religionType = JaxbUtils.marshallString(info.getReligionType());
			aInfo.stateReligion = JaxbUtils.marshallString(info.getStateReligion());
			aInfo.stateReligionBool = JaxbUtils.marshallBoolean(info.isStateReligionBool());
			aInfo.prereqReligion = JaxbUtils.marshallString(info.getPrereqReligion());
			aInfo.prereqCorporation = JaxbUtils.marshallString(info.getPrereqCorporation());
			aInfo.foundsCorporation = JaxbUtils.marshallString(info.getFoundsCorporation());
			aInfo.globalReligionCommerce = JaxbUtils.marshallString(info.getGlobalReligionCommerce());
			aInfo.globalCorporationCommerce = JaxbUtils.marshallString(info.getGlobalCorporationCommerce());
			aInfo.victoryPrereq = JaxbUtils.marshallString(info.getVictoryPrereq());
			aInfo.freeStartEra = JaxbUtils.marshallString(info.getFreeStartEra());
			aInfo.maxStartEra = JaxbUtils.marshallString(info.getMaxStartEra());
			aInfo.obsoleteTech = JaxbUtils.marshallString(info.getObsoleteTech());

			if (CollectionUtils.hasElements(info.getPrereqAndTechs())) {
				aInfo.prereqAndTechs = new ArrayList<String>();
				for(String val: info.getPrereqAndTechs()) {
					aInfo.prereqAndTechs.add(JaxbUtils.marshallMandatoryString(val));
				}
			}
			aInfo.prereqBonus = JaxbUtils.marshallString(info.getPrereqBonus());

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
			aInfo.prereqVicinityBonusUnconnectedAllowed = JaxbUtils.marshallBoolean(info.isPrereqVicinityBonusUnconnectedAllowed());

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
					adaptor.traitType = pair.getKey();
					adaptor.iChange = pair.getValue();
					aInfo.productionTraits.add(adaptor);
				}
			}

			if (CollectionUtils.hasElements(info.getHappinessTraits())) {
				aInfo.happinessTraits = new ArrayList<AdaptedHappinessTraits>();
				for (IPair<String, Integer> pair: info.getHappinessTraits()) {
					AdaptedHappinessTraits adaptor = new AdaptedHappinessTraits();
					adaptor.traitType = pair.getKey();
					adaptor.iChange = pair.getValue();
					aInfo.happinessTraits.add(adaptor);
				}
			}
			aInfo.noBonus = JaxbUtils.marshallString(info.getNoBonus());
			aInfo.powerBonus = JaxbUtils.marshallString(info.getPowerBonus());
			aInfo.freeBonus = JaxbUtils.marshallString(info.getFreeBonus());
			aInfo.numFreeBonuses = JaxbUtils.marshallInteger(info.getNumFreeBonuses());
			aInfo.freeBuilding = JaxbUtils.marshallString(info.getFreeBuilding());
			aInfo.freePromotion = JaxbUtils.marshallString(info.getFreePromotion());
			aInfo.freeUnitClass = JaxbUtils.marshallString(info.getFreeUnitClass());
			aInfo.createFeatureType = JaxbUtils.marshallString(info.getCreateFeatureType());
			aInfo.civicOption = JaxbUtils.marshallString(info.getCivicOption());
			aInfo.greatPeopleUnitClass = JaxbUtils.marshallString(info.getGreatPeopleUnitClass());
			aInfo.greatPeopleRateChange = JaxbUtils.marshallInteger(info.getGreatPeopleRateChange());
			aInfo.hurryAngerModifier = JaxbUtils.marshallInteger(info.getHurryAngerModifier());
			aInfo.borderObstacle = JaxbUtils.marshallBoolean(info.isBorderObstacle());
			aInfo.teamShare = JaxbUtils.marshallBoolean(info.isTeamShare());
			aInfo.water = JaxbUtils.marshallBoolean(info.isWater());
			aInfo.river = JaxbUtils.marshallBoolean(info.isRiver());
			aInfo.powerBool = JaxbUtils.marshallBoolean(info.isPowerBool());
			aInfo.dirtyPower = JaxbUtils.marshallBoolean(info.isDirtyPower());
			aInfo.areaCleanPower = JaxbUtils.marshallBoolean(info.isAreaCleanPower());
			aInfo.diploVoteType = JaxbUtils.marshallString(info.getDiploVoteType());
			aInfo.forceTeamVoteEligible = JaxbUtils.marshallBoolean(info.isForceTeamVoteEligible());
			aInfo.capital = JaxbUtils.marshallBoolean(info.isCapital());
			aInfo.governmentCenter = JaxbUtils.marshallBoolean(info.isGovernmentCenter());
			aInfo.goldenAge = JaxbUtils.marshallBoolean(info.isGoldenAge());
			aInfo.allowsNukes = JaxbUtils.marshallBoolean(info.isAllowsNukes());
			aInfo.mapCentering = JaxbUtils.marshallBoolean(info.isMapCentering());
			aInfo.noUnhappiness = JaxbUtils.marshallBoolean(info.isNoUnhappiness());
			aInfo.unhealthyPopulationModifier = JaxbUtils.marshallInteger(info.getUnhealthyPopulationModifier());
			aInfo.buildingOnlyHealthy = JaxbUtils.marshallBoolean(info.isBuildingOnlyHealthy());
			aInfo.neverCapture = JaxbUtils.marshallBoolean(info.isNeverCapture());
			aInfo.nukeImmune = JaxbUtils.marshallBoolean(info.isNukeImmune());
			aInfo.prereqReligionBool = JaxbUtils.marshallBoolean(info.isPrereqReligionBool());
			aInfo.centerInCity = JaxbUtils.marshallBoolean(info.isCenterInCity());
			aInfo.slaveMarket = JaxbUtils.marshallBoolean(info.isSlaveMarket());
			aInfo.applyAllFreePromotionsOnMove = JaxbUtils.marshallBoolean(info.isApplyAllFreePromotionsOnMove());
			aInfo.applyFreePromotionOnMove = JaxbUtils.marshallBoolean(info.isApplyFreePromotionOnMove());
			aInfo.unitCityDeathCulture = JaxbUtils.marshallBoolean(info.isUnitCityDeathCulture());
			aInfo.unitAllCityDeathCulture = JaxbUtils.marshallBoolean(info.isUnitAllCityDeathCulture());
			aInfo.forceDisableStarSigns = JaxbUtils.marshallBoolean(info.isForceDisableStarSigns());
			aInfo.starSignGoodOnly = JaxbUtils.marshallBoolean(info.isStarSignGoodOnly());
			aInfo.starSignMitigateChangePercent = JaxbUtils.marshallInteger(info.getStarSignMitigateChangePercent());
			aInfo.globalStarSignMitigateChangePercent = JaxbUtils.marshallInteger(info.getGlobalStarSignMitigateChangePercent());
			aInfo.starSignScaleChangePercent = JaxbUtils.marshallInteger(info.getStarSignScaleChangePercent());
			aInfo.globalStarSignScaleChangePercent = JaxbUtils.marshallInteger(info.getGlobalStarSignScaleChangePercent());
			aInfo.aIWeight = JaxbUtils.marshallInteger(info.getAIWeight());
			aInfo.cost = JaxbUtils.marshallInteger(info.getCost());
			aInfo.hurryCostModifier = JaxbUtils.marshallInteger(info.getHurryCostModifier());
			aInfo.advancedStartCost = JaxbUtils.marshallInteger(info.getAdvancedStartCost());
			aInfo.advancedStartCostIncrease = JaxbUtils.marshallInteger(info.getAdvancedStartCostIncrease());
			aInfo.extraBarbarianCostChange = JaxbUtils.marshallInteger(info.getExtraBarbarianCostChange());
			aInfo.barbarianConversionCostModifier = JaxbUtils.marshallInteger(info.getBarbarianConversionCostModifier());
			aInfo.minAreaSize = JaxbUtils.marshallInteger(info.getMinAreaSize());
			aInfo.conquestProb = JaxbUtils.marshallInteger(info.getConquestProb());
			aInfo.citiesPrereq = JaxbUtils.marshallInteger(info.getCitiesPrereq());
			aInfo.teamsPrereq = JaxbUtils.marshallInteger(info.getTeamsPrereq());
			aInfo.levelPrereq = JaxbUtils.marshallInteger(info.getLevelPrereq());
			aInfo.minLatitude = JaxbUtils.marshallInteger(info.getMinLatitude());
			aInfo.maxLatitude = JaxbUtils.marshallInteger(info.getMaxLatitude());
			aInfo.workableRadius = JaxbUtils.marshallInteger(info.getWorkableRadius());
			aInfo.greatPeopleRateModifier = JaxbUtils.marshallInteger(info.getGreatPeopleRateModifier());
			aInfo.greatGeneralRateModifier = JaxbUtils.marshallInteger(info.getGreatGeneralRateModifier());
			aInfo.domesticGreatGeneralRateModifier = JaxbUtils.marshallInteger(info.getDomesticGreatGeneralRateModifier());
			aInfo.globalGreatPeopleRateModifier = JaxbUtils.marshallInteger(info.getGlobalGreatPeopleRateModifier());
			aInfo.anarchyModifier = JaxbUtils.marshallInteger(info.getAnarchyModifier());
			aInfo.goldenAgeModifier = JaxbUtils.marshallInteger(info.getGoldenAgeModifier());
			aInfo.globalHurryModifier = JaxbUtils.marshallInteger(info.getGlobalHurryModifier());
			aInfo.experience = JaxbUtils.marshallInteger(info.getExperience());
			aInfo.globalExperience = JaxbUtils.marshallInteger(info.getGlobalExperience());
			aInfo.foodKept = JaxbUtils.marshallInteger(info.getFoodKept());
			aInfo.airlift = JaxbUtils.marshallInteger(info.getAirlift());
			aInfo.airModifier = JaxbUtils.marshallInteger(info.getAirModifier());
			aInfo.airUnitCapacity = JaxbUtils.marshallInteger(info.getAirUnitCapacity());
			aInfo.nukeModifier = JaxbUtils.marshallInteger(info.getNukeModifier());
			aInfo.nukeExplosionRand = JaxbUtils.marshallInteger(info.getNukeExplosionRand());
			aInfo.freeSpecialist = JaxbUtils.marshallInteger(info.getFreeSpecialist());
			aInfo.areaFreeSpecialist = JaxbUtils.marshallInteger(info.getAreaFreeSpecialist());
			aInfo.globalFreeSpecialist = JaxbUtils.marshallInteger(info.getGlobalFreeSpecialist());
			aInfo.maintenanceModifier = JaxbUtils.marshallInteger(info.getMaintenanceModifier());
			aInfo.warWearinessModifier = JaxbUtils.marshallInteger(info.getWarWearinessModifier());
			aInfo.globalWarWearinessModifier = JaxbUtils.marshallInteger(info.getGlobalWarWearinessModifier());
			aInfo.enemyWarWearinessModifier = JaxbUtils.marshallInteger(info.getEnemyWarWearinessModifier());
			aInfo.healRateChange = JaxbUtils.marshallInteger(info.getHealRateChange());
			aInfo.health = JaxbUtils.marshallInteger(info.getHealth());
			aInfo.areaHealth = JaxbUtils.marshallInteger(info.getAreaHealth());
			aInfo.globalHealth = JaxbUtils.marshallInteger(info.getGlobalHealth());
			aInfo.happiness = JaxbUtils.marshallInteger(info.getHappiness());
			aInfo.areaHappiness = JaxbUtils.marshallInteger(info.getAreaHappiness());
			aInfo.globalHappiness = JaxbUtils.marshallInteger(info.getGlobalHappiness());
			aInfo.stateReligionHappiness = JaxbUtils.marshallInteger(info.getStateReligionHappiness());
			aInfo.workerSpeedModifier = JaxbUtils.marshallInteger(info.getWorkerSpeedModifier());
			aInfo.militaryProductionModifier = JaxbUtils.marshallInteger(info.getMilitaryProductionModifier());
			aInfo.spaceProductionModifier = JaxbUtils.marshallInteger(info.getSpaceProductionModifier());
			aInfo.globalSpaceProductionModifier = JaxbUtils.marshallInteger(info.getGlobalSpaceProductionModifier());
			aInfo.tradeRoutes = JaxbUtils.marshallInteger(info.getTradeRoutes());
			aInfo.coastalTradeRoutes = JaxbUtils.marshallInteger(info.getCoastalTradeRoutes());
			aInfo.globalTradeRoutes = JaxbUtils.marshallInteger(info.getGlobalTradeRoutes());
			aInfo.tradeRouteModifier = JaxbUtils.marshallInteger(info.getTradeRouteModifier());
			aInfo.foreignTradeRouteModifier = JaxbUtils.marshallInteger(info.getForeignTradeRouteModifier());
			aInfo.globalPopulationChange = JaxbUtils.marshallInteger(info.getGlobalPopulationChange());
			aInfo.globalFoundPopulationChange = JaxbUtils.marshallInteger(info.getGlobalFoundPopulationChange());
			aInfo.freeTechs = JaxbUtils.marshallInteger(info.getFreeTechs());
			aInfo.defense = JaxbUtils.marshallInteger(info.getDefense());
			aInfo.obsoleteDefence = JaxbUtils.marshallInteger(info.getObsoleteDefence());
			aInfo.bombardDefense = JaxbUtils.marshallInteger(info.getBombardDefense());
			aInfo.allCityDefense = JaxbUtils.marshallInteger(info.getAllCityDefense());
			aInfo.espionageDefense = JaxbUtils.marshallInteger(info.getEspionageDefense());
			aInfo.asset = JaxbUtils.marshallInteger(info.getAsset());
			aInfo.power = JaxbUtils.marshallInteger(info.getPower());
			aInfo.globalWonderProductionModifier = JaxbUtils.marshallInteger(info.getGlobalWonderProductionModifier());
			aInfo.fVisibilityPriority = JaxbUtils.marshallString(info.getfVisibilityPriority());

			if (CollectionUtils.hasElements(info.getSeaPlotYieldChanges())) {
				aInfo.seaPlotYieldChanges = new ArrayList<Integer>();
				for(Integer val: info.getSeaPlotYieldChanges()) {
					aInfo.seaPlotYieldChanges.add(JaxbUtils.marshallMandatoryInteger(val));
				}
			}

			if (CollectionUtils.hasElements(info.getRiverPlotYieldChanges())) {
				aInfo.riverPlotYieldChanges = new ArrayList<Integer>();
				for(Integer val: info.getRiverPlotYieldChanges()) {
					aInfo.riverPlotYieldChanges.add(JaxbUtils.marshallMandatoryInteger(val));
				}
			}

			if (CollectionUtils.hasElements(info.getGlobalSeaPlotYieldChanges())) {
				aInfo.globalSeaPlotYieldChanges = new ArrayList<Integer>();
				for(Integer val: info.getGlobalSeaPlotYieldChanges()) {
					aInfo.globalSeaPlotYieldChanges.add(JaxbUtils.marshallMandatoryInteger(val));
				}
			}

			if (CollectionUtils.hasElements(info.getYieldChanges())) {
				aInfo.yieldChanges = new ArrayList<Integer>();
				for(Integer val: info.getYieldChanges()) {
					aInfo.yieldChanges.add(JaxbUtils.marshallMandatoryInteger(val));
				}
			}

			if (CollectionUtils.hasElements(info.getGlobalYieldChanges())) {
				aInfo.globalYieldChanges = new ArrayList<Integer>();
				for(Integer val: info.getGlobalYieldChanges()) {
					aInfo.globalYieldChanges.add(JaxbUtils.marshallMandatoryInteger(val));
				}
			}

			if (CollectionUtils.hasElements(info.getYieldModifiers())) {
				aInfo.yieldModifiers = new ArrayList<Integer>();
				for(Integer val: info.getYieldModifiers()) {
					aInfo.yieldModifiers.add(JaxbUtils.marshallMandatoryInteger(val));
				}
			}

			if (CollectionUtils.hasElements(info.getPowerYieldModifiers())) {
				aInfo.powerYieldModifiers = new ArrayList<Integer>();
				for(Integer val: info.getPowerYieldModifiers()) {
					aInfo.powerYieldModifiers.add(JaxbUtils.marshallMandatoryInteger(val));
				}
			}

			if (CollectionUtils.hasElements(info.getAreaYieldModifiers())) {
				aInfo.areaYieldModifiers = new ArrayList<Integer>();
				for(Integer val: info.getAreaYieldModifiers()) {
					aInfo.areaYieldModifiers.add(JaxbUtils.marshallMandatoryInteger(val));
				}
			}

			if (CollectionUtils.hasElements(info.getGlobalYieldModifiers())) {
				aInfo.globalYieldModifiers = new ArrayList<Integer>();
				for(Integer val: info.getGlobalYieldModifiers()) {
					aInfo.globalYieldModifiers.add(JaxbUtils.marshallMandatoryInteger(val));
				}
			}

			if (CollectionUtils.hasElements(info.getCommerceChanges())) {
				aInfo.commerceChanges = new ArrayList<Integer>();
				for(Integer val: info.getCommerceChanges()) {
					aInfo.commerceChanges.add(JaxbUtils.marshallMandatoryInteger(val));
				}
			}

			if (CollectionUtils.hasElements(info.getObsoleteSafeCommerceChanges())) {
				aInfo.obsoleteSafeCommerceChanges = new ArrayList<Integer>();
				for(Integer val: info.getObsoleteSafeCommerceChanges()) {
					aInfo.obsoleteSafeCommerceChanges.add(JaxbUtils.marshallMandatoryInteger(val));
				}
			}

			if (CollectionUtils.hasElements(info.getCommerceChangeDoubleTimes())) {
				aInfo.commerceChangeDoubleTimes = new ArrayList<Integer>();
				for(Integer val: info.getCommerceChangeDoubleTimes()) {
					aInfo.commerceChangeDoubleTimes.add(JaxbUtils.marshallMandatoryInteger(val));
				}
			}

			if (CollectionUtils.hasElements(info.getCommerceModifiers())) {
				aInfo.commerceModifiers = new ArrayList<Integer>();
				for(Integer val: info.getCommerceModifiers()) {
					aInfo.commerceModifiers.add(JaxbUtils.marshallMandatoryInteger(val));
				}
			}

			if (CollectionUtils.hasElements(info.getGlobalCommerceModifiers())) {
				aInfo.globalCommerceModifiers = new ArrayList<Integer>();
				for(Integer val: info.getGlobalCommerceModifiers()) {
					aInfo.globalCommerceModifiers.add(JaxbUtils.marshallMandatoryInteger(val));
				}
			}

			if (CollectionUtils.hasElements(info.getSpecialistExtraCommerces())) {
				aInfo.specialistExtraCommerces = new ArrayList<Integer>();
				for(Integer val: info.getSpecialistExtraCommerces()) {
					aInfo.specialistExtraCommerces.add(JaxbUtils.marshallMandatoryInteger(val));
				}
			}

			if (CollectionUtils.hasElements(info.getStateReligionCommerces())) {
				aInfo.stateReligionCommerces = new ArrayList<Integer>();
				for(Integer val: info.getStateReligionCommerces()) {
					aInfo.stateReligionCommerces.add(JaxbUtils.marshallMandatoryInteger(val));
				}
			}

			if (CollectionUtils.hasElements(info.getCommerceHappinesses())) {
				aInfo.commerceHappinesses = new ArrayList<Integer>();
				for(Integer val: info.getCommerceHappinesses()) {
					aInfo.commerceHappinesses.add(JaxbUtils.marshallMandatoryInteger(val));
				}
			}

			if (CollectionUtils.hasElements(info.getReligionChanges())) {
				aInfo.religionChanges = new ArrayList<AdaptedReligionChanges>();
				for (IPair<String, Integer> pair: info.getReligionChanges()) {
					AdaptedReligionChanges adaptor = new AdaptedReligionChanges();
					adaptor.religionType = pair.getKey();
					adaptor.iChange = pair.getValue();
					aInfo.religionChanges.add(adaptor);
				}
			}

			if (CollectionUtils.hasElements(info.getSpecialistCounts())) {
				aInfo.specialistCounts = new ArrayList<AdaptedSpecialistCounts>();
				for (IPair<String, Integer> pair: info.getSpecialistCounts()) {
					AdaptedSpecialistCounts adaptor = new AdaptedSpecialistCounts();
					adaptor.specialistType = pair.getKey();
					adaptor.iCount = pair.getValue();
					aInfo.specialistCounts.add(adaptor);
				}
			}

			if (CollectionUtils.hasElements(info.getFreeSpecialistCounts())) {
				aInfo.freeSpecialistCounts = new ArrayList<AdaptedFreeSpecialistCounts>();
				for (IPair<String, Integer> pair: info.getFreeSpecialistCounts()) {
					AdaptedFreeSpecialistCounts adaptor = new AdaptedFreeSpecialistCounts();
					adaptor.specialistType = pair.getKey();
					adaptor.iCount = pair.getValue();
					aInfo.freeSpecialistCounts.add(adaptor);
				}
			}

			if (CollectionUtils.hasElements(info.getCommerceFlexibles())) {
				aInfo.commerceFlexibles = new ArrayList<Integer>();
				for(Boolean val: info.getCommerceFlexibles()) {
					aInfo.commerceFlexibles.add(JaxbUtils.marshallMandatoryBoolean(val));
				}
			}

			if (CollectionUtils.hasElements(info.getCommerceChangeOriginalOwners())) {
				aInfo.commerceChangeOriginalOwners = new ArrayList<Integer>();
				for(Boolean val: info.getCommerceChangeOriginalOwners()) {
					aInfo.commerceChangeOriginalOwners.add(JaxbUtils.marshallMandatoryBoolean(val));
				}
			}
			aInfo.constructSound = JaxbUtils.marshallString(info.getConstructSound());

			if (CollectionUtils.hasElements(info.getBonusHealthChanges())) {
				aInfo.bonusHealthChanges = new ArrayList<AdaptedBonusHealthChanges>();
				for (IPair<String, Integer> pair: info.getBonusHealthChanges()) {
					AdaptedBonusHealthChanges adaptor = new AdaptedBonusHealthChanges();
					adaptor.bonusType = pair.getKey();
					adaptor.iChange = pair.getValue();
					aInfo.bonusHealthChanges.add(adaptor);
				}
			}

			if (CollectionUtils.hasElements(info.getBonusHappinessChanges())) {
				aInfo.bonusHappinessChanges = new ArrayList<AdaptedBonusHappinessChanges>();
				for (IPair<String, Integer> pair: info.getBonusHappinessChanges()) {
					AdaptedBonusHappinessChanges adaptor = new AdaptedBonusHappinessChanges();
					adaptor.bonusType = pair.getKey();
					adaptor.iChange = pair.getValue();
					aInfo.bonusHappinessChanges.add(adaptor);
				}
			}

			if (CollectionUtils.hasElements(info.getBonusProductionModifiers())) {
				aInfo.bonusProductionModifiers = new ArrayList<AdaptedBonusProductionModifiers>();
				for (IPair<String, Integer> pair: info.getBonusProductionModifiers()) {
					AdaptedBonusProductionModifiers adaptor = new AdaptedBonusProductionModifiers();
					adaptor.bonusType = pair.getKey();
					adaptor.iModifier = pair.getValue();
					aInfo.bonusProductionModifiers.add(adaptor);
				}
			}

			if (CollectionUtils.hasElements(info.getUnitCombatFreeExperiences())) {
				aInfo.unitCombatFreeExperiences = new ArrayList<AdaptedUnitCombatFreeExperiences>();
				for (IPair<String, Integer> pair: info.getUnitCombatFreeExperiences()) {
					AdaptedUnitCombatFreeExperiences adaptor = new AdaptedUnitCombatFreeExperiences();
					adaptor.unitCombatType = pair.getKey();
					adaptor.iExperience = pair.getValue();
					aInfo.unitCombatFreeExperiences.add(adaptor);
				}
			}

			if (CollectionUtils.hasElements(info.getDomainFreeExperiences())) {
				aInfo.domainFreeExperiences = new ArrayList<AdaptedDomainFreeExperiences>();
				for (IPair<String, Integer> pair: info.getDomainFreeExperiences()) {
					AdaptedDomainFreeExperiences adaptor = new AdaptedDomainFreeExperiences();
					adaptor.domainType = pair.getKey();
					adaptor.iExperience = pair.getValue();
					aInfo.domainFreeExperiences.add(adaptor);
				}
			}

			if (CollectionUtils.hasElements(info.getDomainProductionModifiers())) {
				aInfo.domainProductionModifiers = new ArrayList<AdaptedDomainProductionModifiers>();
				for (IPair<String, Integer> pair: info.getDomainProductionModifiers()) {
					AdaptedDomainProductionModifiers adaptor = new AdaptedDomainProductionModifiers();
					adaptor.domainType = pair.getKey();
					adaptor.iModifier = pair.getValue();
					aInfo.domainProductionModifiers.add(adaptor);
				}
			}

			if (CollectionUtils.hasElements(info.getBuildingClassProductionModifiers())) {
				aInfo.buildingClassProductionModifiers = new ArrayList<AdaptedBuildingClassProductionModifiers>();
				for (IPair<String, Integer> pair: info.getBuildingClassProductionModifiers()) {
					AdaptedBuildingClassProductionModifiers adaptor = new AdaptedBuildingClassProductionModifiers();
					adaptor.buildingClassType = pair.getKey();
					adaptor.iModifier = pair.getValue();
					aInfo.buildingClassProductionModifiers.add(adaptor);
				}
			}

			if (CollectionUtils.hasElements(info.getUnitCombatProductionModifiers())) {
				aInfo.unitCombatProductionModifiers = new ArrayList<AdaptedUnitCombatProductionModifiers>();
				for (IPair<String, Integer> pair: info.getUnitCombatProductionModifiers()) {
					AdaptedUnitCombatProductionModifiers adaptor = new AdaptedUnitCombatProductionModifiers();
					adaptor.unitCombatType = pair.getKey();
					adaptor.iModifier = pair.getValue();
					aInfo.unitCombatProductionModifiers.add(adaptor);
				}
			}

			if (CollectionUtils.hasElements(info.getBuildingHappinessChanges())) {
				aInfo.buildingHappinessChanges = new ArrayList<AdaptedBuildingHappinessChanges>();
				for (IPair<String, Integer> pair: info.getBuildingHappinessChanges()) {
					AdaptedBuildingHappinessChanges adaptor = new AdaptedBuildingHappinessChanges();
					adaptor.buildingType = pair.getKey();
					adaptor.iChange = pair.getValue();
					aInfo.buildingHappinessChanges.add(adaptor);
				}
			}

			if (CollectionUtils.hasElements(info.getPrereqNumOfBuildingClasses())) {
				aInfo.prereqNumOfBuildingClasses = new ArrayList<AdaptedPrereqNumOfBuildingClasses>();
				for (IPair<String, Integer> pair: info.getPrereqNumOfBuildingClasses()) {
					AdaptedPrereqNumOfBuildingClasses adaptor = new AdaptedPrereqNumOfBuildingClasses();
					adaptor.buildingClassType = pair.getKey();
					adaptor.iNeeded = pair.getValue();
					aInfo.prereqNumOfBuildingClasses.add(adaptor);
				}
			}

			if (CollectionUtils.hasElements(info.getPrereqAndBuildingClasses())) {
				aInfo.prereqAndBuildingClasses = new ArrayList<String>();
				for(String val: info.getPrereqAndBuildingClasses()) {
					aInfo.prereqAndBuildingClasses.add(JaxbUtils.marshallMandatoryString(val));
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

			if (CollectionUtils.hasElements(info.getReplacedByBuildingClasses())) {
				aInfo.replacedByBuildingClasses = new ArrayList<String>();
				for(String val: info.getReplacedByBuildingClasses()) {
					aInfo.replacedByBuildingClasses.add(JaxbUtils.marshallMandatoryString(val));
				}
			}
			aInfo.specialistYieldChanges = info.getSpecialistYieldChanges();
			aInfo.bonusYieldModifiers = info.getBonusYieldModifiers();
			aInfo.bonusYieldChanges = info.getBonusYieldChanges();
			aInfo.vicinityBonusYieldChanges = info.getVicinityBonusYieldChanges();
			aInfo.techYieldChanges = info.getTechYieldChanges();
			aInfo.techCommerceChanges = info.getTechCommerceChanges();

			if (CollectionUtils.hasElements(info.getImprovementFreeSpecialists())) {
				aInfo.improvementFreeSpecialists = new ArrayList<AdaptedImprovementFreeSpecialists>();
				for (IPair<String, Integer> pair: info.getImprovementFreeSpecialists()) {
					AdaptedImprovementFreeSpecialists adaptor = new AdaptedImprovementFreeSpecialists();
					adaptor.improvementType = pair.getKey();
					adaptor.iCount = pair.getValue();
					aInfo.improvementFreeSpecialists.add(adaptor);
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
			aInfo.hotKey = JaxbUtils.marshallString(info.getHotKey());
			aInfo.altDown = JaxbUtils.marshallBoolean(info.isAltDown());
			aInfo.shiftDown = JaxbUtils.marshallBoolean(info.isShiftDown());
			aInfo.ctrlDown = JaxbUtils.marshallBoolean(info.isCtrlDown());
			aInfo.hotKeyPriority = JaxbUtils.marshallInteger(info.getHotKeyPriority());
			aInfo.orderPriority = JaxbUtils.marshallInteger(info.getOrderPriority());
			aInfo.graphicalOnly = JaxbUtils.marshallBoolean(info.isGraphicalOnly());

			map.entries.add(aInfo);
		}
		return map;
	}
}