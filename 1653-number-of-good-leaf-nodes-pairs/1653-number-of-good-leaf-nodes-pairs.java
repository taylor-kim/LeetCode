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
    public int countPairs(TreeNode root, int distance) {
        return mySol(root, distance);
    }

    public int mySol(TreeNode root, int distance) {
        List<TreeNode> leaves = new ArrayList();
        Map<TreeNode, List<TreeNode>> graph = buildGraphAndLeaves(root, leaves);

        // collectLeaves(root, leaves);

        int ans = 0;

        for (TreeNode start : leaves) {
            Queue<TreeNode> queue = new LinkedList();
            Set<TreeNode> visit = new HashSet();
            queue.add(start);
            visit.add(start);

            int level = 0;

            while (!queue.isEmpty()) {
                int size = queue.size();

                while (size-- > 0) {
                    TreeNode node = queue.poll();

                    if (node != start && node.left == null && node.right == null && level <= distance) {
                        ans++;
                    }

                    for (TreeNode next : graph.getOrDefault(node, new ArrayList<>())) {
                        if (visit.add(next)) {
                            queue.add(next);
                        }
                    }
                }

                level++;

                if (level > distance) break;
            }
        }

        return ans / 2;
    }

    private void collectLeaves(TreeNode root, List<TreeNode> list) {
        if (root == null) return;

        if (root.left == null && root.right == null) {
            list.add(root);
        }

        collectLeaves(root.left, list);
        collectLeaves(root.right, list);
    }

    private Map<TreeNode, List<TreeNode>> buildGraphAndLeaves(TreeNode root, List<TreeNode> leaves) {
        Map<TreeNode, List<TreeNode>> graph = new HashMap();

        buildGraphAndLeaves(root, graph, leaves);

        return graph;
    }

    private void buildGraphAndLeaves(TreeNode node, Map<TreeNode, List<TreeNode>> graph, List<TreeNode> leaves) {
        if (node == null) return;

        graph.computeIfAbsent(node, k -> new ArrayList());

        if (node.left != null) {
            graph.computeIfAbsent(node, k -> new ArrayList()).add(node.left);
            graph.computeIfAbsent(node.left, k -> new ArrayList()).add(node);

            buildGraphAndLeaves(node.left, graph, leaves);
        }

        if (node.right != null) {
            graph.computeIfAbsent(node, k -> new ArrayList()).add(node.right);
            graph.computeIfAbsent(node.right, k -> new ArrayList()).add(node);

            buildGraphAndLeaves(node.right, graph, leaves);
        }

        if (node.left == null && node.right == null) {
            leaves.add(node);
        }
    }
}