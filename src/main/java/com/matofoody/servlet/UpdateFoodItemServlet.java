package com.matofoody.servlet;

import java.io.IOException;

import com.matofoody.dao.FoodItemDAO;
import com.matofoody.model.FoodItem;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/UpdateFoodItemServlet")
public class UpdateFoodItemServlet extends HttpServlet {

    @Override
    protected void doPost(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        FoodItem item = new FoodItem();

        item.setFoodId(
                Integer.parseInt(
                request.getParameter("foodId")));

        item.setFoodName(
                request.getParameter("foodName"));

        item.setFoodType(
                request.getParameter("foodType"));

        item.setPrice(
                Double.parseDouble(
                request.getParameter("price")));

        item.setDescription(
                request.getParameter("description"));

        FoodItemDAO dao =
                new FoodItemDAO();

        boolean status =
                dao.updateFoodItem(item);

        if(status) {

            response.sendRedirect(
                    "adminFoodItems.jsp");

        } else {

            response.getWriter()
                    .println("Food Item Update Failed");
        }
    }
}