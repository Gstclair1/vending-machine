package com.techelevator.ui;

import com.techelevator.application.VendingMachine;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Inventory {
    private List<String[]> vendingItems = new ArrayList<>();
    private BigDecimal moneyFed = new BigDecimal(0.0).setScale(2);
    private boolean isBogodo = false;
    private final int ZERO = 0;
    private final int FIVE = 5;
    private final int TEN = 10;
    private final int TWENTY_FIVE = 25;
    private final int HUNDRED = 100;


    public List<String[]> getVendingItems() {
        return vendingItems;
    }

    public void addVendingItems(String[] vendingItem) {
        this.vendingItems.add(vendingItem);
    }


    public BigDecimal getMoneyFed() {
        return this.moneyFed;
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
        int count = ZERO;
        for (String[] eachArray : getVendingItems()) {
            if (!eachArray[2].equalsIgnoreCase("Out of Stock")) {
                vendingDisplay += eachArray[0] + " " + eachArray[1] + " $" + eachArray[2] + "   |   ";
            } else{
                vendingDisplay += eachArray[0] + " " + eachArray[1] + " " + eachArray[2] + "   |   ";
            }
            count += 1;
            if (count % 4 == ZERO) {
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
        int count = ZERO;
        int stockLeft = ZERO;
        for (String[] eachArray : vendingItems){
            if (eachArray[0].equals(itemArr[0])){
                stockLeft =Integer.parseInt(this.vendingItems.get(count)[4])-1;
                this.vendingItems.get(count)[4] = String.valueOf(stockLeft);
                if (stockLeft == ZERO) {
                    this.vendingItems.get(count)[2] = "Out of Stock";
                }
            }
            count += 1;
        }
        return stockLeft;
     }

     public int getStockLeft(String choice) {
         String[] itemArr = userSelectedChoice(choice);
         int count = ZERO;
         for (String[] eachArray : vendingItems) {
             if (eachArray[0].equals(itemArr[0])) {
                 return Integer.parseInt(this.vendingItems.get(count)[4]);
             }
             count += 1;
         }
         return ZERO;
     }

     public String getMessage(String choice){
         String[] itemArr = userSelectedChoice(choice);
         String itemType = null;
         int count = ZERO;
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

    public String getChange(){

        double convertedAmt = this.moneyFed.doubleValue() * 100.0;
        int convertedAmtInt = (int) convertedAmt;

        BigDecimal changeDue = this.moneyFed;
        int countDollars = ZERO;
        int countQuarters = ZERO;
        int countDimes = ZERO;
        int countNickles = ZERO;
        while (convertedAmtInt >= HUNDRED){
            convertedAmtInt -= HUNDRED;
            countDollars += 1;
        }
        while (convertedAmtInt >= TWENTY_FIVE){
            convertedAmtInt -= TWENTY_FIVE;
            countQuarters += 1;
        }
        while (convertedAmtInt >= TEN){
            convertedAmtInt -= TEN;
            countDimes += 1;
        }
        while (convertedAmtInt >= FIVE){
            convertedAmtInt -= FIVE;
            countNickles += 1;
        }

        return "$" + changeDue + " is equal to " + countDollars + " dollars, " + countQuarters + " quarters, " + countDimes + " dimes, and " + countNickles + " nickels.";

    }



}
