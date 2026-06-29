package com.matofoody.servlet;

import java.io.IOException;

import com.matofoody.dao.RestaurantDAO;
import com.matofoody.model.Restaurant;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/AddRestaurantServlet")
public class AddRestaurantServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        String name =
                request.getParameter("restaurantName");

        String type =
                request.getParameter("restaurantType");

        String address =
                request.getParameter("address");

        double rating =
                Double.parseDouble(
                request.getParameter("rating"));

        Restaurant restaurant =
                new Restaurant();

        restaurant.setRestaurantName(name);
        restaurant.setRestaurantType(type);
        restaurant.setAddress(address);
        restaurant.setRating(rating);

        RestaurantDAO dao =
                new RestaurantDAO();

        boolean status =
                dao.addRestaurant(restaurant);

        if(status) {
            response.sendRedirect(
                    "adminRestaurants.jsp");
        }
        else {
            response.getWriter()
                    .println("Restaurant Add Failed");
        }
    }
}