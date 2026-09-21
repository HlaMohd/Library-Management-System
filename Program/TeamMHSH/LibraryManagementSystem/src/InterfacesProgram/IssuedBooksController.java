package InterfacesProgram;

import ClassesLibrary.Book_Loan;
import LibraryClasses.Common;
import static LibraryClasses.Common.info;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author HP
 */
public class IssuedBooksController implements Initializable {

    /**
     * Initializes the controller class.
     */
    private Stage stage;
    private Scene scene;
    private Parent root;
    private double totalFine;
     @FXML
    private Button btnbacktomain;
     
    @FXML
    private TableView<Book_Loan> isuuedBooksTable;

    @FXML
    private TableColumn<Book_Loan, String>bookName;

    @FXML
    private TableColumn<Book_Loan, LocalDate> issuedDate;

    @FXML
    private TableColumn <Book_Loan, LocalDate> returnedDate;

    @FXML
    private TableColumn<Book_Loan, Double> fine;

    @FXML
    private TextField txtTotalFine;

   
    @FXML
    void calTotalFine(ActionEvent event) {
    }
    @FXML
    void BackToMain(ActionEvent event) throws IOException {
        showIsuedBook(); 
        root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }
    String getNameBook(String str){
        Connection con;
        Statement stmt;
        ResultSet rs;
        try{
            con = Common.getConnection();
            stmt = con.createStatement();

            rs =  stmt.executeQuery("SELECT * FROM books WHERE id_book = " + str);
            while(rs.next()){
                return rs.getString(2);
            }
            
        }catch(Exception e){}
        return "";
    }
    
    
    
    void showIsuedBook(){
        bookName.setCellValueFactory(new PropertyValueFactory<Book_Loan, String>("nameBook"));
        issuedDate.setCellValueFactory(new PropertyValueFactory<Book_Loan, LocalDate>("dateFrom"));
        returnedDate.setCellValueFactory(new PropertyValueFactory<Book_Loan, LocalDate>("dateReturned"));
        fine.setCellValueFactory(new PropertyValueFactory<Book_Loan, Double>("fine"));

        Common.copyBookList = FXCollections.observableArrayList();
        
        Connection con;
        Statement stmt;
        ResultSet rs;
        try
        {
            con = Common.getConnection();
            stmt = con.createStatement();
            rs =  stmt.executeQuery("SELECT * FROM book_loan WHERE id_student = " + info.getId());
            double tempFine;
            while(rs.next()){
                //this wat to sum total fine
                tempFine  = Book_Loan.calFine(rs.getDate(5).toLocalDate());//here to comparable between date now and day issued book
                totalFine += tempFine;
                Book_Loan book_loan = new Book_Loan(getNameBook(rs.getString(3)),rs.getDate(4).toLocalDate(),rs.getDate(5).toLocalDate(),tempFine);
                Common.copyBookList.add(book_loan);
            }
            
            con.close();
        }catch(Exception e){
            //toDo
        }
        
        txtTotalFine.setText(totalFine+"");
        isuuedBooksTable.setItems(Common.copyBookList);

    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        showIsuedBook(); 
//         TODO
    }    
    
}
