package model;

import org.junit.Before;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;

/**
 * Testing the model.
 */
public class ModelImplTest {
  Model model;

  @Before
  public void setup() {
    model = new ModelImpl();
  }

  @Test
  public void testGetPrice() throws FileNotFoundException {
    assertEquals("0.3325890004634857", model.getPrice("AAPL", "12-03-2001"));
  }

  @Test
  public void getTotalValueOfPortfolio() throws FileNotFoundException {
    Model model = new ModelImpl();
    model.createPortfolio("basit");
    InvestorImpl.InvestorStock i = new InvestorImpl.InvestorStock("AAPL", 20,
            10, 10);
    Map<Portfolio, Portfolio> test = new HashMap<>();
    assertEquals(model.getTotalValueOfPortfolio(false, true,
            test, "basit", "19-12-1980"), 0.00, 0.1);
  }

  @Test
  public void testGetPriceOperation() {
    try {
      assertEquals("0.33", model.operationGetPrice("AAPL"));
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @Test
  public void testDollarCostAvg() throws FileNotFoundException {
    model.createPortfolio("test");
    String[] stocks = new String[]{"AAPL", "EBAY"};
    String[] weights = new String[]{"40", "60"};
    model.modifyPortfolioDollarCost("test", stocks, "2000", weights, "12-03-2001", false);
    String expected = "AAPL 12-03-2001 0.332589 2405 EBAY 12-03-2001 3.32097 240 ";
    assertEquals(expected, model.getPortfolio("test").examine());
  }

  @Test
  public void testDollarCostAvgStartToFinish() throws FileNotFoundException {
    model.createPortfolio("test");
    String[] stocks = new String[]{"AAPL", "EBAY"};
    String[] weights = new String[]{"40", "60"};
    String[] timeRange = new String[]{"12-03-2001", "02-06-2002"};
    model.buyMultipleStocks("test", stocks, "2000", weights, timeRange,
            "days", "30", false);
    String expected = "AAPL 12-03-2001 0.332589 2405 "
            + "AAPL 11-04-2001 0.389286 2055 AAPL 11-05-2001 0.408036 1960 "
            + "AAPL 10-06-2001 1.0 800 AAPL 10-07-2001 0.3775 2119 "
            + "AAPL 09-08-2001 0.340179 2351 AAPL 08-09-2001 1.0 800 "
            + "AAPL 08-10-2001 0.289286 2765 AAPL 07-11-2001 0.349821 2286 "
            + "AAPL 07-12-2001 0.4025 1987 "
            + "AAPL 06-01-2002 1.0 800 "
            + "AAPL 05-02-2002 0.454464 1760 "
            + "AAPL 07-03-2002 0.435357 1837 "
            + "AAPL 06-04-2002 1.0 800 "
            + "EBAY 12-03-2001 3.32097 240 "
            + "EBAY 11-04-2001 4.121423 194 "
            + "EBAY 11-05-2001 5.602904 142 "
            + "EBAY 10-06-2001 1.0 800 "
            + "EBAY 10-07-2001 6.339436 126 "
            + "EBAY 09-08-2001 6.611953 120 "
            + "EBAY 08-09-2001 1.0 800 "
            + "EBAY 08-10-2001 5.831229 137 "
            + "EBAY 07-11-2001 5.954335 134 "
            + "EBAY 07-12-2001 7.149621 111 "
            + "EBAY 06-01-2002 1.0 800 "
            + "EBAY 05-02-2002 5.734428 139 "
            + "EBAY 07-03-2002 6.06271 131 "
            + "EBAY 06-04-2002 1.0 800 ";
    assertEquals(expected, model.getPortfolio("test").examine());
  }
}
