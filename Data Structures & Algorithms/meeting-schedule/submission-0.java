/**
 * Definition of Interval:
 * public class Interval {
 *     public int start, end;
 *     public Interval(int start, int end) {
 *         this.start = start;
 *         this.end = end;
 *     }
 * }
 */

class Solution {
    public boolean canAttendMeetings(List<Interval> intervals) {
        // sort by start
        intervals.sort(new IntervalComparator());

        // compare each interval to the next (in order)
        // the first must not end after the start of the second
        for(int i = 0; i < intervals.size()-1; i++) {
            if(intervals.get(i).end > intervals.get(i+1).start) {
                return false;
            }
        }

        return true;
    }

    class IntervalComparator implements Comparator<Interval> {
        public int compare(Interval i1, Interval i2) {
            return i1.start - i2.start;
        }
    }
}
