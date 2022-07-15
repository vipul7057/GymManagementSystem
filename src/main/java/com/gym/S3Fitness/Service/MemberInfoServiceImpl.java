package com.gym.S3Fitness.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.List;

import org.apache.logging.log4j.core.impl.MementoMessage;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.stereotype.Service;

import com.gym.S3Fitness.controller.MemberController;
import com.gym.S3Fitness.dao.AppDao;
import com.gym.S3Fitness.dao.AppDaoImpl;
import com.gym.S3Fitness.dto.GymPackage;
import com.gym.S3Fitness.dto.GymPaymentlogs;
import com.gym.S3Fitness.dto.MemberInfo;

@Service
public class MemberInfoServiceImpl implements MemberInfoService {

	private static final Logger LOGGER = org.slf4j.LoggerFactory.getLogger(MemberInfoServiceImpl.class);
	@Autowired
	private AppDaoImpl appDao;

	@Override
	public int getPackageAmount(int gp_month) {
		return appDao.getPackageAmount(gp_month);
	}

	@Override
	public int addMemberInfo(String fsname, String lsname, String email, String mobileNo, String amount,
			String finalAmount, int remainingDays, String startDate, String enddate, String btdate, int gp_month,
			String mi_paidamount, String mi_remainamount,String mi_status) {
		return appDao.addMemberInfo(fsname, lsname, email, mobileNo, amount, finalAmount, remainingDays, startDate,
				enddate, btdate, gp_month, mi_paidamount,mi_remainamount,mi_status);

	}

	@Override
	public int manageMemberShip(int mi_id, int gp_month, int discount) {
		MemberInfo memberInfo = appDao.getMemberById(mi_id);
		String startDateStr = memberInfo.getMi_startdate();
		String endDateStr = memberInfo.getMi_enddate();
		System.out.println("endDateStr at start" + endDateStr);
		int remainingDays = 0, finalAmountTemp = 0, finalAmount = 0, amountValue = 0;
		int amount = appDao.getPackageAmount(gp_month);
		int gp_monthVal = memberInfo.getGp_month();
		LocalDate endDate = null;
		DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		try {

			endDate = LocalDate.parse(endDateStr, format);
		} catch (Exception e) {
			// TODO: handle exception
		}
		// updated
		gp_monthVal += gp_month;
		String finalAmountStr = null, amountValueStr = null;
		// updated
		endDate = endDate.plusDays(gp_month * 30);
		System.out.println("actual end date " + endDate);
		// LocalDate datetime = LocalDate.parse(String.valueOf(endDate), format);

		LocalDate now = LocalDate.now();
		// updated
		remainingDays = (int) now.until(endDate, ChronoUnit.DAYS);

		finalAmountTemp = amount - discount;
		finalAmount = Integer.parseInt(memberInfo.getMi_finalAmount()) + finalAmountTemp;

		// updated
		finalAmountStr = String.valueOf(finalAmount);

		amountValue = Integer.parseInt(memberInfo.getMi_amount()) + amount;

		// udpated
		amountValueStr = String.valueOf(amountValue);

		String endDateStrFinal = endDate.toString();
		System.out.println("endDateStrFinal " + endDateStrFinal);

		int mi_paidamount = Integer.parseInt(memberInfo.getMi_paidamount());
		System.out.println("mi_paidamount " + mi_paidamount);

		int mi_remainingAmount = finalAmount - mi_paidamount;
		System.out.println("mi_remaining amount " + mi_remainingAmount);

		String mi_remainingAmountStr = String.valueOf(mi_remainingAmount);
		return appDao.updateMemberShipInfo(gp_monthVal, endDateStrFinal, amountValueStr, finalAmountStr, remainingDays,
				mi_id, mi_remainingAmountStr);

	}

	@Override
	public int updateMemberPayment(int mi_id, String paidAmount) {
		LOGGER.info("**Inside update payment " + mi_id + " " + paidAmount);
		MemberInfo memberInfo = appDao.getMemberById(mi_id);
		int mi_paidamount = Integer.parseInt(memberInfo.getMi_paidamount());
		mi_paidamount += Integer.parseInt(paidAmount);

		int mi_remainingamount = Integer.parseInt(memberInfo.getMi_remainingamount());
		int mi_finalmount = Integer.parseInt(memberInfo.getMi_finalAmount());
		int result = 0;
		int mi_remainingamountFinal = mi_finalmount - mi_paidamount;
		if (Integer.parseInt(paidAmount) > mi_remainingamount) {
			return -1;
		} else {
			int res = appDao.addGymPaymentLogs(mi_id, paidAmount);
			return appDao.updateMemberPayment(mi_id, String.valueOf(mi_paidamount),
					String.valueOf(mi_remainingamountFinal));
			// return 0;

		}

	}

