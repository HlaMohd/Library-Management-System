package main;


import javafx.scene.layout.Pane;

import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Label;
import javafx.scene.control.Button;

import javafx.geometry.Insets;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import javafx.scene.control.Alert;

public class AdminFormProfileSetting extends Pane implements DBInf{
    private TextField txtFirstName = new TextField();
    private TextField txtLastName = new TextField();
    private TextField txtUserName = new TextField();
    private PasswordField txtPassword = new PasswordField();
    private PasswordField txtNewPassword = new PasswordField();
    
    private Label lblTitle = new Label("Profile Setting");
    private Label lblFirstName = new Label("First Name:");
    private Label lblLastName = new Label("Last Name:");
    private Label lblUsername = new Label("Username:");
    private Label lblPassword = new Label("Password:");
    private Label lblNewPassword = new Label("New Password:");
    private Label lblPasswordInvaild = new Label("password invaild");
    
    private Button btnSave= new Button("Save");
    
    private Alert alert = new Alert(Alert.AlertType.ERROR);
    
    public AdminFormProfileSetting(){
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
        btnSave.setTranslateX(170);
        btnSave.setTranslateY(250);
        btnSave.setPrefSize(100,10);
        btnSave.setFocusTraversable(false);
        
        
        alert.setTitle("connection database");
        alert.setContentText("don't worry try again throuh clik ok...");
        alert.setHeaderText("Connection Error");
        
        this.setBackground(new Background(new BackgroundFill(Color.FLORALWHITE, CornerRadii.EMPTY, Insets.EMPTY)));
        this.getChildren().addAll(lblTitle, lblFirstName, txtFirstName, lblLastName, txtLastName,
                lblUsername, txtUserName, lblPassword, lblNewPassword, txtNewPassword, txtPassword, lblPasswordInvaild, btnSave);
        getProfileAdmin();
        btnSave.setOnAction(e->{
            changeInfo();
        });
    }
    
    public void changeInfo(){
        Connection con ;
        Statement stmt;
        ResultSet rs;
        try
        {
            con = Common.getConnection();
            stmt = con.createStatement();
            rs =  stmt.executeQuery("SELECT * FROM users WHERE modeControl = 1");
            
            while(rs.next()){
                if(rs.getString(7).equals(txtPassword.getText())){
                    PreparedStatement pstmt = con.prepareStatement("UPDATE users SET first_name = ?, last_name = ?, user_name = ?, pass = ? ");
                    pstmt.setString(1, txtFirstName.getText());
                    pstmt.setString(2, txtLastName.getText());
                    pstmt.setString(3, txtUserName.getText());
                    pstmt.setString(4, txtNewPassword.getText());
                    pstmt.addBatch();
                    
                    pstmt.executeBatch();
                    
                    
                    lblPasswordInvaild.setText("successfully changed");
                    lblPasswordInvaild.setTextFill(Color.GREEN);

                }
                else{
                    lblPasswordInvaild.setText("password invaild");
                    lblPasswordInvaild.setTextFill(Color.RED);
                    lblPasswordInvaild.visibleProperty().set(true);
                }
                    
                 
            }
           
//         
        }catch(Exception e){
            alert.showAndWait();
            getProfileAdmin();
        }
    }
    public void getProfileAdmin(){
        Connection con ;
        Statement stmt;
        ResultSet rs;
        try
        {
            con = Common.getConnection();
            stmt = con.createStatement();
            
            rs =  stmt.executeQuery("SELECT * FROM users WHERE modeControl = 1");
            while(rs.next()){
                txtFirstName.setText(rs.getString(4));
                txtLastName.setText(rs.getString(5));
                txtUserName.setText(rs.getString(6));
            }
            
        }catch(Exception e){
            alert.showAndWait();
            getProfileAdmin();
        }
    }
}
