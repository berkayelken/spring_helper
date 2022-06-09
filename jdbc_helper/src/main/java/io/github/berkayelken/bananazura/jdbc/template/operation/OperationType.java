package io.github.berkayelken.bananazura.jdbc.template.operation;

import io.github.berkayelken.bananazura.jdbc.tuples.ColumnStatementTuple;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public enum OperationType {
	DELETE{
		public String createQuery(String table, List<ColumnStatementTuple<?>> columns, List<String> inColumns) {
			StringBuilder query = new StringBuilder(name());

			addFromPart(query, table);
			query.append(getWhereClause(columns, inColumns));

			return query.toString();
		}
	},
	INSERT{
		public String createQuery(String table, List<ColumnStatementTuple<?>> columns, List<String> inColumns) {
			StringBuilder query = new StringBuilder(name());

			query.append(INTO);
			addValues(query, columns);

			return query.toString();
		}
	},
	SELECT{
		public String createQuery(String table, List<ColumnStatementTuple<?>> columns, List<String> inColumns) {
			StringBuilder query = new StringBuilder(name());

			query.append(ALL_COLUMNS);
			addFromPart(query, table);
			query.append(getWhereClause(columns, inColumns));

			return query.toString();
		}
	},
	UPDATE{
		public String createQuery(String table, List<ColumnStatementTuple<?>> columns, List<String> inColumns) {
			StringBuilder query = new StringBuilder(name());

			query.append(" ");
			query.append(table);
			query.append(SET);

			addUpdateValues(query, columns);
			query.append(getWhereClause(columns, inColumns));

			return query.toString();
		}
	};

	private static final String ALL_COLUMNS = " * ";
	private static final String FROM = " FROM ";
	private static final String WHERE = " WHERE ";
	private static final String INTO = " INTO ";
	private static final String SET = " SET ";
	private static final String VALUES = " VALUES ";
	private static final String STATEMENT = " # ? ";
	private static final String QUOTA = "\"";
	private static final String AND = " AND ";
	private static final String IN = " IN ";
	private static final String VALUES_VAL = "?";
	private static final String COMMA = ", ";


	public String createQuery(String table, List<ColumnStatementTuple<?>> columns) {
		return createQuery(table, columns, null);
	}

	public String createInQuery(String table, List<String> inColumns){
		return createQuery(table, null, inColumns);
	}

	public abstract String createQuery(String table, List<ColumnStatementTuple<?>> columns, List<String> inColumns);

	private static String getWhereClause(List<ColumnStatementTuple<?>> columns, List<String> inColumns) {
		StringBuilder where = new StringBuilder(WHERE);

		if(columns == null)
			columns = new ArrayList<>();


		List<ColumnStatementTuple<?>> columnExclueded = inColumns == null ? columns:
				columns.stream().filter(col -> !inColumns.contains(col.getColumnName()))
				.collect(Collectors.toList());

		getClassicWhereConditions(where, columnExclueded);
		getInWhereConditions(where, inColumns);

		int index = where.lastIndexOf(AND);
		if(index == -1)
			return where.toString();

		return where.substring(0, index);
	}

	private static void getClassicWhereConditions(StringBuilder where, List<ColumnStatementTuple<?>> columnNames) {
		columnNames.stream().forEach(col -> {
			addColumnName(where, col.getColumnName());
			where.append(STATEMENT.replaceAll("#", col.getLogicalOperator().getOperator()));
			where.append(AND);
		});
	}

	private static void getInWhereConditions(StringBuilder where, List<String> inColumnNames) {
		AtomicInteger counter = new AtomicInteger();

		inColumnNames.stream().forEach(name -> {
			addColumnName(where, name);
			where.append(IN);
			where.append("args");
			where.append(counter.getAndIncrement());
			where.append(AND);
		});
	}

	private static void addValues(StringBuilder query, List<ColumnStatementTuple<?>> columnNames) {
		query.append("(");
		AtomicInteger counter = new AtomicInteger();
		int size = columnNames.size();
		columnNames.stream().forEach(col -> {
			addColumnName(query, col.getColumnName());
			if(counter.incrementAndGet() == size){
				query.append(")");
				return;
			}
			query.append(COMMA);
		});

		query.append(VALUES);
		query.append("(");
		int lastIndex = size - 1;
		for(int i = 0; i < size; i++) {
			query.append(VALUES_VAL);
			if(i == lastIndex){
				query.append(")");
				break;
			}
			query.append(COMMA);
		}
	}

	private static void addUpdateValues(StringBuilder query, List<ColumnStatementTuple<?>> columnNames) {
		AtomicInteger counter = new AtomicInteger();
		int size = columnNames.size();
		columnNames.stream().forEach(col -> {
			addColumnName(query, col.getColumnName());
			query.append(STATEMENT.replaceAll("#", col.getLogicalOperator().getOperator()));
			if(counter.incrementAndGet() == size){
				return;
			}
			query.append(COMMA);
		});
	}

	private static void addFromPart(StringBuilder builder, String table) {
		builder.append(FROM);
		builder.append(table);
	}

	private static void addColumnName(StringBuilder builder, String name) {
		builder.append(QUOTA);
		builder.append(name);
		builder.append(QUOTA);
	}
}
