class Solution {
    public int[] countBits(int n) {
        final int[] out = new int[n+1];
        for(Integer i = 0; i <= n; i++) {
            out[i] = Integer.bitCount(i);
        }
        return out;
    }
}
