package view;

import static java.lang.Thread.sleep;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import javax.swing.JFrame;
import model.Model;

/**
 * View class.
 */
public class ViewImpl implements View {

  Dialog dialog;
  static int chooseOption;
  static String chooseStock;
  static String choosePortfolio;

  static String chooseDate;
  static String chooseNumShares;
  static String choosePath;
  static boolean chooseExit;

  static ArrayList<String> chooseStocks;
  static ArrayList<String> chooseWeights;
  GUIFrame frame;

  /**
   * Implementation of the view interface.
   */
  public ViewImpl() {
    GUIFrame.setDefaultLookAndFeelDecorated(false);
    frame = new GUIFrame();

    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setVisible(true);

    chooseOption = 0;
    chooseNumShares = "";
    chooseStock = "";
    chooseDate = "";
    choosePortfolio = "";
    choosePath = "";
    chooseExit = false;
    chooseStocks = new ArrayList<>();
    chooseWeights = new ArrayList<>();

    dialog = new DialogImpl(frame);
  }

  /**
   * Runs gui.
   */
  public void runGUI() {
    frame.runSystem();
  }

  static void chooseOption(int selectedRadioButton) {
    chooseOption = selectedRadioButton;
  }

  static void chooseStock(String selectedStock) {
    chooseStock = selectedStock;
  }

  static void chooseStocks(ArrayList<String> selectedStocks) {
    chooseStocks = selectedStocks;
  }

  static void choosePortfolio(String selectedPortfolio) {
    choosePortfolio = selectedPortfolio;
  }

  static void chooseDate(String selectedDate) {
    chooseDate = selectedDate;
  }

  static void chooseExit(boolean exit) {
    chooseExit = exit;
  }

  static void chooseNumShares(String numShares) {
    chooseNumShares = numShares;
  }

  static void choosePath(String path) {
    choosePath = path;
  }

  static void chooseWeights(ArrayList<String> selectedWeights) {
    chooseWeights = selectedWeights;
  }

  @Override
  public int getOption() throws InterruptedException {
    while (chooseOption == 0) {
      sleep(1);
    }
    int option = chooseOption;
    chooseOption = 0;
    return option;
  }

  public JFrame frame() {
    return frame;
  }

  /**
   * Buy/Sell stocks.
   *
   * @param allStocks string all stocks.
   */
  public void buySellStock(String allStocks) {
    frame.buySellStock(allStocks);
  }

  /**
   * Option of buy and sell.
   *
   * @return buy or sell
   * @throws InterruptedException on interruption
   */
  public String buySellOption() throws InterruptedException {
    int option = getOption();
    chooseOption = 0;
    if (option == 1) {
      return "buy";
    } else {
      return "sell";
    }
  }

  /**
   * Gets portfolio name.
   *
   * @param portfolios available portfolios
   * @return portfolio names
   * @throws InterruptedException on interruption.
   */
  public String getPortfolioNew(String portfolios) throws InterruptedException {
    frame.choosePortfolioNew();
    while (choosePortfolio.equals("")) {
      sleep(1);
    }
    String portfolio = choosePortfolio;
    String[] all = portfolios.split("\n");
    while (contains(all, portfolio)) {
      dialog.portfolioExists();
      while (choosePortfolio.equals("")) {
        sleep(1);
      }
      portfolio = choosePortfolio;
    }
    choosePortfolio = "";
    return portfolio;
  }

  /**
   * Gets portfolio.
   *
   * @param portfolios available portfolio names
   * @return portfolio
   * @throws InterruptedException on interruption
   */
  public String getPortfolio(String portfolios) throws InterruptedException {
    if (portfolios.equals("")) {
      dialog.portfoliosNotFound();
      return "";
    }
    frame.choosePortfolio(portfolios);
    while (choosePortfolio.equals("")) {
      sleep(1);
    }
    String portfolio = choosePortfolio;
    choosePortfolio = "";
    return portfolio;
  }

  /**
   * Gets stock.
   *
   * @param list list of stocks
   * @return stocks
   * @throws InterruptedException on interruption
   */
  public String getStock(ArrayList<String> list) throws InterruptedException {
    frame.chooseStock(list);
    while (chooseStock.equals("")) {
      sleep(1);
    }
    String stock = chooseStock;
    chooseStock = "";
    return stock;
  }

