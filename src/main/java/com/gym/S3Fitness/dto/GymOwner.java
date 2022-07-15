package com.gym.S3Fitness.dto;

import javax.persistence.Entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name="GymOwner")
public class GymOwner {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int go_id;
	private String go_fsname;
	private String go_lsname;
	private String go_email;
	private String go_password;
	private String otp;
	private String mobileNo;
	
	public String getMobileNo() {
		return mobileNo;
	}
	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}
	public String getOtp() {
		return otp;
	}
	public void setOtp(String otp) {
		this.otp = otp;
	}
	public int getGo_id() {
		return go_id;
	}
	public void setGo_id(int go_id) {
		this.go_id = go_id;
	}
	public String getGo_fsname() {
		return go_fsname;
	}
	public void setGo_fsname(String go_fsname) {
		this.go_fsname = go_fsname;
	}
	public String getGo_lsname() {
		return go_lsname;
	}
	public void setGo_lsname(String go_lsname) {
		this.go_lsname = go_lsname;
	}
	public String getGo_email() {
		return go_email;
	}
	public void setGo_email(String go_email) {
		this.go_email = go_email;
	}
	public String getGo_password() {
		return go_password;
	}
	public void setGo_password(String go_password) {
		this.go_password = go_password;
	}
	public GymOwner() {
		super();
		// TODO Auto-generated constructor stub
	}
	public GymOwner(int go_id, String go_fsname, String go_lsname, String go_email, String go_password, String otp,
			String mobileNo) {
		super();
		this.go_id = go_id;
		this.go_fsname = go_fsname;
		this.go_lsname = go_lsname;
		this.go_email = go_email;
		this.go_password = go_password;
		this.otp = otp;
		this.mobileNo = mobileNo;
	}
	@Override
	public String toString() {
		return "GymOwner [go_id=" + go_id + ", go_fsname=" + go_fsname + ", go_lsname=" + go_lsname + ", go_email="
				+ go_email + ", go_password=" + go_password + ", otp=" + otp + ", mobileNo=" + mobileNo + "]";
	}
	
	

}
