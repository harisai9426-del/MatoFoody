package com.matofoody.servlet;

import java.io.IOException;
import java.util.ArrayList;

import com.matofoody.model.CartItem;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

@WebServlet("/DecreaseQtyServlet")
public class DecreaseQtyServlet extends HttpServlet {

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

            for(int i = 0; i < cart.size(); i++){

                CartItem item = cart.get(i);

                if(item.getFoodId() == foodId){

                    if(item.getQuantity() > 1){

                        item.setQuantity(item.getQuantity() - 1);

                    }else{

                        cart.remove(i);
                    }

                    break;
                }
            }

            session.setAttribute("cart", cart);
        }

        response.sendRedirect("cart.jsp");
    }

}
