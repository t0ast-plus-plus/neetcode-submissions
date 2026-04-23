class DynamicArray {
    int[] a;
    int nextVacant;

    public DynamicArray(int capacity) {
        a = new int[capacity];
        nextVacant = 0;
    }

    public int get(int i) {
        return a[i];
    }

    public void set(int i, int n) {
        a[i] = n;
    }

    public void pushback(int n) {
        if(nextVacant >= a.length)
        {
            resize();
        }
        a[nextVacant] = n;
        nextVacant++;
    }

    public int popback() {
        nextVacant--;
        return a[nextVacant];
    }

    public void resize() {
        a = Arrays.copyOf(a, a.length * 2);
    }

    public int getSize() {
        return nextVacant;
    }

    public int getCapacity() {
        return a.length;
    }
}
