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

public class Login_Form extends Pane  {
    private Label lblLogin = new Label("Library *****");
    private TextField txtUserName  = new TextField();
    private PasswordField txtPass  = new PasswordField();
    private Button btnlogin  = new Button("Log In");
    private Separator s = new Separator();
    private Button btnCreate  = new Button("Sign Up");
    private Pane root = new Pane();
    private Pane pane = new Pane();


   public Login_Form(){
        lblLogin.setTranslateX(5);
        lblLogin.setTranslateY(25);
//        System.out.println( Font.getFontNames());;
        lblLogin.setFont(new Font("Arial Bold",30));
        
        txtUserName.setTranslateX(20);
        txtUserName.setTranslateY(80);
        txtUserName.setPrefSize(260,30);
        txtUserName.setPromptText("username");
        txtUserName.setTooltip(new Tooltip("username"));
        txtUserName.setFocusTraversable(false);
        
        txtPass.setTranslateX(20);
        txtPass.setTranslateY(120);
        txtPass.setPrefSize(260,30);
        txtPass.setPromptText("password");
        txtPass.setTooltip(new Tooltip("password"));
        txtPass.setFocusTraversable(false);
        
        btnlogin.setTranslateX(20);
        btnlogin.setTranslateY(160);
        btnlogin.setPrefSize(260,30);
        btnlogin.setFocusTraversable(false);
        
        s.setTranslateX(20);
        s.setTranslateY(220);
        s.setPrefSize(260, 30);
        
        btnCreate.setTranslateX(50);
        btnCreate.setTranslateY(250);
        btnCreate.setPrefSize(200,30);
        btnCreate.setFocusTraversable(false);
        
        root.setTranslateX(110);
        root.setTranslateY(100);
        root.setPrefSize(300,300);
        root.getChildren().addAll(lblLogin,txtUserName,txtPass,btnlogin,s,btnCreate);
        root.setBackground(new Background(new BackgroundFill(Color.MINTCREAM, CornerRadii.EMPTY, Insets.EMPTY)));

        includeForm(root);
        btnCreate.setOnAction(e->{
            includeForm(new SignUp_Form());
        });
   }
     public void includeForm(Pane pane){
       this.getChildren().addAll(new ImageView(new Image("Images/backImage.png")),pane);
     }
}
