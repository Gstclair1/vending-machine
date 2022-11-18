package com.techelevator.ui;

import com.techelevator.application.VendingMachine;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Inventory {
    private List<String[]> vendingItems = new ArrayList<>();
    private BigDecimal moneyFed = new BigDecimal(0.0).setScale(2);
    private boolean isBogodo = false;


    public List<String[]> getVendingItems() {
        return vendingItems;
    }

    public void addVendingItems(String[] vendingItem) {
        this.vendingItems.add(vendingItem);
    }


    public BigDecimal getMoneyFed() {
        return moneyFed;
    }

    public void addMoney(BigDecimal insertedMoney) {
        this.moneyFed = this.moneyFed.add(insertedMoney);
    }

    public void subtractMoney(BigDecimal moneyDue){
        if (isBogodo) {
            if (moneyDue.compareTo(this.moneyFed) != 1) {
                this.moneyFed = this.moneyFed.subtract(moneyDue);
            }
        } else {
            if (moneyDue.compareTo(this.moneyFed) != 1) {
                this.moneyFed = this.moneyFed.subtract(moneyDue.subtract(BigDecimal.ONE));
            }
        }
    }

    public void setVendingItems(List<String[]> unorderedItems) {
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
    }

    public String displayVending() {
        String vendingDisplay = "";
        int count = 0;
        for (String[] eachArray : getVendingItems()) {
            if (!eachArray[2].equalsIgnoreCase("Out of Stock")) {
                vendingDisplay += eachArray[0] + " " + eachArray[1] + " $" + eachArray[2] + "   |   ";
            } else{
                vendingDisplay += eachArray[0] + " " + eachArray[1] + " " + eachArray[2] + "   |   ";
            }
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

    public int decreasedStockLevel(String choice){
        String[] itemArr = userSelectedChoice(choice);
        int count = 0;
        int stockLeft = 0;
        for (String[] eachArray : vendingItems){
            if (eachArray[0].equals(itemArr[0])){
                stockLeft =Integer.parseInt(this.vendingItems.get(count)[4])-1;
                this.vendingItems.get(count)[4] = String.valueOf(stockLeft);
                if (stockLeft == 0) {
                    this.vendingItems.get(count)[2] = "Out of Stock";
                }
            }
            count += 1;
        }
        return stockLeft;
     }

     public int getStockLeft(String choice) {
         String[] itemArr = userSelectedChoice(choice);
         int count = 0;
         for (String[] eachArray : vendingItems) {
             if (eachArray[0].equals(itemArr[0])) {
                 return Integer.parseInt(this.vendingItems.get(count)[4]);
             }
             count += 1;
         }
         return 0;
     }

     public String getMessage(String choice){
         String[] itemArr = userSelectedChoice(choice);
         String itemType = null;
         int count = 0;
         for (String[] eachArray : vendingItems) {
             if (eachArray[0].equals(itemArr[0])) {
                itemType =this.vendingItems.get(count)[3];
             }
             count += 1;
         }
         if (itemType.equalsIgnoreCase("munchy")){
             return  "Munchy, Munchy, so Good!";
         } else if (itemType.equalsIgnoreCase("candy")){
             return "Sugar, Sugar, so Sweet!";
         } else if (itemType.equalsIgnoreCase("drink")){
             return "Drinky, Drinky, Slurp Slurp!";
         }
         return "Chewy, Chewy, Lots O Bubbles!";
     }

    public void setBogodo(boolean bogodo) {
        this.isBogodo = bogodo;
    }

    public void reverseIsBogodo(){
        this.isBogodo = !isBogodo;
     }

     public boolean checkBogodo(){
        return this.isBogodo;
     }

     // TODO: Possibly create class/interface
    public String getChange(){
        BigDecimal changeDue = this.moneyFed;
        int countDollars = 0;
        int countQuarters = 0;
        int countDimes = 0;
        int countNickles = 0;
        while (this.moneyFed.compareTo(BigDecimal.ONE) != -1){
            this.subtractMoney(BigDecimal.ONE);
            countDollars += 1;
        }
        while (this.moneyFed.compareTo(new BigDecimal("0.25"))!= -1){
            this.subtractMoney((new BigDecimal("0.25")));
            countQuarters += 1;
        }
        while (this.moneyFed.compareTo(new BigDecimal("0.10")) != -1 ){
            this.subtractMoney(new BigDecimal("0.10"));
            countDimes += 1;
        }
        while (this.moneyFed.compareTo(new BigDecimal("0.05")) != -1){
            this.subtractMoney(new BigDecimal("0.05"));
            countNickles += 1;
        }

        return "$" + changeDue + " is equal to " + countDollars + " dollars, " + countQuarters + " quarters, " + countDimes + " dimes, and " + countNickles + " nickels.";

    }



}
