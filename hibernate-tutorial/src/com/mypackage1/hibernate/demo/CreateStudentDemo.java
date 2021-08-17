package com.mypackage1.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.mypackage1.hibernate.demo.entity.Student;

public class CreateStudentDemo {

	public static void main(String[] args) {

		// create SESSION FACORY.
		// Session Factory reads the hibernate config file (hibernate.cfg.xml) and
		// creates Session objects.
		// This is heavy-weight object.
		// This only gets created Once in our app.
		SessionFactory factory = new Configuration()
								.configure("hibernate.cfg.xml")
								.addAnnotatedClass(Student.class)
								.buildSessionFactory();
		// Above, if we do not give config file name in .config() then it searches for file with 
		// default name 'hibernate.cfg.xml'. So we can leave it empty if our file is saved by name 'hibernate.cfg.xml'.
		// .addAnnotatedClass() takes an argument of class in which we have our Entity and Table Annotations.(i.e. Student class).
		
		
		// create a SESSION.
		// Session wraps a JDBC connection.
		// This is main object used to save and retrieve objects to database.
		// This is Short lived object. So for a given method, we will get a session, we will 
		// use it, and we will throw it. And for another method we will get another, use it, and throw away.
		// Session is retrieved from Session Factory.
		Session session = factory.getCurrentSession();
		
		
		try {
			// In try block, we will use the Session object created above 
			// to save/retrieve the Java object to database.
			
			// create a Student object
			System.out.println("Creating a new Student object...");
			Student tempStudent = new Student("Paul", "Wall", "paul@hibernate.com");
			
			// start a transaction
			session.beginTransaction();
			
			// save the Student object
			System.out.println("Saving the student...");
			session.save(tempStudent);

			// commit a transaction
			session.getTransaction().commit();
			
			System.out.println("Done!");
			
		} finally {
			// close the factory
			factory.close();
		}
	}

}
