package io.github.berkayelken.bananazura.jdbc.tuples;

import io.github.berkayelken.bananazura.jdbc.annotation.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ColumnTuple<T> {
	private String columnName;
	private T value;
	private Id id;

	public ColumnTuple(String columnName, T value, Id id) {
		setColumnName(columnName);
		setValue(value);
		setId(id);
	}

	public boolean isId() {
		return id != null;
	}
}
