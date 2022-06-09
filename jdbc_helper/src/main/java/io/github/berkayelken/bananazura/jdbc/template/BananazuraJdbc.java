package io.github.berkayelken.bananazura.jdbc.template;

import io.github.berkayelken.bananazura.jdbc.template.operation.create.BananazuraRowInsertOperations;
import io.github.berkayelken.bananazura.jdbc.template.operation.delete.BananazuraRowDeleteOperations;
import io.github.berkayelken.bananazura.jdbc.template.operation.read.BananazuraRowFindOperations;
import io.github.berkayelken.bananazura.jdbc.template.operation.update.BananazuraRowUpdateOperations;

public interface BananazuraJdbc {
	BananazuraRowInsertOperations getInsertOperations();

	BananazuraRowFindOperations getFinderOperations();

	BananazuraRowUpdateOperations getUpdateOperations();

	BananazuraRowDeleteOperations getDeleteOperations();
}
