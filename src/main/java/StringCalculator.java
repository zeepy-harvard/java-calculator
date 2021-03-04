import java.util.Scanner;

public class StringCalculator {

    public static void main(String[] args) {
        Operation operation;

        while (true){
            Scanner scanner = new Scanner(System.in);
            String input = scanner.nextLine();

            if(input.equals("-1")){
                break;
            }
            operation = new Operation(input);

            start(operation);
        }
        System.out.println("Exit");
    }

    private static void start(Operation operation) {

        int result = 0;

        try{
            operation.checkValidation();
            result = operation.runOperation();
            System.out.println("계산 결과 :" + result);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }


}
