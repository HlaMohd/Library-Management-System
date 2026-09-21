package InterfacesProgram;

import ClassesLibrary.Book;
import LibraryClasses.Common;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import static javafx.scene.input.KeyCode.ENTER;
import javafx.stage.Stage;

public class ViewBooksController implements Initializable {

    private Stage stage;
    private Scene scene;
    private Parent root;
    private  int w = 0;//temp to search at last items
    @FXML
    private TableView<Book> table;

    @FXML
    private TableColumn <Book, Integer> bookId;

    @FXML
    private TableColumn<Book, String> bookName;

    @FXML
    private TableColumn<Book, String> bookPublisher;

    @FXML
    private TableColumn<Book, String>bookAuther;

    @FXML
    private TableColumn<Book, Integer>bookPrice;

    @FXML
    private TableColumn<Book, Integer>bookYear;

    @FXML
    private TableColumn<Book, Integer> numOfCopies;

    @FXML
    private Button btnbacktomain;

     @FXML
    private TextField txtnameOfBookToSearch;
     
     
     
    @FXML
    void backtomain(ActionEvent event) throws IOException {
        if(Common.mode == 3){
            root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
        else if (Common.mode == 2){
            root = FXMLLoader.load(getClass().getResource("Staff.fxml"));
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }

    }
     ObservableList<Book> list;
     
    public void showBooks(){
        Connection con ;
        Statement stmt;
        ResultSet rs;
        String major;
        
        Common.bookList = FXCollections.observableArrayList();
         
        try
        {
            con = Common.getConnection();
            stmt = con.createStatement();
            rs =  stmt.executeQuery("SELECT * FROM books ");

            while(rs.next()){
                //rs.getInt(6)->year
                Book book = new Book(rs.getString(2), rs.getString(3),rs.getString(4),rs.getInt(7) , rs.getInt(5),rs.getInt(6));
                book.setYear(rs.getInt(6));
                book.setId(rs.getInt(1));
                Common.bookList.add(book);
                
            }
            
            con.close();
        }catch(Exception e){
            //toDo
        }
        
        bookId.setCellValueFactory(new PropertyValueFactory<Book, Integer>("id"));
        bookName.setCellValueFactory(new PropertyValueFactory<Book, String>("title"));
        bookPublisher.setCellValueFactory(new PropertyValueFactory<Book, String>("publisher"));
        bookAuther.setCellValueFactory(new PropertyValueFactory<Book, String>("auther"));
        bookPrice.setCellValueFactory(new PropertyValueFactory<Book, Integer>("price"));
        bookYear.setCellValueFactory(new PropertyValueFactory<Book, Integer>("year"));
        numOfCopies.setCellValueFactory(new PropertyValueFactory<Book, Integer>("numOfCopies"));
        
        table.setItems(Common.bookList);
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         showBooks();
         int w = table.getItems().size();
         //Search for book in tabel
         txtnameOfBookToSearch.setOnKeyReleased(e->{
             if(txtnameOfBookToSearch.getText().isEmpty()){
                 showBooks();
             }
             else{
                for(int i=0; i< w; i++){
                    if(table.getItems().get(0).getTitle().toLowerCase().startsWith(txtnameOfBookToSearch.getText().toLowerCase())){
                        table.getSelectionModel().select(0);
                    }
                    else{
                        table.getItems().remove(0);
                    }
                }
             }
         });
        // TODO
    }    
    
}
