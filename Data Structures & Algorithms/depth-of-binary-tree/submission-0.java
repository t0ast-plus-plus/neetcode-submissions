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
    public int maxDepth(final TreeNode root) {
        return findMaxDepth(root, 0);
    }

    // recursive traversal of the tree
    // track depth as it goes down then bubble up the max value of its children
    private int findMaxDepth(final TreeNode node, final int currentDepth) {
        if(node == null) {
            return currentDepth;
        }

        return Math.max(findMaxDepth(node.left, currentDepth+1), findMaxDepth(node.right, currentDepth+1));
    }
}
