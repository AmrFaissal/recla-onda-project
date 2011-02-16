package entities;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DBConnexion {

    private static Connection c;
 
    static {
    	
        try 
        {
            Class.forName("com.mysql.jdbc.Driver");
            c = DriverManager.getConnection("jdbc:mysql://localhost:3306/pl", "root", "0");
        } catch (SQLException ex) {
            Logger.getLogger(DBConnexion.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DBConnexion.class.getName()).log(Level.SEVERE, null, ex);
        }
    } 

    
    
    public static Connection getConnection()
    {
        return c;
    }
    
}