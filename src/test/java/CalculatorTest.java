import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DisplayName("javaCalculator테스트")
public class CalculatorTest {
    @Test
    @DisplayName("올바른_연산식_계산")
    public void successTest()throws Exception{
        Calculator calculator = new Calculator();
        String value = "2 + 3 * 4 / 2";
        String[] values = Input.split(value);

        assertThat(calculator.operation(values)).isEqualTo(10);
    }

    @Test
    @DisplayName("에러_연산value_validation")
    public void validationTest(){
        Calculator calculator = new Calculator();
        String errorValue = "2 + 3 * 4 / ";

        String[] errorValues = Input.split(errorValue);

        assertThatThrownBy(()->{
            assertThat(calculator.operation(errorValues)).isEqualTo(10);
        }).isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("잘못된 연산입력입니다.");
    }
}
