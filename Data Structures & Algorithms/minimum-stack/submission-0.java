class MinStack {
    private Stack<Integer> valStack; // value storage
    private Stack<Integer> minStack; // minimum storage

    public MinStack() {
        valStack = new Stack<>();
        minStack = new Stack<>();
    }

    public void push(int val) {
        // always add to value storage
        valStack.push(val);
        // only add to min storage if this new value establishes a new minimum
        if (minStack.isEmpty() || val <= minStack.peek()) {
            minStack.push(val);
        }
    }

    public void pop() {
        if (valStack.isEmpty()) {
            return;
        }

        // always pop from value storage
        int top = valStack.pop();
        // only pop from min storage if the popped value was a current minimum
        if (top == minStack.peek()) {
            minStack.pop();
        }
    }

    public int top() {
        return valStack.peek();
    }

    public int getMin() {
        return minStack.peek();
    }
}
