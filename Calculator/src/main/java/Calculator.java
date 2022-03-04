import java.util.Scanner;
import java.util.Stack;

public class Calculator {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String expression = sc.nextLine();
        System.out.println(preparingExpression(expression));
        System.out.println(expressionToRPN(preparingExpression(expression)));
        System.out.println(answer(expressionToRPN(preparingExpression(expression))));
    }


    private static String preparingExpression(String expression){
        String preparedExpression = new String();
        for (int i = 0; i < expression.length(); i++) {
            char symbol = expression.charAt(i);
            if (symbol == '-'){
                if (i == 0) preparedExpression += '0';
                else if ( expression.charAt(i-1) == '(') preparedExpression += '0';
            }
            preparedExpression += symbol;
        }
        return preparedExpression;
    }

    private static String expressionToRPN(String expression){
        String current = "";
        Stack<Character> stack = new Stack<>();
        int prior;
        for (int i = 0; i < expression.length(); i++) {
            prior = priority(expression.charAt(i));

            if (prior == 0) current += expression.charAt(i);
            if (prior == 2) stack.push(expression.charAt(i));

            if (prior > 2) {
                current += ' ';
                while (!stack.empty()) {
                    if (priority(stack.peek()) >= prior) current += stack.pop();
                    else break;
                }
                stack.push(expression.charAt(i));
            }
            if (prior == 1) {
                current += ' ';
                while (priority(stack.peek()) != 2) current += stack.pop();
                stack.pop();
            }
        }
            while (!stack.empty()) current += stack.pop();
        return current;
    }


    private static double answer (String currentExpression){
        String operand = new String();
        Stack<Double> stack = new Stack<>();
        for (int i = 0; i < currentExpression.length() ; i++) {
            if (currentExpression.charAt(i) == ' ') continue;
            if (priority(currentExpression.charAt(i)) == 0) {
                while (currentExpression.charAt(i) != ' ' && priority(currentExpression.charAt(i)) == 0){
                    operand+=currentExpression.charAt(i++);
                if (i == currentExpression.length()) break;}
            stack.push(Double.parseDouble(operand));
            operand = new String();
            }

            if (priority(currentExpression.charAt(i)) >2) {
                double a = stack.pop();
                double b = stack.pop();
                if (currentExpression.charAt(i) == '+') stack.push(b+a);
                if (currentExpression.charAt(i) == '-') stack.push(b-a);
                if (currentExpression.charAt(i) == '*') stack.push(b*a);
                if (currentExpression.charAt(i) == '/') stack.push(b/a);
            }
        }

        return stack.pop();
    }

    private static int priority(char token){
        if (token == '*' || token == '/') return 4;
        else if (token == '+' || token == '-') return 3;
        else if (token == '(') return 2;
        else if (token == ')') return 1;
        else return 0;
    }
}
