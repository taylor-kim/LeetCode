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
    public long kthLargestLevelSum(TreeNode root, int k) {
        return bfs(root, k);
    }

    public long bfs(TreeNode root, int k) {
        Queue<TreeNode> queue = new LinkedList();
        queue.add(root);

        Queue<Long> pq = new PriorityQueue<>((a, b) -> {
            return Long.compare(b, a);
        });

        while (!queue.isEmpty()) {
            long levelSum = 0;

            int size = queue.size();

            while (size-- > 0) {
                TreeNode node = queue.poll();

                levelSum += node.val;

                if (node.left != null) queue.add(node.left);
                if (node.right != null) queue.add(node.right);
            }

            pq.add(levelSum);
        }

        while (k-- > 1 && !pq.isEmpty()) {
            pq.poll();
        }
        
        return pq.isEmpty() ? -1 : pq.poll();
    }
}