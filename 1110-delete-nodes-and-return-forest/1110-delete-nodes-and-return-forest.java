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
    public List<TreeNode> delNodes(TreeNode root, int[] to_delete) {
        return official_dfs(root, to_delete);
    }

    public List<TreeNode> official_dfs(TreeNode root, int[] to_delete) {
        Set<Integer> delete = new HashSet();

        for (int val : to_delete) delete.add(val);

        List<TreeNode> result = new ArrayList();

        root = official_dfs(root, delete, result);

        if (root != null) result.add(root);

        return result;
    }

    public TreeNode official_dfs(TreeNode node, Set<Integer> delete, List<TreeNode> result) {
        if(node == null) return null;

        node.left = official_dfs(node.left, delete, result);
        node.right = official_dfs(node.right, delete, result);

        if (delete.contains(node.val)) {
            if (node.left != null) {
                result.add(node.left);
            }

            if (node.right != null) {
                result.add(node.right);
            }

            return null;
        } else {
            return node;
        }
    }

    public List<TreeNode> mySol(TreeNode root, int[] to_delete) {
        Set<Integer> delete = new HashSet();

        for (int val : to_delete) delete.add(val);

        List<TreeNode> result = new ArrayList();

        mySol(root, delete, result, true);

        return result;
    }

    public TreeNode mySol(TreeNode root, Set<Integer> delete, List<TreeNode> result, boolean isRoot) {
        if (root == null) return null;

        if (delete.contains(root.val)) {
            mySol(root.left, delete, result, true);
            mySol(root.right, delete, result, true);

            return null;
        }

        if (isRoot) {
            result.add(root);
        }

        root.left = mySol(root.left, delete, result, false);
        root.right = mySol(root.right, delete, result, false);

        return root;
    }
}