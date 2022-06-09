package io.github.berkayelken.bananazura.jdbc.template.operation.update;

import io.github.berkayelken.bananazura.jdbc.tuples.ColumnStatementTuple;
import io.github.berkayelken.bananazura.jdbc.tuples.ColumnTuple;
import io.github.berkayelken.bananazura.jdbc.tuples.InTuple;

import java.util.List;

public interface BananazuraRowUpdateOperations {

	<T> void updateByEntity(T model);

	<T> void updateByEntity(T model, boolean usePrimitiveDefaults);

	<T> List<?> updateByEntityAndGetIds(T model);

	<T> List<?> updateByEntityAndGetIds(T model, boolean usePrimitiveDefaults);

	<T> void updateWithInStatement(T model, InTuple<?>... inStatements);

	<T> void updateWithInStatement(T model, List<InTuple<?>> inStatements);

	<T> void updateWithInStatement(T model, List<ColumnStatementTuple<?>> statements, InTuple<?>... inStatements);

	<T> void updateWithInStatement(T model, List<ColumnStatementTuple<?>> statements, List<InTuple<?>> inStatements);

	<T> List<?> updateWithInStatementAndGetIds(Class<T> modelClass, List<ColumnTuple<?>> values, InTuple<?>... inStatements);

	<T> List<?> updateWithInStatementAndGetIds(Class<T> modelClass, List<ColumnTuple<?>> values, List<InTuple<?>> inStatements);

	<T> List<?> updateWithInStatementAndGetIds(Class<T> modelClass, List<ColumnTuple<?>> values,
			List<ColumnStatementTuple<?>> statements, InTuple<?>... inStatements);

	<T> List<?> updateWithInStatementAndGetIds(Class<T> modelClass, List<ColumnTuple<?>> values,
			List<ColumnStatementTuple<?>> statements, List<InTuple<?>> inStatements);
}
