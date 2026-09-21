package main;

import ClassesLibrary.Staff;
import ClassesLibrary.Student;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javafx.scene.layout.Pane;

import javafx.scene.control.TableView;
import javafx.collections.ObservableList;
import javafx.collections.FXCollections;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;

public class AdminFormStaff extends Pane implements DBInf{
    TableView table = new TableView();
    ObservableList<Staff> list = FXCollections.observableArrayList();
    Staff staff ;
    
    TableColumn columnName = new TableColumn("Name");
    TableColumn columnUser_id = new TableColumn("id");
    TableColumn columnUsername     = new TableColumn("Username");
    TableColumn columnPassword     = new TableColumn("Password");
    TableColumn columnDeparment     = new TableColumn("Department");
        
    public AdminFormStaff(){
        
        columnDeparment.setPrefWidth(120);
        
        table.setTranslateX(0);
        table.setTranslateY(0);
        table.setPrefSize(450,300);//same size pane in AdminFormMain class
        
        table.getColumns().addAll(columnUser_id,columnName,columnUsername,columnPassword,columnDeparment );
        
        
         showStaff();
         
        columnUser_id.setCellValueFactory(new PropertyValueFactory<Staff, String>("firstName"));//haha to use it little time. to show id user
        columnName.setCellValueFactory(new PropertyValueFactory<Staff, String>("lastName"));//name member in class 
        columnUsername.setCellValueFactory(new PropertyValueFactory<Staff, String>("userName"));
        columnPassword.setCellValueFactory(new PropertyValueFactory<Staff, String>("passWord"));
        columnDeparment.setCellValueFactory(new PropertyValueFactory<Staff, String>("department"));
        
        
         table.setItems(list);
        this.getChildren().addAll(table);

    }
    public void getItemUser(ResultSet rs){
      Connection con ;
        Statement stmt;
        ResultSet rs_2;
        
        try
        {
            con = Common.getConnection();
            stmt = con.createStatement();
            
            rs_2 =  stmt.executeQuery("SELECT * FROM staffs");
            while(rs_2.next()){
                if(rs_2.getString(2).equals(rs.getString(2))){
                        staff.setDepartment(rs_2.getString(3)) ;
                        con.close();
                        break;
                    }
                    
                }
        }catch(Exception e){
            
        }
    }
    public void showStaff(){
        
        Connection con ;
        Statement stmt;
        ResultSet rs;
        String major;
        
        try
        {
            con = Common.getConnection();
            stmt = con.createStatement();
            rs =  stmt.executeQuery("SELECT * FROM users WHERE modeControl = 2");
            
            while(rs.next()){
                staff = new Staff(0,rs.getString(2),rs.getString(4)+" " + rs.getString(5),rs.getString(6),rs.getString(7),"");
                getItemUser(rs);
//                std.setIdStudent(rs.getString(2));
                
                list.add(staff);
                
            }
            
            con.close();
        }catch(Exception e){
            //toDo
        }
        
    }
    
}
