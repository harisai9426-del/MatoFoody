<%@ page import="java.util.ArrayList" %>
<%@ page import="com.matofoody.dao.PaymentDAO" %>
<%@ page import="com.matofoody.model.Payment" %>

<%
PaymentDAO dao = new PaymentDAO();

ArrayList<Payment> payments =
        dao.getAllPayments();
%>

<!DOCTYPE html>
<html>
<head>

<meta charset="UTF-8">

<title>Payment Management</title>

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
    background:#28a745;
    color:white;
    padding:12px;
}

td{
    padding:10px;
    text-align:center;
}

.success{
    color:green;
    font-weight:bold;
}

.failed{
    color:red;
    font-weight:bold;
}

button{
    padding:10px 20px;
    background:#2196F3;
    color:white;
    border:none;
    border-radius:5px;
    cursor:pointer;
}

button:hover{
    background:#1976D2;
}

</style>

</head>

<body>

<h1>Payment Management</h1>

<h3>Total Payments : <%= payments.size() %></h3>

<table>

<tr>

<th>Payment ID</th>

<th>Order ID</th>

<th>Customer Email</th>

<th>Amount</th>

<th>Payment Method</th>

<th>Status</th>

<th>Payment Date</th>

</tr>

<%
for(Payment payment : payments){
%>

<tr>

<td><%= payment.getPaymentId() %></td>

<td><%= payment.getOrderId() %></td>

<td><%= payment.getUserEmail() %></td>

<td>INR <%= payment.getAmount() %></td>

<td><%= payment.getPaymentMethod() %></td>

<td>

<%
if("SUCCESS".equalsIgnoreCase(payment.getPaymentStatus())){
%>

<span class="success">
<%= payment.getPaymentStatus() %>
</span>

<%
}else{
%>

<span class="failed">
<%= payment.getPaymentStatus() %>
</span>

<%
}
%>

</td>

<td><%= payment.getPaymentDate() %></td>

</tr>

<%
}
%>

</table>

<br><br>

<a href="admin.jsp">
<button>Back To Dashboard</button>
</a>

</body>
</html>