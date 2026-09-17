class Solution {
    public String minWindow(final String s, final String t) {
        // edge cases: empty t, s shorter than or equal to length of t
        if(t.length() == 0 || s.length() < t.length()) {
            return "";
        }

        // build char freq map of target string (t)
        final Map<Character, Integer> targetCharFreq = new HashMap<>();
        for(final char c : t.toCharArray()) {
            Integer count = targetCharFreq.getOrDefault(c, 0);
            targetCharFreq.put(c, count+1);
        }

        // track current char freq as we window through s
        final Map<Character, Integer> currentCharFreq = new HashMap<>();
        // starting index of best substring
        int bestStartIndex = -1;
        // length of best substring
        int bestLength = Integer.MAX_VALUE;
        // tracking number of target character quantities that have been satisfied in the window
        int targetCharsSatisfied = 0;
        // the quantity of different characters within the target string
        int targetCharsNeeded = targetCharFreq.size();
        
        int l = 0;
        for (int r = 0; r < s.length(); r++) {
            // add char @ r into the map of current char frequencies
            char c = s.charAt(r);
            currentCharFreq.put(c, currentCharFreq.getOrDefault(c, 0) + 1);

            // if we have reached the target threshold for this character, increment our "satisfied" counter
            if(targetCharFreq.containsKey(c) && currentCharFreq.get(c).equals(targetCharFreq.get(c))) {
                targetCharsSatisfied++;
            }

            // as long as we have met or exceeded all target character counts...
            while(targetCharsSatisfied == targetCharsNeeded) {
                // update "best" window tracking as improvements are found
                if((r - l + 1) < bestLength) {
                    bestLength = r - l + 1;
                    bestStartIndex = l;
                }

                // shrink the left side until we no longer satisfy a target character count
                char leftChar = s.charAt(l);
                currentCharFreq.put(leftChar, currentCharFreq.get(leftChar) - 1);
                if(targetCharFreq.containsKey(leftChar) && currentCharFreq.get(leftChar) < targetCharFreq.get(leftChar)) {
                    targetCharsSatisfied--;
                }
                l++;
            }
        }

        // return best substring if we have one
        return bestLength == Integer.MAX_VALUE ? "" : s.substring(bestStartIndex, bestStartIndex + bestLength);
    }
}
