public class StoreItem extends Item {
    private int stock;

    StoreItem(){
        super();
        stock = 0;
    }

    StoreItem(String itemName, double cost, double discountRate, int stock){
        super(itemName, cost, discountRate);
        this.stock = stock;
    }

    public void setStock(int stock){this.stock = stock;}

    public void setStoreItem(String itemName, double cost, double discountRate, int stock){
        super.setItem(itemName, cost, discountRate);
        this.stock = stock;
    }
    public int getStock(){return stock;}


}
