<%@page import="dao.ProductDAO"%>
<%@page import="core.Product"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
HashMap<Integer, Integer> cart = (HashMap<Integer, Integer>) session.getAttribute("cart");

if(cart == null) {
	cart = new HashMap<>();
}
int total =0;
float totalPrice = 0.0f;
%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no" />
<meta name="description" content="" />
<meta name="author" content="" />
<title>Yummy Shopping Item</title>
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
	<!-- Navigation-->
	<%@ include file="./app/views/header.jsp" %>
<!-- Product section-->
	<section class="py-5" style="height: 620px;">
		<div class="container px-4 px-lg-5 my-5">
			<div class="row gx-4 gx-lg-5 align-items-center">
				<div id="shopping-cart">
					<h2>Shopping Cart</h2>
					<table class="table" cellpadding="10" cellspacing="1">
						<tbody>
							<tr>
								<th style="text-align: left;">Name</th>
								<th style="text-align: right;" width="5%">Quantity</th>
								<th style="text-align: right;" width="10%">Price<br>(
									in $)
								</th>
								<th style="text-align: right;" width="10%">Total<br>(
									in $)
								</th>
								<th style="text-align: center;" width="5%">Remove</th>
							</tr>
							
							<% for(int id: cart.keySet()) { 
								Product product = ProductDAO.getProductById(id);
								totalPrice += product.getPrice()* cart.get(id);
								total += cart.get(id);
							%>
							<tr>
								<td><%= product.getName() %></td>
								<td style="text-align: right;"><%= cart.get(id) %></td>
								<td style="text-align: right;"><%= product.getPrice() %></td>
								<td style="text-align: right;"><%= product.getPrice()* cart.get(id) %></td>
								<td style="text-align: center;"><a onclick="confirm('Delete this order?') && (window.location.href = 'DeleteOrder?id=<%= product.getId()%>')" class=""><i
										class="bi bi-trash"></i></a></td>
							</tr>
							<% } %>
							
							<tr>
								<td colspan="1" align="right">Total:</td>
								<td align="right"><%= total %></td>
								<td align="right" colspan="2"><strong>$<%= totalPrice %></strong></td>
								<td></td>
							</tr>
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</section>

	<%@ include file="./app/views/footer.jsp" %>
	<!-- Bootstrap core JS-->
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
	<!-- Core theme JS-->
	<script src="js/scripts.js"></script>
</body>
</html>
