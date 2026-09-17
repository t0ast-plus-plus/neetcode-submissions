class Solution {
    public int numDecodings(String s) {
        // memoization to store the number of ways we can decode from a given index forward
        Map<Integer, Integer> decodings = new HashMap<>();
        // initialize with base case value of 1
        decodings.put(s.length(), 1);
        return decode(s, 0, decodings);
    }

    private int decode(final String s, final int i, final Map<Integer, Integer> decodings) {
        if(decodings.containsKey(i)) {
            // number of decodings at this index already known
            return decodings.get(i);
        }

        if(s.charAt(i) == '0') {
            // cannot decode numbers beginning with '0'
            return 0;
        }

        // proceed with decoding that treats this index as a single number
        int result = decode(s, i+1, decodings);
        // if this index + next can be interpreted as a valid two-digit number,
        // add those decoding results as well
        if(i+1 < s.length() && Integer.valueOf(s.substring(i, i+2)) <= 26) {
            result += decode(s, i+2, decodings);
        }
        
        // store the decoding result for future reference
        decodings.put(i, result);

        return result;
    }
}
