<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Attendance</title>

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

<script type="text/javascript" src="./js/TrainerAttendence.js"></script>

<script type="text/javascript" src="./js/jquerySessionPlugin.js"></script>

<script type="text/javascript" src="./js/commonFunction.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/bootbox.js/5.5.2/bootbox.min.js"></script>
<link type="text/css" rel="stylesheet" href="./css/ray.css" />

</head>
<body style="background-color: #EAEDED">
	<jsp:include page="./homenavbar.jsp" />

	<div style="display: flex; justify-content: star; height: 91vh">
		<div style="display: flex; flex: 1; flex-direction: column;">
			<div style="flex: 1"></div>
			<div style="flex: 4">
				<div class="mb-3">
					<div class="ml-2" style="font-size: 25px; font-weight: bold;">
						Leave Request For <span id="TrainerNameForLeave">Vipul</span>
					</div>
				</div>
				<form id="TrainerLeaveForm">
					<div class="form-group" style="display: flex;">
						<div style="flex: 1" class="mr-1 ml-2">
							<label for="startdate" style="font-size: 17px">Start Date</label>
							<input type="date" class="form-control" style="width: 100%"
								id="startdate">
						</div>
						<div style="flex: 1" class="ml-1">
							<label for="enddate" style="font-size: 17px">End Date</label> <input
								type="date" class="form-control" style="width: 100%"
								id="enddate">
						</div>

					</div>
					<div>
						<div>
							<label for="LeaveNote" class="ml-2" style="font-size: 17px">Leave
								Note</label><br>
							<textarea rows="5" class="ml-2" cols="60" id="LeaveNote"></textarea>
						</div>
					</div>
				</form>
				<button class="btn btn-primary ml-2 mt-2" onclick="saveAttendance()">Save</button>
			</div>
			<div style="flex: 1"></div>
		</div>
		<div class="ml-2"
			style="display: flex; flex: 1; flex-direction: column;">
			<div style="flex: 1"></div>
			<div style="flex: 4">
				<div class="mb-3">
					<div class="ml-2" style="font-size: 25px; font-weight: bold;">
						Attendance History</div>
				</div>
				<div>
					<table class="table" style="border: 1px solid black">
						<thead>
							<tr>
								<th scope="col">Start Date</th>
								<th scope="col">End Date</th>
								<th scope="col">No Of Days</th>
								<th scope="col">Leave Note</th>
								
							</tr>
						</thead>
						<tbody>
							<tr>

								<td>Mark</td>
								<td>Otto</td>
								<td>@mdo</td>
							</tr>
							<tr>


								<td>Jacob</td>
								<td>Thornton</td>
								<td>@fat</td>
							</tr>
							<tr>


								<td>Larry</td>
								<td>the Bird</td>
								<td>@twitter</td>
							</tr>
							<tr>


								<td>Larry</td>
								<td>the Bird</td>
								<td>@twitter</td>
							</tr>

							<tr>


								<td>Larry</td>
								<td>the Bird</td>
								<td>@twitter</td>
							</tr>
							<tr>

								<td>Larry</td>
								<td>the Bird</td>
								<td>@twitter</td>
							</tr>
						</tbody>
					</table>
				</div>

			</div>
			<div style="flex: 1"></div>
		</div>
	</div>
</body>
</html>