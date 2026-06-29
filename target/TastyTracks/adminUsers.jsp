<%@ page import="java.util.ArrayList" %>
<%@ page import="com.matofoody.dao.UserDAO" %>
<%@ page import="com.matofoody.model.User" %>

<%
UserDAO dao = new UserDAO();

ArrayList<User> users =
        dao.getAllUsers();
%>

<!DOCTYPE html>
<html>
<head>

<meta charset="UTF-8">

<title>Manage Users</title>

<style>

body{
    font-family:Arial;
    margin:30px;
    background:#f5f5f5;
}

h1{
    color:#333;
}

table{
    width:100%;
    border-collapse:collapse;
    background:white;
}

table,th,td{
    border:1px solid #ddd;
}

th{
    background:#2196F3;
    color:white;
    padding:12px;
}

td{
    padding:10px;
    text-align:center;
}

.delete{
    color:red;
    text-decoration:none;
    font-weight:bold;
}

</style>

</head>

<body>

<h1>User Management</h1>

<h3>Total Users : <%= users.size() %></h3>

<table>

<tr>
<th>User ID</th>
<th>Full Name</th>
<th>Email</th>
<th>Delete</th>
</tr>

<%
for(User user : users){
%>

<tr>

<td>
<%= user.getUserId() %>
</td>

<td>
<%= user.getFullName() %>
</td>

<td>
<%= user.getEmail() %>
</td>

<td>

<a class="delete"
href="DeleteUserServlet?userId=<%= user.getUserId() %>">

Delete

</a>

</td>

</tr>

<%
}
%>

</table>

<br><br>

<a href="admin.jsp">
<button>
Back To Dashboard
</button>
</a>

</body>
</html>