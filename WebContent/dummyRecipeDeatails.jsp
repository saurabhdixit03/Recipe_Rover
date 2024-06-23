<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.Arrays"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.List"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Recipe Details</title>
</head>
<body>
	<!DOCTYPE html>
<html lang="zxx">
<!-- fetchRecipeServlet Called ----SearchServlet Called for fetch recipe
 -->
<head>
<meta charset="UTF-8">
<meta name="description" content="Ogani Template">
<meta name="keywords" content="Ogani, unica, creative, html">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">
<title>Recipe Rover</title>

<!-- Google Font -->
<link
	href="https://fonts.googleapis.com/css2?family=Cairo:wght@200;300;400;600;900&display=swap"
	rel="stylesheet">

<!-- Css Styles -->
<link rel="stylesheet" href="css2/bootstrap.min.css" type="text/css">
<link rel="stylesheet" href="css2/font-awesome.min.css" type="text/css">
<link rel="stylesheet" href="css2/elegant-icons.css" type="text/css">
<link rel="stylesheet" href="css2/nice-select.css" type="text/css">
<link rel="stylesheet" href="css2/jquery-ui.min.css" type="text/css">
<link rel="stylesheet" href="css2/owl.carousel.min.css" type="text/css">
<link rel="stylesheet" href="css2/slicknav.min.css" type="text/css">
<link rel="stylesheet" href="css2/style.css" type="text/css">
</head>

