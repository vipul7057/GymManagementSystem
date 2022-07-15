package com.gym.S3Fitness.dto;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "GymExpenses")
public class GymExpenses {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int ge_id;
	private String ge_expenses;
	private String ge_addedBy;
	private Timestamp ge_addedTime;
	private int ge_amount;
	
	public int getGe_amount() {
		return ge_amount;
	}
	public void setGe_amount(int ge_amount) {
		this.ge_amount = ge_amount;
	}
	public int getGe_id() {
		return ge_id;
	}
	public void setGe_id(int ge_id) {
		this.ge_id = ge_id;
	}
	public String getGe_expenses() {
		return ge_expenses;
	}
	public void setGe_expenses(String ge_expenses) {
		this.ge_expenses = ge_expenses;
	}
	public String getGe_addedBy() {
		return ge_addedBy;
	}
	public void setGe_addedBy(String ge_addedBy) {
		this.ge_addedBy = ge_addedBy;
	}
	public Timestamp getGe_addedTime() {
		return ge_addedTime;
	}
	public void setGe_addedTime(Timestamp ge_addedTime) {
		this.ge_addedTime = ge_addedTime;
	}
	@Override
	public String toString() {
		return "GymExpenses [ge_id=" + ge_id + ", ge_expenses=" + ge_expenses + ", ge_addedBy=" + ge_addedBy
				+ ", ge_addedTime=" + ge_addedTime + "]";
	}
	public GymExpenses(int ge_id, String ge_expenses, String ge_addedBy, Timestamp ge_addedTime) {
		super();
		this.ge_id = ge_id;
		this.ge_expenses = ge_expenses;
		this.ge_addedBy = ge_addedBy;
		this.ge_addedTime = ge_addedTime;
	}
	public GymExpenses() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

}
