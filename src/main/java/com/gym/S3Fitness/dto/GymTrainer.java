package com.gym.S3Fitness.dto;

import java.sql.Date;
import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "GymTrainer")
public class GymTrainer {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int gt_id;
	private String gt_fsname;
	private String gt_lsname;
	private String gt_email;
	private String gt_mobileNo;
	private String gt_btdate;
	private String gt_exp;
	private String gt_salary;
	private String gt_startdate;
	private LocalDate gt_enddate = null;
	private String gt_gender;
	public int getGt_id() {
		return gt_id;
	}
	public void setGt_id(int gt_id) {
		this.gt_id = gt_id;
	}
	public String getGt_fsname() {
		return gt_fsname;
	}
	public void setGt_fsname(String gt_fsname) {
		this.gt_fsname = gt_fsname;
	}
	public String getGt_lsname() {
		return gt_lsname;
	}
	public void setGt_lsname(String gt_lsname) {
		this.gt_lsname = gt_lsname;
	}
	public String getGt_email() {
		return gt_email;
	}
	public void setGt_email(String gt_email) {
		this.gt_email = gt_email;
	}
	public String getGt_mobileNo() {
		return gt_mobileNo;
	}
	public void setGt_mobileNo(String gt_mobileNo) {
		this.gt_mobileNo = gt_mobileNo;
	}
	public String getGt_btdate() {
		return gt_btdate;
	}
	public void setGt_btdate(String gt_btdate) {
		this.gt_btdate = gt_btdate;
	}
	public String getGt_exp() {
		return gt_exp;
	}
	public void setGt_exp(String gt_exp) {
		this.gt_exp = gt_exp;
	}
	public String getGt_salary() {
		return gt_salary;
	}
	public void setGt_salary(String gt_salary) {
		this.gt_salary = gt_salary;
	}
	public String getGt_startdate() {
		return gt_startdate;
	}
	public void setGt_startdate(String gt_startdate) {
		this.gt_startdate = gt_startdate;
	}
	public LocalDate getGt_enddate() {
		return gt_enddate;
	}
	public void setGt_enddate(LocalDate gt_enddate) {
		this.gt_enddate = gt_enddate;
	}
	public String getGt_gender() {
		return gt_gender;
	}
	public void setGt_gender(String gt_gender) {
		this.gt_gender = gt_gender;
	}
	public GymTrainer(int gt_id, String gt_fsname, String gt_lsname, String gt_email, String gt_mobileNo,
			String gt_btdate, String gt_exp, String gt_salary, String gt_startdate, LocalDate gt_enddate,
			String gt_gender) {
		super();
		this.gt_id = gt_id;
		this.gt_fsname = gt_fsname;
		this.gt_lsname = gt_lsname;
		this.gt_email = gt_email;
		this.gt_mobileNo = gt_mobileNo;
		this.gt_btdate = gt_btdate;
		this.gt_exp = gt_exp;
		this.gt_salary = gt_salary;
		this.gt_startdate = gt_startdate;
		this.gt_enddate = gt_enddate;
		this.gt_gender = gt_gender;
	}
	public GymTrainer() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "GymTrainer [gt_id=" + gt_id + ", gt_fsname=" + gt_fsname + ", gt_lsname=" + gt_lsname + ", gt_email="
				+ gt_email + ", gt_mobileNo=" + gt_mobileNo + ", gt_btdate=" + gt_btdate + ", gt_exp=" + gt_exp
				+ ", gt_salary=" + gt_salary + ", gt_startdate=" + gt_startdate + ", gt_enddate=" + gt_enddate
				+ ", gt_gender=" + gt_gender + "]";
	}
	
	
	
	

}
