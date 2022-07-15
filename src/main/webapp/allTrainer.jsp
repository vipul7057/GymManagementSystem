<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>All Trainers</title>

<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
	integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"
	crossorigin="anonymous">
<script type="text/javascript"
	src="//ajax.googleapis.com/ajax/libs/jquery/2.0.0/jquery.min.js"></script>
<script src="https://code.jquery.com/jquery-3.4.1.min.js"
	integrity="sha256-CSXorXvZcTkaix6Yvo6HppcZGetbYMGWSFlBw8HfCJo="
	crossorigin="anonymous"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"
	integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
	crossorigin="anonymous"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"
	integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
	crossorigin="anonymous"></script>

<script src="https://kit.fontawesome.com/44e65d50f9.js"
	crossorigin="anonymous"></script>

<script type="text/javascript" src="./js/allTrainer.js"></script>

<script type="text/javascript" src="./js/jquerySessionPlugin.js"></script>

<script type="text/javascript" src="./js/commonFunction.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/bootbox.js/5.5.2/bootbox.min.js"></script>
<link type="text/css" rel="stylesheet" href="./css/ray.css" />

<style type="text/css">
.scrollable {
	width: 100%;
	overflow-x: auto;
	height: 500px;
	overflow-y: auto
}

td div {
	width: 75px
}

#editMemberInfo {
	width: 30px;
}

#emailDiv {
	width: 100px;
}

.money {
	width: 50px;
}


</style>
</head>
<body style="background-color: #EAEDED">
	<jsp:include page="./homenavbar.jsp" />
	<div style="font-size: 30px; font-weight: bold;" class="mt-1 ml-3">All
		Trainer List</div>
	<div class="container-fluid" style="height: 90vh; display: flex;">
		<div style="flex: 1">
			<div class="card"
				style="margin-top: 20px; margin-bottom: 30px; margin-right: 20px">
				<div class="card-body">

					<form id="searchForm">
						<div class="form-group">
							<label for="searchByName">Search By Name</label> <input
								type="text" class="form-control" id="searchByName"
								placeholder="Enter Name">

						</div>


						<button type="button" onclick="getTrainerByName()"
							class="btn btn-outline-primary">Search</button>
						<button type="button" onclick="clearFilter()"
							class="btn btn-outline-danger">Clear Filter</button>
					</form>
				</div>
			</div>
		</div>
		<div style="flex: 4.5">
			<div class="card" style="margin-top: 20px; margin-bottom: 30px">

				<div class="scrollable">
					<table class="table table-striped styled-table" >
						<thead class="bg-light" >
							<tr style="">
								<th scope="col" hidden>gt_id</th>
								<th scope="col">Name</th>
								<th scope="col">Mobile No</th>
								<th scope="col">Email</th>
								<th scope="col">Birthday</th>
								<th scope="col">Start Date</th>
								<th scope="col">End Date</th>
								<th scope="col">Experience<span style="font-size: 12px">(In Months)</span></th>
								<th scope="col">Salary</th>
								<th scope="col">Gender</th>
								<th scope="col">Actions</th>
							</tr>
						</thead>
						<tbody id="Trainerbody"
							style="justify-content: center; font-size: 12px">

						</tbody>
					</table>
				</div>

			</div>
			<div class="" style="display: flex; height: 30px">

				<div style="flex: 1; justify-content: flex-end; display: flex;">
					<button style="width: 40px" type="button" id="previousbtn"
						onclick="previousPage()" class="btn btn-warning mr-2 btn-sm">
						<i class="fas fa-arrow-circle-left"></i>
					</button>
					<button onclick="nextPage()" id="nextbtn" style="width: 40px"
						class="btn btn-warning mr-3 btn-sm">
						<i class="fas fa-arrow-circle-right"></i>
					</button>
				</div>
			</div>
		</div>
		<!-- Modal  Start for FREEZE Membership -->




	</div>
</body>
</html>