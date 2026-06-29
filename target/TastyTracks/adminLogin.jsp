<!DOCTYPE html>
<html>
<head>

<title>Admin Login</title>

<style>

body{
    font-family:Arial;
    background:#f5f5f5;
}

.login-box{
    width:350px;
    margin:100px auto;
    background:white;
    padding:30px;
    border-radius:10px;
}

input{
    width:100%;
    padding:10px;
    margin-top:10px;
    margin-bottom:20px;
}

button{
    width:100%;
    padding:12px;
    background:#2196F3;
    color:white;
    border:none;
}

</style>

</head>

<body>

<div class="login-box">

<h2>Admin Login</h2>

<form action="AdminLoginServlet"
      method="post">

Username

<input type="text"
       name="username">

Password

<input type="password"
       name="password">

<button type="submit">
Login
</button>

</form>

</div>

</body>
</html>