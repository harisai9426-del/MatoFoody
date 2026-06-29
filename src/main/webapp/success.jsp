<%
String username=(String)session.getAttribute("username");

if(username==null){
    response.sendRedirect("login.jsp");
    return;
}
%>

<!DOCTYPE html>
<html>

<head>

<title>Order Success</title>

<style>

body{
font-family:Arial;
background:#f5f5f5;
margin:0;
}

.navbar{

background:#ff4d4d;

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

padding:40px;

text-align:center;

}

.btn{

padding:12px 25px;

background:#28a745;

color:white;

text-decoration:none;

border-radius:5px;

margin:10px;

display:inline-block;

}

</style>

</head>

<body>

<div class="navbar">

<div>

<a href="restaurants.jsp">Restaurants</a>

<a href="cart.jsp">Cart</a>

<a href="customerOrders.jsp">Orders</a>

<a href="LogoutServlet">Logout</a>

</div>

<div>

Welcome,

<%= username %>

</div>

</div>

<div class="container">

<h1>Order Placed Successfully</h1>

<br>

<a href="restaurants.jsp" class="btn">

Continue Shopping

</a>

<a href="DownloadInvoiceServlet" class="btn">

Download Invoice

</a>

</div>

</body>

</html>