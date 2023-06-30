package org.archid.civ4.info.event;

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
import org.archid.utils.ITriple;

@XmlRootElement(name="Civ4EventInfos", namespace="x-schema:CIV4EventSchema.xml")
@XmlAccessorType(XmlAccessType.NONE)
public class EventInfos extends AbstractInfos<IEventInfo> {

	public EventInfos() {
		super(new LinkedHashMap<String, IEventInfo>());
	}

	@XmlJavaTypeAdapter(EventMapAdapter.class)
	@XmlElement(name="EventInfos")
	public Map<String, IEventInfo> getInfoMap() {
		return infos;
	}

	public static IEventInfo createInfo(String type) {
		return new EventInfo(type);
	}

	@Override
	public void setInfoMap(Map<String, IEventInfo> infos) {
		this.infos = infos;
	}

	private static class EventInfo implements IEventInfo{

		private String type;
		private String description;
		private String localInfoText;
		private List<String> worldNewsTexts = new ArrayList<String>();
		private String otherPlayerPopup;
		private String questFailText;
		private Boolean quest;
		private Boolean global;
		private Boolean team;
		private Boolean pickCity;
		private Boolean pickOtherPlayerCity;
		private Boolean declareWar;
		private Integer gold;
		private Boolean goldToPlayer;
		private Integer randomGold;
		private Integer culture;
		private Integer espionagePoints;
		private Boolean goldenAge;
		private Integer freeUnitSupport;
		private Integer inflationMod;
		private Integer spaceProductionMod;
		private String tech;
		private List<IPair<String, Integer>> techFlavors = new ArrayList<IPair<String, Integer>>();
		private Integer techPercent;
		private Integer techCostPercent;
		private Integer techMinTurnsLeft;
		private String prereqTech;
		private String unitClass;
		private Integer numFreeUnits;
		private Boolean disbandUnit;
		private Integer unitExperience;
		private Integer unitImmobileTurns;
		private String unitPromotion;
		private String unitName;
		private List<IPair<String, String>> unitCombatPromotions = new ArrayList<IPair<String, String>>();
		private List<IPair<String, String>> unitClassPromotions = new ArrayList<IPair<String, String>>();
		private String buildingClass;
		private Integer buildingChange;
		private List<ITriple<String, String, Integer>> buildingExtraYields = new ArrayList<ITriple<String, String, Integer>>();
		private List<ITriple<String, String, Integer>> buildingExtraCommerces = new ArrayList<ITriple<String, String, Integer>>();
		private List<IPair<String, Integer>> buildingExtraHappies = new ArrayList<IPair<String, Integer>>();
		private List<IPair<String, Integer>> buildingExtraHealths = new ArrayList<IPair<String, Integer>>();
		private Integer happy;
		private Integer health;
		private Integer hurryAnger;
		private Integer happyTurns;
		private Integer revoltTurns;
		private Integer minPillage;
		private Integer maxPillage;
		private Integer food;
		private Integer foodPercent;
		private List<IPair<String, Integer>> freeSpecialistCounts = new ArrayList<IPair<String, Integer>>();
		private String featureType;
		private Integer featureChange;
		private String improvementType;
		private Integer improvementChange;
		private String bonusType;
		private Integer bonusChange;
		private String routeType;
		private Integer routeChange;
		private String bonusRevealed;
		private String bonusGift;
		private List<IPair<String, Integer>> plotExtraYields = new ArrayList<IPair<String, Integer>>();
		private Integer convertOwnCities;
		private Integer convertOtherCities;
		private Integer maxNumReligions;
		private Integer ourAttitudeModifier;
		private Integer attitudeModifier;
		private Integer theirEnemyAttitudeModifier;
		private Integer populationChange;
		private List<IPair<String, Integer>> additionalEvents = new ArrayList<IPair<String, Integer>>();
		private List<IPair<String, Integer>> eventTimes = new ArrayList<IPair<String, Integer>>();
		private List<IPair<String, Integer>> clearEvents = new ArrayList<IPair<String, Integer>>();
		private String pythonCallback;
		private String pythonExpireCheck;
		private String pythonCanDo;
		private String pythonHelp;
		private String button;
		private Integer aIValue;

