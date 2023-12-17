<%@page import="dao.CategoryDAO"%>
<%@page import="config.Config"%>
<%@page import="core.Category"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	ArrayList<Category> categories = (ArrayList<Category>) request.getAttribute("categories");

	if(categories == null) {
		categories = new ArrayList<Category>();
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
<title>Category Manager Page</title>
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
				<h1 class="text-info">CATEGORY MANAGER PAGE</h1>
				<hr>

				<table class="table">
					<thead class="thead-light text-success">
						<tr>
							<th scope="col">ID</th>
							<th scope="col">NAME</th>
							<th scope="col">NOTE</th>
							<th scope="col"></th>
							<th scope="col"></th>
						</tr>
					</thead>
					<tbody>
					
						<% for(Category category : categories) { %>
							<tr>
								<th scope="row"><%= category.getId() %></th>
								<td><%= category.getName() %></td>
								<td><%= category.getNote() %></td>
								<td><a href="GetUpdateCategory?id=<%= category.getId() %>" class="btn btn-info">Update</a></td>
								<td><a 
									onclick="confirm('Delete this category?') && ( window.location.href = 'DeleteCategory?id=<%= category.getId() %>' )"
								 	class="btn btn-danger">Delete</a></td>
							</tr>
						<% } %>
					
					</tbody>
				</table>

				<a href="CreateCategory" class="btn btn-warning">CREATE NEW CATEGORY</a>

			</div>

		</div>
	</div>

	<div style="text-align: center;">
	<%= Config.getPaginationBar("CategoryManager", 0, CategoryDAO.getTotal(), currentPage, Config.PER_PAGE_MANAGER, Config.OFFSET) %>
	</div>


	<!-- footer -->
	<%@ include file="./remainder/footer.jsp" %>
	<script>
		document.querySelector(".category").classList.add("active");
		
		<% if(createAlert != null) { %>
		alert("<%= createAlert %>");
		<% } %>
		
		<% if(updateAlert != null) { %>
		alert("<%= updateAlert %>");
		<% } %>
	</script>
</body>
</html>