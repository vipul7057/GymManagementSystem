<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>Login</title>
<link
	href="https://fonts.googleapis.com/css?family=Muli:300,400,500,600,700,800,900&display=swap"
	rel="stylesheet">
<script type="text/javascript"
	src="http://ajax.googleapis.com/ajax/libs/jquery/1.3.2/jquery.min.js"></script>
<link
	href="https://fonts.googleapis.com/css?family=Oswald:300,400,500,600,700&display=swap"
	rel="stylesheet">
<link rel="stylesheet" href="css/homepageCSS/bootstrap.min.css"
	type="text/css">
<link rel="stylesheet" href="css/homepageCSS/font-awesome.min.css"
	type="text/css">
<link rel="stylesheet" href="css/homepageCSS/flaticon.css"
	type="text/css">
<link rel="stylesheet" href="css/homepageCSS/owl.carousel.min.css"
	type="text/css">
<link rel="stylesheet" href="css/homepageCSS/barfiller.css"
	type="text/css">
<link rel="stylesheet" href="css/homepageCSS/magnific-popup.css"
	type="text/css">
<link rel="stylesheet" href="css/homepageCSS/slicknav.min.css"
	type="text/css">
<link rel="stylesheet" href="css/homepageCSS/style.css" type="text/css">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<!-- Bootstrap CSS -->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3"
	crossorigin="anonymous">
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p"
	crossorigin="anonymous"></script>
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"
	integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"
	crossorigin="anonymous"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"
	integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
	crossorigin="anonymous"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"
	integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
	crossorigin="anonymous"></script>

<script type="text/javascript"
	src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>


<link type="text/css" rel="stylesheet" href="/css/ray.css" />
<link rel="stylesheet" href="css/signup.css">
<script src="/js/signupAndLogin.js" type="text/javascript"></script>
</head>
<!--  
<style>
body {
	background-image: url("https://cdn.wallpapersafari.com/9/70/RjikWd.jpg");
	background-size: cover;
	width: 100%;
	height: 400px;
}
-->
</style>
<body>
	<!-- Nav bar starting -->
	<%@include file="./navbar.jsp"%>
	<!--  NAV BAR END -->

	<div style="height: 60px; margin-top: 40px"></div>
	<div class="container"
		style="height: 550px; opacity: 0.85; display: flex; justify-content: center;">
		<div></div>
		<div class="" style="margin-top: 60px">
			<div class="">
				<h2 class="form-title" style="margin-top: 45px">Login</h2>
				<form method="POST" class="register-form" id="register-form"
					style="width: 500px">

					<div class="form-group">

						<div>
							<label for="email"><i class="zmdi zmdi-email"></i></label> <input
								type="text" name="email" id="email" style="font-weight: bold;"
								placeholder="Your Email" />
						</div>


					</div>

					<div class="form-group">
						<label for="pass"><i class="zmdi zmdi-lock"></i></label> <input
							type="password" name="pass" id="pass" style="font-weight: bold;"
							placeholder="Password" />
					</div>

				</form>


				<div class="form-group form-button mt-3">
					<button type="button" class="btn btn-primary" id="loginOwner"
						onclick="loginOwner()">Login</button>
					<a href="./Dashboard.jsp"><button type="button" hidden class="btn btn-primary"
							id="loginBtn"></button></a>

				</div>
			</div>

		</div>
	</div>


</body>
</html>