package model;

/**
 * Represents parsing a Stocks-related API for the price or volume.
 */
public interface StocksAPI {
  /**
   * Get the price of a stock on a given date.
   * @param stockName the name of the stock
   * @param date the date to get the price at
   * @param time time to get the price for
   * @return the price of the stock on the given date, -1 if the date is invalid
   */
  String getPrice(String stockName, String date, String time);

  /**
   * Get the volume of a stock on a given date.
   * @param stockName the name of the stock
   * @param date the date to get the volume at
   * @param time time to get the volume for
   * @return the volume of the stock on the given date, -1 if the date is invalid
   */
  String getVolume(String stockName, String date, String time);
}
