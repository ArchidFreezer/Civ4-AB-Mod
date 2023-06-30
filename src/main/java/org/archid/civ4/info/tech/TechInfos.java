package org.archid.civ4.info.tech;

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

@XmlRootElement(name="Civ4TechInfos", namespace="x-schema:CIV4TechnologiesSchema.xml")
@XmlAccessorType(XmlAccessType.NONE)
public class TechInfos extends AbstractInfos<ITechInfo> {

	public TechInfos() {
		super(new LinkedHashMap<String, ITechInfo>());
	}

	@XmlJavaTypeAdapter(TechMapAdapter.class)
	@XmlElement(name="TechInfos")
	public Map<String, ITechInfo> getInfoMap() {
		return infos;
	}

	public static ITechInfo createInfo(String type) {
		return new TechInfo(type);
	}

	@Override
	public void setInfoMap(Map<String, ITechInfo> infos) {
		this.infos = infos;
	}

	private static class TechInfo implements ITechInfo{

		private String type;
		private String description;
		private String civilopedia;
		private String help;
		private String strategy;
		private String advisor;
		private Integer aIWeight;
		private Integer aITradeModifier;
		private Integer cost;
		private Integer advancedStartCost;
		private Integer advancedStartCostIncrease;
		private String era;
		private Boolean civSettled;
		private String freeCapitalBuildingClass;
		private String firstFreeUnitClass;
		private String freeUnitClass;
		private Integer featureProductionModifier;
		private Integer workerSpeedModifier;
		private Integer tradeRoutes;
		private Integer health;
		private Integer happiness;
		private Integer firstFreeTechs;
		private Integer asset;
		private Integer power;
		private Boolean repeat;
		private Boolean trade;
		private Boolean embassyTrading;
		private Boolean freeTradeAgreementTrading;
		private Boolean nonAggressionTrading;
		private Boolean disable;
		private Boolean goodyTech;
		private Boolean extraWaterSeeFrom;
		private Boolean mapCentering;
		private Boolean mapVisible;
		private Boolean mapTrading;
		private Boolean techTrading;
		private Boolean goldTrading;
		private Boolean openBordersTrading;
		private Boolean limitedBordersTrading;
		private Boolean defensivePactTrading;
		private Boolean permanentAllianceTrading;
		private Boolean vassalTrading;
		private Boolean bridgeBuilding;
		private Boolean irrigation;
		private Boolean ignoreIrrigation;
		private Boolean waterWork;
		private Boolean canPassPeaks;
		private Boolean moveFastPeaks;
		private Boolean canFoundOnPeaks;
		private Integer gridX;
		private Integer gridY;
		private List<IPair<String, Integer>> domainExtraMoves = new ArrayList<IPair<String, Integer>>();
		private List<Integer> commerceModifiers = new ArrayList<Integer>();
		private List<Integer> specialistExtraCommerces = new ArrayList<Integer>();
		private List<Boolean> commerceFlexible = new ArrayList<Boolean>();
		private List<String> terrainTrades = new ArrayList<String>();
		private Boolean riverTrade;
		private Boolean captureCities;
		private Boolean unitRangeUnbound;
		private Boolean unitTerritoryUnbound;
		private Integer unitRangeChange;
		private Integer unitRangeModifier;
		private Integer cultureDefenceModifier;
		private List<Integer> forestPlotYieldChanges = new ArrayList<Integer>();
		private List<Integer> riverPlotYieldChanges = new ArrayList<Integer>();
		private List<Integer> seaPlotYieldChanges = new ArrayList<Integer>();
		private List<IPair<String, Integer>> worldViewRevoltTurnChanges = new ArrayList<IPair<String, Integer>>();
		private List<IPair<String, Integer>> flavors = new ArrayList<IPair<String, Integer>>();
		private List<String> orPreReqs = new ArrayList<String>();
		private List<String> andPreReqs = new ArrayList<String>();
		private List<String> enabledWorldViews = new ArrayList<String>();
		private String quote;
		private String sound;
		private String soundMP;
		private String button;

