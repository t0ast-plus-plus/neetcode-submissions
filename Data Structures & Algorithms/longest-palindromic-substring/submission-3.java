class Solution {
    public String longestPalindrome(String s) {
        if (s.length() == 1) {
            return s;
        }

        String longest = "";
        for (int start = 0; start < s.length(); start++) {
            // odd length
            int oddL = start;
            int oddR = start;
            while (oddL >= 0 && oddR < s.length()) {
                if(s.charAt(oddL) != s.charAt(oddR)) {
                    break;
                }

                if (oddR - oddL + 1 > longest.length()) {
                    longest = s.substring(oddL, oddR + 1);
                }
                oddL--;
                oddR++;
            }

            // even length
            int evenL = start;
            int evenR = start + 1;
            while (evenL >= 0 && evenR < s.length()) {
                if(s.charAt(evenL) != s.charAt(evenR)) {
                    break;
                }

                if (evenR - evenL + 1 > longest.length()) {
                    longest = s.substring(evenL, evenR + 1);
                }
                evenL--;
                evenR++;
            }
        }

        return longest;
    }
}
