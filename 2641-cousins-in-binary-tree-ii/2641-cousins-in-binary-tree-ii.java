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
    public TreeNode replaceValueInTree(TreeNode root) {
        return mySol(root);
    }

    public TreeNode mySol(TreeNode root) {
        Map<Integer, Integer> levelSum = new HashMap();

        Queue<TreeNode> queue = new LinkedList();
        queue.add(root);

        int level = 0;

        while (!queue.isEmpty()) {
            int size = queue.size();

            while (size-- > 0) {
                TreeNode node = queue.poll();

                levelSum.put(level, levelSum.getOrDefault(level, 0) + node.val);

                if (node.left != null) queue.add(node.left);
                if (node.right != null) queue.add(node.right);
            }

            level++;
        }

        queue.add(root);
        level = 0;

        while (!queue.isEmpty()) {
            int size = queue.size();

            while (size-- > 0) {
                TreeNode node = queue.poll();

                int childSum = 0;

                if (node.left != null) {
                    childSum += node.left.val;
                    queue.add(node.left);
                }
                if (node.right != null) {
                    childSum += node.right.val;
                    queue.add(node.right);
                }

                if (node.left != null) {
                    node.left.val = levelSum.getOrDefault(level + 1, 0) - childSum;
                }
                if (node.right != null) {
                    node.right.val = levelSum.getOrDefault(level + 1, 0) - childSum;
                }
            }

            level++;
        }

        root.val = 0;

        return root;
    }
}