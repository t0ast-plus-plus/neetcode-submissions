class Solution {
    public int[][] insert(int[][] startingIntervals, int[] newInterval) {
        if(startingIntervals.length == 0) {
            final int[][] solution = new int[1][2];
            solution[0] = newInterval;
            return solution;
        }

        final List<List<Integer>> intervals = new ArrayList<>(startingIntervals.length+1);
        boolean inserted = false;
        
        for(int i = 0; i < startingIntervals.length; i++) {
            int curStart = startingIntervals[i][0];
            int curEnd = startingIntervals[i][1];
            if(inserted) {
                intervals.add(List.of(curStart, curEnd));
                continue;
            }

            // merge into current interval
            if(newInterval[0] <= curEnd && newInterval[1] >= curStart) {
                int mergedStart = Math.min(curStart, newInterval[0]);
                int mergedEnd = Math.max(curEnd, newInterval[1]);
                // also merge into following intervals if appropriate
                for(int j = i+1; j < startingIntervals.length; j++) {
                    int followingStart = startingIntervals[j][0];
                    int followingEnd = startingIntervals[j][1];
                    if(followingStart <= mergedEnd) {
                        mergedEnd = Math.max(followingEnd, mergedEnd);
                        i++;
                    } else {
                        break;
                    }
                }

                intervals.add(List.of(mergedStart, mergedEnd));
                inserted = true;
            } else {
                // check to see if new interval is suitable to insert before this one
                if (newInterval[1] < curStart
                    && (i == 0 || newInterval[0] > startingIntervals[i-1][1])) {
                        intervals.add(List.of(newInterval[0], newInterval[1]));
                        inserted = true;
                    }

                intervals.add(List.of(startingIntervals[i][0], startingIntervals[i][1]));

                // check to see if new interval is suitable to insert after this one
                if (newInterval[0] > curEnd
                    && (i == startingIntervals.length-1 || newInterval[1] < startingIntervals[i+1][0])) {
                    intervals.add(List.of(newInterval[0], newInterval[1]));
                    inserted = true;
                    }
            }
        }

        // translate to return format
        final int[][] solution = new int[intervals.size()][2];
        for(int i = 0; i < intervals.size(); i++) {
            solution[i][0] = intervals.get(i).get(0);
            solution[i][1] = intervals.get(i).get(1);
        }
        return solution;
    }
}
