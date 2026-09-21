package InterfacesProgram;

import LibraryClasses.Common;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
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

public class EditUserNameController implements Initializable {
    
    private Stage stage;
    private Scene scene;
    private Parent root;
    
    private Alert alert = new Alert(Alert.AlertType.INFORMATION);
    
    @FXML
    private TextField newUserName;

    @FXML
    private Button btnchangeUsename;

    @FXML
    void changeUsername(ActionEvent event) {
        if (newUserName.getText().isEmpty() || newUserName.getText().length() < 6){
            alert.setTitle("Error");
            alert.setHeaderText("check you inpout");
            alert.setContentText("The username must be more than 6 character or number.");
            alert.showAndWait();
        }else{
            Connection con ;
            try
            {            
                con = Common.getConnection();
                PreparedStatement pstmt = con.prepareStatement("UPDATE users SET username = ? WHERE id_user = ? ");

                pstmt.setString(2, Common.info.getId());
                pstmt.setString(1, newUserName.getText());

                pstmt.addBatch();
                pstmt.executeBatch();

                Common.info.setUsername(newUserName.getText());

                alert.setTitle("Information");
                alert.setHeaderText("successfully changed");
                alert.showAndWait();

                con.close();
            }catch(Exception e){
                //
                alert.setTitle("Error");
                alert.setHeaderText("try again!!!");
                alert.showAndWait();
            }
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
        //to do
    }      
    
}
