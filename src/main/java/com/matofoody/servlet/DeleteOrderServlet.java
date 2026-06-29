package com.matofoody.servlet;

import java.io.IOException;

import com.matofoody.dao.OrderDAO;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/DeleteOrderServlet")
public class DeleteOrderServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        int orderId =
                Integer.parseInt(
               request.getParameter("orderId"));

        System.out.println("Deleting Order ID = " + orderId);

        OrderDAO dao = new OrderDAO();

        boolean status = dao.deleteOrder(orderId);

        if(status){
            response.sendRedirect("adminOrders.jsp");
        }else{
            response.getWriter().println("Order Delete Failed");
        }
    }
}