	@Override
	public int freezeMemberShip(int mi_id, int NoOfDays) {
		MemberInfo memberInfo = appDao.getMemberById(mi_id);
		int mi_remainingDays = memberInfo.getMi_remainingdays();
		DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		String endDateStr = memberInfo.getMi_enddate();
		LocalDate enddate = LocalDate.parse(endDateStr, format);
		enddate = enddate.plusDays(NoOfDays);

		mi_remainingDays += NoOfDays;
		endDateStr = String.valueOf(enddate);

		return appDao.freezeMemberShip(mi_id, mi_remainingDays, endDateStr);
	}

	@SuppressWarnings("unchecked")
	@Override
	public String getMemberByName(String name, int limit, int offset) {
		System.out.println(limit + " " + offset + " inside service");
		JSONObject result = new JSONObject();
		JSONObject rows = new JSONObject();
		List<MemberInfo> memberListByName = appDao.getMemberByName(name, limit, offset);
		int totalfinalRecords = appDao.getTotalCountOfMembersByName(name);
		int i = 0;
		for (MemberInfo memberInfo : memberListByName) {
			JSONObject obj = new JSONObject();
			obj.put("mi_id", memberInfo.getMi_id());
			obj.put("mi_fullName", memberInfo.getMi_fsname() + " " + memberInfo.getMi_lsname());
			obj.put("mi_email", memberInfo.getMi_email());
			obj.put("mi_mobileNo", memberInfo.getMi_mobileNo());
			obj.put("mi_startdate", memberInfo.getMi_startdate());
			obj.put("mi_enddate", memberInfo.getMi_enddate());
			obj.put("mi_remainingDays", memberInfo.getMi_remainingdays());
			obj.put("gp_month", memberInfo.getGp_month());
			obj.put("mi_btdate", memberInfo.getBtdate());
			obj.put("mi_finalAmount", memberInfo.getMi_finalAmount());
			obj.put("mi_remainingamount", memberInfo.getMi_remainingamount());
			obj.put("mi_amountpaid", memberInfo.getMi_paidamount());

			rows.put(i, obj);
			i++;
		}
		result.put("finalRecordCount", totalfinalRecords);
		result.put("totalrecords", memberListByName.size());
		result.put("rows", rows);
		System.out.println(result.toString());
		return result.toString();
	}

	@SuppressWarnings("unchecked")
	@Override
	public String getMemberById(int mi_id) {
		JSONObject result = new JSONObject();
		JSONObject rows = new JSONObject();

		MemberInfo memberInfo = appDao.getMemberById(mi_id);
		JSONObject obj = new JSONObject();
		obj.put("mi_id", memberInfo.getMi_id());
		obj.put("mi_fsname", memberInfo.getMi_fsname());
		obj.put("mi_lsname", memberInfo.getMi_lsname());
		obj.put("mi_email", memberInfo.getMi_email());
		obj.put("mi_mobileNo", memberInfo.getMi_mobileNo());
		obj.put("mi_startdate", memberInfo.getMi_startdate());
		obj.put("mi_enddate", memberInfo.getMi_enddate());
		obj.put("mi_remainingDays", memberInfo.getMi_remainingdays());
		obj.put("gp_month", memberInfo.getGp_month());
		obj.put("mi_btdate", memberInfo.getBtdate());
		obj.put("mi_finalAmount", memberInfo.getMi_finalAmount());
		obj.put("mi_remainingamount", memberInfo.getMi_remainingamount());
		obj.put("mi_amountpaid", memberInfo.getMi_paidamount());

		rows.put("0", obj);
		result.put("rows", rows);
		System.out.println(result.toString());
		return result.toString();
	}

	@Override
	public int udpateMemberInfo(String fsname, String lsname, String email, String btDateStr, String mobileNo,
			int mi_id) {
		return appDao.udpateMemberInfo(fsname, lsname, email, btDateStr, mobileNo, mi_id);

	}

