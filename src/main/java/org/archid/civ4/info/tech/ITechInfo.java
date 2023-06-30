package org.archid.civ4.info.tech;

import java.util.ArrayList;
import java.util.List;
import org.archid.civ4.info.IInfo;
import org.archid.utils.IPair;

public interface ITechInfo extends IInfo {
	String getDescription();
	void setDescription(String description);

	String getCivilopedia();
	void setCivilopedia(String civilopedia);

	String getHelp();
	void setHelp(String help);

	String getStrategy();
	void setStrategy(String strategy);

	String getAdvisor();
	void setAdvisor(String advisor);

	Integer getAIWeight();
	void setAIWeight(Integer aIWeight);

	Integer getAITradeModifier();
	void setAITradeModifier(Integer aITradeModifier);

	Integer getCost();
	void setCost(Integer cost);

	Integer getAdvancedStartCost();
	void setAdvancedStartCost(Integer advancedStartCost);

	Integer getAdvancedStartCostIncrease();
	void setAdvancedStartCostIncrease(Integer advancedStartCostIncrease);

	String getEra();
	void setEra(String era);

	Boolean isCivSettled();
	void setCivSettled(Boolean civSettled);

	String getFreeCapitalBuildingClass();
	void setFreeCapitalBuildingClass(String freeCapitalBuildingClass);

	String getFirstFreeUnitClass();
	void setFirstFreeUnitClass(String firstFreeUnitClass);

	String getFreeUnitClass();
	void setFreeUnitClass(String freeUnitClass);

	Integer getFeatureProductionModifier();
	void setFeatureProductionModifier(Integer featureProductionModifier);

	Integer getWorkerSpeedModifier();
	void setWorkerSpeedModifier(Integer workerSpeedModifier);

	Integer getTradeRoutes();
	void setTradeRoutes(Integer tradeRoutes);

	Integer getHealth();
	void setHealth(Integer health);

	Integer getHappiness();
	void setHappiness(Integer happiness);

	Integer getFirstFreeTechs();
	void setFirstFreeTechs(Integer firstFreeTechs);

	Integer getAsset();
	void setAsset(Integer asset);

	Integer getPower();
	void setPower(Integer power);

	Boolean isRepeat();
	void setRepeat(Boolean repeat);

	Boolean isTrade();
	void setTrade(Boolean trade);

	Boolean isEmbassyTrading();
	void setEmbassyTrading(Boolean embassyTrading);

	Boolean isFreeTradeAgreementTrading();
	void setFreeTradeAgreementTrading(Boolean freeTradeAgreementTrading);

	Boolean isNonAggressionTrading();
	void setNonAggressionTrading(Boolean nonAggressionTrading);

	Boolean isDisable();
	void setDisable(Boolean disable);

	Boolean isGoodyTech();
	void setGoodyTech(Boolean goodyTech);

	Boolean isExtraWaterSeeFrom();
	void setExtraWaterSeeFrom(Boolean extraWaterSeeFrom);

	Boolean isMapCentering();
	void setMapCentering(Boolean mapCentering);

	Boolean isMapVisible();
	void setMapVisible(Boolean mapVisible);

	Boolean isMapTrading();
	void setMapTrading(Boolean mapTrading);

	Boolean isTechTrading();
	void setTechTrading(Boolean techTrading);

	Boolean isGoldTrading();
	void setGoldTrading(Boolean goldTrading);

	Boolean isOpenBordersTrading();
	void setOpenBordersTrading(Boolean openBordersTrading);

	Boolean isLimitedBordersTrading();
	void setLimitedBordersTrading(Boolean limitedBordersTrading);

	Boolean isDefensivePactTrading();
	void setDefensivePactTrading(Boolean defensivePactTrading);

	Boolean isPermanentAllianceTrading();
	void setPermanentAllianceTrading(Boolean permanentAllianceTrading);

	Boolean isVassalTrading();
	void setVassalTrading(Boolean vassalTrading);

	Boolean isBridgeBuilding();
	void setBridgeBuilding(Boolean bridgeBuilding);

	Boolean isIrrigation();
	void setIrrigation(Boolean irrigation);

	Boolean isIgnoreIrrigation();
	void setIgnoreIrrigation(Boolean ignoreIrrigation);

	Boolean isWaterWork();
	void setWaterWork(Boolean waterWork);

	Boolean isCanPassPeaks();
	void setCanPassPeaks(Boolean canPassPeaks);

	Boolean isMoveFastPeaks();
	void setMoveFastPeaks(Boolean moveFastPeaks);

	Boolean isCanFoundOnPeaks();
	void setCanFoundOnPeaks(Boolean canFoundOnPeaks);

	Integer getGridX();
	void setGridX(Integer gridX);

	Integer getGridY();
	void setGridY(Integer gridY);

	List<IPair<String, Integer>> getDomainExtraMoves();
	void addDomainExtraMove(IPair<String, Integer> domainExtraMove);

	List<Integer> getCommerceModifiers();
	void addCommerceModifier(Integer commerceModifier);

	List<Integer> getSpecialistExtraCommerces();
	void addSpecialistExtraCommerce(Integer specialistExtraCommerce);

	List<Boolean> getCommerceFlexible();
	void addCommerceFlexible(Boolean commerceFlexible);

	List<String> getTerrainTrades();
	void addTerrainTrade(String terrainTrade);

	Boolean isRiverTrade();
	void setRiverTrade(Boolean riverTrade);

	Boolean isCaptureCities();
	void setCaptureCities(Boolean captureCities);

	Boolean isUnitRangeUnbound();
	void setUnitRangeUnbound(Boolean unitRangeUnbound);

	Boolean isUnitTerritoryUnbound();
	void setUnitTerritoryUnbound(Boolean unitTerritoryUnbound);

	Integer getUnitRangeChange();
	void setUnitRangeChange(Integer unitRangeChange);

	Integer getUnitRangeModifier();
	void setUnitRangeModifier(Integer unitRangeModifier);

	Integer getCultureDefenceModifier();
	void setCultureDefenceModifier(Integer cultureDefenceModifier);

	List<Integer> getForestPlotYieldChanges();
	void addForestPlotYieldChange(Integer forestPlotYieldChange);

	List<Integer> getRiverPlotYieldChanges();
	void addRiverPlotYieldChange(Integer riverPlotYieldChange);

	List<Integer> getSeaPlotYieldChanges();
	void addSeaPlotYieldChange(Integer seaPlotYieldChange);

	List<IPair<String, Integer>> getWorldViewRevoltTurnChanges();
	void addWorldViewRevoltTurnChange(IPair<String, Integer> worldViewRevoltTurnChange);

	List<IPair<String, Integer>> getFlavors();
	void addFlavor(IPair<String, Integer> flavor);

	List<String> getOrPreReqs();
	void addOrPreReq(String orPreReq);

	List<String> getAndPreReqs();
	void addAndPreReq(String andPreReq);

	List<String> getEnabledWorldViews();
	void addEnabledWorldView(String enabledWorldView);

	String getQuote();
	void setQuote(String quote);

	String getSound();
	void setSound(String sound);

	String getSoundMP();
	void setSoundMP(String soundMP);

	String getButton();
	void setButton(String button);

}