import java.util.ArrayList;

public class Customer extends User
{
    private ShoppingCart shoppingCart;
    private TransactionHistory transactionHistory;
    private static int numberOfCustomer = 0;
    
    Customer(){
        super();
        shoppingCart = new ShoppingCart();
        transactionHistory  = new TransactionHistory();
        numberOfCustomer++;
    }
    
    Customer(String fullName, int age, String userID, String password){
        super(fullName, age, userID, password);
        shoppingCart = new ShoppingCart();
        transactionHistory  = new TransactionHistory();
        numberOfCustomer++;
    }
    
    //do customer stuff



    public ShoppingCart getShoppingCart(){ return shoppingCart;}
    public TransactionHistory getTransactionHistory(){ return transactionHistory;
    }
    public int getNumberOfCustomer(){return numberOfCustomer;}
}
