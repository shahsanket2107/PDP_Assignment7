package controller;

/**
 * Represents a Stocks-related API Controller.
 */
public interface StocksAPIController {
  /**
   * Query the Alpha Vantage API on a ticker symbol.
   *
   * @param stockSymbol the ticker symbol to query for.
   * @param time        the time to query the API for.
   * @return the data associated with a stock symbol as a string.
   */
  String queryAPI(String stockSymbol, String time);
}
