package org.archid.civ4.info.buildingclass;

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

public class BuildingClassMapAdapter extends XmlAdapter<BuildingClassMapAdapter.BuildingClassMap, Map<String, IBuildingClassInfo>> {

	public static class BuildingClassMap {
		@XmlElement(name = "BuildingClassInfo")
		List<AdaptedBuildingClass> entries = new ArrayList<AdaptedBuildingClass>();
	}

	private static class AdaptedBuildingClass {
		@XmlElement(name="Type")
		private String type;
		@XmlElement(name="Description")
		private String description;
		@XmlElement(name="iCategory")
		private Integer category;
		@XmlElement(name="iMaxGlobalInstances")
		private Integer maxGlobalInstances;
		@XmlElement(name="iMaxTeamInstances")
		private Integer maxTeamInstances;
		@XmlElement(name="iMaxPlayerInstances")
		private Integer maxPlayerInstances;
		@XmlElement(name="iExtraPlayerInstances")
		private Integer extraPlayerInstances;
		@XmlElement(name="bNoLimit")
		private Integer noLimit;
		@XmlElement(name="bMonument")
		private Integer monument;
		@XmlElement(name="DefaultBuilding")
		private String defaultBuilding;
		@XmlElementWrapper(name="VictoryThresholds")
		@XmlElement(name="VictoryThreshold")
		private List<AdaptedVictoryThresholds> victoryThresholds;
	}

	private static class AdaptedVictoryThresholds {
		@XmlElement(name="VictoryType")
		private String victoryType;
		@XmlElement(name="iThreshold")
		private Integer iThreshold;
	}

	@Override
	public Map<String, IBuildingClassInfo> unmarshal(BuildingClassMap v) throws Exception {
		Map<String, IBuildingClassInfo> map = new TreeMap<String, IBuildingClassInfo>();
		for (AdaptedBuildingClass aInfo: v.entries) {
			IBuildingClassInfo info = BuildingClassInfos.createInfo(aInfo.type);
			info.setDescription(JaxbUtils.unmarshallString(aInfo.description));
			info.setCategory(JaxbUtils.unmarshallInteger(aInfo.category));
			info.setMaxGlobalInstances(JaxbUtils.unmarshallInteger(aInfo.maxGlobalInstances));
			info.setMaxTeamInstances(JaxbUtils.unmarshallInteger(aInfo.maxTeamInstances));
			info.setMaxPlayerInstances(JaxbUtils.unmarshallInteger(aInfo.maxPlayerInstances));
			info.setExtraPlayerInstances(JaxbUtils.unmarshallInteger(aInfo.extraPlayerInstances));
			info.setNoLimit(JaxbUtils.unmarshallBoolean(aInfo.noLimit));
			info.setMonument(JaxbUtils.unmarshallBoolean(aInfo.monument));
			info.setDefaultBuilding(JaxbUtils.unmarshallString(aInfo.defaultBuilding));

			if (CollectionUtils.hasElements(aInfo.victoryThresholds)) {
				for (AdaptedVictoryThresholds adaptor: aInfo.victoryThresholds) {
					if (StringUtils.hasCharacters(adaptor.victoryType)) {
						info.addVictoryThreshold(new Pair<String, Integer>(adaptor.victoryType, adaptor.iThreshold));
					}
				}
			}

			map.put(aInfo.type, info);
		}
		return map;
	}

	@Override
	public BuildingClassMap marshal(Map<String, IBuildingClassInfo> v) throws Exception {
		BuildingClassMap map = new BuildingClassMap();
		for (IBuildingClassInfo info: v.values()) {
			AdaptedBuildingClass aInfo = new AdaptedBuildingClass();
			aInfo.type = JaxbUtils.marshallString(info.getType());
			aInfo.description = JaxbUtils.marshallMandatoryString(info.getDescription());
			aInfo.category = JaxbUtils.marshallInteger(info.getCategory());
			aInfo.maxGlobalInstances = JaxbUtils.marshallInteger(info.getMaxGlobalInstances());
			aInfo.maxTeamInstances = JaxbUtils.marshallInteger(info.getMaxTeamInstances());
			aInfo.maxPlayerInstances = JaxbUtils.marshallInteger(info.getMaxPlayerInstances());
			aInfo.extraPlayerInstances = JaxbUtils.marshallInteger(info.getExtraPlayerInstances());
			aInfo.noLimit = JaxbUtils.marshallBoolean(info.isNoLimit());
			aInfo.monument = JaxbUtils.marshallBoolean(info.isMonument());
			aInfo.defaultBuilding = JaxbUtils.marshallMandatoryString(info.getDefaultBuilding());

			if (CollectionUtils.hasElements(info.getVictoryThresholds())) {
				aInfo.victoryThresholds = new ArrayList<AdaptedVictoryThresholds>();
				for (IPair<String, Integer> pair: info.getVictoryThresholds()) {
					AdaptedVictoryThresholds adaptor = new AdaptedVictoryThresholds();
					adaptor.victoryType = pair.getKey();
					adaptor.iThreshold = pair.getValue();
					aInfo.victoryThresholds.add(adaptor);
				}
			}

			map.entries.add(aInfo);
		}
		return map;
	}
}