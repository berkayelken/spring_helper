package io.github.berkayelken.bananazura.jdbc.util;

import io.github.berkayelken.bananazura.jdbc.annotation.Column;
import io.github.berkayelken.bananazura.jdbc.annotation.Id;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.Locale;

import static io.github.berkayelken.bananazura.common.util.CommonUtil.isNotBlank;
import static java.lang.reflect.Modifier.isFinal;
import static java.lang.reflect.Modifier.isStatic;

public final class FieldUtil {
	private static final Locale LOCALE = Locale.ENGLISH;

	private FieldUtil() {

	}

	public static<T extends Annotation> T getAnnotation(Class<T> annotationClass, Field field) {
		if (field.isAnnotationPresent(annotationClass)) {
			return field.getAnnotation(annotationClass);
		}

		return null;
	}

	public static String getColumnName(Field field) {
		Column column = getAnnotation(Column.class, field);
		Id id  = getAnnotation(Id.class, field);
		String defaultName = field.getName().toUpperCase(LOCALE);

		return getColumnName(column, id, defaultName);
	}

	public static String getColumnName(Id id) {
		if(id == null)
			return null;
		if(isNotBlank(id.name()))
			return id.name();
		if(isNotBlank(id.value()))
			return id.value();
		return null;
	}

	public static String getColumnName(Column column) {
		if(column == null)
			return null;
		if(isNotBlank(column.name()))
			return column.name();
		if(isNotBlank(column.value()))
			return column.value();
		return null;
	}

	public static String getColumnName(Column column, Id id, String defaultName) {
		String columnName = getColumnName(column);
		String idName = getColumnName(id);
		if (isNotBlank(columnName))
			return columnName;
		if (isNotBlank(idName))
			return idName;
		return defaultName;
	}


	public static boolean isMutableInstanceField(Field field) {
		boolean staticField = isStatic(field.getModifiers());
		boolean finalField = isFinal(field.getModifiers());

		return !staticField && !finalField;
	}

}
