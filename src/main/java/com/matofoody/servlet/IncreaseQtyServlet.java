package com.matofoody.servlet;

import java.io.IOException;
import java.util.ArrayList;

import com.matofoody.model.CartItem;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

@WebServlet("/IncreaseQtyServlet")
public class IncreaseQtyServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        int foodId =
                Integer.parseInt(request.getParameter("foodId"));

        HttpSession session = request.getSession();

        ArrayList<CartItem> cart =
                (ArrayList<CartItem>) session.getAttribute("cart");

        if(cart != null){

            for(CartItem item : cart){

                if(item.getFoodId() == foodId){

                    item.setQuantity(item.getQuantity() + 1);

                    break;
                }
            }

            session.setAttribute("cart", cart);
        }

        response.sendRedirect("cart.jsp");
    }

}
