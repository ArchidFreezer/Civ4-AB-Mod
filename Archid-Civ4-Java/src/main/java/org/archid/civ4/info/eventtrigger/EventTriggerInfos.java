package org.archid.civ4.info.eventtrigger;

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

@XmlRootElement(name="Civ4EventTriggerInfos", namespace="x-schema:CIV4EventSchema.xml")
@XmlAccessorType(XmlAccessType.NONE)
public class EventTriggerInfos extends AbstractInfos<IEventTriggerInfo> {

	public EventTriggerInfos() {
		super(new LinkedHashMap<String, IEventTriggerInfo>());
	}

	@XmlJavaTypeAdapter(EventTriggerMapAdapter.class)
	@XmlElement(name="EventTriggerInfos")
	public Map<String, IEventTriggerInfo> getInfoMap() {
		return infos;
	}

	public static IEventTriggerInfo createInfo(String type) {
		return new EventTriggerInfo(type);
	}

	@Override
	public void setInfoMap(Map<String, IEventTriggerInfo> infos) {
		this.infos = infos;
	}

	private static class EventTriggerInfo implements IEventTriggerInfo{

		private String type;
		private List<String> worldNewsTexts = new ArrayList<String>();
		private List<IPair<String, String>> triggerTexts = new ArrayList<IPair<String, String>>();
		private Boolean singlePlayer;
		private Integer percentGamesActive;
		private Integer weight;
		private Boolean probabilityUnitMultiply;
		private Boolean probabilityBuildingMultiply;
		private String civic;
		private Integer minTreasury;
		private Integer minPopulation;
		private Integer maxPopulation;
		private Integer minMapLandmass;
		private Integer minOurLandmass;
		private Integer maxOurLandmass;
		private String minDifficulty;
		private Integer angry;
		private Integer unhealthy;
		private List<String> unitsRequired = new ArrayList<String>();
		private Integer numUnits;
		private Integer numUnitsGlobal;
		private Integer unitDamagedWeight;
		private Integer unitDistanceWeight;
		private Integer unitExperienceWeight;
		private Boolean unitsOnPlot;
		private List<String> buildingsRequired = new ArrayList<String>();
		private Integer numBuildings;
		private Integer numBuildingsGlobal;
		private Integer numPlotsRequired;
		private Boolean ownPlot;
		private Integer plotType;
		private List<String> featuresRequired = new ArrayList<String>();
		private List<String> terrainsRequired = new ArrayList<String>();
		private List<String> improvementsRequired = new ArrayList<String>();
		private List<String> bonusesRequired = new ArrayList<String>();
		private List<String> routesRequired = new ArrayList<String>();
		private List<String> religionsRequired = new ArrayList<String>();
		private Integer numReligions;
		private List<String> corporationsRequired = new ArrayList<String>();
		private Integer numCorporations;
		private Boolean pickReligion;
		private Boolean stateReligion;
		private Boolean holyCity;
		private Boolean pickCorporation;
		private Boolean headquarters;
		private List<String> events = new ArrayList<String>();
		private List<String> prereqEvents = new ArrayList<String>();
		private Boolean prereqEventPlot;
		private List<String> orPreReqs = new ArrayList<String>();
		private List<String> andPreReqs = new ArrayList<String>();
		private List<String> obsoleteTechs = new ArrayList<String>();
		private Boolean recurring;
		private Boolean team;
		private Boolean global;
		private Boolean pickPlayer;
		private Boolean otherPlayerWar;
		private Boolean otherPlayerHasReligion;
		private Boolean otherPlayerHasOtherReligion;
		private Boolean otherPlayerAI;
		private Integer otherPlayerShareBorders;
		private String otherPlayerHasTech;
		private Boolean pickCity;
		private Boolean pickOtherPlayerCity;
		private Boolean showPlot;
		private Integer cityFoodWeight;
		private String pythonCanDo;
		private String pythonCanDoCity;
		private String pythonCanDoUnit;
		private String pythonCallback;

