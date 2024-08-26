/*
// Definition for a Node.
class Node {
    public int val;
    public List<Node> children;

    public Node() {}

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, List<Node> _children) {
        val = _val;
        children = _children;
    }
};
*/

class Solution {
    public List<Integer> postorder(Node root) {
        return mySol_rec(root);
    }

    public List<Integer> mySol_rec(Node root) {
        List<Integer> list = new ArrayList();

        mySol_rec(root, list);

        return list;
    }

    public void mySol_rec(Node root, List<Integer> list) {
        if (root == null) return;

        if (root.children != null) {
            for (Node node : root.children) {
                mySol_rec(node, list);
            }
        }

        list.add(root.val);
    }
}