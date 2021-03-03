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
            calculator.operation(errorValues);
        }).isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("잘못된 연산입력입니다.");
    }

    @Test
    @DisplayName("에러_잘못된_연산자")
    public void wrong_input_value(){
        Calculator calculator = new Calculator();
        String errorValue = "2 + 3 _ 4";
        String[] errorValues = Input.split(errorValue);
        assertThatThrownBy(()->{
            calculator.operation(errorValues);
        }).isInstanceOf(RuntimeException.class)
                .hasMessageContaining("올바르지 않는 연산자입니다.");
    }
}
