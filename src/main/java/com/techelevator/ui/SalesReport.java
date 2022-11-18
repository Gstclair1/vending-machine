package com.techelevator.ui;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class SalesReport {
    private List<String[]> salesReport = new ArrayList<>();

    public void editItem(String nameOfItem, boolean isBogodo) {
        int counter = 0;
        for(String[] eachArray : this.salesReport) {
            if (eachArray[0].equals(nameOfItem)) {
                if (isBogodo) {
                    this.salesReport.get(counter)[2] = String.valueOf(Integer.parseInt(eachArray[2])+1) ;
                } else {
                    this.salesReport.get(counter)[1] = String.valueOf(Integer.parseInt(eachArray[1])+1);
                }
            }
                counter += 1;
            }
        }


    public void addToList(String[] itemArr) {
       String[] itemToAdd = new String[4];
       itemToAdd[0] = itemArr[1];
       itemToAdd[1] = "0";
       itemToAdd[2] = "0";
       itemToAdd[3] = itemArr[2];

       this.salesReport.add(itemToAdd);
    }

    public String callSalesReport(){
        System.out.println("Taste Elevator Sales Report");
        BigDecimal moneyMade = new BigDecimal("0.00").setScale(2);
        String salesReportString = "";
        for(String[] eachItem : this.salesReport ){
           salesReportString += eachItem[0] + " | " + eachItem[1] + " | " + eachItem[2] + "\n";
            BigDecimal bogodoMoneyMade = new BigDecimal(eachItem[2]).multiply(new BigDecimal(eachItem[3]).subtract(BigDecimal.ONE)).setScale(2);
            BigDecimal regularMoneyMade = new BigDecimal(eachItem[1]).multiply(new BigDecimal(eachItem[3])).setScale(2);
            moneyMade = (moneyMade.add(regularMoneyMade.add(bogodoMoneyMade)).setScale(2));
        }
         salesReportString += "TOTAL SALES: " + moneyMade;
        return salesReportString;
    }

}
