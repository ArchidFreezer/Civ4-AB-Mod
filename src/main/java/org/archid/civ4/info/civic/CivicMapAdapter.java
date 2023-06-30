package org.archid.civ4.info.civic;

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

public class CivicMapAdapter extends XmlAdapter<CivicMapAdapter.CivicMap, Map<String, ICivicInfo>> {

	public static class CivicMap {
		@XmlElement(name = "CivicInfo")
		List<AdaptedCivic> entries = new ArrayList<AdaptedCivic>();
	}

	private static class AdaptedCivic {
		@XmlElement(name="CivicOptionType")
		private String civicOptionType;
		@XmlElement(name="Type")
		private String type;
		@XmlElement(name="Description")
		private String description;
		@XmlElement(name="Civilopedia")
		private String civilopedia;
		@XmlElement(name="Strategy")
		private String strategy;
		@XmlElement(name="Help")
		private String help;
		@XmlElement(name="Button")
		private String button;
		@XmlElement(name="iIndex")
		private Integer index;
		@XmlElement(name="TechPrereq")
		private String techPrereq;
		@XmlElement(name="bCreateBarbarians")
		private Integer createBarbarians;
		@XmlElement(name="bEnableStarSigns")
		private Integer enableStarSigns;
		@XmlElement(name="iStarSignMitigateChangePercent")
		private Integer starSignMitigateChangePercent;
		@XmlElement(name="iStarSignScaleChangePercent")
		private Integer starSignScaleChangePercent;
		@XmlElement(name="iAnarchyLength")
		private Integer anarchyLength;
		@XmlElement(name="Upkeep")
		private String upkeep;
		@XmlElement(name="iAIWeight")
		private Integer aIWeight;
		@XmlElement(name="iGreatPeopleRateModifier")
		private Integer greatPeopleRateModifier;
		@XmlElement(name="iGreatGeneralRateModifier")
		private Integer greatGeneralRateModifier;
		@XmlElement(name="iDomesticGreatGeneralRateModifier")
		private Integer domesticGreatGeneralRateModifier;
		@XmlElement(name="iStateReligionGreatPeopleRateModifier")
		private Integer stateReligionGreatPeopleRateModifier;
		@XmlElement(name="iDistanceMaintenanceModifier")
		private Integer distanceMaintenanceModifier;
		@XmlElement(name="iNumCitiesMaintenanceModifier")
		private Integer numCitiesMaintenanceModifier;
		@XmlElement(name="iCorporationMaintenanceModifier")
		private Integer corporationMaintenanceModifier;
		@XmlElement(name="iForeignTradeRouteModifier")
		private Integer foreignTradeRouteModifier;
		@XmlElement(name="iExtraHealth")
		private Integer extraHealth;
		@XmlElement(name="iExtraHappiness")
		private Integer extraHappiness;
		@XmlElement(name="iFreeExperience")
		private Integer freeExperience;
		@XmlElement(name="iWorkerSpeedModifier")
		private Integer workerSpeedModifier;
		@XmlElement(name="iImprovementUpgradeRateModifier")
		private Integer improvementUpgradeRateModifier;
		@XmlElement(name="iMilitaryProductionModifier")
		private Integer militaryProductionModifier;
		@XmlElement(name="iBaseFreeUnits")
		private Integer baseFreeUnits;
		@XmlElement(name="iBaseFreeMilitaryUnits")
		private Integer baseFreeMilitaryUnits;
		@XmlElement(name="iFreeUnitsPopulationPercent")
		private Integer freeUnitsPopulationPercent;
		@XmlElement(name="iFreeMilitaryUnitsPopulationPercent")
		private Integer freeMilitaryUnitsPopulationPercent;
		@XmlElement(name="iGoldPerUnit")
		private Integer goldPerUnit;
		@XmlElement(name="iGoldPerMilitaryUnit")
		private Integer goldPerMilitaryUnit;
		@XmlElement(name="iHappyPerMilitaryUnit")
		private Integer happyPerMilitaryUnit;
		@XmlElement(name="bMilitaryFoodProduction")
		private Integer militaryFoodProduction;
		@XmlElement(name="bTribalConscription")
		private Integer tribalConscription;
		@XmlElement(name="iMaxConscript")
		private Integer maxConscript;
		@XmlElement(name="iUnhealthyPopulationModifier")
		private Integer unhealthyPopulationModifier;
		@XmlElement(name="iExpInBorderModifier")
		private Integer expInBorderModifier;
		@XmlElement(name="bBuildingOnlyHealthy")
		private Integer buildingOnlyHealthy;
		@XmlElement(name="iLargestCityHappiness")
		private Integer largestCityHappiness;
		@XmlElement(name="bNoCapitalUnhappiness")
		private Integer noCapitalUnhappiness;
		@XmlElement(name="iTaxRateAngerModifier")
		private Integer taxRateAngerModifier;
		@XmlElement(name="iDistantUnitSupplyCostModifier")
		private Integer distantUnitSupplyCostModifier;
		@XmlElement(name="iWarWearinessModifier")
		private Integer warWearinessModifier;
		@XmlElement(name="iFreeSpecialist")
		private Integer freeSpecialist;
		@XmlElement(name="iTradeRoutes")
		private Integer tradeRoutes;
		@XmlElement(name="bNoForeignTrade")
		private Integer noForeignTrade;
		@XmlElement(name="bNoCorporations")
		private Integer noCorporations;
		@XmlElement(name="bNoForeignCorporations")
		private Integer noForeignCorporations;
		@XmlElement(name="iCivicPercentAnger")
		private Integer civicPercentAnger;
		@XmlElement(name="bStateReligion")
		private Integer stateReligion;
		@XmlElement(name="bNoNonStateReligionSpread")
		private Integer noNonStateReligionSpread;
		@XmlElement(name="iStateReligionHappiness")
		private Integer stateReligionHappiness;
		@XmlElement(name="iNonStateReligionHappiness")
		private Integer nonStateReligionHappiness;
		@XmlElement(name="iStateReligionUnitProductionModifier")
		private Integer stateReligionUnitProductionModifier;
		@XmlElement(name="iStateReligionBuildingProductionModifier")
		private Integer stateReligionBuildingProductionModifier;
		@XmlElement(name="iStateReligionFreeExperience")
		private Integer stateReligionFreeExperience;
		@XmlElement(name="bUnitRangeUnbound")
		private Integer unitRangeUnbound;
		@XmlElement(name="bUnitTerritoryUnbound")
		private Integer unitTerritoryUnbound;
		@XmlElement(name="iUnitRangeChange")
		private Integer unitRangeChange;
		@XmlElement(name="iUnitRangeModifier")
		private Integer unitRangeModifier;
		@XmlElement(name="iCultureDefenceChange")
		private Integer cultureDefenceChange;
		@XmlElement(name="iPopulationGrowthRateModifier")
		private Integer populationGrowthRateModifier;
		@XmlElementWrapper(name="YieldModifiers")
		@XmlElement(name="iYield")
		private List<Integer> yieldModifiers;
		@XmlElementWrapper(name="CapitalYieldModifiers")
		@XmlElement(name="iYield")
		private List<Integer> capitalYieldModifiers;
		@XmlElementWrapper(name="TradeYieldModifiers")
		@XmlElement(name="iYield")
		private List<Integer> tradeYieldModifiers;
		@XmlElementWrapper(name="CommerceModifiers")
		@XmlElement(name="iCommerce")
		private List<Integer> commerceModifiers;
		@XmlElementWrapper(name="CapitalCommerceModifiers")
		@XmlElement(name="iCommerce")
		private List<Integer> capitalCommerceModifiers;
		@XmlElementWrapper(name="SpecialistCommerceChanges")
		@XmlElement(name="iCommerce")
		private List<Integer> specialistCommerceChanges;
		@XmlElementWrapper(name="Hurrys")
		@XmlElement(name="HurryType")
		private List<String> hurrys;
		@XmlElementWrapper(name="SpecialBuildingNotRequireds")
		@XmlElement(name="SpecialBuildingType")
		private List<String> specialBuildingNotRequireds;
		@XmlElementWrapper(name="SpecialistValids")
		@XmlElement(name="SpecialistType")
		private List<String> specialistValids;
		@XmlElementWrapper(name="FreeSpecialistCounts")
		@XmlElement(name="FreeSpecialistCount")
		private List<AdaptedFreeSpecialistCounts> freeSpecialistCounts;
		@XmlElementWrapper(name="BuildingClassProductionModifiers")
		@XmlElement(name="BuildingClassProductionModifier")
		private List<AdaptedBuildingClassProductionModifiers> buildingClassProductionModifiers;
		@XmlElementWrapper(name="BuildingHappinessChanges")
		@XmlElement(name="BuildingHappinessChange")
		private List<AdaptedBuildingHappinessChanges> buildingHappinessChanges;
		@XmlElementWrapper(name="BuildingHealthChanges")
		@XmlElement(name="BuildingHealthChange")
		private List<AdaptedBuildingHealthChanges> buildingHealthChanges;
		@XmlElementWrapper(name="FeatureHappinessChanges")
		@XmlElement(name="FeatureHappinessChange")
		private List<AdaptedFeatureHappinessChanges> featureHappinessChanges;
		@XmlElement(name="ImprovementYieldChanges")
		private ImprovementYieldChanges improvementYieldChanges;
		@XmlElement(name="bUpgradeAnywhere")
		private Integer upgradeAnywhere;
		@XmlElement(name="WeLoveTheKing")
		private String weLoveTheKing;
		@XmlElement(name="iCityDefenceModifier")
		private Integer cityDefenceModifier;
	}

