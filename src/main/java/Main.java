
public class Main {
    public static void main(String[] args){
        Run_Calculator();
    }
    private static void Run_Calculator(){
        Calculator calculator = new Calculator();
        String[] values = getStringArr(calculator);
        try{
            int result = calculator.operation(values);
            Output.outputs(result);
        }catch(Exception e){
            Output.errorOutput(e);
            Run_Calculator();
        }
    }

    private static String[] getStringArr(Calculator calculator){
        String value = Input.inputs();
        String [] values = Input.split(value);
        return values;
    }
}
