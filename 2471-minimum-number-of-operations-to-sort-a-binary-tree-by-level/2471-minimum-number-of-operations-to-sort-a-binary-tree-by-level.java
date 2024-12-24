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
    public int minimumOperations(TreeNode root) {
        return myBfs(root);
    }

    public int myBfs(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList();
        queue.add(root);

        int ans = 0;

        while (!queue.isEmpty()) {
            int size = queue.size();

            List<Integer> list = new ArrayList();

            while (size-- > 0) {
                TreeNode node = queue.poll();

                list.add(node.val);

                if (node.left != null) queue.add(node.left);
                if (node.right != null) queue.add(node.right);
            }

            ans += getSwaps(list);
        }

        return ans;
    }

    private int getSwaps(List<Integer> list) {
        Set<Integer> set = new TreeSet();
        Map<Integer, Integer> map = new HashMap();

        for (int i = 0; i < list.size(); i++) {
            set.add(list.get(i));
            map.put(list.get(i), i);
        }

        int[] arr = new int[list.size()];

        int value = 0;

        for (int num : set) {
            arr[map.get(num)] = value++;
        }

        // System.out.println(Arrays.toString(arr));

        int swap = 0;

        for (int i = 0; i < arr.length; i++) {
            while (arr[i] != i) {
                int temp = arr[arr[i]];
                arr[arr[i]] = arr[i];
                arr[i] = temp;

                swap++;
            }
        }

        return swap;
    }
}