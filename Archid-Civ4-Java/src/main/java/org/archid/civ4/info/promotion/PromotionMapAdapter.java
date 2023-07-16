package org.archid.civ4.info.promotion;

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

public class PromotionMapAdapter extends XmlAdapter<PromotionMapAdapter.PromotionMap, Map<String, IPromotionInfo>> {

	public static class PromotionMap {
		@XmlElement(name = "PromotionInfo")
		List<AdaptedPromotion> entries = new ArrayList<AdaptedPromotion>();
	}

	private static class AdaptedPromotion {
		@XmlElement(name="Type")
		private String type;
		@XmlElement(name="Description")
		private String description;
		@XmlElement(name="Help")
		private String help;
		@XmlElement(name="Sound")
		private String sound;
		@XmlElement(name="LayerAnimationPath")
		private String layerAnimationPath;
		@XmlElement(name="PromotionPrereq")
		private String promotionPrereq;
		@XmlElementWrapper(name="PrereqOrPromotions")
		@XmlElement(name="PromotionType")
		private List<String> prereqOrPromotions;
		@XmlElement(name="TechPrereq")
		private String techPrereq;
		@XmlElement(name="ObsoleteTech")
		private String obsoleteTech;
		@XmlElement(name="StateReligionPrereq")
		private String stateReligionPrereq;
		@XmlElementWrapper(name="SeeInvisibles")
		@XmlElement(name="InvisibleType")
		private List<String> seeInvisibles;
		@XmlElement(name="bCityPrereq")
		private Integer cityPrereq;
		@XmlElement(name="iPromotionGroup")
		private Integer promotionGroup;
		@XmlElement(name="bLeader")
		private Integer leader;
		@XmlElement(name="bBlitz")
		private Integer blitz;
		@XmlElement(name="bAmphib")
		private Integer amphib;
		@XmlElement(name="bRiver")
		private Integer river;
		@XmlElement(name="bEnemyRoute")
		private Integer enemyRoute;
		@XmlElement(name="bAlwaysHeal")
		private Integer alwaysHeal;
		@XmlElement(name="bHillsDoubleMove")
		private Integer hillsDoubleMove;
		@XmlElement(name="bCanMovePeaks")
		private Integer canMovePeaks;
		@XmlElement(name="bImmuneToFirstStrikes")
		private Integer immuneToFirstStrikes;
		@XmlElement(name="bLoyal")
		private Integer loyal;
		@XmlElement(name="bSpyRadiation")
		private Integer spyRadiation;
		@XmlElement(name="bCarryReligion")
		private Integer carryReligion;
		@XmlElement(name="iEnslaveCountChange")
		private Integer enslaveCountChange;
		@XmlElement(name="iVisibilityChange")
		private Integer visibilityChange;
		@XmlElement(name="iMovesChange")
		private Integer movesChange;
		@XmlElement(name="iMoveDiscountChange")
		private Integer moveDiscountChange;
		@XmlElement(name="iAirRangeChange")
		private Integer airRangeChange;
		@XmlElement(name="iInterceptChange")
		private Integer interceptChange;
		@XmlElement(name="iEvasionChange")
		private Integer evasionChange;
		@XmlElement(name="iWithdrawalChange")
		private Integer withdrawalChange;
		@XmlElement(name="iCargoChange")
		private Integer cargoChange;
		@XmlElement(name="iCollateralDamageChange")
		private Integer collateralDamageChange;
		@XmlElement(name="iBombardRateChange")
		private Integer bombardRateChange;
		@XmlElement(name="iFirstStrikesChange")
		private Integer firstStrikesChange;
		@XmlElement(name="iChanceFirstStrikesChange")
		private Integer chanceFirstStrikesChange;
		@XmlElement(name="iEnemyHealChange")
		private Integer enemyHealChange;
		@XmlElement(name="iNeutralHealChange")
		private Integer neutralHealChange;
		@XmlElement(name="iFriendlyHealChange")
		private Integer friendlyHealChange;
		@XmlElement(name="iSameTileHealChange")
		private Integer sameTileHealChange;
		@XmlElement(name="iAdjacentTileHealChange")
		private Integer adjacentTileHealChange;
		@XmlElement(name="iCombatPercent")
		private Integer combatPercent;
		@XmlElement(name="iCityAttack")
		private Integer cityAttack;
		@XmlElement(name="iCityDefense")
		private Integer cityDefense;
		@XmlElement(name="iHillsAttack")
		private Integer hillsAttack;
		@XmlElement(name="iHillsDefense")
		private Integer hillsDefense;
		@XmlElement(name="iKamikazePercent")
		private Integer kamikazePercent;
		@XmlElement(name="iSpyPreparationModifier")
		private Integer spyPreparationModifier;
		@XmlElement(name="iSpyPoisonModifier")
		private Integer spyPoisonModifier;
		@XmlElement(name="iSpyRevoltChange")
		private Integer spyRevoltChange;
		@XmlElement(name="iSpyUnhappyChange")
		private Integer spyUnhappyChange;
		@XmlElement(name="iSpyInterceptChange")
		private Integer spyInterceptChange;
		@XmlElement(name="iSpyEvasionChange")
		private Integer spyEvasionChange;
		@XmlElement(name="iSpyDiploPenaltyChange")
		private Integer spyDiploPenaltyChange;
		@XmlElement(name="iSpyEscapeChange")
		private Integer spyEscapeChange;
		@XmlElement(name="iSpyNukeCityChange")
		private Integer spyNukeCityChange;
		@XmlElement(name="iSpyDisablePowerChange")
		private Integer spyDisablePowerChange;
		@XmlElement(name="iSpyWarWearinessChange")
		private Integer spyWarWearinessChange;
		@XmlElement(name="iSpyResearchSabotageChange")
		private Integer spyResearchSabotageChange;
		@XmlElement(name="iSpyReligionRemovalChange")
		private Integer spyReligionRemovalChange;
		@XmlElement(name="iSpyCorporationRemovalChange")
		private Integer spyCorporationRemovalChange;
		@XmlElement(name="iSpyStealTreasuryChange")
		private Integer spyStealTreasuryChange;
		@XmlElement(name="iSpyBuyTechChange")
		private Integer spyBuyTechChange;
		@XmlElement(name="iSpySwitchCivicChange")
		private Integer spySwitchCivicChange;
		@XmlElement(name="iSpySwitchReligionChange")
		private Integer spySwitchReligionChange;
		@XmlElement(name="iSpyDestroyProjectChange")
		private Integer spyDestroyProjectChange;
		@XmlElement(name="iSpyDestroyBuildingChange")
		private Integer spyDestroyBuildingChange;
		@XmlElement(name="iSpyDestroyImprovementChange")
		private Integer spyDestroyImprovementChange;
		@XmlElement(name="iSpyDestroyProductionChange")
		private Integer spyDestroyProductionChange;
		@XmlElement(name="iSpyCultureChange")
		private Integer spyCultureChange;
		@XmlElement(name="iRevoltProtection")
		private Integer revoltProtection;
		@XmlElement(name="iCollateralDamageProtection")
		private Integer collateralDamageProtection;
		@XmlElement(name="iPillageChange")
		private Integer pillageChange;
		@XmlElement(name="iPlunderChange")
		private Integer plunderChange;
		@XmlElement(name="iExtraMorale")
		private Integer extraMorale;
		@XmlElement(name="iEnemyMoraleModifier")
		private Integer enemyMoraleModifier;
		@XmlElement(name="iUpgradeDiscount")
		private Integer upgradeDiscount;
		@XmlElement(name="iExperiencePercent")
		private Integer experiencePercent;
		@XmlElement(name="iWorkRateModifier")
		private Integer workRateModifier;
		@XmlElement(name="bUnitRangeUnbound")
		private Integer unitRangeUnbound;
		@XmlElement(name="bUnitTerritoryUnbound")
		private Integer unitTerritoryUnbound;
		@XmlElement(name="iUnitRangeChange")
		private Integer unitRangeChange;
		@XmlElement(name="iUnitRangeModifier")
		private Integer unitRangeModifier;
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
		@XmlElementWrapper(name="UnitCombatMods")
		@XmlElement(name="UnitCombatMod")
		private List<AdaptedUnitCombatMods> unitCombatMods;
		@XmlElementWrapper(name="DomainMods")
		@XmlElement(name="DomainMod")
		private List<AdaptedDomainMods> domainMods;
		@XmlElementWrapper(name="TerrainDoubleMoves")
		@XmlElement(name="TerrainType")
		private List<String> terrainDoubleMoves;
		@XmlElementWrapper(name="FeatureDoubleMoves")
		@XmlElement(name="FeatureType")
		private List<String> featureDoubleMoves;
		@XmlElementWrapper(name="BuildLeaveFeatures")
		@XmlElement(name="BuildLeaveFeature")
		private List<AdaptedBuildLeaveFeatures> buildLeaveFeatures;
		@XmlElementWrapper(name="NotUnitCombatTypes")
		@XmlElement(name="UnitCombatType")
		private List<String> notUnitCombatTypes;
		@XmlElementWrapper(name="OrUnitCombatTypes")
		@XmlElement(name="UnitCombatType")
		private List<String> orUnitCombatTypes;
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
		@XmlElement(name="Button")
		private String button;
		@XmlElement(name="iOrderPriority")
		private Integer orderPriority;
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

