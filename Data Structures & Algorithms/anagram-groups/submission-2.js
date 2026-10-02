class Solution {
    /**
     * @param {string[]} strs
     * @return {string[][]}
     */
    groupAnagrams(strs) {
        let stringsBySignature = {};
        for(const s of strs) {
            // turn each string into a char array and sort it to create an anagram "key" for grouping
            const key = s.split('').sort();
            if(!stringsBySignature[key]) {
                stringsBySignature[key] = [];
            }
            stringsBySignature[key].push(s);
        }
        return Object.values(stringsBySignature);
    }
}
