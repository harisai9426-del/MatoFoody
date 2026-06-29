<%@page import="java.util.*"%>
<%@page import="com.matofoody.dao.RestaurantDAO"%>
<%@page import="com.matofoody.model.Restaurant"%>

<%
RestaurantDAO dao =
        new RestaurantDAO();

ArrayList<Restaurant> restaurants =
        dao.getAllRestaurants();
%>

<html>

<head>

<title>Manage Restaurants</title>

<style>

body{
    font-family:Arial;
}

table{
    width:100%;
    border-collapse:collapse;
}

table,th,td{
    border:1px solid black;
}

th,td{
    padding:10px;
}

.addBtn{
    background:green;
    color:white;
    padding:10px;
    text-decoration:none;
}

</style>

</head>

<body>

<h1>Restaurant Management</h1>

<a href="addRestaurant.jsp"
class="addBtn">
Add Restaurant
</a>

<br><br>

<table>

<tr>
<th>ID</th>
<th>Name</th>
<th>Type</th>
<th>Address</th>
<th>Rating</th>
<th>Action</th>
</tr>

<%
for(Restaurant r : restaurants){
%>

<tr>

<td><%= r.getRestaurantId() %></td>

<td><%= r.getRestaurantName() %></td>

<td><%= r.getRestaurantType() %></td>

<td><%= r.getAddress() %></td>

<td><%= r.getRating() %></td>

<td>

<a href="DeleteRestaurantServlet?restaurantId=<%= r.getRestaurantId() %>"
onclick="return confirm('Delete Restaurant?')">
Delete
</a>

</td>

</tr>

<%
}
%>

</table>

</body>

</html>