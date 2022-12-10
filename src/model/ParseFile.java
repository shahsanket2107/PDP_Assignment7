package model;

import java.io.FileNotFoundException;
import java.util.ArrayList;

/**
 * This interface represents a file/portfolio that needs to parsed to a portfolio/file.
 */
public interface ParseFile {
  /**
   * Load in a portfolio saved as a file(CSV).
   *
   * @param path the path to load the portfolio from
   * @throws FileNotFoundException when the specified path is invalid
   */
  void loadFile(String path) throws FileNotFoundException;

  /**
   * Save a portfolio to a file.
   *
   * @param fileName     the name of the file
   * @param newPortfolio the portfolio to save to a file
   */
  void saveFile(String fileName, Portfolio newPortfolio);

  /**
   * Get the contents of a portfolio.
   *
   * @return the contents of a portfolio
   */
  ArrayList<String> getPortfolioContents();

  /**
   * Whether a stock is valid or not.
   *
   * @param stockName the name of the stock to validate
   * @return true if stock is valid else false
   */
  boolean validStock(String stockName);

  /**
   * Get all stock names.
   * @return the name of the stocks as an arraylist of string.
   */
  ArrayList<String> getAllStockNames();

}
