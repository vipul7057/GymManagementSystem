package com.gym.S3Fitness.dto;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "GymLogs")
public class GymLogs {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int gl_id;
	private String gl_gtname;
	private String gl_message;
	private String gl_body;
	private Timestamp gl_time;
	public int getGl_id() {
		return gl_id;
	}
	public void setGl_id(int gl_id) {
		this.gl_id = gl_id;
	}
	public String getGl_gtname() {
		return gl_gtname;
	}
	public void setGl_gtname(String gl_gtname) {
		this.gl_gtname = gl_gtname;
	}
	public String getGl_message() {
		return gl_message;
	}
	public void setGl_message(String gl_message) {
		this.gl_message = gl_message;
	}
	public String getGl_body() {
		return gl_body;
	}
	public void setGl_body(String gl_body) {
		this.gl_body = gl_body;
	}
	public Timestamp getGl_time() {
		return gl_time;
	}
	public void setGl_time(Timestamp gl_time) {
		this.gl_time = gl_time;
	}
	public GymLogs() {
		super();
		// TODO Auto-generated constructor stub
	}
	public GymLogs(int gl_id, String gl_gtname, String gl_message, String gl_body, Timestamp gl_time) {
		super();
		this.gl_id = gl_id;
		this.gl_gtname = gl_gtname;
		this.gl_message = gl_message;
		this.gl_body = gl_body;
		this.gl_time = gl_time;
	}
	@Override
	public String toString() {
		return "GymLogs [gl_id=" + gl_id + ", gl_gtname=" + gl_gtname + ", gl_message=" + gl_message + ", gl_body="
				+ gl_body + ", gl_time=" + gl_time + "]";
	}
	
	
}
