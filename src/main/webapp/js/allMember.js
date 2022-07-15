var limit = 8;
var offset = 0;
var totalFinalRecord;
var record = "";
$(document).ready(function() {
	$.session.set("mi_id", "");
	$('#previousbtn').prop('disabled', true);
	showAllMembers(limit, offset);


});

function showAllMembers(limit, offset) {
	$("#memberTbody").empty();
	var queryParam = { "limit": limit, "offset": offset };
	var url = "getAllGymMembers";
	var text = "";
	$.ajax({
		type: "POST",
		url: url,
		data: {
			queryParam: JSON.stringify(queryParam)
		},
		success: function(data) {
			console.log(data);
			totalFinalRecord = data.finalRecordCount;
			makeTable(data);
		}
	});



}
function clearFilter() {
	$('#searchForm')[0].reset();
	limit = 8;
	offset = 0;
	$('#previousbtn').prop('disabled', false);
	$('#nextbtn').prop('disabled', false);
	showAllMembers(limit, offset);
}
function makeTable(data) {
	var text = "";
	$("#memberTbody").empty();
	for (var i = 0; i < data.totalrecords; i++) {
		text += "<tr><td hidden><div>" + data.rows[i].mi_id + "</div></td>";
		text += "<td><div>" + data.rows[i].mi_fullName + "</div></td>";
		text += "<td><div>" + data.rows[i].mi_mobileNo + "</div></td>";
		text += "<td><div id=\"emailDiv\">" + data.rows[i].mi_email + "</div></td>";
		text += "<td><div>" + data.rows[i].mi_btdate + "</div></td>";
		text += "<td><div>" + data.rows[i].mi_startdate + "</div></td>";
		text += "<td><div>" + data.rows[i].mi_enddate + "</div></td>";
		text += "<td><div class=\"money\">" + data.rows[i].mi_finalAmount + "</div></td>";
		text += "<td><div class=\"money\">" + data.rows[i].mi_amountpaid + "</div></td>";
		text += "<td><div class=\"money\">" + data.rows[i].mi_remainingamount + "</div></td>";
		text += "<td><div>" + data.rows[i].mi_remainingDays + "</div></td>";


		text += "<td>"
			+ "<div style=\"display:flex;justify-content:center\" class=\"dropdown  show\"><a class=\"btn btn-sm btn-secondary dropdown-toggle\" href=\"#\" role=\"button\" id=\"dropdownMenuLink\" data-toggle=\"dropdown\" aria-haspopup=\"true\" aria-expanded=\"false\">"
			+ "<i class=\"fas fa-dumbbell\"></i>"
			+ " </a>"
			+ "<div class=\"dropdown-menu\" aria-labelledby=\"dropdownMenuLink\">"
			+ "<a class=\"dropdown-item\" href=\"#\"><small><button class=\"btn btn-outline-dark btn-block btn-sm\" onclick=\"editMemberDetails(" + data.rows[i].mi_id + ")\">Edit</button></small></a>"
			+ "<a class=\"dropdown-item\" href=\"#\"><small><button class=\"btn btn-outline-dark btn-block btn-sm\" onclick=\"extendMembership(" + data.rows[i].mi_id + ")\" data-toggle=\"modal\" data-target=\"#extendMembershipModal\">Extend</button></small></a>"
			+ "<a class=\"dropdown-item\" href=\"#\"><small><button class=\"btn btn-outline-dark btn-block btn-sm\" onclick=\"setmiidfreeze(" + data.rows[i].mi_id + ")\" data-toggle=\"modal\" data-target=\"#freezeModal\">Freeze</button></small></a>"
			+ "<a class=\"dropdown-item\" href=\"#\"><small><button class=\"btn btn-outline-dark btn-block btn-sm\" onclick=\"setmiidpayment(" + data.rows[i].mi_id + ")\"data-toggle=\"modal\" data-target=\"#paymentModal\">Payments</button></small></a>"
			+ "</div>"
			+ "</div></td>";


	}

	$("#memberTbody").append(text);
}

function editMemberDetails(mi_id) {

	$.session.set("mi_id", mi_id);
	window.location.href = './updateMember.jsp';
}
function getMemberByName() {
	var name = $('#searchByName').val();
	limit = 8;
	offset = 0;
	var queryParam = { "mi_fullName": name, "limit": limit, "offset": offset };
	console.log(queryParam)
	var url = "getMemberByName";
	$('#previousbtn').prop('disabled', true);
	$('#nextbtn').prop('disabled', true);
	$.ajax({
		type: "POST",
		url: url,
		dataType: "json",
		async: true,
		data: {
			queryParam: JSON.stringify(queryParam)
		},
		success: function(data) {
			console.log(data);
			if (data.totalrecords == 0) {
				alert("No Member Found For Given Name");
				clearFilter();
			}
			makeTable(data);

		}
	});
}


function nextPage() {
	offset += 8;
	console.log("limit in next", limit + " offset in next", offset);
	$('#previousbtn').prop('disabled', false);
	console.log(totalFinalRecord % offset)
	showAllMembers(limit, offset);
	if ((totalFinalRecord - offset) <= limit) {
		$('#nextbtn').prop('disabled', true);
		return;
	}


}
function previousPage() {
	offset -= 8;
	console.log("limit ", limit + " offset ", offset);
	$('#nextbtn').prop('disabled', false);
	if (offset < 0) {

		offset = 0;
		$('#previousbtn').prop('disabled', true);
		return;
	}
	showAllMembers(limit, offset);
}


