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
    public TreeNode reverseOddLevels(TreeNode root) {
        return editorial_dfs(root);
    }

    public TreeNode editorial_dfs(TreeNode root) {
        dfs(root.left, root.right, 1);

        return root;
    }

    private void dfs(TreeNode left, TreeNode right, int level) {
        if (left == null || right == null) return;

        if (level % 2 == 1) {
            left.val += right.val;
            right.val = left.val - right.val;
            left.val -= right.val;
        }

        dfs(left.left, right.right, level + 1);
        dfs(left.right, right.left, level + 1);
    }

    public TreeNode mySol(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList();
        List<TreeNode> list = new ArrayList();

        int level = 0;

        queue.add(root);

        while (!queue.isEmpty()) {
            int size = queue.size();

            boolean oddLevel = level % 2 == 1;

            while (size-- > 0) {
                TreeNode node = queue.poll();

                if (oddLevel) {
                    list.add(node);
                }

                if (node.left != null) {
                    queue.add(node.left);
                }

                if (node.right != null) {
                    queue.add(node.right);
                }
            }

            for (int i = 0; i < list.size() / 2; i++) {
                TreeNode one = list.get(i);
                TreeNode opposite = list.get(list.size() - 1 - i);

                one.val += opposite.val;
                opposite.val = one.val - opposite.val;
                one.val -= opposite.val;
            }

            list.clear();

            level++;
        }

        return root;
    }
}