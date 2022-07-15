package com.gym.S3Fitness.dto;

import javax.annotation.Generated;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "TrainerAttendance")
public class TrainerAttendance {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int ta_id;
	private int gt_id;
	private String ta_startdate;
	private String ta_enddate;
	private int ta_noofdays;
	private String ta_leavenote;
	public int getTa_id() {
		return ta_id;
	}
	public void setTa_id(int ta_id) {
		this.ta_id = ta_id;
	}
	public int getGt_id() {
		return gt_id;
	}
	public void setGt_id(int gt_id) {
		this.gt_id = gt_id;
	}
	public String getTa_startdate() {
		return ta_startdate;
	}
	public void setTa_startdate(String ta_startdate) {
		this.ta_startdate = ta_startdate;
	}
	public String getTa_enddate() {
		return ta_enddate;
	}
	public void setTa_enddate(String ta_enddate) {
		this.ta_enddate = ta_enddate;
	}
	public int getTa_noofdays() {
		return ta_noofdays;
	}
	public void setTa_noofdays(int ta_noofdays) {
		this.ta_noofdays = ta_noofdays;
	}
	public String getTa_leavenote() {
		return ta_leavenote;
	}
	public void setTa_leavenote(String ta_leavenote) {
		this.ta_leavenote = ta_leavenote;
	}
	public TrainerAttendance(int ta_id, int gt_id, String ta_startdate, String ta_enddate, int ta_noofdays,
			String ta_leavenote) {
		super();
		this.ta_id = ta_id;
		this.gt_id = gt_id;
		this.ta_startdate = ta_startdate;
		this.ta_enddate = ta_enddate;
		this.ta_noofdays = ta_noofdays;
		this.ta_leavenote = ta_leavenote;
	}
	public TrainerAttendance() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "TrainerAttendance [ta_id=" + ta_id + ", gt_id=" + gt_id + ", ta_startdate=" + ta_startdate
				+ ", ta_enddate=" + ta_enddate + ", ta_noofdays=" + ta_noofdays + ", ta_leavenote=" + ta_leavenote
				+ "]";
	}
	
	
	
	
}
