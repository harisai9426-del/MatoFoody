<%@ page import="java.util.ArrayList"%>
<%@ page import="com.matofoody.dao.DeliveryPartnerDAO"%>
<%@ page import="com.matofoody.model.DeliveryPartner"%>

<%
DeliveryPartnerDAO dao = new DeliveryPartnerDAO();

ArrayList<DeliveryPartner> partners = dao.getAllPartners();
%>

<!DOCTYPE html>
<html>

<head>

<meta charset="UTF-8">

<title>Delivery Partner Management</title>

<style>

body{
    font-family:Arial;
    background:#f5f5f5;
    margin:30px;
}

h1{
    color:#333;
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

a{
    text-decoration:none;
}

.btn{
    padding:6px 12px;
    border-radius:5px;
    color:white;
}

.edit{
    background:#28a745;
}

.delete{
    background:#dc3545;
}

.top{
    margin-bottom:20px;
}

button{
    padding:10px 20px;
    cursor:pointer;
}

</style>

</head>

<body>

<h1>Delivery Partner Management</h1>

<div class="top">

<a href="addDeliveryPartner.jsp">

<button>Add Delivery Partner</button>

</a>

</div>

<table>

<tr>

<th>ID</th>
<th>Name</th>
<th>Phone</th>
<th>Vehicle</th>
<th>Vehicle Number</th>
<th>Status</th>
<th>Edit</th>
<th>Delete</th>

</tr>

<%
for(DeliveryPartner partner : partners){
%>

<tr>

<td><%= partner.getPartnerId() %></td>

<td><%= partner.getPartnerName() %></td>

<td><%= partner.getPhone() %></td>

<td><%= partner.getVehicle() %></td>

<td><%= partner.getVehicleNumber() %></td>

<td><%= partner.getStatus() %></td>

<td>

<a class="btn edit"
href="EditDeliveryPartnerServlet?partnerId=<%=partner.getPartnerId()%>">

Edit

</a>

</td>

<td>

<a class="btn delete"
href="DeleteDeliveryPartnerServlet?partnerId=<%=partner.getPartnerId()%>">

Delete

</a>

</td>

</tr>

<%
}
%>

</table>

<br>

<a href="admin.jsp">

<button>Back To Dashboard</button>

</a>

</body>

</html>