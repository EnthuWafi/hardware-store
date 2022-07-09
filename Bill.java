import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Bill {
    //variables
    private final String fullName;
    private final ShoppingCart cart;
    private final double totalCost;
    private final int totalQuantity;

    private final LocalDateTime dateTime;

    Bill(){
        fullName = "";
        totalCost = 0;
        totalQuantity = 0;
        cart = null;

        dateTime = LocalDateTime.now();
    }

    Bill(Customer customer){
        fullName = customer.getFullName();
        totalCost = customer.getShoppingCart().calculateCart();
        totalQuantity = customer.getShoppingCart().getItemList().size();
        cart = customer.getShoppingCart();

        dateTime = LocalDateTime.now();
    }


    public String getFullName(){return fullName;}
    public int getTotalQuantity(){return totalQuantity;}
    public double getTotalCost(){return totalCost;}
    public String getDate(){
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        return dateTime.format(dateTimeFormatter);
    }

    public void printBill(){
        System.out.printf("%nBill Generated on %s", getDate());
        System.out.printf("%n%-5s %-30s %-16s %-15s %-3s   %-10s%n", "No", "Item Name", "Unit Price", "Item Discount", "Oty", "Total");
        System.out.print("----------------------------------------------------------------------------------------\n");
        for (int i = 0; i < cart.getItemList().size(); i++){
            CartItem item = (CartItem) cart.getItemList().get(i);

            System.out.printf("%-5s %-30s RM%11.2f %16s %5d   RM%11.2f%n", (i+1)+".", item.getItemName(),
                    item.getCost(), item.discountInPercentage()+"%", item.getQuantity(), item.calculateCost());
        }
        System.out.print("\n----------------------------------------------------------------------------------------\n");
        System.out.printf("%-73s   RM%11.2f%n", "Order Total", cart.calculateCart());
        System.out.print("----------------------------------------------------------------------------------------\n");
    }
}
