package com.mypackage1.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.mypackage1.hibernate.demo.entity.Course;
import com.mypackage1.hibernate.demo.entity.Instructor;
import com.mypackage1.hibernate.demo.entity.InstructorDetail;
import com.mypackage1.hibernate.demo.entity.Student;

public class EagerLazyDemo {

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
			
			/// For EAGER ///
			// At this point EVERYTHING ID LOADED/FETCHED, the instructor, instructor details and
			// the courses because of the EAGER LOADING. 
			// We have all this data in the Memory.
			// So below when we print this, there is no need to hit the database anymore.
			
			
			/// For LAZY ///
			// At this point ONLY THE INSTRUCTOR OBJECT IS LOADED.
			// So at this point we only have instructor and instructor detail in our Memory.
			// And below, when we get and print the courses by .getCourses() (Getter method), 
			// then only COURSES WILL BE LOADED ON DEMAND/REQUEST.
			
			
			
			/// RETRIEVE THE COURSES FOR THIS(tempInstructor/id=1) INSTRUCTOR ///
			
			System.out.println("luv2code: Instructor: " + tempInstructor);
						
			
			// getting the courses for the instructor
			System.out.println("luv2code: Courses: " + tempInstructor.getCourses());
			
			/// For LAZY ///
			// At this point, it will load the courses from the database and print
			// it because we have set FetchType as LAZY. 
			// So when we demand/request for courses (like by using getter method in this case),
			// then only it fetches from database.
			

			// commit a transaction
			session.getTransaction().commit();
			
			System.out.println("luv2code: Done!");
			
		} finally {
			// add clean up code
			session.close();
			
			// close the factory
			factory.close();
		}
	}

}
