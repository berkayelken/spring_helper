package io.github.berkayelken.bananazura.jdbc.template.operation.delete;

import io.github.berkayelken.bananazura.jdbc.tuples.ColumnStatementTuple;
import io.github.berkayelken.bananazura.jdbc.tuples.InTuple;

import java.util.List;
import java.util.Map;

public interface BananazuraRowDeleteOperations {
	int deleteAll(Class<?> modelClass);

	<T> List<Map<String, Object>> deleteAllAndGetIds(Class<T> modelClass);

	<T, R> void deleteById(Class<T> modelClass, R idValue);

	<T, R> void deleteById(Class<T> modelClass, R... idValues);

	<T, R> void deleteById(Class<T> modelClass, List<R> idValues);

	<T> void deleteByEntity(T model);

	<T> void deleteByEntity(T model, boolean usePrimitiveDefaults);

	<T> List<Map<String, Object>> deleteByEntityAndGetIds(T model);

	<T> List<Map<String, Object>> deleteByEntityAndGetIds(T model, boolean usePrimitiveDefaults);

	<T> void deleteWithInStatement(Class<T> modelClass, InTuple<?>... inStatements);

	<T> void deleteWithInStatement(Class<T> modelClass, List<InTuple<?>> inStatements);

	<T> void deleteWithStatement(Class<T> modelClass, ColumnStatementTuple<?>... statements);

	<T> void deleteWithStatement(Class<T> modelClass, List<ColumnStatementTuple<?>> statements);

	<T> void deleteWithStatement(Class<T> modelClass, List<ColumnStatementTuple<?>> statements, InTuple<?>... inStatements);

	<T> void deleteWithStatement(Class<T> modelClass, List<ColumnStatementTuple<?>> statements, List<InTuple<?>> inStatements);

	<T> List<Map<String, Object>> deleteWithInStatementAndGetIds(Class<T> modelClass, InTuple<?>... inStatements);

	<T> List<Map<String, Object>> deleteWithInStatementAndGetIds(Class<T> modelClass, List<InTuple<?>> inStatements);

	<T> List<Map<String, Object>> deleteWithStatementAndGetIds(Class<T> modelClass, ColumnStatementTuple<?>... statements);

	<T> List<Map<String, Object>> deleteWithStatementAndGetIds(Class<T> modelClass, List<ColumnStatementTuple<?>> statements);

	<T> List<Map<String, Object>> deleteWithStatementAndGetIds(Class<T> modelClass, List<ColumnStatementTuple<?>> statements, InTuple<?>... inStatements);

	<T> List<Map<String, Object>> deleteWithStatementAndGetIds(Class<T> modelClass, List<ColumnStatementTuple<?>> statements, List<InTuple<?>> inStatements);
}
