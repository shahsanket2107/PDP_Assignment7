package model;

import java.util.ArrayList;

/**
 * This interface represents a portfolio.
 * Stocks can be added to this portfolio, and you can examine a portfolio.
 */
public interface Portfolio {
  /**
   * Add a stock to an Investor.
   *
   * @param s the Investor Stock to add to
   */
  void addStock(InvestorImpl.InvestorStock s);

  /**
   * Get the number of shares for a stock.
   *
   * @param stockName the name of the stock
   * @return the number of shares for a stock
   */
  double getShares(String stockName);

  /**
   * Examine the contents of a portfolio.
   *
   * @return the contents of a portfolio as a string
   */
  String examine();

  /**
   * Examine the contents of a portfolio, no date.
   *
   * @return the contents of a portfolio as a string
   */
  String examineNoDate();

  /**
   * Whether a portfolio has the given stock.
   *
   * @param stockName the name of the stock
   * @return true if it has the stock else false
   */
  boolean containsStock(String stockName);

  /**
   * Get name of portfolio.
   *
   * @return name of portfolio as a String.
   */
  String getName();

  /**
   * Get transaction fee for a date.
   *
   * @param date the date to get the transactions on
   * @return the number of transactions on the date
   */
  float getTransactionFee(String date);

  /**
   * Get a list of the stocks under an investor.
   *
   * @return the list of stocks under the investor
   */
  ArrayList<InvestorImpl.InvestorStock> getList();

}
