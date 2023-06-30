package org.archid.civ4.info.event;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.adapters.XmlAdapter;
import org.archid.utils.CollectionUtils;
import org.archid.utils.IPair;
import org.archid.utils.ITriple;
import org.archid.utils.JaxbUtils;
import org.archid.utils.Pair;
import org.archid.utils.StringUtils;
import org.archid.utils.Triple;

public class EventMapAdapter extends XmlAdapter<EventMapAdapter.EventMap, Map<String, IEventInfo>> {

	public static class EventMap {
		@XmlElement(name = "EventInfo")
		List<AdaptedEvent> entries = new ArrayList<AdaptedEvent>();
	}

	private static class AdaptedEvent {
		@XmlElement(name="Type")
		private String type;
		@XmlElement(name="Description")
		private String description;
		@XmlElement(name="LocalInfoText")
		private String localInfoText;
		@XmlElementWrapper(name="WorldNewsTexts")
		@XmlElement(name="Text")
		private List<String> worldNewsTexts;
		@XmlElement(name="OtherPlayerPopup")
		private String otherPlayerPopup;
		@XmlElement(name="QuestFailText")
		private String questFailText;
		@XmlElement(name="bQuest")
		private Integer quest;
		@XmlElement(name="bGlobal")
		private Integer global;
		@XmlElement(name="bTeam")
		private Integer team;
		@XmlElement(name="bPickCity")
		private Integer pickCity;
		@XmlElement(name="bPickOtherPlayerCity")
		private Integer pickOtherPlayerCity;
		@XmlElement(name="bDeclareWar")
		private Integer declareWar;
		@XmlElement(name="iGold")
		private Integer gold;
		@XmlElement(name="bGoldToPlayer")
		private Integer goldToPlayer;
		@XmlElement(name="iRandomGold")
		private Integer randomGold;
		@XmlElement(name="iCulture")
		private Integer culture;
		@XmlElement(name="iEspionagePoints")
		private Integer espionagePoints;
		@XmlElement(name="bGoldenAge")
		private Integer goldenAge;
		@XmlElement(name="iFreeUnitSupport")
		private Integer freeUnitSupport;
		@XmlElement(name="iInflationMod")
		private Integer inflationMod;
		@XmlElement(name="iSpaceProductionMod")
		private Integer spaceProductionMod;
		@XmlElement(name="Tech")
		private String tech;
		@XmlElementWrapper(name="TechFlavors")
		@XmlElement(name="TechFlavor")
		private List<AdaptedTechFlavors> techFlavors;
		@XmlElement(name="iTechPercent")
		private Integer techPercent;
		@XmlElement(name="iTechCostPercent")
		private Integer techCostPercent;
		@XmlElement(name="iTechMinTurnsLeft")
		private Integer techMinTurnsLeft;
		@XmlElement(name="PrereqTech")
		private String prereqTech;
		@XmlElement(name="UnitClass")
		private String unitClass;
		@XmlElement(name="iNumFreeUnits")
		private Integer numFreeUnits;
		@XmlElement(name="bDisbandUnit")
		private Integer disbandUnit;
		@XmlElement(name="iUnitExperience")
		private Integer unitExperience;
		@XmlElement(name="iUnitImmobileTurns")
		private Integer unitImmobileTurns;
		@XmlElement(name="UnitPromotion")
		private String unitPromotion;
		@XmlElement(name="UnitName")
		private String unitName;
		@XmlElementWrapper(name="UnitCombatPromotions")
		@XmlElement(name="UnitCombatPromotion")
		private List<AdaptedUnitCombatPromotions> unitCombatPromotions;
		@XmlElementWrapper(name="UnitClassPromotions")
		@XmlElement(name="UnitClassPromotion")
		private List<AdaptedUnitClassPromotions> unitClassPromotions;
		@XmlElement(name="BuildingClass")
		private String buildingClass;
		@XmlElement(name="iBuildingChange")
		private Integer buildingChange;
		@XmlElementWrapper(name="BuildingExtraYields")
		@XmlElement(name="BuildingExtraYield")
		private List<AdaptedBuildingExtraYields> buildingExtraYields;
		@XmlElementWrapper(name="BuildingExtraCommerces")
		@XmlElement(name="BuildingExtraCommerce")
		private List<AdaptedBuildingExtraCommerces> buildingExtraCommerces;
		@XmlElementWrapper(name="BuildingExtraHappies")
		@XmlElement(name="BuildingExtraHappy")
		private List<AdaptedBuildingExtraHappies> buildingExtraHappies;
		@XmlElementWrapper(name="BuildingExtraHealths")
		@XmlElement(name="BuildingExtraHealth")
		private List<AdaptedBuildingExtraHealths> buildingExtraHealths;
		@XmlElement(name="iHappy")
		private Integer happy;
		@XmlElement(name="iHealth")
		private Integer health;
		@XmlElement(name="iHurryAnger")
		private Integer hurryAnger;
		@XmlElement(name="iHappyTurns")
		private Integer happyTurns;
		@XmlElement(name="iRevoltTurns")
		private Integer revoltTurns;
		@XmlElement(name="iMinPillage")
		private Integer minPillage;
		@XmlElement(name="iMaxPillage")
		private Integer maxPillage;
		@XmlElement(name="iFood")
		private Integer food;
		@XmlElement(name="iFoodPercent")
		private Integer foodPercent;
		@XmlElementWrapper(name="FreeSpecialistCounts")
		@XmlElement(name="FreeSpecialistCount")
		private List<AdaptedFreeSpecialistCounts> freeSpecialistCounts;
		@XmlElement(name="FeatureType")
		private String featureType;
		@XmlElement(name="iFeatureChange")
		private Integer featureChange;
		@XmlElement(name="ImprovementType")
		private String improvementType;
		@XmlElement(name="iImprovementChange")
		private Integer improvementChange;
		@XmlElement(name="BonusType")
		private String bonusType;
		@XmlElement(name="iBonusChange")
		private Integer bonusChange;
		@XmlElement(name="RouteType")
		private String routeType;
		@XmlElement(name="iRouteChange")
		private Integer routeChange;
		@XmlElement(name="BonusRevealed")
		private String bonusRevealed;
		@XmlElement(name="BonusGift")
		private String bonusGift;
		@XmlElementWrapper(name="PlotExtraYields")
		@XmlElement(name="PlotExtraYield")
		private List<AdaptedPlotExtraYields> plotExtraYields;
		@XmlElement(name="iConvertOwnCities")
		private Integer convertOwnCities;
		@XmlElement(name="iConvertOtherCities")
		private Integer convertOtherCities;
		@XmlElement(name="iMaxNumReligions")
		private Integer maxNumReligions;
		@XmlElement(name="iOurAttitudeModifier")
		private Integer ourAttitudeModifier;
		@XmlElement(name="iAttitudeModifier")
		private Integer attitudeModifier;
		@XmlElement(name="iTheirEnemyAttitudeModifier")
		private Integer theirEnemyAttitudeModifier;
		@XmlElement(name="iPopulationChange")
		private Integer populationChange;
		@XmlElementWrapper(name="AdditionalEvents")
		@XmlElement(name="EventChance")
		private List<AdaptedAdditionalEvents> additionalEvents;
		@XmlElementWrapper(name="EventTimes")
		@XmlElement(name="EventTime")
		private List<AdaptedEventTimes> eventTimes;
		@XmlElementWrapper(name="ClearEvents")
		@XmlElement(name="EventChance")
		private List<AdaptedClearEvents> clearEvents;
		@XmlElement(name="PythonCallback")
		private String pythonCallback;
		@XmlElement(name="PythonExpireCheck")
		private String pythonExpireCheck;
		@XmlElement(name="PythonCanDo")
		private String pythonCanDo;
		@XmlElement(name="PythonHelp")
		private String pythonHelp;
		@XmlElement(name="Button")
		private String button;
		@XmlElement(name="iAIValue")
		private Integer aIValue;
	}

