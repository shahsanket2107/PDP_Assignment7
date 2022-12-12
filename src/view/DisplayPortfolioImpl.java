package view;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

import model.Investor;
import model.InvestorImpl;
import model.Portfolio;
import model.StocksAPI;
import model.StocksAPIAlphaVantageImpl;


/**
 * Implementation of the DisplayPortfolio interface.
 */
public class DisplayPortfolioImpl implements DisplayPortfolio {

  private Investor stockPrices = new InvestorImpl();
  private StocksAPI apiAlphaVantage = new StocksAPIAlphaVantageImpl();
  private String[] months = new String[]{"01", "02", "03", "04", "05", "06",
          "07", "08", "09", "10", "11"};
  private String[] days = new String[]{"01", "02", "03", "04", "05", "06",
          "07", "08", "09", "10", "11", "12", "13", "14", "15", "16",
          "17", "18", "19", "20", "21", "22", "23", "24", "25", "26",
          "27", "28", "29", "30"};
  private String[] monthsString = new String[]{"Jan", "Feb", "Mar", "Apr", "May", "Jun",
          "Jul", "Aug", "Sep", "Oct", "Nov"};

  private String[] calculatePerformanceStars(Portfolio portfolio,
                                             ArrayList<InvestorImpl.InvestorStock> stocks,
                                             String[] months, String[] stars) {
    for (int j = 0; j < months.length; j++) {
      float totalValue = 0;
      for (int i = 0; i < stocks.size(); i++) {
        String stockName = stocks.get(i).name;
        int numShares = (int) portfolio.getShares(stockName);
        String price = apiAlphaVantage.getPrice(stockName,
                "2022-" + months[j], "monthly");
        if (price != null) {
          totalValue += (Float.parseFloat(price) * numShares);
        }
      }
      stars[j] = "*".repeat((int) (totalValue / 1000));
    }
    return stars;
  }

  private String[] calculatePerformanceStarsDaily(Portfolio portfolio,
                                                  ArrayList<InvestorImpl.InvestorStock> stocks,
                                                  String[] months,
                                                  String[] starsDaily,
                                                  String[] days,
                                                  int monthIndex) {
    for (int j = 0; j < days.length; j++) {
      float totalValue = 0;
      for (int i = 0; i < stocks.size(); i++) {
        String stockName = stocks.get(i).name;
        int numShares = (int) portfolio.getShares(stockName);
        String price = apiAlphaVantage.getPrice(stockName,
                "2022-" + months[monthIndex] + "-" + days[j],
                "daily");
        if (Float.parseFloat(price) == -1) {
          price = "1";
        }
        totalValue += (Float.parseFloat(price) * numShares);
      }
      starsDaily[j] = "*".repeat((int) (totalValue / 1000));
    }
    return starsDaily;
  }

  private String[] calculatePerformanceStarsPort(ArrayList<String> stocksPort,
                                                 ArrayList<String> numSharesArr,
                                                 String[] months, String[] stars) {
    for (int j = 0; j < months.length; j++) {
      float totalValue = 0;
      for (int i = 0; i < stocksPort.size(); i++) {
        String stockName = stocksPort.get(i);
        int numShares = Integer.parseInt(numSharesArr.get(i));
        String price = apiAlphaVantage.getPrice(stockName,
                "2022-" + months[j], "monthly");
        totalValue += (Float.parseFloat(price) * numShares);
      }
      stars[j] = "*".repeat((int) (totalValue / 1000));
    }
    return stars;
  }

  private String[] calculatePerformanceStarsDailyPort(ArrayList<String> stocksPort,
                                                      ArrayList<String> numSharesArr,
                                                      String[] months, String[] starsDaily,
                                                      String[] days,
                                                      int monthIndex) {
    for (int j = 0; j < days.length; j++) {
      float totalValue = 0;
      for (int i = 0; i < stocksPort.size(); i++) {
        String stockName = stocksPort.get(i);
        int numShares = Integer.parseInt(numSharesArr.get(i));
        String price = apiAlphaVantage.getPrice(stockName,
                "2022-" + months[monthIndex] + "-" + days[j],
                "daily");
        if (Float.parseFloat(price) == -1) {
          price = "1";
        }
        totalValue += (Float.parseFloat(price) * numShares);
      }
      starsDaily[j] = "*".repeat((int) (totalValue / 1000));
    }
    return starsDaily;

  }

