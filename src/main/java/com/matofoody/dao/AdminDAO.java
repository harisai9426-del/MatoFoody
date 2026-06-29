package com.matofoody.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.matofoody.db.DBConnection;
import com.matofoody.model.Admin;

public class AdminDAO {

    public Admin loginAdmin(
            String username,
            String password) {

        Admin admin = null;

        try {

            Connection con =
                    DBConnection.getConnection();

            String sql =
                    "SELECT * FROM admin WHERE username=? AND password=?";

            PreparedStatement ps =
                    con.prepareStatement(sql);

            ps.setString(1, username);
            ps.setString(2, password);

            ResultSet rs =
                    ps.executeQuery();

            if(rs.next()) {

                admin = new Admin();

                admin.setAdminId(
                        rs.getInt("admin_id"));

                admin.setUsername(
                        rs.getString("username"));

                admin.setPassword(
                        rs.getString("password"));
            }

        } catch(Exception e) {
            e.printStackTrace();
        }

        return admin;
    }
}