		private EventInfo(String type) {
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
		public String getLocalInfoText() {
			return localInfoText;
		}

		@Override
		public void setLocalInfoText(String localInfoText) {
			this.localInfoText = localInfoText;
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
		public String getOtherPlayerPopup() {
			return otherPlayerPopup;
		}

		@Override
		public void setOtherPlayerPopup(String otherPlayerPopup) {
			this.otherPlayerPopup = otherPlayerPopup;
		}

		@Override
		public String getQuestFailText() {
			return questFailText;
		}

		@Override
		public void setQuestFailText(String questFailText) {
			this.questFailText = questFailText;
		}

		@Override
		public Boolean isQuest() {
			return quest;
		}

		@Override
		public void setQuest(Boolean quest) {
			this.quest = quest;
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
		public Boolean isTeam() {
			return team;
		}

		@Override
		public void setTeam(Boolean team) {
			this.team = team;
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
		public Boolean isDeclareWar() {
			return declareWar;
		}

		@Override
		public void setDeclareWar(Boolean declareWar) {
			this.declareWar = declareWar;
		}

		@Override
		public Integer getGold() {
			return gold;
		}

		@Override
		public void setGold(Integer gold) {
			this.gold = gold;
		}

		@Override
		public Boolean isGoldToPlayer() {
			return goldToPlayer;
		}

		@Override
		public void setGoldToPlayer(Boolean goldToPlayer) {
			this.goldToPlayer = goldToPlayer;
		}

		@Override
		public Integer getRandomGold() {
			return randomGold;
		}

		@Override
		public void setRandomGold(Integer randomGold) {
			this.randomGold = randomGold;
		}

		@Override
		public Integer getCulture() {
			return culture;
		}

		@Override
		public void setCulture(Integer culture) {
			this.culture = culture;
		}

		@Override
		public Integer getEspionagePoints() {
			return espionagePoints;
		}

		@Override
		public void setEspionagePoints(Integer espionagePoints) {
			this.espionagePoints = espionagePoints;
		}

		@Override
		public Boolean isGoldenAge() {
			return goldenAge;
		}

		@Override
		public void setGoldenAge(Boolean goldenAge) {
			this.goldenAge = goldenAge;
		}

		@Override
		public Integer getFreeUnitSupport() {
			return freeUnitSupport;
		}

		@Override
		public void setFreeUnitSupport(Integer freeUnitSupport) {
			this.freeUnitSupport = freeUnitSupport;
		}

		@Override
		public Integer getInflationMod() {
			return inflationMod;
		}

		@Override
		public void setInflationMod(Integer inflationMod) {
			this.inflationMod = inflationMod;
		}

		@Override
		public Integer getSpaceProductionMod() {
			return spaceProductionMod;
		}

		@Override
		public void setSpaceProductionMod(Integer spaceProductionMod) {
			this.spaceProductionMod = spaceProductionMod;
		}

		@Override
		public String getTech() {
			return tech;
		}

		@Override
		public void setTech(String tech) {
			this.tech = tech;
		}

		@Override
		public List<IPair<String, Integer>> getTechFlavors() {
			return techFlavors;
		}

		@Override
		public void addTechFlavor(IPair<String, Integer> techFlavor) {
			this.techFlavors.add(techFlavor);
		}

		@Override
		public Integer getTechPercent() {
			return techPercent;
		}

		@Override
		public void setTechPercent(Integer techPercent) {
			this.techPercent = techPercent;
		}

		@Override
		public Integer getTechCostPercent() {
			return techCostPercent;
		}

		@Override
		public void setTechCostPercent(Integer techCostPercent) {
			this.techCostPercent = techCostPercent;
		}

		@Override
		public Integer getTechMinTurnsLeft() {
			return techMinTurnsLeft;
		}

		@Override
		public void setTechMinTurnsLeft(Integer techMinTurnsLeft) {
			this.techMinTurnsLeft = techMinTurnsLeft;
		}

		@Override
		public String getPrereqTech() {
			return prereqTech;
		}

		@Override
		public void setPrereqTech(String prereqTech) {
			this.prereqTech = prereqTech;
		}

		@Override
		public String getUnitClass() {
			return unitClass;
		}

		@Override
		public void setUnitClass(String unitClass) {
			this.unitClass = unitClass;
		}

		@Override
		public Integer getNumFreeUnits() {
			return numFreeUnits;
		}

		@Override
		public void setNumFreeUnits(Integer numFreeUnits) {
			this.numFreeUnits = numFreeUnits;
		}

		@Override
		public Boolean isDisbandUnit() {
			return disbandUnit;
		}

		@Override
		public void setDisbandUnit(Boolean disbandUnit) {
			this.disbandUnit = disbandUnit;
		}

		@Override
		public Integer getUnitExperience() {
			return unitExperience;
		}

		@Override
		public void setUnitExperience(Integer unitExperience) {
			this.unitExperience = unitExperience;
		}

		@Override
		public Integer getUnitImmobileTurns() {
			return unitImmobileTurns;
		}

		@Override
		public void setUnitImmobileTurns(Integer unitImmobileTurns) {
			this.unitImmobileTurns = unitImmobileTurns;
		}

		@Override
		public String getUnitPromotion() {
			return unitPromotion;
		}

		@Override
		public void setUnitPromotion(String unitPromotion) {
			this.unitPromotion = unitPromotion;
		}

		@Override
		public String getUnitName() {
			return unitName;
		}

		@Override
		public void setUnitName(String unitName) {
			this.unitName = unitName;
		}

		@Override
		public List<IPair<String, String>> getUnitCombatPromotions() {
			return unitCombatPromotions;
		}

		@Override
		public void addUnitCombatPromotion(IPair<String, String> unitCombatPromotion) {
			this.unitCombatPromotions.add(unitCombatPromotion);
		}

		@Override
		public List<IPair<String, String>> getUnitClassPromotions() {
			return unitClassPromotions;
		}

		@Override
		public void addUnitClassPromotion(IPair<String, String> unitClassPromotion) {
			this.unitClassPromotions.add(unitClassPromotion);
		}

		@Override
		public String getBuildingClass() {
			return buildingClass;
		}

		@Override
		public void setBuildingClass(String buildingClass) {
			this.buildingClass = buildingClass;
		}

		@Override
		public Integer getBuildingChange() {
			return buildingChange;
		}

		@Override
		public void setBuildingChange(Integer buildingChange) {
			this.buildingChange = buildingChange;
		}

		@Override
		public List<ITriple<String, String, Integer>> getBuildingExtraYields() {
			return buildingExtraYields;
		}

		@Override
		public void addBuildingExtraYield(ITriple<String, String, Integer> buildingExtraYield) {
			this.buildingExtraYields.add(buildingExtraYield);
		}

		@Override
		public List<ITriple<String, String, Integer>> getBuildingExtraCommerces() {
			return buildingExtraCommerces;
		}

		@Override
		public void addBuildingExtraCommerce(ITriple<String, String, Integer> buildingExtraCommerce) {
			this.buildingExtraCommerces.add(buildingExtraCommerce);
		}

		@Override
		public List<IPair<String, Integer>> getBuildingExtraHappies() {
			return buildingExtraHappies;
		}

		@Override
		public void addBuildingExtraHappie(IPair<String, Integer> buildingExtraHappie) {
			this.buildingExtraHappies.add(buildingExtraHappie);
		}

		@Override
		public List<IPair<String, Integer>> getBuildingExtraHealths() {
			return buildingExtraHealths;
		}

		@Override
		public void addBuildingExtraHealth(IPair<String, Integer> buildingExtraHealth) {
			this.buildingExtraHealths.add(buildingExtraHealth);
		}

		@Override
		public Integer getHappy() {
			return happy;
		}

		@Override
		public void setHappy(Integer happy) {
			this.happy = happy;
		}

		@Override
		public Integer getHealth() {
			return health;
		}

		@Override
		public void setHealth(Integer health) {
			this.health = health;
		}

		@Override
		public Integer getHurryAnger() {
			return hurryAnger;
		}

		@Override
		public void setHurryAnger(Integer hurryAnger) {
			this.hurryAnger = hurryAnger;
		}

		@Override
		public Integer getHappyTurns() {
			return happyTurns;
		}

		@Override
		public void setHappyTurns(Integer happyTurns) {
			this.happyTurns = happyTurns;
		}

		@Override
		public Integer getRevoltTurns() {
			return revoltTurns;
		}

		@Override
		public void setRevoltTurns(Integer revoltTurns) {
			this.revoltTurns = revoltTurns;
		}

		@Override
		public Integer getMinPillage() {
			return minPillage;
		}

		@Override
		public void setMinPillage(Integer minPillage) {
			this.minPillage = minPillage;
		}

		@Override
		public Integer getMaxPillage() {
			return maxPillage;
		}

		@Override
		public void setMaxPillage(Integer maxPillage) {
			this.maxPillage = maxPillage;
		}

		@Override
		public Integer getFood() {
			return food;
		}

		@Override
		public void setFood(Integer food) {
			this.food = food;
		}

		@Override
		public Integer getFoodPercent() {
			return foodPercent;
		}

		@Override
		public void setFoodPercent(Integer foodPercent) {
			this.foodPercent = foodPercent;
		}

		@Override
		public List<IPair<String, Integer>> getFreeSpecialistCounts() {
			return freeSpecialistCounts;
		}

		@Override
		public void addFreeSpecialistCount(IPair<String, Integer> freeSpecialistCount) {
			this.freeSpecialistCounts.add(freeSpecialistCount);
		}

		@Override
		public String getFeatureType() {
			return featureType;
		}

		@Override
		public void setFeatureType(String featureType) {
			this.featureType = featureType;
		}

		@Override
		public Integer getFeatureChange() {
			return featureChange;
		}

		@Override
		public void setFeatureChange(Integer featureChange) {
			this.featureChange = featureChange;
		}

		@Override
		public String getImprovementType() {
			return improvementType;
		}

		@Override
		public void setImprovementType(String improvementType) {
			this.improvementType = improvementType;
		}

		@Override
		public Integer getImprovementChange() {
			return improvementChange;
		}

		@Override
		public void setImprovementChange(Integer improvementChange) {
			this.improvementChange = improvementChange;
		}

		@Override
		public String getBonusType() {
			return bonusType;
		}

		@Override
		public void setBonusType(String bonusType) {
			this.bonusType = bonusType;
		}

		@Override
		public Integer getBonusChange() {
			return bonusChange;
		}

		@Override
		public void setBonusChange(Integer bonusChange) {
			this.bonusChange = bonusChange;
		}

		@Override
		public String getRouteType() {
			return routeType;
		}

		@Override
		public void setRouteType(String routeType) {
			this.routeType = routeType;
		}

		@Override
		public Integer getRouteChange() {
			return routeChange;
		}

		@Override
		public void setRouteChange(Integer routeChange) {
			this.routeChange = routeChange;
		}

		@Override
		public String getBonusRevealed() {
			return bonusRevealed;
		}

		@Override
		public void setBonusRevealed(String bonusRevealed) {
			this.bonusRevealed = bonusRevealed;
		}

		@Override
		public String getBonusGift() {
			return bonusGift;
		}

		@Override
		public void setBonusGift(String bonusGift) {
			this.bonusGift = bonusGift;
		}

		@Override
		public List<IPair<String, Integer>> getPlotExtraYields() {
			return plotExtraYields;
		}

		@Override
		public void addPlotExtraYield(IPair<String, Integer> plotExtraYield) {
			this.plotExtraYields.add(plotExtraYield);
		}

		@Override
		public Integer getConvertOwnCities() {
			return convertOwnCities;
		}

		@Override
		public void setConvertOwnCities(Integer convertOwnCities) {
			this.convertOwnCities = convertOwnCities;
		}

		@Override
		public Integer getConvertOtherCities() {
			return convertOtherCities;
		}

		@Override
		public void setConvertOtherCities(Integer convertOtherCities) {
			this.convertOtherCities = convertOtherCities;
		}

		@Override
		public Integer getMaxNumReligions() {
			return maxNumReligions;
		}

		@Override
		public void setMaxNumReligions(Integer maxNumReligions) {
			this.maxNumReligions = maxNumReligions;
		}

		@Override
		public Integer getOurAttitudeModifier() {
			return ourAttitudeModifier;
		}

		@Override
		public void setOurAttitudeModifier(Integer ourAttitudeModifier) {
			this.ourAttitudeModifier = ourAttitudeModifier;
		}

		@Override
		public Integer getAttitudeModifier() {
			return attitudeModifier;
		}

		@Override
		public void setAttitudeModifier(Integer attitudeModifier) {
			this.attitudeModifier = attitudeModifier;
		}

		@Override
		public Integer getTheirEnemyAttitudeModifier() {
			return theirEnemyAttitudeModifier;
		}

		@Override
		public void setTheirEnemyAttitudeModifier(Integer theirEnemyAttitudeModifier) {
			this.theirEnemyAttitudeModifier = theirEnemyAttitudeModifier;
		}

		@Override
		public Integer getPopulationChange() {
			return populationChange;
		}

		@Override
		public void setPopulationChange(Integer populationChange) {
			this.populationChange = populationChange;
		}

		@Override
		public List<IPair<String, Integer>> getAdditionalEvents() {
			return additionalEvents;
		}

		@Override
		public void addAdditionalEvent(IPair<String, Integer> additionalEvent) {
			this.additionalEvents.add(additionalEvent);
		}

		@Override
		public List<IPair<String, Integer>> getEventTimes() {
			return eventTimes;
		}

		@Override
		public void addEventTime(IPair<String, Integer> eventTime) {
			this.eventTimes.add(eventTime);
		}

		@Override
		public List<IPair<String, Integer>> getClearEvents() {
			return clearEvents;
		}

		@Override
		public void addClearEvent(IPair<String, Integer> clearEvent) {
			this.clearEvents.add(clearEvent);
		}

		@Override
		public String getPythonCallback() {
			return pythonCallback;
		}

		@Override
		public void setPythonCallback(String pythonCallback) {
			this.pythonCallback = pythonCallback;
		}

		@Override
		public String getPythonExpireCheck() {
			return pythonExpireCheck;
		}

		@Override
		public void setPythonExpireCheck(String pythonExpireCheck) {
			this.pythonExpireCheck = pythonExpireCheck;
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
		public String getPythonHelp() {
			return pythonHelp;
		}

		@Override
		public void setPythonHelp(String pythonHelp) {
			this.pythonHelp = pythonHelp;
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
		public Integer getAIValue() {
			return aIValue;
		}

		@Override
		public void setAIValue(Integer aIValue) {
			this.aIValue = aIValue;
		}
	}
}