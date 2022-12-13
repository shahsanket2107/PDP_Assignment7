package controller;

import java.io.FileNotFoundException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import model.Model;
import model.ModelImpl;
import model.ParseFile;
import model.ParseFileImpl;
import model.Portfolio;
import view.Dialog;
import view.DialogImpl;
import view.DisplayMenu;
import view.DisplayMenuImpl;
import view.DisplayPortfolio;
import view.DisplayPortfolioImpl;
import view.PrintStatement;
import view.PrintStatementImpl;
import view.View;
import view.ViewImpl;

/**
 * The main-entry to the program. The Main Controller class. Responsible for linking the model and
 * view.
 */
public class MainControllerImpl {

  static DisplayMenu menu = new DisplayMenuImpl();

  static PrintStatement print = new PrintStatementImpl();
  static Model model;
  static View view;
  static Dialog dialog;
  static Map portfolioFiles;
  static boolean text = false;

  /**
   * Main function to run the program and initialize the investor and portfolios.
   *
   * @param args packed arguments
   * @throws FileNotFoundException when an invalid file path is provided
   */
  public static void main(String[] args) throws FileNotFoundException, InterruptedException {
    model = new ModelImpl();
    menu.displayMainScreen();
    portfolioFiles = new HashMap();
    display();
  }

  private static void display() throws FileNotFoundException, InterruptedException {
    print.guiOrTextBased();
    String option = print.readOption();
    while (!option.equals("text") && !option.equals("gui")) {
      print.chooseOption5();
      option = print.readOption();
    }
    if (option.equals("text")) {
      text = true;
    } else if (option.equals("gui")) {
      view = new ViewImpl();
      dialog = new DialogImpl(view.frame());
    }
    while (true) {
      runSystem();
    }
  }

  /**
   * Method to run the program continuously.
   *
   * @throws FileNotFoundException when an invalid file path is provided
   */
  private static void runSystem() throws FileNotFoundException, InterruptedException {
    int option;
    if (text) {
      menu.displayOptions();
      option = chooseOption();
    } else {
      view.runGUI();
      option = view.getOption();
      print.printOption(String.valueOf(option));
    }
    chooseOperation(option);
  }

  /**
   * Method to return the option chosen from the user from the main screen.
   *
   * @return the option chosen
   */
  private static int chooseOption() {
    String option = print.readOption();
    while (!(option.equals("1")) && !(option.equals("2"))
        && !(option.equals("3"))
        && !(option.equals("4"))
        && !(option.equals("5"))
        && !(option.equals("6"))
        && !(option.equals("7"))
        && !(option.equals("8"))
        && !(option.equals("9"))
        && !(option.equals("10"))
        && !(option.equals("11"))) {
      if (option.equals("q")) {
        System.exit(1);
      }
      print.chooseOption1();
      option = print.readOption();
    }

    return Integer.parseInt(option);
  }

  /**
   * Choose a valid operation.
   *
   * @param option the operation to choose
   * @throws FileNotFoundException when an invalid file path is provided
   */
  private static void chooseOperation(int option)
      throws FileNotFoundException, InterruptedException {
    switch (option) {
      case 1:
        operationBuySellStock();
        break;
      case 2:
        operationNewPortfolio();
        break;
      case 3:
        operationComposition();
        break;
      case 4:
        operationPortfolioPerformance();
        break;
      case 5:
        operationValueOfPortfolio();
        break;
      case 6:
        operationGetCostBasis();
        break;
      case 7:
        operationPersistPortfolio();
        break;
      case 8:
        operationAddStockPortfolio();
        break;
      case 9:
        operationStartToFinishDollarCostAvg();
        break;
      case 10:
        operationAddMultipleStocksToPortfolio();
        break;
      case 11:
        rebalancePortfolio();
        break;
      default:
        /*
        No action for default case.
         */
        break;
    }
  }

  private static boolean dateCompare(String date1, String date2) throws ParseException {
    SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
    Date temp_date1 = sdf.parse(date1);
    Date temp_date2 = sdf.parse(date2);
    return temp_date1.compareTo(temp_date2) >= 0;
  }

  private static ArrayList<String> getContentsTillADate(String date, ArrayList<String> contents) {
    ArrayList<String> ans = new ArrayList<>();
    for (int i = 0; i < contents.size(); i++) {
      String[] tokens = contents.get(i).split(",");
      String date1 = tokens[1];
      boolean check = false;
      try {
        check = dateCompare(date, date1);
      } catch (ParseException e) {
        throw new IllegalArgumentException(e.getMessage());
      }
      if (check) {
        ans.add(contents.get(i));
      }
    }
    return ans;
  }

