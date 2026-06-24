class Solution {
    public boolean wordBreak(String s, List<String> wordDict) {
        HashSet<String> wordSet = new HashSet<>(wordDict);
        Map<Integer, Boolean> memo = new HashMap<>();
        return search(s, wordSet, memo, 0);
    }

    private boolean search(String s, HashSet<String> wordSet, Map<Integer, Boolean> memo, int index) {
        if (memo.containsKey(index)) {
            return memo.get(index);
        }

        if (index == s.length()) {
            return true;
        }

        for (int i = index; i < s.length(); i++) {
            if(wordSet.contains(s.substring(index, i+1))) {
                if (search(s, wordSet, memo, i+1)) {
                    memo.put(index, true);
                    return true;
                }
            }
        }

        memo.put(index, false);
        return false;
    }
}
