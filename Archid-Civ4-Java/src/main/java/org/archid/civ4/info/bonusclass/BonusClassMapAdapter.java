package org.archid.civ4.info.bonusclass;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.adapters.XmlAdapter;
import org.archid.utils.JaxbUtils;

public class BonusClassMapAdapter extends XmlAdapter<BonusClassMapAdapter.BonusClassMap, Map<String, IBonusClassInfo>> {

	public static class BonusClassMap {
		@XmlElement(name = "BonusClassInfo")
		List<AdaptedBonusClass> entries = new ArrayList<AdaptedBonusClass>();
	}

	private static class AdaptedBonusClass {
		@XmlElement(name="Type")
		private String type;
		@XmlElement(name="iUnique")
		private Integer unique;
	}

	@Override
	public Map<String, IBonusClassInfo> unmarshal(BonusClassMap v) throws Exception {
		Map<String, IBonusClassInfo> map = new TreeMap<String, IBonusClassInfo>();
		for (AdaptedBonusClass aInfo: v.entries) {
			IBonusClassInfo info = BonusClassInfos.createInfo(aInfo.type);
			info.setUnique(JaxbUtils.unmarshallInteger(aInfo.unique));

			map.put(aInfo.type, info);
		}
		return map;
	}

	@Override
	public BonusClassMap marshal(Map<String, IBonusClassInfo> v) throws Exception {
		BonusClassMap map = new BonusClassMap();
		for (IBonusClassInfo info: v.values()) {
			AdaptedBonusClass aInfo = new AdaptedBonusClass();
			aInfo.type = JaxbUtils.marshallString(info.getType());
			aInfo.unique = JaxbUtils.marshallInteger(info.getUnique());

			map.entries.add(aInfo);
		}
		return map;
	}
}