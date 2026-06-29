package com.matofoody.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.matofoody.db.DBConnection;
import com.matofoody.model.OrderItem;

public class OrderItemDAO {

    public boolean saveOrderItem(OrderItem item) {

        boolean status = false;

        try {

            Connection con = DBConnection.getConnection();

            String sql =
                "INSERT INTO order_items(order_id,food_id,food_name,quantity,price,amount) VALUES(?,?,?,?,?,?)";

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, item.getOrderId());
            ps.setInt(2, item.getFoodId());
            ps.setString(3, item.getFoodName());
            ps.setInt(4, item.getQuantity());
            ps.setDouble(5, item.getPrice());
            ps.setDouble(6, item.getAmount());

            int rows = ps.executeUpdate();

            if(rows > 0) {
                status = true;
            }

        } catch(Exception e) {
            e.printStackTrace();
        }

        return status;
    }

    public ArrayList<OrderItem> getOrderItems(int orderId) {

        ArrayList<OrderItem> list = new ArrayList<>();

        try {

            Connection con = DBConnection.getConnection();

            String sql =
                "SELECT * FROM order_items WHERE order_id=?";

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, orderId);

            ResultSet rs = ps.executeQuery();

            while(rs.next()) {

                OrderItem item = new OrderItem();

                item.setOrderItemId(rs.getInt("order_item_id"));
                item.setOrderId(rs.getInt("order_id"));
                item.setFoodId(rs.getInt("food_id"));
                item.setFoodName(rs.getString("food_name"));
                item.setQuantity(rs.getInt("quantity"));
                item.setPrice(rs.getDouble("price"));
                item.setAmount(rs.getDouble("amount"));

                list.add(item);
            }

        } catch(Exception e) {
            e.printStackTrace();
        }

        return list;
    }
    
    
}
