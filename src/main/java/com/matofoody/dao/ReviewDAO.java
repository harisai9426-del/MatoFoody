package com.matofoody.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.util.ArrayList;

import com.matofoody.db.DBConnection;
import com.matofoody.model.Review;

public class ReviewDAO {

    public boolean addReview(Review review) {

        boolean status = false;

        try {

            Connection con = DBConnection.getConnection();

            String sql =
            "INSERT INTO reviews(restaurant_id,user_email,rating,review) VALUES(?,?,?,?)";

            PreparedStatement ps =
            con.prepareStatement(sql);

            ps.setInt(1, review.getRestaurantId());
            ps.setString(2, review.getUserEmail());
            ps.setInt(3, review.getRating());
            ps.setString(4, review.getReview());

            status = ps.executeUpdate() > 0;

            con.close();

        } catch(Exception e) {
            e.printStackTrace();
        }

        return status;
    }
    
    public ArrayList<Review> getReviewsByRestaurant(int restaurantId){

        ArrayList<Review> list = new ArrayList<>();

        try{

            Connection con = DBConnection.getConnection();

            String sql =
            "SELECT * FROM reviews WHERE restaurant_id=? ORDER BY review_date DESC";

            PreparedStatement ps =
            con.prepareStatement(sql);

            ps.setInt(1, restaurantId);

            ResultSet rs = ps.executeQuery();

            while(rs.next()){

                Review r = new Review();

                r.setReviewId(rs.getInt("review_id"));
                r.setRestaurantId(rs.getInt("restaurant_id"));
                r.setUserEmail(rs.getString("user_email"));
                r.setRating(rs.getInt("rating"));
                r.setReview(rs.getString("review"));
                r.setReviewDate(rs.getString("review_date"));

                list.add(r);
            }

            con.close();

        }catch(Exception e){
            e.printStackTrace();
        }

        return list;
    }
    
    public double getAverageRating(int restaurantId){

        double rating = 0;

        try{

            Connection con = DBConnection.getConnection();

            String sql =
            "SELECT AVG(rating) average FROM reviews WHERE restaurant_id=?";

            PreparedStatement ps =
            con.prepareStatement(sql);

            ps.setInt(1, restaurantId);

            ResultSet rs = ps.executeQuery();

            if(rs.next()){

                rating = rs.getDouble("average");
            }

            con.close();

        }catch(Exception e){
            e.printStackTrace();
        }

        return rating;
    }
}