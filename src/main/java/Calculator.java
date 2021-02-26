import java.util.Scanner;

public class Calculator {
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
        int secondNumber = Integer.parseInt(values[i+2]);
        String op = values[i+1];
        if(i==0){
            result = operating(firstNumber,secondNumber,op);
        }else{
            result = operating(result,secondNumber,op);
        }
        return result;
    }

    public int operating(int firstNumber,int secondNumber,String op){
        char convertOp = convertChar(op);
        int result=0;
        if(convertOp=='+') {result = add(firstNumber,secondNumber);}
        if(convertOp=='-') {result = substract(firstNumber,secondNumber);}
        if(convertOp=='*') {result = multiply(firstNumber,secondNumber);}
        if(convertOp=='/') {result = divide(firstNumber,secondNumber);}
        return result;
    }
    public char convertChar(String op){
        return op.charAt(0);
    }

    public int add(int firstNumber,int secondNumber){
        return firstNumber+secondNumber;
    }

    public int substract(int firstNumber,int secondNumber){
        return firstNumber-secondNumber;
    }

    public int multiply(int firstNumber,int secondNumber){
        return firstNumber*secondNumber;
    }

    public int divide(int firstNumber,int secondNumber){
        return firstNumber/secondNumber;
    }

    public String[] split(String value){
        return value.split(" ");
    }

    public String input(){
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    public void validation(String[] values){
        if(values.length % 2 == 0) throw new IllegalArgumentException("잘못된 연산입력입니다.");
    }
}
