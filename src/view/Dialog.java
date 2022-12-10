package view;

/**
 * Dialog class. Represents dialog with the user through the GUI.
 */
public interface Dialog {

  /**
   * No stocks found under investor profile.
   */
  void stocksNotFound();

  /**
   * Portfolios not found.
   */
  void portfoliosNotFound();

  /**
   * Invalid date entered.
   */
  void invalidDate();

  /**
   * Invalid number entered.
   */
  void invalidNumber();

  /**
   * Invalid path entered.
   */
  void invalidPath();

  /**
   * No price on the given date.
   */
  void noPriceOnSelectedDate();

  /**
   * Successfully bought shares of a stock.
   *
   * @param stockName the name of the stock
   * @param numShares the number of shares bought
   */
  void buySharesSuccess(String stockName, String numShares);

  /**
   * Stock doesn't exist under investor profile.
   *
   * @param stockName the name of the stock
   * @param numShares the number of shares trying to be bought
   */
  void invalidNumShares(String stockName, String numShares);

  /**
   * Weight can't be 0.
   */
  void invalidWeightZero();

  /**
   * Invalid weight entered.
   */
  void invalidWeight();

  /**
   * Total must sum to 100.
   */
  void invalidWeightTotal();

  /**
   * Successfully sold shares.
   *
   * @param stockName the name of the stock.
   * @param numShares the number of shares sold successfully.
   */
  void sellSharesSuccess(String stockName, String numShares);

  /**
   * Sold all shares of given stock.
   *
   * @param stockName the name of the stock
   */
  void sellSharesSuccessRemoved(String stockName);

  /**
   * Stock already exists.
   */
  void stockExists();

  /**
   * Successfully added stock to portfolio.
   *
   * @param stockName     name of the stock
   * @param portfolioName name of the portfolio stock added to
   */
  void addStockSuccess(String stockName, String portfolioName);

  /**
   * Portfolio already exists.
   */
  void portfolioExists();

  /**
   * Portfolio creation success.
   *
   * @param portfolioName name of the portfolio created.
   */
  void portfolioSuccess(String portfolioName);

  /**
   * Successfully loaded portfolio.
   *
   * @param portfolioName name of portfolio loaded
   */
  void portfolioLoadSuccess(String portfolioName);

  /**
   * Feature hasn't been implemented yet.
   */
  void featureNotImplemented();

  /**
   * Successfully saved portfolio.
   *
   * @param portfolioName name of portfolio saved
   */
  void portfolioSaveSuccess(String portfolioName);

  /**
   * Dollar cost average success.
   *
   * @param portfolioName name of the portfolio
   */
  void dollarCostSuccess(String portfolioName);

  /**
   * Successfully updated portfolio.
   *
   * @param portfolioName name of portfolio.
   */
  void portfolioUpdateSuccess(String portfolioName);

}
