package com.matofoody.servlet;

import java.io.IOException;

import com.matofoody.dao.FoodItemDAO;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/DeleteFoodItemServlet")
public class DeleteFoodItemServlet extends HttpServlet {

    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        int foodId =
                Integer.parseInt(
                request.getParameter("foodId"));

        FoodItemDAO dao =
                new FoodItemDAO();

        dao.deleteFoodItem(foodId);

        response.sendRedirect(
                "adminFoodItems.jsp");
    }
}