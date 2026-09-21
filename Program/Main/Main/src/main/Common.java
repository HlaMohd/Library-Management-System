package main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javafx.collections.ObservableList;

public class Common implements DBInf{

    public static Connection getConnection() throws ClassNotFoundException{
        
        Connection con;
        try{
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(DB_NAME,USER,PASSWORD);
            return con;
        }
        catch(SQLException ex){
            return null;
        }
    }
}
