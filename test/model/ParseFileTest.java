package model;

import org.junit.Before;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

/**
 * Parse file test.
 */
public class ParseFileTest {
  ParseFile newFile;

  @Before
  public void setUp() throws FileNotFoundException {
    newFile = new ParseFileImpl();
    newFile.loadFile("files/data.csv");
  }

  @Test(expected = FileNotFoundException.class)
  public void invalidPath() throws FileNotFoundException {
    newFile = new ParseFileImpl();
    newFile.loadFile("files/datas.csv");
  }

  @Test
  public void testContents() {
    ArrayList<String> contents = newFile.getPortfolioContents();
    String stockOne = "AAPL, 149.35, 10";
    String stockTwo = "AMZN, 115.66, 8";
    String stockThree = "BRK.A, 435600.00, 40";
    assertEquals(stockOne, contents.get(0));
    assertEquals(stockTwo, contents.get(1));
    assertEquals(stockThree, contents.get(2));

  }

  @Test
  public void testSaveFile() throws FileNotFoundException {
    Investor investor = new InvestorImpl();
    investor.createPortfolio("retirement");
    investor.buyShares("NI", "20", "03-02-2007", 9);
    investor.addStock("retirement", "NI");
    investor.buyShares("AAPL", "10", "03-02-2007", 90);
    investor.addStock("retirement", "AAPL");
    Portfolio portfolio = investor.getPortfolio("retirement");
    newFile.saveFile("testPort", portfolio);
    ParseFile loadNewFile = new ParseFileImpl();
    loadNewFile.loadFile("testPort.csv");
    ArrayList<String> testOutput = new ArrayList<>();
    testOutput.add("NI,9.0,20");
    testOutput.add("AAPL,90.0,10");
    assertEquals(testOutput, loadNewFile.getPortfolioContents());
  }
}
