package main;

import javafx.scene.layout.Pane;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Button;
import javafx.scene.control.Separator;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import javafx.scene.control.Tooltip;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.geometry.Insets;

import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class SignUp_Form extends Pane  {
    private Label lblLogin = new Label("Library *****");
    private TextField txtUserName  = new TextField();
    private TextField txtFirstName  = new TextField();
    private TextField txtLastName  = new TextField();
    private PasswordField txtPass  = new PasswordField();
    private Button btnSignup  = new Button("Sign Up");
    private Separator s = new Separator();
    private Button btnBack  = new Button("Back");
    private Pane root = new Pane();
    private Pane pane = new Pane();


   public SignUp_Form(){
        lblLogin.setTranslateX(5);
        lblLogin.setTranslateY(25);
//        System.out.println( Font.getFontNames());;
        lblLogin.setFont(new Font("Arial Bold",30));
        
        txtFirstName.setTranslateX(20);
        txtFirstName.setTranslateY(80);
        txtFirstName.setPrefSize(260,30);
        txtFirstName.setPromptText("First Name");
        txtFirstName.setTooltip(new Tooltip("First Name"));
        txtFirstName.setFocusTraversable(false);
        
        
        
        txtLastName.setTranslateX(20);
        txtLastName.setTranslateY(120);
        txtLastName.setPrefSize(260,30);
        txtLastName.setPromptText("Last Name");
        txtLastName.setTooltip(new Tooltip("Last Name"));
        txtLastName.setFocusTraversable(false);
        
        txtUserName.setTranslateX(20);
        txtUserName.setTranslateY(160);
        txtUserName.setPrefSize(260,30);
        txtUserName.setPromptText("username");
        txtUserName.setTooltip(new Tooltip("username"));
        txtUserName.setFocusTraversable(false);
        
        txtPass.setTranslateX(20);
        txtPass.setTranslateY(200);
        txtPass.setPrefSize(260,30);
        txtPass.setPromptText("password");
        txtPass.setTooltip(new Tooltip("password"));
        txtPass.setFocusTraversable(false);
//        
        btnSignup.setTranslateX(20);
        btnSignup.setTranslateY(240);
        btnSignup.setPrefSize(260,30);
        btnSignup.setFocusTraversable(false);
        
        s.setTranslateX(20);
        s.setTranslateY(320);
        s.setPrefSize(260, 30);
        
        btnBack.setTranslateX(50);
        btnBack.setTranslateY(350);
        btnBack.setPrefSize(200,30);
        btnBack.setFocusTraversable(false);
        
        root.setTranslateX(110);
        root.setTranslateY(50);
        root.setPrefSize(300,400);
        root.getChildren().addAll(lblLogin,txtFirstName,txtLastName,txtUserName,txtPass,btnSignup,s,btnBack);
        root.setBackground(new Background(new BackgroundFill(Color.MINTCREAM, CornerRadii.EMPTY, Insets.EMPTY)));
        
        //here to incluode all commpont form singnUp_Form
        includeForm(root);
        
        btnBack.setOnAction(e->{
            includeForm(new Login_Form());
        });
        
   }
   public void includeForm(Pane pane){
       this.getChildren().addAll(new ImageView(new Image("Images/backImage.png")),pane);
     }
}
