package com.techelevator.ui;

import com.techelevator.application.VendingMachine;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Inventory {
    private List<String[]> vendingItems = new ArrayList<>();
    private int itemsPurchased = 0;
    private int itemsPurchasedDiscount = 0;
    private BigDecimal moneyFed = new BigDecimal(0.0).setScale(2);

    public List<String[]> getVendingItems() {
        return vendingItems;
    }

    public void addVendingItems(String[] vendingItem) {
        this.vendingItems.add(vendingItem);
    }

    public int getItemsPurchased() {
        return itemsPurchased;
    }

    public int getItemsPurchasedDiscount() {
        return itemsPurchasedDiscount;
    }

    public BigDecimal getMoneyFed() {
        return moneyFed;
    }

    public void addMoney(BigDecimal insertedMoney) {
        this.moneyFed = this.moneyFed.add(insertedMoney);
    }

    public void subtractMoney(BigDecimal moneyDue){
        if (moneyDue.compareTo(this.moneyFed) != 1) {
            this.moneyFed = this.moneyFed.subtract(moneyDue);
        }
    }


    public String displayVending(List<String[]> unorderedItems) {
        String vendingDisplay = "";
        for (int i = 0; i < 4; i++) {
            for (String[] eachArray : unorderedItems) {
                if (i == 0 && eachArray[0].contains("A")) {
                    this.addVendingItems(eachArray);
                } else if (i == 1 && eachArray[0].contains("B")) {
                    this.addVendingItems(eachArray);
                } else if (i == 2 && eachArray[0].contains("C")) {
                    this.addVendingItems(eachArray);
                } else if (i == 3 && eachArray[0].contains("D")) {
                    this.addVendingItems(eachArray);
                }
            }
        }
        int count = 0;

        for (String[] eachArray : getVendingItems()) {
            vendingDisplay += eachArray[0] + " " + eachArray[1] + " $" + eachArray[2] + "   |   ";
            count += 1;
            if (count % 4 == 0) {
                vendingDisplay+="\n";
            }

        }
        return vendingDisplay;
    }

    public String[] userSelectedChoice(String choice) {
        for (String[] eachArray : this.vendingItems){
            if(eachArray[0].equalsIgnoreCase(choice)){
                return eachArray;
            }
        }
        return null;
    }



}
