package io.github.berkayelken.bananazura.jdbc.util;

import io.github.berkayelken.bananazura.jdbc.template.operation.OperationType;
import io.github.berkayelken.bananazura.jdbc.tuples.ColumnStatementTuple;
import io.github.berkayelken.bananazura.jdbc.tuples.InTuple;

import java.util.List;

import static io.github.berkayelken.bananazura.common.util.CommonUtil.isBlank;
import static io.github.berkayelken.bananazura.common.util.CommonUtil.isNotBlank;
import static io.github.berkayelken.bananazura.jdbc.util.ColumnUtil.getColumnNames;
import static io.github.berkayelken.bananazura.jdbc.util.ColumnUtil.getIdColumnNames;
import static io.github.berkayelken.bananazura.jdbc.util.TableUtil.getTableName;
import static java.util.stream.Collectors.toList;

public final class QueryUtil {
	private QueryUtil() {

	}

	public static String getQuery(Class<?> modelClass, OperationType operationType) {
		return getQuery(modelClass, operationType, null);
	}

	public static String getQueryWithId(Class<?> modelClass, OperationType operationType) {
		String tableName = getTableName(modelClass);
		List<ColumnStatementTuple<?>> columns = getIdColumnNames(modelClass);

		return operationType.createQuery(tableName, columns);
	}

	public static String getQuery(Class<?> modelClass, OperationType operationType, List<ColumnStatementTuple<?>> columnClause) {
		String tableName = getTableName(modelClass);

		if(isNotBlank(columnClause)) {
			return operationType.createQuery(tableName, columnClause, null);
		}
		List<ColumnStatementTuple<?>> columns = getColumnNames(modelClass);

		return operationType.createQuery(tableName, columns);
	}

	public static String getInQuery(Class<?> modelClass, OperationType operationType, List<InTuple<?>> inClause) {
		return getInQuery(modelClass, operationType, inClause, null);
	}

	public static String getInQuery(Class<?> modelClass, OperationType operationType, List<InTuple<?>> inClause, List<ColumnStatementTuple<?>> columnClause) {
		String tableName = getTableName(modelClass);

		if(isBlank(inClause)) {
			return getQuery(modelClass, operationType);
		}

		List<String> inColumns = inClause.stream().map(in -> in.getColumnName()).collect(toList());

		if(isBlank(columnClause)) {
			return operationType.createInQuery(tableName,inColumns);
		}

		return operationType.createQuery(tableName, columnClause, inColumns);
	}
}
