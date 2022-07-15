
package com.gym.S3Fitness.Service;

import java.time.LocalDate;
import java.util.List;

import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.gym.S3Fitness.controller.GymQwnerController;
import com.gym.S3Fitness.dao.AppDaoImpl;
import com.gym.S3Fitness.dto.GymOwner;
import com.gym.S3Fitness.dto.GymPackage;
import com.gym.S3Fitness.dto.GymTrainer;
import com.gym.S3Fitness.dto.MemberInfo;

@Service
public class GymOwnerServiceImpl implements GymOwnerService {

	private static final Logger LOGGER = org.slf4j.LoggerFactory.getLogger(GymOwnerServiceImpl.class);

	@Autowired
	private AppDaoImpl appDao;

	@Autowired
	JavaMailSender javaMailSender;

	@Override
	public int addGymOwner(String fsname, String lsname, String email, String password, String otp, String mobileNo) {
		LOGGER.info("** INSIDE addGymOwner**");
		return appDao.addGymOwner(fsname, lsname, email, password, otp, mobileNo);

	}

	@Override
	public GymOwner loginGymOwner(String email, String password) {
		LOGGER.info("** INSIDE loginGymOwner**");
		return appDao.loginGymOwner(email, password);
	}

	@Override
	public int addGymTrainer(String fsname, String lsname, String email, String mobileNo, String exp, String salary,
			String startDate, String btdate, String gender) {
		LOGGER.info("** INSIDE addGymTrainer**");
		return appDao.addGymTrainer(fsname, lsname, email, mobileNo, exp, salary, startDate, btdate, gender);
	}

	@Override
	public int addExpenses(int ownerId, String expenses, int amount) {
		LOGGER.info("** INSIDE addExpenses**");
		return appDao.addExpenses(ownerId, expenses, amount);
	}

	@SuppressWarnings("unchecked")
	@Override
	public String getAllGymTrainers(int limit, int offset) {
		JSONObject result = new JSONObject();
		JSONObject rows = new JSONObject();
		List<GymTrainer> gymTrainers = appDao.getAllGymTrainers(limit, offset);
		System.out.println(gymTrainers.toString());
		// i should start from offset
		int i = 0;
		for (GymTrainer gymTrainer : gymTrainers) {
			JSONObject obj = new JSONObject();
			obj.put("gt_id", gymTrainer.getGt_id());
			obj.put("gt_fullName", gymTrainer.getGt_fsname() + " " + gymTrainer.getGt_lsname());
			obj.put("gt_email", gymTrainer.getGt_email());
			obj.put("gt_mobileNo", gymTrainer.getGt_mobileNo());
			obj.put("gt_btdate", gymTrainer.getGt_btdate());
			obj.put("gt_exp", gymTrainer.getGt_exp());
			obj.put("gt_startdate", gymTrainer.getGt_startdate());
			obj.put("gt_enddate", gymTrainer.getGt_enddate());
			obj.put("gt_salary", gymTrainer.getGt_salary());
			obj.put("gt_gender", gymTrainer.getGt_gender());

			rows.put(i, obj);
			i++;
		}
		int finalRecordCount = appDao.getTotalCountOfTrainers();
		result.put("finalRecordCount", finalRecordCount);
		result.put("totalrecords", gymTrainers.size());
		result.put("rows", rows);
		System.out.println(result.toString());
		return result.toString();

	}

