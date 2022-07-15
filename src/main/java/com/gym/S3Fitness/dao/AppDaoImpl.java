package com.gym.S3Fitness.dao;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.gym.S3Fitness.Service.GymOwnerServiceImpl;
import com.gym.S3Fitness.dto.GymOwner;
import com.gym.S3Fitness.dto.GymPackage;
import com.gym.S3Fitness.dto.GymPaymentlogs;
import com.gym.S3Fitness.dto.GymTrainer;
import com.gym.S3Fitness.dto.MemberInfo;
import com.gym.S3Fitness.sql.AppSQLImpl;

import aj.org.objectweb.asm.Type;

import java.sql.Types;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public class AppDaoImpl implements AppDao {

	private static final Logger LOGGER = org.slf4j.LoggerFactory.getLogger(AppDaoImpl.class);

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Autowired
	private AppSQLImpl appSql;

	@Override
	public int addGymOwner(String fsname, String lsname, String email, String password, String otp, String mobileNo) {
		LOGGER.info("** INSIDE addGymOwner**");

		final String sql = appSql.getSqlQuery("insert.gymowner");
		final Object[] args = new Object[] { fsname, lsname, email, password, otp, mobileNo };
		final int[] argsTypes = new int[] { Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR,
				Types.VARCHAR };
		return jdbcTemplate.update(sql, args, argsTypes);

	}

	@Override
	public GymOwner loginGymOwner(String email, String password) {
		LOGGER.info("** INSIDE loginGymOwner**");
		final String sql = appSql.getSqlQuery("login.gymowner");
		final Object[] args = new Object[] { email, password };
		final int[] argsTypes = new int[] { Types.VARCHAR, Types.VARCHAR };
		GymOwner result = new GymOwner();
		try {
			result = jdbcTemplate.queryForObject(sql, args, argsTypes, new BeanPropertyRowMapper<>(GymOwner.class));

		} catch (Exception e) {

		}
		return result;
	}

	@Override
	public int addGymTrainer(String fsname, String lsname, String email, String mobileNo, String exp, String salary,
			String startDate, String btdate, String gender) {
		LOGGER.info("** INSIDE addGymTrainer**");
		final String sql = appSql.getSqlQuery("insert.trainer");
		final Object[] args = new Object[] { fsname, lsname, email, mobileNo, exp, salary, startDate, btdate, gender };
		final int[] argsTypes = new int[] { Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR,
				Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR };
		return jdbcTemplate.update(sql, args, argsTypes);
	}

	@Override
	public int addExpenses(int ownerId, String expenses, int amount) {
		LOGGER.info("** INSIDE addExpenses**");
		final String sql = appSql.getSqlQuery("insert.expenses");
		final Object[] args = new Object[] { expenses, ownerId, amount };
		final int[] argsTypes = new int[] { Types.VARCHAR, Types.INTEGER, Types.INTEGER };
		return jdbcTemplate.update(sql, args, argsTypes);

	}

	@Override
	public List<GymTrainer> getAllGymTrainers(int limit, int offset) {
		LOGGER.info("** INSIDE getAllGymTrainers**");
		final String sql = appSql.getSqlQuery("fetch.allGymTrainers");
		final Object[] args = new Object[] { limit, offset };
		final int[] argsTypes = new int[] { Types.INTEGER, Types.INTEGER };
		return jdbcTemplate.query(sql, args, argsTypes, new BeanPropertyRowMapper<>(GymTrainer.class));
	}

	@Override
	public List<MemberInfo> getAllGymMembers(int limit, int offset) {
		LOGGER.info("** INSIDE getAllGymMembers**" + limit + " " + offset);
		final String sql = appSql.getSqlQuery("fetch.allGymMembers");
		final Object[] args = new Object[] { limit, offset };
		final int[] argsTypes = new int[] { Types.INTEGER, Types.INTEGER };
		return jdbcTemplate.query(sql, args, argsTypes, new BeanPropertyRowMapper<>(MemberInfo.class));
	}

	@Override
	public int addGymPackages(int month, String amount) {
		LOGGER.info("** INSIDE addGymPackages**");
		final String sql = appSql.getSqlQuery("insert.packages");
		final Object[] args = new Object[] { month, amount };
		final int[] argsTypes = new int[] { Types.INTEGER, Types.VARCHAR };
		return jdbcTemplate.update(sql, args, argsTypes);

	}

	@Override
	public int getPackageAmount(int gp_month) {

		LOGGER.info("** INSIDE getPackageAmount**");
		final String sql = appSql.getSqlQuery("fetch.package.amount");
		final Object[] args = new Object[] { gp_month };
		final int[] argsTypes = new int[] { Types.INTEGER };
		return jdbcTemplate.queryForObject(sql, args, argsTypes, Integer.class);
	}

	@Override
	public int addMemberInfo(String fsname, String lsname, String email, String mobileNo, String amount,
			String finalAmount, int remainingDays, String startDate, String enddate, String btdate, int gp_month,
			String mi_paidamount, String mi_remainingamount,String mi_status) {
		LOGGER.info("** INSIDE addMemberInfo**");
		final String sql = appSql.getSqlQuery("insert.memberinfo");
		final Object[] args = new Object[] { fsname, lsname, email, mobileNo, amount, finalAmount, remainingDays,
				startDate, enddate, btdate, gp_month, mi_paidamount, mi_remainingamount,mi_status };
		final int[] argsTypes = new int[] { Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR,
				Types.VARCHAR, Types.INTEGER, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.INTEGER, Types.VARCHAR,
				Types.VARCHAR ,Types.VARCHAR };
		return jdbcTemplate.update(sql, args, argsTypes);
	}

	@Override
	public MemberInfo getMemberById(int mi_id) {
		final String sql = appSql.getSqlQuery("fetch.member.byid");
		final Object[] args = new Object[] { mi_id };
		final int[] argsTypes = new int[] { Types.INTEGER };
		return jdbcTemplate.queryForObject(sql, args, argsTypes, new BeanPropertyRowMapper<>(MemberInfo.class));
	}

	@Override
	public int updateMemberShipInfo(int gp_monthVal, String enddate, String amountStr, String finalAmountStr,
			int remainingDays, int mi_id, String mi_remaingingamountstr) {
		LOGGER.info("** INSIDE updateMemberShipInfo**" + mi_remaingingamountstr);

		final String sql = appSql.getSqlQuery("update.membership.info");
		final Object[] args = new Object[] { gp_monthVal, enddate, amountStr, finalAmountStr, remainingDays,
				mi_remaingingamountstr, mi_id };
		final int[] argsTypes = new int[] { Types.INTEGER, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.INTEGER,
				Types.VARCHAR, Types.INTEGER };
		return jdbcTemplate.update(sql, args, argsTypes);
	}

	@Override
	public int updateMemberPayment(int mi_id, String mi_paidamount, String mi_remainingamount) {
		LOGGER.info("** INSIDE updateMemberPayment**");

		final String sql = appSql.getSqlQuery("update.payment.info");
		final Object[] args = new Object[] { mi_paidamount, mi_remainingamount, mi_id };
		final int[] argsTypes = new int[] { Types.VARCHAR, Types.VARCHAR, Types.INTEGER };
		return jdbcTemplate.update(sql, args, argsTypes);

	}

	@Override
	public int addGymPaymentLogs(int mi_id, String gpl_amountpaid) {
		LOGGER.info("** INSIDE addGymPaymentLogs**" + mi_id + " " + gpl_amountpaid);

		final String sql = appSql.getSqlQuery("insert.payment.logs");
		final Object[] args = new Object[] { mi_id, gpl_amountpaid };
		final int[] argsTypes = new int[] { Types.INTEGER, Types.VARCHAR };
		return jdbcTemplate.update(sql, args, argsTypes);
	}

	@Override
	public int freezeMemberShip(int mi_id, int mi_remainingdays, String enddate) {
		LOGGER.info("** INSIDE freezeMemberShip**");

		final String sql = appSql.getSqlQuery("freeze.membership");
		final Object[] args = new Object[] { enddate, mi_remainingdays, mi_id };
		final int[] argsTypes = new int[] { Types.VARCHAR, Types.INTEGER, Types.INTEGER };
		return jdbcTemplate.update(sql, args, argsTypes);
	}

	@Override
	public int updateGymPackageAmount(int gp_id, int gp_amount) {
		LOGGER.info("** INSIDE updateGymPackageAmount**");

		final String sql = appSql.getSqlQuery("update.gym.package.amount");
		final Object[] args = new Object[] { gp_amount, gp_id };
		final int[] argsTypes = new int[] { Types.INTEGER, Types.INTEGER };
		return jdbcTemplate.update(sql, args, argsTypes);
	}

	@Override
	public String verifyGymOwner(String fsname, String lsname, String email, String password) {
		LOGGER.info("** INSIDE verifyGymOwner**");
		final String sql = appSql.getSqlQuery("fetch.otp.for.gym.owner");
		final Object[] args = new Object[] { fsname, lsname, email, password };
		final int[] argsTypes = new int[] { Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR };
		String result = "";
		try {
			result = jdbcTemplate.queryForObject(sql, args, argsTypes, String.class);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			result = "";
		}
		return result;
	}

	@Override
	public int deleteGymOwner(String fsname, String lsname, String email, String password) {
		LOGGER.info("** INSIDE deleteGymOwner**");

		final String sql = appSql.getSqlQuery("delete.gym.owner");
		final Object[] args = new Object[] { fsname, lsname, email, password };
		final int[] argsTypes = new int[] { Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR };
		return jdbcTemplate.update(sql, args, argsTypes);
	}

	@Override
	public List<GymPackage> getAllGymPackages() {
		LOGGER.info("** INSIDE getAllGymPackages**");
		final String sql = "SELECT * FROM gym_package";
		return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(GymPackage.class));
	}

	@Override
	public List<MemberInfo> getMemberByName(String name, int limit, int offset) {
		LOGGER.info("** INSIDE getMemberByName**" + limit + " " + offset);
		final String sql = "SELECT * FROM member_info WHERE mi_fsname LIKE '%" + name + "%' OR mi_lsname LIKE  '%"
				+ name + "%' ORDER BY mi_id LIMIT " + limit + " OFFSET " + offset + "";
		// final Object[] args = new Object[] { name, name, limit, offset };
		// final int[] argsTypes = new int[] { Types.VARCHAR, Types.VARCHAR,
		// Types.INTEGER, Types.INTEGER };
		return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(MemberInfo.class));
	}

	@Override
	public List<GymTrainer> getTrainerByName(String name, int limit, int offset) {
		LOGGER.info("** INSIDE getTrainerByName**" + limit + " " + offset);
		final String sql = "SELECT * FROM gym_trainer WHERE gt_fsname LIKE '%" + name + "%' OR gt_lsname LIKE  '%"
				+ name + "%' ORDER BY gt_id LIMIT " + limit + " OFFSET " + offset + "";
		// final Object[] args = new Object[] { name, name, limit, offset };
		// final int[] argsTypes = new int[] { Types.VARCHAR, Types.VARCHAR,
		// Types.INTEGER, Types.INTEGER };
		return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(GymTrainer.class));
	}

	@Override
	public int getTotalCountOfMembers() {
		LOGGER.info("** INSIDE getTotalCountOfMembers**");
		final String sql = "SELECT COUNT(*) FROM member_info";
		return jdbcTemplate.queryForObject(sql, Integer.class);
	}

	@Override
	public int getTotalCountOfTrainers() {
		LOGGER.info("** INSIDE getTotalCountOfTrainer**");
		final String sql = "SELECT COUNT(*) FROM gym_trainer";
		return jdbcTemplate.queryForObject(sql, Integer.class);
	}

	@Override
	public int getTotalCountOfMembersByName(String name) {
		LOGGER.info("** INSIDE getTotalCountOfMembersByName**");
		final String sql = "SELECT COUNT(*) FROM member_info WHERE mi_fsname LIKE '" + name + "' OR mi_lsname LIKE '"
				+ name + "'";
		return jdbcTemplate.queryForObject(sql, Integer.class);
	}

	@Override
	public int udpateMemberInfo(String fsname, String lsname, String email, String btDateStr, String mobileNo,
			int mi_id) {
		LOGGER.info("** INSIDE udpateMemberInfo**");
		final String sql = appSql.getSqlQuery("update.memberinfo");
		final Object[] args = new Object[] { fsname, lsname, email, btDateStr, mobileNo, mi_id };
		final int[] argsTypes = new int[] { Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR,
				Types.INTEGER };
		return jdbcTemplate.update(sql, args, argsTypes);
	}

	@Override
	public int insertgymPaymentLogs(String paid_amount, String paymentMode, int mi_id) {
		LOGGER.info("**inside insertgymPaymentLogs **" + paid_amount + " " + mi_id);
		final String sql = appSql.getSqlQuery("insert.payment.logs");
		final Object[] args = new Object[] { paid_amount, paymentMode, mi_id };
		final int[] argsTypes = new int[] { Types.VARCHAR, Types.VARCHAR, Types.INTEGER };
		return jdbcTemplate.update(sql, args, argsTypes);
	}

	@Override
	public int getMemberInfoIdByInfo(String mi_fsname, String mi_lsname, String mi_email, String mi_mobile_no,
			String btdate) {
		LOGGER.info("**inside getMemberInfoIdByInfo ");
		LOGGER.info("fsname " + mi_fsname + " lsname " + mi_lsname + " email " + mi_email + " mobile " + mi_mobile_no
				+ "btdate " + btdate);
		String sql = appSql.getSqlQuery("fetch.miid.from.info");
		final Object[] args = new Object[] { mi_fsname, mi_lsname, mi_email, mi_mobile_no, btdate };
		final int[] argsTypes = new int[] { Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR };
		return jdbcTemplate.queryForObject(sql, args, argsTypes, Integer.class);
	}

	@Override
	public List<GymPaymentlogs> getPaymentBreakdown(int mi_id) {
		LOGGER.info("** INSIDE getPaymentBreakdown**");
		final String sql = appSql.getSqlQuery("fetch.payment.logs");
		final Object[] args = new Object[] { mi_id };
		final int[] argsTypes = new int[] { Types.INTEGER };
		return jdbcTemplate.query(sql, args, argsTypes, new BeanPropertyRowMapper<>(GymPaymentlogs.class));
	}

	@Override
	public int extendMembershipIfRemainingDaysNot0(String endDate, String totalamount, String totalFinalAmount,
			String paid_amount, int remainingDays, int gp_month, String remainingAmount, int mi_id) {
		LOGGER.info("** INSIDE extendMembershipIfRemainingDaysNot0 **");
		final String sql = appSql.getSqlQuery("extend.membership.if.days.not.0");
		final Object[] args = new Object[] { endDate, totalamount, totalFinalAmount, paid_amount, remainingDays,
				gp_month, remainingAmount, mi_id };
		final int[] argsTypes = new int[] { Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.INTEGER,
				Types.INTEGER, Types.VARCHAR, Types.INTEGER };
		return jdbcTemplate.update(sql, args, argsTypes);
	}

	@Override
	public int extendMembershipIfRemainingDays0(String startDate, String endDate, String totalamount,
			String totalFinalAmount, String paid_amount, int remainingDays, int gp_month, String remainingAmount,
			int mi_id) {
		LOGGER.info("** INSIDE extendMembershipIfRemainingDays0 **");
		final String sql = appSql.getSqlQuery("extend.membership.if.days.0");
		final Object[] args = new Object[] { startDate, endDate, totalamount, totalFinalAmount, paid_amount,
				remainingDays, gp_month, remainingAmount, mi_id };
		final int[] argsTypes = new int[] { Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR,
				Types.INTEGER, Types.INTEGER, Types.VARCHAR, Types.INTEGER };
		return jdbcTemplate.update(sql, args, argsTypes);
	}

	@Override
	public GymTrainer getTrainerById(int gt_id) {
		LOGGER.info("** INSIDE getTrainerById**");
		final String sql = "SELECT * FROM gym_trainer WHERE gt_id = " + gt_id;

		return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(GymTrainer.class));
	}

	@Override
	public int updateTrainerInfo(String fsname, String lsname, String email, String mobileNo, String salary,
			String btdate, int gt_id) {
		LOGGER.info("** INSIDE getTrainerById**");
		String sql = appSql.getSqlQuery("update.trainer");
		final Object[] args = new Object[] { fsname, lsname, email, mobileNo, salary, btdate, gt_id };
		final int[] argsTypes = new int[] { Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR,
				Types.VARCHAR, Types.INTEGER };
		return jdbcTemplate.update(sql, args, argsTypes);
	}

	@Override
	public List<MemberInfo> getAllMembers() {
		String sql = "SELECT * FROM member_info";
		return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(MemberInfo.class));
	}

	@Override
	public int addTrainerAttendance(int gt_id, String startdate, String enddate, String leaveNote, int NoOfDays) {
		LOGGER.info("**inside addTrainerAttendance **");
		final String sql = appSql.getSqlQuery("insert.trainer.attendace");
		final Object[] args = new Object[] { gt_id, startdate, enddate, leaveNote, NoOfDays };
		final int[] argsTypes = new int[] { Types.INTEGER, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.INTEGER };
		return jdbcTemplate.update(sql, args, argsTypes);
	}

}
