class Solution {
    public int findKthLargest(int[] nums, int k) {
        // O(n log n) solution: sort and get the kth from end
        //Arrays.sort(nums);
        //return nums[nums.length-k];
        
        // O(n log k) solution: feed input into min-heap of size k
        // to keep a rolling store of the top k elements, then pop the head when done
        PriorityQueue<Integer> nq = new PriorityQueue<>(); 

        for(int i : nums) {
            nq.offer(i);
            if(nq.size() > k) {
                nq.poll();
            }
        }

        return nq.poll();
    }
}
