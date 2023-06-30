package org.archid.civ4.info.era;

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

public class EraMapAdapter extends XmlAdapter<EraMapAdapter.EraMap, Map<String, IEraInfo>> {

	public static class EraMap {
		@XmlElement(name = "EraInfo")
		List<AdaptedEra> entries = new ArrayList<AdaptedEra>();
	}

	private static class AdaptedEra {
		@XmlElement(name="Type")
		private String type;
		@XmlElement(name="Description")
		private String description;
		@XmlElement(name="Strategy")
		private String strategy;
		@XmlElement(name="bNoGoodies")
		private Integer noGoodies;
		@XmlElement(name="bNoAnimals")
		private Integer noAnimals;
		@XmlElement(name="bNoBarbUnits")
		private Integer noBarbUnits;
		@XmlElement(name="bNoBarbCities")
		private Integer noBarbCities;
		@XmlElement(name="iAdvancedStartPoints")
		private Integer advancedStartPoints;
		@XmlElement(name="iStartingUnitMultiplier")
		private Integer startingUnitMultiplier;
		@XmlElement(name="iStartingDefenseUnits")
		private Integer startingDefenseUnits;
		@XmlElement(name="iStartingWorkerUnits")
		private Integer startingWorkerUnits;
		@XmlElement(name="iStartingExploreUnits")
		private Integer startingExploreUnits;
		@XmlElement(name="iStartingGold")
		private Integer startingGold;
		@XmlElement(name="iMaxCities")
		private Integer maxCities;
		@XmlElement(name="iFreePopulation")
		private Integer freePopulation;
		@XmlElement(name="iStartPercent")
		private Integer startPercent;
		@XmlElement(name="iGrowthPercent")
		private Integer growthPercent;
		@XmlElement(name="iTrainPercent")
		private Integer trainPercent;
		@XmlElement(name="iConstructPercent")
		private Integer constructPercent;
		@XmlElement(name="iCreatePercent")
		private Integer createPercent;
		@XmlElement(name="iResearchPercent")
		private Integer researchPercent;
		@XmlElement(name="iTechCostModifier")
		private Integer techCostModifier;
		@XmlElement(name="iBuildPercent")
		private Integer buildPercent;
		@XmlElement(name="iImprovementPercent")
		private Integer improvementPercent;
		@XmlElement(name="iGreatPeoplePercent")
		private Integer greatPeoplePercent;
		@XmlElement(name="iCulturePercent")
		private Integer culturePercent;
		@XmlElement(name="iAnarchyPercent")
		private Integer anarchyPercent;
		@XmlElement(name="iEventChancePerTurn")
		private Integer eventChancePerTurn;
		@XmlElement(name="bUnitRangeUnbound")
		private Integer unitRangeUnbound;
		@XmlElement(name="bUnitTerritoryUnbound")
		private Integer unitTerritoryUnbound;
		@XmlElement(name="iUnitRangeChange")
		private Integer unitRangeChange;
		@XmlElement(name="iUnitRangeModifier")
		private Integer unitRangeModifier;
		@XmlElement(name="iSoundtrackSpace")
		private Integer soundtrackSpace;
		@XmlElement(name="bFirstSoundtrackFirst")
		private Integer firstSoundtrackFirst;
		@XmlElementWrapper(name="NaturalYieldLimits")
		@XmlElement(name="iYield")
		private List<Integer> naturalYieldLimits;
		@XmlElementWrapper(name="EraInfoSoundtracks")
		@XmlElement(name="EraInfoSoundtrack")
		private List<String> eraInfoSoundtracks;
		@XmlElementWrapper(name="CitySoundscapes")
		@XmlElement(name="CitySoundscape")
		private List<AdaptedCitySoundscapes> citySoundscapes;
		@XmlElement(name="AudioUnitVictoryScript")
		private String audioUnitVictoryScript;
		@XmlElement(name="AudioUnitDefeatScript")
		private String audioUnitDefeatScript;
	}

	private static class AdaptedCitySoundscapes {
		@XmlElement(name="CitySizeType")
		private String citySizeType;
		@XmlElement(name="SoundscapeScript")
		private String soundscapeScript;
	}