	private static class AdaptedFreeSpecialistCounts {
		@XmlElement(name="SpecialistType")
		private String specialistType;
		@XmlElement(name="iCount")
		private Integer iCount;
	}

	private static class AdaptedBuildingClassProductionModifiers {
		@XmlElement(name="BuildingClassType")
		private String buildingClassType;
		@XmlElement(name="iModifier")
		private Integer iModifier;
	}

	private static class AdaptedBuildingHappinessChanges {
		@XmlElement(name="BuildingType")
		private String buildingType;
		@XmlElement(name="iHappinessChange")
		private Integer iHappinessChange;
	}

	private static class AdaptedBuildingHealthChanges {
		@XmlElement(name="BuildingType")
		private String buildingType;
		@XmlElement(name="iHealthChange")
		private Integer iHealthChange;
	}

	private static class AdaptedFeatureHappinessChanges {
		@XmlElement(name="FeatureType")
		private String featureType;
		@XmlElement(name="iHappinessChange")
		private Integer iHappinessChange;
	}

	@Override
	public Map<String, ICivicInfo> unmarshal(CivicMap v) throws Exception {
		Map<String, ICivicInfo> map = new TreeMap<String, ICivicInfo>();
		for (AdaptedCivic aInfo: v.entries) {
			ICivicInfo info = CivicInfos.createInfo(aInfo.type);
			info.setCivicOptionType(JaxbUtils.unmarshallString(aInfo.civicOptionType));
			info.setDescription(JaxbUtils.unmarshallString(aInfo.description));
			info.setCivilopedia(JaxbUtils.unmarshallString(aInfo.civilopedia));
			info.setStrategy(JaxbUtils.unmarshallString(aInfo.strategy));
			info.setHelp(JaxbUtils.unmarshallString(aInfo.help));
			info.setButton(JaxbUtils.unmarshallString(aInfo.button));
			info.setIndex(JaxbUtils.unmarshallInteger(aInfo.index));
			info.setTechPrereq(JaxbUtils.unmarshallString(aInfo.techPrereq));
			info.setCreateBarbarians(JaxbUtils.unmarshallBoolean(aInfo.createBarbarians));
			info.setEnableStarSigns(JaxbUtils.unmarshallBoolean(aInfo.enableStarSigns));
			info.setStarSignMitigateChangePercent(JaxbUtils.unmarshallInteger(aInfo.starSignMitigateChangePercent));
			info.setStarSignScaleChangePercent(JaxbUtils.unmarshallInteger(aInfo.starSignScaleChangePercent));
			info.setAnarchyLength(JaxbUtils.unmarshallInteger(aInfo.anarchyLength));
			info.setUpkeep(JaxbUtils.unmarshallString(aInfo.upkeep));
			info.setAIWeight(JaxbUtils.unmarshallInteger(aInfo.aIWeight));
			info.setGreatPeopleRateModifier(JaxbUtils.unmarshallInteger(aInfo.greatPeopleRateModifier));
			info.setGreatGeneralRateModifier(JaxbUtils.unmarshallInteger(aInfo.greatGeneralRateModifier));
			info.setDomesticGreatGeneralRateModifier(JaxbUtils.unmarshallInteger(aInfo.domesticGreatGeneralRateModifier));
			info.setStateReligionGreatPeopleRateModifier(JaxbUtils.unmarshallInteger(aInfo.stateReligionGreatPeopleRateModifier));
			info.setDistanceMaintenanceModifier(JaxbUtils.unmarshallInteger(aInfo.distanceMaintenanceModifier));
			info.setNumCitiesMaintenanceModifier(JaxbUtils.unmarshallInteger(aInfo.numCitiesMaintenanceModifier));
			info.setCorporationMaintenanceModifier(JaxbUtils.unmarshallInteger(aInfo.corporationMaintenanceModifier));
			info.setForeignTradeRouteModifier(JaxbUtils.unmarshallInteger(aInfo.foreignTradeRouteModifier));
			info.setExtraHealth(JaxbUtils.unmarshallInteger(aInfo.extraHealth));
			info.setExtraHappiness(JaxbUtils.unmarshallInteger(aInfo.extraHappiness));
			info.setFreeExperience(JaxbUtils.unmarshallInteger(aInfo.freeExperience));
			info.setWorkerSpeedModifier(JaxbUtils.unmarshallInteger(aInfo.workerSpeedModifier));
			info.setImprovementUpgradeRateModifier(JaxbUtils.unmarshallInteger(aInfo.improvementUpgradeRateModifier));
			info.setMilitaryProductionModifier(JaxbUtils.unmarshallInteger(aInfo.militaryProductionModifier));
			info.setBaseFreeUnits(JaxbUtils.unmarshallInteger(aInfo.baseFreeUnits));
			info.setBaseFreeMilitaryUnits(JaxbUtils.unmarshallInteger(aInfo.baseFreeMilitaryUnits));
			info.setFreeUnitsPopulationPercent(JaxbUtils.unmarshallInteger(aInfo.freeUnitsPopulationPercent));
			info.setFreeMilitaryUnitsPopulationPercent(JaxbUtils.unmarshallInteger(aInfo.freeMilitaryUnitsPopulationPercent));
			info.setGoldPerUnit(JaxbUtils.unmarshallInteger(aInfo.goldPerUnit));
			info.setGoldPerMilitaryUnit(JaxbUtils.unmarshallInteger(aInfo.goldPerMilitaryUnit));
			info.setHappyPerMilitaryUnit(JaxbUtils.unmarshallInteger(aInfo.happyPerMilitaryUnit));
			info.setMilitaryFoodProduction(JaxbUtils.unmarshallBoolean(aInfo.militaryFoodProduction));
			info.setTribalConscription(JaxbUtils.unmarshallBoolean(aInfo.tribalConscription));
			info.setMaxConscript(JaxbUtils.unmarshallInteger(aInfo.maxConscript));
			info.setUnhealthyPopulationModifier(JaxbUtils.unmarshallInteger(aInfo.unhealthyPopulationModifier));
			info.setExpInBorderModifier(JaxbUtils.unmarshallInteger(aInfo.expInBorderModifier));
			info.setBuildingOnlyHealthy(JaxbUtils.unmarshallBoolean(aInfo.buildingOnlyHealthy));
			info.setLargestCityHappiness(JaxbUtils.unmarshallInteger(aInfo.largestCityHappiness));
			info.setNoCapitalUnhappiness(JaxbUtils.unmarshallBoolean(aInfo.noCapitalUnhappiness));
			info.setTaxRateAngerModifier(JaxbUtils.unmarshallInteger(aInfo.taxRateAngerModifier));
			info.setDistantUnitSupplyCostModifier(JaxbUtils.unmarshallInteger(aInfo.distantUnitSupplyCostModifier));
			info.setWarWearinessModifier(JaxbUtils.unmarshallInteger(aInfo.warWearinessModifier));
			info.setFreeSpecialist(JaxbUtils.unmarshallInteger(aInfo.freeSpecialist));
			info.setTradeRoutes(JaxbUtils.unmarshallInteger(aInfo.tradeRoutes));
			info.setNoForeignTrade(JaxbUtils.unmarshallBoolean(aInfo.noForeignTrade));
			info.setNoCorporations(JaxbUtils.unmarshallBoolean(aInfo.noCorporations));
			info.setNoForeignCorporations(JaxbUtils.unmarshallBoolean(aInfo.noForeignCorporations));
			info.setCivicPercentAnger(JaxbUtils.unmarshallInteger(aInfo.civicPercentAnger));
			info.setStateReligion(JaxbUtils.unmarshallBoolean(aInfo.stateReligion));
			info.setNoNonStateReligionSpread(JaxbUtils.unmarshallBoolean(aInfo.noNonStateReligionSpread));
			info.setStateReligionHappiness(JaxbUtils.unmarshallInteger(aInfo.stateReligionHappiness));
			info.setNonStateReligionHappiness(JaxbUtils.unmarshallInteger(aInfo.nonStateReligionHappiness));
			info.setStateReligionUnitProductionModifier(JaxbUtils.unmarshallInteger(aInfo.stateReligionUnitProductionModifier));
			info.setStateReligionBuildingProductionModifier(JaxbUtils.unmarshallInteger(aInfo.stateReligionBuildingProductionModifier));
			info.setStateReligionFreeExperience(JaxbUtils.unmarshallInteger(aInfo.stateReligionFreeExperience));
			info.setUnitRangeUnbound(JaxbUtils.unmarshallBoolean(aInfo.unitRangeUnbound));
			info.setUnitTerritoryUnbound(JaxbUtils.unmarshallBoolean(aInfo.unitTerritoryUnbound));
			info.setUnitRangeChange(JaxbUtils.unmarshallInteger(aInfo.unitRangeChange));
			info.setUnitRangeModifier(JaxbUtils.unmarshallInteger(aInfo.unitRangeModifier));
			info.setCultureDefenceChange(JaxbUtils.unmarshallInteger(aInfo.cultureDefenceChange));
			info.setPopulationGrowthRateModifier(JaxbUtils.unmarshallInteger(aInfo.populationGrowthRateModifier));

			if (CollectionUtils.hasElements(aInfo.yieldModifiers)) {
				for (Integer val: aInfo.yieldModifiers) {
					info.addYieldModifier(JaxbUtils.unmarshallInteger(val));
				}
			}

			if (CollectionUtils.hasElements(aInfo.capitalYieldModifiers)) {
				for (Integer val: aInfo.capitalYieldModifiers) {
					info.addCapitalYieldModifier(JaxbUtils.unmarshallInteger(val));
				}
			}

			if (CollectionUtils.hasElements(aInfo.tradeYieldModifiers)) {
				for (Integer val: aInfo.tradeYieldModifiers) {
					info.addTradeYieldModifier(JaxbUtils.unmarshallInteger(val));
				}
			}

			if (CollectionUtils.hasElements(aInfo.commerceModifiers)) {
				for (Integer val: aInfo.commerceModifiers) {
					info.addCommerceModifier(JaxbUtils.unmarshallInteger(val));
				}
			}

			if (CollectionUtils.hasElements(aInfo.capitalCommerceModifiers)) {
				for (Integer val: aInfo.capitalCommerceModifiers) {
					info.addCapitalCommerceModifier(JaxbUtils.unmarshallInteger(val));
				}
			}

			if (CollectionUtils.hasElements(aInfo.specialistCommerceChanges)) {
				for (Integer val: aInfo.specialistCommerceChanges) {
					info.addSpecialistCommerceChange(JaxbUtils.unmarshallInteger(val));
				}
			}

			if (CollectionUtils.hasElements(aInfo.hurrys)) {
				for (String val: aInfo.hurrys) {
					if (StringUtils.hasCharacters(val)) {
						info.addHurry(JaxbUtils.unmarshallString(val));
					}
				}
			}

			if (CollectionUtils.hasElements(aInfo.specialBuildingNotRequireds)) {
				for (String val: aInfo.specialBuildingNotRequireds) {
					if (StringUtils.hasCharacters(val)) {
						info.addSpecialBuildingNotRequired(JaxbUtils.unmarshallString(val));
					}
				}
			}

			if (CollectionUtils.hasElements(aInfo.specialistValids)) {
				for (String val: aInfo.specialistValids) {
					if (StringUtils.hasCharacters(val)) {
						info.addSpecialistValid(JaxbUtils.unmarshallString(val));
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

			if (CollectionUtils.hasElements(aInfo.buildingClassProductionModifiers)) {
				for (AdaptedBuildingClassProductionModifiers adaptor: aInfo.buildingClassProductionModifiers) {
					if (StringUtils.hasCharacters(adaptor.buildingClassType)) {
						info.addBuildingClassProductionModifier(new Pair<String, Integer>(adaptor.buildingClassType, adaptor.iModifier));
					}
				}
			}

			if (CollectionUtils.hasElements(aInfo.buildingHappinessChanges)) {
				for (AdaptedBuildingHappinessChanges adaptor: aInfo.buildingHappinessChanges) {
					if (StringUtils.hasCharacters(adaptor.buildingType)) {
						info.addBuildingHappinessChange(new Pair<String, Integer>(adaptor.buildingType, adaptor.iHappinessChange));
					}
				}
			}

			if (CollectionUtils.hasElements(aInfo.buildingHealthChanges)) {
				for (AdaptedBuildingHealthChanges adaptor: aInfo.buildingHealthChanges) {
					if (StringUtils.hasCharacters(adaptor.buildingType)) {
						info.addBuildingHealthChange(new Pair<String, Integer>(adaptor.buildingType, adaptor.iHealthChange));
					}
				}
			}

			if (CollectionUtils.hasElements(aInfo.featureHappinessChanges)) {
				for (AdaptedFeatureHappinessChanges adaptor: aInfo.featureHappinessChanges) {
					if (StringUtils.hasCharacters(adaptor.featureType)) {
						info.addFeatureHappinessChange(new Pair<String, Integer>(adaptor.featureType, adaptor.iHappinessChange));
					}
				}
			}
			info.setImprovementYieldChanges(aInfo.improvementYieldChanges);
			info.setUpgradeAnywhere(JaxbUtils.unmarshallBoolean(aInfo.upgradeAnywhere));
			info.setWeLoveTheKing(JaxbUtils.unmarshallString(aInfo.weLoveTheKing));
			info.setCityDefenceModifier(JaxbUtils.unmarshallInteger(aInfo.cityDefenceModifier));

			map.put(aInfo.type, info);
		}
		return map;
	}

	@Override
	public CivicMap marshal(Map<String, ICivicInfo> v) throws Exception {
		CivicMap map = new CivicMap();
		for (ICivicInfo info: v.values()) {
			AdaptedCivic aInfo = new AdaptedCivic();
			aInfo.type = JaxbUtils.marshallString(info.getType());
			aInfo.civicOptionType = JaxbUtils.marshallMandatoryString(info.getCivicOptionType());
			aInfo.description = JaxbUtils.marshallMandatoryString(info.getDescription());
			aInfo.civilopedia = JaxbUtils.marshallMandatoryString(info.getCivilopedia());
			aInfo.strategy = JaxbUtils.marshallMandatoryString(info.getStrategy());
			aInfo.help = JaxbUtils.marshallString(info.getHelp());
			aInfo.button = JaxbUtils.marshallMandatoryString(info.getButton());
			aInfo.index = JaxbUtils.marshallMandatoryInteger(info.getIndex());
			aInfo.techPrereq = JaxbUtils.marshallString(info.getTechPrereq());
			aInfo.createBarbarians = JaxbUtils.marshallBoolean(info.isCreateBarbarians());
			aInfo.enableStarSigns = JaxbUtils.marshallBoolean(info.isEnableStarSigns());
			aInfo.starSignMitigateChangePercent = JaxbUtils.marshallInteger(info.getStarSignMitigateChangePercent());
			aInfo.starSignScaleChangePercent = JaxbUtils.marshallInteger(info.getStarSignScaleChangePercent());
			aInfo.anarchyLength = JaxbUtils.marshallInteger(info.getAnarchyLength());
			aInfo.upkeep = JaxbUtils.marshallString(info.getUpkeep());
			aInfo.aIWeight = JaxbUtils.marshallInteger(info.getAIWeight());
			aInfo.greatPeopleRateModifier = JaxbUtils.marshallInteger(info.getGreatPeopleRateModifier());
			aInfo.greatGeneralRateModifier = JaxbUtils.marshallInteger(info.getGreatGeneralRateModifier());
			aInfo.domesticGreatGeneralRateModifier = JaxbUtils.marshallInteger(info.getDomesticGreatGeneralRateModifier());
			aInfo.stateReligionGreatPeopleRateModifier = JaxbUtils.marshallInteger(info.getStateReligionGreatPeopleRateModifier());
			aInfo.distanceMaintenanceModifier = JaxbUtils.marshallInteger(info.getDistanceMaintenanceModifier());
			aInfo.numCitiesMaintenanceModifier = JaxbUtils.marshallInteger(info.getNumCitiesMaintenanceModifier());
			aInfo.corporationMaintenanceModifier = JaxbUtils.marshallInteger(info.getCorporationMaintenanceModifier());
			aInfo.foreignTradeRouteModifier = JaxbUtils.marshallInteger(info.getForeignTradeRouteModifier());
			aInfo.extraHealth = JaxbUtils.marshallInteger(info.getExtraHealth());
			aInfo.extraHappiness = JaxbUtils.marshallInteger(info.getExtraHappiness());
			aInfo.freeExperience = JaxbUtils.marshallInteger(info.getFreeExperience());
			aInfo.workerSpeedModifier = JaxbUtils.marshallInteger(info.getWorkerSpeedModifier());
			aInfo.improvementUpgradeRateModifier = JaxbUtils.marshallInteger(info.getImprovementUpgradeRateModifier());
			aInfo.militaryProductionModifier = JaxbUtils.marshallInteger(info.getMilitaryProductionModifier());
			aInfo.baseFreeUnits = JaxbUtils.marshallInteger(info.getBaseFreeUnits());
			aInfo.baseFreeMilitaryUnits = JaxbUtils.marshallInteger(info.getBaseFreeMilitaryUnits());
			aInfo.freeUnitsPopulationPercent = JaxbUtils.marshallInteger(info.getFreeUnitsPopulationPercent());
			aInfo.freeMilitaryUnitsPopulationPercent = JaxbUtils.marshallInteger(info.getFreeMilitaryUnitsPopulationPercent());
			aInfo.goldPerUnit = JaxbUtils.marshallInteger(info.getGoldPerUnit());
			aInfo.goldPerMilitaryUnit = JaxbUtils.marshallInteger(info.getGoldPerMilitaryUnit());
			aInfo.happyPerMilitaryUnit = JaxbUtils.marshallInteger(info.getHappyPerMilitaryUnit());
			aInfo.militaryFoodProduction = JaxbUtils.marshallBoolean(info.isMilitaryFoodProduction());
			aInfo.tribalConscription = JaxbUtils.marshallBoolean(info.isTribalConscription());
			aInfo.maxConscript = JaxbUtils.marshallInteger(info.getMaxConscript());
			aInfo.unhealthyPopulationModifier = JaxbUtils.marshallInteger(info.getUnhealthyPopulationModifier());
			aInfo.expInBorderModifier = JaxbUtils.marshallInteger(info.getExpInBorderModifier());
			aInfo.buildingOnlyHealthy = JaxbUtils.marshallBoolean(info.isBuildingOnlyHealthy());
			aInfo.largestCityHappiness = JaxbUtils.marshallInteger(info.getLargestCityHappiness());
			aInfo.noCapitalUnhappiness = JaxbUtils.marshallBoolean(info.isNoCapitalUnhappiness());
			aInfo.taxRateAngerModifier = JaxbUtils.marshallInteger(info.getTaxRateAngerModifier());
			aInfo.distantUnitSupplyCostModifier = JaxbUtils.marshallInteger(info.getDistantUnitSupplyCostModifier());
			aInfo.warWearinessModifier = JaxbUtils.marshallInteger(info.getWarWearinessModifier());
			aInfo.freeSpecialist = JaxbUtils.marshallInteger(info.getFreeSpecialist());
			aInfo.tradeRoutes = JaxbUtils.marshallInteger(info.getTradeRoutes());
			aInfo.noForeignTrade = JaxbUtils.marshallBoolean(info.isNoForeignTrade());
			aInfo.noCorporations = JaxbUtils.marshallBoolean(info.isNoCorporations());
			aInfo.noForeignCorporations = JaxbUtils.marshallBoolean(info.isNoForeignCorporations());
			aInfo.civicPercentAnger = JaxbUtils.marshallInteger(info.getCivicPercentAnger());
			aInfo.stateReligion = JaxbUtils.marshallBoolean(info.isStateReligion());
			aInfo.noNonStateReligionSpread = JaxbUtils.marshallBoolean(info.isNoNonStateReligionSpread());
			aInfo.stateReligionHappiness = JaxbUtils.marshallInteger(info.getStateReligionHappiness());
			aInfo.nonStateReligionHappiness = JaxbUtils.marshallInteger(info.getNonStateReligionHappiness());
			aInfo.stateReligionUnitProductionModifier = JaxbUtils.marshallInteger(info.getStateReligionUnitProductionModifier());
			aInfo.stateReligionBuildingProductionModifier = JaxbUtils.marshallInteger(info.getStateReligionBuildingProductionModifier());
			aInfo.stateReligionFreeExperience = JaxbUtils.marshallInteger(info.getStateReligionFreeExperience());
			aInfo.unitRangeUnbound = JaxbUtils.marshallBoolean(info.isUnitRangeUnbound());
			aInfo.unitTerritoryUnbound = JaxbUtils.marshallBoolean(info.isUnitTerritoryUnbound());
			aInfo.unitRangeChange = JaxbUtils.marshallInteger(info.getUnitRangeChange());
			aInfo.unitRangeModifier = JaxbUtils.marshallInteger(info.getUnitRangeModifier());
			aInfo.cultureDefenceChange = JaxbUtils.marshallInteger(info.getCultureDefenceChange());
			aInfo.populationGrowthRateModifier = JaxbUtils.marshallInteger(info.getPopulationGrowthRateModifier());

			if (CollectionUtils.hasElements(info.getYieldModifiers())) {
				aInfo.yieldModifiers = new ArrayList<Integer>();
				for(Integer val: info.getYieldModifiers()) {
					aInfo.yieldModifiers.add(JaxbUtils.marshallMandatoryInteger(val));
				}
			}

			if (CollectionUtils.hasElements(info.getCapitalYieldModifiers())) {
				aInfo.capitalYieldModifiers = new ArrayList<Integer>();
				for(Integer val: info.getCapitalYieldModifiers()) {
					aInfo.capitalYieldModifiers.add(JaxbUtils.marshallMandatoryInteger(val));
				}
			}

			if (CollectionUtils.hasElements(info.getTradeYieldModifiers())) {
				aInfo.tradeYieldModifiers = new ArrayList<Integer>();
				for(Integer val: info.getTradeYieldModifiers()) {
					aInfo.tradeYieldModifiers.add(JaxbUtils.marshallMandatoryInteger(val));
				}
			}

			if (CollectionUtils.hasElements(info.getCommerceModifiers())) {
				aInfo.commerceModifiers = new ArrayList<Integer>();
				for(Integer val: info.getCommerceModifiers()) {
					aInfo.commerceModifiers.add(JaxbUtils.marshallMandatoryInteger(val));
				}
			}

			if (CollectionUtils.hasElements(info.getCapitalCommerceModifiers())) {
				aInfo.capitalCommerceModifiers = new ArrayList<Integer>();
				for(Integer val: info.getCapitalCommerceModifiers()) {
					aInfo.capitalCommerceModifiers.add(JaxbUtils.marshallMandatoryInteger(val));
				}
			}

			if (CollectionUtils.hasElements(info.getSpecialistCommerceChanges())) {
				aInfo.specialistCommerceChanges = new ArrayList<Integer>();
				for(Integer val: info.getSpecialistCommerceChanges()) {
					aInfo.specialistCommerceChanges.add(JaxbUtils.marshallMandatoryInteger(val));
				}
			}

			if (CollectionUtils.hasElements(info.getHurrys())) {
				aInfo.hurrys = new ArrayList<String>();
				for(String val: info.getHurrys()) {
					aInfo.hurrys.add(JaxbUtils.marshallMandatoryString(val));
				}
			}

			if (CollectionUtils.hasElements(info.getSpecialBuildingNotRequireds())) {
				aInfo.specialBuildingNotRequireds = new ArrayList<String>();
				for(String val: info.getSpecialBuildingNotRequireds()) {
					aInfo.specialBuildingNotRequireds.add(JaxbUtils.marshallMandatoryString(val));
				}
			}

			if (CollectionUtils.hasElements(info.getSpecialistValids())) {
				aInfo.specialistValids = new ArrayList<String>();
				for(String val: info.getSpecialistValids()) {
					aInfo.specialistValids.add(JaxbUtils.marshallMandatoryString(val));
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

			if (CollectionUtils.hasElements(info.getBuildingClassProductionModifiers())) {
				aInfo.buildingClassProductionModifiers = new ArrayList<AdaptedBuildingClassProductionModifiers>();
				for (IPair<String, Integer> pair: info.getBuildingClassProductionModifiers()) {
					AdaptedBuildingClassProductionModifiers adaptor = new AdaptedBuildingClassProductionModifiers();
					adaptor.buildingClassType = pair.getKey();
					adaptor.iModifier = pair.getValue();
					aInfo.buildingClassProductionModifiers.add(adaptor);
				}
			}

			if (CollectionUtils.hasElements(info.getBuildingHappinessChanges())) {
				aInfo.buildingHappinessChanges = new ArrayList<AdaptedBuildingHappinessChanges>();
				for (IPair<String, Integer> pair: info.getBuildingHappinessChanges()) {
					AdaptedBuildingHappinessChanges adaptor = new AdaptedBuildingHappinessChanges();
					adaptor.buildingType = pair.getKey();
					adaptor.iHappinessChange = pair.getValue();
					aInfo.buildingHappinessChanges.add(adaptor);
				}
			}

			if (CollectionUtils.hasElements(info.getBuildingHealthChanges())) {
				aInfo.buildingHealthChanges = new ArrayList<AdaptedBuildingHealthChanges>();
				for (IPair<String, Integer> pair: info.getBuildingHealthChanges()) {
					AdaptedBuildingHealthChanges adaptor = new AdaptedBuildingHealthChanges();
					adaptor.buildingType = pair.getKey();
					adaptor.iHealthChange = pair.getValue();
					aInfo.buildingHealthChanges.add(adaptor);
				}
			}

			if (CollectionUtils.hasElements(info.getFeatureHappinessChanges())) {
				aInfo.featureHappinessChanges = new ArrayList<AdaptedFeatureHappinessChanges>();
				for (IPair<String, Integer> pair: info.getFeatureHappinessChanges()) {
					AdaptedFeatureHappinessChanges adaptor = new AdaptedFeatureHappinessChanges();
					adaptor.featureType = pair.getKey();
					adaptor.iHappinessChange = pair.getValue();
					aInfo.featureHappinessChanges.add(adaptor);
				}
			}
			aInfo.improvementYieldChanges = info.getImprovementYieldChanges();
			aInfo.upgradeAnywhere = JaxbUtils.marshallBoolean(info.isUpgradeAnywhere());
			aInfo.weLoveTheKing = JaxbUtils.marshallString(info.getWeLoveTheKing());
			aInfo.cityDefenceModifier = JaxbUtils.marshallInteger(info.getCityDefenceModifier());

			map.entries.add(aInfo);
		}
		return map;
	}
}