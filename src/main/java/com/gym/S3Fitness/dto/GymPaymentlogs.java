package com.gym.S3Fitness.dto;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "GymPaymentlogs")
public class GymPaymentlogs {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int gpl_id;
	private int mi_id;
	private LocalDateTime gpl_datetime;
	private String gpl_amountpaid;
	private String gpl_paymentmode;
	public int getGpl_id() {
		return gpl_id;
	}
	public void setGpl_id(int gpl_id) {
		this.gpl_id = gpl_id;
	}
	public int getMi_id() {
		return mi_id;
	}
	public void setMi_id(int mi_id) {
		this.mi_id = mi_id;
	}
	public LocalDateTime getGpl_datetime() {
		return gpl_datetime;
	}
	public void setGpl_datetime(LocalDateTime gpl_datetime) {
		this.gpl_datetime = gpl_datetime;
	}
	public String getGpl_amountpaid() {
		return gpl_amountpaid;
	}
	public void setGpl_amountpaid(String gpl_amountpaid) {
		this.gpl_amountpaid = gpl_amountpaid;
	}
	public String getGpl_paymentmode() {
		return gpl_paymentmode;
	}
	public void setGpl_paymentmode(String gpl_paymentmode) {
		this.gpl_paymentmode = gpl_paymentmode;
	}
	public GymPaymentlogs() {
		super();
		// TODO Auto-generated constructor stub
	}
	public GymPaymentlogs(int gpl_id, int mi_id, LocalDateTime gpl_datetime, String gpl_amountpaid,
			String gpl_paymentmode) {
		super();
		this.gpl_id = gpl_id;
		this.mi_id = mi_id;
		this.gpl_datetime = gpl_datetime;
		this.gpl_amountpaid = gpl_amountpaid;
		this.gpl_paymentmode = gpl_paymentmode;
	}
	@Override
	public String toString() {
		return "GymPaymentlogs [gpl_id=" + gpl_id + ", mi_id=" + mi_id + ", gpl_datetime=" + gpl_datetime
				+ ", gpl_amountpaid=" + gpl_amountpaid + ", gpl_paymentmode=" + gpl_paymentmode + "]";
	}
	
	

}
