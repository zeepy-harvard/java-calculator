import java.util.Scanner;

public class Calculator {
    public void Calculator(){};
    public int operation(String[] values)throws  Exception{
        this.validation(values);

        int result=0;
        for(int i=0;i<values.length-2;i+=2){
            int firstNumber = Integer.parseInt(values[i]);
            int secondNumber = Integer.parseInt(values[i+2]);
            String op = values[i+1];
            if(i==0){
                result = operating(firstNumber,secondNumber,op);
            }else{
                result = operating(result,secondNumber,op);
            }
        }
        return result;
    }

    private int operating(int firstNumber,int secondNumber,String op){
        int result;
        switch (op){
            case "+":
                result = firstNumber+secondNumber;
                break;
            case "-":
                result = firstNumber-secondNumber;
                break;
            case "*":
                result = firstNumber*secondNumber;
                break;
            case "/":
                result = firstNumber/secondNumber;
                break;
            default:
                throw new IllegalStateException("허용되지 않는 연산자: " + op);
        }
        return result;
    }

    public String input(){
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    public void validation(String[] values){
        if(values.length % 2 == 0) throw new IllegalArgumentException("잘못된 연산입력입니다.");
    }
}