  @Override
  public String[][] portfolioPerformanceData(Portfolio portfolio, ArrayList<String> contents,
                                             String namePort, String timeRange) {
    String[] stars = new String[11];
    String[] starsDaily = new String[30];
    String[][] result = new String[2][];
    String name;
    ArrayList<InvestorImpl.InvestorStock> stocks = null;
    ArrayList<String> stocksPort = new ArrayList<>();
    ArrayList<String> numShares = new ArrayList<>();
    if (contents == null) {
      name = portfolio.getName();
      stocks = portfolio.getList();
      calculatePerformanceStars(portfolio, stocks, months, stars);
    } else {
      name = namePort;
      for (String content : contents) {
        String[] currStrings = content.split(",");
        int j = 0;
        while (j < currStrings.length) {
          stocksPort.add(currStrings[j]);
          numShares.add(currStrings[j + 3]);
          j += 4;
        }
      }
      calculatePerformanceStarsPort(stocksPort, numShares, months, stars);
    }

    if (timeRange.equals("2022")) {
      String[] names = new String[monthsString.length];
      String[] data = new String[monthsString.length];
      for (int i = 0; i < monthsString.length; i++) {
        names[i] = monthsString[i] + " 2022";
        data[i] = stars[i];
      }
      result[0] = names;
      result[1] = data;
      return result;
    } else if (timeRange.matches("\\d{2}-\\d{2}")) {
      String[] monthsRange = timeRange.split("-");
      String monthBeginning = monthsRange[0];
      String monthEnd = monthsRange[1];
      ArrayList<Integer> rangeVals = new ArrayList<>();
      String[] names = new String[rangeVals.size()];
      String[] data = new String[rangeVals.size()];
      for (int i = Integer.parseInt(monthBeginning) - 1; i < Integer.parseInt(monthEnd); i++) {
        rangeVals.add(i);
      }
      for (int i = 0; i < rangeVals.size(); i++) {
        names[i] = monthsString[rangeVals.get(i)] + " 2022";
        data[i] = stars[rangeVals.get(i)];
      }
      result[0] = names;
      result[1] = data;
      return result;
    } else if (timeRange.matches("\\d{2}")) {
      int monthIndex = Integer.parseInt(timeRange) - 1;
      if (contents == null) {
        calculatePerformanceStarsDaily(portfolio, stocks, months,
                starsDaily, days, monthIndex);
      } else {
        calculatePerformanceStarsDailyPort(stocksPort, numShares, months,
                starsDaily, days, monthIndex);
      }
      String[] names = new String[days.length];
      String[] data = new String[days.length];
      for (int i = 0; i < days.length; i++) {
        names[i] = months[monthIndex] + "/" + days[i] + "/2022";
        data[i] = starsDaily[i];
      }
      result[0] = names;
      result[1] = data;
      return result;
    }
    return null;
  }

  @Override
  public void displayPortfolioPerformance(Portfolio portfolio, ArrayList<String> contents,
                                          String namePort, String timeRange) {
    String[] stars = new String[11];
    String[] starsDaily = new String[30];
    String name = "";
    ArrayList<InvestorImpl.InvestorStock> stocks = null;
    ArrayList<String> stocksPort = new ArrayList<>();
    ArrayList<String> numShares = new ArrayList<>();
    if (contents == null) {
      name = portfolio.getName();
      stocks = portfolio.getList();
      calculatePerformanceStars(portfolio, stocks, months, stars);
    } else {
      name = namePort;
      for (String content : contents) {
        String[] currStrings = content.split(",");
        int j = 0;
        while (j < currStrings.length) {
          stocksPort.add(currStrings[j]);
          numShares.add(currStrings[j + 3]);
          j += 4;
        }
      }
      calculatePerformanceStarsPort(stocksPort, numShares, months, stars);
    }

    if (timeRange.equals("2022")) {
      System.out.println("Performance of portfolio " + name + " over the past year(2022)");
      for (int i = 0; i < monthsString.length; i++) {
        System.out.println(monthsString[i] + " 2022: " + stars[i]);
      }
      System.out.println("Scale: * = $1000");
    } else if (timeRange.matches("\\d{2}-\\d{2}")) {
      String[] monthsRange = timeRange.split("-");
      String monthBeginning = monthsRange[0];
      String monthEnd = monthsRange[1];
      System.out.println("Performance of portfolio " + name + " from " + monthBeginning + "/2022"
              + " to " + monthEnd + "/2022");
      ArrayList<Integer> rangeVals = new ArrayList<>();
      for (int i = Integer.parseInt(monthBeginning) - 1; i < Integer.parseInt(monthEnd); i++) {
        rangeVals.add(i);
      }
      for (int i = 0; i < rangeVals.size(); i++) {
        System.out.println(monthsString[rangeVals.get(i)] + " 2022: " + stars[rangeVals.get(i)]);
      }
      System.out.println("Scale: * = $1000");
    } else if (timeRange.matches("\\d{2}")) {
      int monthIndex = Integer.parseInt(timeRange) - 1;
      System.out.println("Performance of portfolio " + name + " during the month of "
              + monthsString[monthIndex]);
      if (contents == null) {
        calculatePerformanceStarsDaily(portfolio, stocks, months,
                starsDaily, days, monthIndex);
      } else {
        calculatePerformanceStarsDailyPort(stocksPort, numShares, months,
                starsDaily, days, monthIndex);
      }
      for (int i = 0; i < days.length; i++) {
        System.out.println(months[monthIndex] + "/" + days[i] + "/2022: " + starsDaily[i]);
      }
      System.out.println("Scale: * = $1000");
    }
  }