	@Override
	public int insertgymPaymentLogs(String paid_amount,String paymentMode, int mi_id) {
		return appDao.insertgymPaymentLogs(paid_amount, paymentMode,mi_id);
	}

	@Override
	public int getMemberInfoIdByInfo(String mi_fsname, String mi_lsname, String mi_email, String mi_mobile_no,
			String btdate) {
		return appDao.getMemberInfoIdByInfo(mi_fsname, mi_lsname, mi_email, mi_mobile_no, btdate);
	}

	@SuppressWarnings("unchecked")
	@Override
	public String getPaymentBreakdown(int mi_id) {
		JSONObject result = new JSONObject();
		JSONObject rows = new JSONObject();
		List<GymPaymentlogs> breakdownList = appDao.getPaymentBreakdown(mi_id);
		int i = 0;
		for (GymPaymentlogs paymentLog : breakdownList) {
			JSONObject obj = new JSONObject();
			obj.put("mi_id", paymentLog.getMi_id());
			obj.put("gpl_paidamount", paymentLog.getGpl_amountpaid());
			obj.put("gpl_date", paymentLog.getGpl_datetime());
			rows.put(i, obj);
			i++;
		}
		result.put("totalrecords", breakdownList.size());
		result.put("rows", rows);
		System.out.println(result.toString());
		return result.toString();
	}

	public LocalDate StringToDate(String date) {
		LocalDate resultDate = null;
		DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		try {

			resultDate = LocalDate.parse(date, format);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return resultDate;
	}

	@Override
	public int extendMembership(int mi_id, int amountpaid, int discount, int gp_month, int gp_amount) {
		LOGGER.info("** INside extendMembership** ");
		MemberInfo memberInfo = appDao.getMemberById(mi_id);
		String startDateStr = null;
		String endDateStr = memberInfo.getMi_enddate();
		LocalDate startDate =null;
		LocalDate endDate = StringToDate(endDateStr);
		int remainingDays = memberInfo.getMi_remainingdays();
		int totalAmount = Integer.parseInt(memberInfo.getMi_amount());
		int totalFinalAmount = Integer.parseInt(memberInfo.getMi_finalAmount());
		int paidAmountDB = Integer.parseInt(memberInfo.getMi_paidamount());
		int remainingAmountDB = Integer.parseInt(memberInfo.getMi_remainingamount());
		int gp_monthDB = memberInfo.getGp_month();
		int result = 0;
		if (remainingDays != 0) {
			// updated values
			// enddate
			endDate = endDate.plusDays(gp_month * 30);
			// remainingDays
			remainingDays += gp_month * 30;
			// Total Amount
			totalAmount += gp_amount;
			// Total Final Amount
			totalFinalAmount = totalFinalAmount + (gp_amount - discount);
			// Paid Amount
			paidAmountDB += amountpaid;
			// Remaining Amount
			remainingAmountDB = totalFinalAmount - paidAmountDB;
			// gp_month
			gp_monthDB += gp_month;
			result = appDao.extendMembershipIfRemainingDaysNot0(String.valueOf(endDate), String.valueOf(totalAmount),
					String.valueOf(totalFinalAmount), String.valueOf(paidAmountDB), remainingDays, gp_monthDB,
					String.valueOf(remainingAmountDB), mi_id);

		}
		// remaining days is 0 then start date should be now
		else if (remainingDays == 0) {
			// updated values
			// start date
			DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			LocalDate now = LocalDate.now();
			startDateStr = format.format(now);
			// enddate
			endDate = now.plusDays(gp_month * 30);
			// remainingDays
			remainingDays += gp_month * 30;
			// Total Amount
			totalAmount = gp_amount;
			// Total Final Amount
			totalFinalAmount = gp_amount - discount;
			// Paid Amount
			paidAmountDB = amountpaid;
			// Remaining Amount
			remainingAmountDB = totalFinalAmount - paidAmountDB;
			// gp_month
			gp_monthDB = gp_month;
			result = appDao.extendMembershipIfRemainingDays0(startDateStr, String.valueOf(endDate),
					String.valueOf(totalAmount), String.valueOf(totalFinalAmount), String.valueOf(paidAmountDB),
					remainingDays, gp_monthDB, String.valueOf(remainingAmountDB), mi_id);
		}

		return result;
	}

}
