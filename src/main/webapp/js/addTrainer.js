function AddTrainer() {
	var fsname = $("#fsname").val();
	var lsname = $("#lsname").val();
	var email = $("#email").val();

	var mobileNo = $("#mobileNo").val();
	var btdate = $("#btdate").val();
	var startdate = $("#startdate").val();
	var gender = $('input[name="gender"]:checked').val();
	var exp = $("#exp").val();
	var salary = $("#salary").val();
	console.log(fsname + " " + lsname + " " + email + " " + btdate + " " + mobileNo + " " + startdate + " " + gender + " " + exp + " " + salary);

	if (fsname == "" || lsname == "" || email == "" || btdate == "" || mobileNo == "" || startdate == ""
		|| gender == "" || exp == "" || salary == "") {
		alert("Please Enter All Required Data");
		return;
	}
	var queryParam = {
		"fsname": fsname, "lsname": lsname, "email": email, "btdate": btdate,
		"mobileNo": mobileNo, "exp": exp, "salary": salary, "startdate": startdate,"gender":gender
	};
	console.log(queryParam);

	var contextPath = "/";
	var url = "addGymTrainer";
	$.ajax({
		type: "POST",
		url: contextPath + url,
		dataType: "json",
		async: true,
		data: {
			queryParam: JSON.stringify(queryParam)
		},
		success: function(data) {
			console.log(data);
			if (data.resStatus == 1) {
				messageBox(data.resultStr);
				$('#addTrainerForm')[0].reset();
			}
			else {
				messageBox(data.resultStr);
			}
		}
	});
}