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
    public boolean isSubtree(TreeNode root, TreeNode subRoot) {
        return treeCheck(root, subRoot);
    }

    private boolean treeCheck(TreeNode root, TreeNode subRoot) {
        // check subRoot against root
        if (treequals(root, subRoot)) {
            return true;
        }

        // try checking subRoot against root's children recursively
        if (root != null && (treeCheck(root.left, subRoot) || treeCheck(root.right, subRoot))) {
            return true;
        }

        return false;
    }

    // check to see if two given nodes and all of their children equal each other
    private boolean treequals(TreeNode a, TreeNode b) {
        if (a == null && b == null) {
            return true;
        } else if (a == null || b == null || a.val != b.val) {
            return false;
        }

        return treequals(a.left, b.left) && treequals(a.right, b.right);
    }
}
