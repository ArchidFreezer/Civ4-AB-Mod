package org.archid.civ4.info.eventtrigger;

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

public class EventTriggerMapAdapter extends XmlAdapter<EventTriggerMapAdapter.EventTriggerMap, Map<String, IEventTriggerInfo>> {

	public static class EventTriggerMap {
		@XmlElement(name = "EventTriggerInfo")
		List<AdaptedEventTrigger> entries = new ArrayList<AdaptedEventTrigger>();
	}

	private static class AdaptedEventTrigger {
		@XmlElement(name="Type")
		private String type;
		@XmlElementWrapper(name="WorldNewsTexts")
		@XmlElement(name="Text")
		private List<String> worldNewsTexts;
		@XmlElementWrapper(name="TriggerTexts")
		@XmlElement(name="TriggerText")
		private List<AdaptedTriggerTexts> triggerTexts;
		@XmlElement(name="bSinglePlayer")
		private Integer singlePlayer;
		@XmlElement(name="iPercentGamesActive")
		private Integer percentGamesActive;
		@XmlElement(name="iWeight")
		private Integer weight;
		@XmlElement(name="bProbabilityUnitMultiply")
		private Integer probabilityUnitMultiply;
		@XmlElement(name="bProbabilityBuildingMultiply")
		private Integer probabilityBuildingMultiply;
		@XmlElement(name="Civic")
		private String civic;
		@XmlElement(name="iMinTreasury")
		private Integer minTreasury;
		@XmlElement(name="iMinPopulation")
		private Integer minPopulation;
		@XmlElement(name="iMaxPopulation")
		private Integer maxPopulation;
		@XmlElement(name="iMinMapLandmass")
		private Integer minMapLandmass;
		@XmlElement(name="iMinOurLandmass")
		private Integer minOurLandmass;
		@XmlElement(name="iMaxOurLandmass")
		private Integer maxOurLandmass;
		@XmlElement(name="MinDifficulty")
		private String minDifficulty;
		@XmlElement(name="iAngry")
		private Integer angry;
		@XmlElement(name="iUnhealthy")
		private Integer unhealthy;
		@XmlElementWrapper(name="UnitsRequired")
		@XmlElement(name="UnitClass")
		private List<String> unitsRequired;
		@XmlElement(name="iNumUnits")
		private Integer numUnits;
		@XmlElement(name="iNumUnitsGlobal")
		private Integer numUnitsGlobal;
		@XmlElement(name="iUnitDamagedWeight")
		private Integer unitDamagedWeight;
		@XmlElement(name="iUnitDistanceWeight")
		private Integer unitDistanceWeight;
		@XmlElement(name="iUnitExperienceWeight")
		private Integer unitExperienceWeight;
		@XmlElement(name="bUnitsOnPlot")
		private Integer unitsOnPlot;
		@XmlElementWrapper(name="BuildingsRequired")
		@XmlElement(name="BuildingClass")
		private List<String> buildingsRequired;
		@XmlElement(name="iNumBuildings")
		private Integer numBuildings;
		@XmlElement(name="iNumBuildingsGlobal")
		private Integer numBuildingsGlobal;
		@XmlElement(name="iNumPlotsRequired")
		private Integer numPlotsRequired;
		@XmlElement(name="bOwnPlot")
		private Integer ownPlot;
		@XmlElement(name="iPlotType")
		private Integer plotType;
		@XmlElementWrapper(name="FeaturesRequired")
		@XmlElement(name="FeatureType")
		private List<String> featuresRequired;
		@XmlElementWrapper(name="TerrainsRequired")
		@XmlElement(name="TerrainType")
		private List<String> terrainsRequired;
		@XmlElementWrapper(name="ImprovementsRequired")
		@XmlElement(name="ImprovementType")
		private List<String> improvementsRequired;
		@XmlElementWrapper(name="BonusesRequired")
		@XmlElement(name="BonusType")
		private List<String> bonusesRequired;
		@XmlElementWrapper(name="RoutesRequired")
		@XmlElement(name="RouteType")
		private List<String> routesRequired;
		@XmlElementWrapper(name="ReligionsRequired")
		@XmlElement(name="ReligionType")
		private List<String> religionsRequired;
		@XmlElement(name="iNumReligions")
		private Integer numReligions;
		@XmlElementWrapper(name="CorporationsRequired")
		@XmlElement(name="CorporationType")
		private List<String> corporationsRequired;
		@XmlElement(name="iNumCorporations")
		private Integer numCorporations;
		@XmlElement(name="bPickReligion")
		private Integer pickReligion;
		@XmlElement(name="bStateReligion")
		private Integer stateReligion;
		@XmlElement(name="bHolyCity")
		private Integer holyCity;
		@XmlElement(name="bPickCorporation")
		private Integer pickCorporation;
		@XmlElement(name="bHeadquarters")
		private Integer headquarters;
		@XmlElementWrapper(name="Events")
		@XmlElement(name="Event")
		private List<String> events;
		@XmlElementWrapper(name="PrereqEvents")
		@XmlElement(name="Event")
		private List<String> prereqEvents;
		@XmlElement(name="bPrereqEventPlot")
		private Integer prereqEventPlot;
		@XmlElementWrapper(name="OrPreReqs")
		@XmlElement(name="PrereqTech")
		private List<String> orPreReqs;
		@XmlElementWrapper(name="AndPreReqs")
		@XmlElement(name="PrereqTech")
		private List<String> andPreReqs;
		@XmlElementWrapper(name="ObsoleteTechs")
		@XmlElement(name="ObsoleteTech")
		private List<String> obsoleteTechs;
		@XmlElement(name="bRecurring")
		private Integer recurring;
		@XmlElement(name="bTeam")
		private Integer team;
		@XmlElement(name="bGlobal")
		private Integer global;
		@XmlElement(name="bPickPlayer")
		private Integer pickPlayer;
		@XmlElement(name="bOtherPlayerWar")
		private Integer otherPlayerWar;
		@XmlElement(name="bOtherPlayerHasReligion")
		private Integer otherPlayerHasReligion;
		@XmlElement(name="bOtherPlayerHasOtherReligion")
		private Integer otherPlayerHasOtherReligion;
		@XmlElement(name="bOtherPlayerAI")
		private Integer otherPlayerAI;
		@XmlElement(name="iOtherPlayerShareBorders")
		private Integer otherPlayerShareBorders;
		@XmlElement(name="OtherPlayerHasTech")
		private String otherPlayerHasTech;
		@XmlElement(name="bPickCity")
		private Integer pickCity;
		@XmlElement(name="bPickOtherPlayerCity")
		private Integer pickOtherPlayerCity;
		@XmlElement(name="bShowPlot")
		private Integer showPlot;
		@XmlElement(name="iCityFoodWeight")
		private Integer cityFoodWeight;
		@XmlElement(name="PythonCanDo")
		private String pythonCanDo;
		@XmlElement(name="PythonCanDoCity")
		private String pythonCanDoCity;
		@XmlElement(name="PythonCanDoUnit")
		private String pythonCanDoUnit;
		@XmlElement(name="PythonCallback")
		private String pythonCallback;
	}

