package org.archid.civ4.info.bonus;

import java.util.ArrayList;
import java.util.List;
import org.archid.civ4.info.IInfo;

public interface IBonusInfo extends IInfo {
	String getDescription();
	void setDescription(String description);

	String getCivilopedia();
	void setCivilopedia(String civilopedia);

	String getHelp();
	void setHelp(String help);

	String getBonusClassType();
	void setBonusClassType(String bonusClassType);

	String getArtDefineTag();
	void setArtDefineTag(String artDefineTag);

	String getTechReveal();
	void setTechReveal(String techReveal);

	String getTechCityTrade();
	void setTechCityTrade(String techCityTrade);

	String getTechObsolete();
	void setTechObsolete(String techObsolete);

	List<Integer> getYieldChanges();
	void addYieldChange(Integer yieldChange);

	Integer getAITradeModifier();
	void setAITradeModifier(Integer aITradeModifier);

	Integer getAIObjective();
	void setAIObjective(Integer aIObjective);

	Integer getHealth();
	void setHealth(Integer health);

	Integer getHappiness();
	void setHappiness(Integer happiness);

	Integer getPlacementOrder();
	void setPlacementOrder(Integer placementOrder);

	Integer getConstAppearance();
	void setConstAppearance(Integer constAppearance);

	Integer getMinAreaSize();
	void setMinAreaSize(Integer minAreaSize);

	Integer getMinLatitude();
	void setMinLatitude(Integer minLatitude);

	Integer getMaxLatitude();
	void setMaxLatitude(Integer maxLatitude);

	Rands getRands();
	void setRands(Rands rands);

	Integer getPlayer();
	void setPlayer(Integer player);

	Integer getTilesPer();
	void setTilesPer(Integer tilesPer);

	Integer getMinLandPercent();
	void setMinLandPercent(Integer minLandPercent);

	Integer getUnique();
	void setUnique(Integer unique);

	Integer getGroupRange();
	void setGroupRange(Integer groupRange);

	Integer getGroupRand();
	void setGroupRand(Integer groupRand);

	Boolean isArea();
	void setArea(Boolean area);

	Boolean isHills();
	void setHills(Boolean hills);

	Boolean isPeaks();
	void setPeaks(Boolean peaks);

	Boolean isFlatlands();
	void setFlatlands(Boolean flatlands);

	Boolean isNoRiverSide();
	void setNoRiverSide(Boolean noRiverSide);

	Boolean isNormalize();
	void setNormalize(Boolean normalize);

	List<String> getTerrainBooleans();
	void addTerrainBoolean(String terrainBoolean);

	List<String> getFeatureBooleans();
	void addFeatureBoolean(String featureBoolean);

	List<String> getFeatureTerrainBooleans();
	void addFeatureTerrainBoolean(String featureTerrainBoolean);

	Boolean isUseLSystem();
	void setUseLSystem(Boolean useLSystem);

}