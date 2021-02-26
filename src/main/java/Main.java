
public class Main {
    public static void main(String[] args)throws Exception{
        Calculator calculator = new Calculator();
        String value = calculator.input();
        String [] values = calculator.split(value);
        int result = calculator.operation(values);
        System.out.println("계산기 결과>>> "+result);
    }
}
