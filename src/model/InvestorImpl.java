package model;

import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import view.PrintStatement;
import view.PrintStatementImpl;

/**
 * Implementation of the investor interface.
 */
public class InvestorImpl implements Investor {

  private Map portfolios;
  private Map stocks;
  private PrintStatement print;

  private float costBasis;

  private boolean local;

  /**
   * Default constructor to initialize the portfolios, stocks, and ID.
   */
  public InvestorImpl() {
    portfolios = new HashMap();
    stocks = new HashMap();
    print = new PrintStatementImpl();
    local = false;
    costBasis = 0;
  }

  /**
   * Get the number of shares for a stock.
   *
   * @param stockName the name of the stock to get the number of shares for
   * @return the number of shares for the stock
   */
  @Override
  public float getShares(String stockName) {
    return ((InvestorStock) stocks.get(stockName)).numShares;
  }

  /**
   * Create a new portfolio.
   *
   * @param portfolioName the name of the portfolio
   */
  @Override
  public void createPortfolio(String portfolioName) {
    Portfolio newPortfolio = new PortfolioImpl(portfolioName);
    portfolios.put(portfolioName, newPortfolio);
  }

  /**
   * Get a portfolio.
   *
   * @param portfolioName the portfolio to get
   * @return a portfolio
   */
  @Override
  public Portfolio getPortfolio(String portfolioName) {
    return (Portfolio) portfolios.get(portfolioName);
  }

  /**
   * Buy shares of a stock.
   *
   * @param stockName the stock to buy shares for
   * @param numShares the number of shares to buy
   * @param price     the price to buy the stock at
   * @throws FileNotFoundException when the file path is invalid
   */
  @Override
  public void buyShares(String stockName, String numShares, String datePurchased, float price, boolean text)
          throws FileNotFoundException {
//    if (!containsDigits(numShares)) {
//      throw new IllegalArgumentException("Invalid input for number of shares.");
//    }
    float t = 0;
    if (text) {
      t = getTransactionFee();
    }
    stocks.put(stockName, new InvestorStock(stockName, Float.valueOf(numShares),
            datePurchased, price, t));
    updateCostBasis(stockName, Float.valueOf(numShares), price, t);
  }

  /**
   * Buy shares of a stock.
   *
   * @param stockName the stock to buy shares for
   * @param numShares the number of shares to buy
   * @param price     the price to buy the stock at
   * @throws FileNotFoundException when the file path is invalid
   */
  @Override
  public void buyShares(String stockName, String numShares, String datePurchased, float price)
          throws FileNotFoundException {
//    if (!containsDigits(numShares)) {
//      throw new IllegalArgumentException("Invalid input for number of shares.");
//    }
    float t = getTransactionFee();
    stocks.put(stockName, new InvestorStock(stockName, Float.valueOf(numShares),
            datePurchased, price, t));
    updateCostBasis(stockName, Float.valueOf(numShares), price, t);
  }

  /**
   * Buy shares of a stock.
   *
   * @param stockName the stock to buy shares for
   * @param numShares the number of shares to buy
   * @param price     the price to buy the stock at
   * @throws FileNotFoundException when the file path is invalid
   */
  @Override
  public void buyShares(String stockName, String numShares, String datePurchased, float price, float fee)
          throws FileNotFoundException {
//    if (!containsDigits(numShares)) {
//      throw new IllegalArgumentException("Invalid input for number of shares.");
//    }
    stocks.put(stockName, new InvestorStock(stockName, Float.valueOf(numShares),
            datePurchased, price, fee));
    updateCostBasis(stockName, Float.valueOf(numShares), price, fee);
  }

  @Override
  public void updateStock(String stockName, String numShares, String datePurchased, float price) {
    stocks.put(stockName, new InvestorStock(stockName, Float.valueOf(numShares),
            datePurchased, price, 0));
  }

  /**
   * Returns the price of given stock at a date.
   *
   * @param stockName the stock name
   * @param date      the date to get the price at
   * @return the price at the date
   * @throws FileNotFoundException when invalid file path
   */
  @Override
  public String getPrice(String stockName, String date) throws FileNotFoundException {
    return ParseFileImpl.getPrice(stockName, date);
  }

  /**
   * Returns the price of given stock at current date.
   *
   * @param stockName the stock name
   * @return the price at the date
   * @throws FileNotFoundException when invalid file path
   */
  @Override
  public String getPrice(String stockName) throws FileNotFoundException {
    String date = "";
    int dayOfMonth = LocalDate.now().getDayOfMonth();
    if (dayOfMonth < 10) {
      String day = "0" + dayOfMonth;
      date = day + "-" + LocalDate.now().getMonthValue()
              + "-" + LocalDate.now().getYear();
    } else {
      date = LocalDate.now().getDayOfMonth() + "-" + LocalDate.now().getMonthValue()
              + "-" + LocalDate.now().getYear();
    }
    StocksAPI alphaVantageAPI = new StocksAPIAlphaVantageImpl();
    String price = alphaVantageAPI.getPrice(stockName, date, "daily");
    if (!(price.equals("-1"))) {
      return price;
    }
    return ParseFileImpl.getPrice(stockName, date);
  }

