package org.archid.civ4.info.promotion;

import java.util.ArrayList;
import java.util.List;
import org.archid.civ4.info.IInfo;
import org.archid.utils.IPair;

public interface IPromotionInfo extends IInfo {
	String getDescription();
	void setDescription(String description);

	String getHelp();
	void setHelp(String help);

	String getSound();
	void setSound(String sound);

	String getLayerAnimationPath();
	void setLayerAnimationPath(String layerAnimationPath);

	String getPromotionPrereq();
	void setPromotionPrereq(String promotionPrereq);

	List<String> getPrereqOrPromotions();
	void addPrereqOrPromotion(String prereqOrPromotion);

	String getTechPrereq();
	void setTechPrereq(String techPrereq);

	String getObsoleteTech();
	void setObsoleteTech(String obsoleteTech);

	String getStateReligionPrereq();
	void setStateReligionPrereq(String stateReligionPrereq);

	List<String> getSeeInvisibles();
	void addSeeInvisible(String seeInvisible);

	Boolean isCityPrereq();
	void setCityPrereq(Boolean cityPrereq);

	Integer getPromotionGroup();
	void setPromotionGroup(Integer promotionGroup);

	Boolean isLeader();
	void setLeader(Boolean leader);

	Boolean isBlitz();
	void setBlitz(Boolean blitz);

	Boolean isAmphib();
	void setAmphib(Boolean amphib);

	Boolean isRiver();
	void setRiver(Boolean river);

	Boolean isEnemyRoute();
	void setEnemyRoute(Boolean enemyRoute);

	Boolean isAlwaysHeal();
	void setAlwaysHeal(Boolean alwaysHeal);

	Boolean isHillsDoubleMove();
	void setHillsDoubleMove(Boolean hillsDoubleMove);

	Boolean isCanMovePeaks();
	void setCanMovePeaks(Boolean canMovePeaks);

	Boolean isImmuneToFirstStrikes();
	void setImmuneToFirstStrikes(Boolean immuneToFirstStrikes);

	Boolean isLoyal();
	void setLoyal(Boolean loyal);

	Boolean isSpyRadiation();
	void setSpyRadiation(Boolean spyRadiation);

	Boolean isCarryReligion();
	void setCarryReligion(Boolean carryReligion);

	Integer getEnslaveCountChange();
	void setEnslaveCountChange(Integer enslaveCountChange);

	Integer getVisibilityChange();
	void setVisibilityChange(Integer visibilityChange);

	Integer getMovesChange();
	void setMovesChange(Integer movesChange);

	Integer getMoveDiscountChange();
	void setMoveDiscountChange(Integer moveDiscountChange);

	Integer getAirRangeChange();
	void setAirRangeChange(Integer airRangeChange);

	Integer getInterceptChange();
	void setInterceptChange(Integer interceptChange);

	Integer getEvasionChange();
	void setEvasionChange(Integer evasionChange);

	Integer getWithdrawalChange();
	void setWithdrawalChange(Integer withdrawalChange);

	Integer getCargoChange();
	void setCargoChange(Integer cargoChange);

	Integer getCollateralDamageChange();
	void setCollateralDamageChange(Integer collateralDamageChange);

	Integer getBombardRateChange();
	void setBombardRateChange(Integer bombardRateChange);

	Integer getFirstStrikesChange();
	void setFirstStrikesChange(Integer firstStrikesChange);

	Integer getChanceFirstStrikesChange();
	void setChanceFirstStrikesChange(Integer chanceFirstStrikesChange);

	Integer getEnemyHealChange();
	void setEnemyHealChange(Integer enemyHealChange);

	Integer getNeutralHealChange();
	void setNeutralHealChange(Integer neutralHealChange);

	Integer getFriendlyHealChange();
	void setFriendlyHealChange(Integer friendlyHealChange);

	Integer getSameTileHealChange();
	void setSameTileHealChange(Integer sameTileHealChange);

	Integer getAdjacentTileHealChange();
	void setAdjacentTileHealChange(Integer adjacentTileHealChange);

	Integer getCombatPercent();
	void setCombatPercent(Integer combatPercent);

	Integer getCityAttack();
	void setCityAttack(Integer cityAttack);

	Integer getCityDefense();
	void setCityDefense(Integer cityDefense);

	Integer getHillsAttack();
	void setHillsAttack(Integer hillsAttack);

	Integer getHillsDefense();
	void setHillsDefense(Integer hillsDefense);

	Integer getKamikazePercent();
	void setKamikazePercent(Integer kamikazePercent);

	Integer getSpyPreparationModifier();
	void setSpyPreparationModifier(Integer spyPreparationModifier);

	Integer getSpyPoisonModifier();
	void setSpyPoisonModifier(Integer spyPoisonModifier);

	Integer getSpyRevoltChange();
	void setSpyRevoltChange(Integer spyRevoltChange);

	Integer getSpyUnhappyChange();
	void setSpyUnhappyChange(Integer spyUnhappyChange);

	Integer getSpyInterceptChange();
	void setSpyInterceptChange(Integer spyInterceptChange);

