// Definition for a pair.
// class Pair {
//     public int key;
//     public String value;
//
//     public Pair(int key, String value) {
//         this.key = key;
//         this.value = value;
//     }
// }
class Solution {
    public List<Pair> mergeSort(List<Pair> pairs) { 
        System.out.println("---------- NEW STEP ----------");

        final int inSize = pairs.size();
        System.out.println("in:" + pairs);
        if(inSize <= 1)
        {
            return pairs;
        }
        
        // divide
        final int midpoint = inSize/2;
        List<Pair> sub1 = mergeSort(pairs.subList(0, midpoint));
        List<Pair> sub2 = mergeSort(pairs.subList(midpoint, inSize));

        // conquer
        return merge(sub1, sub2);
    }

    private List<Pair> merge(List<Pair> sub1, List<Pair> sub2)
    {
        final List<Pair> result = new ArrayList<>();

        int sub1Index = 0;
        int sub2Index = 0;
        
        while(sub1Index < sub1.size() && sub2Index < sub2.size())
        {
            final Pair sub1Pair = sub1.get(sub1Index);
            final Pair sub2Pair = sub2.get(sub2Index);
            if(sub1Pair.key <= sub2Pair.key)
            {
                result.add(sub1Pair);
                sub1Index++;
            } else {
                result.add(sub2Pair);
                sub2Index++;
            }
        }

        // one sub-list will "run out" first, so empty the other list's "remainders" into the result
        while (sub1Index < sub1.size()) {
            result.add(sub1.get(sub1Index));
            sub1Index++;
        }

        while (sub2Index < sub2.size()) {
            result.add(sub2.get(sub2Index));
            sub2Index++;
        }

        return result;
    }
}
