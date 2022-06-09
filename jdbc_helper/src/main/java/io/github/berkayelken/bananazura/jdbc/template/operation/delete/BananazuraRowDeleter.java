package io.github.berkayelken.bananazura.jdbc.template.operation.delete;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import io.github.berkayelken.bananazura.jdbc.tuples.ColumnStatementTuple;
import io.github.berkayelken.bananazura.jdbc.tuples.ColumnTuple;
import io.github.berkayelken.bananazura.jdbc.tuples.InTuple;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import static io.github.berkayelken.bananazura.jdbc.template.operation.OperationType.DELETE;
import static io.github.berkayelken.bananazura.jdbc.util.ColumnUtil.filterPrimitives;
import static io.github.berkayelken.bananazura.jdbc.util.ColumnUtil.getColumnStatementList;
import static io.github.berkayelken.bananazura.jdbc.util.ColumnUtil.getColumnTuples;
import static io.github.berkayelken.bananazura.jdbc.util.ColumnUtil.getIdColumnNamesArray;
import static io.github.berkayelken.bananazura.jdbc.util.ColumnUtil.getInStatementList;
import static io.github.berkayelken.bananazura.jdbc.util.QueryUtil.getInQuery;
import static io.github.berkayelken.bananazura.jdbc.util.QueryUtil.getQuery;
import static io.github.berkayelken.bananazura.jdbc.util.QueryUtil.getQueryWithId;
import static java.util.stream.Collectors.toList;

public class BananazuraRowDeleter implements BananazuraRowDeleteOperations{
	private final JdbcOperations jdbcOperations;

	public BananazuraRowDeleter(JdbcOperations jdbcOperations) {
		this.jdbcOperations = jdbcOperations;
	}

	@Override
	public int deleteAll(Class<?> modelClass) {
		String query = getQuery(modelClass, DELETE);
		return jdbcOperations.update(query);
	}

	@Override
	public <T> List<Map<String, Object>> deleteAllAndGetIds(Class<T> modelClass) {
		KeyHolder keyHolder = new GeneratedKeyHolder();
		final String[] generatedColumns = getIdColumnNamesArray(modelClass);
		String query = getQuery(modelClass, DELETE);

		jdbcOperations.update(con -> con.prepareStatement(query, generatedColumns), keyHolder);

		return keyHolder.getKeyList();
	}

	@Override
	public <T, R> void deleteById(Class<T> modelClass, R idValue) {
		String query = getQueryWithId(modelClass, DELETE);
		jdbcOperations.update(query, idValue);
	}

	@Override
	public <T, R> void deleteById(Class<T> modelClass, R... idValues) {
		String query = getQueryWithId(modelClass, DELETE);
		jdbcOperations.update(query, idValues);
	}

	@Override
	public <T, R> void deleteById(Class<T> modelClass, List<R> idValues) {
		String query = getQueryWithId(modelClass, DELETE);
		jdbcOperations.update(query, idValues.toArray());
	}

	@Override
	public <T> void deleteByEntity(T model) {
		deleteByEntity(model, false);
	}

	@Override
	public <T> void deleteByEntity(T model, boolean usePrimitiveDefaults) {
		List<ColumnStatementTuple<?>> columnTuples = filterPrimitives(getColumnTuples(model), usePrimitiveDefaults, false);
		String query = getQuery(model.getClass(), DELETE, columnTuples);
		jdbcOperations.update(query, columnTuples.stream().map(ColumnTuple::getValue).toArray());
	}

	@Override
	public <T> List<Map<String, Object>> deleteByEntityAndGetIds(T model) {
		return deleteByEntityAndGetIds(model, false);
	}

	@Override
	public <T> List<Map<String, Object>> deleteByEntityAndGetIds(T model, boolean usePrimitiveDefaults) {
		KeyHolder keyHolder = new GeneratedKeyHolder();
		final String[] generatedColumns = getIdColumnNamesArray(model.getClass());

		List<ColumnStatementTuple<?>> columnTuples = filterPrimitives(getColumnTuples(model), usePrimitiveDefaults, false);
		String query = getQuery(model.getClass(), DELETE, columnTuples);
		AtomicInteger counter = new AtomicInteger();

		jdbcOperations.update(con -> {
			PreparedStatement ps = con.prepareStatement(query, generatedColumns);

			columnTuples.stream().map(ColumnTuple::getValue).forEach(val -> {
				try {
					ps.setObject(counter.incrementAndGet(), val);
				} catch (SQLException e) {
					LoggerFactory.getLogger(getClass()).error("PreparedStatement is not properly handled. :: cause={}", e);
				}
			});

			return ps;
		}, keyHolder);

		return keyHolder.getKeyList();
	}

