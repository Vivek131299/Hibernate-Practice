package com.mypackage1.hibernate.demo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity // @Entity Annotation Maps the class to database.(In this case, it will map this class 'Student' to database 'Student').
@Table(name="student") // @Table Annotation maps this class/Entity to a given table (i.e. "student" in this case). 
public class Student {

	// MAPPING FIELDS TO DATABASE COLUMNS (@ID and @COLUMN ANNOTATION)
	
	@Id // @Id Annotation states that this field is the PRIMARY KEY.(So, this field int 'id' is primary key).
	// Above, hibernate will generate primary key with the appropriate way BUT we can also 
	// EXPLICITLY GENERATE PRIMARY KEY by providing strategy that we want to generate primary key.
	// So we do this by @GeneratedValue() Annotation
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id") // @Column Annotation specifies the column name in the table. (So int 'id' field will map to 'id' column in table).
	private int id;
	
	@Column(name="first_name") // This field String 'firstName' will map to 'first_name' column in table.
	private String firstName;
	
	@Column(name="last_name")
	private String lastName;
	
	@Column(name="email")
	private String email;
	
	// defining no-argument constructor
	public Student() {
		
	}

	// Constructor
	public Student(String firstName, String lastName, String email) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
	}

	// Getters and Setters for all fields.
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

	// toString() method.
	@Override
	public String toString() {
		return "Student [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email + "]";
	}
	
	
}
