package com.matofoody.servlet;

import java.io.IOException;

import com.matofoody.dao.DeliveryPartnerDAO;
import com.matofoody.model.DeliveryPartner;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/AddDeliveryPartnerServlet")
public class AddDeliveryPartnerServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        String partnerName = request.getParameter("partnerName");
        String phone = request.getParameter("phone");
        String vehicle = request.getParameter("vehicle");
        String vehicleNumber = request.getParameter("vehicleNumber");
        String status = request.getParameter("status");

        DeliveryPartner partner = new DeliveryPartner();

        partner.setPartnerName(partnerName);
        partner.setPhone(phone);
        partner.setVehicle(vehicle);
        partner.setVehicleNumber(vehicleNumber);
        partner.setStatus(status);

        DeliveryPartnerDAO dao = new DeliveryPartnerDAO();

        boolean result = dao.addPartner(partner);

        if(result){

            response.sendRedirect("adminDeliveryPartners.jsp");

        }else{

            response.getWriter().println("Failed to Add Delivery Partner");

        }

    }

}
