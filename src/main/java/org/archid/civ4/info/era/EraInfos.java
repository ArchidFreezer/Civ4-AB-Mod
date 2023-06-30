package org.archid.civ4.info.era;

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

@XmlRootElement(name="Civ4EraInfos", namespace="x-schema:CIV4GameInfoSchema.xml")
@XmlAccessorType(XmlAccessType.NONE)
public class EraInfos extends AbstractInfos<IEraInfo> {

	public EraInfos() {
		super(new LinkedHashMap<String, IEraInfo>());
	}

	@XmlJavaTypeAdapter(EraMapAdapter.class)
	@XmlElement(name="EraInfos")
	public Map<String, IEraInfo> getInfoMap() {
		return infos;
	}

	public static IEraInfo createInfo(String type) {
		return new EraInfo(type);
	}

	@Override
	public void setInfoMap(Map<String, IEraInfo> infos) {
		this.infos = infos;
	}

	private static class EraInfo implements IEraInfo{

		private String type;
		private String description;
		private String strategy;
		private Boolean noGoodies;
		private Boolean noAnimals;
		private Boolean noBarbUnits;
		private Boolean noBarbCities;
		private Integer advancedStartPoints;
		private Integer startingUnitMultiplier;
		private Integer startingDefenseUnits;
		private Integer startingWorkerUnits;
		private Integer startingExploreUnits;
		private Integer startingGold;
		private Integer maxCities;
		private Integer freePopulation;
		private Integer startPercent;
		private Integer growthPercent;
		private Integer trainPercent;
		private Integer constructPercent;
		private Integer createPercent;
		private Integer researchPercent;
		private Integer techCostModifier;
		private Integer buildPercent;
		private Integer improvementPercent;
		private Integer greatPeoplePercent;
		private Integer culturePercent;
		private Integer anarchyPercent;
		private Integer eventChancePerTurn;
		private Boolean unitRangeUnbound;
		private Boolean unitTerritoryUnbound;
		private Integer unitRangeChange;
		private Integer unitRangeModifier;
		private Integer soundtrackSpace;
		private Boolean firstSoundtrackFirst;
		private List<Integer> naturalYieldLimits = new ArrayList<Integer>();
		private List<String> eraInfoSoundtracks = new ArrayList<String>();
		private List<IPair<String, String>> citySoundscapes = new ArrayList<IPair<String, String>>();
		private String audioUnitVictoryScript;
		private String audioUnitDefeatScript;

