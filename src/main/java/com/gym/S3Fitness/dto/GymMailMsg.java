package com.gym.S3Fitness.dto;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "GymMailMsg")
public class GymMailMsg {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int gmm_id;
	private String gmm_type;
	private String gmm_body;
	public int getGmm_id() {
		return gmm_id;
	}
	public void setGmm_id(int gmm_id) {
		this.gmm_id = gmm_id;
	}
	public String getGmm_type() {
		return gmm_type;
	}
	public void setGmm_type(String gmm_type) {
		this.gmm_type = gmm_type;
	}
	public String getGmm_body() {
		return gmm_body;
	}
	public void setGmm_body(String gmm_body) {
		this.gmm_body = gmm_body;
	}
	public GymMailMsg(int gmm_id, String gmm_type, String gmm_body) {
		super();
		this.gmm_id = gmm_id;
		this.gmm_type = gmm_type;
		this.gmm_body = gmm_body;
	}
	public GymMailMsg() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "GymMailMsg [gmm_id=" + gmm_id + ", gmm_type=" + gmm_type + ", gmm_body=" + gmm_body + "]";
	}
	
	

}
