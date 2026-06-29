<%@ page import="java.util.ArrayList"%>
<%@ page import="com.matofoody.dao.OrderDAO"%>
<%@ page import="com.matofoody.dao.DeliveryPartnerDAO"%>
<%@ page import="com.matofoody.model.Order"%>
<%@ page import="com.matofoody.model.DeliveryPartner"%>

<%
OrderDAO orderDAO = new OrderDAO();
DeliveryPartnerDAO partnerDAO = new DeliveryPartnerDAO();

ArrayList<Order> orders = orderDAO.getAllOrders();
ArrayList<DeliveryPartner> partners = partnerDAO.getAllPartners();
%>

<!DOCTYPE html>
<html>
<head>

<meta charset="UTF-8">

<title>Delivery Management</title>

<style>

body{
    font-family:Arial;
    background:#f5f5f5;
    margin:30px;
}

table{
    width:100%;
    border-collapse:collapse;
    background:white;
}

table,th,td{
    border:1px solid #ddd;
}

th{
    background:#2196F3;
    color:white;
    padding:12px;
}

td{
    padding:10px;
    text-align:center;
}

select{

    padding:6px;

}

button{

    background:#28a745;
    color:white;
    border:none;
    padding:8px 15px;
    cursor:pointer;
    border-radius:5px;

}

button:hover{

    background:#218838;

}

</style>

</head>

<body>

<h1>Delivery Management</h1>

<table>

<tr>

<th>Order ID</th>
<th>Customer</th>
<th>Total</th>
<th>Current Status</th>
<th>Assign Partner</th>
<th>Action</th>

</tr>

<%
for(Order order : orders){
%>

<tr>

<td><%=order.getOrderId()%></td>

<td><%=order.getCustomerName()%></td>

<td>INR <%=order.getTotalAmount()%></td>

<td>

<%=order.getDeliveryStatus()%>

<br><br>

<a href="UpdateDeliveryStatusServlet?orderId=<%=order.getOrderId()%>&status=Preparing">
<button>Preparing</button>
</a>

<a href="UpdateDeliveryStatusServlet?orderId=<%=order.getOrderId()%>&status=Out For Delivery">
<button>Out For Delivery</button>
</a>

<a href="UpdateDeliveryStatusServlet?orderId=<%=order.getOrderId()%>&status=Delivered">
<button>Delivered</button>
</a>

</td>

<td>

<form action="AssignDeliveryServlet" method="post">

<input
type="hidden"
name="orderId"
value="<%=order.getOrderId()%>">

<select name="partnerId">

<%
for(DeliveryPartner partner : partners){
%>

<option value="<%=partner.getPartnerId()%>">

<%=partner.getPartnerName()%>
(
<%=partner.getVehicle()%>
)

</option>

<%
}
%>

</select>

</td>

<td>

<button type="submit">

Assign

</button>

</form>

</td>

</tr>

<%
}
%>

</table>

<br>

<a href="admin.jsp">

<button>

Back To Dashboard

</button>

</a>

</body>
</html>