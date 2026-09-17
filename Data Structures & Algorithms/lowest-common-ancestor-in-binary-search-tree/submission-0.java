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
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if(root == null) {
            return null;
        }

        if(Math.max(p.val, q.val) < root.val) {
            // p and q are both smaller than root, so move left and repeat
            return lowestCommonAncestor(root.left, p, q);
        } else if (Math.min(p.val, q.val) > root.val) {
            // p and q are both larger than root, so move right and repeat
            return lowestCommonAncestor(root.right, p, q);
        } else {
            // root is in-between p and q, therefore they are split among its children
            // which makes root the lowest common ancestor
            return root;
        }
    }
}
