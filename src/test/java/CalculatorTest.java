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
        String[] values = calculator.split(value);

        assertThat(calculator.operation(values)).isEqualTo(10);
    }

    @Test
    @DisplayName("For문으로_연산자_앞뒤숫자_필터링")
    public void filtNumberAndOperationTest(){
        Calculator calculator = new Calculator();

        String[] values = {"2","+","3","*","4","/","2"};

        assertThat(calculator.filtNumberAndOperation(values)).isEqualTo(10);
    }

    @Test
    @DisplayName("Index가_0_일떄_아닐때_구분")
    public void classifyIndexTest(){
        int firstI = 0;
        int secondI = 0;
        int firstResult = 0;
        int secondResult = 0;
        String[] values = {"2","+","3","*","4"};

        Calculator calculator = new Calculator();
        secondResult = calculator.classifyIndex(firstI,firstResult,values);
        secondI +=2;

        assertThat(calculator.classifyIndex(firstI,firstResult,values)).isEqualTo(5);
        assertThat(calculator.classifyIndex(secondI,secondResult,values)).isEqualTo(20);
    }

    @Test
    @DisplayName("연산자_구분_연산")
    public void operatingTest(){
        int firstNumber = 1;
        int secondNumber = 10;
        String op = "+";
        Calculator calculator = new Calculator();
        assertThat(calculator.operating(firstNumber,secondNumber,op)).isEqualTo(11);
    }

    @Test
    @DisplayName("String_연산자_to_char")
    public void convertCharTest(){
        Calculator calculator = new Calculator();
        assertThat(calculator.convertChar("+")).isEqualTo('+');
    }

    @Test
    @DisplayName("덧셈연산")
    public void addTest(){
        Calculator calculator = new Calculator();
        assertThat(calculator.add(10,20)).isEqualTo(30);
    }

    @Test
    @DisplayName("뺄샘연산")
    public void substractTest(){
        Calculator calculator = new Calculator();
        assertThat(calculator.substract(10,20)).isEqualTo(-10);
    }

    @Test
    @DisplayName("곱셈연산")
    public void multiplyTest(){
        Calculator calculator = new Calculator();
        assertThat(calculator.multiply(10,2)).isEqualTo(20);
    }

    @Test
    @DisplayName("나눗셈연산")
    public void divideTest(){
        Calculator calculator = new Calculator();
        assertThat(calculator.divide(8,4)).isEqualTo(2);
    }

    @Test
    @DisplayName("빈칸_기준으로_String[]")
    public void splitTest(){
        Calculator calculator = new Calculator();
        assertThat(calculator.divide(8,4)).isEqualTo(2);
    }

    @Test
    @DisplayName("에러_연산value_validation")
    public void validationTest(){
        Calculator calculator = new Calculator();
        String errorValue = "2 + 3 * 4 / ";

        String[] errorValues = calculator.split(errorValue);

        assertThatThrownBy(()->{
            assertThat(calculator.operation(errorValues)).isEqualTo(10);
        }).isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("잘못된 연산입력입니다.");
    }
}
