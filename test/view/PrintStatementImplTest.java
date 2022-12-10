package view;

import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertEquals;

/**
 * Test class for PrintStatementImplTest.
 */
public class PrintStatementImplTest {

  PrintStatementImpl print;
  ByteArrayOutputStream outputStream;
  PrintStream printStream;

  @Before
  public void setUp() throws Exception {
    print = new PrintStatementImpl();
    outputStream = new ByteArrayOutputStream();
    printStream = new PrintStream(outputStream);
  }

  @Test
  public void chooseOption1() {
    PrintStream prev = System.out;
    System.setOut(printStream);
    print.chooseOption1();
    System.out.flush();
    System.setOut(prev);
    String expectedOutput = "Please select a valid option(1-6): \n";
    assertEquals(expectedOutput, outputStream.toString());
  }

  @Test
  public void chooseOption2() {
    PrintStream prev = System.out;
    System.setOut(printStream);
    print.chooseOption2();
    System.out.flush();
    System.setOut(prev);
    String expectedOutput = "Please select a valid option(y/n): \n";
    assertEquals(expectedOutput, outputStream.toString());
  }

  @Test
  public void chooseOption3() {
    PrintStream prev = System.out;
    System.setOut(printStream);
    print.chooseOption3();
    System.out.flush();
    System.setOut(prev);
    String expectedOutput = "Please select a valid option...\n";
    assertEquals(expectedOutput, outputStream.toString());
  }

  @Test
  public void chooseOption4() {
    PrintStream prev = System.out;
    System.setOut(printStream);
    print.chooseOption4();
    System.out.flush();
    System.setOut(prev);
    String expectedOutput = "Please select a valid option(buy/sell): \n";
    assertEquals(expectedOutput, outputStream.toString());
  }

  @Test
  public void newStock() {
    PrintStream prev = System.out;
    System.setOut(printStream);
    print.newStock();
    System.out.flush();
    System.setOut(prev);
    String expectedOutput = "Enter name of stock or enter q to exit...\n";
    assertEquals(expectedOutput, outputStream.toString());
  }

  @Test
  public void addStock() {
    PrintStream prev = System.out;
    System.setOut(printStream);
    print.addStock();
    System.out.flush();
    System.setOut(prev);
    String expectedOutput = "Do you want add stock to the portfolio? (y/n)\n";
    assertEquals(expectedOutput, outputStream.toString());
  }

  @Test
  public void stocksNotFound() {
    PrintStream prev = System.out;
    System.setOut(printStream);
    print.stocksNotFound();
    System.out.flush();
    System.setOut(prev);
    String expectedOutput = "No stocks found under investor profile.\n\n";
    assertEquals(expectedOutput, outputStream.toString());
  }

  @Test
  public void stocksDisplay() {
    PrintStream prev = System.out;
    System.setOut(printStream);
    print.stocksDisplay("test");
    System.out.flush();
    System.setOut(prev);
    String expectedOutput = "Your stocks are:\ntest\n";
    assertEquals(expectedOutput, outputStream.toString());
  }

  @Test
  public void stockBuySell() {
    PrintStream prev = System.out;
    System.setOut(printStream);
    print.stockBuySell();
    System.out.flush();
    System.setOut(prev);
    String expectedOutput = "You will be charged a fixed commission fee for this purchase.\n"
            + "Would you like to buy or sell stock? (buy / sell)\n";
    assertEquals(expectedOutput, outputStream.toString());
  }

  @Test
  public void chooseStock() {
    PrintStream prev = System.out;
    System.setOut(printStream);
    print.chooseStock();
    System.out.flush();
    System.setOut(prev);
    String expectedOutput = "Choose a stock to add to new portfolio...\n";
    assertEquals(expectedOutput, outputStream.toString());
  }

  @Test
  public void stockErr1() {
    PrintStream prev = System.out;
    System.setOut(printStream);
    print.stockErr1("test1", "test2");
    System.out.flush();
    System.setOut(prev);
    String expectedOutput = "Stock test1 already exists in portfolio test2\n";
    assertEquals(expectedOutput, outputStream.toString());
  }

  @Test
  public void stockErr2() {
    PrintStream prev = System.out;
    System.setOut(printStream);
    print.stockErr2("test");
    System.out.flush();
    System.setOut(prev);
    String expectedOutput = "Stock test does not exist under investor profile\n";
    assertEquals(expectedOutput, outputStream.toString());
  }

  @Test
  public void stockErr3() {
    PrintStream prev = System.out;
    System.setOut(printStream);
    print.stockErr3("test", "3");
    System.out.flush();
    System.setOut(prev);
    String expectedOutput = "Investor does not own 3 shares of stock test.\n";
    assertEquals(expectedOutput, outputStream.toString());
  }

  @Test
  public void stockSuccess() {
    PrintStream prev = System.out;
    System.setOut(printStream);
    print.stockSuccess("test1", "test2");
    System.out.flush();
    System.setOut(prev);
    String expectedOutput = "Stock test1 successfully added to portfolio "
            + "test2.\nWould you like to add another stock to "
            + "test2? (y/n)\n";
    assertEquals(expectedOutput, outputStream.toString());
  }

