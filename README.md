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



## 1차 피드백

---------------------------------

1. 메인 클래스를 만들어주세요.
2. 메인 메서드에서 계산기에 해당하는 객체를 만들어주세요.
3. 계산기의 기능들을 나누어서 메서드를 만들어주세요.
4. 각 메서드의 기능들을 테스트하는 코드를 만들어주세요.
5. 인덴트의 깊이를 2 미만으로 만들어주세요.
6. DisplayName 어노테이션으로 테스트 메서드의 목적을 적어주세요.

---------------------------------

## 2차 풀이

1. Main클래스 제작

2. Main클래스에 계산기 객체 생성 및 작동완료

   ```java
   public class Main {
       public static void main(String[] args)throws Exception{
           Calculator calculator = new Calculator();
           String value = calculator.input();
           String [] values = calculator.split(value);
           int result = calculator.operation(values);
           System.out.println("계산기 결과>>> "+result);
       }
   }
   ```

   

3. 계산기 기능을 나눠서 메서드 제작\
   
4. calculator()메소드에서 구현하던 기능들을 더욱 세분화해서 **filtNumberAndOperation**, **classifyIndex**, **convertChar**, **add**, **substract**, **multiply**, **divid**, **split**로 메소드 마다 최대한 "단일 책임 원칙"을 부여했다.

5. 각 메서드 마다 @Test로 테스트 완료

6. 인덴트의 깊이를 2미만
   즉 for문에 if문이라던가, 이중포문 사용 금지.
   메소드를 세분화 하여 해결

7. DisplayName어노테이션 적용 완료(?)


## 2차  피드백

-----------------------------

1. 접근제어자를 이해하시고 본인의 코드를 수정해주세요.
2. Calculator 클래스가 모든것을 담당하고 있어요. Input을 담당하는 클래스를 만들어주고, 정적 메소드를 활용해 입력을 받아봅시다.
3. Main 클래스에서 print문을 직접 사용하고 있어요. Output을 담당하는 클래스를 만들어주고, 정적 메소드를 활용해 출력해봅시다.

---------------------------------------------

## 3차 풀이

-------------------------------

1. 접근 제어자 이해

   * private
     - private가 선언된 클래스 멤버는 외부에 공개되지 않는다.(직접 접근 불가능)
     - private가 선언된 클래스 멤버에 접근하기 위해서는 public메소드를 통해서만 접근 가능
     - **해당 class에서만 접근 가능**
     - **다른 패키지의 클래스 접근 불가능**
   * public
     * public이 선언된 클래스 멤버는 외부로 공개된다.(직접 접근 가능)
     * public메소드는 private멤버와 프로그램 사이의 인터페이스(interface)역할을 수행한다.
     * **같은 패키지의 클래스 접근 가능**
     * **다른 패키지 클래스 접근 가능**
   * default
     * 접근 제어자가 지정되지 않으면 자동적으로 default접근 제어를 가지게됨.
     * **같은 패키지의 클래스 접근가능**
     * **다른 패키지의 클래스 접근 불가능**
   * protected
     * **protected멤버를 선언한 클래스 접근 가능**
     * **같은 패키지의 클래스 접근가능**
     * **이 멤버를 선언한 클래스를 상속받은 자식 클래스 접근 가능**

2. Input클래스, Output클래스 생성

   ```java
   public class Main{
       public static void main(String[] args){
           String input = Input.inputs();
           Output.outputs(inputs);
       }
   }
   
   public class Input{
       static public String inputs(){
           Scanner scaneer = new Scanner(System.in);
           return scanner.nextLine();
       }
   }
   
   public class Output{
       static public void outputs(String result){
           System.out.println("result>>> "+result);
       }
   }
   ```

   * input클래스와 output클래스를 나눠줌으로써 main클래스에서 행하는 일을 분리해준다(**단일책임 원칙**)

   * 정적메소드(static)을 사용함으로써 생성자로 인스턴스 객체를 통해 메소드를 사용하지 않고 ```클래스명.정적메소드``` 로 메소드를 사용할수 있다.

   * 정적메소드를 바로 사용할수 있는 이유는 static을 함으로써 클래스가 메모리에 올라갈 때 정적 메소드가 자동으로 메모리주소가 할당됨으로 항상 같은 메모리주소에서 메소드를 사용하게되기 떄문에 객체를 생성해주지 않아도 된다.

   * ex

     ```java
     class Test {
     Test () {}
     static void m1 () {}
     void m2 () {}
     }
     
     Test.m1() // O
     Test.m2() // X
     
     Test test = new Test()
     
     test.m1() // X
     test.m2() // O
     ```

3. 예외처리

   * try,catch

     * ```java
       try{
           //에러가 발생할 수 있는 코드
       }catch(Exception e){
           //에러시 수행
           //Exception클래스는 예외의 루트 클래스
       }finally{
           //무조건 수행
       }
       ```

       

   * 빈도수 높은 예외

     - NullPointException
       - 객체 참조가 없는 상태, 즉 null값을 갖는 참조변수로 객체 접근 연산자인 토드(.)을 사용했을때 발생
       - 객체가 없는 상태에서 객체를 사용하려고 한것
     - ArrayIndexOutOfBoundsException
       * 배열에서 인덱스 범위를 초과했을떄 발생
     - NumberFormatException
       * 문자열로 되어있는 데이터를 숫자타입으로 변강하는 경우.
       * 숫자타입으로 변경할 수 없는 문자를 치환시키려고 하면 발생하는 에러
     - ClassCastExcetion
       * 타입 변환은 상위 클래스와 하위클래스간에 발생, 
       * 구현 클래스와 인터페이스간에도 발생이런 관계가 아닌 클래스는 다른 클래스로 타입을 변환할수 없다
       * 위 규칙을 무시하고 억지로 타입을 변환시킬경우 발생하는 에러

     

-----------------------------------


