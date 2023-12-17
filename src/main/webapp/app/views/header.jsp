<%@page import="java.util.HashMap"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% 
HashMap<Integer, Integer> myCart = (HashMap<Integer, Integer>) session.getAttribute("cart");
String user = (String) session.getAttribute("user");
String admin = (String) session.getAttribute("admin");

if(myCart == null) {
	myCart = new HashMap<>();
}
%>
<!-- ======= Header ======= -->
  <header id="header" class="header fixed-top d-flex align-items-center">
    <div class="container d-flex align-items-center justify-content-between">

      <a href="Home" class="logo d-flex align-items-center me-auto me-lg-0">
        <!-- Uncomment the line below if you also wish to use an image logo -->
        <!-- <img src="assets/img/logo.png" alt=""> -->
        <h1>Yummy<span>.</span></h1>
      </a>

      <nav id="navbar" class="navbar">
        <ul>
          <li><a class="home" href="Home">Home</a></li>
          <li><a class="product" href="ViewProduct">Product</a></li>
          <li><a class="search" href="Search">Search</a></li>
        </ul>
      </nav><!-- .navbar -->
      <% if(user != null) { %>
      <a class="btn-book-a-table" href="cart.jsp">My cart (<%= myCart.size() %>)</a>
      <% } else { %>
      <a class="btn-book-a-table" href="login.jsp">Login</a>
      <% } %>
      
      <% if(admin != null) { %>
      <a class="btn-book-a-table" href="ProductManager">Manager Page</a>
      <% } %>
      
      <i class="mobile-nav-toggle mobile-nav-show bi bi-list"></i>
      <i class="mobile-nav-toggle mobile-nav-hide d-none bi bi-x"></i>

    </div>
	  <% if(user != null) { %>
		<a class="btn-book-a-table" href="Logout">Logout</a>
	  <% } %>
  </header><!-- End Header -->
  