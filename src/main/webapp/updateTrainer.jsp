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

<title>Update Member</title>
<script type="text/javascript"
	src="//ajax.googleapis.com/ajax/libs/jquery/2.0.0/jquery.min.js"></script>
<script src="https://code.jquery.com/jquery-3.4.1.min.js"
	integrity="sha256-CSXorXvZcTkaix6Yvo6HppcZGetbYMGWSFlBw8HfCJo="
	crossorigin="anonymous"></script>
<script type="text/javascript" src="./js/updateTrainer.js"></script>
<script type="text/javascript" src="./js/jquerySessionPlugin.js"></script>
<script type="text/javascript" src="./js/commonFunction.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/bootbox.js/5.5.2/bootbox.min.js"></script>
</head>
<body style="background-color: #EAEDED">
	<jsp:include page="./homenavbar.jsp" />



	<div class="container" style="height: 95vh">
		<div class="card" style="margin-top: 20px; margin-bottom: 30px">
			<div style="padding: 10px; font-size: 20px; font-weight: bold;">Update
				Member Details</div>
			<div class="card-body">
				<form id="updateTrainerForm">
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
							<label for="salary">Salary</label> <input type="text"
								class="form-control" id="salary" style="width: 100%">
						</div>
						<div style="flex: 1" class="ml-1">
							<label for="btdate">Birthdate</label> <input type="date"
								class="form-control" style="width: 100%" id="btdate">
						</div>
					</div>

					<button type="button" onclick="UpdateTrainer()"
						class="btn btn-primary">Update</button>
					<a href="./allTrainer.jsp"><button type="button" onclick=""
							class="btn btn-primary">Back</button></a>
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