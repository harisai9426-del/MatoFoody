<%
String username = (String)session.getAttribute("username");

if(username == null){
    response.sendRedirect("login.jsp");
    return;
}
%>

<%@ page import="java.util.ArrayList" %>
<%@ page import="com.matofoody.dao.RestaurantDAO" %>
<%@ page import="com.matofoody.model.Restaurant" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>

<%
RestaurantDAO dao = new RestaurantDAO();

String keyword = request.getParameter("search");

ArrayList<Restaurant> list;

if(keyword != null && !keyword.trim().equals("")){
    list = dao.searchRestaurant(keyword);
}
else{
    list = dao.getAllRestaurants();
}
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>MatoFoody Restaurants</title>

<style>

body{
    font-family:Arial, sans-serif;
    margin:0;
    background:#f5f5f5;
}

.navbar{
    background:#ff4d4d;
    color:white;
    padding:15px;
    display:flex;
    justify-content:space-between;
    align-items:center;
}

.navbar a{
    color:white;
    text-decoration:none;
    margin-right:20px;
    font-weight:bold;
}

.container{
    padding:20px;
}

.search-box{
    margin:20px 0;
}

.search-box input[type=text]{
    width:300px;
    padding:10px;
    border:1px solid #ccc;
    border-radius:5px;
}

.search-box input[type=submit]{
    padding:10px 20px;
    background:#ff4d4d;
    color:white;
    border:none;
    border-radius:5px;
    cursor:pointer;
}

.restaurant-card{
    background:white;
    padding:20px;
    margin-bottom:20px;
    border-radius:10px;
    box-shadow:0px 2px 8px lightgray;
}

.view-btn{
    background:#28a745;
    color:white;
    padding:10px 20px;
    text-decoration:none;
    border-radius:5px;
}

.view-btn:hover{
    background:#218838;
}

</style>

</head>

<body>

<div class="navbar">

<div>
    <a href="restaurants.jsp">Restaurants</a>
    <a href="cart.jsp">Cart</a>
    <a href="customerOrders.jsp">My Orders</a>
    <a href="profile.jsp">Profile</a>
    <a href="LogoutServlet">Logout</a>
</div>

<div>
    Welcome, <%= username %>
</div>

</div>

<div class="container">

<h1>MatoFoody Restaurants</h1>

<div class="search-box">

<form action="restaurants.jsp" method="get">

<input type="text"
       name="search"
       placeholder="Search Restaurant..."
       value="<%= keyword == null ? "" : keyword %>">

<input type="submit"
       value="Search">

</form>

</div>

<h3>Found <%= list.size() %> Restaurant(s)</h3>

<%
if(list.isEmpty()){
%>

<h2 style="color:red;">No Restaurants Found.</h2>

<%
}
else{

for(Restaurant r : list){
%>

<div class="restaurant-card">

<h2><%= r.getRestaurantName() %></h2>

<p><b>Restaurant Type :</b> <%= r.getRestaurantType() %></p>

<p><b>Address :</b> <%= r.getAddress() %></p>

<p><b>Rating :</b> ⭐ <%= r.getRating() %></p>

<br>

<a class="view-btn"
   href="menu.jsp?id=<%= r.getRestaurantId() %>">

View Menu

</a>

</div>

<%
    }
}
%>

</div>

</body>
</html>