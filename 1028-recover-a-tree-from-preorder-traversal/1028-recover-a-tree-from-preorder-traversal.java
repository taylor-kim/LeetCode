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
    public TreeNode recoverFromPreorder(String traversal) {
        return mySol(traversal);
    }

    public TreeNode mySol(String s) {
        if (s == null || s.length() == 0) return null;

        Queue<Integer> numbers = new LinkedList();
        Queue<Integer> depths = new LinkedList();

        int number = 0;
        int depth = 0;

        for (char c : s.toCharArray()) {
            if (Character.isDigit(c)) {
                if (depth != 0) {
                    depths.add(depth);
                    depth = 0;
                }

                number = number * 10 + (c - '0');
            } else {
                if (number != 0) {
                    numbers.add(number);
                    number = 0;
                }
                depth++;
            }
        }

        if (number != 0) {
            numbers.add(number);
        }

        // System.out.println(numbers);
        // System.out.println(depths);

        return dfs(numbers, depths, 0);
    }

    private TreeNode dfs(Queue<Integer> numbers, Queue<Integer> depths, int level) {
        if (numbers.isEmpty()) return null;

        TreeNode node = new TreeNode(numbers.poll());

        if (!depths.isEmpty() && depths.peek() == level + 1) {
            depths.poll();
            node.left = dfs(numbers, depths, level + 1);
        }

        if (!depths.isEmpty() && depths.peek() == level + 1) {
            depths.poll();
            node.right = dfs(numbers, depths, level + 1);
        }

        return node;
    }
}