package com.matofoody.servlet;

import java.io.IOException;
import java.util.ArrayList;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import com.matofoody.dao.FoodItemDAO;
import com.matofoody.model.CartItem;
import com.matofoody.model.FoodItem;

@WebServlet("/CartServlet")
public class CartServlet extends HttpServlet {

    protected void doPost(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        int foodId =
                Integer.parseInt(
                        request.getParameter("foodId"));

        FoodItemDAO dao =
                new FoodItemDAO();

        FoodItem food =
                dao.getFoodById(foodId);
        
        System.out.println("Food ID = " + foodId);

        if(food != null){
            System.out.println("Food Found = " + food.getFoodName());
        }else{
            System.out.println("Food Not Found");
        }

        HttpSession session =
                request.getSession();

        ArrayList<CartItem> cart =
                (ArrayList<CartItem>)
                        session.getAttribute("cart");

        if(cart == null) {
            cart = new ArrayList<>();
        }
        
        boolean found = false;

        for(CartItem c : cart) {

            if(c.getFoodId() == food.getFoodId()) {

                c.setQuantity(
                    c.getQuantity() + 1);

                found = true;
                break;
            }
        }

        if(!found) {

            CartItem item1 = new CartItem();

            item1.setFoodId(food.getFoodId());
            item1.setFoodName(food.getFoodName());
            item1.setPrice(food.getPrice());
            item1.setQuantity(1);

            cart.add(item1);
        }
        
        System.out.println("Cart Size = " + cart.size());

        session.setAttribute("cart", cart);
        
        System.out.println("Cart Saved In Session");

        response.sendRedirect(
                "menu.jsp?id=1");
    }
}