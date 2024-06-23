<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>


<!-- Spice dropdown servlet called -->
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">


<meta http-equiv="X-UA-Compatible" content="ie=edge">
<title>Search Recipe</title>

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



	<%-- 
	<jsp:include page="/spicedropdown" />
	<jsp:include page="/vegetablesdropdown" />
	<jsp:include page="/fruitdropdown" />
 --%>
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
							<li class="active"><a href="index.jsp">Home</a></li>
							<li><a href="favRecipe.jsp">My Favorite</a></li>
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

	<section class="hero">
		<div class="container">
			<!-- <div class="row">
				<div class="col-lg-3">
					<div class="product__details__text">

						<form method="post" action="setSessionVariableServlet"
							id="setSessionVariableServlett">
							Add your input fields, buttons, etc., for the second form here
							For example:
							<a href="search.jsp" class="primary-btn">Confirm Ingrdients</a>
															<button type="submit" class="site-btn">Submit</button>
						</form>


					</div>
				</div>
				<div class="col-lg-9">
					<div class="hero__search">
						<div class="hero__search__form">
							<form method="post" action="searchservlet" id="fetchRecipesForm">

								<input type="text" id="userInput" name="userInput"
									placeholder="Enter ingredients">
								<button type="button" class="site-btn" id="fetchRecipesBtn">Fetch
									Recipes</button>


								Script to send ingrediants to search servlet
								<script>
									document
											.getElementById("fetchRecipesBtn")
											.addEventListener(
													"click",
													function() {
														// Get user input
														var userInput = document
																.getElementById("userInput").value;

														// Set the user input value in the form
														document
																.getElementById("userInput").value = userInput;

														// Submit the form
														document
																.getElementById(
																		"fetchRecipesForm")
																.submit();
													});
								</script>
							</form>


						</div>
					</div>

				</div>
			</div> -->

			<form id="checkboxForm" action="dummyRecipeDeatails.jsp"
				method="post" onsubmit="return validateForm();">
				<div class="product__details__text">

					<!-- Add your input fields, buttons, etc., for the second form here -->
					<!-- For example: -->
					<!-- <a href="search.jsp" class="primary-btn">Confirm Ingrdients</a> -->
					<button type="submit" class="primary-btn">Confirm Selection</button>
					<div id="error-message" style="display: none; color: red;">
						Please select at least one ingredient.</div>
				</div>
				<div class="row">
					<div class="col-lg-3">
						<div class="hero__categories">
							<div class="hero__categories__all">
								<i class="fa fa-bars"></i> <span>Spices</span>
							</div>
							<ul>
								<%-- List of spices with Indian names --%>
								<%
								String[] spices = { "Cumin (Jeera)", "Coriander (Dhania)", "Turmeric (Haldi)", "Chili Pepper (Laal Mirch)",
										"Fenugreek (Methi)", "Mustard Seeds (Sarson Ke Beej)", "Fennel (Saunf)", "Cardamom (Elaichi)", "Cloves (Laung)",
										"Cinnamon (Dalchini)", "Nutmeg (Jaifal)", "Bay Leaf (Tej Patta)", "Asafoetida (Hing)",
										"Black Pepper (Kali Mirch)", "Saffron (Kesar)", "Star Anise (Chakra Phool)", "Poppy Seeds (Khas-Khas)",
										"Black Cardamom (Badi Elaichi)", "Tamarind (Imli)", "Ajwain/Carom Seeds (Ajwain)", "Curry Leaves (Kadhi Patta)",
										"Mace (Javitri)", "Dill Seeds (Soa)", "Celery Seeds (Ajmod)", "Aniseed (Saunf)", "Bishop's Weed (Ajwain)",
										"Long Pepper (Pippali)", "Mango Powder (Aamchur)", "Nigella Seeds (Kalonji)", "White Pepper (Safed Mirch)" };
								for (String spice : spices) {
								%>
								<li><input type="checkbox" name="selectedSpices"
									value="<%=spice%>"> <%=spice%></li>
								<%
								}
								%>
							</ul>
						</div>
					</div>
					<div class="col-lg-3">
						<div class="hero__categories">
							<div class="hero__categories__all">
								<i class="fa fa-bars"></i> <span>Vegetables</span>
							</div>
							<ul>
								<%
								String[] vegetables = { "Potato (Aloo)", "Onion (Pyaz)", "Tomato (Tamatar)", "Cauliflower (Phool Gobi)",
										"Cabbage (Patta Gobi)", "Spinach (Palak)", "Brinjal/Eggplant (Baingan)", "Okra/Ladyfinger (Bhindi)",
										"Bell Pepper/Capsicum (Shimla Mirch)", "Green Peas (Hari Matar)", "Carrot (Gajar)", "Green Beans (Sem)",
										"Bitter Gourd (Karela)", "Bottle Gourd (Lauki)", "Ridge Gourd (Torai)", "Pointed Gourd (Parwal)",
										"Drumstick (Sahjan ki Phalli)", "Radish (Mooli)", "Fenugreek Leaves (Methi)", "Coriander Leaves (Dhaniya)",
										"Green Chili (Hari Mirch)", "Garlic (Lehsun)", "Ginger (Adrak)", "Pumpkin (Kaddu)", "Beetroot (Chukandar)",
										"Turnip (Shalgam)", "Yam (Suran)", "Taro Root (Arbi)", "Colocasia Leaves (Arbi ke Patte)",
										"Cluster Beans (Guar ki Phalli)" };

								for (String vegetable : vegetables) {
								%>
								<li><input type="checkbox" name="selectedveggies"
									value="<%=vegetable%>"> <%=vegetable%></li>
								<%
								}
								%>
							</ul>
						</div>
					</div>
					<div class="col-lg-3">
						<div class="hero__categories">
							<div class="hero__categories__all">
								<i class="fa fa-bars"></i> <span>Fruit</span>
							</div>
							<ul>
								<%-- List of fruits with Indian names --%>
								<%
								String[] fruits = { "Apple (Seb)", "Orange (Santara)", "Mango (Aam)", "Banana (Kela)", "Pear (Nashpati)",
										"Grapes (Angoor)", "Watermelon (Tarbooj)", "Pineapple (Ananas)", "Papaya (Papita)", "Strawberry (Angoori Laal)",
										"Melon (Kharbuza)", "Guava (Amrood)", "Plum (Aloo Bukhara)", "Avocado (Makhanphal)", "Apricot (Khubani)",
										"Coconut (Nariyal)", "Kiwi (Kiwi)", "Lemon (Nimbu)", "Jujube (Ber)", "Jackfruit (Kathal)", "Litchi (Litchi)",
										"Tangerine (Mosambi)", "Lychee (Lichi)", "Dragonfruit (Kamrak)", "Cashew (Kaju)", "Dates (Khajoor)",
										"Fig (Anjeer)", "Pomegranate (Anar)", "Plum (Alubukhara)", "Custard Apple (Sharifa)" };

								for (String fruit : fruits) {
								%>
								<li><input type="checkbox" name="selectedfriuts"
									value="<%=fruit%>"> <%=fruit%></li>
								<%
								}
								%>
							</ul>
						</div>
					</div>
					<div class="col-lg-3">
						<div class="hero__categories">
							<div class="hero__categories__all">
								<i class="fa fa-bars"></i> <span>Dairy Products</span>
							</div>
							<ul>
								<%
								String[] dairyProducts = { "Milk (Doodh)", "Curd/Yogurt (Dahi)", "Butter (Makkhan)", "Ghee (Desi Ghee)",
										"Paneer (Paneer)", "Cheese (Paneer)", "Buttermilk (Chaas)", "Cream (Malai)", "Condensed Milk (Mithai Mate)",
										"Whey (Chhachh)", "Lassi (Lassi)", "Yogurt Drink (Chaach)", "Khoya/Mawa (Khoya/Mawa)",
										"Cottage Cheese (Chhena)", "Clarified Butter (Samna)", "Ricotta Cheese (Ricotta Paneer)",
										"Fermented Milk (Kefir/Dahi)", "Sour Cream (Khatta Malai)", "Evaporated Milk (Sookha Doodh)",
										"Powdered Milk (Powdered Doodh)", "Skimmed Milk (Kam Doodh)", "Full Cream Milk (Pura Malai Wala Doodh)",
										"Condensed Milk (Kondensd Doodh)", "Flavored Milk (Rasila Doodh)", "Cream Cheese (Cream Paneer)",
										"Low-Fat Yogurt (Kam Fat Wala Dahi)", "Non-Fat Milk (Kam Fat Wala Doodh)", "Greek Yogurt (Greek Dahi)",
										"Soy Milk (Soy Doodh)", "Almond Milk (Badam Doodh)" };

								for (String product : dairyProducts) {
								%>
								<li><input type="checkbox" name="selectedDairy"
									value="<%=product%>"> <%=product%></li>
								<%
								}
								%>
							</ul>
						</div>
					</div>

				</div>
				<div class="row">
					<div class="col-lg-3">
						<div class="hero__categories">
							<div class="hero__categories__all">
								<i class="fa fa-bars"></i> <span>Grains</span>
							</div>
							<ul>
								<%-- List of commonly used grains and lentils with Indian names --%>
								<%
								String[] grains = { "Rice (Chawal)", "Wheat (Gehu)", "Maize (Makka)", "Barley (Jau)", "Sorghum (Jowar)",
										"Finger Millet (Ragi)", "Pearl Millet (Bajra)", "Oats (Jai)", "Quinoa (Quinoa)", "Chickpeas (Chana)",
										"Red Lentils (Masoor dal)", "Green Gram (Moong dal)", "Black Gram (Urad dal)", "Pigeon Peas (Toor dal)",
										"Kidney Beans (Rajma)", "Split Bengal Gram (Chana dal)", "Split Red Lentils (Masoor dal)",
										"Split Green Gram (Moong dal)", "Split Black Gram (Urad dal)", "Horse Gram (Kulith)", "Cowpeas (Chawli)",
										"Green Peas (Matar)", "Lentils (Dal)", "Black Eyed Peas (Lobia)", "Soybeans (Soyabean)",
										"Chia Seeds (Chia Seeds)", "Amaranth (Rajgira)", "Buckwheat (Kuttu)", "Job's Tears (Adlay Millet)",
										"Sago (Sabudana)" };
								for (String grain : grains) {
								%>
								<li><input type="checkbox" name="selectedGrains"
									value="<%=grain%>"> <%=grain%></li>
								<%
								}
								%>
							</ul>

						</div>
					</div>
					<div class="col-lg-3"></div>
					<div class="col-lg-3"></div>
					<div class="col-lg-3"></div>

				</div>


			</form>
		</div>

	</section>
	<!-- Hero Section End -->


	<!-- Js Plugins -->
	<script src="js2/jquery-3.3.1.min.js"></script>
	<script src="js2/bootstrap.min.js"></script>
	<script src="js2/jquery.nice-select.min.js"></script>
	<script src="js2/jquery-ui.min.js"></script>
	<script src="js2/jquery.slicknav.js"></script>
	<script src="js2/mixitup.min.js"></script>
	<script src="js2/owl.carousel.min.js"></script>
	<script src="js2/main.js"></script>
	<script src="js/validateSearch.js"></script>

</body>
</html>