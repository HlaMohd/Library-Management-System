package ConnectionClasses;

public class InfoStudent {

    public void setPass(String pass) {
        this.pass = pass;
    }
    private String id;
    private String first_name;
    private String last_name;

    public void setUsername(String username) {
        this.username = username;
    }
    private String username;
    private String pass;
    private String major;

    public InfoStudent(String id, String first_name, String last_name, String username,String pass, String major) {
        this.id = id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.username = username;
        this.pass = pass;
        this.major = major;
    }

    public String getPass() {
        return pass;
    }

    public String getId() {
        return id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public String getLast_name() {
        return last_name;
    }
    
    public String getUsername() {
        return username;
    }
    
    public String getMajor() {
        return major;
    }
    
    public String getName() {
        return first_name+" " + last_name;
    }
    
    
}
