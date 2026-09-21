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
    int preIdx = 0;
    int inIdx = 0;

    public TreeNode buildTree(final int[] preorder, final int[] inorder) {
        return build(preorder, inorder, Integer.MAX_VALUE);

        // BASIC DFS SOLUTION:
        // if (preorder.length == 0 || inorder.length == 0) {
        //     // no elements available = no node to build
        //     return null;
        // }

        // Map<Integer, Integer> inorderIndexByValue = new HashMap<>();
        // for(int i = 0; i < inorder.length; i++) {
        //     inorderIndexByValue.put(inorder[i], i);
        // }

        // // first index of preorder traversal = root
        // final TreeNode root = new TreeNode(preorder[0]);

        // // find the index of the root value within the inorder array
        // int i = 0;
        // for(; i < inorder.length; i++) {
        //     if(inorder[i] == root.val) {
        //         break;
        //     }
        // }

        // // for the inorder array, taking all elements to the left of the root value's index
        // // gives us all left children of the root, and vice versa for the right side/children
        // // (note that Arrays.copyOfRange treats the third element as exclusive)
        // int[] leftInorder = Arrays.copyOfRange(inorder, 0, i);
        // int[] rightInorder = Arrays.copyOfRange(inorder, i + 1, inorder.length);

        // // for the preorder array, partition into left and right based on the sizes of
        // // how the resulting left/right partitions of the inorder array
        // int[] leftPreorder = Arrays.copyOfRange(preorder, 1, i + 1);
        // int[] rightPreorder = Arrays.copyOfRange(preorder, i + 1, preorder.length);

        // // recursively build out the left and right children using the newly partitioned arrays
        // root.left = buildTree(leftPreorder, leftInorder);
        // root.right = buildTree(rightPreorder, rightInorder);

        // return root;
    }

    private TreeNode build(int[] preorder, int[] inorder, int limit) {
        if (preIdx >= preorder.length) {
            // no more values to build nodes from
            return null;
        } 
        
        if (inorder[inIdx] == limit) {
            // if we have just built a parent whose value matches our inorder index value
            // it means we have reached the end of a left branch,
            // so advance the inorder index and return null
            inIdx++;
            return null;
        }

        // build node from the current preorder index and move on to the next one
        TreeNode node = new TreeNode(preorder[preIdx++]);

        // the value of this new node will serve as the limit for building out new left children
        node.left = build(preorder, inorder, node.val);
        // right children can freely build out from the remaining elements
        node.right = build(preorder, inorder, limit);
        return node;
    }
}
