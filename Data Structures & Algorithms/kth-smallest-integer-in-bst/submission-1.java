/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */

class Solution {
    public int kthSmallest(TreeNode root, int k) {
        // storage for counter at [0], anser at [1]
        int[] storage = new int[2];
        storage[0] = k;
        search(root, storage);
        return storage[1];
    }

    // perform recursive in-order traversal (left -> root -> right)
    // each node visited will be in sorted (increasing) order
    // therefore, the Kth smallest node will be found in K steps
    public void search(TreeNode node, int[] storage) {
        if(node == null) {
            return;
        }

        // search left
        search(node.left, storage);
        if(storage[0] == 0) {
            // answer found somewhere to the left, return early
            return;
        }

        // decrement the counter to represent searching this node
        storage[0] -= 1;
        if(storage[0] == 0) {
            // when we run out of counter, we are at the solution
            storage[1] = node.val;
            return;
        }

        // continue search to the right
        search(node.right, storage);
    }
}
