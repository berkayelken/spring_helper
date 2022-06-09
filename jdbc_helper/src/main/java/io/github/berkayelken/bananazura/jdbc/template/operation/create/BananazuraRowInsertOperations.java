package io.github.berkayelken.bananazura.jdbc.template.operation.create;

import java.util.List;
import java.util.Map;

public interface BananazuraRowInsertOperations {
	<T> int insert(T model);

	<T> List<Map<String, Object>> insertAndGetId(T model);

	<T> int[] insertAll(T... models);

	<T> int[] insertAll(List<T> models);
}
