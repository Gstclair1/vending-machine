package com.techelevator.ui;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class InventoryTest {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void addMoneyTest() {
        //arrange
        BigDecimal expectedMoney = new BigDecimal("5.40").setScale(2);
        Inventory money = new Inventory();
        money.addMoney(new BigDecimal("5.40").setScale(2));

        //act
        BigDecimal returnMoney = money.getMoneyFed();
        //assert
        Assert.assertEquals(expectedMoney, returnMoney);

    }
    @Test
    public void addMultiMoneyTest() {
        //arrange
        BigDecimal expectedMoney = new BigDecimal("7.90").setScale(2);
        Inventory money = new Inventory();
        money.addMoney(new BigDecimal("5.40").setScale(2));
        money.addMoney(BigDecimal.ONE.setScale(2));
        money.addMoney(new BigDecimal("1.50").setScale(2));

        //act
        BigDecimal returnMoney = money.getMoneyFed();
        //assert
        Assert.assertEquals(expectedMoney, returnMoney);

    }
    @Test
    public void subtractMoneyTest() {
        //arrange
        BigDecimal expectedMoney = new BigDecimal("2.50").setScale(2);
        Inventory money = new Inventory();
        money.addMoney(new BigDecimal("5.00").setScale(2));
        //TODO: our bogodo isn't running through the test properly
        money.setBogodo(true);
        money.subtractMoney(new BigDecimal("2.50").setScale(2));
        //act
        BigDecimal returnMoney = money.getMoneyFed();
        //assert
        Assert.assertEquals(expectedMoney, returnMoney);

    }
    @Test
    public void subtractMoneyBogodoTest() {
        //arrange
        BigDecimal expectedMoney = new BigDecimal("3.50").setScale(2);
        Inventory money = new Inventory();
        money.addMoney(new BigDecimal("5.00").setScale(2));
        money.setBogodo(false);
        money.subtractMoney(new BigDecimal("2.50").setScale(2));
        //act
        BigDecimal returnMoney = money.getMoneyFed();
        //assert
        Assert.assertEquals(expectedMoney, returnMoney);

    }


    @Test
    public void displayVendingTest() {
        //arrange
        List<String[]> displayItems = new ArrayList<>();
        Inventory display = new Inventory();
        String expected = "A1 Candy $1.65   |   A2 Chippos $2.65   |   A3 Candy $2.65   |   A4 Munchy $2.65   |   \nB1 Chippos $2.65   |   ";
        displayItems.add(new String[]{"A1", "Candy", "1.65"});
        displayItems.add(new String[]{"A2", "Chippos", "2.65"});
        displayItems.add(new String[]{"A3", "Candy", "2.65"});
        displayItems.add(new String[]{"A4", "Munchy", "2.65"});
        displayItems.add(new String[]{"B1", "Chippos", "2.65"});
        display.setVendingItems(displayItems);
        //act
        String actualReturn = display.displayVending();

        //assert
        Assert.assertEquals(expected, actualReturn);

    }
    @Test
    public void userSelectedChoiceTest() {
        //arrange
        List<String[]> displayItems = new ArrayList<>();
        Inventory display = new Inventory();
        String expected = "Munchy";
        displayItems.add(new String[]{"A1", "Candy", "1.65"});
        displayItems.add(new String[]{"A2", "Chippos", "2.65"});
        displayItems.add(new String[]{"A3", "Candy", "2.65"});
        displayItems.add(new String[]{"A4", "Munchy", "2.65"});
        displayItems.add(new String[]{"B1", "Chippos", "2.65"});
        display.setVendingItems(displayItems);
        //act
        String userSelected = display.userSelectedChoice("A4")[1];
        //assert
      Assert.assertEquals(expected, userSelected);

    }
    @Test
    public void userSelectedChoiceTest2() {
        //arrange
        List<String[]> displayItems = new ArrayList<>();
        Inventory display = new Inventory();
        String expected = "Chippos";
        displayItems.add(new String[]{"A1", "Candy", "1.65"});
        displayItems.add(new String[]{"A2", "Chippos", "2.65"});
        displayItems.add(new String[]{"A3", "Candy", "2.65"});
        displayItems.add(new String[]{"A4", "Munchy", "2.65"});
        displayItems.add(new String[]{"B1", "Chippos", "2.65"});
        display.setVendingItems(displayItems);
        //act
        String userSelected = display.userSelectedChoice("A2")[1];
        //assert
        Assert.assertEquals(expected, userSelected);

    }

    @Test
    public void decreasedStockLevelTest() {
        //arrange
        List<String[]> displayItems = new ArrayList<>();
        Inventory display = new Inventory();
        int expected = 5;
        displayItems.add(new String[]{"A1", "Candy", "1.65", "", "6"});
        displayItems.add(new String[]{"A2", "Chippos", "2.65", "", "6"});
        displayItems.add(new String[]{"A3", "Candy", "2.65", "", "6"});
        displayItems.add(new String[]{"A4", "Munchy", "2.65", "", "6"});
        displayItems.add(new String[]{"B1", "Chippos", "2.65", "", "6"});
        display.setVendingItems(displayItems);
        //act
        int returnStockLevel = display.decreasedStockLevel("A3");

        //assert
        Assert.assertEquals(expected, returnStockLevel);

    }

    @Test
    public void outOfStock() {
        //arrange
        List<String[]> displayItems = new ArrayList<>();
        Inventory display = new Inventory();
        String expected = "Out of Stock";
        displayItems.add(new String[]{"A1", "Candy", "1.65", "", "1"});
        displayItems.add(new String[]{"A2", "Chippos", "2.65", "", "6"});
        displayItems.add(new String[]{"A3", "Candy", "2.65", "", "6"});
        displayItems.add(new String[]{"A4", "Munchy", "2.65", "", "6"});
        displayItems.add(new String[]{"B1", "Chippos", "2.65", "", "6"});
        display.setVendingItems(displayItems);
        //act
        int returnStockLevel = display.decreasedStockLevel("A1");
        String returnOOS = display.userSelectedChoice("A1")[2];

        //assert
        Assert.assertEquals(expected, returnOOS);

    }

    @Test
    public void getMessageTest() {
        //arrange
        List<String[]> displayItems = new ArrayList<>();
        Inventory display = new Inventory();
        String expected = "Munchy, Munchy, so Good!";
        displayItems.add(new String[]{"A1", "Candy", "1.65", "candy", "1"});
        displayItems.add(new String[]{"A2", "Munchy", "2.65", "munchy", "6"});
        displayItems.add(new String[]{"A3", "Candy", "2.65", "", "6"});
        displayItems.add(new String[]{"A4", "Munchy", "2.65", "", "6"});
        displayItems.add(new String[]{"B1", "Chippos", "2.65", "", "6"});
        display.setVendingItems(displayItems);
        //act
        String message = display.getMessage("A2");

        //assert
        Assert.assertEquals(expected, message);

    }

    @Test
    public void getSetCheckBogodoTest() {
        //arrange
        Inventory bogodo = new Inventory();
        bogodo.setBogodo(false);
        bogodo.reverseIsBogodo();
        //act
        boolean returnBogodo = bogodo.checkBogodo();

        //assert
       assertTrue( returnBogodo );

    }

    @Test
    public void getChangeTest() {
        //arrange
        Inventory change = new Inventory();
        change.addMoney(new BigDecimal("5.40").setScale(2));
        String expectedReturn = "$5.40 is equal to 5 dollars, 1 quarters, 1 dimes, and 1 nickels.";

        //act
        String changeReturn = change.getChange();

        //assert
        assertEquals(expectedReturn, changeReturn);

    }


}