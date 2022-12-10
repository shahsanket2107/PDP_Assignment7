package model;

import java.io.FileNotFoundException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Map;

import view.DisplayPortfolioImpl;
import view.PrintStatement;
import view.PrintStatementImpl;

/**
 * Implementation of the model interface.
 */
public class ModelImpl implements Model {

  private Investor investor;
  private PrintStatement print;
  private DisplayPortfolioImpl display;

  /**
   * Constructor to initialize the model.
   * Set the investor, print, and display modules.
   */
  public ModelImpl() {
    investor = new InvestorImpl();
    print = new PrintStatementImpl();
    display = new DisplayPortfolioImpl();
  }

  /**
   * Operation to get the price of a stock.
   *
   * @param stockName the name of the stock
   * @return the current price of the stock
   * @throws FileNotFoundException when an invalid file path is provided
   */
  public String[] operationGetPrice(String stockName) throws FileNotFoundException {
    String[] result = {"-", "-"};
    String currPrice = String.format("%.02f", Double.valueOf(investor.getPrice(stockName)));
    if (!currPrice.equals("-1.00")) {
      print.currPrice(stockName, investor.getPrice(stockName));
      print.selectPrice();
      String option = print.readOption();
      while (!option.equals("current") || !option.equals("another")) {
        if (option.equals("current")) {
          print.currPrice(stockName, investor.getPrice(stockName));
          result[1] = String.format("%.02f", Double.valueOf(currPrice));
          return result;
        } else if (option.equals("another")) {
          return investor.loadPastData(stockName);
        } else if (option.equals("q")) {
          System.exit(1);
        } else {
          print.chooseOption3();
          print.selectPrice();
          option = print.readOption();
        }

      }
    } else {
      return investor.loadPastData(stockName);
    }
    return result;
  }

  /**
   * Buy shares of a stock.
   *
   * @param stockName the stock to buy shares for
   * @param numShares the number of shares to buy
   * @param price     the price to buy the stock at
   * @throws FileNotFoundException when the file path is invalid
   */
  public void buyShares(String stockName, String numShares, String datePurchased, float price)
          throws FileNotFoundException {
    investor.buyShares(stockName, numShares, datePurchased, price);
  }

  /**
   * Buy shares of a stock.
   *
   * @param stockName the stock to buy shares for
   * @param numShares the number of shares to buy
   * @param price     the price to buy the stock at
   * @param text      boolean if interface is text-based
   * @throws FileNotFoundException when the file path is invalid
   */
  public void buyShares(String stockName, String numShares, String datePurchased, float price, boolean text)
          throws FileNotFoundException {
    investor.buyShares(stockName, numShares, datePurchased, price, text);
  }

  /**
   * Get a portfolio.
   *
   * @param portfolioName the portfolio to get
   * @return a portfolio
   */
  public Portfolio getPortfolio(String portfolioName) {
    return investor.getPortfolio(portfolioName);
  }

  /**
   * Create a new portfolio.
   *
   * @param portfolioName the name of the portfolio
   */
  public void createPortfolio(String portfolioName) {
    investor.createPortfolio(portfolioName);
  }

  /**
   * Get all the portfolios of this investor.
   *
   * @return a string with the names of all the portfolios under this investor
   */
  public String getAllPortfolioNames() {
    return investor.getAllPortfolioNames();
  }

  public void addMultipleStocks(String portfolioName) {
    String option = print.readOption();
    String allStocks = investor.getAllStockNames();

    if (allStocks.equals("")) {
      print.stocksNotFound();
    } else {

    }
  }

  /**
   * Operation to add a stock to portfolio.
   *
   * @param portfolioName the name of the portfolio
   * @throws FileNotFoundException when an invalid file path is provided
   */
  public void addStock(String portfolioName) {
    print.addStock();
    String option = print.readOption();
    while (!(option.equals("y")) && !(option.equals("n"))) {
      print.chooseOption2();
      option = print.readOption();
    }
    if (option.equals("y")) {
      String allStocks = investor.getAllStockNames();
      if (allStocks.equals("")) {
        print.stocksNotFound();
      } else {
        while (option.equals("y")) {
          print.stocksDisplay(investor.getAllStockNames());
          print.chooseStock();
          option = print.readOption();
          if (allStocks.contains(option)) {
            if (investor.containsStock(portfolioName, option)) {
              print.stockErr1(option, portfolioName);
              addStock(portfolioName);
            } else {
              investor.addStock(portfolioName, option);
              print.stockSuccess(option, portfolioName);
              option = print.readOption();
            }
          } else {
            print.stockErr2(option);
            option = "y";
          }
        }
      }
    }
  }

