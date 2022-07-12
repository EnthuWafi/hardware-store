import java.util.ArrayList;

public class StoreInventory extends Inventory <StoreItem>
{
    StoreInventory(){
        super();
    }

    //processor
    public void add(StoreItem item){
        //avoid duplicates
        StoreItem storeItem = find(item.getItemName());
        if (storeItem == null){
            itemList.add(item);
        }
        else{
            //if a duplicates exist, rewrite over the existing item
            storeItem.setCost(storeItem.getCost());
            storeItem.setDiscountRate(storeItem.getDiscountRate());
            storeItem.setStock(storeItem.getStock());
        }
    }

    public void remove(StoreItem item){
        StoreItem storeItem = find(item.getItemName());
        if (storeItem != null){
            itemList.remove(item);
        }
    }

    //two ways
    public StoreItem find(String itemName){
        for (Item item : itemList){
            if (item.getItemName().equalsIgnoreCase(itemName)){
                return (StoreItem) item;
            }
        }
        return null;
    }

    public void display(){
        System.out.printf("%n%-5s%-30s%-16s%-16s%-10s%n", "No", "Item Name", "Item Cost", "Item Discount", "In Stock");
        for (int i = 0; i < itemList.size(); i++){
            StoreItem item = (StoreItem) itemList.get(i);

            System.out.printf("%-5s%-30sRM%11.2f%16s%11d%n", (i+1)+".", item.getItemName(),
                    item.getCost(), item.discountInPercentage()+"%", item.getStock());
        }
    }

}
