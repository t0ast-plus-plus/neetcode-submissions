class Solution {
    public boolean isValid(String s) {
        final Stack<Character> open = new Stack<>();
        Map<Character, Character> openCharByCloseChar = new HashMap<>();
        openCharByCloseChar.put(')', '(');
        openCharByCloseChar.put(']', '[');
        openCharByCloseChar.put('}', '{');

        for (char c : s.toCharArray()) {
            final Character expectedLastOpen = openCharByCloseChar.get(c);
            if (expectedLastOpen == null) {
                open.push(c);
            } else if (!open.isEmpty() && open.peek() == expectedLastOpen) {
                open.pop();
            } else {
                return false;
            }
        }

        return open.isEmpty();
    }
}
