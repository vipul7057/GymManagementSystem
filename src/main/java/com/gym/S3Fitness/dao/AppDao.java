
package com.gym.S3Fitness.dao;

import java.sql.Types;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.gym.S3Fitness.dto.GymOwner;
import com.gym.S3Fitness.dto.GymPackage;
import com.gym.S3Fitness.dto.GymPaymentlogs;
import com.gym.S3Fitness.dto.GymTrainer;
import com.gym.S3Fitness.dto.MemberInfo;

public interface AppDao {

	public int addGymOwner(String fsname, String lsname, String email, String password, String otp, String mobileNo);

	public GymOwner loginGymOwner(String email, String password);

	public int addGymTrainer(String fsname, String lsname, String email, String mobileNo, String exp, String salary,
			String startDate, String btdate, String gender);

	public int addExpenses(int ownerId, String expenses, int amount);

	public List<GymTrainer> getAllGymTrainers(int limit, int offset);

	public List<MemberInfo> getAllGymMembers(int limit, int offset);

	public int addGymPackages(int month, String amount);

	public int getPackageAmount(int gp_month);

	public int addMemberInfo(String fsname, String lsname, String email, String mobileNo, String amount,
			String finalAmount, int remainingDays, String startDate, String enddate, String btdate, int gp_month,
			String mi_paidamount, String mi_remainamount,String mi_status);

	public MemberInfo getMemberById(int mi_id);

	public int updateMemberShipInfo(int gp_monthVal, String enddate, String amountStr, String finalAmountStr,
			int remainingDays, int mi_id, String mi_remainingamountstr);

	public int updateMemberPayment(int mi_id, String mi_paidamount, String mi_remainingamount);

	public int addGymPaymentLogs(int mi_id, String gpl_amountpaid);

	public int freezeMemberShip(int mi_id, int mi_remainingdays, String enddate);

	public int updateGymPackageAmount(int gp_id, int gp_amount);

	public String verifyGymOwner(String fsname, String lsname, String email, String password);

	public int deleteGymOwner(String fsname, String lsname, String email, String password);

	public List<GymPackage> getAllGymPackages();

	public List<MemberInfo> getMemberByName(String name, int limit, int offset);

	public int getTotalCountOfMembers();

	public int getTotalCountOfTrainers();

	public int getTotalCountOfMembersByName(String name);

	public int udpateMemberInfo(String fsname, String lsname, String email, String btDateStr, String mobileNo,
			int mi_id);

	public int insertgymPaymentLogs(String paid_amount,String paymentMode, int mi_id);

	public int getMemberInfoIdByInfo(String mi_fsname, String mi_lsname, String mi_email, String mi_mobile_no,
			String btdate);

	public List<GymPaymentlogs> getPaymentBreakdown(int mi_id);

	public int extendMembershipIfRemainingDaysNot0(String endDate, String totalamount, String totalFinalAmount,
			String paid_amount, int remainingDays, int gp_month, String remainingAmount, int mi_id);

	public int extendMembershipIfRemainingDays0(String startDate, String endDate, String totalamount,
			String totalFinalAmount, String paid_amount, int remainingDays, int gp_month, String remainingAmount,
			int mi_id);

	public GymTrainer getTrainerById(int gt_id);

	public int updateTrainerInfo(String fsname, String lsname, String email, String mobileNo, String salary,
			String btdate, int gt_id);

	public List<GymTrainer> getTrainerByName(String name, int limit, int offset);
	
	public List<MemberInfo> getAllMembers();
	
	public int addTrainerAttendance(int gt_id,String startdate,String enddate,String leaveNote,int NoOfDays);
}
