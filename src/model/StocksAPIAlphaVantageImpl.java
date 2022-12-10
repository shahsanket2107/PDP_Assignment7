package model;

import controller.StockAPIAlphaVantageControllerImpl;
import controller.StocksAPIController;

/**
 * Implementation of the StocksAPI interface.
 * Parse the data received from the controller.
 * Get the price on a specific date.
 * Get the volume on a specific date.
 */
public class StocksAPIAlphaVantageImpl implements StocksAPI {
  StocksAPIController apiAlphaVantage = new StockAPIAlphaVantageControllerImpl();

  private String[] parseAPIData(String stockName, String date, String time) {
    String data = apiAlphaVantage.queryAPI(stockName, time);
    String[] parsedData = new String[3];
    String[] splitContents = data.trim().split("\\s+\n");
    if (time.equals("daily")) {
      if (!data.contains(date)) {
        return new String[1];
      }
    }
    for (String splitContent : splitContents) {
      if (splitContent.startsWith(date)) {
        String[] parsedContents = splitContent.trim().split(",");
        parsedData[0] = parsedContents[0]; // the date
        parsedData[1] = parsedContents[4]; // the closing price
        parsedData[2] = parsedContents[5]; // the stock volume on the given date
      }
    }

    return parsedData;
  }

  @Override
  public String getPrice(String stockName, String date, String time) {
    String[] parsedAPIData = parseAPIData(stockName, date, time);
    if (parsedAPIData.length == 1) {
      return "-1";
    }
    return parsedAPIData[1];
  }

  @Override
  public String getVolume(String stockName, String date, String time) {
    String[] parsedAPIData = parseAPIData(stockName, date, time);
    if (parsedAPIData.length == 1) {
      return "-1";
    }
    return parsedAPIData[2];
  }
}
