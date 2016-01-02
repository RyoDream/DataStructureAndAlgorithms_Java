package Chapter4;

public class BracketChecker {

    private String input;

    public BracketChecker(String in) {
        input = in;
    }

    public void check() {
        int stackSize = input.length();
        CharStack charStack = new CharStack(stackSize);

        for(int i=0;i<stackSize;i++) {
            char target = input.charAt(i);
            switch (target) {
                case '(':
                case '[':
                case '{':
                    charStack.push(target);
                    break;

                case '}':
                case ']':
                case ')':
                    if(!charStack.isEmpty()) {
                        char top = charStack.pop();
                        if(
                                (target == '}' && top != '{') ||
                                (target == ']' && top != '[') ||
                                (target == ')' && top != '('))
                            System.out.println("Error: " + target + " at " + i);
                    }
                    else
                        System.out.println("Error: " + target + " at " + i);
                    break;
                default:
                    break;
            }
        }

        if(!charStack.isEmpty())
            System.out.println("Error: missing right delimiter");
        else
            System.out.println("Succeed!");
    }

    public static void main(String[] args) {

        String input = "a(b[c][d]{e(d]})";

        BracketChecker checker = new BracketChecker(input);
        checker.check();


    }
}
