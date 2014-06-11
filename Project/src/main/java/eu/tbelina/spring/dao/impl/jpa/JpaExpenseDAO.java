package eu.tbelina.spring.dao.impl.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import eu.tbelina.spring.dao.IExpenseDAO;
import eu.tbelina.spring.model.Expense;

//TODO: 
@Repository("jpaExpenseDAO")
public class JpaExpenseDAO {/*implements IExpenseDAO{

	private static final String SQL_SELECT_EXPENSES = "SELECT e FROM Expense e";
	
	@PersistenceContext
	private EntityManager em;
	
	public EntityManager getEm() {
		return em;
	}

	public void setEm(EntityManager em) {
		this.em = em;
	}

	@Override
	public void addExpense(Expense expense) {
		em.persist(expense);
	}

	@Override
	public void updateExpense(Expense expense) {
		em.merge(expense);
		
	}

	@Override
	public void deleteExpense(Expense expense) {
		em.remove(expense);
	}

	@Override
	public List<Expense> getExpenses() {
		Query query = em.createQuery(SQL_SELECT_EXPENSES);
    
		return query.getResultList();

	}

	@Override
	public Expense getExpenseById(long id) {
		return em.find(Expense.class, id);
	}*/
}
