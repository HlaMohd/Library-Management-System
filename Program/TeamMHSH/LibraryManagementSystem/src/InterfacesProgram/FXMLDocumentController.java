/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package InterfacesProgram;


import ConnectionClasses.InfoStudent;
import LibraryClasses.Common;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
/**
 *
 * @author HP
 */
public class FXMLDocumentController implements Initializable {
    
    private Stage stage;
    private Scene scene;
    private Parent root;
      @FXML
    private Label lblid;

    @FXML
    private Label lblname;

    @FXML
    private Label alblusername;

    @FXML
    private Label lblmajor;

    @FXML
    void ViewIssuedbooks(ActionEvent event) throws IOException, IOException, IOException {
        root = FXMLLoader.load(getClass().getResource("IssuedBooks.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }
     @FXML
    void ViewBooks(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("ViewBooks.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    void Logout(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("LogIn.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    void EditUsername(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("EditUserName.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    void EditPassword(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("EditPassword.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        lblid.setText(Common.info.getId());
        lblname.setText(Common.info.getName());
        alblusername.setText(Common.info.getUsername());
        lblmajor.setText(Common.info.getMajor());
    }    
    
}
