package org.archid.civ4.info.bonus;

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

@XmlRootElement(name="Civ4BonusInfos", namespace="x-schema:CIV4TerrainSchema.xml")
@XmlAccessorType(XmlAccessType.NONE)
public class BonusInfos extends AbstractInfos<IBonusInfo> {

	public BonusInfos() {
		super(new LinkedHashMap<String, IBonusInfo>());
	}

	@XmlJavaTypeAdapter(BonusMapAdapter.class)
	@XmlElement(name="BonusInfos")
	public Map<String, IBonusInfo> getInfoMap() {
		return infos;
	}

	public static IBonusInfo createInfo(String type) {
		return new BonusInfo(type);
	}

	@Override
	public void setInfoMap(Map<String, IBonusInfo> infos) {
		this.infos = infos;
	}

	private static class BonusInfo implements IBonusInfo{

		private String type;
		private String description;
		private String civilopedia;
		private String help;
		private String bonusClassType;
		private String artDefineTag;
		private String techReveal;
		private String techCityTrade;
		private String techObsolete;
		private List<Integer> yieldChanges = new ArrayList<Integer>();
		private Integer aITradeModifier;
		private Integer aIObjective;
		private Integer health;
		private Integer happiness;
		private Integer placementOrder;
		private Integer constAppearance;
		private Integer minAreaSize;
		private Integer minLatitude;
		private Integer maxLatitude;
		private Rands rands = new Rands();
		private Integer player;
		private Integer tilesPer;
		private Integer minLandPercent;
		private Integer unique;
		private Integer groupRange;
		private Integer groupRand;
		private Boolean area;
		private Boolean hills;
		private Boolean peaks;
		private Boolean flatlands;
		private Boolean noRiverSide;
		private Boolean normalize;
		private List<String> terrainBooleans = new ArrayList<String>();
		private List<String> featureBooleans = new ArrayList<String>();
		private List<String> featureTerrainBooleans = new ArrayList<String>();
		private Boolean useLSystem;

		private BonusInfo(String type) {
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
		public String getBonusClassType() {
			return bonusClassType;
		}

		@Override
		public void setBonusClassType(String bonusClassType) {
			this.bonusClassType = bonusClassType;
		}

		@Override
		public String getArtDefineTag() {
			return artDefineTag;
		}

		@Override
		public void setArtDefineTag(String artDefineTag) {
			this.artDefineTag = artDefineTag;
		}

		@Override
		public String getTechReveal() {
			return techReveal;
		}

		@Override
		public void setTechReveal(String techReveal) {
			this.techReveal = techReveal;
		}

		@Override
		public String getTechCityTrade() {
			return techCityTrade;
		}

		@Override
		public void setTechCityTrade(String techCityTrade) {
			this.techCityTrade = techCityTrade;
		}

		@Override
		public String getTechObsolete() {
			return techObsolete;
		}

		@Override
		public void setTechObsolete(String techObsolete) {
			this.techObsolete = techObsolete;
		}

		@Override
		public List<Integer> getYieldChanges() {
			return yieldChanges;
		}

		@Override
		public void addYieldChange(Integer yieldChange) {
			this.yieldChanges.add(yieldChange);
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
		public Integer getAIObjective() {
			return aIObjective;
		}

		@Override
		public void setAIObjective(Integer aIObjective) {
			this.aIObjective = aIObjective;
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
		public Integer getPlacementOrder() {
			return placementOrder;
		}

		@Override
		public void setPlacementOrder(Integer placementOrder) {
			this.placementOrder = placementOrder;
		}

		@Override
		public Integer getConstAppearance() {
			return constAppearance;
		}

		@Override
		public void setConstAppearance(Integer constAppearance) {
			this.constAppearance = constAppearance;
		}

		@Override
		public Integer getMinAreaSize() {
			return minAreaSize;
		}

		@Override
		public void setMinAreaSize(Integer minAreaSize) {
			this.minAreaSize = minAreaSize;
		}

		@Override
		public Integer getMinLatitude() {
			return minLatitude;
		}

		@Override
		public void setMinLatitude(Integer minLatitude) {
			this.minLatitude = minLatitude;
		}

		@Override
		public Integer getMaxLatitude() {
			return maxLatitude;
		}

		@Override
		public void setMaxLatitude(Integer maxLatitude) {
			this.maxLatitude = maxLatitude;
		}

		@Override
		public Rands getRands() {
			return rands;
		}

		@Override
		public void setRands(Rands rands) {
			this.rands = rands;
		}

		@Override
		public Integer getPlayer() {
			return player;
		}

		@Override
		public void setPlayer(Integer player) {
			this.player = player;
		}

		@Override
		public Integer getTilesPer() {
			return tilesPer;
		}

		@Override
		public void setTilesPer(Integer tilesPer) {
			this.tilesPer = tilesPer;
		}

		@Override
		public Integer getMinLandPercent() {
			return minLandPercent;
		}

		@Override
		public void setMinLandPercent(Integer minLandPercent) {
			this.minLandPercent = minLandPercent;
		}

		@Override
		public Integer getUnique() {
			return unique;
		}

		@Override
		public void setUnique(Integer unique) {
			this.unique = unique;
		}

		@Override
		public Integer getGroupRange() {
			return groupRange;
		}

		@Override
		public void setGroupRange(Integer groupRange) {
			this.groupRange = groupRange;
		}

		@Override
		public Integer getGroupRand() {
			return groupRand;
		}

		@Override
		public void setGroupRand(Integer groupRand) {
			this.groupRand = groupRand;
		}

		@Override
		public Boolean isArea() {
			return area;
		}

		@Override
		public void setArea(Boolean area) {
			this.area = area;
		}

		@Override
		public Boolean isHills() {
			return hills;
		}

		@Override
		public void setHills(Boolean hills) {
			this.hills = hills;
		}

		@Override
		public Boolean isPeaks() {
			return peaks;
		}

		@Override
		public void setPeaks(Boolean peaks) {
			this.peaks = peaks;
		}

		@Override
		public Boolean isFlatlands() {
			return flatlands;
		}

		@Override
		public void setFlatlands(Boolean flatlands) {
			this.flatlands = flatlands;
		}

		@Override
		public Boolean isNoRiverSide() {
			return noRiverSide;
		}

		@Override
		public void setNoRiverSide(Boolean noRiverSide) {
			this.noRiverSide = noRiverSide;
		}

		@Override
		public Boolean isNormalize() {
			return normalize;
		}

		@Override
		public void setNormalize(Boolean normalize) {
			this.normalize = normalize;
		}

		@Override
		public List<String> getTerrainBooleans() {
			return terrainBooleans;
		}

		@Override
		public void addTerrainBoolean(String terrainBoolean) {
			this.terrainBooleans.add(terrainBoolean);
		}

		@Override
		public List<String> getFeatureBooleans() {
			return featureBooleans;
		}

		@Override
		public void addFeatureBoolean(String featureBoolean) {
			this.featureBooleans.add(featureBoolean);
		}

		@Override
		public List<String> getFeatureTerrainBooleans() {
			return featureTerrainBooleans;
		}

		@Override
		public void addFeatureTerrainBoolean(String featureTerrainBoolean) {
			this.featureTerrainBooleans.add(featureTerrainBoolean);
		}

		@Override
		public Boolean isUseLSystem() {
			return useLSystem;
		}

		@Override
		public void setUseLSystem(Boolean useLSystem) {
			this.useLSystem = useLSystem;
		}
	}
}