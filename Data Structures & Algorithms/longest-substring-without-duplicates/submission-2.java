class Solution {
    public int lengthOfLongestSubstring(final String s) {
        // edge case handling
        if(s.length() <= 1) {
            return s.length();
        }

        // character set to easily detect duplicates, pre-initialized with the first character
        final Set<Character> chars = new HashSet<>();
        chars.add(s.charAt(0));

        // variable-size sliding window (left = start of substring, right = next char after substring)
        int left = 0;
        int right = 1;
        int maxChars = 1;
        while(right < s.length()) {
            // look at the next character @ right to see if it is duplicated within the substring
            char c = s.charAt(right);
            if(!chars.contains(c)) {
                // not a duplicate: add to char set, update the max, and increment right
                chars.add(c);
                maxChars = Math.max(maxChars, chars.size());
                right++;
            } else {
                // duplicate: drop the character @ left, then increment left
                chars.remove(s.charAt(left));
                left++;
            }
        }

        return maxChars;
    }
}
