class Solution {
    public int characterReplacement(final String s, final int k) {
        int result = 0;
        // collect unique chars in string
        final Set<Character> chars = new HashSet<>();
        for (Character c : s.toCharArray()) {
            chars.add(c);
        }

        // per unique char, use sliding window to find longest substring of matches w/ k replacments
        for (Character c : chars) {
            int left = 0; // left window pointer
            int count = 0; // number of matching characters in the window
            // slide right window pointer across the word
            for (int right = 0; right < s.length(); right++) {
                // increment count if we find a matching character
                if (s.charAt(right) == c) {
                    count++;
                }

                // shrink window if window size - matching character count > k
                while ((right - left + 1) - count > k) {
                    // reduce count if we drop a matching character
                    if (s.charAt(left) == c) {
                        count--;
                    }
                    left++;
                }

                // keep track of the largest window size we find along the way
                result = Math.max(result, right - left + 1);
            }
        }

        return result;
    }
}