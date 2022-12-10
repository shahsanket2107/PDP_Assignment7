package model;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * This interface represents an investor.
 * An investor can create a portfolio and buy shares of a stock.
 */
public interface Investor {
  /**
   * Get the number of shares for a stock.
   *
   * @param stockName the name of the stock to get the number of shares for
   * @return the number of shares for the stock
   */
  float getShares(String stockName);

  /**
   * Create a new portfolio.
   *
   * @param portfolioName the name of the portfolio
   */
  void createPortfolio(String portfolioName);

  /**
   * Get a portfolio.
   *
   * @param portfolioName the portfolio to get
   * @return a portfolio
   */
  Portfolio getPortfolio(String portfolioName);

  /**
   * Buy shares of a stock.
   *
   * @param stockName     the stock to buy shares for
   * @param numShares     the number of shares to buy
   * @param datePurchased the date the stock was purchased
   * @param price         the price to buy the stock at
   * @throws FileNotFoundException when the file path is invalid
   */
  void buyShares(String stockName, String numShares,
                 String datePurchased, float price, boolean text) throws FileNotFoundException;

  void buyShares(String stockName, String numShares,
                 String datePurchased, float price) throws FileNotFoundException;

  /**
   * Add a stock to a portfolio.
   *
   * @param portfolioName the portfolio to add a stock to
   * @param stockName     the name of the stock
   */
  void addStock(String portfolioName, String stockName);

  /**
   * Sell shares of a stock.
   *
   * @param stockName the name of the stock.
   */
  void sellStock(String stockName) throws FileNotFoundException;

  /**
   * Sell shares of a stock.
   *
   * @param stockName the name of the stock.
   */
  int sellStock(String stockName, String date, String numShares) throws FileNotFoundException;

  /**
   * Remove stock from all portfolios containing that stock.
   *
   * @param stockName name of stock to be removed.
   */
  void removeStock(String stockName);

  /**
   * Operation to get the user to enter a valid date.
   *
   * @return the validated date
   */
  String getDate();

  /**
   * If a portfolio contains the given stock.
   *
   * @param portfolioName the name of the portfolio
   * @param stockName     the name of the stock
   * @return true if the portfolio contains the stock else false
   */
  boolean containsStock(String portfolioName, String stockName);

  boolean containsStock(String stockName);

  /**
   * Get all the portfolios of this investor.
   *
   * @return a string with the names of all the portfolios under this investor
   */
  String getAllPortfolioNames();

  void buyShares(String stockName, String numShares, String datePurchased, float price, float fee)
          throws FileNotFoundException;

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
   * Returns the price of given stock at current date.
   *
   * @param stockName the stock name
   * @return the price of the given stock
   * @throws FileNotFoundException when invalid file path
   */
  String getPrice(String stockName) throws FileNotFoundException;

  /**
   * Return string containing all stock names for this investor.
   *
   * @return string containing all stock names for this investor
   */
  String getAllStockNames();

  /**
   * Get iterator of all stocks of which the investor owns shares.
   *
   * @return iterator of all InvestorStock objects.
   */
  Iterator<InvestorImpl.InvestorStock> getAllStocks();

  String examine(Iterator<InvestorImpl.InvestorStock> list);

  /**
   * Boolean if portfolio was created locally or not.
   *
   * @return true if portfolio was created locally, false otherwise.
   */
  boolean getLocal();

  float getTransactionFee();

  /**
   * Load the past data of a stock and return the price accordingly.
   *
   * @param stockName the name of stock
   * @return the price of the stock
   * @throws FileNotFoundException when an invalid file path is provided
   */
  String[] loadPastData(String stockName) throws FileNotFoundException;

  /**
   * Check if date formatting is correct.
   *
   * @param date the date to check
   * @return 1 if it matches, -1 otherwise
   */
  int validDate(String date);

  /**
   * Boolean if a string contains only digits.
   *
   * @param s String to see if it contains digits.
   * @return true if s contains only digits, false otherwise.
   */
  boolean containsDigits(String s);

  /**
   * Load stocks corresponding to a portfolio.
   *
   * @param portfolioName the name of the portfolio
   * @param contents      the contents of the portfolio
   * @throws FileNotFoundException if the portfolio does not exist
   */
  void loadStock(String portfolioName, ArrayList<String> contents) throws FileNotFoundException;


}
