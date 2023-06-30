package org.archid.civ4.info.building;

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

@XmlRootElement(name="Civ4BuildingInfos", namespace="x-schema:CIV4BuildingsSchema.xml")
@XmlAccessorType(XmlAccessType.NONE)
public class BuildingInfos extends AbstractInfos<IBuildingInfo> {

	public BuildingInfos() {
		super(new LinkedHashMap<String, IBuildingInfo>());
	}

	@XmlJavaTypeAdapter(BuildingMapAdapter.class)
	@XmlElement(name="BuildingInfos")
	public Map<String, IBuildingInfo> getInfoMap() {
		return infos;
	}

	public static IBuildingInfo createInfo(String type) {
		return new BuildingInfo(type);
	}

	@Override
	public void setInfoMap(Map<String, IBuildingInfo> infos) {
		this.infos = infos;
	}

	private static class BuildingInfo implements IBuildingInfo{

		private String buildingClass;
		private String type;
		private String specialBuildingType;
		private String description;
		private String civilopedia;
		private String strategy;
		private String help;
		private String advisor;
		private String artDefineTag;
		private String movieDefineTag;
		private Boolean autoBuild;
		private String holyCity;
		private String religionType;
		private String stateReligion;
		private Boolean stateReligionBool;
		private String prereqReligion;
		private String prereqCorporation;
		private String foundsCorporation;
		private String globalReligionCommerce;
		private String globalCorporationCommerce;
		private String victoryPrereq;
		private String freeStartEra;
		private String maxStartEra;
		private String obsoleteTech;
		private List<String> prereqAndTechs = new ArrayList<String>();
		private String prereqBonus;
		private List<String> prereqOrBonuses = new ArrayList<String>();
		private List<String> prereqAndCivics = new ArrayList<String>();
		private List<String> prereqOrCivics = new ArrayList<String>();
		private List<String> prereqAndTerrains = new ArrayList<String>();
		private List<String> prereqOrTerrains = new ArrayList<String>();
		private List<String> prereqVicinityAndBonus = new ArrayList<String>();
		private List<String> prereqVicinityOrBonus = new ArrayList<String>();
		private Boolean prereqVicinityBonusUnconnectedAllowed;
		private List<String> prereqVicinityImprovements = new ArrayList<String>();
		private List<String> prereqVicinityFeatures = new ArrayList<String>();
		private List<String> prereqWorldViews = new ArrayList<String>();
		private Integer minPopulation;
		private String minCultureLevel;
		private Boolean prereqPower;
		private List<IPair<String, Integer>> productionTraits = new ArrayList<IPair<String, Integer>>();
		private List<IPair<String, Integer>> happinessTraits = new ArrayList<IPair<String, Integer>>();
		private String noBonus;
		private String powerBonus;
		private String freeBonus;
		private Integer numFreeBonuses;
		private String freeBuilding;
		private String freePromotion;
		private String freeUnitClass;
		private String createFeatureType;
		private String civicOption;
		private String greatPeopleUnitClass;
		private Integer greatPeopleRateChange;
		private Integer hurryAngerModifier;
		private Boolean borderObstacle;
		private Boolean teamShare;
		private Boolean water;
		private Boolean river;
		private Boolean powerBool;
		private Boolean dirtyPower;
		private Boolean areaCleanPower;
		private String diploVoteType;
		private Boolean forceTeamVoteEligible;
		private Boolean capital;
		private Boolean governmentCenter;
		private Boolean goldenAge;
		private Boolean allowsNukes;
		private Boolean mapCentering;
		private Boolean noUnhappiness;
		private Integer unhealthyPopulationModifier;
		private Boolean buildingOnlyHealthy;
		private Boolean neverCapture;
		private Boolean nukeImmune;
		private Boolean prereqReligionBool;
		private Boolean centerInCity;
		private Boolean slaveMarket;
		private Boolean applyAllFreePromotionsOnMove;
		private Boolean applyFreePromotionOnMove;
		private Boolean unitCityDeathCulture;
		private Boolean unitAllCityDeathCulture;
		private Boolean forceDisableStarSigns;
		private Boolean starSignGoodOnly;
		private Integer starSignMitigateChangePercent;
		private Integer globalStarSignMitigateChangePercent;
		private Integer starSignScaleChangePercent;
		private Integer globalStarSignScaleChangePercent;
		private Integer aIWeight;
		private Integer cost;
		private Integer hurryCostModifier;
		private Integer advancedStartCost;
		private Integer advancedStartCostIncrease;
		private Integer extraBarbarianCostChange;
		private Integer barbarianConversionCostModifier;
		private Integer minAreaSize;
		private Integer conquestProb;
		private Integer citiesPrereq;
		private Integer teamsPrereq;
		private Integer levelPrereq;
		private Integer minLatitude;
		private Integer maxLatitude;
		private Integer workableRadius;
		private Integer greatPeopleRateModifier;
		private Integer greatGeneralRateModifier;
		private Integer domesticGreatGeneralRateModifier;
		private Integer globalGreatPeopleRateModifier;
		private Integer anarchyModifier;
		private Integer goldenAgeModifier;
		private Integer globalHurryModifier;
		private Integer experience;
		private Integer globalExperience;
		private Integer foodKept;
		private Integer airlift;
		private Integer airModifier;
		private Integer airUnitCapacity;
		private Integer nukeModifier;
		private Integer nukeExplosionRand;
		private Integer freeSpecialist;
		private Integer areaFreeSpecialist;
		private Integer globalFreeSpecialist;
		private Integer maintenanceModifier;
		private Integer warWearinessModifier;
		private Integer globalWarWearinessModifier;
		private Integer enemyWarWearinessModifier;
		private Integer healRateChange;
		private Integer health;
		private Integer areaHealth;
		private Integer globalHealth;
		private Integer happiness;
		private Integer areaHappiness;
		private Integer globalHappiness;
		private Integer stateReligionHappiness;
		private Integer workerSpeedModifier;
		private Integer militaryProductionModifier;
		private Integer spaceProductionModifier;
		private Integer globalSpaceProductionModifier;
		private Integer tradeRoutes;
		private Integer coastalTradeRoutes;
		private Integer globalTradeRoutes;
		private Integer tradeRouteModifier;
		private Integer foreignTradeRouteModifier;
		private Integer globalPopulationChange;
		private Integer globalFoundPopulationChange;
		private Integer freeTechs;
		private Integer defense;
		private Integer obsoleteDefence;
		private Integer bombardDefense;
		private Integer allCityDefense;
		private Integer espionageDefense;
		private Integer asset;
		private Integer power;
		private Integer globalWonderProductionModifier;
		private String fVisibilityPriority;
		private List<Integer> seaPlotYieldChanges = new ArrayList<Integer>();
		private List<Integer> riverPlotYieldChanges = new ArrayList<Integer>();
		private List<Integer> globalSeaPlotYieldChanges = new ArrayList<Integer>();
		private List<Integer> yieldChanges = new ArrayList<Integer>();
		private List<Integer> globalYieldChanges = new ArrayList<Integer>();
		private List<Integer> yieldModifiers = new ArrayList<Integer>();
		private List<Integer> powerYieldModifiers = new ArrayList<Integer>();
		private List<Integer> areaYieldModifiers = new ArrayList<Integer>();
		private List<Integer> globalYieldModifiers = new ArrayList<Integer>();
		private List<Integer> commerceChanges = new ArrayList<Integer>();
		private List<Integer> obsoleteSafeCommerceChanges = new ArrayList<Integer>();
		private List<Integer> commerceChangeDoubleTimes = new ArrayList<Integer>();
		private List<Integer> commerceModifiers = new ArrayList<Integer>();
		private List<Integer> globalCommerceModifiers = new ArrayList<Integer>();
		private List<Integer> specialistExtraCommerces = new ArrayList<Integer>();
		private List<Integer> stateReligionCommerces = new ArrayList<Integer>();
		private List<Integer> commerceHappinesses = new ArrayList<Integer>();
		private List<IPair<String, Integer>> religionChanges = new ArrayList<IPair<String, Integer>>();
		private List<IPair<String, Integer>> specialistCounts = new ArrayList<IPair<String, Integer>>();
		private List<IPair<String, Integer>> freeSpecialistCounts = new ArrayList<IPair<String, Integer>>();
		private List<Boolean> commerceFlexibles = new ArrayList<Boolean>();
		private List<Boolean> commerceChangeOriginalOwners = new ArrayList<Boolean>();
		private String constructSound;
		private List<IPair<String, Integer>> bonusHealthChanges = new ArrayList<IPair<String, Integer>>();
		private List<IPair<String, Integer>> bonusHappinessChanges = new ArrayList<IPair<String, Integer>>();
		private List<IPair<String, Integer>> bonusProductionModifiers = new ArrayList<IPair<String, Integer>>();
		private List<IPair<String, Integer>> unitCombatFreeExperiences = new ArrayList<IPair<String, Integer>>();
		private List<IPair<String, Integer>> domainFreeExperiences = new ArrayList<IPair<String, Integer>>();
		private List<IPair<String, Integer>> domainProductionModifiers = new ArrayList<IPair<String, Integer>>();
		private List<IPair<String, Integer>> buildingClassProductionModifiers = new ArrayList<IPair<String, Integer>>();
		private List<IPair<String, Integer>> unitCombatProductionModifiers = new ArrayList<IPair<String, Integer>>();
		private List<IPair<String, Integer>> buildingHappinessChanges = new ArrayList<IPair<String, Integer>>();
		private List<IPair<String, Integer>> prereqNumOfBuildingClasses = new ArrayList<IPair<String, Integer>>();
		private List<String> prereqAndBuildingClasses = new ArrayList<String>();
		private List<String> prereqOrBuildingClasses = new ArrayList<String>();
		private List<String> prereqNotBuildingClasses = new ArrayList<String>();
		private List<String> replacedByBuildingClasses = new ArrayList<String>();
		private SpecialistYieldChanges specialistYieldChanges = new SpecialistYieldChanges();
		private BonusYieldModifiers bonusYieldModifiers = new BonusYieldModifiers();
		private BonusYieldChanges bonusYieldChanges = new BonusYieldChanges();
		private VicinityBonusYieldChanges vicinityBonusYieldChanges = new VicinityBonusYieldChanges();
		private TechYieldChanges techYieldChanges = new TechYieldChanges();
		private TechCommerceChanges techCommerceChanges = new TechCommerceChanges();
		private List<IPair<String, Integer>> improvementFreeSpecialists = new ArrayList<IPair<String, Integer>>();
		private List<IPair<String, Integer>> flavors = new ArrayList<IPair<String, Integer>>();
		private String hotKey;
		private Boolean altDown;
		private Boolean shiftDown;
		private Boolean ctrlDown;
		private Integer hotKeyPriority;
		private Integer orderPriority;
		private Boolean graphicalOnly;

