package com.matofoody.dao;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.matofoody.db.DBConnection;
import com.matofoody.model.Order;

public class OrderHistoryDAO {

    public ArrayList<Order> getAllOrders() {

        ArrayList<Order> list = new ArrayList<>();

        try {

            Connection con =
                    DBConnection.getConnection();

            String sql =
                    "SELECT * FROM orders ORDER BY order_id DESC";

            PreparedStatement ps =
                    con.prepareStatement(sql);

            ResultSet rs =
                    ps.executeQuery();

            while(rs.next()) {

                Order order = new Order();

                order.setOrderId(
                        rs.getInt("order_id"));

                order.setCustomerName(
                        rs.getString("customer_name"));

                order.setAddress(
                        rs.getString("address"));

                order.setQuantity(
                        rs.getInt("quantity"));

                order.setTotalAmount(
                        rs.getDouble("total_amount"));

                list.add(order);
            }

        } catch(Exception e) {
            e.printStackTrace();
        }

        return list;
    }
}