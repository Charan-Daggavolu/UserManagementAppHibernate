<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>User Details</title>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script type="text/javascript" src="js/index.js"></script>
<link rel="stylesheet" type="text/css" href="css/index.css">
</head>
<body>
	<div class="home-icon">
		<a href="index.jsp"> <i class="fas fa-home"></i> User Management
		</a>
	</div>
	<div class="container">
		<h1>User Data</h1>
		<div class="actions-container">
			<a class="create-user-btn" href="userregister.jsp"> <i
				class="fas fa-user-plus"></i>
			</a>
			<div class="search-container">
				<form id="searchForm">
					<input type="text" id="searchInput"
						placeholder="Enter search query...">
					<button type="submit" class="search-icon">
						<i class="fas fa-search"></i>
					</button>
				</form>
			</div>
		</div>
		<div id="userData"></div>
	</div>
</body>
</html>
