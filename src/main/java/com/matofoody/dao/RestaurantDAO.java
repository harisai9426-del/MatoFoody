package com.matofoody.dao;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.matofoody.db.DBConnection;
import com.matofoody.model.Restaurant;

public class RestaurantDAO {

	public ArrayList<Restaurant> getAllRestaurants() {
		
		System.out.println("***** DAO METHOD CALLED *****");

	    ArrayList<Restaurant> list = new ArrayList<>();

	    try {

	        System.out.println("Inside DAO");

	        Connection con = DBConnection.getConnection();

	        System.out.println("Connection = " + con);

	        if(con == null) {
	            System.out.println("CONNECTION IS NULL");
	            return list;
	        }

	        String sql = "SELECT * FROM restaurants";

	        System.out.println(sql);

	        PreparedStatement ps = con.prepareStatement(sql);

	        System.out.println("Prepared Statement Created");

	        ResultSet rs = ps.executeQuery();

	        System.out.println("Query Executed Successfully");

	        while(rs.next()) {

	            System.out.println("Restaurant = "
	                    + rs.getString("restaurant_name"));

	            Restaurant r = new Restaurant();

	            r.setRestaurantId(rs.getInt("restaurant_id"));
	            r.setRestaurantName(rs.getString("restaurant_name"));
	            r.setRestaurantType(rs.getString("restaurant_type"));
	            r.setAddress(rs.getString("address"));
	            r.setRating(rs.getDouble("rating"));
	            r.setImageUrl(rs.getString("image_url"));

	            list.add(r);
	        }

	        System.out.println("Total Records = " + list.size());

	    } catch(Exception e) {
	        System.out.println("ERROR OCCURRED");
	        e.printStackTrace();
	    }

	    return list;
	}
	public boolean addRestaurant(Restaurant restaurant) {

	    boolean status = false;

	    try {

	        Connection con =
	                DBConnection.getConnection();

	        String sql =
	            "INSERT INTO restaurants(restaurant_name,restaurant_type,address,rating) VALUES(?,?,?,?)";

	        PreparedStatement ps =
	                con.prepareStatement(sql);

	        ps.setString(1,
	                restaurant.getRestaurantName());

	        ps.setString(2,
	                restaurant.getRestaurantType());

	        ps.setString(3,
	                restaurant.getAddress());

	        ps.setDouble(4,
	                restaurant.getRating());

	        int rows =
	                ps.executeUpdate();

	        if(rows > 0) {
	            status = true;
	        }

	    } catch(Exception e) {
	        e.printStackTrace();
	    }

	    return status;
	}
	public boolean deleteRestaurant(int restaurantId) {

	    boolean status = false;

	    try {

	        Connection con =
	                DBConnection.getConnection();

	        String sql =
	                "DELETE FROM restaurants "
	                + "WHERE restaurant_id=?";

	        PreparedStatement ps =
	                con.prepareStatement(sql);

	        ps.setInt(1, restaurantId);

	        int rows =
	                ps.executeUpdate();

	        if(rows > 0) {
	            status = true;
	        }

	    } catch(Exception e) {
	        e.printStackTrace();
	    }

	    return status;
	}
	
	public int getRestaurantCount() {

	    int count = 0;

	    try {

	        Connection con =
	                DBConnection.getConnection();

	        String sql =
	                "SELECT COUNT(*) FROM restaurants";

	        PreparedStatement ps =
	                con.prepareStatement(sql);

	        ResultSet rs =
	                ps.executeQuery();

	        if(rs.next()) {
	            count = rs.getInt(1);
	        }

	    } catch(Exception e) {
	        e.printStackTrace();
	    }

	    return count;
	}
	
	public ArrayList<Restaurant> searchRestaurant(String keyword){

	    ArrayList<Restaurant> list = new ArrayList<>();

	    try{

	        Connection con = DBConnection.getConnection();

	        String sql =
	        "SELECT * FROM restaurants WHERE restaurant_name LIKE ?";

	        PreparedStatement ps =
	                con.prepareStatement(sql);

	        ps.setString(1,"%"+keyword+"%");

	        ResultSet rs = ps.executeQuery();

	        while(rs.next()){

	            Restaurant r = new Restaurant();

	            r.setRestaurantId(rs.getInt("restaurant_id"));
	            r.setRestaurantName(rs.getString("restaurant_name"));
	            r.setRestaurantType(rs.getString("restaurant_type"));
	            r.setAddress(rs.getString("address"));
	            r.setRating(rs.getDouble("rating"));
	            r.setImageUrl(rs.getString("image_url"));

	            list.add(r);

	        }

	        con.close();

	    }
	    catch(Exception e){
	        e.printStackTrace();
	    }

	    return list;

	}
}