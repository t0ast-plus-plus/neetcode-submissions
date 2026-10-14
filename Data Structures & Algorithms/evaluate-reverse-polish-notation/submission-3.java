class Solution {
    private final static Set<String> maths = Set.of("+","-","*","/");

    public int evalRPN(String[] tokens) {
        if(tokens.length == 1) {
            return Integer.valueOf(tokens[0]);
        }

        final Stack<Integer> vals = new Stack<>();
        for(int i = 0; i < tokens.length; i++) {
            if(!maths.contains(tokens[i])) {
                vals.push(Integer.valueOf(tokens[i]));
            } else {
                vals.push(doMath(vals.pop(), vals.pop(), tokens[i]));
            }
        }

        return vals.pop();
    }

    private Integer doMath(int right, int left, String op) {
        switch(op) {
            case "+":
                return left+right;
            case "-":
                return left-right;
            case "*":
                return left*right;
            case "/":
                return left/right;
            default:
                // not possible in this problem scenario
                return null;
        }
    }
}
