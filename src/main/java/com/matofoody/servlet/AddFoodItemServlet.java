package com.matofoody.servlet;

import java.io.IOException;

import com.matofoody.dao.FoodItemDAO;
import com.matofoody.model.FoodItem;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/AddFoodItemServlet")
public class AddFoodItemServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest request,
	        HttpServletResponse response)
	        throws ServletException, IOException {

	    try {

	        System.out.println("===== ADD FOOD ITEM CALLED =====");

	        FoodItem item = new FoodItem();

	        item.setRestaurantId(
	                Integer.parseInt(
	                request.getParameter("restaurantId")));

	        item.setCategoryId(
	                Integer.parseInt(
	                request.getParameter("categoryId")));

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
	                dao.addFoodItem(item);

	        System.out.println("Status = " + status);

	        if(status) {

	            response.sendRedirect(
	                    "adminFoodItems.jsp");

	        } else {

	            response.getWriter()
	                    .println("Food Item Not Added");
	        }

	    } catch(Exception e) {

	        e.printStackTrace();

	        response.getWriter()
	                .println("ERROR : " + e.getMessage());
	    }
	}
    }