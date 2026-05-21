class Solution {
    public int longestCommonPrefix(int[] arr1, int[] arr2) {
        return mySol(arr1, arr2);
    }

    public int mySol(int[] arr1, int[] arr2) {
        Trie root = new Trie();

        for (int num : arr1) {
            root.add(num);
        }

        int ans = 0;

        for (int num : arr2) {
            ans = Math.max(ans, root.find(num));
        }

        return ans;
    }

    class Trie {
        private Trie[] arr = new Trie[10];

        public void add(int n) {
            String s = String.valueOf(n);

            Trie t = this;

            for (char c : s.toCharArray()) {
                if (t.arr[c - '0'] == null) {
                    t.arr[c - '0'] = new Trie();
                }

                t = t.arr[c - '0'];
            }
        }

        public int find(int n) {
            String s = String.valueOf(n);

            int length = 0;

            for (Trie t = this; t != null && length < s.length() ; length++) {
                char c = s.charAt(length);

                if (t.arr[c - '0'] == null) {
                    return length;
                }

                t = t.arr[c - '0'];
            }

            return length;
        }
    }
}