<%@page import="dao.CategoryDAO"%>
<%@page import="core.Category"%>
<%@page import="core.Product"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	ArrayList<Category> categories = CategoryDAO.getAllCategories();
%>
<!DOCTYPE html>
<html lang="en">
<head>
<title>Create Product Page</title>
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
				<h1 class="text-info">CREATE PRODUCT PAGE</h1>
				<hr>

				<div class="card" style="border-radius: 15px;">
					<form class="card-body" action="CreateProduct" method="post" enctype="multipart/form-data">

						<div class="row align-items-center py-3">
							<div class="col-md-3 ps-5">

								<h5 class="mb-0 text-danger">Name</h5>

							</div>
							<div class="col-md-9 pe-5">

								<input type="text" name="name" class="form-control form-control-lg"
									placeholder="name" />

							</div>
						</div>
						<hr class="mx-n3">
						
						<div class="row align-items-center py-3">
							<div class="col-md-3 ps-5">

								<h5 class="mb-0 text-danger">Price</h5>

							</div>
							<div class="col-md-9 pe-5">

								<input type="text" name="price" class="form-control form-control-lg"
									placeholder="price" />

							</div>
						</div>
						<hr class="mx-n3">
						
						<div class="row align-items-center py-3">
							<div class="col-md-3 ps-5">

								<h5 class="mb-0 text-danger">Detail</h5>

							</div>
							<div class="col-md-9 pe-5">

								<textarea name="detail" class="form-control form-control-lg"
									placeholder="detail" rows="3" cols="3"></textarea>
							</div>
						</div>
						<hr class="mx-n3">
						
						<div class="row align-items-center py-3">
							<div class="col-md-3 ps-5">

								<h5 class="mb-0 text-danger">Image</h5>

							</div>
							<div class="col-md-9 pe-5">

								<input type="file" name="image" class="form-control form-control-lg"
									placeholder="image" />

							</div>
						</div>
						<hr class="mx-n3">
						
						<div class="row align-items-center py-3">
							<div class="col-md-3 ps-5">

								<h5 class="mb-0 text-danger">Category</h5>

							</div>
							<div class="col-md-9 pe-5">

								<select name="category_id" class="form-control form-control-lg">
									<% for(Category category : categories) { %>
										<option value="<%= category.getId()%>"><%= category.getName() %></option>
									<% } %>
								</select>

							</div>
						</div>
						<hr class="mx-n3">
						
						<div class="px-5 py-4">
							<button type="submit" class="btn btn-success btn-lg">Create</button>
						</div>

					</form>
				</div>

			</div>

		</div>
	</div>

	<!-- footer -->
	<%@ include file="./remainder/footer.jsp" %>
	
	<script>
		document.querySelector(".product").classList.add("active");
	</script>
</body>
</html>