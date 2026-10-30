class Solution {
    public int countSubstrings(final String s) {
        int count = 0;
        for(int i = 0; i < s.length(); i++) {
            // each letter on its own counts as a palindrome
            count++;

            // search outward for both odd-length (starting from i-1 to i+1)
            // and even-length (starting from i to i+1) palindromes
            count += getPalindromeCount(s, i-1, i+1);
            count += getPalindromeCount(s, i, i+1);
        }

        return count;
    }

    /**
    * s = string to evaluate
    * l = starting left index
    * r = starting right index
    * returns number of palindromes found in provided range and outward
    */
    private int getPalindromeCount(final String s, int l, int r) {
        int count = 0;
        while(l >= 0 && r < s.length()) {
            if(s.charAt(l) != s.charAt(r)) {
                break;
            }
            
            count++;
            l--;
            r++;
        }
        return count;
    }
}
