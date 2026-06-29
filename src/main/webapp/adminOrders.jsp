<%@ page import="java.util.ArrayList" %>
<%@ page import="com.matofoody.dao.OrderDAO" %>
<%@ page import="com.matofoody.model.Order" %>
<%@ page import="com.matofoody.dao.OrderItemDAO" %>
<%@ page import="com.matofoody.model.OrderItem" %>

<%
OrderDAO dao = new OrderDAO();
OrderItemDAO itemDAO = new OrderItemDAO();

ArrayList<Order> orders =
        dao.getAllOrders();
%>

<!DOCTYPE html>
<html>
<head>

<meta charset="UTF-8">

<title>Admin Orders</title>

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

.updateBtn{
    text-decoration:none;
    padding:5px 10px;
    background:#28a745;
    color:white;
    border-radius:5px;
    margin:2px;
}

.statusPlaced{
    color:blue;
    font-weight:bold;
}

.statusPreparing{
    color:orange;
    font-weight:bold;
}

.statusDelivered{
    color:green;
    font-weight:bold;
}

</style>

</head>

<body>

<h1>Order Management</h1>

<h3>Total Orders : <%= orders.size() %></h3>

<table>

<tr>
    <th>Order ID</th>
    <th>Customer</th>
    <th>Address</th>
   <th>Items Ordered</th>
    <th>Quantity</th>
    <th>Total Amount</th>
    <th>Status</th>
    <th>Update Status</th>
    <th>Delete</th>
    <th>Order Date</th>
</tr>

<%
for(Order order : orders){
%>

<tr>

    <td>
        <%= order.getOrderId() %>
    </td>

    <td>
        <%= order.getCustomerName() %>
    </td>

    <td>
        <%= order.getAddress() %>
    </td>

   <td align="left">

<%
ArrayList<OrderItem> items =
        itemDAO.getOrderItems(order.getOrderId());

for(OrderItem item : items){
%>

<%= item.getFoodName() %>

(Qty:
<%= item.getQuantity() %>)

<br>

<%
}
%>

</td>

    <td>
        <%= order.getQuantity() %>
    </td>

    <td>
        INR <%= order.getTotalAmount() %>
    </td>

    <td>

        <%
        String status = order.getStatus();

        if(status == null){
            status = "Placed";
        }

        if(status.equalsIgnoreCase("Placed")){
        %>

            <span class="statusPlaced">
                <%= status %>
            </span>

        <%
        }
        else if(status.equalsIgnoreCase("Preparing")){
        %>

            <span class="statusPreparing">
                <%= status %>
            </span>

        <%
        }
        else{
        %>

            <span class="statusDelivered">
                <%= status %>
            </span>

        <%
        }
        %>

    </td>

    <td>

       <a class="updateBtn"
href="UpdateOrderStatusServlet?id=<%=order.getOrderId()%>&status=Preparing">
Preparing
</a>

<a class="updateBtn"
href="UpdateOrderStatusServlet?id=<%=order.getOrderId()%>&status=Ready">
Ready
</a>

<a class="updateBtn"
href="UpdateOrderStatusServlet?id=<%=order.getOrderId()%>&status=Out For Delivery">
Out For Delivery
</a>

<a class="updateBtn"
href="UpdateOrderStatusServlet?id=<%=order.getOrderId()%>&status=Delivered">
Delivered
</a>

    </td>

    <td>

        <a class="delete"
        href="DeleteOrderServlet?orderId=<%=order.getOrderId()%>">
        Delete
        </a>

    </td>
    
    <td>
    <%= order.getOrderDate() %>
</td>

</tr>

<%
}
%>

</table>

<br><br>

<a href="admin.jsp">
<button style="padding:10px 20px;">
Back To Dashboard
</button>
</a>

</body>
</html>