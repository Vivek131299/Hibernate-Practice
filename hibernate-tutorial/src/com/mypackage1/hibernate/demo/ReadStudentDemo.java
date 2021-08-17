package com.mypackage1.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.mypackage1.hibernate.demo.entity.Student;

public class ReadStudentDemo {
	// In this class, we will add a new Student to table and then retrieve/read it.

	public static void main(String[] args) {

		// create SESSION FACORY.
		SessionFactory factory = new Configuration()
								.configure("hibernate.cfg.xml")
								.addAnnotatedClass(Student.class)
								.buildSessionFactory();
		
		// create a SESSION.
		Session session = factory.getCurrentSession();
		
		
		try {
			// create a Student object
			System.out.println("Creating a new Student object...");
			Student tempStudent = new Student("Daffy", "Duck", "daffy@hibernate.com");
			
			// start a transaction
			session.beginTransaction();
			
			// save the Student object
			System.out.println("Saving the student...");
			System.out.println(tempStudent);
			session.save(tempStudent);

			// commit a transaction
			session.getTransaction().commit();
			
			
			// NEW CODE FOR RETRIEVING/READING STUDENT FROM TABLE
			
			// Find out the student's id: primary key
			System.out.println("Saved student. Generated id: " + tempStudent.getId());

			/// Retrieving above student from database. ///
			
			// get a new session and start transaction
			session = factory.getCurrentSession();
			session.beginTransaction();
			
			// retrieve the student based on id: primary key
			System.out.println("\nGetting student with id: " + tempStudent.getId());
			
			Student myStudent = session.get(Student.class, tempStudent.getId()); // tempStudent.getId() is the primary key.
			// Above, if we give wrong primary key, then it will return null.
			
			// Printing out above retrieved student.
			System.out.println("Get complete: " + myStudent);
			
			// commit the transaction
			session.getTransaction().commit();
			
			
			System.out.println("Done!");
			
		} finally {
			// close the factory
			factory.close();
		}
	}

}
