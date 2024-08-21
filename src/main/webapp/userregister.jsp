<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>User Register Form</title>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/intl-tel-input/17.0.8/css/intlTelInput.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/intl-tel-input/17.0.8/js/intlTelInput.min.js"></script>
<script type="text/javascript" src="js/user.js"></script>
<link rel="stylesheet" type="text/css" href="css/user.css">
</head>
<body>
	<div class="home-icon">
		<a href="index.jsp"> <i class="fas fa-home"></i>User Management
		</a>
	</div>
	<div class="container">
		<h1>User Register Form</h1>
		<form id="registrationForm" action="UserServlet" method="post">
			<div class="form-group">
				<label for="firstName">First Name</label> <input type="text"
					id="firstName" name="firstName" required>
			</div>
			<div class="form-group">
				<label for="lastName">Last Name</label> <input type="text"
					id="lastName" name="lastName" required>
			</div>
			<div class="form-group">
				<label for="username">Username</label> <input type="text"
					id="username" name="username" required>
			</div>
			<div class="form-group">
				<label for="email">Email</label> <input type="email" id="email"
					name="email" required>
			</div>
			<div class="form-group">
				<label for="address">Address</label> <input type="text" id="address"
					name="address" required>
			</div>
			<div class="form-group">
				<label for="contact">Contact No</label> <input type="tel"
					id="contact" name="contact" required> <input type="hidden"
					id="countryCode" name="countryCode">
			</div>
			<div class="form-group">
				<input type="submit" value="Submit">
			</div>
		</form>
	</div>
</body>
</html>
