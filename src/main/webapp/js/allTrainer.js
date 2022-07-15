var limit = 8;
var offset = 0;
var totalFinalRecord;
var record = "";
$(document).ready(function() {
	$.session.set("mi_id", "");
	$('#previousbtn').prop('disabled', true);
	showAllTrainers(limit, offset);


});

function showAllTrainers(limit, offset) {
	$("#memberTbody").empty();
	var queryParam = { "limit": limit, "offset": offset };
	var url = "getAllGymTrainers";
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
			if (totalFinalRecord == 0) {
				$('.table').remove();
				messageBox("No Trainer Details Added");
				return;
			}
			makeTable(data);
		}
	});
}



function makeTable(data) {
	var text = "";
	$("#Trainerbody").empty();
	for (var i = 0; i < data.totalrecords; i++) {
		text += "<tr><td hidden><div>" + data.rows[i].gt_id + "</div></td>";
		text += "<td><div>" + data.rows[i].gt_fullName + "</div></td>";
		text += "<td><div>" + data.rows[i].gt_mobileNo + "</div></td>";
		text += "<td><div id=\"emailDiv\">" + data.rows[i].gt_email + "</div></td>";
		text += "<td><div>" + data.rows[i].gt_btdate + "</div></td>";
		text += "<td><div>" + data.rows[i].gt_startdate + "</div></td>";
		if (data.rows[i].gt_enddate == null) {
			data.rows[i].gt_enddate = 'NA';
		}
		text += "<td><div>" + data.rows[i].gt_enddate + "</div></td>";

		text += "<td><div>" + data.rows[i].gt_exp + "</div></td>";
		text += "<td><div>" + data.rows[i].gt_salary + "</div></td>";
		text += "<td><div>" + data.rows[i].gt_gender + "</div></td>";


		text += "<td>"
			+ "<div style=\"display:flex;justify-content:center\" class=\"dropdown  show\"><a class=\"btn btn-sm btn-secondary dropdown-toggle\" href=\"#\" role=\"button\" id=\"dropdownMenuLink\" data-toggle=\"dropdown\" aria-haspopup=\"true\" aria-expanded=\"false\">"
			+ "<i class=\"fas fa-dumbbell\"></i>"
			+ " </a>"
			+ "<div class=\"dropdown-menu\" aria-labelledby=\"dropdownMenuLink\">"
			+ "<a class=\"dropdown-item\" ><small><button class=\"btn btn-outline-dark btn-block btn-sm\" onclick=\"editTrainerDetails(" + data.rows[i].gt_id + ")\">Edit</button></a>"
			+ "<a class=\"dropdown-item\" href=\"#\"><small><button class=\"btn btn-outline-dark btn-block btn-sm\" onclick=\"Attendence(" + data.rows[i].gt_id + ")\" >Attendance</button></a>"
			//+ "<a class=\"dropdown-item\" href=\"#\"><small><button class=\"btn btn-outline-dark btn-block btn-sm\" onclick=\"setmiidfreeze(" + data.rows[i].mi_id + ")\" data-toggle=\"modal\" data-target=\"#freezeModal\">Freeze</button></small></a>"
			//+ "<a class=\"dropdown-item\" href=\"#\"><small><button class=\"btn btn-outline-dark btn-block btn-sm\" onclick=\"setmiidpayment(" + data.rows[i].mi_id + ")\"data-toggle=\"modal\" data-target=\"#paymentModal\">Payments</button></small></a>"
			+ "</div>"
			+ "</div></td>";

	}

	$("#Trainerbody").append(text);
}


function nextPage() {
	offset += 8;
	console.log("limit in next", limit + " offset in next", offset);
	$('#previousbtn').prop('disabled', false);
	showAllTrainers(limit, offset);
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
	showAllTrainers(limit, offset);
}

function editTrainerDetails(gt_id) {
	$.session.set("gt_id", gt_id);
	window.location.href = './updateTrainer.jsp';
}

function clearFilter() {
	$('#searchForm')[0].reset();
	limit = 8;
	offset = 0;
	$('#previousbtn').prop('disabled', false);
	$('#nextbtn').prop('disabled', false);
	showAllTrainers(limit, offset);
}


function getTrainerByName() {
	var name = $('#searchByName').val();
	limit = 8;
	offset = 0;
	var queryParam = { "name": name, "limit": limit, "offset": offset };
	console.log(queryParam)
	var url = "getAllGymTrainersByName";
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


function Attendence(gt_id) {
	$.session.set("gt_id", "");
	$.session.set("gt_id", gt_id);
	window.location.href = './TrainerAttendence.jsp';
}
