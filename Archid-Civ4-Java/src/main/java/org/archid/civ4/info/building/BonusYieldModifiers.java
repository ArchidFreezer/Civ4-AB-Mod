package org.archid.civ4.info.building;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@XmlJavaTypeAdapter(BonusYieldModifiersAdapter.class)
class BonusYieldModifiers{
	private List<BonusYieldModifier> wrapper = new ArrayList<BonusYieldModifier>();

	public List<BonusYieldModifier> getBonusYieldModifierList() {
		return wrapper;
	}

	public void setBonusYieldModifierList(List<BonusYieldModifier> wrapper){
		this.wrapper = wrapper;
	}

	static class BonusYieldModifier {
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