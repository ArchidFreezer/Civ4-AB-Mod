package org.archid.civ4.info.bonusclass;

import java.util.LinkedHashMap;
import java.util.Map;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import org.archid.civ4.info.AbstractInfos;

@XmlRootElement(name="Civ4BonusClassInfos", namespace="x-schema:CIV4TerrainSchema.xml")
@XmlAccessorType(XmlAccessType.NONE)
public class BonusClassInfos extends AbstractInfos<IBonusClassInfo> {

	public BonusClassInfos() {
		super(new LinkedHashMap<String, IBonusClassInfo>());
	}

	@XmlJavaTypeAdapter(BonusClassMapAdapter.class)
	@XmlElement(name="BonusClassInfos")
	public Map<String, IBonusClassInfo> getInfoMap() {
		return infos;
	}

	public static IBonusClassInfo createInfo(String type) {
		return new BonusClassInfo(type);
	}

	@Override
	public void setInfoMap(Map<String, IBonusClassInfo> infos) {
		this.infos = infos;
	}

	private static class BonusClassInfo implements IBonusClassInfo{

		private String type;
		private Integer unique;

		private BonusClassInfo(String type) {
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
		public Integer getUnique() {
			return unique;
		}

		@Override
		public void setUnique(Integer unique) {
			this.unique = unique;
		}
	}
}