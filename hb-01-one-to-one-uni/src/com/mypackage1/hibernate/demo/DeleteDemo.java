package com.mypackage1.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.mypackage1.hibernate.demo.entity.Instructor;
import com.mypackage1.hibernate.demo.entity.InstructorDetail;
import com.mypackage1.hibernate.demo.entity.Student;

public class DeleteDemo {

	public static void main(String[] args) {

		SessionFactory factory = new Configuration()
								.configure("hibernate.cfg.xml")
								.addAnnotatedClass(Instructor.class)
								.addAnnotatedClass(InstructorDetail.class)
								.buildSessionFactory();
	
		// .addAnnotatedClass() takes an argument of class in which we have our
		// Entity and Table Annotations.(i.e. Instructor class and InstructorDetail class).
		
		
		Session session = factory.getCurrentSession();
		
		
		try {
			// start a transaction
			session.beginTransaction();

			/// DELETE AN ENTRY (Deleting an instructor) ///
			
			// get instructor by primary key/id
			int theId = 1;
			Instructor tempInstructor = session.get(Instructor.class, theId); // will return null if not found.
			
			System.out.println("Found instructor: " + tempInstructor);
			
			// DELETE the instructor
			// check if found instructor is not null
			if (tempInstructor != null) {
				System.out.println("Deleting: " + tempInstructor);
				
				session.delete(tempInstructor);
				// So, this will delete entry for instructor id=1 from instructor table,
				// BUT it will ALSO DELETE corresponding/associated entry from instructor_detail table 
				// because we have CasCadeType set as ALL.
			}
			

			// commit a transaction
			session.getTransaction().commit();
			
			System.out.println("Done!");
			
		} finally {
			// close the factory
			factory.close();
		}
	}

}
