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
	// Also define getters/setters and a convenience method (for adding a student to course) for this field.
	private List<Student> students;
	
	
	
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
