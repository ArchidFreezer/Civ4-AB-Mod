package org.archid.civ4.info.promotion;

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

@XmlRootElement(name="Civ4PromotionInfos", namespace="x-schema:CIV4UnitSchema.xml")
@XmlAccessorType(XmlAccessType.NONE)
public class PromotionInfos extends AbstractInfos<IPromotionInfo> {

	public PromotionInfos() {
		super(new LinkedHashMap<String, IPromotionInfo>());
	}

	@XmlJavaTypeAdapter(PromotionMapAdapter.class)
	@XmlElement(name="PromotionInfos")
	public Map<String, IPromotionInfo> getInfoMap() {
		return infos;
	}

	public static IPromotionInfo createInfo(String type) {
		return new PromotionInfo(type);
	}

	@Override
	public void setInfoMap(Map<String, IPromotionInfo> infos) {
		this.infos = infos;
	}

	private static class PromotionInfo implements IPromotionInfo{

		private String type;
		private String description;
		private String help;
		private String sound;
		private String layerAnimationPath;
		private String promotionPrereq;
		private List<String> prereqOrPromotions = new ArrayList<String>();
		private String techPrereq;
		private String obsoleteTech;
		private String stateReligionPrereq;
		private List<String> seeInvisibles = new ArrayList<String>();
		private Boolean cityPrereq;
		private Integer promotionGroup;
		private Boolean leader;
		private Boolean blitz;
		private Boolean amphib;
		private Boolean river;
		private Boolean enemyRoute;
		private Boolean alwaysHeal;
		private Boolean hillsDoubleMove;
		private Boolean canMovePeaks;
		private Boolean immuneToFirstStrikes;
		private Boolean loyal;
		private Boolean spyRadiation;
		private Boolean carryReligion;
		private Integer enslaveCountChange;
		private Integer visibilityChange;
		private Integer movesChange;
		private Integer moveDiscountChange;
		private Integer airRangeChange;
		private Integer interceptChange;
		private Integer evasionChange;
		private Integer withdrawalChange;
		private Integer cargoChange;
		private Integer collateralDamageChange;
		private Integer bombardRateChange;
		private Integer firstStrikesChange;
		private Integer chanceFirstStrikesChange;
		private Integer enemyHealChange;
		private Integer neutralHealChange;
		private Integer friendlyHealChange;
		private Integer sameTileHealChange;
		private Integer adjacentTileHealChange;
		private Integer combatPercent;
		private Integer cityAttack;
		private Integer cityDefense;
		private Integer hillsAttack;
		private Integer hillsDefense;
		private Integer kamikazePercent;
		private Integer spyPreparationModifier;
		private Integer spyPoisonModifier;
		private Integer spyRevoltChange;
		private Integer spyUnhappyChange;
		private Integer spyInterceptChange;
		private Integer spyEvasionChange;
		private Integer spyDiploPenaltyChange;
		private Integer spyEscapeChange;
		private Integer spyNukeCityChange;
		private Integer spyDisablePowerChange;
		private Integer spyWarWearinessChange;
		private Integer spyResearchSabotageChange;
		private Integer spyReligionRemovalChange;
		private Integer spyCorporationRemovalChange;
		private Integer spyStealTreasuryChange;
		private Integer spyBuyTechChange;
		private Integer spySwitchCivicChange;
		private Integer spySwitchReligionChange;
		private Integer spyDestroyProjectChange;
		private Integer spyDestroyBuildingChange;
		private Integer spyDestroyImprovementChange;
		private Integer spyDestroyProductionChange;
		private Integer spyCultureChange;
		private Integer revoltProtection;
		private Integer collateralDamageProtection;
		private Integer pillageChange;
		private Integer plunderChange;
		private Integer extraMorale;
		private Integer enemyMoraleModifier;
		private Integer upgradeDiscount;
		private Integer experiencePercent;
		private Integer workRateModifier;
		private Boolean unitRangeUnbound;
		private Boolean unitTerritoryUnbound;
		private Integer unitRangeChange;
		private Integer unitRangeModifier;
		private List<IPair<String, Integer>> terrainAttacks = new ArrayList<IPair<String, Integer>>();
		private List<IPair<String, Integer>> terrainDefenses = new ArrayList<IPair<String, Integer>>();
		private List<IPair<String, Integer>> featureAttacks = new ArrayList<IPair<String, Integer>>();
		private List<IPair<String, Integer>> featureDefenses = new ArrayList<IPair<String, Integer>>();
		private List<IPair<String, Integer>> unitCombatMods = new ArrayList<IPair<String, Integer>>();
		private List<IPair<String, Integer>> domainMods = new ArrayList<IPair<String, Integer>>();
		private List<String> terrainDoubleMoves = new ArrayList<String>();
		private List<String> featureDoubleMoves = new ArrayList<String>();
		private List<IPair<String, String>> buildLeaveFeatures = new ArrayList<IPair<String, String>>();
		private List<String> notUnitCombatTypes = new ArrayList<String>();
		private List<String> orUnitCombatTypes = new ArrayList<String>();
		private String hotKey;
		private Boolean altDown;
		private Boolean shiftDown;
		private Boolean ctrlDown;
		private Integer hotKeyPriority;
		private String button;
		private Integer orderPriority;