  /**
   * Operation to add a stock to portfolio.
   *
   * @param portfolioName the name of the portfolio
   * @throws FileNotFoundException when an invalid file path is provided
   */
  public boolean addStock(String portfolioName, String stockName) {
    if (investor.containsStock(portfolioName, stockName)) {
      return false;
    } else {
      investor.addStock(portfolioName, stockName);
      return true;
    }
  }

  /**
   * Operation to save a portfolio as a file.
   *
   * @param portfolioName the name of the portfolio to save
   */
  public void savePortfolio(String portfolioName) {
    ParseFile newPortfolioFile;
    boolean validPath = false;
    while (!validPath) {
      print.portfolioSave();
      String portfolioPath = print.readOption();
      newPortfolioFile = new ParseFileImpl();
      newPortfolioFile.saveFile(portfolioPath, getPortfolio(portfolioName));

      validPath = true;
    }
  }

  /**
   * Operation to save a portfolio as a file.
   *
   * @param portfolioName the name of the portfolio to save
   */
  public void savePortfolio(String portfolioName, String portfolioPath) {
    ParseFile newPortfolioFile;
    boolean validPath = false;
    while (!validPath) {
      newPortfolioFile = new ParseFileImpl();
      newPortfolioFile.saveFile(portfolioPath, getPortfolio(portfolioName));

      validPath = true;
    }
  }

  /**
   * Operation to get the user to enter a valid date.
   *
   * @return the validated date
   */
  public String getDate() {
    return investor.getDate();
  }

  /**
   * Return string containing all stock names for this investor.
   *
   * @return string containing all stock names for this investor
   */
  public String getAllStockNames() {
    return investor.getAllStockNames();
  }

  /**
   * Operation to buy a stock.
   *
   * @throws FileNotFoundException when an invalid file path is provided.
   */
  public void buyStock() throws FileNotFoundException {
    print.newStock();
    String stockName = print.readOption();
    ParseFile newPortfolioFile = new ParseFileImpl();
    if (!newPortfolioFile.validStock(stockName)) {
      buyStock();
    } else {
      String[] datePrice = operationGetPrice(stockName);
      String date = datePrice[0];
      String price = datePrice[1];
      print.shares1();
      String option = print.readOption();
      while (!option.matches("[0-9]+") || Float.valueOf(option) % 1 != 0) {
        print.sharesErr1();
        print.shares1();
        option = print.readOption();
      }
      buyShares(stockName, option, date, Float.valueOf(price));
      print.sharesSuccess(option, stockName);
    }
  }

  public String getPriceOnDate(String stockName, String date) throws FileNotFoundException {
    print.newStock();
    print.printOption(stockName);

    return investor.getPrice(stockName, date);
  }

  public int buyNumberOfShares(String numShares, String stockName, String date, String price) throws FileNotFoundException {
    if (!numShares.matches("[0-9]+") || Float.valueOf(numShares) % 1 != 0) {
      return -1;
    }
    buyShares(stockName, numShares, date, Float.valueOf(price));
    return 1;
  }

  public void getStockPrice(String stockName) throws FileNotFoundException {
    ParseFile newPortfolioFile = new ParseFileImpl();
    if (!newPortfolioFile.validStock(stockName)) {
      buyStock();
    } else {
      String[] datePrice = operationGetPrice(stockName);
      String date = datePrice[0];
      String price = datePrice[1];
    }
  }

  /**
   * Sell shares of a stock.
   */
  public void sellStock() throws FileNotFoundException {
    if (investor.getAllStocks().equals("")) {
      print.stocksNotFound();
      return;
    }
    display.displayContents(investor.examine(investor.getAllStocks()));
    print.sellStock1();
    String stockName = print.readOption();
    if (!investor.containsStock(stockName)) {
      print.stockErr2(stockName);
      return;
    }
    investor.sellStock(stockName);
  }

  /**
   * Sell shares of a stock.
   */
  public void sellStock(String stockName, String date) throws FileNotFoundException {
    investor.sellStock(stockName);
  }

  public int sellStock(String stockName, String date, String numShares) throws FileNotFoundException {
    return investor.sellStock(stockName, date, numShares);
  }

  /**
   * Get the price of a stock on a specified date.
   *
   * @param stockName the stock name
   * @param date      the date to get the price at
   * @return the price of the stock on the specified date
   * @throws FileNotFoundException if the stock does not exist
   */
  @Override
  public String getPrice(String stockName, String date) throws FileNotFoundException {
    return investor.getPrice(stockName, date);
  }

