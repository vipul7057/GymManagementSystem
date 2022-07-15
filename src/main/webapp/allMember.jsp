
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>All Members</title>

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

<script type="text/javascript" src="./js/allMember.js"></script>

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
		Member List</div>
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


						<button type="button" onclick="getMemberByName()"
							class="btn btn-outline-primary">Search</button>
						<button type="button" onclick="clearFilter()"
							class="btn btn-outline-danger mt-2">Clear Filter</button>
					</form>
				</div>
			</div>
		</div>
		<div style="flex: 4.5">
			<div class="card" style="margin-top: 20px; margin-bottom: 30px">

				<div class="scrollable">
					<table class="table table-striped styled-table" >
						<thead class="bg-light" style="font-size: 15px">
							<tr >
								<th scope="col" hidden>mi_id</th>
								<th scope="col">Name</th>
								<th scope="col">Mobile No</th>
								<th scope="col">Email</th>
								<th scope="col">Birthday</th>
								<th scope="col">Start Date</th>
								<th scope="col">End Date</th>
								<th scope="col">Total Amount</th>
								<th scope="col">Paid Amount</th>
								<th scope="col">Remaining Amount</th>
								<th scope="col">Remaining Days</th>


								<th scope="col">Action</th>
							</tr>
						</thead>
						<tbody id="memberTbody"
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

		<div class="modal fade" id="freezeModal" tabindex="-1" role="dialog"
			aria-labelledby="freezeModalLabel" aria-hidden="true">
			<div class="modal-dialog" role="document">
				<div class="modal-content">
					<div class="modal-header">
						<h5 class="modal-title" id="freezeModalLabel">Enter No Of
							Days</h5>

					</div>
					<div class="modal-body">
						<form id="freezeMemberForm">
							<input type="text" id="miidforfreeze" hidden>
							<div class="form-group">
								<input type="number" class="form-control" id="freezeInput"
									placeholder="Enter No Of Days">

							</div>

						</form>
					</div>

					<div class="modal-footer">
						<button type="button" class="btn btn-outline-dark"
							data-dismiss="modal">Cancel</button>
						<button type="button" class="btn btn-outline-dark"
							onclick="freezeMembership()">Save</button>
					</div>
				</div>
			</div>
		</div>
		<!-- Modal End for FREEZE Membership -->


		<!-- Modal Start for PAYMENT -->
		<!-- Button trigger modal -->

		<div class="modal fade" id="paymentModal" tabindex="-1"
			aria-labelledby="paymentModalLabel" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<h5 class="modal-title" id="paymentlLabel">Manage Payments</h5>
					</div>
					<div class="modal-body">
						<!-- <a onclick="showTransactionModal()">
							<div style="display: flex; justify-content: flex-end;">Payment
								Breakdown</div> -->
						</a>
						<div>
							<div style="padding: 10px; font-weight: bold;">
								Total Amount : <span id="totalamount"></span>
							</div>
							<div style="padding: 10px; font-weight: bold;">
								Paid Amount : <span id="paidamount"></span>
							</div>
							<div style="padding: 10px; font-weight: bold;">
								Remaining Amount : <span id="remainingamount"></span>
							</div>
							<form id="paymentForm">
								<div style="padding: 10px">
									<input type="text" class="form-control" id="paymentInput"
										placeholder="Enter Amount"> <input type="text"
										id="miidforpayment" hidden>
								</div>
							</form>
						</div>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-secondary"
							data-dismiss="modal">Close</button>
						<button type="button" class="btn btn-primary"
							onclick="managePayment()">Submit</button>
					</div>
				</div>
			</div>
		</div>
		<!-- Modal END for PAYMENTS -->


		<!-- Modal START for payment BREAKDOWN -->
		<div class="modal fade" id="paymentBreakdownModal" tabindex="-1"
			aria-labelledby="paymentBreakdownModal" aria-hidden="true">
			<input type="text" id="miidforbreakdown" hidden>
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-body">

						<table class="table" style="font-size: 10px">
							<thead>
								<tr>
									<th scope="col">Paid Amount</th>
									<th scope="col">Date</th>
								</tr>
							</thead>
							<tbody id="breakdowntbody">

							</tbody>
						</table>

					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-secondary"
							data-bs-dismiss="modal" onclick="closeBreakDown()">OK</button>
					</div>
				</div>
			</div>
		</div>
		<!--  Modal END for payment BREAKDOWN -->

		<!-- MODAL START for extend Membership -->


		<!-- Modal -->
		<div class="modal fade" id="extendMembershipModal" tabindex="-1"
			aria-labelledby="extendMembershipModal" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-body">
						<div>
							<div style="padding: 10px; font-weight: bold;">
								Start Date : <span id="startdate"></span>
							</div>
							<div style="padding: 10px; font-weight: bold;">
								End Date : <span id="enddate"></span>
							</div>
							<div style="padding: 10px; font-weight: bold;">
								Remaining Days : <span id="remainingdays"></span>
							</div>


							<form id="extendMembershipForm" class="mt-3">
								<div class="form-group">
									<label for="gp_month">Gym Package</label> <select
										name="gp_month" id="gp_month">
										<option value="0">None</option>
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
										<label for="paid_amount">Paid Amount</label> <input
											type="text" class="form-control" id="paid_amount"
											placeholder="If No, Enter 0">
									</div>
								</div>
							</form>
						</div>
						<input hidden id="miidforextend" type="text">

					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-secondary"
							data-bs-dismiss="modal" onclick="closeExtendModal()">Close</button>
						<button type="button" class="btn btn-primary" onclick="saveExtendMemberShip()">Save</button>
					</div>
				</div>
			</div>
		</div>
		<!-- MODAL END for extend Membership -->





	</div>
</body>
</html>