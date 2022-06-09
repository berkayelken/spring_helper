package io.github.berkayelken.bananazura.jdbc.template;

import io.github.berkayelken.bananazura.jdbc.template.operation.create.BananazuraRowInsertOperations;
import io.github.berkayelken.bananazura.jdbc.template.operation.create.BananazuraRowInserter;
import io.github.berkayelken.bananazura.jdbc.template.operation.delete.BananazuraRowDeleteOperations;
import io.github.berkayelken.bananazura.jdbc.template.operation.delete.BananazuraRowDeleter;
import io.github.berkayelken.bananazura.jdbc.template.operation.read.BananazuraRowFindOperations;
import io.github.berkayelken.bananazura.jdbc.template.operation.read.BananazuraRowFinder;
import io.github.berkayelken.bananazura.jdbc.template.operation.update.BananazuraRowUpdateOperations;
import io.github.berkayelken.bananazura.jdbc.template.operation.update.BananazuraRowUpdater;

import org.springframework.jdbc.core.JdbcTemplate;


public class BananazuraJdbcTemplate extends JdbcTemplate implements BananazuraJdbc {
	private final BananazuraRowInsertOperations inserter;
	private final BananazuraRowFindOperations finder;
	private final BananazuraRowUpdateOperations updater;
	private final BananazuraRowDeleteOperations deleter;

	public BananazuraJdbcTemplate() {
		inserter = new BananazuraRowInserter(this);
		finder = new BananazuraRowFinder(this);
		updater = new BananazuraRowUpdater(this);
		deleter = new BananazuraRowDeleter(this);
	}

	@Override
	public BananazuraRowInsertOperations getInsertOperations() {
		return inserter;
	}

	@Override
	public BananazuraRowFindOperations getFinderOperations() {
		return finder;
	}

	@Override
	public BananazuraRowUpdateOperations getUpdateOperations() {
		return updater;
	}

	@Override
	public BananazuraRowDeleteOperations getDeleteOperations() {
		return deleter;
	}
}