		private EraInfo(String type) {
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
		public String getStrategy() {
			return strategy;
		}

		@Override
		public void setStrategy(String strategy) {
			this.strategy = strategy;
		}

		@Override
		public Boolean isNoGoodies() {
			return noGoodies;
		}

		@Override
		public void setNoGoodies(Boolean noGoodies) {
			this.noGoodies = noGoodies;
		}

		@Override
		public Boolean isNoAnimals() {
			return noAnimals;
		}

		@Override
		public void setNoAnimals(Boolean noAnimals) {
			this.noAnimals = noAnimals;
		}

		@Override
		public Boolean isNoBarbUnits() {
			return noBarbUnits;
		}

		@Override
		public void setNoBarbUnits(Boolean noBarbUnits) {
			this.noBarbUnits = noBarbUnits;
		}

		@Override
		public Boolean isNoBarbCities() {
			return noBarbCities;
		}

		@Override
		public void setNoBarbCities(Boolean noBarbCities) {
			this.noBarbCities = noBarbCities;
		}

		@Override
		public Integer getAdvancedStartPoints() {
			return advancedStartPoints;
		}

		@Override
		public void setAdvancedStartPoints(Integer advancedStartPoints) {
			this.advancedStartPoints = advancedStartPoints;
		}

		@Override
		public Integer getStartingUnitMultiplier() {
			return startingUnitMultiplier;
		}

		@Override
		public void setStartingUnitMultiplier(Integer startingUnitMultiplier) {
			this.startingUnitMultiplier = startingUnitMultiplier;
		}

		@Override
		public Integer getStartingDefenseUnits() {
			return startingDefenseUnits;
		}

		@Override
		public void setStartingDefenseUnits(Integer startingDefenseUnits) {
			this.startingDefenseUnits = startingDefenseUnits;
		}

		@Override
		public Integer getStartingWorkerUnits() {
			return startingWorkerUnits;
		}

		@Override
		public void setStartingWorkerUnits(Integer startingWorkerUnits) {
			this.startingWorkerUnits = startingWorkerUnits;
		}

		@Override
		public Integer getStartingExploreUnits() {
			return startingExploreUnits;
		}

		@Override
		public void setStartingExploreUnits(Integer startingExploreUnits) {
			this.startingExploreUnits = startingExploreUnits;
		}

		@Override
		public Integer getStartingGold() {
			return startingGold;
		}

		@Override
		public void setStartingGold(Integer startingGold) {
			this.startingGold = startingGold;
		}

		@Override
		public Integer getMaxCities() {
			return maxCities;
		}

		@Override
		public void setMaxCities(Integer maxCities) {
			this.maxCities = maxCities;
		}

		@Override
		public Integer getFreePopulation() {
			return freePopulation;
		}

		@Override
		public void setFreePopulation(Integer freePopulation) {
			this.freePopulation = freePopulation;
		}

		@Override
		public Integer getStartPercent() {
			return startPercent;
		}

		@Override
		public void setStartPercent(Integer startPercent) {
			this.startPercent = startPercent;
		}

		@Override
		public Integer getGrowthPercent() {
			return growthPercent;
		}

		@Override
		public void setGrowthPercent(Integer growthPercent) {
			this.growthPercent = growthPercent;
		}

		@Override
		public Integer getTrainPercent() {
			return trainPercent;
		}

		@Override
		public void setTrainPercent(Integer trainPercent) {
			this.trainPercent = trainPercent;
		}

		@Override
		public Integer getConstructPercent() {
			return constructPercent;
		}

		@Override
		public void setConstructPercent(Integer constructPercent) {
			this.constructPercent = constructPercent;
		}

		@Override
		public Integer getCreatePercent() {
			return createPercent;
		}

		@Override
		public void setCreatePercent(Integer createPercent) {
			this.createPercent = createPercent;
		}

		@Override
		public Integer getResearchPercent() {
			return researchPercent;
		}

		@Override
		public void setResearchPercent(Integer researchPercent) {
			this.researchPercent = researchPercent;
		}

		@Override
		public Integer getTechCostModifier() {
			return techCostModifier;
		}

		@Override
		public void setTechCostModifier(Integer techCostModifier) {
			this.techCostModifier = techCostModifier;
		}

		@Override
		public Integer getBuildPercent() {
			return buildPercent;
		}

		@Override
		public void setBuildPercent(Integer buildPercent) {
			this.buildPercent = buildPercent;
		}

		@Override
		public Integer getImprovementPercent() {
			return improvementPercent;
		}

		@Override
		public void setImprovementPercent(Integer improvementPercent) {
			this.improvementPercent = improvementPercent;
		}

		@Override
		public Integer getGreatPeoplePercent() {
			return greatPeoplePercent;
		}

		@Override
		public void setGreatPeoplePercent(Integer greatPeoplePercent) {
			this.greatPeoplePercent = greatPeoplePercent;
		}

		@Override
		public Integer getCulturePercent() {
			return culturePercent;
		}

		@Override
		public void setCulturePercent(Integer culturePercent) {
			this.culturePercent = culturePercent;
		}

		@Override
		public Integer getAnarchyPercent() {
			return anarchyPercent;
		}

		@Override
		public void setAnarchyPercent(Integer anarchyPercent) {
			this.anarchyPercent = anarchyPercent;
		}

		@Override
		public Integer getEventChancePerTurn() {
			return eventChancePerTurn;
		}

		@Override
		public void setEventChancePerTurn(Integer eventChancePerTurn) {
			this.eventChancePerTurn = eventChancePerTurn;
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
		public Integer getSoundtrackSpace() {
			return soundtrackSpace;
		}

		@Override
		public void setSoundtrackSpace(Integer soundtrackSpace) {
			this.soundtrackSpace = soundtrackSpace;
		}

		@Override
		public Boolean isFirstSoundtrackFirst() {
			return firstSoundtrackFirst;
		}

		@Override
		public void setFirstSoundtrackFirst(Boolean firstSoundtrackFirst) {
			this.firstSoundtrackFirst = firstSoundtrackFirst;
		}

		@Override
		public List<Integer> getNaturalYieldLimits() {
			return naturalYieldLimits;
		}

		@Override
		public void addNaturalYieldLimit(Integer naturalYieldLimit) {
			this.naturalYieldLimits.add(naturalYieldLimit);
		}

		@Override
		public List<String> getEraInfoSoundtracks() {
			return eraInfoSoundtracks;
		}

		@Override
		public void addEraInfoSoundtrack(String eraInfoSoundtrack) {
			this.eraInfoSoundtracks.add(eraInfoSoundtrack);
		}

		@Override
		public List<IPair<String, String>> getCitySoundscapes() {
			return citySoundscapes;
		}

		@Override
		public void addCitySoundscape(IPair<String, String> citySoundscape) {
			this.citySoundscapes.add(citySoundscape);
		}

		@Override
		public String getAudioUnitVictoryScript() {
			return audioUnitVictoryScript;
		}

		@Override
		public void setAudioUnitVictoryScript(String audioUnitVictoryScript) {
			this.audioUnitVictoryScript = audioUnitVictoryScript;
		}

		@Override
		public String getAudioUnitDefeatScript() {
			return audioUnitDefeatScript;
		}

		@Override
		public void setAudioUnitDefeatScript(String audioUnitDefeatScript) {
			this.audioUnitDefeatScript = audioUnitDefeatScript;
		}
	}
}