  /**
   * gets stock.
   *
   * @param stocks a string containing all the stocks
   * @return stocks
   * @throws InterruptedException on interruption
   */
  public String getStock(String stocks) throws InterruptedException {
    frame.chooseStock(stocks);
    while (chooseStock.equals("")) {
      sleep(1);
    }
    String stock = chooseStock;
    chooseStock = "";
    return stock;
  }

  /**
   * Gets stocks.
   *
   * @param list list of stocks
   * @return stocks
   * @throws InterruptedException on interruption
   */
  public String[] getStocks(ArrayList<String> list) throws InterruptedException {
    String[] result;
    frame.chooseStocks(list);
    while (chooseStocks.isEmpty()) {
      sleep(1);
    }
    ArrayList<String> stocks = chooseStocks;
    result = new String[stocks.size()];
    for (int i = 0; i < result.length; i++) {
      result[i] = stocks.get(i);
    }
    chooseStocks.clear();
    return result;
  }

  /**
   * Gets weights.
   *
   * @param stocks stocks to get weights for.
   * @return weights.
   * @throws InterruptedException on interruption
   */
  public String[] getWeights(String[] stocks) throws InterruptedException {
    int totalWeights = 100;
    int weights = 0;
    boolean finished = false;
    while (weights != totalWeights) {
      chooseWeights.clear();
      frame.getWeights(stocks);
      while (chooseWeights.isEmpty()) {
        sleep(1);
      }
      for (int i = 0; i < chooseWeights.size(); i++) {
        if (validWeight(chooseWeights.get(i))) {
          weights += Integer.valueOf(chooseWeights.get(i));
          if (i == chooseWeights.size() - 1) {
            finished = true;
          }
        } else {
          weights = 0;
          break;
        }
      }
      if (finished && weights != totalWeights) {
        dialog.invalidWeightTotal();
        finished = false;
        weights = 0;
      }
    }
    String[] result = new String[stocks.length];
    for (int i = 0; i < result.length; i++) {
      result[i] = chooseWeights.get(i);
    }
    return result;
  }

  /**
   * Gets frequency.
   *
   * @return frequency
   * @throws InterruptedException on interruption
   */
  public String getFrequency() throws InterruptedException {
    frame.getFrequency();
    int option = getOption();
    chooseOption = 0;
    if (option == 1) {
      return "days";
    } else if (option == 2) {
      return "months";
    } else {
      return "years";
    }
  }

  /**
   * Adds stocks.
   *
   * @return true or false
   * @throws InterruptedException on interruption
   */
  public boolean addStock() throws InterruptedException {
    frame.addStock();
    int option = getOption();
    chooseOption = 0;
    return option == 1;
  }

  /**
   * Gets price date.
   *
   * @param stockName stock name
   * @param model     the model
   * @return price date
   * @throws InterruptedException  on interruption
   * @throws FileNotFoundException on file not found
   */
  public String[] getPriceDate(String stockName, Model model)
      throws InterruptedException, FileNotFoundException {
    String[] result = {"-", "-"};
    chooseDate = "";
    frame.chooseDate();
    while (chooseDate.equals("")) {
      sleep(1);
    }
    while (!validDate(chooseDate)) {
      chooseDate = "";
      while (chooseDate.equals("")) {
        sleep(1);
      }
    }
    String price = model.getPriceOnDate(stockName, chooseDate);
    while (!validPrice(price)) {
      chooseDate = "";
      while (chooseDate.equals("")) {
        sleep(1);
      }
      price = model.getPrice(stockName, chooseDate);
    }
    result[0] = chooseDate;
    result[1] = price.substring(0, 5);
    chooseDate = "";
    return result;
  }

  /**
   * Gets date.
   *
   * @return dates
   * @throws InterruptedException on interruption.
   */
  public String getDate() throws InterruptedException {
    frame.chooseDate();
    while (chooseDate.equals("")) {
      sleep(1);
    }
    while (!validDate(chooseDate)) {
      chooseDate = "";
      while (chooseDate.equals("")) {
        sleep(1);
      }
    }
    String date = chooseDate;
    chooseDate = "";
    return date;
  }

