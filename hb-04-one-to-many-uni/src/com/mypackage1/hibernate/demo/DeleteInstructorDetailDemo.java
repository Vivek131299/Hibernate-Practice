package com.mypackage1.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.mypackage1.hibernate.demo.entity.Instructor;
import com.mypackage1.hibernate.demo.entity.InstructorDetail;
import com.mypackage1.hibernate.demo.entity.Student;

public class DeleteInstructorDetailDemo {

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

			// get the instructor detail object
			int theId = 3;
			InstructorDetail tempInstructorDetail = session.get(InstructorDetail.class, theId);
			// This will return null if theId is not valid (if entry is not present in database).
			// Then our tempInstructorDetail will be null.
			
			// print the instructor detail
			System.out.println("tempInstructorDetail: " + tempInstructorDetail);
			
			// print the associated instructor
			System.out.println("the associated instructor: " + tempInstructorDetail.getInstructor());

			
			/// DELETE THE INSTRUCTOR DETAIL ///
			
			System.out.println("Deleting tempInstructorDetail: " + tempInstructorDetail);
			
			// If we try to delete only delete only Instructor Detail and want to keep Instructor as it is,
			// as we have already modified CascadeType by removing REMOVE type in InstructorDetail class,
			// we will still get error.
			// To solve this, we have to REMOVE THE ASSOCIATED OBJECT REFERENCE.
			// And BREAK BI-DIRECTIONAL LINK.
			
			tempInstructorDetail.getInstructor().setInstructorDetail(null);
			// So we are getting the Instructor and setting that person's InstructorDetail to null.
			// So, this will change the associated Instructor's 'instructor_detail_id' column to null.
			// So we are breaking the bi-directional link/chain for this relationship.
			// And then we perform delete operation.
			
			// And now it will delete the InstructorDetail but actually keep the Instructor.
			
			session.delete(tempInstructorDetail);
			
			// This will also do a Cascade Delete on the associated instructor because we 
			// have CascadeType set to ALL.
			
			
			// commit a transaction
			session.getTransaction().commit();
			
			System.out.println("Done!");
			
		} catch (Exception exc) {
			exc.printStackTrace();
		} 
		finally {
			// handle connection leak issue
			session.close();
			
			// close the factory
			factory.close();
		}
	}

}
