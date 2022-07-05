
public class Admin extends User
{
    private StoreInventory storeInventory;
    private static int numberOfAdmin = 0;
    
    //default
    Admin(){
        super();
        numberOfAdmin++;
    }
    //normal
    Admin(String fullName, int age, String userID, String password, StoreInventory storeInventory){
        super(fullName, age, userID, password);
        this.storeInventory = storeInventory;
        numberOfAdmin++;
    }
    
    //do admin stuff

    public StoreInventory getStoreInventory() {
        return storeInventory;
    }
}