	Integer getSpyEvasionChange();
	void setSpyEvasionChange(Integer spyEvasionChange);

	Integer getSpyDiploPenaltyChange();
	void setSpyDiploPenaltyChange(Integer spyDiploPenaltyChange);

	Integer getSpyEscapeChange();
	void setSpyEscapeChange(Integer spyEscapeChange);

	Integer getSpyNukeCityChange();
	void setSpyNukeCityChange(Integer spyNukeCityChange);

	Integer getSpyDisablePowerChange();
	void setSpyDisablePowerChange(Integer spyDisablePowerChange);

	Integer getSpyWarWearinessChange();
	void setSpyWarWearinessChange(Integer spyWarWearinessChange);

	Integer getSpyResearchSabotageChange();
	void setSpyResearchSabotageChange(Integer spyResearchSabotageChange);

	Integer getSpyReligionRemovalChange();
	void setSpyReligionRemovalChange(Integer spyReligionRemovalChange);

	Integer getSpyCorporationRemovalChange();
	void setSpyCorporationRemovalChange(Integer spyCorporationRemovalChange);

	Integer getSpyStealTreasuryChange();
	void setSpyStealTreasuryChange(Integer spyStealTreasuryChange);

	Integer getSpyBuyTechChange();
	void setSpyBuyTechChange(Integer spyBuyTechChange);

	Integer getSpySwitchCivicChange();
	void setSpySwitchCivicChange(Integer spySwitchCivicChange);

	Integer getSpySwitchReligionChange();
	void setSpySwitchReligionChange(Integer spySwitchReligionChange);

	Integer getSpyDestroyProjectChange();
	void setSpyDestroyProjectChange(Integer spyDestroyProjectChange);

	Integer getSpyDestroyBuildingChange();
	void setSpyDestroyBuildingChange(Integer spyDestroyBuildingChange);

	Integer getSpyDestroyImprovementChange();
	void setSpyDestroyImprovementChange(Integer spyDestroyImprovementChange);

	Integer getSpyDestroyProductionChange();
	void setSpyDestroyProductionChange(Integer spyDestroyProductionChange);

	Integer getSpyCultureChange();
	void setSpyCultureChange(Integer spyCultureChange);

	Integer getRevoltProtection();
	void setRevoltProtection(Integer revoltProtection);

	Integer getCollateralDamageProtection();
	void setCollateralDamageProtection(Integer collateralDamageProtection);

	Integer getPillageChange();
	void setPillageChange(Integer pillageChange);

	Integer getPlunderChange();
	void setPlunderChange(Integer plunderChange);

	Integer getExtraMorale();
	void setExtraMorale(Integer extraMorale);

	Integer getEnemyMoraleModifier();
	void setEnemyMoraleModifier(Integer enemyMoraleModifier);

	Integer getUpgradeDiscount();
	void setUpgradeDiscount(Integer upgradeDiscount);

	Integer getExperiencePercent();
	void setExperiencePercent(Integer experiencePercent);

	Integer getWorkRateModifier();
	void setWorkRateModifier(Integer workRateModifier);

	Boolean isUnitRangeUnbound();
	void setUnitRangeUnbound(Boolean unitRangeUnbound);

	Boolean isUnitTerritoryUnbound();
	void setUnitTerritoryUnbound(Boolean unitTerritoryUnbound);

	Integer getUnitRangeChange();
	void setUnitRangeChange(Integer unitRangeChange);

	Integer getUnitRangeModifier();
	void setUnitRangeModifier(Integer unitRangeModifier);

	List<IPair<String, Integer>> getTerrainAttacks();
	void addTerrainAttack(IPair<String, Integer> terrainAttack);

	List<IPair<String, Integer>> getTerrainDefenses();
	void addTerrainDefense(IPair<String, Integer> terrainDefense);

	List<IPair<String, Integer>> getFeatureAttacks();
	void addFeatureAttack(IPair<String, Integer> featureAttack);

	List<IPair<String, Integer>> getFeatureDefenses();
	void addFeatureDefense(IPair<String, Integer> featureDefense);

	List<IPair<String, Integer>> getUnitCombatMods();
	void addUnitCombatMod(IPair<String, Integer> unitCombatMod);

	List<IPair<String, Integer>> getDomainMods();
	void addDomainMod(IPair<String, Integer> domainMod);

	List<String> getTerrainDoubleMoves();
	void addTerrainDoubleMove(String terrainDoubleMove);

	List<String> getFeatureDoubleMoves();
	void addFeatureDoubleMove(String featureDoubleMove);

	List<IPair<String, String>> getBuildLeaveFeatures();
	void addBuildLeaveFeature(IPair<String, String> buildLeaveFeature);

	List<String> getNotUnitCombatTypes();
	void addNotUnitCombatType(String notUnitCombatType);

	List<String> getOrUnitCombatTypes();
	void addOrUnitCombatType(String orUnitCombatType);

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

	String getButton();
	void setButton(String button);

	Integer getOrderPriority();
	void setOrderPriority(Integer orderPriority);

}