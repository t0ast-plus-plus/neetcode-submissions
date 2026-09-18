class Solution {
    public int climbStairs(int n) {
        int[] waysToClimb = new int[n];
        Arrays.fill(waysToClimb, -1);
        return climb(0, waysToClimb);
    }

    public int climb(int n, int[] waysToClimb) {
        if(n == waysToClimb.length) {
            // top stair has only one answer
            return 1;
        }
        if(n > waysToClimb.length) {
            // beyond the top of the stairs, invalid
            return 0;
        }

        if(waysToClimb[n] != -1) {
            // solution known for given stair index
            return waysToClimb[n];
        }

        // recursively calculate on the two possible options: climb one or climb two, memoize, and return
        waysToClimb[n] = climb(n+1, waysToClimb) + climb(n+2, waysToClimb);
        return waysToClimb[n];
    }
}
