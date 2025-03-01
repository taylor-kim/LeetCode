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
        return try_iterative_list(traversal);
    }

    public TreeNode try_iterative_list(String s) {
        List<TreeNode> levels = new ArrayList();

        int n = s.length();
        int index = 0;

        while (index < n) {
            int dash = 0;

            while (index < n && s.charAt(index) == '-') {
                dash++;
                index++;
            }

            int number = 0;

            while (index < n && Character.isDigit(s.charAt(index))) {
                number = number * 10 + ((int)s.charAt(index++) - '0');
            }

            TreeNode node = new TreeNode(number);

            if (dash < levels.size()) {
                levels.set(dash, node);
            } else {
                levels.add(node);
            }

            if (dash > 0) {
                TreeNode parent = levels.get(dash - 1);

                if (parent.left == null) {
                    parent.left = node;
                } else {
                    parent.right = node;
                }
            }
        }

        return levels.get(0);
    }

    public TreeNode try_iterative_stack(String s) {
        Stack<TreeNode> stack = new Stack();

        int n = s.length();
        int index = 0;

        while (index < n) {
            int dash = 0;

            while (index < n && s.charAt(index) == '-') {
                dash++;
                index++;
            }

            int number = 0;

            while (index < n && Character.isDigit(s.charAt(index))) {
                number = number * 10 + ((int)s.charAt(index++) - '0');
            }

            TreeNode node = new TreeNode(number);

            while (stack.size() > dash) {
                stack.pop();
            }

            if (stack.size() > 0) {
                TreeNode parent = stack.peek();

                if (parent.left == null) {
                    parent.left = node;
                } else {
                    parent.right = node;
                }
            }

            stack.push(node);
        }

        while (stack.size() > 1) {
            stack.pop();
        }

        return stack.peek();
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