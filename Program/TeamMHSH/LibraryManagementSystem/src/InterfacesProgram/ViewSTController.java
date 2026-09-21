package InterfacesProgram;

import ClassesLibrary.Book_Loan;
import ClassesLibrary.Copy;
import LibraryClasses.Common;
import static LibraryClasses.Common.numberStudnetHaveOfBook;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ViewSTController implements Initializable {
    private Stage stage;
    private Scene scene;
    private Parent root;
    
    Alert alert = new Alert(Alert.AlertType.INFORMATION);
    
    @FXML
    private Button btnme;

    @FXML
    private Button btnDeleteLIb;
    
    @FXML
    private TextField txtbookIdtoBorrow;

    @FXML
    private Label lblbookStatus;
    
    @FXML
    private TextField btnborrowedStudentId;
    
    int getNumberOfCopy(String id_book){
        Connection con;
        Statement stmt;
        ResultSet rs;
        try{
            con = Common.getConnection();
                stmt = con.createStatement();
                rs = stmt.executeQuery("SELECT * FROM books WHERE id_book = " + id_book);
                
                while(rs.next()){
                    return rs.getInt(7);
                }
        }catch(Exception e){};
        return 0;
    }
    //done button
    @FXML
    void doneBorrow(ActionEvent event) {
        if(Common.Copybook.getId_book().equals(txtbookIdtoBorrow.getText())){
            Connection con;
            Statement stmt;
            ResultSet rs;
            int num2 = 0;//to know number of copies to decrement
            
             
            
            boolean test = false;
            try{
                con = Common.getConnection();
                stmt = con.createStatement();
                rs = stmt.executeQuery("SELECT * FROM students;");
                
                while(rs.next()){
                    if(rs.getString(2).equals(btnborrowedStudentId.getText())){
                        test = true;
                        numberStudnetHaveOfBook = rs.getInt(5);
                        break;
                    }
                    else
                        test = false;
                }
                num2 = getNumberOfCopy(Common.Copybook.getId_book());
                if(test && num2 > 0){
                     if(numberStudnetHaveOfBook < Copy.MAXOFBOOK ){
                         Book_Loan bookLoan = new Book_Loan(btnborrowedStudentId.getText());
                         
                         PreparedStatement pstmt = con.prepareStatement("INSERT INTO book_loan VALUES(?,?,?,?,?,?);");
                         
                         pstmt.setString(1, null);
                         pstmt.setString(2, btnborrowedStudentId.getText());
                         pstmt.setString(3, Common.Copybook.getId_book());
                         pstmt.setDate(4, Date.valueOf(bookLoan.getDateFrom()));
                         pstmt.setDate(5, Date.valueOf(bookLoan.getDateReturned()));
                         pstmt.setString(6,Common.staff.getId());
                         pstmt.executeUpdate();
                         
//                         //here to add book in table students
                         if(numberStudnetHaveOfBook == 0){
                             PreparedStatement pstmt2 = con.prepareStatement("UPDATE students SET numberOfBookHave = ?,id_bookname1 = ? WHERE id_user = ?;");
                             pstmt2.setInt(1,++numberStudnetHaveOfBook);
                             pstmt2.setString(2,Common.Copybook.getId_book());
                             pstmt2.setString(3,btnborrowedStudentId.getText());
                             pstmt2.addBatch();
                             pstmt2.executeBatch();
                         }
                         else if(numberStudnetHaveOfBook == 1){
                             PreparedStatement pstmt2 = con.prepareStatement("UPDATE students SET numberOfBookHave = ?,id_bookname2 = ? WHERE id_user = ?;");
                             pstmt2.setInt(1,++numberStudnetHaveOfBook);
                             pstmt2.setString(2,Common.Copybook.getId_book());
                             pstmt2.setString(3,btnborrowedStudentId.getText());
                             pstmt2.addBatch();
                             pstmt2.executeBatch();
                         }
                         else if(numberStudnetHaveOfBook == 2){
                             PreparedStatement pstmt2 = con.prepareStatement("UPDATE students SET numberOfBookHave = ?,id_bookname3 = ? WHERE id_user = ?;");
                             pstmt2.setInt(1,++numberStudnetHaveOfBook);
                             pstmt2.setString(2,Common.Copybook.getId_book());
                             pstmt2.setString(3,btnborrowedStudentId.getText());
                             pstmt2.addBatch();
                             pstmt2.executeBatch();
                             
                             
                         }
                        if(num2 == 1){
                            PreparedStatement pstmt3 = con.prepareStatement("UPDATE books SET numOfCopies = ? WHERE id_book = ?;");
                            pstmt3.setInt(1,--num2);
                            pstmt3.setString(2,Common.Copybook.getId_book());
                            pstmt3.addBatch();
                            pstmt3.executeBatch();
                            
                            PreparedStatement pstmt2 = con.prepareStatement("UPDATE copy_book SET status_book = ? WHERE id_book = ?;");
                            pstmt2.setBoolean(1,false);
                            pstmt2.setString(2,Common.Copybook.getId_book());
                            pstmt2.addBatch();
                            pstmt2.executeBatch();
                            Common.Copybook.setStatus(false);
                        }
                        else if(num2 > 1){
                            PreparedStatement pstmt2 = con.prepareStatement("UPDATE books SET numOfCopies = ? WHERE id_book = ?;");
                            pstmt2.setInt(1,--num2);
                            pstmt2.setString(2,Common.Copybook.getId_book());
                            pstmt2.addBatch();
                            pstmt2.executeBatch();
                            Common.Copybook.setStatus(true);
                         }
                        Common.CopybookList.set(Common.i,Common.Copybook);
                        con.close();
                        alert.setTitle("Borrow Book");
                        alert.setHeaderText("Successfully borrowed");
                        alert.showAndWait();
                     }
                     else{
                        alert.setTitle("Information of Student");
                        alert.setHeaderText("can not exceed a limited number to borrow(3)");
                        alert.showAndWait();
                     }
                     
                 }
                 else{
                    //we here why? if num2 or test
                    if(num2<=0){
                        lblbookStatus.setText("fasle");
                    }
                    else
                    {
                        alert.setTitle("Error");
                        alert.setHeaderText("Id Student " +  btnborrowedStudentId.getText() + " Not Found");
                        alert.showAndWait();
                    }
                 }
                
                    
                 con.close();
             }catch(Exception e){}
             
             
         }
    }
    @FXML
    void mehodme(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("Staff.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void methodDelete(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("DeletSt.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    
    @FXML
    void AddBookMethod(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("AddBook.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    
      @FXML
    void View_Book(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("ViewBooks.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    
      @FXML
    void methodReturnBook(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("Return_Book.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    
    @FXML
    void Log_Out(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("LogIn.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Connection con ;
        Statement stmt;
        ResultSet rs;
        try
        {
            con = Common.getConnection();
            stmt = con.createStatement();
            rs =  stmt.executeQuery("SELECT * FROM copy_book");
            while(rs.next()){
                Common.CopybookList.add(new Copy(rs.getString(2),rs.getBoolean(3)));
            }
            con.close();
        }catch(Exception e){
        }
        
        
        txtbookIdtoBorrow.setOnKeyReleased(e->{
            for(int i=0; i<Common.CopybookList.size(); i++){
                if(Common.CopybookList.get(i).getId_book().equals(txtbookIdtoBorrow.getText())){
                    if(Common.CopybookList.get(i).getStatus()){
                        Common.i = i;
                        Common.Copybook = new Copy(Common.CopybookList.get(i).getId_book(),Common.CopybookList.get(i).getStatus());
                        lblbookStatus.setText("true");
                    }
                    else
                        lblbookStatus.setText("false");
                    break;
                }
                else
                    lblbookStatus.setText("not found");
            }
            
                
            
        });
    }

}
