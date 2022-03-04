public class StackOwerFlowError {
    public static void main(String[] args) {
        call();
    }

    private static int call(){
        return call();
    }
}
