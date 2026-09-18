class Solution {
    public int eraseOverlapIntervals(final int[][] intervals) {
        // Greedy approach, sorting by end puts the earliest-ending meetings up front
        // which prioritizes those which take the shortest amount of time / leave most room for me
        // so we can process in order and cut those which overlap
        Arrays.sort(intervals, (a, b) -> Integer.compare(a[1], b[1]));
        int result = 0;
        int prevEnd = intervals[0][1];
        for(int i = 1; i < intervals.length; i++) {
            if(intervals[i][0] < prevEnd) {
                // cut this interval
                result++;
            } else {
                // keep this interval and update the current end time
                prevEnd = intervals[i][1];
            }
        }

        return result;
    }
}
