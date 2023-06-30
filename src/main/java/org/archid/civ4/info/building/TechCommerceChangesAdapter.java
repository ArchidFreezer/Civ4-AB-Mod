package org.archid.civ4.info.building;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.adapters.XmlAdapter;

import org.archid.civ4.info.building.TechCommerceChanges.TechCommerceChange;
import org.archid.utils.CollectionUtils;
import org.archid.utils.JaxbUtils;

public class TechCommerceChangesAdapter extends XmlAdapter<TechCommerceChangesAdapter.AdaptedTechCommerceChanges, TechCommerceChanges> {

	public static class AdaptedTechCommerceChanges {
		@XmlElement(name = "TechCommerceChange")
		private List<AdaptedTechCommerceChange> entries = new ArrayList<AdaptedTechCommerceChange>();
	}

	static class AdaptedTechCommerceChange {
		@XmlElement(name="TechType")
		String resource;
		@XmlElementWrapper(name="CommerceChanges")
		@XmlElement(name="iCommerce")
		List<Integer> list;
	}

	@Override
	public TechCommerceChanges unmarshal(AdaptedTechCommerceChanges v) throws Exception {
		TechCommerceChanges changes = new TechCommerceChanges();
		for (AdaptedTechCommerceChange adapter: v.entries) {
			TechCommerceChange wrapper = new TechCommerceChange();
			wrapper.setResource(JaxbUtils.unmarshallString(adapter.resource));
			if (CollectionUtils.hasElements(adapter.list)) {
				for (Integer val: adapter.list) {
					wrapper.addElement(val);
				}
			}
			changes.getTechCommerceChangeList().add(wrapper);
		}
		return changes;
	}

	@Override
	public AdaptedTechCommerceChanges marshal(TechCommerceChanges v) throws Exception {
		if (!CollectionUtils.hasElements(v.getTechCommerceChangeList())) return null;

		AdaptedTechCommerceChanges changes = new AdaptedTechCommerceChanges();
		for(TechCommerceChange wrapper: v.getTechCommerceChangeList()) {
			AdaptedTechCommerceChange adapter = new AdaptedTechCommerceChange();
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