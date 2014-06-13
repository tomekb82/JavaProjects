package eu.tbelina.spring.dao.impl.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.simple.ParameterizedBeanPropertyRowMapper;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import eu.tbelina.spring.dao.IExpenseDAO;
import eu.tbelina.spring.model.Expense;

//@Repository("jdbcExpenseDAO")
public class JdbcExpenseDAO extends JdbcDaoSupport implements IExpenseDAO {
	
	private static final String SQL_SELECT_EXPENSE_BY_ID 
		= "select id, name, value, quantity, date from expenses where id = ?";
	private static final String SQL_SELECT_EXPENSES 
	= "select id, name, value, quantity, date from expenses";
	private static final String SQL_UPDATE_EXPENSE 
		= "update expenses set id = ?, name = ?, value = ?, quantity = ?, date = ?" + "where id = ?";
	private static final String SQL_DELETE_EXPENSE 
	= "delete from expenses where id = ?";
	private static final String SQL_INSERT_EXPENSE 
		= "insert into expenses (id, name, value, quantity, date) values (?, ?, ?, ?, ?)";
	
	private DataSource dataSource;

	public void createExpenses(){
		getJdbcTemplate().execute("create table expenses (id bigint, name varchar, value float, quantity int, date date)");
	}
	
	public void dropExpenses(){
		getJdbcTemplate().execute("drop table expenses");
	}
	
	@Override
	public void addExpense(Expense expense) {
		
		Map<String,Object> params = new HashMap<String, Object>();
		params.put("id", expense.getId());
		params.put("name", expense.getName());
		params.put("value", expense.getValue());
		params.put("quantity", expense.getQuantity());
		params.put("date", expense.getDate());
			
		getJdbcTemplate().update(SQL_INSERT_EXPENSE, 
				expense.getId(),
				expense.getName(),
				expense.getValue(),
				expense.getQuantity(),
				expense.getDate());
				
		//jdbcTemplate.update(SQL_INSERT_EXPENSE, params);
		//expense.setId(queryForIdentity());
	}

	@Override
	public void updateExpense(Expense expense) {
		getJdbcTemplate().update(SQL_UPDATE_EXPENSE,
				expense.getId(),
				expense.getName(),
				expense.getValue(),
				expense.getQuantity(),
				expense.getDate(),
				
				expense.getId());
		//expense.setId(queryForIdentity());		
		
	}

	@Override
	public void deleteExpense(Expense expense) {
		getJdbcTemplate().update(SQL_DELETE_EXPENSE,
				expense.getId());
		//expense.setId(queryForIden
		
	}

	@Override
	public List<Expense> getExpenses() {
	
		List<Expense> expenses = getJdbcTemplate().query(
				SQL_SELECT_EXPENSES, 
				ParameterizedBeanPropertyRowMapper.newInstance(Expense.class));
				
		return expenses;
	}
	
	@Override
	public Expense getExpenseById(long id) {
		
		return getJdbcTemplate().queryForObject(
				SQL_SELECT_EXPENSE_BY_ID,
				new ParameterizedRowMapper<Expense>(){
					public Expense mapRow(ResultSet rs, int rowNum) throws SQLException {
						Expense expense = new Expense();
						expense.setId(rs.getLong(1));
						expense.setName(rs.getString(2));
						expense.setValue(rs.getFloat(3));
						expense.setQuantity(rs.getInt(4));
						expense.setDate(rs.getDate(5));
						System.out.println("AAAAAA=" + expense.toString());
						return expense;
					}
				},
				id  // parameter
				);
	}

	@Override
	public Expense getExpenseByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteExpenseById(long id) {
		// TODO Auto-generated method stub
		
	}
	
	
}
