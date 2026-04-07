import java.util.*;
import java.io.*;

class InvalidAmt extends Exception {
    InvalidAmt(String msg) {
        super(msg);
    }
}

class LowBal extends Exception {
    LowBal(String msg) {
        super(msg);
    }
}

class InvalidCID extends Exception {
    InvalidCID(String msg) {
        super(msg);
    }
}

// Customer class
class Customer {
    int cid;
    String name;
    int age;
    String gender;
    String phone;
    String residence;
    double balance;

    Customer(int cid, String name, int age, String gender, String phone, String residence, double balance){
        this.cid = cid;
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.phone = phone;
        this.residence = residence;
        this.balance = balance;
    }

    public String toString() {
        return cid + "," + name + "," + age + "," + gender + "," + phone + "," + residence + "," + balance;
    }
}

public class BankingSystem {
    static Scanner sc = new Scanner(System.in);
    static final String FILE_NAME = "bank_data.txt";

    // duplicate CID check
    static boolean isCIDExists(int cid) throws IOException {
        File file = new File(FILE_NAME);
        if (!file.exists()) return false;

        BufferedReader br = new BufferedReader(new FileReader(file));
        String line;

        while ((line = br.readLine()) != null) {
            String data[] = line.split(",");
            if (Integer.parseInt(data[0]) == cid) {
                br.close();
                return true;
            }
        }
        br.close();
        return false;
    }

    // Account creation
    static void createAccount() {
        try {
            System.out.print("Enter Customer ID (b/w 100-999): ");
            int cid = sc.nextInt();

            if (cid < 100 || cid > 999)
                throw new InvalidCID("CID must be between 100 and 999");

            if (isCIDExists(cid)) {
                System.out.println("Entered CID already exists! Choose new CID");
                return;
            }

            sc.nextLine();

            System.out.print("Enter Name: ");
            String name = sc.nextLine();

            System.out.print("Enter Age: ");
            int age = sc.nextInt();
            sc.nextLine();

            System.out.print("Enter Gender: ");
            String gender = sc.nextLine();

            System.out.print("Enter Phone No.: ");
            String phone = sc.nextLine();

            System.out.print("Enter Residence: ");
            String residence = sc.nextLine();

            System.out.print("Enter initial amount: ");
            double amount = sc.nextDouble();

            if (amount < 1000)
                throw new InvalidAmt("--x-- Minimum balance required is 1000 --x--");

            Customer c = new Customer(cid, name, age, gender, phone, residence, amount);

            FileWriter fw = new FileWriter(FILE_NAME, true);
            fw.write(c.toString() + "\n");
            fw.close();

            System.out.println("Account created successfully!");

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    // Deposit
    static void deposit() {
        try {
            System.out.print("Enter CID: ");
            int cid = sc.nextInt();

            System.out.print("Enter amount to deposit: ");
            double amt = sc.nextDouble();

            if (amt <= 0) {
                throw new InvalidAmt("Amount must be positive");
            }

            updateBalance(cid, amt, true);

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    // Withdraw
    static void withdraw() {
        try {
            System.out.print("Enter CID: ");
            int cid = sc.nextInt();

            System.out.print("Enter amount to withdraw: ");
            double amt = sc.nextDouble();

            if (amt <= 0) {
                throw new InvalidAmt("Amount must be positive");
            }

            updateBalance(cid, amt, false);

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    // Check Balance
    static void checkBalance() {
        try {
            System.out.print("Enter CID: ");
            int cid = sc.nextInt();

            File file = new File(FILE_NAME);
            if (!file.exists()) {
                System.out.println("No records found.");
                return;
            }

            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;
            boolean found = false;

            while ((line = br.readLine()) != null) {
                String data[] = line.split(",");

                if (data.length < 7) continue;

                if (Integer.parseInt(data[0]) == cid) {
                    found = true;

                    System.out.println("\n--x-- CUSTOMER DETAILS --x--");
                    System.out.println("CID: " + data[0]);
                    System.out.println("Name: " + data[1]);
                    System.out.println("Balance: " + data[data.length - 1].trim());
                    break;
                }
            }

            br.close();

            if (!found) {
                System.out.println("Customer not found!");
            }

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    // Update Balance
    static void updateBalance(int cid, double amt, boolean isDeposit) throws Exception {
        File file = new File(FILE_NAME);
        if (!file.exists()) {
            System.out.println("No records found.");
            return;
        }

        BufferedReader br = new BufferedReader(new FileReader(file));
        List<String> records = new ArrayList<>();
        String line;
        boolean found = false;

        while ((line = br.readLine()) != null) {
            String data[] = line.split(",");

            if (data.length < 7) {
                records.add(line);
                continue;
            }

            if (Integer.parseInt(data[0]) == cid) {
                found = true;
                double balance = Double.parseDouble(data[data.length - 1].trim());

                if (!isDeposit && amt > balance)
                    throw new LowBal("Insufficient balance");

                if (isDeposit) {
                    balance = balance + amt;
                } else {
                    balance = balance - amt;
                }

                data[data.length - 1] = String.valueOf(balance);
                line = String.join(",", data);

                System.out.println("Updated Balance: " + balance);
            }
            records.add(line);
        }

        br.close();

        if (!found) {
            System.out.println("Customer not found!");
            return;
        }

        FileWriter fw = new FileWriter(FILE_NAME);
        for (String rec : records) {
            fw.write(rec + "\n");
        }
        fw.close();
    }

    public static void main(String[] args) {
        int choice;

        do {
            System.out.println("\n------ ABC Bank ------");
            System.out.println("1. Create Account");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. Check Balance");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    createAccount();
                    break;
                case 2:
                    deposit();
                    break;
                case 3:
                    withdraw();
                    break;
                case 4:
                    checkBalance();
                    break;
                case 5:
                    System.out.println("--x-- Exiting --x--");
                    break;
                default:
                    System.out.println("Invalid choice");
            }

        } while (choice != 5);
    }
}