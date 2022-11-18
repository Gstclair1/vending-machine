package com.techelevator.ui;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class SalesReportTest {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void salesReportTest (){
        //arrange
        SalesReport salesReport = new SalesReport();
        salesReport.addToList(new String[]{"A1", "Candy", "2.50", ""});
        salesReport.addToList(new String[]{"B2", "Chips", "3.50", ""});
        salesReport.editItem("Candy", false );
        salesReport.editItem("Chips", true );
        String expectedString = "Candy | 1 | 0\nChips | 0 | 1\nTOTAL SALES: 5.00";
        //act
        String salesReturn = salesReport.callSalesReport();
        //assert
        assertEquals(expectedString, salesReturn);
    }
    @Test
    public void salesReportTest2 (){
        //arrange
        SalesReport salesReport = new SalesReport();
        salesReport.addToList(new String[]{"A1", "Candy", "2.50", ""});
        salesReport.addToList(new String[]{"B2", "Chips", "3.50", ""});
        salesReport.editItem("Candy", true );
        salesReport.editItem("Chips", true );
        String expectedString = "Candy | 0 | 1\nChips | 0 | 1\nTOTAL SALES: 4.00";
        //act
        String salesReturn = salesReport.callSalesReport();
        //assert
        assertEquals(expectedString, salesReturn);
    }
}