import java.util.Scanner;

public class Operation {

    public int runOperation(String input) {
        String[] values = input.split(" ");
        int result = 0;
        String operator = "";

        try {
            checkValidation(values);
            result = Integer.parseInt(values[0]);
            result = getResult(values, result, operator);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return result;
    }

    private int getResult(String[] values, int result, String operator) {
        for (int i = 1; i < values.length; i++) {
            if (i % 2 == 0) {
                result = operation(Integer.parseInt(values[i]), operator, result);
            } else {
                operator = values[i];
            }
        }
        return result;
    }

    private void checkValidation(String[] values) throws IllegalArgumentException {
        for (int i = 0; i < values.length; i += 2) {
            if (!values[i].matches("^[0-9]*$")) {
                throw new IllegalArgumentException("invalid term.");
            }
        }
    }

    private int operation(int operand, String operator, int result) throws IllegalArgumentException {
        switch (operator) {
            case "+":
                result += operand;
                break;
            case "-":
                result -= operand;
                break;
            case "*":
                result *= operand;
                break;
            case "/":
                result /= operand;
                break;
            default:
                throw new IllegalArgumentException("Invalid operator input. Only +, -, *, / works.");
        }
        return result;
    }
}
