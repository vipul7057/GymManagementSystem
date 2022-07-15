package com.gym.S3Fitness.dto;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "GymMailMsgLogs")
public class GymMailMsgLogs {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int gmml_id;
	private String gmml_sentTo;
	@Column(length = 500)
	private String gmml_body;
	private Timestamp gmml_time;
	public int getGmml_id() {
		return gmml_id;
	}
	public void setGmml_id(int gmml_id) {
		this.gmml_id = gmml_id;
	}
	public String getGmml_sentTo() {
		return gmml_sentTo;
	}
	public void setGmml_sentTo(String gmml_sentTo) {
		this.gmml_sentTo = gmml_sentTo;
	}
	public String getGmml_body() {
		return gmml_body;
	}
	public void setGmml_body(String gmml_body) {
		this.gmml_body = gmml_body;
	}
	public Timestamp getGmml_time() {
		return gmml_time;
	}
	public void setGmml_time(Timestamp gmml_time) {
		this.gmml_time = gmml_time;
	}
	public GymMailMsgLogs(int gmml_id, String gmml_sentTo, String gmml_body, Timestamp gmml_time) {
		super();
		this.gmml_id = gmml_id;
		this.gmml_sentTo = gmml_sentTo;
		this.gmml_body = gmml_body;
		this.gmml_time = gmml_time;
	}
	public GymMailMsgLogs() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "GymMailMsgLogs [gmml_id=" + gmml_id + ", gmml_sentTo=" + gmml_sentTo + ", gmml_body=" + gmml_body
				+ ", gmml_time=" + gmml_time + "]";
	}
	
	
	
	

}
