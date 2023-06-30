package org.archid.civ4.info.buildingclass;

import java.util.ArrayList;
import java.util.List;
import org.archid.civ4.info.IInfo;
import org.archid.utils.IPair;

public interface IBuildingClassInfo extends IInfo {
	String getDescription();
	void setDescription(String description);

	Integer getCategory();
	void setCategory(Integer category);

	Integer getMaxGlobalInstances();
	void setMaxGlobalInstances(Integer maxGlobalInstances);

	Integer getMaxTeamInstances();
	void setMaxTeamInstances(Integer maxTeamInstances);

	Integer getMaxPlayerInstances();
	void setMaxPlayerInstances(Integer maxPlayerInstances);

	Integer getExtraPlayerInstances();
	void setExtraPlayerInstances(Integer extraPlayerInstances);

	Boolean isNoLimit();
	void setNoLimit(Boolean noLimit);

	Boolean isMonument();
	void setMonument(Boolean monument);

	String getDefaultBuilding();
	void setDefaultBuilding(String defaultBuilding);

	List<IPair<String, Integer>> getVictoryThresholds();
	void addVictoryThreshold(IPair<String, Integer> victoryThreshold);

}