	private static class AdaptedTechFlavors {
		@XmlElement(name="FlavorType")
		private String flavorType;
		@XmlElement(name="iFlavor")
		private Integer iFlavor;
	}

	private static class AdaptedUnitCombatPromotions {
		@XmlElement(name="UnitCombat")
		private String unitCombat;
		@XmlElement(name="UnitPromotion")
		private String unitPromotion;
	}

	private static class AdaptedUnitClassPromotions {
		@XmlElement(name="UnitClass")
		private String unitClass;
		@XmlElement(name="UnitPromotion")
		private String unitPromotion;
	}

	private static class AdaptedBuildingExtraYields {
		@XmlElement(name="BuildingClass")
		private String buildingClass;
		@XmlElement(name="YieldType")
		private String yieldType;
		@XmlElement(name="iExtraYield")
		private Integer iExtraYield;
	}

	private static class AdaptedBuildingExtraCommerces {
		@XmlElement(name="BuildingClass")
		private String buildingClass;
		@XmlElement(name="CommerceType")
		private String commerceType;
		@XmlElement(name="iExtraCommerce")
		private Integer iExtraCommerce;
	}

	private static class AdaptedBuildingExtraHappies {
		@XmlElement(name="BuildingClass")
		private String buildingClass;
		@XmlElement(name="iHappy")
		private Integer iHappy;
	}

