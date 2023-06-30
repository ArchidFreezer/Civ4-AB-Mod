package org.archid.civ4.info.event;

import java.util.ArrayList;
import java.util.List;
import org.archid.civ4.info.IInfo;
import org.archid.utils.IPair;
import org.archid.utils.ITriple;

public interface IEventInfo extends IInfo {
	String getDescription();
	void setDescription(String description);

	String getLocalInfoText();
	void setLocalInfoText(String localInfoText);

	List<String> getWorldNewsTexts();
	void addWorldNewsText(String worldNewsText);

	String getOtherPlayerPopup();
	void setOtherPlayerPopup(String otherPlayerPopup);

	String getQuestFailText();
	void setQuestFailText(String questFailText);

	Boolean isQuest();
	void setQuest(Boolean quest);

	Boolean isGlobal();
	void setGlobal(Boolean global);

	Boolean isTeam();
	void setTeam(Boolean team);

	Boolean isPickCity();
	void setPickCity(Boolean pickCity);

	Boolean isPickOtherPlayerCity();
	void setPickOtherPlayerCity(Boolean pickOtherPlayerCity);

	Boolean isDeclareWar();
	void setDeclareWar(Boolean declareWar);

	Integer getGold();
	void setGold(Integer gold);

	Boolean isGoldToPlayer();
	void setGoldToPlayer(Boolean goldToPlayer);

	Integer getRandomGold();
	void setRandomGold(Integer randomGold);

	Integer getCulture();
	void setCulture(Integer culture);

	Integer getEspionagePoints();
	void setEspionagePoints(Integer espionagePoints);

	Boolean isGoldenAge();
	void setGoldenAge(Boolean goldenAge);

	Integer getFreeUnitSupport();
	void setFreeUnitSupport(Integer freeUnitSupport);

	Integer getInflationMod();
	void setInflationMod(Integer inflationMod);

	Integer getSpaceProductionMod();
	void setSpaceProductionMod(Integer spaceProductionMod);

	String getTech();
	void setTech(String tech);

	List<IPair<String, Integer>> getTechFlavors();
	void addTechFlavor(IPair<String, Integer> techFlavor);

	Integer getTechPercent();
	void setTechPercent(Integer techPercent);

	Integer getTechCostPercent();
	void setTechCostPercent(Integer techCostPercent);

	Integer getTechMinTurnsLeft();
	void setTechMinTurnsLeft(Integer techMinTurnsLeft);

	String getPrereqTech();
	void setPrereqTech(String prereqTech);

	String getUnitClass();
	void setUnitClass(String unitClass);

	Integer getNumFreeUnits();
	void setNumFreeUnits(Integer numFreeUnits);

	Boolean isDisbandUnit();
	void setDisbandUnit(Boolean disbandUnit);

	Integer getUnitExperience();
	void setUnitExperience(Integer unitExperience);

	Integer getUnitImmobileTurns();
	void setUnitImmobileTurns(Integer unitImmobileTurns);

	String getUnitPromotion();
	void setUnitPromotion(String unitPromotion);

	String getUnitName();
	void setUnitName(String unitName);

	List<IPair<String, String>> getUnitCombatPromotions();
	void addUnitCombatPromotion(IPair<String, String> unitCombatPromotion);

	List<IPair<String, String>> getUnitClassPromotions();
	void addUnitClassPromotion(IPair<String, String> unitClassPromotion);

	String getBuildingClass();
	void setBuildingClass(String buildingClass);

	Integer getBuildingChange();
	void setBuildingChange(Integer buildingChange);

	List<ITriple<String, String, Integer>> getBuildingExtraYields();
	void addBuildingExtraYield(ITriple<String, String, Integer> buildingExtraYield);

	List<ITriple<String, String, Integer>> getBuildingExtraCommerces();
	void addBuildingExtraCommerce(ITriple<String, String, Integer> buildingExtraCommerce);

	List<IPair<String, Integer>> getBuildingExtraHappies();
	void addBuildingExtraHappie(IPair<String, Integer> buildingExtraHappie);

	List<IPair<String, Integer>> getBuildingExtraHealths();
	void addBuildingExtraHealth(IPair<String, Integer> buildingExtraHealth);

	Integer getHappy();
	void setHappy(Integer happy);

	Integer getHealth();
	void setHealth(Integer health);

	Integer getHurryAnger();
	void setHurryAnger(Integer hurryAnger);

	Integer getHappyTurns();
	void setHappyTurns(Integer happyTurns);

	Integer getRevoltTurns();
	void setRevoltTurns(Integer revoltTurns);

	Integer getMinPillage();
	void setMinPillage(Integer minPillage);

	Integer getMaxPillage();
	void setMaxPillage(Integer maxPillage);

	Integer getFood();
	void setFood(Integer food);

	Integer getFoodPercent();
	void setFoodPercent(Integer foodPercent);

	List<IPair<String, Integer>> getFreeSpecialistCounts();
	void addFreeSpecialistCount(IPair<String, Integer> freeSpecialistCount);

	String getFeatureType();
	void setFeatureType(String featureType);

	Integer getFeatureChange();
	void setFeatureChange(Integer featureChange);

	String getImprovementType();
	void setImprovementType(String improvementType);

	Integer getImprovementChange();
	void setImprovementChange(Integer improvementChange);

	String getBonusType();
	void setBonusType(String bonusType);

	Integer getBonusChange();
	void setBonusChange(Integer bonusChange);

	String getRouteType();
	void setRouteType(String routeType);

	Integer getRouteChange();
	void setRouteChange(Integer routeChange);

	String getBonusRevealed();
	void setBonusRevealed(String bonusRevealed);

	String getBonusGift();
	void setBonusGift(String bonusGift);

	List<IPair<String, Integer>> getPlotExtraYields();
	void addPlotExtraYield(IPair<String, Integer> plotExtraYield);

	Integer getConvertOwnCities();
	void setConvertOwnCities(Integer convertOwnCities);

	Integer getConvertOtherCities();
	void setConvertOtherCities(Integer convertOtherCities);

	Integer getMaxNumReligions();
	void setMaxNumReligions(Integer maxNumReligions);

	Integer getOurAttitudeModifier();
	void setOurAttitudeModifier(Integer ourAttitudeModifier);

	Integer getAttitudeModifier();
	void setAttitudeModifier(Integer attitudeModifier);

	Integer getTheirEnemyAttitudeModifier();
	void setTheirEnemyAttitudeModifier(Integer theirEnemyAttitudeModifier);

	Integer getPopulationChange();
	void setPopulationChange(Integer populationChange);

	List<IPair<String, Integer>> getAdditionalEvents();
	void addAdditionalEvent(IPair<String, Integer> additionalEvent);

	List<IPair<String, Integer>> getEventTimes();
	void addEventTime(IPair<String, Integer> eventTime);

	List<IPair<String, Integer>> getClearEvents();
	void addClearEvent(IPair<String, Integer> clearEvent);

	String getPythonCallback();
	void setPythonCallback(String pythonCallback);

	String getPythonExpireCheck();
	void setPythonExpireCheck(String pythonExpireCheck);

	String getPythonCanDo();
	void setPythonCanDo(String pythonCanDo);

	String getPythonHelp();
	void setPythonHelp(String pythonHelp);

	String getButton();
	void setButton(String button);

	Integer getAIValue();
	void setAIValue(Integer aIValue);

}