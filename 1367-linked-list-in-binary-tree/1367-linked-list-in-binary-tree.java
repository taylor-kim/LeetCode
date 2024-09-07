/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
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
    public boolean isSubPath(ListNode head, TreeNode root) {
        return try_iter_hint_by_official(head, root);
    }

    public boolean try_iter_hint_by_official(ListNode head, TreeNode root) {
        Stack<TreeNode> stack = new Stack();
        stack.push(root);

        while (!stack.isEmpty()) {
            TreeNode tree = stack.pop();

            if (isMatch(tree, head)) return true;

            if (tree.left != null) stack.push(tree.left);

            if (tree.right != null) stack.push(tree.right);
        }

        return false;
    }
    
    private boolean isMatch(TreeNode tree, ListNode head) {
        Queue<Pair<TreeNode, ListNode>> queue = new LinkedList();
        queue.add(new Pair(tree, head));

        while (!queue.isEmpty()) {
            TreeNode t = queue.peek().getKey();
            ListNode list = queue.poll().getValue();

            if (list == null) return true;

            if (t == null) continue;

            if (t.val == list.val) {
                queue.add(new Pair(t.left, list.next));
                queue.add(new Pair(t.right, list.next));
            }
        }

        return false;
    }

    public boolean try_iter_fail(ListNode head, TreeNode root) {
        Queue<Pair<TreeNode, ListNode>> queue = new LinkedList();
        queue.add(new Pair(root, head));

        while (!queue.isEmpty()) {
            Queue<Pair<TreeNode, ListNode>> nextQueue = new LinkedList();

            while (!queue.isEmpty()) {
                TreeNode tree = queue.peek().getKey();
                ListNode list = queue.poll().getValue();

                if (list == null) return true;

                if (tree == null) continue;

                if (tree.val == list.val) {
                    // System.out.println(String.format("t.val:%d, list.val:%d", tree.val, list.val));

                    queue.add(new Pair(tree.left, list.next));
                    queue.add(new Pair(tree.right, list.next));
                }

                nextQueue.add(new Pair(tree.left, head));
                nextQueue.add(new Pair(tree.right, head));
            }

            queue = nextQueue;
        }

        return false;
    }

    public boolean mySol_simple(ListNode head, TreeNode root) {
        return dfs(head, root);
    }

    private boolean dfs(ListNode head, TreeNode root) {
        if (root == null) return false;

        return find(head, root) || dfs(head, root.left) || dfs(head, root.right);
    }

    private boolean find(ListNode head, TreeNode root) {
        if (head == null) return true;

        if (root == null) return false;

        if (head.val != root.val) return false;

        return find(head.next, root.left) || find(head.next, root.right);
    }

    public boolean mySol_mle(ListNode head, TreeNode root) {
        Trie t = new Trie();

        buildTrie(t, root);

        return t.find(head);
    }

    private void buildTrie(Trie t, TreeNode root) {
        if (root == null) return;

        t.buildTrie(root);

        buildTrie(t, root.left);
        buildTrie(t, root.right);
    }

    private class Trie {
        // Trie[] data = new Trie[101];
        Map<Integer, Trie> data = new HashMap();


        public void buildTrie(TreeNode node) {
            if (node == null) {
                return;
            }

            if (!data.containsKey(node.val)) {
                // data[node.val] = new Trie();
                data.put(node.val, new Trie());
            }

            data.get(node.val).buildTrie(node.left);
            data.get(node.val).buildTrie(node.right);
        }

        public boolean find(ListNode node) {
            Trie t = this;

            while (t != null && node != null) {
                t = t.data.get(node.val);
                node = node.next;
            }

            return t != null && node == null;
        }
    }
}