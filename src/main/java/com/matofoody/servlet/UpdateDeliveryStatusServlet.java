package com.matofoody.servlet;

import java.io.IOException;

import com.matofoody.dao.OrderDAO;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

@WebServlet("/UpdateDeliveryStatusServlet")
public class UpdateDeliveryStatusServlet extends HttpServlet{

    @Override
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        int orderId =
                Integer.parseInt(request.getParameter("orderId"));

        String status =
                request.getParameter("status");
        
        System.out.println("========== UPDATE DELIVERY ==========");
        System.out.println("Order ID = " + orderId);
        System.out.println("Status = " + status);
        System.out.println("=====================================");

        OrderDAO dao = new OrderDAO();

        dao.updateDeliveryStatus(orderId, status);

        response.sendRedirect("adminDelivery.jsp");

    }

}