package org.archid.civ4.info.eventtrigger;

import java.util.ArrayList;
import java.util.List;
import org.archid.civ4.info.IInfo;
import org.archid.utils.IPair;

public interface IEventTriggerInfo extends IInfo {
	List<String> getWorldNewsTexts();
	void addWorldNewsText(String worldNewsText);

	List<IPair<String, String>> getTriggerTexts();
	void addTriggerText(IPair<String, String> triggerText);

	Boolean isSinglePlayer();
	void setSinglePlayer(Boolean singlePlayer);

	Integer getPercentGamesActive();
	void setPercentGamesActive(Integer percentGamesActive);

	Integer getWeight();
	void setWeight(Integer weight);

	Boolean isProbabilityUnitMultiply();
	void setProbabilityUnitMultiply(Boolean probabilityUnitMultiply);

	Boolean isProbabilityBuildingMultiply();
	void setProbabilityBuildingMultiply(Boolean probabilityBuildingMultiply);

	String getCivic();
	void setCivic(String civic);

	Integer getMinTreasury();
	void setMinTreasury(Integer minTreasury);

	Integer getMinPopulation();
	void setMinPopulation(Integer minPopulation);

	Integer getMaxPopulation();
	void setMaxPopulation(Integer maxPopulation);

	Integer getMinMapLandmass();
	void setMinMapLandmass(Integer minMapLandmass);

	Integer getMinOurLandmass();
	void setMinOurLandmass(Integer minOurLandmass);

	Integer getMaxOurLandmass();
	void setMaxOurLandmass(Integer maxOurLandmass);

	String getMinDifficulty();
	void setMinDifficulty(String minDifficulty);

	Integer getAngry();
	void setAngry(Integer angry);

	Integer getUnhealthy();
	void setUnhealthy(Integer unhealthy);

	List<String> getUnitsRequired();
	void addUnitRequired(String unitRequired);

	Integer getNumUnits();
	void setNumUnits(Integer numUnits);

	Integer getNumUnitsGlobal();
	void setNumUnitsGlobal(Integer numUnitsGlobal);

	Integer getUnitDamagedWeight();
	void setUnitDamagedWeight(Integer unitDamagedWeight);

	Integer getUnitDistanceWeight();
	void setUnitDistanceWeight(Integer unitDistanceWeight);

	Integer getUnitExperienceWeight();
	void setUnitExperienceWeight(Integer unitExperienceWeight);

	Boolean isUnitsOnPlot();
	void setUnitsOnPlot(Boolean unitsOnPlot);

	List<String> getBuildingsRequired();
	void addBuildingRequired(String buildingRequired);

	Integer getNumBuildings();
	void setNumBuildings(Integer numBuildings);

	Integer getNumBuildingsGlobal();
	void setNumBuildingsGlobal(Integer numBuildingsGlobal);

	Integer getNumPlotsRequired();
	void setNumPlotsRequired(Integer numPlotsRequired);

	Boolean isOwnPlot();
	void setOwnPlot(Boolean ownPlot);

	Integer getPlotType();
	void setPlotType(Integer plotType);

	List<String> getFeaturesRequired();
	void addFeatureRequired(String featureRequired);

	List<String> getTerrainsRequired();
	void addTerrainRequired(String terrainRequired);

	List<String> getImprovementsRequired();
	void addImprovementRequired(String improvementRequired);

	List<String> getBonusesRequired();
	void addBonusRequired(String bonusRequired);

	List<String> getRoutesRequired();
	void addRouteRequired(String routeRequired);

	List<String> getReligionsRequired();
	void addReligionRequired(String religionRequired);

	Integer getNumReligions();
	void setNumReligions(Integer numReligions);

	List<String> getCorporationsRequired();
	void addCorporationRequired(String corporationRequired);

	Integer getNumCorporations();
	void setNumCorporations(Integer numCorporations);

	Boolean isPickReligion();
	void setPickReligion(Boolean pickReligion);

	Boolean isStateReligion();
	void setStateReligion(Boolean stateReligion);

	Boolean isHolyCity();
	void setHolyCity(Boolean holyCity);

	Boolean isPickCorporation();
	void setPickCorporation(Boolean pickCorporation);

	Boolean isHeadquarters();
	void setHeadquarters(Boolean headquarters);

	List<String> getEvents();
	void addEvent(String event);

	List<String> getPrereqEvents();
	void addPrereqEvent(String prereqEvent);

	Boolean isPrereqEventPlot();
	void setPrereqEventPlot(Boolean prereqEventPlot);

	List<String> getOrPreReqs();
	void addOrPreReq(String orPreReq);

	List<String> getAndPreReqs();
	void addAndPreReq(String andPreReq);

	List<String> getObsoleteTechs();
	void addObsoleteTech(String obsoleteTech);

	Boolean isRecurring();
	void setRecurring(Boolean recurring);

	Boolean isTeam();
	void setTeam(Boolean team);

	Boolean isGlobal();
	void setGlobal(Boolean global);

	Boolean isPickPlayer();
	void setPickPlayer(Boolean pickPlayer);

	Boolean isOtherPlayerWar();
	void setOtherPlayerWar(Boolean otherPlayerWar);

	Boolean isOtherPlayerHasReligion();
	void setOtherPlayerHasReligion(Boolean otherPlayerHasReligion);

	Boolean isOtherPlayerHasOtherReligion();
	void setOtherPlayerHasOtherReligion(Boolean otherPlayerHasOtherReligion);

	Boolean isOtherPlayerAI();
	void setOtherPlayerAI(Boolean otherPlayerAI);

	Integer getOtherPlayerShareBorders();
	void setOtherPlayerShareBorders(Integer otherPlayerShareBorders);

	String getOtherPlayerHasTech();
	void setOtherPlayerHasTech(String otherPlayerHasTech);

	Boolean isPickCity();
	void setPickCity(Boolean pickCity);

	Boolean isPickOtherPlayerCity();
	void setPickOtherPlayerCity(Boolean pickOtherPlayerCity);

	Boolean isShowPlot();
	void setShowPlot(Boolean showPlot);

	Integer getCityFoodWeight();
	void setCityFoodWeight(Integer cityFoodWeight);

	String getPythonCanDo();
	void setPythonCanDo(String pythonCanDo);

	String getPythonCanDoCity();
	void setPythonCanDoCity(String pythonCanDoCity);

	String getPythonCanDoUnit();
	void setPythonCanDoUnit(String pythonCanDoUnit);

	String getPythonCallback();
	void setPythonCallback(String pythonCallback);

}