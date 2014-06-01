package com.tutorialspoint;

import java.util.List;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/* Projekt wymaga uruchomienia bazy MySQL:
 * user=root, passwd=password,port:3306
 * utworzenia bazy danych TEST oraz tabeli Student i Marks z odpowiednimi polami
 */

public class MainApp_2 {

	public static void main(String[] args) {
		ApplicationContext context =
		new ClassPathXmlApplicationContext("Beans.xml");
		
		StudentDAO studentJDBCTemplate =
		(StudentDAO)context.getBean("studentJDBCTemplate_2");
		System.out.println("------Records creation--------" );
		studentJDBCTemplate.create("Zara", 11, 99, 2010);
		studentJDBCTemplate.create("Nuha", 20, 97, 2010);
		studentJDBCTemplate.create("Ayan", 25, 100, 2011);
		System.out.println("------Listing all the records--------" );
		List<StudentMarks> studentMarks =
		studentJDBCTemplate.listStudents();
		for (StudentMarks record : studentMarks) {
		System.out.print("ID : " + record.getId() );
		System.out.print(", Name : " + record.getName() );
		System.out.print(", Marks : " + record.getMarks());
		System.out.print(", Year : " + record.getYear());
		System.out.println(", Age : " + record.getAge());
		}
		}
	
}
