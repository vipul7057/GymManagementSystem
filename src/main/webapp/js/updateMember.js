$(document).ready(function() {
	var mi_id = $.session.get("mi_id");
	var queryParam = { "mi_id": mi_id };
	var url = "getMemberById";

	$.ajax({
		type: "POST",
		url: url,
		data: {
			queryParam: JSON.stringify(queryParam)
		},
		success: function(data) {
			console.log(data.rows[0]);
			var records = data.rows[0];
			$('#fsname').val(records.mi_fsname);
			$('#lsname').val(records.mi_lsname);
			$('#email').val(records.mi_email);
			$('#mobileNo').val(records.mi_mobileNo);
			$('#btdate').val(records.mi_btdate);

		}
	});
})

function UpdateMember() {
	var fsname = $("#fsname").val();
	var lsname = $("#lsname").val();
	var email = $("#email").val();

	var mobileNo = $("#mobileNo").val();
	var btdate = $("#btdate").val();
	var mi_id = $.session.get("mi_id");
	console.log("mi_id ",mi_id);
	var queryParam = {
		"fsname": fsname, "lsname": lsname, "email": email, "btdate": btdate,
		"mobileNo": mobileNo,"mi_id":mi_id 
	};
	var url = "udpateMemberInfo";

	$.ajax({
		type: "POST",
		url: url,
		data: {
			queryParam: JSON.stringify(queryParam)
		},
		success: function(data) {
			console.log(data);
			if(data.resStatus == 1)
			{
				messageBox(data.resultStr);
			}


		}
	});

}