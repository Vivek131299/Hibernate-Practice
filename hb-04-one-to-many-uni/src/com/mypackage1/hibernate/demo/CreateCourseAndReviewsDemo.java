package com.mypackage1.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.mypackage1.hibernate.demo.entity.Course;
import com.mypackage1.hibernate.demo.entity.Instructor;
import com.mypackage1.hibernate.demo.entity.InstructorDetail;
import com.mypackage1.hibernate.demo.entity.Review;

public class CreateCourseAndReviewsDemo {

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
		// class and also Course class).
		
		
		Session session = factory.getCurrentSession();
		
		
		try {
			/// CREATING NEW COURSES AND ADDING THEM.
			
			// start a transaction
			session.beginTransaction();

			
			// create a course
			Course tempCourse = new Course("Pacman - How To Score One Million Points");
			
			// add some reviews to that course
			tempCourse.addReview(new Review("Great course ... loved it!"));
			tempCourse.addReview(new Review("Cool course ... job well done"));
			tempCourse.addReview(new Review("What a dumb course, you are an idiot!"));

			// save the course and leverage the cascade all.
			// So, when we save the course the it will ALSO SAVE all the associated reviews due to CASCADE ALL.
			System.out.println("Saving the course");
			System.out.println(tempCourse);
			System.out.println(tempCourse.getReviews());
			session.save(tempCourse);
			

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
