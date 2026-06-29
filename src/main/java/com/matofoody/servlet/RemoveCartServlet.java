package com.matofoody.servlet;

import java.io.IOException;
import java.util.ArrayList;

import com.matofoody.model.CartItem;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/RemoveFromCartServlet")
public class RemoveCartServlet extends HttpServlet {

    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        int foodId =
                Integer.parseInt(
                        request.getParameter("foodId"));

        HttpSession session =
                request.getSession();

        ArrayList<CartItem> cart =
                (ArrayList<CartItem>)
                        session.getAttribute("cart");

        if(cart != null) {

            cart.removeIf(
                    item -> item.getFoodId() == foodId);

            session.setAttribute("cart", cart);
        }

        response.sendRedirect("cart.jsp");
    }
}