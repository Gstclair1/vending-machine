package com.techelevator.ui;

import java.io.*;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Audit {
    private List<String[]> auditList = new ArrayList<>();
    private final String FORMATTING_24 = "%-24s";

    DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(String.format("MM/dd/yyyy hh:mm:ss a \t"));

    public String getTypeOfTransaction(String type){
        if (type.equalsIgnoreCase("money fed")){
            return String.format(FORMATTING_24, "MONEY FED: ");
        } else if (type.equalsIgnoreCase("change given")){
            return String.format(FORMATTING_24, "CHANGE GIVEN: ");
        } else {
            return String.format(FORMATTING_24, type);
        }
    }

    public void addToAuditList(String transactionType, String moneyInserted, String moneyEnd, String selectedItem) {
        DecimalFormat formatter = new DecimalFormat("####, ####");
        String type = this.getTypeOfTransaction(transactionType);
        LocalDateTime dateTime = LocalDateTime.now();
        String dateTimeFormat = dateTime.format(dateTimeFormatter);

        String[] auditItem = new String[]{dateTimeFormat, type, String.format("%-4s" , selectedItem.toUpperCase()), String.format("$" + moneyInserted, formatter.format(2)), String.format("\t" + "$" + moneyEnd, formatter.format(2))};

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
                printWriter.print(eachItem);
            }
            printWriter.print("\n");
            printWriter.flush();
            printWriter.close();

        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
    }

    public void setUpAudit(){
        LocalDateTime localDateTime = LocalDateTime.now();
        DateTimeFormatter dateTimeFormat = DateTimeFormatter.ofPattern("MM/dd/yy hh:mm a");
        File auditClear = new File("audit.txt");
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
    }
}
