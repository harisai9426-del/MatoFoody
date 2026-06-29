package com.matofoody.servlet;

import java.io.IOException;

import com.matofoody.dao.RestaurantDAO;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/DeleteRestaurantServlet")
public class DeleteRestaurantServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        int restaurantId =
                Integer.parseInt(
                request.getParameter("restaurantId"));

        RestaurantDAO dao =
                new RestaurantDAO();

        dao.deleteRestaurant(restaurantId);

        response.sendRedirect("adminRestaurants.jsp");
    }
}