<body>
	<%--    <jsp:include page="/searchservlet" /> --%>
	<!-- Header Section Begin -->


	<div class="container">
		<div class="row">
			<div class="col-lg-3">
				<div class="header__logo">
					<a href="./index.html"><img src="images/logo1.PNG" alt=""></a>

					<%
					// Retrieving the selected checkbox values from the request
					String[] defaultList = {};
					String[] selectedSpices = request.getParameterValues("selectedSpices"); // Change "checkbox1" to your actual checkbox name
					String[] selectedveggies = request.getParameterValues("selectedveggies"); // Change "checkbox1" to your actual checkbox name
					String[] selectedfriuts = request.getParameterValues("selectedfriuts"); // Change "checkbox1" to your actual checkbox name
					String[] selectedDairy = request.getParameterValues("selectedDairy"); // Change "checkbox1" to your actual checkbox name
					String[] selectedGrains = request.getParameterValues("selectedGrains"); // Change "checkbox1" to your actual checkbox name
					List<String> arrayList = new ArrayList<>(Arrays.asList(defaultList));
					// Checking if any values are received
					if (selectedSpices != null && selectedSpices.length > 0) {
						arrayList.addAll(Arrays.asList(selectedSpices));
					}
					if (selectedveggies != null && selectedveggies.length > 0) {
						arrayList.addAll(Arrays.asList(selectedveggies));
					}
					if (selectedfriuts != null && selectedfriuts.length > 0) {
						arrayList.addAll(Arrays.asList(selectedfriuts));
					}
					if (selectedDairy != null && selectedDairy.length > 0) {
						arrayList.addAll(Arrays.asList(selectedDairy));
					}
					
					if (selectedGrains != null && selectedGrains.length > 0) {
						arrayList.addAll(Arrays.asList(selectedGrains));
					}

					// Convert ArrayList back to array
					String[] allIngredentsArr = arrayList.toArray(new String[0]);
					// Display the appended array
					System.out.println("Appended Array:");
					System.out.println(Arrays.toString(allIngredentsArr));

					// Create a request dispatcher for the servlet
					String servletURL = "/searchservlet"; // Replace with your servlet URL
					RequestDispatcher dispatcher = request.getRequestDispatcher(servletURL);

					// Set request attributes to pass the newArray to the servlet
					request.setAttribute("allIngredentsArray", Arrays.toString(allIngredentsArr));

					// Dispatch the request to the servlet
					dispatcher.forward(request, response);

					// Retrieve the response from the servlet (if needed) and display it on the UI
					//String responseString = (String) request.getAttribute("recipeOutput");
					//out.println("<div>" + responseString + "</div>");
					%>
				</div>
			</div>
			<div class="col-lg-6">
				<nav class="header__menu">
					<ul>
						<li><a href="index.jsp">Dashboard</a></li>
						<li><a href="search.jsp">Search</a></li>
						<li><a href="favRecipe.jsp">My Favorites</a></li>
						<li><a href="updateProfile.jsp">Profile</a></li>
						<li><a href="#about">About</a></li>


					</ul>
				</nav>
			</div>

		</div>
		<div class="humberger__open">
			<i class="fa fa-bars"></i>
		</div>
	</div>
	</header>
	<!-- Header Section End -->

	<!-- Hero Section Begin -->
	<!-- <section class="hero hero-normal">
		<div class="container">
			<div class="row">
				<div class="col-lg-3"></div>
				<div class="col-lg-9">
					<div class="hero__search">
						<div class="hero__search__form">
							<form method="post" action="searchservlet" id="fetchRecipesForm">
								<div class="hero__search__categories">
									All Categories
									                                    <span class="arrow_carrot-down"></span>
								</div>

								<input type="hidden" id="newArray" name="newArray"
									value="newArray">
								<button type="button" class="site-btn" id="fetchRecipesBtn">Fetch
									Recipes</button>

								<input type="text" placeholder="What you have?">
                                <button type="submit" class="site-btn" id="fetchRecipesBtn" >SEARCH</button>
							</form>
							Script to send ingrediants to search servlet
							<script>
								document
										.getElementById("fetchRecipesBtn")
										.addEventListener(
												"click",
												function() {
													// Get user input
													var newArray = document
															.getElementById("newArray").value;

													// Set the user input value in the form
													document
															.getElementById("newArray").value = newArray;

													// Submit the form
													document.getElementById(
															"fetchRecipesForm")
															.submit();
												});
							</script>
						</div>

					</div>
				</div>
			</div>
		</div>
	</section> -->
	<!-- Hero Section End -->

	<!-- Breadcrumb Section Begin -->

	<section class="breadcrumb-section set-bg"
		data-setbg="images/breadcrumb.jpg">
		<div class="container">
			<div class="row">
				<div class="col-lg-12 text-center">
					<div class="breadcrumb__text">
						<h2>Your Recipe</h2>
						<div class="breadcrumb__option">
							<a href="./index.html">Home</a> <a href="./search.jsp">Search</a>
							<span>Your Recipe</span>
						</div>
					</div>
				</div>
			</div>
		</div>
	</section>
	<!-- Breadcrumb Section End -->

	<!-- Product Details Section Begin -->
	<section class="product-details spad">
		<div class="container">
			<div class="row">
				<div class="col-lg-6 col-md-6">
					<div class="product__details__pic">
						<div class="product__details__pic__item">
							<img class="product__details__pic__item--large"
								src="images/product-details-1.jpg" alt="">
						</div>
						<!-- <div class="product__details__pic__slider owl-carousel">
                            <img data-imgbigurl="img/product/details/product-details-2.jpg"
                                src="img/product/details/thumb-1.jpg" alt="">
                            <img data-imgbigurl="img/product/details/product-details-3.jpg"
                                src="img/product/details/thumb-2.jpg" alt="">
                            <img data-imgbigurl="img/product/details/product-details-5.jpg"
                                src="img/product/details/thumb-3.jpg" alt="">
                            <img data-imgbigurl="img/product/details/product-details-4.jpg"
                                src="img/product/details/thumb-4.jpg" alt="">
                        </div> -->
					</div>
				</div>
				<div class="col-lg-6 col-md-6">
					<div class="product__details__text">
						<h3>Recipe Name</h3>

						<!-- <div class="product__details__price">$50.00</div> -->
						<ul>
							<li><b>Ingredients</b></li>

							<!-- <li><b>Shipping</b> <span>01 day shipping. <samp>Free pickup today</samp></span></li>
                            <li><b>Weight</b> <span>0.5 kg</span></li>
                            <li><b>Share on</b>
                                <div class="share">
                                    <a href="#"><i class="fa fa-facebook"></i></a>
                                    <a href="#"><i class="fa fa-twitter"></i></a>
                                    <a href="#"><i class="fa fa-instagram"></i></a>
                                    <a href="#"><i class="fa fa-pinterest"></i></a>
                                </div>
                            </li> -->

							<li><b>Steps to follow </b></li>
						</ul>
						<%-- <%
						String recipeDetails = (String) request.getAttribute("recipeOutput");
						System.out.println("==========Recipe Details at JSP page"+recipeDetails);
						%>
						<p>
							Recipe Details:
							<%=recipeDetails%></p> --%>

						<!-- <p>Mauris blandit aliquet elit, eget tincidunt nibh pulvinar a. Vestibulum ac diam sit amet quam
                            vehicula elementum sed sit amet dui. Sed porttitor lectus nibh. Vestibulum ac diam sit amet
                            quam vehicula elementum sed sit amet dui. Proin eget tortor risus.</p> -->
						<!-- <div class="product__details__quantity">
                            <div class="quantity">
                                <div class="pro-qty">
                                    <input type="text" value="1">
                                </div>
                            </div>
                        </div> -->
						<form id="addToFav" action="AddToFavServlet" method="post">
							<a href="favRecipe.jsp" class="primary-btn">ADD TO FAVORITE</a>
							<!-- <a href="#" class="heart-icon"><span class="icon_heart_alt"></span></a> -->
						</form>
					</div>
				</div>

			</div>
		</div>
	</section>
	<!-- Product Details Section End -->





	<!-- Js Plugins -->
	<script src="js2/jquery-3.3.1.min.js"></script>
	<script src="js2/bootstrap.min.js"></script>
	<script src="js2/jquery.nice-select.min.js"></script>
	<script src="js2/jquery-ui.min.js"></script>
	<script src="js2/jquery.slicknav.js"></script>
	<script src="js2/mixitup.min.js"></script>
	<script src="js2/owl.carousel.min.js"></script>
	<script src="js2/main.js"></script>


</body>
</html>