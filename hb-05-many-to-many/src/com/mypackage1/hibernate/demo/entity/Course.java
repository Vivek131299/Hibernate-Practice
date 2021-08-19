package com.mypackage1.hibernate.demo.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="course")
public class Course {

	// define our fields and annotate fields
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="title")
	private String title;
	
	
	@ManyToOne(cascade={CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
	@JoinColumn(name="instructor_id")
	private Instructor instructor;
	
	
	/// CONFIGURING ONE-TO-MANY RELATIONSHIP WITH REVIEWS///
	// Because a course can have many reviews.
	// Also define getters/setters and a convenience method (for adding a review to course) for this field.
	
	@OneToMany(fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	// We are defining CascadeType as ALL because we also want to apply Cascade Date operation,
	// because if we delete a course, then we also want to delete the associated reviews.
	@JoinColumn(name="course_id")
	// 'course_id' is the actual column in the 'review' table that will point back to the actual course.
	private List<Review> reviews;
	
	
	
	/// CONFIGURING MANY-TO-MANY RELATIONSHIP WITH STUDENTS ///
	// Because a course can have many students and also a student can have many courses.
	// So we have to ALSO Configure Student class for this. (See Student class)
	// Also define getters/setters and a convenience method (for adding a student to course) for this field.
	@ManyToMany(fetch=FetchType.LAZY, 
			cascade={CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
	@JoinTable(
			   name="course_student", 
			   joinColumns=@JoinColumn(name="course_id"), 
			   inverseJoinColumns=@JoinColumn(name="student_id")
			  )
	private List<Student> students;
	
	///// @JOINTABLE ANNOTATION /////
	// Above, for ManyToMany, we make use of @JoinTable ANNOTATION.
	// In this annotation, we tell hibernate that which columns to use for actually looking up 
	// the appropriate student for our course.
	//
	// // Joint Table // So, 'course_student' is name of our JoinTable. This 'course_student' table we have already created
	// in our MySQL database. This table consists of 2 columns (course_id and student_id). Those both
	// columns are primary key AND ALSO foreign key. So, course_id refers to the 'id' column of 
	// 'course' table and student_id refers to the 'id' column of 'student' table.
	//
	// After that, in 'joinColumns', we need to give actual reference for the regular column(@JoinColumn) 
	// 'course_id' which refers to THIS SIDE (Course class) or 'course' table and points to 'id' column in it.
	// AND in 'inverseJoinColumns', we need to give reference to the column(@JoinColumn) 
	// 'student_id' which refers to the OTHER SIDE/INVERSE (Student class) or 'student' table and points to 'id' column in it.
	//
	// Because we have 2 columns in our course_student table (JoinTable) and we are currently in Course
	// class, So THIS side becomes Course class and the INVERSE/OTHER SIDE becomes Student class.
	//
	// So, in Student class, we will do OPPOSITE of this. (See Student class).
	
	
	
	// define constructors
	public Course() {
		
	}

	public Course(String title) {
		this.title = title;
	}

	
	// define getters and setters
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Instructor getInstructor() {
		return instructor;
	}

	public void setInstructor(Instructor instructor) {
		this.instructor = instructor;
	}

	
	public List<Review> getReviews() {
		return reviews;
	}

	public void setReviews(List<Review> reviews) {
		this.reviews = reviews;
	}

	
	public List<Student> getStudents() {
		return students;
	}

	public void setStudents(List<Student> students) {
		this.students = students;
	}
	

	// add a convenience method for adding review
	public void addReview(Review theReview) {
		
		if (reviews == null) {
			reviews = new ArrayList<>();
		}
		
		reviews.add(theReview);
	}
	
	
	// add a convenience method for adding student
	public void addStudent(Student theStudent) {
		
		if  (students == null) {
			students = new ArrayList<>();
		}
		
		students.add(theStudent);
	}
	
	
	//define toString() method
	@Override
	public String toString() {
		return "Course [id=" + id + ", title=" + title + "]";
	}
			
}