  /**
   * Gets time range.
   *
   * @return string
   * @throws InterruptedException on interruption
   */
  public String getTimeRange() throws InterruptedException {
    frame.chooseStartDate();
    frame.chooseDate();
    while (chooseDate.equals("")) {
      sleep(1);
    }
    while (!validDate(chooseDate)) {
      chooseDate = "";
      while (chooseDate.equals("")) {
        sleep(1);
      }
    }
    String startDate = chooseDate;
    String endDate = "";
    chooseDate = "";

    frame.chooseEndDate();
    int option = getOption();
    chooseOption = 0;
    if (option == 1) {
      frame.chooseDate();
      while (chooseDate.equals("")) {
        sleep(1);
      }
      while (!validDate(chooseDate)) {
        chooseDate = "";
        while (chooseDate.equals("")) {
          sleep(1);
        }
      }
      endDate = chooseDate;
      chooseDate = "";
    } else {
      return startDate;
    }
    return startDate + "," + endDate;
  }

  /**
   * sell stocks.
   *
   * @param stockName the name of the stock
   * @param model     the model
   * @return stocks
   * @throws InterruptedException  on interruption
   * @throws FileNotFoundException on interruption
   */
  public String[] sellStock(String stockName, Model model)
      throws InterruptedException, FileNotFoundException {
    String[] result = {"-", "-", "-"};
    String[] datePrice = getPriceDate(stockName, model);
    String date = datePrice[0];
    String price = datePrice[1];
    result[0] = date;
    result[1] = price;
    result[2] = getNumShares(stockName, date, price);
    return result;
  }

  /**
   * Gets path.
   *
   * @return path
   * @throws InterruptedException on interruption
   */
  public String getPath() throws InterruptedException {
    frame.choosePath();
    while (choosePath.equals("")) {
      sleep(1);
    }
    String path = choosePath;
    choosePath = "";
    return path;
  }

  private boolean validDate(String date) throws InterruptedException {
    if (!date.matches("\\d{2}-\\d{2}-\\d{4}")) {
      dialog.invalidDate();
      return false;
    }
    return true;
  }

  private boolean validWeight(String w) throws InterruptedException {
    if (w.equals("0")) {
      dialog.invalidWeightZero();
      return false;
    } else if (!w.matches("[0-9]+")) {
      dialog.invalidWeight();
      return false;
    }
    return true;
  }

  private boolean validPrice(String price) throws InterruptedException {
    if (price.equals("0") ^ price.matches("-1")) {
      dialog.noPriceOnSelectedDate();
      return false;
    }
    return true;
  }

  /**
   * Num shares.
   *
   * @param stockName name of the stock
   * @param date      date purchased.
   * @param price     price of the stock on the date
   * @return num shares.
   * @throws InterruptedException on interruption
   */
  public String getNumShares(String stockName, String date, String price)
      throws InterruptedException {
    chooseNumShares = "";
    frame.numberOfShares(stockName, date, price);
    while (chooseNumShares.equals("")) {
      sleep(1);
    }
    while (!validNumber(chooseNumShares)) {
      chooseNumShares = "";
      while (chooseNumShares.equals("")) {
        sleep(1);
      }
    }
    return chooseNumShares;
  }

  /**
   * Gets amount invest.
   *
   * @return string.
   * @throws InterruptedException on interruption
   */
  public String getAmountInvest() throws InterruptedException {
    chooseNumShares = "";
    frame.getAmountInvest();
    while (chooseNumShares.equals("")) {
      sleep(1);
    }
    while (!validNumber(chooseNumShares)) {
      chooseNumShares = "";
      while (chooseNumShares.equals("")) {
        sleep(1);
      }
    }
    return chooseNumShares;
  }

  /**
   * Gets amount frequency.
   *
   * @return amount f
   * @throws InterruptedException on interruption
   */
  public String getAmountFrequency() throws InterruptedException {
    chooseNumShares = "";
    frame.getAmountFrequency();
    while (chooseNumShares.equals("")) {
      sleep(1);
    }
    while (!validNumber(chooseNumShares)) {
      chooseNumShares = "";
      while (chooseNumShares.equals("")) {
        sleep(1);
      }
    }
    return chooseNumShares;
  }

  /**
   * View composition.
   *
   * @param portfolioName name of portfolio.
   * @param rowData       row data.
   * @param columnNames   names of columns.
   * @throws InterruptedException on interruption
   */
  public void viewComposition(String portfolioName, Object[][] rowData, Object[] columnNames)
      throws InterruptedException {
    frame.viewComposition(portfolioName, rowData, columnNames);
    while (!chooseExit) {
      sleep(1);
    }
    chooseExit = false;
  }

