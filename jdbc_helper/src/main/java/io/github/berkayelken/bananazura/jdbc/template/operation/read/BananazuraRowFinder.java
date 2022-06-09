package io.github.berkayelken.bananazura.jdbc.template.operation.read;

import io.github.berkayelken.bananazura.jdbc.tuples.ColumnStatementTuple;
import io.github.berkayelken.bananazura.jdbc.tuples.InTuple;
import org.springframework.jdbc.core.JdbcOperations;

import java.util.ArrayList;
import java.util.List;

import static io.github.berkayelken.bananazura.jdbc.rowmapper.BananazuraRowMapper.getRowMapper;
import static io.github.berkayelken.bananazura.jdbc.template.operation.OperationType.SELECT;
import static io.github.berkayelken.bananazura.jdbc.util.ColumnUtil.filterPrimitives;
import static io.github.berkayelken.bananazura.jdbc.util.ColumnUtil.getColumnTuples;
import static io.github.berkayelken.bananazura.jdbc.util.ColumnUtil.getColumnValues;
import static io.github.berkayelken.bananazura.jdbc.util.ColumnUtil.getInStatementList;
import static io.github.berkayelken.bananazura.jdbc.util.QueryUtil.getInQuery;
import static io.github.berkayelken.bananazura.jdbc.util.QueryUtil.getQuery;
import static io.github.berkayelken.bananazura.jdbc.util.QueryUtil.getQueryWithId;
import static java.util.stream.Collectors.toList;

public class BananazuraRowFinder implements BananazuraRowFindOperations {
	private final JdbcOperations jdbcOperations;

	public BananazuraRowFinder(JdbcOperations jdbcOperations) {
		this.jdbcOperations = jdbcOperations;
	}

	@Override
	public <T> List<T> findAll(Class<T> modelClass) {
		String query = getQuery(modelClass, SELECT);
		return jdbcOperations.query(query, getRowMapper(modelClass));
	}

	@Override
	public <T> T findById(Class<T> modelClass, List<Object> idValues) {
		return findById(modelClass, idValues.toArray());
	}

	@Override
	public <T> T findById(Class<T> modelClass, Object... idValues) {
		String query = getQueryWithId(modelClass, SELECT);
		return jdbcOperations.query(query, getRowMapper(modelClass), idValues).stream().findFirst().orElse(null);
	}

	@Override
	public <T> List<T> findByEntity(T model) {
		return findByEntity(model, false);
	}

	@Override
	public <T> List<T> findByEntity(T model, boolean usePrimitiveDefaults) {
		return findByEntity(model, usePrimitiveDefaults, true);
	}

	@Override
	public <T> List<T> findByEntity(T model, boolean usePrimitiveDefaults, boolean useId) {
		List<ColumnStatementTuple<?>> columnTuples = filterPrimitives(getColumnTuples(model), usePrimitiveDefaults, useId);
		String query = getQuery(model.getClass(), SELECT, columnTuples);
		List<Object> values = getColumnValues(model, usePrimitiveDefaults, useId);
		return (List<T>) jdbcOperations.query(query, getRowMapper(model.getClass()), values.toArray());
	}

	@Override
	public <T> T findFirstByEntity(T model) {
		return findFirstByEntity(model, false);
	}

	@Override
	public <T> T findFirstByEntity(T model, boolean usePrimitiveDefaults) {
		return findFirstByEntity(model, usePrimitiveDefaults, false);
	}

	@Override
	public <T> T findFirstByEntity(T model, boolean usePrimitiveDefaults, boolean useId) {
		return findByEntity(model, usePrimitiveDefaults, useId).stream().findFirst().orElse(null);
	}

	@Override
	public <T> List<T> findWithInStatement(Class<T> modelClass, InTuple<?>... inStatements) {
		return findWithInStatement(modelClass, getInStatementList(inStatements));
	}

	@Override
	public <T> List<T> findWithInStatement(Class<T> modelClass, List<InTuple<?>> inStatements) {
		return findWithInStatement(modelClass, new ArrayList<>(), inStatements);
	}

	@Override
	public <T> List<T> findWithInStatement(Class<T> modelClass, List<ColumnStatementTuple<?>> statements, InTuple<?>... inStatements) {
		return findWithInStatement(modelClass, statements, getInStatementList(inStatements));
	}

	@Override
	public <T> List<T> findWithInStatement(Class<T> modelClass, List<ColumnStatementTuple<?>> statements, List<InTuple<?>> inStatements) {
		String query = getInQuery(modelClass, SELECT, inStatements, statements);

		List<Object> values = new ArrayList<>();

		values.addAll(statements.stream().map(ColumnStatementTuple::getValue).collect(toList()));
		values.addAll(inStatements.stream().map(InTuple::getValues).collect(toList()));

		return jdbcOperations.query(query, getRowMapper(modelClass),values.toArray());
	}

}
