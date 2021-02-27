
public class Main {
    public static void main(String[] args){
        Run_Calculator();
    }
    static private void Run_Calculator(){
        Calculator calculator = setCalculatorArg();
        String[] values = getStringArr(calculator);
        try{
            int result = calculator.operation(values);
            Output.outputs(result);
        }catch(Exception e){
            Output.errorOutput(e);
            Run_Calculator();
        }
    }

    static private Calculator setCalculatorArg(){
        Calculator calculator = new Calculator();
        return calculator;
    }
    static private String[] getStringArr(Calculator calculator){
        calculator = new Calculator();
        String value = Input.inputs();
        String [] values = calculator.split(value);
        return values;
    }
}
