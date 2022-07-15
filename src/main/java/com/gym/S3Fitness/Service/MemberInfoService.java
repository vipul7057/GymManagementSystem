package com.gym.S3Fitness.Service;

import java.time.LocalDate;
import java.util.List;

import com.gym.S3Fitness.dto.GymPaymentlogs;
import com.gym.S3Fitness.dto.MemberInfo;

public interface MemberInfoService {

	public int getPackageAmount(int gp_month);

	public int addMemberInfo(String fsname, String lsname, String email, String mobileNo, String amount,
			String finalAmount, int remainingDays, String startDate, String enddate, String btdate, int gp_month,
			String mi_paidamount, String mi_remainamount,String mi_status);

	public int manageMemberShip(int mi_id, int gp_month, int discount);

	public int updateMemberPayment(int mi_id, String paidAmount);

	public int freezeMemberShip(int mi_id, int NoOfDays);

	public String getMemberByName(String name, int limit, int offset);

	public String getMemberById(int mi_id);

	public int udpateMemberInfo(String fsname, String lsname, String email, String btDateStr, String mobileNo,
			int mi_id);

	public int insertgymPaymentLogs(String paid_amount,String paymentMode, int mi_id);

	public int getMemberInfoIdByInfo(String mi_fsname, String mi_lsname, String mi_email, String mi_mobile_no,
			String btdate);

	public String getPaymentBreakdown(int mi_id);
	
	public int extendMembership(int mi_id,int amountpaid,int discount,int gp_month,int gp_amount);
}
