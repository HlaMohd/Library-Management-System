package main;

import javafx.scene.layout.Pane;
import javafx.scene.layout.BorderPane;

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
import javafx.scene.effect.DropShadow;

import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;

public class AdminFormMain extends Pane  {
    private Label lblLogin = new Label("Library *****");
    private Button btnUsers  = new Button("Users");
    private Button btnStaffs  = new Button("Staffs");
    private Button btnAddStaff  = new Button("Add Staff");
    private Button btnProfileSetting  = new Button("Profile Setting");
    private Button btnLogout  = new Button("out");

    private Pane pane = new Pane();
    private Pane pane_2 = new Pane();//in middle
    BorderPane borderPane = new BorderPane();
    
    AdminFormProfileSetting profileSetting= new AdminFormProfileSetting();
    AdminFormUsers users= new AdminFormUsers();
    AdminFormStaff staffs= new AdminFormStaff();
    AdminFormAddStaff addStaffs= new AdminFormAddStaff();


   public AdminFormMain(){
    
        
        btnUsers.setTranslateX(15);
        btnUsers.setTranslateY(40);
        btnUsers.setPrefSize(160,10);
        btnUsers.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));
        btnUsers.setTextFill(Color.WHITE);
        btnUsers.setFont(new Font("Arial",18));
//        txtUserName.setPromptText("username");
        btnUsers.setTooltip(new Tooltip("Show Users"));
        btnUsers.setFocusTraversable(false);
        
        btnStaffs.setTranslateX(15);
        btnStaffs.setTranslateY(80);
        btnStaffs.setPrefSize(160,30);
        btnStaffs.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));
        btnStaffs.setTextFill(Color.WHITE);
        btnStaffs.setFont(new Font("Arial",18));
        btnStaffs.setTooltip(new Tooltip("Show Staff"));
        btnStaffs.setFocusTraversable(false);
        
        btnAddStaff.setTranslateX(15);
        btnAddStaff.setTranslateY(120);
        btnAddStaff.setPrefSize(160,30);
        btnAddStaff.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));
        btnAddStaff.setTextFill(Color.WHITE);
        btnAddStaff.setFont(new Font("Arial",18));
        btnAddStaff.setTooltip(new Tooltip("To add staff"));
        btnAddStaff.setFocusTraversable(false);
        
        btnProfileSetting.setTranslateX(15);
        btnProfileSetting.setTranslateY(160);
        btnProfileSetting.setPrefSize(160,30);
        btnProfileSetting.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));
        btnProfileSetting.setTextFill(Color.WHITE);
        btnProfileSetting.setFont(new Font("Arial",18));
        btnProfileSetting.setTooltip(new Tooltip("Show Profile Setting"));
        btnProfileSetting.setFocusTraversable(false);
        
        btnLogout.setTranslateX(15);
        btnLogout.setTranslateY(200);
        btnLogout.setPrefSize(160,30);
        btnLogout.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));
        btnLogout.setTextFill(Color.WHITE);
        btnLogout.setFont(new Font("Arial",18));
        btnLogout.setTooltip(new Tooltip("Log out admain"));
        btnLogout.setFocusTraversable(false);
        
        
//        pane.setTranslateX(0);
        pane.setTranslateY(120);
        pane.setPrefSize(200,300);

        pane.getChildren().addAll(btnUsers, btnStaffs, btnProfileSetting, btnAddStaff ,btnLogout);
//        pane.setBackground(new Background(new BackgroundFill(Color.MINTCREAM, CornerRadii.EMPTY, Insets.EMPTY)));

//        pane_2.setTranslateX(220);
        pane_2.setTranslateY(110);
        pane_2.setPrefSize(450,300);
        pane_2.setBackground(new Background(new BackgroundFill(Color.FLORALWHITE, CornerRadii.EMPTY, Insets.EMPTY)));
        pane_2.getChildren().add(profileSetting);
        borderPane.setCenter(pane_2);
        borderPane.setLeft(pane);
        

        
        DropShadow shadow = new DropShadow(20,Color.RED);
        btnUsers.setOnMouseEntered(e->{
            btnUsers.setEffect(shadow);
        });
        btnUsers.setOnMouseExited(e->{
            btnUsers.setEffect(null);
        });
        
        btnStaffs.setOnMouseEntered(e->{
            btnStaffs.setEffect(shadow);
        });
        btnStaffs.setOnMouseExited(e->{
            btnStaffs.setEffect(null);
        });
        
        btnAddStaff.setOnMouseEntered(e->{
            btnAddStaff.setEffect(shadow);
        });
        btnAddStaff.setOnMouseExited(e->{
            btnAddStaff.setEffect(null);
        });
        
        btnLogout.setOnMouseEntered(e->{
            btnLogout.setEffect(shadow);
        });
        btnLogout.setOnMouseExited(e->{
            btnLogout.setEffect(null);
        });
        
        btnLogout.setOnAction(e->{
            System.exit(0);
        });
        
        btnProfileSetting.setOnMouseEntered(e->{
            btnProfileSetting.setEffect(shadow);
        });
        btnProfileSetting.setOnMouseExited(e->{
            btnProfileSetting.setEffect(null);
        });
        
        btnUsers.setOnAction(e->{
            showNow(users);
            users.list.clear();
            users.showUsers();
        });
        
        btnProfileSetting.setOnAction(e->{
            showNow(profileSetting);
        });
        
        btnStaffs.setOnAction(e->{
            staffs.list.clear();
            showNow(staffs);
            staffs.showStaff();
        });
        
        btnAddStaff.setOnAction(e->{
            showNow(addStaffs);
        });
        
        includeForm(borderPane);
//        btnCreate.setOnAction(e->{
////            includeForm(new SignUp_Form());
//        });
   }
     public void includeForm(Pane pane){
       
       this.getChildren().addAll(new ImageView(new Image("Images/backGround.JPEG")),pane);
     }
     public void addStaff(){
         showNow(addStaffs);
     }
     
     public void showNow(Pane pane){
         pane_2.getChildren().remove(profileSetting);
         pane_2.getChildren().remove(users);
         pane_2.getChildren().remove(staffs);
         pane_2.getChildren().remove(addStaffs);
         
         pane_2.getChildren().add(pane);
     }
}

