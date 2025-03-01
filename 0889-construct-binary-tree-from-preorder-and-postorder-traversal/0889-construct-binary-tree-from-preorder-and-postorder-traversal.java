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
    public TreeNode constructFromPrePost(int[] preorder, int[] postorder) {
        return official_dfs(preorder, postorder);
    }

    public TreeNode official_dfs(int[] preorder, int[] postorder) {
        return official_dfs(preorder, new int[1], postorder, new int[1]);
    }

    public TreeNode official_dfs(int[] preorder, int[] index, int[] postorder, int[] index2) {
        if (index[0] >= preorder.length) return null;

        TreeNode node = new TreeNode(preorder[index[0]++]);

        if (node.val != postorder[index2[0]]) {
            node.left = official_dfs(preorder, index, postorder, index2);
        }

        if (node.val != postorder[index2[0]]) {
            node.right = official_dfs(preorder, index, postorder, index2);
        }

        index2[0]++;

        return node;
    }

    public TreeNode mySol(int[] preorder, int[] postorder) {
        return dfs(preorder, new int[1], postorder, new int[1], new HashSet());
    }

    public TreeNode dfs(int[] preorder, int[] index, int[] postorder, int[] index2, Set<Integer> set) {
        if (index[0] >= preorder.length) return null;

        TreeNode node = new TreeNode(preorder[index[0]++]);

        set.add(node.val);

        if (node.val == postorder[index2[0]]) {
            index2[0]++;
            return node;
        }

        node.left = dfs(preorder, index, postorder, index2, set);

        if (!set.contains(postorder[index2[0]])) {
            node.right = dfs(preorder, index, postorder, index2, set);
        }

        index2[0]++;

        return node;
    }
}