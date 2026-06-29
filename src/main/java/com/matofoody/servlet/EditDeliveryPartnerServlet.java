package com.matofoody.servlet;

import java.io.IOException;

import com.matofoody.dao.DeliveryPartnerDAO;
import com.matofoody.model.DeliveryPartner;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

@WebServlet("/EditDeliveryPartnerServlet")
public class EditDeliveryPartnerServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        int partnerId =
                Integer.parseInt(request.getParameter("partnerId"));

        DeliveryPartnerDAO dao =
                new DeliveryPartnerDAO();

        DeliveryPartner partner =
                dao.getPartnerById(partnerId);

        request.setAttribute("partner", partner);

        request.getRequestDispatcher("editDeliveryPartner.jsp")
               .forward(request, response);

    }

}