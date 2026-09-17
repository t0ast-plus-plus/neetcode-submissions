class Solution {
    public int longestCommonSubsequence(final String text1, final String text2) {
        // memoization of LCS results for subsequences by starting index in each word
        final int[][] memo = new int[text1.length()][text2.length()];
        for (int[] m : memo) {
            Arrays.fill(m, -1);
        }

        return lcs(text1, text2, 0, 0, memo);
    }

    private int lcs(final String text1, final String text2, final int i1, final int i2, final int[][] memo) {
        if (i1 >= text1.length() || i2 >= text2.length()) {
            // OOB
            return 0;
        }

        if (memo[i1][i2] != -1) {
            // in memo = previously solved
            return memo[i1][i2];
        }

        if (text1.charAt(i1) == text2.charAt(i2)) {
            // chars at specified indexes match, so length = 1 + result of recursing into the next index of each text
            memo[i1][i2] = 1 + lcs(text1, text2, i1 + 1, i2 + 1, memo);
        } else {
            // no match in characters, so update memo with the best result from the two possible paths forward:
            // 1) advance the index for the first text, maintaining the index for the second text
            // 2) advance the index for the second text, maintaining the index for the first text
            memo[i1][i2] =
                Math.max(lcs(text1, text2, i1 + 1, i2, memo), lcs(text1, text2, i1, i2 + 1, memo));
        }

        return memo[i1][i2];
    }
}