  /**
   * Add a stock to a portfolio.
   *
   * @param portfolioName the portfolio to add a stock to
   * @param stockName     the name of the stock
   */
  @Override
  public void addStock(String portfolioName, String stockName) {
    if (!portfolios.containsKey(portfolioName)) {
      throw new IllegalArgumentException("Investor does not have a portfolio"
              + " created under that name");
    } else if (!stocks.containsKey(stockName)) {
      throw new IllegalArgumentException("Investor does not own stock"
              + " by that name");
    }
    this.local = true;
    getPortfolio(portfolioName).addStock((InvestorStock) stocks.get(stockName));
  }

  @Override
  public void loadStock(String portfolioName, ArrayList<String> contents) throws FileNotFoundException {
    for (int i = 0; i < contents.size(); i += 4) {
      buyShares(contents.get((i)), contents.get(i + 3), contents.get(i + 1), Float.valueOf(contents.get(i + 2)), false);
      getPortfolio(portfolioName).addStock((InvestorStock) stocks.get(contents.get(0)));
    }
  }


  /**
   * Sell shares of a stock.
   *
   * @param stockName the name of the stock.
   */
  @Override
  public void sellStock(String stockName) throws FileNotFoundException {
    InvestorStock stock = (InvestorStock) stocks.get(stockName);
    print.sellStock2();
    String date = getDate();
    while (getPrice(stockName, date).equals("0") ^ getPrice(stockName, date).equals("-1")) {
      print.sharesErr2();
      date = getDate();
    }
    print.sellStock3();
    String numShares = print.readOption();
    while (!numShares.matches("[0-9]+") || Float.valueOf(numShares) % 1 != 0) {
      print.sharesErr1();
      print.shares2();
      numShares = print.readOption();
    }
    if (stock.numShares == Float.valueOf(numShares)) {
      stocks.remove(stockName);
      removeStock(stockName);
      updateCostBasis(stockName, 0, 0, getTransactionFee());
    } else if (stock.numShares > Float.valueOf(numShares)) {
      stock.numShares -= Float.valueOf(numShares);
      updateCostBasis(stockName, 0, 0, getTransactionFee());
    } else {
      print.stockErr3(stockName, numShares);
    }
  }

  /**
   * Sell shares of a stock.
   *
   * @param stockName the name of the stock.
   */
  @Override
  public int sellStock(String stockName, String date, String numShares) throws FileNotFoundException {
    InvestorStock stock = (InvestorStock) stocks.get(stockName);
    if (stock.numShares == Float.valueOf(numShares)) {
      stocks.remove(stockName);
      removeStock(stockName);
      updateCostBasis(stockName, 0, 0, 0);
      return 0;
    } else if (stock.numShares > Float.valueOf(numShares)) {
      stock.numShares -= Float.valueOf(numShares);
      updateCostBasis(stockName, 0, 0, 0);
      return 1;
    } else {
      return -1;
    }
  }

  /**
   * Remove stock from all portfolios containing that stock.
   *
   * @param stockName name of stock to be removed.
   */
  @Override
  public void removeStock(String stockName) {
    Iterator portfolioSet = portfolios.values().iterator();
    while (portfolioSet.hasNext()) {
      Portfolio next = (PortfolioImpl) portfolioSet.next();
      ArrayList<InvestorStock> list = next.getList();
      for (int i = 0; i < list.size(); i++) {
        InvestorStock stock = list.get(i);
        if (stock.name.equals(stockName)) {
          portfolios.remove(stockName);
          list.remove(i);
        }
      }
    }
  }


  /**
   * Boolean if portfolio was created locally or not.
   *
   * @return true if portfolio was created locally, false otherwise.
   */
  @Override
  public boolean getLocal() {
    return local;
  }

  /**
   * Get all the portfolios of this investor.
   *
   * @return a string with the names of all the portfolios under this investor
   */
  @Override
  public String getAllPortfolioNames() {
    Iterator portfolioSet = portfolios.keySet().iterator();
    String result = "";
    while (portfolioSet.hasNext()) {
      result += portfolioSet.next() + "\n";
    }
    return result;
  }

  /**
   * Return string containing all stock names for this investor.
   *
   * @return string containing all stock names for this investor
   */
  @Override
  public String getAllStockNames() {
    Iterator stocksSet = stocks.keySet().iterator();
    String result = "";
    while (stocksSet.hasNext()) {
      result += stocksSet.next() + "\n";
    }
    return result;
  }

