
public class Operation {
    private int result = 0;
    private String operator;
    private boolean firstExists = false;

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

    public void checkValue(String value) {
        if (value.matches("\\d*(\\.\\d+)?")) {
            if (firstExists) {
                operation(Integer.parseInt(value));
            } else {
                result = Integer.parseInt(value);
                firstExists = true;
            }
        } else {
            operator = value;
        }
    }

    private void operation(int secondOperand) {
        switch (operator) {
            case "+":
                add(secondOperand);
                break;
            case "-":
                minus(secondOperand);
                break;
            case "*":
                multiple(secondOperand);
                break;
            case "/":
                divide(secondOperand);
                break;
        }
    }

    public int getResult() {
        return result;
    }
}
