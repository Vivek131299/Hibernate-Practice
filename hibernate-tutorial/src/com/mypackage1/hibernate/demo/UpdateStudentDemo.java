package com.mypackage1.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.mypackage1.hibernate.demo.entity.Student;

public class UpdateStudentDemo {
	// In this class, we will update the Student object into the database.

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
			
			/// UPDATING STUDENT ///
			System.out.println("Updating student...");
			// Updating the student's firstName to 'Scooby'
			myStudent.setFirstName("Scooby");
			
			// Here, after this updating the student's firstName, this is stored only in memory.
			// And once we commit the transaction, then the change will apply/update in the database. 		
			
			// NOTE: There is no hard requirement for us to call session.save or session.update (like we do while creating object)
			//       because this Student object is a persistent object that we retrieve from the database. 
			//		 We can simply call the appropriate setters and then finally do a commit and that will actually
			//       update the database.

			
			// commit the transaction
			session.getTransaction().commit();
			
			
			
			////////// BULK UPDATE //////////
			// NEW CODE for bulk update where we are going to set all the student id's to given value.
			
			// get a new session and start transaction
			session = factory.getCurrentSession();
			session.beginTransaction();
			
			// Update email for ALL students
			System.out.println("Updating email for all students...");
			
			session.createQuery("update Student set email='foo@gmail.com'").executeUpdate();
			// So, above UPDATE query will SET email of ALL students to 'foo@gmail.com'.
			// And .executeUpdate() method will actually perform the Update.
			
			// We can also use clauses like WHERE, LIKE, OR, etc. for update.
			
			
			// commit the transaction
			session.getTransaction().commit();
			
			
			
			System.out.println("Done!");
			
			// So for updating the object,
			// We simply first retrieve the object,
			// then we set a value,
			// and then we commit the transaction.
			
		} finally {
			// close the factory
			factory.close();
		}
	}

}
