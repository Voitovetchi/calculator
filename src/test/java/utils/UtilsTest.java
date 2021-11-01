package utils;

import org.junit.jupiter.api.Test;
import utils.Utils;

import static org.junit.jupiter.api.Assertions.*;


public class UtilsTest {
  @Test
  public void testPrepareExpression() {
    assertEquals("3-3", Utils.prepareExpression("3 - 3"));
  }

  @Test
  public void testIsValidExpression() {
    assertFalse(Utils.isValidExpression("string"));
    assertFalse(Utils.isValidExpression("3++4"));
    assertFalse(Utils.isValidExpression("3e+4"));
    assertTrue(Utils.isValidExpression("3-(3*3)"));
  }

  @Test
  public void testGetNumberBefore() {
    assertEquals("-3", Utils.getNumberBefore("3--3"));
  }

  @Test
  public void testGetNumberAfter() {
    assertEquals("10.2", Utils.getNumberAfter("10.2-"));
  }
}
