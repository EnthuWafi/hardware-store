
public class Admin extends User
{
    private StoreInventory storeInventory;
    private TransactionHistory transactionHistory;
    private IDandPassword idandPassword;
    private static int numberOfAdmin = 0;

    //normal
    Admin(String fullName, int age, String userID, String password, StoreInventory storeInventory,
          TransactionHistory transactionHistory, IDandPassword idandPassword){
        super(fullName, age, userID, password);
        this.storeInventory = storeInventory;
        this.transactionHistory = transactionHistory;
        this.idandPassword = idandPassword;
        numberOfAdmin++;
    }

    public StoreInventory getStoreInventory() {
        return storeInventory;
    }
    public TransactionHistory getTransactionHistory(){ return transactionHistory;
    }

    public IDandPassword getIdandPassword(){return idandPassword;}

    public int getNumberOfAdmin(){return numberOfAdmin;}
}
