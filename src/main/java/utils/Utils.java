package utils;

import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.List;

public class Utils {
  private static final List<String> OPERATIONS = Arrays.asList("-", "+", "/", "*");
  private static final List<String> SEPARATORS = Arrays.asList(".");


  public static String prepareExpression(String expression) {
    expression = StringUtils.deleteWhitespace(expression);
    expression = expression.replace(",", ".");

    return expression;
  }

  public static boolean isValidExpression(String expression) {
    for (int i = 0; i <= expression.length() - 1; i++) {
      if (isNotValidChar(expression.charAt(i)) || (i != 0 && areTwoSigns(expression.charAt(i), expression.charAt(i-1)))) {
        return false;
      }
    }

    return true;
  }

  public static String getNumberBefore(String expression) {
    String number = "";

    for(int charIndex = expression.length() - 1; charIndex >= 0; charIndex--) {
      String character = Character.toString(expression.charAt(charIndex));
      if (isValidNumber(character, expression, charIndex)) {
        number = character.concat(number);
      } else if (isNegativeNumber(character, expression, charIndex)) {
        number = "-".concat(number);
        return number;
      } else {
        return number;
      }
    }

    return number;
  }

  public static String getNumberAfter(String expression) {
    String number = String.valueOf(expression.charAt(0));

    for(int charIndex = 1; charIndex <= expression.length() - 1; charIndex++) {
      String character = Character.toString(expression.charAt(charIndex));
      if (StringUtils.isNumeric(character) || character.equals(".")) {
        number = number.concat(character);
      }
      else {
        return number;
      }
    }

    return number;
  }

  private static boolean isValidNumber(String character, String expression, int charIndex) {
    return !OPERATIONS.contains(character) && (charIndex == expression.length() - 1 || !OPERATIONS.contains(String.valueOf(expression.charAt(charIndex+1))));
  }

  private static boolean isNegativeNumber(String character, String expression, int charIndex) {
    return (charIndex == 0 && character.equals("-"))
      || (OPERATIONS.contains(character) && OPERATIONS.contains(String.valueOf(expression.charAt(charIndex - 1))));
  }

  private static boolean areTwoSigns(char currentChar, char previousChar) {
    final String currentCharacter = String.valueOf(currentChar);
    final String previousCharacter = String.valueOf(previousChar);

    return (OPERATIONS.contains(currentCharacter) || SEPARATORS.contains(currentCharacter))
      && (OPERATIONS.contains(previousCharacter) || SEPARATORS.contains(previousCharacter));
  }

  private static boolean isNotValidChar(char currentChar) {
    final String character = String.valueOf(currentChar);

    return !StringUtils.isNumeric(character)
      && !OPERATIONS.contains(character)
      && !SEPARATORS.contains(character)
      && !character.equals("(")
      && !character.equals(")");
  }

}