	private static class AdaptedBuildingExtraHealths {
		@XmlElement(name="BuildingClass")
		private String buildingClass;
		@XmlElement(name="iHealth")
		private Integer iHealth;
	}

	private static class AdaptedFreeSpecialistCounts {
		@XmlElement(name="SpecialistType")
		private String specialistType;
		@XmlElement(name="iFreeSpecialistCount")
		private Integer iFreeSpecialistCount;
	}

	private static class AdaptedPlotExtraYields {
		@XmlElement(name="YieldType")
		private String yieldType;
		@XmlElement(name="iExtraYield")
		private Integer iExtraYield;
	}

	private static class AdaptedAdditionalEvents {
		@XmlElement(name="Event")
		private String event;
		@XmlElement(name="iEventChance")
		private Integer iEventChance;
	}

	private static class AdaptedEventTimes {
		@XmlElement(name="Event")
		private String event;
		@XmlElement(name="iEventTime")
		private Integer iEventTime;
	}

	private static class AdaptedClearEvents {
		@XmlElement(name="Event")
		private String event;
		@XmlElement(name="iEventChance")
		private Integer iEventChance;
	}

	@Override
	public Map<String, IEventInfo> unmarshal(EventMap v) throws Exception {
		Map<String, IEventInfo> map = new TreeMap<String, IEventInfo>();
		for (AdaptedEvent aInfo: v.entries) {
			IEventInfo info = EventInfos.createInfo(aInfo.type);
			info.setDescription(JaxbUtils.unmarshallString(aInfo.description));
			info.setLocalInfoText(JaxbUtils.unmarshallString(aInfo.localInfoText));

			if (CollectionUtils.hasElements(aInfo.worldNewsTexts)) {
				for (String val: aInfo.worldNewsTexts) {
					if (StringUtils.hasCharacters(val)) {
						info.addWorldNewsText(val);
					}
				}
			}
			info.setOtherPlayerPopup(JaxbUtils.unmarshallString(aInfo.otherPlayerPopup));
			info.setQuestFailText(JaxbUtils.unmarshallString(aInfo.questFailText));
			info.setQuest(JaxbUtils.unmarshallBoolean(aInfo.quest));
			info.setGlobal(JaxbUtils.unmarshallBoolean(aInfo.global));
			info.setTeam(JaxbUtils.unmarshallBoolean(aInfo.team));
			info.setPickCity(JaxbUtils.unmarshallBoolean(aInfo.pickCity));
			info.setPickOtherPlayerCity(JaxbUtils.unmarshallBoolean(aInfo.pickOtherPlayerCity));
			info.setDeclareWar(JaxbUtils.unmarshallBoolean(aInfo.declareWar));
			info.setGold(JaxbUtils.unmarshallInteger(aInfo.gold));
			info.setGoldToPlayer(JaxbUtils.unmarshallBoolean(aInfo.goldToPlayer));
			info.setRandomGold(JaxbUtils.unmarshallInteger(aInfo.randomGold));
			info.setCulture(JaxbUtils.unmarshallInteger(aInfo.culture));
			info.setEspionagePoints(JaxbUtils.unmarshallInteger(aInfo.espionagePoints));
			info.setGoldenAge(JaxbUtils.unmarshallBoolean(aInfo.goldenAge));
			info.setFreeUnitSupport(JaxbUtils.unmarshallInteger(aInfo.freeUnitSupport));
			info.setInflationMod(JaxbUtils.unmarshallInteger(aInfo.inflationMod));
			info.setSpaceProductionMod(JaxbUtils.unmarshallInteger(aInfo.spaceProductionMod));
			info.setTech(JaxbUtils.unmarshallString(aInfo.tech));

			if (CollectionUtils.hasElements(aInfo.techFlavors)) {
				for (AdaptedTechFlavors adaptor: aInfo.techFlavors) {
					if (StringUtils.hasCharacters(adaptor.flavorType)) {
						info.addTechFlavor(new Pair<String, Integer>(adaptor.flavorType, adaptor.iFlavor));
					}
				}
			}
			info.setTechPercent(JaxbUtils.unmarshallInteger(aInfo.techPercent));
			info.setTechCostPercent(JaxbUtils.unmarshallInteger(aInfo.techCostPercent));
			info.setTechMinTurnsLeft(JaxbUtils.unmarshallInteger(aInfo.techMinTurnsLeft));
			info.setPrereqTech(JaxbUtils.unmarshallString(aInfo.prereqTech));
			info.setUnitClass(JaxbUtils.unmarshallString(aInfo.unitClass));
			info.setNumFreeUnits(JaxbUtils.unmarshallInteger(aInfo.numFreeUnits));
			info.setDisbandUnit(JaxbUtils.unmarshallBoolean(aInfo.disbandUnit));
			info.setUnitExperience(JaxbUtils.unmarshallInteger(aInfo.unitExperience));
			info.setUnitImmobileTurns(JaxbUtils.unmarshallInteger(aInfo.unitImmobileTurns));
			info.setUnitPromotion(JaxbUtils.unmarshallString(aInfo.unitPromotion));
			info.setUnitName(JaxbUtils.unmarshallString(aInfo.unitName));

			if (CollectionUtils.hasElements(aInfo.unitCombatPromotions)) {
				for (AdaptedUnitCombatPromotions adaptor: aInfo.unitCombatPromotions) {
					if (StringUtils.hasCharacters(adaptor.unitCombat)) {
						info.addUnitCombatPromotion(new Pair<String, String>(adaptor.unitCombat, adaptor.unitPromotion));
					}
				}
			}

			if (CollectionUtils.hasElements(aInfo.unitClassPromotions)) {
				for (AdaptedUnitClassPromotions adaptor: aInfo.unitClassPromotions) {
					if (StringUtils.hasCharacters(adaptor.unitClass)) {
						info.addUnitClassPromotion(new Pair<String, String>(adaptor.unitClass, adaptor.unitPromotion));
					}
				}
			}
			info.setBuildingClass(JaxbUtils.unmarshallString(aInfo.buildingClass));
			info.setBuildingChange(JaxbUtils.unmarshallInteger(aInfo.buildingChange));

			if (CollectionUtils.hasElements(aInfo.buildingExtraYields)) {
				for (AdaptedBuildingExtraYields adaptor: aInfo.buildingExtraYields) {
					if (StringUtils.hasCharacters(adaptor.buildingClass)) {
						info.addBuildingExtraYield(new Triple<String, String, Integer>(adaptor.buildingClass, adaptor.yieldType, adaptor.iExtraYield));
					}
				}
			}

			if (CollectionUtils.hasElements(aInfo.buildingExtraCommerces)) {
				for (AdaptedBuildingExtraCommerces adaptor: aInfo.buildingExtraCommerces) {
					if (StringUtils.hasCharacters(adaptor.buildingClass)) {
						info.addBuildingExtraCommerce(new Triple<String, String, Integer>(adaptor.buildingClass, adaptor.commerceType, adaptor.iExtraCommerce));
					}
				}
			}

			if (CollectionUtils.hasElements(aInfo.buildingExtraHappies)) {
				for (AdaptedBuildingExtraHappies adaptor: aInfo.buildingExtraHappies) {
					if (StringUtils.hasCharacters(adaptor.buildingClass)) {
						info.addBuildingExtraHappie(new Pair<String, Integer>(adaptor.buildingClass, adaptor.iHappy));
					}
				}
			}

			if (CollectionUtils.hasElements(aInfo.buildingExtraHealths)) {
				for (AdaptedBuildingExtraHealths adaptor: aInfo.buildingExtraHealths) {
					if (StringUtils.hasCharacters(adaptor.buildingClass)) {
						info.addBuildingExtraHealth(new Pair<String, Integer>(adaptor.buildingClass, adaptor.iHealth));
					}
				}
			}
			info.setHappy(JaxbUtils.unmarshallInteger(aInfo.happy));
			info.setHealth(JaxbUtils.unmarshallInteger(aInfo.health));
			info.setHurryAnger(JaxbUtils.unmarshallInteger(aInfo.hurryAnger));
			info.setHappyTurns(JaxbUtils.unmarshallInteger(aInfo.happyTurns));
			info.setRevoltTurns(JaxbUtils.unmarshallInteger(aInfo.revoltTurns));
			info.setMinPillage(JaxbUtils.unmarshallInteger(aInfo.minPillage));
			info.setMaxPillage(JaxbUtils.unmarshallInteger(aInfo.maxPillage));
			info.setFood(JaxbUtils.unmarshallInteger(aInfo.food));
			info.setFoodPercent(JaxbUtils.unmarshallInteger(aInfo.foodPercent));

			if (CollectionUtils.hasElements(aInfo.freeSpecialistCounts)) {
				for (AdaptedFreeSpecialistCounts adaptor: aInfo.freeSpecialistCounts) {
					if (StringUtils.hasCharacters(adaptor.specialistType)) {
						info.addFreeSpecialistCount(new Pair<String, Integer>(adaptor.specialistType, adaptor.iFreeSpecialistCount));
					}
				}
			}
			info.setFeatureType(JaxbUtils.unmarshallString(aInfo.featureType));
			info.setFeatureChange(JaxbUtils.unmarshallInteger(aInfo.featureChange));
			info.setImprovementType(JaxbUtils.unmarshallString(aInfo.improvementType));
			info.setImprovementChange(JaxbUtils.unmarshallInteger(aInfo.improvementChange));
			info.setBonusType(JaxbUtils.unmarshallString(aInfo.bonusType));
			info.setBonusChange(JaxbUtils.unmarshallInteger(aInfo.bonusChange));
			info.setRouteType(JaxbUtils.unmarshallString(aInfo.routeType));
			info.setRouteChange(JaxbUtils.unmarshallInteger(aInfo.routeChange));
			info.setBonusRevealed(JaxbUtils.unmarshallString(aInfo.bonusRevealed));
			info.setBonusGift(JaxbUtils.unmarshallString(aInfo.bonusGift));

			if (CollectionUtils.hasElements(aInfo.plotExtraYields)) {
				for (AdaptedPlotExtraYields adaptor: aInfo.plotExtraYields) {
					if (StringUtils.hasCharacters(adaptor.yieldType)) {
						info.addPlotExtraYield(new Pair<String, Integer>(adaptor.yieldType, adaptor.iExtraYield));
					}
				}
			}
			info.setConvertOwnCities(JaxbUtils.unmarshallInteger(aInfo.convertOwnCities));
			info.setConvertOtherCities(JaxbUtils.unmarshallInteger(aInfo.convertOtherCities));
			info.setMaxNumReligions(JaxbUtils.unmarshallInteger(aInfo.maxNumReligions));
			info.setOurAttitudeModifier(JaxbUtils.unmarshallInteger(aInfo.ourAttitudeModifier));
			info.setAttitudeModifier(JaxbUtils.unmarshallInteger(aInfo.attitudeModifier));
			info.setTheirEnemyAttitudeModifier(JaxbUtils.unmarshallInteger(aInfo.theirEnemyAttitudeModifier));
			info.setPopulationChange(JaxbUtils.unmarshallInteger(aInfo.populationChange));

			if (CollectionUtils.hasElements(aInfo.additionalEvents)) {
				for (AdaptedAdditionalEvents adaptor: aInfo.additionalEvents) {
					if (StringUtils.hasCharacters(adaptor.event)) {
						info.addAdditionalEvent(new Pair<String, Integer>(adaptor.event, adaptor.iEventChance));
					}
				}
			}

			if (CollectionUtils.hasElements(aInfo.eventTimes)) {
				for (AdaptedEventTimes adaptor: aInfo.eventTimes) {
					if (StringUtils.hasCharacters(adaptor.event)) {
						info.addEventTime(new Pair<String, Integer>(adaptor.event, adaptor.iEventTime));
					}
				}
			}

			if (CollectionUtils.hasElements(aInfo.clearEvents)) {
				for (AdaptedClearEvents adaptor: aInfo.clearEvents) {
					if (StringUtils.hasCharacters(adaptor.event)) {
						info.addClearEvent(new Pair<String, Integer>(adaptor.event, adaptor.iEventChance));
					}
				}
			}
			info.setPythonCallback(JaxbUtils.unmarshallString(aInfo.pythonCallback));
			info.setPythonExpireCheck(JaxbUtils.unmarshallString(aInfo.pythonExpireCheck));
			info.setPythonCanDo(JaxbUtils.unmarshallString(aInfo.pythonCanDo));
			info.setPythonHelp(JaxbUtils.unmarshallString(aInfo.pythonHelp));
			info.setButton(JaxbUtils.unmarshallString(aInfo.button));
			info.setAIValue(JaxbUtils.unmarshallInteger(aInfo.aIValue));

			map.put(aInfo.type, info);
		}
		return map;
	}

