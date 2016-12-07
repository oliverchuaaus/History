package com.tougher.util;

import org.apache.commons.lang.StringUtils;

public class JPAUtil {

	public static String addWhereOrAnd(String qlString) {
		if (StringUtils.isBlank(qlString)) {
			return " WHERE ";
		} else {
			return " AND ";
		}
	}

}