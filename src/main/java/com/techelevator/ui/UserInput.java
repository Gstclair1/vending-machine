package com.techelevator.ui;

import jdk.swing.interop.SwingInterOpUtils;

import java.math.BigDecimal;
import java.util.Scanner;

/**
 * Responsibilities: This class should handle receiving ALL input from the User
 * 
 * Dependencies: None
 */
public class UserInput {
    private final String TWENTY_BIG_DECIMAL = "20.00";
    private final String FIVE_BIG_DECIMAL = "5.00";
    private String lastInsertedMoney = "0";
    private Scanner scanner = new Scanner(System.in);

    Audit audit = new Audit();

    public String getLastInsertedMoney() {
        return this.lastInsertedMoney;
    }

    public String getHomeScreenOption() {
        System.out.println("What would you like to do?");
        System.out.println();

        System.out.println("D) Display Vending Machine Items");
        System.out.println("P) Purchase");
        System.out.println("E) Exit");

        System.out.println();
        System.out.print("Please select an option: ");

        String selectedOption = scanner.nextLine();
        String option = selectedOption.trim().toUpperCase();

        if (option.equals("D")) {
            return "display";
        } else if (option.equals("P")) {
            return "purchase";
        } else if (option.equals("E")) {
            return "exit";
        } else if(option.equals("S")) {
            return "sales report";
        } else {
            return "";
        }

    }

    public String purchaseScreen() {
        System.out.println("What would you like to do?");
        System.out.println();

        System.out.println("M) Feed Money");
        System.out.println("S) Select Item");
        System.out.println("F) Finish Transaction");

        System.out.println();
        System.out.print("Please select an option: ");

        String purchasedOptionSelected = scanner.nextLine();
        String purchaseOption = purchasedOptionSelected.trim().toUpperCase();

        if (purchaseOption.equals("M")) {
            return "money fed";
        } else if (purchaseOption.equals("S")) {
            return "select item";

        } else if (purchaseOption.equals("F")) {
            return "finish";
        }else {
            return "";
        }

    }

    public BigDecimal insertedMoneyChoice() {
        System.out.println("Feed Money(1,5,10,20 only):  ");
        String insertedMoney = scanner.nextLine();
        BigDecimal moneyFed = new BigDecimal(insertedMoney).setScale(2);
        this.lastInsertedMoney = String.valueOf(moneyFed);
        BigDecimal one = BigDecimal.ONE.setScale(2);
        BigDecimal five = new BigDecimal(FIVE_BIG_DECIMAL).setScale(2);
        BigDecimal ten = BigDecimal.TEN.setScale(2);
        BigDecimal twenty = new BigDecimal(TWENTY_BIG_DECIMAL).setScale(2);
        if (moneyFed.equals(one) || moneyFed.equals(five) || moneyFed.equals(ten) || moneyFed.equals(twenty)) {
            return moneyFed;
        } else {
            System.out.println("Please enter a valid bill");
            moneyFed = this.insertedMoneyChoice();
            return moneyFed;
        }
    }

    public String selectedItem(){
        System.out.println("Please enter an item (ex: A1) : ");
        String userSelected = scanner.nextLine();
        return userSelected;
    }
}
