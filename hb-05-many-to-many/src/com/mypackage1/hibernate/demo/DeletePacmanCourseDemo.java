package com.mypackage1.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.mypackage1.hibernate.demo.entity.Course;
import com.mypackage1.hibernate.demo.entity.Instructor;
import com.mypackage1.hibernate.demo.entity.InstructorDetail;
import com.mypackage1.hibernate.demo.entity.Review;
import com.mypackage1.hibernate.demo.entity.Student;

public class DeletePacmanCourseDemo {

	public static void main(String[] args) {

		SessionFactory factory = new Configuration()
								.configure("hibernate.cfg.xml")
								.addAnnotatedClass(Instructor.class)
								.addAnnotatedClass(InstructorDetail.class)
								.addAnnotatedClass(Course.class)
								.addAnnotatedClass(Review.class)
								.addAnnotatedClass(Student.class)
								.buildSessionFactory();
	
		// .addAnnotatedClass() takes an argument of class in which we have our
		// Entity and Table Annotations.(i.e. Instructor class, InstructorDetail 
		// class, Course class, Review class and also Student class).
		
		
		Session session = factory.getCurrentSession();
		
		
		try {
			/// DELETING PACMAN COURSE.
			// We will delete the Pacman course which we have created previously,
			// and we will make sure that students associated with it SHOULD NOT be deleted,
			// as we have NOT given CascadeType as REMOVE while creating the relationship 
			// between Course and STudent.
			// So, We just want to delete Course and its relationship but not students associated with it.
			
			// start a transaction
			session.beginTransaction();

			
			// get the pacman course from database
			int courseId = 10;
			Course tempCourse = session.get(Course.class, courseId);
			
			// delete the course
			System.out.println("Deleting course: " + tempCourse);
			session.delete(tempCourse);
			
			// So this will delete the entry for pacman course from 'course' table,
			// AND ALSO delete the mapping/relationship between that course and students
			// associated with it from the 'course_student' table.
			// BUT it DOES NOT delete students from 'student' table.
			// So still after this, we have students in our 'student' table.
			
			

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