		private TechInfo(String type) {
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
		public String getCivilopedia() {
			return civilopedia;
		}

		@Override
		public void setCivilopedia(String civilopedia) {
			this.civilopedia = civilopedia;
		}

		@Override
		public String getHelp() {
			return help;
		}

		@Override
		public void setHelp(String help) {
			this.help = help;
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
		public String getAdvisor() {
			return advisor;
		}

		@Override
		public void setAdvisor(String advisor) {
			this.advisor = advisor;
		}

		@Override
		public Integer getAIWeight() {
			return aIWeight;
		}

		@Override
		public void setAIWeight(Integer aIWeight) {
			this.aIWeight = aIWeight;
		}

		@Override
		public Integer getAITradeModifier() {
			return aITradeModifier;
		}

		@Override
		public void setAITradeModifier(Integer aITradeModifier) {
			this.aITradeModifier = aITradeModifier;
		}

		@Override
		public Integer getCost() {
			return cost;
		}

		@Override
		public void setCost(Integer cost) {
			this.cost = cost;
		}

		@Override
		public Integer getAdvancedStartCost() {
			return advancedStartCost;
		}

		@Override
		public void setAdvancedStartCost(Integer advancedStartCost) {
			this.advancedStartCost = advancedStartCost;
		}

		@Override
		public Integer getAdvancedStartCostIncrease() {
			return advancedStartCostIncrease;
		}

		@Override
		public void setAdvancedStartCostIncrease(Integer advancedStartCostIncrease) {
			this.advancedStartCostIncrease = advancedStartCostIncrease;
		}

		@Override
		public String getEra() {
			return era;
		}

		@Override
		public void setEra(String era) {
			this.era = era;
		}

		@Override
		public Boolean isCivSettled() {
			return civSettled;
		}

		@Override
		public void setCivSettled(Boolean civSettled) {
			this.civSettled = civSettled;
		}

		@Override
		public String getFreeCapitalBuildingClass() {
			return freeCapitalBuildingClass;
		}

		@Override
		public void setFreeCapitalBuildingClass(String freeCapitalBuildingClass) {
			this.freeCapitalBuildingClass = freeCapitalBuildingClass;
		}

		@Override
		public String getFirstFreeUnitClass() {
			return firstFreeUnitClass;
		}

		@Override
		public void setFirstFreeUnitClass(String firstFreeUnitClass) {
			this.firstFreeUnitClass = firstFreeUnitClass;
		}

		@Override
		public String getFreeUnitClass() {
			return freeUnitClass;
		}

		@Override
		public void setFreeUnitClass(String freeUnitClass) {
			this.freeUnitClass = freeUnitClass;
		}

		@Override
		public Integer getFeatureProductionModifier() {
			return featureProductionModifier;
		}

		@Override
		public void setFeatureProductionModifier(Integer featureProductionModifier) {
			this.featureProductionModifier = featureProductionModifier;
		}

		@Override
		public Integer getWorkerSpeedModifier() {
			return workerSpeedModifier;
		}

		@Override
		public void setWorkerSpeedModifier(Integer workerSpeedModifier) {
			this.workerSpeedModifier = workerSpeedModifier;
		}

		@Override
		public Integer getTradeRoutes() {
			return tradeRoutes;
		}

		@Override
		public void setTradeRoutes(Integer tradeRoutes) {
			this.tradeRoutes = tradeRoutes;
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
		public Integer getHappiness() {
			return happiness;
		}

		@Override
		public void setHappiness(Integer happiness) {
			this.happiness = happiness;
		}

		@Override
		public Integer getFirstFreeTechs() {
			return firstFreeTechs;
		}

		@Override
		public void setFirstFreeTechs(Integer firstFreeTechs) {
			this.firstFreeTechs = firstFreeTechs;
		}

		@Override
		public Integer getAsset() {
			return asset;
		}

		@Override
		public void setAsset(Integer asset) {
			this.asset = asset;
		}

		@Override
		public Integer getPower() {
			return power;
		}

		@Override
		public void setPower(Integer power) {
			this.power = power;
		}

		@Override
		public Boolean isRepeat() {
			return repeat;
		}

		@Override
		public void setRepeat(Boolean repeat) {
			this.repeat = repeat;
		}

		@Override
		public Boolean isTrade() {
			return trade;
		}

		@Override
		public void setTrade(Boolean trade) {
			this.trade = trade;
		}

		@Override
		public Boolean isEmbassyTrading() {
			return embassyTrading;
		}

		@Override
		public void setEmbassyTrading(Boolean embassyTrading) {
			this.embassyTrading = embassyTrading;
		}

		@Override
		public Boolean isFreeTradeAgreementTrading() {
			return freeTradeAgreementTrading;
		}

		@Override
		public void setFreeTradeAgreementTrading(Boolean freeTradeAgreementTrading) {
			this.freeTradeAgreementTrading = freeTradeAgreementTrading;
		}

		@Override
		public Boolean isNonAggressionTrading() {
			return nonAggressionTrading;
		}

		@Override
		public void setNonAggressionTrading(Boolean nonAggressionTrading) {
			this.nonAggressionTrading = nonAggressionTrading;
		}

		@Override
		public Boolean isDisable() {
			return disable;
		}

		@Override
		public void setDisable(Boolean disable) {
			this.disable = disable;
		}

		@Override
		public Boolean isGoodyTech() {
			return goodyTech;
		}

		@Override
		public void setGoodyTech(Boolean goodyTech) {
			this.goodyTech = goodyTech;
		}

		@Override
		public Boolean isExtraWaterSeeFrom() {
			return extraWaterSeeFrom;
		}

		@Override
		public void setExtraWaterSeeFrom(Boolean extraWaterSeeFrom) {
			this.extraWaterSeeFrom = extraWaterSeeFrom;
		}

		@Override
		public Boolean isMapCentering() {
			return mapCentering;
		}

		@Override
		public void setMapCentering(Boolean mapCentering) {
			this.mapCentering = mapCentering;
		}

		@Override
		public Boolean isMapVisible() {
			return mapVisible;
		}

		@Override
		public void setMapVisible(Boolean mapVisible) {
			this.mapVisible = mapVisible;
		}

		@Override
		public Boolean isMapTrading() {
			return mapTrading;
		}

		@Override
		public void setMapTrading(Boolean mapTrading) {
			this.mapTrading = mapTrading;
		}

		@Override
		public Boolean isTechTrading() {
			return techTrading;
		}

		@Override
		public void setTechTrading(Boolean techTrading) {
			this.techTrading = techTrading;
		}

		@Override
		public Boolean isGoldTrading() {
			return goldTrading;
		}

		@Override
		public void setGoldTrading(Boolean goldTrading) {
			this.goldTrading = goldTrading;
		}

		@Override
		public Boolean isOpenBordersTrading() {
			return openBordersTrading;
		}

		@Override
		public void setOpenBordersTrading(Boolean openBordersTrading) {
			this.openBordersTrading = openBordersTrading;
		}

		@Override
		public Boolean isLimitedBordersTrading() {
			return limitedBordersTrading;
		}

		@Override
		public void setLimitedBordersTrading(Boolean limitedBordersTrading) {
			this.limitedBordersTrading = limitedBordersTrading;
		}

		@Override
		public Boolean isDefensivePactTrading() {
			return defensivePactTrading;
		}

		@Override
		public void setDefensivePactTrading(Boolean defensivePactTrading) {
			this.defensivePactTrading = defensivePactTrading;
		}

		@Override
		public Boolean isPermanentAllianceTrading() {
			return permanentAllianceTrading;
		}

		@Override
		public void setPermanentAllianceTrading(Boolean permanentAllianceTrading) {
			this.permanentAllianceTrading = permanentAllianceTrading;
		}

		@Override
		public Boolean isVassalTrading() {
			return vassalTrading;
		}

		@Override
		public void setVassalTrading(Boolean vassalTrading) {
			this.vassalTrading = vassalTrading;
		}

		@Override
		public Boolean isBridgeBuilding() {
			return bridgeBuilding;
		}

		@Override
		public void setBridgeBuilding(Boolean bridgeBuilding) {
			this.bridgeBuilding = bridgeBuilding;
		}

		@Override
		public Boolean isIrrigation() {
			return irrigation;
		}

		@Override
		public void setIrrigation(Boolean irrigation) {
			this.irrigation = irrigation;
		}

		@Override
		public Boolean isIgnoreIrrigation() {
			return ignoreIrrigation;
		}

		@Override
		public void setIgnoreIrrigation(Boolean ignoreIrrigation) {
			this.ignoreIrrigation = ignoreIrrigation;
		}

		@Override
		public Boolean isWaterWork() {
			return waterWork;
		}

		@Override
		public void setWaterWork(Boolean waterWork) {
			this.waterWork = waterWork;
		}

		@Override
		public Boolean isCanPassPeaks() {
			return canPassPeaks;
		}

		@Override
		public void setCanPassPeaks(Boolean canPassPeaks) {
			this.canPassPeaks = canPassPeaks;
		}

		@Override
		public Boolean isMoveFastPeaks() {
			return moveFastPeaks;
		}

		@Override
		public void setMoveFastPeaks(Boolean moveFastPeaks) {
			this.moveFastPeaks = moveFastPeaks;
		}

		@Override
		public Boolean isCanFoundOnPeaks() {
			return canFoundOnPeaks;
		}

		@Override
		public void setCanFoundOnPeaks(Boolean canFoundOnPeaks) {
			this.canFoundOnPeaks = canFoundOnPeaks;
		}

		@Override
		public Integer getGridX() {
			return gridX;
		}

		@Override
		public void setGridX(Integer gridX) {
			this.gridX = gridX;
		}

		@Override
		public Integer getGridY() {
			return gridY;
		}

		@Override
		public void setGridY(Integer gridY) {
			this.gridY = gridY;
		}

		@Override
		public List<IPair<String, Integer>> getDomainExtraMoves() {
			return domainExtraMoves;
		}

		@Override
		public void addDomainExtraMove(IPair<String, Integer> domainExtraMove) {
			this.domainExtraMoves.add(domainExtraMove);
		}

		@Override
		public List<Integer> getCommerceModifiers() {
			return commerceModifiers;
		}

		@Override
		public void addCommerceModifier(Integer commerceModifier) {
			this.commerceModifiers.add(commerceModifier);
		}

		@Override
		public List<Integer> getSpecialistExtraCommerces() {
			return specialistExtraCommerces;
		}

		@Override
		public void addSpecialistExtraCommerce(Integer specialistExtraCommerce) {
			this.specialistExtraCommerces.add(specialistExtraCommerce);
		}

		@Override
		public List<Boolean> getCommerceFlexible() {
			return commerceFlexible;
		}

		@Override
		public void addCommerceFlexible(Boolean commerceFlexible) {
			this.commerceFlexible.add(commerceFlexible);
		}

		@Override
		public List<String> getTerrainTrades() {
			return terrainTrades;
		}

		@Override
		public void addTerrainTrade(String terrainTrade) {
			this.terrainTrades.add(terrainTrade);
		}

		@Override
		public Boolean isRiverTrade() {
			return riverTrade;
		}

		@Override
		public void setRiverTrade(Boolean riverTrade) {
			this.riverTrade = riverTrade;
		}

		@Override
		public Boolean isCaptureCities() {
			return captureCities;
		}

		@Override
		public void setCaptureCities(Boolean captureCities) {
			this.captureCities = captureCities;
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
		public Integer getCultureDefenceModifier() {
			return cultureDefenceModifier;
		}

		@Override
		public void setCultureDefenceModifier(Integer cultureDefenceModifier) {
			this.cultureDefenceModifier = cultureDefenceModifier;
		}

		@Override
		public List<Integer> getForestPlotYieldChanges() {
			return forestPlotYieldChanges;
		}

		@Override
		public void addForestPlotYieldChange(Integer forestPlotYieldChange) {
			this.forestPlotYieldChanges.add(forestPlotYieldChange);
		}

		@Override
		public List<Integer> getRiverPlotYieldChanges() {
			return riverPlotYieldChanges;
		}

		@Override
		public void addRiverPlotYieldChange(Integer riverPlotYieldChange) {
			this.riverPlotYieldChanges.add(riverPlotYieldChange);
		}

		@Override
		public List<Integer> getSeaPlotYieldChanges() {
			return seaPlotYieldChanges;
		}

		@Override
		public void addSeaPlotYieldChange(Integer seaPlotYieldChange) {
			this.seaPlotYieldChanges.add(seaPlotYieldChange);
		}

		@Override
		public List<IPair<String, Integer>> getWorldViewRevoltTurnChanges() {
			return worldViewRevoltTurnChanges;
		}

		@Override
		public void addWorldViewRevoltTurnChange(IPair<String, Integer> worldViewRevoltTurnChange) {
			this.worldViewRevoltTurnChanges.add(worldViewRevoltTurnChange);
		}

		@Override
		public List<IPair<String, Integer>> getFlavors() {
			return flavors;
		}

		@Override
		public void addFlavor(IPair<String, Integer> flavor) {
			this.flavors.add(flavor);
		}

		@Override
		public List<String> getOrPreReqs() {
			return orPreReqs;
		}

		@Override
		public void addOrPreReq(String orPreReq) {
			this.orPreReqs.add(orPreReq);
		}

		@Override
		public List<String> getAndPreReqs() {
			return andPreReqs;
		}

		@Override
		public void addAndPreReq(String andPreReq) {
			this.andPreReqs.add(andPreReq);
		}

		@Override
		public List<String> getEnabledWorldViews() {
			return enabledWorldViews;
		}

		@Override
		public void addEnabledWorldView(String enabledWorldView) {
			this.enabledWorldViews.add(enabledWorldView);
		}

		@Override
		public String getQuote() {
			return quote;
		}

		@Override
		public void setQuote(String quote) {
			this.quote = quote;
		}

		@Override
		public String getSound() {
			return sound;
		}

		@Override
		public void setSound(String sound) {
			this.sound = sound;
		}

		@Override
		public String getSoundMP() {
			return soundMP;
		}

		@Override
		public void setSoundMP(String soundMP) {
			this.soundMP = soundMP;
		}

		@Override
		public String getButton() {
			return button;
		}

		@Override
		public void setButton(String button) {
			this.button = button;
		}
	}
}