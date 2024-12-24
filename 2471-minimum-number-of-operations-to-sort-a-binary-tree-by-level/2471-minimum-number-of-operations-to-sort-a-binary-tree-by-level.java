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
    public int minimumOperations(TreeNode root) {
        return myBfs(root);
    }

    public int myBfs(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList();
        queue.add(root);

        int ans = 0;

        while (!queue.isEmpty()) {
            int size = queue.size();

            List<Integer> list = new ArrayList();

            while (size-- > 0) {
                TreeNode node = queue.poll();

                list.add(node.val);

                if (node.left != null) queue.add(node.left);
                if (node.right != null) queue.add(node.right);
            }

            List<Integer> sorted = new ArrayList(list);

            Collections.sort(sorted);

            int delta = 0;

            for (int i = 0; i < list.size(); i++) {
                if (list.get(i) != sorted.get(i)) delta++;
            }

            // System.out.println(String.format("delta:%d", delta));

            ans += delta / 2 + (delta % 2);
        }

        return ans;
    }
}