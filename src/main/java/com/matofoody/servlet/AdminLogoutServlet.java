package com.matofoody.servlet;

import java.io.IOException;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

@WebServlet("/AdminLogoutServlet")
public class AdminLogoutServlet
extends HttpServlet {

    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response)
            throws IOException {

        HttpSession session =
                request.getSession(false);

        if(session != null){
            session.invalidate();
        }

        response.sendRedirect(
                "adminLogin.jsp");
    }
}