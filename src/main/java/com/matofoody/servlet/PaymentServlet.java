package com.matofoody.servlet;

import java.io.IOException;


import com.matofoody.dao.PaymentDAO;
import com.matofoody.model.Payment;
import java.util.ArrayList;

import com.matofoody.dao.OrderItemDAO;
import com.matofoody.model.CartItem;
import com.matofoody.model.OrderItem;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;



@WebServlet("/PaymentServlet")
public class PaymentServlet extends HttpServlet{

	@Override
	protected void doPost(HttpServletRequest request,
	                      HttpServletResponse response)
	        throws ServletException, IOException {

		HttpSession session = request.getSession();

		String email = (String) session.getAttribute("username");

		String amountStr = request.getParameter("amount");

		String paymentMethod = request.getParameter("paymentMethod");

		String customerName = request.getParameter("customerName");

		String address = request.getParameter("address");
		
		System.out.println("===============================");
		System.out.println("PAYMENT SERVLET CALLED");
		System.out.println("===============================");

		if(email == null ||
		   amountStr == null ||
		   paymentMethod == null){

		    response.getWriter().println("Missing payment details.");
		    return;
		}

		double amount = Double.parseDouble(amountStr);
		
		session.setAttribute("customerName", customerName);

		session.setAttribute("address", address);

		session.setAttribute("paymentMethod", paymentMethod);

		session.setAttribute("paymentAmount", amount);
		

	    Payment payment = new Payment();

	    int orderId =
	    		Integer.parseInt(
	    		request.getParameter("orderId"));

	    		payment.setOrderId(orderId); // We'll replace this with the real order ID later.
	    payment.setUserEmail(email);
	    payment.setAmount(amount);
	    payment.setPaymentMethod(paymentMethod);
	    payment.setPaymentStatus("SUCCESS");

	    PaymentDAO dao = new PaymentDAO();

	    boolean status = dao.savePayment(payment);
	    
	    if(status){

	        ArrayList<CartItem> cart =
	            (ArrayList<CartItem>)session.getAttribute("cart");

	        OrderItemDAO itemDAO =
	            new OrderItemDAO();

	        for(CartItem item : cart){

	            OrderItem orderItem =
	                new OrderItem();

	            orderItem.setOrderId(orderId);

	            orderItem.setFoodId(item.getFoodId());

	            orderItem.setFoodName(item.getFoodName());

	            orderItem.setQuantity(item.getQuantity());

	            orderItem.setPrice(item.getPrice());

	            orderItem.setAmount(
	                item.getPrice() * item.getQuantity());

	            itemDAO.saveOrderItem(orderItem);
	        }

	        session.removeAttribute("cart");

	        response.sendRedirect("success.jsp");

	    }
	    else{

	        response.getWriter().println("Payment Failed");

	    }
	}

}