	@Override
	public EventMap marshal(Map<String, IEventInfo> v) throws Exception {
		EventMap map = new EventMap();
		for (IEventInfo info: v.values()) {
			AdaptedEvent aInfo = new AdaptedEvent();
			aInfo.type = JaxbUtils.marshallString(info.getType());
			aInfo.description = JaxbUtils.marshallString(info.getDescription());
			aInfo.localInfoText = JaxbUtils.marshallString(info.getLocalInfoText());

			if (CollectionUtils.hasElements(info.getWorldNewsTexts())) {
				aInfo.worldNewsTexts = new ArrayList<String>();
				for(String val: info.getWorldNewsTexts()) {
					aInfo.worldNewsTexts.add(val);
				}
			}
			aInfo.otherPlayerPopup = JaxbUtils.marshallString(info.getOtherPlayerPopup());
			aInfo.questFailText = JaxbUtils.marshallString(info.getQuestFailText());
			aInfo.quest = JaxbUtils.marshallBoolean(info.isQuest());
			aInfo.global = JaxbUtils.marshallBoolean(info.isGlobal());
			aInfo.team = JaxbUtils.marshallBoolean(info.isTeam());
			aInfo.pickCity = JaxbUtils.marshallBoolean(info.isPickCity());
			aInfo.pickOtherPlayerCity = JaxbUtils.marshallBoolean(info.isPickOtherPlayerCity());
			aInfo.declareWar = JaxbUtils.marshallBoolean(info.isDeclareWar());
			aInfo.gold = JaxbUtils.marshallInteger(info.getGold());
			aInfo.goldToPlayer = JaxbUtils.marshallBoolean(info.isGoldToPlayer());
			aInfo.randomGold = JaxbUtils.marshallInteger(info.getRandomGold());
			aInfo.culture = JaxbUtils.marshallInteger(info.getCulture());
			aInfo.espionagePoints = JaxbUtils.marshallInteger(info.getEspionagePoints());
			aInfo.goldenAge = JaxbUtils.marshallBoolean(info.isGoldenAge());
			aInfo.freeUnitSupport = JaxbUtils.marshallInteger(info.getFreeUnitSupport());
			aInfo.inflationMod = JaxbUtils.marshallInteger(info.getInflationMod());
			aInfo.spaceProductionMod = JaxbUtils.marshallInteger(info.getSpaceProductionMod());
			aInfo.tech = JaxbUtils.marshallString(info.getTech());

			if (CollectionUtils.hasElements(info.getTechFlavors())) {
				aInfo.techFlavors = new ArrayList<AdaptedTechFlavors>();
				for (IPair<String, Integer> pair: info.getTechFlavors()) {
					AdaptedTechFlavors adaptor = new AdaptedTechFlavors();
					adaptor.flavorType = pair.getKey();
					adaptor.iFlavor = pair.getValue();
					aInfo.techFlavors.add(adaptor);
				}
			}
			aInfo.techPercent = JaxbUtils.marshallInteger(info.getTechPercent());
			aInfo.techCostPercent = JaxbUtils.marshallInteger(info.getTechCostPercent());
			aInfo.techMinTurnsLeft = JaxbUtils.marshallInteger(info.getTechMinTurnsLeft());
			aInfo.prereqTech = JaxbUtils.marshallString(info.getPrereqTech());
			aInfo.unitClass = JaxbUtils.marshallString(info.getUnitClass());
			aInfo.numFreeUnits = JaxbUtils.marshallInteger(info.getNumFreeUnits());
			aInfo.disbandUnit = JaxbUtils.marshallBoolean(info.isDisbandUnit());
			aInfo.unitExperience = JaxbUtils.marshallInteger(info.getUnitExperience());
			aInfo.unitImmobileTurns = JaxbUtils.marshallInteger(info.getUnitImmobileTurns());
			aInfo.unitPromotion = JaxbUtils.marshallString(info.getUnitPromotion());
			aInfo.unitName = JaxbUtils.marshallString(info.getUnitName());

			if (CollectionUtils.hasElements(info.getUnitCombatPromotions())) {
				aInfo.unitCombatPromotions = new ArrayList<AdaptedUnitCombatPromotions>();
				for (IPair<String, String> pair: info.getUnitCombatPromotions()) {
					AdaptedUnitCombatPromotions adaptor = new AdaptedUnitCombatPromotions();
					adaptor.unitCombat = pair.getKey();
					adaptor.unitPromotion = pair.getValue();
					aInfo.unitCombatPromotions.add(adaptor);
				}
			}

			if (CollectionUtils.hasElements(info.getUnitClassPromotions())) {
				aInfo.unitClassPromotions = new ArrayList<AdaptedUnitClassPromotions>();
				for (IPair<String, String> pair: info.getUnitClassPromotions()) {
					AdaptedUnitClassPromotions adaptor = new AdaptedUnitClassPromotions();
					adaptor.unitClass = pair.getKey();
					adaptor.unitPromotion = pair.getValue();
					aInfo.unitClassPromotions.add(adaptor);
				}
			}
			aInfo.buildingClass = JaxbUtils.marshallString(info.getBuildingClass());
			aInfo.buildingChange = JaxbUtils.marshallInteger(info.getBuildingChange());

			if (CollectionUtils.hasElements(info.getBuildingExtraYields())) {
				aInfo.buildingExtraYields = new ArrayList<AdaptedBuildingExtraYields>();
				for (ITriple<String, String, Integer> triple: info.getBuildingExtraYields()) {
					AdaptedBuildingExtraYields adaptor = new AdaptedBuildingExtraYields();
					adaptor.buildingClass = triple.getKey();
					adaptor.yieldType = triple.getValue();
					adaptor.iExtraYield = triple.getData();
					aInfo.buildingExtraYields.add(adaptor);
				}
			}

			if (CollectionUtils.hasElements(info.getBuildingExtraCommerces())) {
				aInfo.buildingExtraCommerces = new ArrayList<AdaptedBuildingExtraCommerces>();
				for (ITriple<String, String, Integer> triple: info.getBuildingExtraCommerces()) {
					AdaptedBuildingExtraCommerces adaptor = new AdaptedBuildingExtraCommerces();
					adaptor.buildingClass = triple.getKey();
					adaptor.commerceType = triple.getValue();
					adaptor.iExtraCommerce = triple.getData();
					aInfo.buildingExtraCommerces.add(adaptor);
				}
			}

			if (CollectionUtils.hasElements(info.getBuildingExtraHappies())) {
				aInfo.buildingExtraHappies = new ArrayList<AdaptedBuildingExtraHappies>();
				for (IPair<String, Integer> pair: info.getBuildingExtraHappies()) {
					AdaptedBuildingExtraHappies adaptor = new AdaptedBuildingExtraHappies();
					adaptor.buildingClass = pair.getKey();
					adaptor.iHappy = pair.getValue();
					aInfo.buildingExtraHappies.add(adaptor);
				}
			}

			if (CollectionUtils.hasElements(info.getBuildingExtraHealths())) {
				aInfo.buildingExtraHealths = new ArrayList<AdaptedBuildingExtraHealths>();
				for (IPair<String, Integer> pair: info.getBuildingExtraHealths()) {
					AdaptedBuildingExtraHealths adaptor = new AdaptedBuildingExtraHealths();
					adaptor.buildingClass = pair.getKey();
					adaptor.iHealth = pair.getValue();
					aInfo.buildingExtraHealths.add(adaptor);
				}
			}
			aInfo.happy = JaxbUtils.marshallInteger(info.getHappy());
			aInfo.health = JaxbUtils.marshallInteger(info.getHealth());
			aInfo.hurryAnger = JaxbUtils.marshallInteger(info.getHurryAnger());
			aInfo.happyTurns = JaxbUtils.marshallInteger(info.getHappyTurns());
			aInfo.revoltTurns = JaxbUtils.marshallInteger(info.getRevoltTurns());
			aInfo.minPillage = JaxbUtils.marshallInteger(info.getMinPillage());
			aInfo.maxPillage = JaxbUtils.marshallInteger(info.getMaxPillage());
			aInfo.food = JaxbUtils.marshallInteger(info.getFood());
			aInfo.foodPercent = JaxbUtils.marshallInteger(info.getFoodPercent());

			if (CollectionUtils.hasElements(info.getFreeSpecialistCounts())) {
				aInfo.freeSpecialistCounts = new ArrayList<AdaptedFreeSpecialistCounts>();
				for (IPair<String, Integer> pair: info.getFreeSpecialistCounts()) {
					AdaptedFreeSpecialistCounts adaptor = new AdaptedFreeSpecialistCounts();
					adaptor.specialistType = pair.getKey();
					adaptor.iFreeSpecialistCount = pair.getValue();
					aInfo.freeSpecialistCounts.add(adaptor);
				}
			}
			aInfo.featureType = JaxbUtils.marshallString(info.getFeatureType());
			aInfo.featureChange = JaxbUtils.marshallInteger(info.getFeatureChange());
			aInfo.improvementType = JaxbUtils.marshallString(info.getImprovementType());
			aInfo.improvementChange = JaxbUtils.marshallInteger(info.getImprovementChange());
			aInfo.bonusType = JaxbUtils.marshallString(info.getBonusType());
			aInfo.bonusChange = JaxbUtils.marshallInteger(info.getBonusChange());
			aInfo.routeType = JaxbUtils.marshallString(info.getRouteType());
			aInfo.routeChange = JaxbUtils.marshallInteger(info.getRouteChange());
			aInfo.bonusRevealed = JaxbUtils.marshallString(info.getBonusRevealed());
			aInfo.bonusGift = JaxbUtils.marshallString(info.getBonusGift());

			if (CollectionUtils.hasElements(info.getPlotExtraYields())) {
				aInfo.plotExtraYields = new ArrayList<AdaptedPlotExtraYields>();
				for (IPair<String, Integer> pair: info.getPlotExtraYields()) {
					AdaptedPlotExtraYields adaptor = new AdaptedPlotExtraYields();
					adaptor.yieldType = pair.getKey();
					adaptor.iExtraYield = pair.getValue();
					aInfo.plotExtraYields.add(adaptor);
				}
			}
			aInfo.convertOwnCities = JaxbUtils.marshallInteger(info.getConvertOwnCities());
			aInfo.convertOtherCities = JaxbUtils.marshallInteger(info.getConvertOtherCities());
			aInfo.maxNumReligions = JaxbUtils.marshallInteger(info.getMaxNumReligions());
			aInfo.ourAttitudeModifier = JaxbUtils.marshallInteger(info.getOurAttitudeModifier());
			aInfo.attitudeModifier = JaxbUtils.marshallInteger(info.getAttitudeModifier());
			aInfo.theirEnemyAttitudeModifier = JaxbUtils.marshallInteger(info.getTheirEnemyAttitudeModifier());
			aInfo.populationChange = JaxbUtils.marshallInteger(info.getPopulationChange());

			if (CollectionUtils.hasElements(info.getAdditionalEvents())) {
				aInfo.additionalEvents = new ArrayList<AdaptedAdditionalEvents>();
				for (IPair<String, Integer> pair: info.getAdditionalEvents()) {
					AdaptedAdditionalEvents adaptor = new AdaptedAdditionalEvents();
					adaptor.event = pair.getKey();
					adaptor.iEventChance = pair.getValue();
					aInfo.additionalEvents.add(adaptor);
				}
			}

			if (CollectionUtils.hasElements(info.getEventTimes())) {
				aInfo.eventTimes = new ArrayList<AdaptedEventTimes>();
				for (IPair<String, Integer> pair: info.getEventTimes()) {
					AdaptedEventTimes adaptor = new AdaptedEventTimes();
					adaptor.event = pair.getKey();
					adaptor.iEventTime = pair.getValue();
					aInfo.eventTimes.add(adaptor);
				}
			}

			if (CollectionUtils.hasElements(info.getClearEvents())) {
				aInfo.clearEvents = new ArrayList<AdaptedClearEvents>();
				for (IPair<String, Integer> pair: info.getClearEvents()) {
					AdaptedClearEvents adaptor = new AdaptedClearEvents();
					adaptor.event = pair.getKey();
					adaptor.iEventChance = pair.getValue();
					aInfo.clearEvents.add(adaptor);
				}
			}
			aInfo.pythonCallback = JaxbUtils.marshallString(info.getPythonCallback());
			aInfo.pythonExpireCheck = JaxbUtils.marshallString(info.getPythonExpireCheck());
			aInfo.pythonCanDo = JaxbUtils.marshallString(info.getPythonCanDo());
			aInfo.pythonHelp = JaxbUtils.marshallString(info.getPythonHelp());
			aInfo.button = JaxbUtils.marshallString(info.getButton());
			aInfo.aIValue = JaxbUtils.marshallInteger(info.getAIValue());

			map.entries.add(aInfo);
		}
		return map;
	}
}