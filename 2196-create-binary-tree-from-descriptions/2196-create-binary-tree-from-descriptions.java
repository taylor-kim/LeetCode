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
    public TreeNode createBinaryTree(int[][] descriptions) {
        return mySol2(descriptions);
    }

    public TreeNode mySol2(int[][] descriptions) {
        Map<Integer, TreeNode> map = new HashMap();

        TreeNode root = null;

        for (int[] desc : descriptions) {
            TreeNode parent = map.computeIfAbsent(desc[0], k -> new TreeNode(desc[0]));
            TreeNode child = map.computeIfAbsent(desc[1], k -> new TreeNode(desc[1]));

            if (desc[2] == 1) {
                parent.left = child;
            } else {
                parent.right = child;
            }

            if (root == null) {
                root = parent;
            } else if (root == child) {
                root = parent;
            }
        }

        return root;
    }

    public TreeNode mySol(int[][] descriptions) {
        Map<Integer, int[]> graph = new HashMap();
        Map<Integer, Integer> indegree = new HashMap();

        for (int[] desc : descriptions) {
            graph.computeIfAbsent(desc[0], k -> new int[2]);

            graph.get(desc[0])[1 - desc[2]] = desc[1];
            graph.computeIfAbsent(desc[1], k -> new int[2]);

            indegree.computeIfAbsent(desc[0], k -> 0);
            indegree.put(desc[1], indegree.getOrDefault(desc[1], 0) + 1);
        }

        for (int key : indegree.keySet()) {
            if (indegree.get(key) == 0) {
                return build(graph, key);
            }
        }

        return null;
    }

    private TreeNode build(Map<Integer, int[]> graph, int value) {
        if (value == 0) return null;

        TreeNode node = new TreeNode(value);

        node.left = build(graph, graph.get(value)[0]);
        node.right = build(graph, graph.get(value)[1]);

        return node;
    }
}