  private static String helperGetPortfolio() throws IllegalArgumentException {
    String allPortfolios = model.getAllPortfolioNames();
    DisplayPortfolio portfolioFileNames = new DisplayPortfolioImpl();
    if (allPortfolios.equals("") && portfolioFiles.isEmpty()) {
      if (text) {
        print.portfoliosNotFound();
      } else {
        dialog.portfoliosNotFound();
      }
      return null;
    } else {
      if (text) {
        print.displayPortfolios(allPortfolios,
            portfolioFileNames.displayPortfolioFileNames(portfolioFiles));
      }
    }
    String portfolioName;
    if (text) {
      portfolioName = print.readOption();
    } else {
      try {
        portfolioName = view.getPortfolio(allPortfolios
            + portfolioFileNames.displayPortfolioFileNames(portfolioFiles));
      } catch (InterruptedException e) {
        throw new IllegalArgumentException("Thread not yet finished!");
      }
    }
    return portfolioName;
  }

  private static ArrayList<String> helperGetContents(String portfolioName)
      throws IllegalArgumentException {
    boolean portfolioFilesContains = portfolioFiles.containsKey(portfolioName);
    ArrayList<String> contents = null;
    if (portfolioFilesContains) {
      contents = (ArrayList<String>) portfolioFiles.get(portfolioName);
    } else {
      Portfolio p1 = model.getPortfolio(portfolioName);
      try {
        contents = p1.examinePort();
      } catch (NullPointerException e) {
        throw new IllegalArgumentException("Invalid Portfolio Name!!");
      }
    }
    return contents;
  }

  private static void savePortfolioHelper(Portfolio portfolio) {
    ParseFile newPortfolioFile;
    boolean validPath = false;
    while (!validPath) {
      print.portfolioSave();
      String portfolioPath = print.readOption();
      newPortfolioFile = new ParseFileImpl();
      newPortfolioFile.saveFile(portfolioPath, portfolio);
      validPath = true;
    }
  }

  private static String[] rebalanceHelper(String portfolioName, String date)
      throws IllegalArgumentException {
    ArrayList<String> tempContents = helperGetContents(portfolioName);

    List<String> stock = new ArrayList<>();
    String[] stockNames;

    ArrayList<String> contents = getContentsTillADate(date, tempContents);
    if (contents.size() != 0) {
      for (int i = 0; i < contents.size(); i++) {
        String[] tokens = contents.get(i).split(",");
        String currStock = tokens[0];
        String date1 = tokens[1];
        boolean check = false;
        try {
          check = dateCompare(date, date1);
        } catch (ParseException e) {
          throw new IllegalArgumentException("Error in parsing dates!");
        }
        if (check) {
          stock.add(currStock);
        }
      }
    }
    stockNames = stock.toArray(new String[0]);
    return stockNames;
  }

  private static String[] getWeightsForText(String[] stockNames) throws IllegalArgumentException {
    String[] weights;
    double totalWeights = 0;
    weights = new String[stockNames.length];
    String option;
    while (totalWeights != 100) {
      totalWeights = 0;
      print.printMessage(
          "The list of stocks in the portfolio is: " + Arrays.toString(stockNames));
      print.printMessage("Please lise the corresponding weights(%) "
          + "for each stock(i.e. 20,40,40): ");
      option = print.readOption();
      weights = option.split(",");
      for (String w : weights) {
        totalWeights += Double.parseDouble(w);
      }
      if (totalWeights != 100) {
        throw new IllegalArgumentException("The weights should sum to 100.");
      }
    }
    return weights;
  }

