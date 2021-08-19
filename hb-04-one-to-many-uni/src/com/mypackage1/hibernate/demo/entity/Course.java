package com.mypackage1.hibernate.demo.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
	
	/// SETTING UP THE MANY-TO-ONE RELATIONSHIP ///
	// This class Course has ManyToOne Relationship with an Instructor.
	// Because one Instructor can have many courses.
	@ManyToOne(cascade={CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
	// Above, we don't give CascadeType of REMOVE because we don't want to apply Cascading Deletes.
	@JoinColumn(name="instructor_id") // course has the instructor_id column, that has the key
	// that points back to the instructor. So that the course knows how to find its given instructor.
	private Instructor instructor;
	
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

	
	//define toString() method
	@Override
	public String toString() {
		return "Course [id=" + id + ", title=" + title + "]";
	}
			
}
