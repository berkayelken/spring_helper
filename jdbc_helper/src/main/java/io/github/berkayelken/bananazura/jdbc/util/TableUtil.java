package io.github.berkayelken.bananazura.jdbc.util;

import io.github.berkayelken.bananazura.common.annotation.Model;

import java.util.Locale;

import static io.github.berkayelken.bananazura.common.util.CommonUtil.isNotBlank;

public final class TableUtil {
	private TableUtil() {

	}

	public static String getTableName(Class<?> modelClass) {
		Model model = modelClass.getAnnotation(Model.class);

		if(model != null) {
			if(isNotBlank(model.value()))
				return model.value();
			if(isNotBlank(model.name()))
				return model.name();
		}

		return modelClass.getSimpleName().toUpperCase(Locale.ENGLISH);
	}
}
