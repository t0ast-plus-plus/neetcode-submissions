class Solution {
    public List<List<String>> groupAnagrams(String[] in) {
        // create map to identify lists of strings by a map of their character frequency
        Map<Map<Character, Integer>, List<String>> stringsByCharFreq = new HashMap<>();
        for (String str : in) {
            Map<Character, Integer> charFreq = getCharFreqMap(str);
            List<String> strings = stringsByCharFreq.get(charFreq);
            if (strings == null) {
                strings = new ArrayList<>();
                strings.add(str);
                stringsByCharFreq.put(charFreq, strings);
            } else {
                strings.add(str);
            }
        }

        // each value of the map will contain an anagram grouping (same character frequency)
        return stringsByCharFreq.values().stream().toList();
    }

    // helper method to generate character frequency map for a given string
    private Map<Character, Integer> getCharFreqMap(final String s) {
        final Map<Character, Integer> charFreq = new HashMap<>();
        for (Character c : s.toCharArray()) {
            Integer freq = charFreq.get(c);
            if (freq != null) {
                charFreq.put(c, freq + 1);
            } else {
                charFreq.put(c, 1);
            }
        }
        return charFreq;
    }
}
