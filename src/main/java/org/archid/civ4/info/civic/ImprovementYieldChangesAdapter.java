package org.archid.civ4.info.civic;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.adapters.XmlAdapter;

import org.archid.civ4.info.civic.ImprovementYieldChanges.ImprovementYieldChange;
import org.archid.utils.CollectionUtils;
import org.archid.utils.JaxbUtils;

public class ImprovementYieldChangesAdapter extends XmlAdapter<ImprovementYieldChangesAdapter.AdaptedImprovementYieldChanges, ImprovementYieldChanges> {

	public static class AdaptedImprovementYieldChanges {
		@XmlElement(name = "ImprovementYieldChange")
		private List<AdaptedImprovementYieldChange> entries = new ArrayList<AdaptedImprovementYieldChange>();
	}

	static class AdaptedImprovementYieldChange {
		@XmlElement(name="ImprovementType")
		String resource;
		@XmlElementWrapper(name="ImprovementYields")
		@XmlElement(name="iYield")
		List<Integer> list;
	}

	@Override
	public ImprovementYieldChanges unmarshal(AdaptedImprovementYieldChanges v) throws Exception {
		ImprovementYieldChanges changes = new ImprovementYieldChanges();
		for (AdaptedImprovementYieldChange adapter: v.entries) {
			ImprovementYieldChange wrapper = new ImprovementYieldChange();
			wrapper.setResource(JaxbUtils.unmarshallString(adapter.resource));
			if (CollectionUtils.hasElements(adapter.list)) {
				for (Integer val: adapter.list) {
					wrapper.addElement(val);
				}
			}
			changes.getImprovementYieldChangeList().add(wrapper);
		}
		return changes;
	}

	@Override
	public AdaptedImprovementYieldChanges marshal(ImprovementYieldChanges v) throws Exception {
		if (!CollectionUtils.hasElements(v.getImprovementYieldChangeList())) return null;

		AdaptedImprovementYieldChanges changes = new AdaptedImprovementYieldChanges();
		for(ImprovementYieldChange wrapper: v.getImprovementYieldChangeList()) {
			AdaptedImprovementYieldChange adapter = new AdaptedImprovementYieldChange();
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