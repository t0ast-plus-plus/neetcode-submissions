// Definition for a pair.
// class Pair {
//     int key;
//     String value;
//
//     public Pair(int key, String value) {
//         this.key = key;
//         this.value = value;
//     }
// }
class Solution {
    public List<Pair> quickSort(final List<Pair> pairs) {
        // sorting performed in-place on provided list
        sort(pairs, 0, pairs.size() - 1);
        return pairs;
    }

    public void sort(final List<Pair> pairs, final int startIndex, final int endIndex) {
        if (endIndex - startIndex <= 0) {
            // no elements to sort
            return;
        }

        // choose pivot element
        // this can be anything, but this problem specifically wants right-most
        final Pair pivot = pairs.get(endIndex);

        // track an index for swapping elements into, starting at the start index
        int swapIndex = startIndex;

        // iterate through all elements from start to pivot (not inclusive)
        for (int i = startIndex; i < endIndex; i++) {
            if (pairs.get(i).key < pivot.key) {
                // elements smaller than pivot swap with the element at swap index
                final Pair temp = pairs.get(swapIndex);
                pairs.set(swapIndex, pairs.get(i));
                pairs.set(i, temp);
                // then increment the swap index for the next potential swap
                swapIndex++;
            }
        }

        // at this point, all elements left of the swap index are less than pivot
        // and all elements at or right of the swap index are greater than or equal
        // so if we swap the pivot into the swap index, that element is now sorted
        pairs.set(endIndex, pairs.get(swapIndex));
        pairs.set(swapIndex, pivot);

        // recursively sort the elements left of the pivot (at swap index)
        sort(pairs, startIndex, swapIndex - 1);
        // recursively sort the elements right of the pivot (at swap index)
        sort(pairs, swapIndex + 1, endIndex);
        // note that pivot is not included, because it's already in the right position
    }
}
