package model;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Alpha vantage API test.
 */
public class StocksAPIAlphaVantageImplTest {
  StocksAPI alphaVantageAPI;

  @Before
  public void setUp() {
    alphaVantageAPI = new StocksAPIAlphaVantageImpl();
  }

  @Test
  public void testGetPrice() {
    assertEquals("149.7000",
            alphaVantageAPI.getPrice("AAPL", "2022-11-11", "daily"));
  }

  @Test
  public void testGetPriceMonthly() {
    assertEquals("153.3400",
            alphaVantageAPI.getPrice("AAPL", "2022-10", "monthly"));
  }

  @Test
  public void testGetVolume() {
    assertEquals("93979665",
            alphaVantageAPI.getVolume("AAPL", "2022-11-11", "daily"));
  }

  @Test
  public void testGetPriceInvalidDate() {
    assertEquals("-1",
            alphaVantageAPI.getPrice("AAPL", "2022-14-14", "daily"));
  }

  @Test
  public void testGetVolumeInvalidDate() {
    assertEquals("-1",
            alphaVantageAPI.getVolume("AAPL", "2022-14-14", "daily"));
  }

}