  private static void rebalancePortfolio() throws InterruptedException, FileNotFoundException {
    try {
      String portfolioName = helperGetPortfolio();
      if (portfolioName == null) {
        return;
      }
      String date;
      if (text) {
        date = model.getDate();
      } else {
        date = view.getDate();
      }
      String[] stockNames = rebalanceHelper(portfolioName, date);
      boolean portfolioFilesContains = portfolioFiles.get(portfolioName) != null;
      boolean investorContains = model.getPortfolio(portfolioName) != null;
      ParseFile parseFile = new ParseFileImpl();
      String[] weights = null;
      if (text) {
        try {
          weights = getWeightsForText(stockNames);
        } catch (Exception e) {
          System.out.println(e.getMessage());
        }
      } else {
        weights = view.getWeights(stockNames);
      }
      float amount = model.getTotalValueOfPortfolio(portfolioFilesContains, investorContains,
          portfolioFiles, portfolioName, date);
      Portfolio pf = null;
      if (text) {
        try {
          pf = model.reBalance(portfolioName, String.valueOf(amount), stockNames, weights,
              date);
        } catch (Exception e) {
          print.printMessage(e.getMessage());
        }
        savePortfolioHelper(pf);
        print.printMessage("Rebalance Successful");
      } else {
        Portfolio pf1 = null;
        try {
          pf1 = model.reBalance(portfolioName, String.valueOf(amount), stockNames, weights,
              date);
        } catch (Exception e) {
          print.printMessage(e.getMessage());
        }
        String path = view.getPath();
        parseFile.saveFile(path, pf1);
        dialog.rebalanceSuccess();
      }
    } catch (IllegalArgumentException e) {
      print.printMessage(e.getMessage());
    }
  }

  private static void operationAddMultipleStocksToPortfolio()
      throws FileNotFoundException, InterruptedException {
    String allPortfolios = model.getAllPortfolioNames();
    DisplayPortfolio portfolioFileNames = new DisplayPortfolioImpl();
    ParseFile parseFile = new ParseFileImpl();
    String portfolioName;
    if (text) {
      if (allPortfolios.equals("") && portfolioFiles.isEmpty()) {
        print.portfoliosNotFound();
        return;
      } else {
        print.displayPortfolios(allPortfolios,
            portfolioFileNames.displayPortfolioFileNames(portfolioFiles));
      }
      portfolioName = print.readOption();
    } else {
      if (allPortfolios.equals("") && portfolioFiles.isEmpty()) {
        dialog.portfoliosNotFound();
        return;
      } else {
        portfolioName = view.getPortfolio(model.getAllPortfolioNames()
            + portfolioFileNames.displayPortfolioFileNames(portfolioFiles));
      }
    }
    String[] stockNames;
    String amount;
    String[] weights;
    String date;
    if (text) {
      Portfolio port = model.getPortfolio(portfolioName);
      System.out.println("Please list the stocks you would like to "
          + "buy separated by commas(i.e. AAPL,EBAY,NI): ");
      String option = print.readOption();
      stockNames = option.split(",");
      System.out.println("Please list the amount you would like to invest in dollars(i.e. 2000): ");
      option = print.readOption();
      amount = option;
      int totalWeights = 0;
      weights = new String[stockNames.length];
      while (totalWeights != 100) {
        totalWeights = 0;
        System.out.println("Please lise the corresponding weights(%) "
            + "for each stock(i.e. 20,40,40): ");
        option = print.readOption();
        weights = option.split(",");
        for (String w : weights) {
          totalWeights += Integer.parseInt(w);
        }
        if (totalWeights != 100) {
          System.out.println("The weights should sum to 100.");
        }
      }
      date = model.getDate();
    } else {
      stockNames = view.getStocks(parseFile.getAllStockNames());
      amount = view.getAmountInvest();
      weights = view.getWeights(stockNames);
      date = view.getDate();
    }
    model.modifyPortfolioDollarCost(portfolioName, stockNames, amount, weights, date, text);
    if (text) {
      System.out.println("Your portfolio " + portfolioName
          + " has been updated with the new investment strategy.");
    } else {
      dialog.portfolioUpdateSuccess(portfolioName);
    }

  }

