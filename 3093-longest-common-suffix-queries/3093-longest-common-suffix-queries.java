class Solution {
    public int[] stringIndices(String[] wordsContainer, String[] wordsQuery) {
        return mySol(wordsContainer, wordsQuery);
    }

    public int[] mySol(String[] wordsContainer, String[] wordsQuery) {
        int n = wordsContainer.length;
        int index = n;
        int smallestLength = 10000;

        for (int i = 0; i < n; i++) {
            if (smallestLength > wordsContainer[i].length()) {
                smallestLength = wordsContainer[i].length();
                index = i;
            }
        }

        // System.out.println("index:%d".formatted(index));

        Trie root = new Trie();

        for (int i = 0; i < n; i++) {
            String s = wordsContainer[i];
            String reversed = new StringBuilder(s).reverse().toString();

            root.build(reversed, i);
        }

        int[] ans = new int[wordsQuery.length];

        for (int i = 0; i < wordsQuery.length; i++) {
            String q = wordsQuery[i];
            String reversed = new StringBuilder(q).reverse().toString();

            int found = root.find(reversed);

            if (found < 0) {
                ans[i] = index;
            } else {
                ans[i] = found;
            }
        }

        return ans;
    }

    class Trie {
        Trie[] children = new Trie[26];
        TreeSet<int[]> set = new TreeSet<>((a, b) -> {
            // int[] : {length, index}
            return a[0] != b[0] ? a[0] - b[0] : a[1] - b[1];
        });

        private void build(String s, int index) {
            Trie t = this;

            for (char c : s.toCharArray()) {
                if (t.children[c - 'a'] == null) {
                    t.children[c - 'a'] = new Trie();
                }

                t.children[c - 'a'].set.add(new int[] {s.length(), index});

                t = t.children[c - 'a'];
            }
        }

        private int find(String s) {
            Trie t = this;

            int length = 0;

            for (char c : s.toCharArray()) {
                if (t.children[c - 'a'] == null) {
                    break;
                }

                t = t.children[c - 'a'];

                length++;
            }

            int found = t.set.isEmpty() ? -1 : t.set.first()[1];

            // for (int[] data : t.set) {
            //     System.out.println("\t%s".formatted(Arrays.toString(data)));
            // }

            // System.out.println("q:%s, length:%d, found:%d".formatted(s, length, found));

            return found;
        }
    }
}