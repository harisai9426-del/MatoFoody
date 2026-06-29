package com.matofoody.dao;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.matofoody.db.DBConnection;
import com.matofoody.model.FoodItem;

public class FoodItemDAO {

    public ArrayList<FoodItem> getFoodItemsByRestaurant(int restaurantId) {

        ArrayList<FoodItem> list = new ArrayList<>();

        try {

            System.out.println("===== FOOD DAO CALLED =====");
            System.out.println("Restaurant ID = " + restaurantId);

            Connection con = DBConnection.getConnection();

            System.out.println("Connection = " + con);

            if (con == null) {
                System.out.println("CONNECTION IS NULL");
                return list;
            }

            String sql = "SELECT * FROM food_items WHERE restaurant_id = ?";

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, restaurantId);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                FoodItem food = new FoodItem();

                food.setFoodId(rs.getInt("food_id"));
                food.setRestaurantId(rs.getInt("restaurant_id"));
                food.setFoodName(rs.getString("food_name"));
                food.setFoodType(rs.getString("food_type"));
                food.setPrice(rs.getDouble("price"));
                food.setDescription(rs.getString("description"));

                list.add(food);

                System.out.println("Food Item = "
                        + rs.getString("food_name"));
            }

            System.out.println("Total Food Items = " + list.size());

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }
    public FoodItem getFoodById(int foodId) {

        FoodItem food = null;

        try {

            Connection con = DBConnection.getConnection();

            String sql =
                    "SELECT * FROM food_items WHERE food_id=?";

            PreparedStatement ps =
                    con.prepareStatement(sql);

            ps.setInt(1, foodId);

            ResultSet rs =
                    ps.executeQuery();

            if(rs.next()) {

                food = new FoodItem();

                food.setFoodId(
                        rs.getInt("food_id"));

                food.setRestaurantId(
                        rs.getInt("restaurant_id"));

                food.setFoodName(
                        rs.getString("food_name"));

                food.setCategory(
                        rs.getString("food_type"));

                food.setPrice(
                        rs.getDouble("price"));

                food.setDescription(
                        rs.getString("description"));
            }

        } catch(Exception e) {
            e.printStackTrace();
        }

        return food;
    }
    public ArrayList<FoodItem> getAllFoodItems() {

        ArrayList<FoodItem> list =
                new ArrayList<FoodItem>();

        try {

            Connection con =
                    DBConnection.getConnection();

            String sql =
                    "SELECT * FROM food_items";

            PreparedStatement ps =
                    con.prepareStatement(sql);

            ResultSet rs =
                    ps.executeQuery();

            while(rs.next()) {

                FoodItem item =
                        new FoodItem();

                item.setFoodId(
                        rs.getInt("food_id"));

                item.setRestaurantId(
                        rs.getInt("restaurant_id"));

                item.setCategoryId(
                        rs.getInt("category_id"));

                item.setFoodName(
                        rs.getString("food_name"));

                item.setFoodType(
                        rs.getString("food_type"));

                item.setPrice(
                        rs.getDouble("price"));

                item.setDescription(
                        rs.getString("description"));

                list.add(item);
            }

        } catch(Exception e) {
            e.printStackTrace();
        }

        return list;
    }
    public boolean addFoodItem(FoodItem item) {

        boolean status = false;

        try {

            Connection con =
                    DBConnection.getConnection();

            String sql =
                    "INSERT INTO food_items(restaurant_id,category_id,food_name,food_type,price,description) VALUES(?,?,?,?,?,?)";

            PreparedStatement ps =
                    con.prepareStatement(sql);

            ps.setInt(1,
                    item.getRestaurantId());

            ps.setInt(2,
                    item.getCategoryId());

            ps.setString(3,
                    item.getFoodName());

            ps.setString(4,
                    item.getFoodType());

            ps.setDouble(5,
                    item.getPrice());

            ps.setString(6,
                    item.getDescription());

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
    
    public boolean deleteFoodItem(int foodId) {

        boolean status = false;

        try {

            Connection con =
                    DBConnection.getConnection();

            String sql =
                    "DELETE FROM food_items WHERE food_id=?";

            PreparedStatement ps =
                    con.prepareStatement(sql);

            ps.setInt(1, foodId);

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
    
    public FoodItem getFoodItemById(int foodId) {

        FoodItem item = null;

        try {

            Connection con =
                    DBConnection.getConnection();

            String sql =
                    "SELECT * FROM food_items WHERE food_id=?";

            PreparedStatement ps =
                    con.prepareStatement(sql);

            ps.setInt(1, foodId);

            ResultSet rs =
                    ps.executeQuery();

            if(rs.next()) {

                item = new FoodItem();

                item.setFoodId(
                        rs.getInt("food_id"));

                item.setRestaurantId(
                        rs.getInt("restaurant_id"));

                item.setCategoryId(
                        rs.getInt("category_id"));

                item.setFoodName(
                        rs.getString("food_name"));

                item.setFoodType(
                        rs.getString("food_type"));

                item.setPrice(
                        rs.getDouble("price"));

                item.setDescription(
                        rs.getString("description"));
            }

        } catch(Exception e) {
            e.printStackTrace();
        }

        return item;
    }
    public boolean updateFoodItem(FoodItem item) {

        boolean status = false;

        try {

            Connection con =
                    DBConnection.getConnection();

            String sql =
                    "UPDATE food_items SET food_name=?, food_type=?, price=?, description=? WHERE food_id=?";

            PreparedStatement ps =
                    con.prepareStatement(sql);

            ps.setString(1,
                    item.getFoodName());

            ps.setString(2,
                    item.getFoodType());

            ps.setDouble(3,
                    item.getPrice());

            ps.setString(4,
                    item.getDescription());

            ps.setInt(5,
                    item.getFoodId());

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
    
    public int getFoodItemCount() {

        int count = 0;

        try {

            Connection con =
                    DBConnection.getConnection();

            String sql =
                    "SELECT COUNT(*) FROM food_items";

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
    
    public ArrayList<FoodItem> searchFoodItems(
            int restaurantId,
            String searchText){

        ArrayList<FoodItem> list =
                new ArrayList<>();

        try{

            Connection con =
                    DBConnection.getConnection();

            String sql =
            "SELECT * FROM food_items " +
            "WHERE restaurant_id=? " +
            "AND food_name LIKE ?";

            PreparedStatement ps =
                    con.prepareStatement(sql);

            ps.setInt(1, restaurantId);
            ps.setString(2,
                    "%" + searchText + "%");

            ResultSet rs =
                    ps.executeQuery();

            while(rs.next()){

                FoodItem food =
                        new FoodItem();

                food.setFoodId(
                        rs.getInt("food_id"));

                food.setFoodName(
                        rs.getString("food_name"));

                food.setPrice(
                        rs.getDouble("price"));

                food.setFoodType(
                        rs.getString("food_type"));

                list.add(food);
            }

        }catch(Exception e){
            e.printStackTrace();
        }

        return list;
    }
    
    public ArrayList<FoodItem> getFoodByCategory(
            int restaurantId,
            String category) {

        ArrayList<FoodItem> list =
                new ArrayList<>();

        try {

            Connection con =
                    DBConnection.getConnection();

            String sql =
            "SELECT * FROM food_items WHERE restaurant_id=? AND food_type=?";

            PreparedStatement ps =
                    con.prepareStatement(sql);

            ps.setInt(1, restaurantId);
            ps.setString(2, category);

            ResultSet rs =
                    ps.executeQuery();

            while(rs.next()) {

                FoodItem item =
                        new FoodItem();

                item.setFoodId(
                        rs.getInt("food_id"));

                item.setRestaurantId(
                        rs.getInt("restaurant_id"));

                item.setFoodName(
                        rs.getString("food_name"));

                item.setFoodType(
                        rs.getString("food_type"));

                item.setPrice(
                        rs.getDouble("price"));

                item.setDescription(
                        rs.getString("description"));

                list.add(item);
            }

        } catch(Exception e) {
            e.printStackTrace();
        }

        return list;
    }
    
    
}