  @Test
  public void sellStock1() {
    PrintStream prev = System.out;
    System.setOut(printStream);
    print.sellStock1();
    System.out.flush();
    System.setOut(prev);
    String expectedOutput = "Choose a stock to sell or enter q to exit...\n";
    assertEquals(expectedOutput, outputStream.toString());
  }

  @Test
  public void sellStock2() {
    PrintStream prev = System.out;
    System.setOut(printStream);
    print.sellStock2();
    System.out.flush();
    System.setOut(prev);
    String expectedOutput = "Enter date of stock to sell...\n";
    assertEquals(expectedOutput, outputStream.toString());
  }

  @Test
  public void sellStock3() {
    PrintStream prev = System.out;
    System.setOut(printStream);
    print.sellStock3();
    System.out.flush();
    System.setOut(prev);
    String expectedOutput = "Enter number of shares to sell...\n";
    assertEquals(expectedOutput, outputStream.toString());
  }

  @Test
  public void shares1() {
    PrintStream prev = System.out;
    System.setOut(printStream);
    print.shares1();
    System.out.flush();
    System.setOut(prev);
    String expectedOutput = "Enter number of shares to buy or enter q to exit...\n";
    assertEquals(expectedOutput, outputStream.toString());
  }

  @Test
  public void shares2() {
    PrintStream prev = System.out;
    System.setOut(printStream);
    print.shares2();
    System.out.flush();
    System.setOut(prev);
    String expectedOutput = "Enter number of shares to sell or enter q to exit...\n";
    assertEquals(expectedOutput, outputStream.toString());
  }

  @Test
  public void sharesErr1() {
    PrintStream prev = System.out;
    System.setOut(printStream);
    print.sharesErr1();
    System.out.flush();
    System.setOut(prev);
    String expectedOutput = "Number of shares must be a whole number containing only digits.\n";
    assertEquals(expectedOutput, outputStream.toString());
  }

  @Test
  public void sharesErr2() {
    PrintStream prev = System.out;
    System.setOut(printStream);
    print.sharesErr2();
    System.out.flush();
    System.setOut(prev);
    String expectedOutput = "Date entered does not match investor data.\n";
    assertEquals(expectedOutput, outputStream.toString());
  }

  @Test
  public void sharesSuccess() {
    PrintStream prev = System.out;
    System.setOut(printStream);
    print.sharesSuccess("2", "test");
    System.out.flush();
    System.setOut(prev);
    String expectedOutput = "Successfully bought 2 shares of test\n";
    assertEquals(expectedOutput, outputStream.toString());
  }

  @Test
  public void portfolioNew() {
    PrintStream prev = System.out;
    System.setOut(printStream);
    print.portfolioNew();
    System.out.flush();
    System.setOut(prev);
    String expectedOutput = "Enter name for new portfolio...\n";
    assertEquals(expectedOutput, outputStream.toString());
  }

  @Test
  public void portfolioLoad() {
    PrintStream prev = System.out;
    System.setOut(printStream);
    print.portfolioLoad();
    System.out.flush();
    System.setOut(prev);
    String expectedOutput = "Please enter in the name of the portfolio you want to load: \n";
    assertEquals(expectedOutput, outputStream.toString());
  }

  @Test
  public void portfolioErr() {
    PrintStream prev = System.out;
    System.setOut(printStream);
    print.portfolioErr();
    System.out.flush();
    System.setOut(prev);
    String expectedOutput = "A portfolio with this name already exists. "
            + "Please enter a valid name: \n";
    assertEquals(expectedOutput, outputStream.toString());
  }

  @Test
  public void portfolioInvalid() {
    PrintStream prev = System.out;
    System.setOut(printStream);
    print.portfolioInvalid();
    System.out.flush();
    System.setOut(prev);
    String expectedOutput = "Please enter a valid portfolio: \n";
    assertEquals(expectedOutput, outputStream.toString());
  }

  @Test
  public void portfolioCreateSuccess() {
    PrintStream prev = System.out;
    System.setOut(printStream);
    print.portfolioCreateSuccess("test");
    System.out.flush();
    System.setOut(prev);
    String expectedOutput = "New portfolio test has been created and saved to profile.\n";
    assertEquals(expectedOutput, outputStream.toString());
  }

  @Test
  public void portfolioLoadSuccess() {
    PrintStream prev = System.out;
    System.setOut(printStream);
    print.portfolioLoadSuccess("test");
    System.out.flush();
    System.setOut(prev);
    String expectedOutput = "Your portfolio test has been loaded in successfully.\n";
    assertEquals(expectedOutput, outputStream.toString());
  }

  @Test
  public void portfoliosNotFound() {
    PrintStream prev = System.out;
    System.setOut(printStream);
    print.portfoliosNotFound();
    System.out.flush();
    System.setOut(prev);
    String expectedOutput = "No portfolios found under investor profile.\n";
    assertEquals(expectedOutput, outputStream.toString());
  }

  @Test
  public void portfolioNotFound() {
    PrintStream prev = System.out;
    System.setOut(printStream);
    print.portfolioNotFound("test");
    System.out.flush();
    System.setOut(prev);
    String expectedOutput = "Portfolio test does not exist under investor profile.\n";
    assertEquals(expectedOutput, outputStream.toString());
  }

