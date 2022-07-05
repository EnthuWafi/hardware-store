import java.util.ArrayList;

public class StoreInventory
{
    private ArrayList<StoreItem> itemList;
    private int numberOfItems;
    
    StoreInventory(){
        itemList = new ArrayList<StoreItem>();
        numberOfItems = 0;
    }


    //processor
    public void addItem(StoreItem storeItem){
        //avoid duplicates
        StoreItem item = findItem(storeItem.getItemName());
        if (item == null){
            itemList.add(storeItem);
            numberOfItems++;
        }
        else{
            //if a duplicates exist, rewrite over the existing item
            item.setCost(storeItem.getCost());
            item.setDiscountRate(storeItem.getDiscountRate());
            item.setStock(storeItem.getStock());
        }
    }

    public void removeItem(StoreItem storeItem){
        StoreItem item = findItem(storeItem.getItemName());
        if (item != null){
            itemList.remove(item);
            numberOfItems--;
        }
        else {
            System.out.println("No such Item exists in inventory");
        }
    }
    public void modifyItem(StoreItem original, StoreItem replace){

    }

    //two ways
    public StoreItem findItem(String itemName){
        for (StoreItem item : itemList){
            if (item.getItemName().equalsIgnoreCase(itemName)){
                return item;
            }
        }
        return null;
    }

    public StoreItem findItem(StoreItem storeItem){
        for (StoreItem item : itemList){
            if (item == storeItem){
                return item;
            }
        }
        return null;
    }


    public void display(){
        System.out.printf("%n%-5s%-30s%-16s%-16s%-10s%n", "No", "Item Name", "Item Cost", "Item Discount", "In Stock");
        for (int i = 0; i < itemList.size(); i++){
            StoreItem item = itemList.get(i);

            System.out.printf("%-5s%-30sRM%11.2f%16s%11d%n", (i+1)+".", item.getItemName(),
                    item.getCost(), item.discountInPercentage()+"%", item.getStock());
        }
    }

    public int getNumberOfItems(){return numberOfItems;}
}
