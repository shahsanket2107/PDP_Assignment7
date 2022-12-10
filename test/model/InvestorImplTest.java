package model;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import java.io.FileNotFoundException;

/**
 * Test class for model.Investor.
 */
public class InvestorImplTest {

  InvestorImpl investor;

  @Before
  public void setUp() {
    investor = new InvestorImpl();
  }

  @Test
  public void testShares() throws FileNotFoundException {
    investor.buyShares("NI", "20", "03-02-2007", 8);
    assertEquals(20, investor.getShares("NI"));
  }

  @Test
  public void testPortfolio() throws FileNotFoundException {
    investor.createPortfolio("retirement");
    investor.getPortfolio("retirement");
    assert (investor.getPortfolio("retirement") instanceof Portfolio);
    assertEquals(investor.getPortfolio("retirement").getName(), "retirement");
  }

  @Test
  public void testGetPrice() throws FileNotFoundException {
    assertEquals("0.656000018119812", investor.getPrice("ADI", "02-04-1980"));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testAddStock1() throws FileNotFoundException {
    investor.buyShares("NI", "20", "03-02-2007", 8);
    investor.addStock("test", "NI");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testAddStock2() {
    investor.createPortfolio("test");
    investor.addStock("test", "NI");
  }

  @Test
  public void testAddStock3() throws FileNotFoundException {
    investor.createPortfolio("test");
    investor.buyShares("NI", "20", "03-02-2007", 8);
    investor.addStock("test", "NI");
    assertEquals(true, investor.containsStock("test", "NI"));
  }

  @Test
  public void testGetAllPortfolioNames() {
    investor.createPortfolio("test");
    investor.createPortfolio("fish");
    assertEquals("test\nfish\n", investor.getAllPortfolioNames());
  }

  @Test
  public void testGetAllStockNames() throws FileNotFoundException {
    investor.buyShares("NI", "20", "03-02-2007", 20);
    investor.buyShares("ZION", "20", "03-02-2007", 20);
    assertEquals("ZION\nNI\n", investor.getAllStockNames());
  }

  @Test
  public void testContainsDigits() {
    String test = "492h";
    assertEquals(false, investor.containsDigits(test));

    test = "492 ";
    assertEquals(false, investor.containsDigits(test));

    test = "83";
    assertEquals(true, investor.containsDigits(test));
  }

  @Test
  public void testLocal() throws FileNotFoundException {
    assertEquals(false, investor.getLocal());

    investor.createPortfolio("test");
    investor.buyShares("NI", "20", "03-02-2007", 8);
    investor.addStock("test", "NI");

    assertEquals(true, investor.getLocal());
  }

  @Test
  public void testInvestorStock() {
    InvestorImpl.InvestorStock i = new InvestorImpl.InvestorStock("name", 10, 20, 10);
    assertEquals("name", i.name);
    assertEquals(10, i.numShares);
    assertEquals(20, i.priceWhenBought, 0.01);

    InvestorImpl.InvestorStock j = new InvestorImpl.InvestorStock("name", 10,
            "03-02-2007", 20, 10);
    assertEquals("name", j.name);
    assertEquals(10, j.numShares);
    assertEquals("03-02-2007", j.datePurchased);
    assertEquals(20, j.priceWhenBought, 0.01);
  }

  @Test
  public void testRemoveStock() throws FileNotFoundException {
    investor.createPortfolio("test");
    investor.buyShares("NI", "20", "03-02-2007", 20);
    investor.addStock("test", "NI");
    assertEquals(true, investor.containsStock("test", "NI"));
    investor.removeStock("NI");
    assertEquals(false, investor.containsStock("test", "NI"));
  }

}