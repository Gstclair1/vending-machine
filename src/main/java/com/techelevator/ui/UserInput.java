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
    private Scanner scanner = new Scanner(System.in);

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
        System.out.println("E) Exit");

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
        } else if (purchaseOption.equals("E")) {
            return "exit";
        } else {
            return "";
        }

    }

    public BigDecimal insertedMoneyChoice() {
        System.out.println("Feed Money(1,5,10,20 only):  ");
        BigDecimal moneyFed = new BigDecimal(scanner.nextLine()).setScale(2);
        BigDecimal one = BigDecimal.ONE.setScale(2);
        BigDecimal five = new BigDecimal("5.00").setScale(2);
        BigDecimal ten = BigDecimal.TEN.setScale(2);
        BigDecimal twenty = new BigDecimal("20.00").setScale(2);
        if (moneyFed.equals(one) || moneyFed.equals(five) || moneyFed.equals(ten) || moneyFed.equals(twenty)) {
            System.out.println("Success" + moneyFed.doubleValue());
            return moneyFed;
        } else {
            System.out.println("Please enter a valid bill");
            this.insertedMoneyChoice();
            return moneyFed;
        }
    }

    public String selectedItem(){
        System.out.println("Please enter an item (ex: A1) : ");
        String userSelected = scanner.nextLine();
        return userSelected;
    }
}
