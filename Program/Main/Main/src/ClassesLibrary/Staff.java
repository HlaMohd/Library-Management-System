package ClassesLibrary;

import java.util.ArrayList;

public class Staff extends User{
    private static String idStaff ;
    public String department;

    private int maxNumOfCopBorrowed = 3;
    private double[] fines = new double[3];
    public Staff(int i,String firstName,String lastName,String userName,String password,String department){
        super(firstName,lastName,userName, password);
        idStaff = ("01"+i);
        super.setId(idStaff);
        this.department = department;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }
    
    public static String getIdStaff() {
        return idStaff;
    }
//    public static int getNumberOfStaff(){
//        return i;
//    }
    
   
    public void paidFines(Student s,double amount){
        s.setTotalFinesForStudent(s.getTotalFinesForStudent()-amount);
    }
    
}
