package view;

import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Implements the print statement interface.
 */
public class PrintStatementImpl implements PrintStatement {

  private Scanner optionScanner;

  /**
   * Constructor.
   * Set the option scanner to a new Java scanner.
   */
  public PrintStatementImpl() {
    optionScanner = new Scanner(System.in);
  }

  /**
   * Operation to read the option provided by the user.
   *
   * @return the option chosen
   * @throws FileNotFoundException when an invalid file path is provided
   */
  public String readOption() {
    String option = optionScanner.nextLine();
    if (option.equals("q")) {
      System.exit(1);
    }
    return option;
  }

  public void chooseOption1() {
    System.out.println("Please select a valid option(1-9): ");
  }

  public void chooseOption2() {
    System.out.println("Please select a valid option(y/n): ");
  }

  public void chooseOption3() {
    System.out.println("Please select a valid option...");
  }

  public void chooseOption4() {
    System.out.println("Please select a valid option(buy/sell): ");
  }

  public void chooseOption5() {
    System.out.println("Please select a valid option(gui / text): ");
  }

  public void newStock() {
    System.out.println("Enter name of stock or enter q to exit...");
  }

  public void addStock() {
    System.out.println("Do you want add stock to the portfolio? (y/n)");
  }

  public void stocksNotFound() {
    System.out.println("No stocks found under investor profile.\n");
  }

  public void stocksDisplay(String stockNames) {
    System.out.println("Your stocks are:\n" + stockNames);
  }

  public void stockBuySell() {
    System.out.println("You will be charged a fixed commission fee for this purchase.");
    System.out.println("Would you like to buy or sell stock? (buy / sell)");
  }

  public void chooseStock() {
    System.out.println("Choose a stock to add to new portfolio...");
  }

  public void stockErr1(String stockName, String portfolioName) {
    System.out.println("Stock " + stockName + " already exists in portfolio "
            + portfolioName);
  }

  public void stockErr2(String stockName) {
    System.out.println("Stock " + stockName + " does not exist under investor profile");
  }

  public void stockErr3(String stockName, String n) {
    System.out.println("Investor does not own " + n + " shares of stock " + stockName + ".");
  }

  /**
   * Added in a stock to a portfolio successfully.
   *
   * @param stockName     name of stock
   * @param portfolioName name of portfolio
   */
  public void stockSuccess(String stockName, String portfolioName) {
    System.out.println("Stock " + stockName + " successfully added to portfolio "
            + portfolioName + ".\nWould you like to add another stock to "
            + portfolioName + "? (y/n)");
  }

  public void sellStock1() {
    System.out.println("Choose a stock to sell or enter q to exit...");
  }

  public void sellStock2() {
    System.out.println("Enter date of stock to sell...");
  }

  public void sellStock3() {
    System.out.println("Enter number of shares to sell...");
  }

  public void shares1() {
    System.out.println("Enter number of shares to buy or enter q to exit...");
  }

  public void shares2() {
    System.out.println("Enter number of shares to sell or enter q to exit...");
  }

  public void sharesErr1() {
    System.out.println("Number of shares must be a whole number containing only digits.");
  }

  public void sharesErr2() {
    System.out.println("Date entered does not match any stock data.");
  }

  public void sharesSuccess(String numShares, String stockName) {
    System.out.println("Successfully bought " + numShares + " shares of " + stockName);
  }

  public void portfolioNew() {
    System.out.println("Enter name for new portfolio...");
  }

  public void portfolio() {
    System.out.println("Enter name of portfolio...");
  }

  public void portfolioLoad() {
    System.out.println("Please enter in the name of the portfolio you want to load: ");
  }

  public void portfolioErr() {
    System.out.println("A portfolio with this name already exists. Please enter a valid name: ");
  }

  public void portfolioInvalid() {
    System.out.println("Please enter a valid portfolio: ");
  }

  public void portfolioCreateSuccess(String portfolioName) {
    System.out.println("New portfolio " + portfolioName
            + " has been created and saved to profile.");
  }

  public void portfolioLoadSuccess(String portfolioName) {
    System.out.println("Your portfolio " + portfolioName + " has been loaded in successfully.");
  }

  public void portfoliosNotFound() {
    System.out.println("No portfolios found under investor profile.");
  }

  public void portfolioNotFound(String portfolioName) {
    System.out.println("Portfolio " + portfolioName + " does not exist under investor "
            + "profile.");
  }

  public void displayPortfolios(String local, String saved) {
    System.out.println("Your portfolios are:\n" + local + saved
            + "\nChoose a portfolio or enter q to exit...");
  }

  public void value(String date, float value) {
    System.out.println("Value of portfolio on " + date + " is " + value);
  }

  public void portfolioSave() {
    System.out.println("Please enter in a name to save your portfolio as: ");
  }

  public void enterDate() {
    System.out.println("Enter in a date in the format of DD-MM-YYYY: ");
  }

  public void dateInvalid() {
    System.out.println("Invalid format. Enter in a date in the format of DD-MM-YYYY: ");
  }

  public void path() {
    System.out.println("Please enter in a valid path to load in your portfolio file: ");
  }

  public void pathInvalid() {
    System.out.println("That was an invalid path.");
  }

  public void pathSave() {
    System.out.println("Please enter in a name to save your portfolio as: ");
  }

  public void currPrice(String stockName, String currPrice) {
    System.out.println("The current stock price of " + stockName
            + " is $" + currPrice);
  }

  public void selectPrice() {
    System.out.println("Would you like to use this price or select from another "
            + "date? (current / another)");
  }

  public void stockDataErr(String stockName, String date) {
    System.out.println("Stock data doesn't exist for " + stockName + " on "
            + date + ".\nChoose another date...");
  }

  public void price(String stockName, String date, String price) {
    System.out.println("The stock price of " + stockName + " on "
            + date + " is $" + price);
  }

  public void costBasis(String date, float value) {
    System.out.println("Cost basis of portfolio on " + date + " is $" + value);
  }

  public void commission() {
    System.out.println("Enter commission fee... ");
  }

  public void commissionInvalid() {
    System.out.println("Commission fee must only include numbers.");
  }

  public void guiOrTextBased() {
    System.out.println("Would you like to use a GUI or text-based interface? (gui / text)...");
  }

  public void printOption(String option) {
    System.out.println("\nOption selected: " + option);
  }
}
