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
    public int findBottomLeftValue(TreeNode root) {
        return official_bfs(root);
    }
    
    public int official_bfs(TreeNode root) {
        int ans = 0;
        
        Queue<TreeNode> queue = new LinkedList();
        queue.add(root);
        TreeNode node = null;
        
        while (!queue.isEmpty()) {            
            node = queue.poll();
            
            if (node.right != null) {
                queue.add(node.right);
            }
                
            if (node.left != null) {
                queue.add(node.left);
            }
        }
        
        return node.val;
    }
    
    public int mySol_inorder_stack(TreeNode root) {
        int ans = 0;
        int lastRow = -1;
        
        Stack<Pair<TreeNode, Integer>> stack = new Stack();
        Pair<TreeNode, Integer> pair = new Pair(root, 0);
        
        while (pair != null || !stack.isEmpty()) {
            while (pair != null && pair.getKey() != null) {
                TreeNode node = pair.getKey();
                int depth = pair.getValue();
                
                // if (depth > lastRow) {
                //     ans = node.val;
                //     lastRow = depth;
                // }
                
                stack.push(pair);
                
                if (node.left != null) {
                    pair = new Pair(node.left, depth + 1);
                } else {
                    pair = null;
                }
            }
            
            pair = stack.pop();
            
            if (pair.getValue() > lastRow) {
                ans = pair.getKey().val;
                lastRow = pair.getValue();
            }
            
            if (pair.getKey().right != null) {
                pair = new Pair(pair.getKey().right, pair.getValue() + 1);
            } else {
                pair = null;
            }
        }
        
        return ans;
    }
    
    int ans = 0;
    int lastRow = -1;
    
    public int mySol_dfs(TreeNode root) {
        mySol_dfs(root, 0);
        
        return ans;
    }
    
    public void mySol_dfs(TreeNode root, int depth) {
        if (root == null) return;
        
        if (depth > lastRow) {
            ans = root.val;
            lastRow = depth;
        }
        
        mySol_dfs(root.left, depth + 1);
        mySol_dfs(root.right, depth + 1);
    }
    
    public int mySol_bfs(TreeNode root) {
        int ans = 0;
        
        Queue<TreeNode> queue = new LinkedList();
        queue.add(root);
        
        while (!queue.isEmpty()) {
            int size = queue.size();
            
            if (size != 0) {
                ans = queue.peek().val;
            }
            
            while (size-- > 0) {
                TreeNode node = queue.poll();
                
                if (node.left != null) {
                    queue.add(node.left);
                }
                
                if (node.right != null) {
                    queue.add(node.right);
                }
            }
        }
        
        return ans;
    }
}