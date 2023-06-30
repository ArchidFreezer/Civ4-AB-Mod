package org.archid.civ4.info.era;

import java.util.ArrayList;
import java.util.List;
import org.archid.civ4.info.IInfo;
import org.archid.utils.IPair;

public interface IEraInfo extends IInfo {
	String getDescription();
	void setDescription(String description);

	String getStrategy();
	void setStrategy(String strategy);

	Boolean isNoGoodies();
	void setNoGoodies(Boolean noGoodies);

	Boolean isNoAnimals();
	void setNoAnimals(Boolean noAnimals);

	Boolean isNoBarbUnits();
	void setNoBarbUnits(Boolean noBarbUnits);

	Boolean isNoBarbCities();
	void setNoBarbCities(Boolean noBarbCities);

	Integer getAdvancedStartPoints();
	void setAdvancedStartPoints(Integer advancedStartPoints);

	Integer getStartingUnitMultiplier();
	void setStartingUnitMultiplier(Integer startingUnitMultiplier);

	Integer getStartingDefenseUnits();
	void setStartingDefenseUnits(Integer startingDefenseUnits);

	Integer getStartingWorkerUnits();
	void setStartingWorkerUnits(Integer startingWorkerUnits);

	Integer getStartingExploreUnits();
	void setStartingExploreUnits(Integer startingExploreUnits);

	Integer getStartingGold();
	void setStartingGold(Integer startingGold);

	Integer getMaxCities();
	void setMaxCities(Integer maxCities);

	Integer getFreePopulation();
	void setFreePopulation(Integer freePopulation);

	Integer getStartPercent();
	void setStartPercent(Integer startPercent);

	Integer getGrowthPercent();
	void setGrowthPercent(Integer growthPercent);

	Integer getTrainPercent();
	void setTrainPercent(Integer trainPercent);

	Integer getConstructPercent();
	void setConstructPercent(Integer constructPercent);

	Integer getCreatePercent();
	void setCreatePercent(Integer createPercent);

	Integer getResearchPercent();
	void setResearchPercent(Integer researchPercent);

	Integer getTechCostModifier();
	void setTechCostModifier(Integer techCostModifier);

	Integer getBuildPercent();
	void setBuildPercent(Integer buildPercent);

	Integer getImprovementPercent();
	void setImprovementPercent(Integer improvementPercent);

	Integer getGreatPeoplePercent();
	void setGreatPeoplePercent(Integer greatPeoplePercent);

	Integer getCulturePercent();
	void setCulturePercent(Integer culturePercent);

	Integer getAnarchyPercent();
	void setAnarchyPercent(Integer anarchyPercent);

	Integer getEventChancePerTurn();
	void setEventChancePerTurn(Integer eventChancePerTurn);

	Boolean isUnitRangeUnbound();
	void setUnitRangeUnbound(Boolean unitRangeUnbound);

	Boolean isUnitTerritoryUnbound();
	void setUnitTerritoryUnbound(Boolean unitTerritoryUnbound);

	Integer getUnitRangeChange();
	void setUnitRangeChange(Integer unitRangeChange);

	Integer getUnitRangeModifier();
	void setUnitRangeModifier(Integer unitRangeModifier);

	Integer getSoundtrackSpace();
	void setSoundtrackSpace(Integer soundtrackSpace);

	Boolean isFirstSoundtrackFirst();
	void setFirstSoundtrackFirst(Boolean firstSoundtrackFirst);

	List<Integer> getNaturalYieldLimits();
	void addNaturalYieldLimit(Integer naturalYieldLimit);

	List<String> getEraInfoSoundtracks();
	void addEraInfoSoundtrack(String eraInfoSoundtrack);

	List<IPair<String, String>> getCitySoundscapes();
	void addCitySoundscape(IPair<String, String> citySoundscape);

	String getAudioUnitVictoryScript();
	void setAudioUnitVictoryScript(String audioUnitVictoryScript);

	String getAudioUnitDefeatScript();
	void setAudioUnitDefeatScript(String audioUnitDefeatScript);

}