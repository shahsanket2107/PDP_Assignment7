package controller;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Test class for StocksAPIControllerTest.
 */
public class StocksAPIControllerTest {
  StocksAPIController alphaVantageController;

  @Before
  public void setUp() {
    alphaVantageController = new StockAPIAlphaVantageControllerImpl();
  }

  @Test
  public void queryDateDaily() {
    String res = alphaVantageController.queryAPI("AAPL", "daily");
    String resSub = res.substring(0, 30);
    assertEquals(true, res.startsWith(resSub));
  }

  @Test
  public void queryDateMonthly() {
    String res = alphaVantageController.queryAPI("AAPL", "monthly");
    String resSub = res.substring(0, 30);
    assertEquals(true, res.startsWith(resSub));
  }
}
