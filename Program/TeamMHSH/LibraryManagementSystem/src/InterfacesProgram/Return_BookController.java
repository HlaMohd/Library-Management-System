package InterfacesProgram;

import ClassesLibrary.Book_Loan;
import ClassesLibrary.Copy;
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

/**
 * FXML Controller class
 *
 * @author HP
 */
public class Return_BookController implements Initializable {

    Alert alert = new Alert(Alert.AlertType.INFORMATION);
    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    private Button btnme;

    @FXML
    private Button btnView;

    @FXML
    private Button btnDeleteLIb;

    @FXML
    private TextField btnreturnedBookId;

    @FXML
    private TextField btnreturnedStudentId;

    
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
    void methodviewBook(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("viewBook.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void logoutmethod(ActionEvent event) {
        System.exit(0);
    }

    
    public boolean isBookAnUserFound(){
        Connection con ;
        Statement stmt;
        ResultSet rs;
        try
        {
            con = Common.getConnection();
            stmt = con.createStatement();
            rs =  stmt.executeQuery("SELECT * FROM book_loan");
            while(rs.next()){
                if(btnreturnedStudentId.getText().equals(rs.getString(2))&& btnreturnedBookId.getText().equals(rs.getString(3)))
                    return true;
            }
            con.close();
        }catch(Exception e){
        }
        return false;
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }
    @FXML
    void methodDone(ActionEvent event) {
        if(isBookAnUserFound()){
            Connection con ;
            Statement stmt;
            try
            {
                con = Common.getConnection();
                
                PreparedStatement pstmt = con.prepareStatement("DELETE FROM book_loan WHERE id_book = ?");
                pstmt.setString(1, btnreturnedBookId.getText());
                pstmt.addBatch();
                pstmt.executeBatch();
                
                con.close();
                alert.setTitle("Return book");
                alert.setHeaderText("Succesfully");
                alert.showAndWait();
            }catch(Exception e){ }
            
        }
        else
        {
            alert.setTitle("Return book");
            alert.setHeaderText("the book not borrowed or user not found");
            alert.setContentText("Check your input!");
            alert.showAndWait();
        }
    }
     
    
}
