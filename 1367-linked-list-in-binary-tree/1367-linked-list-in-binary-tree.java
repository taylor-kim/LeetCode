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
        return mySol_simple(head, root);
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