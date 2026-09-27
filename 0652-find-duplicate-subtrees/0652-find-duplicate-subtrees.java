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
    public List<TreeNode> findDuplicateSubtrees(TreeNode root) {
        return mySol3(root);
    }

    public List<TreeNode> mySol3(TreeNode root) {
        Map<String, TreeNode> map = new HashMap();
        Set<TreeNode> set = new HashSet();

        dfs2(root, map, set);

        // System.out.println(set);

        // List<TreeNode> ans = new ArrayList();

        // // for (String key : keys) {
        // //     ans.add(map.get(key));
        // // }

        return new ArrayList(set);
    }

    private String dfs2(TreeNode node, Map<String, TreeNode> map, Set<TreeNode> set) {
        if (node == null) return "";

        String l = dfs2(node.left, map, set);
        String r = dfs2(node.right, map, set);

        String key = "%d:l%s:r%s".formatted(node.val, l, r);

        if (map.containsKey(key)) {
            set.add(map.get(key));
        } else {
            map.put(key, node);
        }

        return key;
    }

    public List<TreeNode> mySol2_fail(TreeNode root) {
        Map<String, TreeNode> map = new HashMap();
        Set<String> set = new HashSet();

        dfs(root, map, set);

        List<TreeNode> ans = new ArrayList();

        for (String key : set) {
            System.out.println(key);
            ans.add(map.get(key));
        }

        // for (String key : map.keySet()) {
        //     System.out.println(key);
        // }

        return ans;
    }

    private void dfs(TreeNode root, Map<String, TreeNode> map, Set<String> ans) {
        if (root == null) {
            return;
        }

        dfs(root, root.left, root.val + ":l", map, ans);
        dfs(root, root.right, root.val + ":r", map, ans);
    }

    private void dfs(TreeNode root, TreeNode node, String s, Map<String, TreeNode> map, Set<String> ans) {
        if (node == null) {
            if (map.containsKey(s)) {
                ans.add(s);
            } else {
                map.put(s, root);
            }
            return;
        }

        dfs(root, node.left, s + node.val + ":l", map, ans);
        dfs(root, node.right, s + node.val + ":r", map, ans);

        dfs(node, map, ans);
    }

    public List<TreeNode> mySol_fail(TreeNode root) {
        Trie t = new Trie();
        t.add(root);

        return null;
    }

    class Trie {
        Trie[] children = new Trie[401];
        int[] count = new int[401];
        TreeNode node;

        public void add(TreeNode node) {
            if (node == null) return;

            Trie t = this;

            int val = node.val + 200;

            if (t.children[val] == null) {
                t.children[val] = new Trie();
                t.count[val]++;
                t.node = node;
            }

            t = t.children[val];

            t.add(node.left);
            t.add(node.right);
        }
    }
}