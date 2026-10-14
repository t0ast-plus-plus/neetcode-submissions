class Solution {
    private final Set<String> maths;

    public Solution() {
        maths = new HashSet<>();
        maths.add("+");
        maths.add("-");
        maths.add("*");
        maths.add("/");
    }

    public int evalRPN(String[] tokens) {
        if(tokens.length == 1) {
            return Integer.valueOf(tokens[0]);
        }

        Stack<Integer> vals = new Stack<>();
        for(int i = 0; i < tokens.length; i++) {
            if(!maths.contains(tokens[i])) {
                vals.push(Integer.valueOf(tokens[i]));
            } else {
                int r = vals.pop();
                int l = vals.pop();
                vals.push(doMath(l, r, tokens[i]));
            }
        }

        return vals.pop();
    }

    private Integer doMath(int a, int b, String op) {
        switch(op) {
            case "+":
                return a+b;
            case "-":
                return a-b;
            case "*":
                return a*b;
            case "/":
                return a/b;
            default:
                // not possible in this problem scenario
                return null;
        }
    }
}
