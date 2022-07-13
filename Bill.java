import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Bill {
    //variables
    private String fullName;
    private String userID;
    private ShoppingCart cart;
    private double totalCost;
    private int totalQuantity;

    private LocalDateTime dateTime;

    private PrintWriter billsOutput;

    Bill(Customer customer){
        fullName = customer.getFullName();
        userID = customer.getUserID();
        totalCost = customer.getShoppingCart().calculateCart();
        totalQuantity = customer.getShoppingCart().getItemList().size();
        cart = customer.getShoppingCart();

        dateTime = LocalDateTime.now();

        try {
            billsOutput = new PrintWriter("bills\\" + userID + "\\" + getDateTime() + ".txt");
        } catch (FileNotFoundException e) {
            System.out.println(e.toString());
        }
    }


    public String getFullName(){return fullName;}
    public int getTotalQuantity(){return totalQuantity;}
    public double getTotalCost(){return totalCost;}
    public String getDateTime(){
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH-mm-ss");
        return dateTime.format(dateTimeFormatter);
    }

    public void printBill(){
        //console
        System.out.printf("%nBill Generated on %s", getDateTime());
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

        //file output
        billsOutput.printf("%nBill Generated on %s", getDateTime());
        billsOutput.printf("%n%-5s %-30s %-16s %-15s %-3s   %-10s%n", "No", "Item Name", "Unit Price", "Item Discount", "Oty", "Total");
        billsOutput.print("----------------------------------------------------------------------------------------\n");
        for (int i = 0; i < cart.getItemList().size(); i++){
            CartItem item = (CartItem) cart.getItemList().get(i);

            billsOutput.printf("%-5s %-30s RM%11.2f %16s %5d   RM%11.2f%n", (i+1)+".", item.getItemName(),
                    item.getCost(), item.discountInPercentage()+"%", item.getQuantity(), item.calculateCost());
        }
        billsOutput.print("\n----------------------------------------------------------------------------------------\n");
        billsOutput.printf("%-73s   RM%11.2f%n", "Order Total", cart.calculateCart());
        billsOutput.print("----------------------------------------------------------------------------------------\n");
        billsOutput.close();
    }
}
