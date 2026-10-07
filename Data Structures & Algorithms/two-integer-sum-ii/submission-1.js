class Solution {
    /**
     * @param {number[]} numbers
     * @param {number} target
     * @return {number[]}
     */
    twoSum(numbers, target) {
        let solution = [];
        let l = 0, r = numbers.length-1;
        while(l < r) {
            const sum = numbers[l] + numbers[r];
            if(sum === target) {
                solution[0] = l+1;
                solution[1] = r+1;
                break;
            }

            if(sum < target) {
                l++;
            } else {
                r--;
            }
        }
        
        return solution;
    }
}
