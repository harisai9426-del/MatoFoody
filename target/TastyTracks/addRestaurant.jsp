<html>

<head>

<title>Add Restaurant</title>

<style>

body{
    font-family:Arial;
    margin:40px;
}

input{
    width:300px;
    padding:10px;
    margin:10px 0;
}

button{
    background:green;
    color:white;
    padding:10px 20px;
    border:none;
}

</style>

</head>

<body>

<h1>Add Restaurant</h1>

<form action="AddRestaurantServlet" method="post">

Restaurant Name

<br>

<input type="text"
name="restaurantName">

<br>

Restaurant Type

<br>

<input type="text"
name="restaurantType">

<br>

Address

<br>

<input type="text"
name="address">

<br>

Rating

<br>

<input type="text"
name="rating">

<br><br>

<button type="submit">

Save Restaurant

</button>

</form>

</body>

</html>