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
        return official_min_heap(root, k);
    }

    public long official_min_heap(TreeNode root, int k) {
        Queue<TreeNode> queue = new LinkedList();
        queue.add(root);

        Queue<Long> pq = new PriorityQueue();

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

            if (pq.size() > k) {
                pq.poll();
            }
        }

        if (pq.size() < k) return -1;
        
        return pq.poll();
    }

    public long dfs(TreeNode root, int k) {
        List<Long> list = new ArrayList();

        dfs(root, 0, list);

        Collections.sort(list, Comparator.reverseOrder());

        return list.size() < k ? -1 : list.get(k - 1);
    }

    public void dfs(TreeNode root, int level, List<Long> list) {
        if (root == null) return;

        if (list.size() == level) {
            list.add((long)root.val);
        } else {
            list.set(level, list.get(level) + root.val);
        }

        dfs(root.left, level + 1, list);
        dfs(root.right, level + 1, list);
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