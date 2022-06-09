package io.github.berkayelken.bananazura.jdbc.template;

import io.github.berkayelken.bananazura.jdbc.template.operation.create.BananazuraRowInsertOperations;
import io.github.berkayelken.bananazura.jdbc.template.operation.create.BananazuraRowInserter;
import io.github.berkayelken.bananazura.jdbc.template.operation.delete.BananazuraRowDeleteOperations;
import io.github.berkayelken.bananazura.jdbc.template.operation.delete.BananazuraRowDeleter;
import io.github.berkayelken.bananazura.jdbc.template.operation.read.BananazuraRowFindOperations;
import io.github.berkayelken.bananazura.jdbc.template.operation.read.BananazuraRowFinder;
import io.github.berkayelken.bananazura.jdbc.template.operation.update.BananazuraRowUpdateOperations;
import io.github.berkayelken.bananazura.jdbc.template.operation.update.BananazuraRowUpdater;

import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import javax.sql.DataSource;

public class BananazuraNamedJdbcTemplate extends NamedParameterJdbcTemplate implements BananazuraJdbc{
	private final BananazuraRowInsertOperations inserter;
	private final BananazuraRowFindOperations finder;
	private final BananazuraRowUpdateOperations updater;
	private final BananazuraRowDeleteOperations deleter;

	public BananazuraNamedJdbcTemplate(DataSource dataSource) {
		super(dataSource);
		inserter = new BananazuraRowInserter(getJdbcOperations());
		finder = new BananazuraRowFinder(getJdbcOperations());
		updater = new BananazuraRowUpdater(getJdbcOperations());
		deleter = new BananazuraRowDeleter(getJdbcOperations());
	}

	public BananazuraNamedJdbcTemplate(JdbcOperations classicJdbcTemplate) {
		super(classicJdbcTemplate);
		inserter = new BananazuraRowInserter(getJdbcOperations());
		finder = new BananazuraRowFinder(getJdbcOperations());
		updater = new BananazuraRowUpdater(getJdbcOperations());
		deleter = new BananazuraRowDeleter(getJdbcOperations());
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
