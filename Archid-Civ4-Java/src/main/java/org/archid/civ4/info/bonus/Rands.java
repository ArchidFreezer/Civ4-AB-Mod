package org.archid.civ4.info.bonus;

import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@XmlJavaTypeAdapter(RandsAdapter.class) 
public class Rands {
	private int randApp1;
	private int randApp2;
	private int randApp3;
	private int randApp4;
	
	public int getRandApp1() {
		return randApp1;
	}
	
	public void setRandApp1(int val) {
		randApp1 = val;
	}

	public int getRandApp2() {
		return randApp2;
	}
	
	public void setRandApp2(int val) {
		randApp2 = val;
	}

	public int getRandApp3() {
		return randApp3;
	}
	
	public void setRandApp3(int val) {
		randApp3 = val;
	}

	public int getRandApp4() {
		return randApp4;
	}
	
	public void setRandApp4(int val) {
		randApp4 = val;
	}

}
