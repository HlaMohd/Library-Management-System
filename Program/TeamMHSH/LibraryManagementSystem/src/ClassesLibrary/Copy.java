package ClassesLibrary;

import java.util.Date;

public class Copy {
    private int id;
    private String id_book;
    private  int numOfCop = 0;
    private Date insertDate;
    private boolean status = true;
    private String nameOfBook;
    public final static int MAXOFBOOK = 3;
    

    public Copy(String id_book , boolean status) {
        this.id_book = id_book;
        this.status = status;
        insertDate = new Date();
    }

    public String getId_book() {
        return id_book;
    }

    public boolean isStatus() {
        return status;
    }

    
    public void setNameOfBook(String nameOfBook) {
        this.nameOfBook = nameOfBook;
    }

    public String getNameOfBook() {
        return nameOfBook;
    }
    
    public Copy(){
//        numOfCop++;
        id = numOfCop;
        insertDate = new Date();   
    }

    public  void setNumOfCop(int numOfCop) {
        this.numOfCop = numOfCop;
    }

    
    public  int getNumOfCop() {
        return numOfCop;
    }
    
    public void setInsertDate(Date insertDate) {
        this.insertDate = insertDate;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public void setId(int id) {
         this.id = id;
    }
    public int getId() {
        return id;
    }

    public Date getInsertDate() {
        return insertDate;
    }

    public boolean getStatus() {
        return status;
    }
    @Override
    public String toString() {
        return "Copy{" + "id=" + id + ", insertDate=" + insertDate + ", status(is borrowed or not)=" + status + '}';
    }
    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Copy){
            return id == ((Copy)obj).id;
        }
        else
            return false;
    }  

    
}
