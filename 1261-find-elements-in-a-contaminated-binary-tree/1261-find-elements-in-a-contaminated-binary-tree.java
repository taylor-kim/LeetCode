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
class FindElements {
    private List<Integer> list;
    private Set<Integer> set = new HashSet();

    public FindElements(TreeNode root) {
        // list = new ArrayList();
        // recoverBfs(root, 0);

        recoverDfs_official(root, 0);
    }

    private void recoverDfs_official(TreeNode node, int val) {
        if (node == null) return;

        set.add(val);

        recoverDfs_official(node.left, val * 2 + 1);
        recoverDfs_official(node.right, val * 2 + 2);
    }

    private void recoverBfs(TreeNode root, int val) {
        Queue<TreeNode> queue = new LinkedList();
        root.val = 0;
        queue.add(root);

        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();

            list.add(node.val);

            if (node.left != null) {
                node.left.val = node.val * 2 + 1;
                queue.add(node.left);
            }

            if (node.right != null) {
                node.right.val = node.val * 2 + 2;
                queue.add(node.right);
            }
        }
    }

    private void recover(TreeNode node, int val) {
        if (node == null) return;

        node.val = val;

        recover(node.left, val * 2 + 1);
        recover(node.right, val * 2 + 2);
    }
    
    public boolean find(int target) {
        // return Collections.binarySearch(list, target) >= 0;
        return set.contains(target);
    }

    private boolean find(TreeNode node, int target) {
        if (node == null) return false;

        if (node.val == target) return true;

        return find(node.left, target) || find(node.right, target);
    }
}

/**
 * Your FindElements object will be instantiated and called as such:
 * FindElements obj = new FindElements(root);
 * boolean param_1 = obj.find(target);
 */