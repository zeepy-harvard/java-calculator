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
            operation = new Operation();
            operation.setValues(input);

            try{
                operation.checkValidation();
                operation.runOperation();
            }catch (Exception e){
                System.out.println(e.getMessage());
            }

            System.out.println(operation.getResult());
        }
        System.out.println("Exit");
    }

}
