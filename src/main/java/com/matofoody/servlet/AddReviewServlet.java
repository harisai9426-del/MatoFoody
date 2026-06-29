package com.matofoody.servlet;

import java.io.IOException;


import com.matofoody.dao.ReviewDAO;
import com.matofoody.model.Review;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/AddReviewServlet")
public class AddReviewServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();

        String userEmail = (String) session.getAttribute("username");

        if(userEmail == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        int restaurantId =
                Integer.parseInt(request.getParameter("restaurantId"));

        int rating =
                Integer.parseInt(request.getParameter("rating"));

        String review =
                request.getParameter("review");

        Review r = new Review();

        r.setRestaurantId(restaurantId);
        r.setUserEmail(userEmail);
        r.setRating(rating);
        r.setReview(review);

        ReviewDAO dao = new ReviewDAO();

        dao.addReview(r);

        response.sendRedirect("menu.jsp?id=" + restaurantId);
    }
}
