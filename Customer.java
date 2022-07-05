import java.util.ArrayList;

public class Customer extends User
{
    private ShoppingCart shoppingCart;
    private TransactionHistory transactionHistory = new TransactionHistory();
    private static int numberOfCustomer = 0;
    
    Customer(){
        super();
        shoppingCart = new ShoppingCart();
        numberOfCustomer++;
    }
    
    Customer(String fullName, int age, String userID, String password){
        super(fullName, age, userID, password);
        shoppingCart = new ShoppingCart();
        numberOfCustomer++;
    }
    
    //do customer stuff



    public ShoppingCart getShoppingCart(){ return shoppingCart;}
    public TransactionHistory getTransactionHistory(){ return transactionHistory;
    }
    public int getNumberOfCustomer(){return numberOfCustomer;}
}
