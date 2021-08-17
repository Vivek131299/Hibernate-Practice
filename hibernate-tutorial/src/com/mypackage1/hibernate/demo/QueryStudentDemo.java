package com.mypackage1.hibernate.demo;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.mypackage1.hibernate.demo.entity.Student;


public class QueryStudentDemo {
	// In this class, we will query/read the students from database by using Hibernate Query Language (HQL).
	// It is similar to SQL.
	// We can use also SQL things like WHERE, LIKE, ORDER BY, JOIN, IN, etc. in this.

	public static void main(String[] args) {

		// create SESSION FACORY.
		SessionFactory factory = new Configuration()
								.configure("hibernate.cfg.xml")
								.addAnnotatedClass(Student.class)
								.buildSessionFactory();
		
		
		// create a SESSION.
		Session session = factory.getCurrentSession();
		
		
		try {
			
			// start a transaction
			session.beginTransaction();
			
			// Query the students
			List<Student> theStudents = session.createQuery("from Student").getResultList();
			// Above, Student is actual our class name.
			// And, "from Student" will get all the objects/students from student table from database. 
			
			// display the students
			displayStudents(theStudents);
			
			// query students: lastName='Doe'
			theStudents = session.createQuery("from Student s where s.lastName='Doe'").getResultList();
			// Note: Above, 's' is alias/temporary_name for Student.
			// So, s.lastName means lastName field/property of Student class.
			// And 'lastName' is the property/field name from our Java Student class and NOT the 
			// column name (which is 'last_name') in SQL table.
			// We have to use Java property and not column name because they are already mapped.
			
			// display the students
			System.out.println("\n\nStudents who have last name of 'Doe'");
			displayStudents(theStudents);
			
			
			// query students: lastName='Doe' OR firstName='Daffy'
			theStudents = session.createQuery("from Student s where "
								+ "s.lastName='Doe' OR firstName='Daffy'").getResultList();
			// NOTE: If we are splitting query into 2 lines, then make sure that there is still white space after 'where'.

			// display the students
			System.out.println("\n\nStudents who have last name of 'Doe' OR first name 'Daffy'");
			displayStudents(theStudents);
			
			
			// query students: where email LIKE '%hibernate.com'
			theStudents = session.createQuery("from Student s where "
								+ "s.email LIKE '%hibernate.com'").getResultList();
			
			// display the students
			System.out.println("\n\nStudents whose email ends with 'hibernate.com'");
			displayStudents(theStudents);
			
			
			// commit a transaction
			session.getTransaction().commit();
			
			System.out.println("Done!");
			
		} finally {
			// close the factory
			factory.close();
		}
	}

	private static void displayStudents(List<Student> theStudents) {
		for (Student tempStudent : theStudents) { // iterating over above returned list of students.
			System.out.println(tempStudent);
		}
	}

}
