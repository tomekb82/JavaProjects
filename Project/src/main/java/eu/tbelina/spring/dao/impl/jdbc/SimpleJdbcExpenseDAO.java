package eu.tbelina.spring.dao.impl.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.simple.ParameterizedBeanPropertyRowMapper;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcDaoSupport;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import eu.tbelina.spring.dao.IExpenseDAO;
import eu.tbelina.spring.model.Expense;

@Repository("simpleJdbcExpenseDAO")
public class SimpleJdbcExpenseDAO extends SimpleJdbcDaoSupport implements IExpenseDAO {
	
	private static final String SQL_SELECT_EXPENSE_BY_ID 
		= "select name, value, quantity, date from expenses where id = ?";
	private static final String SQL_SELECT_EXPENSES 
	= "select name, value, quantity, date from expenses";
	private static final String SQL_UPDATE_EXPENSE 
		= "update expenses set name = ?, value = ?, quantity = ?, date = ?" + "where id = ?";
	private static final String SQL_INSERT_EXPENSE 
		= "insert into expenses (name, value, quantity, date) values (?, ?, ?, ?)";
	
	public void createExpenses(){
		//getSimpleJdbcTemplate().query("create table expenses (id bigint, name varchar, value float, quantity int, date date)", null);
	}
	
	public void dropExpenses(){
		//getSimpleJdbcTemplate().query("drop table expenses", null);
	}
	
	@Override
	public void addExpense(Expense expense) {
		
		Map<String,Object> params = new HashMap<String, Object>();
		params.put("name", expense.getName());
		params.put("value", expense.getValue());
		params.put("quantity", expense.getQuantity());
		params.put("date", expense.getDate());
			
		getSimpleJdbcTemplate().update(SQL_INSERT_EXPENSE, 
				expense.getName(),
				expense.getValue(),
				expense.getQuantity(),
				expense.getDate());
				
		//jdbcTemplate.update(SQL_INSERT_EXPENSE, params);
		//expense.setId(queryForIdentity());
	}

	@Override
	public void updateExpense(Expense expense) {
		getSimpleJdbcTemplate().update(SQL_UPDATE_EXPENSE,
				expense.getName(),
				expense.getValue(),
				expense.getQuantity(),
				expense.getDate());
		//expense.setId(queryForIdentity());		
		
	}

	@Override
	public void deleteExpense(Expense expense) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Expense> getExpenses() {
	
		List<Expense> expenses = getSimpleJdbcTemplate().query(
				SQL_SELECT_EXPENSES, 
				ParameterizedBeanPropertyRowMapper.newInstance(Expense.class));
				
		return expenses;
	}
	
	@Override
	public Expense getExpenseById(long id) {
		
		return getSimpleJdbcTemplate().queryForObject(
				SQL_SELECT_EXPENSE_BY_ID,
				new ParameterizedRowMapper<Expense>(){
					public Expense mapRow(ResultSet rs, int rowNum) throws SQLException {
						Expense expense = new Expense();
						expense.setId(rs.getLong(1));
						expense.setName(rs.getString(2));
						expense.setValue(rs.getFloat(3));
						expense.setQuantity(rs.getInt(4));
						expense.setDate(rs.getDate(5));	
						return expense;
					}
				},
				id  // parameter
				);
	}
	
	
}
