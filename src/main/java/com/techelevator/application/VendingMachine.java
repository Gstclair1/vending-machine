package com.techelevator.application;

import com.techelevator.ui.Inventory;
import com.techelevator.ui.UserInput;
import com.techelevator.ui.UserOutput;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class VendingMachine {
    public void run() {
        UserOutput userOutput = new UserOutput();
        UserInput userInput = new UserInput();
        Inventory inventory = new Inventory();

        while (true) {
            List<String[]> vendingItems = new ArrayList<>();
            List<String[]> orderedVendingItems = new ArrayList<>();
            userOutput.displayHomeScreen();
            String choice = userInput.getHomeScreenOption();

            File items = new File("catering1.csv");
            try (Scanner fineInputScanner = new Scanner(items)) {
                while (fineInputScanner.hasNextLine()) {
                    String inventoryItem = fineInputScanner.nextLine();
                    String[] displayArray = inventoryItem.split(",");
                    vendingItems.add(displayArray);
                }

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

            if (choice.equals("display")) {
                //System.out.println("display the vending machine slots");
                //display the vending machine slots

                System.out.println(inventory.displayVending(vendingItems));

                System.out.println("Current Balance: $" + inventory.getMoneyFed());

            } else if (choice.equals("purchase")) {
                boolean keepAddingMoney = true;
                while (keepAddingMoney) {
                    String purchaseChoice = userInput.purchaseScreen();

                    if (purchaseChoice.equals("money fed")) {
                        inventory.addMoney(userInput.insertedMoneyChoice());

                    } else if (purchaseChoice.equals("select item")) {
                        System.out.println(inventory.displayVending(vendingItems));

                        String userSelectedItem = userInput.selectedItem();
                        String[] userSelectedVendingItem = inventory.userSelectedChoice(userSelectedItem);
                        BigDecimal itemCost = new BigDecimal(userSelectedVendingItem[2]);
                        if (inventory.getMoneyFed().compareTo(itemCost) != -1) { //&& if inventory is not OOS notate it and return 
                            System.out.println("Here is your: " + userSelectedVendingItem[1]);
                            inventory.subtractMoney(itemCost);
                            System.out.println("Current Balance: " + inventory.getMoneyFed());

                            keepAddingMoney = false;
                        } else {
                            System.out.println("Not enough money, Please insert more money");

                        }
                    }
                }
                } else if (choice.equals("exit")) {
                    // good bye
                    break;
                }
            }
        }

    }

