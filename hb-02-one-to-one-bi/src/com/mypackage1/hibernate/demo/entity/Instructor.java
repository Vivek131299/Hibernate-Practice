package com.mypackage1.hibernate.demo.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

//In this class we will,
//Annotate the class as an entity and map to database table.
@Entity
@Table(name="instructor")
public class Instructor {

	// define the fields
	// and annotate the fields with database column names.
	@Id // for primary key
	@GeneratedValue(strategy=GenerationType.IDENTITY) // for auto increment
	@Column(name="id") // maps to table column 'id'
	private int id;
	
	@Column(name="first_name")
	private String firstName;
	
	@Column(name="last_name")
	private String lastName;
	
	@Column(name="email")
	private String email;
	
	
	/// SETUP MAPPING / RELATIONSHIP TO InstructorDetail entity and also CASCADE ///
	// For this we will use @OneToOne() Annotation,
	// And @JoinColumn() Annotation for joining column of instructor table to instructor_detail table.
	
	@OneToOne(cascade=CascadeType.ALL) // So cascade will apply for any operations for persisting, deleting, etc.
	// So it will also update associated object/Joint_column accordingly.
	@JoinColumn(name="instructor_detail_id") // We are basically mapping our column(instructor_detail_id) in the instructor class to
	// the primary key in the instructor_detail.
	// In MySQL database, we have already configured foreign key to reference id field in instructor_detail table through MySQL Script.
	// So for e.g.- if we delete any entry from the instructor table then the entry corresponding to
	// 'instructor_detail_id'(foreign key in instructor table) as 'id'(primary key in instructor_detail table) 
	// will be also deleted from the instructor_detail table.
	private InstructorDetail instructorDetail;
	
	
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

	
	// generate toString() method
	@Override
	public String toString() {
		return "Instructor [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email
				+ ", instructorDetail=" + instructorDetail + "]";
	}
		
	
	
}
