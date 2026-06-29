package com.matofoody.dao;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.matofoody.db.DBConnection;
import com.matofoody.model.Order;

public class OrderDAO {
	public int placeOrder(Order order) {

	    int orderId = -1;

	    try {

	        Connection con = DBConnection.getConnection();

	        String sql =
	        "INSERT INTO orders(customer_name,address,food_id,quantity,total_amount,status) VALUES(?,?,?,?,?,?)";

	        PreparedStatement ps =
	        con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

	        ps.setString(1, order.getCustomerName());
	        ps.setString(2, order.getAddress());
	        ps.setInt(3, order.getFoodId());
	        ps.setInt(4, order.getQuantity());
	        ps.setDouble(5, order.getTotalAmount());
	        ps.setString(6, "Placed");

	        int rows = ps.executeUpdate();

	        if(rows > 0){

	            ResultSet rs = ps.getGeneratedKeys();

	            if(rs.next()){

	                orderId = rs.getInt(1);

	                System.out.println("Generated Order ID = " + orderId);

	            }

	        }

	    } catch(Exception e){
	        e.printStackTrace();
	    }

	    return orderId;
	}
	
	public Order getOrderById(int orderId){

	    Order order = null;

	    try{

	        Connection con = DBConnection.getConnection();

	        String sql =
	        "SELECT * FROM orders WHERE order_id=?";

	        PreparedStatement ps =
	        con.prepareStatement(sql);

	        ps.setInt(1, orderId);

	        ResultSet rs = ps.executeQuery();

	        if(rs.next()){

	            order = new Order();

	            order.setOrderId(rs.getInt("order_id"));
	            order.setCustomerName(rs.getString("customer_name"));
	            order.setAddress(rs.getString("address"));
	            order.setFoodId(rs.getInt("food_id"));
	            order.setQuantity(rs.getInt("quantity"));
	            order.setTotalAmount(rs.getDouble("total_amount"));
	            order.setStatus(rs.getString("status"));
	            order.setDeliveryStatus(rs.getString("delivery_status"));
	            order.setOrderDate(rs.getString("order_date"));

	        }

	    }catch(Exception e){
	        e.printStackTrace();
	    }

	    return order;
	}
	public ArrayList<Order> getAllOrders(){

	    ArrayList<Order> list = new ArrayList<>();

	    try{

	        Connection con = DBConnection.getConnection();

	        String sql =
	        "SELECT * FROM orders ORDER BY order_id DESC";

	        PreparedStatement ps =
	        con.prepareStatement(sql);

	        ResultSet rs = ps.executeQuery();

	        while(rs.next()){

	            Order order = new Order();

	            order.setOrderId(rs.getInt("order_id"));
	            order.setCustomerName(rs.getString("customer_name"));
	            order.setAddress(rs.getString("address"));
	            order.setFoodId(rs.getInt("food_id"));
	            order.setQuantity(rs.getInt("quantity"));
	            order.setTotalAmount(rs.getDouble("total_amount"));
	            order.setStatus(rs.getString("status"));
	            order.setDeliveryStatus(rs.getString("delivery_status"));
	            order.setOrderDate(rs.getString("order_date"));

	            list.add(order);

	        }

	    }catch(Exception e){
	        e.printStackTrace();
	    }

	    return list;
	}
	public boolean updateOrderStatus(int orderId,String status){

	    boolean result = false;

	    try{

	        Connection con = DBConnection.getConnection();

	        String sql =
	        "UPDATE orders SET status=? WHERE order_id=?";

	        PreparedStatement ps =
	        con.prepareStatement(sql);

	        ps.setString(1,status);
	        ps.setInt(2,orderId);

	        int rows = ps.executeUpdate();

	        if(rows>0){
	            result = true;
	        }

	    }catch(Exception e){
	        e.printStackTrace();
	    }

	    return result;
	}
	public int getOrderCount(){

	    int count = 0;

	    try{

	        Connection con = DBConnection.getConnection();

	        String sql =
	        "SELECT COUNT(*) FROM orders";

	        PreparedStatement ps =
	        con.prepareStatement(sql);

	        ResultSet rs = ps.executeQuery();

	        if(rs.next()){

	            count = rs.getInt(1);

	        }

	    }catch(Exception e){
	        e.printStackTrace();
	    }

	    return count;
	}

	public boolean deleteOrder(int orderId) {

	    boolean status = false;

	    try {

	        Connection con = DBConnection.getConnection();

	        // Delete order items first
	        String sql1 = "DELETE FROM order_items WHERE order_id=?";
	        PreparedStatement ps1 = con.prepareStatement(sql1);
	        ps1.setInt(1, orderId);
	        ps1.executeUpdate();

	        // Delete payment
	        String sql2 = "DELETE FROM payments WHERE order_id=?";
	        PreparedStatement ps2 = con.prepareStatement(sql2);
	        ps2.setInt(1, orderId);
	        ps2.executeUpdate();

	        // Delete order
	        String sql3 = "DELETE FROM orders WHERE order_id=?";
	        PreparedStatement ps3 = con.prepareStatement(sql3);
	        ps3.setInt(1, orderId);

	        int rows = ps3.executeUpdate();

	        if(rows > 0){
	            status = true;
	        }

	    } catch(Exception e) {
	        e.printStackTrace();
	    }

	    return status;
	}
	
	public ArrayList<Order> getOrdersByCustomer(String customerName){

	    ArrayList<Order> list = new ArrayList<>();

	    try{

	        Connection con = DBConnection.getConnection();

	        String sql =
	        "SELECT * FROM orders WHERE customer_name=? ORDER BY order_id DESC";

	        PreparedStatement ps = con.prepareStatement(sql);

	        ps.setString(1, customerName);

	        ResultSet rs = ps.executeQuery();

	        while(rs.next()){

	            Order order = new Order();

	            order.setOrderId(rs.getInt("order_id"));
	            order.setCustomerName(rs.getString("customer_name"));
	            order.setAddress(rs.getString("address"));
	            order.setFoodId(rs.getInt("food_id"));
	            order.setQuantity(rs.getInt("quantity"));
	            order.setTotalAmount(rs.getDouble("total_amount"));
	            order.setStatus(rs.getString("status"));
	            order.setDeliveryStatus(rs.getString("delivery_status"));
	            order.setOrderDate(rs.getString("order_date"));

	            list.add(order);
	        }

	    }catch(Exception e){
	        e.printStackTrace();
	    }

	    return list;
	}
	
	public boolean assignDeliveryPartner(int orderId, int partnerId){

	    boolean status = false;

	    try{

	        Connection con = DBConnection.getConnection();

	        String sql =
	        "UPDATE orders SET partner_id=?, delivery_status='Preparing' WHERE order_id=?";

	        PreparedStatement ps =
	                con.prepareStatement(sql);

	        ps.setInt(1, partnerId);
	        ps.setInt(2, orderId);

	        int rows = ps.executeUpdate();

	        if(rows > 0){
	            status = true;
	        }

	    }catch(Exception e){
	        e.printStackTrace();
	    }

	    return status;
	}
	
	public boolean updateDeliveryStatus(int orderId, String status){

	    boolean result = false;

	    try{

	        Connection con = DBConnection.getConnection();

	        String sql =
	        "UPDATE orders SET delivery_status=? WHERE order_id=?";

	        PreparedStatement ps =
	                con.prepareStatement(sql);

	        ps.setString(1, status);
	        ps.setInt(2, orderId);

	        int rows = ps.executeUpdate();

	        if(rows > 0){
	            result = true;
	        }

	    }catch(Exception e){
	        e.printStackTrace();
	    }

	    return result;
	}
}
