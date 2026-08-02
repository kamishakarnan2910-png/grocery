import java.util.ArrayList;
import java.util.Scanner;

class GroceryItem {
    int id;
    String name;
    String category;
    double price;
    int quantity;

    GroceryItem(int id, String name, String category,
                double price, int quantity) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.price = price;
        this.quantity = quantity;
    }

    void display() {
        System.out.println("ID       : " + id);
        System.out.println("Name     : " + name);
        System.out.println("Category : " + category);
        System.out.println("Price    : Rs." + price);
        System.out.println("Quantity : " + quantity);
        System.out.println("-------------------------");
    }
}

public class GroceryManagementSystem {

    static ArrayList<GroceryItem> items = new ArrayList<>();
    static Scanner sc = new Scanner(System.in);
    static ArrayList<String> billItems = new ArrayList<>();
    static ArrayList<Integer> billQuantities = new ArrayList<>();
    static ArrayList<Double> billAmounts = new ArrayList<>();

    static void addItem() {

        System.out.print("Enter Item ID: ");
        int id = sc.nextInt();
        sc.nextLine();

        for (GroceryItem item : items) {
            if (item.id == id) {
                System.out.println("Item ID already exists!");
                return;
            }
        }

        System.out.print("Enter Item Name: ");
        String name = sc.nextLine();

        System.out.print("Enter Category: ");
        String category = sc.nextLine();

        System.out.print("Enter Price: ");
        double price = sc.nextDouble();

        System.out.print("Enter Quantity: ");
        int quantity = sc.nextInt();

        items.add(new GroceryItem(id, name, category, price, quantity));

        System.out.println("Item added successfully!");
    }

    static void viewItems() {

        if (items.isEmpty()) {
            System.out.println("No items available!");
            return;
        }

        System.out.println("\n===== AVAILABLE ITEMS =====");

        for (GroceryItem item : items) {
            item.display();
        }
    }

    static void searchItem() {

        System.out.print("Enter Item ID: ");
        int id = sc.nextInt();

        for (GroceryItem item : items) {

            if (item.id == id) {
                System.out.println("\nItem Found!");
                item.display();
                return;
            }
        }

        System.out.println("Item not found!");
    }

    static void updateItem() {

        System.out.print("Enter Item ID: ");
        int id = sc.nextInt();

        for (GroceryItem item : items) {

            if (item.id == id) {

                System.out.print("Enter New Price: ");
                item.price = sc.nextDouble();

                System.out.print("Enter New Quantity: ");
                item.quantity = sc.nextInt();

                System.out.println("Item updated successfully!");
                return;
            }
        }

        System.out.println("Item not found!");
    }

    static void deleteItem() {

        System.out.print("Enter Item ID: ");
        int id = sc.nextInt();

        for (int i = 0; i < items.size(); i++) {

            if (items.get(i).id == id) {

                items.remove(i);

                System.out.println("Item deleted successfully!");
                return;
            }
        }

        System.out.println("Item not found!");
    }

    static void purchaseItem() {

        if (items.isEmpty()) {
            System.out.println("No items available!");
            return;
        }

        System.out.print("Enter Item ID: ");
        int id = sc.nextInt();

        GroceryItem selectedItem = null;

        for (GroceryItem item : items) {

            if (item.id == id) {
                selectedItem = item;
                break;
            }
        }

        if (selectedItem == null) {
            System.out.println("Item not found!");
            return;
        }

        System.out.print("Enter Quantity: ");
        int qty = sc.nextInt();

        if (qty <= 0) {
            System.out.println("Invalid quantity!");
            return;
        }

        if (qty > selectedItem.quantity) {
            System.out.println("Insufficient stock!");
            return;
        }

        double amount = selectedItem.price * qty;

        selectedItem.quantity -= qty;

        billItems.add(selectedItem.name);
        billQuantities.add(qty);
        billAmounts.add(amount);

        System.out.println("Item purchased successfully!");
    }

    static void viewBill() {

        if (billItems.isEmpty()) {
            System.out.println("No purchases made!");
            return;
        }

        double total = 0;

        System.out.println("\n========== BILL ==========");

        for (int i = 0; i < billItems.size(); i++) {

            System.out.println("Item     : " + billItems.get(i));
            System.out.println("Quantity : " + billQuantities.get(i));
            System.out.println("Amount   : Rs." + billAmounts.get(i));
            System.out.println("--------------------------");

            total += billAmounts.get(i);
        }

        System.out.println("Total Amount: Rs." + total);
        System.out.println("==========================");
    }
    public static void main(String[] args) {

        while (true) {

            System.out.println("\n===== GROCERY MANAGEMENT SYSTEM =====");

            System.out.println("1. Add Item");
            System.out.println("2. View Items");
            System.out.println("3. Search Item");
            System.out.println("4. Update Item");
            System.out.println("5. Delete Item");
            System.out.println("6. Purchase Item");
            System.out.println("7. View Bill");
            System.out.println("8. Exit");

            System.out.print("\nEnter your choice: ");
            int choice = sc.nextInt();

            switch (choice) {

                case 1:
                    addItem();
                    break;

                case 2:
                    viewItems();
                    break;

                case 3:
                    searchItem();
                    break;

                case 4:
                    updateItem();
                    break;

                case 5:
                    deleteItem();
                    break;

                case 6:
                    purchaseItem();
                    break;

                case 7:
                    viewBill();
                    break;

                case 8:
                    System.out.println("Thank you!");
                    sc.close();
                    return;

                default:
                    System.out.println("Invalid choice!");
            }
        }
    }
}