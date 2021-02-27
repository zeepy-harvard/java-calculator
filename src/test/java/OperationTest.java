import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class OperationTest {
    @Test
    @DisplayName("첫번째 항을 결과값으로 잘 세팅하는지 확인하는 함수")
    public void testCheckValue(){
        Operation op = new Operation();
        op.checkValue("234");
        assertThat(op.getResult()).isEqualTo(234);
    }

    @Test
    @DisplayName("연산자에 알맞는 연산이 이루어지는지 확인하는 함수")
    public void testOperation(){
        Operation op = new Operation();
        op.setOperator("+");
        op.operation(5);
        assertThat(op.getResult()).isEqualTo(5);
    }
}