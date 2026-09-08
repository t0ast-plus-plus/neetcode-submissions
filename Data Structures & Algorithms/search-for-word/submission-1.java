class Solution {
    public boolean exist(char[][] board, String word) {
        char firstChar = word.charAt(0);
        for(int r = 0; r < board.length; r++) {
            for(int c = 0; c < board[0].length; c++) {
                if(wordSearch(board, word, new boolean[board.length][board[0].length], 0, r, c)) {
                    return true;
                }
            }
        }

        return false;
    }

    private boolean wordSearch(char[][] board, String word, boolean[][] visited, int charIndex, int row, int col) {
        // failure conditions: OOB, previously visited, or char doesn't match
        if(row < 0 || col < 0 || row >= board.length || col >= board[0].length
            || visited[row][col] || board[row][col] != word.charAt(charIndex)) {
            return false;
        }

        // success condition: matched last character of word
        if(charIndex == word.length()-1) {
            return true;
        }

        // mark visited and search for next char
        visited[row][col] = true;
        charIndex++;
        if(wordSearch(board, word, visited, charIndex, row-1, col) ||
            wordSearch(board, word, visited, charIndex, row+1, col) ||
            wordSearch(board, word, visited, charIndex, row, col-1) ||
            wordSearch(board, word, visited, charIndex, row, col+1)) {
            return true;
        }

        // next char not found from this position, so "unvisit" this cell as DFS backtracks
        visited[row][col] = false;
        return false;
    }
}
