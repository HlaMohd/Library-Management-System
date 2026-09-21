package InterfacesProgram;

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

public class DeletStController implements Initializable {

    @FXML
    private Button btnBack;
    
    @FXML
    private TextField id;
    Alert alert = new Alert(Alert.AlertType.ERROR);

    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    void btnBackmethod(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("Staff.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    
    @FXML
    void metondDone(ActionEvent event) {
        Connection con ;
        try{
            
            con = Common.getConnection();
            PreparedStatement pstms = con.prepareStatement("DELETE FROM users WHERE id_user = ? AND modeControl = 3;");
            pstms.setString(1, id.getText());
            
            PreparedStatement pstm = con.prepareStatement("DELETE FROM students WHERE id_user = ?;");
            pstm.setString(1, id.getText());
            
            
            
            pstms.addBatch();
            pstms.executeBatch();
            
            pstm.addBatch();
            pstm.executeBatch();

            alert.setAlertType(Alert.AlertType.INFORMATION);
            alert.setTitle("Delete");
            alert.setHeaderText("The " + id.getText() + " student" + "\n Sucssefully deleted");
            alert.showAndWait();
//            con.close();
            
        }catch(Exception e){
            alert.setAlertType(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("try again!!");
            alert.showAndWait();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}
