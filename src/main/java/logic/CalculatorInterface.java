package logic;

import exceptions.NotValidExpressionException;

public interface CalculatorInterface {
  String getResult(String expression) throws NotValidExpressionException;
}
