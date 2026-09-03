class Solution {
    public int maxProduct(int[] nums) {
        // split array around zeroes to avoid them
        List<List<Integer>> splits = new ArrayList<>();
        List<Integer> sub = new ArrayList<>();
        Integer best = Integer.MIN_VALUE;
        for(int i : nums) {
            best = Math.max(best, i); // capture highest number as we go for an easy baseline "best"
            if(i == 0) {
                if(!sub.isEmpty()) {
                    splits.add(sub);
                    sub = new ArrayList<>();
                }
            } else {
                sub.add(i);
            }
        }
        if(!sub.isEmpty()) {
            splits.add(sub);
        }

        for(List<Integer> split : splits) {
            // count the negative numbers in each split
            int negatives = 0;
            for(int i : split) {
                if(i < 0) {
                    negatives++;
                }
            }
            
            // update negative count to be the "ideal" number of negatives for the highest product,
            // which is the highest possible even quantity of negatives
            negatives = negatives % 2 == 0 ? negatives : negatives-1;

            int product = 1;
            int negCount = 0;
            for(int left = 0, right = 0; right < split.size(); right++) {
                // include value @ right in product
                product *= split.get(right);
                
                // if a new negative value is introduced, increase the count and then trim from the left
                // if we have exceed the "ideal" quantity of negatives
                if(split.get(right) < 0) {
                    negCount++;
                    while (negCount > negatives) {
                        product /= split.get(left);
                        if(split.get(left) < 0) {
                            negCount--;
                        }
                        left++;
                    }
                }                

                // update "best" if we encounter a larger product than what it currently holds
                if(left <= right) {
                    best = Math.max(best, product);
                }
            }
        }

        return best;
    }
}
