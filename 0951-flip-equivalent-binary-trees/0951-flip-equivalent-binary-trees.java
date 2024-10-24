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
    public boolean flipEquiv(TreeNode root1, TreeNode root2) {
        return official_findCanonicalForm(root1, root2);
    }

    public boolean official_findCanonicalForm(TreeNode root1, TreeNode root2) {
        findCanonicalForm(root1);
        findCanonicalForm(root2);
        return areEquivalent(root1, root2);
    }

    public void findCanonicalForm(TreeNode root) {
        if (root == null) return;

        findCanonicalForm(root.left);
        findCanonicalForm(root.right);

        if (root.right == null) {
            return;
        }

        if (root.left == null) {
            root.left = root.right;
            root.right = null;
            return;
        }

        TreeNode left = root.left;
        TreeNode right = root.right;

        if (root.left.val > root.right.val) {
            root.left = right;
            root.right = left;
        }
    }

    public boolean areEquivalent(TreeNode root1, TreeNode root2) {
        if (root1 == null && root2 == null) return true;
        if (root1 == null || root2 == null || root1.val != root2.val) return false;

        return areEquivalent(root1.left, root2.left) && areEquivalent(root1.right, root2.right);
    }

    public boolean official_iterative_dfs(TreeNode root1, TreeNode root2) {
        Stack<TreeNode[]> stack = new Stack();

        if (!match(root1, root2)) return false;

        stack.push(new TreeNode[] {root1, root2});

        while (!stack.isEmpty()) {
            TreeNode[] nodes = stack.pop();

            TreeNode node1 = nodes[0];
            TreeNode node2 = nodes[1];

            if (node1 == null && node2 == null) continue;

            // if (node1 == null || node2 == null) return false;

            // if (node1.val != node2.val) return false;

            if (match(node1.left, node2.left) && match(node1.right, node2.right)) {
                stack.push(new TreeNode[] {node1.left, node2.left});
                stack.push(new TreeNode[] {node1.right, node2.right});
            } else if (match(node1.left, node2.right) && match(node1.right, node2.left)) {
                stack.push(new TreeNode[] {node1.left, node2.right});
                stack.push(new TreeNode[] {node1.right, node2.left});
            } else {
                return false;
            }
        }

        return true;
    }

    private boolean match(TreeNode node1, TreeNode node2) {
        if (node1 == null && node2 == null) return true;

        if (node1 == null || node2 == null) return false;

        return node1.val == node2.val;
    }

    public boolean official_topdown(TreeNode root1, TreeNode root2) {
        if (root1 == null && root2 == null) return true;
        if (root1 == null || root2 == null) return false;

        if (root1.val != root2.val) return false;

        boolean noChange = official_topdown(root1.left, root2.left) && official_topdown(root1.right, root2.right);

        boolean change = official_topdown(root1.left, root2.right) && official_topdown(root1.right, root2.left);

        return change || noChange;
    }

    public boolean mySol2(TreeNode root1, TreeNode root2) {
        if (root1 == null && root2 == null) return true;
        if (root1 == null || root2 == null) return false;

        Queue<TreeNode> queue1 = new LinkedList();
        Queue<TreeNode> queue2 = new LinkedList();

        queue1.add(root1);
        queue2.add(root2);

        while (!queue1.isEmpty() && !queue2.isEmpty()) {
            int size1 = queue1.size();
            int size2 = queue2.size();

            if (size1 != size2) {
                // System.out.println(String.format("size1:%d, size2:%d", size1, size2));
                return false;
            }

            while (size1-- > 0) {
                TreeNode node1 = queue1.poll();
                TreeNode node2 = queue2.poll();

                if (node1.val != node2.val) {
                    // System.out.println(String.format("val1:%d, val2:%d", node1.val, node2.val));
                    return false;
                }

                Queue<TreeNode> pq = new PriorityQueue<>((a, b) -> {
                    return a.val - b.val;
                });

                if (node1.left != null) pq.add(node1.left);
                if (node1.right != null) pq.add(node1.right);
                if (node2.left != null) pq.add(node2.left);
                if (node2.right != null) pq.add(node2.right);

                int nextSize = pq.size();

                if (nextSize % 2 != 0) return false;

                while (nextSize > 0) {
                    if (nextSize % 2 == 0) {
                        queue1.add(pq.poll());
                    } else {
                        queue2.add(pq.poll());
                    }
                    nextSize--;
                }
            }
        }

        return queue1.isEmpty() && queue2.isEmpty();
    }

    public boolean mySol_fail(TreeNode root1, TreeNode root2) {
        if (root1 == null && root2 == null) return true;
        if (root1 == null || root2 == null) return false;

        Queue<TreeNode> queue1 = new PriorityQueue<>((a, b) -> {
            return a.val - b.val;
        });
        Queue<TreeNode> queue2 = new PriorityQueue<>((a, b) -> {
            return a.val - b.val;
        });

        queue1.add(root1);
        queue2.add(root2);

        while (!queue1.isEmpty() && !queue2.isEmpty()) {
            int size1 = queue1.size();
            int size2 = queue2.size();

            if (size1 != size2) {
                System.out.println(String.format("size1:%d, size2:%d", size1, size2));
                return false;
            }

            Queue<TreeNode> nextQueue1 = new PriorityQueue<>((a, b) -> {
                return a.val - b.val;
            });
            Queue<TreeNode> nextQueue2 = new PriorityQueue<>((a, b) -> {
                return a.val - b.val;
            });

            while (size1-- > 0) {
                TreeNode node1 = queue1.poll();
                TreeNode node2 = queue2.poll();

                if (node1.val != node2.val) {
                    System.out.println(String.format("val1:%d, val2:%d", node1.val, node2.val));
                    return false;
                }

                if (node1.left != null) nextQueue1.add(node1.left);
                if (node1.right != null) nextQueue1.add(node1.right);

                if (node2.right != null) nextQueue2.add(node2.right);
                if (node2.left != null) nextQueue2.add(node2.left);
            }

            queue1 = nextQueue1;
            queue2 = nextQueue2;
        }

        return queue1.isEmpty() && queue2.isEmpty();
    }
}