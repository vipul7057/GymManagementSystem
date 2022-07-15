package com.gym.S3Fitness.controller;

import java.text.SimpleDateFormat;

import java.time.LocalDate;


import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;

import javax.persistence.criteria.CriteriaBuilder.In;
import javax.servlet.http.HttpSession;

import org.apache.tomcat.util.http.parser.MediaType;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jackson.JsonObjectDeserializer;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.gym.S3Fitness.Service.GymOwnerServiceImpl;
import com.gym.S3Fitness.Service.MemberInfoServiceImpl;
import com.gym.S3Fitness.dao.AppDaoImpl;
import com.gym.S3Fitness.dto.GymOwner;
import com.gym.S3Fitness.dto.GymTrainer;

import org.slf4j.Logger;

@Controller
public class GymQwnerController {

	private static final Logger LOGGER = org.slf4j.LoggerFactory.getLogger(GymQwnerController.class);

	@RequestMapping(value = "/homepage")
	public ModelAndView homepage() {
		return new ModelAndView("homepage");
	}

	@Autowired
	private GymOwnerServiceImpl gymOwnerService;

	@Autowired
	private AppDaoImpl appDao;

	@Autowired
	private MemberInfoServiceImpl memberInfoServiceImpl;

	public JSONObject getJsonFromString(String queryParam) {
		JSONObject data = new JSONObject();
		try {
			data = (JSONObject) new JSONParser().parse(queryParam);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return data;

	}

	public String getValidLogin(int ownerId) {
		JSONObject resultObj = new JSONObject();
		int resStatus = -1;
		String resultStr = "Gym Owner Need To Login";
		resultObj.put("resStatus", resStatus);
		resultObj.put("resultStr", resultStr);
		return resultObj.toString();
	}

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String display() {
		System.out.println("Inside homepage");
		String url = "homepage";
		return url;
	}

	// ==============================================

	@SuppressWarnings("unchecked")
	@PostMapping(value = "/addGymOwner", consumes = org.springframework.http.MediaType.APPLICATION_FORM_URLENCODED_VALUE, produces = org.springframework.http.MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public String addGymOwner(@RequestParam String queryParam) {
		LOGGER.info("**Inside addGymOwner***" + queryParam);
		int resStatus = 0;
		String resultStr = "User Not Added";
		JSONObject resultObj = new JSONObject();
		String fsname = null, lsname = null, email = null, password = null, otp = null, mobileNo = null;
		JSONObject data = getJsonFromString(queryParam);
		try {
			fsname = data.get("fsname").toString();
			lsname = data.get("lsname").toString();
			email = data.get("email").toString();
			password = data.get("password").toString();
			otp = data.get("otp").toString();
			mobileNo = data.get("mobileNo").toString();
		} catch (Exception e) {
			e.printStackTrace();
		}

		int result = gymOwnerService.addGymOwner(fsname, lsname, email, password, otp, mobileNo);
		try {
			gymOwnerService.sendOtpMail(email, otp);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		// int result = 1;
		if (result == 1) {
			resultStr = "Sign Up Successfully";
			resStatus = 1;
		}
		resultObj.put("resStatus", resStatus);
		resultObj.put("resultStr", resultStr);
		return resultObj.toString();

	}

	// ==============================================

	@PostMapping(value = "/loginGymOwner", consumes = org.springframework.http.MediaType.APPLICATION_FORM_URLENCODED_VALUE, produces = org.springframework.http.MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public String loginGymOwner(HttpSession httpSession, @RequestParam String queryParam) {
		LOGGER.info("**Inside loginGymOwner***" + queryParam);

		JSONObject resultObj = new JSONObject();
		String email = null, password = null;
		int resStatus = 0;
		String resultStr = "Owner Not Found";
		JSONObject data = getJsonFromString(queryParam);
		try {
			email = data.get("email").toString();
			password = data.get("password").toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		GymOwner gymOwner = new GymOwner();
		String fsname = "";

		try {
			gymOwner = gymOwnerService.loginGymOwner(email, password);
			fsname = gymOwner.getGo_fsname();
		} catch (Exception e) {
			fsname = "";
		}
		if (fsname != null) {
			resStatus = 1;
			resultStr = "Login Successful";
			httpSession.setAttribute("OwnerId", gymOwner.getGo_id());
		} else {
			httpSession.setAttribute("OwnerId", 0);
		}
		resultObj.put("resStatus", resStatus);
		resultObj.put("resultStr", resultStr);
		return resultObj.toString();
	}

	// ==============================================

	@PostMapping(value = "/addGymTrainer", consumes = org.springframework.http.MediaType.APPLICATION_FORM_URLENCODED_VALUE, produces = org.springframework.http.MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public String addGymTrainer(HttpSession httpSession, @RequestParam String queryParam) {
		LOGGER.info("**Inside addGymTrainer***" + queryParam);
		JSONObject data = getJsonFromString(queryParam);
		String fsname = null, lsname = null, email = null, mobileNo = null, exp = null, salary = null,
				startDateStr = null, endDateStr = null, btdateStr = null, gender = null;
		LocalDate startdate = null, enddate = null, btdate = null;
		int resStatus = 0;
		String resultStr = "Gym Trainer Not Added";
		JSONObject resultObj = new JSONObject();
		/*
		 * int ownerId = (int) httpSession.getAttribute("OwnerId");
		 * 
		 * 
		 * if (ownerId == 0) { return getValidLogin(ownerId); }
		 */

		try {
			fsname = data.get("fsname").toString();
			lsname = data.get("lsname").toString();
			email = data.get("email").toString();
			mobileNo = data.get("mobileNo").toString();
			exp = data.get("exp").toString();
			salary = data.get("salary").toString();
			startDateStr = data.get("startdate").toString();
			gender = data.get("gender").toString();
			// endDateStr = data.get("enddate").toString();
			btdateStr = data.get("btdate").toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		int result = gymOwnerService.addGymTrainer(fsname, lsname, email, mobileNo, exp, salary, startDateStr,
				btdateStr, gender);
		if (result == 1) {
			resStatus = 1;
			resultStr = "Trainer Added Successfully";
		}
		resultObj.put("resStatus", resStatus);
		resultObj.put("resultStr", resultStr);
		return resultObj.toString();
	}

	// ===========================
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/addExpenses", method = RequestMethod.POST, consumes = org.springframework.http.MediaType.APPLICATION_FORM_URLENCODED_VALUE, produces = org.springframework.http.MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public String addExpenses(HttpSession httpSession, @RequestParam String queryParam) {
		LOGGER.info("**Inside addExpenses***" + queryParam);
		JSONObject data = getJsonFromString(queryParam);
		JSONObject resultObj = new JSONObject();
		int ge_amount = 0;
		int resStatus = 0;
		String resultStr = "Gym Expenses Insertion Failed";
		int ownerId;
		try {
			ownerId = (int) httpSession.getAttribute("OwnerId");
		} catch (Exception e) {
			ownerId = 0;
		}

		if (ownerId == 0) {
			return getValidLogin(ownerId);
		}
		String expenses = "";
		try {
			expenses = data.get("expenses").toString();
			ge_amount = Integer.parseInt(data.get("amount").toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		int result = gymOwnerService.addExpenses(ownerId, expenses, ge_amount);
		if (result == 1) {
			resStatus = 1;
			resultStr = "Gym Expenses Added";
		}

		resultObj.put("resStatus", resStatus);
		resultObj.put("resultStr", resultStr);
		return resultObj.toString();
	}

	@RequestMapping(value = "/getAllGymTrainers", method = RequestMethod.POST, produces = org.springframework.http.MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public String getAllGymTrainers(@RequestParam String queryParam) {
		LOGGER.info("**inside getAllGymMembers** " + queryParam);
		JSONObject data = getJsonFromString(queryParam);
		int limit = 0;
		int offset = 0;
		try {
			limit = Integer.parseInt(data.get("limit").toString());
			offset = Integer.parseInt(data.get("offset").toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return gymOwnerService.getAllGymTrainers(limit, offset);
	}

	@RequestMapping(value = "/getAllGymTrainersByName", method = RequestMethod.POST, produces = org.springframework.http.MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public String getAllGymTrainersByName(@RequestParam String queryParam) {
		LOGGER.info("**inside getAllGymMembers** " + queryParam);
		JSONObject data = getJsonFromString(queryParam);
		int limit = 0;
		int offset = 0;
		String name = null;
		try {
			name = data.get("name").toString();
			limit = Integer.parseInt(data.get("limit").toString());
			offset = Integer.parseInt(data.get("offset").toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return gymOwnerService.getTrainerByName(name, limit, offset);
	}

	@RequestMapping(value = "/getAllGymMembers", method = RequestMethod.POST, consumes = org.springframework.http.MediaType.APPLICATION_FORM_URLENCODED_VALUE, produces = org.springframework.http.MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public String getAllGymMembers(@RequestParam String queryParam) {
		LOGGER.info("**inside getAllGymMembers** " + queryParam);
		JSONObject data = getJsonFromString(queryParam);
		int limit = 0;
		int offset = 0;
		try {
			limit = Integer.parseInt(data.get("limit").toString());
			offset = Integer.parseInt(data.get("offset").toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return gymOwnerService.getAllGymMembers(limit, offset);
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/addGymPackages", method = RequestMethod.POST, consumes = org.springframework.http.MediaType.APPLICATION_FORM_URLENCODED_VALUE, produces = org.springframework.http.MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public String addGymPackages(@RequestParam String queryParam) {
		LOGGER.info("**Inside addGymPackages***" + queryParam);
		JSONObject data = getJsonFromString(queryParam);
		JSONObject resultObj = new JSONObject();
		int resStatus = 0;
		String resultStr = "Gym Packages Insertion Failed";
		int month = 0;
		String amount = null;
		try {
			month = Integer.parseInt(data.get("month").toString());
			amount = data.get("amount").toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		int result = gymOwnerService.addGymPackages(month, amount);
		if (result == 1) {
			resStatus = 1;
			resultStr = "Gym Packages Added";
		}
		resultObj.put("resStatus", resStatus);
		resultObj.put("resultStr", resultStr);
		return resultObj.toString();
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/updateGymPackage", method = RequestMethod.POST, consumes = org.springframework.http.MediaType.APPLICATION_FORM_URLENCODED_VALUE, produces = org.springframework.http.MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public String updateGymPackage(@RequestParam String queryParam) {
		JSONObject data = getJsonFromString(queryParam);
		int gp_id = 0, gp_amount = 0;
		JSONObject resultObj = new JSONObject();
		int resStatus = 0;
		String resultStr = "Gym Package Amount Update Failed";

		try {
			gp_id = Integer.parseInt(data.get("gp_id").toString());
			gp_amount = Integer.parseInt(data.get("gp_amount").toString());
		} catch (Exception e) {
			e.printStackTrace();
		}

		int result = gymOwnerService.updateGymPackageAmount(gp_id, gp_amount);

		if (result == 1) {
			resStatus = 1;
			resultStr = "Gym Package Amount Updated Successfully";
		}
		resultObj.put("resStatus", resStatus);
		resultObj.put("resultStr", resultStr);
		return resultObj.toString();
	}

	@SuppressWarnings("unchecked")
	@PostMapping(value = "/verifyGymOwner", consumes = org.springframework.http.MediaType.APPLICATION_FORM_URLENCODED_VALUE, produces = org.springframework.http.MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public String verifyGymOwner(@RequestParam String queryParam) {
		LOGGER.info("**Inside verifyGymOwner***" + queryParam);
		int result = 0;
		int resStatus = 0;
		String resultStr = "Owner Not Verified:Invalid OTP Entered";
		JSONObject resultObj = new JSONObject();
		String fsname = null, lsname = null, email = null, password = null, otp = null;
		JSONObject data = getJsonFromString(queryParam);
		try {
			fsname = data.get("fsname").toString();
			lsname = data.get("lsname").toString();
			email = data.get("email").toString();
			password = data.get("password").toString();
			otp = data.get("otp").toString();
		} catch (Exception e) {
			e.printStackTrace();
		}

		String resultOTP = gymOwnerService.verifyGymOwner(fsname, lsname, email, password);
		System.out.println("result OTP " + resultOTP);
		if (resultOTP.equals(otp)) {
			result = 1;
		}

		if (result == 1) {
			resultStr = "Gym Owner Verified Successfully";
			resStatus = 1;
		}
		resultObj.put("resStatus", resStatus);
		resultObj.put("resultStr", resultStr);
		return resultObj.toString();

	}

	@SuppressWarnings("unchecked")
	@PostMapping(value = "/deleteGymOwner", consumes = org.springframework.http.MediaType.APPLICATION_FORM_URLENCODED_VALUE, produces = org.springframework.http.MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public String deleteGymOwner(@RequestParam String queryParam) {
		JSONObject data = getJsonFromString(queryParam);
		JSONObject resultObj = new JSONObject();
		String fsname = null, lsname = null, email = null, password = null, otp = null;
		int resStatus = 0;
		String resultStr = "Gym Owner Deletion Failed";
		try {
			fsname = data.get("fsname").toString();
			lsname = data.get("lsname").toString();
			email = data.get("email").toString();
			password = data.get("password").toString();
			otp = data.get("otp").toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		int result = gymOwnerService.deleteGymOwner(fsname, lsname, email, password);

		if (result == 1) {
			resultStr = "Gym Owner Deleted Successfully";
			resStatus = 1;
		}
		resultObj.put("resStatus", resStatus);
		resultObj.put("resultStr", resultStr);
		return resultObj.toString();

	}

	@GetMapping(value = "/getAllGymPackages", produces = org.springframework.http.MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public String getAllGymPackges() {
		return gymOwnerService.getAllGymPackges();
	}

	@PostMapping(value = "/getAGymPackagesAmount", produces = org.springframework.http.MediaType.APPLICATION_JSON_VALUE, consumes = org.springframework.http.MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	@ResponseBody
	public String getAGymPackagesAmount(@RequestParam String queryParam) {
		LOGGER.info("**Inside getAGymPackagesAmount***" + queryParam);
		JSONObject data = getJsonFromString(queryParam);
		JSONObject resultObj = new JSONObject();
		int gp_months = 0;
		try {
			gp_months = Integer.parseInt(data.get("gp_months").toString());
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		System.out.println("gp_month " + gp_months);
		int result = memberInfoServiceImpl.getPackageAmount(gp_months);
		resultObj.put("gp_amount", result);
		return resultObj.toString();
	}

	@SuppressWarnings("unchecked")
	@PostMapping(value = "/getTrainerById", consumes = org.springframework.http.MediaType.APPLICATION_FORM_URLENCODED_VALUE, produces = org.springframework.http.MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public String getTrainerById(@RequestParam String queryParam) {
		LOGGER.info("**Inside getTrainerById***" + queryParam);
		JSONObject data = getJsonFromString(queryParam);
		int gt_id = 0;
		try {
			gt_id = Integer.parseInt(data.get("gt_id").toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		String result = gymOwnerService.getTrainerById(gt_id);
		return result;
	}

	@SuppressWarnings("unchecked")
	@PostMapping(value = "/updateTrainerInfo", consumes = org.springframework.http.MediaType.APPLICATION_FORM_URLENCODED_VALUE, produces = org.springframework.http.MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public String updateTrainerInfo(@RequestParam String queryParam) {
		LOGGER.info("**Inside getTrainerById***" + queryParam);
		JSONObject data = getJsonFromString(queryParam);
		JSONObject resultObj = new JSONObject();
		int resStatus = 0;
		String resultStr = "Trainer Info Update Failed";
		int gt_id = 0;
		String fsname = null, lsname = null, email = null, mobileNo = null, exp = null, salary = null, btdateStr = null;
		try {
			fsname = data.get("fsname").toString();
			lsname = data.get("lsname").toString();
			email = data.get("email").toString();
			mobileNo = data.get("mobileNo").toString();
			salary = data.get("salary").toString();
			btdateStr = data.get("btdate").toString();
			gt_id = Integer.parseInt(data.get("gt_id").toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		int result = gymOwnerService.updateTrainer(fsname, lsname, email, mobileNo, salary, btdateStr, gt_id);
		if (result == 1) {
			resStatus = 1;
			resultStr = "Trainer Details Updated";
		}
		resultObj.put("resStatus", resStatus);
		resultObj.put("resultStr", resultStr);
		return resultObj.toString();

	}

	@SuppressWarnings("unchecked")
	@PostMapping(value = "/addTrainerAttendance", consumes = org.springframework.http.MediaType.APPLICATION_FORM_URLENCODED_VALUE, produces = org.springframework.http.MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public String addTrainerAttendance(@RequestParam String queryParam) {
		LOGGER.info("**Inside addTrainerAttendance***" + queryParam);
		JSONObject data = getJsonFromString(queryParam);
		JSONObject resultObj = new JSONObject();
		Integer gt_id = null;
		Integer NoOfDays = null;
		String startdate = null, enddate = null, leaveNote = null;

		int resStatus = 0;
		String resultStr = "Attendace Record Failed";
		try {
			startdate = data.get("startdate").toString();
			enddate = data.get("enddate").toString();
			leaveNote = data.get("leaveNote").toString();
			gt_id = Integer.parseInt(data.get("gt_id").toString());
			NoOfDays = Integer.parseInt(data.get("NoOfDays").toString());

		} catch (Exception e) {
			e.printStackTrace();
		}
		int result = gymOwnerService.addTrainerAttendance(gt_id, startdate, enddate, leaveNote, NoOfDays);
		
		if (result == 1) {
			resStatus = 1;
			resultStr = "Attendance Recorded Successfully";
		}
		resultObj.put("resStatus", resStatus);
		resultObj.put("resultStr", resultStr);
		return resultObj.toString();
	}

}
