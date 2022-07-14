import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Main
{
    //some variable
    private static final Scanner sc = new Scanner(System.in);
    
    //ids and password
    static IDandPassword idandPassword = new IDandPassword();

    //store inventory
    static StoreInventory storeInventory = new StoreInventory();

    //transaction history
    static TransactionHistory transactionHistory = new TransactionHistory();

    public static void main(String[] args){
        //Start of program
        int userInput;

        System.out.print("***** Hardware Shop Management System ***** ~ Created by GROUP10\n\n");
        try {
            File inputStoreFile = new File("Item.txt");
            File inputAccountFile = new File("Account.txt");

            Scanner inStore = new Scanner(inputStoreFile);
            Scanner inAccount = new Scanner(inputAccountFile);

            //read store items file
            while (inStore.hasNextLine()){
                String nextLine = inStore.nextLine();
                StringTokenizer st = new StringTokenizer(nextLine, ",");

                String itemName = st.nextToken();
                double cost = Double.parseDouble(st.nextToken());
                double discount = Double.parseDouble(st.nextToken());
                int stock = Integer.parseInt(st.nextToken());

                storeInventory.add(new StoreItem(itemName, cost, discount, stock));
            }

            //read accounts
            while (inAccount.hasNextLine()){
                String nextLine = inAccount.nextLine();
                StringTokenizer st = new StringTokenizer(nextLine, ",");

                String fullName = st.nextToken();
                int age = Integer.parseInt(st.nextToken());
                String userID = st.nextToken();
                String password = st.nextToken();
                boolean isAdmin = Boolean.parseBoolean(st.nextToken());

                if (isAdmin){
                    idandPassword.addAccount(new Admin(fullName, age, userID, password, storeInventory, transactionHistory, idandPassword));
                }
                else {
                    idandPassword.addAccount(new Customer(fullName, age, userID, password));
                }
            }

            while (true) {
                System.out.print("============ HARDWARE SHOP MANAGEMENT System ============\n");
                System.out.print("[MAIN MENU]\n\t1) Register\n\t2) Login\n\t3) Exit Program\n>");

                userInput = sc.nextInt();
                sc.nextLine();

                System.out.println();
                //Register
                if (userInput == 1) {
                    System.out.println("Register");
                    registerPath();
                }
                //Login
                else if (userInput == 2) {
                    System.out.println("Login");
                    loginPath();
                }
                //Exit Program
                else if (userInput == 3) {
                    System.out.println("Exit");

                    break;
                } else {
                    System.out.print("Please only choose a number between 1 to 3.");
                }
                System.out.println("Enter any key to continue..\n");
                sc.nextLine();
            }
        }
        catch (FileNotFoundException e){
            e.printStackTrace();
        }
        
        //End of program
    }
    
    static void registerPath(){
        int userInput;
        
        //registration
        User user;
        String fullName;
        int age;
        String userID, password;
        
        System.out.println("============ USER REGISTRATION ============\n");
        
        System.out.println("[What account will you be making?:]\n\t1) Customer Account\n\t2) Admin Account\n>");
        userInput = sc.nextInt();
        sc.nextLine();
        
        if (userInput == 1 || userInput == 2){
            System.out.print("\t=== REGISTRATION FORM ===\n");
            System.out.print("Full Name \t\t: ");
            fullName = sc.nextLine();
            
            System.out.print("Age \t\t\t: ");
            age = sc.nextInt();
            sc.nextLine(); //whitespace
            
            System.out.print("User ID \t\t: ");
            userID = sc.nextLine();
            
            System.out.print("Password \t\t: ");
            password = sc.nextLine();
            
            
            if (userInput == 1){
                user = new Customer(fullName, age, userID, password);
            }
            else {
                user = new Admin(fullName, age, userID, password, storeInventory, transactionHistory, idandPassword);
            }

            if (idandPassword.findAccount(userID, password) == null){
                idandPassword.addAccount(user);
                System.out.println("Registration Complete!");
            }
            else {
                System.out.println("A user with the same UserID and Password already exist");
            }
        }  
        else{
            System.out.println("Please enter a valid input!");            
        }
    }

    static void loginPath(){
        //login
        String userID, password;

        System.out.println("============ USER LOGIN ============\n");

        System.out.print("UserID\t\t\t: ");
        userID = sc.nextLine();
        System.out.print("Password\t\t: ");
        password = sc.nextLine();

        User user = idandPassword.findAccount(userID, password);
        if (user != null){
            System.out.println("\nLogin Successful!\n");

            if (user instanceof Customer){
                //customer path
                Customer customer = (Customer) user;
                customerPath(customer);
            }
            else if (user instanceof Admin) {
                //admin path
                Admin admin = (Admin) user;
                adminPath(admin);
            }
        }
        else{
            System.out.println("\nLogin Failed!\n");
        }
    }

    static void customerPath(Customer customer){
        int userInput;
        boolean loggedIn = true;

        while (loggedIn) {
            System.out.println("========== HARDWARE STORE ==========");
            System.out.println("WELCOME, CUSTOMER " + customer.getFullName());
            System.out.print("[AVAILABLE OPTIONS]\n\t1) Display Product\n\t2) View Cart\n\t3) Transaction History\n\t4) Log out \n>");

            userInput = sc.nextInt();
            sc.nextLine();

            if (userInput == 1) {
                //product
                productPath(customer);
            } else if (userInput == 2) {
                //view cart
                cartPath(customer);
            } else if (userInput == 3){
                //transaction
                System.out.println("[Transaction History]");
                customer.getTransactionHistory().displayTransaction();
                System.out.println();
            }
            else if (userInput == 4) {
                //log out
                System.out.println("Logging out...");
                loggedIn = false;
            } else {
                //invalid
                System.out.println("Invalid input.. Please only enter a number between 1-3!");
            }
            System.out.println("\n============================\n");
        }
    }

    static void productPath(Customer customer){
        int userInput;
        while (true) {
            //display product
            System.out.println("\n================== DISPLAY PRODUCTS ==================");
            storeInventory.display();
            System.out.println("=========================================================");
            //choose
            System.out.print("[What would you like to do?:]\n\t1) Add to Cart\n\t2) Back\n>");
            userInput = sc.nextInt();
            sc.nextLine();

            if (userInput == 1) {
                System.out.print("\n[Add to Cart]\nName of the Item: ");
                String itemName = sc.nextLine();

                //does it exist in the store inventory, yes or no?
                StoreItem storeItem = storeInventory.find(itemName);
                if (storeItem == null) {
                    System.out.println("Item does not exist!");
                    continue; //skip
                }

                int itemQuantity;

                do {
                    System.out.print("Enter the Item quantity (0-99): ");
                    itemQuantity = sc.nextInt();
                    sc.nextLine();
                    //repeats until valid
                } while (itemQuantity > storeItem.getStock());
                //reduce stock
                storeItem.setStock(storeItem.getStock() - itemQuantity);

                //create and add item
                CartItem item = new CartItem(storeItem);
                item.setQuantity(itemQuantity);
                customer.getShoppingCart().add(item);
                System.out.printf("%n%s was added to the Shopping Cart!%n", item.getItemName());
            } else if (userInput == 2) {
                System.out.println(". . .");
                break;
            } else {
                System.out.println("Please only enter a number between 1-2!");
            }
            System.out.println();
        }
    }

    static void cartPath(Customer customer){
        int userInput;
        while(true) {
            ShoppingCart cart = customer.getShoppingCart();
            //display product
            System.out.println("\n=================== VIEW CART ===================");
            cart.display();
            System.out.println("=========================================================");
            //choose
            System.out.print("[What would you like to do?:]\n\t1) Remove from Cart\n\t2) Proceed To Checkout\n\t3) Back\n>");
            userInput = sc.nextInt();
            sc.nextLine();

            if (userInput == 1) {
                //remove
                System.out.print("\n[Remove from Cart]\nName of the Item: ");
                String itemName = sc.nextLine();
                //does it exist in cart
                CartItem cartItem = cart.find(itemName);
                if (cartItem != null) {
                    cart.remove(cartItem);
                } else {
                    System.out.println("Item doesn't exist!");
                }
            } else if (userInput == 2) {
                if (!confirmation()) //if not true
                    continue;

                System.out.println("\nProceeding to Checkout...\n");

                //generate new bill
                Bill bill = new Bill(customer);

                customer.getTransactionHistory().add(bill);
                transactionHistory.add(bill);

                System.out.println("Your order has been confirmed!\nOrder Summary");
                System.out.println("------------------------------------");
                bill.printBill();

                //clear cart
                cart.clearCart();

                break;
            } else if (userInput == 3) {
                System.out.println(". . .");
                break;
            } else {
                System.out.println("Please only enter a number between 1-3!");
            }
            System.out.println();
        }
    }

    static void adminPath(Admin admin){
        int userInput;

        while (true) {
            System.out.println("========== HARDWARE STORE ==========");
            System.out.println("WELCOME, ADMIN " + admin.getFullName());
            System.out.print("[AVAILABLE OPTIONS]\n\t1) Display Product\n\t2) View Accounts\n\t3) Customer Transactions\n\t4) Log out \n>");

            userInput = sc.nextInt();
            sc.nextLine();

            if (userInput == 1) {
                adminProductPath(admin);
            } else if (userInput == 2) {
                //view accounts
                adminAccountPath(admin);
            } else if (userInput == 3){
                //transaction
                System.out.println("[Transaction History]");
                admin.getTransactionHistory().displayTransaction();

                System.out.println();
            }
            else if (userInput == 4) {
                //log out
                System.out.println("Logging out...");
                break;
            } else {
                //invalid
                System.out.println("Invalid input.. Please only enter a number between 1-3!");
            }
        }

    }

    static void adminProductPath(Admin admin){
        int userInput;
        while (true) {
            //display product
            System.out.println("\n================== DISPLAY PRODUCTS ==================");
            admin.getStoreInventory().display();
            //choose
            System.out.print("[What would you like to do?:]\n\t1) Add Item to Inventory\n\t2) Modify Item\n\t3) Back\n>");
            userInput = sc.nextInt();
            sc.nextLine();

            if (userInput == 1) {
                System.out.print("\n[Add Item to Inventory]\nName of the Item: ");
                String itemName = sc.nextLine();

                //does it exist in the store inventory, yes or no?
                if (storeInventory.find(itemName) != null) {
                    System.out.println("Item already exist!");
                    continue; //skip
                }

                System.out.print("\nItem's Cost: RM");
                double itemCost = sc.nextDouble();
                sc.nextLine();

                System.out.print("\nItem's Discount Rate (0.00-1.00): ");
                double itemDiscountRate = sc.nextDouble();
                sc.nextLine();

                System.out.print("\nItem's Stock: ");
                int itemStock = sc.nextInt();
                sc.nextLine();

                //create and add item
                StoreItem item = new StoreItem(itemName, itemCost, itemDiscountRate, itemStock);
                storeInventory.add(item);
                System.out.printf("%n%s was added to the Store Inventory!%n", item.getItemName());
            } else if (userInput == 2){
                System.out.print("\n[Modify Item]\nName of the Item: ");
                String itemName = sc.nextLine();

                StoreItem item = storeInventory.find(itemName);
                //does it exist in the store inventory, yes or no?
                if (item == null) {
                    System.out.println("Item doesn't exist!");
                    continue; //skip
                }

                adminProductModifyPath(item);
            }
            else if (userInput == 3) {
                System.out.println(". . ." );
                break;
            } else {
                System.out.println("Please only enter a number between 1-2!");
            }
            System.out.println();
        }
    }

    static void adminProductModifyPath(StoreItem item){
        int userInput;
        while (true){
            //Might be changed if I decide to implement item code
            System.out.print("[What to modify?]\n\t1. Cost\n\t2. Discount Rate\n\t3. Stock\n\t4. Back \n>");
            userInput = sc.nextInt();
            sc.nextLine();

            if (userInput == 1) {
                System.out.printf("Modify Cost (CURRENT: RM%.2f): RM", item.getCost());
                double itemCost = sc.nextDouble();
                sc.nextLine();

                item.setCost(itemCost);
            }
            else if (userInput == 2){
                System.out.printf("Modify Discount Rate (CURRENT: %.2f): ", item.getDiscountRate());
                double itemDiscountRate = sc.nextDouble();
                sc.nextLine();

                item.setDiscountRate(itemDiscountRate);
            }

            else if (userInput == 3) {
                System.out.printf("Modify Stock (CURRENT: %d): ", item.getStock());
                int itemStock = sc.nextInt();
                sc.nextLine();

                item.setStock(itemStock);
            }
            else if (userInput == 4){
                System.out.println(". . ." );
                break;
            } else {
                System.out.println("Please only enter a number between 1-5!");
            }
            System.out.println("--------");
        }
    }

    static void adminAccountPath(Admin admin){
        int userInput;
        while (true) {
            System.out.println("[All Registered Accounts]");
            admin.getIdandPassword().displayAccount();

            //edit acc?
            System.out.print("\n[What to do]\n\t1. Remove Account\n\t2. Back\n");
            userInput = sc.nextInt();
            sc.nextLine();

            if (userInput == 1) {
                System.out.print("[Remove Account]\n");
                System.out.print("\tUser ID\t : ");
                String userID = sc.nextLine();
                System.out.print("\tPassword\t : ");
                String password = sc.nextLine();
                User account = idandPassword.findAccount(userID, password);

                if (account != null) {
                    //remove account
                    idandPassword.removeAccount(account);
                    System.out.println("Account successfully removed!");
                }
                else{
                    System.out.println("There's no such account!");
                }
            }
            else if (userInput == 2){
                System.out.println(". . ." );
                break;
            }
            else {
                System.out.println("Please only enter a number between 1-2!");
            }
            System.out.println();
        }
        System.out.println();
    }

    static boolean confirmation(){
        char userInput;
        while (true) {
            System.out.print("\n[Would you like to proceed?(Y/N)]: ");
            userInput = sc.nextLine().charAt(0);
            if (userInput == 'Y' || userInput == 'y'){
                return true;
            }
            else if (userInput == 'N' || userInput == 'n'){
                return false;
            }
            else {
                System.out.print("\nPlease enter a valid input. . .\n");
            }
        }
    }
}
