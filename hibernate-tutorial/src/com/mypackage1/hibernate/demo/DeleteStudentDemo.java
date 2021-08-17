package com.mypackage1.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.mypackage1.hibernate.demo.entity.Student;

public class DeleteStudentDemo {
	// In this class, we will delete the Student object from the database.

	public static void main(String[] args) {

		// create SESSION FACORY.
		SessionFactory factory = new Configuration()
								.configure("hibernate.cfg.xml")
								.addAnnotatedClass(Student.class)
								.buildSessionFactory();
		
		// create a SESSION.
		Session session = factory.getCurrentSession();
		
		
		try {
			int studentId = 1;
			
			/// Retrieving above student from database. ///
			
			// get a new session and start transaction
			session = factory.getCurrentSession();
			session.beginTransaction();
			
			// retrieve the student based on id: primary key
			System.out.println("\nGetting student with id: " + studentId);
			Student myStudent = session.get(Student.class, studentId);
			
			// DELETE the student
			System.out.println("Deleting stdent: " + myStudent); // Printing student data before deleting it.
			session.delete(myStudent); 
			// So above, will delete the student with the id '1' because we have retrieved the
			// student with id 'i' into 'myStudent' variable already.
			
			
			// ALTERNATE DELETE APPROACH
			// Instead of first retrieving the student through id and then deleting it,
			// what we can do is, make use of query (session.createQuery()) and make use of 
			// clauses (WHERE, LIKE, etc.) in it and delete the student on the fly.
			
			// So, for Deleting student with id=2:
			System.out.println("Deleting student id=2");
			
			session.createQuery("delete from Student where id=2").executeUpdate();
			
			// NOTE: .executeUpdate() method is also used for delete query like update query.
			//       It basically means we are updating the database. And that update can be
			//       update statement or delete statement,
			//       So it's used for both update and delete queries.
			
			
			
			// commit the transaction
			session.getTransaction().commit();
			
			
			System.out.println("Done!");
			
		} finally {
			// close the factory
			factory.close();
		}
	}

}
