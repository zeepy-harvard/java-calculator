public class Calculator {
    private static final int OPERATION_NUMBER_INDEX = 1;
    private static final int SECOND_NUMBER_INDEX = 2;
    private static final char ADD_OPERATION = '+';
    private static final char SUBSTRACT_OPERATION = '-';
    private static final char MULTIPLY_OPERATION = '*';
    private static final char DIVIDED_OPERATION = '/';
    private static final int OPERATION_FIXED_INDEX = 0;
    private static final String SPACE_BLANK = " ";

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
        if (convertOp == ADD_OPERATION) {
            return add(firstNumber, secondNumber);
        }
        if (convertOp == SUBSTRACT_OPERATION) {
            return subtract(firstNumber, secondNumber);
        }
        if (convertOp == MULTIPLY_OPERATION) {
            return multiply(firstNumber, secondNumber);
        }
        if (convertOp == DIVIDED_OPERATION) {
            return divide(firstNumber, secondNumber);
        }
        throw new RuntimeException("올바르지 않는 연산자입니다.");
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
