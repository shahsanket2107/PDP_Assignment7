package view;

import java.io.FileNotFoundException;
import java.util.ArrayList;


import javax.swing.*;

import model.Model;

import static java.lang.Thread.sleep;

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

  public void buySellStock(String allStocks) {
    frame.buySellStock(allStocks);
  }

  public String buySellOption() throws InterruptedException {
    int option = getOption();
    chooseOption = 0;
    if (option == 1) {
      return "buy";
    } else {
      return "sell";
    }
  }

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

  public String getStock(ArrayList<String> list) throws InterruptedException {
    frame.chooseStock(list);
    while (chooseStock.equals("")) {
      sleep(1);
    }
    String stock = chooseStock;
    chooseStock = "";
    return stock;
  }

  public String getStock(String stocks) throws InterruptedException {
    frame.chooseStock(stocks);
    while (chooseStock.equals("")) {
      sleep(1);
    }
    String stock = chooseStock;
    chooseStock = "";
    return stock;
  }

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

  public boolean addStock() throws InterruptedException {
    frame.addStock();
    int option = getOption();
    chooseOption = 0;
    if (option == 1) {
      return true;
    } else {
      return false;
    }
  }

  public String[] getPriceDate(String stockName, Model model) throws InterruptedException, FileNotFoundException {
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

  public String[] sellStock(String stockName, Model model) throws InterruptedException, FileNotFoundException {
    String[] result = {"-", "-", "-"};
    String[] datePrice = getPriceDate(stockName, model);
    String date = datePrice[0];
    String price = datePrice[1];
    result[0] = date;
    result[1] = price;
    result[2] = getNumShares(stockName, date, price);
    return result;
  }

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

  public String getNumShares(String stockName, String date, String price) throws InterruptedException {
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

  public void viewComposition(String portfolioName, Object[][] rowData, Object[] columnNames) throws InterruptedException {
    frame.viewComposition(portfolioName, rowData, columnNames);
    while (!chooseExit) {
      sleep(1);
    }
    chooseExit = false;
  }

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

  public void portfolioPerformanceYear(String portfolio, String[] names, String[] data) throws InterruptedException {
    frame.portfolioPerformanceYear(portfolio, names, data);
    while (!chooseExit) {
      sleep(1);
    }
    chooseExit = false;
  }

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

  public void portfolioPerformanceMonths(String portfolio, String portfolioPerformanceTimeRangegeMonths,
                                         String[] names, String[] data) throws InterruptedException {
    frame.portfolioPerformanceMonths(portfolio, portfolioPerformanceTimeRangegeMonths, names, data);
    while (!chooseExit) {
      sleep(1);
    }
    chooseExit = false;
  }

  public void portfolioPerformanceMonth(String portfolio, String month,
                                        String[] names, String[] data) throws InterruptedException {
    frame.portfolioPerformanceMonth(portfolio, month, names, data);
    while (!chooseExit) {
      sleep(1);
    }
    chooseExit = false;
  }

  public void valueOfPortfolio(String portfolioName, String date, float value) throws InterruptedException {
    frame.valueOfPortfolio(portfolioName, date, value);
    while (!chooseExit) {
      sleep(1);
    }
    chooseExit = false;
  }

  public void costBasisPortfolio(String portfolioName, String date, float value) throws InterruptedException {
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
