package InterfacesProgram;

import LibraryClasses.Common;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
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
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


public class EditPasswordController implements Initializable {

    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    private PasswordField previousPassword;

    @FXML
    private PasswordField newPassword;

    @FXML
    private PasswordField confirmPassword;

    @FXML
    private Button btnchangeUsername;
    
    private Alert alert = new Alert(Alert.AlertType.INFORMATION);
 
    @FXML
    void changeUsername(ActionEvent event) {
        Connection con ;
        Statement stmt;
        ResultSet rs;
        ResultSet rs_2;
        
        try
        {

            con = Common.getConnection();
            stmt = con.createStatement();
            
            con = Common.getConnection();
            PreparedStatement pstmt = con.prepareStatement("UPDATE users SET pass = ? WHERE id_user = ? ");
            
            if(Common.info.getPass().equals(previousPassword.getText()) && confirmPassword.getText().equals(newPassword.getText())){
                pstmt.setString(2, Common.info.getId());
                pstmt.setString(1, newPassword.getText());
                
                pstmt.addBatch();
                pstmt.executeBatch();
                
                Common.info.setPass(newPassword.getText());
                
                alert.setTitle("Information");
                alert.setHeaderText("successfully changed");
                alert.showAndWait(); 
            }
            else{
                alert.setTitle("Information");
                alert.setHeaderText("password invald,check your password or confirm passowrd.");
                alert.showAndWait(); 
                
                
            }
            
            con.close();
        }catch(Exception e){
            //
        }
    }

    @FXML
    void backtomain(ActionEvent event) throws IOException {
        //3->employee
        //2->student
        if(Common.mode == 3){
            root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
        else if(Common.mode == 2 ){
            root = FXMLLoader.load(getClass().getResource("Staff.fxml"));
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
