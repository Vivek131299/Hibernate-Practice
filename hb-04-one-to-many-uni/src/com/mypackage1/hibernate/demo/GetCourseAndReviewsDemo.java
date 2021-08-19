package com.mypackage1.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.mypackage1.hibernate.demo.entity.Course;
import com.mypackage1.hibernate.demo.entity.Instructor;
import com.mypackage1.hibernate.demo.entity.InstructorDetail;
import com.mypackage1.hibernate.demo.entity.Review;

public class GetCourseAndReviewsDemo {

	public static void main(String[] args) {

		SessionFactory factory = new Configuration()
								.configure("hibernate.cfg.xml")
								.addAnnotatedClass(Instructor.class)
								.addAnnotatedClass(InstructorDetail.class)
								.addAnnotatedClass(Course.class)
								.addAnnotatedClass(Review.class)
								.buildSessionFactory();
	
		// .addAnnotatedClass() takes an argument of class in which we have our
		// Entity and Table Annotations.(i.e. Instructor class, InstructorDetail 
		// class, Course class and also Review class).
		
		
		Session session = factory.getCurrentSession();
		
		
		try {
			/// CREATING NEW COURSES AND ADDING THEM.
			
			// start a transaction
			session.beginTransaction();

			
			// get the course
			int theId = 10;
			Course tempCourse = session.get(Course.class, theId);
			
			// print the course
			System.out.println(tempCourse);
			
			// print the course reviews
			System.out.println(tempCourse.getReviews());
			
			// NOTE that the reviews are Configured as LAZY FETCH.
			// So when we load the course we only get the course.
			// Then later on we get course reviews separately by .getReviews() getter method 
			// that we used here.
			

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
