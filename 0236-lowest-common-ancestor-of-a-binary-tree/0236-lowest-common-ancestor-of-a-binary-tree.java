/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        return others_good(root, p, q);
    }

    public TreeNode others_good(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || root == p || root == q) return root;

        TreeNode left = others_good(root.left, p, q);
        TreeNode right = others_good(root.right, p, q);

        if (left != null && right != null) return root;

        return left != null ? left : right;
    }

    boolean foundP;
    boolean foundQ;

    Map<TreeNode, Integer> depths = new HashMap();
    Map<TreeNode, TreeNode> parents = new HashMap();

    public TreeNode try_20260613(TreeNode root, TreeNode p, TreeNode q) {
        build(root, root, p, q, 0);

        return search(p, q);
    }

    public void build(TreeNode parent, TreeNode node, TreeNode p, TreeNode q, int depth) {
        if (foundP && foundQ) return;

        if (node == null) return;

        depths.put(node, depth);
        parents.put(node, parent);

        if (node == p) {
            foundP = true;
        } else if (node == q) {
            foundQ = true;
        }

        build(node, node.left, p, q, depth + 1);
        build(node, node.right, p, q, depth + 1);
    }

    public TreeNode search(TreeNode p, TreeNode q) {
        if (p == q) return p;

        int depthP = depths.get(p);
        int depthQ = depths.get(q);

        if (depthP > depthQ) {
            return search(parents.get(p), q);
        } else if (depthP < depthQ) {
            return search(p, parents.get(q));
        } else {
            return p == q ? p : search(parents.get(p), parents.get(q));
        }
    }
}