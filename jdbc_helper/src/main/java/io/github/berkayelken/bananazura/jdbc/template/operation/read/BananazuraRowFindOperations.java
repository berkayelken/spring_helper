package io.github.berkayelken.bananazura.jdbc.template.operation.read;

import io.github.berkayelken.bananazura.jdbc.tuples.ColumnStatementTuple;
import io.github.berkayelken.bananazura.jdbc.tuples.InTuple;

import java.util.List;

public interface BananazuraRowFindOperations {
	<T> List<T> findAll(Class<T> modelClass);

	<T> T findById(Class<T> modelClass, Object... idValues);

	<T> T findById(Class<T> modelClass, List<Object> idValues);

	<T> List<T> findByEntity(T model);

	<T> List<T> findByEntity(T model, boolean usePrimitiveDefaults);

	<T> List<T> findByEntity(T model, boolean usePrimitiveDefaults, boolean useId);

	<T> T findFirstByEntity(T model);

	<T> T findFirstByEntity(T model, boolean usePrimitiveDefaults);

	<T> T findFirstByEntity(T model, boolean usePrimitiveDefaults, boolean useId);

	<T> List<T> findWithInStatement(Class<T> modelClass, List<InTuple<?>> inStatements);

	<T> List<T> findWithInStatement(Class<T> modelClass, InTuple<?>... inStatements);

	<T> List<T> findWithInStatement(Class<T> modelClass, List<ColumnStatementTuple<?>> statements, List<InTuple<?>> inStatements);

	<T> List<T> findWithInStatement(Class<T> modelClass, List<ColumnStatementTuple<?>> statements, InTuple<?>... inStatements);

}
