class Solution {
    public int longestCommonPrefix(int[] arr1, int[] arr2) {
        return mySol(arr1, arr2);
    }

    public int mySol(int[] arr1, int[] arr2) {
        Trie root = new Trie();

        for (int num : arr1) root.build(num);

        int ans = 0;

        for (int num : arr2) ans = Math.max(ans, root.find(num));

        return ans;
    }

    private class Trie {
        private Trie[] children = new Trie[10];
        
        private void build(int num) {
            Trie t = this;

            char[] arr = String.valueOf(num).toCharArray();

            for (char c : arr) {
                if (t.children[c - '0'] == null) {
                    t.children[c - '0'] = new Trie();
                }

                t = t.children[c - '0'];
            }
        }

        private int find(int num) {
            Trie t = this;
            char[] arr = String.valueOf(num).toCharArray();

            for (int i = 0; i < arr.length; i++) {
                char c = arr[i];
                if (t.children[c - '0'] == null) return i;

                t = t.children[c - '0'];
            }

            return arr.length;
        }
    }
}