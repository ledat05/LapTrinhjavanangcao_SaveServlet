/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package my.common;

import java.sql.Connection;
import java.sql.DriverManager;


/**
 *
 * @author Admin
 */
public class DatabaseUtil {
    public static Connection getConnection(){
        
        Connection conn=null;
        try{
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                System.out.println("Nap driver OK");
                //2 Thiet lap ket noi csdl
                conn = DriverManager.getConnection("jdbc:sqlserver://ADMIN;databaseName=demodb", "sa", "sa");
                System.out.println("Ket noi OK");
        }catch(Exception ex){
            System.out.println("Loi:"+ ex.toString());
        }
        return conn;
    }
}
