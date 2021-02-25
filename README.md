## 계산기를 만들어 봅시다


## 참고자료
[노션](https://www.notion.so/151670d41d4a482ca17134c8d2d534bf)

## 목적

- 단위 테스트를 익혀보기

## 요구사항

* 사용자가 입력한 문자열 값에 따라 사칙연산을 수행할 수 있는 계산기를 구현해야 합니다.
* 문자열 계산기는 사칙연산의 계산 우선순위가 아닌 입력 값에 따라 계산 순서가 결정됩니다.  즉, 수학에서는 곱셈, 나눗셈이 덧셈, 뺄셈 보다 먼저 계산해야 하지만 이를 무시합니다.
* 예를 들어 ```2 + 3 * 4 / 2```와 같은 문자열을 입력할 경우 ```2 + 3 * 4 / 2``` 실행 결과인 10을 출력해야 합니다.  우리가 알고있는 사칙연산의 계산 우선순위로 인한 ```3 * 4 / 2 + 2``` 의 결과인 8은 오답입니다.

## 풀이

1. TDD에서는 Scanner가 작동하지 않음으로 임의의 값을 설정
2. 띄어쓰기를 기준으로 String배열로 나눠주기 위해 **split** 사용
3. **Caculator.validation**을 통해 연산자 뒤에 값이 없는 잘못된 입력값인지 확인(잘못된 입력값이면 **IllegalArgumentExceoption**발생)
4. **Calculator.operation**에서 연산해줄 값을 설정
5. **Calculator.operating**에서 각 값을 연산해줌.



## 코드

```java
[CalculatorTest.java]

public class CalculatorTest {
    @Test
    public void testSum()throws Exception{
        Calculator calculator = new Calculator();

        String value = "2 + 3 * 4 / 2"; //올바른 입력값
        String errorValue = "2 + 3 * 4 / "; //잘못된 입력값

        String[] values = value.split(" ");	//띄어쓰기를 기준으로 String배열로 반환
        String[] errorValues = errorValue.split(" ");

        assertThat(calculator.operation(values)).isEqualTo(10);	//올바른 입력값을 연산
        assertThatThrownBy(()->{	//잘못된 입력값 연산시 IllegalArgumentExcetion을 예상한 테스트
            assertThat(calculator.operation(errorValues)).isEqualTo(10);
        }).isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("잘못된 연산입력입니다.");
    }
}
```

```java
[Calculator.java]

public class Calculator {
    public void Calculator(){};
    public int operation(String[] values)throws  Exception{
        this.validation(values);

        int result=0;
        for(int i=0;i<values.length-2;i+=2){
            int firstNumber = Integer.parseInt(values[i]);
            int secondNumber = Integer.parseInt(values[i+2]);
            String op = values[i+1];
            if(i==0){	//첫번째 for문에서는 연산된 값이 없기때문에 배열의 첫번째 값으로 넣어준다.
                result = operating(firstNumber,secondNumber,op);
            }else{	//두번쨰 for문부터는 연산된 값이 있기때문에 이전에 연산된 값을 첫번쨰 파라미터에 넣어준다.
                result = operating(result,secondNumber,op);
            }
        }
        return result;
    }

    private int operating(int firstNumber,int secondNumber,String op){
        int result;
        switch (op){
            case "+":
                result = firstNumber+secondNumber;
                break;
            case "-":
                result = firstNumber-secondNumber;
                break;
            case "*":
                result = firstNumber*secondNumber;
                break;
            case "/":
                result = firstNumber/secondNumber;
                break;
            default:
                throw new IllegalStateException("허용되지 않는 연산자: " + op);	//형식상 exception
        }
        return result;
    }

    public String input(){	//TDD에서는 실행되지 않지만 프로덕션 코드에서는 사용할것.
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    public void validation(String[] values){	//연산자 뒤에 값이 없다면 배열의 크기가 짝수이다.
        if(values.length % 2 == 0) throw new IllegalArgumentException("잘못된 연산입력입니다.");
    }
}
```