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
    public boolean isEvenOddTree(TreeNode root) {
        return mySol_dfs(root);
    }
    
    public boolean mySol_dfs(TreeNode root) {
        return dfs(root, 0, new HashMap());
    }
    
    public boolean dfs(TreeNode node, int level, Map<Integer, Integer> prevMap) {
        if (node == null) return true;
        
        int prev = prevMap.computeIfAbsent(level, k -> level % 2 == 0 ? 0 : Integer.MIN_VALUE);
        
        int sign = level % 2 == 0 ? 1 : -1;
        
        int signValue = node.val * sign;
        
        if (node.val % 2 == level % 2) return false;
        
        if (signValue <= prev) return false;
        
        prevMap.put(level, signValue);
        
        return dfs(node.left, level + 1, prevMap) && dfs(node.right, level + 1, prevMap);
    }
    
    public boolean mySol_bfs(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList();
        queue.add(root);
        
        int level = 0;
        
        while (!queue.isEmpty()) {
            int size = queue.size();
            int prev = level % 2 == 0 ? 0 : Integer.MIN_VALUE;
            int sign = level % 2 == 0 ? 1 : -1;
            
            while (size-- > 0) {
                TreeNode node = queue.poll();
                
                if (node.val % 2 == level % 2) {
                    return false;
                }
                
                int signValue = sign * node.val;
                
                if (signValue <= prev) {
                    return false;
                }
                
                prev = signValue;
                
                if (node.left != null) queue.add(node.left);
                if (node.right != null) queue.add(node.right);
            }
            
            level++;
        }
        
        return true;
    }
}