class Solution {

    public String encode(final List<String> strs) {
        // prefix each string with <string length> + "#"
        final StringBuilder sb = new StringBuilder();
        for(final String str : strs) {
            sb.append(str.length());
            sb.append('#');
            sb.append(str);
        }

        return sb.toString();
    }

    public List<String> decode(final String str) {
        final List<String> out = new ArrayList<>();

        // handle empty input string edge case
        if(str.equals("0#")) {
            out.add("");
            return out;
        }

        int start = 0;
        int end = 0;
        StringBuilder lengthBuilder = new StringBuilder();
        boolean getLength = true;
        char c;
        
        while (end < str.length()) {
            if(getLength) {
                c = str.charAt(start);
                if (c == '#') {
                    // delimiter found, flag the length value as complete
                    getLength = false;
                    // string to be extracted will start at next index
                    start++;
                } else {
                    // append this character to the length value and increment the start index
                    lengthBuilder.append(c);
                    start++;
                }
            } else {
                // calculate substring end index using the stored length value
                end = start + Integer.valueOf(lengthBuilder.toString());
                // extract the calcultaed substirng range and move start to the next index
                out.add(str.substring(start, end));
                start = end;
                // reset the length value and set flag to get next length
                lengthBuilder = new StringBuilder();
                getLength = true;
            }
        }

        return out;
    }
}
