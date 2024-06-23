<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">


<meta http-equiv="X-UA-Compatible" content="ie=edge">
<title>Saved Recipe</title>

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
<script src="https://code.jquery.com/jquery-3.6.0.min.js">
	
</script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/jspdf/1.5.3/jspdf.min.js"></script>
<!--   <script src="https://cdnjs.cloudflare.com/ajax/libs/jspdf/2.4.0/jspdf.umd.min.js"></script> -->
</head>
<body>
	<!-- Header Section Begin -->
	 
	<header>
		<div class="container">
			<div class="row">
				<div class="col-lg-3">
					<div class="header__logo">
						<a href="./index.html"><img src="images/logo1.PNG" alt=""></a>
					</div>
				</div>
				<div class="col-lg-6">
					<nav class="header__menu">
						<ul>
							<li class="active"><a href="index.html">Dashboard</a></li>
							<li><a href="search.jsp">Search</a></li>
							<li><a href="favRecipe.jsp">My Favorites</a></li>
							<li><a href="updateProfile.jsp">Profile</a></li>

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

		<!-- Breadcrumb Section Begin -->
	<section class="breadcrumb-section set-bg"
		data-setbg="images/breadcrumb.jpg">
		<div class="container">
			<div class="row">
				<div class="col-lg-12 text-center">
					<div class="breadcrumb__text">
						<h2>Saved Recipe</h2>
						<div class="breadcrumb__option">
							<a href="./index.jsp">Home</a><a href="./favRecipe.jsp">My Favorite</a> <span>Search By Date</span>
						</div>
					</div>
				</div>
			</div>
		</div>
	</section>
	<header>
		<div class="container">
			<div class="row">
				
				<div class="col-lg-3">
					<nav class="header__menu">
						<ul>
							<li><a href="">Search Recipe By Date</a></li>
							 
						</ul>
					</nav>
				</div>
				<div class="col-lg-3">
				<form action="reportsearchbydate" method="post">
								<label for="selectedDate">Select Date</label> 
								<input type="date" id="selectedDate" name="selectedDate" required> <br>
								<br> <input type="submit" value="Search">
							</form></div>
			</div>

		</div>
	</header>
	
	<!-- Breadcrumb Section End -->
	<!-- Shoping Cart Section Begin -->
	<section class="shoping-cart spad">
		<div class="container">
			<div class="row">
				<div class="col-lg-12">
					<div class="shoping__cart__table">
						<table>
							<thead>
								<tr>
									<th class="shoping__product">Title</th>
									<th class="shoping__product">Ingredients</th>
									<th class="shoping__product">Instructions</th>
									<th class="shoping__product">Date</th>


								</tr>
							</thead>
							<tbody>
								<%
								// Retrieve attributes set by the servlet
								String[] titles = (String[]) request.getAttribute("titles");
								String[] dates = (String[]) request.getAttribute("dates");
								String[] ingredients = (String[]) request.getAttribute("ingredients");
								String[] instructions = (String[]) request.getAttribute("instructions");

								// Check if titles and dates arrays are not null and have the same length
								if (titles != null && dates != null && titles.length == dates.length && ingredients != null && instructions != null
										&& ingredients.length == instructions.length) {
									for (int i = 0; i < titles.length; i++) {
								%>
								<tr>
									<td class="shoping__cart__price"><%=titles[i]%></td>
									<td class="shoping__cart__item"><%=ingredients[i]%></td>
									<td class="shoping__cart__item"><%=instructions[i]%></td>
									<td class="shoping__cart__item"><%=dates[i]%></td>
									<td>
										<button class="printRecipeButton">Print</button> <script>
											$(document)
													.ready(
															function() {
																$(
																		'.printRecipeButton')
																		.click(
																				function(
																						e) {
																					e
																							.preventDefault();

																					var title = $(
																							this)
																							.closest(
																									'tr')
																							.find(
																									'.shoping__cart__price')
																							.text();
																					var ingredients = $(
																							this)
																							.closest(
																									'tr')
																							.find(
																									'.shoping__cart__item')
																							.eq(
																									0)
																							.text();
																					var instructions = $(
																							this)
																							.closest(
																									'tr')
																							.find(
																									'.shoping__cart__item')
																							.eq(
																									1)
																							.text();

																					// Create a new jsPDF instance
																					var doc = new jsPDF();
																					//window.jsPDF = window.jspdf.jsPDF;
																					// Set font size and text
																					doc
																							.setFontSize(10);
																					doc
																							.text(
																									"Title: "
																											+ title,
																									10,
																									10);
																					doc
																							.text(
																									"Ingredients: "
																											+ ingredients,
																									10,
																									20);
																					doc
																							.text(
																									"Instructions: "
																											+ instructions,
																									10,
																									50);

																					// Save the PDF file
																					doc
																							.save('recipe_details.pdf');
																					e
																							.stopPropagation();
																					return false;
																				});
															});
										</script>
									</td>


								</tr>

								<%
								}
								} else {
								%>
								<tr>
									<td colspan="2">No data available</td>
								</tr>
								<%
								}
								%>
							</tbody>



						</table>
					</div>
				</div>
			</div>
			<!--  <div class="row">
                <div class="col-lg-12">
                    <div class="shoping__cart__btns">
                        <a href="#" class="primary-btn cart-btn">CONTINUE SHOPPING</a>
                        <a href="#" class="primary-btn cart-btn cart-btn-right"><span class="icon_loading"></span>
                            Upadate Cart</a>
                    </div>
                </div>
                <div class="col-lg-6">
                    <div class="shoping__continue">
                        <div class="shoping__discount">
                            <h5>Discount Codes</h5>
                            <form action="#">
                                <input type="text" placeholder="Enter your coupon code">
                                <button type="submit" class="site-btn">APPLY COUPON</button>
                            </form>
                        </div>
                    </div>
                </div>
                <div class="col-lg-6">
                    <div class="shoping__checkout">
                        <h5>Cart Total</h5>
                        <ul>
                            <li>Subtotal <span>$454.98</span></li>
                            <li>Total <span>$454.98</span></li>
                        </ul>
                        <a href="#" class="primary-btn">PROCEED TO CHECKOUT</a>
                    </div>
                </div>
            </div> -->
		</div>
	</section>
	<!-- Shoping Cart Section End -->



</body>

<!-- Js Plugins -->
<script src="js2/jquery-3.3.1.min.js"></script>
<script src="js2/bootstrap.min.js"></script>
<script src="js2/jquery.nice-select.min.js"></script>
<script src="js2/jquery-ui.min.js"></script>
<script src="js2/jquery.slicknav.js"></script>
<script src="js2/mixitup.min.js"></script>
<script src="js2/owl.carousel.min.js"></script>
<script src="js2/main.js"></script>



</html>