package DogPackage;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class FileHandling {
    public static void main(String[] args) throws IOException {
        File atmFiles = new File("/Users/makima/Desktop/untitled folder/School/src/DogPackage/cat.txt");
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("""
                    --------------------
                    Welcome to myATM!
                    Please choose one of the options:
                    
                    1.Check Balance
                    2.Deposit
                    3.Withdraw
                    4.Search
                    5.Exit
                    """);
            int choice;
            try {
                choice = sc.nextInt();
            } catch (Exception e) {
                System.out.println("Invalid input! Please enter a number.");
                sc.nextLine();
                continue;
            } switch (choice) {
                case 1: {
                    CheckBalance(atmFiles);
                    break;
                } case 2: {
                    System.out.println("How much do you want to deposit? ");
                    double depo = sc.nextDouble();
                    Update(atmFiles, depo);
                    break;
                } case 3: {
                    System.out.println("How much do you want to withdraw? ");
                    double wd = sc.nextDouble();
                    Update(atmFiles, -1 * wd);
                    break;
                } case 4: {
                    sc.nextLine(); 
                    System.out.println("Enter keyword to search: ");
                    String kw = sc.nextLine();
                    Search(atmFiles, kw);
                    break;
                } case 5: {
                    System.out.println("Shut down!");
                    sc.close();
                    System.exit(0);
                } default:
                    System.out.println("Invalid option! Try again.");
            }
        }
    }

    //check balance
    public static void CheckBalance(File file) throws IOException {
        Scanner read = new Scanner(new FileReader(file));
        System.out.println("Account list with balances:");
        while (read.hasNextLine()) {
            String line = read.nextLine();
            String[] parts = line.split(",");
            if (parts.length >= 4) {
                String id = parts[0].trim();
                String name = parts[1].trim();
                String balance = parts[3].trim();
                System.out.println(id + " | " + name + " | Balance: " + balance);
            }
        }
        read.close();
    }

    //deposit ug withdraw
    public static void Update(File file, double amountToUpdate) throws IOException {
        ArrayList<String> fileContents = new ArrayList<>();
        Scanner read = new Scanner(new FileReader(file));
        boolean updated = false;

        while (read.hasNextLine()) {
            String line = read.nextLine();
            String[] parts = line.split(",");
            if (parts.length >= 4) {
                String id = parts[0].trim();
                String name = parts[1].trim();
                String pass = parts[2].trim();
                double balance = Double.parseDouble(parts[3].trim());

                // por demo: always update the first account
                if (!updated) {
                    balance += amountToUpdate;
                    line = id + "," + name + "," + pass + "," + balance;
                    updated = true;
                }
            }
            fileContents.add(line);
        }
        read.close();
    
        if (updated) {
            Clear(file);
            WriteToFile(file, fileContents);
            System.out.println("Balance updated successfully!");
        } else {
            System.out.println("No account updated!");
        }
    }
    	// search
    public static void Search(File file, String Keyword) {
        try (Scanner searcher = new Scanner(file)) {
            boolean searched = false;
            while (searcher.hasNextLine()) {
                String line = searcher.nextLine();
                String[] parts = line.split(",");
               
                if (parts.length >= 2) {
                    String id = parts[0].trim();
                    String name = parts[1].trim();

                if (id.toLowerCase().contains(Keyword.toLowerCase()) ||
                        name.toLowerCase().contains(Keyword.toLowerCase())) {
                        System.out.println("Found: " + line);
                        searched = true;
                    }
                }
            } if (!searched) {
                System.out.println("No matches found in the Database!");
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + e.getMessage());
        }
    }

    
    public static void Clear(File file) throws IOException {
        FileWriter write = new FileWriter(file);
        write.write("");
        write.close();
    }

    public static void WriteToFile(File file, ArrayList<String> dog) throws IOException {
        try (FileWriter write = new FileWriter(file, true)) {
            for (String c : dog) {
                write.write(c + "\n");
            }
        }
    }
}
