
<%@ page import="com.matofoody.model.Admin" %>

<%
Admin admin =
(Admin)session.getAttribute("admin");

if(admin == null){

    response.sendRedirect(
            "adminLogin.jsp");

    return;
}
%><%@ page import="com.matofoody.dao.AdminDashboardDAO" %>

<%
AdminDashboardDAO dao =
        new AdminDashboardDAO();

int users =
        dao.getTotalUsers();

int restaurants =
        dao.getTotalRestaurants();

int orders =
        dao.getTotalOrders();

double revenue =
        dao.getTotalRevenue();
%>

<!DOCTYPE html>
<html>

<head>

<meta charset="UTF-8">

<title>Admin Dashboard</title>

<style>

body{
    font-family:Arial;
    margin:30px;
}

.card{
    width:220px;
    padding:20px;
    margin:15px;
    background:#f4f4f4;
    border-radius:10px;
    display:inline-block;
    text-align:center;
    font-size:22px;
    box-shadow:0px 0px 8px #ccc;
}

.menu{
    margin-top:30px;
}

.menu a{
    text-decoration:none;
}

.menu button{
    padding:12px 20px;
    margin-right:10px;
    background:#2196F3;
    color:white;
    border:none;
    border-radius:5px;
    cursor:pointer;
}

.menu button:hover{
    background:#1976D2;
}

</style>

</head>

<body>

<h1>Admin Dashboard</h1>

<div class="card">
Users
<br><br>
<%= users %>
</div>

<div class="card">
Restaurants
<br><br>
<%= restaurants %>
</div>

<div class="card">
Orders
<br><br>
<%= orders %>
</div>

<div class="card">
Revenue
<br><br>
INR <%= revenue %>
</div>

<div class="menu">

<a href="adminRestaurants.jsp">
<button>
Manage Restaurants
</button>
</a>

<a href="adminFoodItems.jsp">
<button>
Manage Food Items
</button>
</a>

<a href="adminOrders.jsp">
<button>
Manage Orders
</button>
</a>

<a href="adminDelivery.jsp">
<button>
Delivery Management
</button>
</a>

<a href="adminDeliveryPartners.jsp">
    <button>
        Manage Delivery Partners
    </button>
</a>

<a href="adminPayments.jsp">
<button>
Manage Payments
</button>
</a>

<a href="adminUsers.jsp">
<button>
Manage Users
</button>
</a>

<a href="AdminLogoutServlet">
<button>
Logout
</button>
</a>

</div>

</body>
</html>