  /**
   * Get total value of portfolio.
   *
   * @param portfolioFilesContains boolean for if the portfolio is stored in file.
   * @param investorContains       boolean for if the portfolio is stored locally.
   * @param portfolioFiles         file collection with portfolio information.
   * @param portfolioName          name of portfolio.
   * @param date                   date from which to get value of.
   * @return total value as a float.
   * @throws FileNotFoundException if file storing portfolio names is not found.
   */
  public float getTotalValueOfPortfolio(boolean portfolioFilesContains, boolean investorContains,
                                        Map portfolioFiles, String portfolioName, String date)
          throws FileNotFoundException {
    float totalValueOfPortfolio = 0;
    if (portfolioFilesContains) {
      ArrayList<String> contents = (ArrayList<String>) portfolioFiles.get(portfolioName);
      if (contents.size() == 0) {
        return 0;
      }
      for (int i = 0; i < contents.size(); i++) {
        String[] tokens = contents.get(i).split(",");
        String currStock = tokens[0];
        int currNumShares = Integer.parseInt(tokens[3]);
        float currPrice = Float.parseFloat(ParseFileImpl.getPrice(currStock, date));
        totalValueOfPortfolio += (currPrice * currNumShares);
      }
    }
    if (investorContains) {
      return getTotalValueOfPortfolioInvestor(portfolioName, date);

    }
    return totalValueOfPortfolio;
  }

  /**
   * Get value of portfolio for locally stored portfolios.
   *
   * @param portfolioName name of portfolio.
   * @param date          date from which to get value of.
   * @return total value as a float.
   * @throws FileNotFoundException if file storing portfolio names is not found.
   */
  private float getTotalValueOfPortfolioInvestor(String portfolioName, String date)
          throws FileNotFoundException {
    float totalValueOfPortfolio = 0;
    String contents = getPortfolio(portfolioName).examineNoDate();
    String[] contentsArr = contents.trim().split("\\s+");
    if (contentsArr.length == 1) {
      return 0;
    }
    for (int i = 0; i < contentsArr.length; i++) {
      String currStock = contentsArr[0];
      int currNumShares = Integer.parseInt(contentsArr[2]);
      float currPrice = Float.parseFloat(ParseFileImpl.getPrice(currStock, date));
      totalValueOfPortfolio += (currPrice * currNumShares);
    }
    return totalValueOfPortfolio;
  }

  /**
   * Get total purchases made by investor in a portfolio before a given date.
   *
   * @param portfolioName portfolio from which to calculate.
   * @param date          user input date from which to calculate purchases made before.
   * @return total amount of purchases as a float.
   */
  private float getTotalInvestmentsPortfolio(String portfolioName, String date) {
    float totalInvestments = 0;
    String contents = getPortfolio(portfolioName).examine();
    String[] contentsArr = contents.trim().split("\\s+");
    for (int i = 0; i < contentsArr.length; i++) {
      float priceWhenBought = Float.parseFloat(contentsArr[2]);
      double numShares = Integer.parseInt(contentsArr[3]);
      totalInvestments += (priceWhenBought * numShares);
    }
    return totalInvestments;
  }

  /**
   * Calculate cost basis given portfolio name and date.
   * Includes all the purchases made in that portfolio till that date.
   *
   * @param portfolioName   portfolio from which to calculate.
   * @param date            user input date from which to calculate purchases made before.
   * @param portfolioFiles  the loaded in portfolios
   * @param loadedPortfolio if the portfolio is a loaded portoflio
   * @param totalFees       the total commission fees for a loaded in portfolio
   * @return cost basis as a float.
   */
  public float getCostBasis(String portfolioName, String date, Map portfolioFiles,
                            boolean loadedPortfolio, String totalFees) {
    if (loadedPortfolio) {
      try {
        return getTotalValueOfPortfolio(true, false,
                portfolioFiles, portfolioName, date) + Float.parseFloat(totalFees);
      } catch (Exception e) {
        e.printStackTrace();
      }

    }
    return getTotalInvestmentsPortfolio(portfolioName, date)
            + investor.getPortfolio(portfolioName).getTransactionFee(date);
  }

