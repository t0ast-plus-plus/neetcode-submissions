class Solution {
    public boolean isPalindrome(final String s) {
        // walk from each end inward
        int i = 0;
        int j = s.length() - 1;

        while (i < j) {
            // skip over non-alphanumeric characters
            if (!isAlphanumeric(s.charAt(i))) {
                i++;
                continue;
            }

            if (!isAlphanumeric(s.charAt(j))) {
                j--;
                continue;
            }

            // ensure both characters are lower-case representations and then compare
            if (Character.toLowerCase(s.charAt(i)) == Character.toLowerCase(s.charAt(j))) {
                // keep moving both pointers inward
                i++;
                j--;
            } else {
                // mismatch
                return false;
            }
        }

        return true;
    }

    private boolean isAlphanumeric(final char c) {
        return (c >= 'a' && c <= 'z')
            || (c >= 'A' && c <= 'Z') 
            || (c >= '0' && c <= '9');
    }
}