  /**
   * Operation to view the performance of a portfolio.
   */
  private static void operationPortfolioPerformance() throws InterruptedException {
    String allPortfolios = model.getAllPortfolioNames();
    DisplayPortfolio portfolioFileNames = new DisplayPortfolioImpl();
    if (allPortfolios.equals("") && portfolioFiles.isEmpty()) {
      if (text) {
        print.portfoliosNotFound();
      } else {
        dialog.portfoliosNotFound();
      }
      return;
    } else {
      if (text) {
        print.displayPortfolios(allPortfolios,
            portfolioFileNames.displayPortfolioFileNames(portfolioFiles));
      }
    }
    String portfolioName;
    if (text) {
      portfolioName = print.readOption();
    } else {
      portfolioName = view.getPortfolio(allPortfolios
          + portfolioFileNames.displayPortfolioFileNames(portfolioFiles));
    }
    Portfolio port = model.getPortfolio(portfolioName);
    boolean portfolioFilesContains = portfolioFiles.containsKey(portfolioName);
    ArrayList<String> contents = null;
    String name = "";
    if (portfolioFilesContains) {
      name = portfolioName;
      contents = (ArrayList<String>) portfolioFiles.get(portfolioName);
    }
    String duration;
    if (text) {
      System.out.println("Would you like to view the performance over the past year, 'n' months,"
          + " or specific month? (year/months/month)");
      duration = print.readOption();
    } else {
      duration = view.compositionOption();
    }
    DisplayPortfolio performanceDisplay = new DisplayPortfolioImpl();
    if (duration.equals("year")) {
      if (text) {
        performanceDisplay.displayPortfolioPerformance(port,
            contents,
            name,
            "2022");
      } else {
        String[][] data = performanceDisplay.portfolioPerformanceData(port, contents, name, "2022");
        view.portfolioPerformanceYear(portfolioName, data[0], data[1]);
      }
    } else if (duration.equals("months")) {
      String timeRangeMonths;
      if (text) {
        System.out.println("Please enter in the months in the format of MM-MM: ");
        timeRangeMonths = print.readOption();
        while (!timeRangeMonths.matches("\\d{2}-\\d{2}")) {
          System.out.println("The months should be in the format MM-MM");
          timeRangeMonths = print.readOption();
          performanceDisplay.displayPortfolioPerformance(port,
              contents, name, timeRangeMonths);
        }
      } else {
        dialog.featureNotImplemented();

      }
    } else if (duration.equals("month")) {
      String timeRangeMonth;
      if (text) {
        System.out.println("Please enter in the month in the format of MM: ");
        timeRangeMonth = print.readOption();
        while (!timeRangeMonth.matches("\\d{2}")) {
          System.out.println("The month should be in the format MM");
          timeRangeMonth = print.readOption();
        }
        performanceDisplay.displayPortfolioPerformance(port,
            contents, name, timeRangeMonth);
      } else {
        dialog.featureNotImplemented();

      }
    }
  }

  private static void operationStartToFinishDollarCostAvg()
      throws FileNotFoundException, InterruptedException {
    String portfolioName;
    DisplayPortfolio portfolioFileNames = new DisplayPortfolioImpl();
    ParseFile parseFile = new ParseFileImpl();
    if (text) {
      print.portfolioNew();
      String option = print.readOption();
      portfolioName = option;
      while (portfolioFiles.get(portfolioName) != null
          || model.getPortfolio(portfolioName) != null) {
        print.portfolioErr();
        option = print.readOption();
        if (option.equals("q")) {
          System.exit(1);
        }
        portfolioName = option;
      }
    } else {
      portfolioName = view.getPortfolioNew(model.getAllPortfolioNames()
          + portfolioFileNames.displayPortfolioFileNames(portfolioFiles));
    }
    model.createPortfolio(portfolioName);
    String[] stockNames;
    String amount;
    String[] weights;
    String[] timeRange;
    String frequency;
    String amountOfFrequency;
    boolean dmy;
    if (text) {
      System.out.println("Please list the stocks you would like "
          + "to buy separated by commas(i.e. AAPL,EBAY,NI): ");
      String option = print.readOption();
      stockNames = option.split(",");
      System.out.println("Please list the amount you would like to invest in dollars(i.e. 2000): ");
      option = print.readOption();
      amount = option;
      int totalWeights = 0;
      weights = new String[stockNames.length];
      while (totalWeights != 100) {
        totalWeights = 0;
        System.out.println("Please lise the corresponding weights(%) "
            + "for each stock(i.e. 20,40,40): ");
        option = print.readOption();
        weights = option.split(",");
        for (String w : weights) {
          totalWeights += Integer.parseInt(w);
        }
        if (totalWeights != 100) {
          System.out.println("The weights should sum to 100.");
        }
      }
      System.out.println("Please list the time-range for the purchases in the format of "
          + "startDate,endDate or startDate only (i.e. 03-12-2001,31-12-2003 03-12-2001): ");
      option = print.readOption();
      timeRange = option.split(",");
      System.out.println("Please specify the frequency at which "
          + "you would like to buy them at(days, months, or years): ");
      option = print.readOption();
      frequency = option;
      System.out.println("Please specify the amount corresponding to the frequency(30, 15, 2): ");
      option = print.readOption();
      amountOfFrequency = option;
    } else {
      stockNames = view.getStocks(parseFile.getAllStockNames());
      amount = view.getAmountInvest();
      weights = view.getWeights(stockNames);
      timeRange = view.getTimeRange().split(",");
      frequency = view.getFrequency();
      amountOfFrequency = view.getAmountFrequency();
    }
    model.buyMultipleStocks(portfolioName, stockNames, amount,
        weights, timeRange, frequency, amountOfFrequency, text);
    if (text) {
      System.out.println("Your dollar-cost averaged portfolio "
          + portfolioName + " has been created.");
    } else {
      dialog.dollarCostSuccess(portfolioName);
    }
  }