	@Override
	public Map<String, IEraInfo> unmarshal(EraMap v) throws Exception {
		Map<String, IEraInfo> map = new TreeMap<String, IEraInfo>();
		for (AdaptedEra aInfo: v.entries) {
			IEraInfo info = EraInfos.createInfo(aInfo.type);
			info.setDescription(JaxbUtils.unmarshallString(aInfo.description));
			info.setStrategy(JaxbUtils.unmarshallString(aInfo.strategy));
			info.setNoGoodies(JaxbUtils.unmarshallBoolean(aInfo.noGoodies));
			info.setNoAnimals(JaxbUtils.unmarshallBoolean(aInfo.noAnimals));
			info.setNoBarbUnits(JaxbUtils.unmarshallBoolean(aInfo.noBarbUnits));
			info.setNoBarbCities(JaxbUtils.unmarshallBoolean(aInfo.noBarbCities));
			info.setAdvancedStartPoints(JaxbUtils.unmarshallInteger(aInfo.advancedStartPoints));
			info.setStartingUnitMultiplier(JaxbUtils.unmarshallInteger(aInfo.startingUnitMultiplier));
			info.setStartingDefenseUnits(JaxbUtils.unmarshallInteger(aInfo.startingDefenseUnits));
			info.setStartingWorkerUnits(JaxbUtils.unmarshallInteger(aInfo.startingWorkerUnits));
			info.setStartingExploreUnits(JaxbUtils.unmarshallInteger(aInfo.startingExploreUnits));
			info.setStartingGold(JaxbUtils.unmarshallInteger(aInfo.startingGold));
			info.setMaxCities(JaxbUtils.unmarshallInteger(aInfo.maxCities));
			info.setFreePopulation(JaxbUtils.unmarshallInteger(aInfo.freePopulation));
			info.setStartPercent(JaxbUtils.unmarshallInteger(aInfo.startPercent));
			info.setGrowthPercent(JaxbUtils.unmarshallInteger(aInfo.growthPercent));
			info.setTrainPercent(JaxbUtils.unmarshallInteger(aInfo.trainPercent));
			info.setConstructPercent(JaxbUtils.unmarshallInteger(aInfo.constructPercent));
			info.setCreatePercent(JaxbUtils.unmarshallInteger(aInfo.createPercent));
			info.setResearchPercent(JaxbUtils.unmarshallInteger(aInfo.researchPercent));
			info.setTechCostModifier(JaxbUtils.unmarshallInteger(aInfo.techCostModifier));
			info.setBuildPercent(JaxbUtils.unmarshallInteger(aInfo.buildPercent));
			info.setImprovementPercent(JaxbUtils.unmarshallInteger(aInfo.improvementPercent));
			info.setGreatPeoplePercent(JaxbUtils.unmarshallInteger(aInfo.greatPeoplePercent));
			info.setCulturePercent(JaxbUtils.unmarshallInteger(aInfo.culturePercent));
			info.setAnarchyPercent(JaxbUtils.unmarshallInteger(aInfo.anarchyPercent));
			info.setEventChancePerTurn(JaxbUtils.unmarshallInteger(aInfo.eventChancePerTurn));
			info.setUnitRangeUnbound(JaxbUtils.unmarshallBoolean(aInfo.unitRangeUnbound));
			info.setUnitTerritoryUnbound(JaxbUtils.unmarshallBoolean(aInfo.unitTerritoryUnbound));
			info.setUnitRangeChange(JaxbUtils.unmarshallInteger(aInfo.unitRangeChange));
			info.setUnitRangeModifier(JaxbUtils.unmarshallInteger(aInfo.unitRangeModifier));
			info.setSoundtrackSpace(JaxbUtils.unmarshallInteger(aInfo.soundtrackSpace));
			info.setFirstSoundtrackFirst(JaxbUtils.unmarshallBoolean(aInfo.firstSoundtrackFirst));

			if (CollectionUtils.hasElements(aInfo.naturalYieldLimits)) {
				for (Integer val: aInfo.naturalYieldLimits) {
					info.addNaturalYieldLimit(val);
				}
			}

			if (CollectionUtils.hasElements(aInfo.eraInfoSoundtracks)) {
				for (String val: aInfo.eraInfoSoundtracks) {
					if (StringUtils.hasCharacters(val)) {
						info.addEraInfoSoundtrack(val);
					}
				}
			}

			if (CollectionUtils.hasElements(aInfo.citySoundscapes)) {
				for (AdaptedCitySoundscapes adaptor: aInfo.citySoundscapes) {
					if (StringUtils.hasCharacters(adaptor.citySizeType)) {
						info.addCitySoundscape(new Pair<String, String>(adaptor.citySizeType, adaptor.soundscapeScript));
					}
				}
			}
			info.setAudioUnitVictoryScript(JaxbUtils.unmarshallString(aInfo.audioUnitVictoryScript));
			info.setAudioUnitDefeatScript(JaxbUtils.unmarshallString(aInfo.audioUnitDefeatScript));

			map.put(aInfo.type, info);
		}
		return map;
	}

