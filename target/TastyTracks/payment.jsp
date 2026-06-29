<%@ page language="java" contentType="text/html;charset=UTF-8"%>

<%
String username=(String)session.getAttribute("username");

if(username==null){
    response.sendRedirect("login.jsp");
    return;
}

double amount =
Double.parseDouble(request.getParameter("amount"));

String customerName =
request.getParameter("customerName");

String address =
request.getParameter("address");
%>

<!DOCTYPE html>
<html>

<head>

<title>Payment</title>

<style>

body{
font-family:Arial;
background:#f5f5f5;
}

.container{

width:500px;

margin:50px auto;

background:white;

padding:25px;

border-radius:10px;

box-shadow:0px 0px 10px gray;

}

.btn{

background:#28a745;

color:white;

padding:10px 20px;

border:none;

cursor:pointer;

border-radius:5px;

}

</style>

</head>

<body>

<div class="container">

<h2>Payment</h2>

<h3>

Total Amount :

₹ <%= amount %>

</h3>

<form action="PaymentServlet" method="post">

<input type="hidden"
name="orderId"
value="<%= request.getParameter("orderId") %>">

<input type="hidden"
name="amount"
value="<%= request.getParameter("amount") %>">

<input type="hidden"
       name="customerName"
       value="<%= customerName %>">

<input type="hidden"
       name="address"
       value="<%= address %>">

<label>

<input type="radio"

name="paymentMethod"

value="Cash On Delivery"

checked>

Cash On Delivery

</label>

<br><br>

<label>

<input type="radio"

name="paymentMethod"

value="UPI">

UPI

</label>

<br><br>

<label>

<input type="radio"

name="paymentMethod"

value="Credit Card">

Credit Card

</label>

<br><br>

<label>

<input type="radio"

name="paymentMethod"

value="Debit Card">

Debit Card

</label>

<br><br>

<input

type="submit"

value="Pay Now"

class="btn">



</form>

</div>

</body>

</html>