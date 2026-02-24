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
    public int sumRootToLeaf(TreeNode root) {
        return bfs(root);
    }

    public int mySol(TreeNode root) {
        return topdown(root, 0, 0);
    }

    public int bfs(TreeNode root) {
        int sum = 0;

        Queue<Object[]> queue = new LinkedList();
        queue.add(new Object[] {root, 0});

        while (!queue.isEmpty()) {
            TreeNode node = (TreeNode)queue.peek()[0];
            int val = (int)queue.poll()[1];

            int next = (val << 1) | node.val;

            if (node.left == null && node.right == null) {
                sum += next;
            }

            if (node.left != null) {
                queue.add(new Object[] {node.left, next});
            }

            if (node.right != null) {
                queue.add(new Object[] {node.right, next});
            }
        }

        return sum;
    }

    public int topdown(TreeNode root, int val, int depth) {
        if (root == null) return 0;

        if (root.left == null && root.right == null) {
            // System.out.println("depth:%d".formatted(depth));
            return (val << 1) | root.val;
        }

        int left = topdown(root.left, (val << 1) | root.val, depth + 1);
        int right = topdown(root.right, (val << 1) | root.val, depth + 1);

        // System.out.println("left:" + Integer.toBinaryString(left));
        // System.out.println("right:" + Integer.toBinaryString(right));

        return left + right;
    }
}