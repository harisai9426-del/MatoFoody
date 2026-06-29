<!DOCTYPE html>
<html>

<head>

<meta charset="UTF-8">

<title>Add Delivery Partner</title>

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

background:#28a745;

color:white;

border:none;

cursor:pointer;

}

</style>

</head>

<body>

<div class="container">

<h2>Add Delivery Partner</h2>

<form action="AddDeliveryPartnerServlet" method="post">

Partner Name

<input
type="text"
name="partnerName"
required>

Phone

<input
type="text"
name="phone"
required>

Vehicle

<input
type="text"
name="vehicle"
required>

Vehicle Number

<input
type="text"
name="vehicleNumber"
required>

Status

<select name="status">

<option>Available</option>

<option>Busy</option>

</select>

<button type="submit">

Save Partner

</button>

</form>

</div>

</body>

</html>