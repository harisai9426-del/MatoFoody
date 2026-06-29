package com.matofoody.servlet;

import java.io.IOException;

import com.matofoody.dao.OrderDAO;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/AssignDeliveryServlet")
public class AssignDeliveryServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        int orderId =
                Integer.parseInt(request.getParameter("orderId"));

        int partnerId =
                Integer.parseInt(request.getParameter("partnerId"));

        OrderDAO dao = new OrderDAO();

        boolean status =
                dao.assignDeliveryPartner(orderId, partnerId);

        if(status){

            response.sendRedirect("adminDelivery.jsp");

        }else{

            response.getWriter().println("Assignment Failed");

        }

    }

}