package io.github.berkayelken.bananazura.jdbc.tuples;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InTuple<T> {
	private String columnName;
	private List<T> values;
}
