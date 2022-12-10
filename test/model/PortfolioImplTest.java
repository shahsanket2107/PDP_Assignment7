package model;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

/**
 * Test class for model.Portfolio.
 */
public class PortfolioImplTest {

  PortfolioImpl portfolio;

  @Before
  public void setUp() {
    portfolio = new PortfolioImpl("test");
  }

  @Test
  public void testAddStock() {
    InvestorImpl.InvestorStock i = new InvestorImpl.InvestorStock("name", 20, 10, 10);
    portfolio.addStock(i);
    assert (portfolio.containsStock("name"));

    assertEquals(false, portfolio.containsStock("name2"));
  }


  @Test
  public void testGetShares() {
    InvestorImpl.InvestorStock i = new InvestorImpl.InvestorStock("name", 20, 10, 10);
    portfolio.addStock(i);

    assertEquals(20, portfolio.getShares("name"));
  }

  @Test
  public void testExamine() {
    assertEquals("", portfolio.examine());

    InvestorImpl.InvestorStock i = new InvestorImpl.InvestorStock("name", 20, 10, 10);
    portfolio.addStock(i);

    String expected = "name 10.0 20 ";
    assertEquals(expected, portfolio.examine());
  }

  @Test
  public void testGetName() {
    assertEquals("test", portfolio.getName());
  }

}