<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Add Food Item</title>

<style>

body{
    font-family: Arial;
    margin:40px;
}

input{
    width:300px;
    padding:10px;
    margin-bottom:10px;
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

<h1>Add Food Item</h1>

<form action="AddFoodItemServlet" method="post">

Restaurant ID<br>
<input type="number" name="restaurantId" required>

<br>

Category ID<br>
<input type="number" name="categoryId" required>

<br>

Food Name<br>
<input type="text" name="foodName" required>

<br>

Food Type<br>
<input type="text" name="foodType" required>

<br>

Price<br>
<input type="text" name="price" required>

<br>

Description<br>
<input type="text" name="description" required>

<br><br>

<button type="submit">
Save Food Item
</button>

</form>

</body>
</html>