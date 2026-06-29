<%@ page import="java.util.ArrayList" %>
<%@ page import="com.matofoody.dao.OrderDAO" %>
<%@ page import="com.matofoody.model.Order" %>
<%@ page import="com.matofoody.model.User" %>
<%@ page import="com.matofoody.dao.OrderItemDAO" %>
<%@ page import="com.matofoody.model.OrderItem" %>

<%
User user = (User)session.getAttribute("user");

if(user == null){
    response.sendRedirect("login.jsp");
    return;
}

String customerName = user.getFullName();

OrderDAO dao = new OrderDAO();

OrderItemDAO itemDAO = new OrderItemDAO();

ArrayList<Order> orders =
        dao.getOrdersByCustomer(customerName);
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>My Orders</title>

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
}

.navbar a{
    color:white;
    text-decoration:none;
    margin-right:20px;
    font-weight:bold;
}

.container{
    padding:30px;
}

table{
    width:100%;
    background:white;
    border-collapse:collapse;
    margin-top:20px;
}

table,th,td{
    border:1px solid #ddd;
}

th{
    background:#28a745;
    color:white;
    padding:12px;
}

td{
    padding:12px;
    text-align:center;
}

.info{
    font-size:18px;
    margin-bottom:10px;
}

.statusPlaced{
    color:blue;
    font-weight:bold;
}

.statusPreparing{
    color:orange;
    font-weight:bold;
}

.statusDelivered{
    color:green;
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
        Welcome, <%= customerName %>
    </div>

</div>

<div class="container">

<h1>My Orders</h1>

<div class="info">
    <b>Customer:</b> <%= customerName %>
</div>

<div class="info">
    <b>Orders Found:</b> <%= orders.size() %>
</div>

<%
if(orders.size() == 0){
%>

<h3>No Orders Found</h3>

<%
}else{
%>

<table>

<tr>
    <th>Order ID</th>
    <th>Items Ordered</th>
    <th>Quantity</th>
    <th>Total Amount</th>
    <th>Status</th>
    <th>Order Date</th>
    <th>Invoice</th>
</tr>

<%
for(Order order : orders){

    ArrayList<OrderItem> items =
            itemDAO.getOrderItems(order.getOrderId());
%>

<tr>

<td>
<%= order.getOrderId() %>
</td>

<td align="left">

<%
for(OrderItem item : items){
%>

<b><%= item.getFoodName() %></b>
<br>

<%
}
%>

</td>

<td align="left">

<%
for(OrderItem item : items){
%>

<%= item.getQuantity() %>
<br>

<%
}
%>

</td>

<td>

&#8377; <%= order.getTotalAmount() %>

</td>

<td>

<%
String deliveryStatus = order.getDeliveryStatus();

if(deliveryStatus == null || deliveryStatus.trim().equals("")){
    deliveryStatus = "Waiting";
}
%>

<%= deliveryStatus %>

</td>

<td>

<%= order.getOrderDate() %>

</td>

<td>

<a href="DownloadInvoiceServlet?orderId=<%=order.getOrderId()%>">

Download

</a>

</td>

</tr>

<%
}
%>

</table>

<%
}
%>

</div>

</body>
</html>