<%@page import="dao.ProductDAO"%>
<%@page import="config.Config"%>
<%@page import="java.util.Base64"%>
<%@page import="core.Product"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	ArrayList<Product> products = (ArrayList<Product>) request.getAttribute("products");
	if(products == null) {
		products = new ArrayList<Product>();
	}
	
	int currentPage;
	try {
		currentPage = Integer.parseInt(request.getParameter("page"));
	} catch (Exception e) {
		currentPage = 1;
	}
	
	String key = (String) request.getAttribute("key");
	
	if(key == null) {
		key = "";
	}

%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta content="width=device-width, initial-scale=1.0" name="viewport">

<title>Yummy Search Page</title>
<meta content="" name="description">
<meta content="" name="keywords">

<!-- Favicons -->
<link href="assets/img/favicon.png" rel="icon">
<link href="assets/img/apple-touch-icon.png" rel="apple-touch-icon">

<!-- Google Fonts -->
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link
	href="https://fonts.googleapis.com/css2?family=Open+Sans:ital,wght@0,300;0,400;0,500;0,600;0,700;1,300;1,400;1,600;1,700&family=Amatic+SC:ital,wght@0,300;0,400;0,500;0,600;0,700;1,300;1,400;1,500;1,600;1,700&family=Inter:ital,wght@0,300;0,400;0,500;0,600;0,700;1,300;1,400;1,500;1,600;1,700&display=swap"
	rel="stylesheet">

<!-- Vendor CSS Files -->
<link href="assets/vendor/bootstrap/css/bootstrap.min.css"
	rel="stylesheet">
<link href="assets/vendor/bootstrap-icons/bootstrap-icons.css"
	rel="stylesheet">
<link href="assets/vendor/glightbox/css/glightbox.min.css"
	rel="stylesheet">
<link href="assets/vendor/swiper/swiper-bundle.min.css" rel="stylesheet">

<!-- Template Main CSS File -->
<link href="assets/css/main.css" rel="stylesheet">
</head>
<body>
	<!-- Header -->
	<%@ include file="./app/views/header.jsp"%>

	<!-- Search bar -->
	<form action="Search" style="margin: 100px 100px 0; text-align: right;" class="input-groupps-5">
		<input type="text" class="form-control" name="key" value="<%= key %>"> <br>
		<button type="submit" value="SEARCH" class="btn btn-primary">SEARCH</button>
	</form>

	<!-- ======= Menu Section ======= -->
	<section id="menu" class="menu" style="height: 550px;">
		<div class="container" data-aos="fade-up">

			<div class="tab-content" data-aos="fade-up" data-aos-delay="300">

				<div class="tab-pane fade active show" id="menu-starters">

					<div class="row gy-5">

						<%
						for(Product product: products) {
						%>

						<div class="col-lg-4 menu-item">
							<a href="assets/img/menu/menu-item-1.png" class="glightbox"><img
								src="data:image/jpeg;base64,<%= Base64.getEncoder().encodeToString(product.getImage()) %>" class="menu-img img-fluid"
								alt=""></a>
							<h4><%= product.getName() %></h4>
							<p class="ingredients"><%= product.getDetail() %>
							</p>
							<p class="price">$<%= product.getPrice() %></p>
							<a href="AddToCart?id=<%= product.getId()%>" class="btn btn-danger">Add To cart</a>
						</div>
						<!-- Menu Item -->
						
						<% } %>
					</div>
				</div>
				<!-- End Starter Menu Content -->

			</div>
		</div>
		<!-- End Dinner Menu Content -->
		
		<%= Config.getPaginationBar("Search", key, ProductDAO.getTotalBySearch(key), currentPage, Config.PER_PAGE, Config.OFFSET) %>

	</section>
	<!-- End Menu Section -->
	
	<%@ include file="./app/views/footer.jsp" %>

	<script>
		document.querySelector(".search").classList.add("active");
	</script>
</body>
</html>