  @Test
  public void displayPortfolios() {
    PrintStream prev = System.out;
    System.setOut(printStream);
    print.displayPortfolios("test1", "test2");
    System.out.flush();
    System.setOut(prev);
    String expectedOutput = "Your portfolios are:\ntest1test2\nChoose a portfolio "
            + "or enter q to exit...\n";
    assertEquals(expectedOutput, outputStream.toString());
  }

  @Test
  public void value() {
    PrintStream prev = System.out;
    System.setOut(printStream);
    print.value("2000", 3);
    System.out.flush();
    System.setOut(prev);
    String expectedOutput = "Value of portfolio on 2000 is 3.0\n";
    assertEquals(expectedOutput, outputStream.toString());
  }

  @Test
  public void portfolioSave() {
    PrintStream prev = System.out;
    System.setOut(printStream);
    print.portfolioSave();
    System.out.flush();
    System.setOut(prev);
    String expectedOutput = "Please enter in a name to save your portfolio as: \n";
    assertEquals(expectedOutput, outputStream.toString());
  }

  @Test
  public void enterDate() {
    PrintStream prev = System.out;
    System.setOut(printStream);
    print.enterDate();
    System.out.flush();
    System.setOut(prev);
    String expectedOutput = "Enter in a date in the format of DD-MM-YYYY: \n";
    assertEquals(expectedOutput, outputStream.toString());
  }

  @Test
  public void dateInvalid() {
    PrintStream prev = System.out;
    System.setOut(printStream);
    print.dateInvalid();
    System.out.flush();
    System.setOut(prev);
    String expectedOutput = "Invalid format. Enter in a date in the format of DD-MM-YYYY: \n";
    assertEquals(expectedOutput, outputStream.toString());
  }

  @Test
  public void path() {
    PrintStream prev = System.out;
    System.setOut(printStream);
    print.path();
    System.out.flush();
    System.setOut(prev);
    String expectedOutput = "Please enter in a valid path to load in your portfolio file: \n";
    assertEquals(expectedOutput, outputStream.toString());
  }

  @Test
  public void pathInvalid() {
    PrintStream prev = System.out;
    System.setOut(printStream);
    print.pathInvalid();
    System.out.flush();
    System.setOut(prev);
    String expectedOutput = "That was an invalid path.\n";
    assertEquals(expectedOutput, outputStream.toString());
  }

  @Test
  public void pathSave() {
    PrintStream prev = System.out;
    System.setOut(printStream);
    print.pathSave();
    System.out.flush();
    System.setOut(prev);
    String expectedOutput = "Please enter in a name to save your portfolio as: \n";
    assertEquals(expectedOutput, outputStream.toString());
  }

  @Test
  public void currPrice() {
    PrintStream prev = System.out;
    System.setOut(printStream);
    print.currPrice("test1", "30");
    System.out.flush();
    System.setOut(prev);
    String expectedOutput = "The current stock price of test1 is $30\n";
    assertEquals(expectedOutput, outputStream.toString());
  }

  @Test
  public void selectPrice() {
    PrintStream prev = System.out;
    System.setOut(printStream);
    print.selectPrice();
    System.out.flush();
    System.setOut(prev);
    String expectedOutput = "Would you like to use this price or select from another "
            + "date? (current / another)\n";
    assertEquals(expectedOutput, outputStream.toString());
  }

  @Test
  public void stockDataErr() {
    PrintStream prev = System.out;
    System.setOut(printStream);
    print.stockDataErr("test", "2000");
    System.out.flush();
    System.setOut(prev);
    String expectedOutput = "Stock data doesn't exist for test on 2000"
            + ".\nChoose another date...\n";
    assertEquals(expectedOutput, outputStream.toString());
  }

  @Test
  public void price() {
    PrintStream prev = System.out;
    System.setOut(printStream);
    print.price("test", "2000", "30");
    System.out.flush();
    System.setOut(prev);
    String expectedOutput = "The stock price of test on 2000 is $30\n";
    assertEquals(expectedOutput, outputStream.toString());
  }

  @Test
  public void costBasis() {
    PrintStream prev = System.out;
    System.setOut(printStream);
    print.costBasis("2000", 30);
    System.out.flush();
    System.setOut(prev);
    String expectedOutput = "Cost basis of portfolio on 2000 is $30.0\n";
    assertEquals(expectedOutput, outputStream.toString());
  }

  @Test
  public void commission() {
    PrintStream prev = System.out;
    System.setOut(printStream);
    print.commission();
    System.out.flush();
    System.setOut(prev);
    String expectedOutput = "Enter commission fee... \n";
    assertEquals(expectedOutput, outputStream.toString());
  }

  @Test
  public void commissionInvalid() {
    PrintStream prev = System.out;
    System.setOut(printStream);
    print.commissionInvalid();
    System.out.flush();
    System.setOut(prev);
    String expectedOutput = "Commission fee must only include numbers.\n";
    assertEquals(expectedOutput, outputStream.toString());
  }
}