	@SuppressWarnings("unchecked")
	@Override
	public String getAllGymMembers(int limit, int offset) {
		JSONObject result = new JSONObject();
		JSONObject rows = new JSONObject();
		List<MemberInfo> memberInfoList = appDao.getAllGymMembers(limit, offset);
		System.out.println(memberInfoList.toString());
		// i should start with the value of offset
		int i = 0;
		for (MemberInfo memberInfo : memberInfoList) {
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
		int finalRecordCount = appDao.getTotalCountOfMembers();
		result.put("finalRecordCount", finalRecordCount);
		result.put("totalrecords", memberInfoList.size());
		result.put("rows", rows);
		System.out.println(result.toString());
		return result.toString();
	}

	@Override
	public int addGymPackages(int month, String amount) {
		return appDao.addGymPackages(month, amount);
	}

	@Override
	public int updateGymPackageAmount(int gp_id, int gp_amount) {
		return appDao.updateGymPackageAmount(gp_id, gp_amount);
	}

	@Override
	public String sendOtpMail(String email, String otp) {
		SimpleMailMessage message = new SimpleMailMessage();

		message.setFrom("vipulzope58@gmail.com");
		message.setTo(email);
		message.setSubject("OTP For Registration");
		message.setText("Please Use This OTP for Registration " + otp);

		javaMailSender.send(message);

		return "Mail sent successfully";
	}

	@Override
	public String verifyGymOwner(String fsname, String lsname, String email, String password) {
		return appDao.verifyGymOwner(fsname, lsname, email, password);
	}

	@Override
	public int deleteGymOwner(String fsname, String lsname, String email, String password) {
		return appDao.deleteGymOwner(fsname, lsname, email, password);
	}

	@SuppressWarnings("unchecked")
	@Override
	public String getAllGymPackges() {
		JSONObject result = new JSONObject();
		JSONObject rows = new JSONObject();
		List<GymPackage> gymPackageList = appDao.getAllGymPackages();
		System.out.println(gymPackageList.toString());
		// i should start with the value of offset
		int i = 0;
		for (GymPackage gympPackage : gymPackageList) {
			JSONObject obj = new JSONObject();
			obj.put("gp_id", gympPackage.getGp_id());
			obj.put("gp_month", gympPackage.getGp_month());
			obj.put("gp_amount", gympPackage.getGp_amount());

			rows.put(i, obj);
			i++;
		}
		result.put("totalrecords", gymPackageList.size());
		result.put("rows", rows);
		System.out.println(result.toString());
		return result.toString();
	}

	@SuppressWarnings("unchecked")
	@Override
	public String getTrainerById(int gt_id) {
		System.out.println("Inside SERVICE getTrainerByid");
		JSONObject result = new JSONObject();
		JSONObject rows = new JSONObject();

		// i should start from offset
		GymTrainer gymTrainer = appDao.getTrainerById(gt_id);
		rows.put("gt_id", gymTrainer.getGt_id());
		rows.put("gt_fsname", gymTrainer.getGt_fsname());
		rows.put("gt_lsname", gymTrainer.getGt_lsname());
		rows.put("gt_email", gymTrainer.getGt_email());
		rows.put("gt_mobileNo", gymTrainer.getGt_mobileNo());
		rows.put("gt_btdate", gymTrainer.getGt_btdate());
		rows.put("gt_exp", gymTrainer.getGt_exp());
		rows.put("gt_startdate", gymTrainer.getGt_startdate());
		rows.put("gt_enddate", gymTrainer.getGt_enddate());
		rows.put("gt_salary", gymTrainer.getGt_salary());
		rows.put("gt_gender", gymTrainer.getGt_gender());

		result.put("rows", rows);
		System.out.println(result.toString());
		return result.toString();
	}

	@Override
	public int updateTrainer(String fsname, String lsname, String email, String mobileNo, String salary, String btdate,
			int gt_id) {
		return appDao.updateTrainerInfo(fsname, lsname, email, mobileNo, salary, btdate, gt_id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public String getTrainerByName(String name, int limit, int offset) {
		JSONObject result = new JSONObject();
		JSONObject rows = new JSONObject();
		List<GymTrainer> gymTrainers = appDao.getTrainerByName(name, limit, offset);
		System.out.println(gymTrainers.toString());
		// i should start from offset
		int i = 0;
		for (GymTrainer gymTrainer : gymTrainers) {
			JSONObject obj = new JSONObject();
			obj.put("gt_id", gymTrainer.getGt_id());
			obj.put("gt_fullName", gymTrainer.getGt_fsname() + " " + gymTrainer.getGt_lsname());
			obj.put("gt_email", gymTrainer.getGt_email());
			obj.put("gt_mobileNo", gymTrainer.getGt_mobileNo());
			obj.put("gt_btdate", gymTrainer.getGt_btdate());
			obj.put("gt_exp", gymTrainer.getGt_exp());
			obj.put("gt_startdate", gymTrainer.getGt_startdate());
			obj.put("gt_enddate", gymTrainer.getGt_enddate());
			obj.put("gt_salary", gymTrainer.getGt_salary());
			obj.put("gt_gender", gymTrainer.getGt_gender());

			rows.put(i, obj);
			i++;
		}
		int finalRecordCount = appDao.getTotalCountOfTrainers();
		result.put("finalRecordCount", finalRecordCount);
		result.put("totalrecords", gymTrainers.size());
		result.put("rows", rows);
		System.out.println(result.toString());
		return result.toString();
	}

	@Override
	public void getAllMembers() {
		//0 10 0 1/1 * ? *
		List<MemberInfo> members = appDao.getAllMembers();
		System.out.println(members.toString());
	}

	@Override
	public int addTrainerAttendance(int gt_id, String startdate, String enddate, String leaveNote, int NoOfDays) {
		return appDao.addTrainerAttendance(gt_id, startdate, enddate, leaveNote, NoOfDays);
	}

}
