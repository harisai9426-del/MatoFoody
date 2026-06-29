package com.matofoody.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.matofoody.db.DBConnection;
import com.matofoody.model.User;

public class UserDAO {

    public boolean registerUser(User user) {

        boolean status = false;

        try {

            Connection con =
                    DBConnection.getConnection();

            String sql =
                    "INSERT INTO users(full_name,email,password) VALUES(?,?,?)";

            PreparedStatement ps =
                    con.prepareStatement(sql);

            ps.setString(1, user.getFullName());
            ps.setString(2, user.getEmail());
            ps.setString(3, user.getPassword());

            int rows = ps.executeUpdate();

            if(rows > 0) {
                status = true;
            }

        } catch(Exception e) {
            e.printStackTrace();
        }

        return status;
    }

    public User loginUser(String email, String password) {

        User user = null;

        try {

            Connection con =
                    DBConnection.getConnection();

            String sql =
                    "SELECT * FROM users WHERE email=? AND password=?";

            PreparedStatement ps =
                    con.prepareStatement(sql);

            ps.setString(1, email);
            ps.setString(2, password);

            ResultSet rs =
                    ps.executeQuery();

            if(rs.next()) {

                user = new User();

                user.setUserId(
                        rs.getInt("user_id"));

                user.setFullName(
                        rs.getString("full_name"));

                user.setEmail(
                        rs.getString("email"));

                user.setPassword(
                        rs.getString("password"));
            }

        } catch(Exception e) {
            e.printStackTrace();
        }

        return user;
    }

    public boolean updateUser(User user) {

        boolean status = false;

        try {

            Connection con =
                    DBConnection.getConnection();

            String sql =
                    "UPDATE users SET full_name=?, email=? WHERE user_id=?";

            PreparedStatement ps =
                    con.prepareStatement(sql);

            ps.setString(1, user.getFullName());
            ps.setString(2, user.getEmail());
            ps.setInt(3, user.getUserId());

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

    public boolean changePassword(int userId,
                                  String password) {

        boolean status = false;

        try {

            Connection con =
                    DBConnection.getConnection();

            String sql =
                    "UPDATE users SET password=? WHERE user_id=?";

            PreparedStatement ps =
                    con.prepareStatement(sql);

            ps.setString(1, password);
            ps.setInt(2, userId);

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

    public int getUserCount() {

        int count = 0;

        try {

            Connection con =
                    DBConnection.getConnection();

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

    public ArrayList<User> getAllUsers() {

        ArrayList<User> list =
                new ArrayList<User>();

        try {

            Connection con =
                    DBConnection.getConnection();

            String sql =
                    "SELECT * FROM users ORDER BY user_id DESC";

            PreparedStatement ps =
                    con.prepareStatement(sql);

            ResultSet rs =
                    ps.executeQuery();

            while(rs.next()) {

                User user =
                        new User();

                user.setUserId(
                        rs.getInt("user_id"));

                user.setFullName(
                        rs.getString("full_name"));

                user.setEmail(
                        rs.getString("email"));

                user.setPassword(
                        rs.getString("password"));

                list.add(user);
            }

        } catch(Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    public boolean deleteUser(int userId) {

        boolean status = false;

        try {

            Connection con =
                    DBConnection.getConnection();

            String sql =
                    "DELETE FROM users WHERE user_id=?";

            PreparedStatement ps =
                    con.prepareStatement(sql);

            ps.setInt(1, userId);

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
}