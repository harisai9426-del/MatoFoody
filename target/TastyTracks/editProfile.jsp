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
<title>Edit Profile</title>

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
    background:orange;
    color:white;
    border:none;
    padding:10px 20px;
}

</style>

</head>

<body>

<h1>Edit Profile</h1>

<form action="UpdateProfileServlet"
      method="post">

<input type="hidden"
       name="userId"
       value="<%= user.getUserId() %>">

Name<br>

<input type="text"
       name="fullName"
       value="<%= user.getFullName() %>">

<br>

Email<br>

<input type="email"
       name="email"
       value="<%= user.getEmail() %>">

<br><br>

<button type="submit">
Update Profile
</button>

</form>

</body>
</html>