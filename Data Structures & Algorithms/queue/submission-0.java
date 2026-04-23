class Deque {
    final List<Integer> deq;

    public Deque() {
        this.deq = new ArrayList<>();
    }

    public boolean isEmpty() {
        return deq.isEmpty();
    }

    public void append(int value) {
       deq.add(value);
    }

    public void appendleft(int value) {
        deq.add(0, value);
    }

    public int pop() {
        if(deq.isEmpty()) { return -1;}
        return deq.removeLast();
    }

    public int popleft() {
        if(deq.isEmpty()) { return -1;}
        return deq.removeFirst();
    }
}
