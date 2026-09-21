package ClassesLibrary;

import java.util.ArrayList;

public class Staff extends User{
    private static String idStaff ;
    private static int i = 1 ;
    private String department;
    private ArrayList<Copy> borroewdCopies = new ArrayList<>();
    private ArrayList<Book_Loan> loans = new ArrayList<>();
    private int maxNumOfCopBorrowed = 3;
    private double[] fines = new double[3];
    public Staff(String firstName,String lastName,String userName,String password,String department){
        super(firstName,lastName,userName, password);
        idStaff = ("01"+i++);
        super.setId(idStaff);
        this.department = department;
    }   

    public static String getIdStaff() {
        return idStaff;
    }
    public static int getNumberOfStaff(){
        return i;
    }
    
    public String getDepartment() {
        return department;
    }
    public void returnBook(Student s,Copy c){
        s.getBorroewdCopies().remove(c);
        c.setStatus(true);
        s.totalFines();
        s.getLoans().remove(s.getLoanCopy(c));
        if(s.getLoanCopy(c).isFine()){
            System.out.println("pay your fine so you can borrow another cooks or copies");
        }
    }
    public void paidFines(Student s,double amount){
        s.setTotalFinesForStudent(s.getTotalFinesForStudent()-amount);
    }
    
}
