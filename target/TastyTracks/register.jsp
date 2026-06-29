<html>
<head>
<title>Register</title>
</head>
<body>

<h1>User Registration</h1>

<form action="RegisterServlet"
      method="post">

Name:
<input type="text"
       name="fullName"
       required>

<br><br>

Email:
<input type="email"
       name="email">

<br><br>

Password:
<input type="password"
       name="password">

<br><br>

<input type="submit"
       value="Register">

</form>

</body>
</html>