<%@ page import="java.util.*" %>
<%@ page import="com.matofoody.model.CartItem" %>

<%
String username = (String)session.getAttribute("username");

if(username == null){
    response.sendRedirect("login.jsp");
    return;
}
%>

<%
Object obj = session.getAttribute("cart");

System.out.println("SESSION CART = " + obj);

if(obj != null){
    System.out.println("SESSION CLASS = " + obj.getClass());
}
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Shopping Cart</title>

<style>
body{
    font-family: Arial, sans-serif;
    margin:0;
    background:#f5f5f5;
}

button{
    padding:5px 10px;
    background:#ff4d4d;
    color:white;
    border:none;
    border-radius:4px;
    cursor:pointer;
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

table{
    width:100%;
    border-collapse:collapse;
    margin-top:20px;
}

table,th,td{
    border:1px solid #ddd;
}

th{
    background:#ff4d4d;
    color:white;
}

th,td{
    padding:12px;
    text-align:center;
}
.total{
    margin-top:20px;
    font-size:22px;
    font-weight:bold;
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

<h1>Shopping Cart</h1>

<%
ArrayList<CartItem> cart =
(ArrayList<CartItem>)session.getAttribute("cart");

System.out.println("Cart Object = " + cart);

if(cart != null){
    System.out.println("Cart Size JSP = " + cart.size());
}

if(cart == null || cart.size() == 0){
%>

<h2>Cart is Empty</h2>

<%
}else{

double total = 0;
%>

<h3>Total Items = <%= cart.size() %></h3>

<table>

<tr>
    <th>Food ID</th>
    <th>Food Name</th>
    <th>Price</th>
    <th>Quantity</th>
    <th>Amount</th>
    <th>Action</th>
</tr>

<%
for(CartItem item : cart){

double amount =
item.getPrice() * item.getQuantity();

total += amount;
%>

<tr>
    <td><%= item.getFoodId() %></td>
    <td><%= item.getFoodName() %></td>
    <td>INR<%= item.getPrice() %></td>
    <td><%= item.getQuantity() %></td>
    <td>INR<%= amount %></td>
    

<td>

<a href="DecreaseQtyServlet?foodId=<%=item.getFoodId()%>">
<button>-</button>
</a>

&nbsp;

<b><%= item.getQuantity() %></b>

&nbsp;

<a href="IncreaseQtyServlet?foodId=<%=item.getFoodId()%>">
<button>+</button>
</a>

<br><br>

<a href="RemoveFromCartServlet?foodId=<%=item.getFoodId()%>">
Remove
</a>

</td>

</tr>

<%
}
%>

</table>

<div class="total">
Total Amount = INR <%= total %>
</div>

<br>

<a href="checkout.jsp">
    <button style="
padding:12px 25px;
background:#28a745;
color:white;
border:none;
border-radius:5px;
cursor:pointer;">
        Proceed To Checkout
    </button>
</a>

<%
}
%>

</body>
</html>