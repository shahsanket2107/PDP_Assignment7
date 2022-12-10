package view;

import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertEquals;

/**
 * Display Menu Test Class.
 */
public class DisplayMenuTest {
  DisplayMenu menu;

  @Before
  public void setUp() {
    menu = new DisplayMenuImpl();
  }

  @Test
  public void testMainMenu() {
    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    PrintStream printStream = new PrintStream(outputStream);
    PrintStream prev = System.out;
    System.setOut(printStream);
    menu.displayMainScreen();
    System.out.flush();
    System.setOut(prev);
    String expectedOutput = "***Welcome to Portfolio Manager!***" + "\n"
            + "Portfolio Manager allows you to create and manage various stock portfolios." + "\n";
    assertEquals(expectedOutput, outputStream.toString());
  }

  @Test
  public void testOptions() {
    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    PrintStream printStream = new PrintStream(outputStream);
    PrintStream prev = System.out;
    System.setOut(printStream);
    menu.displayOptions();
    System.out.flush();
    System.setOut(prev);
    String expectedOutput = "Do you want to: " + "\n"
            + "1. Buy / sell shares of a stock " + "\n"
            + "2. Create a Stock Portfolio " + "\n"
            + "3. View the composition of a Portfolio " + "\n"
            + "4. View the performance of a Portfolio " + "\n"
            + "5. Determine the total value of a portfolio on a certain date " + "\n"
            + "6. Determine the cost basis of a portfolio on a certain date " + "\n"
            + "7. Load a Portfolio " + "\n"
            + "Please choose an option(1-7) and hit enter. Enter q to quit... " + "\n";
    assertEquals(expectedOutput, outputStream.toString());
  }
}
