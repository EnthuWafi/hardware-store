import java.util.ArrayList;

public class TransactionHistory {
    private ArrayList<Bill> bills;
    private int numberOfBills;
    TransactionHistory(){
        bills = new ArrayList<Bill>();
        numberOfBills = 0;
    }

    //processor
    public void add(Bill bill){
        bills.add(bill);
        numberOfBills++;
    }

    public void displayTransaction(){
        System.out.printf("%-30s %-17s %-11s %-20s", "Full Name", "Total Quantity", "Total Cost", "Date");
        System.out.printf("%n-------------------------------------------------------------------");
        for (int i = 0; i < bills.size(); i++){
            Bill bill = bills.get(i);
            System.out.printf("%n%-30s %14d  RM%11.2f %-20s", bill.getFullName(), bill.getTotalQuantity(),
                    bill.getTotalCost(), bill.getDateTime());
        }
    }
    
}
