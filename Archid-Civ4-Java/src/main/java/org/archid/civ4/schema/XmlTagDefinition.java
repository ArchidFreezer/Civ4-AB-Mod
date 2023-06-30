/**
 * 
 */
package org.archid.civ4.schema;

import java.util.ArrayList;
import java.util.List;

/**
 * @author jim
 * 
 */
public class XmlTagDefinition {

	protected String tagName = null;
	protected DataType dataType = DataType.STRING;
	protected boolean completed = false;
	protected boolean topLevel = true;
	protected List<XmlTagInstance> children = new ArrayList<XmlTagInstance>();

	public XmlTagDefinition(String tagName) {
		this.tagName = tagName;
	}

	public String getTagName() {
		return tagName;
	}

	public void setTagName(String tag) {
		this.tagName = tag;
	}

	public boolean isCompleted() {
		return completed;
	}

	public void setCompleted(boolean completed) {
		this.completed = completed;
	}

	public boolean isTopLevel() {
		return topLevel;
	}

	public void setTopLevel(boolean topLevel) {
		this.topLevel = topLevel;
	}

	public List<XmlTagInstance> getChildren() {
		return children;
	}

	public void addChild(XmlTagInstance child) {
		children.add(child);
	}

	public DataType getDataType() {
		return dataType;
	}

	public void setDataType(DataType dataType) {
		this.dataType = dataType;
	}


	@Override
	public int hashCode() {
		final int prime = 17;
		int result = 1;
		// We only really care about the children when hashing
		int childVals = 1;
		for (XmlTagInstance child: children)
		{
			childVals += child.hashCode();
		}
		result = prime * (result + childVals);
		return result;
	}
	
	@Override
	public boolean equals(final Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		
		final XmlTagDefinition other = (XmlTagDefinition)obj;
		if (!tagName.equals(other.getTagName()))
			return false;
		
		if (children.size() != other.getChildren().size())
			return false;
		
		List<String> childNames = new ArrayList<String>();
		for (XmlTagInstance child: children) {
			childNames.add(child.getTagName());
		}
		for (XmlTagInstance child: other.getChildren()) {
			if (!childNames.contains(child.getTagName()))
				return false;
		}
		return true;
	}

	public enum DataType {
		STRING("text", "", "Text", "String"), INTEGER("int", "0", "Integer", "Integer"), BOOLEAN("boolean", "0", "Boolean", "Boolean"), FLOAT("float", "0.0", "Float", "String"),  MULTI("comp", "", "Compound (Multi-line)", "String");

		/** Mixed case representation of the value for display */
		private final String label;
		private final String defaultVal;
		private final String htmlText;
		private final String javaType;

		/** Constructor */
		DataType(String label, String defaultVal, String htmlText, String javaType) {
			this.label = label;
			this.defaultVal = defaultVal;
			this.htmlText = htmlText;
			this.javaType = javaType;
		}

		/**
		 * Gets the DataType associated with the label
		 */
		public static DataType fromLabel(String v) {
			for (DataType c : DataType.values()) {
				if (c.label.equals(v)) {
					return c;
				}
			}
			throw new IllegalArgumentException(v);
		}
		
		/**
		 * The value returned is in mixed case and better suited for the display of the value.
		 * 
		 * @return Mixed case representation of the value
		 */
		@Override
		public String toString() {
			return label;
		}
		
		public String getDefaultVal() {
			return defaultVal;
		}
		
		public String getHtmlLabel() {
			return htmlText;
		}
		
		public String getJavaType() {
			return javaType;
		}

	}

}
