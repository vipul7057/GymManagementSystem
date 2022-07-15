package com.gym.S3Fitness.dto;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "GymPackage")
public class GymPackage {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int gp_id;
	private int gp_month;
	private int gp_amount;
	public int getGp_id() {
		return gp_id;
	}
	public void setGp_id(int gp_id) {
		this.gp_id = gp_id;
	}
	public int getGp_month() {
		return gp_month;
	}
	public void setGp_month(int gp_month) {
		this.gp_month = gp_month;
	}
	public int getGp_amount() {
		return gp_amount;
	}
	public void setGp_amount(int gp_amount) {
		this.gp_amount = gp_amount;
	}
	@Override
	public String toString() {
		return "GymPackage [gp_id=" + gp_id + ", gp_month=" + gp_month + ", gp_amount=" + gp_amount + "]";
	}
	public GymPackage(int gp_id, int gp_month, int gp_amount) {
		super();
		this.gp_id = gp_id;
		this.gp_month = gp_month;
		this.gp_amount = gp_amount;
	}
	public GymPackage() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

}
