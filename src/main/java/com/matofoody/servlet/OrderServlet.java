package com.matofoody.servlet;

import java.io.IOException;



import com.matofoody.dao.OrderDAO;
import com.matofoody.model.Order;
import java.util.ArrayList;
import com.matofoody.model.CartItem;
import com.matofoody.model.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/OrderServlet")
public class OrderServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
    	
    	System.out.println("=================================");
    	System.out.println("ORDER SERVLET CALLED");
    	System.out.println("=================================");

    	HttpSession session =
    	        request.getSession();

    	User user =
    	        (User) session.getAttribute("user");
    	
    	System.out.println("USER OBJECT = " + user);
    	

    	if(user != null){
    	    System.out.println("FULL NAME = " + user.getFullName());
    	}


    	String customerName = user.getFullName();

    	if(customerName == null && user != null){
    	    customerName = user.getFullName();
    	}

    	String address =
    		    request.getParameter("address");
    	
    	ArrayList<CartItem> cart =
    	        (ArrayList<CartItem>) session.getAttribute("cart");

    	System.out.println("Customer Name = " + customerName);
    	System.out.println("Address = " + address);
    	System.out.println("Cart = " + cart);

    	if(cart == null || cart.isEmpty()){

    	    response.getWriter().println("Cart is Empty");

    	    return;
    	}

        double totalAmount = 0;

        for(CartItem item : cart)
        {
            totalAmount += item.getPrice() * item.getQuantity();
        }

        Order order = new Order();

        order.setCustomerName(customerName);
        order.setAddress(address);
        order.setQuantity(cart.size());
        order.setTotalAmount(totalAmount);

        OrderDAO dao = new OrderDAO();
        
        System.out.println("Customer = " + customerName);
        System.out.println("Address = " + address);
        System.out.println("Quantity = " + cart.size());
        System.out.println("Total Amount = " + totalAmount);

        int orderId =
                dao.placeOrder(order);

        if(orderId > 0){
        	
        	session.setAttribute("orderId", orderId);
        	session.setAttribute("orderAmount", totalAmount);

        	// Keep the cart until payment is successful
        	
        	System.out.println("Order ID = " + orderId);
        	System.out.println("Customer Name = " + customerName);
        	System.out.println("Address = " + address);
        	System.out.println("Total Amount = " + totalAmount);

        	response.sendRedirect(
        		    "payment.jsp?orderId=" + orderId +
        		    "&amount=" + totalAmount +
        		    "&customerName=" + customerName +
        		    "&address=" + address);
        } else {

            response.getWriter()
                    .println("Order Failed");
        }
    }
}