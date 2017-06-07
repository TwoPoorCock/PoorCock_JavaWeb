package com.poolm.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TableHelper {
	public static final List<String> columns = new ArrayList<String>(
			Arrays.asList("userName", "gender", "name", "content", "title",
					"status"));

	/**
	 * 是否需要转成gbk
	 */
	public static String convertToGBK(String key) {
		if (columns.contains(key)) {
			return "convert(" + key + " USING gbk)";
		}
		return key;
	}
}
