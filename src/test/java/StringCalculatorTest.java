import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class StringCalculatorTest {
    @Test
    @DisplayName("String배열에 입력값이 알맞게 들어가는지 확인하는 함수")
    public void testSplit(){
        Operation op = new Operation("2 + 3 * 4 / 2");

        assertThat(values[0]).isEqualTo("2");
        assertThat(values[6]).isEqualTo("2");
    }
}