import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Scanner;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class CalculatorTest {
    @Test
    public void testSum()throws Exception{
        Calculator calculator = new Calculator();

        String value = "2 + 3 * 4 / 2";
        String errorValue = "2 + 3 * 4 / ";

        String[] values = value.split(" ");
        String[] errorValues = errorValue.split(" ");

        assertThat(calculator.operation(values)).isEqualTo(10);
        assertThatThrownBy(()->{
            assertThat(calculator.operation(errorValues)).isEqualTo(10);
        }).isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("잘못된 연산입력입니다.");
    }
}
