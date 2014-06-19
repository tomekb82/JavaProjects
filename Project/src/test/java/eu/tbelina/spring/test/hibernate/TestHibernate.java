package eu.tbelina.spring.test.hibernate;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import eu.tbelina.spring.model.Contact;
import eu.tbelina.spring.model.Expense;
import eu.tbelina.spring.model.Group;
import eu.tbelina.spring.model.Income;
import eu.tbelina.spring.model.User;
import eu.tbelina.spring.service.IExpenseService;

@ContextConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
public class TestHibernate {
	
	Session session;
	
	@Autowired
	SessionFactory SessionFactory;
	
	@Before
	public void setUp() throws Exception {
		session = SessionFactory.openSession();
	}
	
	@Test
	public void testOneToOne(){
		
		session.beginTransaction();
		
		/* Add users without Contact */
		User user1 = new User();		
		user1.setName("Filip");
		user1.setSurname("Belina");
		User user2 = new User();
		user2.setName("Tomek");
		user2.setSurname("Be");
		
		/* Add users with Contact */
		User userWithContact1 = new User();
		userWithContact1.setName("U1");
		userWithContact1.setSurname("UU1");
		User userWithContact2 = new User();
		userWithContact2.setName("U2");
		userWithContact2.setSurname("UU2");
		User userWithContact3 = new User();
		userWithContact3.setName("U3");
		userWithContact3.setSurname("UU3");
		User userWithContact4 = new User();
		userWithContact4.setName("U4");
		userWithContact4.setSurname("UU4");
		
		Contact contact1 = new Contact();
		contact1.setNumber(44);
		contact1.setStreet("Malerska");
		contact1.setCity("Warszawa");
		contact1.setEmail("f@gmail.com");
		contact1.setPhone("234324324");
		contact1.setPostCode("34342");
		Contact contact2 = new Contact();	
		Contact contact3 = new Contact();	
		Contact contact4 = new Contact();	
		
		userWithContact1.setContact(contact1);
		contact1.setUser(userWithContact1);
		userWithContact2.setContact(contact2);
		contact2.setUser(userWithContact2);
		userWithContact3.setContact(contact3);
		contact3.setUser(userWithContact3);
		userWithContact4.setContact(contact4);
		contact4.setUser(userWithContact4);
		
		session.save(user1);   			// save user without contact
		session.save(userWithContact1);  // save user with contact
		session.save(user2);             // save user without contact
		session.save(userWithContact2);  // save user with contact
		session.save(userWithContact3);  // save user with contact
		session.save(userWithContact4);  // save user with contact
		
		session.getTransaction().commit();		
	}
	
	@Test
	public void testOneToMany(){
		
		session.beginTransaction();
		
		User user1 = new User();		
		user1.setName("Jan");
		user1.setSurname("Nowak");
		User user2 = new User();		
		user2.setName("Henryk");
		user2.setSurname("Domanski");
		
        session.save(user2);
        session.save(user1);
        
        Income income1 = new Income();
        income1.setName("paliwo");
        income1.setValue(23);
        income1.setDate(new Date());
        Income income2 = new Income();
        income2.setName("spodnie");
        income2.setValue(55);
        income2.setDate(new Date());
        Income income3 = new Income();
        income3.setName("plecak");
        income3.setValue(34);
        income3.setDate(new Date());
        
        income1.setUser(user1);
        income2.setUser(user1);
        income3.setUser(user1);
        //user1.getIncomes().add(income1);
        //user1.getIncomes().add(income2);
        //user1.getIncomes().add(income3);
   
        session.save(income1);
        session.save(income2);
        session.save(income3);
		
		
		session.getTransaction().commit();		
	}
	
	@Test
	public void testManyToMany(){
		
		session.beginTransaction();
		
		User user1 = new User();		
		user1.setName("Joe");
		user1.setSurname("Doe");
		User user2 = new User();		
		user2.setName("Adam");
		user2.setSurname("Kowalski");
		
		Group group1 = new Group();
		group1.setName("group1");
		Group group2 = new Group();
		group2.setName("group2");
		Group group3 = new Group();
		group3.setName("group3");
		
        Set<Group> groups = new HashSet<Group>();
        groups.add(group1);
        groups.add(group2);
        Set<Group> groupsSecond = new HashSet<Group>();
        groupsSecond.add(group1);
        groupsSecond.add(group2);
        groupsSecond.add(group3);
          
        user1.setUserGroups(groups);
        user2.setUserGroups(groupsSecond);
                
        session.save(user1);
        session.save(user2);
        
		session.getTransaction().commit();		
	}
}