  /**
   * Get iterator of all stocks of which the investor owns shares.
   *
   * @return iterator of all InvestorStock objects.
   */
  @Override
  public Iterator<InvestorStock> getAllStocks() {
    Iterator<InvestorStock> iterator = stocks.values().iterator();

    return iterator;
  }

  /**
   * Examine the contents of a list.
   *
   * @return the contents of a list as a string
   */
  public String examine(Iterator<InvestorStock> list) {
    String result = "";
    while (list.hasNext()) {
      InvestorStock next = list.next();
      result += next.name + " " + next.datePurchased + " "
              + next.priceWhenBought + " " + next.numShares + " ";
    }
    return result;
  }


  /**
   * If a portfolio contains the given stock.
   *
   * @param portfolioName the name of the portfolio
   * @param stockName     the name of the stock
   * @return true if the portfolio contains the stock else false
   */
  @Override
  public boolean containsStock(String portfolioName, String stockName) {
    return getPortfolio(portfolioName).containsStock(stockName);
  }

  /**
   * Determine if an investor owns shares of a stock.
   *
   * @param stockName stock in question.
   * @return true if investor owns shares of the stock, false otherwise.
   */
  @Override
  public boolean containsStock(String stockName) {
    return stocks.containsKey(stockName);
  }


  /**
   * Load the past data of a stock and return the price accordingly.
   *
   * @param stockName the name of stock
   * @return the price of the stock
   * @throws FileNotFoundException when an invalid file path is provided
   */
  public String[] loadPastData(String stockName) throws FileNotFoundException {
    String[] result = {"-", "-"};
    String date = getDate();
    while (getPrice(stockName, date).equals("-1")) {
      print.stockDataErr(stockName, date);
      date = getDate();
    }
    String price = String.format("%.02f", Double.valueOf(getPrice(stockName, date)));
    print.price(stockName, date, price);
    result[0] = date;
    result[1] = price;
    return result;
  }

  /**
   * Update investor's cost basis.
   *
   * @param stockName stock from which transaction was made.
   * @param price     price of stock bought.
   * @param numShares number of shares bought.
   */
  private void updateCostBasis(String stockName, float price, float numShares,
                               float transactionFee) {
    InvestorStock s = (InvestorStock) stocks.get(stockName);
    if (s != null) {
      s.transactionFee += transactionFee;
    }
    costBasis = costBasis + (price * numShares) + transactionFee;
  }

  /**
   * Operation to get the user to enter a valid date.
   *
   * @return the validated date
   */
  public String getDate() {
    print.enterDate();
    String option = print.readOption();
    while (!option.matches("\\d{2}-\\d{2}-\\d{4}")) {
      print.dateInvalid();
      option = print.readOption();
    }
    return option;
  }

  public int validDate(String date) {
    if (!date.matches("\\d{2}-\\d{2}-\\d{4}")) {
      return -1;
    }
    return 1;
  }

  /**
   * Boolean if a string contains only digits.
   *
   * @param s String to see if it contains digits.
   * @return true if s contains only digits, false otherwise.
   */
  public boolean containsDigits(String s) {
    return s.matches("[0-9]+");
  }

  /**
   * Gets transaction fee value from user.
   *
   * @return transaction fee.
   */
  public float getTransactionFee() {
    print.commission();
    String result = print.readOption();
    while (!result.matches("[0-9]+")) {
      print.commissionInvalid();
      print.commission();
      result = print.readOption();
    }
    return Float.valueOf(result);
  }

  /**
   * Gets transaction fee value from user.
   *
   * @return transaction fee.
   */
  public float getTransactionFee(int previousCharge) {
    return previousCharge;
  }

  /**
   * Nested class that represents a stock an Investor can buy.
   */
  public static class InvestorStock {

    public String name;
    public float numShares;
    public String datePurchased;
    public float priceWhenBought;

    public float transactionFee;

    /**
     * Constructor to initialize investor stock.
     *
     * @param name            the name of the stock
     * @param numShares       the number of shares purchased
     * @param datePurchased   the date purchased at
     * @param priceWhenBought the price the stock was bought at
     */
    public InvestorStock(String name, float numShares,
                         String datePurchased, float priceWhenBought, float transactionFee) {
      this.name = name;
      this.numShares = numShares;
      this.datePurchased = datePurchased;
      this.priceWhenBought = priceWhenBought;
    }

    /**
     * Constructor to initialize investor stock.
     *
     * @param name            the name of the stock
     * @param numShares       the number of shares purchased
     * @param priceWhenBought the price the stock was bought at
     */
    public InvestorStock(String name, int numShares, float priceWhenBought, float transactionFee) {
      this.name = name;
      this.numShares = numShares;
      this.priceWhenBought = priceWhenBought;
      this.datePurchased = LocalDate.now().toString();
      this.transactionFee = 0;
    }
  }

}

