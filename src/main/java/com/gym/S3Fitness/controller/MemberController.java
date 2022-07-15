package com.gym.S3Fitness.controller;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Date;

import javax.persistence.criteria.CriteriaBuilder.In;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.gym.S3Fitness.Service.MemberInfoService;
import com.gym.S3Fitness.Service.MemberInfoServiceImpl;
import com.gym.S3Fitness.dao.AppDaoImpl;
import com.gym.S3Fitness.dto.MemberInfo;
import com.gym.S3Fitness.sql.AppSQL;
import com.gym.S3Fitness.sql.AppSQLImpl;

@Controller
public class MemberController {

	private static final Logger LOGGER = org.slf4j.LoggerFactory.getLogger(MemberController.class);

	@Autowired
	private MemberInfoServiceImpl memberInfoServiceImpl;

	@Autowired
	private AppDaoImpl appdao;

	public JSONObject getJsonFromString(String queryParam) {
		JSONObject data = new JSONObject();
		try {
			data = (JSONObject) new JSONParser().parse(queryParam);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return data;
	}

	@SuppressWarnings("unchecked")
	@PostMapping(value = "/addMemberInfo", consumes = org.springframework.http.MediaType.APPLICATION_FORM_URLENCODED_VALUE, produces = org.springframework.http.MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public String addMemberInfo(@RequestParam String queryParam) {
		LOGGER.info("**Inside addMemberInfo***" + queryParam);
		JSONObject data = getJsonFromString(queryParam);
		JSONObject resultObj = new JSONObject();
		int resStatus = 0;
		String resultStr = "Member Not Added";
		String mi_fsname = null, mi_lsname = null, mi_email = null, mi_mobileNo = null, mi_remainingDays = null,
				mi_discount = null, btDateStr = null, mi_paymentMode = null, amountStr = null, endDateStr = null,
				finalAmountStr = null, mi_paidamount = null, mi_remainingamount = null;
		String mi_startdate = null;
		LocalDate startdate = null, enddate = null, btdate = null;
		int gp_months = 0;
		int discountInt = 0;
		int mi_id = 0;
		int finalAmount, remainingDays = 0;
		String mi_status = "Active";
		/*
		 * remainingDays should calculated only gp_id and discount if any will be coming
		 * from UI,finalamount needs to be calculated
		 */

		try {
			mi_fsname = data.get("fsname").toString();
			mi_lsname = data.get("lsname").toString();
			mi_email = data.get("email").toString();
			mi_mobileNo = data.get("mobileNo").toString();
			mi_discount = data.get("discount").toString();
			btDateStr = data.get("btdate").toString();
			mi_paidamount = data.get("paid_amount").toString();
			mi_startdate = data.get("startdate").toString();
			gp_months = Integer.parseInt(data.get("gp_months").toString());
			mi_paymentMode =  data.get("payment").toString();

		} catch (Exception e) {
			e.printStackTrace();
		}

		if (mi_paidamount == "") {
			mi_paidamount = "0";
		}
		if (mi_discount == "") {
			mi_discount = "0";
		}

		try {
			DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			startdate = LocalDate.parse(mi_startdate, format);
			// LocalDate now = LocalDate.now();
			LocalDate endate1 = startdate.plusDays(gp_months * 30);

			endDateStr = format.format(endate1);

			enddate = LocalDate.parse(endDateStr, format);
			btdate = LocalDate.parse(btDateStr, format);

		} catch (Exception e) {
			e.printStackTrace();
		}
		int amount = memberInfoServiceImpl.getPackageAmount(gp_months);
		amountStr = String.valueOf(amount);

		if (!mi_discount.equals("")) {
			discountInt = Integer.parseInt(mi_discount);
		}
		finalAmount = amount - discountInt;
		finalAmountStr = String.valueOf(finalAmount);
		remainingDays = (int) startdate.until(enddate, ChronoUnit.DAYS);

		int paidamount = Integer.parseInt(mi_paidamount);
		int remainingAmount = finalAmount - paidamount;
		mi_remainingamount = String.valueOf(remainingAmount);
		int result = memberInfoServiceImpl.addMemberInfo(mi_fsname, mi_lsname, mi_email, mi_mobileNo, amountStr,
				finalAmountStr, remainingDays, mi_startdate, endDateStr, btDateStr, gp_months, mi_paidamount,
				mi_remainingamount,mi_status);

		mi_id = memberInfoServiceImpl.getMemberInfoIdByInfo(mi_fsname, mi_lsname, mi_email, mi_mobileNo, btDateStr);
		LOGGER.info("mi_id in controller " + mi_id);
		if (!mi_paidamount.equalsIgnoreCase("0")) {
			memberInfoServiceImpl.insertgymPaymentLogs(mi_paidamount,mi_paymentMode, mi_id);
		}

		if (result == 1) {
			resStatus = 1;
			resultStr = "Gym Member Added Successfully";
		}
		resultObj.put("resStatus", resStatus);
		resultObj.put("resultStr", resultStr);
		return resultObj.toString();
	}

	@SuppressWarnings("unchecked")
	@PostMapping(value = "/manageMemberShip", consumes = org.springframework.http.MediaType.APPLICATION_FORM_URLENCODED_VALUE, produces = org.springframework.http.MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public String manageMemberShip(@RequestParam String queryParam) {
		LOGGER.info("**Inside manageMemberShip***" + queryParam);
		JSONObject data = getJsonFromString(queryParam);
		JSONObject resultObj = new JSONObject();
		int resStatus = 0;
		String resultStr = "MemberShip Update Failed";
		int mi_id = 0;
		int gp_month = 0, discount = 0;
		try {
			mi_id = Integer.parseInt(data.get("mi_id").toString());
			gp_month = Integer.parseInt(data.get("gp_month").toString());
			discount = Integer.parseInt(data.get("discount").toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		int result = memberInfoServiceImpl.manageMemberShip(mi_id, gp_month, discount);
		System.out.println("result values " + result);
		if (result == 1) {
			resStatus = 1;
			resultStr = "MemberShip Updated Successfully";
		}
		resultObj.put("resStatus", resStatus);
		resultObj.put("resultStr", resultStr);
		return resultObj.toString();
	}

	@SuppressWarnings("unchecked")
	@PostMapping(value = "/updateMemberPayment", consumes = org.springframework.http.MediaType.APPLICATION_FORM_URLENCODED_VALUE, produces = org.springframework.http.MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public String updateMemberPayment(@RequestParam String queryParam) {
		LOGGER.info("**Inside updateMemberPayment***" + queryParam);
		JSONObject resultObj = new JSONObject();
		JSONObject data = getJsonFromString(queryParam);
		String paidAmount = null;
		int mi_id = 0;
		int resStatus = 0;
		String resultStr = "MemberShip Payment Updation Failed";
		try {
			mi_id = Integer.parseInt(data.get("mi_id").toString());
			paidAmount = data.get("paidamount").toString();
		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println(mi_id + " " + paidAmount);
		int result = memberInfoServiceImpl.updateMemberPayment(mi_id, paidAmount);
		if (result == 1) {
			resStatus = 1;
			resultStr = "MemberShip Payment Updated Successfully";
		} else if (result == -1) {
			resStatus = -1;
			resultStr = "Invalid Amount Entered";
		}
		resultObj.put("resStatus", resStatus);
		resultObj.put("resultStr", resultStr);
		return resultObj.toString();

	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/freezeMemberShip", consumes = org.springframework.http.MediaType.APPLICATION_FORM_URLENCODED_VALUE, produces = org.springframework.http.MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public String freezeMemberShip(@RequestParam String queryParam) {
		JSONObject resultObj = new JSONObject();
		JSONObject data = getJsonFromString(queryParam);
		int mi_id = 0;
		int NoOfDays = 0;
		int resStatus = 0;
		String resultStr = "Membership Freeze Failed";
		try {
			mi_id = Integer.parseInt(data.get("mi_id").toString());
			NoOfDays = Integer.parseInt(data.get("NoOfDays").toString());
		} catch (Exception e) {
			e.printStackTrace();
		}

		int result = memberInfoServiceImpl.freezeMemberShip(mi_id, NoOfDays);
		if (result == 1) {
			resStatus = 1;
			resultStr = "Membership Freezed Successfully";
		}
		resultObj.put("resStatus", resStatus);
		resultObj.put("resultStr", resultStr);
		return resultObj.toString();
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/getMemberByName", consumes = org.springframework.http.MediaType.APPLICATION_FORM_URLENCODED_VALUE, produces = org.springframework.http.MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public String getMemberByName(@RequestParam String queryParam) {
		LOGGER.info("***inside getMemberByName** " + queryParam);
		JSONObject data = getJsonFromString(queryParam);
		String name = "";
		int limit = 0;
		int offset = 0;

		try {
			name = data.get("mi_fullName").toString();
			limit = Integer.parseInt(data.get("limit").toString());
			offset = Integer.parseInt(data.get("offset").toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return memberInfoServiceImpl.getMemberByName(name, limit, offset);
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/getMemberById", consumes = org.springframework.http.MediaType.APPLICATION_FORM_URLENCODED_VALUE, produces = org.springframework.http.MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public String getMemberById(@RequestParam String queryParam) {
		LOGGER.info("***inside getMemberById** " + queryParam);
		JSONObject data = getJsonFromString(queryParam);

		int mi_id = 0;

		try {

			mi_id = Integer.parseInt(data.get("mi_id").toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return memberInfoServiceImpl.getMemberById(mi_id);
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/udpateMemberInfo", consumes = org.springframework.http.MediaType.APPLICATION_FORM_URLENCODED_VALUE, produces = org.springframework.http.MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public String udpateMemberInfo(@RequestParam String queryParam) {
		LOGGER.info("***inside udpateMemberInfo** " + queryParam);
		JSONObject resultObj = new JSONObject();
		JSONObject data = getJsonFromString(queryParam);
		String mi_fsname = null, mi_lsname = null, mi_email = null, mi_mobileNo = null, btDateStr = null;
		int resStatus = 0;
		String resultStr = "Member Details Update Failed";
		int mi_id = 0;
		try {
			mi_fsname = data.get("fsname").toString();
			mi_lsname = data.get("lsname").toString();
			mi_email = data.get("email").toString();
			mi_mobileNo = data.get("mobileNo").toString();
			btDateStr = data.get("btdate").toString();
			mi_id = Integer.parseInt(data.get("mi_id").toString());

		} catch (Exception e) {
			e.printStackTrace();
		}
		int res = memberInfoServiceImpl.udpateMemberInfo(mi_fsname, mi_lsname, mi_email, btDateStr, mi_mobileNo, mi_id);
		if (res == 1) {
			resStatus = 1;
			resultStr = "Member Details Updated";
		}

		resultObj.put("resStatus", resStatus);
		resultObj.put("resultStr", resultStr);
		return resultObj.toString();
	}

	@SuppressWarnings("unchecked")
	@PostMapping(value = "/getPaymentBreakdown", consumes = org.springframework.http.MediaType.APPLICATION_FORM_URLENCODED_VALUE, produces = org.springframework.http.MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public String getPaymentBreakdown(@RequestParam String queryParam) {
		LOGGER.info("***inside getPaymentBreakdown** " + queryParam);
		JSONObject data = getJsonFromString(queryParam);

		int mi_id = 0;

		try {

			mi_id = Integer.parseInt(data.get("mi_id").toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		String result = memberInfoServiceImpl.getPaymentBreakdown(mi_id);
		LOGGER.info("result:-" + result);
		return result;
	}

	@SuppressWarnings("unchecked")
	@PostMapping(value = "/extendMembership", consumes = org.springframework.http.MediaType.APPLICATION_FORM_URLENCODED_VALUE, produces = org.springframework.http.MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public String extendMembership(@RequestParam String queryParam) {
		LOGGER.info("**Inside extendMembership***" + queryParam);
		JSONObject resultObj = new JSONObject();
		int resStatus = 0;
		String resultStr = "Membership Extension Failed";
		int mi_id = 0;
		String mi_discount = null, mi_paidamount = null;
		int discountInt = 0;
		int amountpaidInt = 0;
		int gp_month = 0;
		int gp_amount = 0;
		JSONObject data = getJsonFromString(queryParam);
		try {
			mi_id = Integer.parseInt(data.get("mi_id").toString());
			mi_discount = data.get("discount").toString();
			mi_paidamount = data.get("paidamount").toString();
			gp_month = Integer.parseInt(data.get("gp_month").toString());

		} catch (Exception e) {
			e.printStackTrace();
		}
		gp_amount = memberInfoServiceImpl.getPackageAmount(gp_month);
		System.out.println("gp_amount " + gp_amount);
		if (!mi_discount.equals("")) {
			discountInt = Integer.parseInt(mi_discount);

		}
		if (!mi_paidamount.equals("")) {
			amountpaidInt = Integer.parseInt(mi_paidamount);

		}
		// discount > gp_amount HANDLED
		if (discountInt > gp_amount) {
			resStatus = -1;
			resultStr = "Invalid Data Entered";
		}

		int res = memberInfoServiceImpl.extendMembership(mi_id, amountpaidInt, discountInt, gp_month, gp_amount);
		if (res == 1) {
			resStatus = 1;
			resultStr = "Membership Extended";

		}

		resultObj.put("resStatus", resStatus);
		resultObj.put("resultStr", resultStr);
		return resultObj.toString();
	}

}