  /**
   * Operation to buy a stock.
   *
   * @throws FileNotFoundException when an invalid file path is provided.
   */
  private static void operationBuySellStock() throws FileNotFoundException, InterruptedException {
    ParseFileImpl parseFile = new ParseFileImpl();
    String option = "";
    if (text) {
      if (!model.getAllStockNames().equals("")) {
        print.stocksDisplay(model.getAllStockNames());
      }
      print.stockBuySell();
      option = print.readOption();
      while (!(option.equals("buy")) && !(option.equals("sell"))) {
        print.chooseOption4();
        option = print.readOption();
      }
    } else {
      view.buySellStock(model.getAllStockNames());
      option = view.buySellOption();
      print.printOption(option);
    }
    if (option.equals("buy")) {
      if (text) {
        model.buyStock();
      } else {
        String stockName = view.getStock(parseFile.getAllStockNames());
        String[] datePrice = view.getPriceDate(stockName, model);
        String date = datePrice[0];
        String price = datePrice[1];
        String numShares = view.getNumShares(stockName, date, price);
        model.buyShares(stockName, numShares, date, Float.valueOf(price), text);
        dialog.buySharesSuccess(stockName, numShares);
      }

    } else {
      if (model.getAllStockNames().equals("")) {
        if (text) {
          print.stocksNotFound();
        } else {
          dialog.stocksNotFound();
        }
      } else {
        if (text) {
          model.sellStock();
        } else {
          String stockName = view.getStock(model.getAllStockNames());
          String[] sellInfo = view.sellStock(stockName, model);
          int success = model.sellStock(stockName, sellInfo[0], sellInfo[2]);
          if (success == -1) {
            dialog.invalidNumShares(stockName, sellInfo[2]);
          } else if (success == 0) {
            dialog.sellSharesSuccessRemoved(stockName);
          } else {
            dialog.sellSharesSuccess(stockName, sellInfo[2]);
          }
        }
      }
    }
  }

  /**
   * Operation to create a new portfolio.
   *
   * @throws FileNotFoundException when an invalid file path is provided
   */
  private static void operationNewPortfolio() throws FileNotFoundException, InterruptedException {
    if (text) {
      print.portfolioNew();
      String option = print.readOption();
      String portfolioName = option;
      while (portfolioFiles.get(portfolioName) != null
          || model.getPortfolio(portfolioName) != null) {
        print.portfolioErr();
        option = print.readOption();
        if (option.equals("q")) {
          System.exit(1);
        }
        portfolioName = option;
      }
      model.createPortfolio(portfolioName);
      model.addStock(portfolioName);
      model.savePortfolio(portfolioName);
      print.portfolioCreateSuccess(portfolioName);
    } else {
      String portfolioName = view.getPortfolioNew(model.getAllPortfolioNames());
      model.createPortfolio(portfolioName);
      if (view.addStock()) {
        if (model.getAllStockNames().equals("")) {
          dialog.stocksNotFound();
        } else {
          addStock(portfolioName);
        }
      }
      String path = view.getPath();
      model.savePortfolio(portfolioName, path);
      dialog.portfolioSuccess(portfolioName);
    }
  }

  private static void addStock(String portfolioName) throws InterruptedException {
    if (model.getAllStockNames().equals("")) {
      dialog.stocksNotFound();
    } else {
      String stockName = view.getStock(model.getAllStockNames());
      if (model.addStock(portfolioName, stockName)) {
        dialog.addStockSuccess(stockName, portfolioName);
      } else {
        dialog.stockExists();
      }
    }
  }

