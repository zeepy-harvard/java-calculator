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
1. main에서 Scanner를 통해 수식을 입력받습니다. 입력값이 -1이라면 반복문을 나가 종료합니다.
```java
while (true) {
    Scanner scanner = new Scanner(System.in);
    String input = scanner.nextLine();

    if (input.equals("-1")) {
         break;
    }
    ...
}
```
2. Operation 객체를 생성하여 runOperation 메소드에 매개변수로 수식을 전달하고, 그 결과값을 result 변수에 받아 출력합니다.
```java
operation = new Operation();
result = operation.runOperation(input);
System.out.println("계산 결과 :" + result);
```
3. runOperation은 checkValidation 메소드를 호출해 수식의 유효성을 검사하고, getResult 메소드를 통해 최종 결과값을 받습니다. 최종 결과값은 main에게 전달됩니다.
```java
try {
    checkValidation(values);
    result = Integer.parseInt(values[0]);
    result = getResult(values, result, operator);
} catch (Exception e) {
    System.out.println(e.getMessage());
}

```
4. checkValidation 메소드는 짝수번째 항들이 상수의 패턴을 만족하는지 검사합니다. 아닐 시, 예외를 발생시킵니다.
```java
for (int i = 0; i < values.length; i += 2) {
    if (!values[i].matches("^[0-9]*$")) {
        throw new IllegalArgumentException("invalid term.");
    }
}
```
5. getResult 메소드는 수식을 담은 배열 values에 차례대로 접근합니다. 피연산자일 경우 operation 메소드를 호출하여 중간 결과값을 갱신하고, 연산자일 경우 operator 변수에 해당 연산자를 세팅합니다.
```java
private int getResult(String[] values, int result, String operator) {
    for (int i = 1; i < values.length; i++) {
        if (i % 2 == 0) {
            result = operation(Integer.parseInt(values[i]), operator, result);
        } else {
            operator = values[i];
        }
    }
    return result;
}

private int operation(int operand, String operator, int result) throws IllegalArgumentException {
    switch (operator) {
        case "+":
            result += operand;
            break;
            ...
    }
    return result;
}
```
