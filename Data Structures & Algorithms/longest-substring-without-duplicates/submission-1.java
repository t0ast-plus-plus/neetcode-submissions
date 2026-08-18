class Solution {
    public int lengthOfLongestSubstring(String s) {
        // edge case handling
        if(s.length() <= 1) {
            return s.length();
        }

        // initialize a character set to detect duplicates, starting with the first character
        Set<Character> chars = new HashSet<>();
        chars.add(s.charAt(0));

        // two pointer algorithm: left = start of substring, right = next character after end of substring
        int left = 0;
        int right = 1;
        int maxChars = 1;
        while(right < s.length()) {
            // look at the next character @ right to see if it is duplicated within the substring
            char c = s.charAt(right);
            if(!chars.contains(c)) {
                // not a duplicate, so add it to the char set, update the max substring length, and increment right
                chars.add(c);
                maxChars = Math.max(maxChars, chars.size());
                right++;
            } else {
                // duplicate, so drop the character @ left then increment left to "shrink" the substring
                chars.remove(s.charAt(left));
                left++;
            }
        }

        return maxChars;
    }
}
