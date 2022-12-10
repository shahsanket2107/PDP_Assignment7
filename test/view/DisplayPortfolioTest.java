package view;

import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.PrintStream;

import model.Investor;
import model.InvestorImpl;
import model.ParseFile;
import model.ParseFileImpl;

import static org.junit.Assert.assertEquals;

/**
 * Test class for Display Portfolio.
 */
public class DisplayPortfolioTest {
  ParseFile file;
  ParseFile fileDate;
  DisplayPortfolio newDisplay;

  @Before
  public void setUp() throws FileNotFoundException {
    file = new ParseFileImpl();
    fileDate = new ParseFileImpl();
    file.loadFile("files/data.csv");
    fileDate.loadFile("basitsport.csv");
    newDisplay = new DisplayPortfolioImpl();
  }

  @Test
  public void testDisplayContents() {
    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    PrintStream printStream = new PrintStream(outputStream);
    PrintStream prev = System.out;
    System.setOut(printStream);
    newDisplay.displayContents(file.getPortfolioContents());
    System.out.flush();
    System.setOut(prev);
    String banner = "TICKER|                      STOCK PRICE|         # OF SHARES| ";
    String lines = "--------------------------------------------------------------";

    String stockOne = "AAPL                              149.35                  10";
    String stockTwo = "AMZN                              115.66                   8";
    String stockThree = "BRK.A                          435600.00                  40";
    String expectedOutput = banner + "\n" + lines + "\n"
            + stockOne + "\n"
            + stockTwo + "\n"
            + stockThree + "\n";
    assertEquals(expectedOutput, outputStream.toString());

  }

  @Test
  public void testDisplayContentsDate() {
    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    PrintStream printStream = new PrintStream(outputStream);
    PrintStream prev = System.out;
    System.setOut(printStream);
    newDisplay.displayContents(fileDate.getPortfolioContents());
    System.out.flush();
    System.setOut(prev);
    String banner = "TICKER|                   DATE PURCHASED|"
            + "STOCK PRICE|         # OF SHARES| ";
    String lines = "-------------------------------------------"
            + "----------------------------------------";
    String stockOne = "AAPL                          12-03-2001"
            + "                0.33                  12";
    String expectedOutput = banner + "\n" + lines + "\n"
            + stockOne + "\n";
    assertEquals(expectedOutput, outputStream.toString());

  }

  @Test
  public void testDisplayContentsDateQuery() {
    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    PrintStream printStream = new PrintStream(outputStream);
    PrintStream prev = System.out;
    System.setOut(printStream);
    newDisplay.displayContents(fileDate.getPortfolioContents(), "12-03-2001");
    System.out.flush();
    System.setOut(prev);
    String prefix = "The composition of the portfolio for the date: 12-03-2001";
    String banner = "TICKER|                             DATE|         STOCK PRICE| ";
    String lines = "--------------------------------------------------------------";
    String stockOne = "AAPL                          12-03-2001                0.33";
    String expectedOutput = prefix + "\n" + banner + "\n" + lines + "\n"
            + stockOne + "\n";
    assertEquals(expectedOutput, outputStream.toString());

  }

  @Test
  public void testDisplayContentsString() throws FileNotFoundException {
    Investor investor = new InvestorImpl();
    investor.createPortfolio("retirement");
    investor.buyShares("NI", "20", "03-02-2007", 8);
    investor.addStock("retirement", "NI");
    investor.buyShares("AAPL", "10", "03-02-2007", 9);
    investor.addStock("retirement", "AAPL");

    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    PrintStream printStream = new PrintStream(outputStream);
    PrintStream prev = System.out;
    System.setOut(printStream);
    newDisplay.displayContents(investor.getPortfolio("retirement").examine());
    System.out.flush();
    System.setOut(prev);
    String banner = "TICKER|                   DATE PURCHASED|"
            + "STOCK PRICE|         # OF SHARES| ";
    String lines = "-------------------------------------------"
            + "----------------------------------------";

    String stockOne = "NI                                   8.0                  20";
    String stockTwo = "AAPL                                 9.0                  10";
    String expectedOutput = banner + "\n" + lines + "\n"
            + stockOne + "\n"
            + stockTwo + "\n";
    assertEquals(expectedOutput, outputStream.toString());
  }

  @Test
  public void testDisplayContentsStringDate() throws FileNotFoundException {
    Investor investor = new InvestorImpl();
    investor.createPortfolio("retirement");
    investor.buyShares("NI", "20", "03-02-2007", 8);
    investor.addStock("retirement", "NI");
    investor.buyShares("AAPL", "10", "03-02-2007", 9);
    investor.addStock("retirement", "AAPL");

    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    PrintStream printStream = new PrintStream(outputStream);
    PrintStream prev = System.out;
    System.setOut(printStream);
    newDisplay.displayContents(investor.getPortfolio("retirement").examine());
    System.out.flush();
    System.setOut(prev);
    String banner = "TICKER|                   DATE PURCHASED|"
            + "STOCK PRICE|         # OF SHARES| ";
    String lines = "-------------------------------------------"
            + "----------------------------------------";

    String stockOne = "NI                            03-02-2007"
            + "                 8.0                  20";
    String stockTwo = "AAPL                          03-02-2007"
            + "                 9.0                  10";
    String expectedOutput = banner + "\n" + lines + "\n"
            + stockOne + "\n"
            + stockTwo + "\n";
    assertEquals(expectedOutput, outputStream.toString());
  }

  @Test
  public void testDisplayContentsStringDateQuery() throws FileNotFoundException {
    Investor investor = new InvestorImpl();
    investor.createPortfolio("retirement");
    investor.buyShares("NI", "20", "03-02-2007", 8);
    investor.addStock("retirement", "NI");
    investor.buyShares("AAPL", "10", "03-02-2007", 9);
    investor.addStock("retirement", "AAPL");

    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    PrintStream printStream = new PrintStream(outputStream);
    PrintStream prev = System.out;
    System.setOut(printStream);
    newDisplay.displayContents(investor.getPortfolio("retirement").examine(), "12-03-2001");
    System.out.flush();
    System.setOut(prev);
    String prefix = "The composition of the portfolio for the date: 12-03-2001";
    String banner = "TICKER|                             DATE|         STOCK PRICE| ";
    String lines = "--------------------------------------------------------------";
    String stockOne = "NI                            12-03-2001               11.52";
    String stockTwo = "AAPL                          12-03-2001                0.33";
    String expectedOutput = prefix + "\n"
            + banner + "\n" + lines + "\n"
            + stockOne + "\n"
            + stockTwo + "\n\n";
    assertEquals(expectedOutput, outputStream.toString());
  }


}
