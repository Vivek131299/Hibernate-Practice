package com.mypackage1.hibernate.demo.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

// In this class we will,
// Annotate the class as an entity and we will do bi-directional mapping.
@Entity
@Table(name="instructor_detail") // this will map to 'instructor_detail' table in our database.
public class InstructorDetail {
	
	// define the fields
	// and annotate the fields with database column names.
	@Id // for defining this field(int 'id') as a primary key for table.
	@GeneratedValue(strategy=GenerationType.IDENTITY) // this will help for auto increment for this field.
	@Column(name="id") // maps this field(int 'id') to actual column 'id'
	private int id;
	
	@Column(name="youtube_channel") // maps this field(String 'youtubeChannel') to actual column 'youtube_channel'
	private String youtubeChannel;
	
	@Column(name="hobby")
	private String hobby;
	
	
	/// BI-DIRECTIONAL MAPPING (RELATIONSHIP TO Instructor Entity) ///
	// We already have Relationship to this (InstructorDetail) class in Instructor class and 
	// now we are doing relationship to Instructor class in this class.
	
	// add new field for instructor (also getter/setter methods).
	// add @OneToOne Annotation so that it will give bi-directional relationship.
	
	@OneToOne(mappedBy="instructorDetail", cascade=CascadeType.ALL)
	// Above, 'mappedBy' refers to the 'instructorDetail' property/field of 'Instructor' class.
	// So, hibernate will use information from the Instructor class's @JoinColumn to figure out
	// how to find the associated instructor.
	// And CascadeType=ALL will cascade all operations to associated instructor.
	private Instructor instructor;
	
	
	
	
	
	public Instructor getInstructor() {
		return instructor;
	}

	public void setInstructor(Instructor instructor) {
		this.instructor = instructor;
	}
	

	// create constructors
	public InstructorDetail() {
		
	}

	public InstructorDetail(String youtubeChannel, String hobby) {
		this.youtubeChannel = youtubeChannel;
		this.hobby = hobby;
	}

	
	// generate getter/setter methods
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getYoutubeChannel() {
		return youtubeChannel;
	}

	public void setYoutubeChannel(String youtubeChannel) {
		this.youtubeChannel = youtubeChannel;
	}

	public String getHobby() {
		return hobby;
	}

	public void setHobby(String hobby) {
		this.hobby = hobby;
	}

	
	// generate toString() method
	@Override
	public String toString() {
		return "InstructorDetail [id=" + id + ", youtubeChannel=" + youtubeChannel + ", hobby=" + hobby + "]";
	}
	

}
