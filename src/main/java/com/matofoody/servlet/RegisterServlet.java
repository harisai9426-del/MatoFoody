package com.matofoody.servlet;

import java.io.IOException;

import com.matofoody.dao.UserDAO;
import com.matofoody.model.User;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {

    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        doPost(request, response);
    }

    protected void doPost(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

    	String name =
    		    request.getParameter("fullName");

        String email =
                request.getParameter("email");

        String password =
                request.getParameter("password");
        
        System.out.println("Name = " + name);
        System.out.println("Email = " + email);
        System.out.println("Password = " + password);
        
        User user = new User();

        user.setFullName(name);
        user.setEmail(email);
        user.setPassword(password);

        UserDAO dao = new UserDAO();

        boolean status = dao.registerUser(user);

        System.out.println("Register Status = " + status);

        System.out.println(name);
        System.out.println(email);

        response.sendRedirect("login.jsp");
    }
}