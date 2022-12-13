package view;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import javax.swing.JFrame;
import model.Model;

/**
 * This interface represents a view.
 */
public interface View {

  /**
   * Get option from user.
   *
   * @return the option
   * @throws InterruptedException if option == 0(sleep)
   */
  int getOption() throws InterruptedException;

  /**
   * Run the GUI.
   */
  void runGUI();

  /**
   * Return the frame.
   *
   * @return
   */
  JFrame frame();

  /**
   * Buy and sell stock.
   *
   * @param allStocks string
   */
  void buySellStock(String allStocks);

  /**
   * Buy and sell option.
   *
   * @return buy or sell
   * @throws InterruptedException while thread sleeping/waiting
   */
  String buySellOption() throws InterruptedException;

  /**
   * Gets new portfolio.
   *
   * @param portfolios portfolios
   * @return new portfolios
   * @throws InterruptedException on interruption
   */

  String getPortfolioNew(String portfolios) throws InterruptedException;

  /**
   * Get the amount to invest.
   *
   * @return the amount to invest
   * @throws InterruptedException while thread sleeping/waiting
   */
  String getAmountInvest() throws InterruptedException;

  /**
   * Get the portfolio name.
   *
   * @param portfolios available portfolio names
   * @return the portfolio name
   * @throws InterruptedException while thread sleeping/waiting
   */
  String getPortfolio(String portfolios) throws InterruptedException;

  /**
   * Get the stock from string of purchased stocks.
   *
   * @param list list of stocks
   * @return name of stock
   * @throws InterruptedException on interruption
   */
  String getStock(ArrayList<String> list) throws InterruptedException;


  /**
   * Get the stock from string of purchased stocks.
   *
   * @param stocks a string containing all the stocks
   * @return the name of the stock
   * @throws InterruptedException while thread sleeping/waiting
   */
  String getStock(String stocks) throws InterruptedException;

  /**
   * Return stocks chosen by user.
   *
   * @param list list of stocks
   * @return the stocks chosen as an array
   * @throws InterruptedException while thread sleeping/waiting
   */
  String[] getStocks(ArrayList<String> list) throws InterruptedException;

  /**
   * If a stock has been selected.
   *
   * @return true if the stock has been selected, false otherwise
   * @throws InterruptedException while thread sleeping/waiting
   */
  boolean addStock() throws InterruptedException;

  /**
   * Get price and date for stock.
   *
   * @param stockName stock name
   * @param model     the model
   * @return the price and date for stock as an array
   * @throws InterruptedException  while thread sleeping/waiting
   * @throws FileNotFoundException if stock file not found
   */
  String[] getPriceDate(String stockName, Model model)
      throws InterruptedException, FileNotFoundException;

  /**
   * Get number of shares for stock.
   *
   * @param stockName name of the stock
   * @param date      date purchased.
   * @param price     price of the stock on the date
   * @return the number of shares
   * @throws InterruptedException while thread sleeping/waiting
   */
  String getNumShares(String stockName, String date, String price) throws InterruptedException;

  /**
   * Get the time range.
   *
   * @return the time-range
   * @throws InterruptedException while thread sleeping/waiting
   */
  String getTimeRange() throws InterruptedException;

  /**
   * Get the frequency at which to purchase the stocks at.
   *
   * @return the frequency at which to purchase the stocks at.
   * @throws InterruptedException while thread sleeping/waiting
   */
  String getFrequency() throws InterruptedException;

  /**
   * Get the amount for a frequency(30,2,15,etc).
   *
   * @return the amount for a frequency
   * @throws InterruptedException while thread sleeping/waiting
   */
  String getAmountFrequency() throws InterruptedException;

  /**
   * Sell stock.
   *
   * @param stockName the name of the stock
   * @param model     the model
   * @return date, price, number of shares sold as an array
   * @throws InterruptedException  while thread sleeping/waiting
   * @throws FileNotFoundException stock file doesn't exist
   */
  String[] sellStock(String stockName, Model model)
      throws InterruptedException, FileNotFoundException;

  /**
   * Get the path.
   *
   * @return the file path as a string.
   * @throws InterruptedException while thread sleeping/waiting
   */
  String getPath() throws InterruptedException;

  /**
   * View composition of a portfolio in table format.
   *
   * @param portfolioName name of portfolio.
   * @param rowData       row data.
   * @param columnNames   names of columns.
   */
  void viewComposition(String portfolioName, Object[][] rowData, Object[] columnNames)
      throws InterruptedException;

  /**
   * Get the correct option for the composition.
   *
   * @return the composition
   * @throws InterruptedException if incorrect composition
   */
  String compositionOption() throws InterruptedException;

  /**
   * View the portfolio performance for a year.
   *
   * @param portfolio the name of the portfolio
   * @param names     the names of the stocks
   * @param data      the data needed
   * @throws InterruptedException if invalid portfolio
   */
  void portfolioPerformanceYear(String portfolio, String[] names, String[] data)
      throws InterruptedException;

  /**
   * View the portfolio performance for a range of months(MM-MM).
   *
   * @param portfolio       the name of the portfolio
   * @param timeRangeMonths the time-range(MM-MM)
   * @param names           the names of the stocks
   * @param data            the data needed
   * @throws InterruptedException if invalid portfolio
   */
  void portfolioPerformanceMonths(String portfolio, String timeRangeMonths,
      String[] names, String[] data) throws InterruptedException;

  /**
   * View the portfolio performance for a month(MM).
   *
   * @param portfolio the name of the portfolio
   * @param month     the time-range(MM)
   * @param names     the names of the stocks
   * @param data      the data needed
   * @throws InterruptedException if invalid portfolio
   */
  void portfolioPerformanceMonth(String portfolio, String month,
      String[] names, String[] data) throws InterruptedException;

  /**
   * Get the range in months.
   *
   * @return the month
   * @throws InterruptedException if invalid month
   */
  String getMonthRange() throws InterruptedException;

  /**
   * Get the month.
   *
   * @return the month
   * @throws InterruptedException if invalid month
   */
  String getMonth() throws InterruptedException;

  /**
   * Get the date.
   *
   * @return the date
   * @throws InterruptedException if invalid date
   */
  String getDate() throws InterruptedException;

  /**
   * Get the value of a portfolio.
   *
   * @param portfolioName the name of the portfolio
   * @param date          the date to get value on
   * @param value         the value
   * @throws InterruptedException if invalid portfolio.
   */
  void valueOfPortfolio(String portfolioName, String date, float value) throws InterruptedException;

  /**
   * Get the cost basis for a portfolio.
   *
   * @param portfolioName the name of the portfolio.
   * @param date          the date to get cost basis on
   * @param value         the value of the portfolio
   * @throws InterruptedException if invalid portfolio
   */
  void costBasisPortfolio(String portfolioName, String date, float value)
      throws InterruptedException;

  /**
   * Get the weights.
   *
   * @param stocks stocks to get weights for.
   * @return the weights
   * @throws InterruptedException if invalid stocks array
   */
  String[] getWeights(String[] stocks) throws InterruptedException;


}
