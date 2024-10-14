/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import static java.lang.System.out;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import my.common.DatabaseUtil;

/**
 *
 * @author Admin
 */
public class ViewServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            //b1

            //b2. Xy ly yeu cau(Truy cap CSDL de them moi user)
            Connection conn = null;
            PreparedStatement ps = null;
            ResultSet rs = null;
            String data = "";
            try {
                conn = DatabaseUtil.getConnection();

                //b3 tao doi tuong thi hanh truy van 
                ps = conn.prepareStatement("select * from users");
                //4. thi hanh truy van
                rs = ps.executeQuery();
                //5 xu ly ket qua tra ve
                data += "<table border=\"1\">";
                data += "<tr><th>Id</th><th>Name</th><th>Password</th><th>Email</th><th>Country</th><th>Edit</th><th>Delete</th></tr>";
                while (rs.next()) {
                    data += "<tr>";
                    data += "<td>" + rs.getInt(1) + "</td>";
                    data += "<td>" + rs.getString(2) + "</td>";
                    data += "<td>" + rs.getString(3) + "</td>";
                    data += "<td>" + rs.getString(4) + "</td>";
                    data += "<td>" + rs.getString(5) + "</td>";

                    data += "<td><a href=EditServlet?id=" + rs.getInt(1) + ">Edit</a></td>";
                    data += "<td><a href=DeleteServlet?id=" + rs.getInt(1) + " onclick=\"return confirm('Are you sure to go ?')\">Delete</a></td>\n";
                }
                //6 dong ket noi
                conn.close();
            } catch (Exception e) {
                System.out.println("Loi:" + e.toString());
                out.println("<h2>Them User That Bai</h2>");
            }

            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ViewServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ViewServlet at " + request.getContextPath() + "</h1>");
            out.println("<h1>Users List</h1>");
            out.println(data);
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
