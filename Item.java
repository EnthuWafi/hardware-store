
public class Item
{
    private String itemName;
    private double cost;
    private double discountRate;

    
    //normal
    Item(String itemName, double cost, double discountRate){
        this.itemName = itemName;
        this.cost = cost;
        this.discountRate = discountRate;
    }
    
    //copy Constructor
    Item(Item item){
        this.itemName = item.itemName;
        this.cost = item.cost;
        this.discountRate = item.discountRate;
    }
    
    //default
    Item(){
        this.itemName = "";
        this.cost = 0;
        this.discountRate = 0;
    }
    
    //mutator
    public void setItemName(String itemName){this.itemName = itemName;}
    public void setCost(double cost){this.cost = cost;}
    public void setDiscountRate(double discountRate){this.discountRate = discountRate;}
    public void setItem(String itemName, double cost, double discountRate){
        this.itemName = itemName;
        this.cost= cost;
        this.discountRate = cost;
    }
    
    //accessor
    public String getItemName(){return itemName;}
    public double getCost(){return cost;}
    public double getDiscountRate(){return discountRate;}

    //processor
    public String discountInPercentage(){
        return String.format("%.1f", discountRate*100);
    }
}
