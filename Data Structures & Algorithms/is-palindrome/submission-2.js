class Solution {
    /**
     * @param {string} s
     * @return {boolean}
     */
    isPalindrome(s) {
        let l = 0,
            r = s.length - 1;
        while (l < r) {
            // skip all non-alphanumeric from each end
            while (l < r && !this.isAlphaNum(s[l])) {
                l++;
            }
            while (l < r && !this.isAlphaNum(s[r])) {
                r--;
            }
            // check for lower-case equality
            if (s[l].toLowerCase() !== s[r].toLowerCase()) {
                return false;
            }
            // move both pointers inward
            l++;
            r--;
        }
        return true;
    }

    /**
     * @param {char} c
     * @return {boolean} true if alphanumeric, otherwise false
     */
    isAlphaNum(c) {
        return (c >= "A" && c <= "Z") || (c >= "a" && c <= "z") || (c >= "0" && c <= "9");
    }
}
