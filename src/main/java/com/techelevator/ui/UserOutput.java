package com.techelevator.ui;

import java.math.BigDecimal;
import java.util.Map;


/**
 * Responsibilities: This class should handle formatting and displaying ALL
 * messages to the user
 * 
 * Dependencies: None
 */
public class UserOutput
{
    UserInput userInput = new UserInput();
    Audit audit = new Audit();
    public void displayMessage(String message)
    {
        System.out.println();
        System.out.println(message);
        System.out.println();
    }

    public void displayHomeScreen()
    {
        System.out.println();
        System.out.println("***************************************************");
        System.out.println("        Welcome to the Gianni & Amanda's "         );
        System.out.println("                Vending Machine "                  );
        System.out.println("***************************************************");
        System.out.println();
    }

    public Inventory moneyFedChoice(String purchaseChoice,Inventory inventory){
        inventory.addMoney(userInput.insertedMoneyChoice());
        System.out.println("Current Balance: $" + inventory.getMoneyFed());
        audit.addToAuditList(purchaseChoice, userInput.getLastInsertedMoney(),String.valueOf(inventory.getMoneyFed()), "");
        return inventory;
    }

    public void selectItemChoice(Inventory inventory, String[] userSelectedVendingItem, String userSelectedItem){
        if (!inventory.checkBogodo()) {
            audit.addToAuditList(userSelectedVendingItem[1], String.valueOf(inventory.getMoneyFed()), String.valueOf(inventory.getMoneyFed().subtract(new BigDecimal(userSelectedVendingItem[2]))), userSelectedItem);
        } else  {
            audit.addToAuditList(userSelectedVendingItem[1], String.valueOf(inventory.getMoneyFed()), String.valueOf(inventory.getMoneyFed().subtract(new BigDecimal(userSelectedVendingItem[2]).subtract(BigDecimal.ONE))), userSelectedItem);
        }
    }

    public Inventory vendedItemOutput(Inventory inventory, String[] userSelectedVendingItem, String userSelectedItem, BigDecimal itemCost){
        System.out.println("Here is your: " + userSelectedVendingItem[1]);
        System.out.println(inventory.getMessage(userSelectedItem));
        int stockLeft = inventory.decreasedStockLevel(userSelectedItem);
        System.out.println("There are " + stockLeft + " " + userSelectedVendingItem[1] + "(s) left.");
        inventory.subtractMoney(itemCost);
        System.out.println("Current Balance: $" + inventory.getMoneyFed());
        return inventory;
    }

    public Inventory finishChoice(Inventory inventory){
        audit.addToAuditList("change given", String.valueOf(inventory.getMoneyFed()), "0.00", "");
        System.out.println("Here is you change: $" + inventory.getMoneyFed());
        System.out.println(inventory.getChange());
        inventory.subtractMoney(inventory.getMoneyFed());
        return inventory;
    }

}
