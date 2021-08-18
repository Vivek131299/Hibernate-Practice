package com.mypackage1.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.mypackage1.hibernate.demo.entity.Course;
import com.mypackage1.hibernate.demo.entity.Instructor;
import com.mypackage1.hibernate.demo.entity.InstructorDetail;
import com.mypackage1.hibernate.demo.entity.Student;

public class GetInstructorCoursesDemo {

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
			/// CREATING NEW COURSES AND ADDING THEM.
			
			// start a transaction
			session.beginTransaction();

			
			// get the instructor from database
			int theId = 1;
			Instructor tempInstructor = session.get(Instructor.class, theId);
			
			
			/// RETRIEVE THE COURSES FOR THIS(tempInstructor/id=1) INSTRUCTOR ///
			
			System.out.println("Instructor: " + tempInstructor);
			
			// getting the courses for the instructor
			System.out.println("Courses: " + tempInstructor.getCourses());
			

			// commit a transaction
			session.getTransaction().commit();
			
			System.out.println("Done!");
			
		} finally {
			// add clean up code
			session.close();
			
			// close the factory
			factory.close();
		}
	}

}
