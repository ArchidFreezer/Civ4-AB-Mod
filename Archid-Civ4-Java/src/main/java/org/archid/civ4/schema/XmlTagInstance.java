package org.archid.civ4.schema;

public class XmlTagInstance implements Comparable<XmlTagInstance>{
	
	protected String tagName = null;
	protected boolean mandatory = true;
	protected int count = 1;
	protected String defaultVal = null;
	
	
	public XmlTagInstance(String tag) {
		this.tagName = tag;
	}
	
	public String getTagName() {
		return tagName;
	}
	public void setTagName(String tag) {
		this.tagName = tag;
	}
	public boolean isMandatory() {
		return mandatory;
	}
	public void setMandatory(boolean mandatory) {
		this.mandatory = mandatory;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}

	public String getDefaultVal() {
		return defaultVal;
	}

	public void setDefaultVal(String defaultVal) {
		this.defaultVal = defaultVal;
	}

	@Override
	public int compareTo(XmlTagInstance o) {
		return this.tagName.compareTo(o.getTagName());
	}

}
