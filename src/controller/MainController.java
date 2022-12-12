package controller;

import java.io.FileNotFoundException;

/**
 * Main controller interface.
 * Represents the entry-point to the program.
 */

public interface MainController {

  /**
   * Main function to run the program and initialize the investor and portfolios.
   *
   * @param args packed arguments.
   * @throws FileNotFoundException when an invalid file path is provided.
   */
  void main(String[] args) throws FileNotFoundException;

  /**
   * Method to run the program continuously.
   *
   * @throws FileNotFoundException when an invalid file path is provided
   */
  void runSystem() throws FileNotFoundException;

  /**
   * Method to return the option chosen from the user from the main screen.
   *
   * @return the option chosen
   */
  int chooseOption();

  /**
   * Choose a valid operation.
   *
   * @param option the operation to choose
   * @throws FileNotFoundException when an invalid file path is provided
   */
  void chooseOperation(int option) throws FileNotFoundException;

  /**
   * Operation to buy a stock.
   *
   * @throws FileNotFoundException when an invalid file path is provided.
   */
  void operationBuySellStock() throws FileNotFoundException;

  /**
   * Operation to create a new portfolio.
   *
   * @throws FileNotFoundException when an invalid file path is provided
   */
  void operationNewPortfolio() throws FileNotFoundException;

  /**
   * Operation to display the composition of a portfolio.
   *
   * @throws FileNotFoundException when an invalid file path is provided
   */
  void operationComposition() throws FileNotFoundException;

  /**
   * Operation to view the value of a portfolio on a given date.
   *
   * @throws FileNotFoundException when an invalid file path is provided
   */
  void operationValueOfPortfolio() throws FileNotFoundException;

  /**
   * Operation to load in a portfolio.
   *
   * @throws FileNotFoundException when an invalid file path is provided
   */
  void operationPersistPortfolio() throws FileNotFoundException;
}
