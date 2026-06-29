<%@ page import="com.matofoody.dao.FoodItemDAO" %>
<%@ page import="com.matofoody.model.FoodItem" %>

<%
int foodId =
    Integer.parseInt(
    request.getParameter("foodId"));

FoodItemDAO dao =
    new FoodItemDAO();

FoodItem item =
    dao.getFoodItemById(foodId);
%>

<html>

<head>

<title>Edit Food Item</title>

<style>

body{
    font-family:Arial;
    margin:40px;
}

input{
    width:300px;
    padding:10px;
    margin-bottom:10px;
}

button{
    background:orange;
    color:white;
    padding:10px 20px;
    border:none;
}

</style>

</head>

<body>

<h1>Edit Food Item</h1>

<form action="UpdateFoodItemServlet"
      method="post">

<input type="hidden"
       name="foodId"
       value="<%= item.getFoodId()%>">

Food Name<br>

<input type="text"
       name="foodName"
       value="<%= item.getFoodName()%>">

<br>

Food Type<br>

<input type="text"
       name="foodType"
       value="<%= item.getFoodType()%>">

<br>

Price<br>

<input type="text"
       name="price"
       value="<%= item.getPrice()%>">

<br>

Description<br>

<input type="text"
       name="description"
       value="<%= item.getDescription()%>">

<br><br>

<button type="submit">
Update Food Item
</button>

</form>

</body>

</html>