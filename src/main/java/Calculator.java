public class Calculator {
    private final int OPERATION_NUMBER_INDEX = 1;
    private final int SECOND_NUMBER_INDEX = 2;
    private final char ADD_OPERATION = '+';
    private final char SUBSTRACT_OPERATION = '-';
    private final char MULTIPLY_OPERATION = '*';
    private final char DIVIDED_OPERATION = '/';
    private final int OPERATION_FIXED_INDEX = 0;
    private final String SPACE_BLANK = " ";

    public void Calculator(){};
    public int operation(String[] values)throws  Exception{
        this.validation(values);

        return filtNumberAndOperation(values);
    }

    public int filtNumberAndOperation(String[] values){
        int result=0;
        for(int i=0;i<values.length-2;i+=2){
            result = classifyIndex(i,result,values);
        }
        return result;
    }

    public int classifyIndex(int i, int result, String[] values){
        int firstNumber = Integer.parseInt(values[i]);
        int secondNumber = Integer.parseInt(values[i+SECOND_NUMBER_INDEX]);
        String op = values[i+OPERATION_NUMBER_INDEX];

        if(i==0) return operating(firstNumber,secondNumber,op);

        return operating(result,secondNumber,op);
    }

    public int operating(int firstNumber,int secondNumber,String op){
        char convertOp = convertChar(op);
        int result=0;
        if(convertOp==ADD_OPERATION) {result = add(firstNumber,secondNumber);}
        if(convertOp==SUBSTRACT_OPERATION) {result = subtract(firstNumber,secondNumber);}
        if(convertOp==MULTIPLY_OPERATION) {result = multiply(firstNumber,secondNumber);}
        if(convertOp==DIVIDED_OPERATION) {result = divide(firstNumber,secondNumber);}
        return result;
    }

    public char convertChar(String op){
        return op.charAt(OPERATION_FIXED_INDEX);
    }

    public int add(int firstNumber,int secondNumber){
        return firstNumber+secondNumber;
    }

    public int subtract(int firstNumber, int secondNumber){
        return firstNumber-secondNumber;
    }

    public int multiply(int firstNumber,int secondNumber){
        return firstNumber*secondNumber;
    }

    public int divide(int firstNumber,int secondNumber){
        return firstNumber/secondNumber;
    }

    public String[] split(String value){
        return value.split(SPACE_BLANK);
    }

    public void validation(String[] values){
        if(values.length % 2 == 0) throw new IllegalArgumentException("잘못된 연산입력입니다.");
    }
}