	@Override
	public EraMap marshal(Map<String, IEraInfo> v) throws Exception {
		EraMap map = new EraMap();
		for (IEraInfo info: v.values()) {
			AdaptedEra aInfo = new AdaptedEra();
			aInfo.type = JaxbUtils.marshallString(info.getType());
			aInfo.description = JaxbUtils.marshallString(info.getDescription());
			aInfo.strategy = JaxbUtils.marshallString(info.getStrategy());
			aInfo.noGoodies = JaxbUtils.marshallBoolean(info.isNoGoodies());
			aInfo.noAnimals = JaxbUtils.marshallBoolean(info.isNoAnimals());
			aInfo.noBarbUnits = JaxbUtils.marshallBoolean(info.isNoBarbUnits());
			aInfo.noBarbCities = JaxbUtils.marshallBoolean(info.isNoBarbCities());
			aInfo.advancedStartPoints = JaxbUtils.marshallInteger(info.getAdvancedStartPoints());
			aInfo.startingUnitMultiplier = JaxbUtils.marshallInteger(info.getStartingUnitMultiplier());
			aInfo.startingDefenseUnits = JaxbUtils.marshallInteger(info.getStartingDefenseUnits());
			aInfo.startingWorkerUnits = JaxbUtils.marshallInteger(info.getStartingWorkerUnits());
			aInfo.startingExploreUnits = JaxbUtils.marshallInteger(info.getStartingExploreUnits());
			aInfo.startingGold = JaxbUtils.marshallInteger(info.getStartingGold());
			aInfo.maxCities = JaxbUtils.marshallInteger(info.getMaxCities());
			aInfo.freePopulation = JaxbUtils.marshallInteger(info.getFreePopulation());
			aInfo.startPercent = JaxbUtils.marshallInteger(info.getStartPercent());
			aInfo.growthPercent = JaxbUtils.marshallInteger(info.getGrowthPercent());
			aInfo.trainPercent = JaxbUtils.marshallInteger(info.getTrainPercent());
			aInfo.constructPercent = JaxbUtils.marshallInteger(info.getConstructPercent());
			aInfo.createPercent = JaxbUtils.marshallInteger(info.getCreatePercent());
			aInfo.researchPercent = JaxbUtils.marshallInteger(info.getResearchPercent());
			aInfo.techCostModifier = JaxbUtils.marshallInteger(info.getTechCostModifier());
			aInfo.buildPercent = JaxbUtils.marshallInteger(info.getBuildPercent());
			aInfo.improvementPercent = JaxbUtils.marshallInteger(info.getImprovementPercent());
			aInfo.greatPeoplePercent = JaxbUtils.marshallInteger(info.getGreatPeoplePercent());
			aInfo.culturePercent = JaxbUtils.marshallInteger(info.getCulturePercent());
			aInfo.anarchyPercent = JaxbUtils.marshallInteger(info.getAnarchyPercent());
			aInfo.eventChancePerTurn = JaxbUtils.marshallInteger(info.getEventChancePerTurn());
			aInfo.unitRangeUnbound = JaxbUtils.marshallBoolean(info.isUnitRangeUnbound());
			aInfo.unitTerritoryUnbound = JaxbUtils.marshallBoolean(info.isUnitTerritoryUnbound());
			aInfo.unitRangeChange = JaxbUtils.marshallInteger(info.getUnitRangeChange());
			aInfo.unitRangeModifier = JaxbUtils.marshallInteger(info.getUnitRangeModifier());
			aInfo.soundtrackSpace = JaxbUtils.marshallInteger(info.getSoundtrackSpace());
			aInfo.firstSoundtrackFirst = JaxbUtils.marshallBoolean(info.isFirstSoundtrackFirst());

			if (CollectionUtils.hasElements(info.getNaturalYieldLimits())) {
				aInfo.naturalYieldLimits = new ArrayList<Integer>();
				for(Integer val: info.getNaturalYieldLimits()) {
					aInfo.naturalYieldLimits.add(val);
				}
			}

			if (CollectionUtils.hasElements(info.getEraInfoSoundtracks())) {
				aInfo.eraInfoSoundtracks = new ArrayList<String>();
				for(String val: info.getEraInfoSoundtracks()) {
					aInfo.eraInfoSoundtracks.add(val);
				}
			}

			if (CollectionUtils.hasElements(info.getCitySoundscapes())) {
				aInfo.citySoundscapes = new ArrayList<AdaptedCitySoundscapes>();
				for (IPair<String, String> pair: info.getCitySoundscapes()) {
					AdaptedCitySoundscapes adaptor = new AdaptedCitySoundscapes();
					adaptor.citySizeType = pair.getKey();
					adaptor.soundscapeScript = pair.getValue();
					aInfo.citySoundscapes.add(adaptor);
				}
			}
			aInfo.audioUnitVictoryScript = JaxbUtils.marshallString(info.getAudioUnitVictoryScript());
			aInfo.audioUnitDefeatScript = JaxbUtils.marshallString(info.getAudioUnitDefeatScript());

			map.entries.add(aInfo);
		}
		return map;
	}
}