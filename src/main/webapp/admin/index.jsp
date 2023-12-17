<%@page import="dao.ProductDAO"%>
<%@page import="config.Config"%>
<%@page import="java.util.Base64"%>
<%@page import="dao.CategoryDAO"%>
<%@page import="core.Product"%>
<%@page import="core.Category"%>
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
	
	String createAlert = (String) request.getAttribute("create_alert");
	String updateAlert = (String) request.getAttribute("update_alert");
%>
<!DOCcategory html>
<html lang="en">
<head>
<title>Product Manager Page</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<%@ include file="./css/style.jsp" %>
</head>

<body>

	<!-- navbar -->
	<%@ include file="./remainder/navbar.jsp" %>

	<div class="container text-center">
	
		<div class="row content">

			<div class="text-left">
				<!-- page title -->
				<h1 class="text-info">PRODUCT MANAGER PAGE</h1>
				<hr>

				<table class="table">
					<thead class="thead-light text-success">
						<tr>
							<th scope="col">ID</th>
							<th scope="col">NAME</th>
							<th scope="col">PRICE</th>
							<th scope="col">DETAIL</th>
							<th scope="col">IMAGE</th>
							<th scope="col">CATEGORY</th>
							<th scope="col"></th>
							<th scope="col"></th>
						</tr>
					</thead>
					<tbody>
					
						<% for(Product product : products) { %>
							<tr>
								<th scope="row"><%= product.getId() %></th>
								<td><%= product.getName() %></td>
								<td><%= product.getPrice() %></td>
								<td><%= product.getDetail() %></td>
								<td>
									<img style="width: 10%;" alt="" src="data:image/jpeg;base64,<%= Base64.getEncoder().encodeToString(product.getImage()) %>">
								</td>
								<td>
								<%= CategoryDAO.getCategoryById(product.getCategory_id()).getName() %>
								</td>
								<td><a href="GetUpdateProduct?id=<%= product.getId() %>" class="btn btn-info">Update</a></td>
								<td><a 
									onclick="confirm('Delete this product?') && ( window.location.href = 'DeleteProduct?id=<%= product.getId() %>' )"
								 	class="btn btn-danger">Delete</a></td>
							</tr>
						<% } %>
					
					</tbody>
				</table>

				<a href="CreateProduct" class="btn btn-warning">CREATE NEW PRODUCT</a>

			</div>

		</div>
	</div>
	
	<div style="text-align: center;">
	<%= Config.getPaginationBar("ProductManager", 0, ProductDAO.getTotal(), currentPage, Config.PER_PAGE_MANAGER, Config.OFFSET) %>
	</div>

	<!-- footer -->
	<%@ include file="./remainder/footer.jsp" %>
	<script>
		document.querySelector(".product").classList.add("active");
		
		<% if(createAlert != null) { %>
		alert("<%= createAlert %>");
		<% } %>
		
		<% if(updateAlert != null) { %>
		alert("<%= updateAlert %>");
		<% } %>
	</script>
</body>
</html>