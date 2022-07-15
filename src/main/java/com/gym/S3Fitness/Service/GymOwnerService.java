package com.gym.S3Fitness.Service;

import java.time.LocalDate;
import java.util.List;

import com.gym.S3Fitness.dto.GymOwner;
import com.gym.S3Fitness.dto.GymTrainer;

public interface GymOwnerService {

	public int addGymOwner(String fsname, String lsname, String email, String password, String otp,String mobileNo);

	public GymOwner loginGymOwner(String email, String password);

	public int addGymTrainer(String fsname, String lsname, String email, String mobileNo, String exp, String salary,
			String startDate, String btdate, String gender);

	public int addExpenses(int ownerId, String expenses, int amount);

	public String getAllGymTrainers(int limit,int offset);

	public String getAllGymMembers(int limit , int offset);

	public int addGymPackages(int month, String amount);

	public int updateGymPackageAmount(int gp_id, int gp_amount);

	public String sendOtpMail(String email, String otp);

	public String verifyGymOwner(String fsname, String lsname, String email, String password);
	
	public int deleteGymOwner(String fsname, String lsname, String email, String password);
	
	public String getAllGymPackges();
	
	public String getTrainerById(int gt_id);
	
	public int updateTrainer(String fsname, String lsname, String email, String mobileNo, String salary,
			String btdate, int gt_id);
	
	public String getTrainerByName(String name, int limit, int offset);
	
	public void getAllMembers();
	
	public int addTrainerAttendance(int gt_id,String startdate,String enddate,String leaveNote,int NoOfDays);


}
