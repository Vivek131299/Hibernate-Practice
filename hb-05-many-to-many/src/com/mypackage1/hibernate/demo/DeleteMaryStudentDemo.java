package com.mypackage1.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.mypackage1.hibernate.demo.entity.Course;
import com.mypackage1.hibernate.demo.entity.Instructor;
import com.mypackage1.hibernate.demo.entity.InstructorDetail;
import com.mypackage1.hibernate.demo.entity.Review;
import com.mypackage1.hibernate.demo.entity.Student;

public class DeleteMaryStudentDemo {

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
			/// DELETING MARY STUDENT.
			// We will delete the Mary student which we have created previously,
			// and we will make sure that courses associated with it SHOULD NOT be deleted,
			// as we have NOT given CascadeType as REMOVE while creating the relationship 
			// between Course and Student.
			// So, We just want to delete Student and its relationship but not courses associated with it.
			
			
			// start a transaction
			session.beginTransaction();

			
			// get the student mary from database
			int studentId = 2;
			Student tempStudent = session.get(Student.class, studentId);
			
			System.out.println("\nLoaded student: " + tempStudent);
			System.out.println("Courses: " + tempStudent.getCourses());
			
			// delete student
			System.out.println("\nDeleting student: " + tempStudent);
			session.delete(tempStudent);
			
			// So this will delete the entry for mary student from 'student' table,
			// AND ALSO delete the mapping/relationship between that student and courses
			// associated with it from the 'course_student' table.
			// BUT it DOES NOT delete courses from 'course' table.
			// So still after this, we have courses in our 'course' table.

			
			
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
