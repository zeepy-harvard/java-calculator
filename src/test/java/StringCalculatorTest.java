import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

class StringCalculatorTest {
    private static Stream<Arguments> provideInputData() {
        return Stream.of(
                Arguments.of("2 * 6 - 1 * 2", 22),
                Arguments.of("13", 13),
                Arguments.of("10 - 2 * 2", 16)
        );
    }

    @MethodSource("provideInputData")
    @ParameterizedTest
    public void testCalculation(String inputData, int result) {
        Operation operation = new Operation();
        assertThat(operation.runOperation(inputData)).isEqualTo(result);
    }

    @Test
    @DisplayName("입력된 식의 띄어쓰기가 잘못되었을 때 예외처리")
    public void testCheckValidation() {
        String wrongInputData = "12+ 4";
        Operation operation = new Operation();

        assertThatThrownBy(() -> {
            operation.runOperation(wrongInputData);
        }).isInstanceOf(IllegalArgumentException.class)
                .hasMessage("invalid term.");
    }
}