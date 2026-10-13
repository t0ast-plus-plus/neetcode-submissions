class Solution {
    public int singleNumber(int[] nums) {
        int result = 0;
        for(int i : nums) {
            result ^= i; //Bitwise XOR to add when new and then remove on duplicate
        }
        return result;
    }
}