	private static class AdaptedTriggerTexts {
		@XmlElement(name="Text")
		private String text;
		@XmlElement(name="Era")
		private String era;
	}

	@Override
	public Map<String, IEventTriggerInfo> unmarshal(EventTriggerMap v) throws Exception {
		Map<String, IEventTriggerInfo> map = new TreeMap<String, IEventTriggerInfo>();
		for (AdaptedEventTrigger aInfo: v.entries) {
			IEventTriggerInfo info = EventTriggerInfos.createInfo(aInfo.type);

			if (CollectionUtils.hasElements(aInfo.worldNewsTexts)) {
				for (String val: aInfo.worldNewsTexts) {
					if (StringUtils.hasCharacters(val)) {
						info.addWorldNewsText(val);
					}
				}
			}

			if (CollectionUtils.hasElements(aInfo.triggerTexts)) {
				for (AdaptedTriggerTexts adaptor: aInfo.triggerTexts) {
					if (StringUtils.hasCharacters(adaptor.text)) {
						info.addTriggerText(new Pair<String, String>(adaptor.text, adaptor.era));
					}
				}
			}
			info.setSinglePlayer(JaxbUtils.unmarshallBoolean(aInfo.singlePlayer));
			info.setPercentGamesActive(JaxbUtils.unmarshallInteger(aInfo.percentGamesActive));
			info.setWeight(JaxbUtils.unmarshallInteger(aInfo.weight));
			info.setProbabilityUnitMultiply(JaxbUtils.unmarshallBoolean(aInfo.probabilityUnitMultiply));
			info.setProbabilityBuildingMultiply(JaxbUtils.unmarshallBoolean(aInfo.probabilityBuildingMultiply));
			info.setCivic(JaxbUtils.unmarshallString(aInfo.civic));
			info.setMinTreasury(JaxbUtils.unmarshallInteger(aInfo.minTreasury));
			info.setMinPopulation(JaxbUtils.unmarshallInteger(aInfo.minPopulation));
			info.setMaxPopulation(JaxbUtils.unmarshallInteger(aInfo.maxPopulation));
			info.setMinMapLandmass(JaxbUtils.unmarshallInteger(aInfo.minMapLandmass));
			info.setMinOurLandmass(JaxbUtils.unmarshallInteger(aInfo.minOurLandmass));
			info.setMaxOurLandmass(JaxbUtils.unmarshallInteger(aInfo.maxOurLandmass));
			info.setMinDifficulty(JaxbUtils.unmarshallString(aInfo.minDifficulty));
			info.setAngry(JaxbUtils.unmarshallInteger(aInfo.angry));
			info.setUnhealthy(JaxbUtils.unmarshallInteger(aInfo.unhealthy));

			if (CollectionUtils.hasElements(aInfo.unitsRequired)) {
				for (String val: aInfo.unitsRequired) {
					if (StringUtils.hasCharacters(val)) {
						info.addUnitRequired(val);
					}
				}
			}
			info.setNumUnits(JaxbUtils.unmarshallInteger(aInfo.numUnits));
			info.setNumUnitsGlobal(JaxbUtils.unmarshallInteger(aInfo.numUnitsGlobal));
			info.setUnitDamagedWeight(JaxbUtils.unmarshallInteger(aInfo.unitDamagedWeight));
			info.setUnitDistanceWeight(JaxbUtils.unmarshallInteger(aInfo.unitDistanceWeight));
			info.setUnitExperienceWeight(JaxbUtils.unmarshallInteger(aInfo.unitExperienceWeight));
			info.setUnitsOnPlot(JaxbUtils.unmarshallBoolean(aInfo.unitsOnPlot));

			if (CollectionUtils.hasElements(aInfo.buildingsRequired)) {
				for (String val: aInfo.buildingsRequired) {
					if (StringUtils.hasCharacters(val)) {
						info.addBuildingRequired(val);
					}
				}
			}
			info.setNumBuildings(JaxbUtils.unmarshallInteger(aInfo.numBuildings));
			info.setNumBuildingsGlobal(JaxbUtils.unmarshallInteger(aInfo.numBuildingsGlobal));
			info.setNumPlotsRequired(JaxbUtils.unmarshallInteger(aInfo.numPlotsRequired));
			info.setOwnPlot(JaxbUtils.unmarshallBoolean(aInfo.ownPlot));
			info.setPlotType(JaxbUtils.unmarshallInteger(aInfo.plotType));

			if (CollectionUtils.hasElements(aInfo.featuresRequired)) {
				for (String val: aInfo.featuresRequired) {
					if (StringUtils.hasCharacters(val)) {
						info.addFeatureRequired(val);
					}
				}
			}

			if (CollectionUtils.hasElements(aInfo.terrainsRequired)) {
				for (String val: aInfo.terrainsRequired) {
					if (StringUtils.hasCharacters(val)) {
						info.addTerrainRequired(val);
					}
				}
			}

			if (CollectionUtils.hasElements(aInfo.improvementsRequired)) {
				for (String val: aInfo.improvementsRequired) {
					if (StringUtils.hasCharacters(val)) {
						info.addImprovementRequired(val);
					}
				}
			}

			if (CollectionUtils.hasElements(aInfo.bonusesRequired)) {
				for (String val: aInfo.bonusesRequired) {
					if (StringUtils.hasCharacters(val)) {
						info.addBonusRequired(val);
					}
				}
			}

			if (CollectionUtils.hasElements(aInfo.routesRequired)) {
				for (String val: aInfo.routesRequired) {
					if (StringUtils.hasCharacters(val)) {
						info.addRouteRequired(val);
					}
				}
			}

			if (CollectionUtils.hasElements(aInfo.religionsRequired)) {
				for (String val: aInfo.religionsRequired) {
					if (StringUtils.hasCharacters(val)) {
						info.addReligionRequired(val);
					}
				}
			}
			info.setNumReligions(JaxbUtils.unmarshallInteger(aInfo.numReligions));

			if (CollectionUtils.hasElements(aInfo.corporationsRequired)) {
				for (String val: aInfo.corporationsRequired) {
					if (StringUtils.hasCharacters(val)) {
						info.addCorporationRequired(val);
					}
				}
			}
			info.setNumCorporations(JaxbUtils.unmarshallInteger(aInfo.numCorporations));
			info.setPickReligion(JaxbUtils.unmarshallBoolean(aInfo.pickReligion));
			info.setStateReligion(JaxbUtils.unmarshallBoolean(aInfo.stateReligion));
			info.setHolyCity(JaxbUtils.unmarshallBoolean(aInfo.holyCity));
			info.setPickCorporation(JaxbUtils.unmarshallBoolean(aInfo.pickCorporation));
			info.setHeadquarters(JaxbUtils.unmarshallBoolean(aInfo.headquarters));

			if (CollectionUtils.hasElements(aInfo.events)) {
				for (String val: aInfo.events) {
					if (StringUtils.hasCharacters(val)) {
						info.addEvent(val);
					}
				}
			}

			if (CollectionUtils.hasElements(aInfo.prereqEvents)) {
				for (String val: aInfo.prereqEvents) {
					if (StringUtils.hasCharacters(val)) {
						info.addPrereqEvent(val);
					}
				}
			}
			info.setPrereqEventPlot(JaxbUtils.unmarshallBoolean(aInfo.prereqEventPlot));

			if (CollectionUtils.hasElements(aInfo.orPreReqs)) {
				for (String val: aInfo.orPreReqs) {
					if (StringUtils.hasCharacters(val)) {
						info.addOrPreReq(val);
					}
				}
			}

			if (CollectionUtils.hasElements(aInfo.andPreReqs)) {
				for (String val: aInfo.andPreReqs) {
					if (StringUtils.hasCharacters(val)) {
						info.addAndPreReq(val);
					}
				}
			}

			if (CollectionUtils.hasElements(aInfo.obsoleteTechs)) {
				for (String val: aInfo.obsoleteTechs) {
					if (StringUtils.hasCharacters(val)) {
						info.addObsoleteTech(val);
					}
				}
			}
			info.setRecurring(JaxbUtils.unmarshallBoolean(aInfo.recurring));
			info.setTeam(JaxbUtils.unmarshallBoolean(aInfo.team));
			info.setGlobal(JaxbUtils.unmarshallBoolean(aInfo.global));
			info.setPickPlayer(JaxbUtils.unmarshallBoolean(aInfo.pickPlayer));
			info.setOtherPlayerWar(JaxbUtils.unmarshallBoolean(aInfo.otherPlayerWar));
			info.setOtherPlayerHasReligion(JaxbUtils.unmarshallBoolean(aInfo.otherPlayerHasReligion));
			info.setOtherPlayerHasOtherReligion(JaxbUtils.unmarshallBoolean(aInfo.otherPlayerHasOtherReligion));
			info.setOtherPlayerAI(JaxbUtils.unmarshallBoolean(aInfo.otherPlayerAI));
			info.setOtherPlayerShareBorders(JaxbUtils.unmarshallInteger(aInfo.otherPlayerShareBorders));
			info.setOtherPlayerHasTech(JaxbUtils.unmarshallString(aInfo.otherPlayerHasTech));
			info.setPickCity(JaxbUtils.unmarshallBoolean(aInfo.pickCity));
			info.setPickOtherPlayerCity(JaxbUtils.unmarshallBoolean(aInfo.pickOtherPlayerCity));
			info.setShowPlot(JaxbUtils.unmarshallBoolean(aInfo.showPlot));
			info.setCityFoodWeight(JaxbUtils.unmarshallInteger(aInfo.cityFoodWeight));
			info.setPythonCanDo(JaxbUtils.unmarshallString(aInfo.pythonCanDo));
			info.setPythonCanDoCity(JaxbUtils.unmarshallString(aInfo.pythonCanDoCity));
			info.setPythonCanDoUnit(JaxbUtils.unmarshallString(aInfo.pythonCanDoUnit));
			info.setPythonCallback(JaxbUtils.unmarshallString(aInfo.pythonCallback));

			map.put(aInfo.type, info);
		}
		return map;
	}

