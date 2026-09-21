package InterfacesProgram;

import ClassesLibrary.Book;
import LibraryClasses.Common;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AddBookController implements Initializable {

    private Stage stage;
    private Scene scene;
    private Parent root;
    Alert alert = new Alert(Alert.AlertType.INFORMATION);
   @FXML
    private Button btnme;

    @FXML
    private Button btnDeleteLIb;

    @FXML
    private TextField AddBook_Title;

    @FXML
    private TextField AddBook_Publisher;

    @FXML
    private TextField AddBook_Outher;

    @FXML
    private TextField AddBook_numberOfCobies;

    @FXML
    private TextField AddBook_Price;

    @FXML
    private TextField AddBook_YearOfBublish;

    @FXML
    private Button done;

  

    
@FXML
    void Addme(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("Staff.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
  
    @FXML
    void borrowBook(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("ViewSt.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    
    
    @FXML
    void DeleteLibrationMethod(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("DeletSt.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    
   

    @FXML
    void logoutmethod(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("LogIn.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    
    @FXML
    void methodReturnBook(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("Return_Book.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void methodviewBook(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("ViewBooks.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

  @FXML
        void methodDone(ActionEvent event) {
            if(AddBook_Title.getText().trim().isEmpty() ||  AddBook_Publisher.getText().trim().isEmpty() ||
                    AddBook_Outher.getText().trim().isEmpty() ||  AddBook_numberOfCobies.getText().trim().isEmpty() ||
                    AddBook_Price.getText().trim().isEmpty() || AddBook_YearOfBublish.getText().trim().isEmpty()
                    ){
                alert.setTitle("Error");
                alert.setContentText("maybe you be input somthing empty!");
                alert.setHeaderText("Check your input");
                alert.showAndWait();
            }
            else
            {
                Connection con ;
                Statement stmt;
                ResultSet rs;
                int max_numberBook = 0;
                try
                {
                    con = Common.getConnection();
                    stmt = con.createStatement();
                    rs =  stmt.executeQuery("SELECT COUNT(id_book) FROM books ");
                    rs.next();
                    max_numberBook = rs.getInt(1);
                    con.close();
               
                    
                    Book book = new Book(AddBook_Title.getText(), AddBook_Publisher.getText(), AddBook_Outher.getText(),
                            Integer.parseInt(AddBook_numberOfCobies.getText()) , Double.parseDouble(AddBook_Price.getText()),
                            Integer.parseInt(AddBook_YearOfBublish.getText())
                    );
                    con = Common.getConnection();
                    PreparedStatement pstmt = con.prepareStatement("INSERT INTO books VALUES(?,?,?,?,?,?,?)");
                    PreparedStatement pstmt2 = con.prepareStatement("INSERT INTO copy_book VALUES(?,?,?)");
                    
                    //add book
                    pstmt.setString(1,(max_numberBook+1)+"");
                    pstmt.setString(2,book.getTitle());
                    pstmt.setString(3,book.getPublisher());
                    pstmt.setString(4,book.getAuther());
                    pstmt.setDouble(5,book.getPrice());
                    pstmt.setInt(6, book.getYear());
                    pstmt.setInt(7,book.getNumOfCopies());
                    
                    pstmt.executeUpdate();

                    
                    //add copy book
                    pstmt2.setString(1,null);
                    pstmt2.setString(2,(max_numberBook+1)+"");
                    pstmt2.setBoolean(3,book.getStatus());
                    
                    pstmt2.executeUpdate();
                    
                    con.close();
                    
                    alert.setTitle("Book");
                    alert.setHeaderText("successfully added");
                    alert.showAndWait();
                }catch(Exception e){
                    alert.setTitle("connection database");
                    alert.setContentText("don't worry try again throuh clik on ok...");
                    alert.setHeaderText("Connection Error");
                    alert.showAndWait(); 
                }
            }
    }
}
