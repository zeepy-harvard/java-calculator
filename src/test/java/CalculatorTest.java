import org.junit.Test;

public class CalculatorTest {

    @Test
    public void testSum(){
        Calculator c = new Calculator();
        double result = c.sum(10,50);
    }
}