  @Override
  public String getContents(ArrayList<String> contents) {
    String result = "";
    for (int i = 0; i < contents.size(); i++) {
      String[] currStrings = contents.get(i).split(",");
      for (int j = 0; j < currStrings.length; j++) {
        result += currStrings[j];
        result += " ";
      }
    }
    return result;
  }

  @Override
  public void displayContents(ArrayList<String> contents) {
    System.out.printf("%-20s %20s %20s %20s %n", "TICKER|", "DATE PURCHASED|",
            "STOCK PRICE|", "# OF SHARES|");
    System.out.print("---------------------------------------------"
            + "--------------------------------------");
    for (int i = 0; i < contents.size(); i++) {
      System.out.println();
      String[] currStrings = contents.get(i).split(",");
      System.out.printf("%-20s", currStrings[0]);
      for (int j = 1; j < currStrings.length; j++) {
        if ((j % 4 == 0)) {
          System.out.printf("%-20s", currStrings[j]);
        } else {
          System.out.printf("%20s", currStrings[j]);
        }
      }
    }
    System.out.println();
  }

  private boolean dateCompare(String date1, String date2) throws ParseException {
    SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
    Date temp_date1 = sdf.parse(date1);
    Date temp_date2 = sdf.parse(date2);
    if (temp_date1.compareTo(temp_date2) >= 0) {
      return true;
    } else {
      return false;
    }
  }

  @Override
  public void displayContents(ArrayList<String> contents, String date) {
    System.out.println("The composition of the portfolio for the date: " + date);
    System.out.printf("%-20s %20s %20s %20s %n", "TICKER|", "DATE|", "QUANTITY|", "STOCK PRICE|");
    System.out.print("-------------------------------------------------------------------------");
    for (int i = 0; i < contents.size(); i++) {
      System.out.println();
      String[] currStrings = contents.get(i).split(",");
      String numShares = currStrings[3];
      String tickerSymbol = currStrings[0];
      String date1 = currStrings[1];
      boolean check = false;
      try {
        check = dateCompare(date, date1);
      } catch (ParseException e) {
        e.printStackTrace();
      }
      if (check) {
        System.out.printf("%-20s", tickerSymbol);
        System.out.printf("%20s", date);
        System.out.printf("%20s", numShares);
        try {
          double price = Double.parseDouble(stockPrices.getPrice(tickerSymbol, date));
          System.out.printf("%20s", String.format("%.02f", price));
        } catch (Exception e) {
          e.printStackTrace();
        }
      }
    }
    System.out.println();
  }

  @Override
  public void displayContents(String contents, String date) {
    System.out.println("The composition of the portfolio for the date: " + date);
    System.out.printf("%-20s %20s %20s %20s %n", "TICKER|", "DATE|", "QUANTITY|", "STOCK PRICE|");
    System.out.print("-------------------------------------------------------------------------");
    System.out.println();
    String[] splitContents = contents.trim().split("\\s+");
    ArrayList<String> tickerSymbols = new ArrayList<>();
    ArrayList<String> quantity = new ArrayList<>();
    int j = 0;
    while (j < splitContents.length) {
      tickerSymbols.add(splitContents[j]);
      j += 3;
      quantity.add(splitContents[j]);
      j += 1;
    }
    int i = 0;
    while (i < tickerSymbols.size()) {
      System.out.printf("%-20s", tickerSymbols.get(i));
      System.out.printf("%20s", date);
      System.out.printf("%20s", quantity.get(i));
      try {
        double price = Double.parseDouble(stockPrices.getPrice(tickerSymbols.get(i), date));
        System.out.printf("%20s", String.format("%.02f", price));
      } catch (Exception e) {
        e.printStackTrace();
      }
      System.out.println();
      i += 1;
    }
    System.out.println();

  }

  @Override
  public void displayContents(String contents) {
    System.out.printf("%-20s %20s %20s %20s %n", "TICKER|", "DATE PURCHASED|",
            "STOCK PRICE|", "# OF SHARES|");
    System.out.print("---------------------------------------------------"
            + "--------------------------------");
    System.out.println();
    String[] splitContents = contents.trim().split("\\s+");
    System.out.printf("%-20s", splitContents[0]);
    for (int i = 1; i < splitContents.length; i++) {
      if (i % 4 == 0) {
        System.out.println();
        System.out.printf("%-20s", splitContents[i]);
      } else {
        System.out.printf("%20s", splitContents[i]);

      }
    }
    System.out.println();

  }

  @Override
  public String[] columnNames() {
    String[] col = new String[4];
    col[0] = "TICKER";
    col[1] = "DATE PURCHASED";
    col[2] = "STOCK PRICE";
    col[3] = "# OF SHARES";
    return col;
  }

  @Override
  public String displayPortfolioFileNames(Map portfolios) {
    String fileNames = "";
    for (Object portfolioName : portfolios.keySet()) {
      fileNames += portfolioName + "\n";
    }
    return fileNames;
  }
}
