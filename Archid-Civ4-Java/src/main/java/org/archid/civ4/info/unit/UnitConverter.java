package org.archid.civ4.info.unit;

import java.util.LinkedHashSet;
import java.util.Set;

import org.archid.civ4.info.AbstractConverter;
import org.archid.civ4.info.IXmlTaggedInfo;
import org.archid.utils.StringUtils;

public class UnitConverter extends AbstractConverter {

	private String prefix = super.getPrefix();

	@Override
	protected IXmlTaggedInfo updateCustomTags(IXmlTaggedInfo info) {
		
		prefix = info.getPrefix();
		
		// We need to change the order of some tags and combine others so create a new info object
		IXmlTaggedInfo target = getXmlTaggedInfo();
		target.setStartTag(info.getStartTag());
		target.setEndTag(info.getEndTag());
		
		// There is no iterator through the info tags so we need to build the set of tags that are common first
		String arr1[] = {"Class", "Type", "UniqueNames", "Special", "Capture", "Combat", "Domain", "DefaultUnitAI", "Invisible"};
		copyTags(info, target, arr1);

		// We can now have multiple see invisible types
		String val = getSingleTagValue(info.getTagXml("SeeInvisible"));
		if (StringUtils.hasCharacters(val)) {
			Set<String> seeInvisibles = new LinkedHashSet<String>();
			seeInvisibles.add(val);
			target.setTagXml("SeeInvisibles", buildMultiSingleTag("SeeInvisibles", "InvisibleType", seeInvisibles));
		}
		
		String arr2[] = {"Description", "Civilopedia", "Strategy", "Help", "Advisor",	"bAnimal", "bFood", "bNoBadGoodies", "bOnlyDefensive", "bNoCapture", "bQuickCombat", "bRivalTerritory", "bMilitaryHappiness", "bMilitarySupport", "bMilitaryProduction", "bPillage", "bSpy", "bSabotage",
				"bDestroy", "bStealPlans", "bInvestigate", "bCounterSpy", "bFound", "bGoldenAge", "bInvisible", "bFirstStrikeImmune", "bNoDefensiveBonus", "bIgnoreBuildingDefense", "bCanMoveImpassable",
				"bCanMoveAllTerrain", "bFlatMovementCost", "bIgnoreTerrainCost", "bNukeImmune", "bPrereqBonuses", "bPrereqReligion", "bMechanized", "bRenderBelowWater", "bRenderAlways", "bSuicide", "bLineOfSight",
				"bHiddenNationality", "bAlwaysHostile"}; 
		copyTags(info, target, arr2);
		
		String tmp = info.getTagXml("UnitClassUpgrades");
		if (StringUtils.hasCharacters(tmp)) {
			tmp = removeTag(tmp, "bUnitClassUpgrade");
			tmp = removeTag(tmp, "UnitClassUpgrade>");
			tmp = tmp.replaceAll("\\t<UnitClassUpgradeType", "<UnitClassType");
			tmp = tmp.replaceAll("UnitClassUpgradeType", "UnitClassType");
			target.setTagXml("UnitClassUpgrades", tmp);
		}

		tmp = info.getTagXml("UnitClassTargets");
		if (StringUtils.hasCharacters(tmp)) {
			tmp = removeTag(tmp, "bUnitClassTarget");
			tmp = removeTag(tmp, "UnitClassTarget>");
			tmp = tmp.replaceAll("\\t<UnitClassTargetType", "<UnitClassType");
			tmp = tmp.replaceAll("UnitClassTargetType", "UnitClassType");
			target.setTagXml("UnitClassTargets", tmp);
		}

		tmp = info.getTagXml("UnitCombatTargets");
		if (StringUtils.hasCharacters(tmp)) {
			tmp = removeTag(tmp, "bUnitCombatTarget");
			tmp = removeTag(tmp, "UnitCombatTarget>");
			tmp = tmp.replaceAll("\\t<UnitCombatTargetType", "<UnitCombatType");
			tmp = tmp.replaceAll("UnitCombatTargetType", "UnitCombatType");
			target.setTagXml("UnitCombatTargets", tmp);
		}

		tmp = info.getTagXml("UnitClassDefenders");
		if (StringUtils.hasCharacters(tmp)) {
			tmp = removeTag(tmp, "bUnitClassDefender");
			tmp = removeTag(tmp, "UnitClassDefender>");
			tmp = tmp.replaceAll("\\t<UnitClassDefenderType", "<UnitClassType");
			tmp = tmp.replaceAll("UnitClassDefenderType", "UnitClassType");
			target.setTagXml("UnitClassDefenders", tmp);
		}

		tmp = info.getTagXml("UnitCombatDefenders");
		if (StringUtils.hasCharacters(tmp)) {
			tmp = removeTag(tmp, "bUnitCombatDefender");
			tmp = removeTag(tmp, "UnitCombatDefender>");
			tmp = tmp.replaceAll("\\t<UnitCombatDefenderType", "<UnitCombatType");
			tmp = tmp.replaceAll("UnitCombatDefenderType", "UnitCombatType");
			target.setTagXml("UnitCombatDefenders", tmp);
		}
			
		String arr3[] = {"FlankingStrikes"};
		copyTags(info, target, arr3);
		
		tmp = info.getTagXml("UnitAIs");
		if (StringUtils.hasCharacters(tmp)) {
			tmp = removeTag(tmp, "bUnitAI");
			tmp = removeTag(tmp, "UnitAI>");
			tmp = tmp.replaceAll("\\t<UnitAIType", "<UnitAIType");
			target.setTagXml("UnitAIs", tmp);
		}
			
		tmp = info.getTagXml("NotUnitAIs");
		if (StringUtils.hasCharacters(tmp)) {
			tmp = removeTag(tmp, "bUnitAI");
			tmp = removeTag(tmp, "UnitAI>");
			tmp = tmp.replaceAll("\\t<UnitAIType", "<UnitAIType");
			target.setTagXml("NotUnitAIs", tmp);
		}
			
		tmp = info.getTagXml("Builds");
		if (StringUtils.hasCharacters(tmp)) {
			tmp = removeTag(tmp, "bBuild");
			tmp = removeTag(tmp, "Build>");
			tmp = tmp.replaceAll("\\t<BuildType", "<BuildType");
			target.setTagXml("Builds", tmp);
		}
			
		String arr4[] = {"ReligionSpreads", "CorporationSpreads"};
		copyTags(info, target, arr4);

		tmp = info.getTagXml("GreatPeoples");
		if (StringUtils.hasCharacters(tmp)) {
			tmp = removeTag(tmp, "bGreatPeople");
			tmp = removeTag(tmp, "GreatPeople>");
			tmp = tmp.replaceAll("\\t<GreatPeopleType", "<GreatPeopleType");
			target.setTagXml("GreatPeoples", tmp);
		}
			
		tmp = info.getTagXml("Buildings");
		if (StringUtils.hasCharacters(tmp)) {
			tmp = removeTag(tmp, "bBuilding");
			tmp = removeTag(tmp, "Building>");
			tmp = tmp.replaceAll("\\t<BuildingType", "<BuildingType");
			target.setTagXml("Buildings", tmp);
		}
			
		tmp = info.getTagXml("ForceBuildings");
		if (StringUtils.hasCharacters(tmp)) {
			tmp = removeTag(tmp, "bForceBuilding");
			tmp = removeTag(tmp, "ForceBuilding>");
			tmp = tmp.replaceAll("\\t<BuildingType", "<BuildingType");
			target.setTagXml("ForceBuildings", tmp);
		}
			
		String arr5[] = {"HolyCity", "ReligionType", "StateReligion",	"PrereqReligion", "PrereqCorporation", "PrereqBuilding" };
		copyTags(info, target, arr5);
		
		// The prereq techs are next in the target and need to be processed differently as OOB uses a single tag and a multi-line tag
		// but we combine these into one multi-line tag
		Set<String> techs = new LinkedHashSet<String>();
		val = getSingleTagValue(info.getTagXml("PrereqTech"));
		if (StringUtils.hasCharacters(val)) techs.add(val);
		for (String tech: getMultiSingleValues(info.getTagXml("TechTypes"))) {
			techs.add(tech);
		}
		target.setTagXml("PrereqTechs", buildMultiSingleTag("PrereqTechs", "TechType", techs));
		
		String arr6[] = {"BonusType"};
		copyTags(info, target, arr6);
		
		Set<String> prereqOrBonuses = new LinkedHashSet<String>();
		for (String bonus: getMultiSingleValues(info.getTagXml("PrereqBonuses"))) {
			prereqOrBonuses.add(bonus);
		}
		target.setTagXml("PrereqOrBonuses", buildMultiSingleTag("PrereqOrBonuses", "BonusType", prereqOrBonuses));
		
		String arr7[] = {"ProductionTraits", "Flavors", "iAIWeight", "iCost", "iHurryCostModifier", "iAdvancedStartCost", "iAdvancedStartCostIncrease", "iMinAreaSize", "iMoves", "bNoRevealMap", "iAirRange", "iAirUnitCap",
				"iDropRange", "iNukeRange", "iWorkRate", "iBaseDiscover", "iDiscoverMultiplier", "iBaseHurry", "iHurryMultiplier", "iBaseTrade", "iTradeMultiplier", "iGreatWorkCulture", "iEspionagePoints"};
		copyTags(info, target, arr7);
		
		tmp = info.getTagXml("TerrainImpassables");
		if (StringUtils.hasCharacters(tmp)) {
			tmp = removeTag(tmp, "bTerrainImpassable");
			tmp = removeTag(tmp, "TerrainImpassable>");
			tmp = tmp.replaceAll("\\t<TerrainType", "<TerrainType");
			target.setTagXml("TerrainImpassables", tmp);
		}
			
		tmp = info.getTagXml("FeatureImpassables");
		if (StringUtils.hasCharacters(tmp)) {
			tmp = removeTag(tmp, "bFeatureImpassable");
			tmp = removeTag(tmp, "FeatureImpassable>");
			tmp = tmp.replaceAll("\\t<FeatureType", "<FeatureType");
			target.setTagXml("FeatureImpassables", tmp);
		}
			
		String arr8[] = {"TerrainPassableTechs", "FeaturePassableTechs", "iCombat", "iCombatLimit", "iAirCombat", "iAirCombatLimit", "iXPValueAttack", "iXPValueDefense", "iFirstStrikes",
				"iChanceFirstStrikes", "iInterceptionProbability", "iEvasionProbability", "iWithdrawalProb", "iCollateralDamage", "iCollateralDamageLimit", "iCollateralDamageMaxUnits", "iCityAttack", "iCityDefense",
				"iAnimalCombat", "iHillsAttack", "iHillsDefense"};
		copyTags(info, target, arr8);
		
		tmp = info.getTagXml("TerrainNatives");
		if (StringUtils.hasCharacters(tmp)) {
			tmp = removeTag(tmp, "bTerrainNative");
			tmp = removeTag(tmp, "TerrainNative>");
			tmp = tmp.replaceAll("\\t<TerrainType", "<TerrainType");
			target.setTagXml("TerrainNatives", tmp);
		}
			
		tmp = info.getTagXml("FeatureNatives");
		if (StringUtils.hasCharacters(tmp)) {
			tmp = removeTag(tmp, "bFeatureNative");
			tmp = removeTag(tmp, "FeatureNative>");
			tmp = tmp.replaceAll("\\t<FeatureType", "<FeatureType");
			target.setTagXml("FeatureNatives", tmp);
		}
			
		String arr9[] = {"TerrainAttacks", "TerrainDefenses", "FeatureAttacks", "FeatureDefenses", "UnitClassAttackMods", "UnitClassDefenseMods", "UnitCombatMods", "UnitCombatCollateralImmunes", "DomainMods",
				"BonusProductionModifiers", "iBombRate", "iBombardRate", "SpecialCargo", "DomainCargo", "iCargo", "iConscription", "iCultureGarrison", "iExtraCost", "iAsset", "iPower", "UnitMeshGroups", "FormationType",
				"HotKey", "bAltDown", "bShiftDown", "bCtrlDown", "iHotKeyPriority"};
		copyTags(info, target, arr9);

		tmp = info.getTagXml("FreePromotions");
		if (StringUtils.hasCharacters(tmp)) {
			tmp = removeTag(tmp, "bFreePromotion");
			tmp = removeTag(tmp, "FreePromotion>");
			tmp = tmp.replaceAll("\\t<PromotionType", "<PromotionType");
			target.setTagXml("FreePromotions", tmp);
		}
			
		String arr10[] = {"LeaderPromotion", "iLeaderExperience", "iOrderPriority"};
		copyTags(info, target, arr10);
		
		return target;
	}
	
	protected String getPrefix() {
		return prefix;
	}

}
