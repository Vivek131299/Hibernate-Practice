package com.mypackage1.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.mypackage1.hibernate.demo.entity.Student;

public class PrimaryKeyDemo {
	// In this class, we will add multiple students to table.

	public static void main(String[] args) {

		// create SESSION FACORY.
		SessionFactory factory = new Configuration()
								.configure("hibernate.cfg.xml")
								.addAnnotatedClass(Student.class)
								.buildSessionFactory();
		
		// create a SESSION.
		Session session = factory.getCurrentSession();
		
		
		try {
			// create 3 student objects
			System.out.println("Creating 3 Student object...");
			Student tempStudent1 = new Student("John", "Doe", "john@hibernate.com");
			Student tempStudent2 = new Student("Mary", "Public", "mary@hibernate.com");
			Student tempStudent3 = new Student("Bonita", "Applebum", "bonita@hibernate.com");

			// start a transaction
			session.beginTransaction();
			
			// save the Student objects
			System.out.println("Saving the students...");
			session.save(tempStudent1);
			session.save(tempStudent2);
			session.save(tempStudent3);

			// commit a transaction
			session.getTransaction().commit();
			
			System.out.println("Done!");
			
		} finally {
			// close the factory
			factory.close();
		}
	}

}
