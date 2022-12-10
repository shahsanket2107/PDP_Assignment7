package controller;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Implementation of the StocksAPIController interface.
 * Endpoint to receive data from the Alpha Vantage API.
 */
public class StockAPIAlphaVantageControllerImpl implements StocksAPIController {
  private final String apiKey = "QLWHWRGDCN87D8TT";

  /**
   * Method to query API.
   *
   * @param stockSymbol the ticker symbol to query for.
   * @param time        the time to query the API for.
   * @return output of query.
   */
  public String queryAPI(String stockSymbol, String time) {

    URL url = null;

    try {
      String timeSpecifier = "";
      if (time.equals("daily")) {
        timeSpecifier = "TIME_SERIES_DAILY";
      } else if (time.equals("monthly")) {
        timeSpecifier = "TIME_SERIES_MONTHLY";
      }
      url = new URL("https://www.alphavantage"
              + ".co/query?function="
              + timeSpecifier
              + "&outputsize=full"
              + "&symbol"
              + "=" + stockSymbol + "&apikey=" + apiKey + "&datatype=csv");
    } catch (MalformedURLException e) {
      throw new RuntimeException("the alphavantage API has either changed or "
              + "no longer works");
    }

    InputStream in = null;
    StringBuilder output = new StringBuilder();

    try {
      in = url.openStream();
      int b;

      while ((b = in.read()) != -1) {
        output.append((char) b);
      }
    } catch (IOException e) {
      throw new IllegalArgumentException("No price data found for " + stockSymbol);
    }
    return output.substring(36);
  }
}
