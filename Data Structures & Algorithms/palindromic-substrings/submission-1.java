class Solution {
    public int countSubstrings(String s) {
        int count = 0;
        for(int i = 0; i < s.length(); i++) {
            // each letter on its own counts as a palindrome
            count++;

            // search for palindromes by expanding outward from this index
            // odd-length (starting from i-1 and i+1)
            int l = i-1, r = i+1;
            while(l >= 0 && r < s.length()) {
                if(s.charAt(l) != s.charAt(r)) {
                    break;
                }
                
                count++;
                l--;
                r++;
            }

            //even-length (starting from i and i+1)
            l = i;
            r = i+1;
            while(l >= 0 && r < s.length()) {
                if(s.charAt(l) != s.charAt(r)) {
                    break;
                }
                
                count++;
                l--;
                r++;
            }
        }

        return count;
    }
}
