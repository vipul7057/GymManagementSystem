
var gt_id;
$(document).ready(function() {
	gt_id = $.session.get("gt_id");

});

function saveAttendance() {
	var ta_startdate = $('#startdate').val();
	var ta_enddate = $('#enddate').val();
	const diffInMs = new Date(ta_enddate) - new Date(ta_startdate)
	var NoOfDays = diffInMs / (1000 * 60 * 60 * 24);
	if(NoOfDays == 0)
	{
		NoOfDays = 1;
	}
	else if(NoOfDays < 0)
	{
		messageBox("End Date Should Be Greater Than Start Date");
		return;
	}
	var leaveNote = $('#LeaveNote').val();
	var queryParam = { "startdate": ta_startdate, "enddate": ta_enddate,"NoOfDays":NoOfDays,"gt_id":gt_id,"leaveNote": leaveNote};
	var url = "addTrainerAttendance";
	$.ajax({
		type: "POST",
		url: url,
		data: {
			queryParam: JSON.stringify(queryParam)
		},
		success: function(data) {
			console.log(data);
			messageBox(data.resultStr);
			$('#TrainerLeaveForm')[0].reset();
		}
	});
}