  /**
   * Operation to display the composition of a portfolio.
   *
   * @throws FileNotFoundException when an invalid file path is provided
   */
  private static void operationComposition() throws FileNotFoundException, InterruptedException {
    String allPortfolios = model.getAllPortfolioNames();
    DisplayPortfolio portfolioFileNames = new DisplayPortfolioImpl();
    DisplayPortfolio newPortfolioDisplay = new DisplayPortfolioImpl();
    if (text) {
      if (allPortfolios.equals("") && portfolioFiles.isEmpty()) {
        print.portfoliosNotFound();
        return;
      } else {
        print.displayPortfolios(allPortfolios,
            portfolioFileNames.displayPortfolioFileNames(portfolioFiles));
      }
      String option = print.readOption();
      System.out.println("Would you like to query the composition on a specific date? (y/n)");
      String query = print.readOption();
      if (query.equals("n")) {
        if (portfolioFileNames.displayPortfolioFileNames(portfolioFiles).contains(option)) {
          newPortfolioDisplay.displayContents((ArrayList<String>) portfolioFiles.get(option));
        } else if (allPortfolios.contains(option)) {
          newPortfolioDisplay.displayContents(model.getPortfolio(option).examine());
        } else {
          print.portfolioNotFound(option);
          operationComposition();
        }
      } else if (query.equals("y")) {
        String date = model.getDate();
        if (portfolioFileNames.displayPortfolioFileNames(portfolioFiles).contains(option)) {
          newPortfolioDisplay.displayContents((ArrayList<String>) portfolioFiles.get(option), date);
        } else if (allPortfolios.contains(option)) {
          newPortfolioDisplay.displayContents(model.getPortfolio(option).examine(), date);
        } else {
          print.portfolioNotFound(option);
          operationComposition();
        }
      }
    } else {
      String portfolioName = view.getPortfolio(allPortfolios
          + portfolioFileNames.displayPortfolioFileNames(portfolioFiles));
      if (!portfolioName.equals("")) {
        if (portfolioFileNames.displayPortfolioFileNames(portfolioFiles).contains(portfolioName)) {
          String rowData = newPortfolioDisplay.getContents(
              (ArrayList<String>) portfolioFiles.get(portfolioName));
          String[] columns = newPortfolioDisplay.columnNames();
          view.viewComposition(portfolioName, model.parseContents(rowData), columns);
        } else if (allPortfolios.contains(portfolioName)) {
          String rowData = model.getPortfolio(portfolioName).examine();
          String[] columns = newPortfolioDisplay.columnNames();
          view.viewComposition(portfolioName, model.parseContents(rowData), columns);
        }
      }
    }
  }

  /**
   * Operation to view the value of a portfolio on a given date.
   *
   * @throws FileNotFoundException when an invalid file path is provided
   */
  private static void operationValueOfPortfolio()
      throws FileNotFoundException, InterruptedException {
    String allPortfolios = model.getAllPortfolioNames();
    DisplayPortfolio portfolioFileNames = new DisplayPortfolioImpl();
    if ((allPortfolios.equals("")) && (portfolioFiles.isEmpty())) {
      if (text) {
        print.portfoliosNotFound();
      } else {
        dialog.portfoliosNotFound();
      }
      return;
    } else {
      String portfolioName;
      String date;
      boolean portfolioFilesContains;
      boolean investorContains;
      if (text) {
        print.displayPortfolios(model.getAllPortfolioNames(),
            portfolioFileNames.displayPortfolioFileNames(portfolioFiles));
        String option = print.readOption();
        portfolioFilesContains = portfolioFiles.get(option) != null;
        investorContains = model.getPortfolio(option) != null;
        while (!portfolioFilesContains && !investorContains) {
          print.portfolioInvalid();
          option = print.readOption();
        }
        portfolioName = option;
        date = model.getDate();
      } else {
        portfolioName = view.getPortfolio(model.getAllPortfolioNames() +
            portfolioFileNames.displayPortfolioFileNames(portfolioFiles));
        date = view.getDate();
        portfolioFilesContains = portfolioFiles.get(portfolioName) != null;
        investorContains = model.getPortfolio(portfolioName) != null;
      }
      float totalValueOfPortfolio = model.getTotalValueOfPortfolio(portfolioFilesContains,
          investorContains, portfolioFiles, portfolioName, date);
      if (text) {
        print.value(date, totalValueOfPortfolio);
      } else {
        view.valueOfPortfolio(portfolioName, date, totalValueOfPortfolio);
      }
    }
  }

