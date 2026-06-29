package com.matofoody.servlet;

import java.io.IOException;

import com.matofoody.dao.UserDAO;
import com.matofoody.model.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/UpdateProfileServlet")
public class UpdateProfileServlet extends HttpServlet {

    protected void doPost(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        int userId =
                Integer.parseInt(
                request.getParameter("userId"));

        String fullName =
                request.getParameter("fullName");

        String email =
                request.getParameter("email");

        User user = new User();

        user.setUserId(userId);
        user.setFullName(fullName);
        user.setEmail(email);

        UserDAO dao =
                new UserDAO();

        boolean status =
                dao.updateUser(user);

        if(status){

            HttpSession session =
                    request.getSession();

            session.setAttribute(
                    "user",
                    user);

            response.sendRedirect(
                    "profile.jsp");

        }else{

            response.getWriter()
            .println("Profile Update Failed");
        }
    }
}