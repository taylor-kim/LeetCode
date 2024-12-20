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
        return mySol(root);
    }

    public TreeNode mySol(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList();
        Stack<TreeNode> stack = new Stack();
        Set<TreeNode> set = new HashSet();

        int level = 0;

        queue.add(root);

        while (!queue.isEmpty()) {
            int size = queue.size();

            boolean oddLevel = level % 2 == 1;

            while (size-- > 0) {
                TreeNode node = queue.poll();

                if (oddLevel) {
                    TreeNode opposite = stack.pop();

                    if (set.add(opposite) && set.add(node)) {
                        node.val += opposite.val;
                        opposite.val = node.val - opposite.val;
                        node.val -= opposite.val;
                    }
                }

                if (node.left != null) {
                    if (!oddLevel) {
                        stack.push(node.left);
                    }

                    queue.add(node.left);
                }

                if (node.right != null) {
                    if (!oddLevel) {
                        stack.push(node.right);
                    }

                    queue.add(node.right);
                }
            }

            if (oddLevel) {
                stack.clear();
                set.clear();
            }

            level++;
        }

        return root;
    }
}