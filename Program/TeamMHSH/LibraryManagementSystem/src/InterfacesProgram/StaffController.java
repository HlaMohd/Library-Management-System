package InterfacesProgram;

import LibraryClasses.Common;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class StaffController implements Initializable {
    
    private Stage stage;
    private Scene scene;
    private Parent root;
    
    @FXML
    private Button btnAddSt;
    
    @FXML
    private Text lblId;

    @FXML
    private Text lblName;

    @FXML
    private Text lblUserName;

    @FXML
    private Text lblDepartment;
    
     @FXML
    void borrowBook(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("ViewSt.fxml"));
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
    void methodDelete(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("DeletSt.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    
    @FXML
    void AddBookMethod(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("AddBook.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    
    @FXML
    void ViewBookMethod(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("ViewBooks.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void logoutMethod(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("LogIn.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    void EditPassWordMethod(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("EditPassword.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void EditUserNameMetgod(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("EditUserName.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        lblId.setText(Common.staff.getId());
        lblName.setText(Common.staff.getFirstName() + " " + Common.staff.getLastName() );
        lblUserName.setText(Common.staff.getUserName());
        lblDepartment.setText(Common.staff.getDepartment());
    }
    
}
