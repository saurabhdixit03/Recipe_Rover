<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>

<%
    // Retrieve the user ID from the session
     session = request.getSession();
    String uname = (String) session.getAttribute("uname");

    // Check if the user is logged in (authenticated)
    if (uname != null && !uname.isEmpty()) {
        // Display welcome message with user ID
%>
<%
    } else {
        // Redirect to the login page or handle unauthorized access
        response.sendRedirect("index.jsp");
    }
%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">
<title>Update Profile </title>

<!-- Font Icon -->
<link rel="stylesheet"
	href="fonts/material-icon/css/material-design-iconic-font.min.css">

<!-- Main css -->
<link rel="stylesheet" href="css/style.css">
</head>
<body>
   
	<div class="main">
		<!-- Sign up form -->
		<section class="signup">
		 
			<div class="container">
				<div class="signup-content">
					<div class="signup-form">
					    <h4 class="nav-link py-3 px-0 px-lg-3 rounded" >Welcome <%= uname %>! </h4>
						<h2 class="form-title">Update Profile</h2>
						<form method="post" action="ProfileServlet" class="register-form" id="register-form">
											
							<div class="form-group">
								<label for="name"><i
									class="zmdi zmdi-account material-icons-name"></i></label> <input
									type="text" name="name" id="name" placeholder="Your Name" />
							</div>
							
							<div class="form-group">
								<label for="pass"><i class="zmdi zmdi-lock"></i></label> <input
									type="password" name="pass" id="pass" placeholder="Password" />
							</div>
							<div class="form-group">
								<label for="re-pass"><i class="zmdi zmdi-lock-outline"></i></label>
								<input type="password" name="re_pass" id="re_pass"
									placeholder="Repeat your password" />
							</div>
							<div class="form-group">
								<label for="contact"><i class="zmdi zmdi-lock-outline"></i></label>
								<input type="text" name="contact" id="contact"
									placeholder="Contact no" />
							</div>
							
							<div class="form-group form-button">
								<input type="submit" name="signup" id="signup"
									class="form-submit" value="Update" />
							</div>
						</form>
					</div>
					<div class="signup-image">
						<figure>
							<img src="images/signup-image.jpg" alt="sing up image">
						</figure>
						
					</div>
				</div>
			</div>
		</section>


	</div>
	<!-- JS -->
	<script src="vendor/jquery/jquery.min.js"></script>
	<script src="js/main.js"></script>
	<!-- <script src="js/validateRegistration.js"></script> -->



</body>
<!-- This templates was made by Colorlib (https://colorlib.com) -->
</html>