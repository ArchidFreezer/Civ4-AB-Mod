package org.archid.civ4.info.building;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@XmlJavaTypeAdapter(BonusYieldChangesAdapter.class)
class BonusYieldChanges{
	private List<BonusYieldChange> wrapper = new ArrayList<BonusYieldChange>();

	public List<BonusYieldChange> getBonusYieldChangeList() {
		return wrapper;
	}

	public void setBonusYieldChangeList(List<BonusYieldChange> wrapper){
		this.wrapper = wrapper;
	}

	static class BonusYieldChange {
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