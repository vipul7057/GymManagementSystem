package com.gym.S3Fitness.dto;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "MemberInfo")
public class MemberInfo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int mi_id;
	private String mi_fsname;
	private String mi_lsname;
	private String mi_email;
	private String mi_mobileNo;
	private String mi_startdate;
	private String mi_enddate;
	private int gp_month;
	private String mi_amount;
	private String mi_finalAmount;
	private int mi_remainingdays;
	private String btdate;
	private String mi_paidamount;
	private String mi_remainingamount;
	private String mi_status;
	public int getMi_id() {
		return mi_id;
	}
	public void setMi_id(int mi_id) {
		this.mi_id = mi_id;
	}
	public String getMi_fsname() {
		return mi_fsname;
	}
	public void setMi_fsname(String mi_fsname) {
		this.mi_fsname = mi_fsname;
	}
	public String getMi_lsname() {
		return mi_lsname;
	}
	public void setMi_lsname(String mi_lsname) {
		this.mi_lsname = mi_lsname;
	}
	public String getMi_email() {
		return mi_email;
	}
	public void setMi_email(String mi_email) {
		this.mi_email = mi_email;
	}
	public String getMi_mobileNo() {
		return mi_mobileNo;
	}
	public void setMi_mobileNo(String mi_mobileNo) {
		this.mi_mobileNo = mi_mobileNo;
	}
	public String getMi_startdate() {
		return mi_startdate;
	}
	public void setMi_startdate(String mi_startdate) {
		this.mi_startdate = mi_startdate;
	}
	public String getMi_enddate() {
		return mi_enddate;
	}
	public void setMi_enddate(String mi_enddate) {
		this.mi_enddate = mi_enddate;
	}
	public int getGp_month() {
		return gp_month;
	}
	public void setGp_month(int gp_month) {
		this.gp_month = gp_month;
	}
	public String getMi_amount() {
		return mi_amount;
	}
	public void setMi_amount(String mi_amount) {
		this.mi_amount = mi_amount;
	}
	public String getMi_finalAmount() {
		return mi_finalAmount;
	}
	public void setMi_finalAmount(String mi_finalAmount) {
		this.mi_finalAmount = mi_finalAmount;
	}
	public int getMi_remainingdays() {
		return mi_remainingdays;
	}
	public void setMi_remainingdays(int mi_remainingdays) {
		this.mi_remainingdays = mi_remainingdays;
	}
	public String getBtdate() {
		return btdate;
	}
	public void setBtdate(String btdate) {
		this.btdate = btdate;
	}
	public String getMi_paidamount() {
		return mi_paidamount;
	}
	public void setMi_paidamount(String mi_paidamount) {
		this.mi_paidamount = mi_paidamount;
	}
	public String getMi_remainingamount() {
		return mi_remainingamount;
	}
	public void setMi_remainingamount(String mi_remainingamount) {
		this.mi_remainingamount = mi_remainingamount;
	}
	public String getMi_status() {
		return mi_status;
	}
	public void setMi_status(String mi_status) {
		this.mi_status = mi_status;
	}
	public MemberInfo(int mi_id, String mi_fsname, String mi_lsname, String mi_email, String mi_mobileNo,
			String mi_startdate, String mi_enddate, int gp_month, String mi_amount, String mi_finalAmount,
			int mi_remainingdays, String btdate, String mi_paidamount, String mi_remainingamount, String mi_status) {
		super();
		this.mi_id = mi_id;
		this.mi_fsname = mi_fsname;
		this.mi_lsname = mi_lsname;
		this.mi_email = mi_email;
		this.mi_mobileNo = mi_mobileNo;
		this.mi_startdate = mi_startdate;
		this.mi_enddate = mi_enddate;
		this.gp_month = gp_month;
		this.mi_amount = mi_amount;
		this.mi_finalAmount = mi_finalAmount;
		this.mi_remainingdays = mi_remainingdays;
		this.btdate = btdate;
		this.mi_paidamount = mi_paidamount;
		this.mi_remainingamount = mi_remainingamount;
		this.mi_status = mi_status;
	}
	public MemberInfo() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "MemberInfo [mi_id=" + mi_id + ", mi_fsname=" + mi_fsname + ", mi_lsname=" + mi_lsname + ", mi_email="
				+ mi_email + ", mi_mobileNo=" + mi_mobileNo + ", mi_startdate=" + mi_startdate + ", mi_enddate="
				+ mi_enddate + ", gp_month=" + gp_month + ", mi_amount=" + mi_amount + ", mi_finalAmount="
				+ mi_finalAmount + ", mi_remainingdays=" + mi_remainingdays + ", btdate=" + btdate + ", mi_paidamount="
				+ mi_paidamount + ", mi_remainingamount=" + mi_remainingamount + ", mi_status=" + mi_status + "]";
	}
	
	
	
}
