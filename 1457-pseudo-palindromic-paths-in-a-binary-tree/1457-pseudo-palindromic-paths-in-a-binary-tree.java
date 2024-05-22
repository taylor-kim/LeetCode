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
    public int pseudoPalindromicPaths (TreeNode root) {
        return official_dfs(root);
    }

    public int official_dfs(TreeNode root) {
        return official_dfs(root, 0);
    }

    public int official_dfs(TreeNode root, int path) {
        if (root == null) return 0;

        path = path ^ (1 << root.val);

        int ans = 0;

        if (root.left == null && root.right == null) {
            ans = (path & (path - 1)) == 0 ? 1 : 0;
        } else {
            ans = official_dfs(root.left, path) + official_dfs(root.right, path);
        }

        return ans;
    }

    public int mySol(TreeNode root) {
        return mySol(root, new HashSet());
    }

    public int mySol(TreeNode root, Set<Integer> set) {
        if (root == null) return 0;

        if (set.contains(root.val)) {
            set.remove(root.val);
        } else {
            set.add(root.val);
        }

        int ans = 0;

        // if (root.left == null && root.right == null) {
        //     ans = set.size() <= 1 ? 1 : 0;
        // } else if (root.left == null) {
        //     ans = mySol(root.right, set);
        // } else if (root.right == null ){
        //     ans = mySol(root.left, set);
        // } else {
        //     ans = mySol(root.left, set) + mySol(root.right, set);
        // }

        if (root.left == null && root.right == null) {
            ans = set.size() <= 1 ? 1 : 0;
        } else {
            ans = mySol(root.left, set) + mySol(root.right, set);
        }

        if (set.contains(root.val)) {
            set.remove(root.val);
        } else {
            set.add(root.val);
        }

        return ans;
    }
}