		private BuildingInfo(String type) {
			this.type = type;
		}

		@Override
		public String getBuildingClass() {
			return buildingClass;
		}

		@Override
		public void setBuildingClass(String buildingClass) {
			this.buildingClass = buildingClass;
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
		public String getSpecialBuildingType() {
			return specialBuildingType;
		}

		@Override
		public void setSpecialBuildingType(String specialBuildingType) {
			this.specialBuildingType = specialBuildingType;
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
		public String getArtDefineTag() {
			return artDefineTag;
		}

		@Override
		public void setArtDefineTag(String artDefineTag) {
			this.artDefineTag = artDefineTag;
		}

		@Override
		public String getMovieDefineTag() {
			return movieDefineTag;
		}

		@Override
		public void setMovieDefineTag(String movieDefineTag) {
			this.movieDefineTag = movieDefineTag;
		}

		@Override
		public Boolean isAutoBuild() {
			return autoBuild;
		}

		@Override
		public void setAutoBuild(Boolean autoBuild) {
			this.autoBuild = autoBuild;
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
		public Boolean isStateReligionBool() {
			return stateReligionBool;
		}

		@Override
		public void setStateReligionBool(Boolean stateReligionBool) {
			this.stateReligionBool = stateReligionBool;
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
		public String getFoundsCorporation() {
			return foundsCorporation;
		}

		@Override
		public void setFoundsCorporation(String foundsCorporation) {
			this.foundsCorporation = foundsCorporation;
		}

		@Override
		public String getGlobalReligionCommerce() {
			return globalReligionCommerce;
		}

		@Override
		public void setGlobalReligionCommerce(String globalReligionCommerce) {
			this.globalReligionCommerce = globalReligionCommerce;
		}

		@Override
		public String getGlobalCorporationCommerce() {
			return globalCorporationCommerce;
		}

		@Override
		public void setGlobalCorporationCommerce(String globalCorporationCommerce) {
			this.globalCorporationCommerce = globalCorporationCommerce;
		}

		@Override
		public String getVictoryPrereq() {
			return victoryPrereq;
		}

		@Override
		public void setVictoryPrereq(String victoryPrereq) {
			this.victoryPrereq = victoryPrereq;
		}

		@Override
		public String getFreeStartEra() {
			return freeStartEra;
		}

		@Override
		public void setFreeStartEra(String freeStartEra) {
			this.freeStartEra = freeStartEra;
		}

		@Override
		public String getMaxStartEra() {
			return maxStartEra;
		}

		@Override
		public void setMaxStartEra(String maxStartEra) {
			this.maxStartEra = maxStartEra;
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
		public List<String> getPrereqAndTechs() {
			return prereqAndTechs;
		}

		@Override
		public void addPrereqAndTech(String prereqAndTech) {
			this.prereqAndTechs.add(prereqAndTech);
		}

		@Override
		public String getPrereqBonus() {
			return prereqBonus;
		}

		@Override
		public void setPrereqBonus(String prereqBonus) {
			this.prereqBonus = prereqBonus;
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
		public Boolean isPrereqVicinityBonusUnconnectedAllowed() {
			return prereqVicinityBonusUnconnectedAllowed;
		}

		@Override
		public void setPrereqVicinityBonusUnconnectedAllowed(Boolean prereqVicinityBonusUnconnectedAllowed) {
			this.prereqVicinityBonusUnconnectedAllowed = prereqVicinityBonusUnconnectedAllowed;
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
		public List<IPair<String, Integer>> getHappinessTraits() {
			return happinessTraits;
		}

		@Override
		public void addHappinessTrait(IPair<String, Integer> happinessTrait) {
			this.happinessTraits.add(happinessTrait);
		}

		@Override
		public String getNoBonus() {
			return noBonus;
		}

		@Override
		public void setNoBonus(String noBonus) {
			this.noBonus = noBonus;
		}

		@Override
		public String getPowerBonus() {
			return powerBonus;
		}

		@Override
		public void setPowerBonus(String powerBonus) {
			this.powerBonus = powerBonus;
		}

		@Override
		public String getFreeBonus() {
			return freeBonus;
		}

		@Override
		public void setFreeBonus(String freeBonus) {
			this.freeBonus = freeBonus;
		}

		@Override
		public Integer getNumFreeBonuses() {
			return numFreeBonuses;
		}

		@Override
		public void setNumFreeBonuses(Integer numFreeBonuses) {
			this.numFreeBonuses = numFreeBonuses;
		}

		@Override
		public String getFreeBuilding() {
			return freeBuilding;
		}

		@Override
		public void setFreeBuilding(String freeBuilding) {
			this.freeBuilding = freeBuilding;
		}

		@Override
		public String getFreePromotion() {
			return freePromotion;
		}

		@Override
		public void setFreePromotion(String freePromotion) {
			this.freePromotion = freePromotion;
		}

		@Override
		public String getFreeUnitClass() {
			return freeUnitClass;
		}

		@Override
		public void setFreeUnitClass(String freeUnitClass) {
			this.freeUnitClass = freeUnitClass;
		}

		@Override
		public String getCreateFeatureType() {
			return createFeatureType;
		}

		@Override
		public void setCreateFeatureType(String createFeatureType) {
			this.createFeatureType = createFeatureType;
		}

		@Override
		public String getCivicOption() {
			return civicOption;
		}

		@Override
		public void setCivicOption(String civicOption) {
			this.civicOption = civicOption;
		}

		@Override
		public String getGreatPeopleUnitClass() {
			return greatPeopleUnitClass;
		}

		@Override
		public void setGreatPeopleUnitClass(String greatPeopleUnitClass) {
			this.greatPeopleUnitClass = greatPeopleUnitClass;
		}

		@Override
		public Integer getGreatPeopleRateChange() {
			return greatPeopleRateChange;
		}

		@Override
		public void setGreatPeopleRateChange(Integer greatPeopleRateChange) {
			this.greatPeopleRateChange = greatPeopleRateChange;
		}

		@Override
		public Integer getHurryAngerModifier() {
			return hurryAngerModifier;
		}

		@Override
		public void setHurryAngerModifier(Integer hurryAngerModifier) {
			this.hurryAngerModifier = hurryAngerModifier;
		}

		@Override
		public Boolean isBorderObstacle() {
			return borderObstacle;
		}

		@Override
		public void setBorderObstacle(Boolean borderObstacle) {
			this.borderObstacle = borderObstacle;
		}

		@Override
		public Boolean isTeamShare() {
			return teamShare;
		}

		@Override
		public void setTeamShare(Boolean teamShare) {
			this.teamShare = teamShare;
		}

		@Override
		public Boolean isWater() {
			return water;
		}

		@Override
		public void setWater(Boolean water) {
			this.water = water;
		}

		@Override
		public Boolean isRiver() {
			return river;
		}

		@Override
		public void setRiver(Boolean river) {
			this.river = river;
		}

		@Override
		public Boolean isPowerBool() {
			return powerBool;
		}

		@Override
		public void setPowerBool(Boolean powerBool) {
			this.powerBool = powerBool;
		}

		@Override
		public Boolean isDirtyPower() {
			return dirtyPower;
		}

		@Override
		public void setDirtyPower(Boolean dirtyPower) {
			this.dirtyPower = dirtyPower;
		}

		@Override
		public Boolean isAreaCleanPower() {
			return areaCleanPower;
		}

		@Override
		public void setAreaCleanPower(Boolean areaCleanPower) {
			this.areaCleanPower = areaCleanPower;
		}

		@Override
		public String getDiploVoteType() {
			return diploVoteType;
		}

		@Override
		public void setDiploVoteType(String diploVoteType) {
			this.diploVoteType = diploVoteType;
		}

		@Override
		public Boolean isForceTeamVoteEligible() {
			return forceTeamVoteEligible;
		}

		@Override
		public void setForceTeamVoteEligible(Boolean forceTeamVoteEligible) {
			this.forceTeamVoteEligible = forceTeamVoteEligible;
		}

		@Override
		public Boolean isCapital() {
			return capital;
		}

		@Override
		public void setCapital(Boolean capital) {
			this.capital = capital;
		}

		@Override
		public Boolean isGovernmentCenter() {
			return governmentCenter;
		}

		@Override
		public void setGovernmentCenter(Boolean governmentCenter) {
			this.governmentCenter = governmentCenter;
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
		public Boolean isAllowsNukes() {
			return allowsNukes;
		}

		@Override
		public void setAllowsNukes(Boolean allowsNukes) {
			this.allowsNukes = allowsNukes;
		}

		@Override
		public Boolean isMapCentering() {
			return mapCentering;
		}

		@Override
		public void setMapCentering(Boolean mapCentering) {
			this.mapCentering = mapCentering;
		}

		@Override
		public Boolean isNoUnhappiness() {
			return noUnhappiness;
		}

		@Override
		public void setNoUnhappiness(Boolean noUnhappiness) {
			this.noUnhappiness = noUnhappiness;
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
		public Boolean isBuildingOnlyHealthy() {
			return buildingOnlyHealthy;
		}

		@Override
		public void setBuildingOnlyHealthy(Boolean buildingOnlyHealthy) {
			this.buildingOnlyHealthy = buildingOnlyHealthy;
		}

		@Override
		public Boolean isNeverCapture() {
			return neverCapture;
		}

		@Override
		public void setNeverCapture(Boolean neverCapture) {
			this.neverCapture = neverCapture;
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
		public Boolean isPrereqReligionBool() {
			return prereqReligionBool;
		}

		@Override
		public void setPrereqReligionBool(Boolean prereqReligionBool) {
			this.prereqReligionBool = prereqReligionBool;
		}

		@Override
		public Boolean isCenterInCity() {
			return centerInCity;
		}

		@Override
		public void setCenterInCity(Boolean centerInCity) {
			this.centerInCity = centerInCity;
		}

		@Override
		public Boolean isSlaveMarket() {
			return slaveMarket;
		}

		@Override
		public void setSlaveMarket(Boolean slaveMarket) {
			this.slaveMarket = slaveMarket;
		}

		@Override
		public Boolean isApplyAllFreePromotionsOnMove() {
			return applyAllFreePromotionsOnMove;
		}

		@Override
		public void setApplyAllFreePromotionsOnMove(Boolean applyAllFreePromotionsOnMove) {
			this.applyAllFreePromotionsOnMove = applyAllFreePromotionsOnMove;
		}

		@Override
		public Boolean isApplyFreePromotionOnMove() {
			return applyFreePromotionOnMove;
		}

		@Override
		public void setApplyFreePromotionOnMove(Boolean applyFreePromotionOnMove) {
			this.applyFreePromotionOnMove = applyFreePromotionOnMove;
		}

		@Override
		public Boolean isUnitCityDeathCulture() {
			return unitCityDeathCulture;
		}

		@Override
		public void setUnitCityDeathCulture(Boolean unitCityDeathCulture) {
			this.unitCityDeathCulture = unitCityDeathCulture;
		}

		@Override
		public Boolean isUnitAllCityDeathCulture() {
			return unitAllCityDeathCulture;
		}

		@Override
		public void setUnitAllCityDeathCulture(Boolean unitAllCityDeathCulture) {
			this.unitAllCityDeathCulture = unitAllCityDeathCulture;
		}

		@Override
		public Boolean isForceDisableStarSigns() {
			return forceDisableStarSigns;
		}

		@Override
		public void setForceDisableStarSigns(Boolean forceDisableStarSigns) {
			this.forceDisableStarSigns = forceDisableStarSigns;
		}

		@Override
		public Boolean isStarSignGoodOnly() {
			return starSignGoodOnly;
		}

		@Override
		public void setStarSignGoodOnly(Boolean starSignGoodOnly) {
			this.starSignGoodOnly = starSignGoodOnly;
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
		public Integer getGlobalStarSignMitigateChangePercent() {
			return globalStarSignMitigateChangePercent;
		}

		@Override
		public void setGlobalStarSignMitigateChangePercent(Integer globalStarSignMitigateChangePercent) {
			this.globalStarSignMitigateChangePercent = globalStarSignMitigateChangePercent;
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
		public Integer getGlobalStarSignScaleChangePercent() {
			return globalStarSignScaleChangePercent;
		}

		@Override
		public void setGlobalStarSignScaleChangePercent(Integer globalStarSignScaleChangePercent) {
			this.globalStarSignScaleChangePercent = globalStarSignScaleChangePercent;
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
		public Integer getExtraBarbarianCostChange() {
			return extraBarbarianCostChange;
		}

		@Override
		public void setExtraBarbarianCostChange(Integer extraBarbarianCostChange) {
			this.extraBarbarianCostChange = extraBarbarianCostChange;
		}

		@Override
		public Integer getBarbarianConversionCostModifier() {
			return barbarianConversionCostModifier;
		}

		@Override
		public void setBarbarianConversionCostModifier(Integer barbarianConversionCostModifier) {
			this.barbarianConversionCostModifier = barbarianConversionCostModifier;
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
		public Integer getConquestProb() {
			return conquestProb;
		}

		@Override
		public void setConquestProb(Integer conquestProb) {
			this.conquestProb = conquestProb;
		}

		@Override
		public Integer getCitiesPrereq() {
			return citiesPrereq;
		}

		@Override
		public void setCitiesPrereq(Integer citiesPrereq) {
			this.citiesPrereq = citiesPrereq;
		}

		@Override
		public Integer getTeamsPrereq() {
			return teamsPrereq;
		}

		@Override
		public void setTeamsPrereq(Integer teamsPrereq) {
			this.teamsPrereq = teamsPrereq;
		}

		@Override
		public Integer getLevelPrereq() {
			return levelPrereq;
		}

		@Override
		public void setLevelPrereq(Integer levelPrereq) {
			this.levelPrereq = levelPrereq;
		}

		@Override
		public Integer getMinLatitude() {
			return minLatitude;
		}

		@Override
		public void setMinLatitude(Integer minLatitude) {
			this.minLatitude = minLatitude;
		}

		@Override
		public Integer getMaxLatitude() {
			return maxLatitude;
		}

		@Override
		public void setMaxLatitude(Integer maxLatitude) {
			this.maxLatitude = maxLatitude;
		}

		@Override
		public Integer getWorkableRadius() {
			return workableRadius;
		}

		@Override
		public void setWorkableRadius(Integer workableRadius) {
			this.workableRadius = workableRadius;
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
		public Integer getGlobalGreatPeopleRateModifier() {
			return globalGreatPeopleRateModifier;
		}

		@Override
		public void setGlobalGreatPeopleRateModifier(Integer globalGreatPeopleRateModifier) {
			this.globalGreatPeopleRateModifier = globalGreatPeopleRateModifier;
		}

		@Override
		public Integer getAnarchyModifier() {
			return anarchyModifier;
		}

		@Override
		public void setAnarchyModifier(Integer anarchyModifier) {
			this.anarchyModifier = anarchyModifier;
		}

		@Override
		public Integer getGoldenAgeModifier() {
			return goldenAgeModifier;
		}

		@Override
		public void setGoldenAgeModifier(Integer goldenAgeModifier) {
			this.goldenAgeModifier = goldenAgeModifier;
		}

		@Override
		public Integer getGlobalHurryModifier() {
			return globalHurryModifier;
		}

		@Override
		public void setGlobalHurryModifier(Integer globalHurryModifier) {
			this.globalHurryModifier = globalHurryModifier;
		}

		@Override
		public Integer getExperience() {
			return experience;
		}

		@Override
		public void setExperience(Integer experience) {
			this.experience = experience;
		}

		@Override
		public Integer getGlobalExperience() {
			return globalExperience;
		}

		@Override
		public void setGlobalExperience(Integer globalExperience) {
			this.globalExperience = globalExperience;
		}

		@Override
		public Integer getFoodKept() {
			return foodKept;
		}

		@Override
		public void setFoodKept(Integer foodKept) {
			this.foodKept = foodKept;
		}

		@Override
		public Integer getAirlift() {
			return airlift;
		}

		@Override
		public void setAirlift(Integer airlift) {
			this.airlift = airlift;
		}

		@Override
		public Integer getAirModifier() {
			return airModifier;
		}

		@Override
		public void setAirModifier(Integer airModifier) {
			this.airModifier = airModifier;
		}

		@Override
		public Integer getAirUnitCapacity() {
			return airUnitCapacity;
		}

		@Override
		public void setAirUnitCapacity(Integer airUnitCapacity) {
			this.airUnitCapacity = airUnitCapacity;
		}

		@Override
		public Integer getNukeModifier() {
			return nukeModifier;
		}

		@Override
		public void setNukeModifier(Integer nukeModifier) {
			this.nukeModifier = nukeModifier;
		}

		@Override
		public Integer getNukeExplosionRand() {
			return nukeExplosionRand;
		}

		@Override
		public void setNukeExplosionRand(Integer nukeExplosionRand) {
			this.nukeExplosionRand = nukeExplosionRand;
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
		public Integer getAreaFreeSpecialist() {
			return areaFreeSpecialist;
		}

		@Override
		public void setAreaFreeSpecialist(Integer areaFreeSpecialist) {
			this.areaFreeSpecialist = areaFreeSpecialist;
		}

		@Override
		public Integer getGlobalFreeSpecialist() {
			return globalFreeSpecialist;
		}

		@Override
		public void setGlobalFreeSpecialist(Integer globalFreeSpecialist) {
			this.globalFreeSpecialist = globalFreeSpecialist;
		}

		@Override
		public Integer getMaintenanceModifier() {
			return maintenanceModifier;
		}

		@Override
		public void setMaintenanceModifier(Integer maintenanceModifier) {
			this.maintenanceModifier = maintenanceModifier;
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
		public Integer getGlobalWarWearinessModifier() {
			return globalWarWearinessModifier;
		}

		@Override
		public void setGlobalWarWearinessModifier(Integer globalWarWearinessModifier) {
			this.globalWarWearinessModifier = globalWarWearinessModifier;
		}

		@Override
		public Integer getEnemyWarWearinessModifier() {
			return enemyWarWearinessModifier;
		}

		@Override
		public void setEnemyWarWearinessModifier(Integer enemyWarWearinessModifier) {
			this.enemyWarWearinessModifier = enemyWarWearinessModifier;
		}

		@Override
		public Integer getHealRateChange() {
			return healRateChange;
		}

		@Override
		public void setHealRateChange(Integer healRateChange) {
			this.healRateChange = healRateChange;
		}

		@Override
		public Integer getHealth() {
			return health;
		}

		@Override
		public void setHealth(Integer health) {
			this.health = health;
		}

		@Override
		public Integer getAreaHealth() {
			return areaHealth;
		}

		@Override
		public void setAreaHealth(Integer areaHealth) {
			this.areaHealth = areaHealth;
		}

		@Override
		public Integer getGlobalHealth() {
			return globalHealth;
		}

		@Override
		public void setGlobalHealth(Integer globalHealth) {
			this.globalHealth = globalHealth;
		}

		@Override
		public Integer getHappiness() {
			return happiness;
		}

		@Override
		public void setHappiness(Integer happiness) {
			this.happiness = happiness;
		}

		@Override
		public Integer getAreaHappiness() {
			return areaHappiness;
		}

		@Override
		public void setAreaHappiness(Integer areaHappiness) {
			this.areaHappiness = areaHappiness;
		}

		@Override
		public Integer getGlobalHappiness() {
			return globalHappiness;
		}

		@Override
		public void setGlobalHappiness(Integer globalHappiness) {
			this.globalHappiness = globalHappiness;
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
		public Integer getWorkerSpeedModifier() {
			return workerSpeedModifier;
		}

		@Override
		public void setWorkerSpeedModifier(Integer workerSpeedModifier) {
			this.workerSpeedModifier = workerSpeedModifier;
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
		public Integer getSpaceProductionModifier() {
			return spaceProductionModifier;
		}

		@Override
		public void setSpaceProductionModifier(Integer spaceProductionModifier) {
			this.spaceProductionModifier = spaceProductionModifier;
		}

		@Override
		public Integer getGlobalSpaceProductionModifier() {
			return globalSpaceProductionModifier;
		}

		@Override
		public void setGlobalSpaceProductionModifier(Integer globalSpaceProductionModifier) {
			this.globalSpaceProductionModifier = globalSpaceProductionModifier;
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
		public Integer getCoastalTradeRoutes() {
			return coastalTradeRoutes;
		}

		@Override
		public void setCoastalTradeRoutes(Integer coastalTradeRoutes) {
			this.coastalTradeRoutes = coastalTradeRoutes;
		}

		@Override
		public Integer getGlobalTradeRoutes() {
			return globalTradeRoutes;
		}

		@Override
		public void setGlobalTradeRoutes(Integer globalTradeRoutes) {
			this.globalTradeRoutes = globalTradeRoutes;
		}

		@Override
		public Integer getTradeRouteModifier() {
			return tradeRouteModifier;
		}

		@Override
		public void setTradeRouteModifier(Integer tradeRouteModifier) {
			this.tradeRouteModifier = tradeRouteModifier;
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
		public Integer getGlobalPopulationChange() {
			return globalPopulationChange;
		}

		@Override
		public void setGlobalPopulationChange(Integer globalPopulationChange) {
			this.globalPopulationChange = globalPopulationChange;
		}

		@Override
		public Integer getGlobalFoundPopulationChange() {
			return globalFoundPopulationChange;
		}

		@Override
		public void setGlobalFoundPopulationChange(Integer globalFoundPopulationChange) {
			this.globalFoundPopulationChange = globalFoundPopulationChange;
		}

		@Override
		public Integer getFreeTechs() {
			return freeTechs;
		}

		@Override
		public void setFreeTechs(Integer freeTechs) {
			this.freeTechs = freeTechs;
		}

		@Override
		public Integer getDefense() {
			return defense;
		}

		@Override
		public void setDefense(Integer defense) {
			this.defense = defense;
		}

		@Override
		public Integer getObsoleteDefence() {
			return obsoleteDefence;
		}

		@Override
		public void setObsoleteDefence(Integer obsoleteDefence) {
			this.obsoleteDefence = obsoleteDefence;
		}

		@Override
		public Integer getBombardDefense() {
			return bombardDefense;
		}

		@Override
		public void setBombardDefense(Integer bombardDefense) {
			this.bombardDefense = bombardDefense;
		}

		@Override
		public Integer getAllCityDefense() {
			return allCityDefense;
		}

		@Override
		public void setAllCityDefense(Integer allCityDefense) {
			this.allCityDefense = allCityDefense;
		}

		@Override
		public Integer getEspionageDefense() {
			return espionageDefense;
		}

		@Override
		public void setEspionageDefense(Integer espionageDefense) {
			this.espionageDefense = espionageDefense;
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
		public Integer getGlobalWonderProductionModifier() {
			return globalWonderProductionModifier;
		}

		@Override
		public void setGlobalWonderProductionModifier(Integer globalWonderProductionModifier) {
			this.globalWonderProductionModifier = globalWonderProductionModifier;
		}

		@Override
		public String getfVisibilityPriority() {
			return fVisibilityPriority;
		}

		@Override
		public void setfVisibilityPriority(String fVisibilityPriority) {
			this.fVisibilityPriority = fVisibilityPriority;
		}

		@Override
		public List<Integer> getSeaPlotYieldChanges() {
			return seaPlotYieldChanges;
		}

		@Override
		public void addSeaPlotYieldChange(Integer seaPlotYieldChange) {
			this.seaPlotYieldChanges.add(seaPlotYieldChange);
		}

		@Override
		public List<Integer> getRiverPlotYieldChanges() {
			return riverPlotYieldChanges;
		}

		@Override
		public void addRiverPlotYieldChange(Integer riverPlotYieldChange) {
			this.riverPlotYieldChanges.add(riverPlotYieldChange);
		}

		@Override
		public List<Integer> getGlobalSeaPlotYieldChanges() {
			return globalSeaPlotYieldChanges;
		}

		@Override
		public void addGlobalSeaPlotYieldChange(Integer globalSeaPlotYieldChange) {
			this.globalSeaPlotYieldChanges.add(globalSeaPlotYieldChange);
		}

		@Override
		public List<Integer> getYieldChanges() {
			return yieldChanges;
		}

		@Override
		public void addYieldChange(Integer yieldChange) {
			this.yieldChanges.add(yieldChange);
		}

		@Override
		public List<Integer> getGlobalYieldChanges() {
			return globalYieldChanges;
		}

		@Override
		public void addGlobalYieldChange(Integer globalYieldChange) {
			this.globalYieldChanges.add(globalYieldChange);
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
		public List<Integer> getPowerYieldModifiers() {
			return powerYieldModifiers;
		}

		@Override
		public void addPowerYieldModifier(Integer powerYieldModifier) {
			this.powerYieldModifiers.add(powerYieldModifier);
		}

		@Override
		public List<Integer> getAreaYieldModifiers() {
			return areaYieldModifiers;
		}

		@Override
		public void addAreaYieldModifier(Integer areaYieldModifier) {
			this.areaYieldModifiers.add(areaYieldModifier);
		}

		@Override
		public List<Integer> getGlobalYieldModifiers() {
			return globalYieldModifiers;
		}

		@Override
		public void addGlobalYieldModifier(Integer globalYieldModifier) {
			this.globalYieldModifiers.add(globalYieldModifier);
		}

		@Override
		public List<Integer> getCommerceChanges() {
			return commerceChanges;
		}

		@Override
		public void addCommerceChange(Integer commerceChange) {
			this.commerceChanges.add(commerceChange);
		}

		@Override
		public List<Integer> getObsoleteSafeCommerceChanges() {
			return obsoleteSafeCommerceChanges;
		}

		@Override
		public void addObsoleteSafeCommerceChange(Integer obsoleteSafeCommerceChange) {
			this.obsoleteSafeCommerceChanges.add(obsoleteSafeCommerceChange);
		}

		@Override
		public List<Integer> getCommerceChangeDoubleTimes() {
			return commerceChangeDoubleTimes;
		}

		@Override
		public void addCommerceChangeDoubleTime(Integer commerceChangeDoubleTime) {
			this.commerceChangeDoubleTimes.add(commerceChangeDoubleTime);
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
		public List<Integer> getGlobalCommerceModifiers() {
			return globalCommerceModifiers;
		}

		@Override
		public void addGlobalCommerceModifier(Integer globalCommerceModifier) {
			this.globalCommerceModifiers.add(globalCommerceModifier);
		}

		@Override
		public List<Integer> getSpecialistExtraCommerces() {
			return specialistExtraCommerces;
		}

		@Override
		public void addSpecialistExtraCommerce(Integer specialistExtraCommerce) {
			this.specialistExtraCommerces.add(specialistExtraCommerce);
		}

		@Override
		public List<Integer> getStateReligionCommerces() {
			return stateReligionCommerces;
		}

		@Override
		public void addStateReligionCommerce(Integer stateReligionCommerce) {
			this.stateReligionCommerces.add(stateReligionCommerce);
		}

		@Override
		public List<Integer> getCommerceHappinesses() {
			return commerceHappinesses;
		}

		@Override
		public void addCommerceHappinesse(Integer commerceHappinesse) {
			this.commerceHappinesses.add(commerceHappinesse);
		}

		@Override
		public List<IPair<String, Integer>> getReligionChanges() {
			return religionChanges;
		}

		@Override
		public void addReligionChange(IPair<String, Integer> religionChange) {
			this.religionChanges.add(religionChange);
		}

		@Override
		public List<IPair<String, Integer>> getSpecialistCounts() {
			return specialistCounts;
		}

		@Override
		public void addSpecialistCount(IPair<String, Integer> specialistCount) {
			this.specialistCounts.add(specialistCount);
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
		public List<Boolean> getCommerceFlexibles() {
			return commerceFlexibles;
		}

		@Override
		public void addCommerceFlexible(Boolean commerceFlexible) {
			this.commerceFlexibles.add(commerceFlexible);
		}

		@Override
		public List<Boolean> getCommerceChangeOriginalOwners() {
			return commerceChangeOriginalOwners;
		}

		@Override
		public void addCommerceChangeOriginalOwner(Boolean commerceChangeOriginalOwner) {
			this.commerceChangeOriginalOwners.add(commerceChangeOriginalOwner);
		}

		@Override
		public String getConstructSound() {
			return constructSound;
		}

		@Override
		public void setConstructSound(String constructSound) {
			this.constructSound = constructSound;
		}

		@Override
		public List<IPair<String, Integer>> getBonusHealthChanges() {
			return bonusHealthChanges;
		}

		@Override
		public void addBonusHealthChange(IPair<String, Integer> bonusHealthChange) {
			this.bonusHealthChanges.add(bonusHealthChange);
		}

		@Override
		public List<IPair<String, Integer>> getBonusHappinessChanges() {
			return bonusHappinessChanges;
		}

		@Override
		public void addBonusHappinessChange(IPair<String, Integer> bonusHappinessChange) {
			this.bonusHappinessChanges.add(bonusHappinessChange);
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
		public List<IPair<String, Integer>> getUnitCombatFreeExperiences() {
			return unitCombatFreeExperiences;
		}

		@Override
		public void addUnitCombatFreeExperience(IPair<String, Integer> unitCombatFreeExperience) {
			this.unitCombatFreeExperiences.add(unitCombatFreeExperience);
		}

		@Override
		public List<IPair<String, Integer>> getDomainFreeExperiences() {
			return domainFreeExperiences;
		}

		@Override
		public void addDomainFreeExperience(IPair<String, Integer> domainFreeExperience) {
			this.domainFreeExperiences.add(domainFreeExperience);
		}

		@Override
		public List<IPair<String, Integer>> getDomainProductionModifiers() {
			return domainProductionModifiers;
		}

		@Override
		public void addDomainProductionModifier(IPair<String, Integer> domainProductionModifier) {
			this.domainProductionModifiers.add(domainProductionModifier);
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
		public List<IPair<String, Integer>> getUnitCombatProductionModifiers() {
			return unitCombatProductionModifiers;
		}

		@Override
		public void addUnitCombatProductionModifier(IPair<String, Integer> unitCombatProductionModifier) {
			this.unitCombatProductionModifiers.add(unitCombatProductionModifier);
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
		public List<IPair<String, Integer>> getPrereqNumOfBuildingClasses() {
			return prereqNumOfBuildingClasses;
		}

		@Override
		public void addPrereqNumOfBuildingClass(IPair<String, Integer> prereqNumOfBuildingClass) {
			this.prereqNumOfBuildingClasses.add(prereqNumOfBuildingClass);
		}

		@Override
		public List<String> getPrereqAndBuildingClasses() {
			return prereqAndBuildingClasses;
		}

		@Override
		public void addPrereqAndBuildingClass(String prereqAndBuildingClass) {
			this.prereqAndBuildingClasses.add(prereqAndBuildingClass);
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
		public List<String> getReplacedByBuildingClasses() {
			return replacedByBuildingClasses;
		}

		@Override
		public void addReplacedByBuildingClass(String replacedByBuildingClass) {
			this.replacedByBuildingClasses.add(replacedByBuildingClass);
		}

		@Override
		public SpecialistYieldChanges getSpecialistYieldChanges() {
			return specialistYieldChanges;
		}

		@Override
		public void setSpecialistYieldChanges(SpecialistYieldChanges specialistYieldChanges) {
			this.specialistYieldChanges = specialistYieldChanges;
		}

		@Override
		public BonusYieldModifiers getBonusYieldModifiers() {
			return bonusYieldModifiers;
		}

		@Override
		public void setBonusYieldModifiers(BonusYieldModifiers bonusYieldModifiers) {
			this.bonusYieldModifiers = bonusYieldModifiers;
		}

		@Override
		public BonusYieldChanges getBonusYieldChanges() {
			return bonusYieldChanges;
		}

		@Override
		public void setBonusYieldChanges(BonusYieldChanges bonusYieldChanges) {
			this.bonusYieldChanges = bonusYieldChanges;
		}

		@Override
		public VicinityBonusYieldChanges getVicinityBonusYieldChanges() {
			return vicinityBonusYieldChanges;
		}

		@Override
		public void setVicinityBonusYieldChanges(VicinityBonusYieldChanges vicinityBonusYieldChanges) {
			this.vicinityBonusYieldChanges = vicinityBonusYieldChanges;
		}

		@Override
		public TechYieldChanges getTechYieldChanges() {
			return techYieldChanges;
		}

		@Override
		public void setTechYieldChanges(TechYieldChanges techYieldChanges) {
			this.techYieldChanges = techYieldChanges;
		}

		@Override
		public TechCommerceChanges getTechCommerceChanges() {
			return techCommerceChanges;
		}

		@Override
		public void setTechCommerceChanges(TechCommerceChanges techCommerceChanges) {
			this.techCommerceChanges = techCommerceChanges;
		}

		@Override
		public List<IPair<String, Integer>> getImprovementFreeSpecialists() {
			return improvementFreeSpecialists;
		}

		@Override
		public void addImprovementFreeSpecialist(IPair<String, Integer> improvementFreeSpecialist) {
			this.improvementFreeSpecialists.add(improvementFreeSpecialist);
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
		public Integer getOrderPriority() {
			return orderPriority;
		}

		@Override
		public void setOrderPriority(Integer orderPriority) {
			this.orderPriority = orderPriority;
		}

		@Override
		public Boolean isGraphicalOnly() {
			return graphicalOnly;
		}

		@Override
		public void setGraphicalOnly(Boolean graphicalOnly) {
			this.graphicalOnly = graphicalOnly;
		}
	}
}