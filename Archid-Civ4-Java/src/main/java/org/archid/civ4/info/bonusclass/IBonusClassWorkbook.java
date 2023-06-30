package org.archid.civ4.info.bonusclass;

import org.archid.civ4.info.IInfoWorkbook;
import org.archid.utils.StringUtils;

public interface IBonusClassWorkbook extends IInfoWorkbook {

	public static final String SHEETNAME_LIST = "BonusClassList";

	public enum SheetHeaders {
		TYPE, UNIQUE;

		@Override
		public String toString() {
			return StringUtils.titleCaseSpace(this.name(), '_');
		}
	}
}