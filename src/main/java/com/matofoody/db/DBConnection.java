package com.matofoody.db;

import java.sql.Connection;

import java.sql.DriverManager;

public class DBConnection {

    private static Connection con;

    public static Connection getConnection() {

        try {

            System.out.println("Loading Driver...");

            Class.forName("com.mysql.cj.jdbc.Driver");

            System.out.println("Driver Loaded");

            con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/matofoody_db",
                    "root",
                    "9848076890aA@");

            System.out.println("Database Connected Successfully");
            System.out.println("Connection Object = " + con);

        } catch (Exception e) {

            System.out.println("DATABASE CONNECTION ERROR");
            e.printStackTrace();
        }

        return con;
    }
}