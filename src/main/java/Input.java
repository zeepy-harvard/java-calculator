import java.util.Scanner;

public class Input {
    static private final String SPACE_BLANK =" ";

    static public String inputs(){
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }
    static public String[] split(String value){
        return value.split(SPACE_BLANK);
    }
}
