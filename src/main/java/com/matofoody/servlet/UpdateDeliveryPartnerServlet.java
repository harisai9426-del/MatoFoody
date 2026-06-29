package com.matofoody.servlet;

import java.io.IOException;

import com.matofoody.dao.DeliveryPartnerDAO;
import com.matofoody.model.DeliveryPartner;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

@WebServlet("/UpdateDeliveryPartnerServlet")
public class UpdateDeliveryPartnerServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        DeliveryPartner partner =
                new DeliveryPartner();

        partner.setPartnerId(
                Integer.parseInt(request.getParameter("partnerId")));

        partner.setPartnerName(
                request.getParameter("partnerName"));

        partner.setPhone(
                request.getParameter("phone"));

        partner.setVehicle(
                request.getParameter("vehicle"));

        partner.setVehicleNumber(
                request.getParameter("vehicleNumber"));

        partner.setStatus(
                request.getParameter("status"));

        DeliveryPartnerDAO dao =
                new DeliveryPartnerDAO();

        dao.updatePartner(partner);

        response.sendRedirect("adminDeliveryPartners.jsp");

    }

}
