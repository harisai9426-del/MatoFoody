package com.matofoody.servlet;

import java.io.IOException;

import com.matofoody.dao.UserDAO;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/ChangePasswordServlet")
public class ChangePasswordServlet
extends HttpServlet {

    protected void doPost(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        int userId =
                Integer.parseInt(
                request.getParameter("userId"));

        String password =
                request.getParameter("password");

        UserDAO dao =
                new UserDAO();

        boolean status =
                dao.changePassword(
                        userId,
                        password);

        if(status){

            response.sendRedirect(
                    "profile.jsp");

        }else{

            response.getWriter()
            .println("Password Change Failed");
        }
    }
}
