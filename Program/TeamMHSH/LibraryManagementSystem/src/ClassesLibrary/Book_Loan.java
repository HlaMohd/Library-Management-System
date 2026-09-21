package ClassesLibrary;



import java.sql.Date;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Book_Loan {
    private User borrower;
    private String nameBook;
    private String id_student;
    private Copy copyOfBook;
    private LocalDate dateFrom;
    private LocalDate dateReturned;
    private double fine;
    

    
    
    public Book_Loan(String id_student){
        this.id_student = id_student;
        dateFrom = LocalDate.now();
        dateReturned = dateFrom.plusDays(3);
        fine = 0;
    }
    public Book_Loan(User borrower,Copy copyOfBook){
        this.borrower = borrower;
        this.copyOfBook = copyOfBook;
        dateFrom = LocalDate.now();
        dateReturned = dateFrom.plusDays(3);
        fine = 0;
    }
    
    public Book_Loan(String nameBook,LocalDate dateFrom,LocalDate dateReturned,double fine){
        this.nameBook = nameBook;
        this.dateFrom = dateFrom;
        this.dateReturned = dateReturned;
        this.fine = fine;
        
        
    }

    public void setId_student(String id_student) {
        this.id_student = id_student;
    }

    public void setFine(double fine) {
        this.fine = fine;
    }

   
    public double getFine() {
        return fine;
    }
    
    public void setDateFrom(LocalDate dateFrom) {
        this.dateFrom = dateFrom;
    }

    public String getNameBook() {
        return nameBook;
    }

    public void setNameBook(String nameBook) {
        this.nameBook = nameBook;
    }

    
    public LocalDate getDateReturned() {
        return dateReturned;
    }
     public String getId_student() {
        return id_student;
    }
    
    public void setDateReturned(LocalDate dateReturned) {
        this.dateReturned = dateReturned;
    }
    
     public LocalDate getDateFrom() {
        return dateFrom;
    }
    public User getBorrower() {
        return borrower;
    }

    public Copy getCopyOfBook() {
        return copyOfBook;
    }
    public  double calFine(){
        long daysLate ,  monthsLate;
        if (dateReturned.getYear() == LocalDate.now().getYear()){
            if(dateReturned.getMonth() == LocalDate.now().getMonth()){
                daysLate = ChronoUnit.DAYS.between(dateReturned, LocalDate.now());
                if(daysLate > 0)
                    return fine = daysLate * 15;
                else
                    return fine = 0;
            }
            else{
               monthsLate = ChronoUnit.MONTHS.between(dateReturned, LocalDate.now());
               return fine =  monthsLate * 500;
            }
        } 
        else
           return  fine = 10000;  
    } 
    public static  double calFine(LocalDate dateR){
        long daysLate ,  monthsLate;
        if (dateR.getYear() == LocalDate.now().getYear()){
            if(dateR.getMonth() == LocalDate.now().getMonth()){
                daysLate = ChronoUnit.DAYS.between(dateR, LocalDate.now());
                if(daysLate > 0)
                    return daysLate * 15;
                else
                    return  0;
            }
            else{
               monthsLate = ChronoUnit.MONTHS.between(dateR, LocalDate.now());
               return  monthsLate * 500;
            }
        } 
        else
           return  10000;  
    } 
    public boolean isFine(){
        if(calFine() > 0){
            return true;
        }
        else
            return false;
    }
    
}
