package LibraryClasses;

import ClassesLibrary.Book;
import ClassesLibrary.Book_Loan;
import ClassesLibrary.Copy;
import ClassesLibrary.Staff;
import ConnectionClasses.InfoStudent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import javafx.collections.ObservableList;

public class Common implements DBInf{
    public static InfoStudent info;
    public static Staff staff;
    public static ObservableList<Book> bookList;
    public static ObservableList<Book_Loan> copyBookList;
    public static ArrayList<Copy> CopybookList = new ArrayList<Copy>();
    public static Copy Copybook = new Copy();
    public static int mode = 0;
    public static int numberStudnetHaveOfBook = 0;
    public static int numberCopyeBoook = 0;
    public static int i = 0;  //  CopybookList
    
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
