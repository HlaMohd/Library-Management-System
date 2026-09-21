package InterfacesProgram;

import ClassesLibrary.Staff;
import ConnectionClasses.InfoStudent;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
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
import LibraryClasses.Common;
import javafx.scene.control.PasswordField;


/**
 * FXML Controller class
 *
 * @author HP
 */
public class LogInController implements Initializable {
    private Stage stage;
    private Scene scene;
    private Parent root;
    private Alert alert = new Alert(Alert.AlertType.ERROR);
     @FXML
    private TextField txtUsername;

    @FXML
    private PasswordField txtPassword;

    @FXML
    private Button btnsignIn;

    @FXML
    private Button btnsignUp;

    String getMajor(ResultSet rsx,String id){
        Connection con ;
        Statement stmt;
        ResultSet rs;
        String major;
        
        try
        {
            con = Common.getConnection();
            stmt = con.createStatement();
            rs =  stmt.executeQuery("SELECT * FROM students ");

            while(rs.next()){
                
                if(id.equals(rs.getString(2))){
                    return rs.getString(3);
                }
            }
            
            con.close();
        }catch(Exception e){
            alert.setTitle("connection database");
            alert.setContentText("don't worry try again throuh clik on ok...");
            alert.setHeaderText("Connection Error");
            alert.showAndWait();
            
            signIn(new ActionEvent());
        }
        return "";
    }
    String getDepartment(ResultSet rsx,String id){
        Connection con ;
        Statement stmt;
        ResultSet rs;
        
        try
        {
            con = Common.getConnection();
            stmt = con.createStatement();
            rs =  stmt.executeQuery("SELECT * FROM staffs");
            while(rs.next()){
                if(id.equals(rs.getString(2))){
                    return rs.getString(3);
                }
            }
            con.close();
        }catch(Exception e){
            alert.setTitle("connection database");
            alert.setContentText("don't worry try again throuh clik on ok...");
            alert.setHeaderText("Connection Error");
            alert.showAndWait();
            
            signIn(new ActionEvent());
        }
        return "";
    }
    
    @FXML
    void signIn(ActionEvent event) {
       
        Connection con ;
        Statement stmt;
        ResultSet rs;
        
        
        try
        {
            con = Common.getConnection();
            stmt = con.createStatement();
            
            rs =  stmt.executeQuery("SELECT * FROM users WHERE modeControl != 1");
            boolean test = false;
            while(rs.next()){
               if(rs.getString(6).equals(txtUsername.getText()) && rs.getString(7).equals(txtPassword.getText()) ){
                   test = true;
                     if(rs.getInt(3) == 3){
                         Common.mode = 3;
                         Common.info = new InfoStudent(rs.getString(2), rs.getString(4), rs.getString(5), txtUsername.getText(),txtPassword.getText() ,getMajor(rs,rs.getString(2)));
                     break;
                     }
                     else if (rs.getInt(3) == 2){
                         Common.mode = 2;
                         Common.staff = new Staff(rs.getString(4), rs.getString(5), txtUsername.getText(),txtPassword.getText() ,getDepartment(rs,rs.getString(2)));
                         Common.staff.setId(rs.getString(2));
                         break;
                     }
                    
               } 
            }
            
            if(!test){
                alert.setTitle("information");
                alert.setContentText("don't worry try again throuh clik on ok...");
                alert.setHeaderText("password invaild");
                alert.showAndWait();                
            }
            else{
                if(Common.mode == 3){
                    root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
                    stage = (Stage)((Node)event.getSource()).getScene().getWindow();
                    scene = new Scene(root);
                    stage.setScene(scene);
                    stage.show();
                }
                else if(Common.mode == 2){
                    root = FXMLLoader.load(getClass().getResource("Staff.fxml"));
                    stage = (Stage)((Node)event.getSource()).getScene().getWindow();
                    scene = new Scene(root);
                    stage.setScene(scene);
                    stage.show();
                    stage.setResizable(false);
                }
            }
            con.close();   
        }catch(Exception e){
            alert.setTitle("connection database");
            alert.setContentText("don't worry try again throuh clik on ok...");
            alert.setHeaderText("Connection Error");
            alert.showAndWait();   
        }
    }

    @FXML
    void signUp(ActionEvent event) throws IOException {
        Stage stage;
        Parent root = FXMLLoader.load(getClass().getResource("SignUp.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
