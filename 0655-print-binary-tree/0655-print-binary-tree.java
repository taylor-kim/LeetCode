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
    public List<List<String>> printTree(TreeNode root) {
        return mySol(root);
    }
    
    public List<List<String>> mySol(TreeNode root) {
        List<List<String>> ans = new ArrayList();
        int m = height(root) - 1;
        int n = (int)Math.pow(2, m + 1) - 1;
        
        int column = (n - 1) / 2;
        
        dfs(root, 0, column, m, n, ans);
        
        return ans;
    }
    
    private void dfs(TreeNode node, int r, int c, int m, int n, List<List<String>> list) {
        if (node == null) return;
        
        if (list.size() == r) {
            list.add(new ArrayList(Collections.nCopies(n, "")));
        }
        
        List<String> row = list.get(r);
        row.set(c, node.val + "");
        
        int delta = (int)Math.pow(2, m - r - 1);
        
        dfs(node.left, r + 1, c - delta, m, n, list);
        dfs(node.right, r + 1, c + delta, m, n, list);
    }
    
    private int height(TreeNode node) {
        if (node == null) return 0;
        
        return Math.max(height(node.left), height(node.right)) + 1;
    }
}