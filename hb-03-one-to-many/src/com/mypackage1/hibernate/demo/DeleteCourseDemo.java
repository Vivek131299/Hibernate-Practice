package com.mypackage1.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.mypackage1.hibernate.demo.entity.Course;
import com.mypackage1.hibernate.demo.entity.Instructor;
import com.mypackage1.hibernate.demo.entity.InstructorDetail;
import com.mypackage1.hibernate.demo.entity.Student;

public class DeleteCourseDemo {

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
			/// DELETING THE COURSE ///
			
			// start a transaction
			session.beginTransaction();

			
			// get a course
			int theId = 10;
			Course tempCourse = session.get(Course.class, theId);
			
			// delete a course
			System.out.println("Deleting the course: " + tempCourse);
			
			session.delete(tempCourse);
			// NOTE: It will only delete the course and Not the associated Instructor 
			//       because we have NOT given CascadeType as REMOVE.
			

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