function freezeMembership() {

	var NoOfDays = $('#freezeInput').val();
	var url = "freezeMemberShip";
	var mi_id = $('#miidforfreeze').val();
	var queryParam = { "mi_id": mi_id, "NoOfDays": NoOfDays };
	if (NoOfDays == "") {
		messageBox("Enter No Of Days");
		return;
	}
	console.log(queryParam);
	$.ajax({
		type: "POST",
		url: url,
		dataType: "json",
		async: true,
		data: {
			queryParam: JSON.stringify(queryParam)
		},
		success: function(data) {
			console.log(data);
			messageBox(data.resultStr);
			$('#freezeMemberForm')[0].reset();
			$('#freezeModal').modal('hide');
			limit = 8;
			offset = 0;
			showAllMembers(limit, offset);
		}
	});
}
function setmiidfreeze(mi_id) {
	console.log(mi_id);
	$('#miidforfreeze').val(mi_id);
	console.log("input tag val", $('#miidforfreeze').val());

}


function managePayment() {
	var mi_id = $('miidforpayment').val();
	var paid_amount = $('paymentInput').val();
	var url = "updateMemberPayment";
	var queryParam = { "mi_id": mi_id, "paidamount": paid_amount };
	$.ajax({
		type: "POST",
		url: url,
		dataType: "json",
		async: true,
		data: {
			queryParam: JSON.stringify(queryParam)
		},
		success: function(data) {
			console.log(data);

		}
	});


}

function setmiidpayment(mi_id) {
	console.log(mi_id);
	$('#miidforbreakdown').val(mi_id);
	$('#miidforpayment').val(mi_id);
	var url = "getMemberById";
	var queryParam = { "mi_id": mi_id };
	$.ajax({
		type: "POST",
		url: url,
		dataType: "json",
		async: true,
		data: {
			queryParam: JSON.stringify(queryParam)
		},
		success: function(data) {
			console.log(data);
			$('#totalamount').empty();
			$('#paidamount').empty();
			$('#remainingamount').empty();

			$('#totalamount').append(data.rows[0].mi_finalAmount);
			$('#paidamount').append(data.rows[0].mi_amountpaid);
			$('#remainingamount').append(data.rows[0].mi_remainingamount);

		}
	});


}


function managePayment() {
	var mi_id = $('#miidforpayment').val();
	var paidamount = $('#paymentInput').val();

	$('#miidforbreakdown').val(mi_id);
	var url = "updateMemberPayment";
	var queryParam = { "mi_id": mi_id, "paidamount": paidamount };
	$.ajax({
		type: "POST",
		url: url,
		dataType: "json",
		async: true,
		data: {
			queryParam: JSON.stringify(queryParam)
		},
		success: function(data) {
			console.log(data);
			messageBox(data.resultStr);

			$('#paymentForm')[0].reset();
			$('#paymentModal').modal('hide');
			limit = 8;
			offset = 0;
			showAllMembers(limit, offset);

		}
	});

}
function showTransactionModal() {

	var mi_id = $('#miidforbreakdown').val();
	var url = "getPaymentBreakdown";
	var queryParam = { "mi_id": mi_id };
	console.log("Before AJAX");
	$.ajax({
		type: "POST",
		url: url,
		dataType: "json",
		async: true,
		data: {
			queryParam: JSON.stringify(queryParam)
		},
		success: function(data) {
			console.log("inside success");
			console.log(data);


		}
	});
	console.log("After AJAX");
	$('#paymentModal').modal('hide');
	$('#paymentBreakdownModal').modal('show');
}
function closeBreakDown() {
	$('#paymentModal').modal('show');
	$('#paymentBreakdownModal').modal('hide');
}


function extendMembership(mi_id) {
	$('#miidforextend').val(mi_id);
	$('#amountDiv').hide();
	var url = "getAllGymPackages";

	$.ajax({
		type: "GET",
		url: url,
		success: function(data) {
			$("#gp_month").empty();
			var totalrecords = data.totalrecords;
			var rows = data.rows;
			var str = "";
			console.log(rows);
			str += "<option value=\"0\">None</option>";
			for (var i = 0; i < totalrecords; i++) {

				str += "<option value=" + rows[i].gp_month + ">" + rows[i].gp_month + "</option>";

			}
			$("#gp_month").append(str);

		}
	});


	var url = "getMemberById";
	var queryParam = { "mi_id": mi_id };
	$.ajax({
		type: "POST",
		url: url,
		dataType: "json",
		async: true,
		data: {
			queryParam: JSON.stringify(queryParam)
		},
		success: function(data) {
			console.log(data);
			$('#startdate').empty();
			$('#enddate').empty();
			$('#remainingdays').empty();

			$('#startdate').append(data.rows[0].mi_startdate);
			$('#enddate').append(data.rows[0].mi_enddate);
			$('#remainingdays').append(data.rows[0].mi_remainingDays);


			$('#extendMembershipForm')[0].reset();
			$('#extendMembershipModal').modal('hide');
			//messageBox(data.resultStr);


		}
	});



}
function closeExtendModal() {
	$('#extendMembershipModal').modal('hide');
}

function saveExtendMemberShip() {
	var mi_id = $('#miidforextend').val();
	var gp_months = $("#gp_month").val();
	var paid_amount = $("#paid_amount").val();
	var discount = $("#discount").val();

	var queryParam = {
		"gp_month": gp_months, "paidamount": paid_amount, "discount": discount, "mi_id": mi_id
	};
	var url = "extendMembership";
	$.ajax({
		type: "POST",
		url: "/" + url,
		dataType: "json",
		async: true,
		data: {
			queryParam: JSON.stringify(queryParam)
		},
		success: function(data) {
			console.log(data);
			messageBox(data.resultStr);
			limit = 8;
			offset = 0;
			showAllMembers(limit, offset);

			$('#extendMembershipModal').modal('hide');

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