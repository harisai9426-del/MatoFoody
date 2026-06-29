<html>
<head>

<title>Login</title>

<style>

body{
font-family:Arial;
background:#f4f4f4;
}

.container{

width:350px;
margin:100px auto;

background:white;

padding:20px;

border-radius:10px;

box-shadow:0 0 10px gray;
}

</style>

</head>

<body>

<div class="container">

<h2>User Login</h2>

<form action="LoginServlet"
method="post">

Email:

<input type="email"
name="email"
required>

<br><br>

Password:

<input type="password"
name="password"
required>

<br><br>

<input type="submit"
value="Login">

</form>

<br>

<a href="register.jsp">

New User? Register Here

</a>

</div>

</body>
</html>