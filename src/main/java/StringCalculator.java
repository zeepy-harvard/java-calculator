import java.util.Scanner;

public class StringCalculator {

    public static void main(String[] args) {
        Operation operation;
        int result = 0;

        while (true) {
            Scanner scanner = new Scanner(System.in);
            String input = scanner.nextLine();

            if (input.equals("-1")) {
                break;
            }

            operation = new Operation();
            result = operation.runOperation(input);
            System.out.println("계산 결과 :" + result);
        }
        System.out.println("Exit");
    }
}
