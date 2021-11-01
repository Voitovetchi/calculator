package logic;

import exceptions.NotValidExpressionException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CalculatorTest {

  @Test
  public void testGetResult() throws NotValidExpressionException {
    final CalculatorInterface calculator = new Calculator();
    assertEquals(String.valueOf(6.0), calculator.getResult("3-(-3)"));
    assertEquals(String.valueOf(12.0), calculator.getResult("(-3)*((-2)*2)"));
    assertEquals(String.valueOf(50.0), calculator.getResult("(3+2)*(5+5)"));
    assertEquals(String.valueOf(-2.266666666666666), calculator.getResult("((3.9+2*(3-5.8))*(5+5))/7.5"));
    assertEquals(String.valueOf(51.0), calculator.getResult("54+(9-(-3)*(-4))"));
  }
}
