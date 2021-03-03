public class Output {
    static public void outputs(int result){
        System.out.println("결과>>>"+result);
    }
    static public void errorOutput(Exception e){
        System.out.println("에러발생!!>>>"+e.getMessage());
        e.printStackTrace();
    }
}
