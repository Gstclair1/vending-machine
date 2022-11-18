package com.techelevator.application;

import com.techelevator.ui.Audit;
import com.techelevator.ui.Inventory;
import com.techelevator.ui.UserInput;
import com.techelevator.ui.UserOutput;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class VendingMachine {
    public void run() {
        UserOutput userOutput = new UserOutput();
        UserInput userInput = new UserInput();
        Inventory inventory = new Inventory();
        Audit audit = new Audit();

        List<String[]> vendingItems = new ArrayList<>();

        File items = new File("catering1.csv");
        try (Scanner fineInputScanner = new Scanner(items)) {
            while (fineInputScanner.hasNextLine()) {
                String inventoryItem = fineInputScanner.nextLine();
                inventoryItem += ",6";
                String[] displayArray = inventoryItem.split(",");
                vendingItems.add(displayArray);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        inventory.setVendingItems(vendingItems);

        while (true) {
            userOutput.displayHomeScreen();
            String choice = userInput.getHomeScreenOption();

            if (choice.equals("display")) {
                System.out.println(inventory.displayVending());
                System.out.println("Current Balance: $" + inventory.getMoneyFed());

            } else if (choice.equals("purchase")) {
                //inventory.setBogodo(false);
                boolean keepAddingMoney = true;
                while (keepAddingMoney) {
                    String purchaseChoice = userInput.purchaseScreen();

                    if (purchaseChoice.equals("money fed")) {
                        inventory.addMoney(userInput.insertedMoneyChoice());
                        System.out.println("Current Balance: $" + inventory.getMoneyFed());
                        audit.addToAuditList(purchaseChoice, userInput.getLastInsertedMoney(),String.valueOf(inventory.getMoneyFed()), "");

                    } else if (purchaseChoice.equals("select item")) {
                        System.out.println(inventory.displayVending());
                        if (inventory.checkBogodo()){
                            System.out.println("Thanksgiving special! Next item is $1 off");
                        }

                        String userSelectedItem = userInput.selectedItem();
                        String[] userSelectedVendingItem = inventory.userSelectedChoice(userSelectedItem);
                        try {
                            BigDecimal itemCost = new BigDecimal(userSelectedVendingItem[2]);
                            if (inventory.getMoneyFed().compareTo(itemCost) != -1 || (inventory.checkBogodo() && inventory.getMoneyFed().compareTo(itemCost.subtract(BigDecimal.ONE)) != -1 )) {
                                System.out.println("Here is your: " + userSelectedVendingItem[1]);
                                System.out.println(inventory.getMessage(userSelectedItem));
                                inventory.reverseIsBogodo();
                                int stockLeft = inventory.decreasedStockLevel(userSelectedItem);
                                System.out.println("There are " + stockLeft + " " + userSelectedVendingItem[1] + "(s) left.");
                                inventory.subtractMoney(itemCost);
                                System.out.println("Current Balance: $" + inventory.getMoneyFed());
                            } else {
                                System.out.println("Not enough money, Please insert more money");
                            }
                        } catch (NumberFormatException e){
                            System.out.println("Item out of stock. Try again");
                        }
                    } else if (purchaseChoice.equalsIgnoreCase("finish")){

                        System.out.println("Here is you change: $" + inventory.getMoneyFed());
                        System.out.println(inventory.getChange());
                        keepAddingMoney = false;
                    }
                }
                } else if (choice.equals("exit")) {
                    // good bye
                    break;
                }
            }
        }

    }

