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
    public int maxLevelSum(TreeNode root) {
        return mySol(root);
    }

    public int mySol(TreeNode root) {
        List<Integer> levelSum = new ArrayList();

        dfs(root, levelSum, 0);

        int max = Integer.MIN_VALUE;
        int level = 0;

        for (int i = 0; i < levelSum.size(); i++) {
            if (max < levelSum.get(i)) {
                max = levelSum.get(i);
                level = i;
            }
        }

        return level + 1;
    }

    public void dfs(TreeNode root, List<Integer> levelSum, int level) {
        if (root == null) return;

        if (levelSum.size() == level) {
            levelSum.add(root.val);
        } else {
            levelSum.set(level, levelSum.get(level) + root.val);
        }

        dfs(root.left, levelSum, level + 1);
        dfs(root.right, levelSum, level + 1);
    }
}