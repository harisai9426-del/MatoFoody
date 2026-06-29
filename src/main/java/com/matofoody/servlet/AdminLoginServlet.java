package com.matofoody.servlet;

import java.io.IOException;

import com.matofoody.dao.AdminDAO;
import com.matofoody.model.Admin;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

@WebServlet("/AdminLoginServlet")
public class AdminLoginServlet
extends HttpServlet {

    protected void doPost(
            HttpServletRequest request,
            HttpServletResponse response)
            throws IOException {

        String username =
                request.getParameter("username");

        String password =
                request.getParameter("password");

        AdminDAO dao =
                new AdminDAO();

        Admin admin =
                dao.loginAdmin(
                        username,
                        password);

        if(admin != null) {

            HttpSession session =
                    request.getSession();

            session.setAttribute(
                    "admin",
                    admin);

            response.sendRedirect(
                    "admin.jsp");

        } else {

            response.getWriter()
            .println("Invalid Admin Login");
        }
    }
}
