package main;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;

import javafx.scene.control.Button;
import javafx.scene.layout.Pane;

public class Main extends Application {
   @Override
    public void start(Stage stage) {
        Login_Form root = new Login_Form();
        
        Scene scene = new Scene(root,500,500);
        
        stage.setScene(scene);
        stage.setTitle("Library ***** Demo");
        stage.show();
        stage.setResizable(false); 
    }

    public static void main(String[] args) {
        launch(args);
    }
    
}
