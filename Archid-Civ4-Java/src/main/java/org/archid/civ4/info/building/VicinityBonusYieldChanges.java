package org.archid.civ4.info.building;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@XmlJavaTypeAdapter(VicinityBonusYieldChangesAdapter.class)
class VicinityBonusYieldChanges{
	private List<VicinityBonusYieldChange> wrapper = new ArrayList<VicinityBonusYieldChange>();

	public List<VicinityBonusYieldChange> getVicinityBonusYieldChangeList() {
		return wrapper;
	}

	public void setVicinityBonusYieldChangeList(List<VicinityBonusYieldChange> wrapper){
		this.wrapper = wrapper;
	}

	static class VicinityBonusYieldChange {
		private String resource;
		private List<Integer> list = new ArrayList<Integer>();

		public void setResource (String resource) {
			this.resource = resource;
		}

		public String getResource() {
			return resource;
		}

		public void addElement(Integer element) {
			list.add(element);
		}

		public List<Integer> getElements() {
			return list;
		}
	}
}