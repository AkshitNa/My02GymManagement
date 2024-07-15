package Gym_Management;

import java.sql.*;
public class ConnectionClass 
{
    Connection con;
    Statement stm;
    ConnectionClass()
    {
        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con=DriverManager.getConnection("jdbc:mysql://localhost:3306/gym_management","root","12345");
            stm=con.createStatement();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        } 
    }
    public static void main(String[] args)
    {
        new ConnectionClass();
    }
}
