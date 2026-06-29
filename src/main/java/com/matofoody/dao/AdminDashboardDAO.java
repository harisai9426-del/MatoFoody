package com.matofoody.dao;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.matofoody.db.DBConnection;

public class AdminDashboardDAO {

    public int getTotalUsers() {

        int count = 0;

        try {
            Connection con = DBConnection.getConnection();

            String sql =
                "SELECT COUNT(*) FROM users";

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

    public int getTotalRestaurants() {

        int count = 0;

        try {
            Connection con = DBConnection.getConnection();

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

    public int getTotalOrders() {

        int count = 0;

        try {
            Connection con = DBConnection.getConnection();

            String sql =
                "SELECT COUNT(*) FROM orders";

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

    public double getTotalRevenue() {

        double revenue = 0;

        try {
            Connection con = DBConnection.getConnection();

            String sql =
                "SELECT SUM(total_amount) FROM orders";

            PreparedStatement ps =
                con.prepareStatement(sql);

            ResultSet rs =
                ps.executeQuery();

            if(rs.next()) {
                revenue = rs.getDouble(1);
            }

        } catch(Exception e) {
            e.printStackTrace();
        }

        return revenue;
    }
}