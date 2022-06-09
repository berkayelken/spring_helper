package io.github.berkayelken.bananazura.jdbc.rowmapper;

import io.github.berkayelken.bananazura.jdbc.util.FieldUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.RowMapper;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Locale;

import static io.github.berkayelken.bananazura.common.util.CommonUtil.isNotBlank;
import static io.github.berkayelken.bananazura.jdbc.util.FieldUtil.getColumnName;
import static java.lang.reflect.Modifier.isFinal;
import static java.lang.reflect.Modifier.isStatic;

public class BananazuraRowMapper<T> implements RowMapper<T> {
	private static final Logger LOGGER = LoggerFactory.getLogger(BananazuraRowMapper.class);
	private static final Locale LOCALE = Locale.ENGLISH;

	private Class<T> pojoClass;

	private BananazuraRowMapper(Class<T> pojoClass) {
		this.pojoClass = pojoClass;
	}

	@Override
	public T mapRow(ResultSet rs, int rowNum) {
		T pojo = createPojo();

		Arrays.stream(pojoClass.getDeclaredFields())
				.filter(FieldUtil::isMutableInstanceField)
				.forEach(field -> setField(rs, pojo, field));

		return pojo;
	}

	public static<T> BananazuraRowMapper<T> getRowMapper(Class<T> pojoClass) {
		return new BananazuraRowMapper<>(pojoClass);
	}

	private T createPojo() {
		try {
			return pojoClass.getConstructor().newInstance();
		} catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
			LOGGER.error("Row could not be converted to POJO.", e);
			return null;
		}
	}

	private void setField(ResultSet rs, T pojo, Field field) {
		try {
			Object rowValue = rs.getObject(getColumnName(field), field.getType());
			field.setAccessible(true);
			field.set(pojo, rowValue);
		} catch (IllegalAccessException e) {
			LOGGER.error("Row value could not be set to POJO.", e);
		} catch (SQLException e) {
			LOGGER.error("Row cursor was faced with exception.", e);
		}
	}

}
