
/**
 * USER CLASS
 * ABSTRACT 
 **/
public abstract class User
{
    private String fullName;
    private int age;
    private String userID;
    private String password;
    
    User(){
        fullName = "";
        age = 0;
        userID = "";
        password = "";
    }
    User(String fullName, int age, String userID, String password){
        this.fullName = fullName;
        this.age = age;
        this.userID = userID;
        this.password = password;
    }
    
    public String getFullName(){return fullName;}
    public int getAge(){return age;}
    public String getUserID() {return userID;}
    public String getPassword() {return password;}
    
    public void setFullName(String fullName){this.fullName = fullName;}
    public void setAge(int age){this.age = age;}  
    public void setUserID(String userID){ this.userID = userID;}
    public void setPassword(String password){ this.password = password;}
    
    //processor
    public boolean verifyUser(String userID, String password){
        //userID doesn't have to be precise but password must be precise
        if (this.userID.equalsIgnoreCase(userID) && this.password.equals(password)){
            return true;
        }
        else{
            return false;
        }
    }
}
