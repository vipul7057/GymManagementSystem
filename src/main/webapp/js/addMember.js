$(document).ready(function() {
	var url = "getAllGymPackages";

	$.ajax({
		type: "GET",
		url: url,
		success: function(data) {
			var totalrecords = data.totalrecords;
			var rows = data.rows;
			var str = "";
			console.log(rows);
			for (var i = 0; i < totalrecords; i++) {
				str += "<option value=" + rows[i].gp_month + ">" + rows[i].gp_month + "</option>";

			}
			$("#gp_month").append(str);

		}
	});

});

function AddMember() {
	var fsname = $("#fsname").val();
	var lsname = $("#lsname").val();
	var email = $("#email").val();
	var startdate = $("#startdate").val();
	var mobileNo = $("#mobileNo").val();
	var btdate = $("#btdate").val();
	var gp_months = $("#gp_month").val();
	var paid_amount = $("#paid_amount").val();
	var discount = $("#discount").val();
	var paymentMode = $('#paymentMode').val();
	console.log(fsname + " " + lsname + " " + email + " " + btdate + " " + mobileNo + " " + gp_months + " " + paid_amount
	+" "+paymentMode);

	if (fsname == "" || lsname == "" || email == "" || btdate == "" || mobileNo == "" || gp_months == "0") {
		alert("Please Enter All Required Data");
		return;
	}
	var queryParam = {
		"fsname": fsname, "lsname": lsname, "email": email, "btdate": btdate,
		"mobileNo": mobileNo, "gp_months": gp_months, "paid_amount": paid_amount, "discount": discount,"startdate":startdate
		,"payment":paymentMode
	};
	console.log(queryParam);

	var contextPath = "/";
	var url = "addMemberInfo";
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
				$('#addMemberForm')[0].reset();
				$('#gp_amount').text("");
				$('#amountDiv').hide();
			}
			else {
				messageBox(data.resultStr);
			}
		}
	});

}



$(document).on('change', '#gp_month', function() {

	var value = $(this).val();
	if (value == 0) {
		$('#gp_amount').text("");
		$('#amountDiv').hide();

	}
	var url = "getAGymPackagesAmount";
	var queryParam = { "gp_months": value };
	$.ajax({
		type: "POST",
		dataType: "json",
		async: true,
		url: url,
		data: {
			queryParam: JSON.stringify(queryParam)
		},

		success: function(data) {
			console.log(data);
			$('#gp_amount').text(data.gp_amount);
			$('#amountDiv').show();
		}
	});
});