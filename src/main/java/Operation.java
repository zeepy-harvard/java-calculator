
public class Operation {
    private int result = 0;
    private String operator;
    private boolean isFirst = true;


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

    public void checkValueType(String value) {
        if (value.matches("^[0-9]*$")) {
            if(isFirst){
                result = Integer.parseInt(value);
                isFirst = false;
            } else {
                operation(Integer.parseInt(value));
            }
        } else {
            operator = value;
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
            default: throw new IllegalArgumentException("Invalid operator input. Only +, -, *, / works.");
        }
    }

    public int getResult() {
        return result;
    }

    public void setOperator(String op){
        operator = op;
    }
}
