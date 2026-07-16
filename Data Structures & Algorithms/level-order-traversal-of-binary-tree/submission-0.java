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
    public List<List<Integer>> levelOrder(final TreeNode root) {
        final List<List<Integer>> solution = new ArrayList<>();
        // return empty solution if no root exists
        if (root == null) {
            return solution;
        }

        // initialize solution with root value
        solution.add(List.of(root.val));

        // find children in order
        List<TreeNode> children = getChildrenInOrder(List.of(root));

        while (!children.isEmpty()) {
            // collect values at this layer for the solution
            final List<Integer> values = new ArrayList<>();
            for (final TreeNode child : children) {
                values.add(child.val);
            }
            solution.add(values);

            // find next layer of children
            children = getChildrenInOrder(children);
        }

        return solution;
    }

    public List<TreeNode> getChildrenInOrder(final List<TreeNode> parents) {
        List<TreeNode> children = new ArrayList<>();
        for (final TreeNode parent : parents) {
            if (parent.left != null) {
                children.add(parent.left);
            }
            if (parent.right != null) {
                children.add(parent.right);
            }
        }

        return children;
    }
}
