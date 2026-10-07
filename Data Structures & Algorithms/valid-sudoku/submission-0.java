class Solution {
    public boolean isValidSudoku(char[][] board) {
        Map<Integer, Set<Character>> rows = new HashMap<>();
        Map<Integer, Set<Character>> cols = new HashMap<>();;
        Map<Integer, Set<Character>> subBoxes = new HashMap<>();

        for(int i = 0; i < 9; i++) {
            rows.put(i, new HashSet<>());
            cols.put(i, new HashSet<>());
            subBoxes.put(i, new HashSet<>());
        }

        for(int r = 0; r < board.length; r++) {
            for(int c = 0; c < board[0].length; c++) {
                final char val = board [r][c];
                if(val == '.') {
                    continue;
                }
                
                if(!rows.get(r).add(val) || !cols.get(c).add(val) || !subBoxes.get(getSubBoxId(r, c)).add(val)) {
                    return false;
                }
            }
        }

        return true;
    }

    int getSubBoxId(int r, int c) {
        int id = 0;
        if (r >= 3 && r <= 5) {
            id += 3;
        } else if (r >= 6) {
            id += 6;
        }

        if(c >= 3 && c <= 5) {
            id += 1;
        } else if (c >= 6) {
            id += 2;
        }

        return id;
    }
}
