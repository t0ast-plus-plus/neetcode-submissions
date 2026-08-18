class Solution {
    public int lengthOfLongestSubstring(String s) {
        if(s.length() <= 1) {
            return s.length();
        }

        Set<Character> chars = new HashSet<>();
        chars.add(s.charAt(0));

        int left = 0;
        int right = 1;
        int maxChars = 1;
        while(right < s.length()) {
            // check char at the right pointer index
            char c = s.charAt(right);
            if(!chars.contains(c)) {
                // not a duplicate character, so add it to the char set, update the max, and bump the right index
                chars.add(c);
                maxChars = Math.max(maxChars, chars.size());
                right++;
            } else {
                // duplicate character, so "shrink" the left pointer (also removing its character from the set)
                chars.remove(s.charAt(left));
                left++;
            }
        }

        return maxChars;
    }
}
