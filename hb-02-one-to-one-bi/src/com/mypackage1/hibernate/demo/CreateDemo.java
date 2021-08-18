package com.mypackage1.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.mypackage1.hibernate.demo.entity.Instructor;
import com.mypackage1.hibernate.demo.entity.InstructorDetail;
import com.mypackage1.hibernate.demo.entity.Student;

public class CreateDemo {

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
			// create the objects
//			Instructor tempInstructor = new Instructor("Chad", "Darby", "darby@luv2code.com");
//			
//			InstructorDetail tempInstructorDetail = 
//						new InstructorDetail("http://www.luv2code.com/youtube", "Luv 2 code!!!");
//			
			// another entry
			Instructor tempInstructor = new Instructor("Madhu", "Patel", "madhu@luv2code.com");
		
			InstructorDetail tempInstructorDetail = 
						new InstructorDetail("http://www.youtube.com", "Guitar");
		
			
			// associate the objects
			tempInstructor.setInstructorDetail(tempInstructorDetail); // At this point, these
			// objects are associated in only memory and not in database. We still need to save it 
			// which we do below (session.save()).
			
			// start a transaction
			session.beginTransaction();

			// save the instructor
			System.out.println("Saving instructor: " + tempInstructor);
			session.save(tempInstructor);
			// NOTE: This will also save the tempInstructorDetail object because
			//       of CascadeType.ALL that we defined while Mapping(See Instructor class).
			//       So, CascadeType.ALL means it will save the actual object and also any 
			//       associated objects. This apply for all operations like saving(like in this case),
			//       deleting, etc..
			//       So basically by saving data in tempInstructor table, it will also save data in 
			//       tempInstructorDetail table.

			// commit a transaction
			session.getTransaction().commit();
			
			System.out.println("Done!");
			
		} finally {
			// close the factory
			factory.close();
		}
	}

}
