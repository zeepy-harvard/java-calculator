import java.util.Scanner;

public class Operation {
    private int result = 0;
    private String operator;
    private String[] values;

    private void add(int operand) {
        result += operand;
    }

    private void minus(int operand) {
        result -= operand;
    }

    private void multiple(int operand) {
        result *= operand;
    }

    private void divide(int operand) {
        result /= operand;
    }

    public Operation(String input) {
        values = new String[0];
        values = input.split(" ");
    }

    public int runOperation() {

        result = Integer.parseInt(values[0]);

        for (int i = 1; i < values.length; i++) {
            if (i % 2 == 0) {
                operation(Integer.parseInt(values[i]));
            } else {
                operator = values[i];
            }
        }

        return result;
    }

    public void checkValidation() throws IllegalArgumentException {
        for (int i = 0; i < values.length; i += 2) {
            if (!values[i].matches("^[0-9]*$")) {
                throw new IllegalArgumentException("invalid term.");
            }
        }
    }

    public void operation(int operand) throws IllegalArgumentException {
        switch (operator) {
            case "+":
                add(operand);
                break;
            case "-":
                minus(operand);
                break;
            case "*":
                multiple(operand);
                break;
            case "/":
                divide(operand);
                break;
            default:
                throw new IllegalArgumentException("Invalid operator input. Only +, -, *, / works.");
        }
    }

    public void setOperator(String op) {
        operator = op;
    }

    public void setValues(String input) {

    }
}
