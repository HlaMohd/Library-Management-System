package ClassesLibrary;

import java.util.ArrayList;

public class Book {
    private int id;
    private static int numOfBook = 0;
    private String title;
    private String publisher;
    private String auther;
    private int year;
    private int numOfCopies;
    private ArrayList<Copy> arrOfCopies = new ArrayList<>();
    private double price;
    private boolean status = false;
    
    public Book(String title,String publisher,String auther,int numOfCopies,double price,int year){
        numOfBook++;
        
        this.title = title;
        this.publisher = publisher;
        this.auther = auther;
        this.numOfCopies = numOfCopies;
        this.year = year;
        this.price = price;
        
        for(int i = 0; i < numOfCopies; i++) {
            arrOfCopies.add(new Copy());
            arrOfCopies.get(i).setNameOfBook(this.title);
            arrOfCopies.get(i).setId(id);
        }
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public void setYear(int year) {
        this.year = year;
    }
    public int getYear() {
        return year;
    }
    public void addCopy(int numOfAddedCopies){ //Adds a new copies object to book
        numOfCopies += numOfAddedCopies ;
        for(int i = 0; i < numOfAddedCopies; i++) {
            arrOfCopies.add(new Copy());
        }
    }
    
    public boolean isAvailable(){
        for(Copy c:arrOfCopies){
            if(c.getStatus() == true)
                return true;   
        }
        return false;        
    }
    public static int getNumOfBook() {
        return numOfBook;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getPublisher() {
        return publisher;
    }

    public String getAuther() {
        return auther;
    }

    public int getNumOfCopies() {
        return numOfCopies;
    }
    public double getPrice() {
        return price;
    }

    public boolean getStatus() {
        return status;
    }

    public ArrayList<Copy> getArrOfCopies() {
        return arrOfCopies;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public void setAuther(String auther) {
        this.auther = auther;
    }

    public void setNumOfCopies(int numOfCopies) {
        this.numOfCopies = numOfCopies;
    }

    public void setArrOfCopies(ArrayList<Copy> arrOfCopies) {
        this.arrOfCopies = arrOfCopies;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
     @Override
    public String toString() {
        return "Book{" + "id=" + id + ", title=" + title + ", publisher=" + publisher + ", auther=" + auther + ", numOfCopies=" + numOfCopies + ", arrOfCopies=" + arrOfCopies.toString() + ", price=" + price + ", status=" + status + '}';
    }
     @Override
    public boolean equals(Object obj) {
        if(obj instanceof Book){
            return id == ((Book)obj).id;
        }
        else
            return false;
    }  
    
}
