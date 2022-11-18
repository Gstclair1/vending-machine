package com.techelevator.application;

import com.techelevator.ui.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.sql.SQLOutput;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class VendingMachine {
    public void run() {
        final String STARTING_INVENTORY = ",6";

        UserOutput userOutput = new UserOutput();
        UserInput userInput = new UserInput();
        Inventory inventory = new Inventory();
        Audit audit = new Audit();
        SalesReport salesReport = new SalesReport();

        List<String[]> vendingItems = new ArrayList<>();


        File auditClear = new File("C:\\Users\\Student\\workspace\\java-orange-minicapstonemodule1-team7\\audit.txt");

        LocalDateTime localDateTime = LocalDateTime.now();
        DateTimeFormatter dateTimeFormat = DateTimeFormatter.ofPattern("MM/dd/yy hh:mm a");

        if (auditClear.exists()){
            try {
            PrintWriter clear = new PrintWriter( new FileOutputStream(auditClear, true));
                clear.println("Audit Log for Transactions Starting at: " + localDateTime.format(dateTimeFormat) + "\n");
                clear.flush();
                clear.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }

        File items = new File("catering1.csv");
        try (Scanner fineInputScanner = new Scanner(items)) {
            while (fineInputScanner.hasNextLine()) {
                String inventoryItem = fineInputScanner.nextLine();
                inventoryItem += STARTING_INVENTORY;
                String[] displayArray = inventoryItem.split(",");
                vendingItems.add(displayArray);
                salesReport.addToList(displayArray);
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
                                if (!inventory.checkBogodo()) {
                                    audit.addToAuditList(userSelectedVendingItem[1], String.valueOf(inventory.getMoneyFed()), String.valueOf(inventory.getMoneyFed().subtract(new BigDecimal(userSelectedVendingItem[2]))), userSelectedItem);
                                } else  {
                                    audit.addToAuditList(userSelectedVendingItem[1], String.valueOf(inventory.getMoneyFed()), String.valueOf(inventory.getMoneyFed().subtract(new BigDecimal(userSelectedVendingItem[2]).subtract(BigDecimal.ONE))), userSelectedItem);
                                }
                                System.out.println("Here is your: " + userSelectedVendingItem[1]);
                                salesReport.editItem(userSelectedVendingItem[1], inventory.checkBogodo());
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
                        audit.addToAuditList("change given", String.valueOf(inventory.getMoneyFed()), "0.00", "");
                        System.out.println("Here is you change: $" + inventory.getMoneyFed());
                        System.out.println(inventory.getChange());
                        inventory.subtractMoney(inventory.getMoneyFed());

                        keepAddingMoney = false;
                    }
                }
                } else if (choice.equals("sales report")) {
                System.out.println(salesReport.callSalesReport());
            } else if (choice.equals("exit")) {
                    // good bye
                    break;
                }
            }
        }

    }

