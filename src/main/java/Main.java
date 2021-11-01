import exceptions.NotValidExpressionException;
import logic.Calculator;
import logic.CalculatorInterface;
import org.apache.commons.lang3.math.NumberUtils;

import java.util.Scanner;

public class Main {

  public static void main(String[] args) {
    int option;
    do {
      Scanner scanner = new Scanner(System.in);
      System.out.println("Press 0 to exit. Press 1 to enter an expression");
      option = NumberUtils.toInt(scanner.next(), 1000);

      switch (option) {
        case 1:
          System.out.println("Expression must contain only numeric values or signs /,*,-,+,(,). All double values should be separated by point." +
            "\nAll negative values must be declared using phrases. An example of a valid expression is ((-6)*7+5/5).");
          System.out.println("Enter an expression");
          String expression = scanner.next();
          System.out.println("Result");
          try {
            System.out.println(calculateExpression(expression));
          } catch (Exception exception) {
            System.out.println("Error: " + exception.getMessage() + "\nPlease try again");
          }
          break;
        case 0:
          System.out.println("Have a good day");
          break;
        default:
          System.out.println("Wrong option. Please try again");
      }

    } while (option != 0);
  }

  private static String calculateExpression(String expression) throws NotValidExpressionException {
    final CalculatorInterface calculator = new Calculator();
    return calculator.getResult(expression);
  }
}