	@Override
	public <T> void deleteWithInStatement(Class<T> modelClass, InTuple<?>... inStatements) {
		deleteWithInStatement(modelClass, getInStatementList(inStatements));
	}

	@Override
	public <T> void deleteWithInStatement(Class<T> modelClass, List<InTuple<?>> inStatements) {
		String query = getInQuery(modelClass, DELETE, inStatements);
		jdbcOperations.update(query, inStatements.stream().map(InTuple::getValues).toArray());
	}

	@Override
	public <T> void deleteWithStatement(Class<T> modelClass, ColumnStatementTuple<?>... statements) {
		deleteWithStatement(modelClass, getColumnStatementList(statements));
	}

	@Override
	public <T> void deleteWithStatement(Class<T> modelClass, List<ColumnStatementTuple<?>> statements) {
		String query = getQuery(modelClass, DELETE, statements);
		jdbcOperations.update(query, statements.stream().map(ColumnTuple::getValue).toArray());
	}

	@Override
	public <T> void deleteWithStatement(Class<T> modelClass, List<ColumnStatementTuple<?>> statements, InTuple<?>... inStatements) {
		deleteWithStatement(modelClass, statements, getInStatementList(inStatements));
	}

	@Override
	public <T> void deleteWithStatement(Class<T> modelClass, List<ColumnStatementTuple<?>> statements, List<InTuple<?>> inStatements) {
		String query = getInQuery(modelClass, DELETE, inStatements, statements);
		List<Object> values = statements.stream().map(ColumnTuple::getValue).collect(toList());
		values.addAll(inStatements.stream().map(InTuple::getValues).collect(toList()));
		jdbcOperations.update(query, values.toArray());
	}

	@Override
	public <T> List<Map<String, Object>> deleteWithInStatementAndGetIds(Class<T> modelClass, InTuple<?>... inStatements) {
		return deleteWithInStatementAndGetIds(modelClass, Arrays.asList(inStatements));
	}

	@Override
	public <T> List<Map<String, Object>> deleteWithInStatementAndGetIds(Class<T> modelClass, List<InTuple<?>> inStatements) {
		KeyHolder keyHolder = new GeneratedKeyHolder();
		final String[] generatedColumns = getIdColumnNamesArray(modelClass);

		String query = getInQuery(modelClass, DELETE, inStatements);
		AtomicInteger counter = new AtomicInteger();

		jdbcOperations.update(con -> {
			PreparedStatement ps = con.prepareStatement(query, generatedColumns);

			inStatements.stream().map(InTuple::getValues).forEach(val -> {
				try {
					ps.setObject(counter.incrementAndGet(), val);
				} catch (SQLException e) {
					LoggerFactory.getLogger(getClass()).error("PreparedStatement is not properly handled. :: cause={}", e);
				}
			});

			return ps;
		}, keyHolder);

		return keyHolder.getKeyList();
	}

	@Override
	public <T> List<Map<String, Object>> deleteWithStatementAndGetIds(Class<T> modelClass, ColumnStatementTuple<?>... statements) {
		return deleteWithStatementAndGetIds(modelClass, getColumnStatementList(statements));
	}

	@Override
	public <T> List<Map<String, Object>> deleteWithStatementAndGetIds(Class<T> modelClass, List<ColumnStatementTuple<?>> statements) {
		return deleteWithStatementAndGetIds(modelClass, statements, new ArrayList<>());
	}

	@Override
	public <T> List<Map<String, Object>> deleteWithStatementAndGetIds(Class<T> modelClass, List<ColumnStatementTuple<?>> statements,
			InTuple<?>... inStatements) {
		return deleteWithStatementAndGetIds(modelClass, statements, getInStatementList(inStatements));
	}

	@Override
	public <T> List<Map<String, Object>> deleteWithStatementAndGetIds(Class<T> modelClass, List<ColumnStatementTuple<?>> statements,
			List<InTuple<?>> inStatements) {
		String query = getInQuery(modelClass, DELETE, inStatements, statements);
		List<Object> values = statements.stream().map(ColumnTuple::getValue).collect(toList());
		values.addAll(inStatements.stream().map(InTuple::getValues).collect(toList()));

		KeyHolder keyHolder = new GeneratedKeyHolder();
		final String[] generatedColumns = getIdColumnNamesArray(modelClass);

		AtomicInteger counter = new AtomicInteger();

		jdbcOperations.update(con -> {
			PreparedStatement ps = con.prepareStatement(query, generatedColumns);

			values.forEach(val -> {
				try {
					ps.setObject(counter.incrementAndGet(), val);
				} catch (SQLException e) {
					LoggerFactory.getLogger(getClass()).error("PreparedStatement is not properly handled. :: cause={}", e);
				}
			});

			return ps;
		}, keyHolder);

		return keyHolder.getKeyList();
	}

}
