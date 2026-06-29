package com.matofoody.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.matofoody.db.DBConnection;
import com.matofoody.model.DeliveryPartner;

public class DeliveryPartnerDAO {

    public ArrayList<DeliveryPartner> getAllPartners() {

        ArrayList<DeliveryPartner> list = new ArrayList<>();

        try {

            Connection con = DBConnection.getConnection();

            String sql =
                    "SELECT * FROM delivery_partner ORDER BY partner_name";

            PreparedStatement ps =
                    con.prepareStatement(sql);

            ResultSet rs =
                    ps.executeQuery();

            while(rs.next()) {

                DeliveryPartner partner =
                        new DeliveryPartner();

                partner.setPartnerId(
                        rs.getInt("partner_id"));

                partner.setPartnerName(
                        rs.getString("partner_name"));

                partner.setPhone(
                        rs.getString("phone"));

                partner.setVehicle(
                        rs.getString("vehicle"));

                partner.setVehicleNumber(
                        rs.getString("vehicle_number"));

                partner.setStatus(
                        rs.getString("status"));

                list.add(partner);
            }

        } catch(Exception e) {
            e.printStackTrace();
        }

        return list;
    }
    
    public boolean addPartner(DeliveryPartner partner){

        boolean result = false;

        try{

            Connection con = DBConnection.getConnection();

            String sql =
            "INSERT INTO delivery_partner(partner_name,phone,vehicle,vehicle_number,status) VALUES(?,?,?,?,?)";

            PreparedStatement ps =
                    con.prepareStatement(sql);

            ps.setString(1, partner.getPartnerName());
            ps.setString(2, partner.getPhone());
            ps.setString(3, partner.getVehicle());
            ps.setString(4, partner.getVehicleNumber());
            ps.setString(5, partner.getStatus());

            int rows = ps.executeUpdate();

            if(rows > 0){
                result = true;
            }

        }catch(Exception e){
            e.printStackTrace();
        }

        return result;
    }
    
    public DeliveryPartner getPartnerById(int partnerId){

        DeliveryPartner partner = null;

        try{

            Connection con = DBConnection.getConnection();

            String sql =
            "SELECT * FROM delivery_partner WHERE partner_id=?";

            PreparedStatement ps =
                    con.prepareStatement(sql);

            ps.setInt(1, partnerId);

            ResultSet rs = ps.executeQuery();

            if(rs.next()){

                partner = new DeliveryPartner();

                partner.setPartnerId(rs.getInt("partner_id"));
                partner.setPartnerName(rs.getString("partner_name"));
                partner.setPhone(rs.getString("phone"));
                partner.setVehicle(rs.getString("vehicle"));
                partner.setVehicleNumber(rs.getString("vehicle_number"));
                partner.setStatus(rs.getString("status"));

            }

        }catch(Exception e){
            e.printStackTrace();
        }

        return partner;
    }
    
    public boolean updatePartner(DeliveryPartner partner){

        boolean result = false;

        try{

            Connection con = DBConnection.getConnection();

            String sql =
            "UPDATE delivery_partner SET partner_name=?, phone=?, vehicle=?, vehicle_number=?, status=? WHERE partner_id=?";

            PreparedStatement ps =
                    con.prepareStatement(sql);

            ps.setString(1, partner.getPartnerName());
            ps.setString(2, partner.getPhone());
            ps.setString(3, partner.getVehicle());
            ps.setString(4, partner.getVehicleNumber());
            ps.setString(5, partner.getStatus());
            ps.setInt(6, partner.getPartnerId());

            int rows = ps.executeUpdate();

            if(rows > 0){
                result = true;
            }

        }catch(Exception e){
            e.printStackTrace();
        }

        return result;
    }
    
    public boolean deletePartner(int partnerId){

        boolean result = false;

        try{

            Connection con = DBConnection.getConnection();

            String sql =
            "DELETE FROM delivery_partner WHERE partner_id=?";

            PreparedStatement ps =
                    con.prepareStatement(sql);

            ps.setInt(1, partnerId);

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