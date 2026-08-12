class Solution {
    public List<List<String>> groupAnagrams(final String[] in) {
        // set up a map to store groups of anagrams by a common "signature" string
        Map<String, List<String>> stringsBySignature = new HashMap<>();

        for (final String str : in) {
            // turn the string into a char array and sort it
            final char ch[] = str.toCharArray();
            Arrays.sort(ch);
            // this sorted char array is now a suitable "signature" to detect anagrams
            final String signature = new String(ch);

            // add into the map
            List<String> strings = stringsBySignature.get(signature);
            if(strings == null) {
                strings = new ArrayList<>();
                strings.add(str);
                stringsBySignature.put(signature, strings);
            } else {
                strings.add(str);
            }
        }

        // each value of the map will contain an anagram grouping (same character frequency)
        return stringsBySignature.values().stream().toList();
    }

    
}
