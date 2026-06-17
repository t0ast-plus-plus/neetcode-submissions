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
    public boolean isValidBST(TreeNode root) {
        return validate(null, null, root);
    }

    private boolean validate(Integer min, Integer max, TreeNode node) {
        if (node == null) {
            // nothing to validate
            return true;
        }

        // check for value in bounds
        if((min != null && node.val <= min) || (max != null && node.val >= max)) {
            return false;
        }

        // recurse to children
        return validate(min, node.val, node.left) && validate(node.val, max, node.right);
    }
}
