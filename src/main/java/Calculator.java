public class Calculator {
    static private final int OPERATION_NUMBER_INDEX = 1;
    static private final int SECOND_NUMBER_INDEX = 2;
    static private final char ADD_OPERATION = '+';
    static private final char SUBSTRACT_OPERATION = '-';
    static private final char MULTIPLY_OPERATION = '*';
    static private final char DIVIDED_OPERATION = '/';
    static private final int OPERATION_FIXED_INDEX = 0;
    static private final String SPACE_BLANK = " ";

    public int operation(String[] values) {
        validation(values);

        return filtNumberAndOperation(values);
    }

    private int filtNumberAndOperation(String[] values) {
        int result = 0;
        for (int i = 0; i < values.length - 2; i += 2) {
            result = classifyIndex(i, result, values);
        }
        return result;
    }

    private int classifyIndex(int i, int result, String[] values) {
        int firstNumber = Integer.parseInt(values[i]);
        int secondNumber = Integer.parseInt(values[i + SECOND_NUMBER_INDEX]);
        String op = values[i + OPERATION_NUMBER_INDEX];

        if (i == 0) return operating(firstNumber, secondNumber, op);

        return operating(result, secondNumber, op);
    }

    private int operating(int firstNumber, int secondNumber, String op) {
        char convertOp = convertChar(op);
        int result = 0;
        if (convertOp == ADD_OPERATION) {
            result = add(firstNumber, secondNumber);
        }
        if (convertOp == SUBSTRACT_OPERATION) {
            result = subtract(firstNumber, secondNumber);
        }
        if (convertOp == MULTIPLY_OPERATION) {
            result = multiply(firstNumber, secondNumber);
        }
        if (convertOp == DIVIDED_OPERATION) {
            result = divide(firstNumber, secondNumber);
        }
        return result;
    }

    private char convertChar(String op) {
        return op.charAt(OPERATION_FIXED_INDEX);
    }

    private int add(int firstNumber, int secondNumber) {
        return firstNumber + secondNumber;
    }

    private int subtract(int firstNumber, int secondNumber) {
        return firstNumber - secondNumber;
    }

    private int multiply(int firstNumber, int secondNumber) {
        return firstNumber * secondNumber;
    }

    private int divide(int firstNumber, int secondNumber) {
        return firstNumber / secondNumber;
    }

    private void validation(String[] values) {
        if (values.length % 2 == 0) throw new IllegalArgumentException("잘못된 연산입력입니다.");
    }
}
