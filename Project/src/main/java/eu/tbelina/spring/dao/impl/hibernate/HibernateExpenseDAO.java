package eu.tbelina.spring.dao.impl.hibernate;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import eu.tbelina.spring.dao.IExpenseDAO;
import eu.tbelina.spring.model.Expense;

//@Repository("hibernateExpenseDAO")
public class HibernateExpenseDAO implements IExpenseDAO{

	private static final String SQL_SELECT_EXPENSE_BY_ID = "from Expense where id=?";
	private static final String SQL_DELETE_EXPENSE_BY_ID = "delete Expense where id=?";
	private static final String SQL_SELECT_EXPENSE_BY_NAME = "from Expense where name=?";
	private static final String SQL_SELECT_EXPENSES = "from Expense";
	
	private SessionFactory sessionFactory;
		
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public void addExpense(Expense expense) {
		getSessionFactory().getCurrentSession().save(expense);	
	}

	@Override
	public void updateExpense(Expense expense) {
		getSessionFactory().getCurrentSession().update(expense);
		
	}

	@Override
	public void deleteExpense(Expense expense) {
		getSessionFactory().getCurrentSession().delete(expense);
		
	}

	@Override
	public List<Expense> getExpenses() {
		return getSessionFactory().getCurrentSession().createQuery(SQL_SELECT_EXPENSES).list();
	}

	@Override
	public Expense getExpenseById(long id) {
		List<Expense> list = getSessionFactory().getCurrentSession()
				.createQuery(SQL_SELECT_EXPENSE_BY_ID)
		        .setParameter(0, id).list();
		
		return list.get(0);
	}

	@Override
	public Expense getExpenseByName(String name) {
		List<Expense> list = getSessionFactory().getCurrentSession()
				.createQuery(SQL_SELECT_EXPENSE_BY_NAME)
		        .setParameter(0, name).list();
		
		return list.get(0);
	}

	@Override
	public void deleteExpenseById(long id) {
		Query query = getSessionFactory().getCurrentSession()
				.createQuery(SQL_DELETE_EXPENSE_BY_ID)
		        .setParameter(0, id);
		query.executeUpdate();
	}

}
