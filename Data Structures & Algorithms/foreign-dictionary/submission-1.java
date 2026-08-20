class Solution {
    public String foreignDictionary(final String[] words) {
        ////////////////////////////////////////
        // Input Analysis
        ////////////////////////////////////////

        // Create a character ordering graph by way of a map that stores each character that follows a given character
        final Map<Character, Set<Character>> orderByChar = new HashMap<>();
        // pre-populate the graph/map with each character in each word + an empty collection of following characters
        for (final String word : words) {
            for (final char c : word.toCharArray()) {
                orderByChar.putIfAbsent(c, new HashSet<>());
            }
        }

        // scan each adjacent word pair to locate the first characters that differ between them
        // each found pair of differing characters will inform our character ordering map/graph
        for (int i = 0; i < words.length - 1; i++) {
            Character[] diffChars = getFirstDifferentCharacters(words[i], words[i+1]);

            if (diffChars[1] == null) {
                if(diffChars[0] != null) {
                    // invalid ordering found (e.g. "cats", "cat")
                    return "";
                } else {
                    // words are identical, so skip this pair
                    continue;
                }
            } else if (diffChars[0] != null) {
                // we have two different, ordered characters to add to the map/graph
                orderByChar.get(diffChars[0]).add(diffChars[1]);
            }

            // otherwise we do not have enough information to inform our ordering (e.g., "cat" -> "cats")
        }

        ////////////////////////////////////////
        // Determine Character Ordering
        ////////////////////////////////////////

        // Establish requisite data elements for post-order DFS traversal
        final Map<Character, Boolean> visited = new HashMap<>();
        final List<Character> result = new ArrayList<>();

        // traverse the character ordering map using a post-order DFS method
        for (final char c : orderByChar.keySet()) {
            // a return of false here indicates that the character or one of it's following characters
            // references an earlier character that is still being processed
            // in other words, a loop exists in our graph and therefore cannot be ordered
            if (!getOrder(c, visited, orderByChar, result)) {
                return "";
            }
        }

        // reverse the traversal result (because it was built in post-order) and return
        Collections.reverse(result);
        final StringBuilder sb = new StringBuilder();
        for (final char c : result) {
            sb.append(c);
        }
        return sb.toString();
    }

    // returns a two-character array containing the earliest characters that differ between two words
    // first char will be from the first word, second char will be from the second word
    // if words are entirely identical, both characters will be null
    // if words are initally identical but one word is longer, then one character will be null
    private Character[] getFirstDifferentCharacters(final String firstWord, final String secondWord) {
        final Character[] result = new Character[2];
        Arrays.fill(result, null);

        int charIndex = 0;
        while (result[0] == null && result[1] == null) {
            Character firstChar =
                charIndex < firstWord.length() ? firstWord.charAt(charIndex) : null;
            Character secondChar =
                charIndex < secondWord.length() ? secondWord.charAt(charIndex) : null;

            if (firstChar == null && secondChar == null) {
                // reached the end of each word w/o a difference
                break;
            }

            // record any differeing character(s) (which will break the loop)
            if (firstChar != secondChar) {
                result[0] = firstChar;
                result[1] = secondChar;
            }

            // otherwise move to next character(s)
            charIndex++;
        }

        return result;
    }

    // post-order DFS traversal from character 'c' in the 'orderByChar' map
    // 'orderByChar' provides a character ordering map/graph (key = character, value = all characters that follow, unordered)
    // 'visited' tracks a character's processing state (false = still processing, true = done processing, null = not visited)
    // 'result' will contain the final ordering in reverse
    // note: pre-order traversal is not a good fit here because multiple earlier chars may reference the same later char,
    // thus introducing ambiguity (e.g., A->BC and B->C when starting from A), so we solve by starting from the leaf end(s) instead
    private boolean getOrder(final char c,
        final Map<Character, Boolean> visited,
        final Map<Character, Set<Character>> orderByChar,
        final List<Character> result) {
        // if this character has already been visited, return whether or not it is done processing
        if (visited.containsKey(c)) {
            return visited.get(c);
        }

        // mark this character as visited=false to indicate it is part of an in-progress traversal
        visited.put(c, false);

        // recurse down into the character(s) that follow this one
        // if we get a return indicating that another in-progress character was referenced
        // then it means we have a loop and should immediately bubble it back up to indicate failure
        for (char nextChar : orderByChar.get(c)) {
            if (!getOrder(nextChar, visited, orderByChar, result)) {
                return false;
            }
        }

        // all subsequent characters have been processed, so now we can finish processing this one
        visited.put(c, true);
        result.add(c);
        return true;
    }
}