  /**
   * Determine the cost basis by a specific date.
   *
   * @throws FileNotFoundException if portfolio files are not found.
   */
  private static void operationGetCostBasis() throws FileNotFoundException, InterruptedException {
    String allPortfolios = model.getAllPortfolioNames();
    DisplayPortfolio portfolioFileNames = new DisplayPortfolioImpl();
    if ((allPortfolios.equals("")) && (portfolioFiles.isEmpty())) {
      if (text) {
        print.portfoliosNotFound();
      } else {
        dialog.portfoliosNotFound();
      }
      return;
    }
    String portfolioName;
    String date;
    if (text) {
      print.displayPortfolios(model.getAllPortfolioNames(),
          portfolioFileNames.displayPortfolioFileNames(portfolioFiles));
      portfolioName = print.readOption();
      date = model.getDate();
    } else {
      portfolioName = view.getPortfolio(model.getAllPortfolioNames() +
          portfolioFileNames.displayPortfolioFileNames(portfolioFiles));
      date = view.getDate();
    }
    boolean loadedPortfolio = portfolioFiles.containsKey(portfolioName);
    String totalFees = "";
    if (text && loadedPortfolio) {
      System.out.println("Please specify your total commission fees for this portfolio: ");
      totalFees = print.readOption();
    }
    if (text) {
      print.costBasis(date, model.getCostBasis(portfolioName, date,
          portfolioFiles, loadedPortfolio, totalFees));
    } else {
      view.costBasisPortfolio(portfolioName, date, model.getCostBasis(portfolioName, date,
          portfolioFiles, loadedPortfolio, "0"));
    }
  }

  /**
   * Operation to load in a portfolio.
   *
   * @throws FileNotFoundException when an invalid file path is provided
   */
  private static void operationPersistPortfolio() throws
      FileNotFoundException, InterruptedException {
    String portfolioName = "";
    if (text) {
      print.portfolioLoad();
      portfolioName = print.readOption();
      while (portfolioFiles.get(portfolioName) != null
          || model.getPortfolio(portfolioName) != null) {
        print.portfolioErr();
        portfolioName = print.readOption();
      }
    } else {
      portfolioName = view.getPortfolioNew(model.getAllPortfolioNames());
    }

    ParseFile newPortfolioFile = new ParseFileImpl();
    boolean validPath = false;
    String portfolioPath = "";

    while (!validPath) {
      try {
        if (text) {
          print.path();
          portfolioPath = print.readOption();
          newPortfolioFile.loadFile(portfolioPath);
          validPath = true;
        } else {
          portfolioPath = view.getPath();
          newPortfolioFile.loadFile(portfolioPath);
          validPath = true;
        }
      } catch (FileNotFoundException e) {
        if (text) {
          print.pathInvalid();
        } else {
          dialog.invalidPath();
        }
      }
    }

    ArrayList<String> fileContents = newPortfolioFile.getPortfolioContents();
    portfolioFiles.put(portfolioName, fileContents);
    if (text) {
      print.portfolioLoadSuccess(portfolioName);
    } else {
      dialog.portfolioLoadSuccess(portfolioName);
    }
  }

  private static void operationAddStockPortfolio()
      throws InterruptedException, FileNotFoundException {
    if (text) {
      System.out.println(model.getAllPortfolioNames());
      print.portfolio();
      String portfolioName = print.readOption();
      model.buyStock(portfolioName);
      model.savePortfolio(portfolioName);
      print.portfolioCreateSuccess(portfolioName);
    } else {
      DisplayPortfolio portfolioFileNames = new DisplayPortfolioImpl();
      String portfolioName = view.getPortfolio(model.getAllPortfolioNames()

      );
      if (!portfolioName.equals("")) {
        ParseFile parseFile = new ParseFileImpl();
        String stockName = view.getStock(parseFile.getAllStockNames());
        String[] datePrice = view.getPriceDate(stockName, model);
        String date = datePrice[0];
        String price = datePrice[1];
        String numShares = view.getNumShares(stockName, date, price);
        model.buyShares(stockName, numShares, date, Float.valueOf(price), text);
        model.addStockModified(portfolioName, stockName);
        dialog.buySharesSuccess(stockName, numShares);
        String path = view.getPath();
        model.savePortfolio(portfolioName, path);
        dialog.portfolioSaveSuccess(portfolioName);
      }
    }
  }

}