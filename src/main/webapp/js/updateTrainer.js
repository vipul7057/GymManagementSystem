var salaryDB;
$(document).ready(function() {
	var gt_id = $.session.get("gt_id");
	var queryParam = { "gt_id": gt_id };
	var url = "getTrainerById";

	$.ajax({
		type: "POST",
		url: url,
		data: {
			queryParam: JSON.stringify(queryParam)
		},
		success: function(data) {
			console.log(data.rows);
			var records = data.rows;
			$('#fsname').val(records.gt_fsname);
			$('#lsname').val(records.gt_lsname);
			$('#email').val(records.gt_email);
			$('#mobileNo').val(records.gt_mobileNo);
			$('#btdate').val(records.gt_btdate);
			$('#salary').val(records.gt_salary);
			salaryDB = records.gt_salary;

		}
	});
})


function UpdateTrainer() {
	console.log("inside Update Trainer")
	var fsname = $("#fsname").val();
	var lsname = $("#lsname").val();
	var email = $("#email").val();
	var mobileNo = $("#mobileNo").val();
	var btdate = $("#btdate").val();
	var salary = $("#salary").val();
	var gt_id = $.session.get("gt_id");
	var queryParam = {
		"fsname": fsname, "lsname": lsname, "email": email, "btdate": btdate,
		"mobileNo": mobileNo, "salary": salary, "gt_id": gt_id
	};
	var url = "updateTrainerInfo";
	if (salaryDB > salary) {
		messageBox("Salary Cannot Be Reduced");
		return;
	}
	$.ajax({
		type: "POST",
		url: url,
		data: {
			queryParam: JSON.stringify(queryParam)
		},
		success: function(data) {
			console.log(data);
			if (data.resStatus == 1) {
				messageBox(data.resultStr);

			}


		}
	});

}