package io.github.berkayelken.bananazura.jdbc.util;

import io.github.berkayelken.bananazura.jdbc.annotation.Id;
import io.github.berkayelken.bananazura.jdbc.template.operation.LogicalOperator;
import io.github.berkayelken.bananazura.jdbc.tuples.ColumnStatementTuple;
import io.github.berkayelken.bananazura.jdbc.tuples.ColumnTuple;
import io.github.berkayelken.bananazura.jdbc.tuples.InTuple;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

import static io.github.berkayelken.bananazura.common.util.PrimitiveDefaults.isDefaultValue;
import static io.github.berkayelken.bananazura.jdbc.util.FieldUtil.getAnnotation;
import static io.github.berkayelken.bananazura.jdbc.util.FieldUtil.getColumnName;
import static java.util.stream.Collectors.toList;

public final class ColumnUtil {
	private ColumnUtil() {

	}

	public static<T> List<ColumnTuple<?>> getIdTuples(T model) {
		List<ColumnTuple<?>> ids = Arrays.stream(model.getClass().getDeclaredFields())
				.filter(field -> field.isAnnotationPresent(Id.class)).map(field -> {
					try {
						field.setAccessible(true);
						return new ColumnTuple(field.getName(), field.get(model), field.getAnnotation(Id.class));
					} catch (Throwable t) {
						return null;
					}
				}).collect(toList());

		return ids;
	}

	public static List<ColumnStatementTuple<?>> getIdColumnNames(Class<?> modelClass) {
		return Arrays.stream(modelClass.getDeclaredFields()).filter(field -> field.isAnnotationPresent(Id.class))
				.map(FieldUtil::getColumnName)
				.map(name -> new ColumnStatementTuple(name, null, null, LogicalOperator.EQUALS)).collect(toList());
	}

	public static String[] getIdColumnNamesArray(Class<?> modelClass) {
		List<String> idColumns = getColumnNames(modelClass).stream().map(ColumnTuple::getColumnName).collect(toList());
		return idColumns.toArray(new String[idColumns.size()]);
	}

	public static<T> List<ColumnTuple<?>> getColumnTuples(T model) {
		return getColumnTuples(model, true, true);
	}

	public static<T> List<ColumnTuple<?>> getColumnTuples(T model, boolean usePrimitiveDefaults, boolean useId) {
		Stream<ColumnTuple<?>> columnStream = Arrays.stream(model.getClass().getDeclaredFields())
				.filter(FieldUtil::isMutableInstanceField)
				.map(field -> {
					try {
						field.setAccessible(true);
						Id id = getAnnotation(Id.class, field);
						return new ColumnTuple(getColumnName(field), field.get(model), id);
					} catch (Throwable t) {
						return null;
					}
				});

		return columnStream.filter(Objects::nonNull).filter(col -> isValidPrimitive(col, usePrimitiveDefaults))
				.filter(col -> isValidId(col, useId)).collect(toList());
	}

	public static List<ColumnStatementTuple<?>> filterPrimitives(List<ColumnTuple<?>> columnTuples, boolean usePrimitiveDefaults, boolean useId) {
		return columnTuples.stream().filter(col -> isValidPrimitive(col, usePrimitiveDefaults))
				.filter(col -> isValidId(col, useId))
				.map(ColumnStatementTuple::getStatementTuple).collect(toList());
	}

	private static boolean isValidPrimitive(ColumnTuple<?> columnTuple, boolean usePrimitiveDefaults) {
		if(usePrimitiveDefaults || !columnTuple.getValue().getClass().isPrimitive()) {
			return true;
		}
		return isDefaultValue(columnTuple.getValue());
	}

	private static boolean isValidId(ColumnTuple<?> columnTuple, boolean useId) {
		return useId || !columnTuple.isId();
	}

	public static List<ColumnStatementTuple<?>> getColumnNames(Class<?> modelClass) {
		return Arrays.stream(modelClass.getDeclaredFields()).filter(FieldUtil::isMutableInstanceField).map(FieldUtil::getColumnName)
				.map(name -> new ColumnStatementTuple(name, null, null, LogicalOperator.EQUALS)).collect(toList());
	}

	public static List<ColumnStatementTuple<?>> getColumnStatementList(ColumnStatementTuple<?>... statements) {
		return statements == null ? new ArrayList<>(): Arrays.asList(statements);
	}

	public static List<InTuple<?>> getInStatementList(InTuple<?>... inStatements) {
		return inStatements == null ? new ArrayList<>(): Arrays.asList(inStatements);
	}

	public static <T> List<Object> getColumnValues(T model, boolean usePrimitiveDefaults, boolean useId) {
		List<ColumnStatementTuple<?>> columnTuples = filterPrimitives(getColumnTuples(model), usePrimitiveDefaults, useId);
		List<ColumnTuple<?>> columns = getColumnTuples(model);

		return columns.stream().map(ColumnTuple::getValue).collect(toList());
	}
}
