package model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Implementation of the ParseFile interface.
 * Supports saving and loading a portfolio.
 */
public class ParseFileImpl implements ParseFile {
  private ArrayList<String> portfolio;

  /**
   * Default constructor.
   * Initialize portfolio arraylist.
   */
  public ParseFileImpl() {
    portfolio = new ArrayList<>();
  }

  /**
   * Load in a portfolio saved as a file(CSV).
   *
   * @param path the path to load the portfolio from
   * @throws FileNotFoundException when the specified path is invalid
   */
  public void loadFile(String path) throws FileNotFoundException {
    Scanner sc = new Scanner(new File(path));

    sc.useDelimiter(",");
    while (sc.hasNextLine()) {
      portfolio.add(sc.nextLine());
    }

    sc.close();
  }

  @Override
  public void saveFile(String fileName, Portfolio portfolio) {
    try {
      FileWriter portfolioWriter = new FileWriter(fileName + ".csv");
      String portfolioContents = portfolio.examine();
      //System.out.println(portfolioContents);
      String[] splitContents = portfolioContents.trim().split("\\s+");
      String result = splitContents[0];
      for (int i = 1; i < splitContents.length; i++) {
        if (i % 4 == 0) {
          result += "\n";
          result = result + splitContents[i];
        } else {
          result = result + "," + splitContents[i];
        }
      }
      portfolioWriter.write(result);
      portfolioWriter.close();
    } catch (IOException e) {
      System.out.println("Error: Unable to save portfolio to file.");
      e.printStackTrace();
    }

  }

  @Override
  public ArrayList<String> getPortfolioContents() {
    return this.portfolio;
  }

  /**
   * Get the price of a stock at a given date.
   *
   * @param stockName the name of the stock
   * @param date      the date to get price at
   * @return the price as a string
   * @throws FileNotFoundException when invalid file path
   */
  public static String getPrice(String stockName, String date) throws FileNotFoundException {
    Scanner s = getStockInfo(stockName);
    s.useDelimiter(",");
    while (s.hasNext()) {
      if (s.next().equals(date)) {
        String low = s.next();
        String open = s.next();
        String volume = s.next();
        String high = s.next();
        String close = s.next();
        String adjustedClose = s.next();
        if (close.matches("[0-9]+.[0-9]+")) {
          return close;
        } else {
          return "0";
        }
      }
      s.nextLine();
    }
    s.close();
    return "-1";
  }

  private static Scanner getStockInfo(String stockName) throws FileNotFoundException {
    String path = "files/sp500/csv/" + stockName + ".csv";
    Scanner s = new Scanner(new File(path));
    return s;
  }

  /**
   * Determines if a stock is valid.
   *
   * @param stockName the name of the stock to validate
   * @return true if the stock is valid, false otherwise.
   */
  public boolean validStock(String stockName) {
    try {
      Scanner s = getStockInfo(stockName);
    } catch (FileNotFoundException e) {
      System.out.println("Stock name provided does not exist.");
      return false;
    }
    return true;
  }

  /**
   * Used to get all stock names.
   * @return array of all stock names
   */
  public ArrayList<String> getAllStockNames() {
    File folder = new File("files/sp500/csv/");
    File[] list = folder.listFiles();
    ArrayList<String> result = new ArrayList<>();

    for (int i = 0; i < list.length; i++) {
      if (list[i].isFile()) {
        result.add(list[i].getName().substring(0, (int) (list[i].getName().length() - 4)));
      }
    }
    return result;
  }
}
