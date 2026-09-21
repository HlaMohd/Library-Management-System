package ClassesLibrary;

import java.util.ArrayList;

public class Student extends User {
    private static String idStudent;
    private static int i = 1;
    private String major;
    private ArrayList<Copy> borroewdCopies = new ArrayList<>();
    private ArrayList<Book_Loan> loans = new ArrayList<>();
    private int maxNumOfCopBorrowed = 3;
    private double totalFinesForStudent;
    public Student(String firstName,String lastName,String userName,String password,String major){
        super(firstName,lastName,userName, password);
        idStudent = "00"+(i++);
        super.setId(idStudent);
        this.major = major;
    }   

    public static String getIdStudent() {
        return idStudent;
    }
    public static int getNumberOfStudents(){
        
        return i;
    }

    public ArrayList<Copy> getBorroewdCopies() {
        return borroewdCopies;
    }

    public ArrayList<Book_Loan> getLoans() {
        return loans;
    }
    public void setTotalFinesForStudent(double totalFinesForStudent){
        this.totalFinesForStudent = totalFinesForStudent;
    }

    public double getTotalFinesForStudent() {
        return totalFinesForStudent;
    }
    
    public void totalFines(){
        for(Book_Loan l : loans){
            totalFinesForStudent += l.calFine();
        }
    }  
    public void borrowBook(Copy c){
        if(borroewdCopies.size() <= maxNumOfCopBorrowed){
            if(isTotalFineZero()){
                if(c.getStatus()){
                    borroewdCopies.add(c);
                    loans.add(new Book_Loan(this,c));
                    c.setStatus(false);
                    System.out.println("the copy is borrowed");
                }
                else
                   System.out.println("this copy is not available"); 
            }
            else
                System.out.println("Pay your fine to borrow new book"); 
        } 
        else
            System.out.println("you borrowed the maximum num of books");
    }
    public boolean isTotalFineZero(){
        for(Book_Loan l : loans){
            if(l.isFine()){
                return false;
            }   
        }
       return true;
    }
    public Book_Loan getLoanCopy(Copy c){
       for(Book_Loan l : loans){
            if(l.getCopyOfBook() == c){
                return l;
            }   
        }
       return null;
    }
    public String getMajor() {
        return major;
    }
    public void setMajor(String major) {
        this.major = major;
    }

    

    
}
