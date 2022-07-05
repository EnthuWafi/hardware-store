import java.util.ArrayList;

public class IDandPassword
{
    //variables
    private ArrayList<User> accounts = new ArrayList<User>();
    private int numberOfAccount;
    
    IDandPassword(){
        numberOfAccount = 0;
    }
    
    public void addAccount(User user){
        accounts.add(user);
        numberOfAccount++;
    }

    public User findAccount(String userID, String password){
        for (User user : accounts){
            if (user.verifyUser(userID, password)){
                return user;
            }
        }
        return null; //if found nothing
    }

    public void removeAccount(User user){
        accounts.remove(user);
    }

    public void displayAccount(){
        System.out.printf("%n%-5s %-30s %-5s %-20s %-20s %-20s%n", "No", "Full Name", "Age", "User ID", "Password", "Type");
        System.out.print("---------------------------------------------------------------------------------------------");
        for (int i = 0; i < accounts.size(); i++){
            User user = accounts.get(i);
            String type;
            if (user instanceof Customer){
                type = "Customer";
            }
            else {
                type = "Admin";
            }

            System.out.printf("%n%-5s %-30s %-5d %-20s %-20s %-10s", (i+1), user.getFullName(), user.getAge(),
                    user.getUserID(), user.getPassword(), type);
        }
    }

    public ArrayList<User> getAccounts(){return accounts;}
}
