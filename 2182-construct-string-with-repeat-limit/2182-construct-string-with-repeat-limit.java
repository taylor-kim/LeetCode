class Solution {
    public String repeatLimitedString(String s, int repeatLimit) {
        return editorial_pq(s, repeatLimit);
    }

    public String editorial_pq(String s, int repeatLimit) {
        StringBuilder ans = new StringBuilder();
        Map<Character, Integer> freq = new HashMap();
        Queue<Character> pq = new PriorityQueue<>((a, b) -> {
            return b - a;
        });

        for (char c : s.toCharArray()) {
            freq.put(c, freq.getOrDefault(c, 0) + 1);
        }

        for (char c : freq.keySet()) {
            pq.add(c);
        }

        while (!pq.isEmpty()) {
            char c = pq.poll();

            int use = Math.min(freq.get(c), repeatLimit);

            freq.put(c, freq.get(c) - use);

            for (int i = 0; i < use; i++) {
                ans.append(c);
            }

            if (freq.get(c) > 0 && !pq.isEmpty()) {
                char nextC = pq.peek();
                ans.append(nextC);
                freq.put(nextC, freq.get(nextC) - 1);

                if (freq.get(nextC) == 0) {
                    pq.poll();
                }

                pq.add(c);
            }
        }

        return ans.toString();
    }

    public String mySol(String s, int repeatLimit) {
        int[] freq = new int[26];
        int maxChar = 0;

        for (char c : s.toCharArray()) {
            freq[c - 'a']++;
            maxChar = Math.max(maxChar, c - 'a');
        }

        StringBuilder ans = new StringBuilder();

        while (maxChar >= 0) {
            int count = 0;
            char c = (char)(maxChar + 'a');

            while (freq[maxChar]-- > 0) {
                ans.append((char)(maxChar + 'a'));
                count++;

                if (freq[maxChar] > 0 && count == repeatLimit) {
                    int lower = maxChar - 1;

                    while (lower >= 0 && freq[lower] == 0) {
                        lower--;
                    }

                    if (lower < 0) return ans.toString();

                    freq[lower]--;

                    ans.append((char)(lower + 'a'));

                    count = 0;
                }
            }

            maxChar--;
        }

        return ans.toString();
    }
}