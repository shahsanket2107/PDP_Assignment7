package view;

/**
 * This interface represents a statement printed to the user.
 */
public interface PrintStatement {

  String readOption();

  /**
   * Ask the user for a valid menu option(1-8).
   */
  void chooseOption1();

  /**
   * Ask the user for a valid choice(y/n).
   */
  void chooseOption2();

  /**
   * Ask the user for a valid option.
   */
  void chooseOption3();

  /**
   * Ask the user for a valid options(buy/sell).
   */
  void chooseOption4();

  /**
   * Ask the user for a valid options(gui/text).
   */
  void chooseOption5();

  /**
   * Ask the user to enter in name of a stock.
   */
  void newStock();

  /**
   * Ask the user to choose a stock.
   */
  void chooseStock();

  /**
   * Ask the user if they want to add a stock(y/n).
   */
  void addStock();

  /**
   * Ask the user to choose a stock to sell.
   */
  void sellStock1();

  /**
   * Ask the user to enter in date of stock to sell.
   */
  void sellStock2();

  /**
   * Ask the user for a number of shares to sell.
   */
  void sellStock3();

  /**
   * Print stocks not found for investor.
   */
  void stocksNotFound();

  /**
   * Display the names of the stocks.
   *
   * @param stockNames the names of the stocks
   */
  void stocksDisplay(String stockNames);

  /**
   * Ask the user if they want to buy or sell a stock.
   */
  void stockBuySell();

  /**
   * Tell user the stock already exists in their portfolio.
   *
   * @param stockName     name of stock
   * @param portfolioName name of portfolio
   */
  void stockErr1(String stockName, String portfolioName);

  /**
   * Tell user stock doesn't exist for investor.
   *
   * @param stockName name of stock
   */
  void stockErr2(String stockName);

  /**
   * Tell user the investor does not have n number of shares for a stock.
   *
   * @param stockName name of stock
   * @param n         the number of shares for stock
   */
  void stockErr3(String stockName, String n);

  /**
   * Added in a stock to a portfolio successfully.
   *
   * @param stockName     name of stock
   * @param portfolioName name of portfolio
   */
  void stockSuccess(String stockName, String portfolioName);

  /**
   * Ask the user to enter in number of shares to buy.
   */
  void shares1();

  /**
   * Ask the user to enter in number of shares to sell.
   */
  void shares2();

  /**
   * Enforce no fractional shares.
   */
  void sharesErr1();

  /**
   * Date doesn't match investor data for selling a stock.
   */
  void sharesErr2();

  /**
   * Tell the user they bought n number of shares of a stock.
   *
   * @param numShares number of shares bought
   * @param stockName the name of the stock
   */
  void sharesSuccess(String numShares, String stockName);

  /**
   * Ask the user for a new portfolio name.
   */
  void portfolioNew();

  /**
   * Ask the user for a portfolio name to load in.
   */
  void portfolioLoad();

  /**
   * Tell the user a portfolio with the given name exists already.
   */
  void portfolioErr();

  /**
   * Tell the user the portfolio is invalid.
   */
  void portfolioInvalid();

  /**
   * Successfully created a portfolio.
   *
   * @param portfolioName name of portfolio created
   */
  void portfolioCreateSuccess(String portfolioName);

  /**
   * Successfully loaded in a portfolio.
   *
   * @param portfolioName name of the portfolio loaded in
   */
  void portfolioLoadSuccess(String portfolioName);

  /**
   * Tell the user they don't have any portfolios.
   */
  void portfoliosNotFound();

  /**
   * Tell the user the portfolio given does not exist.
   *
   * @param portfolioName the name of the portfolio
   */
  void portfolioNotFound(String portfolioName);

  /**
   * Display local and saved portfolios.
   *
   * @param local local portfolios names
   * @param saved saved portfolios names
   */
  void displayPortfolios(String local, String saved);

  /**
   * Display the value of a portfolio on a given date.
   *
   * @param date  the date
   * @param value the value of the portfolio on the date
   */
  void value(String date, float value);

  /**
   * Ask the user for a name to save their portfolio with.
   */
  void portfolioSave();

  /**
   * Ask the user to enter a valid date.
   */
  void enterDate();

  /**
   * Tell the user their entered date is invalid.
   */
  void dateInvalid();

  /**
   * Ask the user for a valid path.
   */
  void path();

  /**
   * Tell the user their path is invalid.
   */
  void pathInvalid();

  /**
   * Ask the user for a path to save their portfolio.
   */
  void pathSave();

  /**
   * Display the current price of a stock.
   *
   * @param stockName stock name
   * @param currPrice current price of stock
   */
  void currPrice(String stockName, String currPrice);

  /**
   * Ask the user which price they want to select.
   */
  void selectPrice();

  /**
   * Tell the user stock data does not exist for a given stock and date.
   *
   * @param stockName the name of the stock
   * @param date      the date
   */
  void stockDataErr(String stockName, String date);

  /**
   * Display the price of stock on a given date.
   *
   * @param stockName the name of the stock
   * @param date      the date to display price n
   * @param price     the price of the stock on the date
   */
  void price(String stockName, String date, String price);

  /**
   * Display the cost basis of a portfolio on a given date.
   *
   * @param date  the date to display the cost basis on
   * @param value the total value on the given date
   */
  void costBasis(String date, float value);

  /**
   * Asks user to enter commission fee amount.
   */
  void commission();

  /**
   * Error if commission fee includes characters that aren't numeric.
   */
  void commissionInvalid();

  /**
   * Asks user if they want to use a GUI or text-based interface.
   */
  void guiOrTextBased();

  void portfolio();

  /**
   * Print option selected.
   */
  void printOption(String option);

  /**
   * Prints message inputted as output.
   * @param message the message to be displayed
   */
  void printMessage(String message);
}
