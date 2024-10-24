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
    public boolean flipEquiv(TreeNode root1, TreeNode root2) {
        return mySol2(root1, root2);
    }

    public boolean mySol2(TreeNode root1, TreeNode root2) {
        if (root1 == null && root2 == null) return true;
        if (root1 == null || root2 == null) return false;

        Queue<TreeNode> queue1 = new LinkedList();
        Queue<TreeNode> queue2 = new LinkedList();

        queue1.add(root1);
        queue2.add(root2);

        while (!queue1.isEmpty() && !queue2.isEmpty()) {
            int size1 = queue1.size();
            int size2 = queue2.size();

            if (size1 != size2) {
                // System.out.println(String.format("size1:%d, size2:%d", size1, size2));
                return false;
            }

            while (size1-- > 0) {
                TreeNode node1 = queue1.poll();
                TreeNode node2 = queue2.poll();

                if (node1.val != node2.val) {
                    // System.out.println(String.format("val1:%d, val2:%d", node1.val, node2.val));
                    return false;
                }

                Queue<TreeNode> pq = new PriorityQueue<>((a, b) -> {
                    return a.val - b.val;
                });

                if (node1.left != null) pq.add(node1.left);
                if (node1.right != null) pq.add(node1.right);
                if (node2.left != null) pq.add(node2.left);
                if (node2.right != null) pq.add(node2.right);

                int nextSize = pq.size();

                if (nextSize % 2 != 0) return false;

                while (nextSize > 0) {
                    if (nextSize % 2 == 0) {
                        queue1.add(pq.poll());
                    } else {
                        queue2.add(pq.poll());
                    }
                    nextSize--;
                }
            }
        }

        return queue1.isEmpty() && queue2.isEmpty();
    }

    public boolean mySol_fail(TreeNode root1, TreeNode root2) {
        if (root1 == null && root2 == null) return true;
        if (root1 == null || root2 == null) return false;

        Queue<TreeNode> queue1 = new PriorityQueue<>((a, b) -> {
            return a.val - b.val;
        });
        Queue<TreeNode> queue2 = new PriorityQueue<>((a, b) -> {
            return a.val - b.val;
        });

        queue1.add(root1);
        queue2.add(root2);

        while (!queue1.isEmpty() && !queue2.isEmpty()) {
            int size1 = queue1.size();
            int size2 = queue2.size();

            if (size1 != size2) {
                System.out.println(String.format("size1:%d, size2:%d", size1, size2));
                return false;
            }

            Queue<TreeNode> nextQueue1 = new PriorityQueue<>((a, b) -> {
                return a.val - b.val;
            });
            Queue<TreeNode> nextQueue2 = new PriorityQueue<>((a, b) -> {
                return a.val - b.val;
            });

            while (size1-- > 0) {
                TreeNode node1 = queue1.poll();
                TreeNode node2 = queue2.poll();

                if (node1.val != node2.val) {
                    System.out.println(String.format("val1:%d, val2:%d", node1.val, node2.val));
                    return false;
                }

                if (node1.left != null) nextQueue1.add(node1.left);
                if (node1.right != null) nextQueue1.add(node1.right);

                if (node2.right != null) nextQueue2.add(node2.right);
                if (node2.left != null) nextQueue2.add(node2.left);
            }

            queue1 = nextQueue1;
            queue2 = nextQueue2;
        }

        return queue1.isEmpty() && queue2.isEmpty();
    }
}