	@Override
	public EventTriggerMap marshal(Map<String, IEventTriggerInfo> v) throws Exception {
		EventTriggerMap map = new EventTriggerMap();
		for (IEventTriggerInfo info: v.values()) {
			AdaptedEventTrigger aInfo = new AdaptedEventTrigger();
			aInfo.type = JaxbUtils.marshallString(info.getType());

			if (CollectionUtils.hasElements(info.getWorldNewsTexts())) {
				aInfo.worldNewsTexts = new ArrayList<String>();
				for(String val: info.getWorldNewsTexts()) {
					aInfo.worldNewsTexts.add(val);
				}
			}

			if (CollectionUtils.hasElements(info.getTriggerTexts())) {
				aInfo.triggerTexts = new ArrayList<AdaptedTriggerTexts>();
				for (IPair<String, String> pair: info.getTriggerTexts()) {
					AdaptedTriggerTexts adaptor = new AdaptedTriggerTexts();
					adaptor.text = pair.getKey();
					adaptor.era = pair.getValue();
					aInfo.triggerTexts.add(adaptor);
				}
			}
			aInfo.singlePlayer = JaxbUtils.marshallBoolean(info.isSinglePlayer());
			aInfo.percentGamesActive = JaxbUtils.marshallInteger(info.getPercentGamesActive());
			aInfo.weight = JaxbUtils.marshallInteger(info.getWeight());
			aInfo.probabilityUnitMultiply = JaxbUtils.marshallBoolean(info.isProbabilityUnitMultiply());
			aInfo.probabilityBuildingMultiply = JaxbUtils.marshallBoolean(info.isProbabilityBuildingMultiply());
			aInfo.civic = JaxbUtils.marshallString(info.getCivic());
			aInfo.minTreasury = JaxbUtils.marshallInteger(info.getMinTreasury());
			aInfo.minPopulation = JaxbUtils.marshallInteger(info.getMinPopulation());
			aInfo.maxPopulation = JaxbUtils.marshallInteger(info.getMaxPopulation());
			aInfo.minMapLandmass = JaxbUtils.marshallInteger(info.getMinMapLandmass());
			aInfo.minOurLandmass = JaxbUtils.marshallInteger(info.getMinOurLandmass());
			aInfo.maxOurLandmass = JaxbUtils.marshallInteger(info.getMaxOurLandmass());
			aInfo.minDifficulty = JaxbUtils.marshallString(info.getMinDifficulty());
			aInfo.angry = JaxbUtils.marshallInteger(info.getAngry());
			aInfo.unhealthy = JaxbUtils.marshallInteger(info.getUnhealthy());

			if (CollectionUtils.hasElements(info.getUnitsRequired())) {
				aInfo.unitsRequired = new ArrayList<String>();
				for(String val: info.getUnitsRequired()) {
					aInfo.unitsRequired.add(val);
				}
			}
			aInfo.numUnits = JaxbUtils.marshallInteger(info.getNumUnits());
			aInfo.numUnitsGlobal = JaxbUtils.marshallInteger(info.getNumUnitsGlobal());
			aInfo.unitDamagedWeight = JaxbUtils.marshallInteger(info.getUnitDamagedWeight());
			aInfo.unitDistanceWeight = JaxbUtils.marshallInteger(info.getUnitDistanceWeight());
			aInfo.unitExperienceWeight = JaxbUtils.marshallInteger(info.getUnitExperienceWeight());
			aInfo.unitsOnPlot = JaxbUtils.marshallBoolean(info.isUnitsOnPlot());

			if (CollectionUtils.hasElements(info.getBuildingsRequired())) {
				aInfo.buildingsRequired = new ArrayList<String>();
				for(String val: info.getBuildingsRequired()) {
					aInfo.buildingsRequired.add(val);
				}
			}
			aInfo.numBuildings = JaxbUtils.marshallInteger(info.getNumBuildings());
			aInfo.numBuildingsGlobal = JaxbUtils.marshallInteger(info.getNumBuildingsGlobal());
			aInfo.numPlotsRequired = JaxbUtils.marshallInteger(info.getNumPlotsRequired());
			aInfo.ownPlot = JaxbUtils.marshallBoolean(info.isOwnPlot());
			aInfo.plotType = JaxbUtils.marshallInteger(info.getPlotType());

			if (CollectionUtils.hasElements(info.getFeaturesRequired())) {
				aInfo.featuresRequired = new ArrayList<String>();
				for(String val: info.getFeaturesRequired()) {
					aInfo.featuresRequired.add(val);
				}
			}

			if (CollectionUtils.hasElements(info.getTerrainsRequired())) {
				aInfo.terrainsRequired = new ArrayList<String>();
				for(String val: info.getTerrainsRequired()) {
					aInfo.terrainsRequired.add(val);
				}
			}

			if (CollectionUtils.hasElements(info.getImprovementsRequired())) {
				aInfo.improvementsRequired = new ArrayList<String>();
				for(String val: info.getImprovementsRequired()) {
					aInfo.improvementsRequired.add(val);
				}
			}

			if (CollectionUtils.hasElements(info.getBonusesRequired())) {
				aInfo.bonusesRequired = new ArrayList<String>();
				for(String val: info.getBonusesRequired()) {
					aInfo.bonusesRequired.add(val);
				}
			}

			if (CollectionUtils.hasElements(info.getRoutesRequired())) {
				aInfo.routesRequired = new ArrayList<String>();
				for(String val: info.getRoutesRequired()) {
					aInfo.routesRequired.add(val);
				}
			}

			if (CollectionUtils.hasElements(info.getReligionsRequired())) {
				aInfo.religionsRequired = new ArrayList<String>();
				for(String val: info.getReligionsRequired()) {
					aInfo.religionsRequired.add(val);
				}
			}
			aInfo.numReligions = JaxbUtils.marshallInteger(info.getNumReligions());

			if (CollectionUtils.hasElements(info.getCorporationsRequired())) {
				aInfo.corporationsRequired = new ArrayList<String>();
				for(String val: info.getCorporationsRequired()) {
					aInfo.corporationsRequired.add(val);
				}
			}
			aInfo.numCorporations = JaxbUtils.marshallInteger(info.getNumCorporations());
			aInfo.pickReligion = JaxbUtils.marshallBoolean(info.isPickReligion());
			aInfo.stateReligion = JaxbUtils.marshallBoolean(info.isStateReligion());
			aInfo.holyCity = JaxbUtils.marshallBoolean(info.isHolyCity());
			aInfo.pickCorporation = JaxbUtils.marshallBoolean(info.isPickCorporation());
			aInfo.headquarters = JaxbUtils.marshallBoolean(info.isHeadquarters());

			if (CollectionUtils.hasElements(info.getEvents())) {
				aInfo.events = new ArrayList<String>();
				for(String val: info.getEvents()) {
					aInfo.events.add(val);
				}
			}

			if (CollectionUtils.hasElements(info.getPrereqEvents())) {
				aInfo.prereqEvents = new ArrayList<String>();
				for(String val: info.getPrereqEvents()) {
					aInfo.prereqEvents.add(val);
				}
			}
			aInfo.prereqEventPlot = JaxbUtils.marshallBoolean(info.isPrereqEventPlot());

			if (CollectionUtils.hasElements(info.getOrPreReqs())) {
				aInfo.orPreReqs = new ArrayList<String>();
				for(String val: info.getOrPreReqs()) {
					aInfo.orPreReqs.add(val);
				}
			}

			if (CollectionUtils.hasElements(info.getAndPreReqs())) {
				aInfo.andPreReqs = new ArrayList<String>();
				for(String val: info.getAndPreReqs()) {
					aInfo.andPreReqs.add(val);
				}
			}

			if (CollectionUtils.hasElements(info.getObsoleteTechs())) {
				aInfo.obsoleteTechs = new ArrayList<String>();
				for(String val: info.getObsoleteTechs()) {
					aInfo.obsoleteTechs.add(val);
				}
			}
			aInfo.recurring = JaxbUtils.marshallBoolean(info.isRecurring());
			aInfo.team = JaxbUtils.marshallBoolean(info.isTeam());
			aInfo.global = JaxbUtils.marshallBoolean(info.isGlobal());
			aInfo.pickPlayer = JaxbUtils.marshallBoolean(info.isPickPlayer());
			aInfo.otherPlayerWar = JaxbUtils.marshallBoolean(info.isOtherPlayerWar());
			aInfo.otherPlayerHasReligion = JaxbUtils.marshallBoolean(info.isOtherPlayerHasReligion());
			aInfo.otherPlayerHasOtherReligion = JaxbUtils.marshallBoolean(info.isOtherPlayerHasOtherReligion());
			aInfo.otherPlayerAI = JaxbUtils.marshallBoolean(info.isOtherPlayerAI());
			aInfo.otherPlayerShareBorders = JaxbUtils.marshallInteger(info.getOtherPlayerShareBorders());
			aInfo.otherPlayerHasTech = JaxbUtils.marshallString(info.getOtherPlayerHasTech());
			aInfo.pickCity = JaxbUtils.marshallBoolean(info.isPickCity());
			aInfo.pickOtherPlayerCity = JaxbUtils.marshallBoolean(info.isPickOtherPlayerCity());
			aInfo.showPlot = JaxbUtils.marshallBoolean(info.isShowPlot());
			aInfo.cityFoodWeight = JaxbUtils.marshallInteger(info.getCityFoodWeight());
			aInfo.pythonCanDo = JaxbUtils.marshallString(info.getPythonCanDo());
			aInfo.pythonCanDoCity = JaxbUtils.marshallString(info.getPythonCanDoCity());
			aInfo.pythonCanDoUnit = JaxbUtils.marshallString(info.getPythonCanDoUnit());
			aInfo.pythonCallback = JaxbUtils.marshallString(info.getPythonCallback());

			map.entries.add(aInfo);
		}
		return map;
	}
}