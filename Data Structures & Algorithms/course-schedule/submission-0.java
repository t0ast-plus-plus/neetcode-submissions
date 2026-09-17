class Solution {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        // build map of prereqs for each course
        final Map<Integer, List<Integer>> prereqMap = new HashMap<>();
        for(int i = 0; i < numCourses; i++) {
            prereqMap.put(i, new ArrayList<>());
        }
        for(int[] prereq : prerequisites) {
            prereqMap.get(prereq[0]).add(prereq[1]);
        }

        // check prerequisites for each course
        for(int course = 0; course < numCourses; course++) {
            if(!checkPrereqs(course, prereqMap, new HashSet<>())) {
                return false;
            }
        }

        return true;
    }

    private boolean checkPrereqs(final int course, final Map<Integer, List<Integer>> prereqMap, final Set<Integer> checked) {
        // encountering a prereq that is already part of the sequence being checked = circular dependency
        if(checked.contains(course)) {
            return false;
        }

        final List<Integer> prereqs = prereqMap.get(course);

        // no prereqs = course can be taken
        if(prereqs.isEmpty()) {
            return true;
        }

        // mark this course as being checked and recursively check its prereqs
        checked.add(course);
        for(final int prereq : prereqs) {
            if(!checkPrereqs(prereq, prereqMap, checked)) {
                return false;
            }
        }
        
        // remove from 'being checked' list, as we're now done with examining this course
        checked.remove(course);

        // additionally, if we reached this point, we now know its prereqs are valid
        // so we can clear out its prereq list to short-circuit repeated checks into success
        prereqMap.put(course, Collections.emptyList());

        return true;
    }
    
}
