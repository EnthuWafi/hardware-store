import java.util.ArrayList;

public abstract class Inventory <T>{
    protected ArrayList<Item> itemList;

    Inventory(){
        itemList = new ArrayList<Item>();
    }

    public abstract void add(T t);
    public abstract void remove(T t);
    public abstract Item find(String itemName);

    public abstract void display();

    public ArrayList<Item> getItemList(){ return itemList;}
}
