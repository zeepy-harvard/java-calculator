## 계산기를 만들어 봅시다

### 목적

- 단위 테스트를 익혀봅시다.

### 단위 테스트는 무엇인가요?

- 단위 테스트는 실제 비즈니스 로직이 담겨져 있는 코드를 구현한 후 JUnit과 같은 도구를 활용해 **작은 단위를 테스트하는 것**입니다.
- Input과 Output이 명확한 클래스 메소드에 대한 단위 테스트를 연습해봅시다.

### 요구사항

- 사용자가 입력한 문자열 값에 따라 사칙연산을 수행할 수 있는 계산기를 구현해야 합니다.
- 문자열 계산기는 사칙연산의 계산 우선순위가 아닌 입력 값에 따라 계산 순서가 결정됩니다. 
즉, 수학에서는 곱셈, 나눗셈이 덧셈, 뺄셈 보다 먼저 계산해야 하지만 이를 무시합니다.
- 예를 들어 `2 + 3 * 4 / 2`와 같은 문자열을 입력할 경우
 `2 + 3 * 4 / 2` 실행 결과인 10을 출력해야 합니다. 
우리가 알고있는 사칙연산의 계산 우선순위로 인한 `3 * 4 / 2 + 2` 의 결과인 8은 오답입니다.

### 힌트

- 문자열을 입력받고 빈 공백 문자열을 기준으로 문자들을 분리해야 하겠죠?

    ```java
    String value = scanner.nextLine();
    String[] values = value.split(" ");
    ```

- 문자열을 정수로 변경하는 방법으로는 `Integer` 클래스의 `parseInt()` 메소드가 있습니다.

    ```java
    int number = Integer.parseInt("문자열");
    ```

### 풀이
1. StringCalculator 클래스가 가지고 있는 main 메소드에서 입력을 반복해서 받는 while문이 동작합니다.
2. 사용자가 수식을 입력하면 Operation 객체를 생성하여 String배열 values에 저장하고, -1 입력 시 종료합니다.
```java
while (true){
            Scanner scanner = new Scanner(System.in);
            String input = scanner.nextLine();
            if(input.equals("-1")){
                break;
            }
            operation = new Operation();
            operation.setValues(input);
            ...
```
```java
public void setValues(String input) {
        values = new String[0];
        values = input.split(" ");
    }
```
3. 배열에 저장한 후 숫자항이 제대로 입력되었는지 검사하고, 일정 패턴에 맞는 항이 아니라면 예외를 발생시킵니다.
```java
public void checkValidation() throws IllegalArgumentException {
        for (int i = 0; i < values.length; i += 2) {
            if (!values[i].matches("^[0-9]*$")) {
                throw new IllegalArgumentException("invalid term.");
            }
        }
    }
```
4. 짝수(0, 2, 4..)인덱스는 상수로, 홀수(1, 3, 5..)인덱스는 연산자로 구분합니다. 
연산자라면 객체의 operator 변수에 해당 연산자를 설정합니다. 이후 나오는 상수항이 해당 연산자가 무엇인지에 따라 알맞게 연산될 것입니다. 첫번재 상수라면 result 변수를 그 수로 세팅시키고, 그 이후로의 상수라면 연산 메소드 operation을 호출합니다.
```java
public void runOperation() {
        for (int i = 0; i < values.length; i++) {
            if (i % 2 == 0) {
                if (isFirst) {
                    result = Integer.parseInt(values[i]);
                    isFirst = false;
                } else {
                    operation(Integer.parseInt(values[i]));
                }
            } else {
                operator = values[i];
            }
        }
    }
```
5. 연산 시 +, -, *, / 이외의 연산자가 operator에 들어있다면 예외를 발생시킵니다.
```java
public void operation(int operand) throws IllegalArgumentException {
        switch (operator) {
            case "+":
                add(operand);
                break;
            ...

            default:
                throw new IllegalArgumentException("Invalid operator input. Only +, -, *, / works.");
        }
    }
```
6. 연산이 끝나면 반복문 끝에서 결과를 출력합니다.
```java
System.out.println(operation.getResult());
```
