package main;

import ClassesLibrary.Staff;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class AdminFormAddStaff extends Pane {
     private TextField txtFirstName = new TextField();
    private TextField txtLastName = new TextField();
    private TextField txtUserName = new TextField();
    private TextField txtDepartment= new TextField();
    
    private PasswordField txtPassword = new PasswordField();
    private PasswordField txtNewPassword = new PasswordField();
    
    private Label lblTitle = new Label("Adding Staff");
    private Label lblFirstName = new Label("First Name:");
    private Label lblLastName = new Label("Last Name:");
    private Label lblUsername = new Label("Username:");
    private Label lblDepartment = new Label("Department:");
    private Label lblPassword = new Label("Password:");
    private Label lblNewPassword = new Label("New Password:");
    private Label lblPasswordInvaild = new Label("password invaild");
    
    private Button btnAdd= new Button("add");
    
    private Alert alert = new Alert(Alert.AlertType.INFORMATION);
    
    public AdminFormAddStaff(){
        lblTitle.setTranslateX(20);
        lblTitle.setTranslateY(10);
        lblTitle.setPrefSize(200,10);
        lblTitle.setFocusTraversable(false);
        lblTitle.setFont(new Font("Arial",30));
        
        //first and last name
        lblFirstName.setTranslateX(40);
        lblFirstName.setTranslateY(60);
        lblFirstName.setPrefSize(160,10);
//        txtFirstName.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));
        lblFirstName.setFocusTraversable(false);
        
        txtFirstName.setTranslateX(40);
        txtFirstName.setTranslateY(80);
        txtFirstName.setPrefSize(160,10);
//        txtFirstName.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));
        txtFirstName.setPromptText("Enter first name");
//        txtFirstName.setTooltip(new Tooltip("Enter first your name"));
        txtFirstName.setFocusTraversable(false);
        
        lblLastName.setTranslateX(240);
        lblLastName.setTranslateY(60);
        lblLastName.setPrefSize(160,10);
        lblLastName.setFocusTraversable(false);
        
        txtLastName.setTranslateX(240);
        txtLastName.setTranslateY(80);
        txtLastName.setPrefSize(160,10);
        txtLastName.setPromptText("Enter last name");
        txtLastName.setFocusTraversable(false);
        
        //username
        lblUsername.setTranslateX(40);
        lblUsername.setTranslateY(120);
        lblUsername.setPrefSize(160,10);
        
        txtUserName.setTranslateX(40);
        txtUserName.setTranslateY(140);
        txtUserName.setPrefSize(160,10);
        txtUserName.setPromptText("Enter usernme");
        txtUserName.setFocusTraversable(false);
        
        //Department
        lblDepartment.setTranslateX(240);
        lblDepartment.setTranslateY(120);
        lblDepartment.setPrefSize(160,10);
        
        txtDepartment.setTranslateX(240);
        txtDepartment.setTranslateY(140);
        txtDepartment.setPrefSize(160,10);
        txtDepartment.setPromptText("Enter Department");
        txtDepartment.setFocusTraversable(false);
        
        //password
        lblPassword.setTranslateX(40);
        lblPassword.setTranslateY(180);
        lblPassword.setPrefSize(160,10);
        
        txtPassword.setTranslateX(40);
        txtPassword.setTranslateY(200);
        txtPassword.setPrefSize(160,10);
        txtPassword.setPromptText("Enter password");
        txtPassword.setFocusTraversable(false);
        
        lblPasswordInvaild.setTextFill(Color.RED);
        lblPasswordInvaild.setTranslateX(40);
        lblPasswordInvaild.setTranslateY(225);
        lblPasswordInvaild.visibleProperty().set(false);
        
        //new password
        lblNewPassword.setTranslateX(240);
        lblNewPassword.setTranslateY(180);
        lblNewPassword.setPrefSize(160,10);
        lblNewPassword.setFocusTraversable(false);
        
        txtNewPassword.setTranslateX(240);
        txtNewPassword.setTranslateY(200);
        txtNewPassword.setPrefSize(160,10);
        txtNewPassword.setPromptText("Enter new password");
        txtNewPassword.setFocusTraversable(false);
        
        //button save
        btnAdd.setTranslateX(170);
        btnAdd.setTranslateY(250);
        btnAdd.setPrefSize(100,10);
        btnAdd.setFocusTraversable(false);
        
                
        this.setBackground(new Background(new BackgroundFill(Color.FLORALWHITE, CornerRadii.EMPTY, Insets.EMPTY)));
        this.getChildren().addAll(lblTitle, lblFirstName, txtFirstName, lblLastName, txtLastName,
                lblUsername, txtUserName,lblDepartment, txtDepartment ,lblPassword, lblNewPassword, txtNewPassword, txtPassword, lblPasswordInvaild, btnAdd);
        
        btnAdd.setOnAction(e->{
            if(txtNewPassword.getText().equals(txtPassword.getText()) && !(txtFirstName.getText().isEmpty())
                    && !(txtLastName.getText().isEmpty()) && !isFound(txtUserName.getText())){
                
               Connection con ;
               Statement stmt;
               ResultSet rs;
               int max_numberStudent = 0;
               try
               {
                   con = Common.getConnection();
                   stmt = con.createStatement();
                rs =  stmt.executeQuery("SELECT COUNT(id_user) FROM users WHERE modeControl = 2 ");
                   rs.next();
                   max_numberStudent = rs.getInt(1);
                   
                   con.close();
               }catch(Exception x){
                   //ToDo
               }
               
               Staff staff = new Staff(max_numberStudent,txtFirstName.getText(),txtLastName.getText(),txtUserName.getText(),txtPassword.getText(),txtDepartment.getText());
               
               try{
                con = Common.getConnection();
                PreparedStatement pstmt = con.prepareStatement("INSERT INTO users VALUES(?,?,?,?,?,?,?,?)");
                
                pstmt.setString(1,null);
                pstmt.setString(2,"01"+(max_numberStudent+1));
                pstmt.setInt(3,2);
                pstmt.setString(4,txtFirstName.getText());
                pstmt.setString(5, txtLastName.getText());
                pstmt.setString(6,txtUserName.getText());
                pstmt.setString(7,txtPassword.getText());

                int year = staff.getDateOfRegistration().getYear();
                int month = staff.getDateOfRegistration().getMonth();
                int day = LocalDate.now().getDayOfMonth();

                pstmt.setDate(8, new Date(year, month, day));
                
                pstmt.addBatch();
                pstmt.executeBatch();
                con.close();
                }catch(Exception X){}
               
               try{
                   con = Common.getConnection();
                   PreparedStatement pstmt = con.prepareStatement("INSERT INTO staffs VALUES(?,?,?)");
                   
                   pstmt.setString(1, null);
                   pstmt.setString(2, "01"+(max_numberStudent+1));
                   pstmt.setString(3, txtDepartment.getText());
                   
                   pstmt.addBatch();
                   pstmt.executeBatch();
                   con.close();
                   
                   alert.setTitle("Information");
                   alert.setHeaderText("succsefull signup");
                   alert.showAndWait();
               }catch(Exception x){
                    //
               }
            }
            else{
                
                
                alert.setTitle("Information");
                alert.setHeaderText("Process not working for you because your input error or you try register an existing account.");
                alert.showAndWait();
            }
            txtFirstName.setText("");
            txtLastName.setText("");
            txtDepartment.setText("");
            txtUserName.setText("");
            txtPassword.setText("");
            txtNewPassword.setText("");
        });
    }
    public boolean isFound(String s){
        Connection con ;
        Statement stmt;
        ResultSet rs;
        try
        {
            con = Common.getConnection();
            stmt = con.createStatement();
            rs =  stmt.executeQuery("SELECT * FROM users WHERE modeControl = 2");
            while(rs.next()){
                if(rs.getString(6).equals(s))
                    return true;
            }
        }catch(Exception e){
            
        }
        return false;
    }
}