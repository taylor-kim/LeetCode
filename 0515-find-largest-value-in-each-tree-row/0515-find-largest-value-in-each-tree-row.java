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
    public List<Integer> largestValues(TreeNode root) {
        return mySol_bfs(root);
    }

    public List<Integer> mySol_bfs(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList();

        List<Integer> ans = new ArrayList();

        if (root == null) return ans;
        
        queue.add(root);

        while (!queue.isEmpty()) {
            int size = queue.size();
            int max = Integer.MIN_VALUE;

            while (size-- > 0) {
                TreeNode node = queue.poll();

                max = Math.max(max, node.val);

                if (node.left != null) queue.add(node.left);
                if (node.right != null) queue.add(node.right);
            }

            ans.add(max);
        }

        return ans;
    }
}