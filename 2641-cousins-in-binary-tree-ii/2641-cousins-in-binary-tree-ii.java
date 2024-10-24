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
    public TreeNode replaceValueInTree(TreeNode root) {
        return official_onepass(root);
    }

    public TreeNode official_onepass(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList();
        
        queue.add(root);
        int currentLevelSum = root.val;

        while (!queue.isEmpty()) {
            int size = queue.size();
            int nextLevelSum = 0;

            while (size-- > 0) {
                TreeNode node = queue.poll();

                node.val = currentLevelSum - node.val;

                int childrenSum = (node.left != null ? node.left.val : 0) 
                                + (node.right != null ? node.right.val : 0);

                if (node.left != null) {
                    nextLevelSum += node.left.val;
                    node.left.val = childrenSum;

                    queue.add(node.left);
                }

                if (node.right != null) {
                    nextLevelSum += node.right.val;
                    node.right.val = childrenSum;

                    queue.add(node.right);
                }
            }

            currentLevelSum = nextLevelSum;
        }

        return root;
    }

    public TreeNode official_dfs(TreeNode root) {
        Map<Integer, Integer> map = new HashMap();
        calc_levelSum(root, 0, map);
        replace(root, 0, 0, map);

        return root;
    }

    private void calc_levelSum(TreeNode root, int level, Map<Integer, Integer> map) {
        if (root == null) return;

        map.put(level, map.getOrDefault(level, 0) + root.val);

        calc_levelSum(root.left, level + 1, map);
        calc_levelSum(root.right, level + 1, map);
    }

    private void replace(TreeNode node, int level, int siblingVal, Map<Integer, Integer> map) {
        if (node == null) return;

        int leftVal = node.left != null ? node.left.val : 0;
        int rightVal = node.right != null ? node.right.val : 0;

        if (level <= 1) {
            node.val = 0;
        } else {
            node.val = map.get(level) - node.val - siblingVal;
        }

        replace(node.left, level + 1, rightVal, map);
        replace(node.right, level + 1, leftVal, map);
    }

    public TreeNode mySol(TreeNode root) {
        Map<Integer, Integer> levelSum = new HashMap();

        Queue<TreeNode> queue = new LinkedList();
        queue.add(root);

        int level = 0;

        while (!queue.isEmpty()) {
            int size = queue.size();

            while (size-- > 0) {
                TreeNode node = queue.poll();

                levelSum.put(level, levelSum.getOrDefault(level, 0) + node.val);

                if (node.left != null) queue.add(node.left);
                if (node.right != null) queue.add(node.right);
            }

            level++;
        }

        queue.add(root);
        level = 0;

        while (!queue.isEmpty()) {
            int size = queue.size();

            while (size-- > 0) {
                TreeNode node = queue.poll();

                int childSum = 0;

                if (node.left != null) {
                    childSum += node.left.val;
                    queue.add(node.left);
                }
                if (node.right != null) {
                    childSum += node.right.val;
                    queue.add(node.right);
                }

                if (node.left != null) {
                    node.left.val = levelSum.getOrDefault(level + 1, 0) - childSum;
                }
                if (node.right != null) {
                    node.right.val = levelSum.getOrDefault(level + 1, 0) - childSum;
                }
            }

            level++;
        }

        root.val = 0;

        return root;
    }
}