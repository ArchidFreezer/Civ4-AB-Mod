package org.archid.civ4.info.building;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.adapters.XmlAdapter;

import org.archid.civ4.info.building.BonusYieldModifiers.BonusYieldModifier;
import org.archid.utils.CollectionUtils;
import org.archid.utils.JaxbUtils;

public class BonusYieldModifiersAdapter extends XmlAdapter<BonusYieldModifiersAdapter.AdaptedBonusYieldModifiers, BonusYieldModifiers> {

	public static class AdaptedBonusYieldModifiers {
		@XmlElement(name = "BonusYieldModifier")
		private List<AdaptedBonusYieldModifier> entries = new ArrayList<AdaptedBonusYieldModifier>();
	}

	static class AdaptedBonusYieldModifier {
		@XmlElement(name="BonusType")
		String resource;
		@XmlElementWrapper(name="YieldModifiers")
		@XmlElement(name="iYield")
		List<Integer> list;
	}

	@Override
	public BonusYieldModifiers unmarshal(AdaptedBonusYieldModifiers v) throws Exception {
		BonusYieldModifiers changes = new BonusYieldModifiers();
		for (AdaptedBonusYieldModifier adapter: v.entries) {
			BonusYieldModifier wrapper = new BonusYieldModifier();
			wrapper.setResource(JaxbUtils.unmarshallString(adapter.resource));
			if (CollectionUtils.hasElements(adapter.list)) {
				for (Integer val: adapter.list) {
					wrapper.addElement(val);
				}
			}
			changes.getBonusYieldModifierList().add(wrapper);
		}
		return changes;
	}

	@Override
	public AdaptedBonusYieldModifiers marshal(BonusYieldModifiers v) throws Exception {
		if (!CollectionUtils.hasElements(v.getBonusYieldModifierList())) return null;

		AdaptedBonusYieldModifiers changes = new AdaptedBonusYieldModifiers();
		for(BonusYieldModifier wrapper: v.getBonusYieldModifierList()) {
			AdaptedBonusYieldModifier adapter = new AdaptedBonusYieldModifier();
			adapter.resource = wrapper.getResource();
			adapter.list = new ArrayList<Integer>();
			if (CollectionUtils.hasElements(wrapper.getElements())) {
				adapter.list = wrapper.getElements();
			}
			changes.entries.add(adapter);
		}
		return changes;
	}
}