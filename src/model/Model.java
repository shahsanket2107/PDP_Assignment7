package model;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Map;

/**
 * Model interface.
 * Entry-point into the model.
 */
public interface Model {

  /**
   * Operation to get the price of a stock.
   *
   * @param stockName the name of the stock
   * @return the current price of the stock
   * @throws FileNotFoundException when an invalid file path is provided
   */
  String[] operationGetPrice(String stockName) throws FileNotFoundException;

  /**
   * Buy shares of a stock.
   *
   * @param stockName the stock to buy shares for
   * @param numShares the number of shares to buy
   * @param price     the price to buy the stock at
   * @throws FileNotFoundException when the file path is invalid
   */
  void buyShares(String stockName, String numShares, String datePurchased, float price)
          throws FileNotFoundException;

  /**
   * Get a portfolio.
   *
   * @param portfolioName the portfolio to get
   * @return a portfolio
   */
  Portfolio getPortfolio(String portfolioName);

  /**
   * Create a new portfolio.
   *
   * @param portfolioName the name of the portfolio
   */
  void createPortfolio(String portfolioName);

  /**
   * Get all the portfolios of this investor.
   *
   * @return a string with the names of all the portfolios under this investor
   */
  String getAllPortfolioNames();

  /**
   * Operation to add a stock to portfolio.
   *
   * @param portfolioName the name of the portfolio
   * @throws FileNotFoundException when an invalid file path is provided
   */
  void addStock(String portfolioName);

  /**
   * Operation to add a stock to portfolio.
   *
   * @param portfolioName the name of the portfolio
   * @throws FileNotFoundException when an invalid file path is provided
   */
  boolean addStock(String portfolioName, String stockName);

  /**
   * Operation to save a portfolio as a file.
   *
   * @param portfolioName the name of the portfolio to save
   */
  void savePortfolio(String portfolioName);

  /**
   * Operation to save a portfolio as a file.
   *
   * @param portfolioName the name of the portfolio to save
   */
  void savePortfolio(String portfolioName, String portfolioPath);

  /**
   * Operation to get the user to enter a valid date.
   *
   * @return the validated date
   */
  String getDate();

  /**
   * Return string containing all stock names for this investor.
   *
   * @return string containing all stock names for this investor
   */
  String getAllStockNames();


  /**
   * Operation to buy a stock.
   *
   * @throws FileNotFoundException when an invalid file path is provided.
   */
  void buyStock() throws FileNotFoundException;

  /**
   * Sell shares of a stock.
   */
  void sellStock() throws FileNotFoundException;

  /**
   * Sell shares of a stock on a date.
   *
   * @param stockName the name of the stock to sell
   * @param date      the date to sell on
   * @param numShares the number of shares to sell
   * @return 1 if sold, -1 otherwise
   * @throws FileNotFoundException if stock file doesn't exist
   */
  int sellStock(String stockName, String date, String numShares) throws FileNotFoundException;

  /**
   * Returns the price of given stock at a date.
   *
   * @param stockName the stock name
   * @param date      the date to get the price at
   * @return the price at the date
   * @throws FileNotFoundException when invalid file path
   */
  String getPrice(String stockName, String date) throws FileNotFoundException;

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
  float getTotalValueOfPortfolio(boolean portfolioFilesContains, boolean investorContains,
                                 Map portfolioFiles, String portfolioName, String date)
          throws FileNotFoundException;

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
  float getCostBasis(String portfolioName, String date, Map portfolioFiles,
                     boolean loadedPortfolio, String totalFees) throws FileNotFoundException;

  /**
   * Get the price of a stock on a given date, using user input.
   *
   * @param stockName the name of the stock
   * @param date      the date to get the stock price on
   * @return the price as a string
   * @throws FileNotFoundException if the stock file doesn't exist
   */
  String getPriceOnDate(String stockName, String date) throws FileNotFoundException;

  /**
   * Buy a number of shares on a specified date(no fractional shares).
   *
   * @param numShares the number of shares to buy
   * @param stockName the name of the stock to buy
   * @param date      the date to buy the stock on
   * @param price     the price of the stock
   * @return 1 if bought successfully, -1 otherwise
   * @throws FileNotFoundException
   */
  int buyNumberOfShares(String numShares, String stockName, String date, String price) throws FileNotFoundException;

  /**
   * Buy shares -- text/gui
   *
   * @param stockName     the name of the stock
   * @param numShares     the number of shares to buy
   * @param datePurchased the date the shares were purchased
   * @param price         price of the stock
   * @param text          if the view is text-based or not(true if it is, false otherwise)
   * @throws FileNotFoundException
   */
  void buyShares(String stockName, String numShares, String datePurchased, float price, boolean text)
          throws FileNotFoundException;

  /**
   * Parse the contents of a portfolio.
   *
   * @param contents the contents of a portfolio as a string
   * @return an array representing the contents
   */
  String[][] parseContents(String contents);

  /**
   * Load stocks corresponding to a portfolio.
   *
   * @param portfolioName the name of the portfolio
   * @param contents      the contents of the portfolio
   * @throws FileNotFoundException if the portfolio does not exist
   */
  void loadStock(String portfolioName, ArrayList<String> contents) throws FileNotFoundException;

  /**
   * Buy dollar-cost averaged stocks.
   *
   * @param portfolioName     the name of the portfolio
   * @param stockNames        the names of the stocks to purchase.
   * @param amount            the amount to be averaged and purchased.
   * @param weights           the weights corresponding to each transaction
   * @param timeRange         the time range for the purchases
   * @param frequency         the frequency at which to buy the stocks
   * @param amountOfFrequency the amount of frequency to buy the stocks
   * @param text              true if interface is text-based
   */
  void buyMultipleStocks(String portfolioName, String[] stockNames, String amount, String[] weights,
                         String[] timeRange, String frequency, String amountOfFrequency, boolean text)
          throws FileNotFoundException;

  /**
   * Modify a portfolio using dollar cost average.
   *
   * @param portfolioName the name of the portfolio
   * @param stockNames    the names of the stocks to purchase.
   * @param amount        the amount to be averaged and purchased.
   * @param weights       the weights corresponding to each transaction
   * @param text          true if interface is text-based
   */
  void modifyPortfolioDollarCost(String portfolioName, String[] stockNames, String amount,
                                 String[] weights, String date, boolean text) throws FileNotFoundException;

  /**
   * Buy stocks for existing portfolio.
   *
   * @param portfolioName name of the portfolio
   */
  void buyStock(String portfolioName) throws FileNotFoundException;

  /**
   * Operation to add a stock to portfolio.
   *
   * @param portfolioName the name of the portfolio
   * @throws FileNotFoundException when an invalid file path is provided
   */
  void addStockModified(String portfolioName, String stockName);

  void reBalance(String portfolioName, String amount,
                 String[] stocks, String[] weights, String date);
}
