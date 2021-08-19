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
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

// In this class we will,
// define FetchType for Courses as Eager or Lazy.
@Entity
@Table(name="instructor")
public class Instructor {

	// define the fields
	// and annotate the fields with database column names.
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="first_name")
	private String firstName;
	
	@Column(name="last_name")
	private String lastName;
	
	@Column(name="email")
	private String email;
	
	
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="instructor_detail_id")
	private InstructorDetail instructorDetail;
	

	/// DEFINING FETCH TYPE AS EAGER ///
	// As we know, the instructor has a list of courses.
	// So if we set the FetchType for courses as Eager, then this will basically load the 
	// instructor and also load the courses AT THE SAME TIME.
	
	/// DEFINING FETCH TYPE AS LAZY ///
	// As we know, the instructor has a list of courses.
	// So if we set the FetchType for courses as Eager, then this will load the 
	// courses ONLY ON DEMAND.
	
	@OneToMany(fetch=FetchType.LAZY, 
			   mappedBy="instructor", 
			   cascade={CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
	private List<Course> courses;
	
	// Above later on, we changed FetchType to LAZY.
	
	
	// create constructors
	public Instructor() {
		
	}

	public Instructor(String firstName, String lastName, String email) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
	}

	// generate getter/setter methods
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public InstructorDetail getInstructorDetail() {
		return instructorDetail;
	}

	public void setInstructorDetail(InstructorDetail instructorDetail) {
		this.instructorDetail = instructorDetail;
	}

	
	public List<Course> getCourses() {
		return courses;
	}

	public void setCourses(List<Course> courses) {
		this.courses = courses;
	}
	

	// generate toString() method
	@Override
	public String toString() {
		return "Instructor [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email
				+ ", instructorDetail=" + instructorDetail + "]";
	}
		
	
	/// ADD CONVENIENCE METHODS for Bi-Directional Relationship ///
	
	public void add(Course tempCourse) {
		if (courses == null) {
			courses = new ArrayList<>();
		}
		
		courses.add(tempCourse);
		
		// Bi-directional relationship
		tempCourse.setInstructor(this);
		// So basically we are telling to course that this is your new instructor.
	}
	
}
