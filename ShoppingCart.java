import java.util.ArrayList;

public class ShoppingCart
{
    private ArrayList<CartItem> cart;
    private int numberOfItems;
    
    ShoppingCart(){
        cart = new ArrayList<CartItem>();
        numberOfItems = 0;
    }

    //processor
    public void addToCart(CartItem cartItem){
        //avoid duplicates
        CartItem item = findItem(cartItem.getItemName());
        if (item == null){
            cart.add(cartItem);
            numberOfItems++;
        }
        else {
            //copies quantity if exists
            item.setQuantity(cartItem.getQuantity());
        }

    }
    public void removeFromCart(CartItem cartItem){
        cart.remove(cartItem);
        numberOfItems--;
    }

    //two ways
    public CartItem findItem(String itemName){
        for (CartItem item : cart){
            if (item.getItemName().equalsIgnoreCase(itemName)){
                return item;
            }
        }
        return null;
    }
    public CartItem findItem(CartItem cartItem){
        for (CartItem item : cart){
            if (item == cartItem){
                return item;
            }
        }
        return null;
    }

    public void clearCart(){
        //After checkout, you need to clear whatever is in the shopping cart
        cart.clear();
        numberOfItems = 0;
    }

    public void displayCart(){
        System.out.printf("%n%-5s%-30s%-16s%-16s%-10s%n", "No", "Item Name", "Item Cost", "Item Discount", "Qty");
        for (int i = 0; i < cart.size(); i++){
            CartItem item = cart.get(i);

            System.out.printf("%-5s%-30sRM%11.2f%16s%11d%n", (i+1)+".", item.getItemName(),
                    item.getCost(), item.discountInPercentage()+"%", item.getQuantity());
        }
    }

    public double calculateCart(){
        double totalCost = 0;
        for (CartItem item : cart){
            totalCost += item.calculateCost();
        }
        return totalCost;
    }

    public int getNumberOfItems(){return numberOfItems;}
    public ArrayList<CartItem> getCart(){return cart;}
}
