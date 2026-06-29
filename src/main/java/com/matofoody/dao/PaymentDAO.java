package com.matofoody.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.matofoody.db.DBConnection;
import com.matofoody.model.Payment;

public class PaymentDAO {

    public boolean savePayment(Payment payment) {

        boolean status = false;

        try {

            Connection con = DBConnection.getConnection();

            String sql = "INSERT INTO payments(order_id,user_email,amount,payment_method,payment_status) VALUES(?,?,?,?,?)";

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, payment.getOrderId());
            ps.setString(2, payment.getUserEmail());
            ps.setDouble(3, payment.getAmount());
            ps.setString(4, payment.getPaymentMethod());
            ps.setString(5, payment.getPaymentStatus());

            int rows = ps.executeUpdate();

            if (rows > 0) {
                status = true;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return status;
    }

    public Payment getPaymentByOrder(int orderId) {

        Payment payment = null;

        try {

            Connection con = DBConnection.getConnection();

            String sql = "SELECT * FROM payments WHERE order_id=?";

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, orderId);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                payment = new Payment();

                payment.setPaymentId(rs.getInt("payment_id"));
                payment.setOrderId(rs.getInt("order_id"));
                payment.setUserEmail(rs.getString("user_email"));
                payment.setAmount(rs.getDouble("amount"));
                payment.setPaymentMethod(rs.getString("payment_method"));
                payment.setPaymentStatus(rs.getString("payment_status"));
                payment.setPaymentDate(rs.getString("payment_date"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return payment;
    }

    public ArrayList<Payment> getAllPayments() {

        ArrayList<Payment> list = new ArrayList<>();

        try {

            Connection con = DBConnection.getConnection();

            String sql = "SELECT * FROM payments ORDER BY payment_date DESC";

            PreparedStatement ps = con.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                Payment payment = new Payment();

                payment.setPaymentId(rs.getInt("payment_id"));
                payment.setOrderId(rs.getInt("order_id"));
                payment.setUserEmail(rs.getString("user_email"));
                payment.setAmount(rs.getDouble("amount"));
                payment.setPaymentMethod(rs.getString("payment_method"));
                payment.setPaymentStatus(rs.getString("payment_status"));
                payment.setPaymentDate(rs.getString("payment_date"));

                list.add(payment);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    public boolean updatePaymentStatus(int paymentId, String status) {

        boolean result = false;

        try {

            Connection con = DBConnection.getConnection();

            String sql = "UPDATE payments SET payment_status=? WHERE payment_id=?";

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, status);
            ps.setInt(2, paymentId);

            int rows = ps.executeUpdate();

            if (rows > 0) {
                result = true;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }
}