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
    public int minMeetingRooms(List<Interval> intervals) {
        // sort by start time
        Collections.sort(intervals, (a,b) -> Integer.compare(a.start, b.start));
        
        List<List<Interval>> roomSchedules = new ArrayList<>();
        
        // for each interval, loop through the collected room schedules to look for openings
        // append to the end of a room schedule if no conflict, otherwise initialize a new room schedule
        for(final Interval i : intervals) {
            boolean newRoomNeeded = true;
            for(List<Interval> roomSchedule : roomSchedules) {
                Interval lastInRoom = roomSchedule.getLast();
                if (lastInRoom.end <= i.start) {
                    roomSchedule.add(i);
                    newRoomNeeded = false;
                    break;
                }
            }

            if(newRoomNeeded) {
                List<Interval> roomSchedule = new ArrayList<>();
                roomSchedule.add(i);
                roomSchedules.add(roomSchedule);
            }
        }

        // count the resulting number of room schedule lists
        return roomSchedules.size();
    }
}
