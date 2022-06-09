package io.github.berkayelken.bananazura.jdbc.tuples;

import io.github.berkayelken.bananazura.jdbc.annotation.Id;
import io.github.berkayelken.bananazura.jdbc.template.operation.LogicalOperator;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ColumnStatementTuple<T> extends ColumnTuple<T> {
	private LogicalOperator logicalOperator = LogicalOperator.EQUALS;

	public ColumnStatementTuple(String columnName, T value, Id id, LogicalOperator logicalOperator) {
		super(columnName, value, id);
		setLogicalOperator(logicalOperator);
	}

	public static ColumnStatementTuple<?> getStatementTuple(ColumnTuple columnTuple) {
		return new ColumnStatementTuple<>(columnTuple.getColumnName(), columnTuple.getValue(), columnTuple.getId(), LogicalOperator.EQUALS);
	}
}
