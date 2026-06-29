<%@ page import="java.util.ArrayList" %>
<%@ page import="com.matofoody.model.CartItem" %>

<%
String username = (String) session.getAttribute("username");

if(username == null){
    response.sendRedirect("login.jsp");
    return;
}

ArrayList<CartItem> cart =
(ArrayList<CartItem>)session.getAttribute("cart");

if(cart == null || cart.size() == 0){
    response.sendRedirect("cart.jsp");
    return;
}

double total = 0;

for(CartItem item : cart){

    total += item.getPrice() * item.getQuantity();

}
%>

<!DOCTYPE html>
<html>

<head>

<meta charset="UTF-8">

<title>Checkout</title>

<style>

body{
    font-family:Arial;
    background:#f5f5f5;
    margin:0;
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
}

.container{
    width:500px;
    margin:40px auto;
    background:white;
    padding:25px;
    border-radius:10px;
    box-shadow:0px 0px 10px lightgray;
}

input[type=text]{
    width:100%;
    padding:10px;
    margin-top:5px;
    margin-bottom:20px;
}

.btn{
    background:#28a745;
    color:white;
    border:none;
    padding:12px 20px;
    cursor:pointer;
    border-radius:5px;
    width:100%;
    font-size:16px;
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

        Welcome,
        <%= username %>

    </div>

</div>

<div class="container">

<h2>Checkout</h2>

<h3>

Total Amount :
₹ <%= total %>

</h3>

<form action="OrderServlet" method="post">

<label>

Customer Name

</label>

<input
type="text"
name="customerName"
required>

<label>

Delivery Address

</label>

<input
type="text"
name="address"
required>

<input
type="hidden"
name="amount"
value="<%= total %>">

<br>

<input
type="submit"
value="Proceed To Payment"
class="btn">

</form>

</div>

</body>

</html>