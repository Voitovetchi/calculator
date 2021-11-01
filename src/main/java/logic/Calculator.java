package logic;

import exceptions.NotValidExpressionException;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import utils.Utils;

public class Calculator implements CalculatorInterface {

  @Override
  public String getResult(String expression) throws NotValidExpressionException {
    if (!Utils.isValidExpression(expression)) {
      throw new NotValidExpressionException("Expression is not valid");
    }

    expression = resolvePhrases(expression);

    expression = resolveOperations("*", "/", expression);

    return resolveOperations("-", "+", expression);
  }

  private String resolvePhrases(String expression) {
    while (expression.contains("(")) {
      String expressionPart = expression;

      while (expressionPart.contains("(")) {
        expressionPart = getExpressionInPhrases(expressionPart);
      }

      String phrasesMultiplyDivideResult = resolveOperations("*", "/", expressionPart);
      String phrasesMinusPlusResult = resolveOperations("-", "+", phrasesMultiplyDivideResult);

      expression = expression.replace("(" + expressionPart + ")", phrasesMinusPlusResult);
    }

    return expression;
  }

  private String getExpressionInPhrases(String expressionPart) {
    if (StringUtils.substringBetween(expressionPart, "(", ")").contains("(")) {
      expressionPart = expressionPart.substring(expressionPart.indexOf("(") + 1, expressionPart.lastIndexOf(")"));
    } else {
      expressionPart = expressionPart.substring(expressionPart.indexOf("(") + 1, expressionPart.indexOf(")"));
    }

    return expressionPart;
  }

  private String resolveOperations(String firstOperation, String secondOperation, String expressionPart) {
    while (!NumberUtils.isCreatable(expressionPart) && (expressionPart.contains(firstOperation) || expressionPart.contains(secondOperation))) {
      if (!expressionPart.contains(secondOperation) || (expressionPart.indexOf(firstOperation, 1) < expressionPart.indexOf(secondOperation) && expressionPart.indexOf(firstOperation, 1) != -1)) {
        final String firstNumber = Utils.getNumberBefore(expressionPart.substring(0, expressionPart.indexOf(firstOperation, 1)));
        final String secondNumber = Utils.getNumberAfter(expressionPart.substring(expressionPart.indexOf(firstOperation,1) + 1));
        final double result = calculate(Double.parseDouble(firstNumber), Double.parseDouble(secondNumber), firstOperation);
        expressionPart = expressionPart.replace(firstNumber + firstOperation + secondNumber, Double.toString(result));
      } else {
        final String firstNumber = Utils.getNumberBefore(expressionPart.substring(0, expressionPart.indexOf(secondOperation)));
        final String secondNumber = Utils.getNumberAfter(expressionPart.substring(expressionPart.indexOf(secondOperation) + 1));
        final double result = calculate(Double.parseDouble(firstNumber), Double.parseDouble(secondNumber), secondOperation);
        expressionPart = expressionPart.replace(firstNumber + secondOperation + secondNumber, Double.toString(result));      }
    }

    return expressionPart;
  }

  private double calculate(double firstVal, double secondVal, String operation) {
    switch (operation) {
      case "-" :
        return firstVal - secondVal;
      case "+" :
        return firstVal + secondVal;
      case "/" :
        if (secondVal == 0) {
          throw new ArithmeticException("You can not divide by 0");
        }
        return firstVal / secondVal;
      case "*" :
        return firstVal * secondVal;
      default:
        throw new IllegalArgumentException("There is no such operation");
    }
  }
}
