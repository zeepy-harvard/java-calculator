import java.util.Scanner;

public class StringCalculator {

    static String[] values = new String[0];

    public static void main(String[] args) {
        Operation operation = new Operation();

        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        values = input.split(" ");

        for (String value : values) {
            operation.checkValueType(value);
        }

        System.out.println(operation.getResult());
    }
}
