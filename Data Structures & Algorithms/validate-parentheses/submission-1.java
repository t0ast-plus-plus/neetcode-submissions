class Solution {
    public boolean isValid(String s) {
        final Stack<Character> open = new Stack<>();

        for (char c : s.toCharArray()) {
            switch (c) {
                case '(':
                    open.push('(');
                    break;
                case '[':
                    open.push('[');
                    break;
                case '{':
                    open.push('{');
                    break;
                case ')':
                    if (!open.isEmpty() && open.peek() == '(') {
                        open.pop();
                    } else {
                        return false;
                    }
                    break;
                case ']':
                    if (!open.isEmpty() && open.peek() == '[') {
                        open.pop();
                    } else {
                        return false;
                    }
                    break;
                case '}':
                    if (!open.isEmpty() && open.peek() == '{') {
                        open.pop();
                    } else {
                        return false;
                    }
                    break;
            }
        }

        return open.isEmpty();
    }
}
