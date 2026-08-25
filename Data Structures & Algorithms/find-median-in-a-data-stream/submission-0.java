class MedianFinder {
    final PriorityQueue<Integer> high;
    final PriorityQueue<Integer> low;

    public MedianFinder() {
        // top half of values sorted in default ascending order
        high = new PriorityQueue<>();
        // bottom half of values sorted in descending (reverse default) order
        low = new PriorityQueue<>(Collections.reverseOrder()); // descending order (reversed default)
    }
    
    public void addNum(final int num) {
        // add to high half if greater than its lowest value (head), otherwise add to the low half
        if(!high.isEmpty() && high.peek() < num) {
            high.add(num);
        } else {
            low.add(num);
        }

        // System.out.println("\nADDED: " + num);
        // System.out.println("LOW: " + low);
        // System.out.println("HIGH: " + high);
    }
    
    public double findMedian() {
        // rebalance the size of the two halves
        rebalance();

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

    private class IntegerDescending implements Comparator<Integer> {
        @Override
        public int compare(Integer a, Integer b) {
            return b - a;
        }
    }

    private void rebalance() {
        // move elements from the larger (by quantity) half into the other half until equal in size +/- 1
        while(Math.abs(high.size() - low.size()) > 1)
        {
            if(high.size() > low.size()) {
                low.add(high.poll());
            } else {
                high.add(low.poll());
            }
        }

        // System.out.println("\nREBALANCED: ");
        // System.out.println("LOW: " + low);
        // System.out.println("HIGH: " + high);
    }
}
