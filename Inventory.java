import java.util.ArrayList;

public abstract class Inventory {
    protected ArrayList<Item> itemList;

    Inventory(){
        itemList = new ArrayList<Item>();
    }

    public abstract void add(Item item);
    public abstract void remove(Item item);
    public abstract Item find(String itemName);

    public abstract void display();

}
