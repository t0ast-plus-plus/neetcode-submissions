class MedianFinder {
    final PriorityQueue<Integer> high;
    final PriorityQueue<Integer> low;

    public MedianFinder() {
        // top half of values sorted in default ascending order
        high = new PriorityQueue<>();
        // bottom half of values sorted in descending (reverse default) order
        low = new PriorityQueue<>(Collections.reverseOrder());
    }
    
    public void addNum(final int num) {
        // add to high half if greater than its lowest value (head), otherwise add to the low half
        if(!high.isEmpty() && high.peek() < num) {
            high.add(num);
        } else {
            low.add(num);
        }
        
        // rebalance the sizes of the halves as needed to keep numbers distributed evenly
        if(Math.abs(high.size() - low.size()) > 1) {
            if(high.size() > low.size()) {
                low.add(high.poll());
            } else {
                high.add(low.poll());
            }
        }
    }
    
    public double findMedian() {
        // equal size = even quantity of elements, so median = average of middle two
        if(high.size() == low.size()) {
            return ((double) high.peek() + (double) low.peek()) / 2.0;
        } 
        
        // otherwise, the half with the most elements will hold the median at its head
        if (high.size() > low.size()) {
            return (double) high.peek();
        } else {
            return (double) low.peek();
        }
    }
}
