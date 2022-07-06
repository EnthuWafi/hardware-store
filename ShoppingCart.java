import java.util.ArrayList;

public class ShoppingCart extends Inventory
{
    ShoppingCart(){
        super();
    }

    //processor
    public void add(Item item){
        //avoid duplicates
        CartItem cartItem = find(item.getItemName());
        if (cartItem == null){
            itemList.add(item);
        }
        else {
            //copies quantity if exists
            cartItem.setQuantity(cartItem.getQuantity());
        }

    }
    public void remove(Item item){
        //only if exist in inventory
        CartItem cartItem = find(item.getItemName());
        if (cartItem != null)
            itemList.remove(item);
    }

    //two ways
    public CartItem find(String itemName){
        for (Item item : itemList){
            if (item.getItemName().equalsIgnoreCase(itemName)){
                return (CartItem) item;
            }
        }
        return null;
    }

    public void clearCart(){
        //After checkout, you need to clear whatever is in the shopping cart
        itemList.clear();
    }

    public void display(){
        System.out.printf("%n%-5s%-30s%-16s%-16s%-10s%n", "No", "Item Name", "Item Cost", "Item Discount", "Qty");
        for (int i = 0; i < itemList.size(); i++){
            CartItem item = (CartItem) itemList.get(i);

            System.out.printf("%-5s%-30sRM%11.2f%16s%11d%n", (i+1)+".", item.getItemName(),
                    item.getCost(), item.discountInPercentage()+"%", item.getQuantity());
        }
    }

    public double calculateCart(){
        double totalCost = 0;
        for (Item item : itemList){
            totalCost += ((CartItem)item).calculateCost();
        }
        return totalCost;
    }

}
