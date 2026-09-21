package ClassesLibrary;

import java.util.ArrayList;

public class Student extends User {
    private static String idStudent;
    private static int i = 1;
    private String major;

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

  
    public void setTotalFinesForStudent(double totalFinesForStudent){
        this.totalFinesForStudent = totalFinesForStudent;
    }

    public double getTotalFinesForStudent() {
        return totalFinesForStudent;
    }
    
    
    public String getMajor() {
        return major;
    }
    public void setMajor(String major) {
        this.major = major;
    }

    

    
}
