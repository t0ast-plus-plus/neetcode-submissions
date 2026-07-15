class PrefixTree {
    final TrieNode root;

    public PrefixTree() {
        // start with empty root, not representing any letter
        root = new TrieNode(null);
    }

    public void insert(final String word) {
        TrieNode current = root;
        // go through each word character
        for(final char c : word.toCharArray()) {
            // add or traverse child
            TrieNode child = current.getChild(c);
            if(child == null) {
                child = current.addChild(c);
            }
            
            current = child;
        }

        // mark the final node as a word ending
        current.setWordEnd();
    }

    public boolean search(final String word) {
        TrieNode current = root;
        // attempt to walk the tree or for each letter
        for(final char c : word.toCharArray()) {
            current = current.getChild(c);
            if(current == null) {
                return false;
            }
        }

        // check final node see if the same word was ended here previously
        return current.isWordEnd();
    }

    public boolean startsWith(final String prefix) {
        // same as above, without caring about word endings
        TrieNode current = root;
        for(final char c : prefix.toCharArray()) {
            current = current.getChild(c);
            if(current == null) {
                return false;
            }
        }

        return true;
    }

    private class TrieNode {
        private final Character letter;
        private final Map<Character, TrieNode> childrenByLetter;
        private boolean isWordEnd;

        public TrieNode(final Character letter) {
            this.letter = letter;
            this.childrenByLetter = new HashMap<>();
            this.isWordEnd = false;
        }

        public Character getLetter() {
            return letter;
        }

        public TrieNode getChild(final Character c) {
            return childrenByLetter.get(c);
        }

        public TrieNode addChild(final Character letter) {
            final TrieNode newNode = new TrieNode(letter);
            childrenByLetter.put(letter, newNode);
            return newNode;
        }

        public void setWordEnd() {
            isWordEnd = true;
        }

        public boolean isWordEnd() {
            return isWordEnd;
        }
    }
}
