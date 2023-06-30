package org.archid.civ4.info.bonus;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.adapters.XmlAdapter;

public class RandsAdapter extends XmlAdapter<RandsAdapter.AdaptedRands, Rands> {

	public static class AdaptedRands {
		@XmlElement(name="iRandApp1")
		private Integer randApp1;
		@XmlElement(name="iRandApp2")
		private Integer randApp2;
		@XmlElement(name="iRandApp3")
		private Integer randApp3;
		@XmlElement(name="iRandApp4")
		private Integer randApp4;		
	}

	@Override
	public Rands unmarshal(AdaptedRands v) throws Exception {
		if (v.randApp1 == null) return null;
		Rands rands = new Rands();
		rands.setRandApp1(v.randApp1);
		rands.setRandApp2(v.randApp2);
		rands.setRandApp3(v.randApp3);
		rands.setRandApp4(v.randApp4);
		return rands;
	}

	@Override
	public AdaptedRands marshal(Rands v) throws Exception {
		if (v.getRandApp1() == 0) return null;
		AdaptedRands rands = new AdaptedRands();
		rands.randApp1 = v.getRandApp1();
		rands.randApp2 = v.getRandApp2();
		rands.randApp3 = v.getRandApp3();
		rands.randApp4 = v.getRandApp4();
		return rands;
	}
}
