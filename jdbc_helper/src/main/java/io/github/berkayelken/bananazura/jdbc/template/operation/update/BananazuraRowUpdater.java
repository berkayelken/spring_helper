package io.github.berkayelken.bananazura.jdbc.template.operation.update;

import io.github.berkayelken.bananazura.jdbc.tuples.ColumnStatementTuple;
import io.github.berkayelken.bananazura.jdbc.tuples.ColumnTuple;
import io.github.berkayelken.bananazura.jdbc.tuples.InTuple;

import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import static io.github.berkayelken.bananazura.jdbc.template.operation.OperationType.UPDATE;
import static io.github.berkayelken.bananazura.jdbc.util.ColumnUtil.getColumnValues;
import static io.github.berkayelken.bananazura.jdbc.util.ColumnUtil.getIdColumnNamesArray;
import static io.github.berkayelken.bananazura.jdbc.util.ColumnUtil.getInStatementList;
import static io.github.berkayelken.bananazura.jdbc.util.QueryUtil.getInQuery;
import static io.github.berkayelken.bananazura.jdbc.util.QueryUtil.getQueryWithId;

public class BananazuraRowUpdater implements BananazuraRowUpdateOperations {
	private final JdbcOperations jdbcOperations;

	public BananazuraRowUpdater(JdbcOperations jdbcOperations) {
		this.jdbcOperations = jdbcOperations;
	}

	@Override
	public <T> void updateByEntity(T model) {
		updateByEntity(model, false);
	}

	@Override
	public <T> void updateByEntity(T model, boolean usePrimitiveDefaults) {
		List<Object> columns = getColumnValues(model, usePrimitiveDefaults, false);
		String query = getQueryWithId(model.getClass(), UPDATE);
		jdbcOperations.update(query, columns.toArray());
	}

	@Override
	public <T> List<?> updateByEntityAndGetIds(T model) {
		return updateByEntityAndGetIds(model, false);
	}

	@Override
	public <T> List<?> updateByEntityAndGetIds(T model, boolean usePrimitiveDefaults) {
		KeyHolder keyHolder = new GeneratedKeyHolder();
		final String[] generatedColumns = getIdColumnNamesArray(model.getClass());

		List<Object> columns = getColumnValues(model, usePrimitiveDefaults, false);
		String query = getQueryWithId(model.getClass(), UPDATE);

		AtomicInteger counter = new AtomicInteger();

		jdbcOperations.update(con -> {
			PreparedStatement ps = con.prepareStatement(query, generatedColumns);

			columns.forEach(val -> {
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
	public <T> void updateWithInStatement(T model, InTuple<?>... inStatements) {
		updateWithInStatement(model, getInStatementList(inStatements));
	}

	@Override
	public <T> void updateWithInStatement(T model, List<InTuple<?>> inStatements) {
		updateWithInStatement(model, null, inStatements);
	}

	@Override
	public <T> void updateWithInStatement(T model, List<ColumnStatementTuple<?>> statements, InTuple<?>... inStatements) {
		updateWithInStatement(model, statements, getInStatementList(inStatements));
	}

	@Override
	public <T> void updateWithInStatement(T model, List<ColumnStatementTuple<?>> statements, List<InTuple<?>> inStatements) {
		List<Object> columns = getColumnValues(model, false, false);
		String query = getInQuery(model.getClass(), UPDATE, inStatements, statements);
		jdbcOperations.update(query, columns.toArray());
	}

	@Override
	public <T> List<?> updateWithInStatementAndGetIds(Class<T> modelClass, List<ColumnTuple<?>> values, InTuple<?>... inStatements) {
		return updateWithInStatementAndGetIds(modelClass, values, getInStatementList(inStatements));
	}

	@Override
	public <T> List<?> updateWithInStatementAndGetIds(Class<T> modelClass, List<ColumnTuple<?>> values, List<InTuple<?>> inStatements) {
		return updateWithInStatementAndGetIds(modelClass, values, new ArrayList<>(), inStatements);
	}

	@Override
	public <T> List<?> updateWithInStatementAndGetIds(Class<T> modelClass, List<ColumnTuple<?>> values, List<ColumnStatementTuple<?>> statements,
			InTuple<?>... inStatements) {
		return updateWithInStatementAndGetIds(modelClass, values, statements, getInStatementList(inStatements));
	}

	@Override
	public <T> List<?> updateWithInStatementAndGetIds(Class<T> modelClass, List<ColumnTuple<?>> values, List<ColumnStatementTuple<?>> statements,
			List<InTuple<?>> inStatements) {
		KeyHolder keyHolder = new GeneratedKeyHolder();
		final String[] generatedColumns = getIdColumnNamesArray(modelClass);

		String query = getInQuery(modelClass, UPDATE, inStatements, statements);

		AtomicInteger counter = new AtomicInteger();

		jdbcOperations.update(con -> {
			PreparedStatement ps = con.prepareStatement(query, generatedColumns);

			values.stream().map(ColumnTuple::getValue).forEach(val -> {
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
