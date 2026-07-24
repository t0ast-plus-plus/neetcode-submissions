class Solution {
    public int characterReplacement(final String s, final int k) {
        int result = 0;
        // collect unique chars in string
        final Set<Character> chars = new HashSet<>();
        for (Character c : s.toCharArray()) {
            chars.add(c);
        }

        // per unique char...
        for (Character c : chars) {
            // use sliding window to find widest window using k replacements
            // slide right pointer across the word form 0
            // if right pointer encounters a matching character, increment count
            // if window size minus count of matching characters is > k,
            // then slide left pointer and reduce count until that value reduces to <= k
            // store the maximum window size as we go
            int left = 0;
            int count = 0;
            for (int right = 0; right < s.length(); right++) {
                if (s.charAt(right) == c) {
                    count++;
                }

                while ((right - left + 1) - count > k) {
                    if (s.charAt(left) == c) {
                        count--;
                    }
                    left++;
                }

                result = Math.max(result, right - left + 1);
            }
        }

        return result;
    }
}