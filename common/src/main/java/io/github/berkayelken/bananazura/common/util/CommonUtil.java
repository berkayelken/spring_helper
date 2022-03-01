package io.github.berkayelken.bananazura.common.util;

import java.util.Collection;

public final class CommonUtil {
	private CommonUtil() {

	}

	public static boolean isBlank(String str) {
		int strLen;

		if (str == null || (strLen = str.length()) == 0) {
			return true;
		}

		for (int i = 0; i < strLen; i++) {
			if (Character.isWhitespace(str.charAt(i))) {
				return false;
			}
		}

		return true;
	}

	public static <T> boolean isBlank(T[] array) {
		int len;

		if (array == null || (len = array.length) == 0) {
			return true;
		}

		for (int i = 0; i < len; i++) {
			if (array[i] != null) {
				return false;
			}
		}

		return true;
	}

	public static boolean isBlank(Collection<?> collection) {

		if (collection == null || collection.size() == 0) {
			return true;
		}

		long size = collection.stream().filter(item -> item != null).count();

		if (size > 0L) {
			return false;
		}

		return true;
	}

	public static boolean isNotBlank(String str) {
		return !isBlank(str);
	}

	public static <T> boolean isNotBlank(T[] array) {
		return !isBlank(array);
	}

	public static boolean isNotBlank(Collection<?> collection) {
		return !isBlank(collection);
	}
}