		private PromotionInfo(String type) {
			this.type = type;
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
		public String getDescription() {
			return description;
		}

		@Override
		public void setDescription(String description) {
			this.description = description;
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
		public String getSound() {
			return sound;
		}

		@Override
		public void setSound(String sound) {
			this.sound = sound;
		}

		@Override
		public String getLayerAnimationPath() {
			return layerAnimationPath;
		}

		@Override
		public void setLayerAnimationPath(String layerAnimationPath) {
			this.layerAnimationPath = layerAnimationPath;
		}

		@Override
		public String getPromotionPrereq() {
			return promotionPrereq;
		}

		@Override
		public void setPromotionPrereq(String promotionPrereq) {
			this.promotionPrereq = promotionPrereq;
		}

		@Override
		public List<String> getPrereqOrPromotions() {
			return prereqOrPromotions;
		}

		@Override
		public void addPrereqOrPromotion(String prereqOrPromotion) {
			this.prereqOrPromotions.add(prereqOrPromotion);
		}

		@Override
		public String getTechPrereq() {
			return techPrereq;
		}

		@Override
		public void setTechPrereq(String techPrereq) {
			this.techPrereq = techPrereq;
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
		public String getStateReligionPrereq() {
			return stateReligionPrereq;
		}

		@Override
		public void setStateReligionPrereq(String stateReligionPrereq) {
			this.stateReligionPrereq = stateReligionPrereq;
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
		public Boolean isCityPrereq() {
			return cityPrereq;
		}

		@Override
		public void setCityPrereq(Boolean cityPrereq) {
			this.cityPrereq = cityPrereq;
		}

		@Override
		public Integer getPromotionGroup() {
			return promotionGroup;
		}

		@Override
		public void setPromotionGroup(Integer promotionGroup) {
			this.promotionGroup = promotionGroup;
		}

		@Override
		public Boolean isLeader() {
			return leader;
		}

		@Override
		public void setLeader(Boolean leader) {
			this.leader = leader;
		}

		@Override
		public Boolean isBlitz() {
			return blitz;
		}

		@Override
		public void setBlitz(Boolean blitz) {
			this.blitz = blitz;
		}

		@Override
		public Boolean isAmphib() {
			return amphib;
		}

		@Override
		public void setAmphib(Boolean amphib) {
			this.amphib = amphib;
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
		public Boolean isEnemyRoute() {
			return enemyRoute;
		}

		@Override
		public void setEnemyRoute(Boolean enemyRoute) {
			this.enemyRoute = enemyRoute;
		}

		@Override
		public Boolean isAlwaysHeal() {
			return alwaysHeal;
		}

		@Override
		public void setAlwaysHeal(Boolean alwaysHeal) {
			this.alwaysHeal = alwaysHeal;
		}

		@Override
		public Boolean isHillsDoubleMove() {
			return hillsDoubleMove;
		}

		@Override
		public void setHillsDoubleMove(Boolean hillsDoubleMove) {
			this.hillsDoubleMove = hillsDoubleMove;
		}

		@Override
		public Boolean isCanMovePeaks() {
			return canMovePeaks;
		}

		@Override
		public void setCanMovePeaks(Boolean canMovePeaks) {
			this.canMovePeaks = canMovePeaks;
		}

		@Override
		public Boolean isImmuneToFirstStrikes() {
			return immuneToFirstStrikes;
		}

		@Override
		public void setImmuneToFirstStrikes(Boolean immuneToFirstStrikes) {
			this.immuneToFirstStrikes = immuneToFirstStrikes;
		}

		@Override
		public Boolean isLoyal() {
			return loyal;
		}

		@Override
		public void setLoyal(Boolean loyal) {
			this.loyal = loyal;
		}

		@Override
		public Boolean isSpyRadiation() {
			return spyRadiation;
		}

		@Override
		public void setSpyRadiation(Boolean spyRadiation) {
			this.spyRadiation = spyRadiation;
		}

		@Override
		public Boolean isCarryReligion() {
			return carryReligion;
		}

		@Override
		public void setCarryReligion(Boolean carryReligion) {
			this.carryReligion = carryReligion;
		}

		@Override
		public Integer getEnslaveCountChange() {
			return enslaveCountChange;
		}

		@Override
		public void setEnslaveCountChange(Integer enslaveCountChange) {
			this.enslaveCountChange = enslaveCountChange;
		}

		@Override
		public Integer getVisibilityChange() {
			return visibilityChange;
		}

		@Override
		public void setVisibilityChange(Integer visibilityChange) {
			this.visibilityChange = visibilityChange;
		}

		@Override
		public Integer getMovesChange() {
			return movesChange;
		}

		@Override
		public void setMovesChange(Integer movesChange) {
			this.movesChange = movesChange;
		}

		@Override
		public Integer getMoveDiscountChange() {
			return moveDiscountChange;
		}

		@Override
		public void setMoveDiscountChange(Integer moveDiscountChange) {
			this.moveDiscountChange = moveDiscountChange;
		}

		@Override
		public Integer getAirRangeChange() {
			return airRangeChange;
		}

		@Override
		public void setAirRangeChange(Integer airRangeChange) {
			this.airRangeChange = airRangeChange;
		}

		@Override
		public Integer getInterceptChange() {
			return interceptChange;
		}

		@Override
		public void setInterceptChange(Integer interceptChange) {
			this.interceptChange = interceptChange;
		}

		@Override
		public Integer getEvasionChange() {
			return evasionChange;
		}

		@Override
		public void setEvasionChange(Integer evasionChange) {
			this.evasionChange = evasionChange;
		}

		@Override
		public Integer getWithdrawalChange() {
			return withdrawalChange;
		}

		@Override
		public void setWithdrawalChange(Integer withdrawalChange) {
			this.withdrawalChange = withdrawalChange;
		}

		@Override
		public Integer getCargoChange() {
			return cargoChange;
		}

		@Override
		public void setCargoChange(Integer cargoChange) {
			this.cargoChange = cargoChange;
		}

		@Override
		public Integer getCollateralDamageChange() {
			return collateralDamageChange;
		}

		@Override
		public void setCollateralDamageChange(Integer collateralDamageChange) {
			this.collateralDamageChange = collateralDamageChange;
		}

		@Override
		public Integer getBombardRateChange() {
			return bombardRateChange;
		}

		@Override
		public void setBombardRateChange(Integer bombardRateChange) {
			this.bombardRateChange = bombardRateChange;
		}

		@Override
		public Integer getFirstStrikesChange() {
			return firstStrikesChange;
		}

		@Override
		public void setFirstStrikesChange(Integer firstStrikesChange) {
			this.firstStrikesChange = firstStrikesChange;
		}

		@Override
		public Integer getChanceFirstStrikesChange() {
			return chanceFirstStrikesChange;
		}

		@Override
		public void setChanceFirstStrikesChange(Integer chanceFirstStrikesChange) {
			this.chanceFirstStrikesChange = chanceFirstStrikesChange;
		}

		@Override
		public Integer getEnemyHealChange() {
			return enemyHealChange;
		}

		@Override
		public void setEnemyHealChange(Integer enemyHealChange) {
			this.enemyHealChange = enemyHealChange;
		}

		@Override
		public Integer getNeutralHealChange() {
			return neutralHealChange;
		}

		@Override
		public void setNeutralHealChange(Integer neutralHealChange) {
			this.neutralHealChange = neutralHealChange;
		}

		@Override
		public Integer getFriendlyHealChange() {
			return friendlyHealChange;
		}

		@Override
		public void setFriendlyHealChange(Integer friendlyHealChange) {
			this.friendlyHealChange = friendlyHealChange;
		}

		@Override
		public Integer getSameTileHealChange() {
			return sameTileHealChange;
		}

		@Override
		public void setSameTileHealChange(Integer sameTileHealChange) {
			this.sameTileHealChange = sameTileHealChange;
		}

		@Override
		public Integer getAdjacentTileHealChange() {
			return adjacentTileHealChange;
		}

		@Override
		public void setAdjacentTileHealChange(Integer adjacentTileHealChange) {
			this.adjacentTileHealChange = adjacentTileHealChange;
		}

		@Override
		public Integer getCombatPercent() {
			return combatPercent;
		}

		@Override
		public void setCombatPercent(Integer combatPercent) {
			this.combatPercent = combatPercent;
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
		public Integer getKamikazePercent() {
			return kamikazePercent;
		}

		@Override
		public void setKamikazePercent(Integer kamikazePercent) {
			this.kamikazePercent = kamikazePercent;
		}

		@Override
		public Integer getSpyPreparationModifier() {
			return spyPreparationModifier;
		}

		@Override
		public void setSpyPreparationModifier(Integer spyPreparationModifier) {
			this.spyPreparationModifier = spyPreparationModifier;
		}

		@Override
		public Integer getSpyPoisonModifier() {
			return spyPoisonModifier;
		}

		@Override
		public void setSpyPoisonModifier(Integer spyPoisonModifier) {
			this.spyPoisonModifier = spyPoisonModifier;
		}

		@Override
		public Integer getSpyRevoltChange() {
			return spyRevoltChange;
		}

		@Override
		public void setSpyRevoltChange(Integer spyRevoltChange) {
			this.spyRevoltChange = spyRevoltChange;
		}

		@Override
		public Integer getSpyUnhappyChange() {
			return spyUnhappyChange;
		}

		@Override
		public void setSpyUnhappyChange(Integer spyUnhappyChange) {
			this.spyUnhappyChange = spyUnhappyChange;
		}

		@Override
		public Integer getSpyInterceptChange() {
			return spyInterceptChange;
		}

		@Override
		public void setSpyInterceptChange(Integer spyInterceptChange) {
			this.spyInterceptChange = spyInterceptChange;
		}

		@Override
		public Integer getSpyEvasionChange() {
			return spyEvasionChange;
		}

		@Override
		public void setSpyEvasionChange(Integer spyEvasionChange) {
			this.spyEvasionChange = spyEvasionChange;
		}

		@Override
		public Integer getSpyDiploPenaltyChange() {
			return spyDiploPenaltyChange;
		}

		@Override
		public void setSpyDiploPenaltyChange(Integer spyDiploPenaltyChange) {
			this.spyDiploPenaltyChange = spyDiploPenaltyChange;
		}

		@Override
		public Integer getSpyEscapeChange() {
			return spyEscapeChange;
		}

		@Override
		public void setSpyEscapeChange(Integer spyEscapeChange) {
			this.spyEscapeChange = spyEscapeChange;
		}

		@Override
		public Integer getSpyNukeCityChange() {
			return spyNukeCityChange;
		}

		@Override
		public void setSpyNukeCityChange(Integer spyNukeCityChange) {
			this.spyNukeCityChange = spyNukeCityChange;
		}

		@Override
		public Integer getSpyDisablePowerChange() {
			return spyDisablePowerChange;
		}

		@Override
		public void setSpyDisablePowerChange(Integer spyDisablePowerChange) {
			this.spyDisablePowerChange = spyDisablePowerChange;
		}

		@Override
		public Integer getSpyWarWearinessChange() {
			return spyWarWearinessChange;
		}

		@Override
		public void setSpyWarWearinessChange(Integer spyWarWearinessChange) {
			this.spyWarWearinessChange = spyWarWearinessChange;
		}

		@Override
		public Integer getSpyResearchSabotageChange() {
			return spyResearchSabotageChange;
		}

		@Override
		public void setSpyResearchSabotageChange(Integer spyResearchSabotageChange) {
			this.spyResearchSabotageChange = spyResearchSabotageChange;
		}

		@Override
		public Integer getSpyReligionRemovalChange() {
			return spyReligionRemovalChange;
		}

		@Override
		public void setSpyReligionRemovalChange(Integer spyReligionRemovalChange) {
			this.spyReligionRemovalChange = spyReligionRemovalChange;
		}

		@Override
		public Integer getSpyCorporationRemovalChange() {
			return spyCorporationRemovalChange;
		}

		@Override
		public void setSpyCorporationRemovalChange(Integer spyCorporationRemovalChange) {
			this.spyCorporationRemovalChange = spyCorporationRemovalChange;
		}

		@Override
		public Integer getSpyStealTreasuryChange() {
			return spyStealTreasuryChange;
		}

		@Override
		public void setSpyStealTreasuryChange(Integer spyStealTreasuryChange) {
			this.spyStealTreasuryChange = spyStealTreasuryChange;
		}

		@Override
		public Integer getSpyBuyTechChange() {
			return spyBuyTechChange;
		}

		@Override
		public void setSpyBuyTechChange(Integer spyBuyTechChange) {
			this.spyBuyTechChange = spyBuyTechChange;
		}

		@Override
		public Integer getSpySwitchCivicChange() {
			return spySwitchCivicChange;
		}

		@Override
		public void setSpySwitchCivicChange(Integer spySwitchCivicChange) {
			this.spySwitchCivicChange = spySwitchCivicChange;
		}

		@Override
		public Integer getSpySwitchReligionChange() {
			return spySwitchReligionChange;
		}

		@Override
		public void setSpySwitchReligionChange(Integer spySwitchReligionChange) {
			this.spySwitchReligionChange = spySwitchReligionChange;
		}

		@Override
		public Integer getSpyDestroyProjectChange() {
			return spyDestroyProjectChange;
		}

		@Override
		public void setSpyDestroyProjectChange(Integer spyDestroyProjectChange) {
			this.spyDestroyProjectChange = spyDestroyProjectChange;
		}

		@Override
		public Integer getSpyDestroyBuildingChange() {
			return spyDestroyBuildingChange;
		}

		@Override
		public void setSpyDestroyBuildingChange(Integer spyDestroyBuildingChange) {
			this.spyDestroyBuildingChange = spyDestroyBuildingChange;
		}

		@Override
		public Integer getSpyDestroyImprovementChange() {
			return spyDestroyImprovementChange;
		}

		@Override
		public void setSpyDestroyImprovementChange(Integer spyDestroyImprovementChange) {
			this.spyDestroyImprovementChange = spyDestroyImprovementChange;
		}

		@Override
		public Integer getSpyDestroyProductionChange() {
			return spyDestroyProductionChange;
		}

		@Override
		public void setSpyDestroyProductionChange(Integer spyDestroyProductionChange) {
			this.spyDestroyProductionChange = spyDestroyProductionChange;
		}

		@Override
		public Integer getSpyCultureChange() {
			return spyCultureChange;
		}

		@Override
		public void setSpyCultureChange(Integer spyCultureChange) {
			this.spyCultureChange = spyCultureChange;
		}

		@Override
		public Integer getRevoltProtection() {
			return revoltProtection;
		}

		@Override
		public void setRevoltProtection(Integer revoltProtection) {
			this.revoltProtection = revoltProtection;
		}

		@Override
		public Integer getCollateralDamageProtection() {
			return collateralDamageProtection;
		}

		@Override
		public void setCollateralDamageProtection(Integer collateralDamageProtection) {
			this.collateralDamageProtection = collateralDamageProtection;
		}

		@Override
		public Integer getPillageChange() {
			return pillageChange;
		}

		@Override
		public void setPillageChange(Integer pillageChange) {
			this.pillageChange = pillageChange;
		}

		@Override
		public Integer getPlunderChange() {
			return plunderChange;
		}

		@Override
		public void setPlunderChange(Integer plunderChange) {
			this.plunderChange = plunderChange;
		}

		@Override
		public Integer getExtraMorale() {
			return extraMorale;
		}

		@Override
		public void setExtraMorale(Integer extraMorale) {
			this.extraMorale = extraMorale;
		}

		@Override
		public Integer getEnemyMoraleModifier() {
			return enemyMoraleModifier;
		}

		@Override
		public void setEnemyMoraleModifier(Integer enemyMoraleModifier) {
			this.enemyMoraleModifier = enemyMoraleModifier;
		}

		@Override
		public Integer getUpgradeDiscount() {
			return upgradeDiscount;
		}

		@Override
		public void setUpgradeDiscount(Integer upgradeDiscount) {
			this.upgradeDiscount = upgradeDiscount;
		}

		@Override
		public Integer getExperiencePercent() {
			return experiencePercent;
		}

		@Override
		public void setExperiencePercent(Integer experiencePercent) {
			this.experiencePercent = experiencePercent;
		}

		@Override
		public Integer getWorkRateModifier() {
			return workRateModifier;
		}

		@Override
		public void setWorkRateModifier(Integer workRateModifier) {
			this.workRateModifier = workRateModifier;
		}

		@Override
		public Boolean isUnitRangeUnbound() {
			return unitRangeUnbound;
		}

		@Override
		public void setUnitRangeUnbound(Boolean unitRangeUnbound) {
			this.unitRangeUnbound = unitRangeUnbound;
		}

		@Override
		public Boolean isUnitTerritoryUnbound() {
			return unitTerritoryUnbound;
		}

		@Override
		public void setUnitTerritoryUnbound(Boolean unitTerritoryUnbound) {
			this.unitTerritoryUnbound = unitTerritoryUnbound;
		}

		@Override
		public Integer getUnitRangeChange() {
			return unitRangeChange;
		}

		@Override
		public void setUnitRangeChange(Integer unitRangeChange) {
			this.unitRangeChange = unitRangeChange;
		}

		@Override
		public Integer getUnitRangeModifier() {
			return unitRangeModifier;
		}

		@Override
		public void setUnitRangeModifier(Integer unitRangeModifier) {
			this.unitRangeModifier = unitRangeModifier;
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
		public List<IPair<String, Integer>> getUnitCombatMods() {
			return unitCombatMods;
		}

		@Override
		public void addUnitCombatMod(IPair<String, Integer> unitCombatMod) {
			this.unitCombatMods.add(unitCombatMod);
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
		public List<String> getTerrainDoubleMoves() {
			return terrainDoubleMoves;
		}

		@Override
		public void addTerrainDoubleMove(String terrainDoubleMove) {
			this.terrainDoubleMoves.add(terrainDoubleMove);
		}

		@Override
		public List<String> getFeatureDoubleMoves() {
			return featureDoubleMoves;
		}

		@Override
		public void addFeatureDoubleMove(String featureDoubleMove) {
			this.featureDoubleMoves.add(featureDoubleMove);
		}

		@Override
		public List<IPair<String, String>> getBuildLeaveFeatures() {
			return buildLeaveFeatures;
		}

		@Override
		public void addBuildLeaveFeature(IPair<String, String> buildLeaveFeature) {
			this.buildLeaveFeatures.add(buildLeaveFeature);
		}

		@Override
		public List<String> getNotUnitCombatTypes() {
			return notUnitCombatTypes;
		}

		@Override
		public void addNotUnitCombatType(String notUnitCombatType) {
			this.notUnitCombatTypes.add(notUnitCombatType);
		}

		@Override
		public List<String> getOrUnitCombatTypes() {
			return orUnitCombatTypes;
		}

		@Override
		public void addOrUnitCombatType(String orUnitCombatType) {
			this.orUnitCombatTypes.add(orUnitCombatType);
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
		public String getButton() {
			return button;
		}

		@Override
		public void setButton(String button) {
			this.button = button;
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