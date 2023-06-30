package org.archid.civ4.info.building;

import java.util.LinkedHashSet;
import java.util.Set;

import org.archid.civ4.info.AbstractConverter;
import org.archid.civ4.info.IXmlTaggedInfo;
import org.archid.utils.StringUtils;

public class KBuildingConverter extends AbstractConverter {
	
	private String prefix = super.getPrefix();
	
	public KBuildingConverter() {
		super();
		formatter = new BuildingInfoXmlFormatter();
	}

	@Override
	protected IXmlTaggedInfo updateCustomTags(IXmlTaggedInfo info) {
		
		prefix = info.getPrefix();
		
		// We need to change the order of some tags and combine others so create a new info object
		IXmlTaggedInfo target = getXmlTaggedInfo();
		target.setStartTag(info.getStartTag());
		target.setEndTag(info.getEndTag());
		
		// There is no iterator through the info tags so we need to build the set of tags that are common first
		String arr1[] = {"BuildingClass", "Type", "SpecialBuildingType", "Description", "Civilopedia", "Strategy", "Help", "Advisor", "ArtDefineTag", "MovieDefineTag", "HolyCity", "ReligionType", "StateReligion", "bStateReligion", "PrereqReligion", "bPrereqAllReligions", "PrereqCorporation", "FoundsCorporation", "GlobalReligionCommerce", "GlobalCorporationCommerce", "VictoryPrereq", "FreeStartEra", "MaxStartEra", "ObsoleteTech"};
		copyTags(info, target, arr1);

		// The prereq techs are next in the target and need to be processed differently as OOB uses a single tag and a multi-line tag
		// but we combine these into one multi-line tag
		Set<String> techs = new LinkedHashSet<String>();
		String val = getSingleTagValue(info.getTagXml("PrereqTech"));
		if (StringUtils.hasCharacters(val)) techs.add(val);
		for (String tech: getMultiSingleValues(info.getTagXml("TechTypes"))) {
			techs.add(tech);
		}
		target.setTagXml("PrereqAndTechs", buildMultiSingleTag("PrereqAndTechs", "TechType", techs));
		
		val = getSingleTagValue(info.getTagXml("Bonus"));
		if (StringUtils.hasCharacters(val))	target.setTagXml("PrereqBonus", buildTag("PrereqBonus", val));
		
		Set<String> prereqBonuses = new LinkedHashSet<String>();
		for (String bonus: getMultiSingleValues(info.getTagXml("PrereqBonuses"))) {
			prereqBonuses.add(bonus);
		}
		target.setTagXml("PrereqOrBonuses", buildMultiSingleTag("PrereqOrBonuses", "BonusType", prereqBonuses));
		
		String arr2[] = {"PrereqAndCivics", "PrereqOrCivics", "PrereqAndTerrains", "PrereqOrTerrains"};
		copyTags(info, target, arr2);
		
		val = getSingleTagValue(info.getTagXml("PrereqVicinityBonus"));
		if (StringUtils.hasCharacters(val))	{
			Set<String> prereqVicinityAndBonuses = new LinkedHashSet<String>();
			prereqVicinityAndBonuses.add(val);
			target.setTagXml("PrereqVicinityAndBonus", buildMultiSingleTag("PrereqVicinityAndBonus", "BonusType", prereqVicinityAndBonuses));
		}
		
		Set<String> prereqVicinityOrBonuses = new LinkedHashSet<String>();
		for (String bonus: getMultiSingleValues(info.getTagXml("PrereqVicinityBonuses"))) {
			prereqVicinityOrBonuses.add(bonus);
		}
		target.setTagXml("PrereqVicinityOrBonus", buildMultiSingleTag("PrereqVicinityOrBonus", "BonusType", prereqVicinityOrBonuses));
		
		String arr2a[] = {"PrereqVicinityImprovements", "PrereqVicinityFeatures"};
		copyTags(info, target, arr2a);

		val = getSingleTagValue(info.getTagXml("iPrereqPopulation"));
		if (StringUtils.hasCharacters(val))	target.setTagXml("iMinPopulation", buildTag("iMinPopulation", val));
		
		val = getSingleTagValue(info.getTagXml("PrereqCultureLevel"));
		if (StringUtils.hasCharacters(val))	target.setTagXml("MinCultureLevel", buildTag("MinCultureLevel", val));
		
		String arr2b[] = {"bPrereqPower", "ProductionTraits", "HappinessTraits", "NoBonus", "PowerBonus", "FreeBonus", "iNumFreeBonuses", "FreeBuilding", "FreePromotion", "CivicOption", "GreatPeopleUnitClass", "iGreatPeopleRateChange", "iHurryAngerModifier", "bBorderObstacle", "bTeamShare", "bWater", "bRiver", "bPower", "bDirtyPower", "bAreaCleanPower", "DiploVoteType", "bForceTeamVoteEligible", "bCapital", "bGovernmentCenter", "bGoldenAge", "bAllowsNukes", "bMapCentering", "bNoUnhappiness", "iUnhealthyPopulationModifier"};
		copyTags(info, target, arr2b);
		
		String arr3[] = { "bBuildingOnlyHealthy", "bNeverCapture", "bNukeImmune", "bPrereqReligion", "bCenterInCity", "iAIWeight", "iCost", "iHurryCostModifier", "iAdvancedStartCost", "iAdvancedStartCostIncrease", "iMinAreaSize", "iConquestProb", "iCitiesPrereq", "iTeamsPrereq", "iLevelPrereq", "iMinLatitude", "iMaxLatitude", "iWorkableRadius", "iGreatPeopleRateModifier", "iGreatGeneralRateModifier", "iDomesticGreatGeneralRateModifier", "iGlobalGreatPeopleRateModifier", "iAnarchyModifier", "iGoldenAgeModifier", "iGlobalHurryModifier", "iExperience", "iGlobalExperience", "iFoodKept", "iAirlift", "iAirModifier", "iAirUnitCapacity", "iNukeModifier", "iNukeExplosionRand", "iFreeSpecialist", "iAreaFreeSpecialist", "iGlobalFreeSpecialist", "iMaintenanceModifier", "iWarWearinessModifier", "iGlobalWarWearinessModifier", "iEnemyWarWearinessModifier", "iHealRateChange", "iHealth", "iAreaHealth", "iGlobalHealth", "iHappiness", "iAreaHappiness", "iGlobalHappiness", "iStateReligionHappiness", "iWorkerSpeedModifier", "iMilitaryProductionModifier", "iSpaceProductionModifier", "iGlobalSpaceProductionModifier", "iTradeRoutes", "iCoastalTradeRoutes", "iGlobalTradeRoutes", "iTradeRouteModifier", "iForeignTradeRouteModifier", "iGlobalPopulationChange", "iFreeTechs", "iDefense", "iBombardDefense", "iAllCityDefense", "iEspionageDefense", "iAsset", "iPower", "fVisibilityPriority", "SeaPlotYieldChanges", "RiverPlotYieldChanges", "GlobalSeaPlotYieldChanges", "YieldChanges", "YieldModifiers", "PowerYieldModifiers", "AreaYieldModifiers", "GlobalYieldModifiers", "GlobalYieldChanges", "CommerceChanges", "ObsoleteSafeCommerceChanges", "CommerceChangeDoubleTimes", "CommerceModifiers", "GlobalCommerceModifiers", "SpecialistExtraCommerces", "StateReligionCommerces", "CommerceHappinesses", "ReligionChanges", "SpecialistCounts", "FreeSpecialistCounts", "CommerceFlexibles", "CommerceChangeOriginalOwners", "ConstructSound", "BonusHealthChanges", "BonusHappinessChanges", "BonusProductionModifiers", "UnitCombatFreeExperiences", "DomainFreeExperiences", "DomainProductionModifiers", "BuildingHappinessChanges" };
		copyTags(info, target, arr3);
		
		String tmp = info.getTagXml("PrereqBuildingClasses");
		if (StringUtils.hasCharacters(tmp)) {
			tmp = tmp.replaceAll("PrereqBuildingClasses", "PrereqNumOfBuildingClasses");
			tmp = tmp.replaceAll("PrereqBuildingClass", "PrereqNumOfBuildingClass");
			tmp = tmp.replaceAll("iNumBuildingNeeded", "iNeeded");
			target.setTagXml("PrereqNumOfBuildingClasses", tmp);
		}
		
		tmp = info.getTagXml("BuildingClassNeededs");
		if (StringUtils.hasCharacters(tmp)) {
			tmp = tmp.replaceAll("BuildingClassNeededs", "PrereqAndBuildingClasses");
			tmp = removeTag(tmp, "bNeededInCity");
			tmp = removeTag(tmp, "BuildingClassNeeded>");
			tmp = tmp.replaceAll("\\t<BuildingClassType", "<BuildingClass");
			tmp = tmp.replaceAll("BuildingClassType", "BuildingClass");
			target.setTagXml("PrereqAndBuildingClasses", tmp);
		}
		
		tmp = info.getTagXml("ReplacementBuildings");
		if (StringUtils.hasCharacters(tmp)) {
			tmp = tmp.replaceAll("ReplacementBuildings", "ReplacedByBuildingClasses");
			tmp = removeTag(tmp, "bReplace");
			tmp = removeTag(tmp, "ReplacementBuilding>");
			tmp = tmp.replaceAll("\\t<BuildingClassType", "<BuildingClassType");
			target.setTagXml("ReplacedByBuildingClasses", tmp);
		}
		
		String arr4[] = { "SpecialistYieldChanges", "BonusYieldModifiers", "BonusYieldChanges", "TechYieldChanges", "TechCommerceChanges", "ImprovementFreeSpecialists", "Flavors", "HotKey", "bAltDown", "bShiftDown", "bCtrlDown", "iHotKeyPriority", "iOrderPriority" };
		copyTags(info, target, arr4);

		return target;
	}
	
	protected String getPrefix() {
		return prefix;
	}

}
