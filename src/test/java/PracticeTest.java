import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class PracticeTest {
    @Test
    void split(){
        String inputStr1 = "1,2";
        String inputStr2 = "1";
        assertThat(inputStr1.split(",")).isEqualTo(new String[]{"1", "2"});
        assertThat(inputStr2.split(",")).isEqualTo(new String[]{"1"});
    }

    @Test
    void substring(){
        String inputStr = "(1,2)";
        assertThat(inputStr.substring(0, inputStr.length()-1)).isEqualTo("1,2");
    }

    @Test
    @DisplayName("특정 위치 문자를 가져오고, 위치값을 벗어나면 예외를 발생시킨다.")
    void charAt(){
        assertThat("abc".charAt(0)).isEqualTo('a');
        assertThat("abc".charAt(1)).isEqualTo('b');
        assertThat("abc".charAt(2)).isEqualTo('c');

        assertThatThrownBy(() -> {
            "abc".charAt(4);
        }).isInstanceOf(StringIndexOutOfBoundsException.class)
                .hasMessageContaining("StringIndexOutofBounds");
    }
}