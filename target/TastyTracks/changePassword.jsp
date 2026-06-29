<%@ page import="com.matofoody.model.User" %>

<%
User user = (User)session.getAttribute("user");

if(user == null){
    response.sendRedirect("login.jsp");
    return;
}
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Change Password</title>

<style>

body{
    font-family:Arial;
    margin:40px;
}

input{
    width:300px;
    padding:10px;
    margin-bottom:10px;
}

button{
    background:red;
    color:white;
    border:none;
    padding:10px 20px;
}

</style>

</head>
<body>

<h1>Change Password</h1>

<form action="ChangePasswordServlet" method="post">

<input type="hidden"
       name="userId"
       value="<%= user.getUserId() %>">

New Password<br>

<input type="password"
       name="password"
       required>

<br><br>

<button type="submit">
Change Password
</button>

</form>

</body>
</html>