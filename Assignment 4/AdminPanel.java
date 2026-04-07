import java.io.*;
import java.util.*;

public class AdminPanel {

    static final String FILE_NAME = "bank_data.txt";
    static Scanner sc = new Scanner(System.in);

    //view the customers
    static void viewAllCustomers() {
        try {
            BufferedReader br = new BufferedReader(new FileReader(FILE_NAME));
            String line;

            System.out.println("\n--- ALL CUSTOMERS ---");

            while ((line = br.readLine()) != null) {
                String data[] = line.split(",");

                if (data.length < 7) continue;

                System.out.println("CID: " + data[0]);
                System.out.println("Name: " + data[1]);
                System.out.println("Age: " + data[2]);
                System.out.println("Gender: " + data[3]);
                System.out.println("Phone: " + data[4]);
                System.out.println("Residence: " + data[5]);
                System.out.println("Balance: " + data[data.length - 1].trim());
                System.out.println("----------------------");
            }

            br.close();

        } catch (Exception e) {
            System.out.println("Error reading file.");
        }
    }

    //search by CID
    static void searchCustomer() {
        try {
            System.out.print("Enter CID to search: ");
            int cid = sc.nextInt();

            BufferedReader br = new BufferedReader(new FileReader(FILE_NAME));
            String line;
            boolean found = false;

            while ((line = br.readLine()) != null) {
                String data[] = line.split(",");

                if (data.length < 7) continue;

                if (Integer.parseInt(data[0]) == cid) {
                    found = true;

                    System.out.println("\nCustomer Found:");
                    System.out.println("Name: " + data[1]);
                    System.out.println("Balance: " + data[data.length - 1].trim());
                    break;
                }
            }

            if (!found) {
                System.out.println("Customer not found!");
            }

            br.close();

        } catch (Exception e) {
            System.out.println("Error occurred.");
        }
    }

    // Delete customer
    static void deleteCustomer() {
        try {
            System.out.print("Enter CID to delete: ");
            int cid = sc.nextInt();

            File file = new File(FILE_NAME);
            BufferedReader br = new BufferedReader(new FileReader(file));
            List<String> records = new ArrayList<>();

            String line;
            boolean found = false;

            while ((line = br.readLine()) != null) {
                String data[] = line.split(",");

                if (Integer.parseInt(data[0]) == cid) {
                    found = true;
                    continue; // skip (delete)
                }
                records.add(line);
            }

            br.close();

            FileWriter fw = new FileWriter(FILE_NAME);
            for (String rec : records) {
                fw.write(rec + "\n");
            }
            fw.close();

            if (found)
                System.out.println("Customer deleted.");
            else
                System.out.println("Customer not found.");

        } catch (Exception e) {
            System.out.println("Error deleting record.");
        }
    }

    public static void main(String[] args) {
        int choice;
        do {
            System.out.println("\n--x-- ADMIN PANEL --x--");
            System.out.println("1. View All Customers");
            System.out.println("2. Search Customer");
            System.out.println("3. Delete Customer");
            System.out.println("4. Exit");
            System.out.print("Enter choice: ");

            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    viewAllCustomers();
                    break;
                case 2:
                    searchCustomer();
                    break;
                case 3:
                    deleteCustomer();
                    break;
                case 4:
                    System.out.println("--x-- Exiting --x--");
                    break;
                default:
                    System.out.println("Invalid choice");
            }
        } while (choice != 4);
    }
}