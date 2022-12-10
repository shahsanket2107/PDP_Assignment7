package view;

import java.util.ArrayList;
import java.util.Map;

import model.Portfolio;

/**
 * This interface represents displaying various parts of a portfolio.
 */
public interface DisplayPortfolio {

  /**
   * Display portfolio performance as a graph.
   *
   * @param portfolio portfolio to be displayed.
   * @param contents  contents of portfolio.
   * @param timeRange the range in years or month(s)
   */
  void displayPortfolioPerformance(Portfolio portfolio, ArrayList<String> contents,
                                   String namePort, String timeRange);

  /**
   * Display the contents of a portfolio.
   *
   * @param contents the contents of a portfolio as an arraylist of strings
   */
  void displayContents(ArrayList<String> contents);

  /**
   * Display the contents of a portfolio.
   *
   * @param contents the contents of a portfolio as a string
   */
  void displayContents(String contents);

  /**
   * Display the contents of a portfolio on a given date.
   *
   * @param contents the contents of the portfolio as an arraylist of strings
   * @param date     the date to display the contents on
   */
  void displayContents(ArrayList<String> contents, String date);

  /**
   * Display the contents of a portfolio on a given date.
   *
   * @param contents the contents of a portfolio as a string
   */
  void displayContents(String contents, String date);

  /**
   * Display the names of portfolios the user has loaded in.
   *
   * @param portfolios the map storing the portfolios
   * @return a string representing the portfolio names
   */
  String displayPortfolioFileNames(Map portfolios);

  /**
   * Get the column names for a portfolio as an array of strings.
   *
   * @return column names for a portfolio as an array of strings
   */
  String[] columnNames();

  /**
   * Get the contents of a portfolio as a string, given the contents as an arraylist.
   *
   * @param contents the contents of the portfolio as a string.
   * @return
   */
  String getContents(ArrayList<String> contents);

  /**
   * \
   * Return bar-chart portfolio performance data.
   *
   * @param portfolio the portfolio itself
   * @param contents  the contents of the portfolio
   * @param namePort  the name of the portfolio
   * @param timeRange the time-range specified for the bar-chart.
   * @return the data as a 2D-Array
   */
  String[][] portfolioPerformanceData(Portfolio portfolio, ArrayList<String> contents,
                                      String namePort, String timeRange);
}
