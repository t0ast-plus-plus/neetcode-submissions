// Definition for a pair
// class Pair {
//     int key;
//     String value;
//
//     Pair(int key, String value) {
//         this.key = key;
//         this.value = value;
//     }
// }
public class Solution {
    public List<List<Pair>> insertionSort(List<Pair> pairs) {
        final List<List<Pair>> sortSteps = new ArrayList<>();

        for(int i = 0; i < pairs.size(); i++)
        {
            int j = i-1;
            while(j >= 0 && pairs.get(j).key > pairs.get(j + 1).key)
            {
                Pair temp = pairs.get(j);
                pairs.set(j, pairs.get(j + 1));
                pairs.set(j + 1, temp);
                j--;
            }

            sortSteps.add(new ArrayList<>(pairs));
        }

        return sortSteps;
    }
}
