class PrefixTree {
    TrieNode root;

    public PrefixTree() {
        root = new TrieNode(null);
    }

    public void insert(String word) {
        TrieNode current = root;
        for(char c : word.toCharArray()) {
            TrieNode child = current.getChild(c);
            if(child == null) {
                child = current.addChild(c);
            }
            
            current = child;
        }

        current.end();
    }

    public boolean search(String word) {
        TrieNode current = root;
        for(char c : word.toCharArray()) {
            current = current.getChild(c);
            if(current == null) {
                return false;
            }
        }

        return current.isWordEnd;
    }

    public boolean startsWith(String prefix) {
        TrieNode current = root;
        for(char c : prefix.toCharArray()) {
            current = current.getChild(c);
            if(current == null) {
                return false;
            }
        }

        return true;
    }

    private class TrieNode {
        public Character letter;
        public Map<Character, TrieNode> childrenByLetter;
        public boolean isWordEnd;

        public TrieNode(Character letter) {
            this.letter = letter;
            this.childrenByLetter = new HashMap<>();
            this.isWordEnd = false;
        }

        public TrieNode getChild(Character c) {
            return childrenByLetter.get(c);
        }

        public TrieNode addChild(Character letter) {
            final TrieNode newNode = new TrieNode(letter);
            childrenByLetter.put(letter, newNode);
            return newNode;
        }

        public void end() {
            isWordEnd = true;
        }
    }
}
