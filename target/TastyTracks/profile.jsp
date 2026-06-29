<%@ page import="com.matofoody.model.User" %>

<%
User user =
(User)session.getAttribute("user");

if(user == null){
    response.sendRedirect("login.jsp");
    return;
}
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Profile</title>

<style>

body{
    font-family:Arial;
    margin:40px;
}

.card{
    width:500px;
    padding:20px;
    border:1px solid #ddd;
}

p{
    font-size:18px;
}

a{
    background:green;
    color:white;
    padding:10px 20px;
    text-decoration:none;
}

</style>

</head>
<body>

<h1>Customer Profile</h1>

<div class="card">

<p>
<b>Name:</b>
<%= user.getFullName() %>
</p>

<p>
<b>Email:</b>
<%= user.getEmail() %>
</p>

<p>
<b>Password:</b>
********
</p>

<a href="editProfile.jsp">
Edit Profile
</a>

<br><br>

<a href="changePassword.jsp">
Change Password
</a>

</div>

</body>
</html>