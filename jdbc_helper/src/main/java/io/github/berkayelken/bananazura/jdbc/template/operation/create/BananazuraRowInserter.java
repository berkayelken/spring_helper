package io.github.berkayelken.bananazura.jdbc.template.operation.create;

import io.github.berkayelken.bananazura.jdbc.tuples.ColumnTuple;

import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import static io.github.berkayelken.bananazura.common.util.CommonUtil.isBlank;
import static io.github.berkayelken.bananazura.jdbc.template.operation.OperationType.INSERT;
import static io.github.berkayelken.bananazura.jdbc.util.ColumnUtil.getColumnTuples;
import static io.github.berkayelken.bananazura.jdbc.util.ColumnUtil.getIdColumnNamesArray;
import static io.github.berkayelken.bananazura.jdbc.util.QueryUtil.getQuery;
import static java.util.stream.Collectors.toList;

public class BananazuraRowInserter implements BananazuraRowInsertOperations {
	private final JdbcOperations jdbcOperations;

	public BananazuraRowInserter(JdbcOperations jdbcOperations) {
		this.jdbcOperations = jdbcOperations;
	}

	@Override
	public <T> int insert(T model) {
		String query = getQuery(model.getClass(), INSERT);
		List<Object> values = getColumnTuples(model).stream().map(ColumnTuple::getValue).collect(toList());
		return jdbcOperations.update(query, values);
	}

	@Override 
	public <T> List<Map<String, Object>> insertAndGetId(T model) {
		KeyHolder keyHolder = new GeneratedKeyHolder();
		final String[] generatedColumns = getIdColumnNamesArray(model.getClass());

		String query = getQuery(model.getClass(), INSERT);
		List<Object> values = getColumnTuples(model).stream().map(ColumnTuple::getValue).collect(toList());
		AtomicInteger counter = new AtomicInteger();

		jdbcOperations.update(con -> {
			PreparedStatement ps = con.prepareStatement(query, generatedColumns);
			values.stream().forEach(val -> {
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
	public <T> int[] insertAll(T... models) {
		List<T> modelList = models == null ? new ArrayList<>() : Arrays.asList(models);
		return insertAll(modelList);
	}

	@Override
	public <T> int[] insertAll(List<T> models) {
		if(isBlank(models)){
			return null;
		}

		String query = getQuery(models.get(0).getClass(), INSERT);
		List<Object[]> values = models.stream().map(model -> getColumnTuples(model).stream().map(ColumnTuple::getValue).toArray()).collect(toList());

		return jdbcOperations.batchUpdate(query, values);
	}
}
