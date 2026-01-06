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
        return mySol_bfs(root);
    }

    public int mySol_bfs(TreeNode root) {
        int max = Integer.MIN_VALUE;
        int ans = 0;

        Queue<TreeNode> queue = new LinkedList();
        queue.add(root);

        int level = 1;

        while (!queue.isEmpty()) {
            int size = queue.size();

            int levelSum = 0;

            while (size-- > 0) {
                TreeNode node = queue.poll();

                levelSum += node.val;

                if (node.left != null) queue.add(node.left);
                if (node.right != null) queue.add(node.right);
            }

            if (max < levelSum) {
                max = levelSum;
                ans = level;
            }

            level++;
        }

        return ans;
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