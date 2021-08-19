package com.mypackage1.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.mypackage1.hibernate.demo.entity.Instructor;
import com.mypackage1.hibernate.demo.entity.InstructorDetail;
import com.mypackage1.hibernate.demo.entity.Student;

public class GetInstructorDetailDemo {

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
			int theId = 2;
			InstructorDetail tempInstructorDetail = session.get(InstructorDetail.class, theId);
			// This will return null if theId is not valid (if entry is not present in database).
			// Then our tempInstructorDetail will be null.
			
			// print the instructor detail
			System.out.println("tempInstructorDetail: " + tempInstructorDetail);
			
			// print the ASSOCIATED INSTRUCTOR
			System.out.println("the associated instructor: " + tempInstructorDetail.getInstructor());
			// If tempInstructorDetail is null, it will throw NullPointerException.
			// And also it will show that our connection is leak because we don't close the session.
			// So we catch that exception in catch block below.
			// And we will also close the session in finally block to handle connection leak issue.

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