	private static class AdaptedUnitCombatMods {
		@XmlElement(name="UnitCombatType")
		private String unitCombatType;
		@XmlElement(name="iUnitCombatMod")
		private Integer iUnitCombatMod;
	}

	private static class AdaptedDomainMods {
		@XmlElement(name="DomainType")
		private String domainType;
		@XmlElement(name="iDomainMod")
		private Integer iDomainMod;
	}

	private static class AdaptedBuildLeaveFeatures {
		@XmlElement(name="BuildType")
		private String buildType;
		@XmlElement(name="FeatureType")
		private String featureType;
	}

	@Override
	public Map<String, IPromotionInfo> unmarshal(PromotionMap v) throws Exception {
		Map<String, IPromotionInfo> map = new TreeMap<String, IPromotionInfo>();
		for (AdaptedPromotion aInfo: v.entries) {
			IPromotionInfo info = PromotionInfos.createInfo(aInfo.type);
			info.setDescription(JaxbUtils.unmarshallString(aInfo.description));
			info.setHelp(JaxbUtils.unmarshallString(aInfo.help));
			info.setSound(JaxbUtils.unmarshallString(aInfo.sound));
			info.setLayerAnimationPath(JaxbUtils.unmarshallString(aInfo.layerAnimationPath));
			info.setPromotionPrereq(JaxbUtils.unmarshallString(aInfo.promotionPrereq));

			if (CollectionUtils.hasElements(aInfo.prereqOrPromotions)) {
				for (String val: aInfo.prereqOrPromotions) {
					if (StringUtils.hasCharacters(val)) {
						info.addPrereqOrPromotion(JaxbUtils.unmarshallString(val));
					}
				}
			}
			info.setTechPrereq(JaxbUtils.unmarshallString(aInfo.techPrereq));
			info.setObsoleteTech(JaxbUtils.unmarshallString(aInfo.obsoleteTech));
			info.setStateReligionPrereq(JaxbUtils.unmarshallString(aInfo.stateReligionPrereq));

			if (CollectionUtils.hasElements(aInfo.seeInvisibles)) {
				for (String val: aInfo.seeInvisibles) {
					if (StringUtils.hasCharacters(val)) {
						info.addSeeInvisible(JaxbUtils.unmarshallString(val));
					}
				}
			}
			info.setCityPrereq(JaxbUtils.unmarshallBoolean(aInfo.cityPrereq));
			info.setPromotionGroup(JaxbUtils.unmarshallInteger(aInfo.promotionGroup));
			info.setLeader(JaxbUtils.unmarshallBoolean(aInfo.leader));
			info.setBlitz(JaxbUtils.unmarshallBoolean(aInfo.blitz));
			info.setAmphib(JaxbUtils.unmarshallBoolean(aInfo.amphib));
			info.setRiver(JaxbUtils.unmarshallBoolean(aInfo.river));
			info.setEnemyRoute(JaxbUtils.unmarshallBoolean(aInfo.enemyRoute));
			info.setAlwaysHeal(JaxbUtils.unmarshallBoolean(aInfo.alwaysHeal));
			info.setHillsDoubleMove(JaxbUtils.unmarshallBoolean(aInfo.hillsDoubleMove));
			info.setCanMovePeaks(JaxbUtils.unmarshallBoolean(aInfo.canMovePeaks));
			info.setImmuneToFirstStrikes(JaxbUtils.unmarshallBoolean(aInfo.immuneToFirstStrikes));
			info.setLoyal(JaxbUtils.unmarshallBoolean(aInfo.loyal));
			info.setSpyRadiation(JaxbUtils.unmarshallBoolean(aInfo.spyRadiation));
			info.setCarryReligion(JaxbUtils.unmarshallBoolean(aInfo.carryReligion));
			info.setEnslaveCountChange(JaxbUtils.unmarshallInteger(aInfo.enslaveCountChange));
			info.setVisibilityChange(JaxbUtils.unmarshallInteger(aInfo.visibilityChange));
			info.setMovesChange(JaxbUtils.unmarshallInteger(aInfo.movesChange));
			info.setMoveDiscountChange(JaxbUtils.unmarshallInteger(aInfo.moveDiscountChange));
			info.setAirRangeChange(JaxbUtils.unmarshallInteger(aInfo.airRangeChange));
			info.setInterceptChange(JaxbUtils.unmarshallInteger(aInfo.interceptChange));
			info.setEvasionChange(JaxbUtils.unmarshallInteger(aInfo.evasionChange));
			info.setWithdrawalChange(JaxbUtils.unmarshallInteger(aInfo.withdrawalChange));
			info.setCargoChange(JaxbUtils.unmarshallInteger(aInfo.cargoChange));
			info.setCollateralDamageChange(JaxbUtils.unmarshallInteger(aInfo.collateralDamageChange));
			info.setBombardRateChange(JaxbUtils.unmarshallInteger(aInfo.bombardRateChange));
			info.setFirstStrikesChange(JaxbUtils.unmarshallInteger(aInfo.firstStrikesChange));
			info.setChanceFirstStrikesChange(JaxbUtils.unmarshallInteger(aInfo.chanceFirstStrikesChange));
			info.setEnemyHealChange(JaxbUtils.unmarshallInteger(aInfo.enemyHealChange));
			info.setNeutralHealChange(JaxbUtils.unmarshallInteger(aInfo.neutralHealChange));
			info.setFriendlyHealChange(JaxbUtils.unmarshallInteger(aInfo.friendlyHealChange));
			info.setSameTileHealChange(JaxbUtils.unmarshallInteger(aInfo.sameTileHealChange));
			info.setAdjacentTileHealChange(JaxbUtils.unmarshallInteger(aInfo.adjacentTileHealChange));
			info.setCombatPercent(JaxbUtils.unmarshallInteger(aInfo.combatPercent));
			info.setCityAttack(JaxbUtils.unmarshallInteger(aInfo.cityAttack));
			info.setCityDefense(JaxbUtils.unmarshallInteger(aInfo.cityDefense));
			info.setHillsAttack(JaxbUtils.unmarshallInteger(aInfo.hillsAttack));
			info.setHillsDefense(JaxbUtils.unmarshallInteger(aInfo.hillsDefense));
			info.setKamikazePercent(JaxbUtils.unmarshallInteger(aInfo.kamikazePercent));
			info.setSpyPreparationModifier(JaxbUtils.unmarshallInteger(aInfo.spyPreparationModifier));
			info.setSpyPoisonModifier(JaxbUtils.unmarshallInteger(aInfo.spyPoisonModifier));
			info.setSpyRevoltChange(JaxbUtils.unmarshallInteger(aInfo.spyRevoltChange));
			info.setSpyUnhappyChange(JaxbUtils.unmarshallInteger(aInfo.spyUnhappyChange));
			info.setSpyInterceptChange(JaxbUtils.unmarshallInteger(aInfo.spyInterceptChange));
			info.setSpyEvasionChange(JaxbUtils.unmarshallInteger(aInfo.spyEvasionChange));
			info.setSpyDiploPenaltyChange(JaxbUtils.unmarshallInteger(aInfo.spyDiploPenaltyChange));
			info.setSpyEscapeChange(JaxbUtils.unmarshallInteger(aInfo.spyEscapeChange));
			info.setSpyNukeCityChange(JaxbUtils.unmarshallInteger(aInfo.spyNukeCityChange));
			info.setSpyDisablePowerChange(JaxbUtils.unmarshallInteger(aInfo.spyDisablePowerChange));
			info.setSpyWarWearinessChange(JaxbUtils.unmarshallInteger(aInfo.spyWarWearinessChange));
			info.setSpyResearchSabotageChange(JaxbUtils.unmarshallInteger(aInfo.spyResearchSabotageChange));
			info.setSpyReligionRemovalChange(JaxbUtils.unmarshallInteger(aInfo.spyReligionRemovalChange));
			info.setSpyCorporationRemovalChange(JaxbUtils.unmarshallInteger(aInfo.spyCorporationRemovalChange));
			info.setSpyStealTreasuryChange(JaxbUtils.unmarshallInteger(aInfo.spyStealTreasuryChange));
			info.setSpyBuyTechChange(JaxbUtils.unmarshallInteger(aInfo.spyBuyTechChange));
			info.setSpySwitchCivicChange(JaxbUtils.unmarshallInteger(aInfo.spySwitchCivicChange));
			info.setSpySwitchReligionChange(JaxbUtils.unmarshallInteger(aInfo.spySwitchReligionChange));
			info.setSpyDestroyProjectChange(JaxbUtils.unmarshallInteger(aInfo.spyDestroyProjectChange));
			info.setSpyDestroyBuildingChange(JaxbUtils.unmarshallInteger(aInfo.spyDestroyBuildingChange));
			info.setSpyDestroyImprovementChange(JaxbUtils.unmarshallInteger(aInfo.spyDestroyImprovementChange));
			info.setSpyDestroyProductionChange(JaxbUtils.unmarshallInteger(aInfo.spyDestroyProductionChange));
			info.setSpyCultureChange(JaxbUtils.unmarshallInteger(aInfo.spyCultureChange));
			info.setRevoltProtection(JaxbUtils.unmarshallInteger(aInfo.revoltProtection));
			info.setCollateralDamageProtection(JaxbUtils.unmarshallInteger(aInfo.collateralDamageProtection));
			info.setPillageChange(JaxbUtils.unmarshallInteger(aInfo.pillageChange));
			info.setPlunderChange(JaxbUtils.unmarshallInteger(aInfo.plunderChange));
			info.setExtraMorale(JaxbUtils.unmarshallInteger(aInfo.extraMorale));
			info.setEnemyMoraleModifier(JaxbUtils.unmarshallInteger(aInfo.enemyMoraleModifier));
			info.setUpgradeDiscount(JaxbUtils.unmarshallInteger(aInfo.upgradeDiscount));
			info.setExperiencePercent(JaxbUtils.unmarshallInteger(aInfo.experiencePercent));
			info.setWorkRateModifier(JaxbUtils.unmarshallInteger(aInfo.workRateModifier));
			info.setUnitRangeUnbound(JaxbUtils.unmarshallBoolean(aInfo.unitRangeUnbound));
			info.setUnitTerritoryUnbound(JaxbUtils.unmarshallBoolean(aInfo.unitTerritoryUnbound));
			info.setUnitRangeChange(JaxbUtils.unmarshallInteger(aInfo.unitRangeChange));
			info.setUnitRangeModifier(JaxbUtils.unmarshallInteger(aInfo.unitRangeModifier));

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

			if (CollectionUtils.hasElements(aInfo.unitCombatMods)) {
				for (AdaptedUnitCombatMods adaptor: aInfo.unitCombatMods) {
					if (StringUtils.hasCharacters(adaptor.unitCombatType)) {
						info.addUnitCombatMod(new Pair<String, Integer>(adaptor.unitCombatType, adaptor.iUnitCombatMod));
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

			if (CollectionUtils.hasElements(aInfo.terrainDoubleMoves)) {
				for (String val: aInfo.terrainDoubleMoves) {
					if (StringUtils.hasCharacters(val)) {
						info.addTerrainDoubleMove(JaxbUtils.unmarshallString(val));
					}
				}
			}

			if (CollectionUtils.hasElements(aInfo.featureDoubleMoves)) {
				for (String val: aInfo.featureDoubleMoves) {
					if (StringUtils.hasCharacters(val)) {
						info.addFeatureDoubleMove(JaxbUtils.unmarshallString(val));
					}
				}
			}

			if (CollectionUtils.hasElements(aInfo.buildLeaveFeatures)) {
				for (AdaptedBuildLeaveFeatures adaptor: aInfo.buildLeaveFeatures) {
					if (StringUtils.hasCharacters(adaptor.buildType)) {
						info.addBuildLeaveFeature(new Pair<String, String>(adaptor.buildType, adaptor.featureType));
					}
				}
			}

			if (CollectionUtils.hasElements(aInfo.notUnitCombatTypes)) {
				for (String val: aInfo.notUnitCombatTypes) {
					if (StringUtils.hasCharacters(val)) {
						info.addNotUnitCombatType(JaxbUtils.unmarshallString(val));
					}
				}
			}

			if (CollectionUtils.hasElements(aInfo.orUnitCombatTypes)) {
				for (String val: aInfo.orUnitCombatTypes) {
					if (StringUtils.hasCharacters(val)) {
						info.addOrUnitCombatType(JaxbUtils.unmarshallString(val));
					}
				}
			}
			info.setHotKey(JaxbUtils.unmarshallString(aInfo.hotKey));
			info.setAltDown(JaxbUtils.unmarshallBoolean(aInfo.altDown));
			info.setShiftDown(JaxbUtils.unmarshallBoolean(aInfo.shiftDown));
			info.setCtrlDown(JaxbUtils.unmarshallBoolean(aInfo.ctrlDown));
			info.setHotKeyPriority(JaxbUtils.unmarshallInteger(aInfo.hotKeyPriority));
			info.setButton(JaxbUtils.unmarshallString(aInfo.button));
			info.setOrderPriority(JaxbUtils.unmarshallInteger(aInfo.orderPriority));

			map.put(aInfo.type, info);
		}
		return map;
	}

	@Override
	public PromotionMap marshal(Map<String, IPromotionInfo> v) throws Exception {
		PromotionMap map = new PromotionMap();
		for (IPromotionInfo info: v.values()) {
			AdaptedPromotion aInfo = new AdaptedPromotion();
			aInfo.type = JaxbUtils.marshallString(info.getType());
			aInfo.description = JaxbUtils.marshallString(info.getDescription());
			aInfo.help = JaxbUtils.marshallString(info.getHelp());
			aInfo.sound = JaxbUtils.marshallString(info.getSound());
			aInfo.layerAnimationPath = JaxbUtils.marshallString(info.getLayerAnimationPath());
			aInfo.promotionPrereq = JaxbUtils.marshallString(info.getPromotionPrereq());

			if (CollectionUtils.hasElements(info.getPrereqOrPromotions())) {
				aInfo.prereqOrPromotions = new ArrayList<String>();
				for(String val: info.getPrereqOrPromotions()) {
					aInfo.prereqOrPromotions.add(JaxbUtils.marshallMandatoryString(val));
				}
			}
			aInfo.techPrereq = JaxbUtils.marshallString(info.getTechPrereq());
			aInfo.obsoleteTech = JaxbUtils.marshallString(info.getObsoleteTech());
			aInfo.stateReligionPrereq = JaxbUtils.marshallString(info.getStateReligionPrereq());

			if (CollectionUtils.hasElements(info.getSeeInvisibles())) {
				aInfo.seeInvisibles = new ArrayList<String>();
				for(String val: info.getSeeInvisibles()) {
					aInfo.seeInvisibles.add(JaxbUtils.marshallMandatoryString(val));
				}
			}
			aInfo.cityPrereq = JaxbUtils.marshallBoolean(info.isCityPrereq());
			aInfo.promotionGroup = JaxbUtils.marshallInteger(info.getPromotionGroup());
			aInfo.leader = JaxbUtils.marshallBoolean(info.isLeader());
			aInfo.blitz = JaxbUtils.marshallBoolean(info.isBlitz());
			aInfo.amphib = JaxbUtils.marshallBoolean(info.isAmphib());
			aInfo.river = JaxbUtils.marshallBoolean(info.isRiver());
			aInfo.enemyRoute = JaxbUtils.marshallBoolean(info.isEnemyRoute());
			aInfo.alwaysHeal = JaxbUtils.marshallBoolean(info.isAlwaysHeal());
			aInfo.hillsDoubleMove = JaxbUtils.marshallBoolean(info.isHillsDoubleMove());
			aInfo.canMovePeaks = JaxbUtils.marshallBoolean(info.isCanMovePeaks());
			aInfo.immuneToFirstStrikes = JaxbUtils.marshallBoolean(info.isImmuneToFirstStrikes());
			aInfo.loyal = JaxbUtils.marshallBoolean(info.isLoyal());
			aInfo.spyRadiation = JaxbUtils.marshallBoolean(info.isSpyRadiation());
			aInfo.carryReligion = JaxbUtils.marshallBoolean(info.isCarryReligion());
			aInfo.enslaveCountChange = JaxbUtils.marshallInteger(info.getEnslaveCountChange());
			aInfo.visibilityChange = JaxbUtils.marshallInteger(info.getVisibilityChange());
			aInfo.movesChange = JaxbUtils.marshallInteger(info.getMovesChange());
			aInfo.moveDiscountChange = JaxbUtils.marshallInteger(info.getMoveDiscountChange());
			aInfo.airRangeChange = JaxbUtils.marshallInteger(info.getAirRangeChange());
			aInfo.interceptChange = JaxbUtils.marshallInteger(info.getInterceptChange());
			aInfo.evasionChange = JaxbUtils.marshallInteger(info.getEvasionChange());
			aInfo.withdrawalChange = JaxbUtils.marshallInteger(info.getWithdrawalChange());
			aInfo.cargoChange = JaxbUtils.marshallInteger(info.getCargoChange());
			aInfo.collateralDamageChange = JaxbUtils.marshallInteger(info.getCollateralDamageChange());
			aInfo.bombardRateChange = JaxbUtils.marshallInteger(info.getBombardRateChange());
			aInfo.firstStrikesChange = JaxbUtils.marshallInteger(info.getFirstStrikesChange());
			aInfo.chanceFirstStrikesChange = JaxbUtils.marshallInteger(info.getChanceFirstStrikesChange());
			aInfo.enemyHealChange = JaxbUtils.marshallInteger(info.getEnemyHealChange());
			aInfo.neutralHealChange = JaxbUtils.marshallInteger(info.getNeutralHealChange());
			aInfo.friendlyHealChange = JaxbUtils.marshallInteger(info.getFriendlyHealChange());
			aInfo.sameTileHealChange = JaxbUtils.marshallInteger(info.getSameTileHealChange());
			aInfo.adjacentTileHealChange = JaxbUtils.marshallInteger(info.getAdjacentTileHealChange());
			aInfo.combatPercent = JaxbUtils.marshallInteger(info.getCombatPercent());
			aInfo.cityAttack = JaxbUtils.marshallInteger(info.getCityAttack());
			aInfo.cityDefense = JaxbUtils.marshallInteger(info.getCityDefense());
			aInfo.hillsAttack = JaxbUtils.marshallInteger(info.getHillsAttack());
			aInfo.hillsDefense = JaxbUtils.marshallInteger(info.getHillsDefense());
			aInfo.kamikazePercent = JaxbUtils.marshallInteger(info.getKamikazePercent());
			aInfo.spyPreparationModifier = JaxbUtils.marshallInteger(info.getSpyPreparationModifier());
			aInfo.spyPoisonModifier = JaxbUtils.marshallInteger(info.getSpyPoisonModifier());
			aInfo.spyRevoltChange = JaxbUtils.marshallInteger(info.getSpyRevoltChange());
			aInfo.spyUnhappyChange = JaxbUtils.marshallInteger(info.getSpyUnhappyChange());
			aInfo.spyInterceptChange = JaxbUtils.marshallInteger(info.getSpyInterceptChange());
			aInfo.spyEvasionChange = JaxbUtils.marshallInteger(info.getSpyEvasionChange());
			aInfo.spyDiploPenaltyChange = JaxbUtils.marshallInteger(info.getSpyDiploPenaltyChange());
			aInfo.spyEscapeChange = JaxbUtils.marshallInteger(info.getSpyEscapeChange());
			aInfo.spyNukeCityChange = JaxbUtils.marshallInteger(info.getSpyNukeCityChange());
			aInfo.spyDisablePowerChange = JaxbUtils.marshallInteger(info.getSpyDisablePowerChange());
			aInfo.spyWarWearinessChange = JaxbUtils.marshallInteger(info.getSpyWarWearinessChange());
			aInfo.spyResearchSabotageChange = JaxbUtils.marshallInteger(info.getSpyResearchSabotageChange());
			aInfo.spyReligionRemovalChange = JaxbUtils.marshallInteger(info.getSpyReligionRemovalChange());
			aInfo.spyCorporationRemovalChange = JaxbUtils.marshallInteger(info.getSpyCorporationRemovalChange());
			aInfo.spyStealTreasuryChange = JaxbUtils.marshallInteger(info.getSpyStealTreasuryChange());
			aInfo.spyBuyTechChange = JaxbUtils.marshallInteger(info.getSpyBuyTechChange());
			aInfo.spySwitchCivicChange = JaxbUtils.marshallInteger(info.getSpySwitchCivicChange());
			aInfo.spySwitchReligionChange = JaxbUtils.marshallInteger(info.getSpySwitchReligionChange());
			aInfo.spyDestroyProjectChange = JaxbUtils.marshallInteger(info.getSpyDestroyProjectChange());
			aInfo.spyDestroyBuildingChange = JaxbUtils.marshallInteger(info.getSpyDestroyBuildingChange());
			aInfo.spyDestroyImprovementChange = JaxbUtils.marshallInteger(info.getSpyDestroyImprovementChange());
			aInfo.spyDestroyProductionChange = JaxbUtils.marshallInteger(info.getSpyDestroyProductionChange());
			aInfo.spyCultureChange = JaxbUtils.marshallInteger(info.getSpyCultureChange());
			aInfo.revoltProtection = JaxbUtils.marshallInteger(info.getRevoltProtection());
			aInfo.collateralDamageProtection = JaxbUtils.marshallInteger(info.getCollateralDamageProtection());
			aInfo.pillageChange = JaxbUtils.marshallInteger(info.getPillageChange());
			aInfo.plunderChange = JaxbUtils.marshallInteger(info.getPlunderChange());
			aInfo.extraMorale = JaxbUtils.marshallInteger(info.getExtraMorale());
			aInfo.enemyMoraleModifier = JaxbUtils.marshallInteger(info.getEnemyMoraleModifier());
			aInfo.upgradeDiscount = JaxbUtils.marshallInteger(info.getUpgradeDiscount());
			aInfo.experiencePercent = JaxbUtils.marshallInteger(info.getExperiencePercent());
			aInfo.workRateModifier = JaxbUtils.marshallInteger(info.getWorkRateModifier());
			aInfo.unitRangeUnbound = JaxbUtils.marshallBoolean(info.isUnitRangeUnbound());
			aInfo.unitTerritoryUnbound = JaxbUtils.marshallBoolean(info.isUnitTerritoryUnbound());
			aInfo.unitRangeChange = JaxbUtils.marshallInteger(info.getUnitRangeChange());
			aInfo.unitRangeModifier = JaxbUtils.marshallInteger(info.getUnitRangeModifier());

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

			if (CollectionUtils.hasElements(info.getUnitCombatMods())) {
				aInfo.unitCombatMods = new ArrayList<AdaptedUnitCombatMods>();
				for (IPair<String, Integer> pair: info.getUnitCombatMods()) {
					AdaptedUnitCombatMods adaptor = new AdaptedUnitCombatMods();
					adaptor.unitCombatType = pair.getKey();
					adaptor.iUnitCombatMod = pair.getValue();
					aInfo.unitCombatMods.add(adaptor);
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

			if (CollectionUtils.hasElements(info.getTerrainDoubleMoves())) {
				aInfo.terrainDoubleMoves = new ArrayList<String>();
				for(String val: info.getTerrainDoubleMoves()) {
					aInfo.terrainDoubleMoves.add(JaxbUtils.marshallMandatoryString(val));
				}
			}

			if (CollectionUtils.hasElements(info.getFeatureDoubleMoves())) {
				aInfo.featureDoubleMoves = new ArrayList<String>();
				for(String val: info.getFeatureDoubleMoves()) {
					aInfo.featureDoubleMoves.add(JaxbUtils.marshallMandatoryString(val));
				}
			}

			if (CollectionUtils.hasElements(info.getBuildLeaveFeatures())) {
				aInfo.buildLeaveFeatures = new ArrayList<AdaptedBuildLeaveFeatures>();
				for (IPair<String, String> pair: info.getBuildLeaveFeatures()) {
					AdaptedBuildLeaveFeatures adaptor = new AdaptedBuildLeaveFeatures();
					adaptor.buildType = pair.getKey();
					adaptor.featureType = pair.getValue();
					aInfo.buildLeaveFeatures.add(adaptor);
				}
			}

			if (CollectionUtils.hasElements(info.getNotUnitCombatTypes())) {
				aInfo.notUnitCombatTypes = new ArrayList<String>();
				for(String val: info.getNotUnitCombatTypes()) {
					aInfo.notUnitCombatTypes.add(JaxbUtils.marshallMandatoryString(val));
				}
			}

			if (CollectionUtils.hasElements(info.getOrUnitCombatTypes())) {
				aInfo.orUnitCombatTypes = new ArrayList<String>();
				for(String val: info.getOrUnitCombatTypes()) {
					aInfo.orUnitCombatTypes.add(JaxbUtils.marshallMandatoryString(val));
				}
			}
			aInfo.hotKey = JaxbUtils.marshallString(info.getHotKey());
			aInfo.altDown = JaxbUtils.marshallBoolean(info.isAltDown());
			aInfo.shiftDown = JaxbUtils.marshallBoolean(info.isShiftDown());
			aInfo.ctrlDown = JaxbUtils.marshallBoolean(info.isCtrlDown());
			aInfo.hotKeyPriority = JaxbUtils.marshallInteger(info.getHotKeyPriority());
			aInfo.button = JaxbUtils.marshallString(info.getButton());
			aInfo.orderPriority = JaxbUtils.marshallInteger(info.getOrderPriority());

			map.entries.add(aInfo);
		}
		return map;
	}
}