<%@page import="core.Category"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	Category category = (Category) request.getAttribute("type");

	if(category == null) {
		category = new Category();
	}
%>
<!DOCTYPE html>
<html lang="en">
<head>
<title>Update Type Page</title>
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
				<h1 class="text-info">UPDATE TYPE PAGE</h1>
				<hr>

				<div class="card" style="border-radius: 15px;">
					<form class="card-body" action="UpdateCategory">

						<div class="row align-items-center py-3">
							<div class="col-md-3 ps-5">

								<h5 class="mb-0 text-danger">Name</h5>

							</div>
							<div class="col-md-9 pe-5">

								<input type="text" name="name" class="form-control form-control-lg"
									placeholder="name" value="<%= category.getName() %>"/>

							</div>
						</div>
						<hr class="mx-n3">
						
						<div class="row align-items-center py-3">
							<div class="col-md-3 ps-5">

								<h5 class="mb-0 text-danger">Note</h5>

							</div>
							<div class="col-md-9 pe-5">

								<input type="text" name="note" class="form-control form-control-lg"
									placeholder="note" value="<%= category.getNote() %>" />

							</div>
						</div>
						<hr class="mx-n3">

						<div class="px-5 py-4">
							<button type="submit" class="btn btn-success btn-lg">Update</button>
						</div>

					</form>
				
				</div>

			</div>

		</div>
	</div>

	<!-- footer -->
	<%@ include file="./remainder/footer.jsp" %>
	<script>
		document.querySelector(".category").classList.add("active");
	</script>
</body>
</html>