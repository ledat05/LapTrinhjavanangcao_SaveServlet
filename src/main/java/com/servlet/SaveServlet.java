/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author ADMIN
 */
@WebServlet(name = "SaveServlet", urlPatterns = {"/SaveServlet"})
public class SaveServlet extends HttpServlet {

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
            //b1. Lay gia yti tham so tu clint
            String uname = request.getParameter("uname");
            String upass = request.getParameter("upass");
            String umail = request.getParameter("umail");
            String country = request.getParameter("country");
            //b2. Xy ly yeu cau(Truy cap CSDL de them moi user)
            Connection conn = null;
            PreparedStatement ps = null;
            try {
                //1
                Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                System.out.println("Nap driver OK");
                //2 Thiet lap ket noi csdl
                conn = DriverManager.getConnection("jdbc:sqlserver://pc330;databaseName=demodb", "sa", "sa");
                System.out.println("Ket noi OK");
                //3 Tao doi tuong thi hanh truy van
                ps = conn.prepareStatement("insert into users(name,password,email,country) values(?,?,?,?)");
                //Truyen gia tri cho tham so trong cau lenh sql
                ps.setString(1, uname);
                ps.setString(2, upass);
                ps.setString(3, umail);
                                ps.setString(4,country);
                                //4 Thi hanh truy van
                                int kq=ps.executeUpdate();
                                //5 xy ly ket qua va tra ve
                                if(kq>0)
                                {
                                    out.println("<h2>Them user thanh cong</h2>");
                                }else{
                                    out.println("<h2>Them user that bai</h2>");
                                }
                                //6 dong ket noi
                                conn.close();


            } catch (Exception e) {
                System.out.println("Loi:" + e.toString());
            }
            request.getRequestDispatcher("index.html").include(request, response);

            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet SaveServlet</title>");
            out.println("</head>");
            out.println("<body>");
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
