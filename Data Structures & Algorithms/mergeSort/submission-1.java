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
        final int inSize = pairs.size();
        if(inSize <= 1)
        {
            // can't divide any further
            return pairs;
        }
        
        // keep recursively dividing in half until we hit the condition above
        final int midpoint = inSize/2;
        // note that subList's first param is inclusive and the second is exclusive
        final List<Pair> sub1 = mergeSort(pairs.subList(0, midpoint));
        final List<Pair> sub2 = mergeSort(pairs.subList(midpoint, inSize));

        // put the divided sub-lists back together in order
        return merge(sub1, sub2);
    }

    private List<Pair> merge(final List<Pair> sub1, final List<Pair> sub2)
    {
        final List<Pair> result = new ArrayList<>();

        // indexes to iterate through each list
        int sub1Index = 0;
        int sub2Index = 0;
        
        // compare values in each list at their respective iterator positione
        // copy the minimum into the result list and advance that list's iterator
        // repeat until one of the lists runs out
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

        // one list will have remaining elements to iterate through
        // copy all of those elements (in order) into the result
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
