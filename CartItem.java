
public class CartItem extends Item
{
    private int quantity;

    //default
    CartItem(){
        super();
        quantity = 0;
    }
    //normal
    CartItem(String itemName, double cost, double discountRate, int quantity){
        super(itemName, cost, discountRate);
        this.quantity = quantity;
    }
    //weird copy (copies store item)
    CartItem(StoreItem storeItem){
        super(storeItem);
        this.quantity = 0;
    }


    public void setQuantity(int quantity){this.quantity = quantity;}

    public int getQuantity(){return quantity;}

    public double calculateCost(){
        double unitPrice = getCost() - (getCost() * getDiscountRate());
        return unitPrice * quantity;
    }
}
