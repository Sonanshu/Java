/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AtmMachine;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

/**
 *
 * @author trinkush
 */
interface AtmMachine {

    public void createAcc();

    public String balanceCheck();

    public void withdrawal(int acc);

    public void pinChange(int acc);

    public void atmRun();

    public void updateUserAcc();

    public void transactionStatement(int acc);

    public void pincheck(int acc);
}

class Database {

    Connection con;

    Connection dbcon() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bank", "root", "pb11ay4108");
        } catch (Exception e) {
            System.out.println(e);
        }
        return con;
    }
}

class BankAtm extends Database implements AtmMachine {

    Scanner input = new Scanner(System.in);

    public void admin() {
        String username;
        String password;
        input.nextLine();
        System.out.println("Enter Admin Username: ");
        username = input.nextLine();
        System.out.println("Enter password of Admin: ");
        password = input.nextLine();
        try {
            Connection con = dbcon();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("Select * from admin_login where username = '" + username + "' and password='" + password + "' ");
            if (rs.next()) {
                System.out.println("Welcome Admin");

                System.out.println("Enter Which Operation you want to perform");
                System.out.println("1 : Create Account");
                System.out.println("2: Update User Account");

                int option = input.nextInt();
                if (option == 1) {
                    createAcc();
                } else if (option == 2) {
                    updateUserAcc();
                } else {
                    System.out.println("Wrong Input");
                }
                //createAcc();
                //balanceCheck();
                //updateUserAcc();
                // withdrawal(1234);
                //transactionStatement(1234);
                //pincheck(1234);
                //pinChange(9);
                //  atmRun();

            } else {
                System.out.println("Wrong username or password");
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @Override
    public void createAcc() {
        String name;
        String acc;
        String email;
        String atmpin;
        String address;
        String balance;

        System.out.println("Enter Account Holder Name ");
        name = input.nextLine();
        System.out.println("Enter Account Number ");
        acc = input.nextLine();
        System.out.println("Enter Account Holder email ");
        email = input.nextLine();
        System.out.println("Enter Account Holder ATM Pin ");
        atmpin = input.nextLine();
        System.out.println("Enter Account Holder Address ");
        address = input.nextLine();
        System.out.println("Enter Account Holder Balance ");
        balance = input.nextLine();
        try {
            Connection con = dbcon();
            Statement st = con.createStatement();
            st.executeUpdate("INSERT INTO `bank_account` (`name`, `account_number`, `balance`, `address`, `email`, `atm_pin`) VALUES ('" + name + "', '" + acc + "', '" + balance + "', '" + address + "', '" + email + "', '" + atmpin + "');");
            System.out.println("Account is Created");
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @Override
    public String balanceCheck() {
     input.nextLine();
        String acc;
        System.out.println("Enter Account Number");
        acc = input.nextLine();

        try {
            Connection con = dbcon();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("Select * from bank_account where account_number='" + acc + "' ");
            if (rs.next()) {
                System.out.println("-----------------------");
                System.out.println("Name: " + rs.getString("name"));
                System.out.println("Account Number: " + rs.getString("account_number"));
                System.out.println("Balance: " + rs.getString("balance"));
                System.out.println("E-mail: " + rs.getString("email"));
                System.out.println("Address: " + rs.getString("address"));
                //         System.out.println("ATM Pin: " + rs.getString("atm_pin"));
                System.out.println("------------------------");
            } else {
                System.out.println("Wrong");
            }

        } catch (Exception e) {
            System.out.println(e);
        }
        return acc;
    }

    @Override
    public void withdrawal(int acc) {
        try {
            Connection con = dbcon();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("Select * from bank_account where account_number='" + acc + "' ");
            if (rs.next()) {
                System.out.println("Enter how much money you want to withdraw");
                int amount = input.nextInt();
                if (Integer.parseInt(rs.getString("balance")) >= amount) {
                    int updatedbalance = Integer.parseInt(rs.getString("balance")) - amount;
                    System.out.println("Your Current balance is" + updatedbalance);
                    st.executeUpdate("update bank_account set balance = '" + updatedbalance + "' " + "where account_number = '" + acc + "' ");
                    st.executeUpdate("insert into transactions (account_number,debit_amount)" + "values('" + acc + "','" + amount + "') ");
                    System.out.println("Transaction completed");
                }
            } else {
                System.out.println("Account does not Exist");
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @Override
    public void pinChange(int acc) {

        try {
            System.out.println("Enter your current atm pin");
            int pin = input.nextInt();
            Connection con = dbcon();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select * from bank_account where atm_pin='" + pin + "' and account_number='" + acc + "' ");
            if (rs.next()) {
                System.out.println("Enter your new pin");
                int newpin = input.nextInt();
                System.out.println("Confirm your pin");
                int confpin = input.nextInt();
                if (newpin == confpin) {
                    st.executeUpdate("update bank_account set atm_pin='" + newpin + "' where account_number='" + acc + "' ");
                    System.out.println("Pin Updated");
                } else {
                    System.out.println("New Pin and Confirm Pin does not match Try Again..");
                }
            } else {
                System.out.println("Wrong Pin Try Again");
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @Override
    public void pincheck(int acc) {
        try {

            System.out.println("Enter your atm pin");
            int pin = input.nextInt();
            Connection con = dbcon();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select * from bank_account where atm_pin='" + pin + "' and account_number='" + acc + "' ");
            if (rs.next()) {
                System.out.println("Correct pin");

            } else {
                System.out.println("Wrong Pin Try Again");
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @Override
    public void transactionStatement(int acc) {
        try {
            Connection con = dbcon();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select * from transactions where account_number='" + acc + "' ");
            while (rs.next()) {
                System.out.println("Transaction Id:" + rs.getString("transaction_id"));
                System.out.println("|Account Number:" + rs.getString("account_number"));
                System.out.println("|Debit Ammount:" + rs.getString("debit_amount"));
                System.out.println("|Date and Time:" + rs.getString("date_time"));
                System.out.println("---------------------------");
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @Override
    public void updateUserAcc() {
        String userinput;
        String info;
        String acc = balanceCheck();
        System.out.println("Enter the information you want to Update?");
        userinput = input.nextLine();
        System.out.println("Enter " + userinput + " you want to change");
        info = input.nextLine();

        try {
            Connection con = dbcon();
            Statement st = con.createStatement();
            st.executeUpdate("update bank_account set " + userinput + " = '" + info + "' " + "where account_number= '" + acc + "' ");
            System.out.println("Information Updated");

        } catch (Exception e) {
            System.out.println(e);
        }

    }

    @Override
    public void atmRun() {
        System.out.println("Enter 1 for user 2 for admin");
        int userinput = input.nextInt();
        switch (userinput) {
            case 1:
                System.out.println("Enter Bank Account Number");
                int acc = input.nextInt();
                System.out.println("Enter Which Operation you want to perform");
                System.out.println("1 : Cash Withdrawal");
                System.out.println("2 : Balance Inquiry");
                System.out.println("3 : Transaction Statement");
                System.out.println("4 : Pin Change");

                int option = input.nextInt();
                if (option == 1) {
                    withdrawal(acc);
                } else if (option == 2) {
                    balanceCheck();

                } else if (option == 3) {
                    transactionStatement(acc);

                } else if (option == 4) {
                    pinChange(acc);
                } else {
                    System.out.println("Wrong Input");
                }
                break;

            case 2:
                admin();

                break;
            default:
                System.out.println("Wrong Input");
        }
    }
}

public class Atm {

    public static void main(String[] args) {
        BankAtm obj = new BankAtm();
        obj.atmRun();
    }
}
