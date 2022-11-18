package com.techelevator.ui;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Audit {
    private List<String[]> auditList = new ArrayList<>();

    DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("MM/dd/yyyy hh:mm:ss a");

    public String getTypeOfTransaction(String type){
        if (type.equalsIgnoreCase("money fed")){
            return "MONEY FED:";
        } else if (type.equalsIgnoreCase("change given")){
            return "CHANGE GIVEN:";
        } else {
            return type;
        }
    }



    public void addToAuditList(String transactionType, String moneyInserted, String moneyEnd, String selectedItem) {
        String type = this.getTypeOfTransaction(transactionType);
        LocalDateTime dateTime = LocalDateTime.now();
        String dateTimeFormat = dateTime.format(dateTimeFormatter);
        String[] auditItem = new String[]{dateTimeFormat, type, selectedItem, moneyInserted, moneyEnd};
        File audit = new File("C:\\Users\\Student\\workspace\\java-orange-minicapstonemodule1-team7\\audit.txt");
        if (!audit.exists()) {
            try{
                audit.createNewFile();
        } catch (IOException e){
                System.out.println("File not created");
            }
        }
        try {
            PrintWriter printWriter = new PrintWriter( new FileOutputStream(audit, true));
            for (String eachItem : auditItem) {
                System.out.println(eachItem);
                printWriter.print(eachItem);
            }
            printWriter.print("\n");
            printWriter.flush();
            printWriter.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
    }
}