  /**
   * Gets composition.
   *
   * @return s
   * @throws InterruptedException on interruption
   */
  public String compositionOption() throws InterruptedException {
    frame.portfolioPerformanceTimeRange();
    int option = getOption();
    chooseOption = 0;
    if (option == 1) {
      return "year";
    } else if (option == 2) {
      return "months";
    } else {
      return "month";
    }
  }

  /**
   * ppy.
   *
   * @param portfolio the name of the portfolio
   * @param names     the names of the stocks
   * @param data      the data needed
   * @throws InterruptedException on interruption
   */
  public void portfolioPerformanceYear(String portfolio, String[] names, String[] data)
      throws InterruptedException {
    frame.portfolioPerformanceYear(portfolio, names, data);
    while (!chooseExit) {
      sleep(1);
    }
    chooseExit = false;
  }

  /**
   * gmr.
   *
   * @return m
   * @throws InterruptedException on interruption
   */
  public String getMonthRange() throws InterruptedException {
    frame.getStartMonth();
    int startMonth = getOption();
    chooseOption = 0;
    frame.getEndMonth(startMonth);
    int endMonth = getOption();
    chooseOption = 0;
    if (startMonth < 10 && endMonth < 10) {
      return "0" + startMonth + "-" + "0" + endMonth;
    } else if (startMonth < 10) {
      return "0" + startMonth + "-" + endMonth;
    } else {
      return startMonth + "-" + endMonth;
    }
  }

  /**
   * gm.
   *
   * @return m
   * @throws InterruptedException on interruption
   */
  public String getMonth() throws InterruptedException {
    frame.getMonth();
    int month = getOption();
    chooseOption = 0;
    if (month < 10) {
      return "0" + month;
    } else {
      return String.valueOf(month);
    }
  }

  /**
   * Performance.
   *
   * @param portfolio                             the name of the portfolio
   * @param portfolioPerformanceTimeRangegeMonths the time-range(MM-MM)
   * @param names                                 the names of the stocks
   * @param data                                  the data needed
   * @throws InterruptedException on interruption
   */
  public void portfolioPerformanceMonths(String portfolio,
      String portfolioPerformanceTimeRangegeMonths,
      String[] names, String[] data) throws InterruptedException {
    frame.portfolioPerformanceMonths(portfolio, portfolioPerformanceTimeRangegeMonths, names, data);
    while (!chooseExit) {
      sleep(1);
    }
    chooseExit = false;
  }

  /**
   * Portfolio performance month.
   *
   * @param portfolio the name of the portfolio
   * @param month     the time-range(MM)
   * @param names     the names of the stocks
   * @param data      the data needed
   * @throws InterruptedException on interruption
   */
  public void portfolioPerformanceMonth(String portfolio, String month,
      String[] names, String[] data) throws InterruptedException {
    frame.portfolioPerformanceMonth(portfolio, month, names, data);
    while (!chooseExit) {
      sleep(1);
    }
    chooseExit = false;
  }

  /**
   * Value of portfolio.
   *
   * @param portfolioName the name of the portfolio
   * @param date          the date to get value on
   * @param value         the value
   * @throws InterruptedException on interruption
   */
  public void valueOfPortfolio(String portfolioName, String date, float value)
      throws InterruptedException {
    frame.valueOfPortfolio(portfolioName, date, value);
    while (!chooseExit) {
      sleep(1);
    }
    chooseExit = false;
  }

  /**
   * Cost basis of portfolio.
   *
   * @param portfolioName the name of the portfolio.
   * @param date          the date to get cost basis on
   * @param value         the value of the portfolio
   * @throws InterruptedException on interruption
   */
  public void costBasisPortfolio(String portfolioName, String date, float value)
      throws InterruptedException {
    frame.costBasisPortfolio(portfolioName, date, value);
    while (!chooseExit) {
      sleep(1);
    }
    chooseExit = false;
  }


  private boolean validNumber(String n)
      throws InterruptedException {
    if (!n.matches("[0-9]+")) {
      dialog.invalidNumber();
      return false;
    }
    return true;
  }

  private boolean contains(String[] list, String item) {
    for (int i = 0; i < list.length; i++) {
      if (list[i].equals(item)) {
        return true;
      }
    }
    return false;
  }
}
