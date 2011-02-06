package com.example.reclaadmin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import com.vaadin.addon.sqlcontainer.RowItem;
import com.vaadin.addon.sqlcontainer.TemporaryRowId;
import com.vaadin.addon.sqlcontainer.Util;
import com.vaadin.addon.sqlcontainer.query.Filter;
import com.vaadin.addon.sqlcontainer.query.FilteringMode;
import com.vaadin.addon.sqlcontainer.query.FreeformStatementDelegate;
import com.vaadin.addon.sqlcontainer.query.OrderBy;
import com.vaadin.addon.sqlcontainer.query.generator.StatementHelper;

@SuppressWarnings("serial")
public class _FreeFormDelegate implements FreeformStatementDelegate {

	private List<Filter> filters;
	private List<OrderBy> orderBys;

	@Override
	public String getQueryString(int offset, int limit)
			throws UnsupportedOperationException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getCountQuery() throws UnsupportedOperationException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setFilters(List<Filter> filters)
			throws UnsupportedOperationException {
		this.filters = filters;
	}

	@Override
	public void setFilters(List<Filter> filters, FilteringMode filteringMode)
			throws UnsupportedOperationException {
		setFilters(filters);

	}

	@Override
	public void setOrderBy(List<OrderBy> orderBys)
			throws UnsupportedOperationException {
		this.orderBys = orderBys;
	}

	@Override
	public int storeRow(Connection conn, RowItem row)
			throws UnsupportedOperationException, SQLException {

		PreparedStatement statement = null;
		if (row.getId() instanceof TemporaryRowId) {
			statement = conn
					.prepareStatement("INSERT INTO myActions VALUES(?, ?, ?, ?, DEFAULT,null)");
			setRowValues(statement, row);
		} else {
			statement = conn
					.prepareStatement("UPDATE myActions SET service = ?, theme = ?, observations = ?, action = ?, validation = ? WHERE id = ?");
			setRowValues(statement, row);
			statement.setInt(6, (Integer) row.getItemProperty("id")
					.getValue());
		}

		int retval = statement.executeUpdate();
		statement.close();
		return retval;
	}

	@Override
	public boolean removeRow(Connection conn, RowItem row)
			throws UnsupportedOperationException, SQLException {

		PreparedStatement statement = conn
				.prepareStatement("DELETE FROM myActions WHERE id = ?");
		statement.setInt(1, (Integer) row.getItemProperty("id").getValue());
		int rowsChanged = statement.executeUpdate();
		statement.close();
		return rowsChanged == 1;
	}

	@Override
	public String getContainsRowQueryString(Object... keys)
			throws UnsupportedOperationException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public StatementHelper getQueryStatement(int offset, int limit)
			throws UnsupportedOperationException {

		StatementHelper sh = new StatementHelper();
		StringBuffer query = new StringBuffer(
				"SELECT id, service, theme, observations, action FROM myActions");
		if (filters != null) {
			for (Filter f : filters) {
				generateFilter(query, f, filters.indexOf(f) == 0,
						FilteringMode.FILTERING_MODE_INCLUSIVE, sh);
			}
		}
		query.append(getOrderByString());
		if (offset != 0 || limit != 0) {
			query.append(" LIMIT ").append(limit);
			query.append(" OFFSET ").append(offset);
		}
		sh.setQueryString(query.toString());
		return sh;
	}

	@Override
	public StatementHelper getCountStatement()
			throws UnsupportedOperationException {

		StatementHelper sh = new StatementHelper();
		StringBuffer query = new StringBuffer("SELECT COUNT(*) FROM myActions");
		if (filters != null) {
			for (Filter f : filters) {
				generateFilter(query, f, filters.indexOf(f) == 0,
						FilteringMode.FILTERING_MODE_INCLUSIVE, sh);
			}
		}
		sh.setQueryString(query.toString());
		return sh;
	}

	@Override
	public StatementHelper getContainsRowQueryStatement(Object... keys)
			throws UnsupportedOperationException {

		StatementHelper sh = new StatementHelper();
		StringBuffer query = new StringBuffer(
				"SELECT service, theme, observations, action FROM myActions WHERE idAeroport = ?");
		sh.addParameterValue(keys[0]);
		sh.setQueryString(query.toString());
		return sh;
	}

	private String getOrderByString() {
		StringBuffer orderBuffer = new StringBuffer("");
		if (orderBys != null && !orderBys.isEmpty()) {
			orderBuffer.append(" ORDER BY ");
			OrderBy lastOrderBy = orderBys.get(orderBys.size() - 1);
			for (OrderBy orderBy : orderBys) {
				orderBuffer.append(Util.escapeSQL(orderBy.getColumn()));
				if (orderBy.isAscending()) {
					orderBuffer.append(" ASC");
				} else {
					orderBuffer.append(" DESC");
				}
				if (orderBy != lastOrderBy) {
					orderBuffer.append(", ");
				}
			}
		}
		return orderBuffer.toString();
	}

	private void setRowValues(PreparedStatement statement, RowItem row)
			throws SQLException {
		statement.setString(1, (String) row.getItemProperty("service")
				.getValue());
		statement
				.setString(2, (String) row.getItemProperty("theme").getValue());
		statement.setString(3, (String) row.getItemProperty("observations")
				.getValue());
		statement.setString(4, (String) row.getItemProperty("action")
				.getValue());
		statement.setString(5, (String) row.getItemProperty("validation")
				.getValue());
	}

	protected StringBuffer generateFilter(StringBuffer sb, Filter f,
			boolean firstFilter, FilteringMode filterMode, StatementHelper sh) {
		if (f.getValue() == null) {
			return sb;
		}
		if (Filter.ComparisonType.BETWEEN.equals(f.getComparisonType())) {
			if (f.getValue() != null && f.getSecondValue() != null) {
				sh.addParameterValue(f.getValue());
				sh.addParameterValue(f.getSecondValue());
			} else {
				return sb;
			}
		} else {
			if (f.getValue() != null) {
				sh.addParameterValue(f.getPreparedStatementValue());
			} else {
				return sb;
			}
		}
		if (firstFilter) {
			sb.append(" WHERE ");
		} else {
			if (FilteringMode.FILTERING_MODE_INCLUSIVE.equals(filterMode)) {
				sb.append(" AND ");
			} else if (FilteringMode.FILTERING_MODE_EXCLUSIVE
					.equals(filterMode)) {
				sb.append(" OR ");
			}
		}
		sb.append(f.toPreparedStatementString());
		return sb;
	}

}
