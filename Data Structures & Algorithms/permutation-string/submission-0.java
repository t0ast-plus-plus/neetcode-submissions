class Solution {
    public boolean checkInclusion(String s1, String s2) {
        if(s2.length() < s1.length()) {
            return false;
        }

        Map<Character, Integer> targetFreq = new HashMap<>();
        Map<Character, Integer> currentFreq = new HashMap<>();

        for(int i = 0; i < s1.length(); i++) {
            char targetChar = s1.charAt(i);
            targetFreq.put(targetChar, targetFreq.getOrDefault(targetChar, 0) + 1);

            char currentChar = s2.charAt(i);
            currentFreq.put(currentChar, currentFreq.getOrDefault(currentChar, 0) + 1);
        }

        if(targetFreq.equals(currentFreq)) {
            return true;
        }

        int removeAt = 0;
        int addFrom = s1.length();
        while(addFrom < s2.length()) {
            char charToAdd = s2.charAt(addFrom);
            currentFreq.put(charToAdd, currentFreq.getOrDefault(charToAdd, 0) + 1);
            char charToRemove = s2.charAt(removeAt);
            Integer charFreq = currentFreq.get(charToRemove);
            if(charFreq > 1) {
                currentFreq.put(charToRemove, charFreq-1);
            } else {
                currentFreq.remove(charToRemove);
            }

            System.out.println("current: " + currentFreq);
            System.out.println("target: " + targetFreq);
            if(targetFreq.equals(currentFreq)) {
                return true;
            }

            removeAt++;
            addFrom++;
        }

        return false;
    }
}