		private EventTriggerInfo(String type) {
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
		public List<String> getWorldNewsTexts() {
			return worldNewsTexts;
		}

		@Override
		public void addWorldNewsText(String worldNewsText) {
			this.worldNewsTexts.add(worldNewsText);
		}

		@Override
		public List<IPair<String, String>> getTriggerTexts() {
			return triggerTexts;
		}

		@Override
		public void addTriggerText(IPair<String, String> triggerText) {
			this.triggerTexts.add(triggerText);
		}

		@Override
		public Boolean isSinglePlayer() {
			return singlePlayer;
		}

		@Override
		public void setSinglePlayer(Boolean singlePlayer) {
			this.singlePlayer = singlePlayer;
		}

		@Override
		public Integer getPercentGamesActive() {
			return percentGamesActive;
		}

		@Override
		public void setPercentGamesActive(Integer percentGamesActive) {
			this.percentGamesActive = percentGamesActive;
		}

		@Override
		public Integer getWeight() {
			return weight;
		}

		@Override
		public void setWeight(Integer weight) {
			this.weight = weight;
		}

		@Override
		public Boolean isProbabilityUnitMultiply() {
			return probabilityUnitMultiply;
		}

		@Override
		public void setProbabilityUnitMultiply(Boolean probabilityUnitMultiply) {
			this.probabilityUnitMultiply = probabilityUnitMultiply;
		}

		@Override
		public Boolean isProbabilityBuildingMultiply() {
			return probabilityBuildingMultiply;
		}

		@Override
		public void setProbabilityBuildingMultiply(Boolean probabilityBuildingMultiply) {
			this.probabilityBuildingMultiply = probabilityBuildingMultiply;
		}

		@Override
		public String getCivic() {
			return civic;
		}

		@Override
		public void setCivic(String civic) {
			this.civic = civic;
		}

		@Override
		public Integer getMinTreasury() {
			return minTreasury;
		}

		@Override
		public void setMinTreasury(Integer minTreasury) {
			this.minTreasury = minTreasury;
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
		public Integer getMaxPopulation() {
			return maxPopulation;
		}

		@Override
		public void setMaxPopulation(Integer maxPopulation) {
			this.maxPopulation = maxPopulation;
		}

		@Override
		public Integer getMinMapLandmass() {
			return minMapLandmass;
		}

		@Override
		public void setMinMapLandmass(Integer minMapLandmass) {
			this.minMapLandmass = minMapLandmass;
		}

		@Override
		public Integer getMinOurLandmass() {
			return minOurLandmass;
		}

		@Override
		public void setMinOurLandmass(Integer minOurLandmass) {
			this.minOurLandmass = minOurLandmass;
		}

		@Override
		public Integer getMaxOurLandmass() {
			return maxOurLandmass;
		}

		@Override
		public void setMaxOurLandmass(Integer maxOurLandmass) {
			this.maxOurLandmass = maxOurLandmass;
		}

		@Override
		public String getMinDifficulty() {
			return minDifficulty;
		}

		@Override
		public void setMinDifficulty(String minDifficulty) {
			this.minDifficulty = minDifficulty;
		}

		@Override
		public Integer getAngry() {
			return angry;
		}

		@Override
		public void setAngry(Integer angry) {
			this.angry = angry;
		}

		@Override
		public Integer getUnhealthy() {
			return unhealthy;
		}

		@Override
		public void setUnhealthy(Integer unhealthy) {
			this.unhealthy = unhealthy;
		}

		@Override
		public List<String> getUnitsRequired() {
			return unitsRequired;
		}

		@Override
		public void addUnitRequired(String unitRequired) {
			this.unitsRequired.add(unitRequired);
		}

		@Override
		public Integer getNumUnits() {
			return numUnits;
		}

		@Override
		public void setNumUnits(Integer numUnits) {
			this.numUnits = numUnits;
		}

		@Override
		public Integer getNumUnitsGlobal() {
			return numUnitsGlobal;
		}

		@Override
		public void setNumUnitsGlobal(Integer numUnitsGlobal) {
			this.numUnitsGlobal = numUnitsGlobal;
		}

		@Override
		public Integer getUnitDamagedWeight() {
			return unitDamagedWeight;
		}

		@Override
		public void setUnitDamagedWeight(Integer unitDamagedWeight) {
			this.unitDamagedWeight = unitDamagedWeight;
		}

		@Override
		public Integer getUnitDistanceWeight() {
			return unitDistanceWeight;
		}

		@Override
		public void setUnitDistanceWeight(Integer unitDistanceWeight) {
			this.unitDistanceWeight = unitDistanceWeight;
		}

		@Override
		public Integer getUnitExperienceWeight() {
			return unitExperienceWeight;
		}

		@Override
		public void setUnitExperienceWeight(Integer unitExperienceWeight) {
			this.unitExperienceWeight = unitExperienceWeight;
		}

		@Override
		public Boolean isUnitsOnPlot() {
			return unitsOnPlot;
		}

		@Override
		public void setUnitsOnPlot(Boolean unitsOnPlot) {
			this.unitsOnPlot = unitsOnPlot;
		}

		@Override
		public List<String> getBuildingsRequired() {
			return buildingsRequired;
		}

		@Override
		public void addBuildingRequired(String buildingRequired) {
			this.buildingsRequired.add(buildingRequired);
		}

		@Override
		public Integer getNumBuildings() {
			return numBuildings;
		}

		@Override
		public void setNumBuildings(Integer numBuildings) {
			this.numBuildings = numBuildings;
		}

		@Override
		public Integer getNumBuildingsGlobal() {
			return numBuildingsGlobal;
		}

		@Override
		public void setNumBuildingsGlobal(Integer numBuildingsGlobal) {
			this.numBuildingsGlobal = numBuildingsGlobal;
		}

		@Override
		public Integer getNumPlotsRequired() {
			return numPlotsRequired;
		}

		@Override
		public void setNumPlotsRequired(Integer numPlotsRequired) {
			this.numPlotsRequired = numPlotsRequired;
		}

		@Override
		public Boolean isOwnPlot() {
			return ownPlot;
		}

		@Override
		public void setOwnPlot(Boolean ownPlot) {
			this.ownPlot = ownPlot;
		}

		@Override
		public Integer getPlotType() {
			return plotType;
		}

		@Override
		public void setPlotType(Integer plotType) {
			this.plotType = plotType;
		}

		@Override
		public List<String> getFeaturesRequired() {
			return featuresRequired;
		}

		@Override
		public void addFeatureRequired(String featureRequired) {
			this.featuresRequired.add(featureRequired);
		}

		@Override
		public List<String> getTerrainsRequired() {
			return terrainsRequired;
		}

		@Override
		public void addTerrainRequired(String terrainRequired) {
			this.terrainsRequired.add(terrainRequired);
		}

		@Override
		public List<String> getImprovementsRequired() {
			return improvementsRequired;
		}

		@Override
		public void addImprovementRequired(String improvementRequired) {
			this.improvementsRequired.add(improvementRequired);
		}

		@Override
		public List<String> getBonusesRequired() {
			return bonusesRequired;
		}

		@Override
		public void addBonusRequired(String bonusRequired) {
			this.bonusesRequired.add(bonusRequired);
		}

		@Override
		public List<String> getRoutesRequired() {
			return routesRequired;
		}

		@Override
		public void addRouteRequired(String routeRequired) {
			this.routesRequired.add(routeRequired);
		}

		@Override
		public List<String> getReligionsRequired() {
			return religionsRequired;
		}

		@Override
		public void addReligionRequired(String religionRequired) {
			this.religionsRequired.add(religionRequired);
		}

		@Override
		public Integer getNumReligions() {
			return numReligions;
		}

		@Override
		public void setNumReligions(Integer numReligions) {
			this.numReligions = numReligions;
		}

		@Override
		public List<String> getCorporationsRequired() {
			return corporationsRequired;
		}

		@Override
		public void addCorporationRequired(String corporationRequired) {
			this.corporationsRequired.add(corporationRequired);
		}

		@Override
		public Integer getNumCorporations() {
			return numCorporations;
		}

		@Override
		public void setNumCorporations(Integer numCorporations) {
			this.numCorporations = numCorporations;
		}

		@Override
		public Boolean isPickReligion() {
			return pickReligion;
		}

		@Override
		public void setPickReligion(Boolean pickReligion) {
			this.pickReligion = pickReligion;
		}

		@Override
		public Boolean isStateReligion() {
			return stateReligion;
		}

		@Override
		public void setStateReligion(Boolean stateReligion) {
			this.stateReligion = stateReligion;
		}

		@Override
		public Boolean isHolyCity() {
			return holyCity;
		}

		@Override
		public void setHolyCity(Boolean holyCity) {
			this.holyCity = holyCity;
		}

		@Override
		public Boolean isPickCorporation() {
			return pickCorporation;
		}

		@Override
		public void setPickCorporation(Boolean pickCorporation) {
			this.pickCorporation = pickCorporation;
		}

		@Override
		public Boolean isHeadquarters() {
			return headquarters;
		}

		@Override
		public void setHeadquarters(Boolean headquarters) {
			this.headquarters = headquarters;
		}

		@Override
		public List<String> getEvents() {
			return events;
		}

		@Override
		public void addEvent(String event) {
			this.events.add(event);
		}

		@Override
		public List<String> getPrereqEvents() {
			return prereqEvents;
		}

		@Override
		public void addPrereqEvent(String prereqEvent) {
			this.prereqEvents.add(prereqEvent);
		}

		@Override
		public Boolean isPrereqEventPlot() {
			return prereqEventPlot;
		}

		@Override
		public void setPrereqEventPlot(Boolean prereqEventPlot) {
			this.prereqEventPlot = prereqEventPlot;
		}

		@Override
		public List<String> getOrPreReqs() {
			return orPreReqs;
		}

		@Override
		public void addOrPreReq(String orPreReq) {
			this.orPreReqs.add(orPreReq);
		}

		@Override
		public List<String> getAndPreReqs() {
			return andPreReqs;
		}

		@Override
		public void addAndPreReq(String andPreReq) {
			this.andPreReqs.add(andPreReq);
		}

		@Override
		public List<String> getObsoleteTechs() {
			return obsoleteTechs;
		}

		@Override
		public void addObsoleteTech(String obsoleteTech) {
			this.obsoleteTechs.add(obsoleteTech);
		}

		@Override
		public Boolean isRecurring() {
			return recurring;
		}

		@Override
		public void setRecurring(Boolean recurring) {
			this.recurring = recurring;
		}

		@Override
		public Boolean isTeam() {
			return team;
		}

		@Override
		public void setTeam(Boolean team) {
			this.team = team;
		}

		@Override
		public Boolean isGlobal() {
			return global;
		}

		@Override
		public void setGlobal(Boolean global) {
			this.global = global;
		}

		@Override
		public Boolean isPickPlayer() {
			return pickPlayer;
		}

		@Override
		public void setPickPlayer(Boolean pickPlayer) {
			this.pickPlayer = pickPlayer;
		}

		@Override
		public Boolean isOtherPlayerWar() {
			return otherPlayerWar;
		}

		@Override
		public void setOtherPlayerWar(Boolean otherPlayerWar) {
			this.otherPlayerWar = otherPlayerWar;
		}

		@Override
		public Boolean isOtherPlayerHasReligion() {
			return otherPlayerHasReligion;
		}

		@Override
		public void setOtherPlayerHasReligion(Boolean otherPlayerHasReligion) {
			this.otherPlayerHasReligion = otherPlayerHasReligion;
		}

		@Override
		public Boolean isOtherPlayerHasOtherReligion() {
			return otherPlayerHasOtherReligion;
		}

		@Override
		public void setOtherPlayerHasOtherReligion(Boolean otherPlayerHasOtherReligion) {
			this.otherPlayerHasOtherReligion = otherPlayerHasOtherReligion;
		}

		@Override
		public Boolean isOtherPlayerAI() {
			return otherPlayerAI;
		}

		@Override
		public void setOtherPlayerAI(Boolean otherPlayerAI) {
			this.otherPlayerAI = otherPlayerAI;
		}

		@Override
		public Integer getOtherPlayerShareBorders() {
			return otherPlayerShareBorders;
		}

		@Override
		public void setOtherPlayerShareBorders(Integer otherPlayerShareBorders) {
			this.otherPlayerShareBorders = otherPlayerShareBorders;
		}

		@Override
		public String getOtherPlayerHasTech() {
			return otherPlayerHasTech;
		}

		@Override
		public void setOtherPlayerHasTech(String otherPlayerHasTech) {
			this.otherPlayerHasTech = otherPlayerHasTech;
		}

		@Override
		public Boolean isPickCity() {
			return pickCity;
		}

		@Override
		public void setPickCity(Boolean pickCity) {
			this.pickCity = pickCity;
		}

		@Override
		public Boolean isPickOtherPlayerCity() {
			return pickOtherPlayerCity;
		}

		@Override
		public void setPickOtherPlayerCity(Boolean pickOtherPlayerCity) {
			this.pickOtherPlayerCity = pickOtherPlayerCity;
		}

		@Override
		public Boolean isShowPlot() {
			return showPlot;
		}

		@Override
		public void setShowPlot(Boolean showPlot) {
			this.showPlot = showPlot;
		}

		@Override
		public Integer getCityFoodWeight() {
			return cityFoodWeight;
		}

		@Override
		public void setCityFoodWeight(Integer cityFoodWeight) {
			this.cityFoodWeight = cityFoodWeight;
		}

		@Override
		public String getPythonCanDo() {
			return pythonCanDo;
		}

		@Override
		public void setPythonCanDo(String pythonCanDo) {
			this.pythonCanDo = pythonCanDo;
		}

		@Override
		public String getPythonCanDoCity() {
			return pythonCanDoCity;
		}

		@Override
		public void setPythonCanDoCity(String pythonCanDoCity) {
			this.pythonCanDoCity = pythonCanDoCity;
		}

		@Override
		public String getPythonCanDoUnit() {
			return pythonCanDoUnit;
		}

		@Override
		public void setPythonCanDoUnit(String pythonCanDoUnit) {
			this.pythonCanDoUnit = pythonCanDoUnit;
		}

		@Override
		public String getPythonCallback() {
			return pythonCallback;
		}

		@Override
		public void setPythonCallback(String pythonCallback) {
			this.pythonCallback = pythonCallback;
		}
	}
}