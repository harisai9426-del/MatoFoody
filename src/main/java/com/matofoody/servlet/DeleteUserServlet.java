package com.matofoody.servlet;

import java.io.IOException;

import com.matofoody.dao.UserDAO;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/DeleteUserServlet")
public class DeleteUserServlet extends HttpServlet {
protected void doGet(HttpServletRequest request,
        HttpServletResponse response)
        throws ServletException, IOException {

	int userId =
		    Integer.parseInt(
		    request.getParameter("userId"));

    UserDAO dao =
            new UserDAO();

    dao.deleteUser(userId);

    response.sendRedirect(
            "adminUsers.jsp");
}
}
