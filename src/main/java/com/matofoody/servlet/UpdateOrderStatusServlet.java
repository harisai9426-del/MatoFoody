package com.matofoody.servlet;

import java.io.IOException;

import com.matofoody.dao.OrderDAO;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/UpdateOrderStatusServlet")
public class UpdateOrderStatusServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        int orderId =
                Integer.parseInt(
                request.getParameter("id"));

        String status =
                request.getParameter("status");

        OrderDAO dao =
                new OrderDAO();

        dao.updateOrderStatus(
                orderId,
                status);

        response.sendRedirect(
                "adminOrders.jsp");
    }
}