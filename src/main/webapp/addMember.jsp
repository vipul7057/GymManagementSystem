<!doctype html>
<html lang="en">
<head>
<!-- Required meta tags -->
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">

<!-- Bootstrap CSS -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
	integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"
	crossorigin="anonymous">

<title>Add Member</title>
<script type="text/javascript"
	src="//ajax.googleapis.com/ajax/libs/jquery/2.0.0/jquery.min.js"></script>
<script src="https://code.jquery.com/jquery-3.4.1.min.js"
	integrity="sha256-CSXorXvZcTkaix6Yvo6HppcZGetbYMGWSFlBw8HfCJo="
	crossorigin="anonymous"></script>
<script type="text/javascript" src="./js/addMember.js"></script>
<script type="text/javascript" src="./js/commonFunction.js"></script>

<script
	src="https://cdnjs.cloudflare.com/ajax/libs/bootbox.js/5.5.2/bootbox.min.js"></script>

<script src="https://kit.fontawesome.com/44e65d50f9.js"
	crossorigin="anonymous"></script>
</head>
<body style="background-color: #EAEDED">
	<jsp:include page="./homenavbar.jsp" />


	<div class="container" style="height: 95vh">
		<div class="card" style="margin-top: 20px; margin-bottom: 30px">
			<div style="padding: 10px; font-size: 30px; font-weight: bold;">Member
				Details</div>
			<div class="card-body">
				<form id="addMemberForm">
					<div class="form-group" style="display: flex;">
						<div style="flex: 1" class="mr-1">
							<label for="fsname">First Name</label> <input type="text"
								class="form-control" id="fsname" placeholder="Enter First Name">
						</div>

						<div style="flex: 1" class="ml-1">
							<label for="lsname">Last Name</label> <input type="text"
								class="form-control" id="lsname" placeholder="Enter Last Name">
						</div>
					</div>
					<div class="form-group" style="display: flex;">
						<div style="flex: 1" class="mr-1">
							<label for="email">Email</label> <input type="text"
								class="form-control" id="email" style="width: 100%"
								placeholder="Enter Email">
						</div>
						<div style="flex: 1" class="ml-1">
							<label for="mobileNo">Mobile No</label> <input type="text"
								class="form-control" id="mobileNo" style="width: 100%"
								placeholder="Enter Mobile No">
						</div>
					</div>
					<div class="form-group" style="display: flex;">

						<div style="flex: 1" class="mr-1">
							<label for="startdate">Start Date</label> <input type="date"
								class="form-control" style="width: 100%" id="startdate">
						</div>
						<div style="flex: 1" class="ml-1">
							<label for="btdate">Birth Date</label> <input type="date"
								class="form-control" style="width: 100%" id="btdate">
						</div>
					</div>



					<div class="form-group"></div>
					<div class="form-group"
						style="display: flex; justify-content: flex-start;">
						<div style="flex: 2">
							<label for="mi_photo">Upload Photo</label> <input type="file"
								class="form-control" style="width: 100%" id="mi_photo">
						</div>
						<div style="flex: 2; align-items: center;">
							<button style="margin-top: 33px"
								class="btn btn-outline-primary ml-4">
								<i class="fas fa-file-upload"></i>
							</button>
						</div>
					</div>
					<div class="form-group">
						<label for="gp_month">Gym Package</label> <select name="gp_month"
							id="gp_month">

						</select>
						<div id="amountDiv" style="display: none">
							<p style="color: red">
								Total Amount:- <span style="color: red; font-weight: bold;"
									id="gp_amount"></span>
							</p>
						</div>
					</div>
					<div class="form-group"
						style="display: flex; justify-content: flex-start;">
						<div style="flex: 2" class="mr-1">
							<label for="discount">Discount <small>(Opional)</small>
							</label> <input type="text" class="form-control" id="discount"
								placeholder="If No, Enter 0">
						</div>
						<div style="flex: 2" class="ml-1 ">
							<div style="display: flex;">
								<div style="flex: 2">
									<label for="paid_amount">Paid Amount</label> <input type="text"
										class="form-control" id="paid_amount"
										placeholder="If No, Enter 0">
								</div>
								<div style="flex: 1" class="ml-2">
								<label for="paymentMode">Paid Mode</label><br>
								<select class="form-group" id="paymentMode" style="height: 35px">
								
									<option value="0">None</option>
									<option value="Cash">Cash</option>
									<option value="Upi">UPI</option>
									<option value="Card">Card</option>
								</select>
								</div>
							</div>
						</div>
					</div>
					<button type="button" onclick="AddMember()" class="btn btn-primary">Submit</button>
				</form>
			</div>
		</div>
	</div>

	<!-- Optional JavaScript -->
	<!-- jQuery first, then Popper.js, then Bootstrap JS -->

	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"
		integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
		crossorigin="anonymous"></script>
	<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"
		integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
		crossorigin="anonymous"></script>
</body>
</html>