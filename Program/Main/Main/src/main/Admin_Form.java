package main;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;


public class Admin_Form extends Application {
   @Override
    public void start(Stage stage) {
        AdminFormMain root = new AdminFormMain();
        
        Scene scene = new Scene(root,700,465);
        
        stage.setScene(scene);
        stage.setTitle("Library Management System");
        stage.show();
        stage.setResizable(false); 
    }

    public static void main(String[] args) {
        launch(args);
    }
    
}
