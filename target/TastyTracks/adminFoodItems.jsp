<%@ page import="java.util.ArrayList" %>
<%@ page import="com.matofoody.dao.FoodItemDAO" %>
<%@ page import="com.matofoody.model.FoodItem" %>

<%
FoodItemDAO dao = new FoodItemDAO();
ArrayList<FoodItem> foodList = dao.getAllFoodItems();
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Food Items Management</title>

<style>

body{
    font-family: Arial, sans-serif;
    margin:20px;
}

h1{
    color:#333;
}

.btn{
    background:green;
    color:white;
    padding:10px 20px;
    text-decoration:none;
    border-radius:5px;
}

table{
    width:100%;
    border-collapse:collapse;
    margin-top:20px;
}

table,th,td{
    border:1px solid #ccc;
}

th{
    background:#f2f2f2;
    padding:10px;
}

td{
    padding:10px;
}

.action a{
    text-decoration:none;
    margin-right:10px;
}

</style>

</head>

<body>

<h1>Food Items Management</h1>

<a href="addFoodItem.jsp" class="btn">
Add Food Item
</a>

<table>

<tr>
<th>ID</th>
<th>Restaurant ID</th>
<th>Category ID</th>
<th>Food Name</th>
<th>Food Type</th>
<th>Price</th>
<th>Description</th>
<th>Action</th>
</tr>

<%
for(FoodItem item : foodList){
%>

<tr>

<td><%= item.getFoodId() %></td>

<td><%= item.getRestaurantId() %></td>

<td><%= item.getCategoryId() %></td>

<td><%= item.getFoodName() %></td>

<td><%= item.getFoodType() %></td>

<td>INR<%= item.getPrice() %></td>

<td><%= item.getDescription() %></td>

<td>

<a href="editFoodItem.jsp?foodId=<%= item.getFoodId()%>">
Edit
</a>

|

<a href="DeleteFoodItemServlet?foodId=<%= item.getFoodId()%>"
onclick="return confirm('Delete Food Item?')">
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