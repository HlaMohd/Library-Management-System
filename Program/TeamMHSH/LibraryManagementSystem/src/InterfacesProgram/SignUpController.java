package InterfacesProgram;

import ClassesLibrary.Student;
import LibraryClasses.Common;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.sql.Date;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class SignUpController implements Initializable {
    
    private Alert alert = new Alert(Alert.AlertType.INFORMATION);

    @FXML
    private TextField FirstName;

    @FXML
    private TextField LastName;

    @FXML
    private TextField Magor;

    @FXML
    private TextField UserName;

    @FXML
    private TextField PassWord;

    
    @FXML
    void BackMethod(ActionEvent event) throws IOException{
        Stage stage;
        Parent root = FXMLLoader.load(getClass().getResource("LogIn.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void DoneMethod(ActionEvent event) {
       String[]temp = new String[2];
       
        if(!(PassWord.getText().isEmpty()) && !(FirstName.getText().isEmpty())
                    && !(LastName.getText().isEmpty()) && !isFound(UserName.getText())  && !(Magor.getText().isEmpty())){
           
            
            Student std = new Student(FirstName.getText(), LastName.getText(), UserName.getText(), PassWord.getText(), Magor.getText());

            Connection con ;
            Statement stmt;
            ResultSet rs;
            int max_numberStudent = 0;
            try
            {
                con = Common.getConnection();
                stmt = con.createStatement();
                rs =  stmt.executeQuery("SELECT COUNT(id_user) FROM students ");
                rs.next();
                max_numberStudent = rs.getInt(1);
                con.close();
            }catch(Exception e){
                DoneMethod(event);
                //ToDo
            }
            try {

                con = Common.getConnection();
                PreparedStatement pstmt = con.prepareStatement("INSERT INTO users VALUES(?,?,?,?,?,?,?,?)");

                pstmt.setString(1,null);
                pstmt.setString(2,"00"+(max_numberStudent+1));
                pstmt.setInt(3,3);
                pstmt.setString(4,FirstName.getText());
                pstmt.setString(5, LastName.getText());
                pstmt.setString(6,UserName.getText());
                pstmt.setString(7,PassWord.getText());

                int year = std.getDateOfRegistration().getYear();
                int month = std.getDateOfRegistration().getMonth();
                int day = LocalDate.now().getDayOfMonth();

                pstmt.setDate(8, new Date(year, month, day));
                
                pstmt.addBatch();
                pstmt.executeBatch();
                con.close();

                System.out.println("added");
            }catch(Exception e){
                alert.setTitle("Error");
                alert.setContentText("check your input please!");
                alert.setHeaderText("Connection Error");
                alert.showAndWait();
                DoneMethod(event);
            }
            try{
                con = Common.getConnection();
                stmt = con.createStatement();
                PreparedStatement pstmt = con.prepareStatement("INSERT INTO students VALUES(?,?,?,?,?,?,?,?)");
                pstmt.setString(1,null);

                pstmt.setString(2,"00"+(max_numberStudent+1));
                pstmt.setString(3,Magor.getText());
                pstmt.setInt(4,0);
                pstmt.setInt(5, 0);
                pstmt.setString(6, "");
                pstmt.setString(7, "");
                pstmt.setString(8, "");

                pstmt.addBatch();
                pstmt.executeBatch();
                con.close();
                Stage stage;
                Parent root = FXMLLoader.load(getClass().getResource("LogIn.fxml"));
                stage = (Stage)((Node)event.getSource()).getScene().getWindow();
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();

                alert.setTitle("Information");
                alert.setHeaderText("you sign in, can you enter by new acount");
                alert.showAndWait();

                System.out.println("added");
                }catch(Exception e){
                    DoneMethod(event);
                    //
                }
          }
          else{
              alert.setTitle("Information");
              alert.setHeaderText("Process not working for you because your input error or you try register an existing account.");
              alert.showAndWait();
          }
              
    }
    public boolean isFound(String s){
        Connection con ;
        Statement stmt;
        ResultSet rs;
        try
        {
            con = Common.getConnection();
            stmt = con.createStatement();
            rs =  stmt.executeQuery("SELECT * FROM users WHERE modeControl = 3");
            while(rs.next()){
                if(rs.getString(6).equals(s))
                    return true;
            }
        }catch(Exception e){
            
        }
        return false;
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
