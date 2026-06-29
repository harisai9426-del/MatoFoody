package com.matofoody.servlet;

import java.io.IOException;

import com.matofoody.dao.DeliveryPartnerDAO;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

@WebServlet("/DeleteDeliveryPartnerServlet")
public class DeleteDeliveryPartnerServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        int partnerId =
                Integer.parseInt(request.getParameter("partnerId"));

        DeliveryPartnerDAO dao =
                new DeliveryPartnerDAO();

        dao.deletePartner(partnerId);

        response.sendRedirect("adminDeliveryPartners.jsp");

    }

}