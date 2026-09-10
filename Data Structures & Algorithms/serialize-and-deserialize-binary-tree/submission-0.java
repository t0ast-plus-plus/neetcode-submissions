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

public class Codec {
    private static final String DELIM = ",";
    private static final String NULLVAL = "N";

    // Encodes a tree to a single string.
    public String serialize(final TreeNode root) {
        final String serialized = serializeNode(root);
        System.out.println(serialized);
        return serialized;
    }

    // DFS serialization of a given node
    private String serializeNode(final TreeNode node) {
        if (node == null) {
            return NULLVAL + DELIM;
        }

        final StringBuilder sb = new StringBuilder();
        sb.append(node.val + DELIM);
        sb.append(serializeNode(node.left));
        sb.append(serializeNode(node.right));
        return sb.toString();
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(final String data) {
        // note: using AtomicInteger to pass by reference because we want to
        // keep incrementing our index universally as we recursively build out the tree
        return buildTree(data.split(DELIM), new AtomicInteger(0));
    }

    // builds a TreeNode from the node data at a given index
    private TreeNode buildTree(final String[] nodes, final AtomicInteger i) {
        // node is null if we run out of data or the node data indicates null
        if (i.get() >= nodes.length || NULLVAL.equals(nodes[i.get()])) {
            i.incrementAndGet();
            return null;
        }

        // create this node, advance the index, then recursively (DFS) build out its children
        final TreeNode node = new TreeNode(Integer.parseInt(nodes[i.getAndIncrement()]));
        node.left = buildTree(nodes, i);
        node.right = buildTree(nodes, i);

        return node;
    }
}
