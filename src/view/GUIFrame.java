package view;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;

import java.util.Enumeration;
import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;


/**
 * GUI Frame opens main window.
 */

public class GUIFrame extends JFrame implements
    ActionListener, ItemListener, ListSelectionListener {

  private JPanel mainPanel;
  private JLabel radioDisplay;
  private int selectedRadioButton = 0;
  private String stock = "";
  final String[] months = {"January", "February", "March", "April", "May", "June", "July", "August",
      "September", "October", "November", "December"};

  /**
   * Constructor for GUI.
   */
  public GUIFrame() {
    super();
    setTitle("Stocks");
    setSize(600, 800);

    mainPanel = new JPanel();
    mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));
    JScrollPane mainScrollPane = new JScrollPane(mainPanel);
    add(mainScrollPane);
  }

  /**
   * RunSystem displays main option menu and records option selection.
   */
  public void runSystem() {
    clear();
    JPanel radioPanel = new JPanel();
    radioPanel.setBorder(BorderFactory.createTitledBorder("Please choose an option(1-10)..."));

    radioPanel.setLayout(new BoxLayout(radioPanel, BoxLayout.PAGE_AXIS));

    JRadioButton[] radioButtons = new JRadioButton[11];

    ButtonGroup rGroup = new ButtonGroup();

    radioButtons[0] = new JRadioButton("1. Buy / sell shares of a stock");
    radioButtons[1] = new JRadioButton("2. Create a Stock Portfolio");
    radioButtons[2] = new JRadioButton("3. View the composition of a Portfolio");
    radioButtons[3] = new JRadioButton("4. View the performance of a Portfolio");
    radioButtons[4] = new JRadioButton("5. Determine the total value of a "
        + "portfolio on a certain date");
    radioButtons[5] = new JRadioButton(
        "6. Determine the cost basis of a portfolio on a certain date");
    radioButtons[6] = new JRadioButton("7. Load a Portfolio");
    radioButtons[7] = new JRadioButton("8. Buy shares of a stock for an existing portfolio");
    radioButtons[8] = new JRadioButton(
        "9. Create a Portfolio with Start-to-Finish Dollar Cost Averaging");
    radioButtons[9] = new JRadioButton("10. Modify a Portfolio with Dollar Cost Averaging");
    radioButtons[10] = new JRadioButton("11. Rebalance a portfolio");
    for (int i = 0; i < radioButtons.length; i++) {
      radioButtons[i].setActionCommand("RB" + (i + 1));
      radioButtons[i].addActionListener(this);
      rGroup.add(radioButtons[i]);
      radioPanel.add(radioButtons[i]);
    }
    radioDisplay = new JLabel("Please choose an option(1-11)...");
    radioPanel.add(radioDisplay);
    mainPanel.add(radioPanel);
    JButton enterButton = new JButton("Enter");
    mainPanel.add(enterButton);
    enterButton.addActionListener(e -> {
      ViewImpl.chooseOption(selectedRadioButton);
    });
    SwingUtilities.updateComponentTreeUI(mainPanel);
  }

  /**
   * Displays all investor-owned stocks, asks user to buy or sell stock, records selection.
   *
   * @param allStocks string of all investor-owned stocks.
   */
  public void buySellStock(String allStocks) {
    clear();

    JLabel l;
    if (allStocks.equals("")) {
      l = new JLabel("No stocks found under investor profile.\n");
    } else {
      l = new JLabel("Your stocks are:\n" + allStocks);
    }
    mainPanel.add(l);

    JPanel radioPanel = new JPanel();
    JLabel title = new JLabel("Would you like to buy or sell stock?");
    mainPanel.add(title);
    radioPanel.setLayout(new BoxLayout(radioPanel, BoxLayout.PAGE_AXIS));
    JRadioButton[] radioButtons = new JRadioButton[2];
    ButtonGroup rGroup = new ButtonGroup();

    radioButtons[0] = new JRadioButton("1. Buy");
    radioButtons[1] = new JRadioButton("2. Sell");

    radioButtons[0].setActionCommand("Buy");
    radioButtons[0].addActionListener(this);
    rGroup.add(radioButtons[0]);
    radioPanel.add(radioButtons[0]);

    radioButtons[1].setActionCommand("Sell");
    radioButtons[1].addActionListener(this);
    rGroup.add(radioButtons[1]);
    radioPanel.add(radioButtons[1]);

    radioDisplay = new JLabel("Please choose an option(1-2)...");
    radioPanel.add(radioDisplay);
    mainPanel.add(radioPanel);
    JButton enterButton = new JButton("Enter");
    mainPanel.add(enterButton);
    enterButton.addActionListener(e -> {
      ViewImpl.chooseOption(selectedRadioButton);
    });
    SwingUtilities.updateComponentTreeUI(mainPanel);
  }

  /**
   * Asks user if they want to add a stock to newly created portfolio, records selection.
   */
  public void addStock() {
    clear();

    JPanel radioPanel = new JPanel();
    JLabel title = new JLabel("Would you like to add stock to portfolio?");
    mainPanel.add(title);
    radioPanel.setLayout(new BoxLayout(radioPanel, BoxLayout.PAGE_AXIS));
    JRadioButton[] radioButtons = new JRadioButton[2];
    ButtonGroup rGroup = new ButtonGroup();

    radioButtons[0] = new JRadioButton("1. Yes");
    radioButtons[1] = new JRadioButton("2. No");

    radioButtons[0].setActionCommand("Y");
    radioButtons[0].addActionListener(this);
    rGroup.add(radioButtons[0]);
    radioPanel.add(radioButtons[0]);

    radioButtons[1].setActionCommand("N");
    radioButtons[1].addActionListener(this);
    rGroup.add(radioButtons[1]);
    radioPanel.add(radioButtons[1]);

    radioDisplay = new JLabel("Please choose an option(1-2)...");
    radioPanel.add(radioDisplay);
    mainPanel.add(radioPanel);
    JButton enterButton = new JButton("Enter");
    mainPanel.add(enterButton);
    enterButton.addActionListener(e -> {
      ViewImpl.chooseOption(selectedRadioButton);
    });
    SwingUtilities.updateComponentTreeUI(mainPanel);
  }

  /**
   * Choose stock from list of stocks.
   *
   * @param list ArrayList of all stocks available.
   */
  public void chooseStock(ArrayList<String> list) {
    clear();

    JPanel radioPanel = new JPanel();
    JLabel title = new JLabel("Choose a stock...");
    mainPanel.add(title);
    radioPanel.setLayout(new BoxLayout(radioPanel, BoxLayout.PAGE_AXIS));
    JRadioButton[] radioButtons = new JRadioButton[list.size()];
    ButtonGroup rGroup = new ButtonGroup();
    for (int i = 0; i < radioButtons.length; i++) {
      radioButtons[i] = new JRadioButton(list.get(i));
      radioButtons[i].addActionListener(this);
      rGroup.add(radioButtons[i]);
      radioPanel.add(radioButtons[i]);
    }

    mainPanel.add(radioPanel);
    JButton enterButton = new JButton("Enter");
    mainPanel.add(enterButton);
    enterButton.addActionListener(e -> {
      ViewImpl.chooseStock(getSelectedButton(rGroup));
    });
    SwingUtilities.updateComponentTreeUI(mainPanel);
  }

  /**
   * Choose stock from list of investor-owned stocks.
   *
   * @param stocks string of stocks.
   */
  public void chooseStock(String stocks) {
    clear();

    String[] allStocks = stocks.split("\n");
    JPanel radioPanel = new JPanel();
    JLabel title = new JLabel("Choose a stock...");
    mainPanel.add(title);
    radioPanel.setLayout(new BoxLayout(radioPanel, BoxLayout.PAGE_AXIS));
    JRadioButton[] radioButtons = new JRadioButton[allStocks.length];
    ButtonGroup rGroup = new ButtonGroup();
    for (int i = 0; i < radioButtons.length; i++) {
      radioButtons[i] = new JRadioButton(allStocks[i]);
      radioButtons[i].addActionListener(this);
      rGroup.add(radioButtons[i]);
      radioPanel.add(radioButtons[i]);
    }

    mainPanel.add(radioPanel);
    JButton enterButton = new JButton("Enter");
    mainPanel.add(enterButton);
    enterButton.addActionListener(e -> {
      ViewImpl.chooseStock(getSelectedButton(rGroup));
    });
    SwingUtilities.updateComponentTreeUI(mainPanel);
  }

  /**
   * Choose multiple stock from list.
   *
   * @param list list of all stocks.
   */
  public void chooseStocks(ArrayList<String> list) {
    clear();

    JLabel title = new JLabel("Choose stocks to buy...");
    mainPanel.add(title);

    JPanel checkBoxPanel = new JPanel();
    checkBoxPanel.setLayout(new BoxLayout(checkBoxPanel, BoxLayout.PAGE_AXIS));
    JCheckBox[] checkBoxes = new JCheckBox[list.size()];
    for (int i = 0; i < checkBoxes.length; i++) {
      checkBoxes[i] = new JCheckBox(list.get(i));
      checkBoxes[i].setSelected(false);
      checkBoxes[i].addActionListener(this);
      checkBoxes[i].addItemListener(this);
      checkBoxPanel.add(checkBoxes[i]);
    }

    mainPanel.add(checkBoxPanel);

    JButton enterButton = new JButton("Enter");
    mainPanel.add(enterButton);
    enterButton.addActionListener(e -> {
      ViewImpl.chooseStocks(getSelectedBoxes(checkBoxes));
    });
    SwingUtilities.updateComponentTreeUI(mainPanel);
  }

  /**
   * Get name of newly created portfolio.
   */
  public void choosePortfolioNew() {
    clear();
    JLabel l = new JLabel("Enter name of new Portfolio");
    JButton b = new JButton("Enter");
    b.addActionListener(this);
    JTextField t = new JTextField(20);
    t.setMaximumSize(new Dimension(180, 20));
    mainPanel.add(l);
    mainPanel.add(t);
    mainPanel.add(b);

    b.addActionListener(e -> {
      ViewImpl.choosePortfolio(t.getText());
      t.setText("");
    });
    SwingUtilities.updateComponentTreeUI(mainPanel);
  }

  /**
   * Choose portfolio from all investor-owned portfolios.
   *
   * @param portfolios all investor-owned portfolios.
   */
  public void choosePortfolio(String portfolios) {
    clear();
    String[] all = portfolios.split("\n");
    JPanel radioPanel = new JPanel();
    JLabel title = new JLabel("Choose a portfolio...");
    mainPanel.add(title);
    radioPanel.setLayout(new BoxLayout(radioPanel, BoxLayout.PAGE_AXIS));
    JRadioButton[] radioButtons = new JRadioButton[all.length];
    ButtonGroup rGroup = new ButtonGroup();
    for (int i = 0; i < radioButtons.length; i++) {
      radioButtons[i] = new JRadioButton(all[i]);
      radioButtons[i].addActionListener(this);
      rGroup.add(radioButtons[i]);
      radioPanel.add(radioButtons[i]);
    }

    mainPanel.add(radioPanel);
    JButton enterButton = new JButton("Enter");
    mainPanel.add(enterButton);
    enterButton.addActionListener(e -> {
      ViewImpl.choosePortfolio(getSelectedButton(rGroup));
    });
    SwingUtilities.updateComponentTreeUI(mainPanel);
  }

  /**
   * Title of screen to choose a start date.
   */
  public void chooseStartDate() {
    clear();
    JLabel l = new JLabel("Choose start date for the purchases...");
    mainPanel.add(l);
  }

  /**
   * Asks user if they want to choose an end date, records answer.
   */
  public void chooseEndDate() {
    clear();

    JPanel radioPanel = new JPanel();
    JLabel title = new JLabel("Would you like to choose an end date (time range for purchases)");
    mainPanel.add(title);
    radioPanel.setLayout(new BoxLayout(radioPanel, BoxLayout.PAGE_AXIS));
    JRadioButton[] radioButtons = new JRadioButton[2];
    ButtonGroup rGroup = new ButtonGroup();

    radioButtons[0] = new JRadioButton("1. Yes");
    radioButtons[1] = new JRadioButton("2. No");

    radioButtons[0].setActionCommand("Y");
    radioButtons[0].addActionListener(this);
    rGroup.add(radioButtons[0]);
    radioPanel.add(radioButtons[0]);

    radioButtons[1].setActionCommand("N");
    radioButtons[1].addActionListener(this);
    rGroup.add(radioButtons[1]);
    radioPanel.add(radioButtons[1]);

    radioDisplay = new JLabel("Please choose an option(1-2)...");
    radioPanel.add(radioDisplay);
    mainPanel.add(radioPanel);
    JButton enterButton = new JButton("Enter");
    mainPanel.add(enterButton);
    enterButton.addActionListener(e -> {
      ViewImpl.chooseOption(selectedRadioButton);
    });
    SwingUtilities.updateComponentTreeUI(mainPanel);
  }

  /**
   * Gets user-selected date.
   */
  public void chooseDate() {
    clear();
    JLabel l = new JLabel("Enter date in the format DD-MM-YYYY");
    JButton b = new JButton("Enter");
    b.addActionListener(this);
    JTextField t = new JTextField(10);
    t.setMaximumSize(new Dimension(180, 20));
    mainPanel.add(l);
    mainPanel.add(t);
    mainPanel.add(b);

    b.addActionListener(e -> {
      ViewImpl.chooseDate(t.getText());
      t.setText("");
    });
    SwingUtilities.updateComponentTreeUI(mainPanel);
  }

  /**
   * Gets number of shares user wants to buy based on stock information.
   *
   * @param stockName name of stock selected.
   * @param date      date purchased.
   * @param price     price of stock on date purchased.
   */
  public void numberOfShares(String stockName, String date, String price) {
    clear();

    JLabel l1 = new JLabel("The price of " + stockName + " on "
        + date + " is $" + price);
    JLabel l2 = new JLabel("Enter number of shares...");
    JButton b = new JButton("Enter");
    b.addActionListener(this);
    JTextField t = new JTextField(10);
    t.setMaximumSize(new Dimension(180, 20));
    mainPanel.add(l1);
    mainPanel.add(l2);
    mainPanel.add(t);
    mainPanel.add(b);

    b.addActionListener(e -> {
      ViewImpl.chooseNumShares(t.getText());
      t.setText("");
    });
    SwingUtilities.updateComponentTreeUI(mainPanel);
  }

  /**
   * Gets amount user wants to invest.
   */
  public void getAmountInvest() {
    clear();

    JLabel l = new JLabel(
        "Please list the amount you would like to invest in dollars(i.e. 2000): ");
    JButton b = new JButton("Enter");
    b.addActionListener(this);
    JTextField t = new JTextField(10);
    t.setMaximumSize(new Dimension(180, 20));
    mainPanel.add(l);
    mainPanel.add(t);
    mainPanel.add(b);

    b.addActionListener(e -> {
      ViewImpl.chooseNumShares(t.getText());
      t.setText("");
    });
    SwingUtilities.updateComponentTreeUI(mainPanel);
  }

  /**
   * Gets frequency user wants to invest for dollar-cost average.
   */
  public void getAmountFrequency() {
    clear();

    JLabel l = new JLabel("Please specify the amount corresponding to the frequency(30, 15, 2): ");
    JButton b = new JButton("Enter");
    b.addActionListener(this);
    JTextField t = new JTextField(10);
    t.setMaximumSize(new Dimension(180, 20));
    mainPanel.add(l);
    mainPanel.add(t);
    mainPanel.add(b);

    b.addActionListener(e -> {
      ViewImpl.chooseNumShares(t.getText());
      t.setText("");
    });
    SwingUtilities.updateComponentTreeUI(mainPanel);
  }

  /**
   * Get weights of stocks for dollar cost average.
   *
   * @param stocks stocks selected by user.
   */
  public void getWeights(String[] stocks) {
    clear();
    ArrayList<String> w = new ArrayList<>();
    String[] weights = new String[stocks.length];
    JTextField[] textField = new JTextField[stocks.length];
    JLabel title = new JLabel("Enter weight for stock in percent (total must be 100)...");
    mainPanel.add(title);
    JLabel l;
    for (int i = 0; i != stocks.length; i++) {
      l = new JLabel(stocks[i]);
      mainPanel.add(l);
      JTextField t = new JTextField(20);
      t.setMaximumSize(new Dimension(60, 20));
      mainPanel.add(l);
      mainPanel.add(t);
      textField[i] = t;
    }
    JButton b = new JButton("Enter");
    b.addActionListener(this);
    mainPanel.add(b);
    b.addActionListener(e -> {
      for (int i = 0; i < textField.length; i++) {
        weights[i] = textField[i].getText();
        w.add(weights[i]);
        textField[i].setText("");
      }
      ViewImpl.chooseWeights(w);
    });
    SwingUtilities.updateComponentTreeUI(mainPanel);
  }

  /**
   * Get selected button.
   *
   * @param rGroup group of buttons.
   * @return text field of button.
   */
  private String getSelectedButton(ButtonGroup rGroup) {
    for (Enumeration<AbstractButton> buttons = rGroup.getElements(); buttons.hasMoreElements(); ) {
      AbstractButton button = buttons.nextElement();
      if (button.isSelected()) {
        return button.getText();
      }
    }
    return "";
  }

  /**
   * Gets selected check boxes.
   *
   * @param boxes boxes user selected.
   * @return Arraylist of the text field from the boxes the user selected.
   */
  private ArrayList<String> getSelectedBoxes(JCheckBox[] boxes) {
    ArrayList<String> result = new ArrayList<>();
    for (JCheckBox box : boxes) {
      if (box.isSelected()) {
        result.add(box.getText());
      }
    }
    return result;
  }

  /**
   * Gets file path from user.
   */
  public void choosePath() {
    clear();
    JLabel l = new JLabel("Enter name of portfolio path...");
    JButton b = new JButton("Enter");
    b.addActionListener(this);
    JTextField t = new JTextField(20);
    t.setMaximumSize(new Dimension(180, 20));
    mainPanel.add(l);
    mainPanel.add(t);
    mainPanel.add(b);

    b.addActionListener(e -> {
      ViewImpl.choosePath(t.getText());
      t.setText("");
    });
    SwingUtilities.updateComponentTreeUI(mainPanel);
  }

  /**
   * View composition of a portfolio in table format.
   *
   * @param portfolioName name of portfolio.
   * @param rowData       row data.
   * @param columnNames   names of columns.
   */
  public void viewComposition(String portfolioName, Object[][] rowData, Object[] columnNames) {
    clear();
    JLabel l = new JLabel(portfolioName);
    JTable j = new JTable(rowData, columnNames);
    j.setBounds(30, 40, 100, 300);

    JScrollPane sp = new JScrollPane(j);

    JButton b = new JButton("OK");
    b.addActionListener(new ActionListener() {

      @Override
      public void actionPerformed(ActionEvent e) {
        ViewImpl.chooseExit(true);
      }
    });

    mainPanel.add(l);
    mainPanel.add(sp);
    mainPanel.add(b);
    mainPanel.setVisible(true);
    SwingUtilities.updateComponentTreeUI(mainPanel);
  }

  /**
   * Displays value of portfolio on specific date.
   *
   * @param portfolioName name of portfolio.
   * @param date          date previously selected by user
   * @param value         value of portfolio.
   */
  public void valueOfPortfolio(String portfolioName, String date, float value) {
    clear();
    JLabel l = new JLabel("Total value of " + portfolioName + " on "
        + date + " is " + value);

    JButton b = new JButton("OK");
    b.addActionListener(new ActionListener() {

      @Override
      public void actionPerformed(ActionEvent e) {
        ViewImpl.chooseExit(true);
      }
    });

    mainPanel.add(l);
    mainPanel.add(b);
    mainPanel.setVisible(true);
    SwingUtilities.updateComponentTreeUI(mainPanel);
  }

  /**
   * Displays cost basis of portfolio.
   *
   * @param portfolioName name of portfolio.
   * @param date          date user previously selected.
   * @param value         value of portfolio on date.
   */
  public void costBasisPortfolio(String portfolioName, String date, float value) {
    clear();
    JLabel l = new JLabel("Cost basis of " + portfolioName + " on "
        + date + " is " + value);

    JButton b = new JButton("OK");
    b.addActionListener(new ActionListener() {

      @Override
      public void actionPerformed(ActionEvent e) {
        ViewImpl.chooseExit(true);
      }
    });

    mainPanel.add(l);
    mainPanel.add(b);
    mainPanel.setVisible(true);
    SwingUtilities.updateComponentTreeUI(mainPanel);
  }

  /**
   * Get selected time range from user, record selection.
   */
  public void portfolioPerformanceTimeRange() {
    clear();

    JPanel radioPanel = new JPanel();
    JLabel title = new JLabel(
        "Would you like to view the performance over the past year, 'n' months,"
            + " or specific month?");
    mainPanel.add(title);
    radioPanel.setLayout(new BoxLayout(radioPanel, BoxLayout.PAGE_AXIS));
    JRadioButton[] radioButtons = new JRadioButton[3];
    ButtonGroup rGroup = new ButtonGroup();

    radioButtons[0] = new JRadioButton("1. Performance over the past year");
    radioButtons[1] = new JRadioButton("2. Performance over the past 'n' months");
    radioButtons[2] = new JRadioButton("3. Performance over a specific month");

    radioButtons[0].setActionCommand("RB1");
    radioButtons[0].addActionListener(this);
    rGroup.add(radioButtons[0]);
    radioPanel.add(radioButtons[0]);

    radioButtons[1].setActionCommand("RB2");
    radioButtons[1].addActionListener(this);
    rGroup.add(radioButtons[1]);
    radioPanel.add(radioButtons[1]);

    radioButtons[2].setActionCommand("RB3");
    radioButtons[2].addActionListener(this);
    rGroup.add(radioButtons[2]);
    radioPanel.add(radioButtons[2]);

    radioDisplay = new JLabel("Please choose an option(1-3)...");
    radioPanel.add(radioDisplay);
    mainPanel.add(radioPanel);
    JButton enterButton = new JButton("Enter");
    mainPanel.add(enterButton);
    enterButton.addActionListener(e -> {
      ViewImpl.chooseOption(selectedRadioButton);
    });
    SwingUtilities.updateComponentTreeUI(mainPanel);
  }

  /**
   * Get selected frequency from user, record selection.
   */
  public void getFrequency() {
    clear();

    JPanel radioPanel = new JPanel();
    JLabel title = new JLabel(
        "Please specify the frequency at which you would like to buy them at...");
    mainPanel.add(title);
    radioPanel.setLayout(new BoxLayout(radioPanel, BoxLayout.PAGE_AXIS));
    JRadioButton[] radioButtons = new JRadioButton[3];
    ButtonGroup rGroup = new ButtonGroup();

    radioButtons[0] = new JRadioButton("1. Days");
    radioButtons[1] = new JRadioButton("2. Months");
    radioButtons[2] = new JRadioButton("3. Years");

    radioButtons[0].setActionCommand("RB1");
    radioButtons[0].addActionListener(this);
    rGroup.add(radioButtons[0]);
    radioPanel.add(radioButtons[0]);

    radioButtons[1].setActionCommand("RB2");
    radioButtons[1].addActionListener(this);
    rGroup.add(radioButtons[1]);
    radioPanel.add(radioButtons[1]);

    radioButtons[2].setActionCommand("RB3");
    radioButtons[2].addActionListener(this);
    rGroup.add(radioButtons[2]);
    radioPanel.add(radioButtons[2]);

    radioDisplay = new JLabel("Please choose an option(1-3)...");
    radioPanel.add(radioDisplay);
    mainPanel.add(radioPanel);
    JButton enterButton = new JButton("Enter");
    mainPanel.add(enterButton);
    enterButton.addActionListener(e -> {
      ViewImpl.chooseOption(selectedRadioButton);
    });
    SwingUtilities.updateComponentTreeUI(mainPanel);
  }

  /**
   * Display portfolio performance with time range of year.
   */
  public void portfolioPerformanceYear(String portfolio, String[] names, String[] data) {
    clear();

    JLabel title = new JLabel(
        "Performance of portfolio " + portfolio + " over the past year(2022)");
    JLabel scale = new JLabel("Scale: * = $1000");
    JButton b = new JButton("OK");
    b.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        ViewImpl.chooseExit(true);
      }
    });
    mainPanel.add(title);
    mainPanel.add(scale);
    mainPanel.add(new BarChart(names, data));
    mainPanel.add(b);
    mainPanel.setVisible(true);
    SwingUtilities.updateComponentTreeUI(mainPanel);
  }

  /**
   * Display portfolio performance with time range of months.
   */
  public void portfolioPerformanceMonths(String portfolio, String timeRangeMonths,
      String[] names, String[] data) {
    clear();

    JLabel title = new JLabel("Performance of portfolio " + portfolio + " from " + timeRangeMonths);
    JLabel scale = new JLabel("Scale: * = $1000");
    JButton b = new JButton("OK");
    b.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        ViewImpl.chooseExit(true);
      }
    });
    mainPanel.add(title);
    mainPanel.add(scale);
    mainPanel.add(new BarChart(names, data));
    mainPanel.add(b);
    mainPanel.setVisible(true);
    SwingUtilities.updateComponentTreeUI(mainPanel);
  }

  /**
   * Display portfolio performance with time range of month.
   */
  public void portfolioPerformanceMonth(String portfolio, String month,
      String[] names, String[] data) {
    clear();

    JLabel title = new JLabel("Performance of portfolio " + portfolio + " during the month of "
        + month);
    JLabel scale = new JLabel("Scale: * = $1000");
    JButton b = new JButton("OK");
    b.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        ViewImpl.chooseExit(true);
      }
    });
    mainPanel.add(title);
    mainPanel.add(scale);
    mainPanel.add(new BarChart(names, data));
    mainPanel.add(b);
    mainPanel.setVisible(true);
    SwingUtilities.updateComponentTreeUI(mainPanel);

  }

  /**
   * Gets start month from user, records selection.
   */
  public void getStartMonth() {
    clear();

    JPanel radioPanel = new JPanel();
    JLabel title = new JLabel("Choose a start month..");
    mainPanel.add(title);
    radioPanel.setLayout(new BoxLayout(radioPanel, BoxLayout.PAGE_AXIS));
    JRadioButton[] radioButtons = new JRadioButton[months.length];
    ButtonGroup rGroup = new ButtonGroup();
    for (int i = 0; i < radioButtons.length; i++) {
      radioButtons[i] = new JRadioButton((i + 1) + ". " + months[i]);
      radioButtons[i].setActionCommand("RB" + (i + 1));
      radioButtons[i].addActionListener(this);
      rGroup.add(radioButtons[i]);
      radioPanel.add(radioButtons[i]);
    }

    mainPanel.add(radioPanel);
    JButton enterButton = new JButton("Enter");
    mainPanel.add(enterButton);
    enterButton.addActionListener(e -> {
      ViewImpl.chooseOption(selectedRadioButton);
    });
    SwingUtilities.updateComponentTreeUI(mainPanel);
  }

  /**
   * Gets month from user, records selection.
   */
  public void getMonth() {
    clear();

    JPanel radioPanel = new JPanel();
    JLabel title = new JLabel("Choose a month..");
    mainPanel.add(title);
    radioPanel.setLayout(new BoxLayout(radioPanel, BoxLayout.PAGE_AXIS));
    JRadioButton[] radioButtons = new JRadioButton[months.length];
    ButtonGroup rGroup = new ButtonGroup();
    for (int i = 0; i < radioButtons.length; i++) {
      radioButtons[i] = new JRadioButton((i + 1) + ". " + months[i]);
      radioButtons[i].setActionCommand("RB" + (i + 1));
      radioButtons[i].addActionListener(this);
      rGroup.add(radioButtons[i]);
      radioPanel.add(radioButtons[i]);
    }

    mainPanel.add(radioPanel);
    JButton enterButton = new JButton("Enter");
    mainPanel.add(enterButton);
    enterButton.addActionListener(e -> {
      ViewImpl.chooseOption(selectedRadioButton);
    });
    SwingUtilities.updateComponentTreeUI(mainPanel);
  }

  /**
   * Gets end month from user, records selection.
   *
   * @param startMonth int representation of user-selected start month.
   */
  public void getEndMonth(int startMonth) {
    clear();

    JPanel radioPanel = new JPanel();
    JLabel title = new JLabel("Start month: " + months[startMonth - 1]
        + "\nChoose an end month..");
    mainPanel.add(title);
    radioPanel.setLayout(new BoxLayout(radioPanel, BoxLayout.PAGE_AXIS));
    JRadioButton[] radioButtons = new JRadioButton[months.length - startMonth];
    ButtonGroup rGroup = new ButtonGroup();
    for (int i = startMonth; i < months.length; i++) {
      radioButtons[i - startMonth] = new JRadioButton((i + 1) + ". " + months[i]);
      radioButtons[i - startMonth].setActionCommand("RB" + (i + 1));
      radioButtons[i - startMonth].addActionListener(this);
      rGroup.add(radioButtons[i - startMonth]);
      radioPanel.add(radioButtons[i - startMonth]);
    }

    mainPanel.add(radioPanel);
    JButton enterButton = new JButton("Enter");
    mainPanel.add(enterButton);
    enterButton.addActionListener(e -> {
      ViewImpl.chooseOption(selectedRadioButton);
    });
    SwingUtilities.updateComponentTreeUI(mainPanel);
  }

  /**
   * Gets action performed by radio buttons.
   *
   * @param arg the event to be processed.
   */
  @Override
  public void actionPerformed(ActionEvent arg) {
    switch (arg.getActionCommand()) {
      case "RB1":
      case "Buy":
      case "Y":
        radioDisplay.setText("Option 1 was selected");
        selectedRadioButton = 1;
        break;

      case "RB2":
      case "Sell":
      case "N":
        radioDisplay.setText("Option 2 was selected");
        selectedRadioButton = 2;
        break;

      case "RB3":
        radioDisplay.setText("Option 3 was selected");
        selectedRadioButton = 3;
        break;

      case "RB4":
        radioDisplay.setText("Option 4 was selected");
        selectedRadioButton = 4;
        break;

      case "RB5":
        radioDisplay.setText("Option 5 was selected");
        selectedRadioButton = 5;
        break;

      case "RB6":
        radioDisplay.setText("Option 6 was selected");
        selectedRadioButton = 6;
        break;

      case "RB7":
        radioDisplay.setText("Option 7 was selected");
        selectedRadioButton = 7;
        break;

      case "RB8":
        radioDisplay.setText("Option 8 was selected");
        selectedRadioButton = 8;
        break;
      case "RB9":
        radioDisplay.setText("Option 9 was selected");
        selectedRadioButton = 9;
        break;
      case "RB10":
        radioDisplay.setText("Option 10 was selected");
        selectedRadioButton = 10;
        break;
      case "RB11":
        radioDisplay.setText("Option 11 was selected");
        selectedRadioButton = 11;
        break;
      case "RB12":
        radioDisplay.setText("Option 12 was selected");
        selectedRadioButton = 12;
        break;
      default:
        break;
    }
  }

  /**
   * Clear frame.
   */
  private void clear() {
    mainPanel.removeAll();
    mainPanel.repaint();
  }

  /**
   * Override itemStateChanged method.
   *
   * @param arg the event to be processed
   */
  @Override
  public void itemStateChanged(ItemEvent arg) {
    // item state changed.
  }

  /**
   * Override valueChanged method.
   *
   * @param e the event that characterizes the change.
   */
  @Override
  public void valueChanged(ListSelectionEvent e) {
    // value changed.
  }
}