  public String[][] parseContents(String contents) {
    String[] splitContents = contents.trim().split("\\s+");
    String[][] result = new String[splitContents.length / 4][];
    int j = 0;
    for (int i = 0; i < result.length; i++) {
      String[] data = new String[4];
      data[0] = splitContents[4 * i];
      data[1] = splitContents[4 * i + 1];
      data[2] = splitContents[4 * i + 2];
      data[3] = splitContents[4 * i + 3];
      result[j] = data;
      j++;
    }
    return result;
  }

  public void loadStock(String portfolioName, ArrayList<String> contents) throws FileNotFoundException {
    investor.loadStock(portfolioName, contents);
  }

  @Override
  public void buyMultipleStocks(String portfolioName, String[] stockNames, String amount, String[] weights,
                                String[] timeRange, String frequency, String amountOfFrequency, boolean text)
          throws FileNotFoundException {
    // convert time range to days
    String[] startDate = timeRange[0].split("-");
    String yearsStart = startDate[2];
    String monthsStart = startDate[1];
    String daysStart = startDate[0];
    String modifiedStartDate = yearsStart + "-" + monthsStart + "-" + daysStart;
    String yearsEnd = "";
    String monthsEnd = "";
    String daysEnd = "";
    if (timeRange.length == 2) {
      String[] endDate = timeRange[1].split("-");
      yearsEnd = endDate[2];
      monthsEnd = endDate[1];
      daysEnd = endDate[0];
    } else {
      yearsEnd = "2022";
      monthsEnd = "10";
      daysEnd = "24";
    }

    String modifiedEndDate = yearsEnd + "-" + monthsEnd + "-" + daysEnd;

    long numDaysBetween = ChronoUnit.DAYS.between(LocalDate.parse(modifiedStartDate),
            LocalDate.parse(modifiedEndDate));

    int amtFrequencyDays = 0;
    // convert frequency to days
    if (frequency.equals("months")) {
      amtFrequencyDays = 30 * Integer.parseInt(amountOfFrequency);
    } else if (frequency.equals("years")) {
      amtFrequencyDays = 365 * Integer.parseInt(amountOfFrequency);
    } else {
      amtFrequencyDays = Integer.parseInt(amountOfFrequency);
    }

    int numTimesToPurchase = (int) (numDaysBetween / amtFrequencyDays);

    String result;
    if (text) {
      result = getCommission();
    } else {
      result = "0";
    }
    for (String stockName : stockNames) {
      LocalDate date = LocalDate.parse(modifiedStartDate);
      for (int j = 0; j < numTimesToPurchase; j++) {
        if (j != 0) {
          date = date.plusDays(amtFrequencyDays);
        }
        String tmpDate = date.toString();
        String[] dateTokens = tmpDate.split("-");
        String dateFormatted = dateTokens[2] + "-" + dateTokens[1] + "-" + dateTokens[0];
        String price = investor.getPrice(stockName, dateFormatted);
        float amtForStock = Float.parseFloat(amount) * (Float.parseFloat(weights[0]) / 100);
        if (price.equals("-1")) {
          price = "1";
        }
        double numShares = amtForStock / Float.parseFloat(price);
        // buy shares of stock
        buyShares(stockName, String.valueOf(numShares), dateFormatted, Float.parseFloat(price), Float.parseFloat(result));
        // add the stock to portfolio
        investor.addStock(portfolioName, stockName);
      }

    }
  }

  @Override
  public void modifyPortfolioDollarCost(String portfolioName, String[] stockNames,
                                        String amount, String[] weights, String date,
                                        boolean text) throws FileNotFoundException {
    String result;
    if (text) {
      result = getCommission();
    } else {
      result = "0";
    }

    for (String stockName : stockNames) {
      String price = investor.getPrice(stockName, date);
      float amtForStock = Float.parseFloat(amount) * (Float.parseFloat(weights[0]) / 100);
      if (price.equals("-1")) {
        price = "1";
      }
      double numShares = amtForStock / Float.parseFloat(price);
      // buy shares of stock
      buyShares(stockName, String.valueOf(numShares), date, Float.parseFloat(price), Float.parseFloat(result));
      // add the stock to portfolio
      investor.addStock(portfolioName, stockName);

    }
  }

  private String getCommission() {
    String result = "";
    float m = -1;
    while (m < 0) {
      print.commission();
      result = print.readOption();
      try {
        m = Float.parseFloat(result);
      } catch (NumberFormatException e) {
        print.commissionInvalid();
        m = -1;
      }

    }

    return result;
  }

  private void buyShares(String stockName, String numShares, String datePurchased, float price, float fee)
          throws FileNotFoundException {
    investor.buyShares(stockName, numShares, datePurchased, price, fee);

  }

}
