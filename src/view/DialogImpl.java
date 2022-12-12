package view;


import javax.swing.*;

/**
 * Interface for dialog display boxes.
 */
public class DialogImpl implements Dialog {

  private JFrame frame;

  public DialogImpl(JFrame frame) {
    this.frame = frame;
  }

  /**
   * Stocks are not found under investor profile.
   */
  public void stocksNotFound() {
    JOptionPane.showMessageDialog(frame,
            "No stocks found under investor profile.",
            "Inane warning",
            JOptionPane.WARNING_MESSAGE);
  }

  /**
   * Portfolios are not found under investor profile.
   */
  public void portfoliosNotFound() {
    JOptionPane.showMessageDialog(frame,
            "No portfolios found under investor profile.",
            "Inane warning",
            JOptionPane.WARNING_MESSAGE);
  }


  /**
   * Stock already exists under investor profile.
   */
  public void stockExists() {
    JOptionPane.showMessageDialog(frame,
            "Stock already exists in investor profile.",
            "Inane warning",
            JOptionPane.WARNING_MESSAGE);
  }

  /**
   * Portfolio already exists under investor profile.
   */
  public void portfolioExists() {
    JOptionPane.showMessageDialog(frame,
            "Portfolio name already exists in investor profile.",
            "Inane warning",
            JOptionPane.WARNING_MESSAGE);
  }

  /**
   * Invalid date format.
   */
  public void invalidDate() {
    JOptionPane.showMessageDialog(frame,
            "Invalid date format.",
            "Inane warning",
            JOptionPane.ERROR_MESSAGE);
  }

  /**
   * Invalid number format.
   */
  public void invalidNumber() {
    JOptionPane.showMessageDialog(frame,
            "Please enter valid number containing only digits.",
            "Inane warning",
            JOptionPane.ERROR_MESSAGE);
  }

  /**
   * Invalid number of shares.
   *
   * @param stockName name of stock.
   * @param numShares number of shares.
   */
  public void invalidNumShares(String stockName, String numShares) {
    JOptionPane.showMessageDialog(frame,
            "Investor does not own " + numShares + " shares of "
                    + stockName,
            "Inane warning",
            JOptionPane.ERROR_MESSAGE);
  }

  /**
   * Invalid file path.
   */
  public void invalidPath() {
    JOptionPane.showMessageDialog(frame,
            "Invalid path. Please enter name of valid path.",
            "Inane warning",
            JOptionPane.ERROR_MESSAGE);
  }

  /**
   * Invalid weight where the weight is zero.
   */
  public void invalidWeightZero() {
    JOptionPane.showMessageDialog(frame,
            "Weight cannot be 0",
            "Inane warning",
            JOptionPane.ERROR_MESSAGE);
  }

  /**
   * Invalid weight where the weight is not in bounds.
   */
  public void invalidWeight() {
    JOptionPane.showMessageDialog(frame,
            "Weight must be a valid number greater than 0 and less than 100",
            "Inane warning",
            JOptionPane.ERROR_MESSAGE);
  }

  /**
   * Invalid weight where the total weights do not equal 100.
   */
  public void invalidWeightTotal() {
    JOptionPane.showMessageDialog(frame,
            "Total weights must equal 100",
            "Inane warning",
            JOptionPane.ERROR_MESSAGE);
  }

  /**
   * No price exists on selected date.
   */
  public void noPriceOnSelectedDate() {
    JOptionPane.showMessageDialog(frame,
            "Price does not exist on selected date.\n"
                    + "Please choose another date.");
  }

  /**
   * Successfully bought shares.
   */
  public void buySharesSuccess(String stockName, String numShares) {
    JOptionPane.showMessageDialog(frame,
            "Successfully bought " + numShares + " shares of "
                    + stockName,
            "Success",
            JOptionPane.PLAIN_MESSAGE);
  }

  /**
   * Successfully sold shares.
   */
  public void sellSharesSuccess(String stockName, String numShares) {
    JOptionPane.showMessageDialog(frame,
            "Successfully sold " + numShares + " shares of "
                    + stockName,
            "Success",
            JOptionPane.PLAIN_MESSAGE);
  }

  /**
   * Successfully sold all shares investor owned.
   */
  public void sellSharesSuccessRemoved(String stockName) {
    JOptionPane.showMessageDialog(frame,
            "Successfully sold all shares of "
                    + stockName + "\nThis stock has been removed from "
                    + "investor profile.",
            "Success",
            JOptionPane.PLAIN_MESSAGE);
  }

  /**
   * Successfully added stock to investor profile.
   */
  public void addStockSuccess(String stockName, String portfolioName) {
    JOptionPane.showMessageDialog(frame,
            "Successfully added stock " + stockName + " to "
                    + portfolioName,
            "Success",
            JOptionPane.PLAIN_MESSAGE);
  }

  /**
   * Successfully created portfolio.
   */
  public void portfolioSuccess(String portfolioName) {
    JOptionPane.showMessageDialog(frame,
            "Successfully created new portfolio " + portfolioName,
            "Success",
            JOptionPane.PLAIN_MESSAGE);
  }

  /**
   * Successfully saved portfolio.
   */
  public void portfolioSaveSuccess(String portfolioName) {
    JOptionPane.showMessageDialog(frame,
            "Successfully saved portfolio " + portfolioName
                    + " to investor profile",
            "Success",
            JOptionPane.PLAIN_MESSAGE);
  }

  /**
   * Successfully loaded portfolio.
   */
  public void portfolioLoadSuccess(String portfolioName) {
    JOptionPane.showMessageDialog(frame,
            "Successfully loaded portfolio " + portfolioName,
            "Success",
            JOptionPane.PLAIN_MESSAGE);
  }

  /**
   * Successfully created portfolio with dollar-cost average.
   */
  public void dollarCostSuccess(String portfolioName) {
    JOptionPane.showMessageDialog(frame,
            "Your dollar-cost averaged portfolio " + portfolioName
                    + " has been created.",
            "Success",
            JOptionPane.PLAIN_MESSAGE);
  }

  /**
   * Successfully updated portfolio.
   */
  public void portfolioUpdateSuccess(String portfolioName) {
    JOptionPane.showMessageDialog(frame,
            "Your portfolio " + portfolioName + " has been updated with the new investment strategy.",
            "Success",
            JOptionPane.PLAIN_MESSAGE);
  }

  @Override
  public void rebalanceSuccess() {
    JOptionPane.showMessageDialog(frame,
        "Your portfolio has been successfully rebalanced!",
        "Success",
        JOptionPane.PLAIN_MESSAGE);
  }

  /**
   * Feature has not yet been implemented.
   */
  public void featureNotImplemented() {
    JOptionPane.showMessageDialog(frame,
            "This feature has not yet been implemented.",
            "Success",
            JOptionPane.PLAIN_MESSAGE);
  }
}
