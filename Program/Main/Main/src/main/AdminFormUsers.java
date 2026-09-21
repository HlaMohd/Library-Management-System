package main;

import ClassesLibrary.Student;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javafx.scene.layout.Pane;

import javafx.scene.control.TableView;
import javafx.collections.ObservableList;
import javafx.collections.FXCollections;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;

public class AdminFormUsers extends Pane implements DBInf{
    TableView table = new TableView();
    ObservableList<Student> list = FXCollections.observableArrayList();
    Student std ;
    
    TableColumn columnName = new TableColumn("Name");
    TableColumn columnUser_id = new TableColumn("id");
    TableColumn columnUsername     = new TableColumn("Username");
    TableColumn columnPassword     = new TableColumn("Password");
    TableColumn columnMajor     = new TableColumn("Major");
    TableColumn columnTFine     = new TableColumn("Total Fine");
        
    public AdminFormUsers(){
        
        columnMajor.setPrefWidth(120);
        
        table.setTranslateX(0);
        table.setTranslateY(0);
        table.setPrefSize(450,300);
        table.getColumns().addAll(columnUser_id,columnName,columnUsername,columnPassword,columnMajor,
               columnTFine );
         showUsers();
         
        columnUser_id.setCellValueFactory(new PropertyValueFactory<Student, String>("firstName"));//haha to use it little time. to show id user
        columnName.setCellValueFactory(new PropertyValueFactory<Student, String>("lastName"));//name member in class 
        columnUsername.setCellValueFactory(new PropertyValueFactory<Student, String>("userName"));
        columnPassword.setCellValueFactory(new PropertyValueFactory<Student, String>("passWord"));
        columnMajor.setCellValueFactory(new PropertyValueFactory<Student, Integer>("major"));
        columnTFine.setCellValueFactory(new PropertyValueFactory<Student, Integer>("totalFinesForStudent"));
        
         table.setItems(list);
        this.getChildren().add(table);
    }
    public void getItemUser(ResultSet rs){
      Connection con ;
        Statement stmt;
        ResultSet rs_2;
        
        try
        {
            con = Common.getConnection();
            stmt = con.createStatement();
            
            rs_2 =  stmt.executeQuery("SELECT * FROM students");
            while(rs_2.next()){
                    if(rs_2.getString(2).equals(rs.getString(2))){
                        std.setMajor(rs_2.getString(3));
                        std.setTotalFinesForStudent(rs_2.getDouble(4));
                        break;
                    }
                }
        }catch(Exception e){
            
        }
    }
    public void showUsers(){
        
        Connection con ;
        Statement stmt;
        ResultSet rs;
        String major;
        
        try
        {
            con = Common.getConnection();
            stmt = con.createStatement();
            rs =  stmt.executeQuery("SELECT * FROM users WHERE modeControl != 1 AND modeControl != 2");
            
            while(rs.next()){
                std = new Student(rs.getString(2),rs.getString(4)+" " + rs.getString(5),rs.getString(6),rs.getString(7),"");
                
                getItemUser(rs);
//                std.setIdStudent(rs.getString(2));
                
                list.add(std);
                
            }
            
            con.close();
        }catch(Exception e){
            //toDo
        }
        
    }
}
