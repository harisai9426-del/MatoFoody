<%@ page import="java.util.*,com.matofoody.dao.*,com.matofoody.model.*" %>
<%@ page import="com.matofoody.dao.ReviewDAO"%>
<%@ page import="com.matofoody.model.Review"%>
<%@ page import="java.util.ArrayList"%>

<%
String username =
(String)session.getAttribute("username");

if(username == null){
response.sendRedirect("login.jsp");
return;
}

int restaurantId =
Integer.parseInt(request.getParameter("id"));


String search =
request.getParameter("search");

String category =
request.getParameter("category");

FoodItemDAO dao =
new FoodItemDAO();

ArrayList<FoodItem> foodList;

if(search != null &&
!search.trim().equals("")){
	
foodList =
dao.searchFoodItems(
        restaurantId,
        search);

}
else if(category != null &&
!category.trim().equals("")){


foodList =
dao.getFoodByCategory(
        restaurantId,
        category);


}
else{


foodList =
dao.getFoodItemsByRestaurant(
        restaurantId);


}

ReviewDAO reviewDAO =
new ReviewDAO();

double avgRating =
reviewDAO.getAverageRating(
restaurantId);

ArrayList<Review> reviews =
reviewDAO.getReviewsByRestaurant(
restaurantId);
%>

<!DOCTYPE html>

<html>

<head>

<meta charset="UTF-8">

<title>Restaurant Menu</title>

<style>

body{
    font-family:Arial,sans-serif;
    margin:0;
    background:#f5f5f5;
}

.navbar{
    background:#ff4d4d;
    color:white;
    padding:15px;
    display:flex;
    justify-content:space-between;
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

.food-card{
    border:1px solid #ddd;
    background:white;
    padding:15px;
    margin-bottom:15px;
    border-radius:8px;
}

.food-card h2{
    margin-top:0;
}

.btn{
    background:#28a745;
    color:white;
    border:none;
    padding:10px 15px;
    cursor:pointer;
    border-radius:5px;
}

.filter-section{
    background:white;
    padding:15px;
    margin-bottom:20px;
    border-radius:8px;
}

input,select{
    padding:8px;
    margin-right:10px;
}

</style>

</head>

<body>

<div class="navbar">

<div>

    <a href="restaurants.jsp">
    Restaurants
    </a>

    <a href="cart.jsp">
    Cart
    </a>

    <a href="customerOrders.jsp">
    My Orders
    </a>

    <a href="profile.jsp">
    Profile
    </a>

    <a href="LogoutServlet">
    Logout
    </a>

</div>

<div>
    Welcome, <%= username %>
</div>

</div>

<div class="container">

<h1>Restaurant Menu</h1>

 <h3>
 Average Rating :
<%= String.format("%.1f", avgRating) %> / 5
</h3>

<h3>
Total Items = <%= foodList.size() %>
</h3>

<div class="filter-section">

<form method="get" action="menu.jsp">

<input type="hidden"
    name="id"
    value="<%= restaurantId %>">

<input type="text"
    name="search"
    placeholder="Search Food Item">

<input type="submit"
    value="Search">

</form>

<br>

<form method="get" action="menu.jsp">

<input type="hidden"
    name="id"
    value="<%= restaurantId %>">

<select name="category">

<option value="">
All Categories
</option>

<option value="Veg">
Veg
</option>

<option value="Non Veg">
Non Veg
</option>

<option value="Biryani">
Biryani
</option>

<option value="Dessert">
Dessert
</option>

<option value="Cool Drinks">
Cool Drinks
</option>

</select>

<button class="btn" type="submit">
Filter
</button>

</form>

</div>

<%
for(FoodItem food : foodList){
%>

<div class="food-card">

<h2>
<%= food.getFoodName() %>
</h2>

<p>
<b>Category:</b>
<%= food.getFoodType() %>
</p>

<p>
<b>Price:</b>
INR <%= food.getPrice() %>
</p>

<p>
<b>Description:</b>
<%= food.getDescription() %>
</p>

<form action="CartServlet" method="post">

<input type="hidden"
    name="foodId"
    value="<%= food.getFoodId() %>">

<button class="btn"
     type="submit">

Add To Cart

</button>

</form>

</div>



<%
}
%>

<h2>Write a Review</h2>

<form action="AddReviewServlet" method="post">

<input
type="hidden"
name="restaurantId"
value="<%= restaurantId %>">

<label>Rating</label>

<select name="rating" required>

<option value="5">5 - Excellent</option>
<option value="4">4 - Good</option>
<option value="3">3 - Average</option>
<option value="2">2 - Poor</option>
<option value="1">1 - Very Poor</option>

</select>

<br><br>

<label>Your Review</label>

<br>

<textarea
name="review"
rows="4"
cols="60"
required></textarea>

<br><br>

<input
type="submit"
class="btn"
value="Submit Review">

</form>

<hr>

<h2>Customer Reviews</h2>

<%
if(reviews.isEmpty()){
%>

<p>No Reviews Yet.</p>

<%
}else{

for(Review r : reviews){
%>

<div class="food-card">

<h3>

<%= r.getUserEmail() %>

</h3>

<p>

Rating :

<%= r.getRating() %>/5

</p>

<p>

<%= r.getReview() %>

</p>

<p>

<small>

<%= r.getReviewDate() %>

</small>

</p>

</div>

<%
}

}
%>

</div>

</body>

</html>
