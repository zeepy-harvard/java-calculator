import java.util.Scanner;

public class Input {
    static private final String SPACE_BLANK =" ";

    public static String inputs(){
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }
    public static String[] split(String value){
        return value.split(SPACE_BLANK);
    }
}
