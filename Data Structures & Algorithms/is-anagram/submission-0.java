class Solution {
    public boolean isAnagram(String s, String t) {
        // anagrams must be of equal length
        if (s.length() != t.length()) {
            return false;
        }

        // build out character frequency maps for each string
        // then check for equality between the maps
        final Map<Character, Integer> sCharFreq = new HashMap<>();
        final Map<Character, Integer> tCharFreq = new HashMap<>();

        for(int i = 0; i < s.length(); i++) {
            addChar(s.charAt(i), sCharFreq);
            addChar(t.charAt(i), tCharFreq);
        }

        return sCharFreq.equals(tCharFreq);
    }

    private void addChar(char c, Map<Character, Integer> freqMap) {
        final Integer freq = freqMap.get(c);
        if(freq == null) {
            freqMap.put(c, 1);
        }
        else {
            freqMap.put(c, freq+1);
        }
    }
}
