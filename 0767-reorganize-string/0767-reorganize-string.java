class Solution {
    public String reorganizeString(String s) {
        return others(s);
    }

    public String others(String s) {
        int[] freq = new int[26];
        for (char ch : s.toCharArray()) {
            freq[ch - 'a']++;
        }

        int max = 0;
        char maxChar = 'a';

        for (int i = 0; i < 26; i++) {
            int f = freq[i];

            if (f > max) {
                max = f;
                maxChar = (char)(i + 'a');
            }
        }

        // if (max + (max / 2) > s.length()) return "";

        if (max > (s.length() + 1) / 2) return "";

        char[] res = new char[s.length()];

        int index = 0;

        while (freq[maxChar - 'a'] > 0) {
            res[index] = maxChar;
            index += 2;
            freq[maxChar - 'a']--;
        }

        for (int i = 0; i < freq.length; i++) {
            while (freq[i] > 0) {
                if (index >= res.length) {
                    index = 1;
                }

                res[index] = (char)(i + 'a');
                index += 2;
                freq[i]--;
            }
        }

        return String.valueOf(res);
    }

    public String mySol_fail(String s) {
        int[] freq = new int[26];
        List<Integer> sortedHighFreq = new ArrayList();

        for (char ch : s.toCharArray()) {
            freq[ch - 'a']++;
        }

        for (int i = 0; i < 26; i++) {
            if (freq[i] > 0) {
                sortedHighFreq.add(i);
            }
        }

        Collections.sort(sortedHighFreq, (o1, o2) -> {
            return freq[o2] != freq[o1] ? freq[o2] - freq[o1] : o1 - o2;
        });

        // System.out.println(Arrays.toString(freq));
        // System.out.println(sortedHighFreq);

        StringBuilder sb = new StringBuilder();

        int left = 0;
        int right = 1;

        while (left < sortedHighFreq.size()) {
            int a = sortedHighFreq.get(left);

            if (freq[a] == 0) {
                left++;
                right = left + 1;
                continue;
            }

            if (sb.length() == 0 || sb.charAt(sb.length() - 1) - 'a' != a) {
                freq[a]--;
                sb.append((char)(a + 'a'));
            }

            while (right < sortedHighFreq.size()) {
                int b = sortedHighFreq.get(right);

                if (freq[b] == 0) {
                    right++;
                    continue;
                }

                freq[b]--;

                sb.append((char)(b + 'a'));

                break;
            }
        }

        System.out.println(sb.toString());

        return sb.length() == s.length() ? sb.toString() : "";
    }

    public String mySol_helped_official(String s) {
        int[] freq = new int[26];

        for (char ch : s.toCharArray()) {
            freq[ch - 'a']++;
        }

        Queue<int[]> queue = new PriorityQueue<>((a, b) -> {
            return b[1] != a[1] ? b[1] - a[1] : b[0] - a[0];
        });

        for (int i = 0; i < 26; i++) {
            if (freq[i] > 0) {
                queue.add(new int[] {i + 'a', freq[i]});
            }
        }

        StringBuilder ans = new StringBuilder();

        while (queue.size() > 0) {
            int[] first = queue.poll();

            if (ans.length() == 0 || ans.charAt(ans.length() - 1) != first[0]) {
                ans.append((char)(first[0]));

                if (--first[1] > 0) {
                    queue.add(first);
                }
            } else {
                if (queue.size() == 0) return "";
                
                int[] second = queue.poll();
                    
                ans.append((char)(second[0]));

                if (--second[1] > 0) {
                    queue.add(second);
                }

                queue.add(first);
            }
        }

        return ans.toString();
    }
}