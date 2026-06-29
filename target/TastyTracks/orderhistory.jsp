<%
String username =
(String)session.getAttribute("username");

if(username == null){
    response.sendRedirect("login.jsp");
    return;
}
%>
<%@ page import="java.util.*" %>
<%@ page import="com.matofoody.dao.OrderHistoryDAO" %>
<%@ page import="com.matofoody.model.Order" %>

<html>
<head>
<title>Order History</title>
</head>

<body>

<div style="background:#ff4d4d;
            color:white;
            padding:15px;
            display:flex;
            justify-content:space-between;">

    <div>

        <a href="restaurants.jsp"
        style="color:white;
               margin-right:20px;">
        Restaurants
        </a>

        <a href="cart.jsp"
        style="color:white;
               margin-right:20px;">
        Cart
        </a>

        <a href="orderhistory.jsp"
        style="color:white;
               margin-right:20px;">
        Orders
        </a>

        <a href="LogoutServlet"
        style="color:white;">
        Logout
        </a>

    </div>

    <div>

        Welcome,
        <%= username %>

    </div>

</div>

<h1>Order History</h1>

<%
OrderHistoryDAO dao =
        new OrderHistoryDAO();

ArrayList<Order> orders =
        dao.getAllOrders();
%>

<table border="1" cellpadding="10">

<tr>
<th>Order ID</th>
<th>Customer Name</th>
<th>Address</th>
<th>Quantity</th>
<th>Total Amount</th>
</tr>

<%
for(Order order : orders){
%>

<tr>
<td><%= order.getOrderId() %></td>
<td><%= order.getCustomerName() %></td>
<td><%= order.getAddress() %></td>
<td><%= order.getQuantity() %></td>
<td>INR <%= order.getTotalAmount() %></td>
</tr>

<%
}
%>

</table>

</body>
</html>