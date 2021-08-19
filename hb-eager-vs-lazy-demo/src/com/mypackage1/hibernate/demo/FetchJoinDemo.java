package com.mypackage1.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import com.mypackage1.hibernate.demo.entity.Course;
import com.mypackage1.hibernate.demo.entity.Instructor;
import com.mypackage1.hibernate.demo.entity.InstructorDetail;

public class FetchJoinDemo {

	public static void main(String[] args) {

		SessionFactory factory = new Configuration()
								.configure("hibernate.cfg.xml")
								.addAnnotatedClass(Instructor.class)
								.addAnnotatedClass(InstructorDetail.class)
								.addAnnotatedClass(Course.class)
								.buildSessionFactory();
	
		// .addAnnotatedClass() takes an argument of class in which we have our
		// Entity and Table Annotations.(i.e. Instructor class, InstructorDetail 
		// class and also Course class).
		
		
		Session session = factory.getCurrentSession();
		
		
		try {
			
			// start a transaction
			session.beginTransaction();
			
			
			/// Resolving Closing the Session in Lazy ///
			// Option 2: Hibernate Query with HQL

			
			// get the instructor from database
			int theId = 1;
			
			// Adding Query with HQL
			Query<Instructor> query = 
					session.createQuery("select i from Instructor i JOIN FETCH i.courses where "
							             + "i.id=:theInstructorId", 
							            Instructor.class);
			
			// So when above query is executed later on, it will load instructor and courses
			// all at once.
			
			// set parameter on query before executing it
			query.setParameter("theInstructorId", theId);
			// Above, 'theInstructorId' is the parameter name used in query and 
			// 'theId' is the actual parameter value.
			
			// execute query and get instructor
			Instructor tempInstructor = query.getSingleResult();
			// So this will will load the instructor and courses all at once.
						
			System.out.println("luv2code: Instructor: " + tempInstructor);
						
			
			// commit a transaction
			session.getTransaction().commit();
			
			// close the session
			session.close();
			System.out.println("\nluv2code: The session is now closed!\n");
			
			
			// getting the courses for the instructor
			System.out.println("luv2code: Courses: " + tempInstructor.getCourses());
			// So this will get the courses without any error/ exception even if we have 
			// closed the session before it because have already loaded the courses by
			// using HQL query before closing the session.
			
			
			System.out.println("luv2code: Done!");
			
		} finally {
			// add clean up code
			session.close();
			
			// close the factory
			factory.close();
		}
	}

}
