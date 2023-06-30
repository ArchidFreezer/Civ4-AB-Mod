package org.archid.civ4.info.buildingclass;

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

@XmlRootElement(name="Civ4BuildingClassInfos", namespace="x-schema:CIV4BuildingsSchema.xml")
@XmlAccessorType(XmlAccessType.NONE)
public class BuildingClassInfos extends AbstractInfos<IBuildingClassInfo> {

	public BuildingClassInfos() {
		super(new LinkedHashMap<String, IBuildingClassInfo>());
	}

	@XmlJavaTypeAdapter(BuildingClassMapAdapter.class)
	@XmlElement(name="BuildingClassInfos")
	public Map<String, IBuildingClassInfo> getInfoMap() {
		return infos;
	}

	public static IBuildingClassInfo createInfo(String type) {
		return new BuildingClassInfo(type);
	}

	@Override
	public void setInfoMap(Map<String, IBuildingClassInfo> infos) {
		this.infos = infos;
	}

	private static class BuildingClassInfo implements IBuildingClassInfo{

		private String type;
		private String description;
		private Integer category;
		private Integer maxGlobalInstances;
		private Integer maxTeamInstances;
		private Integer maxPlayerInstances;
		private Integer extraPlayerInstances;
		private Boolean noLimit;
		private Boolean monument;
		private String defaultBuilding;
		private List<IPair<String, Integer>> victoryThresholds = new ArrayList<IPair<String, Integer>>();

		private BuildingClassInfo(String type) {
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
		public Integer getCategory() {
			return category;
		}

		@Override
		public void setCategory(Integer category) {
			this.category = category;
		}

		@Override
		public Integer getMaxGlobalInstances() {
			return maxGlobalInstances;
		}

		@Override
		public void setMaxGlobalInstances(Integer maxGlobalInstances) {
			this.maxGlobalInstances = maxGlobalInstances;
		}

		@Override
		public Integer getMaxTeamInstances() {
			return maxTeamInstances;
		}

		@Override
		public void setMaxTeamInstances(Integer maxTeamInstances) {
			this.maxTeamInstances = maxTeamInstances;
		}

		@Override
		public Integer getMaxPlayerInstances() {
			return maxPlayerInstances;
		}

		@Override
		public void setMaxPlayerInstances(Integer maxPlayerInstances) {
			this.maxPlayerInstances = maxPlayerInstances;
		}

		@Override
		public Integer getExtraPlayerInstances() {
			return extraPlayerInstances;
		}

		@Override
		public void setExtraPlayerInstances(Integer extraPlayerInstances) {
			this.extraPlayerInstances = extraPlayerInstances;
		}

		@Override
		public Boolean isNoLimit() {
			return noLimit;
		}

		@Override
		public void setNoLimit(Boolean noLimit) {
			this.noLimit = noLimit;
		}

		@Override
		public Boolean isMonument() {
			return monument;
		}

		@Override
		public void setMonument(Boolean monument) {
			this.monument = monument;
		}

		@Override
		public String getDefaultBuilding() {
			return defaultBuilding;
		}

		@Override
		public void setDefaultBuilding(String defaultBuilding) {
			this.defaultBuilding = defaultBuilding;
		}

		@Override
		public List<IPair<String, Integer>> getVictoryThresholds() {
			return victoryThresholds;
		}

		@Override
		public void addVictoryThreshold(IPair<String, Integer> victoryThreshold) {
			this.victoryThresholds.add(victoryThreshold);
		}
	}
}