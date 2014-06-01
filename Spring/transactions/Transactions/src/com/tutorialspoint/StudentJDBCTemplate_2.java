package com.tutorialspoint;

import java.util.List;
import javax.sql.DataSource;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

public class StudentJDBCTemplate_2 implements StudentDAO{
		
		private JdbcTemplate jdbcTemplateObject;
		
		public void setDataSource(DataSource dataSource) {
			this.jdbcTemplateObject = new JdbcTemplate(dataSource);
		}
		
		public void create(String name, Integer age, Integer marks, Integer year){
		
			try {
				String SQL1 = "insert into Student (name, age) values (?, ?)";
				jdbcTemplateObject.update( SQL1, name, age);
				// Get the latest student id to be used in Marks table
				String SQL2 = "select max(id) from Student";
				int sid = jdbcTemplateObject.queryForInt( SQL2 );
				String SQL3 = "insert into Marks(sid, marks, year) " + "values (?, ?, ?)";
				jdbcTemplateObject.update( SQL3, sid, marks, year);
				System.out.println("Created Name = " + name + ", Age = " +
						age);
				// to simulate the exception.
				throw new RuntimeException("simulate Error condition") ;
			} catch (DataAccessException e) {
				System.out.println("Error in creating record, rolling back");
				throw e;
			}
		}
		
		public List<StudentMarks> listStudents() {
			String SQL = "select * from Student, Marks where
			Student.id=Marks.sid";
			List <StudentMarks> studentMarks=jdbcTemplateObject.query(SQL,
			new StudentMarksMapper());
			return studentMarks;
		}
}
