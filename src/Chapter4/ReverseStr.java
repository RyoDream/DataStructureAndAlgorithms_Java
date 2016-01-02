package Chapter4;

public class ReverseStr {
    private String input;

    public ReverseStr(String in) {
        input = in;
    }

    public String doRev() {
        int stackSize = input.length();
        CharStack charStack = new CharStack(stackSize);

        for(int i=0;i<stackSize;i++) {
            charStack.push(input.charAt(i));
        }

        String output = "";

        while(!charStack.isEmpty()) {
            output += charStack.pop();
        }

        return output;
    }

    public static void main(String[] args) {
        String input, output;

        System.out.print("Enter a string: ");
        System.out.flush();
        input = "abcdefg123";

        if(input.equals("")) return;

        ReverseStr reverseStr = new ReverseStr(input);
        output = reverseStr.doRev();
        System.out.println("Reversed: "+output);
    }

}
