class Solution {
    public String longestSubsequenceRepeatedK(String s, int k) {
        return official(s, k);
    }

    public String official(String s, int k) {
        int[] freq = new int[26];

        for (char c : s.toCharArray()) {
            freq[c - 'a']++;
        }

        List<Character> candidates = new ArrayList();

        for (int i = 25; i >= 0; i--) {
            if (freq[i] >= k) {
                candidates.add((char)(i + 'a'));
            }
        }

        Queue<String> queue = new LinkedList();

        for (char c : candidates) {
            queue.add(String.valueOf(c));
        }

        String ans = "";

        while (!queue.isEmpty()) {
            String cur = queue.poll();

            if (cur.length() > ans.length()) {
                ans = cur;
            }

            for (char c : candidates) {
                String next = cur + c;

                if (isKRepeatedSubSeq(s, next, k)) {
                    queue.add(next);
                }
            }
        }

        return ans;
    }

    private boolean isKRepeatedSubSeq(String s, String seq, int k) {
        int index = 0;
        int matched = 0;

        for (char c : s.toCharArray()) {
            if (c == seq.charAt(index)) {
                index++;
            }

            if (index == seq.length()) {
                index = 0;
                matched++;

                if (matched == k) {
                    return true;
                }
            }
        }

        return false;
    }

    public String mySol_fail(String s, int k) {
        int[] freq = new int[26];

        for (char c : s.toCharArray()) {
            freq[c - 'a']++;
        }

        TreeMap<Integer, Integer> treeMap = new TreeMap<>();

        int totalCount = 0;

        for (int i = 0; i < freq.length; i++) {
            if (freq[i] >= k) {
                treeMap.put(i, freq[i]);
                totalCount += freq[i];
            }
        }

        Map<Integer, List<Integer>> indices = new HashMap();

        for (int i = 0; i < s.length(); i++) {
            int c = s.charAt(i) - 'a';

            if (treeMap.containsKey(c)) {
                indices.computeIfAbsent(c, key -> new ArrayList()).add(i);
            }
        }

        System.out.println(indices);

        List<int[]> allIndices = new ArrayList();

        for (int key : indices.keySet()) {
            for (int index : indices.get(key)) {
                allIndices.add(new int[] {index, key});
            }
        }

        Collections.sort(allIndices, (a, b) -> {
            return a[0] - b[0];
        });

        StringBuilder sb = new StringBuilder();

        for (int[] data : allIndices) {
            System.out.print((char)(data[1] + 'a') + ", ");
            sb.append((char)(data[1] + 'a'));
        }

        System.out.println("\n");

        return null;
    }
}