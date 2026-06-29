<%@ page import="com.matofoody.model.DeliveryPartner"%>

<%
DeliveryPartner partner =
(DeliveryPartner)request.getAttribute("partner");
%>

<!DOCTYPE html>
<html>

<head>

<meta charset="UTF-8">

<title>Edit Delivery Partner</title>

<style>

body{
    font-family:Arial;
    background:#f5f5f5;
}

.container{

width:450px;

margin:40px auto;

background:white;

padding:30px;

border-radius:10px;

}

input,select{

width:100%;

padding:10px;

margin-top:10px;

margin-bottom:20px;

}

button{

padding:12px;

width:100%;

background:#2196F3;

color:white;

border:none;

cursor:pointer;

}

</style>

</head>

<body>

<div class="container">

<h2>Edit Delivery Partner</h2>

<form action="UpdateDeliveryPartnerServlet" method="post">

<input
type="hidden"
name="partnerId"
value="<%=partner.getPartnerId()%>">

Partner Name

<input
type="text"
name="partnerName"
value="<%=partner.getPartnerName()%>">

Phone

<input
type="text"
name="phone"
value="<%=partner.getPhone()%>">

Vehicle

<input
type="text"
name="vehicle"
value="<%=partner.getVehicle()%>">

Vehicle Number

<input
type="text"
name="vehicleNumber"
value="<%=partner.getVehicleNumber()%>">

Status

<select name="status">

<option
<%=partner.getStatus().equals("Available")?"selected":""%>>

Available

</option>

<option
<%=partner.getStatus().equals("Busy")?"selected":""%>>

Busy

</option>

</select>

<button type="submit">

Update Partner

</button>

</form>

</div>

</body>

</html>