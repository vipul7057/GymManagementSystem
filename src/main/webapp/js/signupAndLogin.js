$(document).ready(function() {
	$("#registerOwner").click(function() {
		var fsname = $("#fsname").val();
		var lsname = $("#lsname").val();
		var email = $("#email").val();
		var password = $("#pass").val();
		var mobileNo = $("#mobileNo").val();
		var otp = (Math.floor(Math.random() * 10000) + 10000).toString().substring(1);
		console.log(fsname + " " + lsname + " " + email + " " + password + " " + mobileNo);


		if (fsname == "" || lsname == "" || email == "" || password == "" || mobileNo == "") {
			$('#OTPModal').modal("hide");
			alert("Please Enter All Required Data");
			return;
		}
		$('#OTPModal').modal('show')
		var queryParam = {
			"fsname": fsname, "lsname": lsname, "email": email, "password": password, "otp": otp,
			"mobileNo": mobileNo
		};
		var url = "addGymOwner";
		ajaxCall(queryParam, url);

	});

});

function loginOwner() {
	var email = $("#email").val();
	var password = $("#pass").val();
	var queryParam = { "email": email, "password": password};
	console.log(queryParam);
	var url = "loginGymOwner";
	var response = {};
	var contextPath = "/";
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
			if(data.resStatus == 1)
			{
				$("#loginBtn").click();
			}else
			{
				alert("Invalid Email and Password");
			}
			

		}
	});

}

function verifyOwner() {
	var fsname = $("#fsname").val();
	var lsname = $("#lsname").val();
	var email = $("#email").val();
	var password = $("#pass").val();
	var otp = $("#otp").val();
	var queryParam = { "fsname": fsname, "lsname": lsname, "email": email, "password": password, "otp": otp };
	console.log(queryParam);
	var url = "verifyGymOwner";
	var response = {};
	var contextPath = "/";
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

				$("#closeXbtn").click();
				$('#register-form')[0].reset();
				$('#OTPModal').modal('hide')

			}
			alert(data.resultStr);

		}
	});
}

function closeOtpModal() {
	var fsname = $("#fsname").val();
	var lsname = $("#lsname").val();
	var email = $("#email").val();
	var password = $("#pass").val();
	var url = "deleteGymOwner";
	var queryParam = { "fsname": fsname, "lsname": lsname, "email": email, "password": password, "otp": otp };
	$('#register-form')[0].reset();
	$('#OTPModal').modal('hide');
	ajaxCall(queryParam, url);

}
function ajaxCall(queryParam, url) {
	console.log(queryParam);
	var response;
	var contextPath = "/";
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

		}
	});

	return response;
}