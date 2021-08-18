package com.mypackage1.hibernate.demo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

//In this class we will,
// Annotate the class as an entity and map to database table.
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
