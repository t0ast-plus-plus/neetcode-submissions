class Solution {
    public int[] findOrder(int numCourses, int[][] pIn) {
        // initialize a list of lists to represent the prerequisites:
        // index = class number
        // value (list) = class numbers that have this index as a prerequisite
        // example: 1 has prereqs 2 and 3, so list.get(1) = empty, list.get(2) = 1, list.get(3) = 1
        final List<List<Integer>> prereqs = new ArrayList<>();
        for(int i = 0; i < numCourses; i++) {
            prereqs.add(new ArrayList<>());
        }

        // spin through the provided array of prerequisites and for each...
        // 1) track indegree (inbound edges = number of prerequisites) for each class
        // 2) populate the prerequisite list as described above
        final int[] indegree = new int[numCourses];
        for(final int[] p : pIn) {
            indegree[p[0]]++;
            prereqs.get(p[1]).add(p[0]);
        }

        // start "taking" classes starting with indegree 0 (no prereqs), firing off a DFS from each
        final List<Integer> taken = new ArrayList<>();
        for(int i = 0; i < numCourses; i++) {
            if(indegree[i] == 0) {
                dfs(i, prereqs, indegree, taken);
            }
        }

        // dfs() above will add each takeable class to "taken", so confirm it matches
        // the required number of courses that was provided to this method
        if(taken.size() != numCourses) {
            return new int[0];
        }

        // build return array
        final int[] res = new int[taken.size()];
        for (int i = 0; i < taken.size(); i++) {
            res[i] = taken.get(i);
        }
        return res;
    }

    private void dfs(final int classNumber, final List<List<Integer>> prereqs, final int[] indegree, final List<Integer> taken) {
        // update indegree of this class to -1 to mark it as "taken", then add to output
        indegree[classNumber] = -1;
        taken.add(classNumber);
        // for each other class that has this one as a prerequisite...
        for(Integer i : prereqs.get(classNumber)) {
            // decrement its indegree, and if it hits zero (no more prerequisites)
            // then recurse into this class number to "take" it next
            if(--indegree[i] == 0) {
                dfs(i, prereqs, indegree, taken);
            }
        }
    }
}
