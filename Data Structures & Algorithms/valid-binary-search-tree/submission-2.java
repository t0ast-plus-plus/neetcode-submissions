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
            // no node here, so nothing to validate
            return true;
        }

        // validate that the node's value is in the min/max bounds provided
        if((min != null && node.val <= min) || (max != null && node.val >= max)) {
            return false;
        }

        // recurse to children, updating left's max and right's min as we go
        return validate(min, node.val, node.left) && validate(node.val, max, node.right);
    }
}
