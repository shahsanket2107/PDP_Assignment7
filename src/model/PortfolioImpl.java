package model;

import java.util.ArrayList;

/**
 * Implementation of the portfolio interface.
 */
public class PortfolioImpl implements Portfolio {

  private String name;
  ArrayList<InvestorImpl.InvestorStock> portfolio;

  /**
   * Constructor to initialize Portfolio.
   *
   * @param name the name of the portfolio
   */
  public PortfolioImpl(String name) {
    this.name = name;
    portfolio = new ArrayList<InvestorImpl.InvestorStock>();
  }

  /**
   * Add a stock to an Investor.
   *
   * @param s the Investor Stock to add to
   */
  public void addStock(InvestorImpl.InvestorStock s) {
    portfolio.add(s);
  }

  /**
   * Whether a portfolio has the given stock.
   *
   * @param stockName the name of the stock
   * @return true if it has the stock else false
   */
  public boolean containsStock(String stockName) {
    for (int i = 0; i < portfolio.size(); i++) {
      if (portfolio.get(i).name.equals(stockName)) {
        return true;
      }
    }
    return false;
  }

  /**
   * Get the number of shares for a stock.
   *
   * @param stockName the name of the stock
   * @return the number of shares for a stock
   */
  public double getShares(String stockName) {
    if (portfolio.isEmpty()) {
      return -1;
    }
    for (int i = 0; i < portfolio.size(); i++) {
      if (portfolio.get(i).name.equals(stockName)) {
        return portfolio.get(i).numShares;
      }
    }
    return -1;
  }

  /**
   * Examine the contents of a portfolio, including the date.
   *
   * @return the contents of a portfolio as a string
   */
  public String examine() {
    String result = "";
    if (portfolio.isEmpty()) {
      return result;
    }
    for (int i = 0; i < portfolio.size(); i++) {
      result += portfolio.get(i).name + " " + portfolio.get(i).datePurchased + " "
              + portfolio.get(i).priceWhenBought + " " + portfolio.get(i).numShares + " ";

    }
    return result;
  }

  /**
   * Examine the contents of a portfolio, no date.
   *
   * @return the contents of a portfolio as a string
   */
  public String examineNoDate() {
    String result = "";
    if (portfolio.isEmpty()) {
      return result;
    }
    for (int i = 0; i < portfolio.size(); i++) {
      result += portfolio.get(i).name + " "
              + portfolio.get(i).priceWhenBought + " " + portfolio.get(i).numShares + " ";

    }
    return result;
  }

  /**
   * Get name of portfolio.
   *
   * @return name of portfolio as a String.
   */
  public String getName() {
    return this.name;
  }

  /**
   * Get transaction fee from user.
   *
   * @param date the date to get the transactions on.
   * @return transaction fee.
   */
  public float getTransactionFee(String date) {
    int result = 0;
    String[] tokens = date.split("-");
    int monthCompare = Integer.valueOf(tokens[0]);
    int dayCompare = Integer.valueOf(tokens[1]);
    int yearCompare = Integer.valueOf(tokens[2]);
    for (int i = 0; i < portfolio.size(); i++) {
      tokens = portfolio.get(i).datePurchased.split("-");
      int month = Integer.valueOf(tokens[0]);
      int day = Integer.valueOf(tokens[1]);
      int year = Integer.valueOf(tokens[2]);
      if (year <= yearCompare && month <= monthCompare && day <= dayCompare) {
        result += portfolio.get(i).transactionFee;
      }
    }
    return result;
  }

  public ArrayList<InvestorImpl.InvestorStock> getList() {
    return portfolio;
  }

}
