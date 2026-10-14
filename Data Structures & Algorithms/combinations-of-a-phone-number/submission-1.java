class Solution {
    final Map<Character, List<Character>> digitChars = new HashMap<>();

    public Solution() {
        char nextChar = 'a';
        for(char d = '2'; d <= '9'; d++) {
            final List<Character> chars = new ArrayList<>();
            for(int j = 0; j < 3; j++) {
                chars.add(nextChar++);
            }
            if(d == '7' || d == '9') {
                // special case - digits with four chars
                chars.add(nextChar++);
            }
            digitChars.put(d, chars);
        }
    }
    
    public List<String> letterCombinations(final String digits) {
        List<String> result = new ArrayList<>();
        if (digits.isEmpty()) {
            return result;
        } 
        
        result.add("");

        for(char d : digits.toCharArray()) {
            // for each digit provided, start with a temporary array
            List<String> temp = new ArrayList<>();
            // populate this array with all permutations of
            // existing results + new characters for this digit
            for(String s : result) {
                for(char c : digitChars.get(d)) {
                    temp.add(s+c);
                }
            }
            // use this temporary array to overwrite the results array so it can
            // serve as the base for the permutations of any following iteration
            // using "23" as an example: [] --> [a, b, c] --> [ad, ae, af, bd, ...]
            result = temp;
        }

        return result;
    }
}
