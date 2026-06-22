class Solution {
    public int[][] merge(int[][] intervals) {
        // sort by start time
        Arrays.sort(intervals, (a, b) -> Integer.compare(a[0],b[0]));
        final List<List<Integer>> merged = new ArrayList<>();

        int cur = 0;
        while (cur < intervals.length) {
            int curStart = intervals[cur][0];
            int curEnd = intervals[cur][1];
            
            // spin through subsequent intervals looking for merge candidates
            int next = cur+1;
            while(next < intervals.length && intervals[next][0] <= curEnd) {
                // update the stored 'end' value to the highest/latest of the two
                curEnd = Math.max(curEnd, intervals[next][1]);
                next++; // move on to next interval
            }

            // add to merged list
            merged.add(List.of(curStart, curEnd));
            cur = next;
        }

        // assemble solution array
        final int[][] solution = new int[merged.size()][2];
        for(int i = 0; i < merged.size(); i++) {
            solution[i][0] = merged.get(i).get(0);
            solution[i][1] = merged.get(i).get(1);
        }

        return solution;
    }
}
