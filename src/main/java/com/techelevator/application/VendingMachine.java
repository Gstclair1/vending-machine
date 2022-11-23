package com.techelevator.application;

import com.techelevator.ui.*;


import java.math.BigDecimal;


public class VendingMachine {
    public void run() {


        UserOutput userOutput = new UserOutput();
        UserInput userInput = new UserInput();
        Inventory inventory = new Inventory();
        Audit audit = new Audit();
        SalesReport salesReport = new SalesReport();


        audit.setUpAudit();
        salesReport = inventory.Inventory(salesReport);

        while (true) {
            userOutput.displayHomeScreen();
            String choice = userInput.getHomeScreenOption();

            if (choice.equals("display")) {
                System.out.println(inventory.displayVending());
                System.out.println("Current Balance: $" + inventory.getMoneyFed());

            } else if (choice.equals("purchase")) {
                boolean keepAddingMoney = true;
                while (keepAddingMoney) {
                    String purchaseChoice = userInput.purchaseScreen();

                    if (purchaseChoice.equals("money fed")) {
                        inventory =  userOutput.moneyFedChoice(purchaseChoice, inventory);

                    } else if (purchaseChoice.equals("select item")) {
                        System.out.println(inventory.displayVending());
                        if (inventory.checkBogodo()){
                            System.out.println("Thanksgiving special! Next item is $1 off");
                        }

                        String userSelectedItem = userInput.selectedItem();
                        String[] userSelectedVendingItem = inventory.userSelectedChoice(userSelectedItem);
                        try {
                            BigDecimal itemCost = inventory.checkItemCost(userSelectedVendingItem);
                            if (inventory.getMoneyFed().compareTo(itemCost) != -1) {
                                userOutput.selectItemChoice(inventory, userSelectedVendingItem, userSelectedItem);
                                salesReport.editItem(userSelectedVendingItem[1], inventory.checkBogodo());
                                inventory = userOutput.vendedItemOutput(inventory, userSelectedVendingItem, userSelectedItem, itemCost);
                                inventory.reverseIsBogodo();
                            } else {
                                System.out.println("Not enough money, Please insert more money");
                            }
                        } catch (NumberFormatException e){
                            System.out.println("Item out of stock. Try again");
                        }
                    } else if (purchaseChoice.equalsIgnoreCase("finish")){
                       inventory = userOutput.finishChoice(inventory);

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

