package com.matofoody.servlet;

import java.io.IOException;
import java.util.ArrayList;

import com.itextpdf.text.Document;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import com.matofoody.dao.OrderDAO;
import com.matofoody.dao.OrderItemDAO;
import com.matofoody.dao.PaymentDAO;

import com.matofoody.model.Order;
import com.matofoody.model.OrderItem;
import com.matofoody.model.Payment;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

@WebServlet("/DownloadInvoiceServlet")
public class DownloadInvoiceServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {
    	
    	System.out.println("========== DownloadInvoiceServlet CALLED ==========");

        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition",
                "attachment; filename=MatoFoody_Invoice.pdf");

        try {

            Document document = new Document();

            PdfWriter.getInstance(document,
                    response.getOutputStream());

            document.open();

            Font title =
                    new Font(Font.FontFamily.HELVETICA,20,Font.BOLD);

            Font normal =
                    new Font(Font.FontFamily.HELVETICA,12);

         // Get Order ID from URL
            int orderId =
                    Integer.parseInt(request.getParameter("orderId"));

            System.out.println("Invoice Order ID = " + orderId);

            // Create DAO objects
            OrderDAO orderDAO = new OrderDAO();
            PaymentDAO paymentDAO = new PaymentDAO();
            OrderItemDAO itemDAO = new OrderItemDAO();

            // Fetch data from database
            Order order =
                    orderDAO.getOrderById(orderId);

            Payment payment =
                    paymentDAO.getPaymentByOrder(orderId);

            ArrayList<OrderItem> items =
                    itemDAO.getOrderItems(orderId);

            if(order == null){

                document.add(new Paragraph("Order Not Found"));

                document.close();

                return;
            }

            document.add(new Paragraph("MatoFoody Invoice", title));
            document.add(new Paragraph(" "));

            document.add(new Paragraph("Invoice No : " + order.getOrderId(), normal));
            document.add(new Paragraph("Customer : " + order.getCustomerName(), normal));
            document.add(new Paragraph("Address : " + order.getAddress(), normal));
            document.add(new Paragraph("Order Date : " + order.getOrderDate(), normal));

            document.add(new Paragraph(" "));
            document.add(new Paragraph("--------------------------------------------"));

            document.add(new Paragraph("Items Ordered", title));
            document.add(new Paragraph(" "));

            for(OrderItem item : items){

                document.add(new Paragraph(
                    item.getFoodName()
                    + "   Qty : "
                    + item.getQuantity()
                    + "   ₹"
                    + item.getAmount(), normal));

            }

            document.add(new Paragraph("--------------------------------------------"));

            document.add(new Paragraph(
                    "Total Amount : ₹" +
                    order.getTotalAmount(), normal));

            if(payment != null){

                document.add(new Paragraph(
                        "Payment Method : "
                        + payment.getPaymentMethod(), normal));

                document.add(new Paragraph(
                        "Payment Status : "
                        + payment.getPaymentStatus(), normal));
            }

            document.add(new Paragraph(" "));
            document.add(new Paragraph("Thank you for ordering with MatoFoody.", normal));

            document.close();

        } catch (Exception e) {

            e.printStackTrace();
        }

    }

}