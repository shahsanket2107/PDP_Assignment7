package view;

/**
 * Implementation of the DisplayMenu interface.
 */
public class DisplayMenuImpl implements DisplayMenu {
  @Override
  public void displayMainScreen() {
    System.out.println("***Welcome to Portfolio Manager!***");
    System.out.println("Portfolio Manager allows you to create "
            + "and manage various stock portfolios.");
  }

  /**
   * Display the various options this program supports.
   */
  public void displayOptions() {
    System.out.println("Do you want to: ");
    System.out.printf("%5s %n", "1. Buy / sell shares of a stock");
    System.out.printf("%5s %n", "2. Create a Stock Portfolio");
    System.out.printf("%5s %n", "3. View the composition of a Portfolio");
    System.out.printf("%5s %n", "4. View the performance of a Portfolio");
    System.out.printf("%5s %n", "5. Determine the total value of a portfolio on a certain date");
    System.out.printf("%5s %n", "6. Determine the cost basis of a portfolio on a certain date");
    System.out.printf("%5s %n", "7. Load a Portfolio");
    System.out.printf("%5s %n", "8. Buy shares of a stock for an existing portfolio");
    System.out.printf("%5s %n", "9. Create a Portfolio with Start-to-Finish Dollar Cost Averaging");
    System.out.printf("%5s %n", "10. Modify a Portfolio with Dollar Cost Averaging");
    System.out.printf("%5s %n", "Please choose an option(1-10) and hit enter. Enter q to quit...");
  }

}
