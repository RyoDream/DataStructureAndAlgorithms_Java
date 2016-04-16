package New_Chapter3;

import java.util.Scanner;
import java.util.Stack;

public class InFixToPostFix {

    static double evalPostFix() {
        Stack<Double> s = new Stack<Double>();
        String token;

        Double a, b, result = 0.0;
        boolean isNumber;

        Scanner sc = new Scanner(System.in);
        token = sc.next();

        while (token.charAt(0) != '=') {
            try {
                isNumber = true;
                result = Double.parseDouble(token);
            } catch (Exception e) {
                isNumber = false;
            }

            if (isNumber)
                s.push(result);
            else {
                a = s.pop();
                b = s.pop();

                switch (token.charAt(0)) {
                    case '+':
                        s.push(a + b);
                        break;
                    case '-':
                        s.push(a - b);
                        break;
                    case '*':
                        s.push(a * b);
                        break;
                    case '/':
                        s.push(a / b);
                        break;
                    case '^':
                        s.push(Math.exp(a * Math.log(b)));
                        break;
                }
            }

            token = sc.next();
        }

        return s.peek();
    }

    static double evalPostFix2(String token) {
        Stack<Double> s = new Stack<Double>();

        Double a, b, result = 0.0;
        boolean isNumber;


        for (char c : token.toCharArray()) {
            try {
                isNumber = true;
                result = Double.parseDouble(c + "");
            } catch (Exception e) {
                isNumber = false;
            }

            if (isNumber)
                s.push(result);
            else {
                a = s.pop();
                b = s.pop();

                switch (c) {
                    case '+':
                        s.push(a + b);
                        break;
                    case '-':
                        s.push(a - b);
                        break;
                    case '*':
                        s.push(a * b);
                        break;
                    case '/':
                        s.push(a / b);
                        break;
                    case '^':
                        s.push(Math.exp(a * Math.log(b)));
                        break;
                }
            }
        }

        return s.peek();
    }

    public static void InFixToPostFix(String expression) {
        Stack<Character> s = new Stack<Character>();
        Character token;
        int i = 0;

        while ((token = expression.charAt(i++)) != '=') {
            if (token >= 'a' && token <= 'z')
                System.out.print(token + " ");
            else {
                switch (token) {
                    case ')':
                        while (!s.empty() && s.peek() != '(') {
                            System.out.print(s.pop() + " ");
                        }
                        s.pop();
                        break;
                    case '(':
                        s.push(token);
                        break;
                    case '^':
                        while (!s.empty() && s.peek() == '^' || s.peek() == '(') {
                            System.out.print(s.pop() + " ");
                        }
                        s.push(token);
                        break;
                    case '*':
                    case '/':
                        while (!s.empty() && s.peek() != '+' && s.peek() != '-' && s.peek() != '(') {
                            System.out.print(s.pop() + " ");
                        }
                        s.push(token);
                        break;
                    case '+':
                    case '-':
                        while (!s.empty() && s.peek() != '(') {
                            System.out.print(s.pop() + " ");
                        }
                        s.push(token);
                        break;
                }
            }
        }
        while (!s.empty()) {
            System.out.print(s.pop());
        }
        System.out.println();
    }


    public static void main(String[] args) {
        InFixToPostFix("a + b * ( c - d ) ^ e ^ f =");
        //System.out.println(evalPostFix2("32+5*98+09++-"));
    }
}
