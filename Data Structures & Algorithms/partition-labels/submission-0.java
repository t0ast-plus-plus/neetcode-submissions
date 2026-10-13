class Solution {
    public List<Integer> partitionLabels(String s) {
        // locate last occurrence of each character
        Map<Character, Integer> lastLoc = new HashMap<>();
        for(int i = 0; i < s.length(); i++) {
            lastLoc.put(s.charAt(i), i);
        }

        final List<Integer> result = new ArrayList<>();
        int size = 0, end = 0;
        for(int i = 0; i < s.length(); i++) {
            size++;
            // keep track of the "furthest out" last index for each char we encounter
            end = Math.max(end, lastLoc.get(s.charAt(i)));

            if(i == end) {
                // hitting the "furthest out" last index indicates a partition end
                // so store it into our solution set and begin a new partition
                result.add(size);
                size = 0;
            }
        }

        return result;
    }
}
