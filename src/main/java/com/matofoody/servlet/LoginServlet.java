package com.matofoody.servlet;

import java.io.IOException;

import com.matofoody.dao.UserDAO;
import com.matofoody.model.User;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

@WebServlet("/LoginServlet")
public class LoginServlet
extends HttpServlet {

    protected void doPost(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException,
            IOException {

        String email =
                request.getParameter("email");

        String password =
                request.getParameter("password");

        UserDAO dao =
                new UserDAO();

        User user =
                dao.loginUser(email,
                              password);

        if(user != null) {

            System.out.println("LOGIN SUCCESS");

            HttpSession session =
                    request.getSession();
            
            System.out.println(
                    "LOGIN USER = "
                    + user.getFullName());

            session.setAttribute("user", user);

            session.setAttribute(
            	    "username",
            	    user.getEmail());

            System.out.println("Redirecting to restaurants.jsp");

            response.sendRedirect("restaurants.jsp");

        }
        else {

            System.out.println("LOGIN FAILED");

            response.getWriter()
                    .println("Invalid Login");
        }
    }
}